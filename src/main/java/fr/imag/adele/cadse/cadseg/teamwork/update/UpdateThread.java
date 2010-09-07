/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.teamwork.db.DBUtil;
import fr.imag.adele.cadse.cadseg.teamwork.db.EvolutionPoliticsInDB;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemDescriptionRef;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;
import fr.imag.adele.cadse.core.transaction.delta.ItemDelta;
import fr.imag.adele.teamwork.db.DBConnectionException;
import fr.imag.adele.teamwork.db.ModelVersionDBException;
import fr.imag.adele.teamwork.db.ModelVersionDBService;
import fr.imag.adele.teamwork.db.TransactionException;

/**
 * Thread used to perform the effective update operation.
 * 
 * @author Thomas
 * 
 */
public class UpdateThread extends Thread {

	private UpdateState			        _updateState;
	private LogicalWorkspaceTransaction	_transaction;
	private Map<UUID, Integer>          _oldRevs = new HashMap<UUID, Integer>();
	private Map<UUID, Integer>          _lastRevs = new HashMap<UUID, Integer>();
	private EvolutionPoliticsInDB       _evolutionPoliticsInDB;
	
	public UpdateThread(UpdateState updateState) {
		super("TWUpdate");
		_updateState = updateState;
		_transaction = updateState.getTransaction();
		_evolutionPoliticsInDB = new EvolutionPoliticsInDB(_transaction);
	}
	
	@Override
	public void run() {
		super.run();

		ModelVersionDBService db = CadseCore.getCadseDomain().getModelVersionDBService();
		
		// commit new state of items without outgoing links
		int i = 0;
		int itemToUpdateNb = _updateState.getOperationsToPerform().size();
		while ((i < itemToUpdateNb) && !_updateState.isFailed() && !_updateState.isUpdatePerformed()) {
			OpToPerform op = _updateState.getOperationsToPerform().get(i);
			UUID itemId = op.getItemId();
			_updateState.beginUpdatingItem(itemId);
			
			// check that evolution politics are set in DB
			try {
				if (op.isImport()) {
					ImportOperation importOp = (ImportOperation) op.getOriginalOperation();
					_evolutionPoliticsInDB.checkEvolPoliticsSetInDB(importOp.getItemType(), db);
				} else
					_evolutionPoliticsInDB.checkEvolPoliticsSetInDB(itemId, db);
			} catch (TransactionException e1) {
				e1.printStackTrace();
				Item item = _transaction.getItem(itemId);
				ItemType itemType = item.getType();
				_updateState.getOperationsToPerformErrors().addError(itemId, "Cannot set evolution politics for item type " + itemType.getName(), e1);
				_updateState.abortUpdate();
				break;
			} catch (DBConnectionException e) {
				e.printStackTrace();
				Item item = _transaction.getItem(itemId);
				ItemType itemType = item.getType();
				_updateState.getOperationsToPerformErrors().addError(itemId, "Cannot connect to database " + e.getDBUrl() +
						" to set evolution politics for item type " + itemType.getName(), e);
				_updateState.abortUpdate();
				break;
			}
			
			// update item state
			try {
				updateItemState(op, db);
			} catch (DBConnectionException e) {
				e.printStackTrace();
				_updateState.getOperationsToPerformErrors().addError(itemId, "Cannot update Item State because connection to database " + 
						e.getDBUrl() + " failed.", e);
				_updateState.abortUpdate();
				break;
			} catch (Exception e) {
				e.printStackTrace();
				_updateState.getOperationsToPerformErrors().addError(itemId, "Cannot update Item State.", e);
				_updateState.abortUpdate();
				break;
			}
			
			_updateState.markStateAsUpdated(itemId);

			i++;
		}

		// commit outgoing links
		i = 0;
		while ((i < itemToUpdateNb) && !_updateState.isFailed() && !_updateState.isUpdatePerformed()) {
			OpToPerform op = _updateState.getOperationsToPerform().get(i);
			UUID itemId = op.getItemId();

			try {
				updateItemLinks(op, db);
			} catch (DBConnectionException e) {
				e.printStackTrace();
				_updateState.getOperationsToPerformErrors().addError(itemId, "Cannot update Item outgoing links because connection to database " + 
						e.getDBUrl() + " failed.", e);
				_updateState.abortUpdate();
				break;
			} catch (Exception e) {
				_updateState.getOperationsToPerformErrors().addError(itemId, "Cannot update Item ougoing links.", e);
				_updateState.abortUpdate();
				e.printStackTrace();
				break;
			}
			
			_updateState.markLinksAsUpdated(itemId);
			
			i++;
		}

		// update contents
		i = 0;
		while ((i < itemToUpdateNb) && !_updateState.isFailed() && !_updateState.isUpdatePerformed()) {
			OpToPerform op = _updateState.getOperationsToPerform().get(i);
			UUID itemId = op.getItemId();

			try {
				//TODO implement it
				if (false) {
					throw new DBConnectionException("", "", null); // TODO remove when implemented
				}
			} catch (DBConnectionException e) {
				e.printStackTrace();
				_updateState.getOperationsToPerformErrors().addError(
						itemId, "Cannot update Item content because connection to database " + 
						e.getDBUrl() + " failed.", e);
				_updateState.abortUpdate();
				break;
			} catch (Exception e) {
				_updateState.getOperationsToPerformErrors().addError(itemId, "Cannot update Item content.", e);
				_updateState.abortUpdate();
				e.printStackTrace();
				break;
			}
			
			_updateState.markContentsAsUpdated(itemId);
			
			i++;
		}
		
		// reset modified state
		i = 0;
		while ((i < itemToUpdateNb) && !_updateState.isFailed()) {
			OpToPerform op = _updateState.getOperationsToPerform().get(i);
			UUID itemId = op.getItemId();

			try {
				if (!op.isUpdate())
					TWUtil.resetTWState(_transaction.getItem(itemId));
			} catch (Exception e) {
				Item item = _transaction.getItem(itemId);
				_updateState.getOperationsToPerformErrors().addError(
						itemId, "Cannot update TeamWork state of item " + item.getName(), e);
				_updateState.abortUpdate();
				e.printStackTrace();
				break;
			}
			
			// notify end of item commit
			_updateState.markAsUpdated(itemId);

			i++;
		}
		
		// finish transaction
		try {
			if (!_updateState.isFailed()) {
				_updateState.endUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			_updateState.abortUpdate();
		}
	}

	private void updateItemLinks(OpToPerform op, ModelVersionDBService db) throws TransactionException, ModelVersionDBException, DBConnectionException {
		UUID itemId = op.getItemId();
		
		if (op.isImport()) {
			//TODO implement it
			
			ItemDelta item = _transaction.getItem(itemId);
			item.finishLoad();
			return;
		} else {

			ItemDelta item = _transaction.getItem(itemId);
			int rev = item.getVersion();
			ItemType itemType = item.getType();
			UUID itemTypeId = itemType.getId();
			
			// set repository for this item
			DBUtil.connectToDB(db, itemType);

			// test if a concurrent work has been performed
			int lastRev = item.getVersion(); // before this commit
			Integer lastRevInt = _lastRevs.get(itemId);
			if (lastRevInt != null)
				lastRevInt = lastRevInt.intValue();

			int oldRev = item.getVersion();
			Integer oldRevInt = _oldRevs.get(itemId);
			if (oldRevInt != null)
				oldRevInt = oldRevInt.intValue();

			if (op.isRevert()) {
				List<IAttributeType<?>> modifiedAttrTypes = computeAttrToUpdate(
						item, true, true);

				for (IAttributeType<?> attrType : modifiedAttrTypes) {
					UUID linkTypeId = attrType.getId();

					// TODO implement it
				}
			}

			if (op.isUpdate()) {
				// TODO implement it
			}
		}
	}

	private void updateItemState(OpToPerform op, ModelVersionDBService db) throws TransactionException, ModelVersionDBException, DBConnectionException {
		UUID itemId = op.getItemId();
		
		int rev = 0;
		ItemType itemType = null;
		ItemDelta item = null;
		if (op.isImport()) {
			ImportOperation importOp = (ImportOperation) op.getOriginalOperation();
			itemType = importOp.getItemType();
			rev = importOp.getDestinationRevNb();
			
			DBUtil.connectToDB(db, itemType);
			
			Map<String, Object> stateMap = db.getObjectState(itemId, rev);
			String qualifiedName = (String) stateMap.get(CadseGCST.ITEM_at_QUALIFIED_NAME);
			String name = (String) stateMap.get(CadseGCST.ITEM_at_NAME);
			
			ItemDescriptionRef itemDesc = new ItemDescriptionRef(itemId, itemType, qualifiedName, name);
			try {
				item = _transaction.loadItem(itemDesc);
				item.setLoaded(true);
				for (IAttributeType<?> attrType : itemType.getAllAttributeTypes()) {
					// links are updated in a second step
					if (TWUtil.isLinkType(attrType))
						continue;
					
					// ignore transient attributes
					if (TWUtil.isToIgnoreForUpdate(attrType))
						continue;
					
					// ignore transient attributes
					if (TWUtil.isTransient(attrType))
						continue;
					
					String attrName = attrType.getName();
					
					item.loadAttribute(attrType, stateMap.get(attrName));
				}
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				item = null;
			}
		} else {
			item = _transaction.getItem(itemId);
			rev = item.getVersion();
			itemType = item.getType();
			
			DBUtil.connectToDB(db, itemType);
		}
		
		int lastRev = db.getLastObjectRevNb(itemId);
		if (rev == ModelVersionDBService.LAST) {
			rev = lastRev;
		}
		int oldRev = rev;
		
		if (op.isRevert()) {
			List<IAttributeType<?>> modifiedAttrTypes = computeAttrToUpdate(item, true, false);
			
			for (IAttributeType<?> attrType : modifiedAttrTypes) {
				String attrName = attrType.getName();
				
				Object newValue = db.getObjectValue(itemId, rev, attrName);
				
				try {
					item.setAttribute(attrType, newValue);
				} catch (CadseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (op.isUpdate()) {
			UpdateOperation updateOp = (UpdateOperation) op.getOriginalOperation();
			int toRev = updateOp.getDestinationRevNb();
			rev = toRev;
			if (rev == ModelVersionDBService.LAST) {
				rev = lastRev;
			}
			
			List<IAttributeType<?>> allAttrTypes = computeAttrToUpdate(item, false, false);
			
			for (IAttributeType<?> attrType : allAttrTypes) {
				String attrName = attrType.getName();
				
				Object oldValue = db.getObjectValue(itemId, oldRev, attrName);
				if (TWUtil.hasReplaceUpdatePolitic(attrType)) {
					try {
						item.setAttribute(attrType, oldValue);
					} catch (CadseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}
				
				Object newValue = item.getAttribute(attrType);
				Object valueToSetInDB = db.getObjectValue(itemId, toRev, attrName);
				if (TWUtil.hasComputeUpdatePolitic(attrType) || TWUtil.hasMergeUpdatePolitic(attrType)) {
					boolean hasBeenModifiedInWS = attrType.isTWValueModified(oldValue, newValue);
					Object valueToset;
					if (!hasBeenModifiedInWS) {
						valueToset = valueToSetInDB;
					} else {
						if (!AttributeManager.isIsListAttribute(attrType)) {
							String errorMsg = "Merge values " + oldValue
									+ " and " + newValue + " of attribute "
									+ attrName + " of item " + item.getName()
									+ " has failed.";
							_updateState.getOperationsToPerformErrors()
									.addError(itemId, errorMsg);

							throw new IllegalStateException(errorMsg);
						}

						valueToset = TWUtil.mergeLists(oldValue,
								valueToSetInDB, newValue);
					}
					try {
						item.setAttribute(attrType, valueToset);
					} catch (CadseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		String comment = (String) db.getObjectValue(itemId, rev, DBUtil.TW_COMMENT_ATTR_NAME);
		String user = (String) db.getObjectValue(itemId, rev, DBUtil.TW_COMMITER_ATTR_NAME);
		Date commitDate = (Date) db.getObjectValue(itemId, rev, DBUtil.TW_COMMIT_DATE_ATTR_NAME);
		
		try {
			_oldRevs.put(itemId, oldRev);
			_lastRevs.put(itemId, lastRev);
			
			item.setVersion(rev);
			item.setAttribute(CadseGCST.ITEM_at_TWLAST_COMMENT_, comment);
			item.setAttribute(CadseGCST.ITEM_at_COMMITTED_BY_, user);
			item.setAttribute(CadseGCST.ITEM_at_COMMITTED_DATE_, commitDate);
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * @param item
	 * @param isRevert
	 * @param onlyLinkTypes if true, return link types else return attribute types which are not link types
	 * @return
	 */
	private List<IAttributeType<?>> computeAttrToUpdate(Item item, boolean isRevert, boolean onlyLinkTypes) {
		List<IAttributeType<?>> attrTypesToUpdate = new ArrayList<IAttributeType<?>>();
		for (IAttributeType<?> attrType : item.getLocalAllAttributeTypes()) {
			
			// links are updated in a second step
			if (!acceptAttrType(attrType, onlyLinkTypes))
				continue;
			
			// ignore transient attributes
			if (TWUtil.isToIgnoreForUpdate(attrType))
				continue;
			
			// ignore transient attributes
			if (TWUtil.isTransient(attrType))
				continue;
			
			if (!isRevert || item.isTWAttributeModified(attrType)) {
				attrTypesToUpdate.add(attrType);
			}
		}
		
		return attrTypesToUpdate;
	}

	private boolean acceptAttrType(IAttributeType<?> attrType, boolean acceptLinkTypes) {
		boolean isLinkType = TWUtil.isLinkType(attrType);
		if (acceptLinkTypes  && isLinkType)
			return true;
		if (!acceptLinkTypes  && !isLinkType)
			return true;
		
		return false;
	}
}

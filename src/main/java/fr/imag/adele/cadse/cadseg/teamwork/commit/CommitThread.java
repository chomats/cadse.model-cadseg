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
 */
package fr.imag.adele.cadse.cadseg.teamwork.commit;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import fr.imag.adele.cadse.cadseg.teamwork.TeamWorkPreferences;
import fr.imag.adele.cadse.cadseg.teamwork.db.DBConnexionParams;
import fr.imag.adele.cadse.cadseg.type.TWEvol;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.teamwork.db.ModelVersionDBException;
import fr.imag.adele.teamwork.db.ModelVersionDBService;
import fr.imag.adele.teamwork.db.TransactionException;

/**
 * Thread used to perform the effective commit operation.
 * 
 * @author Thomas
 * 
 */
public class CommitThread extends Thread {

	private CommitState			_commitState;
	private LogicalWorkspace	_wl;
	private Map<ItemType, Boolean> evolPoliticsSetIndDB = new HashMap<ItemType, Boolean>(); 

	public CommitThread(CommitState commitState, LogicalWorkspace wl) {
		super("TWCommit");
		_commitState = commitState;
		_wl = wl;
	}

	@Override
	public void run() {
		super.run();

		ModelVersionDBService db = CadseCore.getCadseDomain().getModelVersionDBService();

		// start a transaction
		try {
			db.beginTransaction();
		} catch (TransactionException e1) {
			e1.printStackTrace();
			// TODO should add a global error message
			_commitState.abortCommit(); 
			return;
		}

		// commit new state of items without outgoing links
		int i = 0;
		int itemToCommitNb = _commitState.getItemsToCommit().size();
		while ((i < itemToCommitNb) && !_commitState.isFailed() && !_commitState.isCommitPerformed()) {
			UUID itemId = _commitState.getItemsToCommit().get(i);
			_commitState.beginCommittingItem(itemId);
			
			// check that evolution politics are set in DB
			try {
				checkEvolPoliticsSetInDB(itemId, db);
			} catch (TransactionException e1) {
				e1.printStackTrace();
				// TODO should add a global error message
				_commitState.abortCommit();
				return;
			}

			try {
				commitItemState(itemId, db);
			} catch (Exception e) {
				_commitState.abortCommit();
				e.printStackTrace();
				break;
			}

			i++;
		}

		// commit outgoing links
		i = 0;
		while ((i < itemToCommitNb) && !_commitState.isFailed() && !_commitState.isCommitPerformed()) {
			UUID itemId = _commitState.getItemsToCommit().get(i);

			try {
				commitItemLinks(itemId, db);
			} catch (Exception e) {
				_commitState.abortCommit();
				e.printStackTrace();
			}
			
			i++;
		}

		// commit contents
		// TODO implement it
		
		// reset modified state
		i = 0;
		while ((i < itemToCommitNb) && !_commitState.isFailed()) {
			UUID itemId = _commitState.getItemsToCommit().get(i);

			try {
				TWUtil.resetTWState(_wl.getItem(itemId));
			} catch (Exception e) {
				_commitState.abortCommit();
				e.printStackTrace();
			}
			
			// notify end of item commit
			_commitState.markAsCommitted(itemId);

			i++;
		}
		

		// finish transaction
		try {
			if (_commitState.isFailed()) {
				db.rollbackTransaction();
			} else {
				db.commitTransaction();
				_commitState.endCommit();
			}
		} catch (TransactionException e) {
			e.printStackTrace();
			_commitState.abortCommit();
		}
	}

	private void checkEvolPoliticsSetInDB(UUID itemId, ModelVersionDBService db) throws TransactionException {
		Item item = _wl.getItem(itemId);
		ItemType itemType = item.getType();
		UUID itemTypeId = itemType.getId();
		
		Boolean isAlreadySet = evolPoliticsSetIndDB.get(itemType);
		if ((isAlreadySet != null) && isAlreadySet)
			return;
		
		connectToDB(db, itemType);
		for (IAttributeType<?> attrType : itemType.getAllAttributeTypes()) {
			
			if (TWUtil.isTWAttribute(attrType))
				continue;
			if (TWUtil.isInternalCadseAttribute(attrType))
				continue;
			
			try {
				if (isLinkType(attrType)) {
					db.setLinkSrcVersionSpecific(attrType.getId(), attrType.isTWRevSpecific());
					db.setLinkDestVersionSpecific(attrType.getId(), !TWUtil.isBranchDestination(attrType));
				} else
					db.setObjectAttVersionSpecific(itemTypeId, getAttributeName(attrType), attrType.isTWRevSpecific());
			} catch (ModelVersionDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String getAttributeName(IAttributeType<?> attrType) {
		return attrType.getName();
	}

	private void commitItemLinks(UUID itemId, ModelVersionDBService db) {
		// TODO implement it
	}

	private void commitItemState(UUID itemId, ModelVersionDBService db) throws ModelVersionDBException,
			TransactionException {

		// commit item model database
		Item item = _wl.getItem(itemId);
		ItemType itemType = item.getType();

		// set repository for this item
		connectToDB(db, itemType);

		// compute modified attributes
		Map<String, Object> stateMap = new TreeMap<String, Object>();
		Map<String, IAttributeType> attrTypeMap = new TreeMap<String, IAttributeType>();
		for (IAttributeType attrType : item.getLocalAllAttributeTypes()) {
			
			// links are updated in a second step
			if (isLinkType(attrType))
				continue;
			
			// ignore transient attributes
			if (TWUtil.isToIgnoreForCommit(attrType))
				continue;
			
			// ignore transient attributes
			if (TWUtil.isTransient(attrType))
				continue;
			
			stateMap.put(attrType.getName(), item.getAttribute(attrType));

			if (item.isTWAttributeModified(attrType)) {
				attrTypeMap.put(attrType.getName(), attrType);
			}
		}
		int rev = item.getVersion();
		if (rev == 0) {
			// item not already in db
			UUID itemTypeId = itemType.getId();
			rev = db.createObject(itemId, itemTypeId,
					stateMap, isItemType(item));
		} else {
			// an item revision already exists in db
			int lastRev = db.getLastObjectRevNb(itemId);
			
			if (item.isRequireNewRev()) {
				rev = db.createNewObjectRevision(itemId, lastRev);
				//TODO
			} else {
				
				//TODO
			}
		}
		try {
			item.setAttribute(CadseGCST.ITEM_at_TW_VERSION_, rev);
			item.setAttribute(CadseGCST.ITEM_at_TWLAST_COMMENT_, _commitState.getComment());
			String user = TeamWorkPreferences.getUserName();
			item.setAttribute(CadseGCST.ITEM_at_COMMITTED_BY_, user);
			item.setAttribute(CadseGCST.ITEM_at_COMMITTED_DATE_, new Date(System.currentTimeMillis()));
			
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void connectToDB(ModelVersionDBService db, ItemType itemType)
			throws TransactionException {
		Item cadseRuntime = itemType.getOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_CADSE, true);
		String cadseName = cadseRuntime.getName();
		DBConnexionParams dbParams = DBConnexionParams.getConnectionParams(cadseName);
		String url = dbParams.getUrl();
		String login = dbParams.getLogin();
		String pwd = dbParams.getPassword();
		if ((login == null) || (login.trim().length() == 0)) {
			db.setConnectionURL(url);
		} else {
			db.setConnectionURL(url, login, pwd);
		}
	}

	private boolean isLinkType(IAttributeType attrType) {
		return attrType.isInstanceOf(CadseGCST.LINK_TYPE);
	}

	/**
	 * Returns true if this item is an item type.
	 * 
	 * @param item
	 *            an item
	 * @return true if this item is an item type.
	 */
	private boolean isItemType(Item item) {
		return item.isInstanceOf(CadseGCST.ITEM_TYPE);
	}
}

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import fr.imag.adele.cadse.cadseg.teamwork.TeamWorkPreferences;
import fr.imag.adele.cadse.cadseg.teamwork.db.DBConnexionParams;
import fr.imag.adele.cadse.cadseg.teamwork.db.DBUtil;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.teamwork.db.ModelVersionDBException;
import fr.imag.adele.teamwork.db.ModelVersionDBService;
import fr.imag.adele.teamwork.db.Revision;
import fr.imag.adele.teamwork.db.TransactionException;

/**
 * Thread used to perform the effective commit operation.
 * 
 * @author Thomas
 * 
 */
public class CommitThread extends Thread {

	public static final String TW_COMMIT_DATE_ATTR_NAME = "TW_COMMIT_DATE";
	public static final String TW_COMMITER_ATTR_NAME = "TW_COMMITER";
	public static final String TW_COMMENT_ATTR_NAME = "TW_COMMENT";
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
		
		DBUtil.connectToDB(db, itemType);
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

	private void commitItemLinks(UUID itemId, ModelVersionDBService db) throws TransactionException {
		
		// commit item model database
		Item item = _wl.getItem(itemId);
		ItemType itemType = item.getType();

		// set repository for this item
		DBUtil.connectToDB(db, itemType);
		
		for (IAttributeType<?> attrType : item.getLocalAllAttributeTypes()) {
			
			// only links are updated in this step
			if (!isLinkType(attrType))
				continue;
			
			LinkType linkType = (LinkType) attrType;
			
			// ignore internal attributes
			if (TWUtil.isToIgnoreForCommit(attrType))
				continue;
			
			// ignore transient attributes
			if (TWUtil.isTransient(attrType))
				continue;
			
			try {
				if (item.isRequireNewRev()) {
					commitItemLinksWhenNewRev(item, linkType, db);
				} else if (item.isTWAttributeModified(attrType)) {
					// link type has been modified and current revision should be updated
					//TODO
				}
			} catch (ModelVersionDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void commitItemLinksWhenNewRev(Item item, LinkType linkType, ModelVersionDBService db) throws ModelVersionDBException {
		UUID itemId = item.getId();
		int rev = item.getVersion();
		UUID linkTypeId = linkType.getId();
		
		if (TWUtil.hasReplaceCommitPolitic(linkType)) {
			// delete links in base which no more exist
			Set<UUID> destIdsOfLinksToRemove = new HashSet<UUID>();
			for (Revision destRevision : db.getLinkDestRev(linkTypeId, itemId, rev)) {
				UUID destId = destRevision.getId();
				if (!TWUtil.isPresentInWorkspace(destId, _wl))
					destIdsOfLinksToRemove.add(destId);
			}
			for (UUID destId : destIdsOfLinksToRemove) {
				db.deleteLink(linkTypeId, itemId, rev, destId);
			}
			
			// update links : 
			// - update compatible revisions
			// - add new links
			for (Link link : item.getOutgoingLinks(linkType)) {
				Item destItem = link.getDestination(false);
				UUID destId = destItem.getId();
				
				//TODO
				
				int destRev = 0;
				if (link.isLinkResolved())
					destRev = destItem.getVersion();
				else
					destRev = link.getVersion();
				Map<String, Object> stateMap = new HashMap<String, Object>();
				stateMap.put(CadseGCST.ITEM_at_NAME, destItem.getName());
				stateMap.put(CadseGCST.ITEM_at_QUALIFIED_NAME, destItem.getQualifiedName());
				
				db.addLink(linkTypeId, itemId, rev, destId, destRev, stateMap);
			}
			return;
		}
		
		if (TWUtil.hasReconcileCommitPolitic(linkType)) {
//			Object reconciledValue = TWUtil.mergeLists(oldValue, newValue);
//			try {
//				item.setAttribute(attrType, reconciledValue);
//			} catch (CadseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			db.setObjectValue(itemId, rev, attrName, reconciledValue);
		} else if (TWUtil.hasConflictCommitPolitic(linkType)) {
//			if (attrType.isTWValueModified(oldValue, newValue)) {
//				// TODO throw an error
//				throw new IllegalStateException("Conflict values " + 
//					oldValue + " and " + newValue + " of attribute " + 
//					attrName + " of item " + item.getName());
//			} else
//				db.setObjectValue(itemId, rev, attrName, newValue);
		}
	}

	private void commitItemState(UUID itemId, ModelVersionDBService db) throws ModelVersionDBException,
			TransactionException {

		// commit item model database
		Item item = _wl.getItem(itemId);
		ItemType itemType = item.getType();

		// set repository for this item
		DBUtil.connectToDB(db, itemType);

		// compute initial state
		Map<String, Object> stateMap = new TreeMap<String, Object>();
		String comment = _commitState.getComment();
		stateMap.put(TW_COMMENT_ATTR_NAME, comment);
		String user = TeamWorkPreferences.getUserName();
		stateMap.put(TW_COMMITER_ATTR_NAME, user);
		Date commitDate = new Date(System.currentTimeMillis());
		stateMap.put(TW_COMMIT_DATE_ATTR_NAME, commitDate);
		
		List<IAttributeType<?>> modifiedAttrTypes = new ArrayList<IAttributeType<?>>();
		for (IAttributeType<?> attrType : item.getLocalAllAttributeTypes()) {
			
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

			if (item.isTWAttributeModified(attrType))
				modifiedAttrTypes.add(attrType);
		}
		int rev = item.getVersion();
		if (rev == 0) {
			// item not already in db
			UUID itemTypeId = itemType.getId();
			rev = db.createObject(itemId, itemTypeId,
					stateMap, isItemType(item));
		} else {
			// an item revision already exists in db
			int oldRev = rev;
			int lastRev = db.getLastObjectRevNb(itemId);
			
			if (item.isRequireNewRev()) {
				// TODO check that lastRev must be used in place of current revision
				rev = db.createNewObjectRevision(itemId, lastRev); 
			} 
			for (IAttributeType<?> attrType : modifiedAttrTypes) {
				String attrName = attrType.getName();
				
				Object newValue = stateMap.get(attrName);
				if (TWUtil.hasReplaceCommitPolitic(attrType)) {
					db.setObjectValue(itemId, rev, attrName, newValue);
					continue;
				}
				
				Object oldValue = db.getObjectValue(itemId, oldRev, attrName);
				Object lastValue = db.getObjectValue(itemId, lastRev, attrName);
				if (TWUtil.hasReconcileCommitPolitic(attrType)) {
					Object reconciledValue = TWUtil.mergeLists(oldValue, lastValue, newValue);
					try {
						item.setAttribute(attrType, reconciledValue);
					} catch (CadseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					db.setObjectValue(itemId, rev, attrName, reconciledValue);
				} else if (TWUtil.hasConflictCommitPolitic(attrType)) {
					if (attrType.isTWValueModified(oldValue, newValue)) {
						// TODO throw an error
						throw new IllegalStateException("Conflict values " + 
							oldValue + " and " + newValue + " of attribute " + 
							attrName + " of item " + item.getName());
					} else
						db.setObjectValue(itemId, rev, attrName, newValue);
				}
			}
			db.setObjectValue(itemId, rev, TW_COMMENT_ATTR_NAME, comment);
			db.setObjectValue(itemId, rev, TW_COMMITER_ATTR_NAME, user);
			db.setObjectValue(itemId, rev, TW_COMMIT_DATE_ATTR_NAME, commitDate);
		}
		try {
			item.setVersion(rev);
			item.setAttribute(CadseGCST.ITEM_at_TWLAST_COMMENT_, comment);
			item.setAttribute(CadseGCST.ITEM_at_COMMITTED_BY_, user);
			item.setAttribute(CadseGCST.ITEM_at_COMMITTED_DATE_, commitDate);
			
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isLinkType(IAttributeType<?> attrType) {
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

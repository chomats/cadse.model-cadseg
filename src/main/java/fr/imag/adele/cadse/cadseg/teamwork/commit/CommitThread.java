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

import fr.imag.adele.cadse.as.scm.SCMException;
import fr.imag.adele.cadse.as.scm.SCMRevision;
import fr.imag.adele.cadse.as.scm.SCMService;
import fr.imag.adele.cadse.cadseg.teamwork.TeamWorkPreferences;
import fr.imag.adele.cadse.cadseg.teamwork.db.DBConnexionParams;
import fr.imag.adele.cadse.cadseg.teamwork.db.DBUtil;
import fr.imag.adele.cadse.cadseg.teamwork.db.EvolutionPoliticsInDB;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.enumdef.TWCommitKind;
import fr.imag.adele.cadse.core.enumdef.TWEvol;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.internal.CadseDomainImpl;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;
import fr.imag.adele.teamwork.db.DBConnectionException;
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

	private CommitState			        _commitState;
	private LogicalWorkspaceTransaction	_transaction;
	private Map<UUID, Integer>          _oldRevs = new HashMap<UUID, Integer>();
	private Map<UUID, Integer>          _lastRevs = new HashMap<UUID, Integer>();
	private EvolutionPoliticsInDB       _evolutionPoliticsInDB;

	public CommitThread(CommitState commitState, LogicalWorkspaceTransaction transaction) {
		super("TWCommit");
		_commitState = commitState;
		_transaction = transaction;
		_evolutionPoliticsInDB = new EvolutionPoliticsInDB(transaction);
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
			_commitState.getErrors().addError("Cannot open a transaction.", e1);
			_commitState.abortCommit();
		}

		// commit new state of items without outgoing links
		int i = 0;
		int itemToCommitNb = _commitState.getItemsToCommitRequirements().size();
		while ((i < itemToCommitNb) && !_commitState.isFailed() && !_commitState.isCommitPerformed()) {
			UUID itemId = _commitState.getItemsToCommitRequirements().get(i);
			_commitState.beginCommittingItem(itemId);
			
			// check that evolution politics are set in DB
			try {
				_evolutionPoliticsInDB.checkEvolPoliticsSetInDB(itemId, db);
			} catch (TransactionException e1) {
				e1.printStackTrace();
				Item item = _transaction.getItem(itemId);
				ItemType itemType = item.getType();
				_commitState.getErrors().addError(itemId, "Cannot set evolution politics for item type " + itemType.getName(), e1);
				_commitState.abortCommit();
				break;
			} catch (DBConnectionException e) {
				e.printStackTrace();
				Item item = _transaction.getItem(itemId);
				ItemType itemType = item.getType();
				_commitState.getErrors().addError(itemId, "Cannot connect to database " + e.getDBUrl() +
						" to set evolution politics for item type " + itemType.getName(), e);
				_commitState.abortCommit();
				break;
			}

			try {
				commitItemState(itemId, db);
			} catch (DBConnectionException e) {
				e.printStackTrace();
				_commitState.getErrors().addError(itemId, "Cannot commit Item State because connection to database " + 
						e.getDBUrl() + " failed.", e);
				_commitState.abortCommit();
				break;
			} catch (Exception e) {
				_commitState.getErrors().addError(itemId, "Cannot commit Item State.");
				_commitState.abortCommit();
				e.printStackTrace();
				break;
			}
			
			_commitState.markStateAsCommitted(itemId);

			i++;
		}

		// commit outgoing links
		i = 0;
		while ((i < itemToCommitNb) && !_commitState.isFailed() && !_commitState.isCommitPerformed()) {
			UUID itemId = _commitState.getItemsToCommitRequirements().get(i);

			try {
				commitItemLinks(itemId, db);
			} catch (DBConnectionException e) {
				e.printStackTrace();
				_commitState.getErrors().addError(itemId, "Cannot commit Item outgoing links because connection to database " + 
						e.getDBUrl() + " failed.", e);
				_commitState.abortCommit();
				break;
			} catch (Exception e) {
				_commitState.getErrors().addError(itemId, "Cannot commit Item ougoing links.");
				_commitState.abortCommit();
				e.printStackTrace();
				break;
			}
			
			_commitState.markLinksAsCommitted(itemId);
			
			i++;
		}

		// commit contents
		SCMService scmService = ((CadseDomainImpl) CadseCore.getCadseDomain()).getSCMService();
		i = 0;
		while ((i < itemToCommitNb) && !_commitState.isFailed() && !_commitState.isCommitPerformed()) {
			UUID itemId = _commitState.getItemsToCommitRequirements().get(i);
			
			Item item = _transaction.getItem(itemId);
			ContentItem contentItem = item.getContentItem();
			if (contentItem != null) {
				
				// TODO compute content to commit, take into account commit propagation
				try {
					commitItemContent(item, contentItem, scmService, db);
				} catch (DBConnectionException e) {
					e.printStackTrace();
					_commitState.getErrors().addError(
							itemId,
							"Cannot commit Item content because connection to database "
									+ e.getDBUrl() + " failed.", e);
					_commitState.abortCommit();
					break;
				} catch (SCMException e) {
					e.printStackTrace();
					_commitState.getErrors().addError(
							itemId,
							"Cannot commit Item content because commit command failed.", e);
					_commitState.abortCommit();
					break;
				} catch (Exception e) {
					_commitState.getErrors().addError(itemId,
							"Cannot commit Item content.");
					_commitState.abortCommit();
					e.printStackTrace();
					break;
				}
			}
			
			_commitState.markContentsAsCommitted(itemId);
			
			i++;
		}
		
		// reset modified state
		i = 0;
		while ((i < itemToCommitNb) && !_commitState.isFailed()) {
			UUID itemId = _commitState.getItemsToCommit().get(i);

			try {
				TWUtil.resetTWState(_transaction.getItem(itemId));
			} catch (Exception e) {
				Item item = _transaction.getItem(itemId);
				_commitState.getErrors().addError(itemId, "Cannot update TeamWork state of item " + item.getName(), e);
				_commitState.abortCommit();
				e.printStackTrace();
				break;
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

	private void commitItemContent(Item item, ContentItem contentItem,
			SCMService scmService, ModelVersionDBService db) throws SCMException, DBConnectionException, TransactionException, ModelVersionDBException {
		UUID itemId = item.getId();
		ItemType itemType = item.getType();
		String cadseName = TWUtil.getCadse(itemType);
		
		String scmRepoUrl = contentItem.getSCMRepoUrl();
		if (scmRepoUrl == null) {
			scmRepoUrl = scmService.getSCMRepositoryURL(contentItem);
			if (scmRepoUrl == null)  {
				DBConnexionParams dbParams = DBConnexionParams.getConnectionParams(cadseName);
				scmRepoUrl = dbParams.getDefaultContentRepoURL();
			}
			contentItem.setSCMRepoUrl(scmRepoUrl);
		}

		String comment = _commitState.getComment();
		SCMRevision scmRev = scmService.commitContent(contentItem, comment);
		
		// set repository for this item
		DBUtil.connectToDB(db, itemType);
		
		// save content meta information
		UUID contentId = contentItem.getId();
		
		// compute initial state
		Map<String, Object> stateMap = new TreeMap<String, Object>();
		stateMap.put(DBUtil.TW_COMMENT_ATTR_NAME, comment);
		String user = TeamWorkPreferences.getUserName();
		stateMap.put(DBUtil.TW_COMMITER_ATTR_NAME, user);
		Date commitDate = new Date(System.currentTimeMillis());
		stateMap.put(DBUtil.TW_COMMIT_DATE_ATTR_NAME, commitDate);
		stateMap.put(DBUtil.SCM_REPO_URL_ATTR_NAME, scmRepoUrl);
		String scmRevAttrName = DBUtil.SCM_REV_ATTR_NAME;
		stateMap.put(scmRevAttrName, scmRev.getRevision());
		
		int contentRev = contentItem.getVersion();
		int oldContentRev = contentRev;
		UUID contentItemTypeId = CadseGCST.CONTENT_ITEM.getId();
		if (contentRev == 0) {
			// item not already in db
			contentRev = db.createObject(contentId, contentItemTypeId,
					stateMap, false);
		} else {
			// an item revision already exists in db
			int lastRev = db.getLastObjectRevNb(contentId);
			
			if (item.isRequireNewRev()) {
				// TODO check that lastRev must be used in place of current revision
				contentRev = db.createNewObjectRevision(contentId, lastRev); 
			}
			
			Object newValue = stateMap.get(scmRevAttrName);
			TWEvol contentEvol = TWUtil.getContentEvol(item);
			if (TWCommitKind.none.equals(contentEvol)) {
				db.setObjectState(contentId, contentRev, stateMap);
			} else if (TWCommitKind.conflict.equals(contentEvol)) {
				Object oldValue = db.getObjectValue(itemId, oldContentRev, scmRevAttrName);
				Object lastValue = db.getObjectValue(itemId, lastRev, scmRevAttrName);
				
				if (CadseGCST.CONTENT_ITEM_at_SCM_REVISION_.isTWValueModified(
						oldValue, newValue)
						&& CadseGCST.CONTENT_ITEM_at_SCM_REVISION_
								.isTWValueModified(oldValue, lastValue)) {

					String errorMsg = "Conflict values " + oldValue + " and "
							+ newValue + " of attribute "
							+ CadseGCST.CONTENT_ITEM_at_SCM_REVISION
							+ " of item " + item.getName();
					_commitState.getErrors().addError(itemId, errorMsg);

					throw new IllegalStateException(errorMsg);
				} else
					db.setObjectState(contentId, contentRev, stateMap);
			}
		}
		
		int rev = item.getVersion();
		db.deleteOutgoingLinks(CadseGCST.ITEM_lt_CONTENTS.getId(), itemId, rev);
		db.addLink(CadseGCST.ITEM_lt_CONTENTS.getId(), itemId, rev, contentId, contentRev, null, true);
		
		try {
			contentItem.setVersion(contentRev);
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.setObjectValue(contentId, contentRev, DBUtil.TW_COMMENT_ATTR_NAME, comment);
		db.setObjectValue(contentId, contentRev, DBUtil.TW_COMMITER_ATTR_NAME, user);
		db.setObjectValue(contentId, contentRev, DBUtil.TW_COMMIT_DATE_ATTR_NAME, commitDate);
	}

	private void commitItemLinks(UUID itemId, ModelVersionDBService db) throws TransactionException, DBConnectionException {
		
		// commit item model database
		Item item = _transaction.getItem(itemId);
		ItemType itemType = item.getType();

		// set repository for this item
		DBUtil.connectToDB(db, itemType);
		
		for (IAttributeType<?> attrType : item.getLocalAllAttributeTypes()) {
			
			// only links are updated in this step
			if (!TWUtil.isLinkType(attrType))
				continue;
			
			LinkType linkType = (LinkType) attrType;
			
			// ignore internal attributes
			if (TWUtil.isToIgnoreForCommit(attrType))
				continue;
			
			// ignore transient attributes
			if (TWUtil.isTransient(attrType))
				continue;
			
			try {
				if (item.isTWAttributeModified(attrType))
					commitItemLinks(item, linkType, db);
			} catch (ModelVersionDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void commitItemLinks(Item item, LinkType linkType, ModelVersionDBService db) throws ModelVersionDBException {
		UUID itemId = item.getId();
		int rev = item.getVersion();
		if (!linkType.isTWRevSpecific())
			rev = ModelVersionDBService.ALL;
		UUID linkTypeId = linkType.getId();
		
		// test if a concurrent work has been performed
		int lastRev = item.getVersion(); // before this commit
		Integer lastRevInt = _lastRevs.get(itemId);
		if (lastRevInt != null)
			lastRevInt = lastRevInt.intValue();
		
		int oldRev = item.getVersion();
		Integer oldRevInt = _oldRevs.get(itemId);
		if (oldRevInt != null)
			oldRevInt = oldRevInt.intValue();
		
		List<UUID> oldDestIds = new ArrayList<UUID>();
		for (Revision destRevision : db.getLinkDestRev(linkTypeId, itemId, oldRev)) {
			UUID destId = destRevision.getId();
			oldDestIds.add(destId);
		}
		List<UUID> lastDestIds = new ArrayList<UUID>();
		for (Revision destRevision : db.getLinkDestRev(linkTypeId, itemId, lastRev)) {
			UUID destId = destRevision.getId();
			lastDestIds.add(destId);
		}
		List<UUID> newDestIds = new ArrayList<UUID>();
		for (Item destItem : item.getOutgoingItems(linkType, false)) {
			UUID destId = destItem.getId();
			newDestIds.add(destId);
		}
		
		List<UUID> destIdsToSet = newDestIds;
		if ((oldRev != 0) && hasConflict(oldDestIds, lastDestIds, newDestIds)) {
			if (TWUtil.hasConflictCommitPolitic(linkType)) {
				String errorMsg = "Links " + linkType.getName()
						+ " have been changed concurently.";
				_commitState.getErrors().addError(itemId, errorMsg);
				return;
			} else if (TWUtil.hasReconcileCommitPolitic(linkType)) {
				destIdsToSet = TWUtil.mergeLists(oldDestIds, lastDestIds, newDestIds);
			}
		}
		
		// delete links in base which no more exist
		Set<UUID> destIdsOfLinksToRemove = new HashSet<UUID>();
		for (Revision destRevision : db.getLinkDestRev(linkTypeId, itemId, rev)) {
			UUID destId = destRevision.getId();
			if (!destIdsToSet.contains(destId))
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

			int destRev = 0;
			if (TWUtil.isBranchDestination(linkType))
				destRev = ModelVersionDBService.ALL;
			else if (link.isLinkResolved())
				destRev = destItem.getVersion();
			else
				destRev = link.getVersion();

			Map<String, Object> stateMap = new HashMap<String, Object>();
			stateMap.put(CadseGCST.ITEM_at_NAME, destItem.getName());
			stateMap.put(CadseGCST.ITEM_at_QUALIFIED_NAME, destItem
					.getQualifiedName());
			stateMap.put(CadseGCST.ITEM_at_DISPLAY_NAME, destItem
					.getDisplayName());

			//TODO manage effectivity
			
			boolean isInDB = false;
			try {
				isInDB = db
						.linkExists(linkTypeId, itemId, rev, destId, destRev);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!isInDB)
				db.addLink(linkTypeId, itemId, rev, destId, destRev, stateMap, false);
			else {
				// update destination names in base
				Revision linkRev = db.getLinkRev(linkTypeId, itemId, rev,
						destId, destRev);
				db.setLinkState(linkRev.getId(), linkRev.getRev(), stateMap);
			}

			if (TWUtil.isImmutableDestination(linkType)
					|| TWUtil.isMutableDestination(linkType)
					|| TWUtil.isFinalDestination(linkType)) {
				try {
					for (Revision destRevision : db.getLinkDestRev(linkTypeId,
							itemId, rev)) {
						if (destRevision.getId() != destId)
							continue;
						int destRevInBase = destRevision.getRev();
						if (destRevInBase == destRev)
							continue;

						if (TWUtil.isFinalDestination(linkType)) {
							String errorMsg = "Revision of destination "
									+ destId + " of link " + linkType.getName()
									+ " has been modified from "
									+ destRevInBase + " to " + destRev;
							_commitState.getErrors().addError(itemId, errorMsg);

							throw new IllegalStateException(errorMsg);
						} else
							db.deleteLink(linkTypeId, itemId, rev, destId,
									destRevInBase);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private boolean hasConflict(List<UUID> oldDestIds, List<UUID> lastDestIds,
			List<UUID> newDestIds) {	
		return !oldDestIds.equals(newDestIds) && !oldDestIds.equals(lastDestIds);
	}

	private void commitItemState(UUID itemId, ModelVersionDBService db) throws ModelVersionDBException,
			TransactionException, DBConnectionException {

		// commit item model database
		Item item = _transaction.getItem(itemId);
		ItemType itemType = item.getType();

		// set repository for this item
		DBUtil.connectToDB(db, itemType);

		// compute initial state
		Map<String, Object> stateMap = new TreeMap<String, Object>();
		String comment = _commitState.getComment();
		stateMap.put(DBUtil.TW_COMMENT_ATTR_NAME, comment);
		String user = TeamWorkPreferences.getUserName();
		stateMap.put(DBUtil.TW_COMMITER_ATTR_NAME, user);
		Date commitDate = new Date(System.currentTimeMillis());
		stateMap.put(DBUtil.TW_COMMIT_DATE_ATTR_NAME, commitDate);
		Item parentItem = item.getPartParent();
		if (parentItem != null)
			stateMap.put(DBUtil.PARENT_ATTR_NAME, parentItem.getId());
		
		List<IAttributeType<?>> modifiedAttrTypes = new ArrayList<IAttributeType<?>>();
		for (IAttributeType<?> attrType : item.getLocalAllAttributeTypes()) {
			
			// links are updated in a second step
			if (TWUtil.isLinkType(attrType))
				continue;
			
			// ignore transient attributes
			if (TWUtil.isToIgnoreForCommit(attrType))
				continue;
			
			// ignore transient attributes
			if (TWUtil.isTransient(attrType))
				continue;
			
			String attrName = attrType.getName();
			stateMap.put(attrName, item.getAttribute(attrType));

			if (item.isTWAttributeModified(attrType)) {
				if (TWUtil.isFinal(attrType)) {
					String errorMsg = "Value of attribute " + 
					attrName + " of item " + item.getName() + " must not be changed (final politic).";
					_commitState.getErrors().addError(itemId, errorMsg);
				
					return;
				} else
					modifiedAttrTypes.add(attrType);
			}
		}
		int rev = item.getVersion();
		int oldRev = rev;
		int lastRev = 0;
		if (rev == 0) {
			// item not already in db
			UUID itemTypeId = itemType.getId();
			rev = db.createObject(itemId, itemTypeId,
					stateMap, TWUtil.isItemType(item));
		} else {
			// an item revision already exists in db
			lastRev = db.getLastObjectRevNb(itemId);
			
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
					if (attrType.isTWValueModified(oldValue, newValue) &&
							attrType.isTWValueModified(oldValue, lastValue)) {
						
						String errorMsg = "Conflict values " + 
							oldValue + " and " + newValue + " of attribute " + 
							attrName + " of item " + item.getName();
						_commitState.getErrors().addError(itemId, errorMsg);
						
						throw new IllegalStateException(errorMsg);
					} else
						db.setObjectValue(itemId, rev, attrName, newValue);
				}
			}
			db.setObjectValue(itemId, rev, DBUtil.TW_COMMENT_ATTR_NAME, comment);
			db.setObjectValue(itemId, rev, DBUtil.TW_COMMITER_ATTR_NAME, user);
			db.setObjectValue(itemId, rev, DBUtil.TW_COMMIT_DATE_ATTR_NAME, commitDate);
			if (parentItem != null)
				db.setObjectValue(itemId, rev, DBUtil.PARENT_ATTR_NAME, parentItem.getId());
		}
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
}

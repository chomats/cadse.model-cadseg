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
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import fr.imag.adele.cadse.cadseg.teamwork.db.DBConnexionParams;
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
			_commitState.abortCommit(); // TODO should add a global error
			// message
			return;
		}
		
		// check that evolution politics are set in DB
		//TODO implement it

		// commit new state of items without outgoing links
		int i = 0;
		int itemNb = _commitState.getCommittedItems().size();
		while ((i < itemNb) && !_commitState.isFailed() && !_commitState.isCommitPerformed()) {
			UUID itemId = _commitState.getCommittedItems().get(i);
			_commitState.beginCommittingItem(itemId);

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
		while ((i < itemNb) && !_commitState.isFailed() && !_commitState.isCommitPerformed()) {
			UUID itemId = _commitState.getCommittedItems().get(i);

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
		while ((i < itemNb) && !_commitState.isFailed()) {
			UUID itemId = _commitState.getCommittedItems().get(i);

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
			}
		} catch (TransactionException e) {
			e.printStackTrace();
		}
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

		// compute modified attributes
		Map<String, Object> stateMap = new TreeMap<String, Object>();
		Map<String, IAttributeType> attrTypeMap = new TreeMap<String, IAttributeType>();
		for (Item attrItem : item.getOutgoingItems(CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES, true)) {
			IAttributeType attrType = (IAttributeType) attrItem;

			// links are updated in a second step
			if (attrType.isInstanceOf(CadseGCST.LINK_TYPE)) {
				continue;
			}

			if (item.isTWAttributeModified(attrType)) {
				stateMap.put(attrType.getName(), item.getAttribute(attrType));
				attrTypeMap.put(attrType.getName(), attrType);
			}
		}
		int rev = item.getVersion();
		UUID itemUUID = UUID.fromString(itemId.toString());
		if (rev == 0) {
			// item not already in db
			UUID itemTypeId = UUID.fromString(itemType.getId().toString());
			rev = db.createObject(itemUUID, itemTypeId,
					stateMap, isItemType(item));
		} else {
			// an item revision already exists in db
			int lastRev = db.getLastObjectRevNb(itemUUID);
			rev = db.createNewObjectRevision(itemUUID, rev); 
			
			//TODO update attributes
		}
		try {
			item.setAttribute(CadseGCST.ITEM_at_TW_VERSION_, rev);
			//item.setAttribute(CadseGCST.ITEM_at_TW_LAST_COMMENT, _commitState.getComment()); //TODO add comment
			String user = ""; //TODO get user
			item.setAttribute(CadseGCST.ITEM_at_COMMITTED_BY_, user);
			item.setAttribute(CadseGCST.ITEM_at_COMMITTED_DATE_, new Date(System.currentTimeMillis()));
			
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

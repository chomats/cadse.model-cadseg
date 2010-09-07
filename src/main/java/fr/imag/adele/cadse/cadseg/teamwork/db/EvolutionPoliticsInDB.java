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
package fr.imag.adele.cadse.cadseg.teamwork.db;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;
import fr.imag.adele.teamwork.db.DBConnectionException;
import fr.imag.adele.teamwork.db.ModelVersionDBException;
import fr.imag.adele.teamwork.db.ModelVersionDBService;
import fr.imag.adele.teamwork.db.TransactionException;

public class EvolutionPoliticsInDB {
	
	private LogicalWorkspaceTransaction _transaction;
	private Map<ItemType, Boolean> _evolPoliticsSetIndDB = new HashMap<ItemType, Boolean>(); 

	public EvolutionPoliticsInDB(LogicalWorkspaceTransaction transaction) {
		_transaction = transaction;
	}

	public void checkEvolPoliticsSetInDB(UUID itemId, ModelVersionDBService db) throws TransactionException, DBConnectionException {
		Item item = _transaction.getItem(itemId);
		ItemType itemType = item.getType();
		
		checkEvolPoliticsSetInDB(itemType, db);
	}
		
	public void checkEvolPoliticsSetInDB(ItemType itemType, ModelVersionDBService db) throws TransactionException, DBConnectionException {
		UUID itemTypeId = itemType.getId();
		
		Boolean isAlreadySet = _evolPoliticsSetIndDB.get(itemType);
		if ((isAlreadySet != null) && isAlreadySet)
			return;
		
		DBUtil.connectToDB(db, itemType);
		for (IAttributeType<?> attrType : itemType.getAllAttributeTypes()) {
			
			if (TWUtil.isTWAttribute(attrType))
				continue;
			if (TWUtil.isInternalCadseAttribute(attrType))
				continue;
			
			try {
				if (TWUtil.isLinkType(attrType)) {
					db.setLinkSrcVersionSpecific(attrType.getId(), attrType.isTWRevSpecific());
					db.setLinkDestVersionSpecific(attrType.getId(), !TWUtil.isBranchDestination(attrType));
				} else
					db.setObjectAttVersionSpecific(itemTypeId, getAttributeName(attrType), attrType.isTWRevSpecific());
			} catch (ModelVersionDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// set internal attributes evolution politics
		try {
			db.setObjectAttVersionSpecific(itemTypeId, DBUtil.PARENT_ATTR_NAME, false);
			db.setObjectAttVersionSpecific(CadseGCST.CONTENT_ITEM.getId(), DBUtil.SCM_REPO_URL_ATTR_NAME, true);
			db.setObjectAttVersionSpecific(CadseGCST.CONTENT_ITEM.getId(), DBUtil.SCM_REV_ATTR_NAME, true);
			db.setObjectAttVersionSpecific(CadseGCST.CONTENT_ITEM.getId(), DBUtil.TW_COMMENT_ATTR_NAME, true);
			db.setLinkSrcVersionSpecific(CadseGCST.ITEM_lt_CONTENTS.getId(),
					true);
			db.setLinkDestVersionSpecific(CadseGCST.ITEM_lt_CONTENTS.getId(),
					true);
		} catch (ModelVersionDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getAttributeName(IAttributeType<?> attrType) {
		return attrType.getName();
	}
}

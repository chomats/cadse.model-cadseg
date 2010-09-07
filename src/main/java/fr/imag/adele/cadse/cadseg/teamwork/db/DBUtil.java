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

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.teamwork.db.DBConnectionException;
import fr.imag.adele.teamwork.db.ModelVersionDBException;
import fr.imag.adele.teamwork.db.ModelVersionDBService;
import fr.imag.adele.teamwork.db.TransactionException;

public class DBUtil {
	
	private static final String NOT_DEFINED_IN_THIS_REVISION = " not defined in this revision.";
	
	public static final String TW_COMMIT_DATE_ATTR_NAME = "TW_COMMIT_DATE";
	public static final String TW_COMMITER_ATTR_NAME = "TW_COMMITER";
	public static final String TW_COMMENT_ATTR_NAME = "TW_COMMENT";
	public static final String PARENT_ATTR_NAME = "TW_PARENT_ID";
	public static final String SCM_REPO_URL_ATTR_NAME = "TW_SCM_REPO_URL";
	public static final String SCM_REV_ATTR_NAME = "TW_SCM_REPO_REV";

	/**
	 * Returns all revisions of specified item.
	 * 
	 * @param item an item
	 * @return all revisions of specified item.
	 * @throws TransactionException 
	 * @throws ModelVersionDBException 
	 * @throws DBConnectionException 
	 */
	public static int[] getAllRevisions(Item item) throws TransactionException, ModelVersionDBException, DBConnectionException {
		return getAllRevisions(item.getId(), item.getType());
	}
	
	/**
	 * Returns all revisions of specified item.
	 * 
	 * @param itemId    id of item to retrieve all revisions
	 * @param itemType  type of specified item
	 * @return all revisions of specified item.
	 * @throws TransactionException 
	 * @throws ModelVersionDBException 
	 * @throws DBConnectionException 
	 */
	public static int[] getAllRevisions(UUID itemId, ItemType itemType) throws TransactionException, ModelVersionDBException, DBConnectionException {
		ModelVersionDBService db = CadseCore.getCadseDomain().getModelVersionDBService();
		connectToDB(db, itemType);
		
		return db.getObjectRevNbs(itemId);
	}
	
	/**
	 * Connect to the db managing specified item types and its instances.
	 * 
	 * @param db       a model version database service
	 * @param itemType an item type
	 * @throws TransactionException
	 * @throws DBConnectionException if connection to db has failed
	 */
	public static void connectToDB(ModelVersionDBService db, ItemType itemType)
			throws TransactionException, DBConnectionException {
		String cadseName = TWUtil.getCadse(itemType);
		DBConnexionParams dbParams = DBConnexionParams
				.getConnectionParams(cadseName);
		String url = dbParams.getUrl();
		String login = dbParams.getLogin();
		String pwd = dbParams.getPassword();
		if ((login == null) || (login.trim().length() == 0)) {
			db.setConnectionURL(url);
		} else {
			db.setConnectionURL(url, login, pwd);
		}
	}

	/**
	 * Returns a human readable text representing state (values of attributes) of specified item revision.
	 * 
	 * @param item      an item 
	 * @param itemRev   item revision number  
	 * @return a human readable text representing state (values of attributes) of specified item revision.
	 * @throws TransactionException 
	 * @throws ModelVersionDBException 
	 * @throws DBConnectionException 
	 */
	public static String getRevisionStateStr(Item item, int itemRev) throws TransactionException, ModelVersionDBException, DBConnectionException {
		return getRevisionStateStr(item.getId(), item.getType(), itemRev, item);
	}
	
	/**
	 * Returns a human readable text representing state (values of attributes) of specified item revision.
	 * 
	 * @param itemId    id of item for which we want to get revision state
	 * @param itemType  type of specified item
	 * @param itemRev   item revision number  
	 * @return a human readable text representing state (values of attributes) of specified item revision.
	 * @throws TransactionException 
	 * @throws ModelVersionDBException 
	 * @throws DBConnectionException 
	 */
	public static String getRevisionStateStr(UUID itemId, ItemType itemType, int itemRev) 
		throws TransactionException, ModelVersionDBException, DBConnectionException {
		return getRevisionStateStr(itemId, itemType, itemRev, null);
	}
		
	private static String getRevisionStateStr(UUID itemId, ItemType itemType, int itemRev, Item item) 
		throws TransactionException, ModelVersionDBException, DBConnectionException {
		
		ModelVersionDBService db = CadseCore.getCadseDomain().getModelVersionDBService();
		connectToDB(db, itemType);
		
		StringBuffer sb = new StringBuffer();
		Map<String, Object> stateMap = db.getObjectState(itemId, itemRev);
		
		appendTeamWorkAttrVal(sb, "Commit comment ", TW_COMMENT_ATTR_NAME, stateMap);
		appendTeamWorkAttrVal(sb, "Committer ", TW_COMMITER_ATTR_NAME, stateMap);
		appendTeamWorkAttrVal(sb, "Commit date ", TW_COMMIT_DATE_ATTR_NAME, stateMap);
		
		IAttributeType<?>[] attributeTypes;
		if (item != null)
			attributeTypes = item.getLocalAllAttributeTypes();
		else
			attributeTypes = itemType.getAllAttributeTypes();
		for (IAttributeType<?> attrType : attributeTypes) {
			if (TWUtil.isLinkType(attrType))
				continue;
			
			if (TWUtil.isTWAttribute(attrType))
				continue;
			
			if (TWUtil.isInternalCadseAttribute(attrType))
				continue;
			
			String attrName = attrType.getName();
			sb.append(attrName);
			if (stateMap.containsKey(attrName)) {
				Object attrVal = stateMap.get(attrName);
				sb.append(" = ");
				sb.append(attrVal);
			} else {
				sb.append(NOT_DEFINED_IN_THIS_REVISION);
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}

	/**
	 * Appends to specified string buffer of string representing value of specified attribute.
	 * 
	 * @param sb              a string buffer
	 * @param displayAttrName label used to describe the attribute
	 * @param attrName        attribute name
	 * @param stateMap        an item state
	 */
	private static void appendTeamWorkAttrVal(StringBuffer sb,
			String displayAttrName, String attrName, Map<String, Object> stateMap) {
		sb.append(displayAttrName);
		if (stateMap.containsKey(attrName))
			sb.append(": " + stateMap.get(attrName));
		else
			sb.append(NOT_DEFINED_IN_THIS_REVISION);
		sb.append("\n");
	}

	/**
	 * Returns all descriptions of items in teamwork repository of specified type.
	 * 
	 * @param typeId item type id
	 * @return all descriptions of items in teamwork repository of specified type.
	 * @throws TransactionException 
	 * @throws ModelVersionDBException 
	 * @throws DBConnectionException 
	 */
	public static Collection<ItemInDBDesc> getAllItemInDBDescs(ItemType itemType) throws TransactionException, ModelVersionDBException, DBConnectionException {
		ModelVersionDBService db = CadseCore.getCadseDomain().getModelVersionDBService();
		connectToDB(db, itemType);
		
		Set<ItemInDBDesc> itemDescs = new HashSet<ItemInDBDesc>();
		Set<UUID> itemIds = db.getObjects(itemType.getId());
		for (UUID itemId : itemIds) {
			String itemName = itemId.toString();
			try {
				itemName = (String) db.getObjectValue(itemId, ModelVersionDBService.LAST, CadseGCST.ITEM_at_NAME);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			itemDescs.add(new ItemInDBDesc(itemId, itemName));
		}
		
		return itemDescs;
	}
	
	
}

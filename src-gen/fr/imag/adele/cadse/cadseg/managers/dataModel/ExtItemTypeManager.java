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
package fr.imag.adele.cadse.cadseg.managers.dataModel;

import java.util.UUID;

import fr.imag.adele.cadse.cadseg.contents.ExtItemTypeContent;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
 * The Class ExtItemTypeManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ExtItemTypeManager extends ItemTypeManager {

	
	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ExtItemTypeManager() {
		super();
	}

	/**
		@generated
	*/
	@Override
	public String computeQualifiedName(Item item, String name, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			Item currentItem;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(name);
			return sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}


	/**
	 * Gets the display name.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the display name
	 * 
	 * @generated
	 */
	@Override
	public String getDisplayName(Item item) {
		try {
			Object value;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}


	/**
	 * get a link 'ref-type' from 'ExtItemType' to 'ItemType'.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the ref type link
	 * 
	 * @generated
	 */
	static public Link getRefTypeLink(Item extItemType) {
		return extItemType.getOutgoingLink(CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE);
	}

	/**
	 * get all link destination 'ref-type' from 'ExtItemType' to 'ItemType'.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the ref type all
	 * 
	 * @generated
	 */
	static public Item getRefTypeAll(Item extItemType) {
		return extItemType.getOutgoingItem(CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE, false);
	}

	/**
	 * get resolved link destination 'ref-type' from 'ExtItemType' to
	 * 'ItemType'.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the ref type
	 * 
	 * @generated
	 */
	static public Item getRefType(Item extItemType) {
		return extItemType.getOutgoingItem(CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE, true);
	}

	/**
	 * set a link 'ref-type' from 'ExtItemType' to 'ItemType'.
	 * 
	 * @generated
	 */
	static public void setRefType(Item extItemType, Item value) throws CadseException {
		extItemType.setOutgoingItem(CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE,value);
	}
}

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
package fr.imag.adele.cadse.cadseg.managers.content;

import java.util.UUID;

import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.util.Convert;

/**
 * The Class ContentModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ContentItemTypeManager extends DefaultWorkspaceManager  {

	/** The Constant EXPORTERS_LINK. */
	@Deprecated
	public static final String	EXPORTERS_LINK	= "exporters";

	/**
	 * Instantiates a new content model manager.
	 */
	public ContentItemTypeManager() {
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
	 * Gets the workspace model.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the workspace model
	 */
	@Override
	public Item getWorkspaceModel(Item item) {
		return item.getPartParent().getPartParent().getPartParent();
	}

	/**
	 * Checks if is extends class.
	 * 
	 * @param contentmodel
	 *            the contentmodel
	 * 
	 * @return true, if is extends class
	 */
	public static final boolean isExtendsClass(Item contentmodel) {
		Object value = contentmodel.getAttribute(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_);
		if (value == null) {
			return false;
		}

		try {
			return Convert.toBooleanFalseIfNull(value);
		} catch (Throwable t) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#getDisplayName(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String getDisplayName(Item item) {
		return item.getName();
	}

	/**
	 * @generated
	 */
	public static final boolean isExtendsClassAttribute(Item contentItemType) {
		return contentItemType.getAttributeWithDefaultValue(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_, false);
	}

	/**
	 * @generated
	 */
	public static final void setExtendsClassAttribute(Item contentItemType, boolean value) {
		try {
			contentItemType.setAttribute(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getIdRuntimeAttribute(Item contentItemType) {
		return contentItemType.getAttributeWithDefaultValue(CadseGCST.CONTENT_ITEM_TYPE_at_ID_RUNTIME_, null);
	}

	/**
		@generated
	*/
	public static final void setIdRuntimeAttribute(Item contentItemType, String value) {
		try {
			contentItemType.setAttribute(CadseGCST.CONTENT_ITEM_TYPE_at_ID_RUNTIME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the uUID.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the uUID
	 */
	public static UUID getIdRuntime(Item contentIT) {
		
		String uuid_str = contentIT.getAttributeOwner(CadseGCST.CONTENT_ITEM_TYPE_at_ID_RUNTIME_);
		if (uuid_str == null || uuid_str.length() == 0) {
			UUID uuid = UUID.randomUUID();
			uuid_str = uuid.toString();
			try {
				contentIT.setAttribute(CadseGCST.CONTENT_ITEM_TYPE_at_ID_RUNTIME_, uuid_str);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return uuid;
		}
		return UUID.fromString(uuid_str);

	}

}

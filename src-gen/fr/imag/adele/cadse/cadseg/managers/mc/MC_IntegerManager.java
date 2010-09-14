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
package fr.imag.adele.cadse.cadseg.managers.mc;

import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_Integer;

/**
 * The Class IntModelControllerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class MC_IntegerManager extends ModelControllerManager implements IItemManager {

	/**
	 * Instantiates a new int model controller manager.
	 */
	public MC_IntegerManager() {
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
	 * @generated
	 */
	public static final String getErrorMsgMaxAttribute(Item mC_Integer) {
		return mC_Integer.getAttributeWithDefaultValue(CadseGCST.MC_INTEGER_at_ERROR_MSG_MAX_, null);
	}

	/**
	 * @generated
	 */
	public static final void setErrorMsgMaxAttribute(Item mC_Integer, String value) {
		try {
			mC_Integer.setAttribute(CadseGCST.MC_INTEGER_at_ERROR_MSG_MAX_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final String getErrorMsgMinAttribute(Item mC_Integer) {
		return mC_Integer.getAttributeWithDefaultValue(CadseGCST.MC_INTEGER_at_ERROR_MSG_MIN_, null);
	}

	/**
	 * @generated
	 */
	public static final void setErrorMsgMinAttribute(Item mC_Integer, String value) {
		try {
			mC_Integer.setAttribute(CadseGCST.MC_INTEGER_at_ERROR_MSG_MIN_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final int getMaxAttribute(Item mC_Integer) {
		return mC_Integer.getAttributeWithDefaultValue(CadseGCST.MC_INTEGER_at_MAX_, -1);
	}

	/**
	 * @generated
	 */
	public static final void setMaxAttribute(Item mC_Integer, int value) {
		try {
			mC_Integer.setAttribute(CadseGCST.MC_INTEGER_at_MAX_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final int getMinAttribute(Item mC_Integer) {
		return mC_Integer.getAttributeWithDefaultValue(CadseGCST.MC_INTEGER_at_MIN_, -1);
	}

	/**
	 * @generated
	 */
	public static final void setMinAttribute(Item mC_Integer, int value) {
		try {
			mC_Integer.setAttribute(CadseGCST.MC_INTEGER_at_MIN_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final int getDefaultValueAttribute(Item mC_Integer) {
		return mC_Integer.getAttributeWithDefaultValue(CadseGCST.MC_INTEGER_at_DEFAULT_VALUE_, -1);
	}

	/**
	 * @generated
	 */
	public static final void setDefaultValueAttribute(Item mC_Integer, int value) {
		try {
			mC_Integer.setAttribute(CadseGCST.MC_INTEGER_at_DEFAULT_VALUE_, value);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#getDefaultClassName()
	 */
	@Override
	public Class<?> getDefaultClassName() {
		return MC_Integer.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateMeItem(Item itemParent, LinkType lt, ItemType destType) {
		Item field = itemParent;
		Item attribut = FieldManager.getAttribute(field);
		if (attribut == null)
			return "Must set the attribut link for the item " + itemParent.getId();
		if (AttributeManager.isIsListAttribute(attribut))
			return "Must be a singleton value";
		if (attribut.getType() == CadseGCST.INTEGER)
			return null;

		return "The type of the attribut linked at the field must be integer attribute";
	}
}

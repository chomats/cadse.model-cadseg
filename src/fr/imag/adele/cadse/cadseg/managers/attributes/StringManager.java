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

package fr.imag.adele.cadse.cadseg.managers.attributes;

import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseRootCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.util.Convert;

/**
 * The Class StringManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class StringManager extends AttributeManager implements IItemManager, IModelWorkspaceManager {

	/**
	 * Instantiates a new string manager.
	 */
	public StringManager() {
	}

	/**
	 * Compute unique name.
	 * 
	 * @param item
	 *            the item
	 * @param shortName
	 *            the short name
	 * @param parent
	 *            the parent
	 * @param lt
	 *            the lt
	 * 
	 * @return the string
	 * 
	 * @generated
	 */
	@Override
	public String computeUniqueName(Item item, String shortName, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(shortName);
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
			Item currentItem;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * Checks if is not empty attribute.
	 * 
	 * @param string
	 *            the string
	 * 
	 * @return true, if checks if is not empty attribute
	 * 
	 * @generated
	 */
	public static final boolean isNotEmptyAttribute(Item string) {
		Object value = string.getAttribute(WorkspaceCST.STRING_at_NOT_EMPTY_);
		if (value == null) {
			return false;
		}

		try {
			return Convert.toBoolean(value);
		} catch (Throwable t) {
			return false;
		}

	}

	/**
	 * Sets the not empty attribute.
	 * 
	 * @param string
	 *            the string
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setNotEmptyAttribute(Item string, boolean value) {
		try {
			Object setvalue = value;
			string.setAttribute(WorkspaceCST.STRING_at_NOT_EMPTY_, setvalue);
		} catch (Throwable t) {

		}
	}

	@Override
	public ItemType getCadseRootType() {
		return CadseRootCST.STRING_ATTRIBUTE_TYPE;
	}

	@Override
	public int getCadseRootFlag(Item attribute) {
		return super.getCadseRootFlag(attribute) | (isNotEmptyAttribute(attribute) ? StringAttributeType.NOT_EMPTY : 0);
	}

	@Override
	public Class<? extends IAttributeType<?>> getAttributeDefinitionTypeJava() {
		return StringAttributeType.class;
	}

	@Override
	public Class<?> getTypeJava(boolean primitive) {
		return String.class;
	}

	@Override
	public String getJavaTypeDefaultValue(Item attribute) {
		String defaultValue = attribute.getAttribute(WorkspaceCST.ATTRIBUTE_at_DEFAULT_VALUE_);
		if (defaultValue == null || defaultValue.length() == 0) {
			defaultValue = generatedDefaultValue();
		}

		if (defaultValue == null || defaultValue.length() == 0) {
			return "null";
		}
		if ("".equals(defaultValue)) {
			return defaultValue;
		}
		if (defaultValue.startsWith("\"") && defaultValue.endsWith("\"")) {
			if (defaultValue.length() == 1 || defaultValue.length() == 2) {
				defaultValue = "";
			}
			else {
				defaultValue = defaultValue.substring(1, defaultValue.length() - 1);
			}
		}
		defaultValue = defaultValue.replace("\"", "\\\"");
		return "\"" + defaultValue + "\"";
	}
}

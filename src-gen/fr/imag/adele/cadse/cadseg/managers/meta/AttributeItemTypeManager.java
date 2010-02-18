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
package fr.imag.adele.cadse.cadseg.managers.meta;

import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;

/**
 * @generated
 */
public class AttributeItemTypeManager extends ItemTypeManager {

	/**
	 * @generated
	 */
	public AttributeItemTypeManager() {
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
	public static final String getRuntimeQualifiedClassAttribute(Item attributeItemType) {
		return attributeItemType.getAttributeWithDefaultValue(CadseGCST.ATTRIBUTE_ITEM_TYPE_at_RUNTIME_QUALIFIED_CLASS_, null);
	}

	/**
	 * @generated
	 */
	public static final void setRuntimeQualifiedClassAttribute(Item attributeItemType, String value) {
		try {
			attributeItemType.setAttribute(CadseGCST.ATTRIBUTE_ITEM_TYPE_at_RUNTIME_QUALIFIED_CLASS_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getDefaultValueAttribute(Item attributeItemType) {
		return attributeItemType.getAttributeWithDefaultValue(CadseGCST.ATTRIBUTE_ITEM_TYPE_at_DEFAULT_VALUE_, null);
	}

	/**
		@generated
	*/
	public static final void setDefaultValueAttribute(Item attributeItemType, String value) {
		try {
			attributeItemType.setAttribute(CadseGCST.ATTRIBUTE_ITEM_TYPE_at_DEFAULT_VALUE_, value);
		} catch (Throwable t) {

		}
	}

}

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

package fr.imag.adele.cadse.cadseg.managers.actions;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class MenuAbstractManager.
 * 
 * @generated
 */
public class MenuAbstractManager extends DefaultItemManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public MenuAbstractManager() {
		super();
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
	 * Gets the path attribute.
	 * 
	 * @param menuAbstract
	 *            the menu abstract
	 * 
	 * @return the path attribute
	 * 
	 * @generated
	 */
	public static final String getPathAttribute(Item menuAbstract) {
		return menuAbstract.getAttributeWithDefaultValue(CadseGCST.MENU_ABSTRACT_at_PATH_, null);
	}

	/**
	 * Sets the path attribute.
	 * 
	 * @param menuAbstract
	 *            the menu abstract
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setPathAttribute(Item menuAbstract, String value) {
		try {
			menuAbstract.setAttribute(CadseGCST.MENU_ABSTRACT_at_PATH_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the label attribute.
	 * 
	 * @param menuAbstract
	 *            the menu abstract
	 * 
	 * @return the label attribute
	 * 
	 * @generated
	 */
	public static final String getLabelAttribute(Item menuAbstract) {
		return menuAbstract.getAttributeWithDefaultValue(CadseGCST.MENU_ABSTRACT_at_LABEL_, null);
	}

	/**
	 * Sets the label attribute.
	 * 
	 * @param menuAbstract
	 *            the menu abstract
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setLabelAttribute(Item menuAbstract, String value) {
		try {
			menuAbstract.setAttribute(CadseGCST.MENU_ABSTRACT_at_LABEL_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the icon attribute.
	 * 
	 * @param menuAbstract
	 *            the menu abstract
	 * 
	 * @return the icon attribute
	 * 
	 * @generated
	 */
	public static final String getIconAttribute(Item menuAbstract) {
		return menuAbstract.getAttributeWithDefaultValue(CadseGCST.MENU_ABSTRACT_at_ICON_, null);
	}

	/**
	 * Sets the icon attribute.
	 * 
	 * @param menuAbstract
	 *            the menu abstract
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setIconAttribute(Item menuAbstract, String value) {
		try {
			menuAbstract.setAttribute(CadseGCST.MENU_ABSTRACT_at_ICON_, value);
		} catch (Throwable t) {

		}
	}

}

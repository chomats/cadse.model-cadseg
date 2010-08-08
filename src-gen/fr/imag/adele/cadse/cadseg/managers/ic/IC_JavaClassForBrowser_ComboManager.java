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

package fr.imag.adele.cadse.cadseg.managers.ic;

import fede.workspace.eclipse.java.fields.IC_JavaClassForBrowser_Combo;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class IC_JavaClassForBrowser_ComboManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_JavaClassForBrowser_ComboManager extends InteractionControllerManager implements IItemManager {

	/** The Constant style_values_cst. */
	public static final String[]	style_values_cst			= { "CONSIDER_CLASSES", "CONSIDER_INTERFACES",
			"CONSIDER_ANNOTATION_TYPES", "CONSIDER_ENUMS", "CONSIDER_ALL_TYPES", "CONSIDER_CLASSES_AND_INTERFACES",
			"CONSIDER_CLASSES_AND_ENUMS"						};

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
		@generated
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
		@generated
	*/
	public static final String getStyleAttribute(Item iC_JavaClassForBrowser_Combo) {
		return iC_JavaClassForBrowser_Combo.getAttributeWithDefaultValue(CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_STYLE_, null);
	}

	/**
		@generated
	*/
	public static final void setStyleAttribute(Item iC_JavaClassForBrowser_Combo, String value) {
		try {
			iC_JavaClassForBrowser_Combo.setAttribute(CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_STYLE_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getFilterAttribute(Item iC_JavaClassForBrowser_Combo) {
		return iC_JavaClassForBrowser_Combo.getAttributeWithDefaultValue(CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_FILTER_, null);
	}

	/**
		@generated
	*/
	public static final void setFilterAttribute(Item iC_JavaClassForBrowser_Combo, String value) {
		try {
			iC_JavaClassForBrowser_Combo.setAttribute(CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_FILTER_, value);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager#getDefaultClassName()
	 */
	@Override
	public Class<?> getDefaultClassName() {
		return IC_JavaClassForBrowser_Combo.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager#mustBeExtended()
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
		Item attribute = FieldManager.getAttribute(field);
		if (attribute == null) {
			return "select an attribute before";
		}
		if (attribute.getType() != CadseGCST.STRING) {
			return "It's not an string attribute";
		}
		if (itemParent.getType() == CadseGCST.DBROWSER) {
			return null;
		}
		if (itemParent.getType() == CadseGCST.DCOMBO) {
			return null;
		}
		return "It's not a browser field or a combo field";
	}

	
}

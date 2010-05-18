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

import java.util.ArrayList;
import java.util.List;

import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class IC_StaticArrayOfObjectForBrowser_ComboManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_StaticArrayOfObjectForBrowser_ComboManager extends IC_AbstractForBrowser_ComboManager implements
		IItemManager {

	/** The Constant DEFAUL_CLASS_NAME. */
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.ic.IC_StaticArrayOfObjectForBrowser_Combo";

	/**
	 * Instantiates a new i c_ static array of object for browser_ combo
	 * manager.
	 */
	public IC_StaticArrayOfObjectForBrowser_ComboManager() {
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
	@SuppressWarnings("unchecked")
	public static final List<String> getValuesAttribute(Item iC_StaticArrayOfObjectForBrowser_Combo) {
		try {
			List<String> list = iC_StaticArrayOfObjectForBrowser_Combo.getAttribute(CadseGCST.IC_STATIC_ARRAY_OF_OBJECT_FOR_BROWSER_COMBO_at_VALUES_);

			if (list == null)
				return null;

			return new ArrayList<String>(list);
		} catch (Throwable t) {
			return new ArrayList<String>();
		}
	}

	/**
		@generated
	*/
	@SuppressWarnings("unchecked")
	public static final void setValuesAttribute(Item iC_StaticArrayOfObjectForBrowser_Combo, List<String> valueList) {
		try {
			List<String> list = new ArrayList<String>(valueList);
			iC_StaticArrayOfObjectForBrowser_Combo.setAttribute(CadseGCST.IC_STATIC_ARRAY_OF_OBJECT_FOR_BROWSER_COMBO_at_VALUES_, list);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	@SuppressWarnings("unchecked")
	public static final void addValuesAttribute(Item iC_StaticArrayOfObjectForBrowser_Combo, String value) {
		try {
			List<String> list = iC_StaticArrayOfObjectForBrowser_Combo.getAttribute(CadseGCST.IC_STATIC_ARRAY_OF_OBJECT_FOR_BROWSER_COMBO_at_VALUES_);
			if (list == null) {
				list = new ArrayList<String>();
			}
			list.add(value);
			iC_StaticArrayOfObjectForBrowser_Combo.setAttribute(CadseGCST.IC_STATIC_ARRAY_OF_OBJECT_FOR_BROWSER_COMBO_at_VALUES_, list);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	@SuppressWarnings("unchecked")
	public static final void removeValuesAttribute(Item iC_StaticArrayOfObjectForBrowser_Combo, String value) {
		try {

			List<String> list = iC_StaticArrayOfObjectForBrowser_Combo.getAttribute(CadseGCST.IC_STATIC_ARRAY_OF_OBJECT_FOR_BROWSER_COMBO_at_VALUES_);
			if (list == null) {
				return;
			}
			list.remove(value);
			if (list.size() == 0)
				iC_StaticArrayOfObjectForBrowser_Combo.setAttribute(CadseGCST.IC_STATIC_ARRAY_OF_OBJECT_FOR_BROWSER_COMBO_at_VALUES_, null);
			else
				iC_StaticArrayOfObjectForBrowser_Combo.setAttribute(CadseGCST.IC_STATIC_ARRAY_OF_OBJECT_FOR_BROWSER_COMBO_at_VALUES_, list);
		} catch (Throwable t) {

		}
	}

	//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager#createCreationPages(fr.imag.adele.cadse.core.Item,
//	 *      fr.imag.adele.cadse.core.LinkType,
//	 *      fr.imag.adele.cadse.core.ItemType)
//	 */
//	@Override
//	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {
//
//		CreationAction action = new CreationAction(theItemParent, desType, theLinkType,
//				DisplayManager.IC_DEFAULT_NAME);
//
//		RunningModelController mc = new MC_AttributesItem();
//
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
//				"String[] interaction controller for browser or combo",
//				"String[] interaction controller for browser or combo", 3, FieldsCore.createTextField(
//						SELECT_TITLE_ATTRIBUTE, "dialog title"), FieldsCore.createTextField(SELECT_MESSAGE_ATTRIBUTE,
//						"dialog message"), FieldsCore.createList_ListOfString(VALUES_ATTRIBUTE, "values", null, null,
//						false, 1, -1)));
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager#createModificationPage(fr.imag.adele.cadse.core.Item)
//	 */
//	@Override
//	public Pages createModificationPage(Item item) {
//		AbstractActionPage action = new ModificationAction(item);
//
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
//				"String[] interaction controller for browser or combo",
//				"String[] interaction controller for browser or combo", 3, FieldsCore.createTextField(
//						SELECT_TITLE_ATTRIBUTE, "dialog title"), FieldsCore.createTextField(SELECT_MESSAGE_ATTRIBUTE,
//						"dialog message"), FieldsCore.createList_ListOfString(VALUES_ATTRIBUTE, "values", null, null,
//						false, 1, -1)));
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateMeItem(Item itemParent, LinkType lt, ItemType destType) {
		String error = super.canCreateMeItem(itemParent, lt, destType);
		if (error != null) {
			return error;
		}
		Item field = itemParent;
		Item attribute = FieldManager.getAttribute(field);

		if (attribute.getType() != CadseGCST.STRING) {
			return "It's not a string attribute";
		}
		return null;
	}
}

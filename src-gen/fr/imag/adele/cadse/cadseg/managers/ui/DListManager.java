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
package fr.imag.adele.cadse.cadseg.managers.ui;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.util.Convert;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;

/**
 * The Class DListManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class DListManager extends DisplayManager {

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#hasContent(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public boolean hasContent(Item item) {
		return true;
	}
	
	/**
	 * Instantiates a new d list manager.
	 */
	public DListManager() {
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
	public static final boolean isEditableButtonAttribute(Item dList) {
		return dList.getAttributeWithDefaultValue(CadseGCST.DLIST_at_EDITABLE_BUTTON_, true);
	}

	/**
	 * @generated
	 */
	public static final void setEditableButtonAttribute(Item dList, boolean value) {
		try {
			dList.setAttribute(CadseGCST.DLIST_at_EDITABLE_BUTTON_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isUpdateButtonAttribute(Item dList) {
		return dList.getAttributeWithDefaultValue(CadseGCST.DLIST_at_UPDATE_BUTTON_, false);
	}

	/**
	 * @generated
	 */
	public static final void setUpdateButtonAttribute(Item dList, boolean value) {
		try {
			dList.setAttribute(CadseGCST.DLIST_at_UPDATE_BUTTON_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isOrderButtonAttribute(Item dList) {
		return dList.getAttributeWithDefaultValue(CadseGCST.DLIST_at_ORDER_BUTTON_, false);
	}

	/**
	 * @generated
	 */
	public static final void setOrderButtonAttribute(Item dList, boolean value) {
		try {
			dList.setAttribute(CadseGCST.DLIST_at_ORDER_BUTTON_, value);
		} catch (Throwable t) {

		}
	}

	// /* (non-Javadoc)
	// * @see
	// fede.workspace.model.manager.DefaultItemManager#createCreationPages(fr.imag.adele.cadse.core.Item,
	// fr.imag.adele.cadse.core.LinkType, fr.imag.adele.cadse.core.ItemType)
	// */
	// @Override
	// public Pages createCreationPages(Item theItemParent, LinkType
	// theLinkType, ItemType desType) {
	// CreationAction action = new CreationAction(theItemParent, desType,
	// theLinkType, DEFAULT_SHORT_NAME);
	//
	// return FieldsCore.createWizard(action,
	// FieldsCore.createPage("page1", "Create a list", "Create a list", 3,
	// createFieldExtendsIC(),
	// createFieldExtendsMC(),
	// createFieldExtendsUI(),
	// createFieldEditable()/*,
	// createFieldEnable()*/
	// )
	// );
	// }

	// /* (non-Javadoc)
	// * @see
	// fede.workspace.model.manager.DefaultItemManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	// */
	// @Override
	// public Pages createModificationPage(Item item) {
	// AbstractActionPage action = new ModificationAction(item);
	//
	// return FieldsCore.createWizard(action,
	// FieldsCore.createPage("page1", "a list", "a list", 3,
	// createFieldExtendsIC(),
	// createFieldExtendsMC(),
	// createFieldExtendsUI(),
	// createFieldEditable(),
	// createFieldShowFilter()
	// )
	// );
	// }

	// /**
	// * Creates the field show filter.
	// *
	// * @return the d check box ui
	// *
	// * @not generated
	// */
	// static public DCheckBoxUI createFieldShowFilter() {
	// StringToBooleanModelControler mc = new StringToBooleanModelControler();
	// return new DCheckBoxUI(CadseGCST.DLIST_at_SHOW_FILTER,
	// "show filter",
	// EPosLabel.none,
	// mc, null
	// );
	// }
	//

	/**
	 * Checks if is show filter attribute.
	 * 
	 * @param display
	 *            the display
	 * 
	 * @return true, if is show filter attribute
	 */
	public static final boolean isShowFilterAttribute(Item display) {
		Object value = display.getAttribute(CadseGCST.DLIST_at_SHOW_FILTER_);
		if (value == null) {
			return false;
		}

		try {
			return Convert.toBooleanFalseIfNull(value);
		} catch (Throwable t) {
			return false;
		}
	}

	/**
	 * @generated
	 */
	public static final void setShowFilterAttribute(Item dList, boolean value) {
		try {
			dList.setAttribute(CadseGCST.DLIST_at_SHOW_FILTER_, value);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#getDefaultPosLabel()
	 */
	@Override
	protected EPosLabel getDefaultPosLabel() {
		return EPosLabel.top;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#getDefaultClassName()
	 */
	@Override
	public Class<?> getDefaultClassName() {
		return DListUI.class;
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see fede.workspace.model.manager.DefaultItemManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
//	 *      fr.imag.adele.cadse.core.LinkType,
//	 *      fr.imag.adele.cadse.core.ItemType)
//	 */
//	@Override
//	public String canCreateMeItem(Item field, LinkType lt, ItemType destType) {
//		Item attribute = FieldManager.getAttribute(field);
//		if (attribute == null) {
//			return "You must add a link to an attribute link";
//		}
//		if (!AttributeManager.isIsListAttribute(attribute)) {
//			return "List is for a list";
//		}
//		return null;
//	}

}

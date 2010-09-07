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
package fr.imag.adele.cadse.cadseg.managers.view;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.util.Convert;

/**
 * The Class ViewItemTypeManager.
 * 
 * @generated
 */
public class ViewItemTypeManager extends DefaultItemManager {

	/** The Constant ID_ITEM_TYPE_VIEW_ITEM_TYPE. */
	@SuppressWarnings("hiding")
	public static final int	ID_ITEM_TYPE_VIEW_ITEM_TYPE		= 4;

	/** The Constant ID_LINK_TYPE_ITEM_TYPE. */
	public static final int	ID_LINK_TYPE_ITEM_TYPE			= 1;

	/** The Constant ID_LINK_TYPE_VIEW_LINK_TYPES. */
	public static final int	ID_LINK_TYPE_VIEW_LINK_TYPES	= 2;

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ViewItemTypeManager() {
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

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// fede.workspace.model.manager.DefaultItemManager#computeRenameAnnotationChange(org.eclipse.ltk.core.refactoring.CompositeChange,
	// * fr.imag.adele.cadse.core.Item, fr.imag.adele.cadse.core.Item,
	// * fr.imag.adele.cadse.core.var.ContextVariable,
	// * fr.imag.adele.cadse.core.var.ContextVariable)
	// */
	// @Override
	// public RefactoringStatus computeRenameAnnotationChange(CompositeChange
	// change, Item itemAnnotation,
	// Item itemAnnoted, ContextVariable newCxt, ContextVariable oldCxt) {
	// if (itemAnnoted.isInstanceOf(CadseGCST.ITEM_TYPE)) {
	// itemAnnotation.computeRenameChange(change, itemAnnoted.getName(),
	// newCxt, oldCxt);
	// }
	// return null;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return CANNOT_RENAME;
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
	 * get a link 'item-type' from 'ViewItemType' to 'ItemType'.
	 * 
	 * @generated
	 */
	static public Link getItemTypeLink(Item viewItemType) {
		return viewItemType.getOutgoingLink(CadseGCST.VIEW_ITEM_TYPE_lt_ITEM_TYPE);
	}

	/**
	 * get all link destination 'item-type' from 'ViewItemType' to 'ItemType'.
	 * 
	 * @generated
	 */
	static public Item getItemTypeAll(Item viewItemType) {
		return viewItemType.getOutgoingItem(CadseGCST.VIEW_ITEM_TYPE_lt_ITEM_TYPE, false);
	}
//
//	/**
//	 * Creates the creation page creation page1.
//	 * 
//	 * @return the i page
//	 * 
//	 * @not generated
//	 */
//	protected IPage createCreationPageCreationPage1() {
//		return FieldsCore.createPage("creation-page1", "Create ViewItemType", "", 3, FieldsCore.createShortNameField());
//	}
//
//	/**
//	 * Creates the creation pages.
//	 * 
//	 * @param theItemParent
//	 *            the the item parent
//	 * @param theLinkType
//	 *            the the link type
//	 * @param desType
//	 *            the des type
//	 * 
//	 * @return the pages
//	 * 
//	 * @not generated
//	 */
//	@Override
//	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {
//
//		CreationAction action = new CreationAction(theItemParent, desType, theLinkType);
//
//		return FieldsCore.createWizard(action, createCreationPageCreationPage1());
//	}
//
//	/**
//	 * Creates the modification page modification page1.
//	 * 
//	 * @return the i page
//	 */
//	protected IPage createModificationPageModificationPage1() {
//		return FieldsCore.createPage("modification-page1", "ViewItemType", "", 3, FieldsCore.createUniqueNameField(),
//				createFieldItemType(), createFieldIsFirstElement());
//	}
//
//	/**
//	 * Creates the modification page.
//	 * 
//	 * @param item
//	 *            the item
//	 * 
//	 * @return the pages
//	 * 
//	 * @not generated
//	 */
//	@Override
//	public Pages createModificationPage(Item item) {
//		AbstractActionPage action = new ModificationAction(item);
//		return FieldsCore.createWizard(action, createModificationPageModificationPage1());
//	}
//
//	/**
//	 * Creates the field item type.
//	 * 
//	 * @return the uI field
//	 */
//	protected UIField createFieldItemType() {
//		TextLinkModelController mc = new TextLinkModelController(false, null, CadseGCST.VIEW_ITEM_TYPE_lt_ITEM_TYPE);
//		return new DTextUI("item-type", "item-type", EPosLabel.left, mc, null, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY,
//				1, null);
//	}
//
//	/**
//	 * Creates the field view link types.
//	 * 
//	 * @return the UI field
//	 * 
//	 * @not generated
//	 */
//	protected UIField createFieldViewLinkTypes() {
//		LinkModelController mc = new LinkModelController(false, null, CadseGCST.VIEW_ITEM_TYPE_lt_VIEW_LINK_TYPES);
//		return new DListUI("view-link-types", "view-link-types", EPosLabel.top, mc, new IC_LinkForBrowser_Combo_List(
//				"Select a value.", "Select a value.", CadseGCST.VIEW_ITEM_TYPE_lt_VIEW_LINK_TYPES), true, true);
//	}
//
//	/**
//	 * Creates the field ref.
//	 * 
//	 * @return the UI field
//	 * 
//	 * @not generated
//	 */
//	protected UIField createFieldRef() {
//		MC_StringToBoolean mc = new MC_StringToBoolean();
//		return new DCheckBoxUI("ref", "ref", EPosLabel.none, mc, null);
//	}
//
//	/**
//	 * Creates the field is first element.
//	 * 
//	 * @return the UI field
//	 * 
//	 * @not generated
//	 */
//	protected UIField createFieldIsFirstElement() {
//		MC_StringToBoolean mc = new MC_StringToBoolean();
//		return new DCheckBoxUI(CadseGCST.VIEW_ITEM_TYPE_at_IS_ROOT_ELEMENT, "is first element", EPosLabel.none, mc,
//				null);
//	}

	/**
	 * Gets the item type.
	 * 
	 * @param viewItemType
	 *            the view item type
	 * 
	 * @return the item type
	 * 
	 * @generated
	 */
	static public Item getItemType(Item viewItemType) {
		return viewItemType.getOutgoingItem(CadseGCST.VIEW_ITEM_TYPE_lt_ITEM_TYPE, true);
	}

	/**
	 * Sets the item type.
	 * 
	 * @param viewItemType
	 *            the view item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setItemType(Item viewItemType, Item value) throws CadseException {
		viewItemType.setOutgoingItem(CadseGCST.VIEW_ITEM_TYPE_lt_ITEM_TYPE,value);
	}

	/**
	 * get links 'view-link-types' from 'ViewItemType' to 'ViewLinkType'.
	 * 
	 * @generated
	 */
	static public List<Link> getViewLinkTypesLink(Item viewItemType) {
        return viewItemType.getOutgoingLinks(CadseGCST.VIEW_ITEM_TYPE_lt_VIEW_LINK_TYPES);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getViewLinkTypesAll(Item viewItemType) {
        return viewItemType.getOutgoingItems(CadseGCST.VIEW_ITEM_TYPE_lt_VIEW_LINK_TYPES, false);
    }

	/**
	 * Gets the view link types.
	 * 
	 * @param viewItemType
	 *            the view item type
	 * 
	 * @return the view link types
	 * 
	 * @generated
	 */
	static public Collection<Item> getViewLinkTypes(Item viewItemType) {
        return viewItemType.getOutgoingItems(CadseGCST.VIEW_ITEM_TYPE_lt_VIEW_LINK_TYPES,true);
    }

	/**
	 * Adds the view link types.
	 * 
	 * @param viewItemType
	 *            the view item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addViewLinkTypes(Item viewItemType, Item value) throws CadseException {
        viewItemType.addOutgoingItem(CadseGCST.VIEW_ITEM_TYPE_lt_VIEW_LINK_TYPES,value);
    }

	/**
	 * Removes the view link types.
	 * 
	 * @param viewItemType
	 *            the view item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeViewLinkTypes(Item viewItemType, Item value) throws CadseException {
        viewItemType.removeOutgoingItem(CadseGCST.VIEW_ITEM_TYPE_lt_VIEW_LINK_TYPES,value);
    }

	/**
	 * Checks if is ref attribute.
	 * 
	 * @param viewItemType
	 *            the view item type
	 * 
	 * @return true, if checks if is ref attribute
	 * 
	 * @generated
	 */
	public static final boolean isRefAttribute(Item viewItemType) {
		return viewItemType.getAttributeWithDefaultValue(CadseGCST.VIEW_ITEM_TYPE_at_REF_, false);
	}

	/**
	 * Sets the ref attribute.
	 * 
	 * @param viewItemType
	 *            the view item type
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setRefAttribute(Item viewItemType, boolean value) {
		try {
			viewItemType.setAttribute(CadseGCST.VIEW_ITEM_TYPE_at_REF_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isIsRootElementAttribute(Item viewItemType) {
		return viewItemType.getAttributeWithDefaultValue(CadseGCST.VIEW_ITEM_TYPE_at_IS_ROOT_ELEMENT_, false);
	}

	/**
	 * @generated
	 */
	public static final void setIsRootElementAttribute(Item viewItemType, boolean value) {
		try {
			viewItemType.setAttribute(CadseGCST.VIEW_ITEM_TYPE_at_IS_ROOT_ELEMENT_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is checks if is first element attribute.
	 * 
	 * @param viewItemType
	 *            the view item type
	 * 
	 * @return true, if is checks if is first element attribute
	 */
	public static final boolean isIsFirstElementAttribute(Item viewItemType) {

		Object value = viewItemType.getAttribute(CadseGCST.VIEW_ITEM_TYPE_at_IS_ROOT_ELEMENT_);
//		if (value == null) {
//			value = viewItemType.getAttribute("root");
//			try {
//				viewItemType.setAttribute("root", null);
//			} catch (CadseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				boolean ret = Convert.toBooleanFalseIfNull(value);
//				setIsRootElementAttribute(viewItemType, ret);
//				return ret;
//			} catch (Throwable t) {
//				return false;
//			}
//		}

		try {
			return Convert.toBooleanFalseIfNull(value);
		} catch (Throwable t) {
			return false;
		}
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
		return "false";
	}
}

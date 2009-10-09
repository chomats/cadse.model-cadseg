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

package fr.imag.adele.cadse.cadseg.managers.view;

import java.util.List;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.Pages;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.util.Convert;
import java.lang.String;

/**
 * The Class ViewLinkTypeManager.
 * 
 * @generated
 */
public class ViewLinkTypeManager extends DefaultItemManager {

	/** The Constant ID_ITEM_TYPE_VIEW_LINK_TYPE. */
	@SuppressWarnings("hiding")
	public static final int	ID_ITEM_TYPE_VIEW_LINK_TYPE	= 5;

	/** The Constant ID_LINK_TYPE_LINK_TYPE. */
	public static final int	ID_LINK_TYPE_LINK_TYPE		= 1;

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ViewLinkTypeManager() {
		super();
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
	public String computeUniqueName(Item item, String name, Item parent, LinkType lt) {
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
	 * get a link 'link-type' from 'ViewLinkType' to 'Link'.
	 * 
	 * @generated
	 */
	static public Link getLinkTypeLink(Item viewLinkType) {
		return viewLinkType.getOutgoingLink(WorkspaceCST.VIEW_LINK_TYPE_lt_LINK_TYPE);
	}

	/**
	 * get all link destination 'link-type' from 'ViewLinkType' to 'Link'.
	 * 
	 * @generated
	 */
	static public Item getLinkTypeAll(Item viewLinkType) {
		return viewLinkType.getOutgoingItem(WorkspaceCST.VIEW_LINK_TYPE_lt_LINK_TYPE, false);
	}

	/**
	 * Creates the creation page creation page1.
	 * 
	 * @return the i page
	 * 
	 * @not generated
	 */
	protected IPage createCreationPageCreationPage1() {
		return FieldsCore.createPage("creation-page1", "Create ViewLinkType", "", 3, FieldsCore.createShortNameField());
	}

	/**
	 * Creates the creation pages.
	 * 
	 * @param theItemParent
	 *            the the item parent
	 * @param theLinkType
	 *            the the link type
	 * @param desType
	 *            the des type
	 * 
	 * @return the pages
	 * 
	 * @not generated
	 */
	@Override
	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {

		CreationAction action = new CreationAction(theItemParent, desType, theLinkType);

		return FieldsCore.createWizard(action, createCreationPageCreationPage1());
	}

	/**
	 * Creates the modification page modification page1.
	 * 
	 * @return the i page
	 * 
	 * @not generated
	 */
	protected IPage createModificationPageModificationPage1() {
		return FieldsCore.createPage("modification-page1", "ViewLinkType", "", 3, FieldsCore.createUniqueNameField(),
				createFieldCanCreateItem(), createFieldCanCreateLink());
	}

	/**
	 * Creates the modification page.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the pages
	 * 
	 * @not generated
	 */
	@Override
	public Pages createModificationPage(Item item) {
		AbstractActionPage action = new ModificationAction(item);
		return FieldsCore.createWizard(action, createModificationPageModificationPage1());
	}

	/**
	 * Creates the field aggregation.
	 * 
	 * @return the UI field
	 * 
	 * @not generated
	 */
	protected UIField createFieldAggregation() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.VIEW_LINK_TYPE_at_AGGREGATION, "aggregation", EPosLabel.none, mc, null);
	}

	/**
	 * Creates the field link type.
	 * 
	 * @return the UI field
	 * 
	 * @not generated
	 */
	protected UIField createFieldLinkType() {
		LinkModelController mc = new LinkModelController(false, null, WorkspaceCST.VIEW_LINK_TYPE_lt_LINK_TYPE);
		return new DBrowserUI(WorkspaceCST.VIEW_LINK_TYPE_lt_LINK_TYPE.getName(), "link-type", EPosLabel.left, mc,
				new IC_LinkForBrowser_Combo_List("Select a value.", "Select a value.",
						WorkspaceCST.VIEW_LINK_TYPE_lt_LINK_TYPE), 0);
	}

	/**
	 * Creates the field can create item.
	 * 
	 * @return the UI field
	 * 
	 * @not generated
	 */
	protected UIField createFieldCanCreateItem() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.VIEW_LINK_TYPE_at_CAN_CREATE_ITEM, "can create item", EPosLabel.none, mc,
				null);
	}

	/**
	 * Creates the field can create link.
	 * 
	 * @return the UI field
	 * 
	 * @not generated
	 */
	protected UIField createFieldCanCreateLink() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.VIEW_LINK_TYPE_at_CAN_CREATE_LINK, "can create link", EPosLabel.none, mc,
				null);
	}

	/**
	 * Creates the field display create.
	 * 
	 * @return the UI field
	 * 
	 * @generated
	 */
	protected UIField createFieldDisplayCreate() {
		return new DTextUI(WorkspaceCST.VIEW_LINK_TYPE_at_DISPLAY_CREATE, "display-create", EPosLabel.left,
				new MC_AttributesItem(), null);
	}

	/**
	 * Checks if is aggregation attribute.
	 * 
	 * @param viewLinkType
	 *            the view link type
	 * 
	 * @return true, if checks if is aggregation attribute
	 * 
	 * @generated
	 */
	public static final boolean isAggregationAttribute(Item viewLinkType) {
		return viewLinkType.getAttributeWithDefaultValue(WorkspaceCST.VIEW_LINK_TYPE_at_AGGREGATION_, true);
	}

	/**
	 * Sets the aggregation attribute.
	 * 
	 * @param viewLinkType
	 *            the view link type
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setAggregationAttribute(Item viewLinkType, boolean value) {
		try {
			viewLinkType.setAttribute(WorkspaceCST.VIEW_LINK_TYPE_at_AGGREGATION_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the link type.
	 * 
	 * @param viewLinkType
	 *            the view link type
	 * 
	 * @return the link type
	 * 
	 * @generated
	 */
	static public Item getLinkType(Item viewLinkType) {
		return viewLinkType.getOutgoingItem(WorkspaceCST.VIEW_LINK_TYPE_lt_LINK_TYPE, true);
	}

	/**
	 * Sets the link type.
	 * 
	 * @param viewLinkType
	 *            the view link type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setLinkType(Item viewLinkType, Item value) throws CadseException {
		viewLinkType.setOutgoingItem(WorkspaceCST.VIEW_LINK_TYPE_lt_LINK_TYPE, value);
	}

	/**
	 * get a link '#invert_part_view-link-types_to_ViewItemType' from
	 * 'ViewLinkType' to 'ViewItemType'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_viewLinkTypes_to_ViewItemTypeLink(Item viewLinkType) {
		return viewLinkType
				.getOutgoingLink(WorkspaceCST.VIEW_LINK_TYPE_lt__$_INVERT_PART_VIEW_LINK_TYPES_TO_VIEW_ITEM_TYPE);
	}

	/**
	 * get all link destination '#invert_part_view-link-types_to_ViewItemType'
	 * from 'ViewLinkType' to 'ViewItemType'.
	 * 
	 * @generated
	 */
	static public Item get_$_Invert_part_viewLinkTypes_to_ViewItemTypeAll(Item viewLinkType) {
		return viewLinkType.getOutgoingItem(
				WorkspaceCST.VIEW_LINK_TYPE_lt__$_INVERT_PART_VIEW_LINK_TYPES_TO_VIEW_ITEM_TYPE, false);
	}

	/**
	 * get resolved link destination
	 * '#invert_part_view-link-types_to_ViewItemType' from 'ViewLinkType' to
	 * 'ViewItemType'.
	 * 
	 * @generated
	 */
	static public Item get_$_Invert_part_viewLinkTypes_to_ViewItemType(Item viewLinkType) {
		return viewLinkType.getOutgoingItem(
				WorkspaceCST.VIEW_LINK_TYPE_lt__$_INVERT_PART_VIEW_LINK_TYPES_TO_VIEW_ITEM_TYPE, true);
	}

	/**
	 * set a link '#invert_part_view-link-types_to_ViewItemType' from
	 * 'ViewLinkType' to 'ViewItemType'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_viewLinkTypes_to_ViewItemType(Item viewLinkType, Item value)
			throws CadseException {
		viewLinkType.setOutgoingItem(WorkspaceCST.VIEW_LINK_TYPE_lt__$_INVERT_PART_VIEW_LINK_TYPES_TO_VIEW_ITEM_TYPE,
				value);
	}

	/**
	 * Checks if is can create item attribute.
	 * 
	 * @param viewLinkType
	 *            the view link type
	 * 
	 * @return true, if checks if is can create item attribute
	 * 
	 * @generated
	 */
	public static final boolean isCanCreateItemAttribute(Item viewLinkType) {
		return viewLinkType.getAttributeWithDefaultValue(WorkspaceCST.VIEW_LINK_TYPE_at_CAN_CREATE_ITEM_, true);
	}

	/**
	 * Sets the can create item attribute.
	 * 
	 * @param viewLinkType
	 *            the view link type
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setCanCreateItemAttribute(Item viewLinkType, boolean value) {
		try {
			viewLinkType.setAttribute(WorkspaceCST.VIEW_LINK_TYPE_at_CAN_CREATE_ITEM_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is can create link attribute.
	 * 
	 * @param viewLinkType
	 *            the view link type
	 * 
	 * @return true, if checks if is can create link attribute
	 * 
	 * @generated
	 */
	public static final boolean isCanCreateLinkAttribute(Item viewLinkType) {
		return viewLinkType.getAttributeWithDefaultValue(WorkspaceCST.VIEW_LINK_TYPE_at_CAN_CREATE_LINK_, true);
	}

	/**
	 * Sets the can create link attribute.
	 * 
	 * @param viewLinkType
	 *            the view link type
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setCanCreateLinkAttribute(Item viewLinkType, boolean value) {
		try {
			viewLinkType.setAttribute(WorkspaceCST.VIEW_LINK_TYPE_at_CAN_CREATE_LINK_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the display create attribute.
	 * 
	 * @param viewLinkType
	 *            the view link type
	 * 
	 * @return the display create attribute
	 * 
	 * @generated
	 */
	public static final String getDisplayCreateAttribute(Item viewLinkType) {
		return viewLinkType.getAttributeWithDefaultValue(WorkspaceCST.VIEW_LINK_TYPE_at_DISPLAY_CREATE_, null);
	}

	/**
	 * Sets the display create attribute.
	 * 
	 * @param viewLinkType
	 *            the view link type
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setDisplayCreateAttribute(Item viewLinkType, String value) {
		try {
			viewLinkType.setAttribute(WorkspaceCST.VIEW_LINK_TYPE_at_DISPLAY_CREATE_, value);
		} catch (Throwable t) {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#validate(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.IItemManager.ProblemReporter)
	 */
	@Override
	public List<Item> validate(Item item, ProblemReporter reporter) {
		List<Item> ret = super.validate(item, reporter);
		Item lt = getLinkType(item);
		if (lt == null) {
			reporter.report(item, 1, "Cannot find the link type from view link type {0}", item.getQualifiedName());
			return ret;
		}
		Item itemtypedest = LinkManager.getDestination(lt);
		if (itemtypedest == null) {
			reporter.report(item, 1, "Cannot find destination of link type {0}", lt.getQualifiedName());
			return ret;
		}

		Item itemtypesource = LinkManager.getSource(lt);

		Item view = item.getPartParent().getPartParent();
		Item viewItemtype = ViewManager.getViewItemType(view, itemtypedest);
		if (viewItemtype == null) {
			reporter.report(item, 1,
					"Cannot find the item type {0} in the view {1}, it is needed for the link type {2}::{3}.",
					itemtypedest.getName(), view.getName(), itemtypesource.getName(), lt.getName());
			return ret;
		}

		return ret;
	}

	//
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
	// if (itemAnnoted.isInstanceOf(WorkspaceCST.LINK)) {
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
		return "Cannot rename";
	}

}

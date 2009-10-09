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

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.Pages;
import fede.workspace.model.manager.properties.FieldsCore;

/**
 * The Class ViewModelManager.
 * 
 * @generated
 */
public class ViewModelManager extends DefaultItemManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ViewModelManager() {
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
	 * @generated
	 */
	protected IPage createCreationPageCreationPage1() {
		return FieldsCore.createPage("creation-page1", "Create ViewModel", "", 3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createCreationPages(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {
		CreationAction action = new CreationAction(theItemParent, desType, theLinkType,
				CadseDefinitionManager.VIEW_MODEL);

		return FieldsCore.createWizard(action, createCreationPageCreationPage1());
	}

	// /**
	// * Creates the modification page modification page1.
	// *
	// * @return the i page
	// *
	// * @generated
	// */
	// protected IPage createModificationPageModificationPage1() {
	// return FieldsCore.createPage("modification-page1","ViewModel","",3,
	// createFieldViews()
	// );
	// }

	// /* (non-Javadoc)
	// * @see
	// fede.workspace.model.manager.DefaultItemManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	// */
	// @Override
	// public Pages createModificationPage(Item item) {
	// AbstractActionPage action = new ModificationAction(item);
	// return FieldsCore.createWizard(action,
	// createModificationPageModificationPage1()
	// );
	// }
	//
	// /**
	// * Creates the field views.
	// *
	// * @return the d list ui
	// *
	// * @generated
	// */
	// protected DListUI createFieldViews() {
	// LinkModelController mc = new LinkModelController(false, null,
	// WorkspaceCST.VIEW_MODEL_lt_VIEWS);
	// return new DListUI("views",
	// "views",
	// EPosLabel.top,
	// mc,
	// new IC_LinkForBrowser_Combo_List("Select a value.","Select a
	// value.",WorkspaceCST.VIEW_MODEL_lt_VIEWS),
	// true,true
	// );
	// }
	/**
	 * Gets the views.
	 * 
	 * @param viewModel
	 *            the view model
	 * 
	 * @return the views
	 * 
	 * @generated
	 */
	static public Collection<Item> getViews(Item viewModel) {
		return viewModel.getOutgoingItems(WorkspaceCST.VIEW_MODEL_lt_VIEWS, true);
	}

	/**
	 * Adds the views.
	 * 
	 * @param viewModel
	 *            the view model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addViews(Item viewModel, Item value) throws CadseException {
		viewModel.addOutgoingItem(WorkspaceCST.VIEW_MODEL_lt_VIEWS, value);
	}

	/**
	 * Removes the views.
	 * 
	 * @param viewModel
	 *            the view model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeViews(Item viewModel, Item value) throws CadseException {
		viewModel.removeOutgoingItem(WorkspaceCST.VIEW_MODEL_lt_VIEWS, value);
	}

	/**
	 * get a link '#invert_part_view-model_to_CadseDefinition' from 'ViewModel'
	 * to 'CadseDefinition'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_viewModel_to_CadseDefinitionLink(Item viewModel) {
		return viewModel.getOutgoingLink(WorkspaceCST.VIEW_MODEL_lt__$_INVERT_PART_VIEW_MODEL_TO_CADSE_DEFINITION);
	}

	/**
	 * get all link destination '#invert_part_view-model_to_CadseDefinition'
	 * from 'ViewModel' to 'CadseDefinition'.
	 * 
	 * @generated
	 */
	static public Item get_$_Invert_part_viewModel_to_CadseDefinitionAll(Item viewModel) {
		return viewModel.getOutgoingItem(WorkspaceCST.VIEW_MODEL_lt__$_INVERT_PART_VIEW_MODEL_TO_CADSE_DEFINITION,
				false);
	}

	/**
	 * get resolved link destination
	 * '#invert_part_view-model_to_CadseDefinition' from 'ViewModel' to
	 * 'CadseDefinition'.
	 * 
	 * @generated
	 */
	static public Item get_$_Invert_part_viewModel_to_CadseDefinition(Item viewModel) {
		return viewModel
				.getOutgoingItem(WorkspaceCST.VIEW_MODEL_lt__$_INVERT_PART_VIEW_MODEL_TO_CADSE_DEFINITION, true);
	}

	/**
	 * set a link '#invert_part_view-model_to_CadseDefinition' from 'ViewModel'
	 * to 'CadseDefinition'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_viewModel_to_CadseDefinition(Item viewModel, Item value) throws CadseException {
		viewModel.setOutgoingItem(WorkspaceCST.VIEW_MODEL_lt__$_INVERT_PART_VIEW_MODEL_TO_CADSE_DEFINITION, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return DefaultItemManager.CANNOT_RENAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canDeleteItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canDeleteItem(Item item) {
		Item parent = item.getPartParent();
		if (parent == null) {
			return null;
		}

		return DefaultItemManager.CANNOT_DELETE;
	}

}

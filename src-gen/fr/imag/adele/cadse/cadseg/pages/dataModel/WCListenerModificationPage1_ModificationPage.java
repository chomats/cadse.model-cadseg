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
package fr.imag.adele.cadse.cadseg.pages.dataModel;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;

/**
 * @generated
 */
public class WCListenerModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DTextUI __short_name__;

	/**
	 * @generated
	 */
	protected DListUI fieldListenItemTypes;

	/**
	 * @generated
	 */
	protected DListUI fieldListenAttributeDefinitions;

	/**
	 * @generated
	 */
	protected WCListenerModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public WCListenerModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "WCListener", "WCListener", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldListenItemTypes = createFieldListenItemTypes();
		this.fieldListenAttributeDefinitions = createFieldListenAttributeDefinitions();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldListenItemTypes,
				this.fieldListenAttributeDefinitions);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createInternalNameField() {
		return FieldsCore.createUniqueNameField();
	}

	/**
	 * @generated
	 */
	public DListUI createFieldListenItemTypes() {
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.WCLISTENER_lt_LISTEN_ITEM_TYPES);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.WCLISTENER_lt_LISTEN_ITEM_TYPES);
		return new DListUI(CadseGCST.WCLISTENER_lt_LISTEN_ITEM_TYPES.getName(),
				"listenItemTypes", EPosLabel.top, mc, ic, true, false, false,
				false);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldListenAttributeDefinitions() {
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.WCLISTENER_lt_LISTEN_ATTRIBUTE_DEFINITIONS);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.WCLISTENER_lt_LISTEN_ATTRIBUTE_DEFINITIONS);
		return new DListUI(CadseGCST.WCLISTENER_lt_LISTEN_ATTRIBUTE_DEFINITIONS
				.getName(), "listenAttributeDefinitions", EPosLabel.top, mc,
				ic, true, false, false, false);
	}

}
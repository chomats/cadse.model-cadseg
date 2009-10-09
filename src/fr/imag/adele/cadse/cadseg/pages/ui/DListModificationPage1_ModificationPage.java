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
package fr.imag.adele.cadse.cadseg.pages.ui;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;

/**
 * @generated
 */
public class DListModificationPage1_ModificationPage extends DisplayModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldEditableButton;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldUpdateButton;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldOrderButton;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldShowFilter;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldExtendsIC;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldExtendsMC;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldExtendsUI;

	/**
	 * @generated
	 */
	protected DListModificationPage1_ModificationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public DListModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "DList", "DList", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldEditableButton = createFieldEditableButton();
		this.fieldUpdateButton = createFieldUpdateButton();
		this.fieldOrderButton = createFieldOrderButton();
		this.fieldShowFilter = createFieldShowFilter();
		this.fieldExtendsIC = createFieldExtendsIC();
		this.fieldExtendsMC = createFieldExtendsMC();
		this.fieldExtendsUI = createFieldExtendsUI();
		this.fieldEditable = createFieldEditable();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldEditableButton, this.fieldUpdateButton, this.fieldOrderButton,
				this.fieldShowFilter, this.fieldExtendsIC, this.fieldExtendsMC, this.fieldExtendsUI, this.fieldEditable);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldEditableButton() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.DLIST_at_EDITABLE_BUTTON, "editable-button", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldUpdateButton() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.DLIST_at_UPDATE_BUTTON, "update-button", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldOrderButton() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.DLIST_at_ORDER_BUTTON, "order-button", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldShowFilter() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.DLIST_at_SHOW_FILTER, "show-filter", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsIC() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.DISPLAY_at_EXTENDS_IC, "extendsIC", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsMC() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.DISPLAY_at_EXTENDS_MC, "extendsMC", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsUI() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.DISPLAY_at_EXTENDS_UI, "extendsUI", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldEditable() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.DISPLAY_at_EDITABLE, "editable", EPosLabel.none, mc, null);
	}

}

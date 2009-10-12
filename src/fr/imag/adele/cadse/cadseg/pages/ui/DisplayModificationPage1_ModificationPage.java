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
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;

/**
 * @generated
 */
public class DisplayModificationPage1_ModificationPage extends PageImpl {

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
	protected DCheckBoxUI fieldExtendsInteractionController;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldExtendsModelController;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldExtendsUiField;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldEditable;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldEnable;

	/**
	    @generated
	 */
	protected DBrowserUI fieldIc;

	/**
	    @generated
	 */
	protected DBrowserUI fieldMc;

	/**
	 * @generated
	 */
	protected DisplayModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public DisplayModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Display", "Display", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldExtendsInteractionController = createFieldExtendsInteractionController();
		this.fieldExtendsModelController = createFieldExtendsModelController();
		this.fieldExtendsUiField = createFieldExtendsUiField();
		this.fieldEditable = createFieldEditable();
		this.fieldEnable = createFieldEnable();
		this.fieldIc = createFieldIc();
		this.fieldMc = createFieldMc();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldExtendsInteractionController,
				this.fieldExtendsModelController, this.fieldExtendsUiField,
				this.fieldEditable, this.fieldEnable, this.fieldIc,
				this.fieldMc);

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
	public DCheckBoxUI createFieldExtendsInteractionController() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_IC,
				"extends interaction controller", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsModelController() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_MC,
				"extends model controller", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsUiField() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_UI,
				"extends ui field", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldEditable() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EDITABLE, "editable",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldEnable() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_ENABLE, "enable",
				EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldIc() {
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.DISPLAY_lt_IC);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.", CadseGCST.DISPLAY_lt_IC);
		return new DBrowserUI(CadseGCST.DISPLAY_lt_IC.getName(), "ic",
				EPosLabel.left, mc, ic);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldMc() {
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.DISPLAY_lt_MC);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.", CadseGCST.DISPLAY_lt_MC);
		return new DBrowserUI(CadseGCST.DISPLAY_lt_MC.getName(), "mc",
				EPosLabel.left, mc, ic);
	}

}

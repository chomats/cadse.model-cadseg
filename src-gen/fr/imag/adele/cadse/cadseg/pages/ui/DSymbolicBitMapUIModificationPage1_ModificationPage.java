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
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_DefaultForList;
import fede.workspace.model.manager.properties.impl.mc.MC_DefaultForList;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;

/**
 * @generated
 */
public class DSymbolicBitMapUIModificationPage1_ModificationPage extends
		DisplayModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldExtendsUI;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldExtendsIC;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldExtendsMC;

	/**
	 * @generated
	 */
	protected DTextUI fieldNumberColumn;

	/**
	 * @generated
	 */
	protected DListUI fieldLabels;

	/**
	 * @generated
	 */
	protected DSymbolicBitMapUIModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public DSymbolicBitMapUIModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "DSymbolicBitMapUI", "DSymbolicBitMapUI",
				"", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldExtendsUI = createFieldExtendsUI();
		this.fieldEnable = createFieldEnable();
		this.fieldExtendsIC = createFieldExtendsIC();
		this.fieldEditable = createFieldEditable();
		this.fieldExtendsMC = createFieldExtendsMC();
		this.fieldNumberColumn = createFieldNumberColumn();
		this.fieldLabels = createFieldLabels();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldExtendsUI, this.fieldEnable,
				this.fieldExtendsIC, this.fieldEditable, this.fieldExtendsMC,
				this.fieldNumberColumn, this.fieldLabels);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsUI() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_UI, "extendsUI",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsIC() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_IC, "extendsIC",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsMC() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_MC, "extendsMC",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldNumberColumn() {
		return new DTextUI(CadseGCST.DSYMBOLIC_BIT_MAP_UI_at_NUMBER_COLUMN,
				"number-column", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldLabels() {
		MC_DefaultForList mc = new MC_DefaultForList(0, -1);
		IC_DefaultForList ic = new IC_DefaultForList("Select a value.",
				"Select a value.", false);
		return new DListUI(CadseGCST.DSYMBOLIC_BIT_MAP_UI_at_LABELS, "labels",
				EPosLabel.top, mc, ic, true, false, false, false);
	}

}

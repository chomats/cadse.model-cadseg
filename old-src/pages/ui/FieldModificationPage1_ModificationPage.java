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
package fr.imag.adele.cadse.cadseg.pages.ui;

import fede.workspace.model.manager.properties.impl.mc.StringToEnumModelController;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_EnumForBrowser_Combo;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class FieldModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldAttribute;

	/**
	 * @generated
	 */
	protected DTextUI fieldLabel;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldPosition;

	/**
	    @generated
	 */
	protected DBrowserUI fieldOverwritefield;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldEditable;

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
	protected FieldModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public FieldModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Field", "Field", "", false, 3);
		this.item = item;
		this.fieldAttribute = createFieldAttribute();
		this.fieldLabel = createFieldLabel();
		this.fieldPosition = createFieldPosition();
		this.fieldOverwritefield = createFieldOverwritefield();
		this.fieldEditable = createFieldEditable();
		this.fieldIc = createFieldIc();
		this.fieldMc = createFieldMc();
		setActionPage(null);
		addLast(this.fieldAttribute, this.fieldLabel, this.fieldPosition,
				this.fieldOverwritefield, this.fieldEditable, this.fieldIc,
				this.fieldMc);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldAttribute() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.FIELD_lt_ATTRIBUTE);
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.FIELD_lt_ATTRIBUTE);
		return new DBrowserUI(CadseGCST.FIELD_lt_ATTRIBUTE.getName(),
				"attribute", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldLabel() {
		return new DTextUI(CadseGCST.FIELD_at_LABEL, "label", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldPosition() {
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("??", "??",
				EPosLabel.class);
		StringToEnumModelController mc = new StringToEnumModelController(
				EPosLabel.class, EPosLabel.defaultpos);
		return new DBrowserUI(CadseGCST.FIELD_at_POSITION, "position",
				EPosLabel.left, mc, ic);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldOverwritefield() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.FIELD_lt_OVERWRITEFIELD);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.FIELD_lt_OVERWRITEFIELD);
		return new DBrowserUI(CadseGCST.FIELD_lt_OVERWRITEFIELD.getName(),
				"overwritefield", EPosLabel.left, mc, ic);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldEditable() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.FIELD_at_EDITABLE, "editable",
				EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldIc() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.", CadseGCST.FIELD_lt_IC);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.FIELD_lt_IC);
		return new DBrowserUI(CadseGCST.FIELD_lt_IC.getName(), "ic",
				EPosLabel.left, mc, ic);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldMc() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.", CadseGCST.FIELD_lt_MC);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.FIELD_lt_MC);
		return new DBrowserUI(CadseGCST.FIELD_lt_MC.getName(), "mc",
				EPosLabel.left, mc, ic);
	}

}

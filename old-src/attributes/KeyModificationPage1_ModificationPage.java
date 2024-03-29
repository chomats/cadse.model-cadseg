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
package fr.imag.adele.cadse.cadseg.pages.attributes;

import fede.workspace.model.manager.properties.impl.mc.StringToEnumModelController;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.enumdef.TWCommitKind;
import fr.imag.adele.cadse.core.enumdef.TWEvol;
import fr.imag.adele.cadse.core.enumdef.TWUpdateKind;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_EnumForBrowser_Combo;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;

/**
 * @generated
 */
public class KeyModificationPage1_ModificationPage extends
		AttributeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldTWRevSpecific;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldTWEvol;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldTWCommitKind;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldTWUpdateKind;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldCannotBeUndefined;

	/**
	 * @generated
	 */
	protected DListUI fieldWcListens;

	/**
	 * @generated
	 */
	protected KeyModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public KeyModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Key", "Key", "", false, 3);
		this.item = item;
		this.fieldTWRevSpecific = createFieldTWRevSpecific();
		this.fieldTWEvol = createFieldTWEvol();
		this.fieldNatif = createFieldNatif();
		this.fieldTWCommitKind = createFieldTWCommitKind();
		this.fieldTWUpdateKind = createFieldTWUpdateKind();
		this.fieldTransient = createFieldTransient();
		this.fieldCannotBeUndefined = createFieldCannotBeUndefined();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldWcListens = createFieldWcListens();
		this.fieldFinalValue = createFieldFinalValue();
		this.fieldRequire = createFieldRequire();
		this.fieldMustBeInitialized = createFieldMustBeInitialized();
		this.fieldIsList = createFieldIsList();
		setActionPage(null);
		addLast(this.fieldTWRevSpecific, this.fieldTWEvol, this.fieldNatif,
				this.fieldTWCommitKind, this.fieldTWUpdateKind,
				this.fieldTransient, this.fieldCannotBeUndefined,
				this.fieldDefaultValue, this.fieldWcListens,
				this.fieldFinalValue, this.fieldRequire,
				this.fieldMustBeInitialized, this.fieldIsList);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldTWRevSpecific() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC,
				"TWRevSpecific", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWEvol() {
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("??", "??",
				TWEvol.class);
		StringToEnumModelController mc = new StringToEnumModelController(
				TWEvol.class, TWEvol.twImmutable);
		return new DBrowserUI(CadseGCST.ATTRIBUTE_at_TWEVOL, "TWEvol",
				EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWCommitKind() {
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("??", "??",
				TWCommitKind.class);
		StringToEnumModelController mc = new StringToEnumModelController(
				TWCommitKind.class, TWCommitKind.conflict);
		return new DBrowserUI(CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND,
				"TWCommitKind", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWUpdateKind() {
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("??", "??",
				TWUpdateKind.class);
		StringToEnumModelController mc = new StringToEnumModelController(
				TWUpdateKind.class, TWUpdateKind.merge);
		return new DBrowserUI(CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND,
				"TWUpdateKind", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldCannotBeUndefined() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED,
				"cannot-be-undefined", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldWcListens() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ATTRIBUTE_lt_WC_LISTENS);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ATTRIBUTE_lt_WC_LISTENS);
		return new DListUI(CadseGCST.ATTRIBUTE_lt_WC_LISTENS.getName(),
				"wcListens", EPosLabel.top, mc, ic, true, false, false, false);
	}

}

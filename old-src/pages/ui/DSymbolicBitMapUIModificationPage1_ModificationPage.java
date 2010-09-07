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

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_DefaultForList;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_DefaultForList;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

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
		this.fieldExtendsUI = createFieldExtendsUI();
		this.fieldExtendsIC = createFieldExtendsIC();
		this.fieldExtendsMC = createFieldExtendsMC();
		this.fieldNumberColumn = createFieldNumberColumn();
		this.fieldLabels = createFieldLabels();
		setActionPage(null);
		addLast(this.fieldExtendsUI, this.fieldExtendsIC, this.fieldExtendsMC,
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
		IC_DefaultForList ic = new IC_DefaultForList("", "", false);
		MC_DefaultForList mc = new MC_DefaultForList(0, -1);
		return new DListUI(CadseGCST.DSYMBOLIC_BIT_MAP_UI_at_LABELS, "labels",
				EPosLabel.top, mc, ic, true, false, false, false);
	}

}

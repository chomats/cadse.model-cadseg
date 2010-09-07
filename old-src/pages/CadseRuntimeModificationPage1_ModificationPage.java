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
package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;

/**
 @generated
 */
public class CadseRuntimeModificationPage1_ModificationPage extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DListUI fieldItemTypes;

	/**
	    @generated
	 */
	protected DListUI fieldExtends;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldExecuted;

	/**
	    @generated
	 */
	protected CadseRuntimeModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public CadseRuntimeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Cadse", "Cadse", "", false, 3);
		this.item = item;
		this.fieldItemTypes = createFieldItemTypes();
		this.fieldExtends = createFieldExtends();
		this.fieldExecuted = createFieldExecuted();
		setActionPage(null);
		addLast(this.fieldItemTypes, this.fieldExtends, this.fieldExecuted);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DListUI createFieldItemTypes() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.CADSE_RUNTIME_lt_ITEM_TYPES);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.CADSE_RUNTIME_lt_ITEM_TYPES);
		return new DListUI(CadseGCST.CADSE_RUNTIME_lt_ITEM_TYPES.getName(),
				"itemTypes", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	    @generated
	 */
	public DListUI createFieldExtends() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.CADSE_RUNTIME_lt_EXTENDS);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.CADSE_RUNTIME_lt_EXTENDS);
		return new DListUI(CadseGCST.CADSE_RUNTIME_lt_EXTENDS.getName(),
				"extends", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldExecuted() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.CADSE_RUNTIME_at_EXECUTED, "executed",
				EPosLabel.none, mc, null);
	}

}

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
package fr.imag.adele.cadse.cadseg.pages.build;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;

/**
 * @generated
 */
public class CompositeItemTypeModificationPage1_ModificationPage extends
		PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldItemType;

	/**
	 * @generated
	 */
	protected DListUI fieldComposers;

	/**
	 * @generated
	 */
	protected DListUI fieldBuilders;

	/**
	 * @generated
	 */
	protected CompositeItemTypeModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public CompositeItemTypeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "CompositeItemType", "CompositeItemType",
				"", false, 3);
		this.item = item;
		this.fieldItemType = createFieldItemType();
		this.fieldComposers = createFieldComposers();
		this.fieldBuilders = createFieldBuilders();
		setActionPage(null);
		addLast(this.fieldItemType, this.fieldComposers, this.fieldBuilders);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldItemType() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.COMPOSITE_ITEM_TYPE_lt_ITEM_TYPE);
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.COMPOSITE_ITEM_TYPE_lt_ITEM_TYPE);
		return new DBrowserUI(CadseGCST.COMPOSITE_ITEM_TYPE_lt_ITEM_TYPE
				.getName(), "item-type", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldComposers() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS);
		return new DListUI(
				CadseGCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS.getName(),
				"composers", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldBuilders() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.COMPOSITE_ITEM_TYPE_lt_BUILDERS);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.COMPOSITE_ITEM_TYPE_lt_BUILDERS);
		return new DListUI(CadseGCST.COMPOSITE_ITEM_TYPE_lt_BUILDERS.getName(),
				"builders", EPosLabel.top, mc, ic, true, false, false, false);
	}

}

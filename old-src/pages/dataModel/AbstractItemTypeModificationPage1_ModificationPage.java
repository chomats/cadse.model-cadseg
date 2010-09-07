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
package fr.imag.adele.cadse.cadseg.pages.dataModel;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class AbstractItemTypeModificationPage1_ModificationPage extends
		PageImpl {

	/**
	    @generated
	 */
	public Item item;
	/**
	 * @generated
	 */
	protected DListUI fieldAttributes;

	/**
	    @generated
	 */
	protected DTextUI fieldIdRuntime;

	/**
	 * @generated
	 */
	protected AbstractItemTypeModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public AbstractItemTypeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "AbstractItemType", "AbstractItemType", "",
				false, 3);
		this.item = item;
		this.fieldAttributes = createFieldAttributes();
		this.fieldIdRuntime = createFieldIdRuntime();
		setActionPage(null);
		addLast(this.fieldAttributes, this.fieldIdRuntime);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DListUI createFieldAttributes() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES);
		return new DListUI(
				CadseGCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES.getName(),
				"attributes", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldIdRuntime() {
		return new DTextUI(CadseGCST.ABSTRACT_ITEM_TYPE_at_ID_RUNTIME,
				"id-runtime", EPosLabel.left, new MC_AttributesItem(), null, 1,
				"", false, false, false);
	}

}

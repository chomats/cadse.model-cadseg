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
package fr.imag.adele.cadse.cadseg.pages.view;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class ViewLinkTypeModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldLinkType;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldCanCreateItem;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldCanCreateLink;

	/**
	 * @generated
	 */
	protected DTextUI fieldDisplayCreate;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldAggregation;

	/**
	 * @generated
	 */
	protected ViewLinkTypeModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ViewLinkTypeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "ViewLinkType", "ViewLinkType", "", false,
				3);
		this.item = item;
		this.fieldLinkType = createFieldLinkType();
		this.fieldCanCreateItem = createFieldCanCreateItem();
		this.fieldCanCreateLink = createFieldCanCreateLink();
		this.fieldDisplayCreate = createFieldDisplayCreate();
		this.fieldAggregation = createFieldAggregation();
		setActionPage(null);
		addLast(this.fieldLinkType, this.fieldCanCreateItem,
				this.fieldCanCreateLink, this.fieldDisplayCreate,
				this.fieldAggregation);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldLinkType() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.VIEW_LINK_TYPE_lt_LINK_TYPE);
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.VIEW_LINK_TYPE_lt_LINK_TYPE);
		return new DBrowserUI(CadseGCST.VIEW_LINK_TYPE_lt_LINK_TYPE.getName(),
				"link-type", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldCanCreateItem() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.VIEW_LINK_TYPE_at_CAN_CREATE_ITEM,
				"can-create-item", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldCanCreateLink() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.VIEW_LINK_TYPE_at_CAN_CREATE_LINK,
				"can-create-link", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldDisplayCreate() {
		return new DTextUI(CadseGCST.VIEW_LINK_TYPE_at_DISPLAY_CREATE,
				"display-create", EPosLabel.left, new MC_AttributesItem(),
				null, 1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldAggregation() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.VIEW_LINK_TYPE_at_AGGREGATION,
				"aggregation", EPosLabel.none, mc, null);
	}

}

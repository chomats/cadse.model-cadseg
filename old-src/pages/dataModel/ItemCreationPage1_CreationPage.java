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
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_Integer;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 @generated
 */
public class ItemCreationPage1_CreationPage extends PageImpl {

	/**
	    @generated
	 */
	public Item parent;

	/**
	    @generated
	 */
	public ItemType it;

	/**
	    @generated
	 */
	public LinkType lt;

	/**
	    @generated
	 */
	protected DTextUI __short_name__;

	/**
	    @generated
	 */
	protected DBrowserUI fieldInstanceOf;

	/**
	    @generated
	 */
	protected DBrowserUI fieldParent;

	/**
	    @generated
	 */
	protected DListUI fieldModifiedAttributes;

	/**
	    @generated
	 */
	protected DTextUI fieldTWVersion;

	/**
	    @generated
	 */
	protected DTextUI fieldId;

	/**
	    @generated
	 */
	protected DTextUI fieldCommittedDate;

	/**
	    @generated
	 */
	protected ItemCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public ItemCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create Item", "Create Item", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.__short_name__ = createInternalNameField();
		this.fieldInstanceOf = createFieldInstanceOf();
		this.fieldParent = createFieldParent();
		this.fieldModifiedAttributes = createFieldModifiedAttributes();
		this.fieldTWVersion = createFieldTWVersion();
		this.fieldId = createFieldId();
		this.fieldCommittedDate = createFieldCommittedDate();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldInstanceOf, this.fieldParent,
				this.fieldModifiedAttributes, this.fieldTWVersion,
				this.fieldId, this.fieldCommittedDate);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DTextUI createInternalNameField() {
		return FieldsCore.createShortNameField();
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldInstanceOf() {
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.ITEM_lt_INSTANCE_OF);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ITEM_lt_INSTANCE_OF);
		return new DBrowserUI(CadseGCST.ITEM_lt_INSTANCE_OF.getName(),
				"instance-of", EPosLabel.left, mc, ic);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldParent() {
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ITEM_lt_PARENT);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.", CadseGCST.ITEM_lt_PARENT);
		return new DBrowserUI(CadseGCST.ITEM_lt_PARENT.getName(), "parent",
				EPosLabel.left, mc, ic);
	}

	/**
	    @generated
	 */
	public DListUI createFieldModifiedAttributes() {
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES);
		return new DListUI(CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES.getName(),
				"modified-attributes", EPosLabel.top, mc, ic, true, false,
				false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldTWVersion() {
		MC_Integer mc = new MC_Integer(0, 0, null, null, null);
		return new DTextUI(CadseGCST.ITEM_at_TW_VERSION, "TW-version",
				EPosLabel.left, mc, null, 1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldId() {
		return new DTextUI(CadseGCST.ITEM_at_ID, "id", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldCommittedDate() {
		return new DTextUI(CadseGCST.ITEM_at_COMMITTED_DATE, "committedDate",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

}

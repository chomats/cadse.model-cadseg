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
import fr.imag.adele.cadse.core.impl.ui.mc.MC_Integer;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_StringToBoolean;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 @generated
 */
public class ItemModificationPage1_ModificationPage extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DTextUI __short_name__;

	/**
	    @generated
	 */
	protected DTextUI fieldCommittedBy;

	/**
	    @generated
	 */
	protected DTextUI fieldDisplayName;

	/**
	    @generated
	 */
	protected DBrowserUI fieldInstanceOf;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldItemHidden;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldItemReadonly;

	/**
	    @generated
	 */
	protected DTextUI fieldName;

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
	protected DTextUI fieldQualifiedName;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldRequireNewRev;

	/**
	    @generated
	 */
	protected DTextUI fieldTWVersion;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldRevModified;

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
	protected ItemModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public ItemModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Item", "Item", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldCommittedBy = createFieldCommittedBy();
		this.fieldDisplayName = createFieldDisplayName();
		this.fieldInstanceOf = createFieldInstanceOf();
		this.fieldItemHidden = createFieldItemHidden();
		this.fieldItemReadonly = createFieldItemReadonly();
		this.fieldName = createFieldName();
		this.fieldParent = createFieldParent();
		this.fieldModifiedAttributes = createFieldModifiedAttributes();
		this.fieldQualifiedName = createFieldQualifiedName();
		this.fieldRequireNewRev = createFieldRequireNewRev();
		this.fieldTWVersion = createFieldTWVersion();
		this.fieldRevModified = createFieldRevModified();
		this.fieldId = createFieldId();
		this.fieldCommittedDate = createFieldCommittedDate();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldCommittedBy,
				this.fieldDisplayName, this.fieldInstanceOf,
				this.fieldItemHidden, this.fieldItemReadonly, this.fieldName,
				this.fieldParent, this.fieldModifiedAttributes,
				this.fieldQualifiedName, this.fieldRequireNewRev,
				this.fieldTWVersion, this.fieldRevModified, this.fieldId,
				this.fieldCommittedDate);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DTextUI createInternalNameField() {
		return FieldsCore.createUniqueNameField();
	}

	/**
	    @generated
	 */
	public DTextUI createFieldCommittedBy() {
		return new DTextUI(CadseGCST.ITEM_at_COMMITTED_BY, "committedBy",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldDisplayName() {
		return new DTextUI(CadseGCST.ITEM_at_DISPLAY_NAME, "display-name",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
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
	public DCheckBoxUI createFieldItemHidden() {
		MC_StringToBoolean mc = new MC_StringToBoolean();
		return new DCheckBoxUI(CadseGCST.ITEM_at_ITEM_HIDDEN, "item-hidden",
				EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldItemReadonly() {
		MC_StringToBoolean mc = new MC_StringToBoolean();
		return new DCheckBoxUI(CadseGCST.ITEM_at_ITEM_READONLY,
				"item-readonly", EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldName() {
		return new DTextUI(CadseGCST.ITEM_at_NAME, "name", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
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
	public DTextUI createFieldQualifiedName() {
		return new DTextUI(CadseGCST.ITEM_at_QUALIFIED_NAME, "qualified-name",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldRequireNewRev() {
		MC_StringToBoolean mc = new MC_StringToBoolean();
		return new DCheckBoxUI(CadseGCST.ITEM_at_REQUIRE_NEW_REV,
				"requireNewRev", EPosLabel.none, mc, null);
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
	public DCheckBoxUI createFieldRevModified() {
		MC_StringToBoolean mc = new MC_StringToBoolean();
		return new DCheckBoxUI(CadseGCST.ITEM_at_REV_MODIFIED, "revModified",
				EPosLabel.none, mc, null);
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

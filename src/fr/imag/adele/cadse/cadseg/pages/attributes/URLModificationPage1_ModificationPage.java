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
package fr.imag.adele.cadse.cadseg.pages.attributes;

import fede.workspace.model.manager.properties.impl.ic.IC_EnumForBrowser_Combo;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.mc.StringToEnumModelController;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.enumdef.TWCommitKind;
import fr.imag.adele.cadse.core.enumdef.TWEvol;
import fr.imag.adele.cadse.core.enumdef.TWUpdateKind;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;

/**
 * @generated
 */
public class URLModificationPage1_ModificationPage extends AttributeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DTextUI		fieldMin;

	/**
	 * @generated
	 */
	protected DTextUI		fieldMax;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldTWRevSpecific;

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldTWEvol;

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldTWCommitKind;

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldTWUpdateKind;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldCannotBeUndefined;

	/**
	 * @generated
	 */
	protected DListUI		fieldWcListens;

	/**
	 * @generated
	 */
	protected URLModificationPage1_ModificationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public URLModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "URL", "URL", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldMin = createFieldMin();
		// this.fieldCached= createFieldCached();
		this.fieldClassAttribute = createFieldClassAttribute();
		this.fieldMax = createFieldMax();
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
		addLast(
				this.__short_name__,
				this.fieldMin,
				// this.fieldCached,
				this.fieldClassAttribute, this.fieldMax, this.fieldTWRevSpecific, this.fieldTWEvol, this.fieldNatif,
				this.fieldTWCommitKind, this.fieldTWUpdateKind, this.fieldTransient, this.fieldCannotBeUndefined,
				this.fieldDefaultValue, this.fieldWcListens, this.fieldFinalValue, this.fieldRequire,
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
	public DTextUI createFieldMin() {
		return new DTextUI(WorkspaceCST.ATTRIBUTE_at_MIN, "min", EPosLabel.left, new MC_AttributesItem(), null, 1, "",
				false, false, false);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldCached() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_CACHED, "cached", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldClassAttribute() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_CLASS_ATTRIBUTE, "class-attribute", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldMax() {
		return new DTextUI(WorkspaceCST.ATTRIBUTE_at_MAX, "max", EPosLabel.left, new MC_AttributesItem(), null, 1, "",
				false, false, false);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldTWRevSpecific() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_TWREV_SPECIFIC, "TWRevSpecific", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWEvol() {
		StringToEnumModelController mc = new StringToEnumModelController(TWEvol.class, TWEvol.twImmutable);
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("Select a value.", "Select a value.", TWEvol.class);
		return new DBrowserUI(WorkspaceCST.ATTRIBUTE_at_TWEVOL, "TWEvol", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldNatif() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_NATIF, "natif", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWCommitKind() {
		StringToEnumModelController mc = new StringToEnumModelController(TWCommitKind.class, TWCommitKind.conflict);
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("Select a value.", "Select a value.",
				TWCommitKind.class);
		return new DBrowserUI(WorkspaceCST.ATTRIBUTE_at_TWCOMMIT_KIND, "TWCommitKind", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWUpdateKind() {
		StringToEnumModelController mc = new StringToEnumModelController(TWUpdateKind.class, TWUpdateKind.merge);
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("Select a value.", "Select a value.",
				TWUpdateKind.class);
		return new DBrowserUI(WorkspaceCST.ATTRIBUTE_at_TWUPDATE_KIND, "TWUpdateKind", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldTransient() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_TRANSIENT, "transient", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldCannotBeUndefined() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED, "cannot-be-undefined", EPosLabel.none,
				mc, null);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldDefaultValue() {
		return new DTextUI(WorkspaceCST.ATTRIBUTE_at_DEFAULT_VALUE, "default-value", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldWcListens() {
		LinkModelController mc = new LinkModelController(false, null, WorkspaceCST.ATTRIBUTE_lt_WC_LISTENS);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List("Select a value.", "Select a value.",
				WorkspaceCST.ATTRIBUTE_lt_WC_LISTENS);
		return new DListUI(WorkspaceCST.ATTRIBUTE_lt_WC_LISTENS.getName(), "wcListens", EPosLabel.top, mc, ic, true,
				false);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldFinalValue() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_FINAL_VALUE, "final-value", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldRequire() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_REQUIRE, "require", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldMustBeInitialized() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_MUST_BE_INITIALIZED, "must-be-initialized", EPosLabel.none,
				mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldIsList() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_IS_LIST, "is-list", EPosLabel.none, mc, null);
	}

}

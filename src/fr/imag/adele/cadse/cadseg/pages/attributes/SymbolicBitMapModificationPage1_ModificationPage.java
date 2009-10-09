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

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_DefaultForList;
import fede.workspace.model.manager.properties.impl.mc.MC_DefaultForList;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;

/**
 * @generated
 */
public class SymbolicBitMapModificationPage1_ModificationPage extends AttributeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DTextUI	fieldMin;

	/**
	 * @generated
	 */
	protected DTextUI	fieldMax;

	/**
	 * @generated
	 */
	protected DListUI	fieldValues;

	/**
	 * @generated
	 */
	protected SymbolicBitMapModificationPage1_ModificationPage(String id, String label, String title,
			String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public SymbolicBitMapModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "SymbolicBitMap", "SymbolicBitMap", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldMin = createFieldMin();
		this.fieldClassAttribute = createFieldClassAttribute();
		this.fieldMax = createFieldMax();
		this.fieldNatif = createFieldNatif();
		this.fieldCannotBeDefined = createFieldCannotBeDefined();
		this.fieldTransient = createFieldTransient();
		this.fieldRequire = createFieldRequire();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldIsList = createFieldIsList();
		this.fieldValues = createFieldValues();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldMin, this.fieldClassAttribute, this.fieldMax, this.fieldNatif,
				this.fieldCannotBeDefined, this.fieldTransient, this.fieldRequire, this.fieldDefaultValue,
				this.fieldIsList, this.fieldValues);

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
		return new DTextUI(WorkspaceCST.ATTRIBUTE_at_MIN, "min", EPosLabel.left, new MC_AttributesItem(), null, 0, 1,
				"");
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
		return new DTextUI(WorkspaceCST.ATTRIBUTE_at_MAX, "max", EPosLabel.left, new MC_AttributesItem(), null, 0, 1,
				"");
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
	public DCheckBoxUI createFieldCannotBeDefined() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED, "can-be-null", EPosLabel.none, mc, null);
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
	public DCheckBoxUI createFieldRequire() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_REQUIRE, "require", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldDefaultValue() {
		return new DTextUI(WorkspaceCST.ATTRIBUTE_at_DEFAULT_VALUE, "default-value", EPosLabel.left,
				new MC_AttributesItem(), null, 0, 1, "");
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldIsList() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_IS_LIST, "is-list", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldValues() {
		MC_DefaultForList mc = new MC_DefaultForList(0, -1);
		IC_DefaultForList ic = new IC_DefaultForList("Select a value.", "Select a value.", false);
		return new DListUI(WorkspaceCST.SYMBOLIC_BIT_MAP_at_VALUES, "values", EPosLabel.top, mc, ic, true, false);
	}

}

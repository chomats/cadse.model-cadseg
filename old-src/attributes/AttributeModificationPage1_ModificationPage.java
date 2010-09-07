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

import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.RuningInteractionController;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class AttributeModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldCannotBeDefined;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldNatif;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldTransient;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldFinalValue;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldMustBeInitialized;

	/**
	 * @generated
	 */
	protected DTextUI fieldDefaultValue;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldIsList;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldRequire;

	/**
	 * @generated
	 */
	protected AttributeModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public AttributeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "an attribute", "an attribute", "", false,
				3);
		this.item = item;
		this.fieldIsList = createFieldIsList();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldRequire = createFieldRequire();
		this.fieldCannotBeDefined = createFieldCannotBeDefined();
		this.fieldNatif = createFieldNatif();
		this.fieldTransient = createFieldTransient();
		this.fieldFinalValue = createFieldFinalValue();
		this.fieldMustBeInitialized = createFieldMustBeInitialized();
		setActionPage(null);
		addLast(this.fieldIsList, this.fieldDefaultValue, this.fieldRequire,
				this.fieldCannotBeDefined, this.fieldNatif,
				this.fieldTransient, this.fieldFinalValue,
				this.fieldMustBeInitialized);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	@Override
	public void init(UIPlatform pageController) throws CadseException {
		super.init(pageController);
		if (AttributeManager.isDevGeneratedAttribute(getItem())) {
			for (UIField uif : getFields()) {
				uif.setEditable(false);
			}
		}
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldCannotBeDefined() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED,
				"can-be-null", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldNatif() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_NATIF, "natif",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldTransient() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_TRANSIENT, "transient",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldFinalValue() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_FINAL_VALUE,
				"final-value", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldMustBeInitialized() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED,
				"must-be-initialized", EPosLabel.none, mc, null);
	}

	/**
	 * Creates the field default value.
	 * 
	 * @return the UI field
	 * 
	 * @change add method to create mc and ic
	 * 
	 */
	public DTextUI createFieldDefaultValue() {
		return new DTextUI(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE,
				"default value", EPosLabel.left, createMCDefaultValue(),
				createICDefaultValue(), 0, 1,
				"a java expression, sample \"my string\" or null or false for boolean");
	}

	protected MC_AttributesItem createMCDefaultValue() {
		return new MC_AttributesItem();
	}

	protected RuningInteractionController createICDefaultValue() {
		return null;
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldIsList() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_IS_LIST, "is list",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldRequire() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_REQUIRE, "mandatory",
				EPosLabel.none, mc, null);
	}

}

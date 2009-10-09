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

import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;

/**
 * @generated
 */
public class TimeModificationPage1_ModificationPage extends LongModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldInitWithTheCurrentTime;
	private DCheckBoxUI		fieldCannotBeUndefined;

	/**
	 * @generated
	 */
	protected TimeModificationPage1_ModificationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public TimeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Time", "Time", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldCached = createFieldCached();
		this.fieldClassAttribute = createFieldClassAttribute();
		this.fieldNatif = createFieldNatif();
		this.fieldTransient = createFieldTransient();
		this.fieldCannotBeUndefined = createFieldCannotBeUndefined();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldFinalValue = createFieldFinalValue();
		this.fieldRequire = createFieldRequire();
		this.fieldMustBeInitialized = createFieldMustBeInitialized();
		this.fieldIsList = createFieldIsList();
		this.fieldInitWithTheCurrentTime = createFieldInitWithTheCurrentTime();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldCached, this.fieldClassAttribute, this.fieldNatif, this.fieldTransient,
				this.fieldCannotBeUndefined, this.fieldDefaultValue, this.fieldFinalValue, this.fieldRequire,
				this.fieldMustBeInitialized, this.fieldIsList, this.fieldInitWithTheCurrentTime);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
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
	public DCheckBoxUI createFieldNatif() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_NATIF, "natif", EPosLabel.none, mc, null);
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

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldInitWithTheCurrentTime() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.TIME_at_INIT_WITH_THE_CURRENT_TIME, "init-with-the-current-time",
				EPosLabel.none, mc, null);
	}

}

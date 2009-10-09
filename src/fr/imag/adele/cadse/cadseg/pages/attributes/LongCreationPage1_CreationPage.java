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
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.ui.EPosLabel;

/**
 * @generated
 */
public class LongCreationPage1_CreationPage extends AttributeCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldMustBeInitialized;

	/**
	 * @generated
	 */
	protected LongCreationPage1_CreationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public LongCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create Long", "Create Long", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.__short_name__ = createInternalNameField();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldMustBeInitialized = createFieldMustBeInitialized();
		this.fieldIsList = createFieldIsList();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldDefaultValue, this.fieldMustBeInitialized, this.fieldIsList);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @not generated
	 */
	public DTextUI createFieldDefaultValue() {
		return new DTextUI(WorkspaceCST.ATTRIBUTE_at_DEFAULT_VALUE, "default-value", EPosLabel.left,
				new MC_LongTextField(), null, 1, "", false, false, false);
	}

	/**
	 * @not generated
	 */
	public DCheckBoxUI createFieldMustBeInitialized() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_MUST_BE_INITIALIZED, "show attribute in creation wizard",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldIsList() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_IS_LIST, "is-list", EPosLabel.none, mc, null);
	}

}

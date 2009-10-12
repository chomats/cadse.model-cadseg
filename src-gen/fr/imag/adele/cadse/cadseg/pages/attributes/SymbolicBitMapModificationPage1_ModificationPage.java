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

import fr.imag.adele.cadse.core.CadseGCST;
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
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
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
public class SymbolicBitMapModificationPage1_ModificationPage extends
		AttributeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DListUI fieldValues;

	/**
	 * @generated
	 */
	protected SymbolicBitMapModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public SymbolicBitMapModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "SymbolicBitMap", "SymbolicBitMap", "",
				false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldNatif = createFieldNatif();
		this.fieldCannotBeDefined = createFieldCannotBeDefined();
		this.fieldTransient = createFieldTransient();
		this.fieldRequire = createFieldRequire();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldIsList = createFieldIsList();
		this.fieldValues = createFieldValues();
		this.fieldFinalValue = createFieldFinalValue();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldNatif,
				this.fieldCannotBeDefined, this.fieldTransient,
				this.fieldRequire, this.fieldDefaultValue, this.fieldIsList,
				this.fieldValues, this.fieldFinalValue);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DListUI createFieldValues() {
		MC_DefaultForList mc = new MC_DefaultForList(0, -1);
		IC_DefaultForList ic = new IC_DefaultForList("Select a value.",
				"Select a value.", false);
		return new DListUI(CadseGCST.SYMBOLIC_BIT_MAP_at_VALUES, "values",
				EPosLabel.top, mc, ic, true, false, false, false);
	}

}
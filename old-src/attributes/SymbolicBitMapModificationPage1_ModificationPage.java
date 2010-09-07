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

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_DefaultForList;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_DefaultForList;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;

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
		this.fieldNatif = createFieldNatif();
		this.fieldCannotBeDefined = createFieldCannotBeDefined();
		this.fieldTransient = createFieldTransient();
		this.fieldRequire = createFieldRequire();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldIsList = createFieldIsList();
		this.fieldValues = createFieldValues();
		this.fieldFinalValue = createFieldFinalValue();
		setActionPage(null);
		addLast(this.fieldNatif, this.fieldCannotBeDefined,
				this.fieldTransient, this.fieldRequire, this.fieldDefaultValue,
				this.fieldIsList, this.fieldValues, this.fieldFinalValue);

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
		IC_DefaultForList ic = new IC_DefaultForList("", "", false);
		MC_DefaultForList mc = new MC_DefaultForList(0, -1);
		return new DListUI(CadseGCST.SYMBOLIC_BIT_MAP_at_VALUES, "values",
				EPosLabel.top, mc, ic, true, false, false, false);
	}

}

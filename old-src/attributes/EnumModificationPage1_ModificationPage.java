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

import fr.imag.adele.cadse.cadseg.pages.attributes.EnumCreationPage1_CreationPage.DefaultEnumMC;
import fr.imag.adele.cadse.cadseg.pages.attributes.EnumCreationPage1_CreationPage.DefaultValueIC;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_PartLinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class EnumModificationPage1_ModificationPage extends
		AttributeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DBrowserUI fieldEnumType;

	/**
	 * @generated
	 */
	protected EnumModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public EnumModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Enum", "Enum", "", false, 3);
		this.item = item;
		this.fieldEnumType = createFieldEnumType();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldIsList = createFieldIsList();
		setActionPage(null);
		addLast(this.fieldEnumType, this.fieldDefaultValue, this.fieldIsList);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * 
	 */
	public DTextUI createFieldDefaultValue() {
		// return new DTextUI(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE,
		// "default value",
		// EPosLabel.left,new MC_AttributesItem(), null,0,1,"");
		DefaultValueIC ic = new DefaultValueIC();
		return FieldsCore.createTextField(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE,
				"default value", 1, null, ic, new DefaultEnumMC());
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldEnumType() {
		IC_PartLinkForBrowser_Combo_List ic = new IC_PartLinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ENUM_lt_ENUM_TYPE, CadseGCST.DATA_MODEL_lt_ENUMS,
				"Select a value.");
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ENUM_lt_ENUM_TYPE);
		return new DBrowserUI(CadseGCST.ENUM_lt_ENUM_TYPE.getName(),
				"enum-type", EPosLabel.left, mc, ic);
	}

}

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
package fr.imag.adele.cadse.cadseg.pages.meta;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class AttributeItemTypeAttributeItemTypePage_ModificationPage extends
		PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DTextUI fieldRuntimeQualifiedClass;

	/**
	 * @generated
	 */
	protected AttributeItemTypeAttributeItemTypePage_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public AttributeItemTypeAttributeItemTypePage_ModificationPage(Item item) {
		super(
				"attribute-item-type-page",
				"Attribute Item Type",
				"Attribute Item Type",
				"This page help to put the description of the type of attribute definition",
				false, 3);
		this.item = item;
		this.fieldRuntimeQualifiedClass = createFieldRuntimeQualifiedClass();
		setActionPage(null);
		addLast(this.fieldRuntimeQualifiedClass);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldRuntimeQualifiedClass() {
		return new DTextUI(
				CadseGCST.ATTRIBUTE_ITEM_TYPE_at_RUNTIME_QUALIFIED_CLASS,
				"runtime-qualified-class", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

}

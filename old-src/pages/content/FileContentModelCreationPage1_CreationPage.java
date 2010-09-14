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
package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class FileContentModelCreationPage1_CreationPage extends
		ResourceContentModelCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected DTextUI fieldFileName;

	/**
	 * @generated
	 */
	protected DTextUI fieldFilePath;

	/**
	 * @generated
	 */
	protected FileContentModelCreationPage1_CreationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public FileContentModelCreationPage1_CreationPage(Item parent, ItemType it,
			LinkType lt) {
		super("creation-page1", "Create FileContentModel",
				"Create FileContentModel", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldExtendsClass = createFieldExtendsClass();
		this.fieldFileName = createFieldFileName();
		this.fieldFilePath = createFieldFilePath();
		setActionPage(null);
		addLast(this.fieldExtendsClass, this.fieldFileName, this.fieldFilePath);

		registerListener();
	}

	@Override
	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// model.workspace.workspace.managers.content.ContentModelManager#createCreationPages(fr.imag.adele.cadse.core.Item,
	// * fr.imag.adele.cadse.core.LinkType,
	// * fr.imag.adele.cadse.core.ItemType)
	// */
	// @Override
	// public Pages createCreationPages(Item theItemParent, LinkType
	// theLinkType, ItemType desType) {
	//
	// ItemType it = desType;
	//
	// String title = it.getAttribute("title-create");
	// CreationAction action = new CreationAction(theItemParent, desType,
	// theLinkType, it.getShortName());
	//
	// return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
	// "Create " + title, "Create " + title, 3,
	// FieldsCore.createCheckBox(CadseGCST.CONTENT_MODEL_at_EXTENDS_CLASS,
	// "extends class"),
	// createFileNameField(), createFilePathField()));
	// }

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// model.workspace.workspace.managers.content.ContentModelManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	// */
	// @Override
	// public Pages createModificationPage(Item item) {
	// AbstractActionPage action = new ModificationAction(item);
	//
	// ItemType it = item.getType();
	//
	// String title = it.getAttribute("title-create");
	//
	// return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
	// "Create " + title, "Create " + title, 3,
	// FieldsCore.createCheckBox(CadseGCST.CONTENT_MODEL_at_EXTENDS_CLASS,
	// "extends class"),
	// createFileNameField(), createFilePathField()));
	// }

}

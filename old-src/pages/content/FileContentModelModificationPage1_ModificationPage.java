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
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class FileContentModelModificationPage1_ModificationPage extends
		ResourceContentModelModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DTextUI fieldFilePath;

	/**
	 * @generated
	 */
	protected DTextUI fieldFileName;

	/**
	 * @generated
	 */
	protected FileContentModelModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public FileContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "FileContentModel", "FileContentModel", "",
				false, 3);
		this.item = item;
		this.fieldExtendsClass = createFieldExtendsClass();
		this.fieldFilePath = createFieldFilePath();
		this.fieldFileName = createFieldFileName();
		setActionPage(null);
		addLast(this.fieldExtendsClass, this.fieldFilePath, this.fieldFileName);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @not generated
	 */
	public DTextUI createFieldFilePath() {
		return new DTextUI(CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH,
				"file-path", EPosLabel.left, new MC_AttributesItem() {
					@Override
					public Object defaultValue() {
						return "/";
					}
				}, new IC_ItemTypeTemplateForText() {
					@Override
					protected Item getItemFromContext() {
						Item manager = getContext().getPartParent();
						return ManagerManager.getItemType(manager);
					}
				}, 1, "", false, false, false);
	}

	/**
	 * @not generated
	 */
	public DTextUI createFieldFileName() {
		return new DTextUI(CadseGCST.FILE_CONTENT_MODEL_at_FILE_NAME,
				"file-name", EPosLabel.left, new MC_AttributesItem() {
					@Override
					public Object defaultValue() {
						return "${#short-name}";
					}
				}, new IC_ItemTypeTemplateForText() {
					@Override
					protected Item getItemFromContext() {
						Item manager = getContext().getPartParent();
						return ManagerManager.getItemType(manager);
					}
				}, 1, "", false, false, false);
	}

}

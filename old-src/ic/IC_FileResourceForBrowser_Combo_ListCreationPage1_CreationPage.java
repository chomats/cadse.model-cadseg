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
package fr.imag.adele.cadse.cadseg.pages.ic;

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
public class IC_FileResourceForBrowser_Combo_ListCreationPage1_CreationPage
		extends
		IC_ResourceTreeDialogForBrowser_Combo_ListCreationPage1_CreationPage {

	/**
	    @generated
	 */
	protected DTextUI fieldSelectFolder;

	/**
	    @generated
	 */
	protected DTextUI fieldPatternSelectFile;

	/**
	 * @generated
	 */
	protected IC_FileResourceForBrowser_Combo_ListCreationPage1_CreationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_FileResourceForBrowser_Combo_ListCreationPage1_CreationPage(
			Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create IC_FileResourceForBrowser_Combo_List",
				"Create IC_FileResourceForBrowser_Combo_List", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldSelectFolder = createFieldSelectFolder();
		this.fieldPatternSelectFile = createFieldPatternSelectFile();
		setActionPage(null);
		addLast(this.fieldSelectFolder, this.fieldPatternSelectFile);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	    @generated
	 */
	public DTextUI createFieldSelectFolder() {
		return new DTextUI(
				CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST_at_SELECT_FOLDER,
				"select-folder", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldPatternSelectFile() {
		return new DTextUI(
				CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST_at_PATTERN_SELECT_FILE,
				"pattern-select-file", EPosLabel.left, new MC_AttributesItem(),
				null, 1, "", false, false, false);
	}

}

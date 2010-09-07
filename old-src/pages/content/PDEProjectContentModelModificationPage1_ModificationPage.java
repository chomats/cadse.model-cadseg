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

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;

/**
 * @generated
 */
public class PDEProjectContentModelModificationPage1_ModificationPage extends
		JavaProjectContentModelModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DListUI fieldContentModel;

	/**
	 * @generated
	 */
	protected PDEProjectContentModelModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public PDEProjectContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "PDEProjectContentModel",
				"PDEProjectContentModel", "", false, 3);
		this.item = item;
		this.fieldContentModel = createFieldContentModel();
		setActionPage(null);
		addLast(this.fieldContentModel);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DListUI createFieldContentModel() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL);
		return new DListUI(CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL
				.getName(), "content-model", EPosLabel.top, mc, ic, true,
				false, false, false);
	}

}

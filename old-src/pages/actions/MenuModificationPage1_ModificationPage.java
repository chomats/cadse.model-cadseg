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
package fr.imag.adele.cadse.cadseg.pages.actions;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_IconResourceForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;

/**
 * @generated
 */
public class MenuModificationPage1_ModificationPage extends
		MenuAbstractModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DBrowserUI fieldIcon;

	/**
	 * @generated
	 */
	protected MenuModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public MenuModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Menu", "Menu", "", false, 3);
		this.item = item;
		this.fieldPath = createFieldPath();
		this.fieldLabel = createFieldLabel();
		this.fieldIcon = createFieldIcon();
		setActionPage(null);
		addLast(this.fieldPath, this.fieldLabel, this.fieldIcon);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @not generated
	 */
	public DBrowserUI createFieldIcon() {
		return new DBrowserUI(CadseGCST.MENU_ABSTRACT_at_ICON, "icon",
				EPosLabel.left, new MC_AttributesItem(),
				new IC_IconResourceForBrowser_Combo_List("Select an icon",
						"Select an icon", null));
	}

}

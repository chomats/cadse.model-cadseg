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

import org.eclipse.swt.SWT;

import fede.workspace.model.manager.properties.impl.mc.StringToResourceSimpleModelController;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_FileResourceForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_IconResourceForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class MenuActionModificationPage1_ModificationPage extends
		MenuAbstractModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DBrowserUI fieldIcon;

	/**
	 * @generated
	 */
	protected MenuActionModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public MenuActionModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "MenuAction", "MenuAction", "", false, 3);
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
	 * Creates a new MenuActionModificationPage1_ModificationPage object.
	 * 
	 * @return the d text ui
	 */
	public DTextUI createFieldPath() {
		GlobalPathIC ic = new GlobalPathIC();
		return new DTextUI(CadseGCST.MENU_ABSTRACT_at_PATH, "path in menu",
				EPosLabel.left, new MC_AttributesItem(), ic, 0, 1,
				"La valeur peut être context1, context2 ... context6, context1/new");
	}

	/**
	 * @not generated
	 */
	public DBrowserUI createFieldIcon() {
		StringToResourceSimpleModelController mc = new StringToResourceSimpleModelController();
		IC_FileResourceForBrowser_Combo_List ic = new IC_IconResourceForBrowser_Combo_List();
		return new DBrowserUI(CadseGCST.MENU_ABSTRACT_at_ICON, "icon",
				EPosLabel.left, mc, ic, SWT.BORDER | SWT.SINGLE);
	}

}

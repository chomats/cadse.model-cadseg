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
package fr.imag.adele.cadse.cadseg.pages.view;

import org.eclipse.swt.SWT;

import fede.workspace.model.manager.properties.impl.mc.StringToResourceSimpleModelController;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewManager.IC_ViewManager_DataModelView;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_IconResourceForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckedTreeUI;

/**
 * @generated
 */
public class ViewModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DBrowserUI fieldIcon;

	/**
	 * @generated
	 */
	protected ViewModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @not generated
	 */
	public ViewModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "View", "View", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldIcon = createFieldIcon();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldIcon, createFieldDataModel(item));

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * Creates the field icon.
	 * 
	 * @return the UI field
	 * 
	 * 
	 */
	protected DBrowserUI createFieldIcon() {
		return new DBrowserUI(CadseGCST.VIEW_at_ICON, "icon", EPosLabel.left,
				new StringToResourceSimpleModelController(),
				new IC_IconResourceForBrowser_Combo_List(), SWT.BORDER
						| SWT.SINGLE);
	}

	/**
	 * Creates the field data model.
	 * 
	 * @param view
	 *            the view
	 * 
	 * @return the uI field
	 */
	protected DCheckedTreeUI createFieldDataModel(Item view) {
		Item cadsedef = ViewManager.getCadsegModel(view);
		IC_ViewManager_DataModelView ic_mc = new IC_ViewManager_DataModelView(
				CadseDefinitionManager.getDependenciesCadsesAndMe(cadsedef),
				view);
		DCheckedTreeUI checkedTreeUI = new DCheckedTreeUI("sel", "",
				EPosLabel.none, ic_mc, ic_mc, true, false);
		ic_mc.setCheckedTreeUI(checkedTreeUI);
		return checkedTreeUI;
	}
}

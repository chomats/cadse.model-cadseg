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

import fr.imag.adele.cadse.cadseg.managers.actions.MenuManager;
import fr.imag.adele.cadse.core.CadseException;
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
public class MenuCreationPage1_CreationPage extends
		MenuAbstractCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected DTextUI fieldPath;

	/**
	 * @generated
	 */
	protected MenuCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public MenuCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create Menu", "Create Menu", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldName = createFieldName();
		this.fieldPath = createFieldPath();
		setActionPage(null);
		addLast(this.fieldName, this.fieldPath);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.IPage#initAfterUI()
	 */
	@Override
	public void initAfterUI() {
		super.initAfterUI();
		Item menu = fieldPath.getItem();
		if (CadseGCST.TYPE_DEFINITION.isSuperTypeOf(menu.getPartParent()
				.getType())) {
			fieldPath.internalSetVisible(false);
			fieldName.internalSetVisible(false);
			try {
				menu.setName("default");
				MenuManager.setPathAttribute(menu, "default");
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldPath() {
		return new DTextUI(CadseGCST.MENU_ABSTRACT_at_PATH, "path",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

}

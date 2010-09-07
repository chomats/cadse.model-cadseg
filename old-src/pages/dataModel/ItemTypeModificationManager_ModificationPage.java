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
package fr.imag.adele.cadse.cadseg.pages.dataModel;

import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager.ValidFieldUC;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;

/**
 * @generated
 */
public class ItemTypeModificationManager_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected ItemTypeModificationManager_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @not generated
	 */
	public ItemTypeModificationManager_ModificationPage(Item item) {
		super("modification-manager", "Instance name control",
				"Instance name control", "Instance name control", false, 3);

		this.item = ItemTypeManager.getManager(item);

		setActionPage(null);
		// Field display name removed from addLast :
		// FieldsCore.createTextField(CadseGCST.MANAGER_at_HUMAN_NAME,
		// "Display name"),
		addLast(FieldsCore.createTextField(
				CadseGCST.MANAGER_at_LONG_NAME_TEMPLATE,
				"Qualified name template", 1, "",
				new IC_ItemTypeTemplateForText(), new MC_AttributesItem()),
				FieldsCore.createTextField(
						CadseGCST.MANAGER_at_DISPLAY_NAME_TEMPLATE,
						"Display name template", 1, "",
						new IC_ItemTypeTemplateForText(),
						new MC_AttributesItem()), FieldsCore.createTextField(
						CadseGCST.MANAGER_at_VALID_PATTERN_ID,
						"Valid name pattern", new ValidFieldUC()), FieldsCore
						.createTextField(CadseGCST.MANAGER_at_MESSAGE_ERROR_ID,
								"Error message while invalid name"));

		registerListener();
	}

	@Override
	public void init(UIPlatform pageController) throws CadseException {
		for (UIField f : getFields()) {
			f.setItem(item);
		}
		super.init(pageController);
	}

	protected void registerListener() {
		// add init and register
	}
}

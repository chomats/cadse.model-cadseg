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
 */
package fr.imag.adele.cadse.cadseg.pages.dataModel;

import fede.workspace.model.manager.properties.FieldsCore;
import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager.ValidFieldUC;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;

/**
 * @generated
 */
public class ItemTypeManager_CreationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item		parent;

	/**
	 * @generated
	 */
	public ItemType	it;

	/**
	 * @generated
	 */
	public LinkType	lt;

	/**
	 * @generated
	 */
	protected ItemTypeManager_CreationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @not generated
	 */
	public ItemTypeManager_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("manager", "Instance name control", "Instance name control", "Instance name control", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		setActionPage(null);
		// Field display name removed from addLast :
		// FieldsCore.createTextField(WorkspaceCST.MANAGER_at_HUMAN_NAME,
		// "Display name"),
		addLast(FieldsCore.createTextField(WorkspaceCST.MANAGER_at_LONG_NAME_TEMPLATE, "Qualified name template", 1,
				"", new IC_ItemTypeTemplateForText(), new MC_AttributesItem()), FieldsCore.createTextField(
				WorkspaceCST.MANAGER_at_DISPLAY_NAME_TEMPLATE, "Display name template", 1, "",
				new IC_ItemTypeTemplateForText(), new MC_AttributesItem()), FieldsCore.createTextField(
				WorkspaceCST.MANAGER_at_VALID_PATTERN_ID, "Valid name pattern", 1, null, new ValidFieldUC(),
				new ValidPattern()), FieldsCore.createTextField(WorkspaceCST.MANAGER_at_MESSAGE_ERROR_ID,
				"Error message while invalid name"));

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

}

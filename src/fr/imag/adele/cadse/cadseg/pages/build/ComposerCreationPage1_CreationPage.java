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
package fr.imag.adele.cadse.cadseg.pages.build;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_DefaultForList;
import fede.workspace.model.manager.properties.impl.mc.MC_DefaultForList;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;

/**
 * @generated
 */
public class ComposerCreationPage1_CreationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item			parent;

	/**
	 * @generated
	 */
	public ItemType		it;

	/**
	 * @generated
	 */
	public LinkType		lt;

	/**
	 * @generated
	 */
	protected DTextUI	__short_name__;

	/**
	 * @generated
	 */
	protected DListUI	fieldTypes;

	/**
	 * @generated
	 */
	protected ComposerCreationPage1_CreationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ComposerCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create Composer", "Create Composer", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.__short_name__ = createInternalNameField();
		this.fieldTypes = createFieldTypes();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldTypes);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createInternalNameField() {
		return FieldsCore.createShortNameField();
	}

	/**
	 * @generated
	 */
	public DListUI createFieldTypes() {
		MC_DefaultForList mc = new MC_DefaultForList(0, -1);
		IC_DefaultForList ic = new IC_DefaultForList("Select a value.", "Select a value.", false);
		return new DListUI(WorkspaceCST.COMPOSER_at_TYPES, "types", EPosLabel.top, mc, ic, false, false, false, false);
	}

}

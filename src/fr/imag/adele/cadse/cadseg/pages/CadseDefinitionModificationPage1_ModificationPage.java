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
package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
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
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.ic.IC_DefaultForList;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.MC_DefaultForList;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;

/**
 * @generated
 */
public class CadseDefinitionModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item			item;

	/**
	 * @generated
	 */
	protected DTextUI	__short_name__;

	/**
	 * @generated
	 */
	protected DTextUI	fieldPackagename;

	/**
	 * @generated
	 */
	protected DListUI	fieldImports;

	/**
	 * @generated
	 */
	protected DListUI	fieldExtends;

	/**
	 * @generated
	 */
	protected DTextUI	fieldVendorName;

	/**
	 * @generated
	 */
	protected DTextUI	fieldVersionCadse;

	/**
	 * @generated
	 */
	protected DTextUI	fieldDescription;

	/**
	 * @generated
	 */
	protected DTextUI	fieldCommentary;

	/**
	 * @generated
	 */
	private DTextUI		fieldDisplayName;

	/**
	 * @generated
	 */
	protected CadseDefinitionModificationPage1_ModificationPage(String id, String label, String title,
			String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public CadseDefinitionModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Cadse definition", "Cadse definition", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldPackagename = createFieldPackagename();
		this.fieldImports = createFieldImports();
		this.fieldExtends = createFieldExtends();
		this.fieldVendorName = createFieldVendorName();
		this.fieldVersionCadse = createFieldVersionCadse();
		this.fieldDescription = createFieldDescription();
		this.fieldCommentary = createFieldCommentary();
		this.fieldDisplayName = createFieldDisplayName();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldPackagename, this.fieldImports, this.fieldExtends, this.fieldVendorName,
				this.fieldVersionCadse, this.fieldDescription, this.fieldCommentary, this.fieldDisplayName);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createInternalNameField() {
		return FieldsCore.createUniqueNameField();
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldPackagename() {
		return new DTextUI(WorkspaceCST.CADSE_DEFINITION_at_PACKAGENAME, "main package", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldImports() {
		MC_DefaultForList mc = new MC_DefaultForList(0, -1);
		IC_DefaultForList ic = new IC_DefaultForList("Enter an import package", "Enter an import package", false);
		return new DListUI(WorkspaceCST.CADSE_DEFINITION_at_IMPORTS, "imports", EPosLabel.top, mc, ic, true, false,
				false, false);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldExtends() {
		LinkModelController mc = new LinkModelController(false, null, WorkspaceCST.CADSE_DEFINITION_lt_EXTENDS);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List("Select a cadse to extend.",
				"Select a cadse to extend.", WorkspaceCST.CADSE_DEFINITION_lt_EXTENDS);
		return new DListUI(WorkspaceCST.CADSE_DEFINITION_lt_EXTENDS.getName(), "extends", EPosLabel.top, mc, ic, true,
				false, false, false);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldVendorName() {
		return new DTextUI(WorkspaceCST.CADSE_DEFINITION_at_VENDOR_NAME, "vendor name", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldVersionCadse() {
		return new DTextUI(WorkspaceCST.CADSE_DEFINITION_at_VERSION_CADSE, "version cadse", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldDescription() {
		return new DTextUI(WorkspaceCST.CADSE_DEFINITION_at_DESCRIPTION, "description", EPosLabel.left,
				new MC_AttributesItem(), null, 50, "", true, false, true);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldCommentary() {
		return new DTextUI(WorkspaceCST.CADSE_DEFINITION_at_COMMENTARY, "commentary", EPosLabel.left,
				new MC_AttributesItem(), null, 25, "", true, false, true);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldDisplayName() {
		return new DTextUI(WorkspaceCST.CADSE_DEFINITION_at_DISPLAY_NAME, "display-name", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

}

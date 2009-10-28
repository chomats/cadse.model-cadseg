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

import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.cadseg.managers.IC_SuperTypeForBrowser_Combo;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;

/**
 * @generated
 */
public class ItemTypeCreationPage1_CreationPage extends
		AbstractItemTypeCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected DBrowserUI fieldSuperType;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldHasContent;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldIsAbstract;

	/**
	    @generated
	 */
	protected DBrowserUI fieldCadseRuntime;

	/**
	    @generated
	 */
	protected DTextUI fieldItemFactory;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldIsRootElement;

	/**
	 * @generated
	 */
	protected ItemTypeCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ItemTypeCreationPage1_CreationPage(Item parent, ItemType it,
			LinkType lt) {
		super("creation-page1", "Create an item type", "Create an item type",
				"Create an item type", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldName = createInternalNameField();
		this.fieldSuperType = createFieldSuperType();
		this.fieldIsRootElement = createFieldIsRootElement();
		this.fieldHasContent = createFieldHasContent();
		this.fieldIsAbstract = createFieldIsAbstract();
		this.fieldCadseRuntime = createFieldCadseRuntime();
		this.fieldItemFactory = createFieldItemFactory();
		setActionPage(null);
		addLast(this.fieldName, this.fieldSuperType,
				this.fieldIsRootElement, this.fieldHasContent,
				this.fieldIsAbstract, this.fieldCadseRuntime,
				this.fieldItemFactory);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @not generated
	 */
	public DBrowserUI createFieldSuperType() {

		IC_SuperTypeForBrowser_Combo superTypeLinkAction = new IC_SuperTypeForBrowser_Combo(
				"Select a super type", "Select a super type");
		return FieldsCore.createLinkDependencyField(
				CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, "super type", EPosLabel.top,
				superTypeLinkAction, false, null);

		// LinkModelController mc = new LinkModelController(false,
		// null,CadseGCST.ITEM_TYPE_lt_SUPER_TYPE);
		// IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
		// "Select a value.", "Select a
		// value.",CadseGCST.ITEM_TYPE_lt_SUPER_TYPE);
		// return new
		// DBrowserUI(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE.getShortName(),
		// "super-type",
		// EPosLabel.left,mc, ic);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldHasContent() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ITEM_TYPE_at_HAS_CONTENT,
				"has-content", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldIsAbstract() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ITEM_TYPE_at_IS_ABSTRACT,
				"is-abstract", EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldCadseRuntime() {
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ITEM_TYPE_lt_CADSE_RUNTIME);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ITEM_TYPE_lt_CADSE_RUNTIME);
		return new DBrowserUI(CadseGCST.ITEM_TYPE_lt_CADSE_RUNTIME.getName(),
				"cadse-runtime", EPosLabel.left, mc, ic);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldItemFactory() {
		return new DTextUI(CadseGCST.ITEM_TYPE_at_ITEM_FACTORY, "item-factory",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldIsRootElement() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT,
				"root element", EPosLabel.none, mc, null);
	}

}

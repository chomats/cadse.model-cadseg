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
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.cadseg.managers.IC_SuperTypeForBrowser_Combo;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
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

/**
 * @generated
 */
public class ItemTypeModificationPage1_ModificationPage extends
		AbstractItemTypeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DBrowserUI fieldSuperType;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldIsRootElement;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldIsMetaItemType;

	/**
	    @generated
	 */
	protected DTextUI fieldIcon;

	/**
	    @generated
	 */
	protected DBrowserUI fieldCadseRuntime;

	/**
	    @generated
	 */
	protected DTextUI fieldItemFactory;

	/**
	    @generated
	 */
	protected DTextUI fieldItemManager;

	/**
	    @generated
	 */
	protected DListUI fieldLinkType;

	/**
	    @generated
	 */
	protected DListUI fieldSubTypes;

	/**
	    @generated
	 */
	protected DTextUI fieldPackageName;

	/**
	    @generated
	 */
	protected DListUI fieldModificationPages;

	/**
	    @generated
	 */
	protected DListUI fieldCreationPages;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldHasContent;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldIsAbstract;

	private DTextUI displayNameField;

	private Item manager;

	private DBrowserUI iconField;

	/**
	 * @generated
	 */
	protected ItemTypeModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @not generated
	 */
	public ItemTypeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "ItemType", "ItemType", "", false, 3);
		this.item = item;

		this.__short_name__ = createInternalNameField();
		this.fieldSuperType = createFieldSuperType();
		this.fieldIsRootElement = createFieldIsRootElement();
		this.fieldHasContent = createFieldHasContent();
		this.fieldIsAbstract = createFieldIsAbstract();
		this.manager = ItemTypeManager.getManager(item);
		this.displayNameField = FieldsCore.createTextField(
				CadseGCST.MANAGER_at_HUMAN_NAME, "Display name");
		this.displayNameField.setItem(manager);
		this.iconField = FieldsCore.createBrowserIconField(
				CadseGCST.MANAGER_at_ICON, "icon", EPosLabel.left);
		this.iconField.setItem(manager);
		this.fieldIsMetaItemType = createFieldIsMetaItemType();

		setActionPage(null);
		addLast(this.__short_name__, this.displayNameField,
				this.fieldSuperType, this.fieldHasContent,
				this.fieldIsAbstract, this.fieldIsRootElement, this.iconField,
				this.fieldIsMetaItemType);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * 
	 */
	public DBrowserUI createFieldSuperType() {
		IC_SuperTypeForBrowser_Combo ic = new IC_SuperTypeForBrowser_Combo(
				"Select a super type", "Select a super type");
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ITEM_TYPE_lt_SUPER_TYPE);
		return new DBrowserUI(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE.getName(),
				"super type", EPosLabel.top, mc, ic);
	}

	/**
	 * @generated FieldsCore.createCheckBox(ItemTypeManager.IS_ROOT_ELEMENT_ATTRIBUTE,
	 *            "root element"),
	 * 
	 */
	public DCheckBoxUI createFieldIsRootElement() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT,
				"is-root-element", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldIsMetaItemType() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ITEM_TYPE_at_IS_META_ITEM_TYPE,
				"is-meta-item-type (advanced users only)", EPosLabel.none, mc,
				null);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldIcon() {
		return new DTextUI(CadseGCST.ITEM_TYPE_at_ICON, "icon", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldCadseRuntime() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ITEM_TYPE_lt_CADSE_RUNTIME);
		LinkModelController mc = new LinkModelController(false, null,
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
	    @generated
	 */
	public DTextUI createFieldItemManager() {
		return new DTextUI(CadseGCST.ITEM_TYPE_at_ITEM_MANAGER, "item-manager",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	/**
	    @generated
	 */
	public DListUI createFieldLinkType() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ITEM_TYPE_lt_LINK_TYPE);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ITEM_TYPE_lt_LINK_TYPE);
		return new DListUI(CadseGCST.ITEM_TYPE_lt_LINK_TYPE.getName(),
				"link-type", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	    @generated
	 */
	public DListUI createFieldSubTypes() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ITEM_TYPE_lt_SUB_TYPES);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ITEM_TYPE_lt_SUB_TYPES);
		return new DListUI(CadseGCST.ITEM_TYPE_lt_SUB_TYPES.getName(),
				"sub-types", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldPackageName() {
		return new DTextUI(CadseGCST.ITEM_TYPE_at_PACKAGE_NAME, "package-name",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	/**
	    @generated
	 */
	public DListUI createFieldModificationPages() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ITEM_TYPE_lt_MODIFICATION_PAGES);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ITEM_TYPE_lt_MODIFICATION_PAGES);
		return new DListUI(CadseGCST.ITEM_TYPE_lt_MODIFICATION_PAGES.getName(),
				"modification-pages", EPosLabel.top, mc, ic, true, false,
				false, false);
	}

	/**
	    @generated
	 */
	public DListUI createFieldCreationPages() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ITEM_TYPE_lt_CREATION_PAGES);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ITEM_TYPE_lt_CREATION_PAGES);
		return new DListUI(CadseGCST.ITEM_TYPE_lt_CREATION_PAGES.getName(),
				"creation-pages", EPosLabel.top, mc, ic, true, false, false,
				false);
	}

	/**
	 * @generated FieldsCore.createCheckBox(ItemTypeManager.HAS_CONTENT_ATTRIBUTE,
	 *            "has content")
	 */
	public DCheckBoxUI createFieldHasContent() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ITEM_TYPE_at_HAS_CONTENT,
				"has-content", EPosLabel.none, mc, null);
	}

	/**
	 * @generated FieldsCore.createCheckBox(ItemTypeManager.IS_ABSTRACT_ATTRIBUTE,
	 *            "is abstract"),
	 */
	public DCheckBoxUI createFieldIsAbstract() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ITEM_TYPE_at_IS_ABSTRACT,
				"is-abstract", EPosLabel.none, mc, null);
	}

}

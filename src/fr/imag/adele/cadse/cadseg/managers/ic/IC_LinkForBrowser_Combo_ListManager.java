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

package fr.imag.adele.cadse.cadseg.managers.ic;

import java.util.Set;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.ui.DisplayManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.Pages;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fede.workspace.model.manager.properties.FieldsCore;

/**
 * The Class IC_LinkForBrowser_Combo_ListManager.
 * 
 * @generated
 */
public class IC_LinkForBrowser_Combo_ListManager extends IC_AbstractTreeDialogForList_Browser_ComboManager {

	// String title, String message, LinkType linkType
	/**
	 * The Class MyContentItem.
	 */
	class MyContentItem extends IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem {

		/**
		 * Instantiates a new my content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 * @throws CadseException
		 */
		protected MyContentItem(ContentItem parent, Item item) throws CadseException {
			super(parent, item);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set, java.lang.Object)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
			super.generateCallArguments(sb, imports, object);
			Item ic = getItem();
			Item a = FieldManager.getAttribute(ic.getPartParent().getPartParent());
			sb.append(GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariable.DEFAULT, a, null, null, imports))
					.append(",");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			super.generateConstructorParameter(sb);
			sb.append(" LinkType linkType,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			super.generateConstrustorArguments(sb);
			sb.append(" linkType,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#computeImportsPackage(java.util.Set)
		 */
		@Override
		public void computeImportsPackage(Set<String> imports) {
			super.computeImportsPackage(imports);
			imports.add("fede.workspace.model.manager.properties");
			imports.add("fede.workspace.model.manager.properties.impl.ic");
		}
	}

	/** The Constant DEFAUL_CLASS_NAME. */

	@SuppressWarnings("hiding")
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List";

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public IC_LinkForBrowser_Combo_ListManager() {
	}

	/**
	 * Creates the creation pages.
	 * 
	 * @param theItemParent
	 *            the the item parent
	 * @param theLinkType
	 *            the the link type
	 * @param desType
	 *            the des type
	 * 
	 * @return the pages
	 * 
	 * @generated
	 */
	@Override
	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {

		CreationAction action = new CreationAction(theItemParent, desType, theLinkType,
				DisplayManager.IC_DEFAULT_SHORT_NAME);

		IModelController mc = new MC_AttributesItem();
		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "Add a link interaction controller",
				"Add a link interaction controller", 2, createFieldDialogTitle(), createFieldDialogMessage()));
	}

	/**
	 * Creates the field dialog message.
	 * 
	 * @return the uI field
	 */
	protected UIField createFieldDialogMessage() {
		return FieldsCore.createTextField(SELECT_MESSAGE_ATTRIBUTE, "dialog message");
	}

	/**
	 * Creates the field dialog title.
	 * 
	 * @return the uI field
	 */
	protected UIFieldImpl createFieldDialogTitle() {
		return FieldsCore.createTextField(SELECT_TITLE_ATTRIBUTE, "dialog title");
	}

	/**
	 * Creates the property folder folder1.
	 * 
	 * @return the i page
	 */
	IPage createPropertyFolderFolder1() {
		return FieldsCore.createPage("page1", " a link interaction controller", " a link user controller", 2,
				createFieldDialogTitle(), createFieldDialogMessage());
	}

	/**
	 * Creates the modification page.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the pages
	 * 
	 * @generated
	 */
	@Override
	public Pages createModificationPage(Item item) {
		AbstractActionPage action = new ModificationAction(item);
		return FieldsCore.createWizard(action, createPropertyFolderFolder1());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentManager(Item item) throws CadseException {
		return new MyContentItem(null, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateMeItem(Item itemParent, LinkType lt, ItemType destType) {
		String error = super.canCreateMeItem(itemParent, lt, destType);
		if (error != null) {
			return error;
		}

		Item field = itemParent.getPartParent();
		Item attribute = FieldManager.getAttribute(field);
		if (attribute.getType() != WorkspaceCST.LINK) {
			return "It's not a link attribute";
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}
}

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
import fr.imag.adele.cadse.cadseg.managers.ui.DisplayManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.Pages;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.mc.IntModelController;

/**
 * The Class IC_ResourceTreeDialogForBrowser_Combo_ListManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_ResourceTreeDialogForBrowser_Combo_ListManager extends
		IC_AbstractTreeDialogForList_Browser_ComboManager implements IItemManager {

	/** The Constant SELECT_ROOT_ATTRIBUTE. */
	public static final String	SELECT_ROOT_ATTRIBUTE	= "select-type-root";

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
			DisplayManager.addAttributeInCall(getItem(), SELECT_ROOT_ATTRIBUTE, false, "0", sb);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			super.generateConstructorParameter(sb);
			sb.append(" int selectRoot,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			super.generateConstrustorArguments(sb);
			sb.append(" selectRoot,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#computeImportsPackage(java.util.Set)
		 */
		@Override
		public void computeImportsPackage(Set<String> imports) {
			super.computeImportsPackage(imports);
			imports.add("fede.workspace.model.manager.properties.impl.ic");
		}
	}

	/** The Constant DEFAUL_CLASS_NAME. */
	@SuppressWarnings("hiding")
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.ic.IC_ResourceTreeDialogForBrowser_Combo_List";

	/**
	 * Instantiates a new i c_ resource tree dialog for browser_ combo_ list
	 * manager.
	 */
	public IC_ResourceTreeDialogForBrowser_Combo_ListManager() {
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
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
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
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager#createCreationPages(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {

		CreationAction action = new CreationAction(theItemParent, desType, theLinkType,
				DisplayManager.IC_DEFAULT_SHORT_NAME);

		IntModelController hspan_vc = new IntModelController(0, 0, "The number of column must be > 0", null, 1);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", getCreateTitle(), getCreateTitle(), 2,
				FieldsCore.createTextField(SELECT_TITLE_ATTRIBUTE, "dialog title"), FieldsCore.createTextField(
						SELECT_MESSAGE_ATTRIBUTE, "dialog message"), FieldsCore.createTextField(SELECT_ROOT_ATTRIBUTE,
						"where find resource", hspan_vc)));
	}

	/**
	 * Gets the creates the title.
	 * 
	 * @return the creates the title
	 */
	protected String getCreateTitle() {
		return "create a default resource user controller";
	}

	/**
	 * Gets the property title.
	 * 
	 * @return the property title
	 */
	protected String getPropertyTitle() {
		return "a default resource user controller";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Pages createModificationPage(Item item) {
		AbstractActionPage action = new ModificationAction(item);

		IntModelController hspan_vc = new IntModelController(0, 0, "The number of column must be > 0", null, 1);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", getPropertyTitle(), getPropertyTitle(),
				2, FieldsCore.createTextField(SELECT_TITLE_ATTRIBUTE, "dialog title"), FieldsCore.createTextField(
						SELECT_MESSAGE_ATTRIBUTE, "dialog message"), FieldsCore.createTextField(SELECT_ROOT_ATTRIBUTE,
						"where find resource", hspan_vc)));
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
		if (attribute == null) {
			return "select an attribute before";
		}
		if (attribute.getType() != WorkspaceCST.STRING) {
			return "It's not a string attribute";
		}

		return null;
	}

}

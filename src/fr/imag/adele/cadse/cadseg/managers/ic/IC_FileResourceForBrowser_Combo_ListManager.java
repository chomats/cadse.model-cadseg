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

import fr.imag.adele.cadse.cadseg.managers.ui.DisplayManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.Pages;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.mc.IntModelController;

/**
 * The Class IC_FileResourceForBrowser_Combo_ListManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_FileResourceForBrowser_Combo_ListManager extends IC_ResourceTreeDialogForBrowser_Combo_ListManager
		implements IItemManager {

	/**
	 * The Class MyContentItem.
	 */
	class MyContentItem extends IC_ResourceTreeDialogForBrowser_Combo_ListManager.MyContentItem {

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
		 * @see model.workspace.workspace.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set, java.lang.Object)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
			super.generateCallArguments(sb, imports, object);

			DisplayManager.addAttributeInCall(getItem(), PATTERN_SELECT_FILE_ATTRIBUTE, true, ".*", sb);
			DisplayManager.addAttributeInCall(getItem(), SELECT_FOLDER_ATTRIBUTE, false, "false", sb);

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			super.generateConstructorParameter(sb);
			sb.append(" String pattern, boolean selectFolder,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			super.generateConstrustorArguments(sb);
			sb.append(" pattern, selectFolder,");
		}

	}

	/** The Constant DEFAUL_CLASS_NAME. */
	@SuppressWarnings("hiding")
	public static final String	DEFAUL_CLASS_NAME				= "fede.workspace.model.manager.properties.impl.ic.IC_FileResourceForBrowser_Combo_List";

	/** The Constant PATTERN_SELECT_FILE_ATTRIBUTE. */
	public static final String	PATTERN_SELECT_FILE_ATTRIBUTE	= "pattern-select-file";

	/** The Constant SELECT_FOLDER_ATTRIBUTE. */
	public static final String	SELECT_FOLDER_ATTRIBUTE			= "select-folder";

	/**
	 * Instantiates a new i c_ file resource for browser_ combo_ list manager.
	 */
	public IC_FileResourceForBrowser_Combo_ListManager() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager#createCreationPages(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {

		CreationAction action = new CreationAction(theItemParent, desType, theLinkType,
				DisplayManager.IC_DEFAULT_SHORT_NAME);

		IntModelController hspan_vc = new IntModelController(0, 0, "The number of column must be > 0", null, 1);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
				"Add a File resource user controller for a list field",
				"Add a File resource user for the current field list", 2, FieldsCore.createTextField(
						SELECT_TITLE_ATTRIBUTE, "dialog title"), FieldsCore.createTextField(SELECT_MESSAGE_ATTRIBUTE,
						"dialog message"), FieldsCore.createTextField(SELECT_ROOT_ATTRIBUTE, "where find resource",
						hspan_vc), FieldsCore.createTextField(PATTERN_SELECT_FILE_ATTRIBUTE, "file pattern"),
				FieldsCore.createCheckBox(SELECT_FOLDER_ATTRIBUTE, "select folder")));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Pages createModificationPage(Item item) {
		AbstractActionPage action = new ModificationAction(item);

		IModelController mc = new MC_AttributesItem();

		IntModelController hspan_vc = new IntModelController(0, 0, "The number of column must be > 0", null, 1);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "a File resource user controller",
				"a File resource user controller", 2, FieldsCore
						.createTextField(SELECT_TITLE_ATTRIBUTE, "dialog title"), FieldsCore.createTextField(
						SELECT_MESSAGE_ATTRIBUTE, "dialog message"), FieldsCore.createTextField(SELECT_ROOT_ATTRIBUTE,
						"where find resource", hspan_vc), FieldsCore.createTextField(PATTERN_SELECT_FILE_ATTRIBUTE,
						"file pattern"), FieldsCore.createCheckBox(SELECT_FOLDER_ATTRIBUTE, "select folder")));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentManager(Item item) throws CadseException {
		return new MyContentItem(null, item);
	}

}

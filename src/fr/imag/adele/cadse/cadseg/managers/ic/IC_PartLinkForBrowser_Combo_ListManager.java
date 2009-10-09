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

import java.util.List;
import java.util.Set;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
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
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.Pages;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fede.workspace.model.manager.properties.FieldsCore;

/**
 * The Class IC_PartLinkForBrowser_Combo_ListManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_PartLinkForBrowser_Combo_ListManager extends IC_LinkForBrowser_Combo_ListManager {

	// String title, String message, LinkType linkType,
	// LinkType partLinkType, String errormessage
	/**
	 * The Class MyContentItem.
	 */
	class MyContentItem extends IC_LinkForBrowser_Combo_ListManager.MyContentItem {

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
		 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set, java.lang.Object)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
			super.generateCallArguments(sb, imports, object);
			Item ic = getItem();

			Item a = FieldManager.getAttribute(ic.getPartParent().getPartParent());
			Item itemtypedest = LinkManager.getDestination(a);

			Item[] incomingLinkType = ItemTypeManager.getIncomingLinkTypesOfPart(itemtypedest);

			if (incomingLinkType.length == 1 && incomingLinkType[0] != a) {
				Item partLinkTytpe = incomingLinkType[0];
				sb.append(
						GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariable.DEFAULT, partLinkTytpe, null,
								null, imports)).append(",");
			} else {
				sb.append("null /*error cannot find incoming part from ").append(a.getName()).append("*/,");
			}
			DisplayManager.addAttributeInCall(getItem(), ERROR_MESSAGE_ATTRIBUTE, true, "??", sb);

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			super.generateConstructorParameter(sb);
			sb.append(" LinkType partLinkType, String errormessage,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			super.generateConstrustorArguments(sb);
			sb.append(" partLinkType, errormessage,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager.MyContentItem#computeImportsPackage(java.util.Set)
		 */
		@Override
		public void computeImportsPackage(Set<String> imports) {
			super.computeImportsPackage(imports);
		}
	}

	/** The Constant DEFAUL_CLASS_NAME. */

	@SuppressWarnings("hiding")
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.ic.IC_PartLinkForBrowser_Combo_List";

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public IC_PartLinkForBrowser_Combo_ListManager() {
	}

	/** The Constant ERROR_MESSAGE_ATTRIBUTE. */
	public static final String	ERROR_MESSAGE_ATTRIBUTE	= "select-messsage";

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

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
				"Add a link interaction controller for a browser or a combo",
				"Add a link interaction controller for a browser or  a combo", 2, createFieldDialogTitle(),
				createFieldDialogMessage(), createFieldErrorMessage()));
	}

	/**
	 * Creates the field error message.
	 * 
	 * @return the uI field
	 */
	protected UIField createFieldErrorMessage() {
		return FieldsCore.createTextField(ERROR_MESSAGE_ATTRIBUTE, "error message");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager#createPropertyFolderFolder1()
	 */
	@Override
	IPage createPropertyFolderFolder1() {
		return FieldsCore.createPage("page1", " a link interaction controller for a browser or a combo",
				" a link user controller for the current browser field", 2, createFieldDialogTitle(),
				createFieldDialogMessage(), createFieldErrorMessage());
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

	// /**
	// * @generated
	// */
	// static public Item getSelectLink(Item linkDialogUserControllerForBrowser)
	// {
	// return linkDialogUserControllerForBrowser.getOutgoingItem(
	// SELECT_LINK_LINK, true);
	// }
	//
	// /**
	// * @generated
	// */
	// static public void setSelectLink(Item linkDialogUserControllerForBrowser,
	// Item value) throws CadseException {
	// linkDialogUserControllerForBrowser.setOutgoingItem(SELECT_LINK_LINK,
	// value);
	// }
	//
	// /**
	// * @generated
	// */
	// static public Item getSelectType(Item linkDialogUserControllerForBrowser)
	// {
	// return linkDialogUserControllerForBrowser.getOutgoingItem(
	// SELECT_TYPE_LINK, true);
	// }
	//
	// /**
	// * @generated
	// */
	// static public void setSelectType(Item linkDialogUserControllerForBrowser,
	// Item value) throws CadseException {
	// linkDialogUserControllerForBrowser.setOutgoingItem(SELECT_TYPE_LINK,
	// value);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentManager(Item item) throws CadseException {
		return new MyContentItem(null, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
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
		Item itemtypedest = LinkManager.getDestination(attribute);

		Item[] incomingLinkType = ItemTypeManager.getIncomingLinkTypesOfPart(itemtypedest);
		if (incomingLinkType.length == 1 && incomingLinkType[0] != attribute) {
			return null;
		}
		return "no incoming part";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#validate(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.IItemManager.ProblemReporter)
	 */
	@Override
	public List<Item> validate(Item item, ProblemReporter reporter) {
		List<Item> ret = super.validate(item, reporter);

		Item ic = item;

		Item a = FieldManager.getAttribute(ic.getPartParent().getPartParent());
		Item itemtypedest = LinkManager.getDestination(a);

		Item[] incomingLinkType = ItemTypeManager.getIncomingLinkTypesOfPart(itemtypedest);

		if (!(incomingLinkType.length == 1 && incomingLinkType[0] != a)) {
			reporter.error(item, 0, "Cannot find incoming part from {0}.", a.getName());
		}
		return ret;
	}
}

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

import org.eclipse.jdt.core.IType;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
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
import fr.imag.adele.cadse.core.var.ContextVariable;
import fede.workspace.model.manager.properties.FieldsCore;

/**
 * The Class IC_EnumForListManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_EnumForListManager extends IC_AbstractForBrowser_ComboManager implements IItemManager {

	/**
	 * The Class MyContentItem.
	 */
	class MyContentItem extends IC_AbstractForBrowser_ComboManager.MyContentItem {

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
		 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set, java.lang.Object)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
			super.generateCallArguments(sb, imports, object);
			Item uc = getItem();

			Item field = uc.getPartParent().getPartParent();

			Item enumattribute = FieldManager.getAttribute(field);

			Item enumtype = EnumManager.getEnumType(enumattribute);

			IType javaenumtype = EnumTypeManager.getEnumQualifiedClass(ContextVariable.DEFAULT, enumtype);

			sb.append(javaenumtype.getElementName()).append(".class");
			sb.append(",");

			imports.add(javaenumtype.getFullyQualifiedName());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			super.generateConstructorParameter(sb);
			sb.append(" Class values,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			super.generateConstrustorArguments(sb);
			sb.append(" values,");
		}

	}

	/** The Constant DEFAUL_CLASS_NAME. */
	@SuppressWarnings("hiding")
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.ic.IC_EnumForList";

	/**
	 * Instantiates a new i c_ enum for list manager.
	 */
	public IC_EnumForListManager() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager#createCreationPages(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {

		CreationAction action = new CreationAction(theItemParent, desType, theLinkType,
				DisplayManager.IC_DEFAULT_SHORT_NAME);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
				"Create a interaction controler for list with an enum attribut",
				"Create a interaction controler for list with an enum attribut", 3, FieldsCore.createTextField(
						SELECT_TITLE_ATTRIBUTE, "dialog title"), FieldsCore.createTextField(SELECT_MESSAGE_ATTRIBUTE,
						"dialog message")));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Pages createModificationPage(Item item) {
		AbstractActionPage action = new ModificationAction(item);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
				"Create a interaction controler for list with an enum attribut",
				"Create a interaction controler for list with an enum attribut", 3, FieldsCore.createTextField(
						SELECT_TITLE_ATTRIBUTE, "dialog title"), FieldsCore.createTextField(SELECT_MESSAGE_ATTRIBUTE,
						"dialog message")));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentManager(Item item) throws CadseException {
		return new MyContentItem(null, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateMeItem(Item itemParent, LinkType lt, ItemType destType) {
		Item field = itemParent.getPartParent();
		Item attribute = FieldManager.getAttribute(field);
		if (attribute == null) {
			return "select an attribute before";
		}
		if (attribute.getType() != WorkspaceCST.ENUM) {
			return "It's not an enum attribute";
		}
		if (itemParent.getType() == WorkspaceCST.DLIST) {
			return null;
		}
		return "It's for a list field";
	}

}

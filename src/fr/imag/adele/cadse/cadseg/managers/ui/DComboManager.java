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

package fr.imag.adele.cadse.cadseg.managers.ui;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import java.util.Set;

import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.Pages;
import fede.workspace.model.manager.properties.FieldsCore;

/**
 * The Class DComboManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class DComboManager extends DisplayManager implements IItemManager {

	/**
	 * The Class MyContentItem.
	 */
	public final class MyContentItem extends DisplayManager.MyContentItem {

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
		 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports) {
			super.generateCallArguments(sb, imports);
			sb.append(isEditableAttribute(getItem())).append(',');
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			sb.append("String key, String label, EPosLabel poslabel, "
					+ "IModelController mc, IInteractionControllerForBrowserOrCombo ic, boolean edit,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			sb.append("key, label, poslabel, " + "mc, ic, edit,");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#hasContent(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public boolean hasContent(Item item) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentManager(Item item) throws CadseException {
		return new MyContentItem(null, item);
	}

	/** The Constant DEFAUL_CLASS_NAME. */
	@SuppressWarnings("hiding")
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.ui.DComboUI";

	/**
	 * Instantiates a new d combo manager.
	 */
	public DComboManager() {
	}

	/**
	 * @generated
	 */
	@Override
	public String computeUniqueName(Item item, String shortName, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(shortName);
			return sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * @generated
	 */
	@Override
	public String getDisplayName(Item item) {
		try {
			Object value;
			Item currentItem;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * get a link 'ic' from 'DCombo' to 'InteractionController'.
	 * 
	 * @generated
	 */
	static public Link getIcLink(Item dCombo) {
		return dCombo.getOutgoingLink(WorkspaceCST.DCOMBO_lt_IC);
	}

	/**
	 * get all link destination 'ic' from 'DCombo' to 'InteractionController'.
	 * 
	 * @generated
	 */
	static public Item getIcAll(Item dCombo) {
		return dCombo.getOutgoingItem(WorkspaceCST.DCOMBO_lt_IC, false);
	}

	/**
	 * get resolved link destination 'ic' from 'DCombo' to
	 * 'InteractionController'.
	 * 
	 * @generated
	 */
	static public Item getIc(Item dCombo) {
		return dCombo.getOutgoingItem(WorkspaceCST.DCOMBO_lt_IC, true);
	}

	/**
	 * set a link 'ic' from 'DCombo' to 'InteractionController'.
	 * 
	 * @generated
	 */
	static public void setIc(Item dCombo, Item value) throws CadseException {
		dCombo.setOutgoingItem(WorkspaceCST.DCOMBO_lt_IC, value);
	}

	/**
	 * get a link 'mc' from 'DCombo' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public Link getMcLink(Item dCombo) {
		return dCombo.getOutgoingLink(WorkspaceCST.DCOMBO_lt_MC);
	}

	/**
	 * get all link destination 'mc' from 'DCombo' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public Item getMcAll(Item dCombo) {
		return dCombo.getOutgoingItem(WorkspaceCST.DCOMBO_lt_MC, false);
	}

	/**
	 * get resolved link destination 'mc' from 'DCombo' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public Item getMc(Item dCombo) {
		return dCombo.getOutgoingItem(WorkspaceCST.DCOMBO_lt_MC, true);
	}

	/**
	 * set a link 'mc' from 'DCombo' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public void setMc(Item dCombo, Item value) throws CadseException {
		dCombo.setOutgoingItem(WorkspaceCST.DCOMBO_lt_MC, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createCreationPages(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {

		CreationAction action = new CreationAction(theItemParent, desType, theLinkType, DEFAULT_SHORT_NAME);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "Create a combo", "Create a combo", 3,
				createFieldExtendsIC(), createFieldExtendsMC(), createFieldExtendsUI(), createFieldEditable()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Pages createModificationPage(Item item) {
		AbstractActionPage action = new ModificationAction(item);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "a combo", "a combo", 3,
				createFieldExtendsIC(), createFieldExtendsMC(), createFieldExtendsUI(), createFieldEditable()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateMeItem(Item field, LinkType lt, ItemType destType) {
		Item attribute = FieldManager.getAttribute(field);
		if (attribute == null) {
			return "You must add a link to an attribute link";
		}
		if (AttributeManager.isIsListAttribute(attribute)) {
			return "Combo is not for a list";
		}
		return null;
	}

}

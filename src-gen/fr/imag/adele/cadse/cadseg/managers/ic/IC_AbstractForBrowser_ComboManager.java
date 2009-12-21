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
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import java.util.UUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;

// TODO message
/**
 * The Class IC_AbstractForBrowser_ComboManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_AbstractForBrowser_ComboManager extends InteractionControllerManager implements IItemManager {

	/**
	 * The Class MyContentItem.
	 */
	class MyContentItem extends InteractionControllerManager.InteractionControllerContent {

		/**
		 * Instantiates a new my content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 * @throws CadseException
		 */
		protected MyContentItem(UUID id) throws CadseException {
			super(id);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set, java.lang.Object)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
			DisplayManager.addAttributeInCall(getItem(), SELECT_TITLE_ATTRIBUTE, true, "??", sb);
			DisplayManager.addAttributeInCall(getItem(), SELECT_MESSAGE_ATTRIBUTE, true, "??", sb);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			sb.append(" String title, String message,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			sb.append(" title, message,");
		}
	}

	/** The Constant SELECT_TITLE_ATTRIBUTE. */
	public static final String	SELECT_TITLE_ATTRIBUTE		= "select-title";

	/** The Constant SELECT_MESSAGE_ATTRIBUTE. */
	public static final String	SELECT_MESSAGE_ATTRIBUTE	= "select-messsage";

	/** The Constant DEFAUL_CLASS_NAME. */
	private static final String	DEFAUL_CLASS_NAME			= "fede.workspace.model.manager.properties.impl.ic.IC_AbstractForBrowser_Combo";

	/**
	 * Instantiates a new i c_ abstract for browser_ combo manager.
	 */
	public IC_AbstractForBrowser_ComboManager() {
	}

	/**
		@generated
	*/
	@Override
	public String computeQualifiedName(Item item, String name, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			Item currentItem;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(name);
			return sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
		@generated
	*/
	@Override
	public String getDisplayName(Item item) {
		try {
			Object value;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
		@generated
	*/
	public static final String getMessageAttribute(Item iC_AbstractForBrowser_Combo) {
		return iC_AbstractForBrowser_Combo.getAttributeWithDefaultValue(CadseGCST.IC_ABSTRACT_FOR_BROWSER_COMBO_at_MESSAGE_, null);
	}

	/**
		@generated
	*/
	public static final void setMessageAttribute(Item iC_AbstractForBrowser_Combo, String value) {
		try {
			iC_AbstractForBrowser_Combo.setAttribute(CadseGCST.IC_ABSTRACT_FOR_BROWSER_COMBO_at_MESSAGE_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getTitleAttribute(Item iC_AbstractForBrowser_Combo) {
		return iC_AbstractForBrowser_Combo.getAttributeWithDefaultValue(CadseGCST.IC_ABSTRACT_FOR_BROWSER_COMBO_at_TITLE_, null);
	}

	/**
		@generated
	*/
	public static final void setTitleAttribute(Item iC_AbstractForBrowser_Combo, String value) {
		try {
			iC_AbstractForBrowser_Combo.setAttribute(CadseGCST.IC_ABSTRACT_FOR_BROWSER_COMBO_at_TITLE_, value);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentItem(UUID id) throws CadseException {
		return new MyContentItem(id);
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see fede.workspace.model.manager.DefaultItemManager#createCreationPages(fr.imag.adele.cadse.core.Item,
//	 *      fr.imag.adele.cadse.core.LinkType,
//	 *      fr.imag.adele.cadse.core.ItemType)
//	 */
//	@Override
//	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {
//
//		CreationAction action = new CreationAction(theItemParent, desType, theLinkType,
//				DisplayManager.IC_DEFAULT_NAME);
//
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "IC_AbstractForBrowser_ComboManager",
//				"IC_AbstractForBrowser_ComboManager", 3, FieldsCore.createTextField(SELECT_TITLE_ATTRIBUTE,
//						"dialog title"), FieldsCore.createTextField(SELECT_MESSAGE_ATTRIBUTE, "dialog message")));
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see fede.workspace.model.manager.DefaultItemManager#createModificationPage(fr.imag.adele.cadse.core.Item)
//	 */
//	@Override
//	public Pages createModificationPage(Item item) {
//		AbstractActionPage action = new ModificationAction(item);
//
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "IC_AbstractForBrowser_ComboManager",
//				"IC_AbstractForBrowser_ComboManager", 3, FieldsCore.createTextField(SELECT_TITLE_ATTRIBUTE,
//						"dialog title"), FieldsCore.createTextField(SELECT_MESSAGE_ATTRIBUTE, "dialog message")));
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
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
		if (itemParent.getType() != CadseGCST.DBROWSER && itemParent.getType() != CadseGCST.DCOMBO) {
			return "It's not a browser field or a combo field";
		}
		return null;

	}

	

}

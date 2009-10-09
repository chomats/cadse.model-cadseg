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
import fr.imag.adele.cadse.cadseg.util.Util;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.internal.ui.PagesImpl;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.Pages;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ic.IC_StaticArrayOfObjectForBrowser_Combo;

/**
 * The Class IC_JavaClassForBrowser_ComboManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_JavaClassForBrowser_ComboManager extends InteractionControllerManager implements IItemManager {

	/** The Constant SELECT_TITLE_ATTRIBUTE. */
	public static final String		SELECT_TITLE_ATTRIBUTE		= "select-title";

	/** The Constant SELECT_MESSAGE_ATTRIBUTE. */
	public static final String		SELECT_MESSAGE_ATTRIBUTE	= "select-messsage";

	/** The Constant STYLE_ATTRIBUTE. */
	public static final String		STYLE_ATTRIBUTE				= "STYLE";

	/** The Constant FILTER_ATTRIBUTE. */
	public static final String		FILTER_ATTRIBUTE			= "FILTER";

	/** The Constant style_values. */
	public static final String[]	style_values				= { "CLASSES", "INTERFACES", "ANNOTATIONS", "ENUMS",
			"ALL TYPES", "CLASSES AND INTERFACES", "CLASSES AND ENUMS" };

	/** The Constant style_values_cst. */
	public static final String[]	style_values_cst			= { "CONSIDER_CLASSES", "CONSIDER_INTERFACES",
			"CONSIDER_ANNOTATION_TYPES", "CONSIDER_ENUMS", "CONSIDER_ALL_TYPES", "CONSIDER_CLASSES_AND_INTERFACES",
			"CONSIDER_CLASSES_AND_ENUMS"						};

	/**
	 * The Class MyContentItem.
	 */
	class MyContentItem extends InteractionControllerManager.MyContentItem {

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
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set, java.lang.Object)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
			DisplayManager.addAttributeInCall(getItem(), SELECT_TITLE_ATTRIBUTE, true, "??", sb);
			DisplayManager.addAttributeInCall(getItem(), SELECT_MESSAGE_ATTRIBUTE, true, "??", sb);
			sb.append(" IJavaElementSearchConstants.");
			String style = getItem().getAttribute(STYLE_ATTRIBUTE);
			String stylecst = style_values_cst[0];
			if (style != null) {
				for (int i = 0; i < style_values.length; i++) {
					if (style.equals(style_values[i])) {
						stylecst = style_values_cst[i];
						break;
					}
				}
			}
			sb.append(stylecst).append(",");
			DisplayManager.addAttributeInCall(getItem(), FILTER_ATTRIBUTE, true, "", sb);
			imports.add("org.eclipse.jdt.ui.IJavaElementSearchConstants");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			sb.append(" String title, String message, int style, String filter,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			sb.append(" title, message, style, filter,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#computeImportsPackage(java.util.Set)
		 */
		@Override
		public void computeImportsPackage(Set<String> imports) {
			super.computeImportsPackage(imports);
			imports.add("org.eclipse.jdt.ui");
		}

	}

	/** The Constant DEFAUL_CLASS_NAME. */
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.eclipse.java.fields.IC_JavaClassForBrowser_Combo";

	/**
	 * Instantiates a new i c_ java class for browser_ combo manager.
	 */
	public IC_JavaClassForBrowser_ComboManager() {
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

		CreationAction action = new CreationAction(theItemParent, desType, theLinkType,
				DisplayManager.IC_DEFAULT_SHORT_NAME);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
				"Create a interaction controler for browser or combo with a java class attribut",
				"Create a interaction controler for browser or combo with a java class attribut", 3, FieldsCore
						.createTextField(SELECT_TITLE_ATTRIBUTE, "dialog title"), FieldsCore.createTextField(
						SELECT_MESSAGE_ATTRIBUTE, "dialog message"), FieldsCore.createComboBox(STYLE_ATTRIBUTE,
						"style", EPosLabel.left, new IC_StaticArrayOfObjectForBrowser_Combo("", "", style_values),
						null, true), FieldsCore.createTextField(FILTER_ATTRIBUTE, "filter",
						"The initial pattern to filter the set of types.\n"
								+ "For example \"Abstract\" shows  all types starting with \"Abstract\".\n"
								+ "The meta character '?' representing any character and\n"
								+ "'*' representing any string are supported.\n"
								+ "You can pass an empty string if no filtering is required")

		));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Pages createModificationPage(Item item) {
		AbstractActionPage action = new ModificationAction(item);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
				"Create a interaction controler for browser or combo with a java class attribut",
				"Create a interaction controler for browser or combo with a java class attribut", 3, FieldsCore
						.createTextField(SELECT_TITLE_ATTRIBUTE, "dialog title"), FieldsCore.createTextField(
						SELECT_MESSAGE_ATTRIBUTE, "dialog message"), FieldsCore.createComboBox(STYLE_ATTRIBUTE,
						"style", EPosLabel.left, new IC_StaticArrayOfObjectForBrowser_Combo("", "", style_values),
						null, true), FieldsCore.createTextField(FILTER_ATTRIBUTE, "filter",
						"The initial pattern to filter the set of types.\n"
								+ "For example \"Abstract\" shows  all types starting with \"Abstract\".\n"
								+ "The meta character '?' representing any character and\n"
								+ "'*' representing any string are supported.\n"
								+ "You can pass an empty string if no filtering is required")

		));
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
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentManager(Item item) throws CadseException {
		return new MyContentItem(null, item);
	}

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
		if (attribute.getType() != WorkspaceCST.STRING) {
			return "It's not an string attribute";
		}
		if (itemParent.getType() == WorkspaceCST.DBROWSER) {
			return null;
		}
		if (itemParent.getType() == WorkspaceCST.DCOMBO) {
			return null;
		}
		return "It's not a browser field or a combo field";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createdItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public void createdItem(Item item) throws CadseException {
		super.createdItem(item);
		Util.setDefaultValueIfNeed(item, SELECT_MESSAGE_ATTRIBUTE, "Select a value.");
		Util.setDefaultValueIfNeed(item, SELECT_TITLE_ATTRIBUTE, "Select a value.");
	}
}

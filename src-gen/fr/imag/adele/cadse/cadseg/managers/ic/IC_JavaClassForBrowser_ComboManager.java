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
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_WithDialogAction;

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
			DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
					true, "??", sb);
			DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
					true, "??", sb);
			sb.append(" IJavaElementSearchConstants.");
			String style = getOwnerItem().getAttribute(CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_STYLE_);
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
			DisplayManager.addAttributeInCall(getOwnerItem(), 
					CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_FILTER_, true, "", sb);
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
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
//				"Create a interaction controler for browser or combo with a java class attribut",
//				"Create a interaction controler for browser or combo with a java class attribut", 3, FieldsCore
//						.createTextField(SELECT_TITLE_ATTRIBUTE, "dialog title"), FieldsCore.createTextField(
//						SELECT_MESSAGE_ATTRIBUTE, "dialog message"), FieldsCore.createComboBox(STYLE_ATTRIBUTE,
//						"style", EPosLabel.left, new IC_StaticArrayOfObjectForBrowser_Combo("", "", style_values),
//						null, true), FieldsCore.createTextField(FILTER_ATTRIBUTE, "filter",
//						"The initial pattern to filter the set of types.\n"
//								+ "For example \"Abstract\" shows  all types starting with \"Abstract\".\n"
//								+ "The meta character '?' representing any character and\n"
//								+ "'*' representing any string are supported.\n"
//								+ "You can pass an empty string if no filtering is required")
//
//		));
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
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
//				"Create a interaction controler for browser or combo with a java class attribut",
//				"Create a interaction controler for browser or combo with a java class attribut", 3, FieldsCore
//						.createTextField(SELECT_TITLE_ATTRIBUTE, "dialog title"), FieldsCore.createTextField(
//						SELECT_MESSAGE_ATTRIBUTE, "dialog message"), FieldsCore.createComboBox(STYLE_ATTRIBUTE,
//						"style", EPosLabel.left, new IC_StaticArrayOfObjectForBrowser_Combo("", "", style_values),
//						null, true), FieldsCore.createTextField(FILTER_ATTRIBUTE, "filter",
//						"The initial pattern to filter the set of types.\n"
//								+ "For example \"Abstract\" shows  all types starting with \"Abstract\".\n"
//								+ "The meta character '?' representing any character and\n"
//								+ "'*' representing any string are supported.\n"
//								+ "You can pass an empty string if no filtering is required")
//
//		));
//	}

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
	public ContentItem createContentItem(UUID id) throws CadseException {
		return new MyContentItem(id);
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
		if (attribute.getType() != CadseGCST.STRING) {
			return "It's not an string attribute";
		}
		if (itemParent.getType() == CadseGCST.DBROWSER) {
			return null;
		}
		if (itemParent.getType() == CadseGCST.DCOMBO) {
			return null;
		}
		return "It's not a browser field or a combo field";
	}

	
}

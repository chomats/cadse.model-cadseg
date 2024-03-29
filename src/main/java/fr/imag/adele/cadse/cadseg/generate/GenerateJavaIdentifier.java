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
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.cadseg.generate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.PageManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class GenerateJavaIdentifier.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class GenerateJavaIdentifier {

	/**
	 * Gets the manager class name.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param manager
	 *            the manager
	 * 
	 * @return the manager class name
	 */
	public static String getManagerClassName(ContextVariable cxt, ItemType itemType, Item manager, boolean custom) {
		// Item itemType = ManagerManager.getItemType(manager);
		if (itemType != null && itemType.isRuntime()) {
			String m = itemType.getAttribute(CadseGCST.ITEM_TYPE_at_ITEM_MANAGER_);
			return JavaIdentifier.getlastclassName(m);
		}
		if (itemType == null)
			itemType = (ItemType) ManagerManager.getItemType(manager);
		if (itemType == null)
			return "???";
		return JavaIdentifier.javaIdentifierFromString(cxt.getName(itemType), true, false, custom ? "CustomManager"
				: "Manager");
	}

	/**
	 * Gets the manager class name.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param manager
	 *            the manager
	 * 
	 * @return the manager class name
	 */
	public static String getCIFClassName(ContextVariable cxt, Item itemType) {
		return JavaIdentifier.javaIdentifierFromString(cxt.getName(itemType), true, false, "CIF");
	}
	/**
	 * Gets the exporter class name.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param itemType
	 *            the itemtype
	 * 
	 * @return the exporter class name
	 */
	public static String getExporterClassName(ContextVariable cxt, Item itemType) {
		return JavaIdentifier.javaIdentifierFromString(cxt.getName(itemType), true, false, "Exporter");
	}
	
	/**
	 * Gets the exporter package name.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param itemType
	 *            the itemtype
	 * 
	 * @return the exporter package name
	 */
	public static String getExporterPackageName(ContextVariable cxt, Item itemType) {
		return getItemTypePackage(cxt, itemType, null, ".exporter");
	}

	/**
	 * Gets the composer class name.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param itemType
	 *            the itemtype
	 * 
	 * @return the composer class name
	 */
	public static String getComposerClassName(ContextVariable cxt, Item itemType) {
		return JavaIdentifier.javaIdentifierFromString(cxt.getName(itemType), true, false, "Composer");
	}
	
	/**
	 * Gets the composer package name.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param itemType
	 *            the itemtype
	 * 
	 * @return the composer package name
	 */
	public static String getComposerPackageName(ContextVariable cxt, Item itemType) {
		return getItemTypePackage(cxt, itemType, null, ".composer");
	}

	/**
	 * Gets the contentitem class name.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param itemType
	 *            the type
	 * 
	 * @return the contentitem class name
	 */
	public static String getContentClassName(ContextVariable cxt, Item itemType) {
		return JavaIdentifier.javaIdentifierFromString(cxt.getName(itemType), true, false, "Content");
	}

	/**
	 * Gets the manager class name.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param manager
	 *            the manager
	 * 
	 * @return the manager class name
	 */
	public static String getContentPackageName(ContextVariable cxt, Item itemType) {
		
		while(true) {
			if (itemType != null && itemType.isRuntime()) {
				Class<? extends ContentItem> m = ((ItemType)itemType).getContentItemClass();
				if (m != null)
					return m.getPackage().getName();
			}
			else
				return getItemTypePackage(cxt, itemType, null, ".contents");
			itemType =  ((ItemType)itemType).getSuperType();
			if (itemType == null)
				return null;
		}
	}
	
	
	

	/**
	 * Gets the ext class name.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the ext class name
	 */
	public static String getExtClassName(ContextVariable cxt, Item extItemType) {
		return JavaIdentifier.javaIdentifierFromString(cxt.getName(extItemType), true, false, "Ext");
	}

	/**
	 * Gets the manager package.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param manager
	 *            the manager
	 * 
	 * @return the manager package
	 */
	public static String getManagerPackage(ContextVariable cxt, ItemType itemType, Item manager) {

		if (itemType != null && itemType.isRuntime()) {
			String m = itemType.getAttribute(CadseGCST.ITEM_TYPE_at_ITEM_MANAGER_);
			return JavaIdentifier.getPackageName(m);
		}
		if (itemType == null)
			itemType = (ItemType) ManagerManager.getItemType(manager);
		if (itemType == null)
			return "cannot-find-item-type-from-" + manager.getName();
		return getItemTypePackage(cxt, itemType, null, ".managers");
	}

	/**
	 * Gets the manager package.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param manager
	 *            the manager
	 * 
	 * @return the manager package
	 */
	public static String getQualifiedManager(ContextVariable cxt, ItemType itemType, Item manager, boolean custom) {
		if (itemType != null && itemType.isRuntime()) {
			String m = itemType.getAttribute(CadseGCST.ITEM_TYPE_at_ITEM_MANAGER_);
			return JavaIdentifier.getPackageName(m);
		}
		if (itemType == null)
			itemType = (ItemType) ManagerManager.getItemType(manager);
		return getItemTypePackage(cxt, itemType, null, ".managers") + "."
				+ getManagerClassName(cxt, itemType, manager, custom);
	}

	/**
	 * Gets the manager package.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param manager
	 *            the manager
	 * 
	 * @return the manager package
	 */
	public static String getCIFPackage(ContextVariable cxt, Item itemtype) {
		return getItemTypePackage(cxt, itemtype, null, ".contents");
	}

	/**
	 * Gets the ext package.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the ext package
	 */
	public static String getExtPackage(ContextVariable cxt, Item extItemType) {
		Item cadseDefinition = extItemType.getPartParent(CadseGCST.CADSE_DEFINITION);
		return getItemTypePackage(cxt, extItemType, cadseDefinition, ".ext");
	}

	/**
	 * Gets the item type package.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param itemtype
	 *            the itemtype
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param prefix
	 *            the prefix
	 * 
	 * @return the item type package
	 */
	public static String getItemTypePackage(ContextVariable cxt, Item itemtype, Item cadseDefinition, String prefix) {
		if (cadseDefinition == null) {
			cadseDefinition = ItemTypeManager.getCadseDefinition(itemtype);
		}
		String packageName = getPrefixPackageName(cxt, cadseDefinition, prefix);

		String subpackage = ItemTypeManager.getSubPackageFromCategory(itemtype);
		// subpackage = manager.getAttribute(SUB_PACKAGE_ATTRIBUTE);
		if (subpackage != null && subpackage.length() != 0) {
			packageName += "." + subpackage;
		}
		return packageName;
	}

	protected static String getPrefixPackageName(ContextVariable cxt,
			Item cadseDefinition, String prefix) {
		if (cadseDefinition == null) {
			
			return "<>";
		}
		String packageName = CadseDefinitionManager.getDefaultPackage(cxt, cadseDefinition);
		if (prefix != null) {
			packageName += prefix;
		}
		return packageName;
	}

	/**
	 * Cst item type.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param itemType
	 *            the item type
	 * 
	 * @return the string
	 */
	public static String cstItemType(ContextVariable cxt, Item itemType) {
		return cstExtaCstName(cxt, itemType, null);
	}
	
	/**
	 * Cst item type.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param itemType
	 *            the item type
	 * 
	 * @return the string
	 */
	public static String cstExtaCstName(ContextVariable cxt, Item itemType, String postfix) {
		return JavaIdentifier.javaIdentifierFromStringUPPER(cxt.getName(itemType), postfix);
	}

	/**
	 * Cst attribute.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param attribute
	 *            the attribute
	 * @param absItemType
	 *            the abs item type
	 * 
	 * @return the string
	 */
	public static String cstAttribute(ContextVariable cxt, Item attribute, Item absItemType) {
		if (absItemType == null) {
			absItemType = attribute.getPartParent();
		}
		if (absItemType == null)
			return "??";

		if (AttributeManager.isLinkAttribute(attribute)) {
			return JavaIdentifier.javaIdentifierFromStringUPPER(cxt.getName(absItemType), null) + "_lt_"
					+ JavaIdentifier.javaIdentifierFromStringUPPER(cxt.getName(attribute), null);
		} else {
			return JavaIdentifier.javaIdentifierFromStringUPPER(cxt.getName(absItemType), null) + "_at_"
					+ JavaIdentifier.javaIdentifierFromStringUPPER(cxt.getName(attribute), null);
		}
	}

	/**
	 * Cst qualified attribute.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param attribute
	 *            the attribute
	 * @param itemType
	 *            the item type
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param imports
	 *            the imports
	 * 
	 * @return the string
	 */
	public static String cstQualifiedAttribute(ContextVariable cxt, Item attribute, Item itemType,
			Item cadseDefinition, Set<String> imports) {
		if (attribute == null) {
			return null;
		}
		if (itemType == null) {
			itemType = attribute.getPartParent();
		}
		if (cadseDefinition == null) {
			cadseDefinition = ItemTypeManager.getCadseDefinition(itemType);
		}
		String cn = javaClassNameFileCST_FromCadseDefinition(cxt, cadseDefinition);
		if (imports != null) {
			imports.add(javaPackageNameFileCST_FromCadseDefinition(cxt, cadseDefinition) + "." + cn);
		}
		return cn + "." + cstAttribute(cxt, attribute, itemType);
	}

	/**
	 * Cst qualified attribute item type.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param itemType
	 *            the item type
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param imports
	 *            the imports
	 * 
	 * @return the string
	 */
	public static String cstQualifiedAttributeItemType(ContextVariable cxt, Item itemType, Item cadseDefinition,
			Set<String> imports) {
		if (cadseDefinition == null) {
			cadseDefinition = ItemTypeManager.getCadseDefinition(itemType);
		}
		String cn = javaClassNameFileCST_FromCadseDefinition(cxt, cadseDefinition);
		if (imports != null) {
			imports.add(javaPackageNameFileCST_FromCadseDefinition(cxt, cadseDefinition) + "." + cn);
		}
		return cn + "." + cstItemType(cxt, itemType);
	}

	/**
	 * Adds the import cst.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param imports
	 *            the imports
	 * 
	 * @return the string
	 */
	public static String addImportCST(ContextVariable cxt, Item cadseDefinition, Set<String> imports) {
		String cn = javaClassNameFileCST_FromCadseDefinition(cxt, cadseDefinition);
		imports.add(javaPackageNameFileCST_FromCadseDefinition(cxt, cadseDefinition) + "." + cn);
		return cn;
	}

	/**
	 * Adds the import cst.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param imports
	 *            the imports
	 * 
	 * @return the string
	 */
	public static String getImportCST(ContextVariable cxt, Item cadseDefinition) {
		String cn = javaClassNameFileCST_FromCadseDefinition(cxt, cadseDefinition);
		return javaPackageNameFileCST_FromCadseDefinition(cxt, cadseDefinition) + "." + cn;
	}
	
	/**
	 * Cst get attribute.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param attribute
	 *            the attribute
	 * 
	 * @return the string
	 */
	public static String cstGetAttribute(ContextVariable cxt, Item attribute) {
		String upper_first_att_name = JavaIdentifier
				.javaIdentifierFromString(cxt.getName(attribute), true, false, null);
		if (AttributeManager.isLinkAttribute(attribute)) {
			return "get" + upper_first_att_name;
		} else {
			return (!AttributeManager.isIsListAttribute(attribute) && attribute.getType() == CadseGCST.BOOLEAN ? "is"
					: "get")
					+ upper_first_att_name + "Attribute";
		}
	}

	/**
	 * Cst set attribute.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param attribute
	 *            the attribute
	 * 
	 * @return the string
	 */
	public static String cstSetAttribute(ContextVariable cxt, Item attribute) {
		String upper_first_att_name = JavaIdentifier
				.javaIdentifierFromString(cxt.getName(attribute), true, false, null);
		if (AttributeManager.isLinkAttribute(attribute)) {
			return "set" + upper_first_att_name;
		}
		return "set" + upper_first_att_name + "Attribute";
	}

	/**
	 * Java package file cst from attribute.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param attribute
	 *            the attribute
	 * 
	 * @return the string
	 */
	public static String javaPackageFileCSTFromAttribute(ContextVariable cxt, Item attribute) {
		Item itemtype = attribute.getPartParent();
		Item cadseDefinition = ItemTypeManager.getCadseDefinition(itemtype);
		return javaPackageNameFileCST_FromCadseDefinition(cxt, cadseDefinition);
	}

	/**
	 * Java package name file cs t_ from cadse definition.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the string
	 */
	public static String javaPackageNameFileCST_FromCadseDefinition(ContextVariable cxt, Item cadseDefinition) {
		if (cadseDefinition == null)
			return "??";
		if (cadseDefinition.getType() == CadseGCST.CADSE) {
			if (cadseDefinition.getBaseItem() == null)
				return "??";
			return JavaIdentifier.getPackageName(((CadseRuntime) cadseDefinition.getBaseItem())
					.getCstQualifiedClassName());
		}

		String defaultValue = CadseDefinitionManager.getDefaultPackage(cxt, cadseDefinition);
		return ow(cadseDefinition, "java.package.cst", defaultValue);
	}

	/**
	 * Java class name file cst from attribute.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param attribute
	 *            the attribute
	 * 
	 * @return the string
	 */
	public static String javaClassNameFileCSTFromAttribute(ContextVariable cxt, Item attribute) {
		Item itemtype = attribute.getPartParent();
		Item cadseDefinition = ItemTypeManager.getCadseDefinition(itemtype);
		return javaClassNameFileCST_FromCadseDefinition(cxt, cadseDefinition);
	}

	static java.util.Properties getCadsegOWProperties(Item cadseDefinition) {
		File f = cadseDefinition.getMainMappingContent(File.class);
		if (f != null) {
			final File cadsePropertiesFile = new File(f, "cadseg.properties");
			if (cadsePropertiesFile.exists()) {
				try {
					Properties ret = new Properties();
					ret.load(new FileInputStream(cadsePropertiesFile));
					return ret;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static String ow(Item cadseDefinition, String key, String defaultValue) {
		Properties p = getCadsegOWProperties(cadseDefinition);
		if (p == null) {
			return defaultValue;
		}
		String v = p.getProperty("ow." + key);
		if (v != null) {
			return v;
		}
		return defaultValue;
	}

	/**
	 * Java class name file cs t_ from cadse definition.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the string
	 */
	public static String javaClassNameFileCST_FromCadseDefinition(ContextVariable cxt, Item cadseDefinition) {
		if (cadseDefinition == null)
			return "??";
		if (cadseDefinition.getType() == CadseGCST.CADSE) {
			if (cadseDefinition.getBaseItem() == null)
				return "??";
			return JavaIdentifier.getlastclassName(((CadseRuntime) cadseDefinition.getBaseItem())
					.getCstQualifiedClassName());
		}

		return ow(cadseDefinition, "java.classname.cst", JavaIdentifier.javaIdentifierFromString(cxt
				.getName(cadseDefinition), true, false, "CST"));
	}

	/**
	 * Qualified global creation action from creation dialog.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param creationDialog
	 *            the creation dialog
	 * 
	 * @return the string
	 */
	public static String qualifiedGlobalCreationActionFromCreationDialog(ContextVariable cxt, Item creationDialog) {
		return javaPackageGlobalCreationActionFromCreationDialog(cxt, creationDialog) + "."
				+ javaClassNameGlobalCreationActionFromCreationDialog(cxt, creationDialog);
	}

	/**
	 * Java package global creation action from creation dialog.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param creationDialog
	 *            the creation dialog
	 * 
	 * @return the string
	 */
	public static String javaPackageGlobalCreationActionFromCreationDialog(ContextVariable cxt, Item creationDialog) {
		return getItemTypePackage(cxt, creationDialog.getPartParent(), null, ".pages.actions");
	}

	/**
	 * Java class name global creation action from creation dialog.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param creationDialog
	 *            the creation dialog
	 * 
	 * @return the string
	 */
	public static String javaClassNameGlobalCreationActionFromCreationDialog(ContextVariable cxt, Item creationDialog) {
		Item itemtype = creationDialog.getPartParent();
		return JavaIdentifier.javaIdentifierFromString(cxt.getName(itemtype), true, false, "_CreationPagesAction");
	}

	/**
	 * Java package page factory from page.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param page
	 *            the page
	 * 
	 * @return the string
	 */
	public static String javaPackagePageFactoryFromPage(ContextVariable cxt, Item page) {
		return javaPackagePage(cxt, (ItemType) page.getPartParent(CadseGCST.ITEM_TYPE));
	}

	/**
	 * Java package page factory from dialog.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param dialog
	 *            the dialog
	 * 
	 * @return the string
	 */
	public static String javaPackagePage(ContextVariable cxt, ItemType type) {
		return getItemTypePackage(cxt, type, null, ".pages");
	}

	/**
	 * Qualified page factory from page.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param page
	 *            the page
	 * 
	 * @return the string
	 */
	public static String qualifiedPageFactoryFromPage(ContextVariable cxt, Item page) {
		return javaPackagePageFactoryFromPage(cxt, page) + "." + javaClassNamePageFactoryFromPage(cxt, page);
	}

	/**
	 * Java class name page factory from page.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param page
	 *            the page
	 * 
	 * @return the string
	 */
	public static String javaClassNamePageFactoryFromPage(ContextVariable cxt, Item page) {
		Item itemtype = null;
		if (page instanceof IPage) {
			itemtype = ((IPage) page).getParentItemType();
		}
		else
			itemtype = page.getPartParent(CadseGCST.ITEM_TYPE);
		if (itemtype == null)
			return "<?>";
		
		if (PageManager.isModificationPage(page)) {
			return JavaIdentifier.javaIdentifierFromString(cxt.getName(itemtype) + "-" + cxt.getName(page), true,
					false, "_ModificationPage");
		}
		return JavaIdentifier.javaIdentifierFromString(cxt.getName(itemtype) + "-" + cxt.getName(page), true, false,
				"_CreationPage");
	}

	/**
	 * Java class name page action from page.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param page
	 *            the page
	 * 
	 * @return the string
	 */
	public static String javaClassNamePageActionFromPage(ContextVariable cxt, Item page) {
		Item dialog = page.getPartParent();
		Item itemtype = dialog.getPartParent();
		if (PageManager.isModificationPage(page)) {
			return JavaIdentifier.javaIdentifierFromString(cxt.getName(itemtype) + "-" + cxt.getName(page), true,
					false, "_ModificationPageAction");
		}
		return JavaIdentifier.javaIdentifierFromString(cxt.getName(itemtype) + "-" + cxt.getName(page), true, false,
				"_CreationPageAction");
	}

	/**
	 * Java class name menu action.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param menuaction
	 *            the menuaction
	 * 
	 * @return the string
	 */
	public static String javaClassNameMenuAction(ContextVariable cxt, Item menuaction) {
		return JavaIdentifier.javaIdentifierFromString(cxt.getName(menuaction), true, false, "MenuAction");
	}

	/**
	 * Java package menu action.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param menuaction
	 *            the menuaction
	 * 
	 * @return the string
	 */
	public static String javaPackageMenuAction(ContextVariable cxt, Item menuaction) {
		Item itemtype = menuaction.getPartParent(CadseGCST.TYPE_DEFINITION);
		Item cadseDefinition = itemtype.getPartParent(CadseGCST.CADSE_DEFINITION);
		return getItemTypePackage(cxt, itemtype, cadseDefinition, ".menu");
	}

	/**
	 * Qualified menu action.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param menuaction
	 *            the menuaction
	 * 
	 * @return the string
	 */
	public static String qualifiedMenuAction(ContextVariable cxt, Item menuaction) {
		return javaPackageMenuAction(cxt, menuaction) + "." + javaClassNameMenuAction(cxt, menuaction);
	}

	public static String getInitPackageName(GenContext cxt, Item cadseDef) {
		return getPrefixPackageName(cxt, cadseDef, ".init");
	}

	public static String getInitClassName(GenContext cxt, Item cadseDef) {
		return "CadseInit";
	}
}

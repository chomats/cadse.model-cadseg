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

package fr.imag.adele.cadse.cadseg.managers.dataModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseRootCST;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.ItemFilter;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.util.Convert;
import fr.imag.adele.cadse.core.impl.ContentItemImpl;
import fr.imag.adele.cadse.core.key.SpaceKeyType;
import fr.imag.adele.cadse.core.util.ArraysUtil;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class ItemTypeManager.
 */
public class ItemTypeManager extends AbstractItemTypeManager {

	/**
	 * Gets the package.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param itemtype
	 *            the itemtype
	 * @param cat
	 *            the cat
	 * 
	 * @return the package
	 */
	public static String getPackage(ContextVariable cxt, Item itemtype, String cat) {
		Item model = getCadseDefinition(itemtype);
		String packageName = CadseDefinitionManager.getDefaultPackage(cxt, model) + cat;

		String subpackage = ItemTypeManager.getSubPackageFromCategory(itemtype);
		// subpackage = manager.getAttribute(SUB_PACKAGE_ATTRIBUTE);
		if (subpackage != null && subpackage.length() != 0) {
			packageName += "." + subpackage;
		}
		return packageName;
	}

	/**
	 * The Class ItemTypeJavaFileContentManager.
	 */
	private final static class ItemTypeJavaFileContentManager extends ContentItemImpl implements IGenerateContent,
			IPDEContributor {
		// JavaFileContentManager itemJavaFile;
		// JavaFileContentManager itemImplJavaFile;
		// JavaFileContentManager itemtypeJavaFile;
		// JavaFileContentManager itemtypeImplJavaFile;

		/**
		 * Instantiates a new item type java file content manager.
		 * 
		 * @param itemType
		 *            the item type
		 * @param workspaceModel
		 *            the workspace model
		 * 
		 * @throws CadseException
		 *             the melusine exception
		 */
		private ItemTypeJavaFileContentManager(Item itemType, Item workspaceModel) throws CadseException {
			super(workspaceModel.getContentItem(), itemType);
			// itemJavaFile = new
			// JavaFileContentManager(workspaceModel.getAndCreateContentManager(),itemType,getPackage(itemType,".instance"),
			// JavaIdentifier
			// .javaIdentifierFromString(itemType.getName(),
			// true, false, "Instance"));
			// itemImplJavaFile = new
			// JavaFileContentManager(workspaceModel.getAndCreateContentManager(),itemType,getPackage(itemType,".instance.impl"),
			// JavaIdentifier
			// .javaIdentifierFromString(itemType.getName(),
			// true, false, "InstanceImpl"));
			// itemtypeJavaFile = new
			// JavaFileContentManager(workspaceModel.getAndCreateContentManager(),itemType,getPackage(itemType,".types"),
			// JavaIdentifier
			// .javaIdentifierFromString(itemType.getName(),
			// true, false, "Type"));
			// itemtypeImplJavaFile = new
			// JavaFileContentManager(workspaceModel.getAndCreateContentManager(),itemType,getPackage(itemType,".types.impl"),
			// JavaIdentifier
			// .javaIdentifierFromString(itemType.getName(),
			// true, false, "TypeImpl"));
		}

		/**
		 * Generate.
		 * 
		 * @param cxt
		 *            the cxt
		 * 
		 * @see ManagerManager#getContentModel(Item )
		 */
		public void generate(ContextVariable cxt) {
			// generateItemImpl();
			//
			// Item itemtype = getItem();
			// ManagerSkeltonTemplate itypetmp = new ManagerSkeltonTemplate();
			// Item model = getWorkspaceModel(itemtype);
			// String packageName = getPackageName();
			// String className = getClassName();
			// try {
			// EclipsePluginContentManger.generateJava(MelusineProjectManager
			// .getProject(model), new Path("sources/"
			// + packageName.replace('.', '/')), className + ".java",
			// itypetmp.generate(itemtype), Eclipse
			// .getDefaultMonitor());
			//
			// } catch (CoreException e) {
			// e.printStackTrace();
			// }
			// ((IGenerateContent)
			// getWorkspaceModel(itemtype).getAndCreateContentManager())
			// .generate();
		}

		// private void generateItemImpl() {
		// IFile f = itemImplJavaFile.getFile();
		// try {
		// EclipsePluginContentManger.generateJava(f,
		// itypetmp.generate(itemtype), Eclipse
		// .getDefaultMonitor());
		//
		// } catch (CoreException e) {
		// e.printStackTrace();
		// }
		// }

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IGenerateContent#getGenerateModel()
		 */
		public ManagerManager.GenerateModel getGenerateModel() {
			return null;
			// Item itemtype = getItem();
			// ManagerManager.GenerateModel cm = new
			// ManagerManager.GenerateModel();
			// cm.importsArray = new HashSet<String>();
			//
			//
			// cm.itemName = itemtype.getName();
			// cm.className = getClassName();
			// cm.packageName = getPackageName();
			//
			// // Item manager = getManager(theitemtype);
			// //ManagerManager.Type kindClassName =
			// ManagerManager.getType(manager);
			// cm.callSuper = !ItemTypeManager.isWorkspaceRoot(itemtype);
			// if (cm.callSuper) {
			// Item superItem = ItemTypeManager.getSuperType(itemtype);
			// if (superItem != null) {
			// Item superItemManager = ItemTypeManager
			// .getManager(superItem);
			// cm.superClassName = ((JavaFileContentManager) superItemManager
			// .getAndCreateContentManager()).getClassName();
			// String importClass = ((JavaFileContentManager) superItemManager
			// .getAndCreateContentManager()).getPackageName()
			// + "." + cm.superClassName;
			// cm.importsArray.add(importClass);
			// } else {
			// cm.superClassName = kindClassName.getTypeJava();
			// cm.importsArray.addAll(Arrays
			// .asList(kindClassName.java_imports));
			//
			// }
			// } else {
			//
			// cm.superClassName = "StandardWStemManager";
			// cm.importsArray
			// .add("fede.workspace.model.manager.StandardWStemManager");
			// //
			// cm.importsArray.add("org.eclipse.jface.resource.ImageDescriptor");
			// }
			//
			// cm.body = new GenStringBuilder();
			// cm.body.begin();
			// generate(cm.body, "manager", "all", cm.importsArray, null);
			// cm.body.end();
			// // Item content = getContentModel(manager);
			//
			// // Item wizardItem = getWizard(manager);
			// // if (wizardItem != null) {
			// // cm.methodsSB.newline();
			// // cm.methodsSB.newline().append("/**");
			// // cm.methodsSB.newline().append(" @generated");
			// // cm.methodsSB.newline().append("*/");
			// //
			// // cm.methodsSB.append("@Override").newline()
			// // .append("public IFieldDescription
			// getCreateItemFieldDescription()
			// // {").newline();
			// // cm.methodsSB.begin();
			// // cm.methodsSB.append("return ");
			// // IModelWorkspaceManager im = (IModelWorkspaceManager)
			// // WSPlugin.getManager(wizardItem);
			// // im.generate(wizardItem, cm.methodsSB, cm.importsArray);
			// // cm.methodsSB.append(';').newline();
			// // cm.methodsSB.append('}').newline();
			// // }
			// // cm.methodsSB.end();
			//
			// cm.attributes_str = cm.body.toString();
			// cm.methods_str = "";
			//
			// cm.importsArray
			// .add("fede.workspace.tool.eclipse.IFieldDescription");
			// return cm;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fr.imag.adele.cadse.core.ContentManager#generate(fr.imag.adele.cadse
		 * .core.GenStringBuilder, java.lang.String, java.lang.String,
		 * java.util.Set, fr.imag.adele.cadse.core.GenContext)
		 */
		@Override
		public void generate(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {
			if (kind.equals("all")) {
				generateParts(sb, type, "inner-class", imports, context);
				generateParts(sb, type, "cstes", imports, context);
				generateParts(sb, type, "attributes", imports, context);
				generateParts(sb, type, "constructors", imports, context);
				generateParts(sb, type, "methods", imports, context);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeExportsPackage(java.util.Set)
		 */
		public void computeExportsPackage(Set<String> exports) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeImportsPackage(java.util.Set)
		 */
		public void computeImportsPackage(Set<String> imports) {

			imports.add("fede.workspace.model.manager");
			imports.add("org.eclipse.jface.resource");
			imports.add("org.eclipse.core.resources");
			imports.add("org.eclipse.core.runtime.jobs");

			imports.add("fede.workspace.tool.eclipse");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion
		 * (org.eclipse.pde.core.plugin.IPluginBase,
		 * org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
		 */
		public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ContentManager#create()
		 */
		@Override
		public void create() throws CadseException {
			// this.itemImplJavaFile.create();
			// this.itemJavaFile.create();
			// this.itemtypeImplJavaFile.create();
			// this.itemtypeJavaFile.create();

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ContentManager#delete()
		 */
		@Override
		public void delete() throws CadseException {
			// this.itemImplJavaFile.delete();
			// this.itemJavaFile.delete();
			// this.itemtypeImplJavaFile.delete();
			// this.itemtypeJavaFile.delete();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ContentManager#getKindsResource()
		 */
		@Override
		public String[] getKindsResource() {
			// TODO Auto-generated method stub
			return new String[0]; // this.itemtypeJavaFile.getKindsResource();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ContentManager#getMainResource()
		 */
		@Override
		public Object getMainResource() {
			// TODO Auto-generated method stub
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fr.imag.adele.cadse.core.ContentManager#getResources(java.lang.String
		 * )
		 */
		@Override
		public Object[] getResources(String kind) {
			// TODO Auto-generated method stub
			return new Object[0]; // this.itemtypeJavaFile.getResources(kind);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fr.imag.adele.cadse.core.ContentManager#setResources(java.lang.String
		 * , java.lang.Object[])
		 */
		@Override
		public void setResources(String kind, Object[] resource) throws CadseException {
			// TODO Auto-generated method stub

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#init(fr.imag.adele.cadse
	 * .core.ItemType)
	 */
	@Override
	public void init() {
		WorkspaceCST.ITEM_TYPE.setSpaceKeyType(new SpaceKeyType(WorkspaceCST.ITEM_TYPE, WorkspaceCST.DATA_MODEL) {
			@Override
			protected String convertName(String name) {
				if (name == null) return null;
				return name.toUpperCase();
			}
			
			@Override
			protected String getName(Item item) {
				return convertName(super.getName(item));
			}
		});
		WorkspaceCST.ITEM_TYPE.setHasShortNameAttribute(true);
		WorkspaceCST.ITEM_TYPE.setHasUniqueNameAttribute(true);
	}

	/**
	 * Instantiates a new item type manager.
	 */
	public ItemTypeManager() {
		_default = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.dataModel.AbstractItemTypeManager#
	 * computeUniqueName(fr.imag.adele.cadse.core.Item, java.lang.String,
	 * fr.imag.adele.cadse.core.Item, fr.imag.adele.cadse.core.LinkType)
	 */
	@Override
	public String computeUniqueName(Item theItemType, String shortName, Item parent, LinkType lt) {
		Item cadse = getCadseDefinition(theItemType);
		String subpackage = getSubPackageFromCategory(theItemType);
		StringBuilder sb = new StringBuilder();
		sb.append(cadse.getName());
		if (subpackage != null) {
			sb.append(".").append(subpackage);
		}
		sb.append(".");
		sb.append(theItemType.getName());
		return sb.toString();
	}

	/**
	 * Gets the type name.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the type name
	 */
	public static String getTypeName(Item item) {
		return item.getName();
	}

	/**
	 * Gets the attributes.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the attributes
	 */
	static public Collection<Item> getAttributes(Item itemtype) {
		return itemtype.getOutgoingItems(WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES, true);
	}

	/**
	 * Gets the attributes.
	 * 
	 * @param fromCadse
	 *            the from cadse
	 * @param itemtype
	 *            the itemtype
	 * @param attributes
	 *            the attributes
	 * @param itemfilter
	 *            the itemfilter
	 * @param keepLastAttribute
	 * 
	 * @return the attributes
	 */
	static public void getAttributes(HashSet<Item> fromCadse, Item itemtype, Map<String, Item> attributes,
			ItemFilter itemfilter) {

		for (Link l : itemtype.getOutgoingLinks()) {
			// Select link has kind Part and destination.
			if (l.getLinkType() == WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES && l.isLinkResolved()) {
				Item destination = l.getResolvedDestination();
				if (destination == null) {
					continue;
				}

				if (attributes.containsKey(destination.getName())) {
					continue;
				}
				// if dest not null, take this destination to return list.
				if (itemfilter == null || itemfilter.accept(destination)) {
					attributes.put(destination.getName(), destination);
				}
			}
		}
		if (fromCadse != null) {
			Set<Item> extensions = getAllExtensions(itemtype, fromCadse);
			for (Item ext : extensions) {
				for (Link l : ext.getOutgoingLinks()) {
					// Select link has kind Part and destination.
					if (l.getLinkType() == WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES && l.isLinkResolved()) {
						Item destination = l.getResolvedDestination();
						if (destination == null) {
							continue;
						}

						if (attributes.containsKey(destination.getName())) {
							continue;
						}

						// if dest not null, take this destination to return
						// list.
						if (itemfilter == null || itemfilter.accept(destination)) {
							attributes.put(destination.getName(), destination);
						}
					}
				}
			}
		}
	}

	/**
	 * Gets the attributes.
	 * 
	 * @param fromCadse
	 *            the from cadse
	 * @param itemtype
	 *            the itemtype
	 * @param attributes
	 *            the attributes
	 * @param itemfilter
	 *            the itemfilter
	 * @param keepLastAttribute
	 * 
	 * @return the attributes
	 */
	static public void getAttributes(HashSet<Item> fromCadse, Item itemtype, List<Item> attributes,
			ItemFilter itemfilter, boolean keepLastAttribute) {

		for (Link l : itemtype.getOutgoingLinks()) {
			// Select link has kind Part and destination.
			if (l.getLinkType() == WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES && l.isLinkResolved()) {
				Item destination = l.getResolvedDestination();
				// if dest not null, take this destination to return list.
				if (itemfilter == null || itemfilter.accept(destination)) {
					attributes.add(destination);
				}
			}
		}
		if (fromCadse != null) { // utiliser par IC_InverseLink
			Set<Item> extensions = getAllExtensions(itemtype, fromCadse);
			for (Item ext : extensions) {
				for (Link l : ext.getOutgoingLinks()) {
					// Select link has kind Part and destination.
					if (l.getLinkType() == WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES && l.isLinkResolved()) {
						Item destination = l.getResolvedDestination();
						// if dest not null, take this destination to return
						// list.
						if (itemfilter == null || itemfilter.accept(destination)) {
							attributes.add(destination);
						}
					}
				}
			}
		}
	}

	/**
	 * Returne toutes les extensions d'un item type qui appartiennent � la list
	 * des cadse definition contenu dans fromCadse.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * @param fromCadse
	 *            the from cadse
	 * 
	 * @return La liste des extension (not null)
	 */
	public static Set<Item> getAllExtensions(Item itemtype, HashSet<Item> fromCadse) {
		Collection<Item> exts = itemtype.getIncomingItems(WorkspaceCST.EXT_ITEM_TYPE_lt_REF_TYPE);
		HashSet<Item> ret = new HashSet<Item>();
		if (fromCadse == null) {
			ret.addAll(exts);
		} else {
			if (exts != null) {
				for (Item e : exts) {
					Item c = ItemTypeManager.getCadseDefinition(e);
					if (c != null && fromCadse.contains(c)) {
						ret.add(e);
					}
				}
			}
		}
		return ret;
	}

	/**
	 * Sets the attributes.
	 * 
	 * @param ItemType
	 *            the item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	static public void setAttributes(Item ItemType, List<Item> value) throws CadseException {
		ItemType.setOutgoingItems(WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES, value);
	}

	/**
	 * Adds the attributes.
	 * 
	 * @param ItemType
	 *            the item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	static public void addAttributes(Item ItemType, Item value) throws CadseException {
		ItemType.addOutgoingItem(WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES, value);
	}

	/**
	 * Removes the attributes.
	 * 
	 * @param ItemType
	 *            the item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	static public void removeAttributes(Item ItemType, Item value) throws CadseException {
		ItemType.removeOutgoingItem(WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES, value);
	}

	/**
	 * set a link 'super-type' from 'ItemType' to 'ItemType'.
	 * 
	 * @param ItemType
	 *            the item type
	 * 
	 * @return the super type
	 * 
	 * @generated
	 */
	static public Item getSuperType(Item itemType) {
		return itemType.getOutgoingItem(WorkspaceCST.ITEM_TYPE_lt_SUPER_TYPE, true);
	}

	/**
	 * Checks if is has content attribute.
	 * 
	 * @param itemType
	 *            the item type
	 * 
	 * @return true, if checks if is has content attribute
	 * 
	 * @generated
	 */
	public static final boolean isHasContentAttribute(Item itemType) {
		return itemType.getAttributeWithDefaultValue(WorkspaceCST.ITEM_TYPE_at_HAS_CONTENT_, true);
	}

	/**
	 * Sets the has content attribute.
	 * 
	 * @param itemType
	 *            the item type
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setHasContentAttribute(Item itemType, boolean value) {
		try {
			itemType.setAttribute(WorkspaceCST.ITEM_TYPE_at_HAS_CONTENT_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is is root element attribute.
	 * 
	 * @param itemType
	 *            the item type
	 * 
	 * @return true, if checks if is is root element attribute
	 * 
	 * @generated
	 */
	public static final boolean isIsRootElementAttribute(Item itemType) {
		return itemType.getAttributeWithDefaultValue(WorkspaceCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_, true);
	}

	/**
	 * Sets the is root element attribute.
	 * 
	 * @param itemType
	 *            the item type
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setIsRootElementAttribute(Item itemType, boolean value) {
		try {
			itemType.setAttribute(WorkspaceCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is is abstract attribute.
	 * 
	 * @param itemType
	 *            the item type
	 * 
	 * @return true, if checks if is is abstract attribute
	 * 
	 * @generated
	 */
	public static final boolean isIsAbstractAttribute(Item itemType) {
		return itemType.getAttributeWithDefaultValue(WorkspaceCST.ITEM_TYPE_at_IS_ABSTRACT_, false);
	}

	/**
	 * Sets the is abstract attribute.
	 * 
	 * @param itemType
	 *            the item type
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setIsAbstractAttribute(Item itemType, boolean value) {
		try {
			itemType.setAttribute(WorkspaceCST.ITEM_TYPE_at_IS_ABSTRACT_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is has short name attribute.
	 * 
	 * @param itemType
	 *            the item type
	 * 
	 * @return true, if checks if is has short name attribute
	 * 
	 * @generated
	 */
	public static final boolean isHasShortNameAttribute(Item itemType) {
		return itemType.getAttributeWithDefaultValue(WorkspaceCST.ITEM_TYPE_at_HAS_SHORT_NAME_, false);
	}

	/**
	 * Sets the has short name attribute.
	 * 
	 * @param itemType
	 *            the item type
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setHasShortNameAttribute(Item itemType, boolean value) {
		try {
			itemType.setAttribute(WorkspaceCST.ITEM_TYPE_at_HAS_SHORT_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is has unique name attribute.
	 * 
	 * @param itemType
	 *            the item type
	 * 
	 * @return true, if checks if is has unique name attribute
	 * 
	 * @generated
	 */
	public static final boolean isHasUniqueNameAttribute(Item itemType) {
		return itemType.getAttributeWithDefaultValue(WorkspaceCST.ITEM_TYPE_at_HAS_UNIQUE_NAME_, false);
	}

	/**
	 * Sets the has unique name attribute.
	 * 
	 * @param itemType
	 *            the item type
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setHasUniqueNameAttribute(Item itemType, boolean value) {
		try {
			itemType.setAttribute(WorkspaceCST.ITEM_TYPE_at_HAS_UNIQUE_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isIsMetaItemTypeAttribute(Item itemType) {
		return itemType.getAttributeWithDefaultValue(WorkspaceCST.ITEM_TYPE_at_IS_META_ITEM_TYPE_, false);
	}

	/**
	 * 
	 */
	public static final boolean isMetaItemTypeH(Item itemType) {
		while (itemType != null) {
			if (isIsMetaItemTypeAttribute(itemType)) {
				return true;
			}
			itemType = getSuperType(itemType);
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static final void setIsMetaItemTypeAttribute(Item itemType, boolean value) {
		try {
			itemType.setAttribute(WorkspaceCST.ITEM_TYPE_at_IS_META_ITEM_TYPE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * get links 'wcListeners' from 'ItemType' to 'WCListener'.
	 * 
	 * @generated
	 */
	static public List<Link> getWcListenersLink(Item itemType) {
		return itemType.getOutgoingLinks(WorkspaceCST.ITEM_TYPE_lt_WC_LISTENERS);
	}

	/**
	 * @generated
	 */
	static public Collection<Item> getWcListenersAll(Item itemType) {
		return itemType.getOutgoingItems(WorkspaceCST.ITEM_TYPE_lt_WC_LISTENERS, false);
	}

	/**
	 * @generated
	 */
	static public Collection<Item> getWcListeners(Item itemType) {
		return itemType.getOutgoingItems(WorkspaceCST.ITEM_TYPE_lt_WC_LISTENERS, true);
	}

	/**
	 * @generated
	 */
	static public void addWcListeners(Item itemType, Item value) throws CadseException {
		itemType.addOutgoingItem(WorkspaceCST.ITEM_TYPE_lt_WC_LISTENERS, value);
	}

	/**
	 * @generated
	 */
	static public void removeWcListeners(Item itemType, Item value) throws CadseException {
		itemType.removeOutgoingItem(WorkspaceCST.ITEM_TYPE_lt_WC_LISTENERS, value);
	}

	/**
	 * @generated
	 */
	public static final boolean isIsHiddenAttribute(Item itemType) {
		return itemType.getAttributeWithDefaultValue(WorkspaceCST.ITEM_TYPE_at_IS_HIDDEN_, false);
	}

	/**
	 * @generated
	 */
	public static final void setIsHiddenAttribute(Item itemType, boolean value) {
		try {
			itemType.setAttribute(WorkspaceCST.ITEM_TYPE_at_IS_HIDDEN_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isOverwriteDefaultPagesAttribute(Item itemType) {
		return itemType.getAttributeWithDefaultValue(WorkspaceCST.ITEM_TYPE_at_OVERWRITE_DEFAULT_PAGES_, true);
	}

	/**
	 * @generated
	 */
	public static final void setOverwriteDefaultPagesAttribute(Item itemType, boolean value) {
		try {
			itemType.setAttribute(WorkspaceCST.ITEM_TYPE_at_OVERWRITE_DEFAULT_PAGES_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * get links '#invert_part_types_to_DataModel' from 'ItemType' to
	 * 'DataModel'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_types_to_DataModelLink(Item itemType) {
		return itemType.getOutgoingLink(WorkspaceCST.ITEM_TYPE_lt__$_INVERT_PART_TYPES_TO_DATA_MODEL);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_types_to_DataModelAll(Item itemType) {
		return itemType.getOutgoingItem(WorkspaceCST.ITEM_TYPE_lt__$_INVERT_PART_TYPES_TO_DATA_MODEL, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_types_to_DataModel(Item itemType) {
		return itemType.getOutgoingItem(WorkspaceCST.ITEM_TYPE_lt__$_INVERT_PART_TYPES_TO_DATA_MODEL, true);
	}

	/**
	 * set a link '#invert_part_types_to_DataModel' from 'ItemType' to
	 * 'DataModel'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_types_to_DataModel(Item itemType, Item value) throws CadseException {
		itemType.setOutgoingItem(WorkspaceCST.ITEM_TYPE_lt__$_INVERT_PART_TYPES_TO_DATA_MODEL, value);
	}

	/**
	 * get links '#invert_part_types_to_ProductModel' from 'ItemType' to
	 * 'ProductModel'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_types_to_ProductModelLink(Item itemType) {
		return itemType.getOutgoingLink(WorkspaceCST.ITEM_TYPE_lt__$_INVERT_PART_TYPES_TO_PRODUCT_MODEL);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_types_to_ProductModelAll(Item itemType) {
		return itemType.getOutgoingItem(WorkspaceCST.ITEM_TYPE_lt__$_INVERT_PART_TYPES_TO_PRODUCT_MODEL, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_types_to_ProductModel(Item itemType) {
		return itemType.getOutgoingItem(WorkspaceCST.ITEM_TYPE_lt__$_INVERT_PART_TYPES_TO_PRODUCT_MODEL, true);
	}

	/**
	 * set a link '#invert_part_types_to_ProductModel' from 'ItemType' to
	 * 'ProductModel'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_types_to_ProductModel(Item itemType, Item value) throws CadseException {
		itemType.setOutgoingItem(WorkspaceCST.ITEM_TYPE_lt__$_INVERT_PART_TYPES_TO_PRODUCT_MODEL, value);
	}

	/**
	 * Sets the super type.
	 * 
	 * @param ItemType
	 *            the item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	static public void setSuperType(Item ItemType, Item value) throws CadseException {
		ItemType.setOutgoingItem(WorkspaceCST.ITEM_TYPE_lt_SUPER_TYPE, value);
	}

	/** The _default. */
	private static ItemTypeManager	_default;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#createContentManager(
	 * fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentManager(Item item) throws CadseException {
		return new ItemTypeJavaFileContentManager(item, getCadseDefinition(item));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#hasContent(fr.imag.adele
	 * .cadse.core.Item)
	 */
	@Override
	public boolean hasContent(Item item) {
		return true;
	}

	/**
	 * Delete dialog.
	 * 
	 * @param managerItem
	 *            the manager item
	 */
	private void deleteDialog(Item managerItem) {
		Item createDialog = getCreationDialog(managerItem);
		if (createDialog != null) {
			try {
				createDialog.delete(false);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Item modificationDialog = getModificationDialog(managerItem);
		if (modificationDialog != null) {
			try {
				modificationDialog.delete(false);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets the main data model.
	 * 
	 * @param theItemType
	 *            the the item type
	 * 
	 * @return the main data model
	 */
	public static Item getMainDataModel(Item theItemType) {
		Item cadsegModel = getCadseDefinition(theItemType);
		if (cadsegModel == null) {
			return null;
		}
		return CadseDefinitionManager.getMainDataModel(cadsegModel);
	}

	/**
	 * Gets the default.
	 * 
	 * @return the default
	 */
	public static ItemTypeManager getDefault() {
		return _default;
	}

	/**
	 * Gets the manager.
	 * 
	 * @param i
	 *            the i
	 * 
	 * @return the manager
	 */
	public static Item getManager(Item i) {
		return ManagerManager.getManagerFromItemType(i);
	}

	/**
	 * Gets the cadse definition.
	 * 
	 * @param theItemType
	 *            the the item type
	 * 
	 * @return the cadse definition
	 */
	static public Item getCadseDefinition(Item theItemType) {
		while (theItemType != null) {
			if (theItemType.getType() == WorkspaceCST.CADSE_DEFINITION) {
				return theItemType;
			}
			theItemType = theItemType.getPartParent();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.dataModel.AbstractItemTypeManager#
	 * getDisplayName(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String getDisplayName(Item item) {
		return item.getName();
	}

	/**
	 * get a link 'super-type' from 'ItemType' to 'ItemType'.
	 * 
	 * @param itemType
	 *            the item type
	 * 
	 * @return the super type link
	 * 
	 * @generated
	 */
	static public Link getSuperTypeLink(Item itemType) {
		return itemType.getOutgoingLink(WorkspaceCST.ITEM_TYPE_lt_SUPER_TYPE);
	}

	/**
	 * get all link destination 'super-type' from 'ItemType' to 'ItemType'.
	 * 
	 * @param itemType
	 *            the item type
	 * 
	 * @return the super type all
	 * 
	 * @generated
	 */
	static public Item getSuperTypeAll(Item itemType) {
		return itemType.getOutgoingItem(WorkspaceCST.ITEM_TYPE_lt_SUPER_TYPE, false);
	}

	/**
	 * Gets the all attributes.
	 * 
	 * @param fromCadse
	 *            the from cadse
	 * @param theItemType
	 *            the the item type
	 * @param itemfilter
	 *            the itemfilter
	 * 
	 * @return the all attributes
	 */
	public static Item[] getAllAttributes(HashSet<Item> fromCadse, Item theItemType, ItemFilter itemfilter,
			boolean keepLastAttribute) {
		if (!keepLastAttribute) {
			List<Item> attributes = new ArrayList<Item>();
			getAllAttributes(fromCadse, theItemType, attributes, itemfilter, keepLastAttribute);
			return attributes.toArray(new Item[attributes.size()]);
		} else {
			HashMap<String, Item> attributes = new HashMap<String, Item>();
			getAllAttributes(fromCadse, theItemType, attributes, itemfilter);
			return attributes.values().toArray(new Item[attributes.size()]);
		}

	}

	/**
	 * Gets the all attributes.
	 * 
	 * @param fromCadse
	 *            the from cadse
	 * @param theItemType
	 *            the the item type
	 * @param attributes
	 *            the attributes
	 * @param itemfilter
	 *            the itemfilter
	 * 
	 * @return the all attributes
	 */
	public static void getAllAttributes(HashSet<Item> fromCadse, Item theItemType, List<Item> attributes,
			ItemFilter itemfilter, boolean keepLastAttribute) {
		getAttributes(fromCadse, theItemType, attributes, itemfilter, keepLastAttribute);
		Item superItem = getSuperType(theItemType);
		if (superItem != null) {
			getAllAttributes(fromCadse, superItem, attributes, itemfilter, keepLastAttribute);
		}

	}

	/**
	 * Gets the all attributes.
	 * 
	 * @param fromCadse
	 *            the from cadse
	 * @param theItemType
	 *            the the item type
	 * @param attributes
	 *            the attributes
	 * @param itemfilter
	 *            the itemfilter
	 * 
	 * @return the all attributes
	 */
	public static void getAllAttributes(HashSet<Item> fromCadse, Item theItemType, Map<String, Item> attributes,
			ItemFilter itemfilter) {
		if (theItemType.getType() == WorkspaceCST.EXT_ITEM_TYPE) {
			// find my attribute definition.
			getAttributes(fromCadse, theItemType, attributes, itemfilter);
			theItemType = ExtItemTypeManager.getRefType(theItemType);
		}

		while (theItemType != null) {
			getAttributes(fromCadse, theItemType, attributes, itemfilter);
			theItemType = getSuperType(theItemType);
		}
	}

	/**
	 * Find attribute.
	 * 
	 * @param theItemType
	 *            the the item type
	 * @param shortName
	 *            the short name
	 * 
	 * @return the item
	 */
	public static Item findAttribute(Item theItemType, String shortName) {
		// Ajout le cas pour les extentions.
		if (theItemType.getType() == WorkspaceCST.EXT_ITEM_TYPE) {
			Item findItem = getOutgoingItems(theItemType, WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES, shortName);
			if (findItem != null) {
				return findItem;
			}
			theItemType = ExtItemTypeManager.getRefType(theItemType);
		}
		while (theItemType != null) {
			Item findItem = getOutgoingItems(theItemType, WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES, shortName);
			if (findItem != null) {
				return findItem;
			}
			theItemType = getSuperType(theItemType);
		}
		return null;
	}

	/**
	 * Gets the outgoing item.
	 * 
	 * @param item
	 *            the item
	 * @param linkNameID
	 *            the link name id
	 * @param shortNameId
	 *            the short name id
	 * 
	 * @return the outgoing item
	 */
	public static Item getOutgoingItems(Item item, LinkType linkNameID, String shortNameId) {
		for (Link l : item.getOutgoingLinks()) {
			// Select link has kind Part and destination.
			if (l.getLinkType() == linkNameID && l.isLinkResolved()
					&& l.getResolvedDestination().getName().equals(shortNameId)) {
				return l.getResolvedDestination();
			}
		}
		return null;
	}

	/**
	 * Checks if is composition.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return true, if is composition
	 */
	public static boolean isComposition(Item item) {
		for (Link l : item.getOutgoingLinks()) {
			if (!l.isLinkResolved()) {
				continue;
			}
			if (l.getDestinationType() != WorkspaceCST.LINK) {
				continue;
			}
			boolean composition = LinkManager.isComposition(l.getResolvedDestination());
			if (composition) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the incoming part.
	 * 
	 * @param itemtypedest
	 *            the itemtypedest
	 * @param itemtypesource
	 *            the itemtypesource
	 * 
	 * @return the incoming part
	 */
	public static Item getIncomingPart(Item itemtypedest, Item itemtypesource) {
		while (itemtypedest != null) {
			for (Link l : itemtypedest.getIncomingLinks()) {
				Item item = l.getSource();
				if (item.getType() != WorkspaceCST.LINK) {
					continue;
				}

				if (LinkManager.isPart(item) && LinkManager.getSource(item) == itemtypesource) {
					return item;
				}
			}
			itemtypedest = getSuperType(itemtypedest);
		}
		return null;
	}

	// public static Map<Item, Integer[]> getPartAllParents(Item itemType) {
	// List<Item> visited = new ArrayList<Item>();
	// int profondeur = 1;
	// List<Item> todoVisited = new ArrayList<Item>();
	//
	// todoVisited.add(itemType);
	//
	//
	// Map<Item, Integer[]> ret = new HashMap<Item, Integer[]>();
	// for (Link l : itemType.getIncomingLinks()) {
	// Item item = l.getSource();
	// if (!item.getType().getId().equals(LinkManager.NAME_TYPE)) continue;
	//
	// //it's a linktype
	// if (!LinkManager.isPart(item))
	// continue;
	// ret.add(item.getPartParent()); // Le parent du linktype est la source de
	// la relation. c'est un itemtype;
	// }
	// return ret;
	// }

	/**
	 * Gets the incoming link type.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * @param s
	 *            the s
	 * 
	 * @return the incoming link type
	 */
	public static Item getIncomingLinkType(Item itemtype, String s) {
		while (itemtype != null) {
			for (Link l : itemtype.getIncomingLinks()) {
				Item item = l.getSource();
				if (item.getType() != WorkspaceCST.LINK) {
					continue;
				}

				if (item.getName().equals(s)) {
					return item;
				}
			}
			itemtype = getSuperType(itemtype);
		}
		return null;
	}

	/**
	 * Gets the outgoing link type.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * @param s
	 *            the s
	 * 
	 * @return the outgoing link type
	 */
	public static Item getOutgoingLinkType(Item itemtype, String s) {
		while (itemtype != null) {
			for (Link l : itemtype.getOutgoingLinks()) {
				if (!l.isLinkResolved()) {
					continue;
				}
				Item item = l.getResolvedDestination();
				if (item.getType() != WorkspaceCST.LINK) {
					continue;
				}

				if (item.getName().equals(s)) {
					return item;
				}
			}
			itemtype = getSuperType(itemtype);
		}
		return null;
	}

	/**
	 * Checks if is super type of.
	 * 
	 * @param THIS
	 *            the tHIS
	 * @param findType
	 *            the find type
	 * 
	 * @return true, if is super type of
	 */
	public static boolean isSuperTypeOf(Item THIS, Item findType) {
		while (true) {
			Item super_it = getSuperType(findType);
			if (super_it == null) {
				return false;
			}
			if (super_it.equals(THIS)) {
				return true;
			}
			findType = super_it;
		}
	}

	/**
	 * Gets the sub types.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the sub types
	 */
	public static Item[] getSubTypes(Item itemtype) {
		List<Item> ret = new ArrayList<Item>();
		for (Link l : itemtype.getIncomingLinks()) {
			if (l.getLinkType() != WorkspaceCST.ITEM_TYPE_lt_SUPER_TYPE) {
				continue;
			}

			ret.add(l.getSource());
		}
		return ret.toArray(new Item[ret.size()]);
	}

	/**
	 * Retourne tous les sous types y compris lui - m�me de toutes la
	 * hierarchie.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the all sub types
	 */
	public static Set<Item> getAllSubTypes(Item itemtype) {
		LinkedHashSet<Item> ret = new LinkedHashSet<Item>();
		getAllSubTypes(itemtype, ret);
		return ret;
	}

	/**
	 * Retourne tous les sous types y compris lui - m�me de toutes la
	 * hierarchie.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the all sub types
	 */
	public static void getAllSubTypes(Item itemtype, Set<Item> types) {
		HashSet<Item> visited = new HashSet<Item>();
		List<Item> compute = new ArrayList<Item>();
		compute.add(itemtype);
		while (compute.size() != 0) {
			Item aItemType = compute.remove(compute.size() - 1);
			visited.add(aItemType);
			types.add(aItemType);
			for (Link l : aItemType.getIncomingLinks()) {
				if (l.getLinkType() != WorkspaceCST.ITEM_TYPE_lt_SUPER_TYPE) {
					continue;
				}
				if (!visited.contains(l.getSource())) {
					compute.add(l.getSource());
				}
			}
		}
	}

	/**
	 * Retourne tous les sous types y compris lui - m�me de toutes la
	 * hierarchie.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the all super types
	 */
	public static Item[] getAllSuperTypes(Item itemtype) {
		LinkedHashSet<Item> ret = new LinkedHashSet<Item>();
		ret.add(itemtype);
		getAllSuperTypes(itemtype, ret);
		Item[] ret_array = ret.toArray(new Item[ret.size()]);
		ArraysUtil.inverser(ret_array);
		return ret_array;
	}

	/**
	 * Retourne tous les sous types y compris lui - m�me de toutes la
	 * hierarchie.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the all super types
	 */
	public static void getAllSuperTypes(Item itemtype, Set<Item> types) {
		HashSet<Item> visited = new HashSet<Item>();
		itemtype = getSuperType(itemtype);
		while (itemtype != null) {
			if (visited.contains(itemtype)) {
				return;
			}
			visited.add(itemtype);
			types.add(itemtype);
			itemtype = getSuperType(itemtype);
		}
	}

	/**
	 * Gets the all creation pages.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the all creation pages
	 */
	public static Item[] getAllCreationPages(Item itemtype) {
		Item[] supertype = getAllSuperTypes(itemtype);
		Map<String, Item> pages = new HashMap<String, Item>();
		for (int i = 0; i < supertype.length; i++) {
			Item anItem = supertype[i];
			Item dialog = getCreationDialogAll(anItem);
			if (dialog == null || !dialog.isResolved()) {
				continue;
			}
			Collection<Item> itempages = CreationDialogManager.getPages(dialog);
			for (Item aPage : itempages) {
				pages.put(aPage.getName(), aPage);
			}
		}
		Collection<Item> pagevalues = pages.values();
		return pagevalues.toArray(new Item[pagevalues.size()]);
	}

	/**
	 * Gets the all modification pages.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the all modification pages
	 */
	public static Item[] getAllModificationPages(Item itemtype) {
		Item[] supertype = getAllSuperTypes(itemtype);
		Map<String, Item> pages = new HashMap<String, Item>();
		for (int i = 0; i < supertype.length; i++) {
			Item anItem = supertype[i];
			Item dialog = getModificationDialog(anItem);
			if (dialog == null || !dialog.isResolved()) {
				continue;
			}
			Collection<Item> itempages = CreationDialogManager.getPages(dialog);
			for (Item aPage : itempages) {
				pages.put(aPage.getName(), aPage);
			}
		}
		Collection<Item> pagevalues = pages.values();
		return pagevalues.toArray(new Item[pagevalues.size()]);
	}

	/**
	 * Gets the incoming link types.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the incoming link types
	 */
	public static Map<String, Item> getIncomingLinkTypes(Item itemtype) {
		Map<String, Item> ret = new HashMap<String, Item>();
		while (itemtype != null) {
			for (Link l : itemtype.getIncomingLinks()) {
				Item item = l.getSource();
				if (item.getType() != WorkspaceCST.LINK) {
					continue;
				}
				if (ret.containsKey(item.getName())) {
					continue;
				}
				ret.put(item.getName(), item);
			}
			itemtype = getSuperType(itemtype);
		}
		return ret;
	}

	/**
	 * Gets the incoming link types.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * @param includeSuperTypes
	 *            includes all super types
	 * @param includeSubTypes
	 *            includes all sub types
	 * @return the incoming link types
	 */
	public static List<Item> getIncomingLinkTypes(Item itemtype, boolean includeSuperTypes, boolean includeSubTypes) {
		LinkedHashSet<Item> types = new LinkedHashSet<Item>();
		types.add(itemtype);

		if (includeSuperTypes) {
			getAllSuperTypes(itemtype, types);
		}
		if (includeSubTypes) {
			getAllSubTypes(itemtype, types);
		}

		List<Item> ret = new ArrayList<Item>();
		for (Item it : types) {
			for (Link l : it.getIncomingLinks()) {
				Item linkDefinition = l.getSource();
				if (linkDefinition.getType() != WorkspaceCST.LINK) {
					continue;
				}

				ret.add(linkDefinition);
			}
		}
		return ret;
	}

	/**
	 * Gets the incoming link types of part.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the incoming link types of part
	 */
	public static Item[] getIncomingLinkTypesOfPart(Item itemtype) {
		List<Item> ret = new ArrayList<Item>();
		while (itemtype != null) {
			for (Link l : itemtype.getIncomingLinks()) {
				Item item = l.getSource();
				if (item.getType() != WorkspaceCST.LINK) {
					continue;
				}

				if (LinkManager.isPart(item)) {
					ret.add(item);
				}
			}
			itemtype = getSuperType(itemtype);
		}
		return ret.toArray(new Item[ret.size()]);
	}

	/**
	 * Gets the ougoing link types.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the ougoing link types
	 */
	public static Map<String, Item> getOugoingLinkTypesH(Item itemtype) {
		Map<String, Item> ret = new HashMap<String, Item>();
		while (itemtype != null) {
			for (Link l : itemtype.getOutgoingLinks()) {
				if (!l.isLinkResolved()) {
					continue;
				}
				Item item = l.getResolvedDestination();
				if (item.getType() != WorkspaceCST.LINK) {
					continue;
				}

				if (ret.containsKey(item.getName())) {
					continue;
				}
				ret.put(item.getName(), item);
			}
			itemtype = getSuperType(itemtype);
		}
		return ret;
	}

	/**
	 * Gets the ougoing link types.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the ougoing link types
	 */
	public static Item[] getOugoingLinkTypes(Item itemtype) {
		List<Item> ret = new ArrayList<Item>();
		for (Link l : itemtype.getOutgoingLinks()) {
			if (!l.isLinkResolved()) {
				continue;
			}
			Item item = l.getResolvedDestination();
			if (item.getType() != WorkspaceCST.LINK) {
				continue;
			}

			ret.add(item);
		}
		return ret.toArray(new Item[ret.size()]);
	}

	/**
	 * Gets the ougoing item types for aggregation.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the ougoing item types for aggregation
	 */
	public static Item[] getOugoingItemTypesForAggregation(Item itemtype) {
		List<Item> ret = new ArrayList<Item>();
		for (Link l : itemtype.getOutgoingLinks()) {
			if (!l.isLinkResolved()) {
				continue;
			}
			Item linktype = l.getResolvedDestination();
			if (linktype.getType() != WorkspaceCST.LINK) {
				continue;
			}

			if (!LinkManager.isAggregation(linktype)) {
				continue;
			}

			Item dest = LinkManager.getDestination(linktype);
			if (dest == null) {
				continue;
			}
			ret.add(dest);
		}
		return ret.toArray(new Item[ret.size()]);
	}

	/**
	 * Gets the part parent.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the part parent
	 */
	public static Item getPartParent(Item itemtype) {
		for (Link l : itemtype.getIncomingLinks()) {
			Item item = l.getSource();
			if (l.getLinkType() != WorkspaceCST.LINK_lt_DESTINATION || (!AttributeManager.isLinkAttribute(item))) {
				continue;
			}
			if (LinkManager.isPart(item)) {
				return LinkManager.getSource(item);
			}
		}
		Item supertype = getSuperType(itemtype);
		if (supertype != null) {
			return getPartParent(supertype);
		}
		return null;
	}

	/**
	 * Gets the part parents.
	 * 
	 * @param itemType
	 *            the item type
	 * 
	 * @return the part parents
	 */
	public static Item[] getPartParents(Item itemType) {
		List<Item> ret = new ArrayList<Item>();
		for (Link l : itemType.getIncomingLinks()) {
			Item item = l.getSource();
			if (l.getLinkType() != WorkspaceCST.LINK_lt_DESTINATION || (!AttributeManager.isLinkAttribute(item))) {
				continue;
			}

			// it's a linktype
			if (!LinkManager.isPart(item)) {
				continue;
				// it's a part linktype
			}

			// get the source of the linktype : it's an itemtype
			Item source = LinkManager.getSource(item);
			if (source != null) {
				ret.add(source);
				// Le parent du linktype est la source de la relation. c'est un
				// itemtype;
			}
		}
		return ret.toArray(new Item[ret.size()]);
	}

	public static String getQualifiedName(Item theItemType) {
		Item cadse = getCadseDefinition(theItemType);
		String subpackage = getSubPackageFromCategory(theItemType);
		StringBuilder sb = new StringBuilder();
		sb.append(cadse.getName());
		if (subpackage != null) {
			sb.append(".").append(subpackage);
		}
		sb.append(".");
		sb.append(theItemType.getName());
		return sb.toString();
	}

	/**
	 * Gets the sub package from category.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the sub package from category
	 */
	public static String getSubPackageFromCategory(Item theItemType) {
		Item data_model_or_categories = theItemType.getPartParent();
		if (data_model_or_categories == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		while (data_model_or_categories.getPartParent() != null) {
			if (data_model_or_categories.getPartParent().getType() == WorkspaceCST.CADSE_DEFINITION) {
				break;
			}
			if (sb.length() != 0) {
				sb.insert(0, '.');
			}
			sb.insert(0, data_model_or_categories.getName());
			data_model_or_categories = data_model_or_categories.getPartParent();
		}

		return sb.toString();
	}

	/**
	 * Calcul la liste de tous les items de type itemtype contenu dans le data
	 * model quelque soit la profondeur. Si le data model est le data model
	 * principal d'un cadse definition, il retroune tous les itemtypes de ce
	 * cadse. Il ne suit pas la relation 'extends' entre cadse.
	 * 
	 * @param datamodel
	 *            un item de type datamodel
	 * 
	 * @return la liste de tous les itemtypes
	 */
	public static Item[] getAllItemType(Item datamodel) {
		if (datamodel == null) {
			return new Item[0];
		}
		HashSet<Item> ret = getAllPackages(datamodel);
		HashSet<Item> retitemtype = new HashSet<Item>();
		for (Item dd : ret) {
			retitemtype.addAll(dd.getOutgoingItems(WorkspaceCST.DATA_MODEL_lt_TYPES, true));
		}

		return retitemtype.toArray(new Item[retitemtype.size()]);
	}

	/**
	 * Retourne tous les packages ou datamodel sous le data model, ne suis pas
	 * la relation extends.
	 * 
	 * @param datamodel
	 *            the datamodel
	 * 
	 * @return the all packages
	 */
	public static HashSet<Item> getAllPackages(Item datamodel) {
		HashSet<Item> ret = new HashSet<Item>();
		ret.add(datamodel);
		Set<Item> pass = new HashSet<Item>();
		pass.add(datamodel);
		while (pass.size() != 0) {
			Set<Item> nextpass = new HashSet<Item>();
			for (Item source : pass) {
				for (Link l : source.getOutgoingLinks()) {
					if (l.getLinkType() == WorkspaceCST.DATA_MODEL_lt_CATEGORIES && l.isLinkResolved()) {
						Item dest = l.getResolvedDestination();
						if (!ret.contains(dest)) {
							ret.add(dest);
							nextpass.add(dest);
						}
					}
				}
			}
			pass = nextpass;
		}
		return ret;
	}

	/**
	 * Calcul la liste de tous les items de type Ext_Type contenu dans le data
	 * model quelque soit la profondeur. Si le data model est le data model
	 * principal d'un cadse definition, il retroune tous les Ext_Type de ce
	 * cadse. Il ne suit pas la relation 'extends' entre cadse.
	 * 
	 * @param datamodel
	 *            un item de type datamodel
	 * 
	 * @return la liste de tous les itemtypes
	 */
	public static Item[] getAllExtItemType(Item datamodel) {
		if (datamodel == null) {
			return new Item[0];
		}
		HashSet<Item> ret = getAllPackages(datamodel);
		HashSet<Item> retitemtype = new HashSet<Item>();
		for (Item dd : ret) {
			retitemtype.addAll(dd.getOutgoingItems(WorkspaceCST.DATA_MODEL_lt_EXT_TYPES, true));
		}

		return retitemtype.toArray(new Item[retitemtype.size()]);
	}

	/**
	 * Calcul la liste de tous les items de type itemtype contenu dans le cadse
	 * definition quelque soit la profondeur, y compris ceux des cadses etendu.
	 * Si le data model est le data model principal d'un cadse definition, il
	 * retroune tous les itemtypes de ce cadse. Cet method suit la relation
	 * 'extends'.
	 * 
	 * @param cadsedef
	 *            un item de type datamodel
	 * 
	 * @return la liste de tous les itemtypes
	 */
	public static Item[] getAllAllItemType(Item cadsedef, ItemFilter filter) {
		if (cadsedef == null) {
			return new Item[0];
		}

		Item datamodel = CadseDefinitionManager.getMainDataModel(cadsedef);
		if (datamodel == null) {
			return new Item[0];
		}

		Item[] ret = getAllDataModel(datamodel);
		HashSet<Item> retitemtype = new HashSet<Item>();
		for (Item dd : ret) {
			if (filter == null || filter.accept(dd)) {
				retitemtype.addAll(dd.getOutgoingItems(WorkspaceCST.DATA_MODEL_lt_TYPES, true));
			}
			if (filter != null && filter.stop()) {
				break;
			}
		}

		return retitemtype.toArray(new Item[retitemtype.size()]);
	}

	/**
	 * Retourne tous les datamodels y compris ceux des cadses etendu., Cet
	 * method suit la relation 'extends'.
	 * 
	 * @param datamodel
	 *            the datamodel
	 * 
	 * @return the all data model
	 */
	public static Item[] getAllDataModel(Item datamodel) {
		HashSet<Item> ret = new HashSet<Item>();
		ret.add(datamodel);
		Set<Item> pass = new HashSet<Item>();
		pass.add(datamodel);
		while (pass.size() != 0) {
			Set<Item> nextpass = new HashSet<Item>();
			for (Item aDataModel : pass) {
				Item partParent = aDataModel.getPartParent();
				if (partParent.getType() == WorkspaceCST.CADSE_DEFINITION) {
					Collection<Item> extendsCadse = CadseDefinitionManager.getExtends(partParent);
					for (Item cadseDef : extendsCadse) {
						Item extDataModel = CadseDefinitionManager.getMainDataModel(cadseDef);
						if (extDataModel != null) {
							if (!ret.contains(extDataModel)) {
								ret.add(extDataModel);
								nextpass.add(extDataModel);
							}
						}
					}
				}
				for (Link l : aDataModel.getOutgoingLinks()) {

					if (l.getLinkType() == WorkspaceCST.DATA_MODEL_lt_CATEGORIES && l.isLinkResolved()) {
						Item dest = l.getResolvedDestination();
						if (!ret.contains(dest)) {
							ret.add(dest);
							nextpass.add(dest);
						}
					}
				}
			}
			pass = nextpass;
		}
		return ret.toArray(new Item[ret.size()]);
	}

	/**
	 * Gets the attribute.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * @param attr
	 *            the attr
	 * 
	 * @return the attribute
	 */
	public static Item getAttribute(Item itemtype, String attr) {
		for (Link l : itemtype.getOutgoingLinks()) {
			// Select link has kind Part and destination.
			if (l.getLinkType() == (WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES) && l.isLinkResolved()
					&& l.getResolvedDestination().getName().equals(attr)) {
				Item destination = l.getResolvedDestination();
				return destination;
			}
		}
		Item superItem = getSuperType(itemtype);
		if (superItem != null) {
			return getAttribute(superItem, attr);
		}
		return null;
	}

	/**
	 * Compute link type id.
	 * 
	 * @param superItemType
	 *            must be a root item type (no super type)
	 */
	private static void computeLinkTypeID(Item superItemType) {
		int intID = 1;
		Set<Item> alltypes = getAllSubTypes(superItemType);
		for (Item anItemType : alltypes) {
			Item[] linktypes = getOugoingLinkTypes(anItemType);
			for (Item linktype : linktypes) {
				LinkManager.computeIntID(linktype, intID++);
			}
		}
	}

	/**
	 * Compute int id.
	 * 
	 * @param dataModel
	 *            the data model
	 */
	public static void computeIntID(Item dataModel) {
	}

	/**
	 * Return la racine de l'arbre d'heritage.
	 * 
	 * @param itemType
	 *            (cannot be null)
	 * 
	 * @return la racine de l'arbre d'heritage (cannot be null)
	 */
	public static Item getRootSuperType(Item itemType) {
		Item superItemType = itemType;
		while (true) {
			Item newSuperItemType = getSuperType(superItemType);
			if (newSuperItemType == null) {
				break;
			}
			superItemType = newSuperItemType;
		}
		return superItemType;
	}

	/**
	 * Return the generate int id.
	 * 
	 * @param itemType
	 *            an item Type object
	 * 
	 * @return the generate int id
	 */
	public static int getIntID(Item itemType) {
		return 0;
	}

	/**
	 * Gets the creation dialog.
	 * 
	 * @param itemType
	 *            the item type
	 * 
	 * @return the creation dialog
	 */
	public static Item getCreationDialog(Item itemType) {
		return itemType.getOutgoingItem(WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_CREATION_DIALOG, true);
	}

	/**
	 * Gets the modification dialog.
	 * 
	 * @param itemType
	 *            the item type
	 * 
	 * @return the modification dialog
	 */
	public static Item getModificationDialog(Item itemType) {
		return itemType.getOutgoingItem(WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_MODIFICATION_DIALOG, true);
	}

	public static String getMetaType(Item itemType) {
		// TODO change the default meta type

		return null;
	}

	public static String getItemFactoryClass(Item itemType) {
		return itemType.getAttribute(CadseRootCST.META_ITEM_TYPE_at_ITEM_FACTORY_);
	}

	public static void setItemFactoryClass(Item itemType, String value) {
		try {
			itemType.setAttribute(CadseRootCST.META_ITEM_TYPE_at_ITEM_FACTORY_, value);
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
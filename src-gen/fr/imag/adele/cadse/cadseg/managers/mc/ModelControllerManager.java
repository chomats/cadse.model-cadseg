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

package fr.imag.adele.cadse.cadseg.managers.mc;

import java.util.Set;
import java.util.UUID;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.SubFileContentManager;
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.managers.IExtendClassManager;
import fr.imag.adele.cadse.cadseg.managers.ui.DisplayManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemFactory;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.ItemFactory;
import fr.imag.adele.cadse.core.transaction.delta.ItemDelta;

/**
 * The Class ModelControllerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ModelControllerManager extends DefaultWorkspaceManager implements IItemManager, IExtendClassManager , IItemFactory{
	// public static final String EXTENDS_CLASS_ATTRIBUTE = "extends-class";


	/**
	 * The Class MyContentItem.
	 */
	public class ModelControllerContent extends SubFileContentManager implements IPDEContributor {

		/**
		 * Instantiates a new my content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		public ModelControllerContent(UUID id) throws CadseException {
			super(id);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ContentManager#generate(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.lang.String, java.lang.String, java.util.Set,
		 *      fr.imag.adele.cadse.core.GenContext)
		 */
		@Override
		public void generate(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {
			String defaultQualifiedClassName = getDefaultClassName();
			String defaultClassName = JavaIdentifier.getlastclassName(defaultQualifiedClassName);

			if ("inner-class".equals(kind)) {
				generateParts(sb, type, kind, imports, null);
				boolean extendsClass = mustBeExtended() || isExtendsClass(getOwnerItem());
				if (extendsClass) {
					Item mc = getOwnerItem();
					Item field = mc.getPartParent().getPartParent();

					String extendsClassName = defaultClassName;

					defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false, "MC");
					sb.newline();
					sb.newline().append("/**");
					sb.newline().append("	@generated");
					sb.newline().append("*/");
					sb.newline().append("static public class ").append(defaultClassName).append(" extends ").append(
							extendsClassName).append(" {");
					sb.begin();
					sb.newline();
					sb.newline().append("/**");
					sb.newline().append("	@generated");
					sb.newline().append("*/");
					sb.newline().append("public ").append(defaultClassName).append("(");
					generateConstructorParameter(sb);
					sb.decrementLength();
					sb.append(") {");
					sb.newline().append("	super(");
					generateConstrustorArguments(sb);
					sb.decrementLength();
					sb.append(");");
					sb.newline().append("}");
					sb.end();
					sb.newline();
					sb.newline().append("}");
					sb.newline();
				}
			}
			if (kind.equals("field-init")) {
				boolean extendsClass = mustBeExtended() || isExtendsClass(getOwnerItem());
				if (extendsClass) {
					Item mc = getOwnerItem();
					Item field = mc.getPartParent().getPartParent();

					defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false, "MC");
				}

				sb.newline().append(defaultClassName).append(" mc = ");

				sb.append("new ").append(defaultClassName).append("(");
				generateCallArguments(sb, imports, null);
				sb.decrementLength();
				sb.append(");");

				imports.add(defaultQualifiedClassName);
			}

		}

		/**
		 * Generate call arguments.
		 * 
		 * @param sb
		 *            the sb
		 * @param imports
		 *            the imports
		 * @param object
		 *            the object
		 */
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
			ModelControllerManager.this.generateCallArguments(getOwnerItem(), sb, imports, object);
		}

		/**
		 * Generate construstor arguments.
		 * 
		 * @param sb
		 *            the sb
		 */
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			ModelControllerManager.this.generateConstrustorArguments(getOwnerItem(), sb);
		}

		/**
		 * Generate constructor parameter.
		 * 
		 * @param sb
		 *            the sb
		 */
		protected void generateConstructorParameter(GenStringBuilder sb) {
			ModelControllerManager.this.generateConstructorParameter(getOwnerItem(), sb);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExportsPackage(java.util.Set)
		 */
		public void computeExportsPackage(Set<String> exports) {
			// TODO Auto-generated method stub

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion(org.eclipse.pde.core.plugin.IPluginBase,
		 *      org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
		 */
		public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
		 */
		public void computeImportsPackage(Set<String> imports) {
			String defaultQualifiedClassName = getDefaultClassName();
			String packageName = JavaIdentifier.packageName(defaultQualifiedClassName);
			imports.add(packageName);
		}

	}

	/**
	 * Instantiates a new model controller manager.
	 */
	public ModelControllerManager() {
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
	 * @generated
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
	 * Checks if is user controller.
	 * 
	 * @return true, if is user controller
	 */
	public boolean isUserController() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#getClassName(fr.imag.adele.cadse.core.Item)
	 */
	public String getClassName(Item mc) {
		String className = mc.getAttribute(CadseGCST.RUNTIME_ITEM_at_CLASS_NAME_);
		if (className == null) {
			className = getDefaultClassName();
		}
		return className;
	}

	/** The Constant DEFAUL_CLASS_NAME. */
	public static final String	DEFAUL_CLASS_NAME	= "fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem";

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#getDefaultClassName()
	 */
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#mustBeExtended()
	 */
	public boolean mustBeExtended() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentItem(UUID id, Item owerItem) throws CadseException {
		return new ModelControllerContent(id);
	}

	/**
	 * Checks if is extends class.
	 * 
	 * @param mc
	 *            the mc
	 * 
	 * @return true, if is extends class
	 */
	public static final boolean isExtendsClass(Item mc) {
		Item display = mc.getPartParent();
		return DisplayManager.isExtendsMCAttribute(display);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return "Cannot rename a mc";
	}

	/**
	 * Gets the field.
	 * 
	 * @param mc
	 *            the mc
	 * 
	 * @return the field
	 */
	public Item getField(Item mc) {
		Item display = mc.getPartParent();
		if (display == null) {
			return null;
		}
		return display.getPartParent();
	}

	/**
	 * Gets the attribute.
	 * 
	 * @param mc
	 *            the mc
	 * 
	 * @return the attribute
	 */
	static public Item getAttribute(Item mc) {
		Item display = mc.getPartParent();
		if (display == null) {
			return null;
		}
		Item field = display.getPartParent();
		if (field == null) {
			return null;
		}
		return FieldManager.getAttribute(field);
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
//				DisplayManager.MC_DEFAULT_NAME);
//
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "Create a default model controller",
//				"Create a default model controller", 3));
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
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "a default model controller",
//				"a default model controller", 3));
//	}

	/**
	 * Generate call arguments.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param object
	 *            the object
	 */
	protected void generateCallArguments(Item item, GenStringBuilder sb, Set<String> imports, Object object) {
	}

	/**
	 * Generate construstor arguments.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstrustorArguments(Item item, GenStringBuilder sb) {
	}

	/**
	 * Generate constructor parameter.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstructorParameter(Item item, GenStringBuilder sb) {
	}
	
	public Item newForCommitItem(LogicalWorkspace wl, ItemType it, ItemDelta item) {
		return ItemFactory.SINGLETON.newForCommitItem(wl, it, item);
	}
}

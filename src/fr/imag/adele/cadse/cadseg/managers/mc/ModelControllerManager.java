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

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.SubFileContentManager;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.model.manager.properties.FieldsCore;
import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.managers.IExtendClassManager;
import fr.imag.adele.cadse.cadseg.managers.ui.DisplayManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.var.Variable;
import java.util.Collection;
import java.util.List;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.Pages;

/**
 * The Class ModelControllerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ModelControllerManager extends DefaultWorkspaceManager implements IItemManager, IExtendClassManager {
	// public static final String EXTENDS_CLASS_ATTRIBUTE = "extends-class";

	/**
	 * The Class MyContentItem.
	 */
	public class MyContentItem extends SubFileContentManager implements IPDEContributor {

		/**
		 * Instantiates a new my content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		public MyContentItem(ContentItem parent, Item item) throws CadseException {
			super(parent, item);
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
				boolean extendsClass = mustBeExtended() || isExtendsClass(getItem());
				if (extendsClass) {
					Item mc = getItem();
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
				boolean extendsClass = mustBeExtended() || isExtendsClass(getItem());
				if (extendsClass) {
					Item mc = getItem();
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
			ModelControllerManager.this.generateCallArguments(getItem(), sb, imports, object);
		}

		/**
		 * Generate construstor arguments.
		 * 
		 * @param sb
		 *            the sb
		 */
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			ModelControllerManager.this.generateConstrustorArguments(getItem(), sb);
		}

		/**
		 * Generate constructor parameter.
		 * 
		 * @param sb
		 *            the sb
		 */
		protected void generateConstructorParameter(GenStringBuilder sb) {
			ModelControllerManager.this.generateConstructorParameter(getItem(), sb);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#init(fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public void init() {
		getItemType().setHasUniqueNameAttribute(false);
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
		String className = mc.getAttribute(CLASS_NAME);
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
	public ContentItem createContentManager(Item item) throws CadseException {
		return new MyContentItem(null, item);
	}

	/**
	 * get links '#invert_part_mc_to_DTree' from 'ModelController' to 'DTree'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_mc_to_DTreeLink(Item modelController) {
		return modelController.getOutgoingLink(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DTREE);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DTreeAll(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DTREE, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DTree(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DTREE, true);
	}

	/**
	 * set a link '#invert_part_mc_to_DTree' from 'ModelController' to 'DTree'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_mc_to_DTree(Item modelController, Item value) throws CadseException {
		modelController.setOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DTREE, value);
	}

	/**
	 * get links '#invert_part_mc_to_DList' from 'ModelController' to 'DList'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_mc_to_DListLink(Item modelController) {
		return modelController.getOutgoingLink(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DLIST);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DListAll(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DLIST, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DList(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DLIST, true);
	}

	/**
	 * set a link '#invert_part_mc_to_DList' from 'ModelController' to 'DList'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_mc_to_DList(Item modelController, Item value) throws CadseException {
		modelController.setOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DLIST, value);
	}

	/**
	 * get links '#invert_part_mc_to_DBrowser' from 'ModelController' to
	 * 'DBrowser'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_mc_to_DBrowserLink(Item modelController) {
		return modelController.getOutgoingLink(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DBROWSER);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DBrowserAll(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DBROWSER, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DBrowser(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DBROWSER, true);
	}

	/**
	 * set a link '#invert_part_mc_to_DBrowser' from 'ModelController' to
	 * 'DBrowser'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_mc_to_DBrowser(Item modelController, Item value) throws CadseException {
		modelController.setOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DBROWSER, value);
	}

	/**
	 * get links '#invert_part_mc_to_DCheckBox' from 'ModelController' to
	 * 'DCheckBox'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_mc_to_DCheckBoxLink(Item modelController) {
		return modelController.getOutgoingLink(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DCHECK_BOX);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DCheckBoxAll(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DCHECK_BOX, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DCheckBox(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DCHECK_BOX, true);
	}

	/**
	 * set a link '#invert_part_mc_to_DCheckBox' from 'ModelController' to
	 * 'DCheckBox'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_mc_to_DCheckBox(Item modelController, Item value) throws CadseException {
		modelController.setOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DCHECK_BOX, value);
	}

	/**
	 * get links '#invert_part_mc_to_DCheckedList' from 'ModelController' to
	 * 'DCheckedList'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_mc_to_DCheckedListLink(Item modelController) {
		return modelController.getOutgoingLink(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DCHECKED_LIST);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DCheckedListAll(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DCHECKED_LIST,
				false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DCheckedList(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DCHECKED_LIST,
				true);
	}

	/**
	 * set a link '#invert_part_mc_to_DCheckedList' from 'ModelController' to
	 * 'DCheckedList'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_mc_to_DCheckedList(Item modelController, Item value) throws CadseException {
		modelController.setOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DCHECKED_LIST, value);
	}

	/**
	 * get links '#invert_part_mc_to_DCombo' from 'ModelController' to 'DCombo'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_mc_to_DComboLink(Item modelController) {
		return modelController.getOutgoingLink(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DCOMBO);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DComboAll(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DCOMBO, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DCombo(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DCOMBO, true);
	}

	/**
	 * set a link '#invert_part_mc_to_DCombo' from 'ModelController' to
	 * 'DCombo'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_mc_to_DCombo(Item modelController, Item value) throws CadseException {
		modelController.setOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DCOMBO, value);
	}

	/**
	 * get links '#invert_part_mc_to_DText' from 'ModelController' to 'DText'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_mc_to_DTextLink(Item modelController) {
		return modelController.getOutgoingLink(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DTEXT);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DTextAll(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DTEXT, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_mc_to_DText(Item modelController) {
		return modelController.getOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DTEXT, true);
	}

	/**
	 * set a link '#invert_part_mc_to_DText' from 'ModelController' to 'DText'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_mc_to_DText(Item modelController, Item value) throws CadseException {
		modelController.setOutgoingItem(WorkspaceCST.MODEL_CONTROLLER_lt__$_INVERT_PART_MC_TO_DTEXT, value);
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
				DisplayManager.MC_DEFAULT_SHORT_NAME);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "Create a default model controller",
				"Create a default model controller", 3));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Pages createModificationPage(Item item) {
		AbstractActionPage action = new ModificationAction(item);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "a default model controller",
				"a default model controller", 3));
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
}

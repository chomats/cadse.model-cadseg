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

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.SubFileContentManager;
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.managers.IExtendClassManager;
import fr.imag.adele.cadse.cadseg.managers.ui.DisplayManager;
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
import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.SubFileContentManager;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fede.workspace.eclipse.java.JavaIdentifier;

/**
 * The Class InteractionControllerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class InteractionControllerManager extends DefaultWorkspaceManager implements IItemManager, IExtendClassManager {

	// public static final String EXTENDS_CLASS_ATTRIBUTE = "extends-class";
	/** The Constant DEFAUL_CLASS_NAME. */
	private static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.ic.IC_Abstract";

	/**
	 * The Class MyContentItem.
	 */
	public class MyContentItem extends SubFileContentManager implements IPDEContributor {

		/** The Constant POSTFIXE_IC. */
		private static final String	POSTFIXE_IC	= "IC";

		/**
		 * Instantiates a new my content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		protected MyContentItem(ContentItem parent, Item item) {
			super(parent, item);
		}

		/**
		 * Compute local manifest imports.
		 * 
		 * @param item
		 *            the item
		 * @param imports
		 *            the imports
		 */
		public void computeLocalManifestImports(Item item, Set<String> imports) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExportsPackage(java.util.Set)
		 */
		public void computeExportsPackage(Set<String> exports) {
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
					Item uc = getItem();
					Item field = uc.getPartParent().getPartParent();

					String extendsClassName = defaultClassName;

					defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false,
							POSTFIXE_IC);
					sb.newline();
					sb.newline().append("/**");
					sb.newline().append("	@generated");
					sb.newline().append("*/");
					sb.newline().append("static public class ").append(defaultClassName);
					if (isInterface()) {
						sb.append(" implements ");
					} else {
						sb.append(" extends ");
					}
					sb.append(extendsClassName).append(" {");
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
				Item ic = getItem();
				boolean extendsClass = mustBeExtended() || isExtendsClass(getItem());
				if (extendsClass) {
					Item field = ic.getPartParent().getPartParent();
					defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false,
							POSTFIXE_IC);
				}

				sb.newline().append(defaultClassName).append(" ic = ");

				sb.append("new ").append(defaultClassName).append("(");
				generateCallArguments(sb, imports, null);
				sb.decrementLength();
				sb.append(");");
				imports.add(defaultQualifiedClassName);

				imports.add(getDefaultClassName());
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
		}

		/**
		 * Generate construstor arguments.
		 * 
		 * @param sb
		 *            the sb
		 */
		protected void generateConstrustorArguments(GenStringBuilder sb) {
		}

		/**
		 * Generate constructor parameter.
		 * 
		 * @param sb
		 *            the sb
		 */
		protected void generateConstructorParameter(GenStringBuilder sb) {
		}

	}

	/**
	 * Instantiates a new interaction controller manager.
	 */
	public InteractionControllerManager() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#getClassName(fr.imag.adele.cadse.core.Item)
	 */
	public String getClassName(Item action) {
		return action.getAttributeWithDefaultValue(CLASS_NAME, getDefaultClassName());
	}

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

	/**
	 * Checks if is interface.
	 * 
	 * @return true, if is interface
	 */
	public boolean isInterface() {
		return false;
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
	 * get links '#invert_part_ic_to_DCombo' from 'InteractionController' to
	 * 'DCombo'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_ic_to_DComboLink(Item interactionController) {
		return interactionController
				.getOutgoingLink(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DCOMBO);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DComboAll(Item interactionController) {
		return interactionController.getOutgoingItem(
				WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DCOMBO, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DCombo(Item interactionController) {
		return interactionController.getOutgoingItem(
				WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DCOMBO, true);
	}

	/**
	 * set a link '#invert_part_ic_to_DCombo' from 'InteractionController' to
	 * 'DCombo'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_ic_to_DCombo(Item interactionController, Item value) throws CadseException {
		interactionController
				.setOutgoingItem(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DCOMBO, value);
	}

	/**
	 * get links '#invert_part_ic_to_DCheckBox' from 'InteractionController' to
	 * 'DCheckBox'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_ic_to_DCheckBoxLink(Item interactionController) {
		return interactionController
				.getOutgoingLink(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DCHECK_BOX);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DCheckBoxAll(Item interactionController) {
		return interactionController.getOutgoingItem(
				WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DCHECK_BOX, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DCheckBox(Item interactionController) {
		return interactionController.getOutgoingItem(
				WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DCHECK_BOX, true);
	}

	/**
	 * set a link '#invert_part_ic_to_DCheckBox' from 'InteractionController' to
	 * 'DCheckBox'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_ic_to_DCheckBox(Item interactionController, Item value) throws CadseException {
		interactionController.setOutgoingItem(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DCHECK_BOX,
				value);
	}

	/**
	 * get links '#invert_part_ic_to_DCheckedList' from 'InteractionController'
	 * to 'DCheckedList'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_ic_to_DCheckedListLink(Item interactionController) {
		return interactionController
				.getOutgoingLink(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DCHECKED_LIST);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DCheckedListAll(Item interactionController) {
		return interactionController.getOutgoingItem(
				WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DCHECKED_LIST, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DCheckedList(Item interactionController) {
		return interactionController.getOutgoingItem(
				WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DCHECKED_LIST, true);
	}

	/**
	 * set a link '#invert_part_ic_to_DCheckedList' from 'InteractionController'
	 * to 'DCheckedList'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_ic_to_DCheckedList(Item interactionController, Item value)
			throws CadseException {
		interactionController.setOutgoingItem(
				WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DCHECKED_LIST, value);
	}

	/**
	 * get links '#invert_part_ic_to_DTree' from 'InteractionController' to
	 * 'DTree'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_ic_to_DTreeLink(Item interactionController) {
		return interactionController.getOutgoingLink(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DTREE);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DTreeAll(Item interactionController) {
		return interactionController.getOutgoingItem(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DTREE,
				false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DTree(Item interactionController) {
		return interactionController.getOutgoingItem(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DTREE,
				true);
	}

	/**
	 * set a link '#invert_part_ic_to_DTree' from 'InteractionController' to
	 * 'DTree'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_ic_to_DTree(Item interactionController, Item value) throws CadseException {
		interactionController.setOutgoingItem(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DTREE, value);
	}

	/**
	 * get links '#invert_part_ic_to_DBrowser' from 'InteractionController' to
	 * 'DBrowser'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_ic_to_DBrowserLink(Item interactionController) {
		return interactionController
				.getOutgoingLink(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DBROWSER);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DBrowserAll(Item interactionController) {
		return interactionController.getOutgoingItem(
				WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DBROWSER, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DBrowser(Item interactionController) {
		return interactionController.getOutgoingItem(
				WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DBROWSER, true);
	}

	/**
	 * set a link '#invert_part_ic_to_DBrowser' from 'InteractionController' to
	 * 'DBrowser'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_ic_to_DBrowser(Item interactionController, Item value) throws CadseException {
		interactionController.setOutgoingItem(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DBROWSER,
				value);
	}

	/**
	 * get links '#invert_part_ic_to_DList' from 'InteractionController' to
	 * 'DList'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_ic_to_DListLink(Item interactionController) {
		return interactionController.getOutgoingLink(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DLIST);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DListAll(Item interactionController) {
		return interactionController.getOutgoingItem(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DLIST,
				false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DList(Item interactionController) {
		return interactionController.getOutgoingItem(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DLIST,
				true);
	}

	/**
	 * set a link '#invert_part_ic_to_DList' from 'InteractionController' to
	 * 'DList'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_ic_to_DList(Item interactionController, Item value) throws CadseException {
		interactionController.setOutgoingItem(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DLIST, value);
	}

	/**
	 * get links '#invert_part_ic_to_DText' from 'InteractionController' to
	 * 'DText'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_ic_to_DTextLink(Item interactionController) {
		return interactionController.getOutgoingLink(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DTEXT);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DTextAll(Item interactionController) {
		return interactionController.getOutgoingItem(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DTEXT,
				false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_ic_to_DText(Item interactionController) {
		return interactionController.getOutgoingItem(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DTEXT,
				true);
	}

	/**
	 * set a link '#invert_part_ic_to_DText' from 'InteractionController' to
	 * 'DText'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_ic_to_DText(Item interactionController, Item value) throws CadseException {
		interactionController.setOutgoingItem(WorkspaceCST.INTERACTION_CONTROLLER_lt__$_INVERT_PART_IC_TO_DTEXT, value);
	}

	/**
	 * Checks if is extends class.
	 * 
	 * @param ic
	 *            the ic
	 * 
	 * @return true, if is extends class
	 */
	public static final boolean isExtendsClass(Item ic) {

		Item display = ic.getPartParent();
		return DisplayManager.isExtendsICAttribute(display);
	}

	/**
	 * _get workspace model.
	 * 
	 * @param uc
	 *            the uc
	 * 
	 * @return the item
	 */
	public static Item _getWorkspaceModel(Item uc) {
		// TODO Auto-generated method stub
		return uc.getPartParent().getPartParent().getPartParent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return "Cannot rename a ic";
	}
}

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

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.SubFileContentManager;
import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.generate.GenerateCreationDialogController;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.CadseUtil;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.delta.ImmutableItemDelta;
import fr.imag.adele.cadse.core.delta.ImmutableWorkspaceDelta;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.ui.Pages;
import fr.imag.adele.cadse.core.util.Convert;
import java.lang.String;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.si.view.View;

/**
 * The Class CreationDialogManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class CreationDialogManager extends DefaultWorkspaceManager implements IItemManager, IModelWorkspaceManager {

	/**
	 * The Class PrivateContentManager.
	 */
	private final class PrivateContentManager extends SubFileContentManager implements IPDEContributor,
			IGenerateContent {

		/** The global action content. */
		GlobalActionContent	globalActionContent;

		/**
		 * Instantiates a new private content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		private PrivateContentManager(ContentItem parent, Item item) {
			super(parent, item);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.eclipse.content.SubFileContentManager#create()
		 */
		@Override
		public void create() throws CadseException {
			super.create();
			if (hasGlobalActionPage(getItem())) {
				createGlobalActionContent();
			}
		}

		/**
		 * Creates the global action content.
		 */
		public void createGlobalActionContent() {
			Item cadsedef = getItem().getPartParent(WorkspaceCST.CADSE_DEFINITION);
			if (cadsedef == null) {
				return;
			}
			ContentItem cm = cadsedef.getContentItem();
			globalActionContent = new GlobalActionContent(cm, getItem());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeExportsPackage(java.util.Set)
		 */
		public void computeExportsPackage(Set<String> exports) {
			if (globalActionContent != null) {
				globalActionContent.computeExportsPackage(exports);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeImportsPackage(java.util.Set)
		 */
		public void computeImportsPackage(Set<String> imports) {
			if (globalActionContent != null) {
				globalActionContent.computeImportsPackage(imports);
			}

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

			if ("inner-class".equals(kind)) {
				if (globalActionContent != null) {
					globalActionContent.generateGlobalAction(context);
				}
				generateParts(sb, type, kind, imports, new GenContext(null));
			}
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
		 * @see
		 * fr.imag.adele.cadse.core.IGenerateContent#generate(fr.imag.adele.
		 * cadse.core.var.ContextVariable)
		 */
		public void generate(ContextVariable cxt) {
			if (globalActionContent != null) {
				globalActionContent.generateGlobalAction(cxt);
			}
			generateParts(null, "", "inner-class", null, new GenContext(null));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IGenerateContent#getGenerateModel()
		 */
		public GenerateModel getGenerateModel() {
			return null;
		}

	}

	/**
	 * The Class GlobalActionContent.
	 */
	private final class GlobalActionContent extends JavaFileContentManager implements IPDEContributor {

		/**
		 * Instantiates a new global action content.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		private GlobalActionContent(ContentItem parent, Item item) {
			super(parent, item, new VariableImpl() {
				public String compute(ContextVariable context, Item itemCurrent) {
					return GenerateJavaIdentifier.javaPackageGlobalCreationActionFromCreationDialog(context,
							itemCurrent);
				}
			}, new VariableImpl() {
				public String compute(ContextVariable context, Item itemCurrent) {
					return GenerateJavaIdentifier.javaClassNameGlobalCreationActionFromCreationDialog(context,
							itemCurrent);
				}
			});
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
			imports.add("fede.workspace.model.manager.properties");
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

			if ("inner-class".equals(kind)) {
				generateGlobalAction(context);
			}
		}

		/**
		 * Generate global action.
		 * 
		 * @param cxt
		 *            the cxt
		 */
		private void generateGlobalAction(ContextVariable cxt) {
			Item dialog = getItem();
			Item itemtype = dialog.getPartParent();

			String pn = getPackageName(cxt);
			String cn = getClassName(cxt);

			boolean isAutomaticShortName = isAutomaticShortNameAttribute(dialog);
			String generateExpresion = getGenerateAutomaticShortNameAttribute(dialog);
			boolean hasGlobalAction = hasGlobalActionPage(dialog);
			if (hasGlobalAction) {
				IFile f = getFile();
				IType javatype = getJavaType(cxt);

				GenerateCreationDialogController cdc = new GenerateCreationDialogController(cxt, isAutomaticShortName,
						generateExpresion, dialog, itemtype, javatype, pn, cn);
				String content = cdc.getContent();
				try {
					EclipsePluginContentManger.generateJava(f, content, View.getDefaultMonitor());

				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
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
			// TODO Auto-generated method stub

		}

	}

	/** The Constant DEFAULT_SHORT_NAME. */
	public static final String	DEFAULT_SHORT_NAME	= "creation-dialog";

	/**
	 * Instantiates a new creation dialog manager.
	 */
	public CreationDialogManager() {
	}

	/**
	 * @generated
	 */
	@Override
	public String computeUniqueName(Item item, String name, Item parent, LinkType lt) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#init(fr.imag.adele.cadse
	 * .core.ItemType)
	 */
	@Override
	public void init() {
		WorkspaceCST.CREATION_DIALOG.setHasUniqueNameAttribute(false);
	}

	/**
	 * Gets the display name.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the display name
	 * 
	 * @generated
	 */
	@Override
	public String getDisplayName(Item item) {
		try {
			return "creation dialog";
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * get links 'pages' from 'CreationDialog' to 'Page'.
	 * 
	 * @param creationDialog
	 *            the creation dialog
	 * 
	 * @return the pages link
	 * 
	 * @generated
	 */
	static public List<Link> getPagesLink(Item creationDialog) {
		return creationDialog.getOutgoingLinks(WorkspaceCST.CREATION_DIALOG_lt_PAGES);
	}

	/**
	 * Gets the pages all.
	 * 
	 * @param creationDialog
	 *            the creation dialog
	 * 
	 * @return the pages all
	 * 
	 * @generated
	 */
	static public Collection<Item> getPagesAll(Item creationDialog) {
		return creationDialog.getOutgoingItems(WorkspaceCST.CREATION_DIALOG_lt_PAGES, false);
	}

	/**
	 * Get a page by name.
	 * 
	 * @param dialog
	 *            the dialog contains some page.
	 * 
	 * 
	 * @return the found page or null
	 */
	static public Item getPage(Item dialog, String pageName) {
		return CadseUtil.getItemByName(getPages(dialog), pageName);
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * fede.workspace.model.manager.DefaultItemManager#createCreationPages(fr
	// * .imag.adele.cadse.core.Item, fr.imag.adele.cadse.core.LinkType,
	// * fr.imag.adele.cadse.core.ItemType)
	// */
	// @Override
	// public Pages createCreationPages(Item theItemParent, LinkType
	// theLinkType, ItemType desType) {
	// return
	// CadseDefinitionManager.createCreationDialog_CreationDialog(theItemParent,
	// desType, theLinkType);
	// }

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * fede.workspace.model.manager.DefaultItemManager#createModificationPage
	// * (fr.imag.adele.cadse.core.Item)
	// */
	// @Override
	// public Pages createModificationPage(Item item) {
	// return
	// CadseDefinitionManager.createCreationDialog_ModificationDialog(item);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag
	 * .adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return CANNOT_RENAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#createContentManager(
	 * fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentManager(Item item) {
		Item cadsedef = item.getPartParent(WorkspaceCST.CADSE_DEFINITION);
		if (cadsedef == null) {
			return null;
		}
		ContentItem cm = cadsedef.getContentItem();
		return new PrivateContentManager(cm, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#validateShortName(fr.
	 * imag.adele.cadse.core.Item, java.lang.String)
	 */
	@Override
	public String validateShortName(Item futurItem, String shortName) {
		try {
			JavaIdentifier.javaIdentifierFromString(shortName, true, false, null);
		} catch (Throwable e) {
			return "must be to transform to java identifier : space and - acceppter";
		}
		return null;
	}

	/**
	 * Gets the pages.
	 * 
	 * @param creationDialog
	 *            the creation dialog
	 * 
	 * @return the pages
	 * 
	 * @generated
	 */
	static public Collection<Item> getPages(Item creationDialog) {
		return creationDialog.getOutgoingItems(WorkspaceCST.CREATION_DIALOG_lt_PAGES, true);
	}

	/**
	 * Adds the pages.
	 * 
	 * @param creationDialog
	 *            the creation dialog
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addPages(Item creationDialog, Item value) throws CadseException {
		creationDialog.addOutgoingItem(WorkspaceCST.CREATION_DIALOG_lt_PAGES, value);
	}

	/**
	 * Removes the pages.
	 * 
	 * @param creationDialog
	 *            the creation dialog
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removePages(Item creationDialog, Item value) throws CadseException {
		creationDialog.removeOutgoingItem(WorkspaceCST.CREATION_DIALOG_lt_PAGES, value);
	}

	/**
	 * Gets the default short name attribute.
	 * 
	 * @param creationDialog
	 *            the creation dialog
	 * 
	 * @return the default short name attribute
	 * 
	 * @not generated
	 */
	public static final String getDefaultShortNameAttribute(Item creationDialog) {
		String gv = getGenerateAutomaticShortNameAttribute(creationDialog);
		if (gv != null&& gv.contains("${"))
			return null;
		return gv;

	}

	/**
	 * Sets the default short name attribute.
	 * 
	 * @param creationDialog
	 *            the creation dialog
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setDefaultShortNameAttribute(Item creationDialog, String value) {
		try {
			creationDialog.setAttribute(WorkspaceCST.CREATION_DIALOG_at_DEFAULT_SHORT_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is automatic short name attribute.
	 * 
	 * @param creationDialog
	 *            the creation dialog
	 * 
	 * @return true, if checks if is automatic short name attribute
	 * 
	 * @generated
	 */
	public static final boolean isAutomaticShortNameAttribute(Item creationDialog) {
		return creationDialog
				.getAttributeWithDefaultValue(WorkspaceCST.CREATION_DIALOG_at_AUTOMATIC_SHORT_NAME_, false);
	}

	/**
	 * Sets the automatic short name attribute.
	 * 
	 * @param creationDialog
	 *            the creation dialog
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setAutomaticShortNameAttribute(Item creationDialog, boolean value) {
		try {
			creationDialog.setAttribute(WorkspaceCST.CREATION_DIALOG_at_AUTOMATIC_SHORT_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is extends dialog controller attribute.
	 * 
	 * @param creationDialog
	 *            the creation dialog
	 * 
	 * @return true, if checks if is extends dialog controller attribute
	 * 
	 * @generated
	 */
	public static final boolean isExtendsDialogControllerAttribute(Item creationDialog) {
		return creationDialog.getAttributeWithDefaultValue(WorkspaceCST.CREATION_DIALOG_at_EXTENDS_DIALOG_CONTROLLER_,
				false);
	}

	/**
	 * Sets the extends dialog controller attribute.
	 * 
	 * @param creationDialog
	 *            the creation dialog
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setExtendsDialogControllerAttribute(Item creationDialog, boolean value) {
		try {
			creationDialog.setAttribute(WorkspaceCST.CREATION_DIALOG_at_EXTENDS_DIALOG_CONTROLLER_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the generate automatic short name attribute.
	 * 
	 * @param creationDialog
	 *            the creation dialog
	 * 
	 * @return the generate automatic short name attribute
	 * 
	 * @not generated
	 */
	public static final String getGenerateAutomaticShortNameAttribute(Item creationDialog) {
		Object value = creationDialog.getAttribute(WorkspaceCST.CREATION_DIALOG_at_GENERATE_AUTOMATIC_SHORT_NAME);
		if (value == null) {
			return null;
		}

		try {
			String retvalue = (String) value;

			if (retvalue.length() == 0) {
				return null;
			}

			return retvalue;
		} catch (Throwable t) {
			return null;
		}

	}

	/**
	 * Sets the generate automatic short name attribute.
	 * 
	 * @param creationDialog
	 *            the creation dialog
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setGenerateAutomaticShortNameAttribute(Item creationDialog, String value) {
		try {
			creationDialog.setAttribute(WorkspaceCST.CREATION_DIALOG_at_GENERATE_AUTOMATIC_SHORT_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * get links '#invert_part_creation-dialog_to_AbstractItemType' from
	 * 'CreationDialog' to 'AbstractItemType'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_creationDialog_to_AbstractItemTypeLink(Item creationDialog) {
		return creationDialog
				.getOutgoingLink(WorkspaceCST.CREATION_DIALOG_lt__$_INVERT_PART_CREATION_DIALOG_TO_ABSTRACT_ITEM_TYPE);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_creationDialog_to_AbstractItemTypeAll(Item creationDialog) {
		return creationDialog.getOutgoingItem(
				WorkspaceCST.CREATION_DIALOG_lt__$_INVERT_PART_CREATION_DIALOG_TO_ABSTRACT_ITEM_TYPE, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_creationDialog_to_AbstractItemType(Item creationDialog) {
		return creationDialog.getOutgoingItem(
				WorkspaceCST.CREATION_DIALOG_lt__$_INVERT_PART_CREATION_DIALOG_TO_ABSTRACT_ITEM_TYPE, true);
	}

	/**
	 * set a link '#invert_part_creation-dialog_to_AbstractItemType' from
	 * 'CreationDialog' to 'AbstractItemType'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_creationDialog_to_AbstractItemType(Item creationDialog, Item value)
			throws CadseException {
		creationDialog.setOutgoingItem(
				WorkspaceCST.CREATION_DIALOG_lt__$_INVERT_PART_CREATION_DIALOG_TO_ABSTRACT_ITEM_TYPE, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#isOutgoingLinkSorted()
	 */
	@Override
	public boolean isOutgoingLinkSorted() {
		return false;
	}

	/**
	 * Checks for global action page.
	 * 
	 * @param dialog
	 *            the dialog
	 * 
	 * @return true, if successful
	 */
	public static boolean hasGlobalActionPage(Item dialog) {
		boolean isExtendGlobalAction = isExtendsDialogControllerAttribute(dialog);
		boolean isAutomaticShortName = isAutomaticShortNameAttribute(dialog);
		String generateExpresion = getGenerateAutomaticShortNameAttribute(dialog);
		if (generateExpresion!= null && !(generateExpresion.contains("${"))) {
			generateExpresion = null;
		}
		boolean hasGlobalAction = isExtendGlobalAction || isAutomaticShortName
				|| (generateExpresion != null && generateExpresion.length() != 0);
		return hasGlobalAction;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#notifie(fr.imag.adele
	 * .cadse.core.Item, fr.imag.adele.cadse.core.WorkspaceDelta)
	 */
	@Override
	public void notifie(Item item, ImmutableWorkspaceDelta wd) throws CadseException {
		ImmutableItemDelta itemDela = wd.getItem(item);
		if (itemDela != null && !itemDela.isDeleted()) {
			PrivateContentManager cm = (PrivateContentManager) item.getContentItem();
			boolean hasGlobalAction = hasGlobalActionPage(item);
			if (cm.globalActionContent == null && hasGlobalAction) {
				cm.createGlobalActionContent();
			} else if (cm.globalActionContent != null && !hasGlobalAction) {
				cm.globalActionContent = null;
				cm = null;
				regenerateCadseDefinitionModel(item);
				return;
			}
			if (cm.globalActionContent != null) {
				cm.globalActionContent.generateGlobalAction(ContextVariable.DEFAULT);
				regenerateCadseDefinitionModel(item);
			}
		}
	}

	/**
	 * regnerate cadse model
	 * 
	 * @param item
	 */
	private void regenerateCadseDefinitionModel(Item item) {
		// regnerate cadse model
		Item it = item.getPartParent();
		Item wm = ItemTypeManager.getCadseDefinition(it);
		IGenerateContent wmcm = ((IGenerateContent) wm.getContentItem());
		if (wmcm != null) {
			wmcm.generate(ContextVariable.DEFAULT);
		}
	}

}

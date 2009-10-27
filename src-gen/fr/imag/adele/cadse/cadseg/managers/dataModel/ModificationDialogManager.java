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

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.SubFileContentManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseUtil;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class ModificationDialogManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ModificationDialogManager extends DefaultItemManager {

	public static final String	DEFAULT_NAME_PAGE	= "modification-page1";

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ModificationDialogManager() {
		super();
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
			return "modification dialog";
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * get links 'pages' from 'ModificationDialog' to 'Page'.
	 * 
	 * @param modificationDialog
	 *            the modification dialog
	 * 
	 * @return the pages link
	 * 
	 * @generated
	 */
	static public List<Link> getPagesLink(Item modificationDialog) {
        return modificationDialog.getOutgoingLinks(CadseGCST.MODIFICATION_DIALOG_lt_PAGES);
    }

	/**
	 * Gets the pages all.
	 * 
	 * @param modificationDialog
	 *            the modification dialog
	 * 
	 * @return the pages all
	 * 
	 * @generated
	 */
	static public Collection<Item> getPagesAll(Item modificationDialog) {
        return modificationDialog.getOutgoingItems(CadseGCST.MODIFICATION_DIALOG_lt_PAGES, false);
    }

	/**
	 * The Class PrivateContentManager.
	 */
	private final class PrivateContentManager extends SubFileContentManager implements IPDEContributor,
			IGenerateContent {

		/**
		 * Instantiates a new private content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		private PrivateContentManager(CompactUUID id) {
			super(id);
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
			if ("inner-class".equals(kind)) {
				generateParts(sb, type, kind, imports, context);
			}
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
		 * @see fr.imag.adele.cadse.core.IGenerateContent#generate(fr.imag.adele.cadse.core.var.ContextVariable)
		 */
		public void generate(ContextVariable cxt) {
			generateParts(null, "", "inner-class", null, new GenContext(null));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IGenerateContent#getGenerateModel()
		 */
		public GenerateModel getGenerateModel() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	/** The Constant DEFAULT_SHORT_NAME. */
	public static final String	DEFAULT_SHORT_NAME	= "modification-dialog";

	/**
	 * Gets the pages.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return the pages
	 */
	static public Collection<Item> getPages(Item page) {
		return page.getOutgoingItems(CadseGCST.MODIFICATION_DIALOG_lt_PAGES, true);
	}

	/**
	 * Gets the pages.
	 * 
	 * @param _page
	 *            the page
	 * 
	 * @return the pages
	 */
	static public Item getPage(Item dialog, String pageName) {
		return CadseUtil.getItemByName(getPages(dialog), pageName);
	}

	/**
	 * Adds the pages.
	 * 
	 * @param modificationDialog
	 *            the modification dialog
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addPages(Item modificationDialog, Item value) throws CadseException {
        modificationDialog.addOutgoingItem(CadseGCST.MODIFICATION_DIALOG_lt_PAGES,value);
    }

	/**
	 * Removes the pages.
	 * 
	 * @param modificationDialog
	 *            the modification dialog
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removePages(Item modificationDialog, Item value) throws CadseException {
        modificationDialog.removeOutgoingItem(CadseGCST.MODIFICATION_DIALOG_lt_PAGES,value);
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
	 * @see fede.workspace.model.manager.DefaultItemManager#init(fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public void init() {
		CadseGCST.MODIFICATION_DIALOG.setHasQualifiedNameAttribute(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentItem(CompactUUID id) throws CadseException {
		return new PrivateContentManager(id);
	}

	/**
	 * Checks if is show internal attribute.
	 * 
	 * @param modificationDialog
	 *            the modification dialog
	 * 
	 * @return true, if is show internal attribute
	 */
	public static boolean isShowInternalAttribute(Item modificationDialog) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#isOutgoingLinkSorted()
	 */
	@Override
	public boolean isOutgoingLinkSorted() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return CANNOT_RENAME;
	}

}

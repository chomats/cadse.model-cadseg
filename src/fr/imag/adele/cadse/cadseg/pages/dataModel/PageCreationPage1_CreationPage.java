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

package fr.imag.adele.cadse.cadseg.pages.dataModel;

import java.util.Arrays;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ViewerFilter;

import fr.imag.adele.cadse.cadseg.ItemLabelProvider;
import fr.imag.adele.cadse.cadseg.ItemShortNameComparator;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.cadseg.managers.dataModel.AbstractItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForBrowserOrCombo;
import fede.workspace.model.manager.properties.impl.ic.IC_AbstractTreeDialogForList_Browser_Combo;
import fede.workspace.model.manager.properties.impl.ic.ItemTreeContentProvider;
import fede.workspace.model.manager.properties.impl.mc.IntModelController;
import fede.workspace.model.manager.properties.impl.mc.MC_ShortNameItemProperty;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.core.CadseRootCST;

/**
 * The Class PageCreationPage1_CreationPageFactory.
 * 
 * @generated
 */
public class PageCreationPage1_CreationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item		parent;

	/**
	 * @generated
	 */
	public ItemType	it;

	/**
	 * @generated
	 */
	public LinkType	lt;

	/**
	 * The Class IC_SelectPages.
	 */
	@SuppressWarnings("deprecation")
	private static final class IC_SelectPages extends IC_AbstractTreeDialogForList_Browser_Combo {

		/** The dialog. */
		Item	dialog;

		/**
		 * Instantiates a new i c_ select pages.
		 * 
		 * @param dialog
		 *            the dialog
		 * @param title
		 *            the title
		 * @param message
		 *            the message
		 */
		private IC_SelectPages(Item dialog, String title, String message) {
			super(title, message);
			this.dialog = dialog;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_AbstractTreeDialogForList_Browser_Combo#getFilter()
		 */
		@Override
		protected ViewerFilter getFilter() {
			// TODO Auto-generated method stub
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_AbstractTreeDialogForList_Browser_Combo#getInputValues()
		 */
		@Override
		protected Object getInputValues() {
			Item theItemType = dialog.getPartParent();
			Item[] ret = ItemTypeManager
					.getAllSuperTypes(AbstractItemTypeManager.getSuperAbstractItemType(theItemType));
			Arrays.sort(ret, new ItemShortNameComparator());
			return ret;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.IInteractionControllerForList#getLabelProvider()
		 */
		public ILabelProvider getLabelProvider() {
			return new ItemLabelProvider();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_AbstractTreeDialogForList_Browser_Combo#getTreeContentProvider()
		 */
		@Override
		protected ITreeContentProvider getTreeContentProvider() {
			return (dialog.getType() == WorkspaceCST.CREATION_DIALOG) ? new ItemTreeContentProvider(
					new ItemShortNameComparator(), WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_CREATION_DIALOG,
					WorkspaceCST.CREATION_DIALOG_lt_PAGES) : new ItemTreeContentProvider(new ItemShortNameComparator(),
					WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_MODIFICATION_DIALOG, WorkspaceCST.MODIFICATION_DIALOG_lt_PAGES);
		}

		// {context <-[parent-part]- -[item-types] ->} - {context} -
		// {context<-[super-type]-*}
		// @Override
		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.IInteractionControllerForBrowserOrCombo#getValues()
		 */
		public Object[] getValues() {
			Item theItemType = dialog.getPartParent();
			return (dialog.getType() == WorkspaceCST.CREATION_DIALOG) ? ItemTypeManager
					.getAllCreationPages(theItemType) : ItemTypeManager.getAllModificationPages(theItemType);

		}

		// @Override
		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.IInteractionControllerForBrowserOrCombo#toString(java.lang.Object)
		 */
		public String toString(Object value) {
			if (value == null) {
				return "";
			}
			return value.toString();
		}

		@Override
		protected Object createGoodObject(Object object) {
			return ((Item) object).getName();
		}

		// @Override
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.dialogs.ISelectionStatusValidator#validate(java.lang.Object[])
		 */
		public IStatus validate(Object[] selection) {
			if (selection != null && selection.length == 1) {
				Object sel = selection[0];
				if (sel instanceof Item) {
					if (((Item) sel).getType() == WorkspaceCST.PAGE) {

						Item superpage = (Item) sel;

						IItemManager im = getItem().getType().getItemManager();
						String message = im.validateShortName(getItem(), superpage.getName());
						if (message != null) {
							return new Status(Status.ERROR, WSPlugin.PLUGIN_ID, message);
						}
						return Status.OK_STATUS;
					}
				}
			}

			return new Status(Status.ERROR, WSPlugin.PLUGIN_ID, "select a page");
		}

		@Override
		public Object fromString(String value) {
			return value;
		}

		@Override
		public boolean hasDeleteFunction() {
			return false;
		}

		public ItemType getType() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	/**
	 * The __short_name__.
	 * 
	 */
	protected DTextUI		__short_name__;

	/**
	 * The field create page action.
	 * 
	 * @generated
	 */
	protected DCheckBoxUI	fieldCreatePageAction;

	/**
	 * The field description.
	 * 
	 * @generated
	 */
	protected DTextUI		fieldDescription;

	/**
	 * The field hspan.
	 * 
	 * @generated
	 */
	protected DTextUI		fieldHspan;

	/**
	 * The field title.
	 * 
	 * @generated
	 */
	protected DTextUI		fieldTitle;

	/**
	 * @generated
	 */
	public PageCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create Page", "Create Page", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.__short_name__ = createInternalNameField();
		this.fieldTitle = createFieldTitle();
		this.fieldDescription = createFieldDescription();
		this.fieldHspan = createFieldHspan();
		this.fieldCreatePageAction = createFieldCreatePageAction();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldTitle, this.fieldDescription, this.fieldHspan,
				this.fieldCreatePageAction);

		registerListener();
	}

	/**
	 * @generated
	 */
	protected PageCreationPage1_CreationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * Creates the field create page action.
	 * 
	 * @return the d check box ui
	 * 
	 * @generated
	 */
	public DCheckBoxUI createFieldCreatePageAction() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.PAGE_at_CREATE_PAGE_ACTION, "create page action", EPosLabel.none, mc, null);
	}

	/**
	 * Creates the field description.
	 * 
	 * @return the d text ui
	 * 
	 * @generated
	 */
	public DTextUI createFieldDescription() {
		return new DTextUI(WorkspaceCST.PAGE_at_DESCRIPTION, "description", EPosLabel.left, new MC_AttributesItem(),
				null, 1, "", false, false, false);
	}

	/**
	 * Creates the field hspan.
	 * 
	 * @return the d text ui
	 * 
	 * @generated
	 */
	public DTextUI createFieldHspan() {
		IntModelController mc = new IntModelController(1, 0, "The number of column must be > 0", null, 3);
		return new DTextUI(WorkspaceCST.PAGE_at_HSPAN, "number of columns", EPosLabel.left, mc, null, 1, "", false,
				false, false);
	}

	/**
	 * Creates the field title.
	 * 
	 * @return the d text ui
	 * 
	 * @generated
	 */
	public DTextUI createFieldTitle() {
		return new DTextUI(WorkspaceCST.PAGE_at_TITLE, "title", EPosLabel.left, new MC_AttributesItem(), null, 1, "",
				false, false, false);
	}

	/**
	 * Creates a new PageCreationPage1_CreationPage object.
	 * 
	 * @param dialog
	 *            the dialog
	 * 
	 * @return the d browser ui
	 */
	private DBrowserUI createShortName(Item dialog) {
		IInteractionControllerForBrowserOrCombo ic = new IC_SelectPages(dialog, "select a page", "select a page");
		return FieldsCore.createBrowserField(CadseRootCST.ITEM_TYPE_at_NAME, "name:", EPosLabel.left, ic,
				new MC_ShortNameItemProperty());
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createInternalNameField() {
		return FieldsCore.createShortNameField();
	}

}

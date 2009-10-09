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

package fr.imag.adele.cadse.cadseg.managers.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardDialog;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ic.IC_AbstractForChecked;
import fede.workspace.model.manager.properties.impl.ic.IC_TreeCheckedUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckedTreeUI;
import fede.workspace.model.manager.properties.impl.ui.WizardController;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IInteractionController;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * The Class ItemTypeViewAction.
 * 
 * @author chomats
 */
public class ItemTypeViewAction extends IMenuAction {

	/** The manager. */
	private final ViewManager	manager;

	/**
	 * The Class DataModelViewWizardController.
	 */
	class DataModelViewWizardController extends AbstractActionPage implements IModelController {

		/**
		 * Instantiates a new data model view wizard controller.
		 */
		public DataModelViewWizardController() {
		}

		/** The ic. */
		private IInteractionController	ic;

		/** The ui. */
		private UIField					ui;

		/**
		 * Gets the interaction controller.
		 * 
		 * @return the interaction controller
		 */
		public IInteractionController getInteractionController() {
			return ic;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#getUIField()
		 */
		public UIField getUIField() {
			return ui;
		}

		/**
		 * Sets the interaction controller.
		 * 
		 * @param ic
		 *            the new interaction controller
		 */
		public void setInteractionController(IInteractionController ic) {
			this.ic = ic;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#setUIField(fr.imag.adele.cadse.core.ui.UIField)
		 */
		public void setUIField(UIField ui) {
			this.ui = ui;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#defaultValue()
		 */
		public Object defaultValue() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.impl.ui.AbstractActionPage#dispose()
		 */
		@Override
		public void dispose() {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#getValue()
		 */
		public Object getValue() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#initAfterUI()
		 */
		@Override
		public void initAfterUI() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieSubValueAdded(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public void notifieSubValueAdded(UIField field, Object added) {
			try {
				if (added instanceof Object[]) {
					Object[] arrayadded = (Object[]) added;
					for (Object obj : arrayadded) {
						addItem((Item) obj);
					}
				} else {
					addItem((Item) added);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieSubValueRemoved(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public void notifieSubValueRemoved(UIField field, Object removed) {
			try {
				if (removed instanceof Object[]) {
					Object[] arrayremoved = (Object[]) removed;
					for (Object obj : arrayremoved) {
						removeItem((Item) obj);
					}
				} else {
					removeItem((Item) removed);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieValueChanged(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public void notifieValueChanged(UIField field, Object value) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieValueDeleted(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public void notifieValueDeleted(UIField field, Object oldvalue) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.impl.ui.AbstractActionPage#doFinish(java.lang.Object)
		 */
		@Override
		public void doFinish(Object monitor) throws Exception {
			Object[] selected = (Object[]) checkedTreeUI.getVisualValue();
			for (Object so : selected) {
				System.out.println(so.toString());
				Item dest = (Item) so;
				ItemType type = dest.getType();
				if (type == WorkspaceCST.ITEM_TYPE) {
					ViewManager.createViewItemType(view, dest);
				}
			}
			for (Object so : selected) {
				Item dest = (Item) so;
				ItemType type = dest.getType();
				if (type == WorkspaceCST.LINK) {
					ViewManager.createViewLinkType(view, dest);
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#init()
		 */
		public void init() throws CadseException {
			// TODO Auto-generated method stub

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#init(fr.imag.adele.cadse.core.ui.UIField)
		 */
		public void init(UIField field) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IValidateContributor#validSubValueAdded(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public boolean validSubValueAdded(UIField field, Object added) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IValidateContributor#validSubValueRemoved(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public boolean validSubValueRemoved(UIField field, Object removed) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IValidateContributor#validValue(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public boolean validValue(UIField field, Object value) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IValidateContributor#validValueChanged(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public boolean validValueChanged(UIField field, Object value) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IValidateContributor#validValueDeleted(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public boolean validValueDeleted(UIField field, Object removed) {
			return false;
		}

		public boolean isAnonymous() {
			// TODO Auto-generated method stub
			return false;
		}
	}

	/**
	 * The Class IC_DataModelView.
	 */
	class IC_DataModelView extends IC_AbstractForChecked implements IC_TreeCheckedUI {

		/**
		 * Instantiates a new i c_ data model view.
		 */
		public IC_DataModelView() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.IC_ForCheckedViewer#getSources()
		 */
		public Object[] getSources() {
			return new Object[] { itemtype };
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_TreeCheckedUI#getChildren(java.lang.Object)
		 */
		public Object[] getChildren(Object obj) {
			Item item = (Item) obj;
			List<Item> ret = new ArrayList<Item>();
			for (Link l : item.getOutgoingLinks()) {
				if (!l.isLinkResolved()) {
					continue;
				}

				if (!l.isPart()) {
					continue;
				}

				Item dest = l.getResolvedDestination();
				ItemType type = dest.getType();
				if (type == WorkspaceCST.ITEM_TYPE || type == WorkspaceCST.DATA_MODEL || type == WorkspaceCST.LINK) {
					ret.add(dest);
				}
			}

			return ret.toArray(new Item[ret.size()]);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_TreeCheckedUI#getParent(java.lang.Object)
		 */
		public Object getParent(Object item) {
			return ((Item) item).getPartParent();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_TreeCheckedUI#canObjectDeselected(java.lang.Object)
		 */
		public String canObjectDeselected(Object object) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_TreeCheckedUI#canObjectSelected(java.lang.Object)
		 */
		public String canObjectSelected(Object object) {
			return null;
		}

		public ItemType getType() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	/** The itemtype. */
	Item			itemtype;

	/** The view. */
	Item			view;

	/** The checked tree ui. */
	DCheckedTreeUI	checkedTreeUI;

	/**
	 * Instantiates a new item type view action.
	 * 
	 * @param manager
	 *            the manager
	 * @param view
	 *            the view
	 * @param itemtype
	 *            the itemtype
	 */
	public ItemTypeViewAction(ViewManager manager, Item view, Item itemtype) {
		this.manager = manager;
		this.itemtype = itemtype;
		this.view = view;
	}

	/**
	 * Removes the item.
	 * 
	 * @param item
	 *            the item
	 */
	public void removeItem(Item item) {
		if (item.getType() == WorkspaceCST.ITEM_TYPE) {
			selectAnItemType(item, false);
		} else {
			if (item.getType() == WorkspaceCST.DATA_MODEL) {
				selectAnDataModel(item, false);
			}
		}
	}

	/**
	 * Select an data model.
	 * 
	 * @param item
	 *            the item
	 * @param sel
	 *            the sel
	 */
	private void selectAnDataModel(Item item, boolean sel) {
		for (Link l : item.getOutgoingLinks()) {
			if (!l.isLinkResolved()) {
				continue;
			}

			if (!l.isPart()) {
				continue;
			}

			Item dest = l.getResolvedDestination();
			ItemType type = dest.getType();
			if (type == WorkspaceCST.LINK) {
				checkedTreeUI.selectObject(dest, sel);
			} else if (type == WorkspaceCST.ITEM_TYPE) {
				checkedTreeUI.selectObject(dest, sel);
				selectAnItemType(dest, sel);
			} else if (type == WorkspaceCST.DATA_MODEL) {
				checkedTreeUI.selectObject(dest, sel);
				selectAnDataModel(dest, sel);
			}
		}
	}

	/**
	 * Adds the item.
	 * 
	 * @param item
	 *            the item
	 */
	public void addItem(Item item) {
		if (item.getType() == WorkspaceCST.ITEM_TYPE) {
			selectAnItemType(item, true);
		} else if (item.getType() == WorkspaceCST.DATA_MODEL) {
			selectAnDataModel(item, true);
		} else if (item.getType() == WorkspaceCST.LINK) {
			checkedTreeUI.selectObject(item.getPartParent(), true);
		}
	}

	/**
	 * Select an item type.
	 * 
	 * @param item
	 *            the item
	 * @param sel
	 *            the sel
	 */
	protected void selectAnItemType(Item item, boolean sel) {
		for (Link l : item.getOutgoingLinks()) {
			if (!l.isLinkResolved()) {
				continue;
			}

			if (!l.isPart()) {
				continue;
			}

			Item dest = l.getResolvedDestination();
			ItemType type = dest.getType();
			if (type == WorkspaceCST.LINK) {
				checkedTreeUI.selectObject(dest, sel);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#run(fr.imag.adele.cadse.core.IItemNode[])
	 */
	@Override
	public void run(IItemNode[] selection) throws CadseException {
		try {
			DataModelViewWizardController action = new DataModelViewWizardController();
			checkedTreeUI = new DCheckedTreeUI("sel", "non", EPosLabel.none, action, new IC_DataModelView(), true,
					false);
			WizardController wc = new WizardController(FieldsCore.createWizard(action, FieldsCore.createPage("page1",
					"Select Item Type and Link", "Select Item Type and Link for this view", 3, checkedTreeUI)));
			WizardDialog wd = new WizardDialog(null, wc);
			wd.setPageSize(300, 500);
			wd.open();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#getImage()
	 */
	@Override
	public URL getImage() {
		return itemtype.getType().getImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#getLabel()
	 */
	@Override
	public String getLabel() {
		return "Add item type " + itemtype.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#isSeparator()
	 */
	@Override
	public boolean isSeparator() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#getMenuPath()
	 */
	@Override
	public String getMenuPath() {
		return NEW_MENU;
	}

}
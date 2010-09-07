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
package fr.imag.adele.cadse.cadseg.managers.view;

import java.util.ArrayList;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.AbstractModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.RuningInteractionController;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_AbstractForChecked;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_TreeCheckedUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckedTreeUI;

/**
 * The Class ItemTypeViewAction.
 * 
 * @author chomats
 */
public class ItemTypeViewAction extends IMenuAction {

	/** The manager. */
	private final ViewManager	manager;

	class DataModelMC extends AbstractModelController {
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
	}
	/**
	 * The Class DataModelViewWizardController.
	 */
	class DataModelViewWizardController extends AbstractActionPage   {

		/**
		 * Instantiates a new data model view wizard controller.
		 */
		public DataModelViewWizardController() {
		}

		/** The ic. */
		private RuningInteractionController	ic;

		/** The ui. */
		private UIField					ui;

		/**
		 * Gets the interaction controller.
		 * 
		 * @return the interaction controller
		 */
		public RuningInteractionController getInteractionController() {
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
		public void setInteractionController(RuningInteractionController ic) {
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
		 * @see fr.imag.adele.cadse.core.impl.ui.AbstractActionPage#doFinish(java.lang.Object)
		 */
		@Override
		public void doFinish(UIPlatform uiPlatform, Object monitor) throws Exception {
			Object[] selected = (Object[]) checkedTreeUI.getVisualValue();
			for (Object so : selected) {
				System.out.println(so.toString());
				Item dest = (Item) so;
				ItemType type = dest.getType();
				if (type == CadseGCST.ITEM_TYPE) {
					ViewManager.createViewItemType(view, dest);
				}
			}
			for (Object so : selected) {
				Item dest = (Item) so;
				ItemType type = dest.getType();
				if (type == CadseGCST.LINK_TYPE) {
					ViewManager.createViewLinkType(view, dest);
				}
			}
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

				if (!l.getLinkType().isPart()) {
					continue;
				}

				Item dest = l.getResolvedDestination();
				ItemType type = dest.getType();
				if (type == CadseGCST.ITEM_TYPE || type == CadseGCST.DATA_MODEL || type == CadseGCST.LINK_TYPE) {
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

	private IPage	_page;

	private SWTUIPlatform	_uiPlatform;

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
		if (item.getType() == CadseGCST.ITEM_TYPE) {
			selectAnItemType(item, false);
		} else {
			if (item.getType() == CadseGCST.DATA_MODEL) {
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

			if (!l.getLinkType().isPart()) {
				continue;
			}

			Item dest = l.getResolvedDestination();
			ItemType type = dest.getType();
			if (type == CadseGCST.LINK_TYPE) {
				checkedTreeUI.selectObject(dest, sel);
			} else if (type == CadseGCST.ITEM_TYPE) {
				checkedTreeUI.selectObject(dest, sel);
				selectAnItemType(dest, sel);
			} else if (type == CadseGCST.DATA_MODEL) {
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
		if (item.getType() == CadseGCST.ITEM_TYPE) {
			selectAnItemType(item, true);
		} else if (item.getType() == CadseGCST.DATA_MODEL) {
			selectAnDataModel(item, true);
		} else if (item.getType() == CadseGCST.LINK_TYPE) {
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

			if (!l.getLinkType().isPart()) {
				continue;
			}

			Item dest = l.getResolvedDestination();
			ItemType type = dest.getType();
			if (type == CadseGCST.LINK_TYPE) {
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
			_uiPlatform = new SWTUIPlatform();
			_page = _uiPlatform.createPageDescription("Select Item Type and Link", "Select Item Type and Link for this view");
			checkedTreeUI = _uiPlatform.createDCheckedTreeUI(_page, "#sel", "", EPosLabel.none, new DataModelMC(), new IC_DataModelView(), true, false);
			_uiPlatform.open(null, _page, action, false);
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
	public String getImage() {
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

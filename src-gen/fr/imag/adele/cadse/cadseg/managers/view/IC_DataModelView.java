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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.WorkspaceListener;
import fr.imag.adele.cadse.core.delta.ImmutableItemDelta;
import fr.imag.adele.cadse.core.delta.ImmutableWorkspaceDelta;
import fr.imag.adele.cadse.core.ui.RunningModelController;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_AbstractForChecked;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_ContextMenu;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_TreeCheckedUI;

/**
 * The Class IC_DataModelView.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_DataModelView extends IC_AbstractForChecked implements IC_TreeCheckedUI, IC_ContextMenu,
		RunningModelController {

	/**
	 * The Class RootAction.
	 */
	static public class RootAction extends Action {

		/** The itemtype. */
		private Item				itemtype;

		/** The view. */
		private Item				view;

		/** The viewitemtype. */
		private Item				viewitemtype;

		/** The ic. */
		private IC_DataModelView	ic;

		/**
		 * Instantiates a new root action.
		 * 
		 * @param ic
		 *            the ic
		 * @param itemsel
		 *            the itemsel
		 * @param viewitemtype
		 *            the viewitemtype
		 * @param view
		 *            the view
		 */
		public RootAction(IC_DataModelView ic, Item itemsel, Item viewitemtype, Item view) {
			super("is first element", AS_CHECK_BOX);
			this.itemtype = itemsel;
			this.view = view;
			this.viewitemtype = viewitemtype;
			this.ic = ic;
			setChecked(ic.isFirstElementChecked(itemtype, this.viewitemtype));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			ic.setIsFirstElementChecked(itemtype, viewitemtype, isChecked());
		}

	}

	/**
	 * The Class CanCreationItemAction.
	 */
	static public class CanCreationItemAction extends Action {

		/** The linktype. */
		private Item				linktype;

		/** The view. */
		private Item				view;

		/** The viewlinktype. */
		private Item				viewlinktype;

		/** The ic. */
		private IC_DataModelView	ic;

		/**
		 * Instantiates a new can creation item action.
		 * 
		 * @param ic
		 *            the ic
		 * @param itemsel
		 *            the itemsel
		 * @param viewlinktype
		 *            the viewlinktype
		 * @param view
		 *            the view
		 */
		public CanCreationItemAction(IC_DataModelView ic, Item itemsel, Item viewlinktype, Item view) {
			super("can create item", AS_CHECK_BOX);
			this.linktype = itemsel;
			this.view = view;
			this.viewlinktype = viewlinktype;
			this.ic = ic;
			setChecked(ic.isCanCreateItemChecked(linktype, this.viewlinktype));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			ic.setCanCreateItemChecked(linktype, viewlinktype, isChecked());
		}

	}

	/**
	 * The Class CanCreationLinkAction.
	 */
	static public class CanCreationLinkAction extends Action {

		/** The linktype. */
		private Item				linktype;

		/** The view. */
		private Item				view;

		/** The viewlinktype. */
		private Item				viewlinktype;

		/** The ic. */
		private IC_DataModelView	ic;

		/**
		 * Instantiates a new can creation link action.
		 * 
		 * @param ic
		 *            the ic
		 * @param itemsel
		 *            the itemsel
		 * @param viewlinktype
		 *            the viewlinktype
		 * @param view
		 *            the view
		 */
		public CanCreationLinkAction(IC_DataModelView ic, Item itemsel, Item viewlinktype, Item view) {
			super("can create link", AS_CHECK_BOX);
			this.linktype = itemsel;
			this.view = view;
			this.viewlinktype = viewlinktype;
			this.ic = ic;
			setChecked(ic.isCanCreateLinkChecked(linktype, this.viewlinktype));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			ic.setCanCreateLinkChecked(linktype, viewlinktype, isChecked());
		}

	}

	/**
	 * The Class AggregationAction.
	 */
	static public class AggregationAction extends Action {

		/** The linktype. */
		private Item				linktype;

		/** The view. */
		private Item				view;

		/** The viewlinktype. */
		private Item				viewlinktype;

		/** The ic. */
		private IC_DataModelView	ic;

		/**
		 * Instantiates a new aggregation action.
		 * 
		 * @param ic
		 *            the ic
		 * @param itemsel
		 *            the itemsel
		 * @param viewlinktype
		 *            the viewlinktype
		 * @param view
		 *            the view
		 */
		public AggregationAction(IC_DataModelView ic, Item itemsel, Item viewlinktype, Item view) {
			super("is aggregation link", AS_CHECK_BOX);
			this.linktype = itemsel;
			this.view = view;
			this.viewlinktype = viewlinktype;
			this.ic = ic;
			setChecked(ic.isAggregationChecked(linktype, this.viewlinktype));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {
			ic.setAggregationChecked(linktype, viewlinktype, isChecked());
		}

	}

	/** The datamodel. */
	Item[]				datamodel;

	/** The viewmodel. */
	Item				viewmodel;

	WorkspaceListener	_listener	= new WorkspaceListener() {
										@Override
										public void workspaceChanged(ImmutableWorkspaceDelta wd) {

											for (ImmutableItemDelta itemdelta : wd.getItems()) {
												Item item = itemdelta.getItem();
												ItemType type = item.getType();
												if (isGoodItem(item)) {
													if (itemdelta.isCreated()) {
														checkedTreeUI.addNode(item);
													} else if (itemdelta.isDeleted()) {
														checkedTreeUI.removeNode(item);
													}
												} else if (type == CadseGCST.VIEW_ITEM_TYPE) {
													Item itemtype = ViewItemTypeManager.getItemType(item);
													if (isGoodItem(itemtype)) {
														if (itemdelta.isCreated()) {
															checkedTreeUI.selectObject(itemtype, true);
														} else if (itemdelta.isDeleted()) {
															checkedTreeUI.selectObject(itemtype, false);
														}
														// TODO si l'attriut "is
														// first element" a
														// change :
														// update de l'icon ou
														// du label
														// lorsque l'icon ou le
														// label sera d�core.
													}
												} else if (type == CadseGCST.VIEW_LINK_TYPE) {
													Item linktype = ViewLinkTypeManager.getLinkType(item);
													if (isGoodItem(linktype)) {
														if (itemdelta.isCreated()) {
															checkedTreeUI.selectObject(linktype, true);
														} else if (itemdelta.isDeleted()) {
															checkedTreeUI.selectObject(linktype, false);
														}
														// TODO si l'attriut "is
														// first element" a
														// change :
														// update de l'icon ou
														// du label
														// lorsque l'icon ou le
														// label sera d�core.
													}
												}
											}

										}

									};

	/**
	 * Instantiates a new i c_ data model view.
	 * 
	 * @param datamodel
	 *            the datamodel
	 * @param viewmodel
	 *            the viewmodel
	 */
	public IC_DataModelView(Item[] datamodel, Item viewmodel) {
		this.datamodel = datamodel;
		this.viewmodel = viewmodel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.impl.ic.IC_Abstract#init()
	 */
	@Override
	public void init() throws CadseException {
		super.init();
		viewmodel.getLogicalWorkspace().addListener(_listener, 0xFFFF);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.impl.ic.IC_Abstract#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		viewmodel.getLogicalWorkspace().removeListener(_listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.IC_ForCheckedViewer#getSources()
	 */
	public Object[] getSources() {
		return this.datamodel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.impl.ic.IC_TreeCheckedUI#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object obj) {
		if (obj instanceof Item[]) {
			return (Item[]) obj;
		}
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
			if (type == CadseGCST.ITEM_TYPE || type == CadseGCST.DATA_MODEL || type == CadseGCST.LINK) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.impl.ic.IC_ContextMenu#hasRemoveAllWhenShown()
	 */
	public boolean hasRemoveAllWhenShown() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.impl.ic.IC_ContextMenu#menuAboutToHide(java.lang.Object[],
	 *      org.eclipse.jface.action.IMenuManager)
	 */
	public void menuAboutToHide(Object[] selection, IMenuManager manager) {
		if (selection != null && selection.length == 1) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.impl.ic.IC_ContextMenu#menuAboutToShow(java.lang.Object[],
	 *      org.eclipse.jface.action.IMenuManager)
	 */
	public void menuAboutToShow(Object[] selection, IMenuManager manager) {
		if (selection != null && selection.length == 1) {
			Item itemsel = (Item) selection[0];
			ItemType type = itemsel.getType();
			if (type == CadseGCST.ITEM_TYPE) {
				Item viewitemtype = ViewManager.getViewItemType(viewmodel, itemsel);
				manager.add(new RootAction(this, itemsel, viewitemtype, viewmodel));
			} else if (type == CadseGCST.LINK) {
				Item viewlinktype = null;
				Item itemtype = itemsel.getPartParent();
				Item viewitemtype = null;
				if (itemtype != null) {
					viewitemtype = ViewManager.getViewItemType(viewmodel, itemtype);
				}
				if (viewitemtype != null) {
					viewlinktype = ViewManager.getViewLinkType(viewitemtype, itemsel);
				}

				manager.add(new CanCreationItemAction(this, itemsel, viewlinktype, viewmodel));
				manager.add(new CanCreationLinkAction(this, itemsel, viewlinktype, viewmodel));
				manager.add(new AggregationAction(this, itemsel, viewlinktype, viewmodel));
			}
		}
	}

	/**
	 * Checks if is first element checked.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * @param viewitemtype
	 *            the viewitemtype
	 * 
	 * @return true, if is first element checked
	 */
	public boolean isFirstElementChecked(Item itemtype, Item viewitemtype) {
		return viewitemtype != null && ViewItemTypeManager.isIsFirstElementAttribute(viewitemtype);
	}

	/**
	 * Sets the is first element checked.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * @param viewitemtype
	 *            the viewitemtype
	 * @param b
	 *            the b
	 */
	public void setIsFirstElementChecked(Item itemtype, Item viewitemtype, boolean b) {
	}

	/**
	 * Sets the can create link checked.
	 * 
	 * @param linktype
	 *            the linktype
	 * @param viewlinktype
	 *            the viewlinktype
	 * @param b
	 *            the b
	 */
	public void setCanCreateLinkChecked(Item linktype, Item viewlinktype, boolean b) {
	}

	/**
	 * Sets the can create item checked.
	 * 
	 * @param linktype
	 *            the linktype
	 * @param viewlinktype
	 *            the viewlinktype
	 * @param b
	 *            the b
	 */
	public void setCanCreateItemChecked(Item linktype, Item viewlinktype, boolean b) {
	}

	/**
	 * Sets the aggregation checked.
	 * 
	 * @param linktype
	 *            the linktype
	 * @param viewlinktype
	 *            the viewlinktype
	 * @param b
	 *            the b
	 */
	public void setAggregationChecked(Item linktype, Item viewlinktype, boolean b) {
	}

	/**
	 * Checks if is can create item checked.
	 * 
	 * @param linktype
	 *            the linktype
	 * @param viewlinktype
	 *            the viewlinktype
	 * 
	 * @return true, if is can create item checked
	 */
	public boolean isCanCreateItemChecked(Item linktype, Item viewlinktype) {
		return viewlinktype != null && ViewLinkTypeManager.isCanCreateItemAttribute(viewlinktype);
	}

	/**
	 * Checks if is can create link checked.
	 * 
	 * @param linktype
	 *            the linktype
	 * @param viewlinktype
	 *            the viewlinktype
	 * 
	 * @return true, if is can create link checked
	 */
	public boolean isCanCreateLinkChecked(Item linktype, Item viewlinktype) {
		return viewlinktype != null && ViewLinkTypeManager.isCanCreateLinkAttribute(viewlinktype);
	}

	/**
	 * Checks if is aggregation checked.
	 * 
	 * @param linktype
	 *            the linktype
	 * @param viewlinktype
	 *            the viewlinktype
	 * 
	 * @return true, if is aggregation checked
	 */
	public boolean isAggregationChecked(Item linktype, Item viewlinktype) {
		return viewlinktype != null && ViewLinkTypeManager.isAggregationAttribute(viewlinktype);
	}

	/**
	 * Gets the viewmodel.
	 * 
	 * @return the viewmodel
	 */
	public Item getViewmodel() {
		return viewmodel;
	}

	/**
	 * Gets the datamodel.
	 * 
	 * @return the datamodel
	 */
	public Item[] getDatamodel() {
		return datamodel;
	}

	/** The checked tree ui. */
	DCheckedTreeUI	checkedTreeUI;

	/**
	 * Sets the checked tree ui.
	 * 
	 * @param checkedTreeUI
	 *            the new checked tree ui
	 */
	public void setCheckedTreeUI(DCheckedTreeUI checkedTreeUI) {
		this.checkedTreeUI = checkedTreeUI;
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
	 * @see fr.imag.adele.cadse.core.ui.IModelController#getValue()
	 */
	public Object getValue() {
		ArrayList<Item> viewmodelItems = new ArrayList<Item>();
		Item view = getViewmodel();
		Collection<Item> viewitemtypes = ViewManager.getViewItemTypes(view);

		for (Item item : viewitemtypes) {
			Item itemtype = ViewItemTypeManager.getItemType(item);
			if (itemtype == null) {
				continue;
			}
			viewmodelItems.add(itemtype);
			Collection<Item> viewlinktypes = ViewItemTypeManager.getViewLinkTypes(item);
			for (Item vlt : viewlinktypes) {
				Item lt = ViewLinkTypeManager.getLinkType(vlt);
				if (lt == null) {
					continue;
				}
				viewmodelItems.add(lt);
			}
		}
		return viewmodelItems.toArray(new Item[viewmodelItems.size()]);
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

	/**
	 * Removes the item.
	 * 
	 * @param item
	 *            the item
	 */
	private void removeItem(Item item) {
		ItemType type = item.getType();
		if (type == CadseGCST.ITEM_TYPE) {
			selectAnItemType(item, false);
			Item viewitemtype = ViewManager.getViewItemType(getViewmodel(), item);
			if (viewitemtype != null) {
				try {
					viewitemtype.delete(false);
				} catch (CadseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (type == CadseGCST.LINK) {
			Item viewitemtype = ViewManager.getViewItemType(getViewmodel(), item.getPartParent());
			if (viewitemtype != null) {
				try {
					Item viewlinktype = ViewManager.getViewLinkType(viewitemtype, item);
					if (viewlinktype != null) {
						viewlinktype.delete(false);
					}
				} catch (CadseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				checkedTreeUI.selectObject(item, false);
			}
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
			if (type == CadseGCST.LINK) {
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
			if (type == CadseGCST.LINK) {
				checkedTreeUI.selectObject(dest, sel);
				if (sel) {
					createViewLinkType(dest);
				}
			}
		}
	}

	/**
	 * Adds the item.
	 * 
	 * @param item
	 *            the item
	 */
	protected void addItem(Item item) {
		try {
			ItemType type = item.getType();
			if (type == CadseGCST.ITEM_TYPE) {
				selectAnItemType(item, true);
				createViewItemType(item);
			} else if (type == CadseGCST.LINK) {
				checkedTreeUI.selectObject(item.getPartParent(), true);
				createViewLinkType(item);
			} else if (item.getType() == CadseGCST.DATA_MODEL) {
				selectAnDataModel(item, true);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates the view link type.
	 * 
	 * @param item
	 *            the item
	 */
	protected void createViewLinkType(Item item) {
	}

	/**
	 * Creates the view item type.
	 * 
	 * @param item
	 *            the item
	 */
	protected void createViewItemType(Item item) {
	}

	/**
	 * Checks if is good item.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return true, if is good item
	 */
	private boolean isGoodItem(Item item) {
		List<Item> list = Arrays.asList(datamodel);
		while (true) {
			if (item == null) {
				return false;
			}
			ItemType type = item.getType();
			if (type != CadseGCST.ITEM_TYPE && type != CadseGCST.DATA_MODEL && type != CadseGCST.LINK) {
				return false;
			}
			if (list.contains(item)) {
				return true;
			}
			item = item.getPartParent();
		}
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

	public ItemType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAnonymous() {
		// TODO Auto-generated method stub
		return false;
	}

}
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
package fr.imag.adele.cadse.cadseg.teamwork.commit;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.UUID;

import org.eclipse.core.internal.databinding.property.value.SetSimpleValueObservableMap;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredTree;

import fede.workspace.tool.view.WSPlugin;
import fede.workspace.tool.view.node.AbstractCadseViewNode;
import fede.workspace.tool.view.node.ArrayFilterItem;
import fede.workspace.tool.view.node.CategoryNode;
import fede.workspace.tool.view.node.FilterItem;
import fede.workspace.tool.view.node.FilteredItemNode;
import fede.workspace.tool.view.node.FilteredItemNodeModel;
import fede.workspace.tool.view.node.ItemNode;
import fede.workspace.tool.view.node.LinkTypeCategoryNode;
import fede.workspace.tool.view.node.Rule;
import fede.workspace.tool.view.node.FilteredItemNode.Category;
import fr.imag.adele.cadse.cadseg.teamwork.Error;
import fr.imag.adele.cadse.cadseg.teamwork.VisitedItems;
import fr.imag.adele.cadse.cadseg.teamwork.ui.CompleteItemNode;
import fr.imag.adele.cadse.cadseg.teamwork.ui.DecoratingTableLabelProvider;
import fr.imag.adele.cadse.cadseg.teamwork.ui.IC_DynamicArrayOfObjectForList;
import fr.imag.adele.cadse.cadseg.teamwork.ui.ItemNodeWithoutChildren;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ChangeID;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemShortNameComparator;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.internal.LogicalWorkspaceTransactionImpl;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.AbstractModelController;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.Pages;
import fr.imag.adele.cadse.core.ui.RuningInteractionController;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;
import fr.imag.adele.cadse.eclipse.view.SelfViewContentProvider;
import fr.imag.adele.cadse.eclipse.view.SelfViewLabelProvider;
import fr.imag.adele.cadse.eclipse.view.SelfViewTableLabelProvider;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.UIRunningField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.dialog.SWTDialog;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_TreeModel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DGridUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DSashFormUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTreeModelUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.WizardController;
/**
 * Dialog used for providing informations about commit operation to user. Status
 * informations provided to the user are : - order of items to commit - commit
 * status of each item (not already commited, commited or commit failed) -
 * commit error by item
 * 
 * @author Thomas
 * 
 */
public class CommitStatusDialog extends SWTDialog {

	/*
	 * UI fields.
	 */
	protected DSashFormUI							_selectSashField;

	protected DSashFormUI							_rootSashField;

	protected DTreeModelUI<ModifiedItemTreeIC>			_treeField;

	protected DTextUI<RuningInteractionController>				_errorsField;

	protected DCheckBoxUI							_commitedField;

	protected DTextUI								_revField;

	protected final DTreeModelUI<ItemToCommitTreeIC>								_listOfCommitedItemsField;

	protected List<UIRunningField<?>>							_selectDependentFields	= new ArrayList<UIRunningField<?>>();

	/**
	 * Status and definition of commit operation.
	 */
	protected final CommitState						_commitState;

	protected final LogicalWorkspace   				_workspaceCopy;

	/*
	 * State of this dialog.
	 */
	protected Item									_selectedItem			= null;

	protected Set<UUID>						_itemsToShow			= new HashSet<UUID>();

	/*
	 * Fields used for synchronization of tree refresh.
	 */
	protected boolean								_refreshTree			= true;

	protected Object								_refreshTreeLock		= new Object();
	
	protected boolean								_refreshListOfCommitedItems	= true;

	protected Object								_refreshListOfCommitedItemsLock		= new Object();

	/**
	 * Commit operation thread.
	 */
	private CommitThread							_commitThread;

	

	/*
	 * Tree Structure controller classes.
	 */

	static public class RootItemsFromItemTypeRule extends Rule {
		Comparator<Item>	sortFct	= null;
		ItemType			it;
		private FilterItem	_filter;

		public RootItemsFromItemTypeRule(ItemType it, Comparator<Item> sortFct, FilterItem filter) {
			super();
			this.it = it;
			this.sortFct = sortFct;
			_filter = filter;
		}

		@Override
		public void computeChildren(FilteredItemNode root, AbstractCadseViewNode node, List<AbstractCadseViewNode> ret) {
			Collection<Item> values = it.getItems();
			if (sortFct != null) {
				TreeSet<Item> values2 = new TreeSet<Item>(sortFct);
				values2.addAll(values);
				values = values2;
			}
			for (Item valueItem : values) {
				ItemType type = valueItem.getType();
				if (valueItem.isRuntime()) {
					continue;
				}

				if (!type.isRootElement() || type.isHidden()) {
					continue;
				}

				if (!_filter.accept(valueItem)) {
					continue;
				}

				ret.add(new CompleteItemNode(root, node, valueItem));
			}
		}
	}
	
	static public class ItemsToCommitRule extends Rule {
		Comparator<Item>	sortFct	= null;
		LogicalWorkspace	_lw;
		private FilterItem	_filter;

		public ItemsToCommitRule(LogicalWorkspace lw, Comparator<Item> sortFct, FilterItem filter) {
			super();
			this._lw = lw;
			this.sortFct = sortFct;
			_filter = filter;
		}

		@Override
		public void computeChildren(FilteredItemNode root, AbstractCadseViewNode node, List<AbstractCadseViewNode> ret) {
			Collection<Item> values = _lw.getItems();
			if (sortFct != null) {
				TreeSet<Item> values2 = new TreeSet<Item>(sortFct);
				values2.addAll(values);
				values = values2;
			}
			for (Item valueItem : values) {
				ItemType type = valueItem.getType();
				if (valueItem.isRuntime()) {
					continue;
				}

				if (type.isHidden()) {
					continue;
				}

				if (!_filter.accept(valueItem)) {
					continue;
				}

				ret.add(new CompleteItemNode(root, node, valueItem));
			}
		}
	}

	static public class ItemsFromItemTypeWithFilterRule extends Rule {
		Comparator<Item>	sortFct	= null;
		ItemType			it;
		FilterItem			_filter;

		public ItemsFromItemTypeWithFilterRule(ItemType it, Comparator<Item> sortFct, FilterItem filter) {
			super();
			this.it = it;
			this.sortFct = sortFct;
			_filter = filter;
		}

		@Override
		public void computeChildren(FilteredItemNode root, AbstractCadseViewNode node, List<AbstractCadseViewNode> ret) {
			Collection<Item> values = it.getItems();
			if (sortFct != null) {
				TreeSet<Item> values2 = new TreeSet<Item>(sortFct);
				values2.addAll(values);
				values = values2;
			}
			for (Item valueItem : values) {
				if ((_filter == null) || (_filter.accept(valueItem))) {
					ItemNode itemNode = new ItemNode(root, node, valueItem);
					if (itemNode.hasChildren()) {
						ret.add(itemNode);
					}
				}
			}
		}
	}

	static public class ItemsFromLinkFromLinkTypeRule extends Rule {
		Comparator<Item>	sortFct	= null;
		boolean				resolved;
		boolean				inverse;
		FilterItem			filter;

		public ItemsFromLinkFromLinkTypeRule(Comparator<Item> sortFct, boolean resolved, boolean inverse,
				FilterItem filter) {
			super();
			this.resolved = resolved;
			this.inverse = inverse;
			this.sortFct = sortFct;
			this.filter = filter;
		}

		public ItemsFromLinkFromLinkTypeRule(Comparator<Item> sortFct, boolean resolved, boolean inverse,
				Collection<Item> filter) {
			super();
			this.resolved = resolved;
			this.inverse = inverse;
			this.sortFct = sortFct;
			this.filter = new ArrayFilterItem(filter);
		}

		@Override
		public void computeChildren(FilteredItemNode root, AbstractCadseViewNode node, List<AbstractCadseViewNode> ret) {
			LinkType lt = node.getLinkType();
			Item item = node.getItem();
			if (item != null) {
				Collection<Item> values = null;
				if (lt != null) {
					if (inverse) {
						values = item.getIncomingItems(lt);
					} else {
						values = item.getOutgoingItems(lt, resolved);
					}
				} else {
					if (inverse) {
						values = item.getIncomingItems();
					} else {
						values = item.getOutgoingItems(resolved);
					}
				}
				if (sortFct != null) {
					TreeSet<Item> values2 = new TreeSet<Item>(sortFct);
					values2.addAll(values);
					values = values2;
				}
				for (Item valueItem : values) {
					// if this node is already shown in the ancestor don't show
					// its children
					boolean isAlreadyShown = false;
					IItemNode curNode = node.getParent();
					while (curNode != null) {
						Item curItem = curNode.getItem();
						if ((curItem != null) && curItem.equals(valueItem)) {
							isAlreadyShown = true;
							break;
						}

						curNode = curNode.getParent();
					}

					if (filter == null || filter.accept(valueItem)) {
						ret.add(new ItemNodeWithoutChildren(root, node, valueItem, !isAlreadyShown));
					}
				}
			}
		}
	}

	static public class LinkTypeCategoryRule extends Rule {

		public LinkTypeCategoryRule() {
			super();
		}

		@Override
		public void computeChildren(FilteredItemNode root, AbstractCadseViewNode node, List<AbstractCadseViewNode> ret) {
			ItemType type = node.getItemType();
			List<LinkType> linkTypes = type.getOutgoingLinkTypes();
			if ((linkTypes == null) || linkTypes.isEmpty()) {
				return;
			}

			for (LinkType linkType : linkTypes) {
				if (linkType.isHidden()) {// || AttributeManager.isIsMetaAttributeAttribute(linkType)) {
					continue;
				}

				Category categ = new Category();
				categ.name = linkType.getName();

				LinkTypeCategoryNode categoryNode = new LinkTypeCategoryNode(root, node, categ, linkType);
				if (categoryNode.hasChildren()) {
					ret.add(categoryNode);
				}
			}
		}
	}

	/*
	 * Classes for Model controllers and Interaction controllers.
	 */

	public class DefaultMC_AttributesItem extends MC_AttributesItem {

		@Override
		public Item getItem() {
			return _selectedItem;
		}

		@Override
		public Object getValue() {
			if (getItem() == null) {
				return "";
			}
			Object _ret = super.getValue();
			if (_ret == null) {
				return "";
			}
			return _ret;
		}

		@Override
		public void notifieValueChanged(UIField field, Object value) {
			// read only value
		}
	}

	public class ItemError_MC extends MC_AttributesItem {

		@Override
		public Item getItem() {
			return _selectedItem;
		}

		@Override
		public Object getValue() {
			Item item = getItem();
			if (item == null) {
				return "";
			}

			List<Error> errors = _commitState.getErrors().getErrors(item.getId());
			StringBuffer errorsVal = new StringBuffer();
			for (int i = 0; i < errors.size(); i++) {
				Error error = errors.get(i);

				errorsVal.append("--- Error " + (i + 1) + " ---\n");
				errorsVal.append(error.getMessage());
				errorsVal.append("\n\n");
			}
			return errorsVal.toString();
		}

		@Override
		public void notifieValueChanged(UIField field, Object value) {
			// read only value
		}
	}

	public class CommitActionPage extends AbstractActionPage {

		@Override
		public void initAfterUI(UIPlatform uiPlatform) {
			super.initAfterUI(uiPlatform);
			CheckboxTreeViewer treeViewer = _treeField.getTreeViewer();
			treeViewer.setLabelProvider(new DecoratingLabelProvider((ILabelProvider) treeViewer.getLabelProvider(),
					new CommitErrorDecorator(_commitState)));

			FilteredTree treeOfList = (FilteredTree) _listOfCommitedItemsField.getMainControl();
			TreeViewer listViewer = treeOfList.getViewer();
			listViewer.setLabelProvider(new DecoratingLabelProvider((ILabelProvider) listViewer.getLabelProvider(),
					new CommitErrorDecorator(_commitState)));

			_listOfCommitedItemsField.setVisualValue(_commitState.getItemsToCommit());
		}
		
		@Override
		public void doFinish(UIPlatform uiPlatform, Object monitor) throws Exception {
			Object[] array = _treeField.getSelectedObjects();

			
		}
	}

	public class ModifiedItemTreeIC extends IC_TreeModel {

		private IContentProvider	_contentProvider;

		@Override
		public ItemType getType() {
			return null;
		}

		@Override
		public void initAfterUI() {
			super.initAfterUI();
			try {
				FilteredItemNode node = getOrCreateFilteredNode();
				for (IItemNode n : node.getChildren()) {
					if (isSelected(n) == IItemNode.SELECTED) {
						((DTreeModelUI) getUIField()).selectNode(n);
					}
				}
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public ILabelProvider getLabelProvider() {
			return new DecoratingLabelProvider(super.getLabelProvider(),
					new CommitErrorDecorator(_commitState));
		}

		@Override
		public String canObjectDeselected(Object object) {
			IItemNode node = (IItemNode) object;

			return null;
		}

		@Override
		public String canObjectSelected(Object object) {
			IItemNode node = (IItemNode) object;
			if (node.getItem() == null || node instanceof CategoryNode) {
				return "This node cannot be selected.";
			}

			return null;
		}

		@Override
		public void select(Object data) {
			IItemNode node = (IItemNode) data;
			if (node.getItem() == null || (node instanceof CategoryNode)) {
				selectInvalidTreeItem();
				return;
			}

			_uiPlatform.setMessage(null, UIPlatform.NONE);
			setSelectedItem(node.getItem());
		}

		private void selectInvalidTreeItem() {
			_uiPlatform.setMessage("This node should not be selected.", UIPlatform.ERROR);
		}

		/**
		 * Create the structured model to show.
		 */
		@Override
		protected FilteredItemNodeModel getTreeModel() {
			if (model == null) {
				model = new FilteredItemNodeModel();

				// roots are root elements which are root items
				model.addRule(FilteredItemNodeModel.ROOT_ENTRY, new RootItemsFromItemTypeRule(CadseGCST.ITEM,
						ItemShortNameComparator.INSTANCE, new FilterItem() {
							public boolean accept(Item item) {
								return _itemsToShow.contains(item.getId());
							}
						}));

				// children are all destinations items
				model.addRule(CadseGCST.ITEM, new LinkTypeCategoryRule());
				model.addRule(CadseGCST.LINK_TYPE, new ItemsFromLinkFromLinkTypeRule(
						ItemShortNameComparator.INSTANCE, false, false, new FilterItem() {
							public boolean accept(Item item) {
								return !item.isRuntime() && _itemsToShow.contains(item.getId());
							}
						}));
			}
			return super.getTreeModel();
		}

		@Override
		protected FilteredItemNode createRootNode() {
			return new FilteredItemNode(null, getTreeModel()) {
				@Override
				public int isSelected(IItemNode node) {
					return ModifiedItemTreeIC.this.isSelected(node);
				}
			};
		}

		public int isSelected(Object element) {
			if (element instanceof ItemNode) {
				Item item = ((IItemNode) element).getItem();
				if (item != null) {
					return _commitState.isToCommit(item.getId()) ? IItemNode.SELECTED : IItemNode.DESELECTED;
				}
			}
			if (element instanceof CategoryNode) {
				return IItemNode.GRAY_SELECTED;
			}

			return IItemNode.DESELECTED;
		}

		@Override
		public IContentProvider getContentProvider() {
			if (_contentProvider == null) {
				_contentProvider = new SelfViewContentProvider() {
					@Override
					public void notifieChangeEvent(ChangeID id, Object... values) {
						if ((values != null) && (values.length != 0)) {
							super.notifieChangeEvent(id, values);
							return;
						}

						PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
							public void run() {
								refreshAll();
							}
						});
					}
				};
			}

			return _contentProvider;
		}
	}
	
	public class ItemToCommitTreeIC extends IC_TreeModel {

		private IContentProvider	_contentProvider;

		@Override
		public ItemType getType() {
			return null;
		}

		@Override
		public void initAfterUI() {
			super.initAfterUI();
			try {
				FilteredItemNode node = getOrCreateFilteredNode();
				for (IItemNode n : node.getChildren()) {
					if (isSelected(n) == IItemNode.SELECTED) {
						((DTreeModelUI) getUIField()).selectNode(n);
					}
				}
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public ILabelProvider getLabelProvider() {
			return new DecoratingTableLabelProvider(
					new SelfViewTableLabelProvider() {
				@Override
				public Image getColumnImage(Object element, int columnIndex) {
					Item item = null;
					if (element instanceof IItemNode) {
						item = ((IItemNode) element).getItem();
					}
					if (item == null)
						return super.getImage(element);
					
					if (columnIndex == 1)
						return null;
					if (columnIndex == 2)
						return null;
					if (columnIndex == 3)
						return null;
					
					return super.getImage(element);
				}

				@Override
				public String getColumnText(Object element, int columnIndex) {
					Item item = null;
					if (element instanceof IItemNode) {
						item = ((IItemNode) element).getItem();
					}
					if (item == null)
						return super.getText(element);
					UUID itemId = item.getId();
					
					if (columnIndex == 1)
						return _commitState.isStateCommitted(itemId) ? "Done" : "";
					if (columnIndex == 2)
						return _commitState.isLinksCommitted(itemId) ? "Done" : "";
					if (columnIndex == 3)
						return _commitState.isContentCommitted(itemId) ? "Done" : "";
					
					return super.getText(element);
				}
			}, new CommitErrorDecorator(_commitState));
		}

		@Override
		public String canObjectDeselected(Object object) {
			IItemNode node = (IItemNode) object;

			return null;
		}

		@Override
		public String canObjectSelected(Object object) {
			return null;
		}

		@Override
		public void select(Object data) {
			IItemNode node = (IItemNode) data;
			if (node.getItem() == null || (node instanceof CategoryNode)) {
				selectInvalidTreeItem();
				return;
			}

			_uiPlatform.setMessage(null, UIPlatform.NONE);
			setSelectedItem(node.getItem());
		}

		private void selectInvalidTreeItem() {
			_uiPlatform.setMessage("This node should not be selected.", UIPlatform.ERROR);
		}

		/**
		 * Create the structured model to show.
		 */
		@Override
		protected FilteredItemNodeModel getTreeModel() {
			if (model == null) {
				model = new FilteredItemNodeModel();

				// roots are root elements which are root items
				model.addRule(FilteredItemNodeModel.ROOT_ENTRY, new ItemsToCommitRule(_workspaceCopy,
						new Comparator<Item>() {
							public int compare(Item o1, Item o2) {
								int o1Idx = _commitState.getItemsToCommitRequirements().indexOf(o1.getId());
								int o2Idx = _commitState.getItemsToCommitRequirements().indexOf(o2.getId());
								if( o1Idx > o2Idx )
									return 1;
								else if( o1Idx < o2Idx )
									return -1;
								else
									return 0;
							}
						}, new FilterItem() {
							public boolean accept(Item item) {
								return _commitState.getItemsToCommitRequirements().contains(item.getId());
							}
						}));
			}
			return super.getTreeModel();
		}

		@Override
		protected FilteredItemNode createRootNode() {
			return new FilteredItemNode(null, getTreeModel()) {
				@Override
				public int isSelected(IItemNode node) {
					return ItemToCommitTreeIC.this.isSelected(node);
				}
			};
		}

		public int isSelected(Object element) {
			if (element instanceof ItemNode) {
				Item item = ((IItemNode) element).getItem();
				if (item != null) {
					return IItemNode.SELECTED;
				}
			}
			if (element instanceof CategoryNode) {
				return IItemNode.GRAY_SELECTED;
			}

			return IItemNode.DESELECTED;
		}

		@Override
		public IContentProvider getContentProvider() {
			if (_contentProvider == null) {
				_contentProvider = new SelfViewContentProvider() {
					@Override
					public void notifieChangeEvent(ChangeID id, Object... values) {
						if ((values != null) && (values.length != 0)) {
							super.notifieChangeEvent(id, values);
							return;
						}

						PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
							public void run() {
								refreshAll();
							}
						});
					}
				};
			}

			return _contentProvider;
		}
	}

	public class MC_CommitTree extends AbstractModelController {

		public Object getValue(UIPlatform uiPlatform) {
			return _commitState.getItemsToCommitRequirements();
		}

		public void notifieValueChanged(UIField field, Object value) {

		}

		@Override
		public void notifieSubValueAdded(UIField field, Object added) {
			// do nothing
		}

		@Override
		public void notifieSubValueRemoved(UIField field, Object removed) {
			// do nothing
		}
	}

	/**
	 * Create the dialog structure... DSashFormUI DSashFormUI DGrillUI
	 * (selection part) _treeField _ DGrillUI (selection dependent part)
	 * _errorsField _commitedField _revField DGrillUI (selection independent
	 * part) _listOfCommitedItemsField
	 */
	public CommitStatusDialog(CommitState commitState) {
		super(new SWTUIPlatform(), "Commit Status", "Commit Status");

		// create a transaction to perform commit operation
		_workspaceCopy = commitState.getTransaction();

		// set manipulated data
		_commitState = commitState;
		computeItemsToShow(commitState);

		// create all UI fields
		_treeField = createTreeField(false);
		_errorsField = createErrorsField();
		_commitedField = createCommittedField();
		_revField = createRevField();
		_listOfCommitedItemsField = createListOfCommitedItemsField();

		DefaultMC_AttributesItem defaultMc = new DefaultMC_AttributesItem();

		
		// create selection part containing a tree
		DGridUI treeGrild = _swtuiPlatforms.createDGridUI(_page,	"#tree", "", EPosLabel.none, defaultMc, null, _treeField);
		

		// create part with editors for selected node
		DGridUI selectDependentFieldsGrild = _swtuiPlatforms.createDGridUI(_page,
				"#selectEdit", "", EPosLabel.none, defaultMc, null,_errorsField, _commitedField, _revField);
		
		/*
		 * Selection part
		 */
		_selectSashField = _swtuiPlatforms.createDSashFormUI(_page, 
				"#selectSash","", EPosLabel.none, defaultMc, null, treeGrild, selectDependentFieldsGrild);
		_selectSashField.setWeight(60); // 60% , 40%
		

		_selectDependentFields.add(_errorsField);
		_selectDependentFields.add(_commitedField);
		_selectDependentFields.add(_revField);

		/*
		 * Selection independent part
		 */

		// create part with editors independent of selected node
		DGridUI selectIndependentFieldsGrid = 
		_swtuiPlatforms.createDGridUI(_page,
				"#noSelectEdit", "", EPosLabel.none, defaultMc, null, _listOfCommitedItemsField);

		

		
		_rootSashField = _swtuiPlatforms.createDSashFormUI(_page, "#rootSash", "", EPosLabel.none, defaultMc, null, _selectSashField, selectIndependentFieldsGrid);
		
		_rootSashField.setHorizontal(false);
		// 65%
		// 35%
		_rootSashField.setWeight(65);
		// add main field
		_page.addLast(_rootSashField.getAttributeDefinition());


		// add listeners
		registerListener();
	}

	/**
	 * Refresh the tree used to add or remove items to commit.
	 */
	private void refreshTree(boolean forceRefresh) {
		if (!forceRefresh && !_refreshTree) {
			return;
		}

		synchronized (_refreshTreeLock) {
			SelfViewContentProvider contentProvider = (SelfViewContentProvider) _treeField._ic
					.getContentProvider();
			contentProvider.notifieChangeEvent(null);
			CheckboxTreeViewer treeViewer = _treeField.getTreeViewer();
			if (treeViewer != null)
				treeViewer.refresh();
			_refreshTree = false;
		}
	}
	
	/**
	 * Refresh the tree used to list commited items.
	 */
	private void refreshListOfCommitedItems(boolean forceRefresh) {
		if (!forceRefresh && !_refreshListOfCommitedItems) {
			return;
		}

		synchronized (_refreshListOfCommitedItemsLock) {
			SelfViewContentProvider contentProvider = (SelfViewContentProvider) _listOfCommitedItemsField._ic
					.getContentProvider();
			contentProvider.notifieChangeEvent(null);
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					_listOfCommitedItemsField.getTreeViewer().refresh();
				}
			});
			_refreshListOfCommitedItems = false;
		}
	}

	/**
	 * Register listener or validator if need
	 */
	protected void registerListener() {
		// fieldExtends.addValidateContributor(this);
	}

	/**
	 * Called when an item is selected, set the selected item and reset the
	 * values of fields.
	 * 
	 * @param selectedItem
	 *            selected item
	 */
	public void setSelectedItem(Item selectedItem) {
		this._selectedItem = selectedItem;
		
		getSWTUIPlatform().setItem(_selectedItem);
		refreshSelectDependentFields();
	}

	/**
	 * Refresh all fields which depend on selection.
	 * 
	 */
	protected void refreshSelectDependentFields() {
		for (UIRunningField<?> field : _selectDependentFields) {
			field.resetVisualValue();
		}
	}

	/**
	 * Create a tree field.
	 */
	public DTreeModelUI<ModifiedItemTreeIC> createTreeField(boolean checkBox) {
		DTreeModelUI<ModifiedItemTreeIC> treeField = _swtuiPlatforms.createTreeModelUI(_page, "#list", "Items which are commiting", EPosLabel.top,
				new MC_CommitTree(), new ModifiedItemTreeIC(), checkBox);
		return treeField;
	}

	/**
	 * Create a text field to display the errors related to selected item.
	 */
	public DTextUI createRevField() {
		return _swtuiPlatforms.createTextUI(_page, CadseGCST.ITEM_at_TW_VERSION_, "Current Revision", EPosLabel.left,
				new MC_AttributesItem(), null, 1, false, false, false,false, false, null);
	  }

	/**
	 * Create a text field to display the errors related to selected item.
	 */
	public DTextUI createErrorsField() {
		return _swtuiPlatforms.createTextUI(_page, "#errorsField",
				"Errors", EPosLabel.top, new ItemError_MC(), null, 10, true, false, true, false, false, null);
	}

	/**
	 * Create a list field showing modified attributes.
	 */
	protected DTreeModelUI<ItemToCommitTreeIC> createListOfCommitedItemsField() {
		
		DTreeModelUI<ItemToCommitTreeIC> treeField = _swtuiPlatforms.createTreeModelUI(_page, "#list", "Items which are commiting", EPosLabel.top,
				new MC_CommitTree(), new ItemToCommitTreeIC(), false, true, new String[] { "Item", "Attributes", "Links", "Content" });
		return treeField;
	}

	/**
	 * Create read only committed check box.
	 */
	public DCheckBoxUI createCommittedField() {
		AbstractModelController mc = new AbstractModelController() {

			@Override
			public Object getValue() {
					if (_selectedItem == null) {
					return Boolean.FALSE;
				}

				return _commitState.isCommitted(_selectedItem.getId());
			}

			public void notifieValueChanged(UIField field, Object value) {
				// do nothing
			}

		};
		DCheckBoxUI checkBoxField = _swtuiPlatforms.createCheckBoxUI(_page, "#committedField", "Commited without Errors", EPosLabel.none, mc,
				null);
		checkBoxField._field.setEditable(false);

		return checkBoxField;
	}

	/**
	 * 
	 * @return
	 */
	public IActionPage getFinishAction() {
		return new CommitActionPage();
	}

	/**
	 * Open Commit Definition dialog.
	 * 
	 * @param commitState
	 *            status and definition of commit operation
	 */
	static public void openDialog(final CommitState commitState) {

		/**
		 * Create a new display wen call getDefault(). Workbench is not
		 * started. This method is called by federation in start level.
		 * 
		 */
		Display d = PlatformUI.getWorkbench().getDisplay();

		d.syncExec(new Runnable() {
			public void run() {
				try {
					// create a new transaction for commit
					final LogicalWorkspaceTransaction transaction = commitState.getTransaction();
					
					final CommitStatusDialog p = new CommitStatusDialog(commitState);
					p._swtuiPlatforms.setAction(p.getFinishAction());
					final Pages f = p._swtuiPlatforms.getPages();
					final WizardController wc = new WizardController(p._swtuiPlatforms) {

						@Override
						public boolean canFinish() {
							if (!super.canFinish()) {
								return false;
							}

							return !commitState.isPerformingCommit() && commitState.isCommitPerformed();
						}
						
						@Override
						public boolean performCancel() {
							return !commitState.isPerformingCommit() && commitState.isFailed();
						}

						@Override
						public boolean performFinish() {
							return doFinish(p, f);
						}

						private boolean doFinish(final CommitStatusDialog p,
								final Pages f) {
							IRunnableWithProgress op = new IRunnableWithProgress() {
								public void run(IProgressMonitor monitor) throws InvocationTargetException,
										InterruptedException {
									try {
										f.getAction().doFinish(p.getSWTUIPlatform(), monitor);
									} catch (CoreException e) {
										throw new InvocationTargetException(e);
									} catch (Throwable e) {
										throw new InvocationTargetException(e);
									} finally {
										monitor.done();
									}
								}
							};
							return executeRunnable(op, this);
						}
					};
					
					// begin effective commit operation
					commitState.addListener(new CommitListener() {

						@Override
						public void beginCommit() {
							// do nothing
						}

						@Override
						public void beginCommitItem(UUID itemId) {
							// do nothing
						}

						@Override
						public void commitFail() {
							PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
								public void run() {
									refreshListOfCommitedItems();
									p.refreshTree(true);
									p.refreshSelectDependentFields();
									p._swtuiPlatforms.setMessageError("Commit failed !");
								}
							});
						}

						@Override
						public void endCommit() {
							
							// if commit is OK, commit workspace logical copy
							if (commitState.isCommitPerformed()) {
								PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
									public void run() {
										p._swtuiPlatforms.setMessage("Commit succeed !", IMessageProvider.INFORMATION);
									}
								});
							} else {
								PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
									public void run() {
										p._swtuiPlatforms.setMessageError("Commit failed !");
									}
								});
							}
						}

						@Override
						public void endCommitItem(UUID itemId) {
							refreshListOfCommitedItems();
						}

						@Override
						public void endCommitItemContent(UUID itemId) {
							refreshListOfCommitedItems();
						}

						@Override
						public void endCommitItemLinks(UUID itemId) {
							refreshListOfCommitedItems();
						}

						@Override
						public void endCommitItemState(UUID itemId) {
							refreshListOfCommitedItems();
						}

						private void refreshListOfCommitedItems() {
							p.refreshListOfCommitedItems(true);
						}
						
					});
					CommitThread commitThread = new CommitThread(commitState, transaction);
					commitThread.start();
					
					p.setPageSize(800, 500);
					p.open(null, wc);
				
					
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SWTUIPlatform getSWTUIPlatform() {
		return _swtuiPlatforms;
	}

	public static  boolean executeRunnable(final Runnable runnable,
			final WizardController wizardController) {
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException,
					InterruptedException {
				try {
					runnable.run();
				} catch (Throwable e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		return executeRunnable(op, wizardController);
	}
	
	public static boolean executeRunnable(IRunnableWithProgress op,
			final WizardController wizardController) {
		try {
			wizardController.getContainer().run(false, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			if (realException instanceof NullPointerException) {
				MessageDialog.openError(wizardController.getShell(), "Error", "Null pointeur exception");
				realException.printStackTrace();
				return false;
			}
			MessageDialog.openError(wizardController.getShell(), "Error", realException.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Returns state of the commit operation.
	 * 
	 * @return state of the commit operation.
	 */
	public CommitState getCommitState() {
		return _commitState;
	}



	protected void computeItemsToShow(CommitState commitState) {
		List<UUID> itemsToCommit = commitState.getItemsToCommit();
		if ((itemsToCommit == null) || itemsToCommit.isEmpty()) {
			_itemsToShow.clear();
			return;
		}

		_itemsToShow.clear();
		for (UUID itemId : itemsToCommit) {
			Item item = _workspaceCopy.getItem(itemId);
			addItemToCommit(item);
		}
	}

	/**
	 * Add an item and all items which allow to navigate to him to show item
	 * set. Return true if we have been able to find a path to this item.
	 * 
	 * @param itemId
	 *            id of item to show
	 * @return true if we have been able to find a path to this item.
	 */
	protected boolean addItemToShow(UUID itemId) {
		_itemsToShow.add(itemId);

		// compute the ancestors of this item
		Item item = CadseCore.getLogicalWorkspace().getItem(itemId);
		Stack<Item> pathToRoot = new Stack<Item>();
		if (!findPathToRoot(item, pathToRoot)) {
			return false; // TODO should add a warning, item will not be shown
			// in the tree
		}

		// add all items on the path
		Item curItem = pathToRoot.pop();
		while (curItem != null) {
			if (curItem != item) {
				_itemsToShow.add(curItem.getId());
			}

			if (!pathToRoot.isEmpty()) {
				curItem = pathToRoot.pop();
			} else {
				curItem = null;
			}
		}

		return true;
	}

	/**
	 * Add specified item to list of items to commit. Update errors. Returns
	 * true if an update of tree is necessary.
	 * 
	 * @param item
	 *            item to commit
	 * @return true if an update of tree is necessary.
	 */
	protected boolean addItemToCommit(Item item) {
		VisitedItems visited = new VisitedItems();
		return addItemToCommit(item, visited);
	}

	/**
	 * Returns all tree nodes which represent specified item.
	 * 
	 * @param item
	 *            an item
	 * @return all tree nodes which represent specified item.
	 */
	private List<AbstractCadseViewNode> findItemNodes(Item item) {
		UUID itemId = item.getId();

		AbstractCadseViewNode rootNode = _treeField._ic.getOrCreateFilteredNode();
		List<AbstractCadseViewNode> itemNodes = new ArrayList<AbstractCadseViewNode>();
		findItemNodes(itemId, rootNode, itemNodes);

		return itemNodes;
	}

	/**
	 * Add to item node list, all child nodes of rootNode which represent
	 * specified item.
	 * 
	 * @param itemId
	 *            id of item to find
	 * @param rootNode
	 *            root node of the search
	 * @param itemNodes
	 *            list of item nodes which represent itemId
	 */
	private void findItemNodes(UUID itemId, AbstractCadseViewNode rootNode, List<AbstractCadseViewNode> itemNodes) {
		if (!rootNode.hasChildren()) {
			return;
		}

		for (AbstractCadseViewNode childNode : rootNode.getChildren()) {
			if (childNode instanceof CategoryNode) {
				continue;
			}

			Item item = childNode.getItem();
			if (itemId.equals(item.getId())) {
				itemNodes.add(rootNode);
			}

			findItemNodes(itemId, childNode, itemNodes);
		}
	}

	/**
	 * Add specified item to list of items to commit. Update errors. Returns
	 * true if an update of tree is necessary.
	 * 
	 * @param item
	 *            item to commit
	 * @param visited
	 *            list of already visited items
	 * @return true if an update of tree is necessary.
	 */
	protected boolean addItemToCommit(Item item, VisitedItems visited) {
		UUID itemId = item.getId();

		addItemToShow(itemId);
		visited.markAsVisited(itemId);

		return true; // TODO should compute if refresh UI is necessary
	}

	private boolean findPathToRoot(Item item, Stack<Item> pathToRoot) {
		pathToRoot.push(item);

		ItemType itemType = item.getType();
		if ((itemType != null) && itemType.isRootElement()) {
			return true;
		}

		// first try to navigate to parent
		Item parentItem = item.getPartParent();
		if (parentItem != null) {
			if (findPathToRoot(parentItem, pathToRoot)) {
				return true;
			}
		}

		// second try to navigate thought aggregate links
		List<? extends Link> incomingLinks = item.getIncomingLinks();
		for (Link link : incomingLinks) {
			if (link.getLinkType().isPart() || !link.isAggregation()) {
				continue;
			}

			if (findPathToRoot(link.getSource(), pathToRoot)) {
				return true;
			}
		}

		// finally try all other links
		for (Link link : incomingLinks) {
			if (link.getLinkType().isPart() || link.isAggregation()) {
				continue;
			}

			if (findPathToRoot(link.getSource(), pathToRoot)) {
				return true;
			}
		}

		pathToRoot.pop();

		return false;
	}
}

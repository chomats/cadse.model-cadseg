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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

import fede.workspace.tool.view.WSPlugin;
import fede.workspace.tool.view.node.AbstractCadseViewNode;
import fede.workspace.tool.view.node.ArrayFilterItem;
import fede.workspace.tool.view.node.CategoryNode;
import fede.workspace.tool.view.node.FilterItem;
import fede.workspace.tool.view.node.FilteredItemNode;
import fede.workspace.tool.view.node.FilteredItemNodeModel;
import fede.workspace.tool.view.node.ItemNode;
import fede.workspace.tool.view.node.ItemsFromLinkOfLinkTypeRule;
import fede.workspace.tool.view.node.LinkTypeCategoryNode;
import fede.workspace.tool.view.node.Rule;
import fede.workspace.tool.view.node.FilteredItemNode.Category;
import fr.imag.adele.cadse.cadseg.teamwork.Error;
import fr.imag.adele.cadse.cadseg.teamwork.VisitedItems;
import fr.imag.adele.cadse.cadseg.teamwork.ui.CompleteItemNode;
import fr.imag.adele.cadse.cadseg.teamwork.ui.IC_ModifiedAttr_List;
import fr.imag.adele.cadse.cadseg.teamwork.ui.ItemNodeWithoutChildren;
import fr.imag.adele.cadse.cadseg.teamwork.ui.ItemSelectionListener;
import fr.imag.adele.cadse.cadseg.teamwork.ui.SelectedItemChangeLinkModelController;
import fr.imag.adele.cadse.cadseg.teamwork.ui.SelectedItemChange_ModifiedAttr_ModelController;
import fr.imag.adele.cadse.cadseg.teamwork.ui.TWSelfViewContentProvider;
import fr.imag.adele.cadse.cadseg.teamwork.ui.TWUIUtil;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ChangeID;
import java.util.UUID;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemShortNameComparator;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.cadse.core.impl.internal.ui.PagesImpl;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.AbstractModelController;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_StringToBoolean;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.RuningInteractionController;
import fr.imag.adele.cadse.core.ui.UIPlatform;
import fr.imag.adele.cadse.core.ui.Pages;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.eclipse.view.SelfViewContentProvider;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.UIRunningField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.ActionController;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_ForBrowserOrCombo;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_ForList;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_StaticArrayOfObjectForBrowser_Combo;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_TreeModel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DButtonUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DComboUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DGridUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DSashFormUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTreeModelUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.WizardController;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.dialog.SWTDialog;

/**
 * Dialog used for asking confirmation of items to commit. Informations provided
 * by the user are : - list of items to commit - message for this commit
 * operation
 * 
 * Informations gived by the dialog : - errors which forbid to commit.
 * 
 * @author Thomas
 * 
 */
public class CommitDialog extends SWTDialog {

	/*
	 * UI fields.
	 */
	protected DButtonUI						_addItemField;

	protected DButtonUI						_addAllItemsField;

	protected DButtonUI						_deselectAllItemsField;

	protected DSashFormUI<RuningInteractionController>					_selectSashField;

	protected DSashFormUI<RuningInteractionController>					_rootSashField;

	protected DTreeModelUI<IC_TreeModel>					_treeField;

	protected DTextUI<RuningInteractionController>						_errorsField;

	protected DListUI<IC_ForList>						_modifiedAttrsField;

	protected DCheckBoxUI<RuningInteractionController>					_reqNewRevField;

	protected DComboUI<IC_ForBrowserOrCombo>						_lastUsedCommentsField;

	protected DTextUI						_commentField;

	protected List<UIRunningField<?>>		_selectDependentFields	= new ArrayList<UIRunningField<?>>();

	/**
	 * Status and definition of commit operation.
	 */
	protected final CommitState				_commitState;

	/**
	 * Listeners interested in selection informations.
	 */
	protected List<ItemSelectionListener>	_selectListeners		= new ArrayList<ItemSelectionListener>();

	/*
	 * State of this dialog.
	 */
	protected Item							_selectedItem			= null;

	protected Set<UUID>				_itemsToShow			= new HashSet<UUID>();

	/*
	 * Fields used for synchronization of tree refresh.
	 */
	protected boolean						_refreshTree			= true;

	protected Object						_refreshTreeLock		= new Object();

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
				if (linkType.isHidden()) { // || AttributeTypeManager.isIsMetaAttributeAttribute(linkType)) {
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

	public class ErrorDecorator extends LabelProvider implements ILabelDecorator {

		private static final String		ERROR_IMAGE_KEY		= "error";

		private LocalResourceManager	_resourceManager	= new LocalResourceManager(JFaceResources
																	.getResources(PlatformUI.getWorkbench()
																			.getDisplay()));

		public ErrorDecorator() {
		}

		public Image decorateImage(Image image, Object element) {
			if (image == null) {
				return null;
			}

			if (element == null) {
				return null;
			}

			if (!(element instanceof IItemNode)) {
				return null;
			}

			IItemNode itemNode = (IItemNode) element;
			Item item = itemNode.getItem();
			if (item == null) {
				return null;
			}
			UUID itemId = item.getId();
			if (_commitState.getErrors().isInError(itemId)) {
				return computeImage(image, "icons/delete_ovr.gif");
			}

			return null;
		}

		private Image computeImage(Image image, String path) {
			ImageDescriptor overlay = WSPlugin.getImageDescriptor(path);
			DecorationOverlayIcon icon = new DecorationOverlayIcon(image, overlay, IDecoration.BOTTOM_RIGHT);
			return _resourceManager.createImage(icon);
		}

		public String decorateText(String text, Object element) {
			if (element == null) {
				return null;
			}

			return null;
		}
	}

	/*
	 * Classes for Model controllers and Interaction controllers.
	 */

	public class CommentMC extends AbstractModelController {

		public Object getValue() {
			return _commitState.getComment();
		}

		public void notifieValueChanged(UIField field, Object value) {
//			if (!field.equals(_commentField)) {
//				return;
//			}

			_commitState.setComment((String) value);
		}

	}

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
		public void doFinish(UIPlatform uiPlatform, Object monitor)
				throws Exception {
//			Object[] array = _treeField.getSelectedObjects();

			// save comment
			LastCommitMsg.addLastCommitMsg((String) _commentField.getVisualValue());
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
						_treeField.selectNode(n);
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
					new ErrorDecorator());
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

		@Override
		public int isSelected(IItemNode element) {
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

	public class MC_CommitTree extends AbstractModelController {

		public Object getValue() {
			return _commitState.getItemsToCommit();
		}

		public void notifieValueChanged(UIField field, Object value) {

		}

		@Override
		public void notifieSubValueAdded(UIField field, Object added) {
			addItem(added);
			refreshTree(false);
			refreshSelectDependentFields();
		}

		private void addItem(Object added) {
			if (!(added instanceof ItemNode)) {
				return;
			}

			ItemNode itemNode = (ItemNode) added;
			Item item = itemNode.getItem();
			if (item == null) {
				return;
			}
			if (addItemToCommit(item)) {
				markTreeToRefresh();
			}
		}

		private void markTreeToRefresh() {
			synchronized (_refreshTreeLock) {
				_refreshTree = true;
			}
		}

		@Override
		public void notifieSubValueRemoved(UIField field, Object removed) {
			removeItem(removed);
			refreshTree(true);
			refreshSelectDependentFields();
		}

		private void removeItem(Object removed) {
			if (!(removed instanceof ItemNode)) {
				return;
			}

			ItemNode itemNode = (ItemNode) removed;
			Item item = itemNode.getItem();
			if (item == null) {
				return;
			}

			if (removeItemToCommit(item)) {
				markTreeToRefresh();
			}
		}

		@Override
		public boolean validSubValueAdded(UIField field, Object added) {
			if (super.validSubValueAdded(field, added)) {
				return true;
			}

			// add item to commit
			addItem(added);

			// compute errors
			boolean isValid = validState();
			if (!isValid) {
				String errorMsg = "Error";
				if (_commitState.hasNoItemToCommit()) {
					errorMsg = "You must select at least one item to commit";
				}
				if (_commitState.getErrors().hasError()) {
					errorMsg = "You cannot commit items with errors.";
				}
				_uiPlatform.setMessage(errorMsg, UIPlatform.ERROR);
			}

			return !isValid;
		}

		@Override
		public boolean validSubValueRemoved(UIField field, Object removed) {
			if (super.validSubValueRemoved(field, removed)) {
				return true;
			}

			// remove item to commit
			removeItem(removed);

			// compute errors
			boolean isValid = validState();
			if (!isValid) {
				String errorMsg = "Error";
				if (_commitState.hasNoItemToCommit()) {
					errorMsg = "You must select at least one item to commit";
				}
				if (_commitState.getErrors().hasError()) {
					errorMsg = "You cannot commit items with errors.";
				}
				_uiPlatform.setMessage(errorMsg, UIPlatform.ERROR);
			}

			return false;
		}

		public boolean validState() {
			return !_commitState.getErrors().hasError() && !_commitState.hasNoItemToCommit();
		}
	}

	/**
	 * Create the dialog structure... DSashFormUI DSashFormUI DGrillUI
	 * (selection part) _treeField _ DGrillUI (selection dependent part)
	 * _errorsField _modifiedAttrsField _reqNewRevField DGrillUI (selection
	 * independent part) _commentField
	 */
	public CommitDialog(CommitState commitState) {
		super(new SWTUIPlatform(), "Commit Operation Definition", "Select items to commit");
		

		// set manipulated data
		_commitState = new CommitState();
		computeItemsToShow(commitState);

		// create all UI fields
		_treeField = createTreeField(true);
		_addItemField = createAddItemField();
		_addAllItemsField = createAddAllItemsField();
		_deselectAllItemsField = createDeselectAllItemsField();
		_errorsField = createErrorsField();
		_modifiedAttrsField = createModifiedAttrsField();
		_reqNewRevField = createReqNewRevField();
		_lastUsedCommentsField = createLastUsedCommentsField();
		_commentField = createCommentField();

		DefaultMC_AttributesItem defaultMc = new DefaultMC_AttributesItem();

		
		/*
		 * Selection part
		 */
		_treeField._field.setHSpan(3);
		
		// create selection part containing a tree
		DGridUI treeGrild = _swtuiPlatforms.createDGridUI(_page, "#tree", "", EPosLabel.none, defaultMc, null, _treeField, _addAllItemsField, _addItemField, _deselectAllItemsField);
		
		// create part with editors for selected node
		DGridUI selectDependentFieldsGrild = _swtuiPlatforms.createDGridUI(_page, "#selectEdit", "", EPosLabel.none, defaultMc, null, _errorsField, _modifiedAttrsField, _reqNewRevField);

		_selectDependentFields.add(_errorsField);
		_selectDependentFields.add(_modifiedAttrsField);
		_selectDependentFields.add(_reqNewRevField);

		_selectSashField = _swtuiPlatforms.createDSashFormUI(_page, "#selectSash", "", EPosLabel.none, defaultMc, null, treeGrild, selectDependentFieldsGrild);
		_selectSashField.setWeight(60); // 60% , 40%
		
		/*
		 * Selection independent part
		 */

		// create part with editors independent of selected node
		DGridUI selectIndependentFieldsGrid = _swtuiPlatforms.createDGridUI(_page, "#noSelectEdit", "", EPosLabel.none, defaultMc, null, _lastUsedCommentsField, _commentField);

		_rootSashField = _swtuiPlatforms.createDSashFormUI(_page, "#rootSash", "", EPosLabel.none, defaultMc, null, _selectSashField, selectIndependentFieldsGrid);
		_rootSashField.setHorizontal(false);
		// 80%
		// 20%
		_rootSashField.setWeight(80);

		// add main field
		addLast(_rootSashField);

		// add listeners
		registerListener();
	}

	private DComboUI createLastUsedCommentsField() {
		return _swtuiPlatforms.createDComboUI(_page, "#lastCommentsField", "Last Used Comment", EPosLabel.left, new AbstractModelController() {

			public Object getValue() {

				return null;
			}

			public void notifieValueChanged(UIField field, Object value) {
				if (value == null) {
					return;
				}

				if (!(value instanceof String)) {
					return;
				}

				String msg = (String) value;
				_commentField.setValue(msg);
			}

		}, new IC_StaticArrayOfObjectForBrowser_Combo("Last Used Comment", "Last Used Comment", LastCommitMsg
				.getLastCommitMsgTab()), false);
	}

	private DButtonUI createDeselectAllItemsField() {
		return _swtuiPlatforms.createDButtonUI(_page, "#deselectAllItemsField", "Deselect All", EPosLabel.none, null, new ActionController() {

			@Override
			public void callAction() {
				_commitState.clearItemsToCommit();
				_itemsToShow.clear();
				_selectedItem = null;

				// refresh tree
				refreshTree(true);
				refreshSelectDependentFields();
			}
		});
	}

	private DButtonUI createAddAllItemsField() {
		return _swtuiPlatforms.createDButtonUI(_page, "#addAllItemsField", "Commit All Items", EPosLabel.none, null, new ActionController() {

			@Override
			public void callAction() {
				for (Item item : CadseCore.getLogicalWorkspace().getItems()) {
					if (item.isRuntime() || !TWUtil.isModified(item)) {
						continue;
					}

					UUID itemId = item.getId();
					if (_commitState.isToCommit(itemId)) {
						continue;
					}

					addItemToCommit(item);
				}

				// refresh tree
				refreshTree(true);
				refreshSelectDependentFields();
			}
		});
	}

	private DButtonUI createAddItemField() {
		return _swtuiPlatforms.createDButtonUI(_page, "#addItemField", "Add Item To Commit", EPosLabel.none, null, new ActionController() {

			@Override
			public void callAction() {
				Shell parentShell = _swtuiPlatforms.getShell();
				ElementTreeSelectionDialog lsd = new ElementTreeSelectionDialog(parentShell, getLabelProvider(),
						getTreeContentProvider());
				ViewerFilter filter = getFilter();
				if (filter != null) {
					lsd.addFilter(filter);
				}
				lsd.setValidator(new ISelectionStatusValidator() {
					public IStatus validate(Object[] selection) {
						if ((selection == null) || (selection.length == 0)) {
							return TWUIUtil.createErrorStatus("You must select at least one item.");
						}

						for (Object select : selection) {
							if (!(select instanceof IItemNode)) {
								return TWUIUtil.createErrorStatus("You must select an item.");
							}

							IItemNode itemNode = (IItemNode) select;
							Item item = itemNode.getItem();
							if (TWUtil.cannotCommit(item)) {
								return TWUIUtil.createErrorStatus("Selected item cannot be commited.");
							}
						}

						return Status.OK_STATUS;
					}
				});
				lsd.setInput(getInputValues());
				lsd.setAllowMultiple(true);
				lsd.setTitle("Select Items to commit");
				lsd.setMessage("Select Items to commit");

				lsd.open();

				// manage selected items
				Object[] results = lsd.getResult();
				if (results == null) {
					return;
				}
				for (Object selectObj : results) {
					IItemNode itemNode = (IItemNode) selectObj;
					addItemToCommit(itemNode.getItem());
				}

				// refresh tree
				refreshTree(true);
				refreshSelectDependentFields();
			}

			protected TWSelfViewContentProvider getTreeContentProvider() {
				return new TWSelfViewContentProvider();
			}

			protected ILabelProvider getLabelProvider() {
				return new LabelProvider() {
					@Override
					public String getText(Object element) {
						if (element == null) {
							return null;
						}

						return ((IItemNode) element).getItem().getDisplayName();
					}

					@Override
					public Image getImage(Object element) {
						if (element instanceof IItemNode) {
							Item item = ((IItemNode) element).getItem();
							return createImage(item.getType(), item);
						}
						return super.getImage(element);
					}

					private Image createImage(ItemType it, Item item) {
						return WSPlugin.getDefault().getImageFrom(it, item);
					}
				};
			}

			protected Object getInputValues() {
				FilteredItemNodeModel model = new FilteredItemNodeModel();

				// roots are all item types
				model.addRule(FilteredItemNodeModel.ROOT_ENTRY, new ItemsFromItemTypeWithFilterRule(
						CadseGCST.ITEM_TYPE, ItemShortNameComparator.INSTANCE, null));

				// children are all items of parent item type
				model.addRule(CadseGCST.ITEM_TYPE, new ItemsFromLinkOfLinkTypeRule(
						CadseGCST.ITEM_lt_INSTANCE_OF, ItemShortNameComparator.INSTANCE, true, true,
						new FilterItem() {
							public boolean accept(Item item) {
								return TWUtil.isModified(item) && !_commitState.isToCommit(item.getId());
							}
						}));

				return new FilteredItemNode(null, model);
			}

			protected ViewerFilter getFilter() {
				return null;
			}
		});
	}

	/**
	 * Refresh the tree used to add or remove items to commit.
	 */
	private void refreshTree(boolean forceRefresh) {
		if (!forceRefresh && !_refreshTree) {
			return;
		}

		synchronized (_refreshTreeLock) {
			SelfViewContentProvider contentProvider = (SelfViewContentProvider) _treeField._ic.getContentProvider();
			contentProvider.notifieChangeEvent(null);
			_treeField.getTreeViewer().refresh();
			_refreshTree = false;
		}
	}

	/**
	 * Register listener or validator if need
	 */
	protected void registerListener() {
//		 _treeField.addValidateContributor(this);
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

		for (ItemSelectionListener listener : _selectListeners) {
			listener.selectItem(selectedItem);
		}

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
	public DTreeModelUI createTreeField(boolean checkBox) {
		DTreeModelUI treeField = _swtuiPlatforms.createTreeModelUI(_page, "#list", "Items to commit", EPosLabel.top, new MC_CommitTree(),
				new ModifiedItemTreeIC(), checkBox);
		return treeField;
	}

	/**
	 * Create a text field to display the errors related to selected item.
	 */
	public DTextUI createErrorsField() {
		return _swtuiPlatforms.createTextUI(_page, "#errorsField", "Errors", EPosLabel.top, new ItemError_MC(), null, 7, true, false, true, false, false);
	}

	/**
	 * Create a list field showing modified attributes.
	 */
	protected DListUI createModifiedAttrsField() {
		SelectedItemChange_ModifiedAttr_ModelController mc = new SelectedItemChange_ModifiedAttr_ModelController(false, null);
		_selectListeners.add(mc);

		IC_LinkForBrowser_Combo_List ic = new IC_ModifiedAttr_List("Select a value.", "Select a value.");
		return _swtuiPlatforms.createDListUI(_page, CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES,
				"Modified Attributes, Links and Content", EPosLabel.top, mc, ic, false, false, false, false);
	}

	/**
	 * Create a text field to edit commit comment.
	 */
	public DTextUI createCommentField() {
		return _swtuiPlatforms.createTextUI(_page, "#commentField", "Comment", EPosLabel.left, new CommentMC(), null, 3,
				true, false, false, false, false, "Provide a comment for the commit");
	}

	/**
	 * Create read only require new revision check box.
	 */
	public DCheckBoxUI createReqNewRevField() {
		MC_StringToBoolean mc = new MC_StringToBoolean();
		DCheckBoxUI checkBoxField = _swtuiPlatforms.createCheckBoxUI(_page, CadseGCST.ITEM_at_REQUIRE_NEW_REV_,
				"Commit will create a new revision", EPosLabel.none, mc, null);
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
	 * @throws CadseException
	 */
	static public void openDialog(final CommitState commitState) {

		/**
		 * Create a new display wen call getDefault(). Worksbench is not
		 * started. This method is called by federation in start level.
		 * 
		*/
				try {
					final CommitDialog p = new CommitDialog(commitState);
					p._swtuiPlatforms.setAction(p.getFinishAction());
					final Pages f = p._swtuiPlatforms.getPages();
					
					WizardController wc = new WizardController(p._swtuiPlatforms) {

						@Override
						public boolean canFinish() {
							if (!super.canFinish()) {
								return false;
							}

							CommitState commitState = p.getCommitState();
							return !commitState.getErrors().hasError() && !commitState.hasNoItemToCommit();
						}
						
						@Override
						public boolean performFinish() {
							IRunnableWithProgress op = new IRunnableWithProgress() {
								public void run(IProgressMonitor monitor) throws InvocationTargetException,
										InterruptedException {
									try {
										p._commitState.beginCommit();
										f.getAction().doFinish(p._swtuiPlatforms, monitor);
									} catch (CoreException e) {
										throw new InvocationTargetException(e);
									} catch (Throwable e) {
										throw new InvocationTargetException(e);
									} finally {
										monitor.done();
									}
								}
							};
							try {
								getContainer().run(false, false, op);
							} catch (InterruptedException e) {
								return false;
							} catch (InvocationTargetException e) {
								Throwable realException = e.getTargetException();
								if (realException instanceof NullPointerException) {
									MessageDialog.openError(getShell(), "Error", "Null pointeur exception");
									realException.printStackTrace();
									return false;
								}
								MessageDialog.openError(getShell(), "Error", realException.getMessage());
								return false;
							}

							return true;
						}

						@Override
						public boolean performCancel() {
							super.performCancel();
							
							IRunnableWithProgress op = new IRunnableWithProgress() {
								public void run(IProgressMonitor monitor) throws InvocationTargetException,
										InterruptedException {
									try {
										p._commitState.abortCommit();
									} catch (Throwable e) {
										throw new InvocationTargetException(e);
									} finally {
										monitor.done();
									}
								}
							};
							try {
								getContainer().run(false, false, op);
							} catch (InterruptedException e) {
								return false;
							} catch (InvocationTargetException e) {
								Throwable realException = e.getTargetException();
								if (realException instanceof NullPointerException) {
									MessageDialog.openError(getShell(), "Error", "Null pointeur exception");
									realException.printStackTrace();
									return false;
								}
								MessageDialog.openError(getShell(), "Error", realException.getMessage());
								return false;
							}

							return true;
						}
					};
					
					p.open(null, wc);

					// open commit progression dialog only if commit definition
					// has not been aborted
					if (!p._commitState.isPerformingCommit()) {
						return;
					}

					// open status dialog
					CommitStatusDialog.openDialog(p._commitState);
				} catch (Throwable e) {
					e.printStackTrace();
				}
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
			Item item = CadseCore.getLogicalWorkspace().getItem(itemId);
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
	 * Remove specified item for list of items to commit. Update errors.
	 * 
	 * @param item
	 *            item to remove
	 * @return true if the tree should be refreshed or updated
	 */
	protected boolean removeItemToCommit(Item item) {
		UUID itemId = item.getId();
		if (!_commitState.isToCommit(itemId)) {
			return false;
		}

		_commitState.removeItemToCommit(itemId);
		return removeItemToShow(item);
	}

	/**
	 * Hide this item from tree and update errors.
	 * 
	 * @param item
	 *            item to remove
	 * @return true if the tree should be refreshed or updated
	 */
	protected boolean removeItemToShow(Item item) {
		UUID itemId = item.getId();

		// compute new errors
		boolean hasErrors = _commitState.getErrors().isInError(itemId);
		_commitState.getErrors().removeErrors(itemId);
		if (TWUtil.isRequireNewRev(item)) {
			for (Item sourceItem : item.getIncomingItems()) {
				UUID sourceId = sourceItem.getId();
				if (_commitState.isToCommit(sourceId)) {
					_commitState.getErrors().addError(
							sourceId,
							new MissCommitError(sourceId, "Destination item " + item.getDisplayName()
									+ " of link from " + sourceItem.getDisplayName() + " must be commited.", itemId));
				}
			}
		}

		// only remove it from tree if it has no child which is to commit
		List<AbstractCadseViewNode> itemNodes = findItemNodes(item);
		boolean toRemoveFromTree = true;
		for (AbstractCadseViewNode itemNode : itemNodes) {
			if (itemNode.hasChildren()) {
				toRemoveFromTree = false;
				break;
			}
		}
		if (!toRemoveFromTree) {
			return (!hasErrors);
		}
		_itemsToShow.remove(itemId);

		// compute the ancestors of this item
		Stack<AbstractCadseViewNode> pathToRoot = new Stack<AbstractCadseViewNode>();
		for (AbstractCadseViewNode itemNode : itemNodes) {
			if (!findPathToRootNode(itemNode, pathToRoot)) {
				return true; // should never happen
			}

			// remove all items on the path if no more necessary
			AbstractCadseViewNode curNode = pathToRoot.pop();
			while (curNode != null) {
				if ((curNode != itemNode) && (curNode.hasChildren())) {
					Item curItem = curNode.getItem();
					_itemsToShow.remove(curItem.getId());
				}

				if (!pathToRoot.isEmpty()) {
					curNode = pathToRoot.pop();
				} else {
					curNode = null;
				}
			}
		}

		return true;
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

	private boolean findPathToRootNode(AbstractCadseViewNode itemNode, Stack<AbstractCadseViewNode> pathToRoot) {
		Item item = itemNode.getItem();

		pathToRoot.push(itemNode);

		AbstractCadseViewNode parentNode = (AbstractCadseViewNode) itemNode.getParent();
		if ((parentNode == null) || item.getType().isRootElement()) {
			return true;
		}

		// first try to navigate to parent
		if (parentNode != null) {
			if (findPathToRootNode(parentNode, pathToRoot)) {
				return true;
			}
		}

		return false;
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
		if (_commitState.isToCommit(itemId)) {
			return false;
		}

		_commitState.addItemToCommit(itemId);
		addItemToShow(itemId);
		visited.markAsVisited(itemId);

		// remove errors
		if (TWUtil.isRequireNewRev(item)) {
			for (Item sourceItem : item.getIncomingItems()) {
				UUID sourceId = sourceItem.getId();
				if (!_commitState.isToCommit(sourceId)) {
					continue;
				}

				// compute list of errors to remove
				List<Error> errorsToRem = new ArrayList<Error>();
				for (Error error : _commitState.getErrors().getErrors(sourceId)) {
					if (!(error instanceof MissCommitError)) {
						continue;
					}

					MissCommitError missError = (MissCommitError) error;
					if (missError.getMissingItemId().equals(itemId)) {
						errorsToRem.add(missError);
					}
				}

				// remove these errors
				for (Error error : errorsToRem) {
					_commitState.getErrors().removeError(sourceId, error);
				}
			}
		}

		/*
		 * Commit destination if destination is NewRev before committing source
		 * 
		 * Formal description: E1.commit() ; (Let E2 :=
		 * E1.r.getDestinations().select()) & (E2.getState() = NewRev) =>
		 * E2.commit(), E1.commit()
		 */
		Set<Item> itemsToCommit = TWUtil.computeLocalItemsToCommit(item);
		for (Item destItem : itemsToCommit) {
			if (visited.hasBeenVisited(destItem)) {
				continue;
			}

			addItemToCommit(destItem, visited);
		}

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
			if (link.getLinkType().isPart() || !link.getLinkType().isAggregation()) {
				continue;
			}

			if (findPathToRoot(link.getSource(), pathToRoot)) {
				return true;
			}
		}

		// finally try all other links
		for (Link link : incomingLinks) {
			if (link.getLinkType().isPart() || link.getLinkType().isAggregation()) {
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

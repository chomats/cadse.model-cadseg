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
package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

import fede.workspace.tool.view.WSPlugin;
import fede.workspace.tool.view.node.AbstractCadseViewNode;
import fede.workspace.tool.view.node.ArrayFilterItem;
import fede.workspace.tool.view.node.FilterItem;
import fede.workspace.tool.view.node.FilteredItemNode;
import fede.workspace.tool.view.node.FilteredItemNodeModel;
import fede.workspace.tool.view.node.ItemNode;
import fede.workspace.tool.view.node.ItemsFromLinkOfLinkTypeRule;
import fede.workspace.tool.view.node.Rule;
import fr.imag.adele.cadse.cadseg.teamwork.commit.CommitDialog.ItemsFromItemTypeWithFilterRule;
import fr.imag.adele.cadse.cadseg.teamwork.db.DBUtil;
import fr.imag.adele.cadse.cadseg.teamwork.db.ItemInDBDesc;
import fr.imag.adele.cadse.cadseg.teamwork.ui.ItemInDBNode;
import fr.imag.adele.cadse.cadseg.teamwork.ui.TWSelfViewContentProvider;
import fr.imag.adele.cadse.cadseg.teamwork.ui.TWUIUtil;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemShortNameComparator;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.AbstractModelController;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.Pages;
import fr.imag.adele.cadse.core.ui.RuningInteractionController;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.UIRunningField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.dialog.SWTDialog;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.ActionController;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DButtonUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DGridUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DSashFormUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.WizardController;
import fr.imag.adele.teamwork.db.ModelVersionDBException;
import fr.imag.adele.teamwork.db.TransactionException;

/**
 * Dialog used for defining update operations to perform. 
 * Informations provided by the user are : 
 * - list of update operation (import, update, revert)
 * - error ignoring confirmations
 * 
 * Informations given by the dialog : 
 * - impacts of global update operations in terms of required update operations
 * - errors found
 * - causes and consequences of each update operation
 * 
 * @author Thomas
 * 
 */
public class UpdateDialogPage extends SWTDialog {

	/*
	 * UI fields.
	 */
	protected DListUI<IC_OperationsForList>			_requirementsField;
	
	protected DButtonUI						_addItemToImportField;
	
	protected DButtonUI						_addItemToUpdateField;
	
	protected DButtonUI						_addItemToRevertField;

	protected DButtonUI						_updateAllItemsField;
	
	protected DButtonUI						_clearAllOpsField;
	
	protected DButtonUI						_computeImpactsField;

	protected DSashFormUI<RuningInteractionController>	_separateRequirementsAndImpactsSashField;

	protected DSashFormUI<RuningInteractionController>	_separateImpactsAndAnalysisSash;

	protected DListUI<IC_OperationsForList>			_impactsField;

	protected DListUI<IC_ErrorsForList>				_errorsField;

	protected DListUI<IC_OperationsFromSelectedImpactForList>			_causesField;

	protected DListUI<IC_OperationsFromSelectedImpactForList>			_consequencesField;

	protected List<UIRunningField<?>>				_selectedImpactDependentFields	= new ArrayList<UIRunningField<?>>();

	/**
	 * Status and definition of update operation.
	 */
	protected final UpdateState				_updateState;

	/**
	 * Listeners interested in selection informations.
	 */
	protected List<OpSelectionListener>	    _selectListeners		= new ArrayList<OpSelectionListener>();

	/*
	 * State of this dialog.
	 */
	protected Operation					    _selectedImpact			= null;

	/*
	 * controller classes.
	 */
	
	static public class ItemsInDBFromItemTypeRule extends Rule {
		Comparator<ItemInDBDesc>	_sortFct	= null;

		public ItemsInDBFromItemTypeRule(Comparator<ItemInDBDesc> sortFct) {
			super();
			_sortFct = sortFct;
		}

		@Override
		public void computeChildren(FilteredItemNode root, AbstractCadseViewNode node, List<AbstractCadseViewNode> ret) {
			Item item = node.getItem();
			if (!TWUtil.isItemType(item))
				return;
			ItemType itemType = (ItemType) item;
			
			if (item != null) {
				Collection<ItemInDBDesc> itemDescs;
				try {
					itemDescs = DBUtil.getAllItemInDBDescs(itemType);
				} catch (Exception e) {
					itemDescs = new HashSet<ItemInDBDesc>();
				}
				
				if (_sortFct != null) {
					TreeSet<ItemInDBDesc> itemDescs2 = new TreeSet<ItemInDBDesc>(_sortFct);
					itemDescs2.addAll(itemDescs);
					itemDescs = itemDescs2;
				}
				for (ItemInDBDesc itemDesc : itemDescs) {
					if (TWUtil.cannotImport(itemDesc.getId(), itemType.getLogicalWorkspace()))
						continue;
						
					ret.add(new ItemInDBNode(root, node, itemDesc.getId(), itemDesc.getName(), itemType));
				}
			}
		}
	}

	public class DefaultMC_AttributesItem extends MC_AttributesItem {

		@Override
		public Item getItem() {
			return null;
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
	
	public class UpdateActionPage extends AbstractActionPage {

		@Override
		public void initAfterUI(UIPlatform uiPlatform) {
			((FilteredTree) _impactsField.getMainControl()).getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
				
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					event.getSelection();
				}
			});
		}
		
		@Override
		public void doFinish(UIPlatform uiPlatform, Object monitor)
				throws Exception {
			// do nothing
		}
	}

	/**
	 * Create the dialog structure.
	 */
	public UpdateDialogPage(UpdateState updateState) {
		super(new SWTUIPlatform(), "Define update operations to perform", "Update Operation Definition");

		// set manipulated data
		_updateState = updateState;

		// create all UI fields
		_requirementsField = createRequirementsField();
		_addItemToImportField = createAddItemToImportField();
		_addItemToUpdateField = createAddItemToUpdateField();
		_addItemToRevertField = createAddItemToRevertField();
		_updateAllItemsField = createUpdateAllItemsField();
		_clearAllOpsField = createClearAllOpsField();
		_computeImpactsField = createComputeImpactsField();
		_impactsField = createImpactsField();
		_errorsField = createErrorsField();
		_causesField = createCausesField();
		_consequencesField = createConsequencesField();
		
		DefaultMC_AttributesItem defaultMc = new DefaultMC_AttributesItem();

		/*
		 * Requirements part
		 */
		_requirementsField._field.setHSpan(1);
		
		DGridUI requirementsListGrid = _swtuiPlatforms.createDGridUI(_page, "#requirementsListPart", "", EPosLabel.none, defaultMc, null, 
				_requirementsField, _computeImpactsField);
		requirementsListGrid._field.setFlag(Item.UI_NO_BORDER, true);
		
		DGridUI requirementsButtonsGrid = _swtuiPlatforms.createDGridUI(_page, "#requirementsButtonPart", "", EPosLabel.none, defaultMc, null, 
				_addItemToImportField, _addItemToUpdateField, _addItemToRevertField, 
				_updateAllItemsField, _clearAllOpsField);
		requirementsButtonsGrid._field.setFlag(Item.UI_NO_BORDER, true);
		
		DGridUI requirementsGrid = _swtuiPlatforms.createDGridUI(_page, "#requirementsPart", "", EPosLabel.none, defaultMc, null, 
				requirementsListGrid, requirementsButtonsGrid);
		requirementsGrid.setColumnNb(2);
		
		/*
		 * Impacts part
		 */
		DGridUI impactsGrid = _swtuiPlatforms.createDGridUI(_page, "#impactsPart", "", EPosLabel.none, defaultMc, null, 
				_impactsField, _errorsField);
		
		DGridUI analysisGrid = _swtuiPlatforms.createDGridUI(_page, "#analysisPart", "", EPosLabel.none, defaultMc, null, 
				_causesField, _consequencesField);

		_selectedImpactDependentFields.add(_causesField);
		_selectedImpactDependentFields.add(_consequencesField);

		_separateImpactsAndAnalysisSash = _swtuiPlatforms.createDSashFormUI(_page, "#selectSash", "", EPosLabel.none, defaultMc, null, 
				impactsGrid, analysisGrid);
		_separateImpactsAndAnalysisSash.setWeight(50); // 50% , 50%
		

		_separateRequirementsAndImpactsSashField = _swtuiPlatforms.createDSashFormUI(_page, "#separateRequirementsAndImpactsSash", "", EPosLabel.none, defaultMc, null, 
				requirementsGrid, _separateImpactsAndAnalysisSash);
		_separateRequirementsAndImpactsSashField.setHorizontal(false);
		// 40%
		// 60%
		_separateRequirementsAndImpactsSashField.setWeight(40);

		// add main field
		addLast(_separateRequirementsAndImpactsSashField);

		// add listeners
		registerListener();
		
		
	}
	
	/**
	 * Called when an impact operation is selected.
	 * 
	 * @param selectedItem
	 *            selected item
	 */
	public void setSelectedImpact(Operation selectedImpact) {
		_selectedImpact = selectedImpact;

		refreshImpactSelectDependentFields();
	}
	
	private DListUI<IC_OperationsFromSelectedImpactForList> createConsequencesField() {

		// retrieve list of update operations
		final IC_OperationsFromSelectedImpactForList ic = new IC_OperationsFromSelectedImpactForList(_updateState, 
				OperationAnalysisCategory.CONSEQUENCE);
		_selectListeners.add(ic);
		
		AbstractModelController mc = new AbstractModelController() {

			@Override
			public Object getValue() {
				return ic.getListOfValues();
			}

			public void notifieValueChanged(UIField field, Object value) {
				// do nothing
			}
		};
		
		return	_swtuiPlatforms.createDListUI(_page, "#listOfConsequences",
				"Consequences", EPosLabel.top, mc, ic, false, false, false, false);
	}

	private DListUI<IC_OperationsFromSelectedImpactForList> createCausesField() {

		// retrieve list of update operations
		final IC_OperationsFromSelectedImpactForList ic = new IC_OperationsFromSelectedImpactForList(_updateState, 
				OperationAnalysisCategory.CAUSE);
		_selectListeners.add(ic);
		
		AbstractModelController mc = new AbstractModelController() {

			@Override
			public Object getValue() {
				return ic.getListOfValues();
			}

			public void notifieValueChanged(UIField field, Object value) {
				// do nothing
			}
		};
		
		return	_swtuiPlatforms.createDListUI(_page, "#listOfCauses",
				"Causes", EPosLabel.top, mc, ic, false, false, false, false);
	}

	private DListUI<IC_ErrorsForList> createErrorsField() {
		AbstractModelController mc = new AbstractModelController() {

			@Override
			public Object getValue() {
				return _updateState.getDefinition().getErrors().getErrors();
			}

			@Override
			public void notifieValueChanged(UIField field, Object value) {
				// read only value
			}
		};
		
		IC_ErrorsForList ic = new IC_ErrorsForList(_updateState, OperationCategory.IMPACTS);
		
		return _swtuiPlatforms.createDListUI(_page, "#errorsField",
				"Errors", EPosLabel.top, mc, ic, false, false, false, false);
	}

	private DListUI<IC_OperationsForList> createImpactsField() {
		AbstractModelController mc = new AbstractModelController() {

			@Override
			public Object getValue() {
				return _updateState.getDefinition().getImpacts();
			}

			public void notifieValueChanged(UIField field, Object value) {
				// do nothing
			}
		};

		// retrieve list of update operations
		IC_OperationsForList ic = new IC_OperationsForList(_updateState, OperationCategory.IMPACTS) {
			@Override
			public void initAfterUI() {
				super.initAfterUI();

				((FilteredTree) _impactsField.getMainControl()).getViewer()
						.addSelectionChangedListener(
								new ISelectionChangedListener() {

									@Override
									public void selectionChanged(
											SelectionChangedEvent event) {
										_selectedImpact = (Operation) ((TreeSelection) event.getSelection()).getFirstElement();
										refreshImpactSelectDependentFields();
									}
								});
			}
		};
		
		DListUI impactsField = _swtuiPlatforms.createDListUI(_page, "#listOfImpacts",
				"Impacts", EPosLabel.top, mc, ic, false, false, false, false);
		return impactsField;
	}

	private DButtonUI createComputeImpactsField() {
		return _swtuiPlatforms.createDButtonUI(_page, "#computeImpactsField", "Compute impacts", EPosLabel.none, null, new ActionController() {

			@Override
			public void callAction() {
				UpdateUtil.computeImpacts(_updateState);
				
				refreshImpactsFields();
			}
		});
	}
	
	private void refreshImpactsFields() {
		_impactsField.resetVisualValue();
		
		refreshImpactSelectDependentFields();
		
		refreshWizardButtons();
	}

	private void refreshImpactSelectDependentFields() {
		for (OpSelectionListener listener : _selectListeners) {
			if (_selectedImpact == null)
				listener.noMoreSelectedOperation();
			else
				listener.selectOperation(_selectedImpact);
		}
		for (UIRunningField<?> field : _selectedImpactDependentFields) {
			field.resetVisualValue();
		}
	}

	private DButtonUI createClearAllOpsField() {
		return _swtuiPlatforms.createDButtonUI(_page, "#clearAlloperationsField", "Clear all operations", EPosLabel.none, null, new ActionController() {

			@Override
			public void callAction() {
				_updateState.getDefinition().clearRequirements();
				refreshRequirements();
			}
		});
	}

	private DButtonUI createUpdateAllItemsField() {
		return _swtuiPlatforms.createDButtonUI(_page, "#updateAllItemsField", "Update All items", EPosLabel.none, null, new ActionController() {

			@Override
			public void callAction() {
				_updateState.getDefinition().clearRequirements();
				
				// compute items to update
				for (Item item : _updateState.getTransaction().getItems()) {
					if (TWUtil.cannotUpdate(item))
						continue;

					_updateState.getDefinition().addItemToUpdate(
							item.getId(), UpdateState.LAST_REV);
				}
				
				refreshRequirements();
			}
		});
	}

	private DButtonUI createAddItemToRevertField() {
		return _swtuiPlatforms.createDButtonUI(_page, "#addItemToRevertField", "Add Item to revert", EPosLabel.none, null, new ActionController() {

					@Override
					public void callAction() {

						Shell parentShell = _swtuiPlatforms.getShell();
						ElementTreeSelectionDialog lsd = new ElementTreeSelectionDialog(
								parentShell, getLabelProvider(),
								getTreeContentProvider());
						ViewerFilter filter = getFilter();
						if (filter != null) {
							lsd.addFilter(filter);
						}
						lsd.setValidator(new ISelectionStatusValidator() {
							public IStatus validate(Object[] selection) {
								if ((selection == null)
										|| (selection.length == 0)) {
									return TWUIUtil.createErrorStatus("You must select at least one item.");
								}

								for (Object select : selection) {
									if (!(select instanceof IItemNode)) {
										return TWUIUtil.createErrorStatus("You must select an item.");
									}

									IItemNode itemNode = (IItemNode) select;
									Item item = itemNode.getItem();
									if (TWUtil.cannotRevert(item)) {
										return TWUIUtil.createErrorStatus("Selected item cannot be reverted.");
									}
								}

								return Status.OK_STATUS;
							}
						});
						lsd.setInput(getInputValues());
						lsd.setAllowMultiple(true);
						lsd.setTitle("Select Items to revert");
						lsd.setMessage("Select Items to revert");

						lsd.open();

						// manage selected items
						Object[] results = lsd.getResult();
						if (results == null) {
							return;
						}
						for (Object selectObj : results) {
							IItemNode itemNode = (IItemNode) selectObj;
							_updateState.getDefinition().addItemToRevert(itemNode.getItem().getId());
						}

						// refresh ui
						refreshRequirements();
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

								return ((IItemNode) element).getItem()
										.getDisplayName();
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
								return WSPlugin.getDefault().getImageFrom(it,
										item);
							}
						};
					}

					protected Object getInputValues() {
						FilteredItemNodeModel model = new FilteredItemNodeModel();

						// roots are all item types
						model.addRule(FilteredItemNodeModel.ROOT_ENTRY,
										new ItemsFromItemTypeWithFilterRule(
												CadseGCST.ITEM_TYPE,
												ItemShortNameComparator.INSTANCE,
												null));

						// children are all items of parent item type
						model.addRule(CadseGCST.ITEM_TYPE,
								new ItemsFromLinkOfLinkTypeRule(
										CadseGCST.ITEM_lt_INSTANCE_OF,
										ItemShortNameComparator.INSTANCE, true,
										true, new FilterItem() {
											public boolean accept(Item item) {
												return TWUtil.isModified(item)
														&& !_updateState
																.getDefinition()
																.hasRequirementOn(item.getId());
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
	 * Refresh all UI fields related to requirements.
	 */
	protected void refreshRequirements() {
		_requirementsField.resetVisualValue();
		
		refreshWizardButtons();
	}

	private void refreshWizardButtons() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (!_updateState.getDefinition().getErrors().hasNotResolvedError() && !_updateState.hasNoOperationToPerform())
					_swtuiPlatforms.setMessage("", IMessageProvider.INFORMATION);
				else
					_swtuiPlatforms.setMessageError("Cannot perform update");
			}
		});
	}

	private DButtonUI createAddItemToUpdateField() {
		return _swtuiPlatforms.createDButtonUI(_page, "#addItemToUpdateField", "Add Item to update", EPosLabel.none, null, new ActionController() {

			@Override
			public void callAction() {

				Shell parentShell = _swtuiPlatforms.getShell();
				AddItemToUpdateDialog lsd = createAddItemToUpdateDialog(parentShell);

				lsd.open();

				// manage selected items
				Object[] results = lsd.getResult();
				if (results == null) {
					return;
				}
				for (Object selectObj : results) {
					IItemNode itemNode = (IItemNode) selectObj;
					_updateState.getDefinition().addItemToUpdate(itemNode.getItem().getId(), lsd.getSelectedRev());
				}

				// refresh UI
				refreshRequirements();
			}

			private AddItemToUpdateDialog createAddItemToUpdateDialog(Shell shell) {
				
				AddItemToUpdateDialog lsd = new AddItemToUpdateDialog(shell, getLabelProvider(), getTreeContentProvider());
				
				ViewerFilter filter = getFilter();
				if (filter != null) {
					lsd.addFilter(filter);
				}
				lsd.setValidator(new ISelectionStatusValidator() {
					public IStatus validate(Object[] selection) {
						if ((selection == null)
								|| (selection.length != 1)) {
							return TWUIUtil.createErrorStatus("You must select at least one item.");
						}

						for (Object select : selection) {
							if (!(select instanceof IItemNode)) {
								return TWUIUtil.createErrorStatus("You must select an item.");
							}

							IItemNode itemNode = (IItemNode) select;
							Item item = itemNode.getItem();
							if (TWUtil.cannotUpdate(item)) {
								return TWUIUtil.createErrorStatus("Selected item cannot be updated.");
							}
						}

						return Status.OK_STATUS;
					}
				});
				lsd.setInput(getInputValues());
				lsd.setAllowMultiple(false);
				lsd.setTitle("Select Items to update");
				lsd.setMessage("Select Items to update");
				return lsd;
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

						return ((IItemNode) element).getItem()
								.getDisplayName();
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
						return WSPlugin.getDefault().getImageFrom(it,
								item);
					}
				};
			}

			protected Object getInputValues() {
				FilteredItemNodeModel model = new FilteredItemNodeModel();

				// roots are all item types
				model.addRule(FilteredItemNodeModel.ROOT_ENTRY,
								new ItemsFromItemTypeWithFilterRule(
										CadseGCST.ITEM_TYPE,
										ItemShortNameComparator.INSTANCE,
										null));

				// children are all items of parent item type
				model.addRule(CadseGCST.ITEM_TYPE,
						new ItemsFromLinkOfLinkTypeRule(
								CadseGCST.ITEM_lt_INSTANCE_OF,
								ItemShortNameComparator.INSTANCE, true,
								true, new FilterItem() {
									public boolean accept(Item item) {
										return !_updateState
														.getDefinition()
														.hasRequirementOn(item.getId());
									}
								}));

				return new FilteredItemNode(null, model);
			}

			protected ViewerFilter getFilter() {
				return null;
			}
			
			
		});
	}

	private DButtonUI createAddItemToImportField() {
		return _swtuiPlatforms.createDButtonUI(_page, "#addItemToImportField", "Add Item to import", EPosLabel.none, null, new ActionController() {

			@Override
			public void callAction() {

				Shell parentShell = _swtuiPlatforms.getShell();
				AddItemToImportDialog lsd = createAddItemToImportDialog(parentShell);

				lsd.open();

				// manage selected items
				Object[] results = lsd.getResult();
				if (results == null) {
					return;
				}
				for (Object selectObj : results) {
					ItemInDBNode itemNode = (ItemInDBNode) selectObj;
					_updateState.getDefinition().addItemToImport(itemNode.getItemId(), itemNode.getName(), lsd.getSelectedRev(), itemNode.getItemType());
				}

				// refresh UI
				refreshRequirements();
			}

			private AddItemToImportDialog createAddItemToImportDialog(Shell shell) {
				
				AddItemToImportDialog lsd = new AddItemToImportDialog(shell, getLabelProvider(), getTreeContentProvider());
				
				ViewerFilter filter = getFilter();
				if (filter != null) {
					lsd.addFilter(filter);
				}
				lsd.setValidator(new ISelectionStatusValidator() {
					public IStatus validate(Object[] selection) {
						if ((selection == null)
								|| (selection.length != 1)) {
							return TWUIUtil.createErrorStatus("You must select at least one item.");
						}

						for (Object select : selection) {
							if (!(select instanceof ItemInDBNode)) {
								return TWUIUtil.createErrorStatus("You must select an item.");
							}

							ItemInDBNode itemNode = (ItemInDBNode) select;
							if (TWUtil.cannotImport(itemNode.getItemId(), _updateState.getTransaction())) {
								return TWUIUtil.createErrorStatus("Selected item cannot be imported.");
							}
						}

						return Status.OK_STATUS;
					}
				});
				lsd.setInput(getInputValues());
				lsd.setAllowMultiple(false);
				lsd.setTitle("Select Items to import");
				lsd.setMessage("Select Items to import");
				return lsd;
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
						if (element instanceof ItemInDBNode) {
							ItemInDBNode itemNode = (ItemInDBNode) element;
							return itemNode.getItemName();
						}

						return ((IItemNode) element).getItem()
								.getDisplayName();
					}

					@Override
					public Image getImage(Object element) {
						if (element instanceof ItemInDBNode) {
							ItemInDBNode itemNode = (ItemInDBNode) element;
							return createImage(itemNode.getItemType(), null);
						}
						if (element instanceof IItemNode) {
							Item item = ((IItemNode) element).getItem();
							return createImage(item.getType(), item);
						}

						return super.getImage(element);
					}

					private Image createImage(ItemType it, Item item) {
						return WSPlugin.getDefault().getImageFrom(it,
								item);
					}
				};
			}

			protected Object getInputValues() {
				FilteredItemNodeModel model = new FilteredItemNodeModel();

				// roots are all item types
				model.addRule(FilteredItemNodeModel.ROOT_ENTRY,
								new ItemsFromItemTypeWithFilterRule(
										CadseGCST.ITEM_TYPE,
										ItemShortNameComparator.INSTANCE,
										null));

				// children are all items of parent item type
				model.addRule(CadseGCST.ITEM_TYPE,
						new ItemsInDBFromItemTypeRule(
								new Comparator<ItemInDBDesc>() {
									@Override
									public int compare(ItemInDBDesc o1,
											ItemInDBDesc o2) {
										return o1.getName().compareTo(o2.getName());
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
	 * Register listener or validator if need
	 */
	protected void registerListener() {
		// do nothing
	}

	/**
	 * Create a list field showing modified attributes.
	 */
	protected DListUI<IC_OperationsForList> createRequirementsField() {
		AbstractModelController mc = new AbstractModelController() {

			@Override
			public Object getValue() {
				return _updateState.getDefinition().getRequirements();
			}

			public void notifieValueChanged(UIField field, Object value) {
				// do nothing
			}
		};

		// retrieve list of update operations
		IC_OperationsForList ic = new IC_OperationsForList(_updateState, OperationCategory.REQUIREMENTS);
		
		return	_swtuiPlatforms.createDListUI(_page, "#listOfRequirements",
				"Requirements", EPosLabel.top, mc, ic, false, false, false, false);
	}
	
	/**
	 * Open Update Definition dialog.
	 * 
	 * @param updateState
	 *            status and definition of update operation
	 * @throws CadseException
	 */
	static public void openDialog(final UpdateState updateState) {

		/**
		 * Create a new display when call getDefault(). Workbench is not
		 * started. This method is called by federation in start level.
		 * 
		*/
				try {
					final UpdateDialogPage p = new UpdateDialogPage(updateState);
					p._swtuiPlatforms.setAction(p.getFinishAction());
					final Pages f = p._swtuiPlatforms.getPages();
					
					WizardController wc = new WizardController(p._swtuiPlatforms) {

						@Override
						public boolean canFinish() {
							if (!super.canFinish()) {
								return false;
							}

							UpdateState updateState = p.getUpdateState();
							return !updateState.getDefinition().getErrors().hasNotResolvedError() && !updateState.hasNoOperationToPerform();
						}

						@Override
						public boolean performFinish() {
							p._updateState.beginUpdate();

							IRunnableWithProgress op = new IRunnableWithProgress() {
								public void run(IProgressMonitor monitor) throws InvocationTargetException,
										InterruptedException {
									try {
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

							p._updateState.abortUpdate();

							return true;
						}
					};
					
					p.open(null, wc);

					// open update progression dialog only if update definition
					// has not been aborted
					if (!p._updateState.isPerformingUpdate()) {
						return;
					}

					// open status dialog
					UpdateStatusDialog.openDialog(p._updateState);
				} catch (Throwable e) {
					e.printStackTrace();
				}
	}
	
	/**
	 * Returns state of the update operation.
	 * 
	 * @return state of the update operation.
	 */
	public UpdateState getUpdateState() {
		return _updateState;
	}
	
	public IActionPage getFinishAction() {
		return new UpdateActionPage();
	}
}

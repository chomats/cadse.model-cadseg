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
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredTree;

import fede.workspace.tool.view.node.AbstractCadseViewNode;
import fede.workspace.tool.view.node.CategoryNode;
import fede.workspace.tool.view.node.FilteredItemNode;
import fede.workspace.tool.view.node.FilteredItemNodeModel;
import fede.workspace.tool.view.node.ItemNode;
import fede.workspace.tool.view.node.Rule;
import fr.imag.adele.cadse.cadseg.teamwork.ui.DecoratingTableLabelProvider;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ChangeID;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
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
import fr.imag.adele.cadse.eclipse.view.SelfViewTableLabelProvider;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.UIRunningField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.dialog.SWTDialog;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_TreeModel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DGridUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DSashFormUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTreeModelUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.WizardController;



/**
 * Dialog used for providing informations about current update operation to user. Status
 * informations provided to the user are : 
 * - order of update operations 
 * - status of each operation (not already performed, 
 * performed for content part, performed for item part or operation failed) 
 * - list of errors
 * 
 * @author Thomas
 * 
 */
public class UpdateStatusDialog extends SWTDialog {

	/*
	 * UI fields.
	 */
	protected DSashFormUI<RuningInteractionController>					_separateRequirementsAndOpsSashField;

	protected DSashFormUI<RuningInteractionController>					_separateOpsAndAnalysisSash;

	protected DTreeModelUI<ItemToUpdateTreeIC>						_operationsField;

	protected DListUI<IC_ErrorsForList>									_errorsField;

	protected DListUI<IC_OperationsFromSelectedImpactForList>			_causesField;

	protected DListUI<IC_OperationsFromSelectedImpactForList>			_consequencesField;

	protected List<UIRunningField<?>>									_selectedOpDependentFields	= new ArrayList<UIRunningField<?>>();

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
	protected Operation					    _selectedOp			= null;
	
	/*
	 * Fields used for synchronization of tree refresh.
	 */
	protected boolean								_refreshListOfUpdatedItems	= true;

	protected Object								_refreshListOfUpdatedItemsLock		= new Object();

	/*
	 * controller classes.
	 */

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
	
	static public class ItemsToUpdateRule extends Rule {
		Comparator<OpToPerform>	sortFct	= null;
		LogicalWorkspace	_lw;
		private FilterOperation	_filter;
		private UpdateState _updateState;

		public ItemsToUpdateRule(LogicalWorkspace lw, Comparator<OpToPerform> sortFct, FilterOperation filter, UpdateState updateState) {
			super();
			this._lw = lw;
			this.sortFct = sortFct;
			_filter = filter;
			_updateState = updateState;
		}

		@Override
		public void computeChildren(FilteredItemNode root, AbstractCadseViewNode node, List<AbstractCadseViewNode> ret) {
			Collection<OpToPerform> values = _updateState.getOperationsToPerform();
			if (sortFct != null) {
				TreeSet<OpToPerform> values2 = new TreeSet<OpToPerform>(sortFct);
				values2.addAll(values);
				values = values2;
			}
			for (OpToPerform op : values) {

				if (!_filter.accept(op)) {
					continue;
				}

				ret.add(new OperationNode(root, node, op));
			}
		}
	}
	
	public class ItemToUpdateTreeIC extends IC_TreeModel {

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
					OpToPerform op = null;
					OperationNode opNode = null;
					if (element instanceof OperationNode) {
						opNode = (OperationNode) element;
						op = opNode.getOperation();
					}
					if (op == null)
						return super.getImage(element);
					
					if (columnIndex == 0) {
						UUID itemId = op.getItemId();
						Item item = _updateState.getTransaction().getItem(itemId);
						if (item == null)
							return null;
						else
							return getColumnImage(new ItemNode(opNode.getCtl(), (AbstractCadseViewNode) opNode.getParent(), item), columnIndex);
					}
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
					OpToPerform op = null;
					OperationNode opNode = null;
					if (element instanceof OperationNode) {
						opNode = (OperationNode) element;
						op = opNode.getOperation();
					}
					if (op == null)
						return super.getText(element);
					
					if (columnIndex == 0) {
						UUID itemId = op.getItemId();
						Item item = _updateState.getTransaction().getItem(itemId);
						if (item == null)
							return null;
						else
							return getColumnText(new ItemNode(opNode.getCtl(), (AbstractCadseViewNode) opNode.getParent(), item), columnIndex);
					}
					if (columnIndex == 1)
						return op.isStateUpdated() ? "Done" : "";
					if (columnIndex == 2)
						return op.isLinksUpdated() ? "Done" : "";
					if (columnIndex == 3)
						return op.isContentUpdated() ? "Done" : "";
					
					return super.getText(element);
				}
			}, new UpdateErrorDecorator(_updateState));
		}

		@Override
		public String canObjectDeselected(Object object) {
			
			return null;
		}

		@Override
		public String canObjectSelected(Object object) {
			return null;
		}

		@Override
		public void select(Object data) {
			OpToPerform op = null;
			OperationNode opNode = null;
			if (data instanceof OperationNode) {
				opNode = (OperationNode) data;
				op = opNode.getOperation();
			}
			if (op == null) {
				selectInvalidTreeItem();
				return;
			}
			
			_uiPlatform.setMessage(null, UIPlatform.NONE);
			setSelectedOperation(op);
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
				model.addRule(FilteredItemNodeModel.ROOT_ENTRY, new ItemsToUpdateRule(_updateState.getTransaction(),
						new Comparator<OpToPerform>() {
							public int compare(OpToPerform op1, OpToPerform op2) {
								int op1Idx = _updateState.getOperationsToPerform().indexOf(op1);
								int op2Idx = _updateState.getOperationsToPerform().indexOf(op2);
								if( op1Idx > op2Idx )
									return 1;
								else if( op1Idx < op2Idx )
									return -1;
								else
									return 0;
							}
						}, new FilterOperation() {
							public boolean accept(Operation op) {
								return _updateState.getOperationsToPerform().contains(op);
							}
						}, _updateState));
			}
			return super.getTreeModel();
		}

		@Override
		protected FilteredItemNode createRootNode() {
			return new FilteredItemNode(null, getTreeModel()) {
				@Override
				public int isSelected(IItemNode node) {
					return ItemToUpdateTreeIC.this.isSelected(node);
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
	
	public class MC_UpdateTree extends AbstractModelController {

		public Object getValue(UIPlatform uiPlatform) {
			return _updateState.getOperationsToPerform();
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
	
	public class UpdateActionPage extends AbstractActionPage {

		@Override
		public void initAfterUI(UIPlatform uiPlatform) {
			((FilteredTree) _operationsField.getMainControl()).getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
				
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
	public UpdateStatusDialog(UpdateState updateState) {
		super(new SWTUIPlatform(), "Update operations in progress", "Update Operation Status");

		// set manipulated data
		_updateState = updateState;

		// create all UI fields
		_operationsField = createOperationsField();
		_errorsField = createErrorsField();
		_causesField = createCausesField();
		_consequencesField = createConsequencesField();
		
		DefaultMC_AttributesItem defaultMc = new DefaultMC_AttributesItem();

		/*
		 * Operation Performed part
		 */
		DGridUI operationsGrid = _swtuiPlatforms.createDGridUI(_page, "#opsPart", "", EPosLabel.none, defaultMc, null, 
				_operationsField, _errorsField);
		
		DGridUI analysisGrid = _swtuiPlatforms.createDGridUI(_page, "#analysisPart", "", EPosLabel.none, defaultMc, null, 
				_causesField, _consequencesField);

		_selectedOpDependentFields.add(_causesField);
		_selectedOpDependentFields.add(_consequencesField);

		_separateOpsAndAnalysisSash = _swtuiPlatforms.createDSashFormUI(_page, "#selectSash", "", EPosLabel.none, defaultMc, null, 
				operationsGrid, analysisGrid);
		_separateOpsAndAnalysisSash.setWeight(50); // 50% , 50%
		
		// add main field
		addLast(_separateOpsAndAnalysisSash);

		// add listeners
		registerListener();
		
		
	}
	
	/**
	 * Called when an operation is selected.
	 * 
	 * @param selectedItem
	 *            selected item
	 */
	public void setSelectedOperation(Operation selectedOp) {
		_selectedOp = selectedOp;

		refreshOpSelectDependentFields();
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

	private DTreeModelUI<ItemToUpdateTreeIC> createOperationsField() {
		
		DTreeModelUI<ItemToUpdateTreeIC> treeField = _swtuiPlatforms.createTreeModelUI(
				_page, "#listOfUpdatedItems", "Items which are updating", EPosLabel.top,
				new MC_UpdateTree(), new ItemToUpdateTreeIC(), false, true, new String[] { "Item", "Attributes", "Links", "Content" });
		return treeField;
	}
	
	private void refreshImpactsFields() {
		_operationsField.resetVisualValue();
		
		refreshOpSelectDependentFields();
		
		refreshWizardButtons();
	}

	private void refreshOpSelectDependentFields() {
		for (OpSelectionListener listener : _selectListeners) {
			if (_selectedOp == null)
				listener.noMoreSelectedOperation();
			else
				listener.selectOperation(_selectedOp);
		}
		for (UIRunningField<?> field : _selectedOpDependentFields) {
			field.resetVisualValue();
		}
	}

	
	
	private void refreshWizardButtons() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (_updateState.isFailed())
					_swtuiPlatforms.setMessageError("Cannot perform update");
				else 
					_swtuiPlatforms.setMessage("", IMessageProvider.INFORMATION);
			}
		});
	}
	
	/**
	 * Refresh the list used of updated items.
	 */
	private void refreshListOfUpdatedItems(boolean forceRefresh) {
		if (!forceRefresh && !_refreshListOfUpdatedItems) {
			return;
		}

		synchronized (_refreshListOfUpdatedItemsLock) {
			SelfViewContentProvider contentProvider = (SelfViewContentProvider) _operationsField._ic
					.getContentProvider();
			contentProvider.notifieChangeEvent(null);
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					_operationsField.getTreeViewer().refresh();
				}
			});
			_refreshListOfUpdatedItems = false;
		}
	}

	/**
	 * Register listener or validator if need
	 */
	protected void registerListener() {
		// do nothing
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
					final UpdateStatusDialog p = new UpdateStatusDialog(updateState);
					p._swtuiPlatforms.setAction(p.getFinishAction());
					final Pages f = p._swtuiPlatforms.getPages();
					
					WizardController wc = new WizardController(p._swtuiPlatforms) {

						@Override
						public boolean canFinish() {
							if (!super.canFinish()) {
								return false;
							}

							UpdateState updateState = p.getUpdateState();
							return !updateState.isPerformingUpdate();
						}

						@Override
						public boolean performFinish() {

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
					
					// begin effective commit operation
					updateState.addListener(new UpdateListener() {

						
						@Override
						public void updateFail() {
							PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
								public void run() {
									p._swtuiPlatforms.setMessageError("Update failed !");
								}
							});
						}

						@Override
						public void endUpdate() {
							
							// if update is OK, commit workspace logical copy
							LogicalWorkspaceTransaction transaction = updateState.getTransaction();
							if (updateState.isUpdatePerformed()) {
								try {
									transaction.commit();
								} catch (CadseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
									public void run() {
										p._swtuiPlatforms.setMessage("Update succeed !", IMessageProvider.INFORMATION);
									}
								});
							} else {
								transaction.rollback();
								PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
									public void run() {
										p._swtuiPlatforms.setMessageError("Update failed !");
									}
								});
							}
						}

						@Override
						public void endUpdateItem(UUID itemId) {
							refreshListOfCommitedItems();
						}

						@Override
						public void endUpdateItemContent(UUID itemId) {
							refreshListOfCommitedItems();
						}

						@Override
						public void endUpdateItemLinks(UUID itemId) {
							refreshListOfCommitedItems();
						}

						@Override
						public void endUpdateItemState(UUID itemId) {
							refreshListOfCommitedItems();
						}

						private void refreshListOfCommitedItems() {
							p.refreshListOfUpdatedItems(true);
						}

						@Override
						public void beginUpdate() {
							// do nothing
						}

						@Override
						public void beginUpdatingItem(UUID itemId) {
							// do nothing
						}
						
					});
					UpdateThread updateThread = new UpdateThread(updateState);
					updateThread.start();
					
					p.open(null, wc);
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

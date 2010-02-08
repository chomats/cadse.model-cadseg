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
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

import fede.workspace.tool.view.WSPlugin;
import fede.workspace.tool.view.node.AbstractCadseViewNode;
import fede.workspace.tool.view.node.FilterItem;
import fede.workspace.tool.view.node.FilteredItemNode;
import fede.workspace.tool.view.node.FilteredItemNodeModel;
import fede.workspace.tool.view.node.ItemsFromLinkOfLinkTypeRule;
import fede.workspace.tool.view.node.Rule;
import fr.imag.adele.cadse.cadseg.teamwork.commit.CommitDialog.ItemsFromItemTypeWithFilterRule;
import fr.imag.adele.cadse.cadseg.teamwork.db.DBUtil;
import fr.imag.adele.cadse.cadseg.teamwork.db.ItemInDBDesc;
import fr.imag.adele.cadse.cadseg.teamwork.ui.ItemInDBNode;
import fr.imag.adele.cadse.cadseg.teamwork.ui.TWSelfViewContentProvider;
import fr.imag.adele.cadse.cadseg.teamwork.ui.TWUIUtil;
import fr.imag.adele.cadse.cadseg.teamwork.update.UpdateDialogPage.DefaultMC_AttributesItem;
import fr.imag.adele.cadse.cadseg.teamwork.update.UpdateDialogPage.ItemsInDBFromItemTypeRule;
import fr.imag.adele.cadse.cadseg.teamwork.update.UpdateDialogPage.UpdateActionPage;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemShortNameComparator;
import fr.imag.adele.cadse.core.ItemType;
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
	protected DSashFormUI<RuningInteractionController>	_separateRequirementsAndImpactsSashField;

	protected DSashFormUI<RuningInteractionController>	_separateImpactsAndAnalysisSash;

	protected DListUI<IC_OperationsForList>			_operationsField;

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
		super(new SWTUIPlatform(), "Update operations to perform", "Update Operation Status");

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

		_selectedImpactDependentFields.add(_causesField);
		_selectedImpactDependentFields.add(_consequencesField);

		_separateImpactsAndAnalysisSash = _swtuiPlatforms.createDSashFormUI(_page, "#selectSash", "", EPosLabel.none, defaultMc, null, 
				operationsGrid, analysisGrid);
		_separateImpactsAndAnalysisSash.setWeight(50); // 50% , 50%
		
		// add main field
		addLast(_separateImpactsAndAnalysisSash);

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

	private DListUI<IC_OperationsForList> createOperationsField() {
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

				((FilteredTree) _operationsField.getMainControl()).getViewer()
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
	
	private void refreshImpactsFields() {
		_operationsField.resetVisualValue();
		
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

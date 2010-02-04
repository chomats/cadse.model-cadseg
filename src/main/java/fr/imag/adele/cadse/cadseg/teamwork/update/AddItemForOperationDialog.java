package fr.imag.adele.cadse.cadseg.teamwork.update;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

import fr.imag.adele.teamwork.db.ModelVersionDBException;
import fr.imag.adele.teamwork.db.TransactionException;

public abstract class AddItemForOperationDialog extends ElementTreeSelectionDialog {

	protected int _selectedRev;
	protected CCombo _combo;
	protected Text _stateText;
	
	public AddItemForOperationDialog(Shell parent, ILabelProvider labelProvider,
			ITreeContentProvider contentProvider) {
		super(parent, labelProvider, contentProvider);
	}

	@Override
	protected TreeViewer createTreeViewer(Composite parent) {
		TreeViewer treeViewer = super.createTreeViewer(parent);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				computeSelectedItem(((TreeSelection) event
						.getSelection()).getFirstElement());
				selectedItemHasChanged();
			}
		});

		return treeViewer;
	}
	
	protected abstract void computeSelectedItem(Object selectedNode);

	@Override
	protected Control createDialogArea(Composite parent) {
		SashForm sash = new SashForm(parent, SWT.BORDER | SWT.VERTICAL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		sash.setLayoutData(gd);
		
		Composite dialogArea = (Composite) super.createDialogArea(sash);
		
		Composite selectDependentComposite = new Composite(sash, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		selectDependentComposite.setLayout(layout);
		
		gd = new GridData(GridData.FILL_HORIZONTAL);
		selectDependentComposite.setLayoutData(gd);

		// label of combo box
		Label label = new Label(selectDependentComposite, SWT.SINGLE);
		label.setText("Revision");

		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalSpan = 1;
		label.setLayoutData(gd);
		label.pack();
		
		// combo of revisions
		_combo = new CCombo(selectDependentComposite, SWT.BORDER | SWT.READ_ONLY);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;
		_combo.setLayoutData(gd);
		_combo.setEditable(true);
		_combo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				computeSelectedRev();
				selectedRevHasChanged();
			}

		});
		_combo.pack();
		
		// label of combo box
		Label statelabel = new Label(selectDependentComposite, SWT.SINGLE);
		statelabel.setText("Item Revision State");

		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalSpan = 2;
		statelabel.setLayoutData(gd);
		statelabel.pack();
		
		// text area of item revision state
		_stateText = new Text(selectDependentComposite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		_stateText.setEnabled(true);
		_stateText.setEditable(false);
		
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		gd.horizontalSpan = 2;
		gd.verticalSpan = 3;
		gd.verticalAlignment = SWT.FILL;
		_stateText.setLayoutData(gd);
		_stateText.pack();
		
		selectDependentComposite.pack();
		
		sash.setWeights(new int[] { 50, 50 });
		sash.pack();

		return sash;
	}
	
	protected void computeSelectedRev() {
		int selectedIdx = _combo.getSelectionIndex();
		if (selectedIdx < 0) {
			_selectedRev = 0;
		} else {
			try {
				_selectedRev = Integer.parseInt(_combo.getItem(selectedIdx));
			} catch (NumberFormatException e) {
				_selectedRev = 0;
			}
		}
	}

	protected void selectedRevHasChanged() {
		String stateStr = "No Selected Revision.";
		if ((getSelectedItemObj() != null) && (_selectedRev > 0)) {
			
			try {
				stateStr = getRevisionStateStr();
			} catch (Exception e) {
				stateStr = "Unable to retrieve state of selected revision.";
			}
		}
		_stateText.setText(stateStr);
	}

	protected abstract String getRevisionStateStr() throws TransactionException,
			ModelVersionDBException; 

	private void selectedItemHasChanged() {
		_selectedRev = 0;

		int[] values = new int[0];
		if (getSelectedItemObj() != null) {
			try {
				values = getAllRevisions();
			} catch (Exception e) {
				values = new int[0];
			}
		}

		String[] valuesString = new String[values.length];
		for (int i = 0; i < valuesString.length; i++) {
			valuesString[i] = Integer.toString(values[i]);
		}
		_combo.setItems(valuesString);
		if (values.length != 0) {
			_combo.select(0);
		}
		
		if (getSelectedItemObj() == null) {
			_combo.setEnabled(false);
			_combo.setText("No selected item");
		} else if (values.length == 0) {
			_combo.setEnabled(false);
			_combo.setText("No revision found");
		} else {
			_combo.setEnabled(true);
		}
		
		computeSelectedRev();
		selectedRevHasChanged();
	}

	protected abstract int[] getAllRevisions() throws TransactionException,
			ModelVersionDBException;
	
	/**
	 * Returns revision number selected of selected item.
	 * Returns 0 if no item has been selected.
	 * 
	 * @return revision number selected of selected item.
	 */
	public int getSelectedRev() {
		return _selectedRev;
	}
	
	protected abstract Object getSelectedItemObj();
}

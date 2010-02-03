package fr.imag.adele.cadse.cadseg.teamwork.update;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

import fr.imag.adele.cadse.cadseg.teamwork.db.DBUtil;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;

public class AddItemToUpdateDialog extends ElementTreeSelectionDialog {

	private Item _selectedItem;
	private int _selectedRev;
	private CCombo _combo;
	
	public AddItemToUpdateDialog(Shell parent, ILabelProvider labelProvider,
			ITreeContentProvider contentProvider) {
		super(parent, labelProvider, contentProvider);
	}

	@Override
	protected TreeViewer createTreeViewer(Composite parent) {
		TreeViewer treeViewer = super.createTreeViewer(parent);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IItemNode selectedNode = (IItemNode) ((TreeSelection) event
						.getSelection()).getFirstElement();
				if (selectedNode != null)
					_selectedItem = selectedNode.getItem();
				else
					_selectedItem = null;
				selectedItemHasChanged();
			}
		});

		return treeViewer;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialog = (Composite) super.createDialogArea(parent);

		Composite composite = new Composite(dialog, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		composite.setLayoutData(gd);

		// label of combo box
		Label label = new Label(composite, SWT.SINGLE);
		label.setText("Revision");

		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalSpan = 1;
		label.setLayoutData(gd);
		label.pack();
		
		_combo = new CCombo(composite, SWT.BORDER | SWT.READ_ONLY);

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
				int selectedIdx = _combo.getSelectionIndex();
				if (selectedIdx < 0) {
					_selectedRev = 0;
					return;
				}

				_selectedRev = Integer.parseInt(_combo.getItem(selectedIdx));
			}

		});
		_combo.pack();
		
		composite.pack();

		return dialog;
	}

	private void selectedItemHasChanged() {

		int[] values = new int[0];
		if (_selectedItem != null) {
			try {
				values = DBUtil.getAllRevisions(_selectedItem);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				values = new int[0];
			}
		}

		String[] valuesString = new String[values.length];
		for (int i = 0; i < valuesString.length; i++) {
			valuesString[i] = Integer.toString(values[i]);
		}
		_combo.setItems(valuesString);
		if (values.length != 0)
			_combo.select(0);
		
		if (_selectedItem == null) {
			_combo.setEnabled(false);
			_combo.setText("No selected item");
		} else {
			_combo.setEnabled(true);
		}
	}
	
	/**
	 * Returns revision number selected of selected item.
	 * Returns 0 if no item has been selected.
	 * 
	 * @return revision number selected of selected item.
	 */
	public int getSelectedRev() {
		return _selectedRev;
	}
}

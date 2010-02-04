package fr.imag.adele.cadse.cadseg.teamwork.update;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.widgets.Shell;

import fr.imag.adele.cadse.cadseg.teamwork.db.DBUtil;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.teamwork.db.ModelVersionDBException;
import fr.imag.adele.teamwork.db.TransactionException;

public class AddItemToUpdateDialog extends AddItemForOperationDialog {
	
	protected Item _selectedItem;
	
	public AddItemToUpdateDialog(Shell parent, ILabelProvider labelProvider,
			ITreeContentProvider contentProvider) {
		super(parent, labelProvider, contentProvider);
	}
	
	protected void computeSelectedItem(Object selectedNode) {
		IItemNode selectedItemNode = (IItemNode) selectedNode;
		if (selectedNode != null)
			_selectedItem = selectedItemNode.getItem();
		else
			_selectedItem = null;
	}

	@Override
	protected Item getSelectedItemObj() {
		return _selectedItem;
	}

	protected String getRevisionStateStr() throws TransactionException,
			ModelVersionDBException {
		return DBUtil.getRevisionStateStr(_selectedItem, _selectedRev);
	}
	
	protected int[] getAllRevisions() throws TransactionException,
			ModelVersionDBException {
		return DBUtil.getAllRevisions(_selectedItem);
	}
}

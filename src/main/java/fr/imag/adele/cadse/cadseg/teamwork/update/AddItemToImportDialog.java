package fr.imag.adele.cadse.cadseg.teamwork.update;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.widgets.Shell;

import fr.imag.adele.cadse.cadseg.teamwork.db.DBUtil;
import fr.imag.adele.cadse.cadseg.teamwork.db.ItemInDBDesc;
import fr.imag.adele.cadse.cadseg.teamwork.ui.ItemInDBNode;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.teamwork.db.ModelVersionDBException;
import fr.imag.adele.teamwork.db.TransactionException;

public class AddItemToImportDialog extends AddItemForOperationDialog {

	protected ItemInDBDesc _selectedItemDesc;
	protected ItemType _selectedItemType;
	
	public AddItemToImportDialog(Shell parent, ILabelProvider labelProvider,
			ITreeContentProvider contentProvider) {
		super(parent, labelProvider, contentProvider);
	}
	
	protected void computeSelectedItem(Object selectedNode) {
		ItemInDBNode selectedItemNode = null;
		if (selectedNode instanceof ItemInDBNode) {
			selectedItemNode = (ItemInDBNode) selectedNode;
		}
		if (selectedItemNode != null) {
			_selectedItemDesc = new ItemInDBDesc(selectedItemNode.getItemId(), selectedItemNode.getItemName());
			_selectedItemType = selectedItemNode.getItemType();
		} else {
			_selectedItemDesc = null;
			_selectedItemType = null;
		}
	}
	
	protected ItemInDBDesc getSelectedItemObj() {
		return _selectedItemDesc;
	}
	
	protected String getRevisionStateStr() throws TransactionException,
			ModelVersionDBException {
		return DBUtil.getRevisionStateStr(_selectedItemDesc.getId(), _selectedItemType, _selectedRev);
	}
	
	protected int[] getAllRevisions() throws TransactionException,
			ModelVersionDBException {
		return DBUtil.getAllRevisions(_selectedItemDesc.getId(), _selectedItemType);
	}
}

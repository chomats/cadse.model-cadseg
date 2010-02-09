package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.UUID;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.teamwork.db.ModelVersionDBService;
import fr.imag.adele.teamwork.db.TransactionException;

/**
 * Thread used to perform the effective update operation.
 * 
 * @author Thomas
 * 
 */
public class UpdateThread extends Thread {

	private UpdateState			_updateState;
	private LogicalWorkspace	_wl;
	
	public UpdateThread(UpdateState updateState) {
		super("TWUpdate");
		_updateState = updateState;
		_wl = updateState.getTransaction();
	}
	
	@Override
	public void run() {
		super.run();

		ModelVersionDBService db = CadseCore.getCadseDomain().getModelVersionDBService();
		
		// commit new state of items without outgoing links
		int i = 0;
		int itemToUpdateNb = _updateState.getOperationsToPerform().size();
		while ((i < itemToUpdateNb) && !_updateState.isFailed() && !_updateState.isUpdatePerformed()) {
			OpToPerform op = _updateState.getOperationsToPerform().get(i);
			UUID itemId = op.getItemId();
			_updateState.beginUpdatingItem(itemId);
			
			try {
				updateItemState(op, db);
			} catch (Exception e) {
				_updateState.getOperationsToPerformErrors().addError(itemId, "Cannot update Item State.");
				_updateState.abortUpdate();
				e.printStackTrace();
				break;
			}
			
			_updateState.markStateAsUpdated(itemId);

			i++;
		}

		// commit outgoing links
		i = 0;
		while ((i < itemToUpdateNb) && !_updateState.isFailed() && !_updateState.isUpdatePerformed()) {
			OpToPerform op = _updateState.getOperationsToPerform().get(i);
			UUID itemId = op.getItemId();

			try {
				updateItemLinks(op, db);
			} catch (Exception e) {
				_updateState.getOperationsToPerformErrors().addError(itemId, "Cannot update Item ougoing links.");
				_updateState.abortUpdate();
				e.printStackTrace();
			}
			
			_updateState.markLinksAsUpdated(itemId);
			
			i++;
		}

		// commit contents
		i = 0;
		while ((i < itemToUpdateNb) && !_updateState.isFailed() && !_updateState.isUpdatePerformed()) {
			OpToPerform op = _updateState.getOperationsToPerform().get(i);
			UUID itemId = op.getItemId();

			try {
				//TODO implement it
			} catch (Exception e) {
				_updateState.getOperationsToPerformErrors().addError(itemId, "Cannot update Item content.");
				_updateState.abortUpdate();
				e.printStackTrace();
			}
			
			_updateState.markContentsAsUpdated(itemId);
			
			i++;
		}
		
		// reset modified state
		i = 0;
		while ((i < itemToUpdateNb) && !_updateState.isFailed()) {
			OpToPerform op = _updateState.getOperationsToPerform().get(i);
			UUID itemId = op.getItemId();

			try {
				if (!op.isUpdate())
					TWUtil.resetTWState(_wl.getItem(itemId));
			} catch (Exception e) {
				_updateState.abortUpdate();
				e.printStackTrace();
			}
			
			// notify end of item commit
			_updateState.markAsUpdated(itemId);

			i++;
		}
		
		// finish transaction
		try {
			if (!_updateState.isFailed()) {
				_updateState.endUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			_updateState.abortUpdate();
		}
	}

	private void updateItemLinks(OpToPerform op, ModelVersionDBService db) {
		UUID itemId = op.getItemId();
		
		//TODO implement it
	}

	private void updateItemState(OpToPerform op, ModelVersionDBService db) {
		UUID itemId = op.getItemId();
		
		if (op.isRevert()) {
			
		}
		
		//TODO implement it
	}
}

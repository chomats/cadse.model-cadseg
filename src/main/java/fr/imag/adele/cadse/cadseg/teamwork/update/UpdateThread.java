package fr.imag.adele.cadse.cadseg.teamwork.update;

import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.teamwork.db.ModelVersionDBService;

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
}

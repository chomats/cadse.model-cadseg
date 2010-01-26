package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class UpdateState {
	
	private Map<UUID, UpdateOperation> _updatesMap = new HashMap<UUID, UpdateOperation>();

	public static final int LAST_REV = -1;

	public void addItemToRevert(UUID id) {
		// TODO Auto-generated method stub
		
	}

	public void addItemToUpdate(UUID id, int toRev) {
		// TODO Auto-generated method stub
		
	}

	public void beginUpdate() {
		// TODO Auto-generated method stub
		
	}

	public boolean hasNoUpdateOperation() {
		// TODO Auto-generated method stub
		return false;
	}

	public void abortUpdate() {
		// TODO Auto-generated method stub
		
	}

	public boolean isPerformingUpdate() {
		// TODO Auto-generated method stub
		return false;
	}

}

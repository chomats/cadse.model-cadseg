package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.UUID;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;

/**
 * Represents definition of an update operation.
 * 
 * @author Thomas
 *
 */
public class UpdateOperation extends Operation {
	
	private int _toRev;

	public UpdateOperation(UUID itemId, int toRev, boolean isRequirement) {
		super(itemId, OperationType.UPDATE, isRequirement);
		_toRev = toRev;
	}
	
	/**
	 * Returns destination item revision number.
	 * 
	 * @return destination item revision number.
	 */
	public int getDestinationRevNb() {
		return _toRev;
	}
	
	@Override
	public String getRequirementDisplay(LogicalWorkspace lw) {
		return "Update of " + getQualifiedItemName(lw) + "(" + 
		getDisplayOfRev(lw.getItem(getItemId()).getVersion()) + " to " + getDisplayOfRev(getDestinationRevNb()) + ")";
	}

	@Override
	public Operation clone() {
		return new UpdateOperation(getItemId(), _toRev, isRequirement());
	}
}

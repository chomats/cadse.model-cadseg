package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.UUID;

import fr.imag.adele.cadse.core.LogicalWorkspace;

/**
 * Represents definition of a revert operation of an item.
 * 
 * @author Thomas
 *
 */
public class RevertOperation extends Operation {

	public RevertOperation(UUID itemId, boolean isRequirement) {
		super(itemId, OperationType.REVERT, isRequirement);
	}

	@Override
	public String getRequirementDisplay(LogicalWorkspace lw) {
		return "Revert of " + getQualifiedItemName(lw);
	}
	
	@Override
	public Operation clone() {
		return new RevertOperation(getItemId(), isRequirement());
	}
}

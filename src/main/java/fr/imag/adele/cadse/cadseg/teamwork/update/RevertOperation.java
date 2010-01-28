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

	public RevertOperation(UUID itemId) {
		super(itemId, OperationType.REVERT);
	}

	@Override
	public String getRequirementDisplay(LogicalWorkspace lw) {
		return "Revert of " + getQualifiedItemName(lw);
	}
}

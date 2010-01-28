package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.UUID;

import fr.imag.adele.cadse.core.LogicalWorkspace;

/**
 * Represents definition of an item import operation.
 * 
 * @author Thomas
 *
 */
public class ImportOperation extends Operation {
	
	private int _rev;

	public ImportOperation(UUID itemId, int rev) {
		super(itemId, OperationType.IMPORT);
		_rev = rev;
	}

	/**
	 * Returns revision number of item version to import.
	 * 
	 * @return revision number of item version to import.
	 */
	public int getDestinationRevNb() {
		return _rev;
	}

	@Override
	public String getRequirementDisplay(LogicalWorkspace lw) {
		return "Import of " + getQualifiedItemName(lw) + "(" + _rev + ")";
	}
}
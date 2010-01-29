package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.UUID;

import fr.imag.adele.cadse.core.LogicalWorkspace;

/**
 * Represents an operation (import, update or revert) of a global update operation.
 * 
 * @author Thomas
 *
 */
public abstract class Operation {
	
	protected enum OperationType { REVERT, UPDATE, IMPORT }

	private UUID _itemId;
	private OperationType _opType;
	
	protected Operation(UUID itemId, OperationType opType) {
		_itemId = itemId;
		_opType = opType;
	}
	
	/**
	 * Return id of item related to this operation.
	 * 
	 * @return id of item related to this operation.
	 */
	public UUID getItemId() {
		return _itemId;
	}
	
	/**
	 * Return true if this operation is a revert one.
	 * 
	 * @return true if this operation is a revert one.
	 */
	public boolean isRevert() {
		return OperationType.REVERT.equals(_opType);
	}
	
	/**
	 * Return true if this operation is an update one.
	 * 
	 * @return true if this operation is an update one.
	 */
	public boolean isUpdate(){
		return OperationType.UPDATE.equals(_opType);
	}
	
	/**
	 * Return true if this operation is an import one.
	 * 
	 * @return true if this operation is an import one.
	 */
	public boolean isImport() {
		return OperationType.IMPORT.equals(_opType);
	}

	/**
	 * Returns a human readable label for this operation. 
	 * 
	 * @param lw the logical workspace
	 * @return a human readable label for this operation. 
	 */
	public abstract String getRequirementDisplay(LogicalWorkspace lw);
	
	/**
	 * Returns qualified name of item related to this operation.
	 * 
	 * @param lw a logical workspace
	 * @return qualified name of item related to this operation.
	 */
	protected String getQualifiedItemName(LogicalWorkspace lw) {
		return lw.getItem(getItemId()).getQualifiedDisplayName();
	}
	
	/**
	 * Returns human readable revision number of specified revision.
	 * 
	 * @param rev revision number
	 * @return human readable revision number of specified revision.
	 */
	protected String getDisplayOfRev(int rev) {
		if (rev == UpdateState.LAST_REV)
			return "HEAD";
		if (rev == 0)
			return "No";
		
		return Integer.toString(rev);
	}
}
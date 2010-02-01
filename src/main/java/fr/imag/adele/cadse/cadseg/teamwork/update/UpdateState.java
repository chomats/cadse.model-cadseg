package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.List;

import fr.imag.adele.cadse.cadseg.teamwork.Errors;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;

/**
 * Represents state of a global update operation which can be constituted of
 * - revert operations
 * - update operations
 * - import operations
 * This state includes
 * - original requirements
 * - current status of the global update operation.
 * 
 * @author Thomas
 *
 */
public class UpdateState {
	
	private UpdateDefinition _updateDefinition = new UpdateDefinition();
	
	private LogicalWorkspaceTransaction _transaction = CadseCore.getLogicalWorkspace().createTransaction();
	
	private Errors _errors = new Errors();
	
	private boolean _performUpdate = false;
	
	private boolean _updatePerformed = false;
	
	private boolean _failed = false;

	/**
	 * Return definition of this global update operation.
	 * 
	 * @return definition of this global update operation.
	 */
	public UpdateDefinition getDefinition() {
		return _updateDefinition;
	}

	public static final int LAST_REV = -1;
	
	

	public void beginUpdate() {
		_performUpdate = true;
		
		//TODO notify listeners
	}

	/**
	 * Returns true if there is no operation to perform for this update operation.
	 * The possible causes are 
	 * - no requirements
	 * - impacts of requirements has not been computed.
	 * 
	 * @return true if there is no operation to perform for this update operation.
	 */
	public boolean hasNoOperationToPerform() {
		List<Operation> impacts = _updateDefinition.getImpacts();
		
		return ((impacts == null) || impacts.isEmpty());
	}

	/**
	 * Aborts the update operation.
	 */
	public void abortUpdate() {
		_failed = true;
		_performUpdate = false;
		_updatePerformed = true;
		
		//TODO notify listeners
	}
	
	/**
	 * Marks this update operation as completed.
	 */
	public void endUpdate() {
		_failed = false;
		_performUpdate = false;
		_updatePerformed = true;
		
		//TODO notify listeners
	}

	/**
	 * Returns true if update operation is performing.
	 * 
	 * @return true if update operation is performing.
	 */
	public boolean isPerformingUpdate() {
		return _performUpdate;
	}
	
	/**
	 * Return true if update operation has failed.
	 * 
	 * @return true if update operation has failed.
	 */
	public boolean isFailed() {
		return _failed;
	}
	
	/**
	 * Return true if update operation has been performed successfully.
	 * 
	 * @return true if update operation has been performed successfully.
	 */
	public boolean isCommitPerformed() {
		return _updatePerformed && !_failed;
	}

	/**
	 * Returns transaction in which all update operations will be performed.
	 * 
	 * @return transaction in which all update operations will be performed.
	 */
	public LogicalWorkspaceTransaction getTransaction() {
		return _transaction;
	}

	/**
	 * Returns all operations to perform for this global update operation.
	 * 
	 * @return all operations to perform for this global update operation.
	 */
	public List<Operation> getOperationsToPerform() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns all errors which have appeared during the update operation.
	 * 
	 * @return all errors which have appeared during the update operation.
	 */
	public Errors getOperationsToPerformErrors() {
		return _errors;
	}
	
	/**
	 * Remove all errors.
	 */
	public void clearErrors() {
		_errors.clear();
	}

}

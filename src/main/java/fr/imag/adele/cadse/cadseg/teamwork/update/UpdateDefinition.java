package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import fr.imag.adele.cadse.cadseg.teamwork.Errors;
import fr.imag.adele.cadse.core.ItemType;

/**
 * Represents definition of a global update operation.
 * 
 * @author Thomas
 *
 */
public class UpdateDefinition {
	
	/**
	 * Used to keep order or requirements.
	 */
	private List<Operation> _requirementsList = new ArrayList<Operation>();

	private Map<UUID, Operation> _requirements = new HashMap<UUID, Operation>();
	
	/**
	 * Used to order or impacts.
	 */
	private List<Operation> _impactsList = new ArrayList<Operation>();

	private Map<UUID, Operation> _impacts = new HashMap<UUID, Operation>();
	
	/**
	 * Errors in definition of the update operation.
	 */
	private Errors _errors = new Errors();

	/**
	 * Return all requirements for this global update operation.
	 * 
	 * @return all requirements for this global update operation.
	 */
	public List<Operation> getRequirements() {
		return _requirementsList;
	}
	
	/**
	 * Remove all requirements of this update operation.
	 */
	public void clearRequirements() {
		_requirementsList.clear();
		_requirements.clear();
	}
	
	/**
	 * Remove all impacts of this update operation.
	 */
	public void clearImpacts() {
		_impactsList.clear();
		_impacts.clear();
	}

	/**
	 * Add to requirements specified revert of specified item.
	 * 
	 * @param itemId id of item to revert
	 * @throws IllegalArgumentException if it already exists an operation for specified item.
	 */
	public void addItemToRevert(UUID itemId) {
		Operation newOp = new RevertOperation(itemId, true);
		checkNoOtherOp(itemId, newOp, false);
		
		_requirements.put(itemId, newOp);
		_requirementsList.add(newOp);
	}

	/**
	 * Throws IllegalArgumentException if it exists an operation for specified item.
	 * 
	 * @param itemId  an item id
	 * @param opToAdd the operation to add
	 * @param isImpact if true, check if an impact operation already exists for specified item
	 *                 else check if an requirement operation already exists for specified item
	 * @throws IllegalArgumentException if it already exists an operation for specified item.
	 */
	private void checkNoOtherOp(UUID itemId, Operation opToAdd, boolean isImpact) {
		Operation op = null;
		if (isImpact)
			op = _impacts.get(itemId);
		else
			op = _requirements.get(itemId);
		if (op != null) {
			throw new IllegalArgumentException("Cannot add a " + opToAdd.toString() + 
					" operation for item " + itemId + 
					" because an operation already exists for him: " + op);
		}
	}

	/**
	 * Add to requirements specified update of specified item.
	 * 
	 * @param itemId id of item to revert
	 * @param toRev  destination item revision of the update operation 
	 * @throws IllegalArgumentException if it already exists an operation for specified item.
	 */
	public void addItemToUpdate(UUID itemId, int toRev) {
		Operation newOp = new UpdateOperation(itemId, toRev, true);
		checkNoOtherOp(itemId, newOp, false);
		
		_requirements.put(itemId, newOp);
		_requirementsList.add(newOp);
	}
	
	/**
	 * Add to requirements specified update of specified item.
	 * 
	 * @param itemId id of item to revert
	 * @param toRev  destination item revision of the update operation 
	 * @throws IllegalArgumentException if it already exists an operation for specified item.
	 */
	public void addItemToImport(UUID itemId, String itemName, int rev, ItemType itemType) {
		Operation newOp = new ImportOperation(itemId, itemName, rev, itemType, true);
		checkNoOtherOp(itemId, newOp, false);
		
		_requirements.put(itemId, newOp);
		_requirementsList.add(newOp);
	}

	/**
	 * Returns list of operations to perform implied by the user requirements.
	 * 
	 * @return list of operations to perform implied by the user requirements.
	 */
	public List<Operation> getImpacts() {
		return _impactsList;
	}
	
	/**
	 * Returns all errors related to definition of this update operation.
	 * 
	 * @return all errors related to definition of this update operation.
	 */
	public Errors getErrors() {
		return _errors;
	}

	/**
	 * Adds specified operation as an impact of requirements.
	 * 
	 * @param op an operation
	 */
	public void addImpactOperation(Operation op) {
		UUID itemId = op.getItemId();
		checkNoOtherOp(itemId, op, true);
		
		_impacts.put(itemId, op);
		_impactsList.add(op);
	}

	/**
	 * Returns true if a requirement defines a revert operation on specified item.
	 * 
	 * @param itemId item id
	 * @return true if a requirement defines a revert operation on specified item.
	 */
	public boolean isToRevertInRequirements(UUID itemId) {
		Operation op = _requirements.get(itemId);
		if (op != null) {
			return op.isRevert();
		}
		
		return false;
	}
	
	/**
	 * Returns true if a requirement defines an update operation on specified item.
	 * 
	 * @param itemId item id
	 * @return true if a requirement defines an update operation on specified item.
	 */
	public boolean isToUpdateInRequirements(UUID itemId) {
		Operation op = _requirements.get(itemId);
		if (op != null) {
			return op.isUpdate();
		}
		
		return false;
	}
	
	/**
	 * Returns true if a requirement defines an import operation on specified item.
	 * 
	 * @param itemId item id
	 * @return true if a requirement defines an import operation on specified item.
	 */
	public boolean isToImportInRequirements(UUID itemId) {
		Operation op = _requirements.get(itemId);
		if (op != null) {
			return op.isImport();
		}
		
		return false;
	}
	
	/**
	 * Returns true if at least a requirement operation on specified item exists.
	 * 
	 * @param itemId item id
	 * @return true if at least a requirement operation on specified item exists.
	 */
	public boolean hasRequirementOn(UUID itemId) {
		return (_requirements.get(itemId) != null);
	}
}

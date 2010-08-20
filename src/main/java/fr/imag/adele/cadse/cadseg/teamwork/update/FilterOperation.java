package fr.imag.adele.cadse.cadseg.teamwork.update;


/**
 * Represents a filter of update operation.
 * 
 * @author Thomas
 *
 */
public interface FilterOperation {
	
	/**
	 * Returns true if specified operation must not be discarded.
	 * 
	 * @param op an operation 
	 * @return true if specified operation must not be discarded.
	 */
	public boolean accept(Operation op);

}

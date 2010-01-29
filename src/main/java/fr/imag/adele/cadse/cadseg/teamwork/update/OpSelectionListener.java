package fr.imag.adele.cadse.cadseg.teamwork.update;

import fr.imag.adele.cadse.core.Item;

/**
 * Listener which is interested in update operation definition selection informations.
 * @author Thomas
 *
 */
public interface OpSelectionListener {

	/**
	 * Called each time a new update operation is selected.
	 * 
	 * @param newOp new selected update operation
	 */
	public void selectOperation(Item newOp);
	
	/**
	 * Called each time a selected update operation becomes deselected.
	 * 
	 * @param oldOp update operation which has been deselected
	 */
	public void deselectItem(Item oldOp);
	
	/**
	 * Called when there is no more selected update operation. 
	 */
	public void noMoreSelectedOperation();
}

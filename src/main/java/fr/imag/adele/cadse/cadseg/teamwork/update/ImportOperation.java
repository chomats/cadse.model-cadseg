package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.UUID;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;

/**
 * Represents definition of an item import operation.
 * 
 * @author Thomas
 *
 */
public class ImportOperation extends Operation {
	
	private int _rev;
	private String _itemName;
	private ItemType _itemType;

	public ImportOperation(UUID itemId, String itemName, int rev, ItemType itemType, boolean isRequirement) {
		super(itemId, OperationType.IMPORT, isRequirement);
		_rev = rev;
		_itemName = itemName;
		_itemType = itemType;
	}

	/**
	 * Returns revision number of item version to import.
	 * 
	 * @return revision number of item version to import.
	 */
	public int getDestinationRevNb() {
		return _rev;
	}
	
	/**
	 * Returns type of item to import.
	 * 
	 * @return type of item to import.
	 */
	public ItemType getItemType() {
		return _itemType;
	}

	@Override
	public String getRequirementDisplay(LogicalWorkspace lw) {
		return "Import of " + _itemName + "(" + _rev + ")";
	}
	
	@Override
	public Operation clone() {
		return new ImportOperation(getItemId(), getItemName(), _rev, _itemType, isRequirement());
	}

	/**
	 * Returns name of item to import.
	 * 
	 * @return name of item to import.
	 */
	public String getItemName() {
		return _itemName;
	}
}

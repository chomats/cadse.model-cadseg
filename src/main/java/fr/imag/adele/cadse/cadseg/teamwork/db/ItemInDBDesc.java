package fr.imag.adele.cadse.cadseg.teamwork.db;

import java.util.UUID;

/**
 * Represents description of an item in a teamwork repository.
 * @author Thomas
 *
 */
public class ItemInDBDesc {

	private UUID _id;
	private String _name;
	
	public ItemInDBDesc(UUID id, String name) {
		_id = id;
		_name = name;
	}

	public UUID getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ItemInDBDesc))
			return false;
		
		ItemInDBDesc item2 = (ItemInDBDesc) obj;
		return _id.equals(item2._id);
	}
	
	@Override
	public int hashCode() {
		return _id.hashCode();
	}
}

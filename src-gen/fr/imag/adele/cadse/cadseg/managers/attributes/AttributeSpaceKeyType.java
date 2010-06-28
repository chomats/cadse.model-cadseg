/**
 * 
 */
package fr.imag.adele.cadse.cadseg.managers.attributes;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.key.DefaultKeyDefinitionImpl;
import fr.imag.adele.cadse.core.key.DefaultKeyImpl;
import fr.imag.adele.cadse.core.key.Key;

public final class AttributeSpaceKeyType extends DefaultKeyDefinitionImpl {
	public AttributeSpaceKeyType(ItemType itemType, ItemType spaceKeyType) {
		super(itemType, spaceKeyType);
	}

	@Override
	protected Key getParentSpaceKeyFromItem(Item item) {
		Item it = null;
		it = item.getPartParent(false);
		if (it == null) {
			it = ((Item) item).getPartParent(true);
		}
		if (it == null)
			return DefaultKeyImpl.INVALID;
		
		Key key = it.getKey();
		assert key != null;
		return key;
	}
	
	@Override
	public String convertName(String name) {
		if (name == null) return null;
		return name.toUpperCase();
	}
	
	@Override
	protected String getName(Item item) {
		return convertName(super.getName(item));
	}
}
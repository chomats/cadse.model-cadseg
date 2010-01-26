/**
 * 
 */
package fr.imag.adele.cadse.cadseg.managers.attributes;

import fr.imag.adele.cadse.cadseg.managers.dataModel.ExtItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.key.DefaultKeyDefinitionImpl;
import fr.imag.adele.cadse.core.key.DefaultKeyImpl;
import fr.imag.adele.cadse.core.key.Key;
import fr.imag.adele.cadse.util.Assert;

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
		
		if (it.getType() == CadseGCST.EXT_ITEM_TYPE) {
			Item it2 = ExtItemTypeManager.getRefType(it);
			Assert
					.isNotNull(it2, "Cannot found ref itemtype form " + it.getQualifiedName() + "::"
							+ item.getName());

			it = it2;
		}
		Key key = it.getKey();
		assert key != null;
		return key;
	}
	
	@Override
	protected String convertName(String name) {
		if (name == null) return null;
		return name.toUpperCase();
	}
	
	@Override
	protected String getName(Item item) {
		return convertName(super.getName(item));
	}
}
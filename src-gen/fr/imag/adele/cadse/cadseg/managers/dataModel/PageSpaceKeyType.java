/**
 * 
 */
package fr.imag.adele.cadse.cadseg.managers.dataModel;

import java.util.logging.Level;
import java.util.logging.Logger;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.key.DefaultKeyDefinitionImpl;
import fr.imag.adele.cadse.core.key.DefaultKeyImpl;
import fr.imag.adele.cadse.core.key.Key;

public final class PageSpaceKeyType extends DefaultKeyDefinitionImpl {
	public PageSpaceKeyType(ItemType type, ItemType type2) {
		super(type, type2);
	}

	@Override
	public Key computeKey(Item item) {
		Key parentKey = null;
		if (_parentKeyDefinition != null) {
			parentKey = getParentSpaceKeyFromItem(item);
		}
		if (parentKey == DefaultKeyImpl.INVALID) {
			Logger.getLogger("fr.imag.adele.cadse.key").log(Level.SEVERE, 
					"Parent key is invalide for item "+item.getType().getName() + "::"+item.getDisplayName());				
			return DefaultKeyImpl.INVALID;
		}
		return new PageSpaceKey(item, this, item.getName(), parentKey);
	}

	@Override
	public Key computeKey(Key parentKey, Object... values)
			throws CadseException {
		if (parentKey == DefaultKeyImpl.INVALID)
			return DefaultKeyImpl.INVALID;
		
		return new PageSpaceKey(null, this, (String) values[0], parentKey, ((Boolean) values[1]).booleanValue()
				);
		//parentItem.getPartParentLinkType() == CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES
	}
	
	@Override
	public void getQualifiedString(Key key, StringBuilder sb) {
		if (((PageSpaceKey) key).modificationPage) {
			sb.append("Modification ");
		} else {
			sb.append("Creation ");
		}
		sb.append(getItemType().getName()).append(" ");
		super.getQualifiedString(key, sb);
	}


}
/**
 *
 */
package fr.imag.adele.cadse.cadseg.contents;




import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;

import java.util.UUID;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.IContentItemFactory;

public final class CadseDefinitionCIF implements IContentItemFactory {
	public ContentItem createContentItem(UUID id, Item owerItem) throws CadseException {
		return new CadseDefinitionContent(id);
	}
}
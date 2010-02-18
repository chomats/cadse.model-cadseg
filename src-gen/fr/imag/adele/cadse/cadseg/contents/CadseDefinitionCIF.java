/**
 *
 */
package fr.imag.adele.cadse.cadseg.contents;




import java.util.UUID;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.content.ContentItem;

public final class CadseDefinitionCIF implements IContentItemFactory {
	public ContentItem createContentItem(UUID id, Item owerItem) throws CadseException {
		return new CadseDefinitionContent(id);
	}
}
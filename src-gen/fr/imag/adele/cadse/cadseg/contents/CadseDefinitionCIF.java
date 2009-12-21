/**
 *
 */
package fr.imag.adele.cadse.cadseg.contents;




import fr.imag.adele.cadse.core.CadseException;
import java.util.UUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.IContentItemFactory;

public final class CadseDefinitionCIF implements IContentItemFactory {
	public ContentItem createContentItem(UUID id) throws CadseException {
		return new CadseDefinitionContent(id);
	}
}
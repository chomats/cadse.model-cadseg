/**
 *
 */
package fr.imag.adele.cadse.cadseg.contents;




import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.IContentItemFactory;

public final class CadseDefinitionCIF implements IContentItemFactory {
	public ContentItem createContentItem(CompactUUID id) throws CadseException {
		return new CadseDefinitionContent(id);
	}
}
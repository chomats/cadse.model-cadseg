package fr.imag.adele.cadse.cadseg.contents.ic;



import fr.imag.adele.cadse.cadseg.managers.ic.InteractionControllerManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;

import java.util.UUID;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.IContentItemFactory;

public class InteractionControllerCIF implements IContentItemFactory {
	InteractionControllerManager	_manager;

	public InteractionControllerCIF(InteractionControllerManager interactionControllerManager) {
		_manager = interactionControllerManager;
	}

	public ContentItem createContentItem(UUID id, Item owerItem) throws CadseException {
		return new InteractionControllerContent(id, _manager);
	}
	
}

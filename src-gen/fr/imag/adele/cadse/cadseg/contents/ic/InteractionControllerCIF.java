package fr.imag.adele.cadse.cadseg.contents.ic;



import fr.imag.adele.cadse.cadseg.managers.ic.InteractionControllerManager;
import fr.imag.adele.cadse.core.CadseException;
import java.util.UUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.IContentItemFactory;

public class InteractionControllerCIF implements IContentItemFactory {
	InteractionControllerManager	_manager;

	public InteractionControllerCIF(InteractionControllerManager interactionControllerManager) {
		_manager = interactionControllerManager;
	}

	public ContentItem createContentItem(UUID id) throws CadseException {
		return new InteractionControllerContent(id, _manager);
	}
	
}

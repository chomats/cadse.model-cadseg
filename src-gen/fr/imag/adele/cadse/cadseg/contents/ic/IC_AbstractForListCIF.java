package fr.imag.adele.cadse.cadseg.contents.ic;


import fr.imag.adele.cadse.cadseg.managers.ic.InteractionControllerManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.ContentItem;

public class IC_AbstractForListCIF extends InteractionControllerCIF {
	public IC_AbstractForListCIF(InteractionControllerManager interactionControllerManager) {
		super(interactionControllerManager);
	}

	@Override
	public ContentItem createContentItem(CompactUUID id) throws CadseException {
		return new IC_AbstractForListContent(id, _manager);
	}

}

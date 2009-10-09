package fr.imag.adele.cadse.cadseg.contents.ic;

import fr.imag.adele.cadse.cadseg.managers.ic.InteractionControllerManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.Item;

public class IC_AbstractForCheckedCIF extends InteractionControllerCIF {

	/**
	 * @generated
	 */
	public class MyContentItem extends InteractionControllerCIF.MyContentItem {

		/**
		 * @generated
		 */
		public MyContentItem(ContentItem parent, Item item, InteractionControllerManager interactionControllerManager)
				throws CadseException {
			super(parent, item, interactionControllerManager);
		}

	}

	public IC_AbstractForCheckedCIF(InteractionControllerManager interactionControllerManager) {
		super(interactionControllerManager);
	}

	@Override
	public ContentItem createContentItem(Item item) throws CadseException {
		return new MyContentItem(null, item, _manager);
	}

}

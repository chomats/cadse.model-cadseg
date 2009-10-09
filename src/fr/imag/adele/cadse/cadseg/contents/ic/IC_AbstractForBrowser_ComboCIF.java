package fr.imag.adele.cadse.cadseg.contents.ic;

import java.util.Set;

import fr.imag.adele.cadse.cadseg.managers.ic.IC_AbstractForBrowser_ComboManager;
import fr.imag.adele.cadse.cadseg.managers.ic.InteractionControllerManager;
import fr.imag.adele.cadse.cadseg.managers.ui.DisplayManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

public class IC_AbstractForBrowser_ComboCIF extends InteractionControllerCIF {
	/**
	 * The Class MyContentItem.
	 */
	class MyContentItem extends InteractionControllerCIF.MyContentItem {

		/**
		 * Instantiates a new my content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 * @throws CadseException
		 */
		protected MyContentItem(ContentItem parent, Item item, InteractionControllerManager interactionControllerManager)
				throws CadseException {
			super(parent, item, interactionControllerManager);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set, java.lang.Object)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
			DisplayManager.addAttributeInCall(getItem(), IC_AbstractForBrowser_ComboManager.SELECT_TITLE_ATTRIBUTE,
					true, "??", sb);
			DisplayManager.addAttributeInCall(getItem(), IC_AbstractForBrowser_ComboManager.SELECT_MESSAGE_ATTRIBUTE,
					true, "??", sb);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			sb.append(" String title, String message,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			sb.append(" title, message,");
		}
	}

	public IC_AbstractForBrowser_ComboCIF(InteractionControllerManager interactionControllerManager) {
		super(interactionControllerManager);
	}

	@Override
	public ContentItem createContentItem(Item item) throws CadseException {
		return new MyContentItem(null, item, _manager);
	}
}

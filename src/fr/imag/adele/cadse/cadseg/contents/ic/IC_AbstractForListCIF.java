package fr.imag.adele.cadse.cadseg.contents.ic;

import java.util.Set;

import fr.imag.adele.cadse.cadseg.managers.ic.IC_AbstractForListManager;
import fr.imag.adele.cadse.cadseg.managers.ic.InteractionControllerManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

public class IC_AbstractForListCIF extends InteractionControllerCIF {
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
		 * @param _manager
		 * @throws CadseException
		 */
		protected MyContentItem(ContentItem parent, Item item, InteractionControllerManager manager)
				throws CadseException {
			super(parent, item, manager);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set, java.lang.Object)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
			Item uc = getItem();
			sb.append_string_vir(uc, IC_AbstractForListManager.SELECT_TITLE_ATTRIBUTE);
			sb.append_string_vir(uc, IC_AbstractForListManager.SELECT_MESSAGE_ATTRIBUTE);

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			sb.append("String title, String message,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			sb.append("title, message,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#computeImportsPackage(java.util.Set)
		 */
		@Override
		public void computeImportsPackage(Set<String> imports) {
			super.computeImportsPackage(imports);
			imports.add("fede.workspace.model.manager.properties");
		}
	}

	public IC_AbstractForListCIF(InteractionControllerManager interactionControllerManager) {
		super(interactionControllerManager);
	}

	@Override
	public ContentItem createContentItem(Item item) throws CadseException {
		return new MyContentItem(null, item, _manager);
	}

}

package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;

/**
 @generated
 */
public class IC_BooleanTextModificationPageIC_BooleanText_ModificationPage
		extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected IC_BooleanTextModificationPageIC_BooleanText_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public IC_BooleanTextModificationPageIC_BooleanText_ModificationPage(
			Item item) {
		super("modification-page-IC_BooleanText", "IC_BooleanText",
				"IC_BooleanText", "", false, 3);
		this.item = item;
		setActionPage(null);
		addLast();

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

}

package fr.imag.adele.cadse.cadseg.pages.ui;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;

/**
 @generated
 */
public class DCheckedTreeModificationPageDCheckedTree_ModificationPage extends
		PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DCheckedTreeModificationPageDCheckedTree_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public DCheckedTreeModificationPageDCheckedTree_ModificationPage(Item item) {
		super("modification-page-DCheckedTree", "DCheckedTree", "DCheckedTree",
				"", false, 3);
		this.item = item;
		setActionPage(null);
		addLast();

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

}

package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class IC_StaticArrayOfObjectForBrowser_ComboModificationPage1_ModificationPage
		extends IC_AbstractForBrowser_ComboModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected IC_StaticArrayOfObjectForBrowser_ComboModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_StaticArrayOfObjectForBrowser_ComboModificationPage1_ModificationPage(
			Item item) {
		super("modification-page1", "IC_StaticArrayOfObjectForBrowser_Combo",
				"IC_StaticArrayOfObjectForBrowser_Combo", "", false, 3);
		this.item = item;
		setActionPage(null);
		addLast();

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

}

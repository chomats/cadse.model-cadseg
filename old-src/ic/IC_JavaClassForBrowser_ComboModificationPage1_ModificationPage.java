package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class IC_JavaClassForBrowser_ComboModificationPage1_ModificationPage
		extends InteractionControllerModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected IC_JavaClassForBrowser_ComboModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_JavaClassForBrowser_ComboModificationPage1_ModificationPage(
			Item item) {
		super("modification-page1", "IC_JavaClassForBrowser_Combo",
				"IC_JavaClassForBrowser_Combo", "", false, 3);
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

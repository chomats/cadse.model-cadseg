package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class IC_PartLinkForBrowser_Combo_ListModificationPage1_ModificationPage
		extends IC_LinkForBrowser_Combo_ListModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected IC_PartLinkForBrowser_Combo_ListModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_PartLinkForBrowser_Combo_ListModificationPage1_ModificationPage(
			Item item) {
		super("modification-page1", "IC_PartLinkForBrowser_Combo_List",
				"IC_PartLinkForBrowser_Combo_List", "", false, 3);
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

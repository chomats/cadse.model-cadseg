package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;

/**
 * @generated
 */
public class IC_PartLinkForBrowser_Combo_ListCreationPage1_CreationPage extends
		IC_LinkForBrowser_Combo_ListCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected IC_PartLinkForBrowser_Combo_ListCreationPage1_CreationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_PartLinkForBrowser_Combo_ListCreationPage1_CreationPage(
			Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create IC_PartLinkForBrowser_Combo_List",
				"Create IC_PartLinkForBrowser_Combo_List", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		setActionPage(null);
		addLast();

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

}

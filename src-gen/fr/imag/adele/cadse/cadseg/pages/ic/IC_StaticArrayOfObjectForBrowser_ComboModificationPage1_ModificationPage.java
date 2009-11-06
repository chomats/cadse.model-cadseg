package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;

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

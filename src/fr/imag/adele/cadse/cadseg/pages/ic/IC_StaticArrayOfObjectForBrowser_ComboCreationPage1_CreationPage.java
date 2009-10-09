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
public class IC_StaticArrayOfObjectForBrowser_ComboCreationPage1_CreationPage extends
		IC_AbstractForBrowser_ComboCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected IC_StaticArrayOfObjectForBrowser_ComboCreationPage1_CreationPage(String id, String label, String title,
			String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_StaticArrayOfObjectForBrowser_ComboCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create IC_StaticArrayOfObjectForBrowser_Combo",
				"Create IC_StaticArrayOfObjectForBrowser_Combo", "", false, 3);
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

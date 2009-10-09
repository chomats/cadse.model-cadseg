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
public class IC_JavaClassForBrowser_ComboModificationPage1_ModificationPage extends
		InteractionControllerModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected IC_JavaClassForBrowser_ComboModificationPage1_ModificationPage(String id, String label, String title,
			String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_JavaClassForBrowser_ComboModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "IC_JavaClassForBrowser_Combo", "IC_JavaClassForBrowser_Combo", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		setActionPage(null);
		addLast(this.__short_name__);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

}

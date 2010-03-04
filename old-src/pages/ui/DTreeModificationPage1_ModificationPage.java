package fr.imag.adele.cadse.cadseg.pages.ui;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class DTreeModificationPage1_ModificationPage extends
		DisplayModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DTreeModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public DTreeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "DTree", "DTree", "", false, 3);
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

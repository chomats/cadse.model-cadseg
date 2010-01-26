package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class PackageListControllerModificationPage1_ModificationPage extends
		InteractionControllerModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected PackageListControllerModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public PackageListControllerModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "PackageListController",
				"PackageListController", "", false, 3);
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

package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class PackageContentModelModificationPage1_ModificationPage extends
		FolderContentModelModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected PackageContentModelModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public PackageContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "PackageContentModel",
				"PackageContentModel", "", false, 3);
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

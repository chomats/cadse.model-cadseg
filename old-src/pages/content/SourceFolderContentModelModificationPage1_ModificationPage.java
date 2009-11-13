package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class SourceFolderContentModelModificationPage1_ModificationPage extends
		FolderContentModelModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected SourceFolderContentModelModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public SourceFolderContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "SourceFolderContentModel",
				"SourceFolderContentModel", "", false, 3);
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

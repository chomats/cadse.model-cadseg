package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class JavaJarFileContentModelModificationPage1_ModificationPage extends
		ContentItemTypeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected JavaJarFileContentModelModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public JavaJarFileContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "JavaJarFileContentModel",
				"JavaJarFileContentModel", "", false, 3);
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

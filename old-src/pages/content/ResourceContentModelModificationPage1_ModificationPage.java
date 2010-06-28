package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class ResourceContentModelModificationPage1_ModificationPage extends
		ContentItemTypeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected ResourceContentModelModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ResourceContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "ResourceContentModel",
				"ResourceContentModel", "", false, 3);
		this.item = item;
		this.fieldExtendsClass = createFieldExtendsClass();
		setActionPage(null);
		addLast(this.fieldExtendsClass);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

}

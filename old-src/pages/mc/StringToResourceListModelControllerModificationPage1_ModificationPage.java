package fr.imag.adele.cadse.cadseg.pages.mc;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class StringToResourceListModelControllerModificationPage1_ModificationPage
		extends ModelControllerModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected StringToResourceListModelControllerModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public StringToResourceListModelControllerModificationPage1_ModificationPage(
			Item item) {
		super("modification-page1", "StringToResourceListModelController",
				"StringToResourceListModelController", "", false, 3);
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
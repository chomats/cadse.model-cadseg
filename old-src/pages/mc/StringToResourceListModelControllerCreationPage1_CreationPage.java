package fr.imag.adele.cadse.cadseg.pages.mc;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;

/**
 * @generated
 */
public class StringToResourceListModelControllerCreationPage1_CreationPage
		extends ModelControllerCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected StringToResourceListModelControllerCreationPage1_CreationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public StringToResourceListModelControllerCreationPage1_CreationPage(
			Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create StringToResourceListModelController",
				"Create StringToResourceListModelController", "", false, 3);
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

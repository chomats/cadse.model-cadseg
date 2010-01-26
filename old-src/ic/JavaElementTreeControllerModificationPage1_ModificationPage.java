package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class JavaElementTreeControllerModificationPage1_ModificationPage extends
		InteractionControllerModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected JavaElementTreeControllerModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public JavaElementTreeControllerModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "JavaElementTreeController",
				"JavaElementTreeController", "", false, 3);
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

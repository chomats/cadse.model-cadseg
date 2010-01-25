package fr.imag.adele.cadse.cadseg.pages.build.composer;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class AJProjectComposerModificationPage1_ModificationPage extends
		EclipseComposerModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected AJProjectComposerModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public AJProjectComposerModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "AJProjectComposer", "AJProjectComposer",
				"", false, 3);
		this.item = item;
		this.fieldComposerLinks = createFieldComposerLinks();
		setActionPage(null);
		addLast(this.fieldComposerLinks);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

}

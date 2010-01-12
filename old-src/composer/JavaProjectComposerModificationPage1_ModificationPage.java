package fr.imag.adele.cadse.cadseg.pages.build.composer;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class JavaProjectComposerModificationPage1_ModificationPage extends
		EclipseComposerModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected JavaProjectComposerModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public JavaProjectComposerModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "JavaProjectComposer",
				"JavaProjectComposer", "", false, 3);
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

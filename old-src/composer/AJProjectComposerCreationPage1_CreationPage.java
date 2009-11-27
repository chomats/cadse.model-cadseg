package fr.imag.adele.cadse.cadseg.pages.build.composer;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;

/**
 * @generated
 */
public class AJProjectComposerCreationPage1_CreationPage extends
		EclipseComposerCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected AJProjectComposerCreationPage1_CreationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public AJProjectComposerCreationPage1_CreationPage(Item parent,
			ItemType it, LinkType lt) {
		super("creation-page1", "Create AJProjectComposer",
				"Create AJProjectComposer", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldName = createFieldName();
		setActionPage(null);
		addLast(this.fieldName);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

}

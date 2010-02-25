package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;

/**
 * @generated
 */
public class JavaFileContentModelCreationPage1_CreationPage extends
		FileContentModelCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected JavaFileContentModelCreationPage1_CreationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public JavaFileContentModelCreationPage1_CreationPage(Item parent,
			ItemType it, LinkType lt) {
		super("creation-page1", "Create Java File Content",
				"Create Java File Content", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldExtendsClass = createFieldExtendsClass();
		this.fieldFileName = createFieldFileName();
		this.fieldFilePath = createFieldFilePath();
		setActionPage(null);
		addLast(this.fieldExtendsClass, this.fieldFileName, this.fieldFilePath);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

}

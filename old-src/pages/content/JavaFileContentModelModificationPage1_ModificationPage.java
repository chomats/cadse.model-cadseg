package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class JavaFileContentModelModificationPage1_ModificationPage extends
		FileContentModelModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected JavaFileContentModelModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public JavaFileContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Java File Content", "Java File Content",
				"", false, 3);
		this.item = item;
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

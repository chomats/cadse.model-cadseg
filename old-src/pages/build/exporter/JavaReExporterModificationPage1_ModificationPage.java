package fr.imag.adele.cadse.cadseg.pages.build.exporter;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class JavaReExporterModificationPage1_ModificationPage extends
		EclipseReExporterModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected JavaReExporterModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public JavaReExporterModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "JavaReExporter", "JavaReExporter", "",
				false, 3);
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

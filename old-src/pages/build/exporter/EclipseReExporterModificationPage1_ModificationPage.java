package fr.imag.adele.cadse.cadseg.pages.build.exporter;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class EclipseReExporterModificationPage1_ModificationPage extends
		ExporterModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected EclipseReExporterModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public EclipseReExporterModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "EclipseReExporter", "EclipseReExporter",
				"", false, 3);
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

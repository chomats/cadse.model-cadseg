package fr.imag.adele.cadse.cadseg.pages.build.exporter;

import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class AspectJReExporterModificationPage1_ModificationPage extends
		JavaReExporterModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected AspectJReExporterModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public AspectJReExporterModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "AspectJReExporter", "AspectJReExporter",
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

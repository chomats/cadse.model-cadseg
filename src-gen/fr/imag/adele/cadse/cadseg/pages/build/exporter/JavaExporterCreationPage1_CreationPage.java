package fr.imag.adele.cadse.cadseg.pages.build.exporter;

import fede.workspace.eclipse.composition.java.JavaProjectExporter;
import fr.imag.adele.cadse.cadseg.managers.build.exporter.JavaExporterManager;
import fr.imag.adele.cadse.cadseg.managers.build.exporter.JavaReExporterManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.UIPlatform;
import fr.imag.adele.cadse.core.ui.PageFactory;

/**
 * @generated
 */
public class JavaExporterCreationPage1_CreationPage extends
		EclipseExporterCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected JavaExporterCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public JavaExporterCreationPage1_CreationPage(Item parent, ItemType it,
			LinkType lt) {
		super("creation-page1", "Create JavaExporter", "Create JavaExporter",
				"", false, 3);
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

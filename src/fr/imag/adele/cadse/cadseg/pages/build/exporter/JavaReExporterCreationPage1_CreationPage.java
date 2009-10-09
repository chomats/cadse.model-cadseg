package fr.imag.adele.cadse.cadseg.pages.build.exporter;

import fede.workspace.eclipse.composition.java.JavaProjectExporter;
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
import fr.imag.adele.cadse.core.ui.IPageController;
import fr.imag.adele.cadse.core.ui.PageFactory;

/**
 * @generated
 */
public class JavaReExporterCreationPage1_CreationPage extends EclipseReExporterCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected JavaReExporterCreationPage1_CreationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public JavaReExporterCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create JavaReExporter", "Create JavaReExporter", "", false, 3);
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

	@Override
	public void init(IPageController pageController) throws CadseException {
		super.init(pageController);
		JavaReExporterManager.addTypesAttribute(getItem(), JavaProjectExporter.JAVA_TYPE_EXPORTER);
	}
}

package fr.imag.adele.cadse.cadseg.pages.build.composer;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fede.workspace.eclipse.composition.java.JavaProjectExporter;
import fr.imag.adele.cadse.cadseg.managers.build.composer.JavaProjectComposerManager;
import fr.imag.adele.cadse.cadseg.managers.build.exporter.JavaExporterManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.IPageController;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * @generated
 */
public class JavaProjectComposerCreationPage1_CreationPage extends EclipseComposerCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldExtendsClass;

	/**
	 * @generated
	 */
	protected JavaProjectComposerCreationPage1_CreationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public JavaProjectComposerCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create JavaProjectComposer", "Create JavaProjectComposer", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldExtendsClass = createFieldExtendsClass();
		setActionPage(null);
		addLast(this.fieldExtendsClass);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsClass() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.COMPOSER_at_EXTENDS_CLASS, "extends-class", EPosLabel.none, mc, null);
	}

}

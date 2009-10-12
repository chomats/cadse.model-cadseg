package fr.imag.adele.cadse.cadseg.pages.content;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * @generated
 */
public class JavaProjectContentModelCreationPage1_CreationPage extends
		ProjectContentModelCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldHasSourceFolder;

	/**
	 * @generated
	 */
	protected JavaProjectContentModelCreationPage1_CreationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public JavaProjectContentModelCreationPage1_CreationPage(Item parent,
			ItemType it, LinkType lt) {
		super("creation-page1", "Create Java Project Content",
				"Create Java Project Content", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldExtendsClass = createFieldExtendsClass();
		this.fieldProjectName = createFieldProjectName();
		this.fieldHasSourceFolder = createFieldHasSourceFolder();
		setActionPage(null);
		addLast(this.fieldExtendsClass, this.fieldProjectName,
				this.fieldHasSourceFolder);

		registerListener();
	}

	@Override
	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @not generated
	 */
	@Override
	public DTextUI createFieldProjectName() {
		return super.createFieldProjectName();
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldHasSourceFolder() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(
				CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER,
				"has source folder", EPosLabel.none, mc, null);
	}

}

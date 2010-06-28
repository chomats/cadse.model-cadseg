package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class JavaProjectContentModelModificationPage1_ModificationPage extends
		ProjectContentModelModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldHasSourceFolder;

	/**
	 * @generated
	 */
	protected JavaProjectContentModelModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public JavaProjectContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Java Project Content",
				"Java Project Content", "", false, 3);
		this.item = item;
		this.fieldHasSourceFolder = createFieldHasSourceFolder();
		this.fieldProjectName = createFieldProjectName();
		this.fieldExtendsClass = createFieldExtendsClass();
		setActionPage(null);
		addLast(this.fieldHasSourceFolder, this.fieldProjectName,
				this.fieldExtendsClass);

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

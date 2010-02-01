package fr.imag.adele.cadse.cadseg.pages.build.exporter;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class FolderExporterCreationPage1_CreationPage extends
		EclipseExporterCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected DTextUI fieldPath;

	/**
	 * @generated
	 */
	protected FolderExporterCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public FolderExporterCreationPage1_CreationPage(Item parent, ItemType it,
			LinkType lt) {
		super("creation-page1", "Create FolderExporter",
				"Create FolderExporter", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldName = createFieldName();
		this.fieldPath = createFieldPath();
		this.fieldTypes = createFieldTypes();
		setActionPage(null);
		addLast(this.fieldName, this.fieldPath, this.fieldTypes);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldPath() {
		return new DTextUI(CadseGCST.FOLDER_EXPORTER_at_PATH, "path",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

}

package fr.imag.adele.cadse.cadseg.pages.build.exporter;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_DefaultForList;
import fede.workspace.model.manager.properties.impl.mc.MC_DefaultForList;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * @generated
 */
public class FolderExporterCreationPage1_CreationPage extends EclipseExporterCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected DTextUI	fieldPath;

	/**
	 * @generated
	 */
	protected FolderExporterCreationPage1_CreationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public FolderExporterCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create FolderExporter", "Create FolderExporter", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.__short_name__ = createInternalNameField();
		this.fieldPath = createFieldPath();
		this.fieldTypes = createFieldTypes();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldPath, this.fieldTypes);

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
		return new DTextUI(WorkspaceCST.FOLDER_EXPORTER_at_PATH, "path", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldTypes() {
		MC_DefaultForList mc = new MC_DefaultForList(0, -1);
		IC_DefaultForList ic = new IC_DefaultForList("Select a value.", "Select a value.", false);
		return new DListUI(WorkspaceCST.EXPORTER_at_TYPES, "types", EPosLabel.top, mc, ic, true, false, false, false);
	}

}

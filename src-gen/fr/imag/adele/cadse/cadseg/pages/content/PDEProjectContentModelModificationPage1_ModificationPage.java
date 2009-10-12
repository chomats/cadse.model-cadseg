package fr.imag.adele.cadse.cadseg.pages.content;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
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
public class PDEProjectContentModelModificationPage1_ModificationPage extends
		JavaProjectContentModelModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DListUI fieldContentModel;

	/**
	 * @generated
	 */
	protected PDEProjectContentModelModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public PDEProjectContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "PDEProjectContentModel",
				"PDEProjectContentModel", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldContentModel = createFieldContentModel();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldContentModel);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DListUI createFieldContentModel() {
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL);
		return new DListUI(CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL
				.getName(), "content-model", EPosLabel.top, mc, ic, true,
				false, false, false);
	}

}

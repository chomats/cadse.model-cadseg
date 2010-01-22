package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;

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
		this.fieldContentModel = createFieldContentModel();
		setActionPage(null);
		addLast(this.fieldContentModel);

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
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL);
		return new DListUI(CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL
				.getName(), "content-model", EPosLabel.top, mc, ic, true,
				false, false, false);
	}

}

package fr.imag.adele.cadse.cadseg.pages.view;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;

/**
 * @generated
 */
public class ViewModelModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DListUI fieldViews;

	/**
	 * @generated
	 */
	protected ViewModelModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ViewModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "ViewModel", "ViewModel", "", false, 3);
		this.item = item;
		this.fieldViews = createFieldViews();
		setActionPage(null);
		addLast(this.fieldViews);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DListUI createFieldViews() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.VIEW_MODEL_lt_VIEWS);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.VIEW_MODEL_lt_VIEWS);
		return new DListUI(CadseGCST.VIEW_MODEL_lt_VIEWS.getName(), "views",
				EPosLabel.top, mc, ic, true, false, false, false);
	}

}

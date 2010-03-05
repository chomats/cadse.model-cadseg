package fr.imag.adele.cadse.cadseg.pages.content;

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
public class MappingModelModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DListUI fieldManagers;

	/**
	 * @generated
	 */
	protected MappingModelModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public MappingModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "MappingModel", "MappingModel", "", false,
				3);
		this.item = item;
		this.fieldManagers = createFieldManagers();
		setActionPage(null);
		addLast(this.fieldManagers);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DListUI createFieldManagers() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.MAPPING_MODEL_lt_MANAGERS);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.MAPPING_MODEL_lt_MANAGERS);
		return new DListUI(CadseGCST.MAPPING_MODEL_lt_MANAGERS.getName(),
				"managers", EPosLabel.top, mc, ic, true, false, false, false);
	}

}

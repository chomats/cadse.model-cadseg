package fr.imag.adele.cadse.cadseg.pages.build;

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
public class BuilderModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DListUI fieldRequires;

	/**
	 * @generated
	 */
	protected BuilderModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public BuilderModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Builder", "Builder", "", false, 3);
		this.item = item;
		this.fieldRequires = createFieldRequires();
		setActionPage(null);
		addLast(this.fieldRequires);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DListUI createFieldRequires() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.BUILDER_lt_REQUIRES);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.BUILDER_lt_REQUIRES);
		return new DListUI(CadseGCST.BUILDER_lt_REQUIRES.getName(), "requires",
				EPosLabel.top, mc, ic, true, false, false, false);
	}

}

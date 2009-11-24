package fr.imag.adele.cadse.cadseg.pages;

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
public class ConfigurationModelModificationPage1_ModificationPage extends
		PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DListUI fieldBuild;

	/**
	 * @generated
	 */
	protected ConfigurationModelModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ConfigurationModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "ConfigurationModel", "ConfigurationModel",
				"", false, 3);
		this.item = item;
		this.fieldBuild = createFieldBuild();
		setActionPage(null);
		addLast(this.fieldBuild);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DListUI createFieldBuild() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.CONFIGURATION_MODEL_lt_BUILD);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.CONFIGURATION_MODEL_lt_BUILD);
		return new DListUI(CadseGCST.CONFIGURATION_MODEL_lt_BUILD.getName(),
				"build", EPosLabel.top, mc, ic, true, false, false, false);
	}

}
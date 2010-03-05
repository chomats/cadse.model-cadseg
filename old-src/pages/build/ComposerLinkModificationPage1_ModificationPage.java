package fr.imag.adele.cadse.cadseg.pages.build;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;

/**
 * @generated
 */
public class ComposerLinkModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldLink;

	/**
	 * @generated
	 */
	protected DListUI fieldExporters;

	/**
	 * @generated
	 */
	protected ComposerLinkModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ComposerLinkModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "ComposerLink", "ComposerLink", "", false,
				3);
		this.item = item;
		this.fieldLink = createFieldLink();
		this.fieldExporters = createFieldExporters();
		setActionPage(null);
		addLast(this.fieldLink, this.fieldExporters);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldLink() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.COMPOSER_LINK_lt_LINK);
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.COMPOSER_LINK_lt_LINK);
		return new DBrowserUI(CadseGCST.COMPOSER_LINK_lt_LINK.getName(),
				"link", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldExporters() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.COMPOSER_LINK_lt_EXPORTERS);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.COMPOSER_LINK_lt_EXPORTERS);
		return new DListUI(CadseGCST.COMPOSER_LINK_lt_EXPORTERS.getName(),
				"exporters", EPosLabel.top, mc, ic, true, false, false, false);
	}

}

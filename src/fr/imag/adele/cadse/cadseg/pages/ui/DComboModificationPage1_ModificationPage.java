package fr.imag.adele.cadse.cadseg.pages.ui;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * @generated
 */
public class DComboModificationPage1_ModificationPage extends DisplayModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldIc;

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldMc;

	/**
	 * @generated
	 */
	protected DComboModificationPage1_ModificationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public DComboModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "DCombo", "DCombo", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldIc = createFieldIc();
		this.fieldMc = createFieldMc();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldIc, this.fieldMc);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldIc() {
		LinkModelController mc = new LinkModelController(true, null, WorkspaceCST.DCOMBO_lt_IC);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List("Select a value.", "Select a value.",
				WorkspaceCST.DCOMBO_lt_IC);
		return new DBrowserUI(WorkspaceCST.DCOMBO_lt_IC.getName(), "ic", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldMc() {
		LinkModelController mc = new LinkModelController(false, null, WorkspaceCST.DCOMBO_lt_MC);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List("Select a value.", "Select a value.",
				WorkspaceCST.DCOMBO_lt_MC);
		return new DBrowserUI(WorkspaceCST.DCOMBO_lt_MC.getName(), "mc", EPosLabel.left, mc, ic);
	}

}

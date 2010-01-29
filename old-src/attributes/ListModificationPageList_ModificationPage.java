package fr.imag.adele.cadse.cadseg.pages.attributes;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;

/**
 @generated
 */
public class ListModificationPageList_ModificationPage extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DBrowserUI fieldSubType;

	/**
	    @generated
	 */
	protected ListModificationPageList_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public ListModificationPageList_ModificationPage(Item item) {
		super("modification-page-List", "List", "List", "", false, 3);
		this.item = item;
		this.fieldSubType = createFieldSubType();
		setActionPage(null);
		addLast(this.fieldSubType);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldSubType() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.LIST_lt_SUB_TYPE);
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.LIST_lt_SUB_TYPE);
		return new DBrowserUI(CadseGCST.LIST_lt_SUB_TYPE.getName(), "sub-type",
				EPosLabel.left, mc, ic);
	}

}

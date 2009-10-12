package fr.imag.adele.cadse.cadseg.pages.attributes;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
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
	protected DTextUI __short_name__;

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
		this.__short_name__ = createInternalNameField();
		this.fieldSubType = createFieldSubType();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldSubType);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DTextUI createInternalNameField() {
		return FieldsCore.createUniqueNameField();
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldSubType() {
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.LIST_lt_SUB_TYPE);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.LIST_lt_SUB_TYPE);
		return new DBrowserUI(CadseGCST.LIST_lt_SUB_TYPE.getName(), "sub-type",
				EPosLabel.left, mc, ic);
	}

}

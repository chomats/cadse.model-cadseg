package fr.imag.adele.cadse.cadseg.pages.meta;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
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
public class ContentCadsegItemTypeModificationPageContentCadsegItemType_ModificationPage
		extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DBrowserUI fieldRuntimeType;

	/**
	    @generated
	 */
	protected ContentCadsegItemTypeModificationPageContentCadsegItemType_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public ContentCadsegItemTypeModificationPageContentCadsegItemType_ModificationPage(
			Item item) {
		super("modification-page-ContentCadsegItemType",
				"ContentCadsegItemType", "ContentCadsegItemType", "", false, 3);
		this.item = item;
		this.fieldRuntimeType = createFieldRuntimeType();
		setActionPage(null);
		addLast(this.fieldRuntimeType);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldRuntimeType() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.CONTENT_CADSEG_ITEM_TYPE_lt_RUNTIME_TYPE);
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.CONTENT_CADSEG_ITEM_TYPE_lt_RUNTIME_TYPE);
		return new DBrowserUI(
				CadseGCST.CONTENT_CADSEG_ITEM_TYPE_lt_RUNTIME_TYPE.getName(),
				"runtimeType", EPosLabel.left, mc, ic);
	}

}

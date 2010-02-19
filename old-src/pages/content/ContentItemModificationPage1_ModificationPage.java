package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 @generated
 */
public class ContentItemModificationPage1_ModificationPage extends PageImpl {

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
	protected DBrowserUI fieldOwnerItem;

	/**
	    @generated
	 */
	protected ContentItemModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public ContentItemModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "ContentItem", "ContentItem", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldOwnerItem = createFieldOwnerItem();
		setActionPage(null);
		addLast(this.__short_name__,
				this.fieldOwnerItem);

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
	public DBrowserUI createFieldOwnerItem() {
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.CONTENT_ITEM_lt_OWNER_ITEM);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.CONTENT_ITEM_lt_OWNER_ITEM);
		return new DBrowserUI(CadseGCST.CONTENT_ITEM_lt_OWNER_ITEM.getName(),
				"ownerItem", EPosLabel.left, mc, ic);
	}

}

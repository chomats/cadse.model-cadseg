package fr.imag.adele.cadse.cadseg.pages.content;

import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.RunningModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.FieldsCore;

/**
 @generated
 */
public class ContentItemModificationPageContentItem_ModificationPage extends
		PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DBrowserUI fieldOwnerItem;

	/**
	    @generated
	 */
	protected DListUI fieldChildren;

	/**
	    @generated
	 */
	protected ContentItemModificationPageContentItem_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public ContentItemModificationPageContentItem_ModificationPage(Item item) {
		super("modification-page-ContentItem", "ContentItem", "ContentItem",
				"", false, 3);
		this.item = item;
		this.fieldOwnerItem = createFieldOwnerItem();
		this.fieldChildren = createFieldChildren();
		setActionPage(null);
		addLast(this.fieldOwnerItem, this.fieldChildren);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldOwnerItem() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.CONTENT_ITEM_lt_OWNER_ITEM);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.CONTENT_ITEM_lt_OWNER_ITEM);
		return new DBrowserUI(CadseGCST.CONTENT_ITEM_lt_OWNER_ITEM.getName(),
				"owner-item", EPosLabel.left, mc, ic);
	}

	/**
	    @generated
	 */
	public DListUI createFieldChildren() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.CONTENT_ITEM_lt_CHILDREN);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.CONTENT_ITEM_lt_CHILDREN);
		return new DListUI(CadseGCST.CONTENT_ITEM_lt_CHILDREN.getName(),
				"children", EPosLabel.top, mc, ic, true, false, false, false);
	}

}

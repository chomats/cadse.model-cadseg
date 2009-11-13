package fr.imag.adele.cadse.cadseg.pages.content;

import fede.workspace.model.manager.properties.impl.mc.MC_ShortNameItemProperty;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 @generated
 */
public class ContentItemCreationPageContentItem_CreationPage extends PageImpl {

	/**
	    @generated
	 */
	public Item parent;

	/**
	    @generated
	 */
	public ItemType it;

	/**
	    @generated
	 */
	public LinkType lt;

	/**
	    @generated
	 */
	protected DTextUI fieldName;

	/**
	    @generated
	 */
	protected DListUI fieldChildren;

	/**
	    @generated
	 */
	protected ContentItemCreationPageContentItem_CreationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public ContentItemCreationPageContentItem_CreationPage(Item parent,
			ItemType it, LinkType lt) {
		super("creation-page-ContentItem", "Create ContentItem",
				"Create ContentItem", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldName = createFieldName();
		this.fieldChildren = createFieldChildren();
		setActionPage(null);
		addLast(this.fieldName, this.fieldChildren);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DTextUI createFieldName() {
		MC_ShortNameItemProperty mc = new MC_ShortNameItemProperty();
		return new DTextUI(CadseGCST.ITEM_at_NAME, "name", EPosLabel.left, mc,
				null, 1, "", false, false, false);
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

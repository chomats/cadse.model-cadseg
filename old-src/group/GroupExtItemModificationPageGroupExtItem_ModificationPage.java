package fr.imag.adele.cadse.cadseg.pages.group;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;

/**
 @generated
 */
public class GroupExtItemModificationPageGroupExtItem_ModificationPage extends
		PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DListUI fieldMembers;

	/**
	    @generated
	 */
	protected DBrowserUI fieldMemberOf;

	/**
	    @generated
	 */
	protected GroupExtItemModificationPageGroupExtItem_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public GroupExtItemModificationPageGroupExtItem_ModificationPage(Item item) {
		super("modification-page-GroupExtItem", "GroupExtItem", "GroupExtItem",
				"", false, 3);
		this.item = item;
		this.fieldMembers = createFieldMembers();
		this.fieldMemberOf = createFieldMemberOf();
		setActionPage(null);
		addLast(this.fieldMembers, this.fieldMemberOf);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DListUI createFieldMembers() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS);
		return new DListUI(CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS.getName(),
				"members", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldMemberOf() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF);
		return new DBrowserUI(CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF.getName(),
				"memberOf", EPosLabel.left, mc, ic);
	}

}

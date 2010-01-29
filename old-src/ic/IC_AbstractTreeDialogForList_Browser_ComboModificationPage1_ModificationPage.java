package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class IC_AbstractTreeDialogForList_Browser_ComboModificationPage1_ModificationPage
		extends InteractionControllerModificationPage1_ModificationPage {

	/**
	    @generated
	 */
	protected DTextUI fieldMessage;

	/**
	    @generated
	 */
	protected DTextUI fieldTitle;

	/**
	    @generated
	 */
	protected DTextUI fieldSelectMessage;

	/**
	    @generated
	 */
	protected DTextUI fieldSelectTitle;

	/**
	 * @generated
	 */
	protected IC_AbstractTreeDialogForList_Browser_ComboModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_AbstractTreeDialogForList_Browser_ComboModificationPage1_ModificationPage(
			Item item) {
		super("modification-page1",
				"IC_AbstractTreeDialogForList_Browser_Combo",
				"IC_AbstractTreeDialogForList_Browser_Combo", "", false, 3);
		this.item = item;
		this.fieldMessage = createFieldMessage();
		this.fieldTitle = createFieldTitle();
		this.fieldSelectMessage = createFieldSelectMessage();
		this.fieldSelectTitle = createFieldSelectTitle();
		setActionPage(null);
		addLast(this.fieldMessage, this.fieldTitle, this.fieldSelectMessage,
				this.fieldSelectTitle);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	    @generated
	 */
	public DTextUI createFieldMessage() {
		return new DTextUI(
				CadseGCST.IC_ABSTRACT_TREE_DIALOG_FOR_LIST_BROWSER_COMBO_at_MESSAGE,
				"message", EPosLabel.left, new MC_AttributesItem(), null, 1,
				"", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldTitle() {
		return new DTextUI(
				CadseGCST.IC_ABSTRACT_TREE_DIALOG_FOR_LIST_BROWSER_COMBO_at_TITLE,
				"title", EPosLabel.left, new MC_AttributesItem(), null, 1, "",
				false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldSelectMessage() {
		return new DTextUI(
				CadseGCST.IC_ABSTRACT_TREE_DIALOG_FOR_LIST_BROWSER_COMBO_at_SELECT_MESSAGE,
				"select-message", EPosLabel.left, new MC_AttributesItem(),
				null, 1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldSelectTitle() {
		return new DTextUI(
				CadseGCST.IC_ABSTRACT_TREE_DIALOG_FOR_LIST_BROWSER_COMBO_at_SELECT_TITLE,
				"select-title", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

}

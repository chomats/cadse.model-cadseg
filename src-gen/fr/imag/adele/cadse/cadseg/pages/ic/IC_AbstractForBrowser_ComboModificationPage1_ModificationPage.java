package fr.imag.adele.cadse.cadseg.pages.ic;

import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;

/**
 * @generated
 */
public class IC_AbstractForBrowser_ComboModificationPage1_ModificationPage
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
	 * @generated
	 */
	protected IC_AbstractForBrowser_ComboModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_AbstractForBrowser_ComboModificationPage1_ModificationPage(
			Item item) {
		super("modification-page1", "IC_AbstractForBrowser_Combo",
				"IC_AbstractForBrowser_Combo", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldMessage = createFieldMessage();
		this.fieldTitle = createFieldTitle();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldMessage, this.fieldTitle);

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
		return new DTextUI(CadseGCST.IC_ABSTRACT_FOR_BROWSER_COMBO_at_MESSAGE,
				"message", EPosLabel.left, new MC_AttributesItem(), null, 1,
				"", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldTitle() {
		return new DTextUI(CadseGCST.IC_ABSTRACT_FOR_BROWSER_COMBO_at_TITLE,
				"title", EPosLabel.left, new MC_AttributesItem(), null, 1, "",
				false, false, false);
	}

}

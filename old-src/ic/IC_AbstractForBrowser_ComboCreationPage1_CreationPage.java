package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class IC_AbstractForBrowser_ComboCreationPage1_CreationPage extends
		InteractionControllerCreationPage1_CreationPage {

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
	protected IC_AbstractForBrowser_ComboCreationPage1_CreationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_AbstractForBrowser_ComboCreationPage1_CreationPage(Item parent,
			ItemType it, LinkType lt) {
		super("creation-page1", "Create IC_AbstractForBrowser_Combo",
				"Create IC_AbstractForBrowser_Combo", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldMessage = createFieldMessage();
		this.fieldTitle = createFieldTitle();
		setActionPage(null);
		addLast(this.fieldMessage, this.fieldTitle);

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

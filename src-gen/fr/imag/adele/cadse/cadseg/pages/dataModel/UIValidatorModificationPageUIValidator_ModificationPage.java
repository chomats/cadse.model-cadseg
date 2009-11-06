package fr.imag.adele.cadse.cadseg.pages.dataModel;

import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
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
public class UIValidatorModificationPageUIValidator_ModificationPage extends
		PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DListUI fieldOverwrite;

	/**
	    @generated
	 */
	protected DListUI fieldListenAttributes;

	/**
	    @generated
	 */
	protected UIValidatorModificationPageUIValidator_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public UIValidatorModificationPageUIValidator_ModificationPage(Item item) {
		super("modification-page-UIValidator", "UIValidator", "UIValidator",
				"", false, 3);
		this.item = item;
		this.fieldOverwrite = createFieldOverwrite();
		this.fieldListenAttributes = createFieldListenAttributes();
		setActionPage(null);
		addLast(this.fieldOverwrite, this.fieldListenAttributes);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DListUI createFieldOverwrite() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.UIVALIDATOR_lt_OVERWRITE);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.UIVALIDATOR_lt_OVERWRITE);
		return new DListUI(CadseGCST.UIVALIDATOR_lt_OVERWRITE.getName(),
				"overwrite", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	    @generated
	 */
	public DListUI createFieldListenAttributes() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.UIVALIDATOR_lt_LISTEN_ATTRIBUTES);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.UIVALIDATOR_lt_LISTEN_ATTRIBUTES);
		return new DListUI(
				CadseGCST.UIVALIDATOR_lt_LISTEN_ATTRIBUTES.getName(),
				"listenAttributes", EPosLabel.top, mc, ic, true, false, false,
				false);
	}

}

package fr.imag.adele.cadse.cadseg.pages;

import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_StringToBoolean;
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
public class CadseRuntimeModificationPage1_ModificationPage extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DListUI fieldItemTypes;

	/**
	    @generated
	 */
	protected DListUI fieldExtends;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldExecuted;

	/**
	    @generated
	 */
	protected CadseRuntimeModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public CadseRuntimeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Cadse", "Cadse", "", false, 3);
		this.item = item;
		this.fieldItemTypes = createFieldItemTypes();
		this.fieldExtends = createFieldExtends();
		this.fieldExecuted = createFieldExecuted();
		setActionPage(null);
		addLast(this.fieldItemTypes, this.fieldExtends, this.fieldExecuted);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DListUI createFieldItemTypes() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.CADSE_RUNTIME_lt_ITEM_TYPES);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.CADSE_RUNTIME_lt_ITEM_TYPES);
		return new DListUI(CadseGCST.CADSE_RUNTIME_lt_ITEM_TYPES.getName(),
				"itemTypes", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	    @generated
	 */
	public DListUI createFieldExtends() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.CADSE_RUNTIME_lt_EXTENDS);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.CADSE_RUNTIME_lt_EXTENDS);
		return new DListUI(CadseGCST.CADSE_RUNTIME_lt_EXTENDS.getName(),
				"extends", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldExecuted() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.CADSE_RUNTIME_at_EXECUTED, "executed",
				EPosLabel.none, mc, null);
	}

}

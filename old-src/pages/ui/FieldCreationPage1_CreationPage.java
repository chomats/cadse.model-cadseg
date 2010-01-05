package fr.imag.adele.cadse.cadseg.pages.ui;

import fede.workspace.model.manager.properties.impl.mc.MC_ShortNameItemProperty;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class FieldCreationPage1_CreationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item parent;

	/**
	 * @generated
	 */
	public ItemType it;

	/**
	 * @generated
	 */
	public LinkType lt;

	/**
	    @generated
	 */
	protected DTextUI fieldName;

	/**
	    @generated
	 */
	protected DBrowserUI fieldOverwritefield;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldEditable;

	/**
	 * @generated
	 */
	protected FieldCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public FieldCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create Field", "Create Field", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldName = createFieldName();
		this.fieldOverwritefield = createFieldOverwritefield();
		this.fieldEditable = createFieldEditable();
		setActionPage(null);
		addLast(this.fieldName, this.fieldOverwritefield, this.fieldEditable);

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
	public DBrowserUI createFieldOverwritefield() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.FIELD_lt_OVERWRITEFIELD);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.FIELD_lt_OVERWRITEFIELD);
		return new DBrowserUI(CadseGCST.FIELD_lt_OVERWRITEFIELD.getName(),
				"overwritefield", EPosLabel.left, mc, ic);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldEditable() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.FIELD_at_EDITABLE, "editable",
				EPosLabel.none, mc, null);
	}

}

package fr.imag.adele.cadse.cadseg.pages.ui;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ic.IC_EnumForBrowser_Combo;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.mc.StringToEnumModelController;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
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

/**
 * @generated
 */
public class FieldModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item				item;

	/**
	 * @generated
	 */
	protected DTextUI		__short_name__;

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldAttribute;

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldDisplay;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldReadonly;

	/**
	 * @generated
	 */
	protected DTextUI		fieldLabel;

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldPosition;

	/**
	 * @generated
	 */
	protected FieldModificationPage1_ModificationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public FieldModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Field", "Field", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldAttribute = createFieldAttribute();
		this.fieldDisplay = createFieldDisplay();
		this.fieldReadonly = createFieldReadonly();
		this.fieldLabel = createFieldLabel();
		this.fieldPosition = createFieldPosition();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldAttribute, this.fieldDisplay, this.fieldReadonly, this.fieldLabel,
				this.fieldPosition);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createInternalNameField() {
		return FieldsCore.createUniqueNameField();
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldAttribute() {
		LinkModelController mc = new LinkModelController(true, null, WorkspaceCST.FIELD_lt_ATTRIBUTE);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List("Select a value.", "Select a value.",
				WorkspaceCST.FIELD_lt_ATTRIBUTE);
		return new DBrowserUI(WorkspaceCST.FIELD_lt_ATTRIBUTE.getName(), "attribute", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldDisplay() {
		LinkModelController mc = new LinkModelController(true, null, WorkspaceCST.FIELD_lt_DISPLAY);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List("Select a value.", "Select a value.",
				WorkspaceCST.FIELD_lt_DISPLAY);
		return new DBrowserUI(WorkspaceCST.FIELD_lt_DISPLAY.getName(), "display", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldReadonly() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.FIELD_at_READONLY, "readonly", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldLabel() {
		return new DTextUI(WorkspaceCST.FIELD_at_LABEL, "label", EPosLabel.left, new MC_AttributesItem(), null, 1, "",
				false, false, false);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldPosition() {
		StringToEnumModelController mc = new StringToEnumModelController(EPosLabel.class, EPosLabel.defaultpos);
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("Select a value.", "Select a value.", EPosLabel.class);
		return new DBrowserUI(WorkspaceCST.FIELD_at_POSITION, "position", EPosLabel.left, mc, ic);
	}

}

package fr.imag.adele.cadse.cadseg.pages.view;

import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
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
public class ViewLinkTypeModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DTextUI __short_name__;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldLinkType;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldCanCreateItem;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldCanCreateLink;

	/**
	 * @generated
	 */
	protected DTextUI fieldDisplayCreate;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldAggregation;

	/**
	 * @generated
	 */
	protected ViewLinkTypeModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ViewLinkTypeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "ViewLinkType", "ViewLinkType", "", false,
				3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldLinkType = createFieldLinkType();
		this.fieldCanCreateItem = createFieldCanCreateItem();
		this.fieldCanCreateLink = createFieldCanCreateLink();
		this.fieldDisplayCreate = createFieldDisplayCreate();
		this.fieldAggregation = createFieldAggregation();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldLinkType,
				this.fieldCanCreateItem, this.fieldCanCreateLink,
				this.fieldDisplayCreate, this.fieldAggregation);

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
	public DBrowserUI createFieldLinkType() {
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.VIEW_LINK_TYPE_lt_LINK_TYPE);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.VIEW_LINK_TYPE_lt_LINK_TYPE);
		return new DBrowserUI(CadseGCST.VIEW_LINK_TYPE_lt_LINK_TYPE.getName(),
				"link-type", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldCanCreateItem() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.VIEW_LINK_TYPE_at_CAN_CREATE_ITEM,
				"can-create-item", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldCanCreateLink() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.VIEW_LINK_TYPE_at_CAN_CREATE_LINK,
				"can-create-link", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldDisplayCreate() {
		return new DTextUI(CadseGCST.VIEW_LINK_TYPE_at_DISPLAY_CREATE,
				"display-create", EPosLabel.left, new MC_AttributesItem(),
				null, 1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldAggregation() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.VIEW_LINK_TYPE_at_AGGREGATION,
				"aggregation", EPosLabel.none, mc, null);
	}

}

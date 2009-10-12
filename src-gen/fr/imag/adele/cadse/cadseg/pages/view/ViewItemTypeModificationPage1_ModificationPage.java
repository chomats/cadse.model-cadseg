package fr.imag.adele.cadse.cadseg.pages.view;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * @generated
 */
public class ViewItemTypeModificationPage1_ModificationPage extends PageImpl {

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
	protected DListUI fieldViewLinkTypes;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldItemType;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldRef;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldIsRootElement;

	/**
	 * @generated
	 */
	protected ViewItemTypeModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ViewItemTypeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "ViewItemType", "ViewItemType", "", false,
				3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldViewLinkTypes = createFieldViewLinkTypes();
		this.fieldItemType = createFieldItemType();
		this.fieldRef = createFieldRef();
		this.fieldIsRootElement = createFieldIsRootElement();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldViewLinkTypes,
				this.fieldItemType, this.fieldRef, this.fieldIsRootElement);

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
	public DListUI createFieldViewLinkTypes() {
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.VIEW_ITEM_TYPE_lt_VIEW_LINK_TYPES);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.VIEW_ITEM_TYPE_lt_VIEW_LINK_TYPES);
		return new DListUI(CadseGCST.VIEW_ITEM_TYPE_lt_VIEW_LINK_TYPES
				.getName(), "view-link-types", EPosLabel.top, mc, ic, true,
				false, false, false);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldItemType() {
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.VIEW_ITEM_TYPE_lt_ITEM_TYPE);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.VIEW_ITEM_TYPE_lt_ITEM_TYPE);
		return new DBrowserUI(CadseGCST.VIEW_ITEM_TYPE_lt_ITEM_TYPE.getName(),
				"item-type", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldRef() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.VIEW_ITEM_TYPE_at_REF, "ref",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldIsRootElement() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.VIEW_ITEM_TYPE_at_IS_ROOT_ELEMENT,
				"is-root-element", EPosLabel.none, mc, null);
	}

}
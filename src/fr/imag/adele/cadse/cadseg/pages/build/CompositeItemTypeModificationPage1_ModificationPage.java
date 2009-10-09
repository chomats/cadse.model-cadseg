package fr.imag.adele.cadse.cadseg.pages.build;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
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
public class CompositeItemTypeModificationPage1_ModificationPage extends PageImpl {

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
	protected DBrowserUI	fieldItemType;

	/**
	 * @generated
	 */
	protected DListUI		fieldComposers;

	/**
	 * @generated
	 */
	protected DListUI		fieldBuilders;

	/**
	 * @generated
	 */
	protected CompositeItemTypeModificationPage1_ModificationPage(String id, String label, String title,
			String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public CompositeItemTypeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "CompositeItemType", "CompositeItemType", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldItemType = createFieldItemType();
		this.fieldComposers = createFieldComposers();
		this.fieldBuilders = createFieldBuilders();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldItemType, this.fieldComposers, this.fieldBuilders);

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
	public DBrowserUI createFieldItemType() {
		LinkModelController mc = new LinkModelController(true, null, WorkspaceCST.COMPOSITE_ITEM_TYPE_lt_ITEM_TYPE);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List("Select a value.", "Select a value.",
				WorkspaceCST.COMPOSITE_ITEM_TYPE_lt_ITEM_TYPE);
		return new DBrowserUI(WorkspaceCST.COMPOSITE_ITEM_TYPE_lt_ITEM_TYPE.getName(), "item-type", EPosLabel.left, mc,
				ic);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldComposers() {
		LinkModelController mc = new LinkModelController(false, null, WorkspaceCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List("Select a value.", "Select a value.",
				WorkspaceCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS);
		return new DListUI(WorkspaceCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS.getName(), "composers", EPosLabel.top, mc, ic,
				true, false, false, false);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldBuilders() {
		LinkModelController mc = new LinkModelController(false, null, WorkspaceCST.COMPOSITE_ITEM_TYPE_lt_BUILDERS);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List("Select a value.", "Select a value.",
				WorkspaceCST.COMPOSITE_ITEM_TYPE_lt_BUILDERS);
		return new DListUI(WorkspaceCST.COMPOSITE_ITEM_TYPE_lt_BUILDERS.getName(), "builders", EPosLabel.top, mc, ic,
				true, false, false, false);
	}

}

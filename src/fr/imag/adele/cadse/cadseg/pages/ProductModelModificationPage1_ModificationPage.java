package fr.imag.adele.cadse.cadseg.pages;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
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
public class ProductModelModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item			item;

	/**
	 * @generated
	 */
	protected DTextUI	__short_name__;

	/**
	 * @generated
	 */
	protected DListUI	fieldTypes;

	/**
	 * @generated
	 */
	protected ProductModelModificationPage1_ModificationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ProductModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "ProductModel", "ProductModel", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldTypes = createFieldTypes();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldTypes);

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
	public DListUI createFieldTypes() {
		LinkModelController mc = new LinkModelController(true, null, WorkspaceCST.PRODUCT_MODEL_lt_TYPES);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List("Select a value.", "Select a value.",
				WorkspaceCST.PRODUCT_MODEL_lt_TYPES);
		return new DListUI(WorkspaceCST.PRODUCT_MODEL_lt_TYPES.getName(), "types", EPosLabel.top, mc, ic, true, false,
				false, false);
	}

}

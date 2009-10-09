package fr.imag.adele.cadse.cadseg.pages;

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
public class ContentTypeModificationPage1_ModificationPage extends PageImpl {

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
	protected DListUI		fieldAttributes;

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldSuperType;

	/**
	 * @generated
	 */
	protected ContentTypeModificationPage1_ModificationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ContentTypeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "ContentType", "ContentType", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldAttributes = createFieldAttributes();
		this.fieldSuperType = createFieldSuperType();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldAttributes, this.fieldSuperType);

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
	public DListUI createFieldAttributes() {
		LinkModelController mc = new LinkModelController(false, null, WorkspaceCST.CONTENT_TYPE_lt_ATTRIBUTES);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List("Select a value.", "Select a value.",
				WorkspaceCST.CONTENT_TYPE_lt_ATTRIBUTES);
		return new DListUI(WorkspaceCST.CONTENT_TYPE_lt_ATTRIBUTES.getName(), "attributes", EPosLabel.top, mc, ic,
				true, false, false, false);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldSuperType() {
		LinkModelController mc = new LinkModelController(false, null, WorkspaceCST.CONTENT_TYPE_lt_SUPER_TYPE);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List("Select a value.", "Select a value.",
				WorkspaceCST.CONTENT_TYPE_lt_SUPER_TYPE);
		return new DBrowserUI(WorkspaceCST.CONTENT_TYPE_lt_SUPER_TYPE.getName(), "super-type", EPosLabel.left, mc, ic);
	}

}

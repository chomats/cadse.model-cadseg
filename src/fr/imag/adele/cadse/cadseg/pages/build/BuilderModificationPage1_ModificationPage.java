package fr.imag.adele.cadse.cadseg.pages.build;

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
public class BuilderModificationPage1_ModificationPage extends PageImpl {

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
	protected DListUI	fieldRequires;

	/**
	 * @generated
	 */
	protected BuilderModificationPage1_ModificationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public BuilderModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Builder", "Builder", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldRequires = createFieldRequires();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldRequires);

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
	public DListUI createFieldRequires() {
		LinkModelController mc = new LinkModelController(false, null, WorkspaceCST.BUILDER_lt_REQUIRES);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List("Select a value.", "Select a value.",
				WorkspaceCST.BUILDER_lt_REQUIRES);
		return new DListUI(WorkspaceCST.BUILDER_lt_REQUIRES.getName(), "requires", EPosLabel.top, mc, ic, true, false,
				false, false);
	}

}

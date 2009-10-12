package fr.imag.adele.cadse.cadseg.pages.build;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
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
public class ComposerLinkModificationPage1_ModificationPage extends PageImpl {

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
	protected DBrowserUI fieldLink;

	/**
	 * @generated
	 */
	protected DListUI fieldExporters;

	/**
	 * @generated
	 */
	protected ComposerLinkModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ComposerLinkModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "ComposerLink", "ComposerLink", "", false,
				3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldLink = createFieldLink();
		this.fieldExporters = createFieldExporters();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldLink, this.fieldExporters);

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
	public DBrowserUI createFieldLink() {
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.COMPOSER_LINK_lt_LINK);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.COMPOSER_LINK_lt_LINK);
		return new DBrowserUI(CadseGCST.COMPOSER_LINK_lt_LINK.getName(),
				"link", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldExporters() {
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.COMPOSER_LINK_lt_EXPORTERS);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.COMPOSER_LINK_lt_EXPORTERS);
		return new DListUI(CadseGCST.COMPOSER_LINK_lt_EXPORTERS.getName(),
				"exporters", EPosLabel.top, mc, ic, true, false, false, false);
	}

}

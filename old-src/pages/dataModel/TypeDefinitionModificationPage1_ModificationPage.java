package fr.imag.adele.cadse.cadseg.pages.dataModel;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 @generated
 */
public class TypeDefinitionModificationPage1_ModificationPage extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DListUI fieldAttributes;

	/**
	    @generated
	 */
	protected DTextUI fieldIdRuntime;

	/**
	    @generated
	 */
	protected DListUI fieldFields;

	/**
	    @generated
	 */
	protected DListUI fieldCreationPages;

	/**
	    @generated
	 */
	protected DListUI fieldValidators;

	/**
	    @generated
	 */
	protected DBrowserUI fieldCadse;

	/**
	    @generated
	 */
	protected DListUI fieldModificationPages;

	/**
	    @generated
	 */
	protected TypeDefinitionModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public TypeDefinitionModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "TypeDefinition", "TypeDefinition", "",
				false, 3);
		this.item = item;
		this.fieldAttributes = createFieldAttributes();
		this.fieldIdRuntime = createFieldIdRuntime();
		this.fieldFields = createFieldFields();
		this.fieldCreationPages = createFieldCreationPages();
		this.fieldValidators = createFieldValidators();
		this.fieldCadse = createFieldCadse();
		this.fieldModificationPages = createFieldModificationPages();
		setActionPage(null);
		addLast(this.fieldAttributes, this.fieldIdRuntime, this.fieldFields,
				this.fieldCreationPages, this.fieldValidators, this.fieldCadse,
				this.fieldModificationPages);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DListUI createFieldAttributes() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES);
		return new DListUI(CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES.getName(),
				"attributes", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldIdRuntime() {
		return new DTextUI(CadseGCST.TYPE_DEFINITION_at_ID_RUNTIME,
				"id-runtime", EPosLabel.left, new MC_AttributesItem(), null, 1,
				"", false, false, false);
	}

	/**
	    @generated
	 */
	public DListUI createFieldFields() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.TYPE_DEFINITION_lt_FIELDS);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.TYPE_DEFINITION_lt_FIELDS);
		return new DListUI(CadseGCST.TYPE_DEFINITION_lt_FIELDS.getName(),
				"fields", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	    @generated
	 */
	public DListUI createFieldCreationPages() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.TYPE_DEFINITION_lt_CREATION_PAGES);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.TYPE_DEFINITION_lt_CREATION_PAGES);
		return new DListUI(CadseGCST.TYPE_DEFINITION_lt_CREATION_PAGES
				.getName(), "creation-pages", EPosLabel.top, mc, ic, true,
				false, false, false);
	}

	/**
	    @generated
	 */
	public DListUI createFieldValidators() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.TYPE_DEFINITION_lt_VALIDATORS);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.TYPE_DEFINITION_lt_VALIDATORS);
		return new DListUI(CadseGCST.TYPE_DEFINITION_lt_VALIDATORS.getName(),
				"validators", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldCadse() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.TYPE_DEFINITION_lt_CADSE);
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.TYPE_DEFINITION_lt_CADSE);
		return new DBrowserUI(CadseGCST.TYPE_DEFINITION_lt_CADSE.getName(),
				"cadse", EPosLabel.left, mc, ic);
	}

	/**
	    @generated
	 */
	public DListUI createFieldModificationPages() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES);
		return new DListUI(CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES
				.getName(), "modification-pages", EPosLabel.top, mc, ic, true,
				false, false, false);
	}

}

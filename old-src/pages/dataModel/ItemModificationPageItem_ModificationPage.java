package fr.imag.adele.cadse.cadseg.pages.dataModel;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_PartLinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 @generated
 */
public class ItemModificationPageItem_ModificationPage extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DTextUI fieldCommittedBy;

	/**
	    @generated
	 */
	protected DTextUI fieldDisplayName;

	/**
	    @generated
	 */
	protected DBrowserUI fieldInstanceOf;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldItemHidden;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldItemReadonly;

	/**
	    @generated
	 */
	protected DTextUI fieldName;

	/**
	    @generated
	 */
	protected DBrowserUI fieldParent;

	/**
	    @generated
	 */
	protected DListUI fieldModifiedAttributes;

	/**
	    @generated
	 */
	protected DTextUI fieldQualifiedName;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldRequireNewRev;

	/**
	    @generated
	 */
	protected DTextUI fieldTWVersion;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldRevModified;

	/**
	    @generated
	 */
	protected DTextUI fieldId;

	/**
	    @generated
	 */
	protected DTextUI fieldCommittedDate;

	/**
	    @generated
	 */
	protected DBrowserUI fieldContents;

	/**
	    @generated
	 */
	protected ItemModificationPageItem_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public ItemModificationPageItem_ModificationPage(Item item) {
		super("modification-page-Item", "Item", "Item", "", false, 3);
		this.item = item;
		this.fieldCommittedBy = createFieldCommittedBy();
		this.fieldDisplayName = createFieldDisplayName();
		this.fieldInstanceOf = createFieldInstanceOf();
		this.fieldItemHidden = createFieldItemHidden();
		this.fieldItemReadonly = createFieldItemReadonly();
		this.fieldName = createFieldName();
		this.fieldParent = createFieldParent();
		this.fieldModifiedAttributes = createFieldModifiedAttributes();
		this.fieldQualifiedName = createFieldQualifiedName();
		this.fieldRequireNewRev = createFieldRequireNewRev();
		this.fieldTWVersion = createFieldTWVersion();
		this.fieldRevModified = createFieldRevModified();
		this.fieldId = createFieldId();
		this.fieldCommittedDate = createFieldCommittedDate();
		this.fieldContents = createFieldContents();
		setActionPage(null);
		addLast(this.fieldCommittedBy, this.fieldDisplayName,
				this.fieldInstanceOf, this.fieldItemHidden,
				this.fieldItemReadonly, this.fieldName, this.fieldParent,
				this.fieldModifiedAttributes, this.fieldQualifiedName,
				this.fieldRequireNewRev, this.fieldTWVersion,
				this.fieldRevModified, this.fieldId, this.fieldCommittedDate,
				this.fieldContents);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DTextUI createFieldCommittedBy() {
		return new DTextUI(CadseGCST.ITEM_at_COMMITTED_BY, "committedBy",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldDisplayName() {
		return new DTextUI(CadseGCST.ITEM_at_DISPLAY_NAME, "display-name",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldInstanceOf() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ITEM_lt_INSTANCE_OF);
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.ITEM_lt_INSTANCE_OF);
		return new DBrowserUI(CadseGCST.ITEM_lt_INSTANCE_OF.getName(),
				"instance-of", EPosLabel.left, mc, ic);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldItemHidden() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ITEM_at_ITEM_HIDDEN, "item-hidden",
				EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldItemReadonly() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ITEM_at_ITEM_READONLY,
				"item-readonly", EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldName() {
		return new DTextUI(CadseGCST.ITEM_at_NAME, "name", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldParent() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.", CadseGCST.ITEM_lt_PARENT);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ITEM_lt_PARENT);
		return new DBrowserUI(CadseGCST.ITEM_lt_PARENT.getName(), "parent",
				EPosLabel.left, mc, ic);
	}

	/**
	    @generated
	 */
	public DListUI createFieldModifiedAttributes() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES);
		return new DListUI(CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES.getName(),
				"modified-attributes", EPosLabel.top, mc, ic, true, false,
				false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldQualifiedName() {
		return new DTextUI(CadseGCST.ITEM_at_QUALIFIED_NAME, "qualified-name",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldRequireNewRev() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ITEM_at_REQUIRE_NEW_REV,
				"requireNewRev", EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldTWVersion() {
		IntModelController mc = new IntModelController(0, 0, null, null, null);
		return new DTextUI(CadseGCST.ITEM_at_TW_VERSION, "TW-version",
				EPosLabel.left, mc, null, 1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldRevModified() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ITEM_at_REV_MODIFIED, "revModified",
				EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldId() {
		return new DTextUI(CadseGCST.ITEM_at_ID, "id", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldCommittedDate() {
		return new DTextUI(CadseGCST.ITEM_at_COMMITTED_DATE, "committedDate",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldContents() {
		IC_PartLinkForBrowser_Combo_List ic = new IC_PartLinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.ITEM_lt_CONTENTS, CadseGCST.MANAGER_lt_CONTENT_MODEL,
				"Select a value.");
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.ITEM_lt_CONTENTS);
		return new DBrowserUI(CadseGCST.ITEM_lt_CONTENTS.getName(), "contents",
				EPosLabel.left, mc, ic);
	}

}

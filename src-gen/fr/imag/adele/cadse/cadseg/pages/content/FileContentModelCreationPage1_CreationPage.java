package fr.imag.adele.cadse.cadseg.pages.content;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
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
public class FileContentModelCreationPage1_CreationPage extends
		ResourceContentModelCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected DTextUI fieldFileName;

	/**
	 * @generated
	 */
	protected DTextUI fieldFilePath;

	/**
	 * @generated
	 */
	protected FileContentModelCreationPage1_CreationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public FileContentModelCreationPage1_CreationPage(Item parent, ItemType it,
			LinkType lt) {
		super("creation-page1", "Create FileContentModel",
				"Create FileContentModel", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldExtendsClass = createFieldExtendsClass();
		this.fieldFileName = createFieldFileName();
		this.fieldFilePath = createFieldFilePath();
		setActionPage(null);
		addLast(this.fieldExtendsClass, this.fieldFileName, this.fieldFilePath);

		registerListener();
	}

	@Override
	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @not generated
	 */
	public DTextUI createFieldFileName() {
		return new DTextUI(CadseGCST.FILE_CONTENT_MODEL_at_FILE_NAME,
				"file-name", EPosLabel.left, new MC_AttributesItem() {
					@Override
					public Object defaultValue() {
						return "${#short-name}";
					}
				}, new IC_ItemTypeTemplateForText() {
					@Override
					protected Item getItemFromContext() {
						Item manager = getContext().getPartParent();
						return ManagerManager.getItemType(manager);
					}
				}, 1, "", false, false, false);
	}

	/**
	 * @not generated
	 */
	public DTextUI createFieldFilePath() {
		return new DTextUI(CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH,
				"file-path", EPosLabel.left, new MC_AttributesItem() {
					@Override
					public Object defaultValue() {
						return "/";
					}
				}, new IC_ItemTypeTemplateForText() {
					@Override
					protected Item getItemFromContext() {
						Item manager = getContext().getPartParent();
						return ManagerManager.getItemType(manager);
					}
				}, 1, "", false, false, false);
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// model.workspace.workspace.managers.content.ContentModelManager#createCreationPages(fr.imag.adele.cadse.core.Item,
	// * fr.imag.adele.cadse.core.LinkType,
	// * fr.imag.adele.cadse.core.ItemType)
	// */
	// @Override
	// public Pages createCreationPages(Item theItemParent, LinkType
	// theLinkType, ItemType desType) {
	//
	// ItemType it = desType;
	//
	// String title = it.getAttribute("title-create");
	// CreationAction action = new CreationAction(theItemParent, desType,
	// theLinkType, it.getShortName());
	//
	// return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
	// "Create " + title, "Create " + title, 3,
	// FieldsCore.createCheckBox(CadseGCST.CONTENT_MODEL_at_EXTENDS_CLASS,
	// "extends class"),
	// createFileNameField(), createFilePathField()));
	// }

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// model.workspace.workspace.managers.content.ContentModelManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	// */
	// @Override
	// public Pages createModificationPage(Item item) {
	// AbstractActionPage action = new ModificationAction(item);
	//
	// ItemType it = item.getType();
	//
	// String title = it.getAttribute("title-create");
	//
	// return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
	// "Create " + title, "Create " + title, 3,
	// FieldsCore.createCheckBox(CadseGCST.CONTENT_MODEL_at_EXTENDS_CLASS,
	// "extends class"),
	// createFileNameField(), createFilePathField()));
	// }

}
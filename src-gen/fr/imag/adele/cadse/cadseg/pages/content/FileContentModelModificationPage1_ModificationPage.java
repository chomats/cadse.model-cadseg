package fr.imag.adele.cadse.cadseg.pages.content;

import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
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
public class FileContentModelModificationPage1_ModificationPage extends
		ResourceContentModelModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DTextUI fieldFilePath;

	/**
	 * @generated
	 */
	protected DTextUI fieldFileName;

	/**
	 * @generated
	 */
	protected FileContentModelModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public FileContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "FileContentModel", "FileContentModel", "",
				false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldExtendsClass = createFieldExtendsClass();
		this.fieldFilePath = createFieldFilePath();
		this.fieldFileName = createFieldFileName();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldExtendsClass,
				this.fieldFilePath, this.fieldFileName);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
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

}

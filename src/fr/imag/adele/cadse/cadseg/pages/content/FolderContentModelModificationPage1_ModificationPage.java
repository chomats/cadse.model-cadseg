package fr.imag.adele.cadse.cadseg.pages.content;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_Abstract;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
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
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * @generated
 */
public class FolderContentModelModificationPage1_ModificationPage extends
		ResourceContentModelModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	static public class FolderPathIC extends IC_ItemTypeTemplateForText {

		/**
		 * @generated
		 */
		public FolderPathIC() {
			super();
		}

		@Override
		protected Item getItemFromContext() {
			Item manager = getContext().getPartParent();
			return ManagerManager.getItemType(manager);
		}

	}

	/**
	 * @generated
	 */
	static public class FolderPathMC extends MC_AttributesItem {

		/**
		 * @generated
		 */
		public FolderPathMC() {
			super();
		}

		@Override
		public Object defaultValue() {
			return "${#short-name}";
		}

	}

	/**
	 * @generated
	 */
	protected DTextUI	fieldFolderPath;

	/**
	 * @generated
	 */
	protected FolderContentModelModificationPage1_ModificationPage(String id, String label, String title,
			String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public FolderContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "FolderContentModel", "FolderContentModel", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldExtendsClass = createFieldExtendsClass();
		this.fieldFolderPath = createFieldFolderPath();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldExtendsClass, this.fieldFolderPath);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsClass() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.CONTENT_MODEL_at_EXTENDS_CLASS, "extends-class", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldFolderPath() {
		FolderPathIC ic = new FolderPathIC();
		FolderPathMC mc = new FolderPathMC();
		return new DTextUI(WorkspaceCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH, "folder-path", EPosLabel.left, mc, ic, 1,
				"", false, false, false);
	}

}

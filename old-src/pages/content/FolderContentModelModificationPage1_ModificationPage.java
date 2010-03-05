package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.ic.IC_Abstract;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class FolderContentModelModificationPage1_ModificationPage extends
		ResourceContentModelModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	static public class FolderPathIC extends IC_Abstract {

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
	protected DTextUI fieldFolderPath;

	/**
	 * @generated
	 */
	protected FolderContentModelModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public FolderContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "FolderContentModel", "FolderContentModel",
				"", false, 3);
		this.item = item;
		this.fieldExtendsClass = createFieldExtendsClass();
		this.fieldFolderPath = createFieldFolderPath();
		setActionPage(null);
		addLast(this.fieldExtendsClass, this.fieldFolderPath);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldFolderPath() {
		FolderPathIC ic = new FolderPathIC();
		FolderPathMC mc = new FolderPathMC();
		return new DTextUI(CadseGCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH,
				"folder-path", EPosLabel.left, mc, ic, 1, "", false, false,
				false);
	}

}

package fr.imag.adele.cadse.cadseg.pages.ic;

import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
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
public class IC_FileResourceForBrowser_Combo_ListModificationPage1_ModificationPage
		extends
		IC_ResourceTreeDialogForBrowser_Combo_ListModificationPage1_ModificationPage {

	/**
	    @generated
	 */
	protected DTextUI fieldSelectFolder;

	/**
	    @generated
	 */
	protected DTextUI fieldPatternSelectFile;

	/**
	 * @generated
	 */
	protected IC_FileResourceForBrowser_Combo_ListModificationPage1_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_FileResourceForBrowser_Combo_ListModificationPage1_ModificationPage(
			Item item) {
		super("modification-page1", "IC_FileResourceForBrowser_Combo_List",
				"IC_FileResourceForBrowser_Combo_List", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldSelectFolder = createFieldSelectFolder();
		this.fieldPatternSelectFile = createFieldPatternSelectFile();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldSelectFolder,
				this.fieldPatternSelectFile);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	    @generated
	 */
	public DTextUI createFieldSelectFolder() {
		return new DTextUI(
				CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST_at_SELECT_FOLDER,
				"select-folder", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldPatternSelectFile() {
		return new DTextUI(
				CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST_at_PATTERN_SELECT_FILE,
				"pattern-select-file", EPosLabel.left, new MC_AttributesItem(),
				null, 1, "", false, false, false);
	}

}

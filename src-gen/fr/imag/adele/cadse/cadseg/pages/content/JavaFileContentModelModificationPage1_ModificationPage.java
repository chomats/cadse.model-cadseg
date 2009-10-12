package fr.imag.adele.cadse.cadseg.pages.content;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
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

/**
 * @generated
 */
public class JavaFileContentModelModificationPage1_ModificationPage extends
		FileContentModelModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected JavaFileContentModelModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public JavaFileContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Java File Content", "Java File Content",
				"", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldExtendsClass = createFieldExtendsClass();
		this.fieldFileName = createFieldFileName();
		this.fieldFilePath = createFieldFilePath();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldExtendsClass,
				this.fieldFileName, this.fieldFilePath);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

}

package fr.imag.adele.cadse.cadseg.pages.build;

import fede.workspace.model.manager.properties.impl.mc.MC_ShortNameItemProperty;
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
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.FieldsCore;

/**
 * @generated
 */
public class BuilderCreationPage1_CreationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item parent;

	/**
	 * @generated
	 */
	public ItemType it;

	/**
	 * @generated
	 */
	public LinkType lt;

	/**
	    @generated
	 */
	protected DTextUI fieldName;

	/**
	 * @generated
	 */
	protected BuilderCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public BuilderCreationPage1_CreationPage(Item parent, ItemType it,
			LinkType lt) {
		super("creation-page1", "Create Builder", "Create Builder", "", false,
				3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldName = createFieldName();
		setActionPage(null);
		addLast(this.fieldName);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DTextUI createFieldName() {
		MC_ShortNameItemProperty mc = new MC_ShortNameItemProperty();
		return new DTextUI(CadseGCST.ITEM_at_NAME, "name", EPosLabel.left, mc,
				null, 1, "", false, false, false);
	}

}

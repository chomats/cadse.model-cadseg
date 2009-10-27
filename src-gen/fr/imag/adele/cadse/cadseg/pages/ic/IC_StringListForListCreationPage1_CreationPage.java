package fr.imag.adele.cadse.cadseg.pages.ic;

import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
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

/**
 * @generated
 */
public class IC_StringListForListCreationPage1_CreationPage extends
		IC_AbstractForListCreationPage1_CreationPage {

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldAllowDuplicate;

	/**
	 * @generated
	 */
	protected IC_StringListForListCreationPage1_CreationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_StringListForListCreationPage1_CreationPage(Item parent,
			ItemType it, LinkType lt) {
		super("creation-page1", "Create IC_StringListForList",
				"Create IC_StringListForList", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldAllowDuplicate = createFieldAllowDuplicate();
		setActionPage(null);
		addLast(this.fieldAllowDuplicate);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldAllowDuplicate() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(
				CadseGCST.IC_STRING_LIST_FOR_LIST_at_ALLOW_DUPLICATE,
				"allowDuplicate", EPosLabel.none, mc, null);
	}

}

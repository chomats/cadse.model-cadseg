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
public class IC_StringListForListModificationPage1_ModificationPage extends
		IC_AbstractForListModificationPage1_ModificationPage {

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldAllowDuplicate;

	/**
	 * @generated
	 */
	protected IC_StringListForListModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_StringListForListModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "IC_StringListForList",
				"IC_StringListForList", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldAllowDuplicate = createFieldAllowDuplicate();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldAllowDuplicate);

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

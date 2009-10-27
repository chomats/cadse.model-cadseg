package fr.imag.adele.cadse.cadseg.pages.attributes;

import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;

/**
 @generated
 */
public class UnresolvedAttributeTypeModificationPageUnresolvedAttributeType_ModificationPage
		extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DTextUI __short_name__;

	/**
	    @generated
	 */
	protected UnresolvedAttributeTypeModificationPageUnresolvedAttributeType_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public UnresolvedAttributeTypeModificationPageUnresolvedAttributeType_ModificationPage(
			Item item) {
		super("modification-page-UnresolvedAttributeType",
				"UnresolvedAttributeType", "UnresolvedAttributeType", "",
				false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		setActionPage(null);
		addLast(this.__short_name__);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DTextUI createInternalNameField() {
		return FieldsCore.createUniqueNameField();
	}

}

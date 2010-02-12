package fr.imag.adele.cadse.cadseg.pages.attributes;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;

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
		setActionPage(null);
		addLast();

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

}

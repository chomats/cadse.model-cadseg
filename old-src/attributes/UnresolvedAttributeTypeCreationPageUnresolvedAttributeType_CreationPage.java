package fr.imag.adele.cadse.cadseg.pages.attributes;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;

/**
 @generated
 */
public class UnresolvedAttributeTypeCreationPageUnresolvedAttributeType_CreationPage
		extends PageImpl {

	/**
	    @generated
	 */
	public Item parent;

	/**
	    @generated
	 */
	public ItemType it;

	/**
	    @generated
	 */
	public LinkType lt;

	/**
	    @generated
	 */
	protected UnresolvedAttributeTypeCreationPageUnresolvedAttributeType_CreationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public UnresolvedAttributeTypeCreationPageUnresolvedAttributeType_CreationPage(
			Item parent, ItemType it, LinkType lt) {
		super("creation-page-UnresolvedAttributeType",
				"Create UnresolvedAttributeType",
				"Create UnresolvedAttributeType", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		setActionPage(null);
		addLast();

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

}

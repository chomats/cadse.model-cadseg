package fr.imag.adele.cadse.cadseg.pages.mc;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;

/**
 @generated
 */
public class MC_NameAttributeCreationPageMC_NameAttribute_CreationPage extends
		PageImpl {

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
	protected MC_NameAttributeCreationPageMC_NameAttribute_CreationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public MC_NameAttributeCreationPageMC_NameAttribute_CreationPage(
			Item parent, ItemType it, LinkType lt) {
		super("creation-page-MC_NameAttribute", "Create MC_NameAttribute",
				"Create MC_NameAttribute", "", false, 3);
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
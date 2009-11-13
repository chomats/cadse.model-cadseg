package fr.imag.adele.cadse.cadseg.pages.mc;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;

/**
 @generated
 */
public class MC_NameAttributeModificationPageMC_NameAttribute_ModificationPage
		extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected MC_NameAttributeModificationPageMC_NameAttribute_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public MC_NameAttributeModificationPageMC_NameAttribute_ModificationPage(
			Item item) {
		super("modification-page-MC_NameAttribute", "MC_NameAttribute",
				"MC_NameAttribute", "", false, 3);
		this.item = item;
		setActionPage(null);
		addLast();

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

}

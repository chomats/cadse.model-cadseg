package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;

/**
 @generated
 */
public class IC_BooleanTextCreationPageIC_BooleanText_CreationPage extends
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
	protected IC_BooleanTextCreationPageIC_BooleanText_CreationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public IC_BooleanTextCreationPageIC_BooleanText_CreationPage(Item parent,
			ItemType it, LinkType lt) {
		super("creation-page-IC_BooleanText", "Create IC_BooleanText",
				"Create IC_BooleanText", "", false, 3);
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

package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.core.IItemNode;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.FieldsCore;

/**
 * @generated
 */
public class ContentModelCreationPage1_CreationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item		parent;

	/**
	 * @generated
	 */
	public ItemType	it;

	/**
	 * @generated
	 */
	public LinkType	lt;

	/**
	 * @generated
	 */
	protected ContentModelCreationPage1_CreationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ContentModelCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create ContentModel", "Create ContentModel", "", false, 3);
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

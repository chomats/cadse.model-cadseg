package fr.imag.adele.cadse.cadseg.pages.dataModel;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
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

/**
 * @generated
 */
public class PageListenerCreationPage1_CreationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item				parent;

	/**
	 * @generated
	 */
	public ItemType			it;

	/**
	 * @generated
	 */
	public LinkType			lt;

	/**
	 * @generated
	 */
	protected DTextUI		__short_name__;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldListenShortName;

	/**
	 * @generated
	 */
	protected PageListenerCreationPage1_CreationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public PageListenerCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create PageListener", "Create PageListener", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.__short_name__ = createInternalNameField();
		this.fieldListenShortName = createFieldListenShortName();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldListenShortName);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createInternalNameField() {
		return FieldsCore.createShortNameField();
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldListenShortName() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.PAGE_LISTENER_at_LISTEN_SHORT_NAME, "listen-short-name", EPosLabel.none,
				mc, null);
	}

}

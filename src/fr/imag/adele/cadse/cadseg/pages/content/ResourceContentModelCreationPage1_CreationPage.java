package fr.imag.adele.cadse.cadseg.pages.content;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
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
public class ResourceContentModelCreationPage1_CreationPage extends ContentModelCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldExtendsClass;

	/**
	 * @generated
	 */
	protected ResourceContentModelCreationPage1_CreationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ResourceContentModelCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create ResourceContentModel", "Create ResourceContentModel", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldExtendsClass = createFieldExtendsClass();
		setActionPage(null);
		addLast(this.fieldExtendsClass);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsClass() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.CONTENT_MODEL_at_EXTENDS_CLASS, "extends-class", EPosLabel.none, mc, null);
	}

}

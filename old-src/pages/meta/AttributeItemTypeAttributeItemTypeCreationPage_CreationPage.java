package fr.imag.adele.cadse.cadseg.pages.meta;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class AttributeItemTypeAttributeItemTypeCreationPage_CreationPage extends
		PageImpl {

	/**
	 * @generated
	 */
	public Item parent;

	/**
	 * @generated
	 */
	public ItemType it;

	/**
	 * @generated
	 */
	public LinkType lt;

	/**
	 * @generated
	 */
	protected DTextUI fieldRuntimeQualifiedClass;

	/**
	 * @generated
	 */
	protected AttributeItemTypeAttributeItemTypeCreationPage_CreationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public AttributeItemTypeAttributeItemTypeCreationPage_CreationPage(
			Item parent, ItemType it, LinkType lt) {
		super("attribute-item-type-creation-page",
				"Create an attribute item Type",
				"Create an attribute item Type", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldRuntimeQualifiedClass = createFieldRuntimeQualifiedClass();
		setActionPage(null);
		addLast(this.fieldRuntimeQualifiedClass);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldRuntimeQualifiedClass() {
		return new DTextUI(
				CadseGCST.ATTRIBUTE_ITEM_TYPE_at_RUNTIME_QUALIFIED_CLASS,
				"runtime-qualified-class", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

}

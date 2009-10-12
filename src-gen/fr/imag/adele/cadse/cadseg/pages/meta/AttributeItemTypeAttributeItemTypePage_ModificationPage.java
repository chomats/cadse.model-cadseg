package fr.imag.adele.cadse.cadseg.pages.meta;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * @generated
 */
public class AttributeItemTypeAttributeItemTypePage_ModificationPage extends
		PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DTextUI __short_name__;

	/**
	 * @generated
	 */
	protected DTextUI fieldRuntimeQualifiedClass;

	/**
	 * @generated
	 */
	protected AttributeItemTypeAttributeItemTypePage_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public AttributeItemTypeAttributeItemTypePage_ModificationPage(Item item) {
		super(
				"attribute-item-type-page",
				"Attribute Item Type",
				"Attribute Item Type",
				"This page help to put the description of the type of attribute definition",
				false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldRuntimeQualifiedClass = createFieldRuntimeQualifiedClass();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldRuntimeQualifiedClass);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createInternalNameField() {
		return FieldsCore.createUniqueNameField();
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

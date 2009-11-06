package fr.imag.adele.cadse.cadseg.pages.content;

import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_StringToBoolean;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.FieldsCore;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;

/**
 * @generated
 */
public class ProjectContentModelCreationPage1_CreationPage extends
		ContentItemTypeCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldExtendsClass;
	/**
	 * @generated
	 */
	protected DTextUI fieldProjectName;

	/**
	 * @generated
	 */
	protected ProjectContentModelCreationPage1_CreationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ProjectContentModelCreationPage1_CreationPage(Item parent,
			ItemType it, LinkType lt) {
		super("creation-page1", "Create Project Content",
				"Create Project Content", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldExtendsClass = createFieldExtendsClass();
		this.fieldProjectName = createFieldProjectName();
		setActionPage(null);
		addLast(this.fieldExtendsClass, this.fieldProjectName);

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
		return new DCheckBoxUI(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS,
				"extends-class", EPosLabel.none, mc, null);
	}

	/**
	 * @not generated
	 */
	public DTextUI createFieldProjectName() {
		// return new
		// DTextUI(CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME,
		// "project-name", EPosLabel.left, new MC_AttributesItem(), null,
		// 1, "", false, false, false);
		return FieldsCore.createTextField(
				CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME,
				"project name:", 1, "", new IC_ItemTypeTemplateForText() {
					@Override
					protected Item getItemFromContext() {
						Item manager = getContext().getPartParent();
						return ManagerManager.getItemType(manager);
					}
				}, new MC_AttributesItem() {
					@Override
					public Object defaultValue() {
						return "${#qualified-name}";
					}
				});
	}

}

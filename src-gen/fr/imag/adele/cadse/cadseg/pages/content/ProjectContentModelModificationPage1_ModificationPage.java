package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;

/**
 * @generated
 */
public class ProjectContentModelModificationPage1_ModificationPage extends
		ContentItemTypeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DTextUI fieldProjectName;

	/**
	 * @generated
	 */
	protected ProjectContentModelModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ProjectContentModelModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Project Content", "Project Content", "",
				false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldProjectName = createFieldProjectName();
		this.fieldExtendsClass = createFieldExtendsClass();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldProjectName,
				this.fieldExtendsClass);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
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
						return "${#unique-name}";
					}
				});
	}

}

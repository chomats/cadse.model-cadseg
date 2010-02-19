package fr.imag.adele.cadse.cadseg.pages.content;

import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

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
		this.fieldProjectName = createFieldProjectName();
		this.fieldExtendsClass = createFieldExtendsClass();
		setActionPage(null);
		addLast(this.fieldProjectName, this.fieldExtendsClass);

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

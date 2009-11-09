package fr.imag.adele.cadse.cadseg.pages.dataModel;

import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager.ValidFieldUC;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIPlatform;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.FieldsCore;

/**
 * @generated
 */
public class ItemTypeModificationManager_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected ItemTypeModificationManager_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @not generated
	 */
	public ItemTypeModificationManager_ModificationPage(Item item) {
		super("modification-manager", "Instance name control",
				"Instance name control", "Instance name control", false, 3);

		this.item = ItemTypeManager.getManager(item);

		setActionPage(null);
		// Field display name removed from addLast :
		// FieldsCore.createTextField(CadseGCST.MANAGER_at_HUMAN_NAME,
		// "Display name"),
		addLast(FieldsCore.createTextField(
				CadseGCST.MANAGER_at_LONG_NAME_TEMPLATE,
				"Qualified name template", 1, "",
				new IC_ItemTypeTemplateForText(), new MC_AttributesItem()),
				FieldsCore.createTextField(
						CadseGCST.MANAGER_at_DISPLAY_NAME_TEMPLATE,
						"Display name template", 1, "",
						new IC_ItemTypeTemplateForText(),
						new MC_AttributesItem()), FieldsCore.createTextField(
						CadseGCST.MANAGER_at_VALID_PATTERN_ID,
						"Valid name pattern", new ValidFieldUC()), FieldsCore
						.createTextField(CadseGCST.MANAGER_at_MESSAGE_ERROR_ID,
								"Error message while invalid name"));

		registerListener();
	}

	@Override
	public void init(UIPlatform pageController) throws CadseException {
		for (UIField f : getFields()) {
			f.setItem(item);
		}
		super.init(pageController);
	}

	protected void registerListener() {
		// add init and register
	}
}

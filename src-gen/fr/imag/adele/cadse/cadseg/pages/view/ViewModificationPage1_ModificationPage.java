package fr.imag.adele.cadse.cadseg.pages.view;

import org.eclipse.swt.SWT;

import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_IconResourceForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToOneResourceModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToResourceSimpleModelController;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckedTreeUI;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewManager.IC_ViewManager_DataModelView;
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
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;

/**
 * @generated
 */
public class ViewModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DTextUI __short_name__;

	/**
	    @generated
	 */
	protected DBrowserUI fieldIcon;

	/**
	 * @generated
	 */
	protected ViewModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @not generated
	 */
	public ViewModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "View", "View", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldIcon = createFieldIcon();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldIcon, createFieldDataModel(item));

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
	 * Creates the field icon.
	 * 
	 * @return the UI field
	 * 
	 * 
	 */
	protected DBrowserUI createFieldIcon() {
		return new DBrowserUI(CadseGCST.VIEW_at_ICON, "icon", EPosLabel.left,
				new StringToResourceSimpleModelController(),
				new IC_IconResourceForBrowser_Combo_List(), SWT.BORDER
						| SWT.SINGLE);
	}

	/**
	 * Creates the field data model.
	 * 
	 * @param view
	 *            the view
	 * 
	 * @return the uI field
	 */
	protected DCheckedTreeUI createFieldDataModel(Item view) {
		Item cadsedef = ViewManager.getCadsegModel(view);
		IC_ViewManager_DataModelView ic_mc = new IC_ViewManager_DataModelView(
				CadseDefinitionManager.getDependenciesCadsesAndMe(cadsedef),
				view);
		DCheckedTreeUI checkedTreeUI = new DCheckedTreeUI("sel", "",
				EPosLabel.none, ic_mc, ic_mc, true, false);
		ic_mc.setCheckedTreeUI(checkedTreeUI);
		return checkedTreeUI;
	}
}

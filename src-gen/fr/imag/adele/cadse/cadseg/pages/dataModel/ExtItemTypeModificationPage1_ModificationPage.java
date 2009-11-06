package fr.imag.adele.cadse.cadseg.pages.dataModel;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ITreeContentProvider;

import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ic.ItemTreeContentProvider;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.cadseg.ItemShortNameComparator;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.FieldsCore;

/**
 * @generated
 */
public class ExtItemTypeModificationPage1_ModificationPage extends
		ItemTypeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	static public class RefTypeIC extends IC_LinkForBrowser_Combo_List {

		/**
		 * @generated
		 */
		public RefTypeIC(String title, String message, LinkType linkType) {
			super(title, message, linkType);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#getTreeContentProvider()
		 */
		@Override
		protected ITreeContentProvider getTreeContentProvider() {
			return new ItemTreeContentProvider(new ItemShortNameComparator(),
					CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL,
					CadseGCST.DATA_MODEL_lt_TYPES,
					CadseGCST.DATA_MODEL_lt_CATEGORIES);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#validate(java.lang.Object[])
		 */
		@Override
		public IStatus validate(Object[] selection) {
			if (selection != null && selection.length == 1) {
				Object sel = selection[0];
				if (sel instanceof Item) {
					if (((Item) sel).getType() == CadseGCST.ITEM_TYPE) {
						return Status.OK_STATUS;
					}
				}
			}
			return new Status(Status.ERROR, WSPlugin.PLUGIN_ID,
					"select an item type");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#getInputValues()
		 */
		@Override
		protected Object getInputValues() {
			Item theAttribute = getItem();
			Item theItemType = theAttribute.getPartParent();
			Item cadsedef = theItemType
					.getPartParent(CadseGCST.CADSE_DEFINITION);
			List<Item> allcadse = CadseDefinitionManager
					.getAllDependenciesCadse(cadsedef);
			allcadse.add(cadsedef);
			Item[] ret = (Item[]) allcadse.toArray(new Item[allcadse.size()]);
			Arrays.sort(ret, new ItemShortNameComparator());
			return ret;
		}

	}

	/**
	 * @generated
	 */
	protected DBrowserUI fieldRefType;

	/**
	 * @generated
	 */
	protected ExtItemTypeModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ExtItemTypeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "ExtItemType", "ExtItemType", "", false, 3);
		this.item = item;
		this.fieldRefType = createFieldRefType();
		setActionPage(null);
		addLast(this.fieldRefType);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldRefType() {
		RefTypeIC ic = new RefTypeIC("Select a value.", "Select a value.",
				CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE);
		return new DBrowserUI(CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE.getName(),
				"ref-type", EPosLabel.left, mc, ic);
	}

}

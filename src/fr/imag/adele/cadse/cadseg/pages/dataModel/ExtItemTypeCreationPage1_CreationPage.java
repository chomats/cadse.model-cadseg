package fr.imag.adele.cadse.cadseg.pages.dataModel;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ITreeContentProvider;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.ic.ItemTreeContentProvider;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.cadseg.ItemShortNameComparator;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
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
public class ExtItemTypeCreationPage1_CreationPage extends
		AbstractItemTypeCreationPage1_CreationPage {

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

		// {context <-[parent-part]- -[item-types] ->} - {context} -
		// {context<-[super-type]-*}
		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#getValues()
		 */
		@Override
		public Object[] getValues() {
			Item theAttribute = getItem();
			Item theItemType = theAttribute.getPartParent();
			Item cadsedef = theItemType
					.getPartParent(CadseGCST.CADSE_DEFINITION);

			return ItemTypeManager.getAllAllItemType(cadsedef, null);
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
	protected ExtItemTypeCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ExtItemTypeCreationPage1_CreationPage(Item parent, ItemType it,
			LinkType lt) {
		super("creation-page1", "Create ExtItemType", "Create ExtItemType", "",
				false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.__short_name__ = createInternalNameField();
		this.fieldRefType = createFieldRefType();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldRefType);

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
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE);
		RefTypeIC ic = new RefTypeIC("Select a value.", "Select a value.",
				CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE);
		return new DBrowserUI(CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE.getName(),
				"ref-type", EPosLabel.left, mc, ic);
	}

}

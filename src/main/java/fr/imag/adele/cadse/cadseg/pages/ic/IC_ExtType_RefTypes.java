package fr.imag.adele.cadse.cadseg.pages.ic;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ITreeContentProvider;

import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.cadseg.ItemShortNameComparator;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.ItemTreeContentProvider;

public class IC_ExtType_RefTypes extends IC_LinkForBrowser_Combo_List {


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
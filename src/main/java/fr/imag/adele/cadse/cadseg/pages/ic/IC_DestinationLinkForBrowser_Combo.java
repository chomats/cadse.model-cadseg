package fr.imag.adele.cadse.cadseg.pages.ic;

import java.util.Arrays;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ITreeContentProvider;

import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.cadseg.ItemShortNameComparator;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.DataModelManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemFilter;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.ItemTreeContentProvider;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.Proposal;

/**
 * The Class IC_DestinationLinkForBrowser_Combo.
 */
public final class IC_DestinationLinkForBrowser_Combo extends
		IC_LinkForBrowser_Combo_List {

	

	// {context <-[parent-part]- -[item-types] ->} - {context} -
	// {context<-[super-type]-*}
	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#getValues()
	 */
	@Override
	public Object[] getValues() {
		Item superAttribute = getSuperAttribute();
		final Item superDestinationType = superAttribute == null ? null
				: LinkTypeManager.getDestination(superAttribute);

		Item theAttribute = getItem();
		final Item theItemType = theAttribute.getPartParent();
		Item cadsedef = theItemType
				.getPartParent(CadseGCST.CADSE_DEFINITION);

		return ItemTypeManager.getAllAllItemType(cadsedef,
				new ItemFilter<Item>() {
					public boolean accept(Item item) {
						if (item == theItemType) {
							return false;
						}
						return (superDestinationType == null || ItemTypeManager
								.isSuperTypeOf(superDestinationType, item));
					}

					public boolean stop() {
						return false;
					}
				}, true);
	}

	private Item getSuperAttribute() {
		return ItemTypeManager.findSuperAttribute(_uiPlatform.getItem(), null);
	}

	@Override
	protected fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.Proposal createProposal(Item item, String contents,
			int position, Object[] items) {
		if (contents != null && !item.getName().startsWith(contents)) {
			return null;
		}
		String content = item.getName();

		Item dm = item.getPartParent();

		Item cadse = ItemTypeManager.getCadseDefinition(item);

		String packageString = DataModelManager.getQualifiedDM(dm);
		String cadseString = cadse.getName();

		String label = content;
		String description = "Item Type " + item.getName() + " in package "
				+ packageString + " in cadse " + cadseString;
		return new Proposal(content, label, description, 0, item);
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
				CadseGCST.DATA_MODEL_lt_CATEGORIES,
				CadseGCST.CADSE_lt_ITEM_TYPES);
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
					Item superAttribute = getSuperAttribute();
					final Item superDestinationType = superAttribute == null ? null
							: LinkTypeManager.getDestination(superAttribute);

					if (superDestinationType != null) {
						if (ItemTypeManager.isSuperTypeOf(
								superDestinationType, (Item) sel)
								|| sel == superDestinationType) {
							return Status.OK_STATUS;
						} else {
							return new Status(Status.ERROR,
									WSPlugin.PLUGIN_ID,
									"you must select a sub type of "
											+ superDestinationType
													.getName());

						}
					}

					return Status.OK_STATUS;
				}
				if (((Item) sel).getType() == CadseGCST.EXT_ITEM_TYPE) {
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
		Item[] ret = CadseDefinitionManager
				.getDependenciesCadsesAndMe(cadsedef);
		Arrays.sort(ret, new ItemShortNameComparator());
		return ret;
	}

}
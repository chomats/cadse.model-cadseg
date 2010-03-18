package fr.imag.adele.cadse.cadseg.menu;

import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.view.LinkTypeViewAction;
import fr.imag.adele.cadse.cadseg.managers.view.ViewItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Menu;
import fr.imag.adele.cadse.core.ui.AbstractActionContributor;
import fr.imag.adele.cadse.core.ui.view.ViewDescription;

public class ViewActionContributor extends AbstractActionContributor {

	@Override
	public void contributeMenuAction(ViewDescription viewDescription,
			Menu menu, IItemNode[] selection) {
		if (selection.length == 1) {
			Item item = selection[0].getItem();
			if (item != null) {
				if (item.isInstanceOf(CadseGCST.VIEW_ITEM_TYPE)) {
					vit_contributeMenuNewAction(menu, item);
				}
			}
		}
	}
	
	
	public void vit_contributeMenuNewAction(Menu menu, Item view) {
		if (view.isResolved()) {
			Item itemtype = ViewItemTypeManager.getItemType(view);
			if (itemtype != null) {
				Item[] linkTypes = ItemTypeManager.getOugoingLinkTypes(itemtype);
				for (Item acat : linkTypes) {
					if (acat.getIncomingItem(CadseGCST.VIEW_LINK_TYPE_lt_LINK_TYPE) == null)
						menu.insert(IMenuAction.NEW_MENU_ID, new LinkTypeViewAction(view, acat), true);
				}
			}
		}
	}

}

package fr.imag.adele.cadse.cadseg.menu.group;


import java.util.List;

import org.eclipse.jface.window.IShellProvider;

import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.Menu;
import fr.imag.adele.cadse.core.ui.IActionContributor;
import fr.imag.adele.cadse.core.ui.view.NewContext;
import fr.imag.adele.cadse.core.ui.view.ViewDescription;



/**
    @generated
*/
public class GroupActionActionContributor implements  IActionContributor {

	/**
	    @generated
	*/
	public GroupActionActionContributor() {
	}
	@Override
	public void contributeMenuAction(ViewDescription viewDescription, Menu menu, IItemNode[] selection) {
		for (IItemNode n : selection) {
			Item item = n.getItem();
			if (item == null) continue;
			if (!(item instanceof ItemType)) continue;
			ItemType gt = (ItemType) item;
			ItemType gtType = (ItemType) gt.getGroupType();
			if (gtType == null) continue;
			
			List<LinkType> lts = gtType.getGroupOutgoingLinkTypes();
			for (LinkType glt : lts) {
				insertNewAction(viewDescription, insertNewMenuIfNeed(menu), gt, gtType, glt, glt.getDestination(), n);
			}
		}
	}
	
	NewContext getNewContext(IItemNode n) {
		NewContext ret = new NewContext(n);
	//	setPartContext(ret);
		
		return ret;		
	}

	

	private void insertNewAction(ViewDescription viewDescription, Menu menu, ItemType gt, ItemType gtType, LinkType glt,
			ItemType destinationType, IItemNode n) {
		NewContext c = getNewContext(n);
		c.setGroupHead(gt);
		c.setGroupLinkType(glt);
		c.setGroupType(gtType);
		c.setDestinationType(destinationType);
	//	setPartContext(c);
		
		if (!viewDescription.filterNew(c)) {
			menu.insert(null, new MenuNewAction((IShellProvider) viewDescription.getWindowProvider(), c, gt.getDisplayName()+" "+destinationType.getDisplayName()), true);
		}
		
		for (ItemType it : destinationType.getSubTypes()) {
			insertNewAction(viewDescription, menu, gt, gtType, glt, it, n);
		}
		
	}

	private Menu insertNewMenuIfNeed(Menu menu) {
		Menu newmenu = (Menu) menu.find(IMenuAction.NEW_MENU_ID);
		if (newmenu != null)
			return newmenu;
		newmenu = new Menu(IMenuAction.NEW_MENU_ID, "New", null);
		menu.insert(IMenuAction.CONTEXT_1_MENU, newmenu, true);
		return newmenu;
	}

}


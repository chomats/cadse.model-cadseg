package fr.imag.adele.cadse.cadseg.menu.group;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.window.IShellProvider;

import fede.workspace.tool.view.WSPlugin;
import fede.workspace.tool.view.actions.CreateLinkAction;
import fede.workspace.tool.view.actions.DeleteLinkAction;
import fede.workspace.tool.view.actions.GenerateAction;
import fede.workspace.tool.view.addlink.LinkRootNode;
import fede.workspace.tool.view.menu.RecreatePartLinkAction;
import fede.workspace.tool.view.node.RootNode;
import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.Menu;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.internal.Accessor;
import fr.imag.adele.cadse.core.ui.IActionContributor;
import fr.imag.adele.cadse.core.ui.view.FilterContext;
import fr.imag.adele.cadse.core.ui.view.NewContext;
import fr.imag.adele.cadse.core.ui.view.ViewDescription;
import fr.imag.adele.cadse.eclipse.view.IViewLinkManager;



/**
    @generated
*/
public class GroupActionActionContributor implements  IActionContributor {
	boolean init;
	
	/**
	    @generated
	*/
	public GroupActionActionContributor() {
	}
	
	@Override
	public void contributeMenuAction(ViewDescription viewDescription, Menu principalMenu, IItemNode[] selection) {
		LogicalWorkspace logicalWorkspace = CadseCore.getLogicalWorkspace();
		CadseRuntime[] cr;
		if (!init && ((cr = logicalWorkspace.getCadseRuntime()) != null) && cr.length > 0) {
			cr[0].addDefineNewContext(new RuntimeDefinieNewContext());
			init = true;
		}
		
		HashSet<ItemType> types = new HashSet<ItemType>();
		for (IItemNode in : selection) {
			Item i = in.getItem();
			if (i != null) {
				types.add(i.getType());
			}
			Link l = in.getLink();
			if (l != null) {
				types.add(l.getSource().getType());
				types.add(l.getDestination().getType());
			}
		}

		ArrayList<Item> itemsToRecreatePartLink = new ArrayList<Item>();
		for (IItemNode in : selection) {
			Item i = in.getItem();
			if (i == null) {
				continue;
			}
			Item p = i.getPartParent();
			if (p == null) {
				continue;
			}
			Link l = Accessor.getPartParentLink(i);
			if (l != null) {
				continue;
			}
			itemsToRecreatePartLink.add(i);
		}
		if (itemsToRecreatePartLink.size() != 0) {
			principalMenu.insert(IMenuAction.CONTEXT_5_MENU, new RecreatePartLinkAction(itemsToRecreatePartLink,
					(IShellProvider) viewDescription.getWindowProvider()), true);
		}

		
		if (selection.length <= 1) {
			FilterContext context = new FilterContext(viewDescription, selection.length == 1 ? selection[0] : null);
			NewContext[] newActions = logicalWorkspace.getNewContextFrom(context );
			Menu newmenu = computeNewMenu(newActions);
			if (newmenu != null) {
				principalMenu.insert(IMenuAction.CONTEXT_1_MENU, newmenu, true);
			}
		}

		Set<IItemNode> itemsToDelete = getItemsToDelete(selection);
		if (itemsToDelete.size() != 0) {
			principalMenu.insert(IMenuAction.CONTEXT_1_MENU, new DeleteItemAction(itemsToDelete), true);
		}

		if (Platform.inDevelopmentMode()) {
			Set<IItemNode> itemsToRename = getItemsToRename(selection);
			if (itemsToRename.size() != 0) {
				principalMenu.insert(IMenuAction.CONTEXT_1_MENU, new RenameAction(itemsToRename,
						(IShellProvider) viewDescription.getWindowProvider()), true);
			}
		}

		Set<IItemNode> linksToDelete = getLinksToDelete(selection);
		if (linksToDelete.size() != 0) {
			principalMenu.insert(IMenuAction.CONTEXT_2_MENU, new DeleteLinkAction(linksToDelete,
					(IShellProvider) viewDescription.getWindowProvider()), true);
		}
		
		List<Item> generateContent = getGenerateObject(selection);
		if (generateContent.size() != 0) {
			principalMenu.insert(IMenuAction.CONTEXT_2_MENU, new GenerateAction(generateContent), true);
		}

		if (selection.length == 1) {
			IItemNode node = selection[0];
			Item item = node.getItem();

			if (item != null
					&& item.isResolved()
					&& LinkRootNode.getLinkTypeNodeAndItemTypeNode(new LinkRootNode(), item.getType(), item,
							(IViewLinkManager) viewDescription).length != 0) {
				principalMenu
						.insert(IMenuAction.CONTEXT_2_MENU, new CreateLinkAction(item, (IShellProvider) viewDescription
								.getWindowProvider(), (IViewLinkManager) viewDescription), true);
			}
		}

		principalMenu.insert(IMenuAction.CONTEXT_2_MENU, new AddCadseModelAction(), true);
	}
	
	public IContributionItem findUsingPath(IContributionManager manager, String path) {
		String group;
		String rest = null;
		IContributionItem findMenuManager = null;
		int separator = path.indexOf('/');
		if (separator != -1) {
			group = path.substring(0, separator);
			rest = path.substring(separator + 1);
		} else {
			group = path;
		}
		findMenuManager = manager.find(group);
		if (findMenuManager == null) {
			findMenuManager = new Separator(group);
			manager.add(findMenuManager);
		}
		if (rest != null) {
			path = rest;
			separator = path.indexOf('/');
			String id;
			if (separator != -1) {
				id = path.substring(0, separator);
				rest = path.substring(separator + 1);
			} else {
				id = path;
			}
			findMenuManager = manager.find(id);
			if (findMenuManager == null) {
				findMenuManager = new MenuManager(id, id);
				manager.appendToGroup(group, findMenuManager);
			}
		}

		if (findMenuManager instanceof IMenuManager && rest != null) {
			manager = (IMenuManager) findMenuManager;
			return findUsingPath(manager, rest);
		}
		return findMenuManager;
	}

	protected void computeMenu(ViewDescription viewDescription, Menu principalMenu, IItemNode[] selection) {

		

	}

	protected Set<IItemNode> getLinksToDelete(IItemNode[] selection) {
		HashSet<IItemNode> links = new HashSet<IItemNode>();
		for (int i = 0; i < selection.length; i++) {
			IItemNode iv = selection[i];
			Link l = iv.getLink();

			if (l != null && l.getLinkType().isPart() && l.isLinkResolved()) {
				continue;
			}

			if (canDeleteLink(l)) {
				links.add(iv);
			}
		}
		return links;
	}

	protected List<Item> getGenerateObject(IItemNode[] selection) {
		List<Item> ret = new ArrayList<Item>();
		for (int i = 0; i < selection.length; i++) {
			try {
				IItemNode iiv = selection[i];
				Item item = iiv.getItem();
				if (item != null && item.isResolved() && item.itemHasContent()
						&& item.getContentItem() instanceof IGenerateContent) {
					ret.add(item);
				}
			} catch (Throwable e) {
				WSPlugin.logException(e);
			}
		}
		return ret;
	}

	protected boolean canDeleteLink(Link l) {
		return l != null && !l.isReadOnly() && l.getSource().getType().getItemManager().canDeleteLink(l) == null;
	}

	

	protected Set<IItemNode> getItemsToDelete(IItemNode[] selection) {
		HashSet<IItemNode> items = new HashSet<IItemNode>();
		ONE: for (Object obj : selection) {
			IItemNode iiv;
			if (obj instanceof IItemNode) {
				iiv = ((IItemNode) obj);
			} else {
				continue;
			}
			Item item = iiv.getItem();
			if (item == null || !item.isResolved() || item.isStatic()) {
				continue;
			}

			if (!(iiv.getParent() instanceof RootNode) && item.getType().getItemManager().canDeleteItem(item) != null) {
				continue;
			}
			Link itemLink = iiv.getLink();

			if (itemLink != null && !itemLink.getLinkType().isPart() && item.getType().hasIncomingParts()) {
				continue;
			}

			for (Link l : item.getIncomingLinks()) {
				if (l.isReadOnly()) {
					continue ONE;
				}
			}

			items.add(iiv);
		}

		return items;
	}

	protected Set<IItemNode> getItemsToRename(IItemNode[] selection) {
		HashSet<IItemNode> items = new HashSet<IItemNode>();
		for (IItemNode iiv : selection) {
			if (iiv.getItem() == null || !iiv.getItem().isResolved()) {
				continue;
			}
			Item item = iiv.getItem();

			// cannot rename a readonly item, a static item is readonly
			if (item.isReadOnly() || item.isStatic()) {
				continue;
			}
			if (item.getType().getItemManager().canRenameItem(item) != null) {
				continue;
			}

			items.add(iiv);
		}

		return items;
	}

	/**
	 * Adds the items to show to the given list.
	 * @param newActions 
	 * 
	 * @param list
	 *            the list to add items to
	 */
	protected Menu computeNewMenu(NewContext[] newActions) {
		if (newActions == null || newActions.length == 0) return null;
		
		Comparator<IMenuAction> comparator = new Comparator<IMenuAction>() {
			public int compare(IMenuAction o1, IMenuAction o2) {
				return o1.getLabel().compareTo(o2.getLabel());
			}

		};
		SortedSet<IMenuAction> list = new TreeSet<IMenuAction>(comparator);
		for (NewContext nc : newActions) {
			if (nc == null) continue;
			list.add(new MenuNewAction(nc));
		}
		return new Menu(IMenuAction.NEW_MENU_ID+2, "New2", null, new ArrayList(list));

	}


	/**
	 * Adds the new wizard shortcuts for the current perspective to the given
	 * list.
	 * 
	 * @param list
	 *            the list to add items to
	 * @return <code>true</code> if any items were added, <code>false</code> if
	 *         none were added
	 */
	protected static boolean addShortcuts(List<IMenuAction> list) {
		boolean added = false;
		return added;
	}

	protected Set<IItemNode> getItemsToRefresh(IItemNode[] selection) {
		HashSet<IItemNode> items = new HashSet<IItemNode>(Arrays.asList(selection));
		return items;

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


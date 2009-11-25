package fr.imag.adele.cadse.cadseg.managers;


import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemManager;
import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.core.CadseDomain;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.key.SpaceKeyType;
import fr.imag.adele.cadse.core.util.Convert;
import java.lang.String;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



/**
    @generated
*/
public class CadseManager extends ItemManager {

	/**
	    @generated
	*/
	public CadseManager() {
		super();
	}

	/**
		@generated
	*/
	@Override
	public String computeQualifiedName(Item item, String name, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			Item currentItem;
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(name);
			return sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@Override
	public void init() {
		getItemType().setHasQualifiedNameAttribute(false);
		CadseGCST.CADSE.setSpaceKeyType(new SpaceKeyType(CadseGCST.CADSE, null));
	}

	/**
		@generated
	*/
	@Override
	public String getDisplayName(Item item) {
		try {
			Object value;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
		get  links 'extends' from 'CadseRuntime' to 'CadseRuntime'.
        @generated
    */
    static public List<Link> getExtendsLink(Item cadse) {
        return cadse.getOutgoingLinks(CadseGCST.CADSE_lt_EXTENDS);
    }

	/**
        @generated
    */
    static public Collection<Item> getExtendsAll(Item cadse) {
        return cadse.getOutgoingItems(CadseGCST.CADSE_lt_EXTENDS, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getExtends(Item cadse) {
        return cadse.getOutgoingItems(CadseGCST.CADSE_lt_EXTENDS,true);
    }

	/**
        @generated
    */
    static public void addExtends(Item cadse, Item value) throws CadseException {
        cadse.addOutgoingItem(CadseGCST.CADSE_lt_EXTENDS,value);
    }

	/**
        @generated
    */
    static public void removeExtends(Item cadse, Item value) throws CadseException {
        cadse.removeOutgoingItem(CadseGCST.CADSE_lt_EXTENDS,value);
    }

	/**
		@generated
	*/
	public static final String getDescriptionAttribute(Item cadse) {
		return cadse.getAttributeWithDefaultValue(CadseGCST.CADSE_at_DESCRIPTION_, null);
	}

	/**
		@generated
	*/
	public static final void setDescriptionAttribute(Item cadse, String value) {
		try {
			cadse.setAttribute(CadseGCST.CADSE_at_DESCRIPTION_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getDefaultContentRepoURLAttribute(Item cadse) {
		return cadse.getAttributeWithDefaultValue(CadseGCST.CADSE_at_DEFAULT_CONTENT_REPO_URL_, null);
	}

	/**
		@generated
	*/
	public static final void setDefaultContentRepoURLAttribute(Item cadse, String value) {
		try {
			cadse.setAttribute(CadseGCST.CADSE_at_DEFAULT_CONTENT_REPO_URL_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final boolean isExecutedAttribute(Item cadse) {
		return cadse.getAttributeWithDefaultValue(CadseGCST.CADSE_at_EXECUTED_, false);
	}

	/**
		@generated
	*/
	public static final void setExecutedAttribute(Item cadse, boolean value) {
		try {
			cadse.setAttribute(CadseGCST.CADSE_at_EXECUTED_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getItemRepoLoginAttribute(Item cadse) {
		return cadse.getAttributeWithDefaultValue(CadseGCST.CADSE_at_ITEM_REPO_LOGIN_, null);
	}

	/**
		@generated
	*/
	public static final void setItemRepoLoginAttribute(Item cadse, String value) {
		try {
			cadse.setAttribute(CadseGCST.CADSE_at_ITEM_REPO_LOGIN_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getItemRepoPasswdAttribute(Item cadse) {
		return cadse.getAttributeWithDefaultValue(CadseGCST.CADSE_at_ITEM_REPO_PASSWD_, null);
	}

	/**
		@generated
	*/
	public static final void setItemRepoPasswdAttribute(Item cadse, String value) {
		try {
			cadse.setAttribute(CadseGCST.CADSE_at_ITEM_REPO_PASSWD_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getItemRepoURLAttribute(Item cadse) {
		return cadse.getAttributeWithDefaultValue(CadseGCST.CADSE_at_ITEM_REPO_URL_, null);
	}

	/**
		@generated
	*/
	public static final void setItemRepoURLAttribute(Item cadse, String value) {
		try {
			cadse.setAttribute(CadseGCST.CADSE_at_ITEM_REPO_URL_, value);
		} catch (Throwable t) {

		}
	}

	/**
		get  links 'itemTypes' from 'CadseRuntime' to 'ItemType'.
        @generated
    */
    static public List<Link> getItemTypesLink(Item cadse) {
        return cadse.getOutgoingLinks(CadseGCST.CADSE_lt_ITEM_TYPES);
    }

	/**
        @generated
    */
    static public Collection<Item> getItemTypesAll(Item cadse) {
        return cadse.getOutgoingItems(CadseGCST.CADSE_lt_ITEM_TYPES, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getItemTypes(Item cadse) {
        return cadse.getOutgoingItems(CadseGCST.CADSE_lt_ITEM_TYPES,true);
    }

	/**
        @generated
    */
    static public void addItemTypes(Item cadse, Item value) throws CadseException {
        cadse.addOutgoingItem(CadseGCST.CADSE_lt_ITEM_TYPES,value);
    }

	/**
        @generated
    */
    static public void removeItemTypes(Item cadse, Item value) throws CadseException {
        cadse.removeOutgoingItem(CadseGCST.CADSE_lt_ITEM_TYPES,value);
    }
    
    /**
		@generated
	*/
	public static final String getIdDefinitionAttribute(Item cadse) {
		return cadse.getAttributeWithDefaultValue(CadseGCST.CADSE_at_ID_DEFINITION_, null);
	}

	/**
		@generated
	*/
	public static final void setIdDefinitionAttribute(Item cadse, String value) {
		try {
			cadse.setAttribute(CadseGCST.CADSE_at_ID_DEFINITION_, value);
		} catch (Throwable t) {

		}
	}

	@Override
	public URL getImage(Item item) {
    	if (item == null)
    		return CadseGCST.CADSE.getImage();
		if (((item instanceof CadseRuntime) && ((CadseRuntime) item).isExecuted()) || isExecutedAttribute(item))
			return WSPlugin.getImageURL("Model.Workspace.CadseG", "icons/cadseruntime.png");
		return CadseGCST.CADSE.getImage();
	}
	
	@Override
	public boolean hasImageByItem() {
		return true;
	}

	/**
	 * Gets the all dependencies cadse.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the all dependencies cadse
	 */
	public static List<Item> getAllDependenciesCadse(Item cadseDefinition) {
		List<Item> ret = new ArrayList<Item>();
		Collection<Item> aextends = getExtends(cadseDefinition);
		if (aextends.size() != 0) {
			ret.addAll(aextends);
			for (Item subcadse : aextends) {
				ret.addAll(getAllDependenciesCadse(subcadse));
			}
		}
		return ret;
	}

	/**
	 * Gets the dependencies cadses and me.
	 * 
	 * @param cadsedef
	 *            the cadsedef
	 * 
	 * @return the dependencies cadses and me
	 */
	public static Item[] getDependenciesCadsesAndMe(Item cadsedef) {
		List<Item> allcadse = getAllDependenciesCadse(cadsedef);
		allcadse.add(cadsedef);
		Item[] ret = allcadse.toArray(new Item[allcadse.size()]);
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#canCreateLink(fr.imag
	 * .adele.cadse.core.Item, fr.imag.adele.cadse.core.Item,
	 * fr.imag.adele.cadse.core.LinkType)
	 */
	@Override
	public String canCreateLink(Item source, Item dest, LinkType lt) {
		if (source.getQualifiedName().equals(CadseDomain.CADSE_ROOT_MODEL)) {
			return "Cannot extend this cadse : it's the root cadse";
		}

		if (lt == CadseGCST.CADSE_lt_EXTENDS) {
			if (dest == source) {
				return "Cannot extends self";
			}

			List<Item> deps = getAllDependenciesCadse(dest);
			if (deps.contains(source)) {
				return "Cannot extend this cadse";
			}
		}
		return super.canCreateLink(source, dest, lt);
	}
}


package fr.imag.adele.cadse.cadseg.managers;


import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemManager;
import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.util.Convert;
import java.lang.String;
import java.net.URL;
import java.util.Collection;
import java.util.List;



/**
    @generated
*/
public class CadseRuntimeManager extends ItemManager {

	/**
	    @generated
	*/
	public CadseRuntimeManager() {
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
    static public List<Link> getExtendsLink(Item cadseRuntime) {
        return cadseRuntime.getOutgoingLinks(CadseGCST.CADSE_RUNTIME_lt_EXTENDS);
    }

	/**
        @generated
    */
    static public Collection<Item> getExtendsAll(Item cadseRuntime) {
        return cadseRuntime.getOutgoingItems(CadseGCST.CADSE_RUNTIME_lt_EXTENDS, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getExtends(Item cadseRuntime) {
        return cadseRuntime.getOutgoingItems(CadseGCST.CADSE_RUNTIME_lt_EXTENDS,true);
    }

	/**
        @generated
    */
    static public void addExtends(Item cadseRuntime, Item value) throws CadseException {
        cadseRuntime.addOutgoingItem(CadseGCST.CADSE_RUNTIME_lt_EXTENDS,value);
    }

	/**
        @generated
    */
    static public void removeExtends(Item cadseRuntime, Item value) throws CadseException {
        cadseRuntime.removeOutgoingItem(CadseGCST.CADSE_RUNTIME_lt_EXTENDS,value);
    }

	/**
		@generated
	*/
	public static final String getDescriptionAttribute(Item cadseRuntime) {
		return cadseRuntime.getAttributeWithDefaultValue(CadseGCST.CADSE_RUNTIME_at_DESCRIPTION_, null);
	}

	/**
		@generated
	*/
	public static final void setDescriptionAttribute(Item cadseRuntime, String value) {
		try {
			cadseRuntime.setAttribute(CadseGCST.CADSE_RUNTIME_at_DESCRIPTION_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getDefaultContentRepoURLAttribute(Item cadseRuntime) {
		return cadseRuntime.getAttributeWithDefaultValue(CadseGCST.CADSE_RUNTIME_at_DEFAULT_CONTENT_REPO_URL_, null);
	}

	/**
		@generated
	*/
	public static final void setDefaultContentRepoURLAttribute(Item cadseRuntime, String value) {
		try {
			cadseRuntime.setAttribute(CadseGCST.CADSE_RUNTIME_at_DEFAULT_CONTENT_REPO_URL_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final boolean isExecutedAttribute(Item cadseRuntime) {
		return cadseRuntime.getAttributeWithDefaultValue(CadseGCST.CADSE_RUNTIME_at_EXECUTED_, false);
	}

	/**
		@generated
	*/
	public static final void setExecutedAttribute(Item cadseRuntime, boolean value) {
		try {
			cadseRuntime.setAttribute(CadseGCST.CADSE_RUNTIME_at_EXECUTED_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getItemRepoLoginAttribute(Item cadseRuntime) {
		return cadseRuntime.getAttributeWithDefaultValue(CadseGCST.CADSE_RUNTIME_at_ITEM_REPO_LOGIN_, null);
	}

	/**
		@generated
	*/
	public static final void setItemRepoLoginAttribute(Item cadseRuntime, String value) {
		try {
			cadseRuntime.setAttribute(CadseGCST.CADSE_RUNTIME_at_ITEM_REPO_LOGIN_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getItemRepoPasswdAttribute(Item cadseRuntime) {
		return cadseRuntime.getAttributeWithDefaultValue(CadseGCST.CADSE_RUNTIME_at_ITEM_REPO_PASSWD_, null);
	}

	/**
		@generated
	*/
	public static final void setItemRepoPasswdAttribute(Item cadseRuntime, String value) {
		try {
			cadseRuntime.setAttribute(CadseGCST.CADSE_RUNTIME_at_ITEM_REPO_PASSWD_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getItemRepoURLAttribute(Item cadseRuntime) {
		return cadseRuntime.getAttributeWithDefaultValue(CadseGCST.CADSE_RUNTIME_at_ITEM_REPO_URL_, null);
	}

	/**
		@generated
	*/
	public static final void setItemRepoURLAttribute(Item cadseRuntime, String value) {
		try {
			cadseRuntime.setAttribute(CadseGCST.CADSE_RUNTIME_at_ITEM_REPO_URL_, value);
		} catch (Throwable t) {

		}
	}

	/**
		get  links 'itemTypes' from 'CadseRuntime' to 'ItemType'.
        @generated
    */
    static public List<Link> getItemTypesLink(Item cadseRuntime) {
        return cadseRuntime.getOutgoingLinks(CadseGCST.CADSE_RUNTIME_lt_ITEM_TYPES);
    }

	/**
        @generated
    */
    static public Collection<Item> getItemTypesAll(Item cadseRuntime) {
        return cadseRuntime.getOutgoingItems(CadseGCST.CADSE_RUNTIME_lt_ITEM_TYPES, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getItemTypes(Item cadseRuntime) {
        return cadseRuntime.getOutgoingItems(CadseGCST.CADSE_RUNTIME_lt_ITEM_TYPES,true);
    }

	/**
        @generated
    */
    static public void addItemTypes(Item cadseRuntime, Item value) throws CadseException {
        cadseRuntime.addOutgoingItem(CadseGCST.CADSE_RUNTIME_lt_ITEM_TYPES,value);
    }

	/**
        @generated
    */
    static public void removeItemTypes(Item cadseRuntime, Item value) throws CadseException {
        cadseRuntime.removeOutgoingItem(CadseGCST.CADSE_RUNTIME_lt_ITEM_TYPES,value);
    }
    
    /**
		@generated
	*/
	public static final String getIdDefinitionAttribute(Item cadseRuntime) {
		return cadseRuntime.getAttributeWithDefaultValue(CadseGCST.CADSE_RUNTIME_at_ID_DEFINITION_, null);
	}

	/**
		@generated
	*/
	public static final void setIdDefinitionAttribute(Item cadseRuntime, String value) {
		try {
			cadseRuntime.setAttribute(CadseGCST.CADSE_RUNTIME_at_ID_DEFINITION_, value);
		} catch (Throwable t) {

		}
	}

	@Override
	public URL getImage(Item item) {
    	if (item == null)
    		return CadseGCST.CADSE_RUNTIME.getImage();
		if (((item instanceof CadseRuntime) && ((CadseRuntime) item).isExecuted()) || isExecutedAttribute(item))
			return WSPlugin.getImageURL("Model.Workspace.CadseG", "icons/cadseruntime.png");
		return CadseGCST.CADSE_RUNTIME.getImage();
	}
	
	@Override
	public boolean hasImageByItem() {
		return true;
	}

}


package fr.imag.adele.cadse.cadseg.managers.dataModel;


import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.util.Convert;
import java.lang.String;
import java.util.Collection;
import java.util.List;



/**
    @generated
*/
public class ItemManager extends DefaultItemManager {

	/**
	    @generated
	*/
	public ItemManager() {
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
		@generated
	*/
	public static final String getCommittedByAttribute(Item item) {
		return item.getAttributeWithDefaultValue(CadseGCST.ITEM_at_COMMITTED_BY_, null);
	}

	/**
		@generated
	*/
	public static final void setCommittedByAttribute(Item item, String value) {
		try {
			item.setAttribute(CadseGCST.ITEM_at_COMMITTED_BY_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getDisplayNameAttribute(Item item) {
		return item.getAttributeWithDefaultValue(CadseGCST.ITEM_at_DISPLAY_NAME_, null);
	}

	/**
		@generated
	*/
	public static final void setDisplayNameAttribute(Item item, String value) {
		try {
			item.setAttribute(CadseGCST.ITEM_at_DISPLAY_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
		get a link 'instance-of' from 'Item' to 'ItemType'.
		@generated
	*/
	static public Link getInstanceOfLink(Item item) {
		return item.getOutgoingLink(CadseGCST.ITEM_lt_INSTANCE_OF);
	}

	/**
		get all link destination 'instance-of' from 'Item' to 'ItemType'.
		@generated
	*/
	static public Item getInstanceOfAll(Item item) {
		return item.getOutgoingItem(CadseGCST.ITEM_lt_INSTANCE_OF, false);
	}

	/**
		get resolved link destination 'instance-of' from 'Item' to 'ItemType'.
		@generated
	*/
	static public Item getInstanceOf(Item item) {
		return item.getOutgoingItem(CadseGCST.ITEM_lt_INSTANCE_OF, true);
	}

	/**
		set a link 'instance-of' from 'Item' to 'ItemType'.
		@generated
	*/
	static public void setInstanceOf(Item item, Item value) throws CadseException {
		item.setOutgoingItem(CadseGCST.ITEM_lt_INSTANCE_OF,value);
	}

	/**
		@generated
	*/
	public static final boolean isItemHiddenAttribute(Item item) {
		return item.getAttributeWithDefaultValue(CadseGCST.ITEM_at_ITEM_HIDDEN_, false);
	}

	/**
		@generated
	*/
	public static final void setItemHiddenAttribute(Item item, boolean value) {
		try {
			item.setAttribute(CadseGCST.ITEM_at_ITEM_HIDDEN_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final boolean isItemReadonlyAttribute(Item item) {
		return item.getAttributeWithDefaultValue(CadseGCST.ITEM_at_ITEM_READONLY_, false);
	}

	/**
		@generated
	*/
	public static final void setItemReadonlyAttribute(Item item, boolean value) {
		try {
			item.setAttribute(CadseGCST.ITEM_at_ITEM_READONLY_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getNameAttribute(Item item) {
		return item.getAttributeWithDefaultValue(CadseGCST.ITEM_at_NAME_, null);
	}

	/**
		@generated
	*/
	public static final void setNameAttribute(Item item, String value) {
		try {
			item.setAttribute(CadseGCST.ITEM_at_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
		get a link 'parent' from 'Item' to 'Item'.
		@generated
	*/
	static public Link getParentLink(Item item) {
		return item.getOutgoingLink(CadseGCST.ITEM_lt_PARENT);
	}

	/**
		get all link destination 'parent' from 'Item' to 'Item'.
		@generated
	*/
	static public Item getParentAll(Item item) {
		return item.getOutgoingItem(CadseGCST.ITEM_lt_PARENT, false);
	}

	/**
		get resolved link destination 'parent' from 'Item' to 'Item'.
		@generated
	*/
	static public Item getParent(Item item) {
		return item.getOutgoingItem(CadseGCST.ITEM_lt_PARENT, true);
	}

	/**
		set a link 'parent' from 'Item' to 'Item'.
		@generated
	*/
	static public void setParent(Item item, Item value) throws CadseException {
		item.setOutgoingItem(CadseGCST.ITEM_lt_PARENT,value);
	}

	/**
		get  links 'modified-attributes' from 'Item' to 'Attribute'.
        @generated
    */
    static public List<Link> getModifiedAttributesLink(Item item) {
        return item.getOutgoingLinks(CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES);
    }

	/**
        @generated
    */
    static public Collection<Item> getModifiedAttributesAll(Item item) {
        return item.getOutgoingItems(CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getModifiedAttributes(Item item) {
        return item.getOutgoingItems(CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES,true);
    }

	/**
        @generated
    */
    static public void addModifiedAttributes(Item item, Item value) throws CadseException {
        item.addOutgoingItem(CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES,value);
    }

	/**
        @generated
    */
    static public void removeModifiedAttributes(Item item, Item value) throws CadseException {
        item.removeOutgoingItem(CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES,value);
    }

	/**
		@generated
	*/
	public static final String getQualifiedNameAttribute(Item item) {
		return item.getAttributeWithDefaultValue(CadseGCST.ITEM_at_QUALIFIED_NAME_, null);
	}

	/**
		@generated
	*/
	public static final void setQualifiedNameAttribute(Item item, String value) {
		try {
			item.setAttribute(CadseGCST.ITEM_at_QUALIFIED_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final boolean isRequireNewRevAttribute(Item item) {
		return item.getAttributeWithDefaultValue(CadseGCST.ITEM_at_REQUIRE_NEW_REV_, false);
	}

	/**
		@generated
	*/
	public static final void setRequireNewRevAttribute(Item item, boolean value) {
		try {
			item.setAttribute(CadseGCST.ITEM_at_REQUIRE_NEW_REV_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final int getTWVersionAttribute(Item item) {
		return item.getAttributeWithDefaultValue(CadseGCST.ITEM_at_TW_VERSION_, -1);
	}

	/**
		@generated
	*/
	public static final void setTWVersionAttribute(Item item, int value) {
		try {
			item.setAttribute(CadseGCST.ITEM_at_TW_VERSION_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final boolean isRevModifiedAttribute(Item item) {
		return item.getAttributeWithDefaultValue(CadseGCST.ITEM_at_REV_MODIFIED_, false);
	}

	/**
		@generated
	*/
	public static final void setRevModifiedAttribute(Item item, boolean value) {
		try {
			item.setAttribute(CadseGCST.ITEM_at_REV_MODIFIED_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getIdAttribute(Item item) {
		return item.getAttributeWithDefaultValue(CadseGCST.ITEM_at_ID_, null);
	}

	/**
		@generated
	*/
	public static final void setIdAttribute(Item item, String value) {
		try {
			item.setAttribute(CadseGCST.ITEM_at_ID_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final long getCommittedDateAttribute(Item item) {
		return item.getAttributeWithDefaultValue(CadseGCST.ITEM_at_COMMITTED_DATE_, 0L);
	}

	/**
		@generated
	*/
	public static final void setCommittedDateAttribute(Item item, long value) {
		try {
			item.setAttribute(CadseGCST.ITEM_at_COMMITTED_DATE_, value);
		} catch (Throwable t) {

		}
	}

	/**
		get  links 'contents' from 'Item' to 'ContentItem'.
        @generated
    */
    static public Link getContentsLink(Item item) {
		return item.getOutgoingLink(CadseGCST.ITEM_lt_CONTENTS);
	}

	/**
        @generated
    */
    static public Item getContentsAll(Item item) {
		return item.getOutgoingItem(CadseGCST.ITEM_lt_CONTENTS, false);
	}

	/**
        @generated
    */
    static public Item getContents(Item item) {
		return item.getOutgoingItem(CadseGCST.ITEM_lt_CONTENTS, true);
	}

	/**
		set a link 'contents' from 'Item' to 'ContentItem'.
		@generated
	*/
	static public void setContents(Item item, Item value) throws CadseException {
		item.setOutgoingItem(CadseGCST.ITEM_lt_CONTENTS,value);
	}

}


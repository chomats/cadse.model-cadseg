package fr.imag.adele.cadse.cadseg.ext.group;


import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import java.util.Collection;
import java.util.List;



/**
    @generated
*/
public class GroupExtItemExt {

	/**
	    @generated
	*/
	public GroupExtItemExt() {
	}	/**
		get  links 'members' from 'GroupExtItem' to 'Item'.
        @generated
    */
    static public List<Link> getMembersLink(Item groupExtItem) {
        return groupExtItem.getOutgoingLinks(CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS);
    }

    /**
        @generated
    */
    static public Collection<Item> getMembersAll(Item groupExtItem) {
        return groupExtItem.getOutgoingItems(CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS, false);
    }

    /**
        @generated
    */
    static public Collection<Item> getMembers(Item groupExtItem) {
        return groupExtItem.getOutgoingItems(CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS,true);
    }

    /**
        @generated
    */
    static public void addMembers(Item groupExtItem, Item value) throws CadseException {
        groupExtItem.addOutgoingItem(CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS,value);
    }

    /**
        @generated
    */
    static public void removeMembers(Item groupExtItem, Item value) throws CadseException {
        groupExtItem.removeOutgoingItem(CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS,value);
    }


	/**
		get a link 'memberOf' from 'GroupExtItem' to 'Item'.
		@generated
	*/
	static public Link getMemberOfLink(Item groupExtItem) {
		return groupExtItem.getOutgoingLink(CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF);
	}

	/**
		get all link destination 'memberOf' from 'GroupExtItem' to 'Item'.
		@generated
	*/
	static public Item getMemberOfAll(Item groupExtItem) {
		return groupExtItem.getOutgoingItem(CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF, false);
	}

	/**
		get resolved link destination 'memberOf' from 'GroupExtItem' to 'Item'.
		@generated
	*/
	static public Item getMemberOf(Item groupExtItem) {
		return groupExtItem.getOutgoingItem(CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF, true);
	}

	/**
		set a link 'memberOf' from 'GroupExtItem' to 'Item'.
		@generated
	*/
	static public void setMemberOf(Item groupExtItem, Item value) throws CadseException {
		groupExtItem.setOutgoingItem(CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF,value);
	}

}


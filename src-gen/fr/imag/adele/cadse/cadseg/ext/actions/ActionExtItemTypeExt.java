package fr.imag.adele.cadse.cadseg.ext.actions;


import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;



/**
    @generated
*/
public class ActionExtItemTypeExt {

	/**
	    @generated
	*/
	public ActionExtItemTypeExt() {
	}

	/**
		get a link 'actions-model' from 'ActionExtItemType' to 'Menu'.
		@generated
	*/
	static public Link getActionsModelLink(Item actionExtItemType) {
		return actionExtItemType.getOutgoingLink(CadseGCST.ACTION_EXT_ITEM_TYPE_lt_ACTIONS_MODEL);
	}

	/**
		get all link destination 'actions-model' from 'ActionExtItemType' to 'Menu'.
		@generated
	*/
	static public Item getActionsModelAll(Item actionExtItemType) {
		return actionExtItemType.getOutgoingItem(CadseGCST.ACTION_EXT_ITEM_TYPE_lt_ACTIONS_MODEL, false);
	}

	/**
		get resolved link destination 'actions-model' from 'ActionExtItemType' to 'Menu'.
		@generated
	*/
	static public Item getActionsModel(Item actionExtItemType) {
		return actionExtItemType.getOutgoingItem(CadseGCST.ACTION_EXT_ITEM_TYPE_lt_ACTIONS_MODEL, true);
	}

	/**
		set a link 'actions-model' from 'ActionExtItemType' to 'Menu'.
		@generated
	*/
	static public void setActionsModel(Item actionExtItemType, Item value) throws CadseException {
		actionExtItemType.setOutgoingItem(CadseGCST.ACTION_EXT_ITEM_TYPE_lt_ACTIONS_MODEL,value);
	}

}


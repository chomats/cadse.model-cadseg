package extItemType;


import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;



/**
    @generated
*/
public class extItemType {

	/**
	    @generated
	*/
	public extItemType() {
	}

	/**
		get a link 'actions-model' from 'extItemType' to 'Menu'.
		@generated
	*/
	static public Link getActionsModelLink(Item extItemType) {
		return extItemType.getOutgoingLink(CadseGCST.EXT_ITEM_TYPE_lt_ACTIONS_MODEL);
	}

	/**
		get all link destination 'actions-model' from 'extItemType' to 'Menu'.
		@generated
	*/
	static public Item getActionsModelAll(Item extItemType) {
		return extItemType.getOutgoingItem(CadseGCST.EXT_ITEM_TYPE_lt_ACTIONS_MODEL, false);
	}

	/**
		get resolved link destination 'actions-model' from 'extItemType' to 'Menu'.
		@generated
	*/
	static public Item getActionsModel(Item extItemType) {
		return extItemType.getOutgoingItem(CadseGCST.EXT_ITEM_TYPE_lt_ACTIONS_MODEL, true);
	}

	/**
		set a link 'actions-model' from 'extItemType' to 'Menu'.
		@generated
	*/
	static public void setActionsModel(Item extItemType, Item value) throws CadseException {
		extItemType.setOutgoingItem(CadseGCST.EXT_ITEM_TYPE_lt_ACTIONS_MODEL,value);
	}

}


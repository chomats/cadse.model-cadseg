package fr.imag.adele.cadse.cadseg.managers.view;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class LinkTypeViewAction.
 */
public class LinkTypeViewAction extends IMenuAction {

	/** The viewitemtype. */
	Item	viewitemtype;

	/** The linktype. */
	Item	linktype;

	/**
	 * Instantiates a new link type view action.
	 * 
	 * @param viewitemtype
	 *            the viewitemtype
	 * @param linktype
	 *            the linktype
	 */
	public LinkTypeViewAction(Item viewitemtype, Item linktype) {
		this.linktype = linktype;
		this.viewitemtype = viewitemtype;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#run(fr.imag.adele.cadse.core.IItemNode[])
	 */
	@Override
	public void run(IItemNode[] selection) throws CadseException {
		ViewManager.createViewLinkType2(viewitemtype, linktype);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#getImage()
	 */
	@Override
	public String getImage() {
		return linktype.getType().getImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#getLabel()
	 */
	@Override
	public String getLabel() {
		return "Add link type " + linktype.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#getMenuPath()
	 */
	@Override
	public String getMenuPath() {
		return NEW_MENU;
	}

}
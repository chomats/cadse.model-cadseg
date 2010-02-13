package fr.imag.adele.cadse.cadseg.teamwork.ui;

import java.util.UUID;

import fede.workspace.tool.view.ItemInViewer;
import fede.workspace.tool.view.node.AbstractCadseViewNode;
import fede.workspace.tool.view.node.CadseViewModelController;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * A tree node which represents an item in a Teamwork repository.
 * 
 * @author Thomas
 *
 */
public class ErrorNode extends AbstractCadseViewNode {

	private String _errorMsg;
	
	public ErrorNode(CadseViewModelController viewer, AbstractCadseViewNode parent, 
			String errorMsg) {
		super(ItemInViewer.OTHER, parent);
		_errorMsg = errorMsg;
		ctl = viewer;
	}
	
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0) && _errorMsg.equals(((ErrorNode) arg0).getErrorMsg());
	}

	public String getErrorMsg() {
		return _errorMsg;
	}

	@Override
	public int hashCode() {
		return _errorMsg.hashCode();
	}
	
	@Override
	public Object getElementModel() {
		return null;
	}
	
	@Override
	public Item getItem() {
		return null;
	}
	@Override
	public Link getLink() {
		return null;
	}
	@Override
	public LinkType getLinkType() {
		return null;
	}
	
	@Override
	public ItemType getItemType() {
		return null;
	}
	
	@Override
	public String toString() {
		return getErrorMsg();
	}
	
	@Override
	public String getName() {
		return null;
	}
	
	public String getItemName() {
		return null;
	}
}
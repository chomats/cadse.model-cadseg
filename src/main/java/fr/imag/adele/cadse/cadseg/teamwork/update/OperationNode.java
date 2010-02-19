package fr.imag.adele.cadse.cadseg.teamwork.update;

import fede.workspace.tool.view.ItemInViewer;
import fede.workspace.tool.view.node.AbstractCadseViewNode;
import fede.workspace.tool.view.node.CadseViewModelController;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

public class OperationNode extends AbstractCadseViewNode {
	
	private OpToPerform _op;

	protected OperationNode(CadseViewModelController viewer, AbstractCadseViewNode parent,
			OpToPerform op) {
		super(ItemInViewer.OTHER, parent);
		ctl = viewer;
		_op = op;
	}
	
	public OpToPerform getOperation() {
		return _op;
	}

	@Override
	public Object getElementModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Link getLink() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkType getLinkType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}

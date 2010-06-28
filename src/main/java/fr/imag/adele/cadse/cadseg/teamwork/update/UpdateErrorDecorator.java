package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.UUID;

import org.eclipse.swt.graphics.Image;

import fede.workspace.tool.view.node.AbstractCadseViewNode;
import fede.workspace.tool.view.node.ItemNode;
import fr.imag.adele.cadse.cadseg.teamwork.ui.ErrorDecorator;
import fr.imag.adele.cadse.core.Item;

public class UpdateErrorDecorator extends ErrorDecorator {
	
	private UpdateState _updateState;

	public UpdateErrorDecorator(UpdateState updateState) {
		_updateState = updateState;
	}

	@Override
	public Image decorateImage(Image image, Object element) {
		OpToPerform op = null;
		OperationNode opNode = null;
		if (element instanceof OperationNode) {
			opNode = (OperationNode) element;
			op = opNode.getOperation();
		}
		if (op == null)
			return null;
		
		UUID itemId = op.getItemId();
		Item item = _updateState.getTransaction().getItem(itemId);
		if (item == null)
			return null;
		
		if (_updateState.getOperationsToPerformErrors().isInError(itemId)) {
			return computeImage(image, "icons/delete_ovr.gif");
		}
		if (_updateState.isUpdated(itemId)) {
			return computeImage(image, "icons/add_ovr.gif");
		}

		return null;
	}

}

package fr.imag.adele.cadse.cadseg.teamwork.commit;

import java.util.UUID;

import org.eclipse.swt.graphics.Image;

import fr.imag.adele.cadse.cadseg.teamwork.ui.ErrorDecorator;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;

public class CommitErrorDecorator extends ErrorDecorator {
	
	private CommitState _commitState;
	
	public CommitErrorDecorator(CommitState commitState) {
		_commitState = commitState;
	}

	public Image decorateImage(Image image, Object element) {
		if (image == null) {
			return null;
		}

		if (element == null) {
			return null;
		}

		if (!(element instanceof IItemNode)) {
			return null;
		}

		IItemNode itemNode = (IItemNode) element;
		Item item = itemNode.getItem();
		if (item == null) {
			return null;
		}
		UUID itemId = item.getId();
		if (_commitState.getErrors().isInError(itemId)) {
			return computeImage(image, "icons/delete_ovr.gif");
		}
		if (_commitState.isCommitted(itemId)) {
			return computeImage(image, "icons/add_ovr.gif");
		}

		return null;
	}
}

package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_AbstractForList;

/**
 * Interaction controller used to represent a list of errors.
 * 
 * @author Thomas
 * 
 */
public class IC_ErrorsForList extends IC_AbstractForList implements ILabelProvider {

	private UpdateState	_updateState;
	private OperationCategory _opCateg;
	
	public IC_ErrorsForList(UpdateState updateState, OperationCategory opCateg) {
		super("", "");
		_updateState = updateState;
		_opCateg = opCateg;
	}

	@Override
	public Object[] getValues() {
		List<fr.imag.adele.cadse.cadseg.teamwork.Error> errors = null;
		if (OperationCategory.IMPACTS.equals(_opCateg))
			errors = _updateState.getDefinition().getErrors().getErrors();
		else if (OperationCategory.TO_PERFORM.equals(_opCateg))
			errors = _updateState.getOperationsToPerformErrors().getErrors();
		
		return errors.toArray(new fr.imag.adele.cadse.cadseg.teamwork.Error[errors.size()]);
	}

	public ItemType getType() {
		return null;
	}

	public ILabelProvider getLabelProvider() {
		return this;
	}

	public Image getImage(Object element) {
		return null;
	}

	public String getText(Object element) {
		if ((element == null) || !(element instanceof fr.imag.adele.cadse.cadseg.teamwork.Error)) {
			return "";
		}

		fr.imag.adele.cadse.cadseg.teamwork.Error error = (fr.imag.adele.cadse.cadseg.teamwork.Error) element;
		return error.getMessage();
	}

	public void addListener(ILabelProviderListener listener) {
		// do nothing
	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// do nothing
	}
}

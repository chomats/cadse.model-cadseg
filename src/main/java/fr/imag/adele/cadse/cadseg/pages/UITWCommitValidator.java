/**
 * 
 */
package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.enumdef.TWCommitKind;
import fr.imag.adele.cadse.core.impl.attribute.AttributeType;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;

public class UITWCommitValidator extends AbstractUIRunningValidator {
	
	@Override
	public boolean validValue(UIField field, Object value) {
		
		return validValue();
	}
	
	@Override
	public boolean validValueChanged(UIField field, Object value) {
		if (field.getAttributeDefinition() == CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_) {
			return validValue();
		}
		
		return false;
	}

	private boolean validValue() {
		//FIXME: Class cast exception (test create a link type)
		AttributeType attr = (AttributeType) _uiPlatform.getItem(); 
		
		TWCommitKind commitKind = AttributeManager.getTWCommitKindAttribute(attr);
		boolean hasReconcile = TWCommitKind.reconcile.equals(commitKind);
		boolean isList = AttributeManager.isIsListAttribute(attr);
		if (!isList && hasReconcile) {
			_uiPlatform.setMessageError("Attribute must be a list one to apply " + 
				TWCommitKind.reconcile.toString() + " evolution politic.");
			return true;
		} else
			return false;
	}
}
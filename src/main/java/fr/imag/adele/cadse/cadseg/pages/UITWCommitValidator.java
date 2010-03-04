/**
 * 
 */
package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.enumdef.TWCommitKind;
import fr.imag.adele.cadse.core.impl.attribute.AttributeType;
import fr.imag.adele.cadse.core.transaction.delta.ItemDelta;
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
		Item attrItem = _uiPlatform.getItem();
		if (attrItem instanceof ItemDelta) {
			attrItem = ((ItemDelta)attrItem).getBaseItem();
		}
		if (attrItem instanceof AttributeType) {
			AttributeType attr = (AttributeType) attrItem;
			
			TWCommitKind commitKind = AttributeManager.getTWCommitKindAttribute(attr);
			boolean hasReconcile = TWCommitKind.reconcile.equals(commitKind);
			boolean isList = AttributeManager.isIsListAttribute(attr);
			if (!isList && hasReconcile) {
				_uiPlatform.setMessageError("Attribute must be a list one to apply " + 
					TWCommitKind.reconcile.toString() + " evolution politic.");
				return true;
			}
		}
		return false;
	}
}
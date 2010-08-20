package fr.imag.adele.cadse.cadseg.validators;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.ui.mc.UIValidator_Descriptor;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;

public class NotEmptyValidator extends AbstractUIRunningValidator {

	
	@Override
	public boolean validValueChanged(UIField field, Object value) {
		if (value instanceof String && ((String)value).length() == 0) {
			_uiPlatform.setMessageError(field.getAttributeDefinition().getDisplayName()+" not empty");
			return true;
		}
		return super.validValueChanged(field, value);
	}
	
	@Override
	public boolean validValue(UIField field, Object val) {
		Item item = _uiPlatform.getItem();
		IAttributeType<?>[] listenAttribute = ((UIValidator_Descriptor)_desc).getListenAttributes();
		for (IAttributeType<?> attributeType : listenAttribute) {
			Object value = item.getAttribute(attributeType);
			if (value instanceof String && ((String)value).length() == 0) {
				_uiPlatform.setMessageError(attributeType.getDisplayName()+" not empty");
				return true;
			}	
		}
		return false;
	}
}

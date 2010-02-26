package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.attribute.CheckStatus;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;

public class CannotBeUndefinedValidator extends AbstractUIRunningValidator {

	
	@Override
	public boolean validValue(UIField field, Object value) {
		if (_uiPlatform.isModification()) return false;
		
		Item item = _uiPlatform.getItem();
		IAttributeType<?>[] attribute = item.getLocalAllAttributeTypes();
		for (IAttributeType<?> att : attribute) {
			if (att.canBeUndefined()) continue;
			if (item.isDelegatedValue(att)) continue;
			if (att instanceof LinkType) {
				
			} else {
				Object v = item.getAttribute(att);
				CheckStatus error = att.check(item, v);
				if (error != null) {
					if (error.getType() == UIPlatform.ERROR) {
						_uiPlatform.setMessageError("Hidden attribute "+att.getName()+": "+error.getFormatedMessage());
						return true;
					}
					_uiPlatform.setMessage("Cached attribute "+att.getName()+": "+error.getFormatedMessage(), error.getType());
				}
			}
		}
		return super.validValue(field, value);
	}
}

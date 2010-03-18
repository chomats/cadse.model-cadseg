package fr.imag.adele.cadse.cadseg.validators;

import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;

public class LinkTypeIsGoupListener extends AbstractUIRunningValidator {

	@Override
	public void notifieValueChanged(UIField field, Object value) {
		if (value == Boolean.TRUE) {
			Item item = _uiPlatform.getItem(field);
			LinkTypeManager.setPartAttribute(item, true);
		}
	}
}

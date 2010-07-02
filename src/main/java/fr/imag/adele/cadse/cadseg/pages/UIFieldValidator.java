package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;

public class UIFieldValidator extends AbstractUIRunningValidator {

	
	@Override
	public void init(UIPlatform uiPlatform) {
		super.init(uiPlatform);
	}
	
	@Override
	public void notifieValueChanged(UIField field, Object value) {
		if (field.getAttributeDefinition() == CadseGCST.FIELD_lt_ATTRIBUTE) {
			if (!_uiPlatform.isModification()) {
				Item item = _uiPlatform.getItem();
				try {
					item.setAttribute(CadseGCST.ITEM_at_DISPLAY_NAME_, value);
					item.setAttribute(CadseGCST.FIELD_at_LABEL_, value);
				} catch (CadseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}

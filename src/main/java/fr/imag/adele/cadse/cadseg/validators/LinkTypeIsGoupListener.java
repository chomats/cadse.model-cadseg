package fr.imag.adele.cadse.cadseg.validators;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;

public class LinkTypeIsGoupListener extends AbstractUIRunningValidator {

	
	@Override
	public void initAfterUI() {
		if (_uiPlatform.isModification()) {
			Item item = _uiPlatform.getItem();
			_uiPlatform.setEditable(CadseGCST.LINK_TYPE_at_PART_, !item.getAttribute(CadseGCST.LINK_TYPE_at_GROUP_));
		}
	}
	
	@Override
	public void notifieValueChanged(UIField field, Object value) {
		if (value == Boolean.TRUE) {
			_uiPlatform.setVisualValue(CadseGCST.LINK_TYPE_at_PART_, true, true);
			_uiPlatform.setEditable(CadseGCST.LINK_TYPE_at_PART_, false);
		}
		else
			_uiPlatform.setEditable(CadseGCST.LINK_TYPE_at_PART_, true);
	}
}

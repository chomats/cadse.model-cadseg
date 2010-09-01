package fr.imag.adele.cadse.cadseg.validators;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIPlatform;

public class MenuValidator extends AbstractUIRunningValidator {

	@Override
	public void init(UIPlatform uiPlatform) {
		super.init(uiPlatform);
		if (_uiPlatform.isModification()) return;
		
		Item item = _uiPlatform.getItem();
		Item parent = item.getPartParent();
		if (parent.isInstanceOf(CadseGCST.TYPE_DEFINITION)) {
			try {
				item.setAttribute(CadseGCST.ITEM_at_NAME_, "main");
				item.setAttribute(CadseGCST.MENU_ABSTRACT_at_LABEL_, "main");
				_uiPlatform.setHidden(CadseGCST.MENU_ABSTRACT_at_ICON_, true);
				_uiPlatform.setHidden(CadseGCST.MENU_ABSTRACT_at_LABEL_, true);
				_uiPlatform.setHidden(CadseGCST.ITEM_at_NAME_, true);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}

package fr.imag.adele.cadse.cadseg.validators;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIPlatform;

public class ExtendsClassValidator extends AbstractUIRunningValidator {

	@Override
 	public void init(UIPlatform uiPlatform) {
 		super.init(uiPlatform);
 		
 		if (!uiPlatform.isModification())
			try {
				uiPlatform.getItem().setAttribute(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_, true);
			} catch (CadseException e) {
				e.printStackTrace();
			}
 	}
}

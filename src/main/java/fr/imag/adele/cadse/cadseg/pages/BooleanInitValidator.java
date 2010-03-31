/**
 * 
 */
package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.cadseg.managers.attributes.BooleanManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIPlatform;

public class BooleanInitValidator extends AbstractUIRunningValidator {
	

	@Override
	public void init(UIPlatform uiPlatform) {
		super.init(uiPlatform);
		if (_uiPlatform.isModification()) return;
		
		Item item = _uiPlatform.getItem();
		BooleanManager.setDefaultValueAttribute(item, "false");
		BooleanManager.setCannotBeUndefinedAttribute(item, true);
	}
}
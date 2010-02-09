/**
 * 
 */
package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;

public class UITWCommitValidator extends AbstractUIRunningValidator {
	
	

	@Override
	public boolean validValue(UIField field, Object value) {
		return false;
	}
	
	@Override
	public boolean validValueChanged(UIField field, Object value) {
		return false;
	}
}
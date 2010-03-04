package fr.imag.adele.cadse.cadseg.pages;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.UIField;

public class ValidPattern extends MC_AttributesItem {

	@Override
	public boolean validValue(UIField field, Object value) {

		try {
			Pattern.compile((String) value);
		} catch (PatternSyntaxException e) {
			_uiPlatform.setMessageError(getUIField().getAttributeDefinition().getName() + ":" + e.getMessage());
		}

		return super.validValue(field, value);
	}

	@Override
	public boolean validValueChanged(UIField field, Object visualValue) {
		try {
			Pattern.compile((String) visualValue);
		} catch (PatternSyntaxException e) {
			_uiPlatform.setMessageError(
                                getUIField().getAttributeDefinition().getName() + ":" + e.getMessage());
		}
		return super.validValueChanged(field, visualValue);
	}
}

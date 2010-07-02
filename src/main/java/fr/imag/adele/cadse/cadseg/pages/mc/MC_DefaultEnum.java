package fr.imag.adele.cadse.cadseg.pages.mc;

import java.util.List;

import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * The Class DefaultEnumMC.
 */
public final class MC_DefaultEnum extends MC_AttributesItem {

	/**
	 * Gets the values.
	 * 
	 * @return the values
	 */
	private List<String> getValues() {
		Item enumItem = getItem();
		Item enumType = EnumManager.getEnumType(enumItem);
		return EnumTypeManager.getEnumTypeValues(enumType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.AbstractModelController#validValueChanged(fr.imag.adele.cadse.core.ui.UIField,
	 *      java.lang.Object)
	 */
	@Override
	public boolean validValueChanged(UIField field, Object value) {
		List<String> values = getValues();
		if (value == null) {
			_uiPlatform.setMessageError("Enter a default value");
			return true;
		}
		if (!values.contains(value)) {
			_uiPlatform.setMessageError("Enter a valid default value");
			return true;
		}
		return false;
	}
}
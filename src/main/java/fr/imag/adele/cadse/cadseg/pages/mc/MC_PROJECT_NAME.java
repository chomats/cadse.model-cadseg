/**
 * 
 */
package fr.imag.adele.cadse.cadseg.pages.mc;

import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;

public class MC_PROJECT_NAME extends MC_AttributesItem {
	@Override
	public Object defaultValue() {
		return "${#qualified-name}";
	}
}
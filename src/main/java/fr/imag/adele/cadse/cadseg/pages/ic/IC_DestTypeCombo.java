package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_AbstractForBrowser_Combo;

public class IC_DestTypeCombo extends IC_AbstractForBrowser_Combo{
	
	@Override
	public Object[] getValues() {
		LinkType lt = (LinkType) getUIField().getAttributeDefinition();
		ItemType[] types =lt.getDestination().getAllConcreteType();
		return types;
	}
	
	@Override
	public String toString(Object value) {
		if (value == null) {
			return "";
		}
		if (value instanceof ItemType)
			return ((ItemType)value).getName();
		return value.toString();
	}

	@Override
	protected Object[] getSelectableValues() {
		return getValues();
	}

}

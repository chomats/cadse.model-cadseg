package fr.imag.adele.cadse.cadseg.pages.ic;

import java.util.Arrays;
import java.util.HashSet;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;

public class IC_AttributeDestType extends IC_DestTypeCombo {

	@Override
	protected Object[] filter(ItemType[] types) {
		HashSet<ItemType> s = new HashSet<ItemType>(Arrays.asList(types));
		s.remove(CadseGCST.LIST);
		s.remove(CadseGCST.GROUP_OF_ATTRIBUTES);
		s.remove(CadseGCST.LINK_TYPE);
		s.remove(CadseGCST.CONTENT_LINK_TYPE);
		return (ItemType[]) s.toArray(new ItemType[s.size()]);
	}
}

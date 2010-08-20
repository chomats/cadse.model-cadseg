package fr.imag.adele.cadse.cadseg.teamwork.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;

public class IC_ModifiedAttr_List extends IC_LinkForBrowser_Combo_List {

	public IC_ModifiedAttr_List() {
	}

	public IC_ModifiedAttr_List(String title, String message) {
		super(title, message);
	}
	
	public String toString(Object value) {
		if (value == null) {
			return "<none>";
		}

		if (value instanceof String) {
			return (String) value;
		}

		return super.toString(value);
	}
	
	public Object[] getValues() {
		Item item = _uiPlatform.getItem(getUIField());
		Collection<Item> values = this.getLinkType().getSelectingDestination(item);
		
		List<Item> resultList = new ArrayList<Item>();
		resultList.addAll(values);
		ContentItem contentItem = item.getContentItem();
		if ((contentItem != null) && contentItem.isSCMModified())
			resultList.add(contentItem);
		
		return resultList.toArray();
	}
}

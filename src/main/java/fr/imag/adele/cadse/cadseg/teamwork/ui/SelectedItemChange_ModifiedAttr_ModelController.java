package fr.imag.adele.cadse.cadseg.teamwork.ui;

import java.util.ArrayList;
import java.util.List;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.content.ContentItem;

public class SelectedItemChange_ModifiedAttr_ModelController extends
		SelectedItemChangeLinkModelController {

	public SelectedItemChange_ModifiedAttr_ModelController(boolean mandatory,
			String msg) {
		super(mandatory, msg);
	}

	@Override
	public Object getValue() {
		List<Object> resultList = new ArrayList<Object>();
		List<Link> linkList = (List<Link>) super.getValue();
		if (linkList != null)
			resultList.addAll(linkList);
		
		Item item = getItem();
		if (item == null)
			return resultList;
		ContentItem contentItem = item.getContentItem();
		if ((contentItem != null) && contentItem.isSCMModified())
			resultList.add("content");
		
		return resultList;
	}
}

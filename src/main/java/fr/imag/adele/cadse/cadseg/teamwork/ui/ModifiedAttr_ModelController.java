package fr.imag.adele.cadse.cadseg.teamwork.ui;

import java.util.ArrayList;
import java.util.List;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;

public class ModifiedAttr_ModelController extends LinkModelController {

	public ModifiedAttr_ModelController(boolean mandatory, String msg) {
		super(mandatory, msg);
	}

	public ModifiedAttr_ModelController() {
	}
	
	@Override
	public Object getValue() {
		List<Link> linkList = (List<Link>) super.getValue();
		List<Object> resultList = new ArrayList<Object>();
		resultList.addAll(linkList);
		
		Item item = _uiPlatform.getItem(getUIField());
		ContentItem contentItem = item.getContentItem();
		if ((contentItem != null) && contentItem.isSCMModified())
			resultList.add("content");
		
		return resultList;
	}
}

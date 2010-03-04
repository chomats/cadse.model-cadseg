package fr.imag.adele.cadse.cadseg.teamwork.ui;

import java.util.ArrayList;
import java.util.List;

import fr.imag.adele.cadse.as.platformide.IPlatformIDE;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.internal.CadseDomainImpl;
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
		if ((contentItem != null) && contentItem.isSCMModified()) {
			StringBuffer contentSB = new StringBuffer();
			contentSB.append("content");
			
			IPlatformIDE ideService = ((CadseDomainImpl) CadseCore.getCadseDomain()).getIdeService();
			String resName = ideService.getRessourceName(contentItem);
			if ((resName != null) && !resName.trim().equals("")) {
				contentSB.append(" (");
				contentSB.append(resName);
				contentSB.append(")");
			}
			resultList.add(contentSB.toString());
		}
		
		return resultList;
	}
}

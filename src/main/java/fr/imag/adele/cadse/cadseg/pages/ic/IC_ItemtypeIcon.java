package fr.imag.adele.cadse.cadseg.pages.ic;

import org.eclipse.core.resources.IResource;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_IconResourceForBrowser_Combo_List;

public class IC_ItemtypeIcon extends IC_IconResourceForBrowser_Combo_List {

	
	@Override
	protected IResource getRootSelect() {
		Item item = _uiPlatform.getItem(getUIField());
		Item cadseDef = item.getPartParent(CadseGCST.CADSE_DEFINITION);
		if (cadseDef != null)
			return cadseDef.getMainMappingContent(IResource.class);
		return super.getRootSelect();
	}
}

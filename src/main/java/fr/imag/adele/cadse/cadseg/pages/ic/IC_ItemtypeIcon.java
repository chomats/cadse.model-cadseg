package fr.imag.adele.cadse.cadseg.pages.ic;

import java.util.ArrayList;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.ui.model.WorkbenchContentProvider;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_IconResourceForBrowser_Combo_List;

public class IC_ItemtypeIcon extends IC_IconResourceForBrowser_Combo_List {
	
	private static final class MultiWorkbenchContentProvider extends
			WorkbenchContentProvider {
		
		@Override
		public Object[] getElements(Object element) {
			if (element instanceof IResource[])
				return (IResource[]) element;
			return super.getElements(element);
		}
	}

	@Override
	protected IResource[] getRootSelect() {
		Item item = _uiPlatform.getItem(getUIField());
		Item cadseDef = item.getPartParent(CadseGCST.CADSE_DEFINITION);
		
		if (cadseDef != null) {
			ArrayList<IResource> ret = new ArrayList<IResource>();
			ret.add(cadseDef.getMainMappingContent(IResource.class));
		
			for (Item ec : CadseDefinitionManager.getExtends(cadseDef)) {
				ret.add(ec.getMainMappingContent(IResource.class));
			}

			return (IResource[]) ret.toArray(new IResource[ret.size()]);
		}
		return super.getRootSelect();
	}
	
	@Override
	protected ITreeContentProvider getTreeContentProvider() {
		return new MultiWorkbenchContentProvider();
	}
}

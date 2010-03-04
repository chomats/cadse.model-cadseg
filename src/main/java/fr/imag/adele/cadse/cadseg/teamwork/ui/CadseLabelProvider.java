package fr.imag.adele.cadse.cadseg.teamwork.ui;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;

import fede.workspace.tool.view.WSPlugin;

public class CadseLabelProvider extends LabelProvider {

	protected LocalResourceManager	_resourceManager	= new LocalResourceManager(JFaceResources
			.getResources(PlatformUI.getWorkbench()
					.getDisplay()));
	
	protected Image createImage(String url) {
		return WSPlugin.getDefault().getImageFromURL(url);
	}
	
	protected Image createCadsegImage(String relativeUrl) {
		return WSPlugin.getDefault().getImageFromURL("platform:/plugin/Model.Workspace.CadseG/" + relativeUrl);
	}
}

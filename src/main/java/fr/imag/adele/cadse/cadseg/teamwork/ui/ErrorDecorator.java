package fr.imag.adele.cadse.cadseg.teamwork.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;

import fede.workspace.tool.view.WSPlugin;

public abstract class ErrorDecorator extends LabelProvider implements ILabelDecorator {

	private LocalResourceManager	_resourceManager	= new LocalResourceManager(JFaceResources
																.getResources(PlatformUI.getWorkbench()
																		.getDisplay()));

	public ErrorDecorator() {
	}

	protected Image computeImage(Image image, String path) {
		ImageDescriptor overlay = WSPlugin.getImageDescriptor(path);
		DecorationOverlayIcon icon = new DecorationOverlayIcon(image, overlay, IDecoration.BOTTOM_RIGHT);
		return _resourceManager.createImage(icon);
	}

	public String decorateText(String text, Object element) {
		if (element == null) {
			return null;
		}

		return null;
	}
}

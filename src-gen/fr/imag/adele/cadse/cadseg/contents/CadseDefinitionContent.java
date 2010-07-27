package fr.imag.adele.cadse.cadseg.contents;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fede.workspace.eclipse.java.JavaProjectManager;
import fede.workspace.eclipse.java.osgi.OsgiManifest;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.impl.var.StringVariable;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;
import fr.imag.adele.fede.workspace.si.view.View;

/**
 * The Class WorkspaceModelContentManager.
 */
public class CadseDefinitionContent extends EclipsePluginContentManger  {

	/**
	 * Instantiates a new workspace model content manager.
	 * 
	 * @param item
	 *            the item
	 */
	CadseDefinitionContent(final UUID id) {
		super(id, new VariableImpl() {

			public String compute(ContextVariable context, Item item) {
				if (item == null)
					return "??";
				return context.getAttribute(item, CadseGCST.ITEM_at_QUALIFIED_NAME_);
			}
		}, new StringVariable("src-gen"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seefede.workspace.eclipse.java.manager.JavaProjectContentManager#
	 * addDependencyClasspathEntry(fr.imag.adele.cadse.core.Link,
	 * fr.imag.adele.cadse.core.Item, fr.imag.adele.cadse.core.Item,
	 * fede.workspace
	 * .dependencies.eclipse.java.IJavaItemManager.DependencyNature,
	 * java.util.Set, org.eclipse.core.runtime.MultiStatus)
	 */
	@Override
	public void addDependencyClasspathEntry(Link requirementLink, Item target, Item source, DependencyNature nature,
			Set<IClasspathEntry> classpath, MultiStatus ms) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seefede.workspace.eclipse.composition.java.EclipsePluginContentManger#
	 * getDefaultPackage()
	 */
	@Override
	protected String getDefaultPackage() {
		return CadseDefinitionManager.getDefaultPackage(ContextVariableImpl.DEFAULT, getOwnerItem());
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @seefede.workspace.eclipse.composition.java.EclipsePluginContentManger#
	 * computeManifest(fede.workspace.eclipse.java.osgi.OsgiManifest)
	 */
	@Override
	public void computeManifest(OsgiManifest omf) {
		omf.putArray(OsgiManifest.REQUIRE_BUNDLE, true, false, "org.eclipse.ui", "org.eclipse.ui.ide");
		List<Item> dep = CadseDefinitionManager.getAllDependenciesCadse(getOwnerItem());
		for (Item item : dep) {
			omf.putArray(OsgiManifest.REQUIRE_BUNDLE, true, false, item.getQualifiedName());
		}
		super.computeManifest(omf);
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.eclipse.composition.java.EclipsePluginContentManger#create
	 * ()
	 */
	@Override
	public void create() throws CadseException {
		super.create();
		IProgressMonitor monitor = View.getDefaultMonitor();
		try {
			JavaProjectManager.createJavaSourceFolder(getOwnerItem(), getProject().getFolder("src"), null, monitor);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	@Override
	protected void computeModel(PDEGenerateModel model) {
		super.computeModel(model);
		model.sourceName = "src-gen";
	}

	/**
	 * Gets the java source element.
	 * 
	 * @param cxt
	 *            the cxt
	 * 
	 * @return the java source element
	 */
	public IPackageFragmentRoot getCustomJavaSourceElement(ContextVariable cxt) {
		IFolder source = getProject(cxt).getFolder("src");
		IJavaProject jp = getJavaProject(cxt);
		if (jp != null) {
			return jp.getPackageFragmentRoot(source);
		}
		return null;
	}

	/**
	 * Gets the java source element.
	 * 
	 * @param cxt
	 *            the cxt
	 * 
	 * @return the java source element
	 */
	public IContainer getCustomJavaSourceElementContainer(ContextVariable cxt) {
		IPackageFragmentRoot jp = getCustomJavaSourceElement(cxt);
		if (jp != null) {
			return (IContainer) jp.getResource();
		}
		return null;
	}
	
	public void generateManifest(PDEGenerateModel info, IProgressMonitor monitor) throws CoreException {
		IProject p = getProject();
		if (p.getFile("pom.xml").exists())
			return;
		super.generateManifest(info, monitor);
	};

	
	
	/**
	 * Gets the source folder.
	 * 
	 * @param cxt
	 *            the cxt
	 * 
	 * @return the source folder
	 */
	public IContainer getSourceFolder(ContextVariable cxt) {
		IPackageFragmentRoot je = getJavaSourceElement(cxt);
		if (je != null) {
			return (IContainer) je.getResource();
		}
		return null;
	}

}
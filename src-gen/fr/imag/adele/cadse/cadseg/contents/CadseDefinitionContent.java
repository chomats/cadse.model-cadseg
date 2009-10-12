package fr.imag.adele.cadse.cadseg.contents;

import java.io.StringWriter;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.internal.core.plugin.PluginElement;
import org.eclipse.pde.internal.core.plugin.PluginExtension;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.dependencies.eclipse.java.IJavaItemManager.DependencyNature;
import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.JavaProjectManager;
import fede.workspace.eclipse.java.osgi.OsgiManifest;
import fede.workspace.tool.eclipse.MappingManager;
import fede.workspace.tool.loadmodel.model.jaxb.CCadse;
import fr.imag.adele.cadse.cadseg.generate.GenerateCadseDefinitionModel;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaFileCST;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.view.model.ViewCategoryModel;
import fr.imag.adele.cadse.cadseg.managers.view.model.ViewModel;
import fr.imag.adele.cadse.cadseg.managers.view.model.ViewModels;
import fr.imag.adele.cadse.cadseg.template.LaunchApplicationTemplate;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.impl.var.StringVariable;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.si.view.View;

/**
 * The Class WorkspaceModelContentManager.
 */
public class CadseDefinitionContent extends EclipsePluginContentManger implements IPDEContributor {

	/**
	 * Instantiates a new workspace model content manager.
	 * 
	 * @param item
	 *            the item
	 */
	CadseDefinitionContent(final CompactUUID id) {
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
	 * @see fede.workspace.eclipse.java.manager.JavaProjectContentManager#addDependencyClasspathEntry(fr.imag.adele.cadse.core.Link,
	 *      fr.imag.adele.cadse.core.Item, fr.imag.adele.cadse.core.Item,
	 *      fede.workspace.dependencies.eclipse.java.IJavaItemManager.DependencyNature,
	 *      java.util.Set, org.eclipse.core.runtime.MultiStatus)
	 */
	@Override
	public void addDependencyClasspathEntry(Link requirementLink, Item target, Item source,
			DependencyNature nature, Set<IClasspathEntry> classpath, MultiStatus ms) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.EclipsePluginContentManger#getDefaultPackage()
	 */
	@Override
	protected String getDefaultPackage() {
		return CadseDefinitionManager.getDefaultPackage(ContextVariable.DEFAULT, getOwnerItem());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExportsPackage(java.util.Set)
	 */
	public void computeExportsPackage(Set<String> exports) {
		exports.add(getDefaultPackage());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
	 */
	public void computeImportsPackage(Set<String> imports) {
		List<String> importsList = CadseDefinitionManager.getImports(getItem());
		if (importsList != null) {
			imports.addAll(importsList);
		}
		imports.add("fr.imag.adele.cadse.core");
		imports.add("fr.imag.adele.cadse.core.attribute");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.EclipsePluginContentManger#computeManifest(fede.workspace.eclipse.java.osgi.OsgiManifest)
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
	 * @see fede.workspace.eclipse.composition.java.EclipsePluginContentManger#generate(fr.imag.adele.cadse.core.var.ContextVariable)
	 */
	@Override
	synchronized public void generate(ContextVariable cxt) {
		super.generate(cxt);

		// IProgressMonitor monitor = Eclipse.getDefaultMonitor();
		// try {
		// generateViewXml(getProject(), monitor);
		// } catch (CoreException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		generateStrandardXML();

		try {
			GenerateJavaFileCST gCST = new GenerateJavaFileCST(cxt, getOwnerItem());
			String content = gCST.getContent();

			IFile cstFile = CadseDefinitionManager.getCSTCU(cxt, getOwnerItem());
			MappingManager.generate(cstFile.getProject(), cstFile.getParent().getProjectRelativePath(), cstFile
					.getName(), content, null);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Generate strandard xml.
	 */
	public void generateStrandardXML() {
		IProject p = getProject();
		IFile f = p.getFile(new Path("model/cadse.xml"));
		CCadse cadse = GenerateCadseDefinitionModel.generateCADSE(getItem());
		StringWriter writer = new StringWriter();

		try {
			JAXBContext jc = JAXBContext.newInstance("fede.workspace.tool.loadmodel.model.jaxb", this.getClass()
					.getClassLoader());
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(cadse, writer);
			MappingManager
					.generate(p, f.getParent().getProjectRelativePath(), f.getName(), writer.toString(), null);
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.EclipsePluginContentManger#create()
	 */
	@Override
	public void create() throws CadseException {
		super.create();
	
		try {
			IProgressMonitor monitor = View.getDefaultMonitor();
			JavaProjectManager.createJavaSourceFolder(getOwnerItem(), getProject().getFolder("src"), null, monitor);
			
			IFile launchAppli = getProject().getFile("run-cadse-" + getOwnerItem().getName() + ".launch");
			if (!launchAppli.exists()) {
				LaunchApplicationTemplate lat = new LaunchApplicationTemplate();
				MappingManager
						.generate(getProject(), null, launchAppli.getName(), lat.generate(getOwnerItem()), monitor);
			}
		} catch (CoreException e) {
			throw new CadseException("Cannot create workspace project from cadse {0} : {1}", e,
					getOwnerItem().getName(), e.getMessage());
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
	

	// private void setFile(IFile fdefinition, String plugin_id, String
	// path,
	// IProgressMonitor monitor) throws CoreException, IOException {
	// Bundle b = Platform.getBundle(plugin_id);
	// URL data = b.getEntry(path);
	// if (fdefinition.exists()) {
	// fdefinition.setContents(data.openStream(), true, false,monitor);
	// } else {
	// fdefinition.create(data.openStream(), true, monitor);
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion(org.eclipse.pde.core.plugin.IPluginBase,
	 *      org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
	 */
	public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
		try {
			IPluginExtension[] exts = pluginBase.getExtensions();

			IPluginExtension findExt = findExtension(pluginBase, exts, "org.eclipse.ui.views");

			if (findExt != null) {
				pluginBase.remove(findExt);
			}
			PluginExtension pluginExtension = new PluginExtension();
			pluginExtension.setModel(workspacePluginModel);
			pluginBase.add(pluginExtension);
			pluginExtension.setPoint("org.eclipse.ui.views");
			findExt = pluginExtension;

			ViewModels viewmodels = new ViewModels(CadseDefinitionManager.getViewModel(getItem()));
			for (ViewCategoryModel vc : viewmodels.categories) {
				PluginElement categoryElt = new PluginElement();
				categoryElt.setModel(workspacePluginModel);
				findExt.add(categoryElt);
				categoryElt.setName("category");
				categoryElt.setAttribute("name", vc.name);
				categoryElt.setAttribute("id", vc.id);

			}
			for (ViewModel v : viewmodels.views) {
				PluginElement categoryElt = new PluginElement();
				categoryElt.setModel(workspacePluginModel);
				findExt.add(categoryElt);
				categoryElt.setName("view");
				if (v.icon != null) {
					categoryElt.setAttribute("icon", v.icon);
				}
				if (v.category != null) {
					categoryElt.setAttribute("category", v.category);
				}

				categoryElt.setAttribute("class", v.qualifiedClassName);
				categoryElt.setAttribute("id", v.id);
				categoryElt.setAttribute("name", v.name);

			}

		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Find extension.
	 * 
	 * @param pluginBase
	 *            the plugin base
	 * @param exts
	 *            the exts
	 * @param pt
	 *            the pt
	 * 
	 * @return the i plugin extension
	 * 
	 * @throws CoreException
	 *             the core exception
	 */
	private IPluginExtension findExtension(IPluginBase pluginBase, IPluginExtension[] exts, String pt)
			throws CoreException {
		for (IPluginExtension e : exts) {
			if (e.getPoint().equals(pt)) {
				return e;
			}
		}
		return null;
	}

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

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// fede.workspace.eclipse.content.ProjectContentManager#computeRenameChange(org.eclipse.ltk.core.refactoring.CompositeChange,
	// * fr.imag.adele.cadse.core.var.ContextVariable,
	// * fr.imag.adele.cadse.core.var.ContextVariable)
	// */
	// @Override
	// public RefactoringStatus computeRenameChange(CompositeChange
	// change,
	// ContextVariable newCxt,
	// ContextVariable oldCxt) {
	// RefactoringStatus ret = new RefactoringStatus();
	//
	// // change class java CST
	// String oldDefaultPackage =
	// CadseDefinitionManager.getDefaultPackage(oldCxt, getItem());
	// String newDefaultPackage =
	// CadseDefinitionManager.getDefaultPackage(newCxt, getItem());
	//
	// String oldCSTName =
	// GenerateJavaIdentifier.javaClassNameFileCST_FromCadseDefinition(oldCxt,
	// getItem());
	// String newCSTName =
	// GenerateJavaIdentifier.javaClassNameFileCST_FromCadseDefinition(newCxt,
	// getItem());
	//
	// IPackageFragment pack =
	// getJavaSourceElement(oldCxt).getPackageFragment(oldDefaultPackage);
	// final ICompilationUnit unit = pack.getCompilationUnit(oldCSTName
	// +
	// ".java");
	// NullProgressMonitor pm = new NullProgressMonitor();
	//
	// change.add(new RenameConstanteFile(unit, newCSTName + ".java"));
	//
	// change.add(new RenameDefaultPackage(pack, newDefaultPackage));
	//
	// // RenamePackageChange packageChange = new
	// RenamePackageChange(pack,
	// // newDefaultPackage,true);
	// // change.add(packageChange);
	//
	// ret.merge(super.computeRenameChange(change, newCxt, oldCxt));
	// if (ret.hasFatalError()) {
	// return ret;
	// }
	//
	// // ChangeMananifest
	// CorrectManifestAfterRenameChange manifestChange = new
	// CorrectManifestAfterRenameChange(this,
	// oldDefaultPackage, newDefaultPackage, oldCxt.getValue(getItem(),
	// WorkspaceDomain.UNIQUE_NAME_ATTRIBUTE));
	// change.add(manifestChange);
	//
	// return ret;
	// }

}
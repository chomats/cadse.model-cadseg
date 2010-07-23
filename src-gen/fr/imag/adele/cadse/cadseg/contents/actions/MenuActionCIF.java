package fr.imag.adele.cadse.cadseg.contents.actions;

import java.util.Set;
import java.util.UUID;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.si.view.View;

public class MenuActionCIF implements IContentItemFactory {

	/**
	 * The Class MyContentItem.
	 */
	static public class MenuActionContent extends JavaFileContentManager implements IPDEContributor {

		

		/**
		 * Instantiates a new my content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 * 
		 * @throws CadseException
		 *             the melusine exception
		 */
		public MenuActionContent(UUID id) throws CadseException {
			super(id, new VariableImpl() {

				public String compute(ContextVariable context, Item item) {
					return GenerateJavaIdentifier.javaPackageMenuAction(context, item);
				}
			}, new VariableImpl() {

				public String compute(ContextVariable context, Item item) {
					return GenerateJavaIdentifier.javaClassNameMenuAction(context, item);
				}
			});
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExportsPackage(java.util.Set)
		 */
		public void computeExportsPackage(Set<String> exports) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion(org.eclipse.pde.core.plugin.IPluginBase,
		 *      org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
		 */
		public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
		 */
		public void computeImportsPackage(Set<String> imports) {
			imports.add("fr.imag.adele.cadse.core");
			imports.add("fr.imag.adele.cadse.core.ui");
		}
	}

	public ContentItem createContentItem(UUID id, Item owerItem) throws CadseException {
		MenuActionContent cm = new MenuActionContent(id);
		cm.setComposers();
		cm.setExporters();
		return cm;
	}
}

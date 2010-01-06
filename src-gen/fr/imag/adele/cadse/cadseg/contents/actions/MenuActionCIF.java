package fr.imag.adele.cadse.cadseg.contents.actions;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateClass;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseException;
import java.util.UUID;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.Variable;
import fr.imag.adele.fede.workspace.si.view.View;

public class MenuActionCIF implements IContentItemFactory {

	/**
	 * The Class MyContentItem.
	 */
	static public class MenuActionContent extends JavaFileContentManager implements IGenerateContent, IPDEContributor {

		

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
		 * @see fr.imag.adele.cadse.core.IGenerateContent#generate(fr.imag.adele.cadse.core.var.ContextVariable)
		 */
		public void generate(ContextVariable cxt) {
			generate(cxt, getOwnerItem());
		}

		/**
		 * Generate.
		 * 
		 * @param cxt
		 *            the cxt
		 * @param menuaction
		 *            the menuaction
		 */
		public void generate(ContextVariable cxt, Item menuaction) {
			GenerateClass ret;
			String cn = getClassName(cxt);
			String pn = getPackageName(cxt);

			String super_pn = "fr.imag.adele.cadse.core.ui";
			String super_cn = "MenuAction";

			IFile f = getFile();
			IType javatype = getJavaType(cxt);

			ret = new GenerateClass(null, true, pn, cn, super_pn + "." + super_cn, (String) null, javatype, false) {
				@Override
				protected void generateMethods(GenStringBuilder sb, Set<String> imports, GenContext context) {
					imports.add("fr.imag.adele.cadse.core.IItemNode");
					imports.add("fr.imag.adele.cadse.core.ui.MenuAction");
					imports.add("fr.imag.adele.cadse.core.CadseException");

					sb.newline().append("@Override");
					sb.newline().append("public void run(IItemNode[] selection) throws CadseException {");
					sb.newline().append("	// TODO Auto-generated method stub");
					sb.newline();
					sb.newline().append("}");

				}
			};

			String content = ret.getContent();
			try {
				EclipsePluginContentManger.generateJava(f, content, View.getDefaultMonitor());
			} catch (CoreException e) {
				e.printStackTrace();
			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IGenerateContent#getGenerateModel()
		 */
		public GenerateModel getGenerateModel() {
			return null;
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

	public ContentItem createContentItem(UUID id) throws CadseException {
		MenuActionContent cm = new MenuActionContent(id);
		cm.setComposers();
		cm.setExporters();
		return cm;
	}
}

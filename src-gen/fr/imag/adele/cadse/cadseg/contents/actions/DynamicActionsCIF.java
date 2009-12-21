package fr.imag.adele.cadse.cadseg.contents.actions;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateClass;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseException;
import java.util.UUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.Variable;
import fr.imag.adele.fede.workspace.si.view.View;

public class DynamicActionsCIF implements IContentItemFactory {

	/**
	 * @generated
	 */
	static final class PackageNameVariable extends VariableImpl {

		/**
		 * @generated
		 */
		public final static Variable	INSTANCE	= new PackageNameVariable();

		public String compute(ContextVariable context, Item itemCurrent) {
			try {
				return GenerateJavaIdentifier.javaPackageMenuAction(context, itemCurrent);
			} catch (Throwable e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	/**
	 * @generated
	 */
	static final class ClassNameVariable extends VariableImpl {

		/**
		 * @generated
		 */
		public final static Variable	INSTANCE	= new ClassNameVariable();

		public String compute(ContextVariable context, Item itemCurrent) {
			try {
				return JavaIdentifier.javaIdentifierFromString(context.getName(itemCurrent), true, false,
						"ActionContributor");
			} catch (Throwable e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	/**
	 * @generated
	 */
	public class DynamicActionsContent extends JavaFileContentManager implements IGenerateContent, IPDEContributor {

		/**
		 * @generated
		 */
		public DynamicActionsContent(UUID id, Variable packageNameVariable, Variable classNameVariable)
				throws CadseException {
			super(id, packageNameVariable, classNameVariable);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fr.imag.adele.cadse.core.IGenerateContent#generate(fr.imag.adele.
		 * cadse.core.var.ContextVariable)
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
			String super_cn = "IActionContributor";

			IFile f = getFile();
			IType javatype = getJavaType(cxt);

			ret = new GenerateClass(null, true, pn, cn, (String) null, super_pn + "." + super_cn, javatype, false) {
				@Override
				protected void generateMethods(GenStringBuilder sb, Set<String> imports, GenContext context) {
					imports.add("fr.imag.adele.cadse.core.IItemNode");
					imports.add("fr.imag.adele.cadse.core.Menu");
					imports.add("fr.imag.adele.cadse.core.ui.IActionContributor");
					imports.add("fr.imag.adele.cadse.core.ui.view.ViewDescription");

					sb.newline().append("@Override");
					sb
							.newline()
							.append(
									"public void contributeMenuAction(ViewDescription viewDescription, Menu menu, IItemNode[] selection) {");
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
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeImportsPackage(java.util.Set)
		 */
		public void computeImportsPackage(Set<String> imports) {
			imports.add("fr.imag.adele.cadse.core");
			imports.add("fr.imag.adele.cadse.core.ui");
			imports.add("fr.imag.adele.cadse.core.ui.view");
		}

		public GenerateModel getGenerateModel() {
			return null;
		}

		public void computeExportsPackage(Set<String> exports) {

		}

		public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
		}
	}

	public ContentItem createContentItem(UUID id) throws CadseException {
		DynamicActionsContent cm = new DynamicActionsContent(id, PackageNameVariable.INSTANCE,
				ClassNameVariable.INSTANCE);
		cm.setComposers();
		cm.setExporters();
		return cm;
	}

}

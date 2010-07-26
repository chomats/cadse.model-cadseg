package fr.imag.adele.cadse.cadseg.contents.actions;

import java.util.Set;
import java.util.UUID;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.Variable;

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
	public class DynamicActionsContent extends JavaFileContentManager  {

		/**
		 * @generated
		 */
		public DynamicActionsContent(UUID id, Variable packageNameVariable, Variable classNameVariable)
				throws CadseException {
			super(id, packageNameVariable, classNameVariable);
		}

	}

	public ContentItem createContentItem(UUID id, Item owerItem) throws CadseException {
		DynamicActionsContent cm = new DynamicActionsContent(id, PackageNameVariable.INSTANCE,
				ClassNameVariable.INSTANCE);
		cm.setComposers();
		cm.setExporters();
		return cm;
	}

}

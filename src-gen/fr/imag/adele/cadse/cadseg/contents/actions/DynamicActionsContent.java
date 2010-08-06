package fr.imag.adele.cadse.cadseg.contents.actions;

import java.util.UUID;

import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.Variable;

public class DynamicActionsContent extends JavaFileContentManager {

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
	public DynamicActionsContent(UUID id)
			throws CadseException {
		super(id, PackageNameVariable.INSTANCE,
				ClassNameVariable.INSTANCE);
	}
	
	@Override
	public ContentItem getParentContentItemWherePutMyContent() {
		return getOwnerItem().getPartParent(CadseGCST.CADSE_DEFINITION).getContentItem();
	}
}

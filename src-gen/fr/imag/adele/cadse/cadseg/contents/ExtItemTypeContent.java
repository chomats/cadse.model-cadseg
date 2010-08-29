package fr.imag.adele.cadse.cadseg.contents;

import java.util.UUID;

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.Variable;

/**
 * The Class ExtItemTypeContent. implements IGenerateContent
 * 
 * @generated
 */
public class ExtItemTypeContent extends JavaFileContentManager  {
	static final class PackageNameVariable extends VariableImpl {

		public final static Variable	INSTANCE	= new PackageNameVariable();

		public String compute(ContextVariable context, Item itemCurrent) {
			try {
				return GenerateJavaIdentifier.getExtPackage(context, itemCurrent);
			} catch (Throwable e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	static final class ClassNameVariable extends VariableImpl {

		public final static Variable	INSTANCE	= new ClassNameVariable();

		public String compute(ContextVariable context, Item itemCurrent) {
			try {
				return GenerateJavaIdentifier.getExtClassName(context, itemCurrent);
			} catch (Throwable e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	/**
		@generated
	*/
	public ExtItemTypeContent(UUID id) throws CadseException {
		super(id, PackageNameVariable.INSTANCE, ClassNameVariable.INSTANCE);
	}
	
}
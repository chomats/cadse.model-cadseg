package fr.imag.adele.cadse.cadseg.managers.content;

import java.util.UUID;

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class ManagerJavaFileContentManager.
 */
public final class ManagerJavaFileContentManager extends JavaFileContentManager {

	/**
	 * Instantiates a new manager java file content manager.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param manager
	 *            the manager
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	public ManagerJavaFileContentManager(UUID id) throws CadseException {
		super(id, new VariableImpl() {

			public String compute(ContextVariable context, Item item) {
				return GenerateJavaIdentifier.getManagerPackage(context, null, item);
			}

		}, new VariableImpl() {

			public String compute(ContextVariable context, Item item) {
				return GenerateJavaIdentifier.getManagerClassName(context, null, item, false);
			}
		});
	}

	

}
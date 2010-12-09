package fr.imag.adele.cadse.cadseg.contents;

import java.util.UUID;

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class ViewJavaFileContentManager.
 */
public final class ViewJavaFileContentManager extends JavaFileContentManager {

	/**
	 * Instantiates a new view java file content manager.
	 * 
	 * @param workspaceModel
	 *            the workspace model
	 * @param view
	 *            the view
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	public ViewJavaFileContentManager(UUID id) throws CadseException {
		super(id, new VariableImpl() {
			public String compute(ContextVariable context, Item itemCurrent) {
				return ViewManager.getPackage(context, itemCurrent);
			}
		}, new VariableImpl() {
			public String compute(ContextVariable context, Item itemCurrent) {
				// TODO Auto-generated method stub
				return ViewManager.getClassName(context, itemCurrent);
			}
		});
	}


}
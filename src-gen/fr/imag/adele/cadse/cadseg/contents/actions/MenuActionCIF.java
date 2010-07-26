package fr.imag.adele.cadse.cadseg.contents.actions;

import java.util.Set;
import java.util.UUID;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;

public class MenuActionCIF implements IContentItemFactory {

	/**
	 * The Class MyContentItem.
	 */
	static public class MenuActionContent extends JavaFileContentManager {

		

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

	}

	public ContentItem createContentItem(UUID id, Item owerItem) throws CadseException {
		MenuActionContent cm = new MenuActionContent(id);
		cm.setComposers();
		cm.setExporters();
		return cm;
	}
}

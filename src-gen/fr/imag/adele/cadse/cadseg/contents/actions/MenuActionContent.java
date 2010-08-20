package fr.imag.adele.cadse.cadseg.contents.actions;

import java.util.UUID;

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;

public class MenuActionContent extends JavaFileContentManager {


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

	@Override
	public ContentItem getParentContentItemWherePutMyContent() {
		Item ownerItem = getOwnerItem();
		Item cadseDef = ownerItem.getPartParent(CadseGCST.CADSE_DEFINITION);
		return cadseDef.getContentItem();
	}
}

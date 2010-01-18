package fr.imag.adele.cadse.cadseg.contents.attributes;

import java.util.Set;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.SubFileContentManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.template.ListOfValueAttribute;
import fr.imag.adele.cadse.cadseg.template.ValueAttribute;
import fr.imag.adele.cadse.core.CadseException;
import java.util.UUID;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class AttributeCIF implements IContentItemFactory {
	/**
	 * @generated
	 */
	protected AttributeManager	_attributeManager;

	/**
	 * The Class AttributeContentManager.
	 */
	public static class AttributeContentItem extends SubFileContentManager implements IPDEContributor {
		protected AttributeManager	_attributeManager;

		/**
		 * Instantiates a new attribute content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 *            
		 * @generated
		 *
		 */
		protected AttributeContentItem(UUID id, AttributeManager attributeManager) {
			super(id);
			_attributeManager = attributeManager;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ContentManager#generate(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.lang.String, java.lang.String, java.util.Set,
		 *      fr.imag.adele.cadse.core.GenContext)
		 */
		@Override
		public void generate(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {

			if (kind.equals("methods")) {
				Item source = getOwnerItem();
				if (AttributeManager.isIsListAttribute(source)) {
					ListOfValueAttribute temp = new ListOfValueAttribute();
					sb.append(temp.generate(source.getPartParent().getName(), source, imports));
					imports.add("java.util.List");
					imports.add("java.util.ArrayList");

				} else {
					ValueAttribute temp = new ValueAttribute();
					sb.append(temp.generate(source.getPartParent().getName(), source, imports));
				}
				_attributeManager.addJavaImport(imports);				
			}
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
		 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
		 */
		public void computeImportsPackage(Set<String> imports) {
			_attributeManager.computeImportsManifest(imports);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion(org.eclipse.pde.core.plugin.IPluginBase,
		 *      org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
		 */
		public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
		}
	}
	
	/**
	 * @generated
	 */
	public AttributeCIF(AttributeManager manager) {
		super();
		_attributeManager = manager;
	}

	/**
	 * @generated
	 */
	public ContentItem createContentItem(UUID id) throws CadseException {
		return new AttributeContentItem(id, _attributeManager);
	}
}

package fr.imag.adele.cadse.cadseg.contents.attributes;

import java.util.Set;

import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.cadseg.template.LinkAttributeMultiTemplate;
import fr.imag.adele.cadse.cadseg.template.LinkAttributeOneTemplate;
import fr.imag.adele.cadse.core.CadseException;
import java.util.UUID;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

public class LinkCIF extends AttributeCIF {

	/**
	 * The Class LinkTypeContentManager.
	 */
	public final class LinkTypeContentItem extends AttributeCIF.AttributeContentItem {

		/**
		 * Instantiates a new link type content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		protected LinkTypeContentItem(UUID id, AttributeManager manager) {
			super(id, manager);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.attributes.AttributeManager.AttributeContentManager#generate(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.lang.String, java.lang.String, java.util.Set,
		 *      fr.imag.adele.cadse.core.GenContext)
		 */
		@Override
		public void generate(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {

			if (kind.equals("methods")) {
				Item source = getItem();
				if (true) { // !AttributeManager.isClassAttributeAttribute(source)
					int max = LinkManager.getMax(source);
					if (max == 1) {
						LinkAttributeOneTemplate temp = new LinkAttributeOneTemplate();
						sb.append(temp.generate(source.getPartParent().getName(), source, imports));
					} else {
						LinkAttributeMultiTemplate temp = new LinkAttributeMultiTemplate();
						sb.append(temp.generate(source.getPartParent().getName(), source, imports));
						imports.add("java.util.Collection");
						imports.add("java.util.List");
					}
					imports.add("fr.imag.adele.cadse.core.Item");
					imports.add("fr.imag.adele.cadse.core.Link");
					imports.add("fr.imag.adele.cadse.core.CadseException");
				}
			} else {
				super.generate(sb, type, kind, imports, context);
			}

		}
	}

	public LinkCIF(AttributeManager manager) {
		super(manager);
	}

	@Override
	public ContentItem createContentItem(UUID id) throws CadseException {
		return new LinkTypeContentItem(id, _attributeManager);
	}
}

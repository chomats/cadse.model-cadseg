package fr.imag.adele.cadse.cadseg.contents.attributes;

import java.util.Set;

import org.eclipse.jdt.core.IType;

import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.cadseg.template.EnumListOfValueAttribute;
import fr.imag.adele.cadse.cadseg.template.EnumValueAttribute;
import fr.imag.adele.cadse.core.CadseException;
import java.util.UUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

public class EnumCIF extends AttributeCIF {
	/**
	 * The Class EnumAttributeContentManager.
	 */
	public class EnumAttributeContentItem extends AttributeContentItem {

		/**
		 * Instantiates a new enum attribute content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		protected EnumAttributeContentItem(UUID id, AttributeManager manager) {
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
				if (true) { // !AttributeManager.isClassAttributeAttribute(getItem())
					Item enumType = EnumManager.getEnumType(getOwnerItem());
					IType enumQualifiedClass = EnumTypeManager.getEnumQualifiedClass(context, enumType);
					imports.add(enumQualifiedClass.getFullyQualifiedName());
					Item source = getOwnerItem();
					if (AttributeManager.isIsListAttribute(source)) {
						EnumListOfValueAttribute temp = new EnumListOfValueAttribute();
						sb.append(temp.generate(source.getPartParent().getName(), source, imports));
						imports.add("java.util.List");
						imports.add("java.util.ArrayList");

					} else {
						EnumValueAttribute temp = new EnumValueAttribute();
						sb.append(temp.generate(source.getPartParent().getName(), source, imports));
						imports.add("fr.imag.adele.cadse.core.util.Convert");
					}
					imports.add("fr.imag.adele.cadse.core.Item");
					imports.add("fr.imag.adele.cadse.core.CadseException");
				}
			} else {
				super.generate(sb, type, kind, imports, context);
			}
		}
		
		@Override
		public void computeImportsPackage(Set<String> imports) {
			super.computeImportsPackage(imports);
			imports.add("fr.imag.adele.cadse.core.util");
		}
	}
	
	

	public EnumCIF(AttributeManager manager) {
		super(manager);
	}

	@Override
	public ContentItem createContentItem(UUID id) throws CadseException {
		return new EnumAttributeContentItem(id, _attributeManager);
	}

}

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package fr.imag.adele.cadse.cadseg.managers.content;

import java.util.Set;
import java.util.UUID;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.SubFileContentManager;
import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generate.GenerateVariable;
import fr.imag.adele.cadse.cadseg.managers.build.CompositeItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.util.Convert;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class ContentModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ContentItemTypeManager extends DefaultWorkspaceManager  {

	/** The Constant EXPORTERS_LINK. */
	@Deprecated
	public static final String	EXPORTERS_LINK	= "exporters";

	/**
	 * The Class MyContentItem.
	 */
	public class MyContentItem extends SubFileContentManager implements IGenerateContent, IPDEContributor {

		/**
		 * Instantiates a new my content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		public MyContentItem(UUID id) {
			super(id);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IGenerateContent#generate(fr.imag.adele.cadse.core.var.ContextVariable)
		 */
		public void generate(ContextVariable cxt) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IGenerateContent#getGenerateModel()
		 */
		public GenerateModel getGenerateModel() {
			return null;
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
			ItemType it = getOwnerItem().getType();
			Class<?> defaultQualifiedClassName = getRuntimeClassName();
			String defaultClassName = defaultQualifiedClassName.getSimpleName();

			if ("inner-class".equals(kind)) {
				Item manager = getOwnerItem().getPartParent();

				Item itemtype = ManagerManager.getItemType(manager);
				generateAllClassVariables(sb, imports);

				generateParts(sb, type, kind, imports, null);
				generateComposersParts(getOwnerItem(), sb, type, kind, imports, null);

				boolean extendsClass = mustBeExtended() | isExtendsClass(getOwnerItem());
				if (extendsClass) {

					String extendsClassName = defaultClassName;

					extendsClassName = findSuperClassName(context, extendsClassName, itemtype);
					defaultClassName = GenerateJavaIdentifier.getContentClassName(context, itemtype);
					sb.newline();
					sb.newline().append("/**");
					sb.newline().append("	@generated");
					sb.newline().append("*/");
					sb.newline().append("public class ").append(defaultClassName).append(" extends ").append(
							extendsClassName).append(" {");
					sb.begin();
					sb.newline();
					sb.newline().append("/**");
					sb.newline().append("	@generated");
					sb.newline().append("*/");
					sb.newline().append("public ").append(defaultClassName).append("(");
					generateConstructorParameter(sb);
					sb.decrementLength();
					sb.append(") throws CadseException {");
					sb.newline().append("	super(");
					generateConstrustorArguments(sb);
					sb.decrementLength();
					sb.append(");");
					sb.newline().append("}");
					sb.end();
					sb.newline();
					sb.newline().append("}");
					sb.newline();
					imports.add("fr.imag.adele.cadse.core.CadseException");
				}
			}
			if ("methods".equals(kind)) {
				boolean extendsClass = mustBeExtended() | isExtendsClass(getOwnerItem());
				
				Item manager = getOwnerItem().getPartParent();
				Item itemtype = ManagerManager.getItemType(manager);

				String className = (extendsClass ? GenerateJavaIdentifier.getContentClassName(context, itemtype) : defaultClassName);
				String itemVar = JavaIdentifier.javaIdentifierFromString(itemtype.getName(), false, true, null);
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("@Override");
				sb.newline().append("public ContentItem createContentItem(UUID id, Item owerItem ").append(
						") throws CadseException {");
				/* 1 */sb.begin();
				GenContext newcontext = new GenContext(context);
				newcontext.setAttribute("itemVar", itemVar);
				generateParts(sb, type, "createcontent-init", imports, newcontext);
				generateCallInit(sb, imports, newcontext);
				sb.newline().append(className).append(" cm = new ").append(className).append("(");
				/* 2 */sb.begin();
				sb.newline().append("id,");
				generateParts(sb, type, "createcontent-args", imports, newcontext);
				/* 3 */sb.begin();
				generateCallArguments(sb, imports, newcontext);
				sb.decrementLength();
				/* 3 */sb.end();

				sb.newline().append(");");
				/* 2 */sb.end();
				sb.newline().append("owerItem.setComposers(");
				/* 2 */sb.begin();
				generateComposersParts(getOwnerItem(), sb, type, "composers", imports, newcontext);
				sb.decrementLength();
				/* 2 */sb.end();
				sb.newline().append(");");
				sb.newline().append("owerItem.setExporters(");
				/* 2 */sb.begin();
				generateExportersParts(getOwnerItem(), sb, type, "exporters", imports, newcontext);
				sb.decrementLength();
				/* 2 */sb.end();
				sb.newline().append(");");
				sb.newline().append("return cm;");
				/* 1 */sb.end();
				sb.newline().append("}").newline();
				imports.add("fr.imag.adele.cadse.core.content.ContentItem");
				imports.add("java.util.UUID");
				imports.add("fr.imag.adele.cadse.core.Item");
				imports.add("fr.imag.adele.cadse.core.var.Variable");
				imports.add("fr.imag.adele.cadse.core.var.ContextVariable");
				imports.add("fr.imag.adele.cadse.core.CadseException");
				imports.add(defaultQualifiedClassName.getCanonicalName());

			}
		}

		/**
		 * Recherche la class du contentmanager et le manager provenant d'un
		 * super type...
		 * 
		 * @param extendsClassName
		 *            default valeur;
		 * @param itemtype
		 *            le type auquel on veut associer ce content manager.
		 * @param cxt
		 *            the cxt
		 * 
		 * @return le nom de la class ou la valeur par default.
		 */
		private String findSuperClassName(ContextVariable cxt, String extendsClassName, Item itemtype) {
			while (true) {
				Item superitemtype = ItemTypeManager.getSuperType(itemtype);
				if (superitemtype == null) {
					break;
				}
				Item superItemManager = ItemTypeManager.getManager(superitemtype);
				if (superItemManager == null)
					break;
				Item supercontentItem = ManagerManager.getContentModel(superItemManager);
				if (supercontentItem != null && supercontentItem.getType() == getOwnerItem().getType()) {
					if (isExtendsClass(supercontentItem)) {
						return ((JavaFileContentManager) superItemManager.getContentItem()).getClassName(cxt)
								+ "."+GenerateJavaIdentifier.getContentClassName(cxt, superitemtype);
					}
				}
				itemtype = superitemtype;

			}
			return extendsClassName;
		}

		/**
		 * Generate all class variables.
		 * 
		 * @param sb
		 *            the sb
		 * @param imports
		 *            the imports
		 */
		protected void generateAllClassVariables(GenStringBuilder sb, Set<String> imports) {
			StringAttributeType[] kinds = getResourceKindsName();

			if (kinds == null) {
				return;
			} else {
				for (int i = 0; i < kinds.length; i++) {
					StringAttributeType strKinds = kinds[i];
					String value = getOwnerItem().getAttribute(strKinds);

					value = getDefaultValue(strKinds, value);
					if (value == null || value.length() ==0) {
						continue;
					}
					GenerateVariable.generateClassVariable(getOwnerItem(),
							GenerateVariable.getClassVariable(strKinds.getName(), true), value, sb, imports);
				}
			}
		}

		protected String getDefaultValue(StringAttributeType strKinds, String value) {
			return value;
		}

		/**
		 * Gets the resource kinds name.
		 * 
		 * @return the resource kinds name
		 */
		protected StringAttributeType[] getResourceKindsName() {
			return null;
		}

		/**
		 * Generate call init.
		 * 
		 * @param sb
		 *            the sb
		 * @param imports
		 *            the imports
		 * @param newcontext
		 *            the newcontext
		 */
		protected void generateCallInit(GenStringBuilder sb, Set<String> imports, GenContext newcontext) {

		}

		/**
		 * Generate exporters parts.
		 * 
		 * @param item
		 *            the item
		 * @param sb
		 *            the sb
		 * @param type
		 *            the type
		 * @param kind
		 *            the kind
		 * @param imports
		 *            the imports
		 * @param context
		 *            the context
		 */
		public void generateExportersParts(Item item, GenStringBuilder sb, String type, String kind,
				Set<String> imports, GenContext context) {
			Item mapping = item.getPartParent();
			for (Link l : mapping.getOutgoingLinks()) {
				if (l.getLinkType().isPart() && l.isLinkResolved()) {
					ContentItem cm = l.getResolvedDestination().getContentItem();
					if (!l.getLinkType().getName().equals(EXPORTERS_LINK)) {
						continue;
					}
					if (cm != null) {
						cm.generate(sb, type, kind, imports, context);
					}
				}
			}
		}

		/**
		 * Generate composers parts.
		 * 
		 * @param item
		 *            the item
		 * @param sb
		 *            the sb
		 * @param type
		 *            the type
		 * @param kind
		 *            the kind
		 * @param imports
		 *            the imports
		 * @param context
		 *            the context
		 */
		public void generateComposersParts(Item item, GenStringBuilder sb, String type, String kind,
				Set<String> imports, GenContext context) {
			Item managerItem = item.getPartParent();
			Item itemtype = ManagerManager.getItemType(managerItem);
			Item compositeItem = CompositeItemTypeManager.getCompositeItemFromItemType(itemtype);
			if (compositeItem != null) {
				for (Link l : compositeItem.getOutgoingLinks()) {
					if (l.getLinkType().isPart() && l.isLinkResolved()) {
						ContentItem cm = l.getResolvedDestination().getContentItem();
						if (l.getLinkType() != CadseGCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS) {
							continue;
						}
						if (cm != null) {
							cm.generate(sb, type, kind, imports, context);
						}
					}
				}
			}
		}

		/**
		 * Generate call arguments.
		 * 
		 * @param sb
		 *            the sb
		 * @param imports
		 *            the imports
		 * @param context
		 *            the context
		 */
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, GenContext context) {
			StringAttributeType[] kinds = getResourceKindsName();
			if (kinds == null) {
				return;
			} else {
				for (int i = 0; i < kinds.length; i++) {
					StringAttributeType strKinds = kinds[i];
					String value = getOwnerItem().getAttribute(strKinds);
					value = getDefaultValue(strKinds, value);
					if (value == null || value.length() == 0) {
						sb.append(" ").append("NullVariable.INSTANCE,");
						imports.add("fr.imag.adele.cadse.core.impl.var.NullVariable");
					} else {
						sb.append(" ").append(GenerateVariable.getClassVariable(strKinds.getName(), true)).append(".INSTANCE,");
					}
				}
			}
		}

		/**
		 * Generate construstor arguments.
		 * 
		 * @param sb
		 *            the sb
		 */
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			sb.append("id,");
			StringAttributeType[] kinds = getResourceKindsName();
			if (kinds == null) {
				return;
			} else {
				for (int i = 0; i < kinds.length; i++) {
					StringAttributeType strKinds = kinds[i];
					sb.append(" ").append(GenerateVariable.getClassVariable(strKinds.getName(), false)).append(",");
				}
			}
		}

		/**
		 * Generate constructor parameter.
		 * 
		 * @param sb
		 *            the sb
		 */
		protected void generateConstructorParameter(GenStringBuilder sb) {
			
			sb.append("UUID id,");
			StringAttributeType[] kinds = getResourceKindsName();

			if (kinds == null) {
				return;
			} else {
				for (int i = 0; i < kinds.length; i++) {
					StringAttributeType strKinds = kinds[i];
					sb.append(" Variable ").append(GenerateVariable.getClassVariable(strKinds.getName(), false)).append(",");
				}
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
			Class<?> className = getRuntimeClassName();
			if (className != null) {
				String packageName = className.getPackage().getName();
				imports.add(packageName);
			}
			imports.add("fr.imag.adele.cadse.core");
			imports.add("org.eclipse.ltk.core.refactoring");
			imports.add("fr.imag.adele.cadse.core.build");
			imports.add("fr.imag.adele.cadse.core.var");
			imports.add("fr.imag.adele.cadse.core.impl.var");
			imports.add("fr.imag.adele.cadse.core.impl");
			imports.add("fr.imag.adele.cadse.core.content");
			imports.add("fr.imag.adele.cadse.util");
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
	 * Instantiates a new content model manager.
	 */
	public ContentItemTypeManager() {
	}

	/**
		@generated
	*/
	@Override
	public String computeQualifiedName(Item item, String name, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			Item currentItem;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(name);
			return sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	public boolean hasParentContent() {
		return false;
	}

	/**
	 * <meta-attribute key="title-create"> <value type="string" value="Extended
	 * Content manager"/> </meta-attribute> <meta-attribute
	 * key="must-be-extended"> <value type="boolean" value="true"/>
	 * </meta-attribute> <meta-attribute key="class-name"> <value type="string"
	 * value="fede.workspace.domain.ContentManager"/> </meta-attribute>
	 * 
	 * @return
	 */
	public Class<? extends ContentItem> getRuntimeClassName() {
		return fr.imag.adele.cadse.core.impl.ContentItemImpl.class;
	}

	/**
	 * Gets the workspace model.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the workspace model
	 */
	@Override
	public Item getWorkspaceModel(Item item) {
		return item.getPartParent().getPartParent().getPartParent();
	}

	/**
	 * Checks if is extends class.
	 * 
	 * @param contentmodel
	 *            the contentmodel
	 * 
	 * @return true, if is extends class
	 */
	public static final boolean isExtendsClass(Item contentmodel) {
		Object value = contentmodel.getAttribute(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_);
		if (value == null) {
			return false;
		}

		try {
			return Convert.toBooleanFalseIfNull(value);
		} catch (Throwable t) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#getDisplayName(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String getDisplayName(Item item) {
		return item.getName();
	}

	/**
	 * @generated
	 */
	public static final boolean isExtendsClassAttribute(Item contentItemType) {
		return contentItemType.getAttributeWithDefaultValue(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_, false);
	}

	/**
	 * @generated
	 */
	public static final void setExtendsClassAttribute(Item contentItemType, boolean value) {
		try {
			contentItemType.setAttribute(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getIdRuntimeAttribute(Item contentItemType) {
		return contentItemType.getAttributeWithDefaultValue(CadseGCST.CONTENT_ITEM_TYPE_at_ID_RUNTIME_, null);
	}

	/**
		@generated
	*/
	public static final void setIdRuntimeAttribute(Item contentItemType, String value) {
		try {
			contentItemType.setAttribute(CadseGCST.CONTENT_ITEM_TYPE_at_ID_RUNTIME_, value);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentItem(UUID id, Item owerItem) throws CadseException {
		return new ContentItemTypeManager.MyContentItem(id);
	}

	/**
	 * Must be extended.
	 * 
	 * @return true, if successful
	 */
	public boolean mustBeExtended() {
		return true;
	}

	/**
	 * Gets the uUID.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the uUID
	 */
	public static UUID getIdRuntime(Item contentIT) {
		
		String uuid_str = contentIT.getAttributeOwner(CadseGCST.CONTENT_ITEM_TYPE_at_ID_RUNTIME_);
		if (uuid_str == null || uuid_str.length() == 0) {
			UUID uuid = UUID.randomUUID();
			uuid_str = uuid.toString();
			try {
				contentIT.setAttribute(CadseGCST.CONTENT_ITEM_TYPE_at_ID_RUNTIME_, uuid_str);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return uuid;
		}
		return UUID.fromString(uuid_str);

	}

}

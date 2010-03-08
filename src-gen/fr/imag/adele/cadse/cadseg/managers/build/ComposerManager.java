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

package fr.imag.adele.cadse.cadseg.managers.build;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.SubFileContentManager;
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.IExtendClassManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.Menu;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.util.Convert;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class ComposerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ComposerManager extends DefaultItemManager implements IExtendClassManager {

	
	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ComposerManager() {
		super();
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

	/**
	 * The Class ComposerLinkAction.
	 */
	class ComposerLinkAction extends IMenuAction {

		/** The composer. */
		Item	composer;

		/** The link. */
		Item	link;

		/**
		 * Instantiates a new composer link action.
		 * 
		 * @param composer
		 *            the composer
		 * @param link
		 *            the link
		 */
		public ComposerLinkAction(Item composer, Item link) {
			this.composer = composer;
			this.link = link;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IMenuAction#run(fr.imag.adele.cadse.core.IItemNode[])
		 */
		@Override
		public void run(IItemNode[] selection) throws CadseException {
			try {
				CadseCore.createItemIfNeed(null, "link-" + link.getName(), CadseGCST.COMPOSER_LINK, composer,
						CadseGCST.COMPOSER_lt_COMPOSER_LINKS, CadseGCST.COMPOSER_LINK_lt_LINK, link);

			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IMenuAction#getImage()
		 */
		@Override
		public String getImage() {
			return CadseGCST.COMPOSER_LINK.getImage();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IMenuAction#getLabel()
		 */
		@Override
		public String getLabel() {
			return "Add composite link " + link.getName();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IMenuAction#isSeparator()
		 */
		@Override
		public boolean isSeparator() {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IMenuAction#getMenuPath()
		 */
		@Override
		public String getMenuPath() {
			return NEW_MENU;
		}

	}


	/**
	 * The Class ContentManager.
	 */
	public class ComposerContent extends SubFileContentManager implements IGenerateContent, IPDEContributor {

	
		/**
		 * Instantiates a new content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		public ComposerContent(UUID id) throws CadseException {
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
			String defaultQualifiedClassName = getDefaultClassName();
			String defaultClassName = JavaIdentifier.getlastclassName(defaultQualifiedClassName);

			if ("inner-class".equals(kind)) {
				imports.add("fr.imag.adele.cadse.core.Item");
				imports.add("fr.imag.adele.cadse.core.CadseException");
				imports.add("fr.imag.adele.cadse.core.build.IExporterTarget");
				imports.add(defaultQualifiedClassName);

				boolean extendsClass = mustBeExtended() || isExtendsClass(getOwnerItem());
				if (extendsClass) {

					String extendsClassName = defaultClassName;
					defaultClassName = JavaIdentifier.javaIdentifierFromString(getOwnerItem().getName(), true, false,
							"Composer");
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
					sb.newline().append("public ").append(defaultClassName).append(" (");
					generateConstructorParameter(sb);
					sb.decrementLength();
					sb.append(") {");
					sb.newline().append("	super(");
					generateConstrustorArguments(sb);
					sb.decrementLength();
					sb.append(");");
					sb.newline().append("}");
					if (generateGetTargetMethod()) {

						imports.add("fr.imag.adele.cadse.core.build.IExporterTarget");

						sb.newline();
						sb.newline().append("@Override");
						sb.newline().append("protected IExporterTarget getTarget() {");
						sb.newline().append("	// TODO Auto-generated method stub");
						sb.newline().append("	return null;");
						sb.newline().append("}");

					}

					sb.newline();
					sb.newline().append("@Override");
					sb
							.newline()
							.append(
									"protected void postCompose("
											+ "IBuildingContext context, List<IExportedContent> listExportedContent, IExporterTarget target) {");
					if (callsuperPostCompose()) {
						sb.newline().append("	super.postCompose(context, listExportedContent, target);");
					}
					sb.newline().append("	// TODO Auto-generated method stub");
					sb.newline().append("}");

					generateOtherMethods(sb, imports, context);

					sb.end();

					sb.newline().append("}");

					imports.add("java.util.List");
					imports.add("fr.imag.adele.cadse.core.build.Composer");
					imports.add("fr.imag.adele.cadse.core.build.IBuildingContext");
					imports.add("fr.imag.adele.cadse.core.build.IExportedContent");

				}
			}

			if ("composers".equals(kind)) {
				boolean extendsClass = mustBeExtended() || isExtendsClass(getOwnerItem());

				if (extendsClass) {
					defaultClassName = JavaIdentifier.javaIdentifierFromString(getOwnerItem().getName(), true, false,
							"Composer");
				}

				sb.newline().append("new ").append(defaultClassName).append(" (cm,");
				generateCallArguments(sb, imports, context);
				sb.decrementLength();
				sb.append("),");
			}

		}

		/**
		 * Generate other methods in the composer class when the composer
		 * extends the super class.
		 * 
		 * @param sb
		 *            A String builder to put generated code.
		 * @param imports
		 *            The list of the import package
		 * @param context
		 *            A context.
		 */
		protected void generateOtherMethods(GenStringBuilder sb, Set<String> imports, GenContext context) {

		}

		/**
		 * Generate the arguments to call the extended constructor of the
		 * composer when the composer class extends the super class.
		 * 
		 * @param sb
		 *            A String builder to put generated code.
		 * @param imports
		 *            The list of the import package
		 * @param context
		 *            A context.
		 */
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, GenContext context) {
			List<String> types = getTypesAttribute(getOwnerItem());
			if (types != null) {
				for (String exporterType : types) {
					sb.append(' ').appendStringValue(exporterType).append(',');
				}
			}
		}

		/**
		 * Generate the arguments to call the super constructor of the composer
		 * when the composer class extends the super class.
		 * 
		 * @param sb
		 *            A String builder to put generated code.
		 */
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			sb.newline().append("contentItem, exporterTypes,");
		}

		/**
		 * Generate the parameters of the extended constructor of the composer
		 * when the composer class extends the super class.
		 * 
		 * @param sb
		 *            A String builder to put generated code.
		 */
		protected void generateConstructorParameter(GenStringBuilder sb) {
			sb.append("ContentItem contentItem, String... exporterTypes,");
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
			String className = getDefaultClassName();
			String packageName = JavaIdentifier.packageName(className);
			imports.add(packageName);
			imports.add("fr.imag.adele.cadse.core");
			if (mustBeExtended()) {
				imports.add("org.eclipse.core.resources");
				imports.add("fede.workspace.eclipse.composition");
			}
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
		@generated
	*/
	@Override
	public ContentItem createContentItem(UUID id, Item owerItem ) throws CadseException {
		ComposerContent cm = new ComposerContent(
			id
			);
		owerItem.setComposers(
		);
		owerItem.setExporters(
		);
		return cm;
	}


	
	/**
	 * get links 'composer-links' from 'Composer' to 'ComposerLink'.
	 * 
	 * @param composer
	 *            the composer
	 * 
	 * @return the composer links link
	 * 
	 * @generated
	 */
	static public List<Link> getComposerLinksLink(Item composer) {
        return composer.getOutgoingLinks(CadseGCST.COMPOSER_lt_COMPOSER_LINKS);
    }

	/**
	 * Gets the composer links all.
	 * 
	 * @param composer
	 *            the composer
	 * 
	 * @return the composer links all
	 * 
	 * @generated
	 */
	static public Collection<Item> getComposerLinksAll(Item composer) {
        return composer.getOutgoingItems(CadseGCST.COMPOSER_lt_COMPOSER_LINKS, false);
    }

	/**
	 * Callsuper post compose.
	 * 
	 * @return true, if successful
	 */
	public boolean callsuperPostCompose() {
		return false;
	}

	/**
	 * Generate get target method.
	 * 
	 * @return true, if successful
	 */
	public boolean generateGetTargetMethod() {
		return true;
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
		Object value = contentmodel.getAttribute(CadseGCST.RUNTIME_ITEM_at_EXTENDS_CLASS_);
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
	 * @see model.workspace.workspace.managers.IExtendClassManager#getClassName(fr.imag.adele.cadse.core.Item)
	 */
	public String getClassName(Item uc) {
		return null;
	}

	/** The Constant DEFAUL_CLASS_NAME. */
	public static final String	DEFAUL_CLASS_NAME	= "fr.imag.adele.cadse.core.build.Composer";

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#getDefaultClassName()
	 */
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#mustBeExtended()
	 */
	public boolean mustBeExtended() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.IModelWorkspaceManager#getWorkspaceModel(fr.imag.adele.cadse.core.Item)
	 */
	public Item getWorkspaceModel(Item source) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the composer links.
	 * 
	 * @param composer
	 *            the composer
	 * 
	 * @return the composer links
	 * 
	 * @generated
	 */
	static public Collection<Item> getComposerLinks(Item composer) {
        return composer.getOutgoingItems(CadseGCST.COMPOSER_lt_COMPOSER_LINKS,true);
    }

	/**
	 * Adds the composer links.
	 * 
	 * @param composer
	 *            the composer
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addComposerLinks(Item composer, Item value) throws CadseException {
        composer.addOutgoingItem(CadseGCST.COMPOSER_lt_COMPOSER_LINKS,value);
    }

	/**
	 * Removes the composer links.
	 * 
	 * @param composer
	 *            the composer
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeComposerLinks(Item composer, Item value) throws CadseException {
        composer.removeOutgoingItem(CadseGCST.COMPOSER_lt_COMPOSER_LINKS,value);
    }

	/**
	 * Gets the types attribute.
	 * 
	 * @param composer
	 *            the composer
	 * 
	 * @return the types attribute
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final List<String> getTypesAttribute(Item composer) {
		try {
			List<String> list = composer.getAttribute(CadseGCST.COMPOSER_at_TYPES_);

			if (list == null)
				return null;

			return new ArrayList<String>(list);
		} catch (Throwable t) {
			return new ArrayList<String>();
		}
	}

	/**
	 * Sets the types attribute.
	 * 
	 * @param composer
	 *            the composer
	 * @param valueList
	 *            the value list
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void setTypesAttribute(Item composer, List<String> valueList) {
		try {
			List<String> list = new ArrayList<String>(valueList);
			composer.setAttribute(CadseGCST.COMPOSER_at_TYPES_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * Adds the types attribute.
	 * 
	 * @param composer
	 *            the composer
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void addTypesAttribute(Item composer, String value) {
		try {
			List<String> list = composer.getAttribute(CadseGCST.COMPOSER_at_TYPES_);
			if (list == null) {
				list = new ArrayList<String>();
			}
			list.add(value);
			composer.setAttribute(CadseGCST.COMPOSER_at_TYPES_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * Removes the types attribute.
	 * 
	 * @param composer
	 *            the composer
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void removeTypesAttribute(Item composer, String value) {
		try {

			List<String> list = composer.getAttribute(CadseGCST.COMPOSER_at_TYPES_);
			if (list == null) {
				return;
			}
			list.remove(value);
			if (list.size() == 0)
				composer.setAttribute(CadseGCST.COMPOSER_at_TYPES_, null);
			else
				composer.setAttribute(CadseGCST.COMPOSER_at_TYPES_, list);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#contributeMenuNewAction(fr.imag.adele.cadse.core.IMenuAction.Menu,
	 *      fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public void contributeMenuNewAction(Menu menu, Item composer) {

		Collection<Item> composerlinks = getComposerLinks(composer);
		Item itemtype = CompositeItemTypeManager.getItemType(composer.getPartParent());
		ONE: for (Link l : itemtype.getOutgoingLinks()) {
			if (l.getDestinationType() != CadseGCST.LINK_TYPE) {
				continue;
			}
			if (!l.isLinkResolved()) {
				continue;
			}

			Item link = l.getResolvedDestination();
			for (Item item : composerlinks) {
				if (ComposerLinkManager.getLink(item) == link) {
					continue ONE;
				}
			}
			// not found
			menu.insert(null, new ComposerLinkAction(composer, link), true);

		}
	}

}

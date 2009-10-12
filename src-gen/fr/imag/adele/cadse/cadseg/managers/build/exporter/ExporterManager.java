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

package fr.imag.adele.cadse.cadseg.managers.build.exporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.SubFileContentManager;
import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fede.workspace.model.manager.properties.impl.ic.IC_DefaultForList;
import fede.workspace.model.manager.properties.impl.mc.MC_DefaultForList;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.managers.IExtendClassManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.Pages;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.util.Convert;
import fr.imag.adele.cadse.core.var.Variable;
import java.lang.String;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;

/**
 * The Class ExporterManager.
 * 
 * @generated
 */
public class ExporterManager extends DefaultItemManager implements IExtendClassManager {

	

	/**
	 * The Class ContentManager.
	 */
	public class ExporterContent extends SubFileContentManager implements IGenerateContent, IPDEContributor {

		/**
		 * Instantiates a new content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		public ExporterContent(CompactUUID id) throws CadseException {
			super(id);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fr.imag.adele.cadse.core.IGenerateContent#generate(fr.imag.adele.
		 * cadse.core.var.ContextVariable)
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
		 * @see
		 * fr.imag.adele.cadse.core.ContentManager#generate(fr.imag.adele.cadse
		 * .core.GenStringBuilder, java.lang.String, java.lang.String,
		 * java.util.Set, fr.imag.adele.cadse.core.GenContext)
		 */
		@Override
		public void generate(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {
			// /ItemType it = getItem().getType();
			String defaultQualifiedClassName = getDefaultClassName();
			String defaultClassName = JavaIdentifier.getlastclassName(defaultQualifiedClassName);

			if ("inner-class".equals(kind)) {
				generateParts(sb, type, kind, imports, null);
				boolean extendsClass = mustBeExtended() || isExtendsClass(getItem());

				if (extendsClass) {

					String extendsClassName = defaultClassName;
					defaultClassName = JavaIdentifier.javaIdentifierFromString(getItem().getName(), true, false,
							"Exporter");

					Item manager = getItem().getPartParent();

					Item itemtype = ManagerManager.getItemType(manager);

					Item superitemtype = ItemTypeManager.getSuperType(itemtype);
					if (superitemtype != null) {
						Item superItemManager = ItemTypeManager.getManager(superitemtype);
						Item supercontentItem = ManagerManager.getContentModel(superItemManager);
						if (supercontentItem != null) {
							if (isExtendsClass(supercontentItem)) {
								extendsClassName = ((JavaFileContentManager) superItemManager.getContentItem())
										.getClassName(context)
										+ ".MyContentItem";
							}
						}
					}
					sb.newline();
					sb.appendGeneratedTag();
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
					sb.append(") {");
					sb.newline().append("	super(");
					generateConstrustorArguments(sb);
					sb.decrementLength();
					sb.append(");");
					sb.newline().append("}");
					sb.end();
					sb.newline().newline().append("@Override");
					sb.newline().append(
							"public IExportedContent exportItem(IBuildingContext context, "
									+ "IExporterTarget target, String exporterType) throws CadseException {");
					sb.newline().append("	// TODO Auto-generated method stub");
					sb.newline().append("	return null;");
					sb.newline().append("}");
					sb.newline();
					sb.newline().append("}");
					sb.newline();

					imports.add("fr.imag.adele.cadse.core.build.IBuildingContext");
					imports.add("fr.imag.adele.cadse.core.build.IExportedContent");
					imports.add("fr.imag.adele.cadse.core.build.IExporterTarget");

				}
			}
			if ("exporters".equals(kind)) {
				boolean extendsClass = mustBeExtended() || isExtendsClass(getItem());

				if (extendsClass) {
					defaultClassName = JavaIdentifier.javaIdentifierFromString(getItem().getName(), true, false,
							"Exporter");
				}

				sb.newline().append("new ").append(defaultClassName).append("(cm,");
				generateCallArguments(sb, imports, null);
				sb.decrementLength();
				sb.append("),");

				imports.add("fr.imag.adele.cadse.core.ContentItem");
				imports.add("fr.imag.adele.cadse.core.Item");
				imports.add("fr.imag.adele.cadse.core.CadseException");
				imports.add(defaultQualifiedClassName);
				imports.add("fr.imag.adele.cadse.core.build.IExportedContent");
				imports.add("fr.imag.adele.cadse.core.build.Exporter");
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
			List<String> types = getTypesAttribute(getItem());
			if (types != null) {
				for (String exporterType : types) {
					sb.append(' ').appendStringValue(exporterType).append(',');
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
			sb.append("contentItem, exporterTypes,");
		}

		/**
		 * Generate constructor parameter.
		 * 
		 * @param sb
		 *            the sb
		 */
		protected void generateConstructorParameter(GenStringBuilder sb) {
			sb.append("ContentItem contentItem, String... exporterTypes,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeExportsPackage(java.util.Set)
		 */
		public void computeExportsPackage(Set<String> exports) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeImportsPackage(java.util.Set)
		 */
		public void computeImportsPackage(Set<String> imports) {
			String className = getDefaultClassName();
			String packageName = JavaIdentifier.packageName(className);
			imports.add(packageName);
			imports.add("fr.imag.adele.cadse.core");
			imports.add("fr.imag.adele.cadse.core.build");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion
		 * (org.eclipse.pde.core.plugin.IPluginBase,
		 * org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
		 */
		public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
		}

	}

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ExporterManager() {
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
	 * Gets the types attribute.
	 * 
	 * @param exporter
	 *            the exporter
	 * 
	 * @return the types attribute
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final List<String> getTypesAttribute(Item exporter) {
		try {
			List<String> list = exporter.getAttribute(CadseGCST.EXPORTER_at_TYPES_);

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
	 * @param exporter
	 *            the exporter
	 * @param valueList
	 *            the value list
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void setTypesAttribute(Item exporter, List<String> valueList) {
		try {
			List<String> list = new ArrayList<String>(valueList);
			exporter.setAttribute(CadseGCST.EXPORTER_at_TYPES_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * Adds the types attribute.
	 * 
	 * @param exporter
	 *            the exporter
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void addTypesAttribute(Item exporter, String value) {
		try {
			List<String> list = exporter.getAttribute(CadseGCST.EXPORTER_at_TYPES_);
			if (list == null) {
				list = new ArrayList<String>();
			}
			list.add(value);
			exporter.setAttribute(CadseGCST.EXPORTER_at_TYPES_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * Removes the types attribute.
	 * 
	 * @param exporter
	 *            the exporter
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void removeTypesAttribute(Item exporter, String value) {
		try {

			List<String> list = exporter.getAttribute(CadseGCST.EXPORTER_at_TYPES_);
			if (list == null) {
				return;
			}
			list.remove(value);
			if (list.size() == 0)
				exporter.setAttribute(CadseGCST.EXPORTER_at_TYPES_, null);
			else
				exporter.setAttribute(CadseGCST.EXPORTER_at_TYPES_, list);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.IExtendClassManager#mustBeExtended()
	 */
	public boolean mustBeExtended() {
		return true;
	}

	/** The Constant EXTENDS_CLASS_ATTRIBUTE. */
	public static final String	EXTENDS_CLASS_ATTRIBUTE	= "extends-class";

	/**
	 * Checks if is extends class.
	 * 
	 * @param contentmodel
	 *            the contentmodel
	 * 
	 * @return true, if is extends class
	 */
	public static final boolean isExtendsClass(Item contentmodel) {
		Object value = contentmodel.getAttribute(EXTENDS_CLASS_ATTRIBUTE);
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
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#getDisplayName(fr.imag
	 * .adele.cadse.core.Item)
	 */
	@Override
	public String getDisplayName(Item item) {
		return item.getName();
	}

	/**
		@generated
	*/
	@Override
	public ContentItem createContentItem(CompactUUID id ) throws CadseException {
		ExporterContent cm = new ExporterContent(
			id
			);
		cm.setComposers(
		);
		cm.setExporters(
		);
		return cm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.IExtendClassManager#getClassName(fr
	 * .imag.adele.cadse.core.Item)
	 */
	public String getClassName(Item uc) {
		return (mustBeExtended() || isExtendsClass(uc)) ? JavaIdentifier.javaIdentifierFromString(uc.getName(), true,
				false, "Exporter") : getDefaultClassName();
	}

	/** The Constant DEFAUL_CLASS_NAME. */
	public static final String	DEFAUL_CLASS_NAME	= "fr.imag.adele.cadse.core.build.Exporter";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.IExtendClassManager#getDefaultClassName
	 * ()
	 */
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.IModelWorkspaceManager#getWorkspaceModel(fr
	 * .imag.adele.cadse.core.Item)
	 */
	public Item getWorkspaceModel(Item source) {
		// TODO Auto-generated method stub
		return null;
	}
}

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

package fr.imag.adele.cadse.cadseg.managers.ui;

import java.util.Set;
import java.util.UUID;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.SubFileContentManager;
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.CadseG_WLWCListener;
import fr.imag.adele.cadse.cadseg.managers.IExtendClassManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemFactory;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.ItemFactory;
import fr.imag.adele.cadse.core.transaction.delta.ItemDelta;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.util.Convert;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

/**
 * The Class DisplayManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class DisplayManager extends DefaultWorkspaceManager implements IItemManager, IExtendClassManager, IItemFactory {

	
	/** The Constant DEFAULT_SHORT_NAME. */
	public static final String	DEFAULT_SHORT_NAME	= "display";

	/** The Constant POSTFIXE_UI. */
	public static final String	POSTFIXE_UI			= "UI";

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return "Cannot rename a display";
	}

	/**
	 * Adds the attribute in call.
	 * 
	 * @param field
	 *            the field
	 * @param key
	 *            the key
	 * @param valueQuote
	 *            the value quote
	 * @param defaultValue
	 *            the default value
	 * @param sb
	 *            the sb
	 */
	static public <T> void addAttributeInCall(Item field, IAttributeType<T> key, boolean valueQuote, T defaultValue,
			GenStringBuilder sb) {
		T value = field.getAttribute(key);
		if (value == null) {
			value = defaultValue;
		}
		if (value instanceof String && ((String)value).length() == 0) {
			value = defaultValue;
		}

		sb.append(" ");
		if (value == null) {
			sb.append("null,");
		} else if (valueQuote) {
			sb.appendStringValue_vir((String) value);
		} else {
			sb.append(value).append(",");
		}
	}

	/**
	 * The Class MyContentItem.
	 */
	public class DisplayContent extends SubFileContentManager implements IPDEContributor {

		/**
		 * Instantiates a new my content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		protected DisplayContent(UUID id) throws CadseException {
			super(id);
		}

		/**
		 * Compute local manifest imports.
		 * 
		 * @param item
		 *            the item
		 * @param imports
		 *            the imports
		 */
		public void computeLocalManifestImports(Item item, Set<String> imports) {

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
			imports.add("fr.imag.adele.cadse.core.ui");
			String className = getClassName(getOwnerItem());
			if (className == null) {
				className = getDefaultClassName();
				if (className != null) {
					imports.add(JavaIdentifier.packageName(className));
				}
			}
			imports.add("fr.imag.adele.cadse.core");
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
				generateParts(sb, type, kind, imports, null);
				Item display = getOwnerItem();
				boolean extendsUI = DisplayManager.isExtendsUIAttribute(display);
				if (extendsUI) {
					Item field = display.getPartParent();

					String extendsClassName = defaultClassName;

					defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false,
							POSTFIXE_UI);
					sb.newline();
					sb.newline().append("/**");
					sb.newline().append("	@generated");
					sb.newline().append("*/");
					sb.newline().append("static public class ").append(defaultClassName).append(" extends ").append(
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
					sb.newline();
					sb.newline().append("}");
					sb.newline();
				}
			}
			if (kind.equals("in-field")) {
				generateParts(sb, type, kind, imports, context);

				Item display = getOwnerItem();
				Item field = display.getPartParent();
				Item attribute = CadseG_WLWCListener.tryToRecreateAttributeLink(field);
				if (attribute == null) {
					// erreur
					sb.append("/* ERROR not found attribut*/");
					// TODO register error in workspace error manager( create)
					return;
				}

				generateParts(sb, type, "field-init", imports, context);

				boolean extendsClass = DisplayManager.isExtendsUIAttribute(display);
				if (defaultQualifiedClassName != null) {
					imports.add(defaultQualifiedClassName);
				}
				imports.add("fr.imag.adele.cadse.core.ui.EPosLabel");

				if (extendsClass) {
					defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false,
							POSTFIXE_UI);
				}

				sb.newline().append("return ");
				sb.append("new ").append(defaultClassName).append("(");
				generateCallArguments(sb, imports);

				sb.decrementLength();
				sb.append(");");
			}

		}

		/**
		 * Generate call arguments.
		 * 
		 * @param sb
		 *            the sb
		 * @param imports
		 *            the imports
		 */
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports) {

			Item display = getOwnerItem();
			Item field = display.getPartParent();
			Item attribute = CadseG_WLWCListener.tryToRecreateAttributeLink(field);
			if (attribute == null) {
				// erreur
				sb.append("/* ERROR not found attribut*/");
				// TODO register error in workspace error manager( create)
				return;
			}
			EPosLabel poslabel = FieldManager.getPositionAttribute(field);
			if (poslabel == null || EPosLabel.defaultpos == poslabel) {
				poslabel = getDefaultPosLabel();
			}
			String label = FieldManager.getLabelAttribute(field);
			if (label == null) {
				label = "???";
			}
			sb.append(GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, attribute, null, null,
					imports));
			if (AttributeManager.isLinkAttribute(attribute)) {
				sb.append(".getName()");
			}
			sb.append(",");
			sb.begin();
			sb.newline().appendStringValue_vir(label);
			sb.newline().append("EPosLabel.").append(poslabel.toString()).append(',');

			Item ic = getItemIC(display);
			Item mc = getItemMC(display);
			if (mc != null) {
				sb.append("mc, ");
			} else {
				sb.append("new MC_AttributesItem(), ");
				imports.add("fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem");
			}
			if (ic != null) {
				sb.append("ic,");
			} else {
				sb.append("null,");
			}
			sb.end();
		}

		/**
		 * Generate construstor arguments.
		 * 
		 * @param sb
		 *            the sb
		 */
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			sb.append("key, label, poslabel, mc, ic");
		}

		/**
		 * Generate constructor parameter.
		 * 
		 * @param sb
		 *            the sb
		 */
		protected void generateConstructorParameter(GenStringBuilder sb) {
			sb.append("String key, String label, EPosLabel poslabel, "
					+ "IModelController mc, IInteractionController ic,");
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

	/** The Constant IC_LINK. */
	public static final String	IC_LINK					= "ic";

	/** The Constant MC_LINK. */
	public static final String	MC_LINK					= "mc";

	/** The Constant IC_DEFAULT_SHORT_NAME. */
	public static final String	IC_DEFAULT_NAME	= "ic";

	/** The Constant MC_DEFAULT_SHORT_NAME. */
	public static final String	MC_DEFAULT_NAME	= "mc";

	/**
	 * Instantiates a new display manager.
	 */
	public DisplayManager() {
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
	 * @generated
	 */
	@Override
	public String getDisplayName(Item item) {
		try {
			Object value;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * Gets the item ic.
	 * 
	 * @param field
	 *            the field
	 * 
	 * @return the item ic
	 */
	static public Item getItemIC(Item field) {
		return field.getOutgoingItem(IC_LINK, true);
	}

	/**
	 * Gets the item mc.
	 * 
	 * @param field
	 *            the field
	 * 
	 * @return the item mc
	 */
	static public Item getItemMC(Item field) {
		return field.getOutgoingItem(MC_LINK, true);
	}

	/**
	 * Gets the default pos label.
	 * 
	 * @return the default pos label
	 */
	protected EPosLabel getDefaultPosLabel() {
		return EPosLabel.left;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentItem(UUID id, Item owerItem) throws CadseException {
		return new DisplayContent(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#getClassName(fr.imag.adele.cadse.core.Item)
	 */
	public String getClassName(Item uc) {
		return uc.getAttribute(CadseGCST.RUNTIME_ITEM_at_CLASS_NAME_);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#getDefaultClassName()
	 */
	public String getDefaultClassName() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#mustBeExtended()
	 */
	public boolean mustBeExtended() {
		return true;
	}

	// /** The Constant EXTENDS_IC_ATTRIBUTE. */
	// public static final String EXTENDS_IC_ATTRIBUTE = "extendsIC";

	// /** The Constant EXTENDS_MC_ATTRIBUTE. */
	// public static final String EXTENDS_MC_ATTRIBUTE = "extendsMC";

	// /** The Constant EXTENDS_UI_ATTRIBUTE. */
	// public static final String EXTENDS_UI_ATTRIBUTE = "extendsUI";

	/**
	 * Checks if is extends ic attribute.
	 * 
	 * @param display
	 *            the display
	 * 
	 * @return true, if checks if is extends ic attribute
	 * 
	 * @generated
	 */
	public static final boolean isExtendsICAttribute(Item display) {
		return display.getAttributeWithDefaultValue(CadseGCST.DISPLAY_at_EXTENDS_IC_, false);
	}

	/**
	 * Sets the extends ic attribute.
	 * 
	 * @param display
	 *            the display
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setExtendsICAttribute(Item display, boolean value) {
		try {
			display.setAttribute(CadseGCST.DISPLAY_at_EXTENDS_IC_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is extends mc attribute.
	 * 
	 * @param display
	 *            the display
	 * 
	 * @return true, if checks if is extends mc attribute
	 * 
	 * @generated
	 */
	public static final boolean isExtendsMCAttribute(Item display) {
		return display.getAttributeWithDefaultValue(CadseGCST.DISPLAY_at_EXTENDS_MC_, false);
	}

	/**
	 * Checks if is editable attribute.
	 * 
	 * @param display
	 *            the display
	 * 
	 * @return true, if checks if is editable attribute
	 * 
	 * 
	 */
	public static final boolean isEditableAttribute(Item display) {
		Object value = display.getAttribute(CadseGCST.FIELD_at_EDITABLE_);
		if (value == null) {
			return true;
		}

		try {
			return Convert.toBooleanFalseIfNull(value);
		} catch (Throwable t) {
			return true;
		}
	}

	/**
	 * Sets the extends mc attribute.
	 * 
	 * @param display
	 *            the display
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setExtendsMCAttribute(Item display, boolean value) {
		try {
			display.setAttribute(CadseGCST.DISPLAY_at_EXTENDS_MC_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is extends ui attribute.
	 * 
	 * @param display
	 *            the display
	 * 
	 * @return true, if checks if is extends ui attribute
	 * 
	 * @generated
	 */
	public static final boolean isExtendsUIAttribute(Item display) {
		return display.getAttributeWithDefaultValue(CadseGCST.DISPLAY_at_EXTENDS_UI_, false);
	}

	/**
	 * Sets the extends ui attribute.
	 * 
	 * @param display
	 *            the display
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setExtendsUIAttribute(Item display, boolean value) {
		try {
			display.setAttribute(CadseGCST.DISPLAY_at_EXTENDS_UI_, value);
		} catch (Throwable t) {

		}
	}
//
//	/**
//	 * Creates the field extends ic.
//	 * 
//	 * @return the d check box ui
//	 * 
//	 * @not generated
//	 */
//	static public DCheckBoxUI createFieldExtendsIC() {
//		MC_StringToBoolean mc = new MC_StringToBoolean();
//		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_IC, "extends Interaction Controller", EPosLabel.none,
//				mc, null);
//	}
//
//	/**
//	 * Creates the field extends mc.
//	 * 
//	 * @return the d check box ui
//	 * 
//	 * @not generated
//	 */
//	static public DCheckBoxUI createFieldExtendsMC() {
//		MC_StringToBoolean mc = new MC_StringToBoolean();
//		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_MC, "extends Model Controller", EPosLabel.none, mc, null);
//	}
//
//	/**
//	 * Creates the field extends ui.
//	 * 
//	 * @return the d check box ui
//	 * 
//	 * @not generated
//	 */
//	static public DCheckBoxUI createFieldExtendsUI() {
//		MC_StringToBoolean mc = new MC_StringToBoolean();
//		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_UI, "extends UI", EPosLabel.none, mc, null);
//	}
//
//	/**
//	 * Creates the field editable.
//	 * 
//	 * @return the d check box ui
//	 * 
//	 * @not generated
//	 */
//	static public DCheckBoxUI createFieldEditable() {
//		MC_StringToBoolean mc = new MC_StringToBoolean();
//		return new DCheckBoxUI(CadseGCST.FIELD_at_EDITABLE, "editable", EPosLabel.none, mc, null);
//	}

	

	@Override
	public Item newForCommitItem(LogicalWorkspace wl, ItemType it, ItemDelta item) {
		return ItemFactory.SINGLETON.newForCommitItem(wl, it, item);
	}

}

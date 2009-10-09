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

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.util.Convert;
import java.lang.String;

/**
 * The Class DTextManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class DTextManager extends DisplayManager implements IItemManager {

	/**
	 * The Class MyContentItem.
	 */
	public final class MyContentItem extends DisplayManager.MyContentItem {

		/** The Constant STYLE_ATTRIBUTE. */
		private static final String	STYLE_ATTRIBUTE	= "style_attribute";

		/**
		 * Instantiates a new my content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 * @throws CadseException
		 */
		protected MyContentItem(ContentItem parent, Item item) throws CadseException {
			super(parent, item);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#computeImportsPackage(java.util.Set)
		 */
		@Override
		public void computeImportsPackage(Set<String> imports) {
			imports.add("fr.imag.adele.cadse.core");
			imports.add("fede.workspace.model.manager.properties");
			imports.add("fede.workspace.model.manager.properties.impl.ui");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports) {
			super.generateCallArguments(sb, imports);
			Item display = getItem();
			sb.append_exp_vir(display, WorkspaceCST.DTEXT_at_VERTICAL_SPAN, "1");
			sb.append_string_vir(display, WorkspaceCST.DTEXT_at_TOOL_TIP);
			sb.append(Convert.toBoolean(display.getAttribute(WorkspaceCST.DTEXT_at_MULTI_LINE_), false)).append(",");
			sb.append(Convert.toBoolean(display.getAttribute(WorkspaceCST.DTEXT_at_NO_BORDER_), false)).append(",");
			sb.append(Convert.toBoolean(display.getAttribute(WorkspaceCST.DTEXT_at_WRAP_LINE_), false)).append(",");

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			sb
					.append("String key, String label, EPosLabel poslabel, "
							+ "IModelController mc, IInteractionControllerForBrowserOrCombo ic, int vspan, String tooltip, boolean multiLine, boolean wrapLine, boolean noBorder,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			sb.append("key, label, poslabel, " + "mc, ic, style, vspan, tooltip, multiLine, wrapLine, noBorder,");
		}

	}

	/** The Constant DEFAUL_CLASS_NAME. */
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.ui.DTextUI";

	/**
	 * Instantiates a new d text manager.
	 */
	public DTextManager() {
	}

	/**
	 * @generated
	 */
	@Override
	public String computeUniqueName(Item item, String shortName, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(shortName);
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
			Item currentItem;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * get a link 'ic' from 'DText' to 'InteractionController'.
	 * 
	 * @generated
	 */
	static public Link getIcLink(Item dText) {
		return dText.getOutgoingLink(WorkspaceCST.DTEXT_lt_IC);
	}

	/**
	 * get all link destination 'ic' from 'DText' to 'InteractionController'.
	 * 
	 * @generated
	 */
	static public Item getIcAll(Item dText) {
		return dText.getOutgoingItem(WorkspaceCST.DTEXT_lt_IC, false);
	}

	/**
	 * get resolved link destination 'ic' from 'DText' to
	 * 'InteractionController'.
	 * 
	 * @generated
	 */
	static public Item getIc(Item dText) {
		return dText.getOutgoingItem(WorkspaceCST.DTEXT_lt_IC, true);
	}

	/**
	 * set a link 'ic' from 'DText' to 'InteractionController'.
	 * 
	 * @generated
	 */
	static public void setIc(Item dText, Item value) throws CadseException {
		dText.setOutgoingItem(WorkspaceCST.DTEXT_lt_IC, value);
	}

	/**
	 * get a link 'mc' from 'DText' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public Link getMcLink(Item dText) {
		return dText.getOutgoingLink(WorkspaceCST.DTEXT_lt_MC);
	}

	/**
	 * get all link destination 'mc' from 'DText' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public Item getMcAll(Item dText) {
		return dText.getOutgoingItem(WorkspaceCST.DTEXT_lt_MC, false);
	}

	/**
	 * get resolved link destination 'mc' from 'DText' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public Item getMc(Item dText) {
		return dText.getOutgoingItem(WorkspaceCST.DTEXT_lt_MC, true);
	}

	/**
	 * set a link 'mc' from 'DText' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public void setMc(Item dText, Item value) throws CadseException {
		dText.setOutgoingItem(WorkspaceCST.DTEXT_lt_MC, value);
	}

	/**
	 * @generated
	 */
	public static final String getToolTipAttribute(Item dText) {
		return dText.getAttributeWithDefaultValue(WorkspaceCST.DTEXT_at_TOOL_TIP_, null);
	}

	/**
	 * @generated
	 */
	public static final void setToolTipAttribute(Item dText, String value) {
		try {
			dText.setAttribute(WorkspaceCST.DTEXT_at_TOOL_TIP_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final int getVerticalSpanAttribute(Item dText) {
		return dText.getAttributeWithDefaultValue(WorkspaceCST.DTEXT_at_VERTICAL_SPAN_, 1);
	}

	/**
	 * @generated
	 */
	public static final void setVerticalSpanAttribute(Item dText, int value) {
		try {
			dText.setAttribute(WorkspaceCST.DTEXT_at_VERTICAL_SPAN_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isMultiLineAttribute(Item dText) {
		return dText.getAttributeWithDefaultValue(WorkspaceCST.DTEXT_at_MULTI_LINE_, false);
	}

	/**
	 * @generated
	 */
	public static final void setMultiLineAttribute(Item dText, boolean value) {
		try {
			dText.setAttribute(WorkspaceCST.DTEXT_at_MULTI_LINE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isWrapLineAttribute(Item dText) {
		return dText.getAttributeWithDefaultValue(WorkspaceCST.DTEXT_at_WRAP_LINE_, false);
	}

	/**
	 * @generated
	 */
	public static final void setWrapLineAttribute(Item dText, boolean value) {
		try {
			dText.setAttribute(WorkspaceCST.DTEXT_at_WRAP_LINE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isNoBorderAttribute(Item dText) {
		return dText.getAttributeWithDefaultValue(WorkspaceCST.DTEXT_at_NO_BORDER_, null);
	}

	/**
	 * @generated
	 */
	public static final void setNoBorderAttribute(Item dText, boolean value) {
		try {
			dText.setAttribute(WorkspaceCST.DTEXT_at_NO_BORDER_, value);
		} catch (Throwable t) {

		}
	}

	// /* (non-Javadoc)
	// * @see
	// fede.workspace.model.manager.DefaultItemManager#createCreationPages(fr.imag.adele.cadse.core.Item,
	// fr.imag.adele.cadse.core.LinkType, fr.imag.adele.cadse.core.ItemType)
	// */
	// @Override
	// public Pages createCreationPages(Item theItemParent, LinkType
	// theLinkType,
	// ItemType desType) {
	//
	// CreationAction action = new CreationAction(theItemParent, desType,
	// theLinkType, DEFAULT_SHORT_NAME);
	//
	// return FieldsCore.createWizard(action,
	// FieldsCore.createPage("page1", "Create a text", "Create a text", 3,
	// FieldsCore.createTextField(WorkspaceCST.DTEXT_at_TOOL_TIP, "tool tip",
	// 5),
	// FieldsCore.createTextField(WorkspaceCST.DTEXT_at_VERTICAL_SPAN, "vertical
	// span",
	// new IntModelController(1,-1,"The value must be bigger than 1",null,"1")),
	// FieldsCore.createTextField("pattern", "pattern (not implemented)"),
	// createFieldExtendsIC(),
	// createFieldExtendsMC(),
	// createFieldExtendsUI()
	//
	// )
	// );
	// }

	// /* (non-Javadoc)
	// * @see
	// fede.workspace.model.manager.DefaultItemManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	// */
	// @Override
	// public Pages createModificationPage(Item item) {
	// AbstractActionPage action = new ModificationAction(item);
	//
	// return FieldsCore.createWizard(action,
	// FieldsCore.createPage("page1", "a text", "a text", 3,
	// FieldsCore.createTextField(WorkspaceCST.DTEXT_at_TOOL_TIP, "tool tip",
	// 5),
	// FieldsCore.createTextField(WorkspaceCST.DTEXT_at_VERTICAL_SPAN, "vertical
	// span",
	// new IntModelController(1,-1,"The value must be bigger than 1",null,"1")),
	// FieldsCore.createTextField("pattern", "pattern (not implemented)"),
	// createFieldExtendsIC(),
	// createFieldExtendsMC(),
	// createFieldExtendsUI()
	//
	// )
	// );
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentManager(Item item) throws CadseException {
		return new MyContentItem(null, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateMeItem(Item field, LinkType lt, ItemType destType) {
		Item attribute = FieldManager.getAttribute(field);
		if (attribute == null) {
			return "You must add a link to an attribute link";
		}
		if (AttributeManager.isIsListAttribute(attribute)) {
			return "Text is not for a list";
		}
		ItemType it = attribute.getType();

		if (WorkspaceCST.BOOLEAN != it && WorkspaceCST.DOUBLE != it && WorkspaceCST.INTEGER != it
				&& WorkspaceCST.STRING != it & WorkspaceCST.DATE != it) {
			return "Text is for Boolean, Double, Integer, String or Date";
		}

		return null;
	}

}

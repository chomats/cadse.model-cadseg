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
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.util.Convert;

/**
 * The Class DListManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class DListManager extends DisplayManager {

	/** The Constant SHOW_FILTER. */
	// public static final String SHOW_FILTER = "show-filter";
	/**
	 * The Class MyContentItem.
	 */
	public final class MyContentItem extends DisplayManager.MyContentItem {

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
		 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			sb
					.append("String key, String label, EPosLabel poslabel, "
							+ "IModelController mc, IInteractionControllerForList ic, boolean edit, boolean showFilter, boolean order, boolean update,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			sb.append("key, label, poslabel, " + "mc, ic, edit, showFilter, order, update,");
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
			sb.append(" ").append(isEditableButtonAttribute(getItem())).append(',');
			sb.append(" ").append(isShowFilterAttribute(getItem())).append(',');
			sb.append(" ").append(isOrderButtonAttribute(getItem())).append(',');
			sb.append(" ").append(isUpdateButtonAttribute(getItem())).append(',');

			imports.add("fede.workspace.model.manager.properties.IInteractionControllerForList");
			imports.add("fr.imag.adele.cadse.core.ui.IModelController");

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#computeImportsPackage(java.util.Set)
		 */
		@Override
		public void computeImportsPackage(Set<String> imports) {
			super.computeImportsPackage(imports);
			imports.add("org.eclipse.jface.fieldassist");
			imports.add("org.eclipse.ui.dialogs");

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#hasContent(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public boolean hasContent(Item item) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentManager(Item item) throws CadseException {
		return new MyContentItem(null, item);
	}

	/** The Constant DEFAUL_CLASS_NAME. */
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.ui.DListUI";

	/**
	 * Instantiates a new d list manager.
	 */
	public DListManager() {
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
	 * get a link 'ic' from 'DList' to 'InteractionController'.
	 * 
	 * @generated
	 */
	static public Link getIcLink(Item dList) {
		return dList.getOutgoingLink(WorkspaceCST.DLIST_lt_IC);
	}

	/**
	 * get all link destination 'ic' from 'DList' to 'InteractionController'.
	 * 
	 * @generated
	 */
	static public Item getIcAll(Item dList) {
		return dList.getOutgoingItem(WorkspaceCST.DLIST_lt_IC, false);
	}

	/**
	 * get resolved link destination 'ic' from 'DList' to
	 * 'InteractionController'.
	 * 
	 * @generated
	 */
	static public Item getIc(Item dList) {
		return dList.getOutgoingItem(WorkspaceCST.DLIST_lt_IC, true);
	}

	/**
	 * set a link 'ic' from 'DList' to 'InteractionController'.
	 * 
	 * @generated
	 */
	static public void setIc(Item dList, Item value) throws CadseException {
		dList.setOutgoingItem(WorkspaceCST.DLIST_lt_IC, value);
	}

	/**
	 * get a link 'mc' from 'DList' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public Link getMcLink(Item dList) {
		return dList.getOutgoingLink(WorkspaceCST.DLIST_lt_MC);
	}

	/**
	 * get all link destination 'mc' from 'DList' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public Item getMcAll(Item dList) {
		return dList.getOutgoingItem(WorkspaceCST.DLIST_lt_MC, false);
	}

	/**
	 * get resolved link destination 'mc' from 'DList' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public Item getMc(Item dList) {
		return dList.getOutgoingItem(WorkspaceCST.DLIST_lt_MC, true);
	}

	/**
	 * set a link 'mc' from 'DList' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public void setMc(Item dList, Item value) throws CadseException {
		dList.setOutgoingItem(WorkspaceCST.DLIST_lt_MC, value);
	}

	/**
	 * @generated
	 */
	public static final boolean isEditableButtonAttribute(Item dList) {
		return dList.getAttributeWithDefaultValue(WorkspaceCST.DLIST_at_EDITABLE_BUTTON_, true);
	}

	/**
	 * @generated
	 */
	public static final void setEditableButtonAttribute(Item dList, boolean value) {
		try {
			dList.setAttribute(WorkspaceCST.DLIST_at_EDITABLE_BUTTON_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isUpdateButtonAttribute(Item dList) {
		return dList.getAttributeWithDefaultValue(WorkspaceCST.DLIST_at_UPDATE_BUTTON_, false);
	}

	/**
	 * @generated
	 */
	public static final void setUpdateButtonAttribute(Item dList, boolean value) {
		try {
			dList.setAttribute(WorkspaceCST.DLIST_at_UPDATE_BUTTON_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isOrderButtonAttribute(Item dList) {
		return dList.getAttributeWithDefaultValue(WorkspaceCST.DLIST_at_ORDER_BUTTON_, false);
	}

	/**
	 * @generated
	 */
	public static final void setOrderButtonAttribute(Item dList, boolean value) {
		try {
			dList.setAttribute(WorkspaceCST.DLIST_at_ORDER_BUTTON_, value);
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
	// theLinkType, ItemType desType) {
	// CreationAction action = new CreationAction(theItemParent, desType,
	// theLinkType, DEFAULT_SHORT_NAME);
	//
	// return FieldsCore.createWizard(action,
	// FieldsCore.createPage("page1", "Create a list", "Create a list", 3,
	// createFieldExtendsIC(),
	// createFieldExtendsMC(),
	// createFieldExtendsUI(),
	// createFieldEditable()/*,
	// createFieldEnable()*/
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
	// FieldsCore.createPage("page1", "a list", "a list", 3,
	// createFieldExtendsIC(),
	// createFieldExtendsMC(),
	// createFieldExtendsUI(),
	// createFieldEditable(),
	// createFieldShowFilter()
	// )
	// );
	// }

	// /**
	// * Creates the field show filter.
	// *
	// * @return the d check box ui
	// *
	// * @not generated
	// */
	// static public DCheckBoxUI createFieldShowFilter() {
	// StringToBooleanModelControler mc = new StringToBooleanModelControler();
	// return new DCheckBoxUI(WorkspaceCST.DLIST_at_SHOW_FILTER,
	// "show filter",
	// EPosLabel.none,
	// mc, null
	// );
	// }
	//

	/**
	 * Checks if is show filter attribute.
	 * 
	 * @param display
	 *            the display
	 * 
	 * @return true, if is show filter attribute
	 */
	public static final boolean isShowFilterAttribute(Item display) {
		Object value = display.getAttribute(WorkspaceCST.DLIST_at_SHOW_FILTER);
		if (value == null) {
			return false;
		}

		try {
			return Convert.toBooleanFalseIfNull(value);
		} catch (Throwable t) {
			return false;
		}
	}

	/**
	 * @generated
	 */
	public static final void setShowFilterAttribute(Item dList, boolean value) {
		try {
			dList.setAttribute(WorkspaceCST.DLIST_at_SHOW_FILTER_, value);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#getDefaultPosLabel()
	 */
	@Override
	protected EPosLabel getDefaultPosLabel() {
		return EPosLabel.top;
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
		if (!AttributeManager.isIsListAttribute(attribute)) {
			return "List is for a list";
		}
		return null;
	}

}

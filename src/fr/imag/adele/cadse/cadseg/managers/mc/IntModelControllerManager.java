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

package fr.imag.adele.cadse.cadseg.managers.mc;

import java.util.Set;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.ui.DisplayManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.Pages;
import fr.imag.adele.cadse.core.util.Convert;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.mc.IntModelController;

/**
 * The Class IntModelControllerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IntModelControllerManager extends ModelControllerManager implements IItemManager {

	/**
	 * The Class MyContentItem.
	 */
	class MyContentItem extends ModelControllerManager.MyContentItem {

		/**
		 * Instantiates a new my content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 * @throws CadseException
		 */
		public MyContentItem(ContentItem parent, Item item) throws CadseException {
			super(parent, item);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set, java.lang.Object)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
			sb.append(getItem().getAttributeWithDefaultValue("min", 0)).append(", ");
			String maxAttribute = Convert.toString(getItem().getAttributeWithDefaultValue("max", 0));
			if (maxAttribute == null || maxAttribute.length() == 0)
				maxAttribute = "0";
			sb.append(maxAttribute).append(", ");
			String minError = getItem().getAttribute("min-error");
			String maxError = getItem().getAttribute("max-error");
			if (minError == null || minError.length() == 0)
				sb.append("null, ");
			else
				sb.appendStringValue(minError).append(", ");
			if (maxError == null || maxError.length() == 0)
				sb.append("null, ");
			else
				sb.appendStringValue(maxError).append(", ");
			sb.append(Convert.toInteger(getItem().getAttribute(WorkspaceCST.INT_MODEL_CONTROLLER_at_DEFAULT_VALUE)))
					.append(",");

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			sb.append("min, max, msg_min, msg_max, defaultValue");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			sb.append("int min, int max, String msg_min, String msg_max, Integer defaultValue");
		}
	}

	/** The Constant DEFAUL_CLASS_NAME. */
	@SuppressWarnings("hiding")
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.mc.IntModelController";

	/**
	 * Instantiates a new int model controller manager.
	 */
	public IntModelControllerManager() {
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
	 * @generated
	 */
	public static final String getErrorMsgMaxAttribute(Item intModelController) {
		Object value = intModelController.getAttribute(WorkspaceCST.INT_MODEL_CONTROLLER_at_ERROR_MSG_MAX);
		if (value == null)
			return "";

		try {
			String retvalue = (String) value;

			return retvalue;
		} catch (Throwable t) {
			return "";
		}

	}

	/**
	 * @generated
	 */
	public static final void setErrorMsgMaxAttribute(Item intModelController, String value) {
		try {
			Object setvalue = value;
			intModelController.setAttribute(WorkspaceCST.INT_MODEL_CONTROLLER_at_ERROR_MSG_MAX, setvalue);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final String getErrorMsgMinAttribute(Item intModelController) {
		Object value = intModelController.getAttribute(WorkspaceCST.INT_MODEL_CONTROLLER_at_ERROR_MSG_MIN);
		if (value == null)
			return "";

		try {
			String retvalue = (String) value;

			return retvalue;
		} catch (Throwable t) {
			return "";
		}

	}

	/**
	 * @generated
	 */
	public static final void setErrorMsgMinAttribute(Item intModelController, String value) {
		try {
			Object setvalue = value;
			intModelController.setAttribute(WorkspaceCST.INT_MODEL_CONTROLLER_at_ERROR_MSG_MIN, setvalue);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final int getMaxAttribute(Item intModelController) {
		Object value = intModelController.getAttribute(WorkspaceCST.INT_MODEL_CONTROLLER_at_MAX);
		if (value == null)
			return -1;

		try {
			return Convert.toInt(value, null, -1);
		} catch (Throwable t) {
			return -1;
		}

	}

	/**
	 * @generated
	 */
	public static final void setMaxAttribute(Item intModelController, int value) {
		try {
			Object setvalue = value;
			intModelController.setAttribute(WorkspaceCST.INT_MODEL_CONTROLLER_at_MAX, setvalue);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final int getMinAttribute(Item intModelController) {
		Object value = intModelController.getAttribute(WorkspaceCST.INT_MODEL_CONTROLLER_at_MIN);
		if (value == null)
			return -1;

		try {
			return Convert.toInt(value, null, -1);
		} catch (Throwable t) {
			return -1;
		}

	}

	/**
	 * @generated
	 */
	public static final void setMinAttribute(Item intModelController, int value) {
		try {
			Object setvalue = value;
			intModelController.setAttribute(WorkspaceCST.INT_MODEL_CONTROLLER_at_MIN, setvalue);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final int getDefaultValueAttribute(Item intModelController) {
		Object value = intModelController.getAttribute(WorkspaceCST.INT_MODEL_CONTROLLER_at_DEFAULT_VALUE);
		if (value == null)
			return -1;

		try {
			return Convert.toInt(value, null, -1);
		} catch (Throwable t) {
			return -1;
		}

	}

	/**
	 * @generated
	 */
	public static final void setDefaultValueAttribute(Item intModelController, int value) {
		try {
			Object setvalue = value;
			intModelController.setAttribute(WorkspaceCST.INT_MODEL_CONTROLLER_at_DEFAULT_VALUE, setvalue);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#getClassName(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String getClassName(Item uc) {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentManager(Item item) throws CadseException {
		return new MyContentItem(null, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#createCreationPages(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {
		CreationAction action = new CreationAction(theItemParent, desType, theLinkType,
				DisplayManager.MC_DEFAULT_SHORT_NAME);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "a int model controller",
				"a int model controller", 3, FieldsCore.createTextField("min", "min", new IntModelController(1, -1,
						null, null, null)), FieldsCore.createTextField("min-error", "min error message"), FieldsCore
						.createTextField("max", "max", new IntModelController(1, -1, null, null, null)), FieldsCore
						.createTextField("max-error", "max error message"), FieldsCore.createTextField("default-value",
						"default value")));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Pages createModificationPage(Item item) {
		AbstractActionPage action = new ModificationAction(item);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "a int model controller",
				"a int model controller", 3, FieldsCore.createTextField("min", "min", new IntModelController(1, -1,
						null, null, null)), FieldsCore.createTextField("min-error", "min error message"), FieldsCore
						.createTextField("max", "max", new IntModelController(1, -1, null, null, null)), FieldsCore
						.createTextField("max-error", "max error message"), FieldsCore.createTextField("default-value",
						"default value")));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateMeItem(Item itemParent, LinkType lt, ItemType destType) {
		Item field = itemParent.getPartParent();
		Item attribut = FieldManager.getAttribute(field);
		if (attribut == null)
			return "Must set the attribut link for the item " + itemParent.getId();
		if (AttributeManager.isIsListAttribute(attribut))
			return "Must be a singleton value";
		if (attribut.getType() == WorkspaceCST.INTEGER)
			return null;

		return "The type of the attribut linked at the field must be integer attribute";
	}
}

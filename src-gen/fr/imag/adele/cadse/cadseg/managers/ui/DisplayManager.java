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

import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.managers.IExtendClassManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemFactory;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.ItemFactory;
import fr.imag.adele.cadse.core.transaction.delta.ItemDelta;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.util.Convert;

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
	public Class<?> getDefaultClassName() {
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

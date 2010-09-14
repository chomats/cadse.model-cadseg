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
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.cadseg.managers.ui;

import java.util.Set;

import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.util.Convert;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class FieldManager.
 * 
 * @generated
 */
public class FieldManager extends DefaultItemManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public FieldManager() {
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


	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return "Cannot rename a build model";
	}

	/**
	 * Gets the attribute.
	 * 
	 * @param field
	 *            the field
	 * 
	 * @return the attribute
	 * 
	 * @generated
	 */
	static public Item getAttribute(Item field) {
		return field.getOutgoingItem(CadseGCST.FIELD_lt_ATTRIBUTE, true);
	}

	/**
	 * Gets the item type from field.
	 * 
	 * @param field
	 *            the field
	 * 
	 * @return the item type from field
	 */
	static public Item getItemTypeFromField(Item field) {
		return field.getPartParent(CadseGCST.ITEM_TYPE);
	}

	/**
	 * Sets the attribute.
	 * 
	 * @param field
	 *            the field
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setAttribute(Item field, Item value) throws CadseException {
		field.setOutgoingItem(CadseGCST.FIELD_lt_ATTRIBUTE,value);
	}

	/**
	 * Gets the label attribute.
	 * 
	 * @param field
	 *            the field
	 * 
	 * @return the label attribute
	 * 
	 * @generated
	 */
	public static final String getLabelAttribute(Item field) {
		return field.getAttributeWithDefaultValue(CadseGCST.FIELD_at_LABEL_, "");
	}

	/**
	 * Sets the label attribute.
	 * 
	 * @param field
	 *            the field
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setLabelAttribute(Item field, String value) {
		try {
			field.setAttribute(CadseGCST.FIELD_at_LABEL_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * get a link 'attribute' from 'Field' to 'Attribute'.
	 * 
	 * @param field
	 *            the field
	 * 
	 * @return the attribute link
	 * 
	 * @generated
	 */
	static public Link getAttributeLink(Item field) {
		return field.getOutgoingLink(CadseGCST.FIELD_lt_ATTRIBUTE);
	}

	/**
	 * get all link destination 'attribute' from 'Field' to 'Attribute'.
	 * 
	 * @param field
	 *            the field
	 * 
	 * @return the attribute all
	 * 
	 * @generated
	 */
	static public Item getAttributeAll(Item field) {
		return field.getOutgoingItem(CadseGCST.FIELD_lt_ATTRIBUTE, false);
	}

	/**
	 * Gets the position attribute.
	 * 
	 * @param field
	 *            the field
	 * 
	 * @return the position attribute
	 * 
	 * @generated
	 */
	public static final EPosLabel getPositionAttribute(Item field) {
		Object value = field.getAttribute(CadseGCST.FIELD_at_POSITION_);
		return Convert.toEnum(value,CadseGCST.FIELD_at_POSITION_,EPosLabel.defaultpos);
	}

	/**
	 * Sets the position attribute.
	 * 
	 * @param field
	 *            the field
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setPositionAttribute(Item field, EPosLabel value) {
		try {
			field.setAttribute(CadseGCST.FIELD_at_POSITION_, value);
		} catch (Throwable t) {
		}
	}

	/**
		get a link 'overwritefield' from 'Field' to 'Field'.
		@generated
	*/
	static public Link getOverwritefieldLink(Item field) {
		return field.getOutgoingLink(CadseGCST.FIELD_lt_OVERWRITEFIELD);
	}

	/**
		get all link destination 'overwritefield' from 'Field' to 'Field'.
		@generated
	*/
	static public Item getOverwritefieldAll(Item field) {
		return field.getOutgoingItem(CadseGCST.FIELD_lt_OVERWRITEFIELD, false);
	}

	/**
		get resolved link destination 'overwritefield' from 'Field' to 'Field'.
		@generated
	*/
	static public Item getOverwritefield(Item field) {
		return field.getOutgoingItem(CadseGCST.FIELD_lt_OVERWRITEFIELD, true);
	}

	/**
		set a link 'overwritefield' from 'Field' to 'Field'.
		@generated
	*/
	static public void setOverwritefield(Item field, Item value) throws CadseException {
		field.setOutgoingItem(CadseGCST.FIELD_lt_OVERWRITEFIELD,value);
	}

	/**
		@generated
	*/
	public static final boolean isEditableAttribute(Item field) {
		return field.getAttributeWithDefaultValue(CadseGCST.FIELD_at_EDITABLE_, false);
	}

	/**
		@generated
	*/
	public static final void setEditableAttribute(Item field, boolean value) {
		try {
			field.setAttribute(CadseGCST.FIELD_at_EDITABLE_, value);
		} catch (Throwable t) {

		}
	}

	/**
		get a link 'ic' from 'Field' to 'InteractionController'.
		@generated
	*/
	static public Link getIcLink(Item field) {
		return field.getOutgoingLink(CadseGCST.FIELD_lt_IC);
	}

	/**
		get all link destination 'ic' from 'Field' to 'InteractionController'.
		@generated
	*/
	static public Item getIcAll(Item field) {
		return field.getOutgoingItem(CadseGCST.FIELD_lt_IC, false);
	}

	/**
		get resolved link destination 'ic' from 'Field' to 'InteractionController'.
		@generated
	*/
	static public Item getIc(Item field) {
		return field.getOutgoingItem(CadseGCST.FIELD_lt_IC, true);
	}

	/**
		set a link 'ic' from 'Field' to 'InteractionController'.
		@generated
	*/
	static public void setIc(Item field, Item value) throws CadseException {
		field.setOutgoingItem(CadseGCST.FIELD_lt_IC,value);
	}

	/**
		get a link 'mc' from 'Field' to 'ModelController'.
		@generated
	*/
	static public Link getMcLink(Item field) {
		return field.getOutgoingLink(CadseGCST.FIELD_lt_MC);
	}

	/**
		get all link destination 'mc' from 'Field' to 'ModelController'.
		@generated
	*/
	static public Item getMcAll(Item field) {
		return field.getOutgoingItem(CadseGCST.FIELD_lt_MC, false);
	}

	/**
		get resolved link destination 'mc' from 'Field' to 'ModelController'.
		@generated
	*/
	static public Item getMc(Item field) {
		return field.getOutgoingItem(CadseGCST.FIELD_lt_MC, true);
	}

	/**
		set a link 'mc' from 'Field' to 'ModelController'.
		@generated
	*/
	static public void setMc(Item field, Item value) throws CadseException {
		field.setOutgoingItem(CadseGCST.FIELD_lt_MC,value);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateMeItem(Item itemParent, LinkType lt, ItemType destType) {
		return "Only by program";
	}

	/**
	 * Gets the field generate info.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param field
	 *            the field
	 * @param superField
	 * @param imports
	 *            the imports
	 * 
	 * @return the field generate info
	 */
	public static FieldGenerateInfo getFieldGenerateInfo(ContextVariable cxt, Item field, Item superField,
			Set<String> imports) {
		FieldGenerateInfo ret = new FieldGenerateInfo();
		ret.field = field;
//		ret.display = getDisplay(field);
//		if (ret.display == null) {
//			return null;
//		}
		ret.mc = ret.field != null ? DisplayManager.getItemMC(ret.field) : null;
		ret.ic = ret.field != null ? DisplayManager.getItemIC(ret.field) : null;
		computeUITypeName(cxt, ret, imports);
		ret.methodName = "createField" + JavaIdentifier.javaIdentifierFromString(field.getName(), true, false, null);
		ret.fieldName = "field" + JavaIdentifier.javaIdentifierFromString(field.getName(), true, false, null);
		ret.superField = superField;
		return ret;
	}

	/**
	 * Compute ui type name.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param info
	 *            the info
	 * @param imports
	 *            the imports
	 */
	private static void computeUITypeName(ContextVariable cxt, FieldGenerateInfo info, Set<String> imports) {
		info.extendsUI = DisplayManager.isExtendsUIAttribute(info.field);
		if (info.extendsUI) {
			Item page = info.field.getPartParent();
			JavaFileContentManager contentManager = (JavaFileContentManager) page.getContentItem();

			String cn = JavaIdentifier.javaIdentifierFromString(info.field.getName(), true, false,
					DisplayManager.POSTFIXE_UI);
			imports.add(contentManager.getPackageName(cxt) + "." + contentManager.getClassName(cxt) + "." + cn);
			info.uiTypeName = cn;
		} else {
			DisplayManager m = (DisplayManager) info.field.getType().getItemManager();
			Class<?> defaultQualifiedClassName = m.getDefaultClassName();
			imports.add(defaultQualifiedClassName.getName());
			info.uiTypeName = defaultQualifiedClassName.getSimpleName();
		}
	}

	/**
	 * Generate method.
	 * 
	 * @param info
	 *            the info
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 */
	public static void generateMethod(FieldGenerateInfo info, GenStringBuilder sb, Set<String> imports) {
		sb.newline();
		sb.newline().appendGeneratedTag();
		sb.newline().append("public ").append(info.uiTypeName).append(" ");
		sb.append(info.methodName).append("() {");
		sb.begin();

		if (info.field == null) {
			sb.newline().append("return null;");
		} else {
			//ContentItemImpl.generate(info.field, sb, "field", "in-field", imports, null);
		}
		sb.end();
		sb.newline().append("}");
		imports.add("fr.imag.adele.cadse.core.ui.UIField");
	}

}

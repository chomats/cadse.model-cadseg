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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import fede.workspace.eclipse.content.SubFileContentManager;
import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ExtItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.AbstractLinkTypeManager;
import fr.imag.adele.cadse.core.impl.ContentItemImpl;
import fr.imag.adele.cadse.core.key.LinksSpaceKeyType;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.util.Convert;
import java.lang.String;
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

	/**
	 * The Class FieldContentManager.
	 */
	private final class FieldContentManager extends SubFileContentManager {

		/**
		 * Instantiates a new field content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		private FieldContentManager(CompactUUID id) {
			super(id);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#init(fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public void init() {
		CadseGCST.FIELD.setHasQualifiedNameAttribute(false);
		CadseGCST.FIELD.setSpaceKeyType(new LinksSpaceKeyType(CadseGCST.FIELD, CadseGCST.PAGE,
				CadseGCST.FIELD_lt_ATTRIBUTE));

		CadseGCST.FIELD_lt_ATTRIBUTE.setManager(new AbstractLinkTypeManager() {
			@Override
			public Collection<Item> getSelectingDestination(Item field) {
				Item container = field.getPartParent().getPartParent().getPartParent();
				Item itemType = null;
				Collection<Item> allAttributes = new ArrayList<Item>();

				if (container.getType() == CadseGCST.EXT_ITEM_TYPE) {
					itemType = ExtItemTypeManager.getRefType(container);
					allAttributes.addAll(ExtItemTypeManager.getAttributes(container));
				} else if (container.getType() == CadseGCST.ITEM_TYPE) {
					itemType = container;
				}
				if (itemType != null) {
					Item[] ret = ItemTypeManager.getAllAttributes(null, itemType, null, true);
					allAttributes.addAll(Arrays.asList(ret));
				}
				return allAttributes;
			}
		});
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

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// fede.workspace.model.manager.DefaultItemManager#computeRenameAnnotationChange(org.eclipse.ltk.core.refactoring.CompositeChange,
	// * fr.imag.adele.cadse.core.Item, fr.imag.adele.cadse.core.Item,
	// * fr.imag.adele.cadse.core.var.ContextVariable,
	// * fr.imag.adele.cadse.core.var.ContextVariable)
	// */
	// @Override
	// public RefactoringStatus computeRenameAnnotationChange(CompositeChange
	// change, Item itemAnnotation,
	// Item itemAnnoted, ContextVariable newCxt, ContextVariable oldCxt) {
	// if (itemAnnoted.isInstanceOf(CadseGCST.ATTRIBUTE)) {
	// return itemAnnotation.computeRenameChange(change,
	// itemAnnoted.getName(), newCxt, oldCxt);
	// }
	// return null;
	// }

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
	 * Checks if is readonly attribute.
	 * 
	 * @param field
	 *            the field
	 * 
	 * @return true, if checks if is readonly attribute
	 * 
	 * @generated
	 */
	public static final boolean isReadonlyAttribute(Item field) {
		return field.getAttributeWithDefaultValue(CadseGCST.FIELD_at_READONLY_, false);
	}

	/**
	 * Sets the readonly attribute.
	 * 
	 * @param field
	 *            the field
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setReadonlyAttribute(Item field, boolean value) {
		try {
			field.setAttribute(CadseGCST.FIELD_at_READONLY_, value);
		} catch (Throwable t) {

		}
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
	 * Gets the display.
	 * 
	 * @param field
	 *            the field
	 * 
	 * @return the display
	 * 
	 * @generated
	 */
	static public Item getDisplay(Item field) {
		return field.getOutgoingItem(CadseGCST.FIELD_lt_DISPLAY, true);
	}

	/**
	 * Sets the display.
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
	static public void setDisplay(Item field, Item value) throws CadseException {
		field.setOutgoingItem(CadseGCST.FIELD_lt_DISPLAY,value);
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
		Object value = field.getAttribute(CadseGCST.FIELD_at_POSITION);
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
			field.setAttribute(CadseGCST.FIELD_at_POSITION, value);
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
		return field.getAttributeWithDefaultValue(CadseGCST.FIELD_at_EDITABLE_, null);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentItem(CompactUUID id) {
		return new FieldContentManager(id);
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
	 * get a link 'display' from 'Field' to 'Display'.
	 * 
	 * @param field
	 *            the field
	 * 
	 * @return the display link
	 * 
	 * @generated
	 */
	static public Link getDisplayLink(Item field) {
		return field.getOutgoingLink(CadseGCST.FIELD_lt_DISPLAY);
	}

	/**
	 * get all link destination 'display' from 'Field' to 'Display'.
	 * 
	 * @param field
	 *            the field
	 * 
	 * @return the display all
	 * 
	 * @generated
	 */
	static public Item getDisplayAll(Item field) {
		return field.getOutgoingItem(CadseGCST.FIELD_lt_DISPLAY, false);
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
		ret.display = getDisplay(field);
		if (ret.display == null) {
			return null;
		}
		ret.mc = ret.display != null ? DisplayManager.getItemMC(ret.display) : null;
		ret.ic = ret.display != null ? DisplayManager.getItemIC(ret.display) : null;
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
		info.extendsUI = DisplayManager.isExtendsUIAttribute(info.display);
		if (info.extendsUI) {
			Item page = info.field.getPartParent();
			JavaFileContentManager contentManager = (JavaFileContentManager) page.getContentItem();

			String cn = JavaIdentifier.javaIdentifierFromString(info.field.getName(), true, false,
					DisplayManager.POSTFIXE_UI);
			imports.add(contentManager.getPackageName(cxt) + "." + contentManager.getClassName(cxt) + "." + cn);
			info.uiTypeName = cn;
		} else {
			DisplayManager m = (DisplayManager) info.display.getType().getItemManager();
			String defaultQualifiedClassName = m.getDefaultClassName();
			imports.add(defaultQualifiedClassName);
			info.uiTypeName = JavaIdentifier.getlastclassName(defaultQualifiedClassName);
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

		if (info.display == null) {
			sb.newline().append("return null;");
		} else {
			ContentItemImpl.generate(info.display, sb, "field", "in-field", imports, null);
		}
		sb.end();
		sb.newline().append("}");
		imports.add("fr.imag.adele.cadse.core.ui.UIField");
		imports.add("fede.workspace.model.manager.properties.FieldsCore");
	}

}
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

package fr.imag.adele.cadse.cadseg.managers.attributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.IType;

import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.contents.attributes.EnumCIF;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseRootCST;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.attribute.EnumAttributeType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.ListAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class EnumManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class EnumManager extends AttributeManager implements IItemManager, IModelWorkspaceManager {

	/** The Constant ENUM_BAD_DEFAULT_VALUE. */
	private static final int	ENUM_BAD_DEFAULT_VALUE	= 1;

	/**
	 * Instantiates a new enum manager.
	 */
	public EnumManager() {
	}

	/**
	 * Compute unique name.
	 * 
	 * @param item
	 *            the item
	 * @param shortName
	 *            the short name
	 * @param parent
	 *            the parent
	 * @param lt
	 *            the lt
	 * 
	 * @return the string
	 * 
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
	 * Gets the display name.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the display name
	 * 
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
	 * get a link 'enum-type' from 'Enum' to 'EnumType'.
	 * 
	 * @param _enum
	 *            the _enum
	 * 
	 * @return the enum type link
	 * 
	 * @generated
	 */
	static public Link getEnumTypeLink(Item _enum) {
		return _enum.getOutgoingLink(WorkspaceCST.ENUM_lt_ENUM_TYPE);
	}

	/**
	 * get all link destination 'enum-type' from 'Enum' to 'EnumType'.
	 * 
	 * @param _enum
	 *            the _enum
	 * 
	 * @return the enum type all
	 * 
	 * @generated
	 */
	static public Item getEnumTypeAll(Item _enum) {
		return _enum.getOutgoingItem(WorkspaceCST.ENUM_lt_ENUM_TYPE, false);
	}

	@Override
	public IContentItemFactory getContentItemFactory() {
		return new EnumCIF(this);
	}

	/**
	 * Gets the enum type.
	 * 
	 * @param _enum
	 *            the _enum
	 * 
	 * @return the enum type
	 * 
	 * @generated
	 */
	static public Item getEnumType(Item _enum) {
		return _enum.getOutgoingItem(WorkspaceCST.ENUM_lt_ENUM_TYPE, true);
	}

	/**
	 * set a link 'enum-type' from 'Enum' to 'EnumType'.
	 * 
	 * @param _enum
	 *            the _enum
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setEnumType(Item _enum, Item value) throws CadseException {
		_enum.setOutgoingItem(WorkspaceCST.ENUM_lt_ENUM_TYPE, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#validate(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.IItemManager.ProblemReporter)
	 */
	@Override
	public List<Item> validate(Item item, ProblemReporter reporter) {
		super.validate(item, reporter);
		Item itemenumtype = getEnumType(item);
		if (itemenumtype == null || !itemenumtype.isResolved()) {
			return Collections.emptyList();
		}
		List<Item> ret = new ArrayList<Item>();
		ret.add(itemenumtype);
		String defaultValue = getDefaultValueAttribute(item);
		if (defaultValue != null && defaultValue.length() == 0) {
			defaultValue = null;
		}
		if (defaultValue == null) {
			reporter.error(item, ENUM_BAD_DEFAULT_VALUE, "Pas de valeur par defaut pour {0}", item.getName());
			return ret;
		}

		IType type = EnumTypeManager.getEnumQualifiedClass(ContextVariable.DEFAULT, itemenumtype);
		if (type != null && type.exists()) {
			List<String> values = EnumTypeManager.getEnumTypeValues(itemenumtype);
			if (values == null || !values.contains(defaultValue)) {
				reporter.error(item, ENUM_BAD_DEFAULT_VALUE, "Mauvaise valeur {0} pour {0}", defaultValue, item
						.getName());
				return ret;
			}
		}
		return ret;
	}

	@Override
	public ItemType getCadseRootType() {
		return CadseRootCST.ENUM_ATTRIBUTE_TYPE;
	}

	@Override
	public Object getCadseRootAttributeValue(ContextVariable cxt, IAttributeType<?> attType, Item attribute) {
		if (attType == CadseRootCST.ENUM_ATTRIBUTE_TYPE_at_ENUM_CLAZZ_) {
			Item enumType = EnumManager.getEnumType(attribute);
			IType enumQualifiedClass = null;
			if (enumType != null) {
				enumQualifiedClass = EnumTypeManager.getEnumQualifiedClass(cxt, enumType);
			}
			if (enumQualifiedClass != null) {
				return enumQualifiedClass.getFullyQualifiedName();
			}
		}
		return super.getCadseRootAttributeValue(cxt, attType, attribute);

	}

	@Override
	public void generateAttributeRefCst(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
			Set<String> imports) {

		Item enumType = EnumManager.getEnumType(attribute);
		if (enumType != null) {
			IType enumTypeClass = EnumTypeManager.getEnumQualifiedClass(ContextVariable.DEFAULT, enumType);
			if (enumTypeClass != null) {
				if (AttributeManager.isIsListAttribute(attribute)) {
					appendCST(cxt, sb, absItemType, attribute, imports, ListAttributeType.class, enumTypeClass);
				} else {
					appendCST(cxt, sb, absItemType, attribute, imports, EnumAttributeType.class, enumTypeClass);
				}
				return;
			}
		}
		if (AttributeManager.isIsListAttribute(attribute)) {
			appendCST(cxt, sb, absItemType, attribute, imports, ListAttributeType.class);
		} else {
			appendCST(cxt, sb, absItemType, attribute, imports, EnumAttributeType.class);
		}

	}

	@Override
	public Class<?> getAttributeDefinitionTypeJava() {
		return fr.imag.adele.cadse.core.attribute.EnumAttributeType.class;
	}

}

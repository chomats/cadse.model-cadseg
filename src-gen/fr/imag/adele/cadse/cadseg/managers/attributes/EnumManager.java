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
package fr.imag.adele.cadse.cadseg.managers.attributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.IType;

import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.Validator;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

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
		return _enum.getOutgoingLink(CadseGCST.ENUM_lt_ENUM_TYPE);
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
		return _enum.getOutgoingItem(CadseGCST.ENUM_lt_ENUM_TYPE, false);
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
		return _enum.getOutgoingItem(CadseGCST.ENUM_lt_ENUM_TYPE, true);
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
		_enum.setOutgoingItem(CadseGCST.ENUM_lt_ENUM_TYPE,value);
	}

	/**
		@generated
	*/
	public static final String getEnumClazzAttribute(Item _enum) {
		return _enum.getAttributeWithDefaultValue(CadseGCST.ENUM_at_ENUM_CLAZZ_, null);
	}

	/**
		@generated
	*/
	public static final void setEnumClazzAttribute(Item _enum, String value) {
		try {
			_enum.setAttribute(CadseGCST.ENUM_at_ENUM_CLAZZ_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	@SuppressWarnings("unchecked")
	public static final List<String> getValuesAttribute(Item _enum) {
		try {
			List<String> list = _enum.getAttribute(CadseGCST.ENUM_at_VALUES_);

			if (list == null)
				return null;

			return new ArrayList<String>(list);
		} catch (Throwable t) {
			return new ArrayList<String>();
		}
	}

	/**
		@generated
	*/
	@SuppressWarnings("unchecked")
	public static final void setValuesAttribute(Item _enum, List<String> valueList) {
		try {
			List<String> list = new ArrayList<String>(valueList);
			_enum.setAttribute(CadseGCST.ENUM_at_VALUES_, list);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	@SuppressWarnings("unchecked")
	public static final void addValuesAttribute(Item _enum, String value) {
		try {
			List<String> list = _enum.getAttribute(CadseGCST.ENUM_at_VALUES_);
			if (list == null) {
				list = new ArrayList<String>();
			}
			list.add(value);
			_enum.setAttribute(CadseGCST.ENUM_at_VALUES_, list);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	@SuppressWarnings("unchecked")
	public static final void removeValuesAttribute(Item _enum, String value) {
		try {

			List<String> list = _enum.getAttribute(CadseGCST.ENUM_at_VALUES_);
			if (list == null) {
				return;
			}
			list.remove(value);
			if (list.size() == 0)
				_enum.setAttribute(CadseGCST.ENUM_at_VALUES_, null);
			else
				_enum.setAttribute(CadseGCST.ENUM_at_VALUES_, list);
		} catch (Throwable t) {

		}
	}

	public static final class EnumValidator extends Validator {
		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.DefaultItemManager#validate(fr.imag.adele.cadse.core.Item,
		 *      fr.imag.adele.cadse.core.IItemManager.ProblemReporter)
		 */
		@Override
		public List<Item> validate(Item item, ProblemReporter reporter) {
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
	
			IType type = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, itemenumtype);
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
	}
}

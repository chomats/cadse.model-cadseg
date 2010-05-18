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
import java.util.List;

import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.as.initmodel.IAttributeCadsegForGenerate;
import fr.imag.adele.fede.workspace.as.initmodel.IInitModel;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ObjectFactory;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ValueTypeType;

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

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.ENUM;
	}


	@Override
	public Class<?> getAttributeDefinitionTypeJava() {
		return fr.imag.adele.cadse.core.attribute.EnumAttributeType.class;
	}
	
	@Override
	public IAttributeType<?> loadAttributeDefinition(IInitModel initModel, LogicalWorkspace theWorkspaceLogique,
			TypeDefinition parent, CValuesType type, String cadseName) throws CadseException {
		String enumTypeName = type.getTypeName();
		if (type.getElement().size() == 1) {
			CValuesType clazzElement = type.getElement().get(0);
			enumTypeName = clazzElement.getValue();
		}

		// Probleme de compilation avec javac
		Class<? extends Enum> clazz = (Class<? extends Enum>) (Class<?>) initModel.loadClass(cadseName, enumTypeName);
		if (clazz == null) {
			throw new CadseException("cannot create type from {0}", type.getKey());
		}
		return new fr.imag.adele.cadse.core.impl.attribute.EnumAttributeType(initModel.getUUID(type.getId()), initModel.getFlag(type), type.getKey(), clazz,
				type.getValue());
	}

	@Override
	public void writeAttributeDefinition(ObjectFactory factory, ContextVariable cxt,
			IAttributeCadsegForGenerate cadsegManager, CValuesType cvt, Item attribute) {
		cvt.setType(ValueTypeType.ENUMERATION);
		String enumQualifiedClass = (String) cadsegManager.getCadseRootAttributeValue(cxt,
				CadseGCST.ENUM_at_ENUM_CLAZZ_, attribute);
		if (enumQualifiedClass != null) {
			CValuesType ncvt = factory.createCValuesType();
			cvt.getElement().add(ncvt);
			ncvt.setValue(enumQualifiedClass);
			ncvt.setKey(CadseGCST.ENUM_at_ENUM_CLAZZ);
		}
		super.writeAttributeDefinition(factory, cxt, cadsegManager, cvt, attribute);
	}

}

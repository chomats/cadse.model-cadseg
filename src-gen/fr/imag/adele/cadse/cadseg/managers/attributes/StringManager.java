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

import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ObjectFactory;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ValueTypeType;
import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.util.Convert;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.as.initmodel.IAttributeCadsegForGenerate;
import fr.imag.adele.fede.workspace.as.initmodel.IInitModel;

/**
 * The Class StringManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class StringManager extends AttributeManager implements IItemManager, IModelWorkspaceManager {

	/**
	 * Instantiates a new string manager.
	 */
	public StringManager() {
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
	 * Checks if is not empty attribute.
	 * 
	 * @param string
	 *            the string
	 * 
	 * @return true, if checks if is not empty attribute
	 * 
	 * @generated
	 */
	public static final boolean isNotEmptyAttribute(Item string) {
		return string.getAttributeWithDefaultValue(CadseGCST.STRING_at_NOT_EMPTY_, false);
	}

	/**
	 * Sets the not empty attribute.
	 * 
	 * @param string
	 *            the string
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setNotEmptyAttribute(Item string, boolean value) {
		try {
			string.setAttribute(CadseGCST.STRING_at_NOT_EMPTY_, value);
		} catch (Throwable t) {

		}
	}

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.STRING;
	}

	@Override
	public int getCadseRootFlag(Item attribute) {
		return super.getCadseRootFlag(attribute) | (isNotEmptyAttribute(attribute) ? StringAttributeType.NOT_EMPTY : 0);
	}

	@Override
	public Class<? extends IAttributeType<?>> getAttributeDefinitionTypeJava() {
		return StringAttributeType.class;
	}

	@Override
	public Class<?> getTypeJava(boolean primitive) {
		return String.class;
	}

	@Override
	public String getJavaTypeDefaultValue(Item attribute) {
		String defaultValue = attribute.getAttribute(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);
		if (defaultValue == null || defaultValue.length() == 0) {
			defaultValue = generatedDefaultValue();
		}

		if (defaultValue == null || defaultValue.length() == 0) {
			return "null";
		}
		if ("".equals(defaultValue)) {
			return defaultValue;
		}
		if (defaultValue.startsWith("\"") && defaultValue.endsWith("\"")) {
			if (defaultValue.length() == 1 || defaultValue.length() == 2) {
				defaultValue = "";
			}
			else {
				defaultValue = defaultValue.substring(1, defaultValue.length() - 1);
			}
		}
		defaultValue = defaultValue.replace("\"", "\\\"");
		return "\"" + defaultValue + "\"";
	}
	


	@Override
	public IAttributeType<?> loadAttributeDefinition(IInitModel initModel, LogicalWorkspace theWorkspaceLogique,
			TypeDefinition parent, CValuesType type, String cadseName) throws CadseException {
		StringAttributeType ret = new fr.imag.adele.cadse.core.impl.attribute.StringAttributeType(initModel.getUUID(type.getId()), initModel.getFlag(type),
				type.getKey(), type.getValue());
		return ret;
	}

	@Override
	public void writeAttributeDefinition(ObjectFactory factory, ContextVariable cxt,
			IAttributeCadsegForGenerate cadsegManager, CValuesType cvt, Item attribute) {
		cvt.setType(ValueTypeType.STRING);
		super.writeAttributeDefinition(factory, cxt, cadsegManager, cvt, attribute);
	}
}

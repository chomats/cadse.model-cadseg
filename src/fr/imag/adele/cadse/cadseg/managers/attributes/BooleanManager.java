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

import fede.workspace.tool.loadmodel.model.jaxb.CValuesType;
import fede.workspace.tool.loadmodel.model.jaxb.ObjectFactory;
import fede.workspace.tool.loadmodel.model.jaxb.ValueTypeType;
import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.attribute.BooleanAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.as.initmodel.IAttributeCadsegForGenerate;
import fr.imag.adele.fede.workspace.as.initmodel.IInitModel;

/**
 * The Class BooleanManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class BooleanManager extends AttributeManager implements IItemManager, IModelWorkspaceManager {

	/**
	 * Instantiates a new boolean manager.
	 */
	public BooleanManager() {
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

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.BOOLEAN;
	}

	@Override
	public String getJavaTypeConvertToMethod() {
		return "fr.imag.adele.cadse.core.util.Convert.toBoolean";
	}

	@Override
	public Class<? extends IAttributeType<?>> getAttributeDefinitionTypeJava() {
		return fr.imag.adele.cadse.core.attribute.BooleanAttributeType.class;
	}

	@Override
	public Class<?> getTypeJava(boolean primitive) {
		return primitive ? Boolean.TYPE : Boolean.class;
	}
	
	@Override
	public IAttributeType<?> loadAttributeDefinition(IInitModel initModel, LogicalWorkspace theWorkspaceLogique,
			ItemType parent, CValuesType type, String cadseName) {
		BooleanAttributeType ret = new BooleanAttributeType(initModel.getUUID(type.getId()), initModel.getFlag(type),
				type.getKey(), type.getValue());
		return ret;
	}

	@Override
	public void writeAttributeDefinition(ObjectFactory factory, ContextVariable cxt,
			IAttributeCadsegForGenerate cadsegManager, CValuesType cvt, Item attribute) {
		cvt.setType(ValueTypeType.BOOLEAN);
		super.writeAttributeDefinition(factory, cxt, cadsegManager, cvt, attribute);
	}
}

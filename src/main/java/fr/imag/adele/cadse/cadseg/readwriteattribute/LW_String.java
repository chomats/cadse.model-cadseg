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
package fr.imag.adele.cadse.cadseg.readwriteattribute;

import fr.imag.adele.cadse.cadseg.managers.attributes.StringManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.as.initmodel.IInitModel;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ObjectFactory;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ValueTypeType;

/**
 * The Class StringManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class LW_String extends LW_Attribute {

	@Override
	public IAttributeType<?> loadAttributeDefinition(IInitModel initModel, LogicalWorkspace theWorkspaceLogique,
			TypeDefinition parent, CValuesType type, String cadseName) throws CadseException {
		StringAttributeType ret = new fr.imag.adele.cadse.core.impl.attribute.StringAttributeType(initModel.getUUID(type.getId()), initModel.getFlag(type),
				type.getKey(), type.getValue());
		return ret;
	}

	@Override
	public void writeAttributeDefinition(ObjectFactory factory, ContextVariable cxt,
			CValuesType cvt, Item attribute) {
		cvt.setType(ValueTypeType.STRING);
		super.writeAttributeDefinition(factory, cxt, cvt, attribute);
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
	public int getCadseRootFlag(Item attribute) {
		return super.getCadseRootFlag(attribute) | 
		 (StringManager.isNotEmptyAttribute(attribute) ? Item.NOT_EMPTY : 0);
	}


}

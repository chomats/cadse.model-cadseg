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


import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.ListAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.as.initmodel.IInitModel;
import fr.imag.adele.fede.workspace.as.initmodel.InitModelLoadAndWrite;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ObjectFactory;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ValueTypeType;



/**
    @generated
*/
public class LW_List extends LW_Attribute {

	@Override
	public IAttributeType<?> loadAttributeDefinition(IInitModel initModel, LogicalWorkspace theWorkspaceLogique,
			TypeDefinition parent, CValuesType type, String cadseName) throws CadseException {
		
		List<CValuesType> elements = type.getElement();
		if (elements == null || elements.size() != 1) {
			throw new CadseException(
					"cannot create value from {0} : bad definition of list",
					type.getKey());
		}		
		
		ListAttributeType<?> ret = new fr.imag.adele.cadse.core.impl.attribute.ListAttributeType(
			initModel.getUUID(type.getId()), 
				initModel.getFlag(type),
				type.getKey(), 
				initModel.getMin(type), 
				initModel.getMax(type), initModel.createAttrituteType(theWorkspaceLogique, null, elements
						.get(0), cadseName));
		
		
		return ret;
	}

	@Override
	public void writeAttributeDefinition(ObjectFactory factory, ContextVariable cxt,
			CValuesType cvt, Item attribute) {
		// cvt.setType(ValueTypeType.UUID);
		super.writeAttributeDefinition(factory, cxt, cvt, attribute);
		cvt.setType(ValueTypeType.LIST);
		cvt.setMax(-1);
		cvt.setMin(0);
		
		ListAttributeType<?> listAttr = (ListAttributeType<?>) attribute;
		IAttributeType<?> eltAttr = listAttr.getSubAttributeType();
		if (eltAttr != null) {
			CValuesType ncvt = factory.createCValuesType();
			cvt.getElement().add(ncvt);

			cvt = ncvt;
			cvt.setKey("Element of "+attribute.getName());
			cvt.setTypeName(eltAttr.getType().getId().toString());
			Object defaultValue = eltAttr.getDefaultValue();
			cvt.setValue(defaultValue == null ? null : defaultValue.toString());
			InitModelLoadAndWrite manager = eltAttr.getType().adapt(InitModelLoadAndWrite.class);
			if (manager != null) {
				manager.writeAttributeDefinition(factory, cxt, ncvt, eltAttr);
			}
		}
	}
	
	@Override
	public Class<?> getAttributeDefinitionTypeJava() {
		return ListAttributeType.class;
	}

	@Override
	public Class<?> getTypeJava(boolean primitive) {
		return List.class;
	}


}
/** Licensed to the Apache Software Foundation (ASF) under one* or more contributor license agreements.  See the NOTICE file* distributed with this work for additional information* regarding copyright ownership.  The ASF licenses this file* to you under the Apache License, Version 2.0 (the* "License"); you may not use this file except in compliance* with the License.  You may obtain a copy of the License at**   http://www.apache.org/licenses/LICENSE-2.0** Unless required by applicable law or agreed to in writing,* software distributed under the License is distributed on an* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY* KIND, either express or implied.  See the License for the* specific language governing permissions and limitations* under the License.*/


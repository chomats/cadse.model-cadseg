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

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IType;

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;
import fr.imag.adele.fede.workspace.as.initmodel.IInitModel;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ObjectFactory;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ValueTypeType;

/**
 * The Class EnumManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class LW_Enum extends LW_Attribute {

	
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
			CValuesType cvt, Item attribute) {
		super.writeAttributeDefinition(factory, cxt, cvt, attribute);
		cvt.setType(ValueTypeType.ENUMERATION);
		String enumQualifiedClass = null;
		Item enumType = EnumManager.getEnumType(attribute);
		if (enumType != null) {
			IType enumTypeClass = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, enumType);
			enumQualifiedClass = enumTypeClass == null ? null : enumTypeClass.getFullyQualifiedName();
		}
		if (enumQualifiedClass != null) {
			CValuesType ncvt = factory.createCValuesType();
			cvt.getElement().add(ncvt);
			ncvt.setValue(enumQualifiedClass);
			ncvt.setKey(CadseGCST.ENUM_at_ENUM_CLAZZ);
		}
	}
	
	@Override
	public Class<?> getAttributeDefinitionTypeJava() {
		return fr.imag.adele.cadse.core.attribute.EnumAttributeType.class;
	}

}

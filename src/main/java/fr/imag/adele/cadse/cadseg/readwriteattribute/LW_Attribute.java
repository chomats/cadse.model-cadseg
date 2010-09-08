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

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.as.initmodel.IInitModel;
import fr.imag.adele.fede.workspace.as.initmodel.InitModelLoadAndWrite;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ObjectFactory;

/**
 * The Class AttributeManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class LW_Attribute extends InitModelLoadAndWrite {

	static public void init() {
		CadseGCST.ATTRIBUTE.addAdapter(new LW_Attribute());
		CadseGCST.BOOLEAN.addAdapter(new LW_Boolean());
		CadseGCST.DATE.addAdapter(new LW_Date());
		CadseGCST.DOUBLE.addAdapter(new LW_Double());
		CadseGCST.ENUM.addAdapter(new LW_Enum());
		CadseGCST.INTEGER.addAdapter(new LW_Integer());
		CadseGCST.LINK_TYPE.addAdapter(new LW_LinkType());
		CadseGCST.LONG.addAdapter(new LW_Long());
		CadseGCST.STRING.addAdapter(new LW_String());
	}

	@Override
	public IAttributeType<?> loadAttributeDefinition(IInitModel arg0,
			LogicalWorkspace arg1, TypeDefinition arg2, CValuesType arg3,
			String arg4) throws CadseException {
		return null;
	}

	
//
//	public void addCompleteAttributeDefinition(CAttType attType) {
//	}
//
//	public void generateAttributeRefCst(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
//			Set<String> imports) {
//		Class<?> cl = getAttributeDefinitionTypeJava();
//		if (cl != null) {
//			if (AttributeManager.isIsListAttribute(attribute)) {
//				appendCST2(cxt, sb, absItemType, attribute, imports, ListAttributeType.class, getTypeJava(false));
//			}
//			else {
//				appendCST(cxt, sb, absItemType, attribute, imports, cl);
//			}
//		}
//	}
//
//	protected void appendCST(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
//			Set<String> imports, Class<?> cl, IType... params) {
//		sb.appendGeneratedTag();
//		sb.newline().append("public static ");
//		sb.append(cl.getSimpleName());
//		if (cl.getTypeParameters().length != 0) {
//			sb.append("<");
//			if (params.length == 0) {
//				sb.append("?");
//			}
//			else {
//				for (int i = 0; i < params.length; i++) {
//					IType p = params[i];
//					if (i != 0) {
//						sb.append(",");
//					}
//					sb.append(p.getElementName());
//					imports.add(p.getFullyQualifiedName());
//				}
//			}
//			sb.append(">");
//		}
//
//		sb.append(" ").append(GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append("_;");
//		imports.add(cl.getName());
//	}
//
//	protected void appendCST(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
//			Set<String> imports, Class<?> cl, Class<?> cl2, IType... params) {
//		sb.appendGeneratedTag();
//		sb.newline().append("public static ");
//		sb.append(cl.getSimpleName());
//		if (cl.getTypeParameters().length != 0) {
//			sb.append("<");
//			sb.append(cl2.getSimpleName());
//
//			if (cl2.getTypeParameters().length != 0) {
//				sb.append("<");
//				if (params.length == 0) {
//					sb.append("?");
//				}
//				else {
//					for (int i = 0; i < params.length; i++) {
//						IType p = params[i];
//						if (i != 0) {
//							sb.append(",");
//						}
//						sb.append(p.getElementName());
//						imports.add(p.getFullyQualifiedName());
//					}
//				}
//				sb.append(">");
//			}
//
//			sb.append(">");
//		}
//
//		sb.append(" ").append(GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append("_;");
//		imports.add(cl.getName());
//	}
//
//	protected void appendCST2(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
//			Set<String> imports, Class<?> cl, Class<?>... params) {
//		sb.appendGeneratedTag();
//		sb.newline().append("public static ");
//		sb.append(cl.getSimpleName());
//		if (cl.getTypeParameters().length != 0) {
//			sb.append("<");
//			if (params.length == 0) {
//				sb.append("?");
//			}
//			else {
//				for (int i = 0; i < params.length; i++) {
//					Class<?> p = params[i];
//					if (i != 0) {
//						sb.append(",");
//					}
//					sb.append(p.getSimpleName());
//					imports.add(p.getName());
//				}
//			}
//			sb.append(">");
//		}
//
//		sb.append(" ").append(GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append("_;");
//		imports.add(cl.getName());
//	}

	public Class<?> getTypeJava(boolean primitive) {
		return Object.class;
	}

	public Class<?> getAttributeDefinitionTypeJava() {
		return fr.imag.adele.cadse.core.impl.attribute.AttributeType.class;
	}

	public int getCadseRootFlag(Item attribute) {
		return (fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager.isShowInDefaultCpAttribute(attribute) ? Item.SHOW_IN_DEFAULT_CP : 0)
				| (fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager.isTransientAttribute(attribute) ? Item.TRANSIENT : 0)
				| (!fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager.isCannotBeUndefinedAttribute(attribute) ? Item.CAN_BE_UNDEFINED : 0)
				| (fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager.isShowInDefaultMpAttribute(attribute) ? Item.SHOW_IN_DEFAULT_MP : 0);
	}

	public Object getCadseRootAttributeValue(ContextVariable cxt, IAttributeType<?> attType, Item attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCadseRootRequireAttribute(Item attribute) {
		return fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager.isRequireAttribute(attribute);
	}

	public String getJavaTypeDefaultValue(Item attribute) {
		String defaultValue = attribute.getAttribute(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);
		if (defaultValue == null || defaultValue.length() == 0) {
			defaultValue = generatedDefaultValue();
		}

		if (defaultValue == null || defaultValue.length() == 0) {
			defaultValue = "null";
		}
		return defaultValue;
	}

	protected String generatedDefaultValue() {
		return null;
	}
//
//	public String getJavaTypeConvertFromMethod() {
//		return null;
//	}
//
//	public String getJavaTypeConvertToMethod() {
//		return null;
//	}
//
//	public String exp_to_string() {
//		return null;
//	}
//
//	
//
//	/*
//	 * (non-Javadoc)
//	 * @see
//	 * fr.imag.adele.cadse.core.root.managers.attribute.InitModelLoadAndWrite#loadAttributeDefinition(fr.imag.adele.
//	 * fede.workspace.as.initmodel.IInitModel, fr.imag.adele.cadse.core.IWorkspaceLogique,
//	 * fr.imag.adele.cadse.core.ItemType, fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType, java.lang.String)
//	 */
//	@Override
//	public IAttributeType<?> loadAttributeDefinition(IInitModel initModel, LogicalWorkspace theWorkspaceLogique,
//			TypeDefinition parent, CValuesType type, String cadseName) throws CadseException {
//		return null;
//	}
//
	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.core.root.managers.attribute.InitModelLoadAndWrite#writeAttributeDefinition(fr.imag.adele
	 * .fede.workspace.as.initmodel.jaxb.ObjectFactory, fr.imag.adele.cadse.core.var.ContextVariable,
	 * fr.imag.adele.cadse.core.root.managers.attribute.IAttributeCadsegForGenerate,
	 * fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType, fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public void writeAttributeDefinition(ObjectFactory factory, ContextVariable cxt,
			CValuesType cvt, Item attribute) {
		
		
		cvt.setTypeName(attribute.getType().getId().toString());
		cvt.setMin(isCadseRootRequireAttribute(attribute) ? 1 : 0);
		if (fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager.isIsListAttribute(attribute)) {
			if (attribute.getType() == CadseGCST.LIST)
				cvt.setFlag(getCadseRootFlag(attribute) & (Item.SHOW_IN_DEFAULT_CP | Item.SHOW_IN_DEFAULT_MP));
			else
				cvt.setFlag(getCadseRootFlag(attribute) & ~Item.CAN_BE_UNDEFINED);
		}
		else
			cvt.setFlag(getCadseRootFlag(attribute));
	}

}

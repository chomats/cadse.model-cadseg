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
package fr.imag.adele.cadse.cadseg.managers.dataModel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class EnumTypeManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 * @extends DefaultWorkspaceManager
 */
public class EnumTypeManager extends DefaultWorkspaceManager implements IItemManager {

	/** The Constant VALUES_ATTRIBUTE. */
	// public static final String VALUES_ATTRIBUTE = "values";
	/** The Constant JAVA_CLASS. */
	// public static final String JAVA_CLASS =
	// CadseGCST.ENUM_TYPE_at_JAVA_CLASS_"JavaClass";
	/** The _ thi s_. */
	private static EnumTypeManager	_THIS_;

	/**
	 * Instantiates a new enum type manager.
	 */
	public EnumTypeManager() {
		_THIS_ = this;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#createCreationPages(fr
	 * .imag.adele.cadse.core.Item, fr.imag.adele.cadse.core.LinkType,
	 * fr.imag.adele.cadse.core.ItemType)
	 */
	// @Override
	// public Pages createCreationPages(Item theItemParent, LinkType
	// theLinkType, ItemType desType) {
	// return CadseDefinitionManager.createWizardEnumType(theItemParent,
	// desType, theLinkType);
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#createModificationPage
	 * (fr.imag.adele.cadse.core.Item)
	 */
	// @Override
	// public Pages createModificationPage(Item item) {
	// return CadseDefinitionManager.createPropertyEnumType(item);
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.DefaultWorkspaceManager#getDisplayName(fr.imag
	 * .adele.cadse.core.Item)
	 */
	@Override
	public String getDisplayName(Item item) {
		return item.getName();
	}

	/**
	 * @generated
	 */
	public static final boolean isMustBeGeneratedAttribute(Item enumType) {
		return enumType.getAttributeWithDefaultValue(CadseGCST.ENUM_TYPE_at_MUST_BE_GENERATED_, true);
	}

	/**
	 * @generated
	 */
	public static final void setMustBeGeneratedAttribute(Item enumType, boolean value) {
		try {
			enumType.setAttribute(CadseGCST.ENUM_TYPE_at_MUST_BE_GENERATED_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final String getJavaClassAttribute(Item enumType) {
		return enumType.getAttributeWithDefaultValue(CadseGCST.ENUM_TYPE_at_JAVA_CLASS_, null);
	}

	/**
	 * @generated
	 */
	public static final void setJavaClassAttribute(Item enumType, String value) {
		try {
			enumType.setAttribute(CadseGCST.ENUM_TYPE_at_JAVA_CLASS_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final List<String> getValuesAttribute(Item enumType) {
		try {
			List<String> list = enumType.getAttribute(CadseGCST.ENUM_TYPE_at_VALUES_);

			if (list == null)
				return null;

			return new ArrayList<String>(list);
		} catch (Throwable t) {
			return new ArrayList<String>();
		}
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void setValuesAttribute(Item enumType, List<String> valueList) {
		try {
			List<String> list = new ArrayList<String>(valueList);
			enumType.setAttribute(CadseGCST.ENUM_TYPE_at_VALUES_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void addValuesAttribute(Item enumType, String value) {
		try {
			List<String> list = enumType.getAttribute(CadseGCST.ENUM_TYPE_at_VALUES_);
			if (list == null) {
				list = new ArrayList<String>();
			}
			list.add(value);
			enumType.setAttribute(CadseGCST.ENUM_TYPE_at_VALUES_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void removeValuesAttribute(Item enumType, String value) {
		try {

			List<String> list = enumType.getAttribute(CadseGCST.ENUM_TYPE_at_VALUES_);
			if (list == null) {
				return;
			}
			list.remove(value);
			if (list.size() == 0)
				enumType.setAttribute(CadseGCST.ENUM_TYPE_at_VALUES_, null);
			else
				enumType.setAttribute(CadseGCST.ENUM_TYPE_at_VALUES_, list);
		} catch (Throwable t) {

		}
	}

	

	

	

	/**
	 * Gets the package.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param enumType
	 *            the enum type
	 * 
	 * @return the package
	 */
	public String getPackage(ContextVariable cxt, Item enumType) {
		Item model = getWorkspaceModel(enumType);
		String packageName = CadseDefinitionManager.getDefaultPackage(cxt, model) + ".type";
		return packageName;
	}

	/**
	 * Gets the package path.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param enumType
	 *            the enum type
	 * 
	 * @return the package path
	 */
	public IPath getPackagePath(ContextVariable cxt, Item enumType) {
		Item model = getWorkspaceModel(enumType);
		return CadseDefinitionManager.getDefaultPackagePath(cxt, model).append("type");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.DefaultWorkspaceManager#getWorkspaceModel(fr
	 * .imag.adele.cadse.core.Item)
	 */
	@Override
	public Item getWorkspaceModel(Item theItemType) {
		while (theItemType != null) {
			if (theItemType.getType() == CadseGCST.CADSE_DEFINITION) {
				return theItemType;
			}
			theItemType = theItemType.getPartParent();

		}
		return null;
	}

	/**
	 * Gets the classname.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param enumType
	 *            the enum type
	 * 
	 * @return the classname
	 */
	public String getClassname(ContextVariable cxt, Item enumType) {
		return JavaIdentifier.javaIdentifierFromString(cxt.getName(enumType), true, false, null);
	}
	/**
	 * Gets the enum file.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param enumType
	 *            the enum type
	 * 
	 * @return the enum file
	 */
	public IFile getEnumFile(ContextVariable cxt, Item enumType) {
		String className = getClassname(cxt, enumType);
		Item model = getWorkspaceModel(enumType);

		IProject p = model.getMainMappingContent(IProject.class);
		return p.getFolder(getPackagePath(cxt, enumType)).getFile(className + ".java");
	}

	/**
	 * Gets the enum qualified class.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param enumType
	 *            the enum type
	 * 
	 * @return the enum qualified class
	 */
	public static IType getEnumQualifiedClass(ContextVariable cxt, Item enumType) {
		IType ret = getSelectedEnumQualifiedClass(enumType, true);
		if (ret != null) {
			return ret;
		}
		if (isMustBeGeneratedAttribute(enumType)) {
			ICompilationUnit cu = (ICompilationUnit) JavaCore.create(_THIS_.getEnumFile(cxt, enumType));

			return cu.getType(_THIS_.getClassname(cxt, enumType));
		}
		return null;
	}

	public static IType getSelectedEnumQualifiedClass(Item enumType, boolean attemptToResolve) {
		String typeStr = enumType.getAttribute(CadseGCST.ENUM_TYPE_at_JAVA_CLASS_);
		IType ret = null;
		if (typeStr != null && typeStr.length() != 0) {
			ret = (IType) JavaCore.create(typeStr);
		}
		if (ret != null && attemptToResolve) {
			IJavaProject jp = ret.getJavaProject();
			try {
				// bug le type peut etre dans un type et avoir ete mis � jour et
				// donc pointer sur un ancien jar...
				// il faut le rechercher
				IType ret2 = jp.findType(ret.getFullyQualifiedName());
				if (ret2 != null) {
					ret = ret2;
				}
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ret == null && typeStr != null && typeStr.length() > 0) {
			Item cadseDef = enumType.getPartParent(CadseGCST.CADSE_DEFINITION);
			IJavaProject jp = cadseDef.getMainMappingContent(IJavaProject.class);
			try {
				final IType findType = jp.findType(typeStr);
				if (findType != null) {
					enumType.setAttribute(CadseGCST.ENUM_TYPE_at_JAVA_CLASS_, findType.getHandleIdentifier());
				}
				return findType;
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * Gets the enum type values.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param enumType
	 *            the enum type
	 * 
	 * @return the enum type values
	 */
	public static List<String> getEnumTypeValues(Item enumType) {
		List<String> values = new ArrayList<String>();
		if (enumType != null && enumType.isResolved()) {
			if (!isMustBeGeneratedAttribute(enumType)) {
				IType type = getSelectedEnumQualifiedClass(enumType, true);
				if (type != null) {
					try {
						IField[] fields = type.getFields();
						for (IField field : fields) {
							if (field.getElementName().equals("ENUM$VALUES") || field.getElementName().equals("$VALUES")) {
								continue;
							}
							values.add(field.getElementName());
						}
						return values;
					} catch (JavaModelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} else {
				values = getValuesAttribute(enumType);
			}
		}
		if (values == null) {
			return new ArrayList<String>();
		}
		return values;
	}

	

}

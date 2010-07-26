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

package fr.imag.adele.cadse.cadseg.managers.build.exporter;

import java.util.ArrayList;
import java.util.List;

import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.IExtendClassManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.util.Convert;

/**
 * The Class ExporterManager.
 * 
 * @generated
 */
public class ExporterManager extends DefaultItemManager implements
		IExtendClassManager {

	

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ExporterManager() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	public String computeQualifiedName(Item item, String name, Item parent,
			LinkType lt) {
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
	 * Gets the types attribute.
	 * 
	 * @param exporter
	 *            the exporter
	 * 
	 * @return the types attribute
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final List<String> getTypesAttribute(Item exporter) {
		try {
			List<String> list = exporter.getAttribute(CadseGCST.EXPORTER_at_TYPES_);

			if (list == null)
				return null;

			return new ArrayList<String>(list);
		} catch (Throwable t) {
			return new ArrayList<String>();
		}
	}

	/**
	 * Sets the types attribute.
	 * 
	 * @param exporter
	 *            the exporter
	 * @param valueList
	 *            the value list
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void setTypesAttribute(Item exporter,
			List<String> valueList) {
		try {
			List<String> list = new ArrayList<String>(valueList);
			exporter.setAttribute(CadseGCST.EXPORTER_at_TYPES_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * Adds the types attribute.
	 * 
	 * @param exporter
	 *            the exporter
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void addTypesAttribute(Item exporter, String value) {
		try {
			List<String> list = exporter.getAttribute(CadseGCST.EXPORTER_at_TYPES_);
			if (list == null) {
				list = new ArrayList<String>();
			}
			list.add(value);
			exporter.setAttribute(CadseGCST.EXPORTER_at_TYPES_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * Removes the types attribute.
	 * 
	 * @param exporter
	 *            the exporter
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void removeTypesAttribute(Item exporter, String value) {
		try {

			List<String> list = exporter.getAttribute(CadseGCST.EXPORTER_at_TYPES_);
			if (list == null) {
				return;
			}
			list.remove(value);
			if (list.size() == 0)
				exporter.setAttribute(CadseGCST.EXPORTER_at_TYPES_, null);
			else
				exporter.setAttribute(CadseGCST.EXPORTER_at_TYPES_, list);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.IExtendClassManager#mustBeExtended()
	 */
	public boolean mustBeExtended() {
		return true;
	}

	/**
	 * Checks if is extends class.
	 * 
	 * @param contentmodel
	 *            the contentmodel
	 * 
	 * @return true, if is extends class
	 */
	public static final boolean isExtendsClass(Item contentmodel) {
		Object value = contentmodel.getAttribute(CadseGCST.RUNTIME_ITEM_at_EXTENDS_CLASS_);
		if (value == null) {
			return false;
		}

		try {
			return Convert.toBooleanFalseIfNull(value);
		} catch (Throwable t) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#getDisplayName(fr.imag
	 * .adele.cadse.core.Item)
	 */
	@Override
	public String getDisplayName(Item item) {
		return item.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.IExtendClassManager#getClassName(fr
	 * .imag.adele.cadse.core.Item)
	 */
	public String getClassName(Item uc) {
		return (mustBeExtended() || isExtendsClass(uc)) ? JavaIdentifier
				.javaIdentifierFromString(uc.getName(), true, false, "Exporter")
				: getDefaultClassName();
	}

	/** The Constant DEFAUL_CLASS_NAME. */
	public static final String DEFAUL_CLASS_NAME = "fr.imag.adele.cadse.core.build.Exporter";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.IExtendClassManager#getDefaultClassName
	 * ()
	 */
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.IModelWorkspaceManager#getWorkspaceModel(fr
	 * .imag.adele.cadse.core.Item)
	 */
	public Item getWorkspaceModel(Item source) {
		// TODO Auto-generated method stub
		return null;
	}
}

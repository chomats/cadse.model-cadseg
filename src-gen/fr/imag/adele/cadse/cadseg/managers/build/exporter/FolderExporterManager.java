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

import java.util.List;
import java.util.Set;
import java.util.UUID;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
 * The Class FolderExporterManager.
 * 
 * @generated
 */
public class FolderExporterManager extends EclipseExporterManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public FolderExporterManager() {
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
	 * Gets the path attribute.
	 * 
	 * @param folderExporter
	 *            the folder exporter
	 * 
	 * @return the path attribute
	 * 
	 * @generated
	 */
	public static final String getPathAttribute(Item folderExporter) {
		return folderExporter.getAttributeWithDefaultValue(CadseGCST.FOLDER_EXPORTER_at_PATH_, null);
	}

	/**
	 * Sets the path attribute.
	 * 
	 * @param folderExporter
	 *            the folder exporter
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setPathAttribute(Item folderExporter, String value) {
		try {
			folderExporter.setAttribute(CadseGCST.FOLDER_EXPORTER_at_PATH_, value);
		} catch (Throwable t) {

		}
	}

	/** The Constant DEFAUL_CLASS_NAME. */
	@SuppressWarnings("hiding")
	public static final String DEFAUL_CLASS_NAME = "fede.workspace.eclipse.exporter.FolderExporter";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.exporter.EclipseExporterManager
	 * #getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seemodel.workspace.workspace.managers.build.exporter.ExporterManager#
	 * mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

}

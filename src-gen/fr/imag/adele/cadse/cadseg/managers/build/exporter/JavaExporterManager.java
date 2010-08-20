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

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class JavaExporterManager.
 * 
 * @generated
 */
public class JavaExporterManager extends EclipseExporterManager {

	/** The Constant DEFAULT_EXPORTER_SHORT_NAME. */
	private static final String DEFAULT_EXPORTER_SHORT_NAME = "java-exporter";

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public JavaExporterManager() {
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

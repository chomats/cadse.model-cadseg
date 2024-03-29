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
package fr.imag.adele.cadse.cadseg.managers.content;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class JavaFileContentModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class JavaFileContentModelManager extends FileContentModelManager {
	
	/**
	 * Instantiates a new java file content model manager.
	 */
	public JavaFileContentModelManager() {
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
		@generated
	*/
	public static final String getPackageNameAttribute(Item javaFileContentModel) {
		return javaFileContentModel.getAttributeWithDefaultValue(CadseGCST.JAVA_FILE_CONTENT_MODEL_at_PACKAGE_NAME_, null);
	}

	/**
		@generated
	*/
	public static final void setPackageNameAttribute(Item javaFileContentModel, String value) {
		try {
			javaFileContentModel.setAttribute(CadseGCST.JAVA_FILE_CONTENT_MODEL_at_PACKAGE_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getClassNameAttribute(Item javaFileContentModel) {
		return javaFileContentModel.getAttributeWithDefaultValue(CadseGCST.JAVA_FILE_CONTENT_MODEL_at_CLASS_NAME_, null);
	}

	/**
		@generated
	*/
	public static final void setClassNameAttribute(Item javaFileContentModel, String value) {
		try {
			javaFileContentModel.setAttribute(CadseGCST.JAVA_FILE_CONTENT_MODEL_at_CLASS_NAME_, value);
		} catch (Throwable t) {

		}
	}
}

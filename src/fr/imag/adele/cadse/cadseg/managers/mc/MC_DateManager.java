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
package fr.imag.adele.cadse.cadseg.managers.mc;

import java.util.Set;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;

/**
 * @generated
 */
public class MC_DateManager extends ModelControllerManager {

	/**
	 * @generated
	 */
	public MC_DateManager() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	public String computeUniqueName(Item item, String shortName, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(shortName);
			return sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * @generated
	 */
	@Override
	public String getDisplayName(Item item) {
		try {
			Object value;
			Item currentItem;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * @generated
	 */
	public static final String getPatternAttribute(Item mC_Date) {
		return mC_Date.getAttributeWithDefaultValue(WorkspaceCST.MC_DATE_at_PATTERN_, "");
	}

	/**
	 * @generated
	 */
	public static final void setPatternAttribute(Item mC_Date, String value) {
		try {
			mC_Date.setAttribute(WorkspaceCST.MC_DATE_at_PATTERN_, value);
		} catch (Throwable t) {

		}
	}

	/** The Constant DEFAUL_CLASS_NAME. */
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.mc.MC_Date";

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	@Override
	protected void generateCallArguments(Item item, GenStringBuilder sb, Set<String> imports, Object object) {
		sb.append(getPatternAttribute(item)).append(", ");
	}

}

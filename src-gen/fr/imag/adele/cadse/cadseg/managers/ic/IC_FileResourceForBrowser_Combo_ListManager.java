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

package fr.imag.adele.cadse.cadseg.managers.ic;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class IC_FileResourceForBrowser_Combo_ListManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_FileResourceForBrowser_Combo_ListManager extends IC_ResourceTreeDialogForBrowser_Combo_ListManager
		implements IItemManager {

	

	/** The Constant DEFAUL_CLASS_NAME. */
	@SuppressWarnings("hiding")
	public static final String	DEFAUL_CLASS_NAME				= "fede.workspace.model.manager.properties.impl.ic.IC_FileResourceForBrowser_Combo_List";

	/**
	 * Instantiates a new i c_ file resource for browser_ combo_ list manager.
	 */
	public IC_FileResourceForBrowser_Combo_ListManager() {
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
		@generated
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
	public static final String getSelectFolderAttribute(Item iC_FileResourceForBrowser_Combo_List) {
		return iC_FileResourceForBrowser_Combo_List.getAttributeWithDefaultValue(CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST_at_SELECT_FOLDER_, null);
	}

	/**
		@generated
	*/
	public static final void setSelectFolderAttribute(Item iC_FileResourceForBrowser_Combo_List, String value) {
		try {
			iC_FileResourceForBrowser_Combo_List.setAttribute(CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST_at_SELECT_FOLDER_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getPatternSelectFileAttribute(Item iC_FileResourceForBrowser_Combo_List) {
		return iC_FileResourceForBrowser_Combo_List.getAttributeWithDefaultValue(CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST_at_PATTERN_SELECT_FILE_, null);
	}

	/**
		@generated
	*/
	public static final void setPatternSelectFileAttribute(Item iC_FileResourceForBrowser_Combo_List, String value) {
		try {
			iC_FileResourceForBrowser_Combo_List.setAttribute(CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST_at_PATTERN_SELECT_FILE_, value);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

}

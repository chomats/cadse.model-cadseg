package fr.imag.adele.cadse.cadseg.managers.ic;


import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.util.Convert;
import java.lang.String;



/**
    @generated
*/
public class IC_WithTitleForDialogManager extends InteractionControllerManager {

	/**
	    @generated
	*/
	public IC_WithTitleForDialogManager() {
		super();
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
			if (parent != null) {
				value = ItemManager.getQualifiedNameAttribute(parent);
				sb.append(
				String.valueOf(value));
			}
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
	public static final String getSelectTitleAttribute(Item iC_WithTitleForDialog) {
		return iC_WithTitleForDialog.getAttributeWithDefaultValue(CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, null);
	}

	/**
		@generated
	*/
	public static final void setSelectTitleAttribute(Item iC_WithTitleForDialog, String value) {
		try {
			iC_WithTitleForDialog.setAttribute(CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getSelectMessageAttribute(Item iC_WithTitleForDialog) {
		return iC_WithTitleForDialog.getAttributeWithDefaultValue(CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, null);
	}

	/**
		@generated
	*/
	public static final void setSelectMessageAttribute(Item iC_WithTitleForDialog, String value) {
		try {
			iC_WithTitleForDialog.setAttribute(CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, value);
		} catch (Throwable t) {

		}
	}

}
/** Licensed to the Apache Software Foundation (ASF) under one* or more contributor license agreements.  See the NOTICE file* distributed with this work for additional information* regarding copyright ownership.  The ASF licenses this file* to you under the Apache License, Version 2.0 (the* "License"); you may not use this file except in compliance* with the License.  You may obtain a copy of the License at**   http://www.apache.org/licenses/LICENSE-2.0** Unless required by applicable law or agreed to in writing,* software distributed under the License is distributed on an* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY* KIND, either express or implied.  See the License for the* specific language governing permissions and limitations* under the License.*/


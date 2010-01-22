package fr.imag.adele.cadse.cadseg.managers.ui;


import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.util.Convert;



/**
    @generated
*/
public class DGroupManager extends ItemManager {

	/**
	    @generated
	*/
	public DGroupManager() {
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
	public static final int getColumnAttribute(Item dGroup) {
		return dGroup.getAttributeWithDefaultValue(CadseGCST.DGROUP_at_COLUMN_, -1);
	}

	/**
		@generated
	*/
	public static final void setColumnAttribute(Item dGroup, int value) {
		try {
			dGroup.setAttribute(CadseGCST.DGROUP_at_COLUMN_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final boolean isMakeColumnsEqualWidthAttribute(Item dGroup) {
		return dGroup.getAttributeWithDefaultValue(CadseGCST.DGROUP_at_MAKE_COLUMNS_EQUAL_WIDTH_, false);
	}

	/**
		@generated
	*/
	public static final void setMakeColumnsEqualWidthAttribute(Item dGroup, boolean value) {
		try {
			dGroup.setAttribute(CadseGCST.DGROUP_at_MAKE_COLUMNS_EQUAL_WIDTH_, value);
		} catch (Throwable t) {

		}
	}

}
/** Licensed to the Apache Software Foundation (ASF) under one* or more contributor license agreements.  See the NOTICE file* distributed with this work for additional information* regarding copyright ownership.  The ASF licenses this file* to you under the Apache License, Version 2.0 (the* "License"); you may not use this file except in compliance* with the License.  You may obtain a copy of the License at**   http://www.apache.org/licenses/LICENSE-2.0** Unless required by applicable law or agreed to in writing,* software distributed under the License is distributed on an* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY* KIND, either express or implied.  See the License for the* specific language governing permissions and limitations* under the License.*/


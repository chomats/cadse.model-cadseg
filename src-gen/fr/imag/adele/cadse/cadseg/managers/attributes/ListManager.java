package fr.imag.adele.cadse.cadseg.managers.attributes;


import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;



/**
    @generated
*/
public class ListManager extends AttributeManager {

	/**
	    @generated
	*/
	public ListManager() {
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
		get a link 'sub-type' from 'List' to 'Attribute'.
		@generated
	*/
	static public Link getSubTypeLink(Item list) {
		return list.getOutgoingLink(CadseGCST.LIST_lt_SUB_TYPE);
	}

	/**
		get all link destination 'sub-type' from 'List' to 'Attribute'.
		@generated
	*/
	static public Item getSubTypeAll(Item list) {
		return list.getOutgoingItem(CadseGCST.LIST_lt_SUB_TYPE, false);
	}

	/**
		get resolved link destination 'sub-type' from 'List' to 'Attribute'.
		@generated
	*/
	static public Item getSubType(Item list) {
		return list.getOutgoingItem(CadseGCST.LIST_lt_SUB_TYPE, true);
	}

	/**
		set a link 'sub-type' from 'List' to 'Attribute'.
		@generated
	*/
	static public void setSubType(Item list, Item value) throws CadseException {
		list.setOutgoingItem(CadseGCST.LIST_lt_SUB_TYPE,value);
	}

}
/** Licensed to the Apache Software Foundation (ASF) under one* or more contributor license agreements.  See the NOTICE file* distributed with this work for additional information* regarding copyright ownership.  The ASF licenses this file* to you under the Apache License, Version 2.0 (the* "License"); you may not use this file except in compliance* with the License.  You may obtain a copy of the License at**   http://www.apache.org/licenses/LICENSE-2.0** Unless required by applicable law or agreed to in writing,* software distributed under the License is distributed on an* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY* KIND, either express or implied.  See the License for the* specific language governing permissions and limitations* under the License.*/


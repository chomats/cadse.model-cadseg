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
package fr.imag.adele.cadse.cadseg.managers.attributes;


import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;



/**
    @generated
*/
public class GroupOfAttributesManager extends AttributeManager {

	/**
	    @generated
	*/
	public GroupOfAttributesManager() {
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
	}	/**
		get  links 'attributes' from 'GroupOfAttributes' to 'Attribute'.
        @generated
    */
    static public List<Link> getAttributesLink(Item groupOfAttributes) {
        return groupOfAttributes.getOutgoingLinks(CadseGCST.GROUP_OF_ATTRIBUTES_lt_ATTRIBUTES);
    }

    /**
        @generated
    */
    static public Collection<Item> getAttributesAll(Item groupOfAttributes) {
        return groupOfAttributes.getOutgoingItems(CadseGCST.GROUP_OF_ATTRIBUTES_lt_ATTRIBUTES, false);
    }

    /**
        @generated
    */
    static public Collection<Item> getAttributes(Item groupOfAttributes) {
        return groupOfAttributes.getOutgoingItems(CadseGCST.GROUP_OF_ATTRIBUTES_lt_ATTRIBUTES,true);
    }

    /**
        @generated
    */
    static public void addAttributes(Item groupOfAttributes, Item value) throws CadseException {
        groupOfAttributes.addOutgoingItem(CadseGCST.GROUP_OF_ATTRIBUTES_lt_ATTRIBUTES,value);
    }

    /**
        @generated
    */
    static public void removeAttributes(Item groupOfAttributes, Item value) throws CadseException {
        groupOfAttributes.removeOutgoingItem(CadseGCST.GROUP_OF_ATTRIBUTES_lt_ATTRIBUTES,value);
    }


	/**
		get a link 'superGroup' from 'GroupOfAttributes' to 'GroupOfAttributes'.
		@generated
	*/
	static public Link getSuperGroupLink(Item groupOfAttributes) {
		return groupOfAttributes.getOutgoingLink(CadseGCST.GROUP_OF_ATTRIBUTES_lt_SUPER_GROUP);
	}

	/**
		get all link destination 'superGroup' from 'GroupOfAttributes' to 'GroupOfAttributes'.
		@generated
	*/
	static public Item getSuperGroupAll(Item groupOfAttributes) {
		return groupOfAttributes.getOutgoingItem(CadseGCST.GROUP_OF_ATTRIBUTES_lt_SUPER_GROUP, false);
	}

	/**
		get resolved link destination 'superGroup' from 'GroupOfAttributes' to 'GroupOfAttributes'.
		@generated
	*/
	static public Item getSuperGroup(Item groupOfAttributes) {
		return groupOfAttributes.getOutgoingItem(CadseGCST.GROUP_OF_ATTRIBUTES_lt_SUPER_GROUP, true);
	}

	/**
		set a link 'superGroup' from 'GroupOfAttributes' to 'GroupOfAttributes'.
		@generated
	*/
	static public void setSuperGroup(Item groupOfAttributes, Item value) throws CadseException {
		groupOfAttributes.setOutgoingItem(CadseGCST.GROUP_OF_ATTRIBUTES_lt_SUPER_GROUP,value);
	}
	/**
		@generated
	*/
	public static final int getColumnAttribute(Item groupOfAttributes) {
		return groupOfAttributes.getAttributeWithDefaultValue(CadseGCST.GROUP_OF_ATTRIBUTES_at_COLUMN_, -1);
	}

	/**
		@generated
	*/
	public static final void setColumnAttribute(Item groupOfAttributes, int value) {
		try {
			groupOfAttributes.setAttribute(CadseGCST.GROUP_OF_ATTRIBUTES_at_COLUMN_, value);
		} catch (Throwable t) {

		}
	}

	@Override
	public String canCreateMeItem(Item itemParent, LinkType lt,
			ItemType destType) {
		return CANNOT_CREATE;
	}

}
/** Licensed to the Apache Software Foundation (ASF) under one* or more contributor license agreements.  See the NOTICE file* distributed with this work for additional information* regarding copyright ownership.  The ASF licenses this file* to you under the Apache License, Version 2.0 (the* "License"); you may not use this file except in compliance* with the License.  You may obtain a copy of the License at**   http://www.apache.org/licenses/LICENSE-2.0** Unless required by applicable law or agreed to in writing,* software distributed under the License is distributed on an* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY* KIND, either express or implied.  See the License for the* specific language governing permissions and limitations* under the License.*/


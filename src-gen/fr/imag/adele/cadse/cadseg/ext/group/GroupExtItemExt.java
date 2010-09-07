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
package fr.imag.adele.cadse.cadseg.ext.group;


import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;



/**
    @generated
*/
public class GroupExtItemExt {

	/**
	    @generated
	*/
	public GroupExtItemExt() {
	}	/**
		get  links 'members' from 'GroupExtItem' to 'Item'.
        @generated
    */
    static public List<Link> getMembersLink(Item groupExtItem) {
        return groupExtItem.getOutgoingLinks(CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS);
    }

    /**
        @generated
    */
    static public Collection<Item> getMembersAll(Item groupExtItem) {
        return groupExtItem.getOutgoingItems(CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS, false);
    }

    /**
        @generated
    */
    static public Collection<Item> getMembers(Item groupExtItem) {
        return groupExtItem.getOutgoingItems(CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS,true);
    }

    /**
        @generated
    */
    static public void addMembers(Item groupExtItem, Item value) throws CadseException {
        groupExtItem.addOutgoingItem(CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS,value);
    }

    /**
        @generated
    */
    static public void removeMembers(Item groupExtItem, Item value) throws CadseException {
        groupExtItem.removeOutgoingItem(CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS,value);
    }


	/**
		get a link 'memberOf' from 'GroupExtItem' to 'Item'.
		@generated
	*/
	static public Link getMemberOfLink(Item groupExtItem) {
		return groupExtItem.getOutgoingLink(CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF);
	}

	/**
		get all link destination 'memberOf' from 'GroupExtItem' to 'Item'.
		@generated
	*/
	static public Item getMemberOfAll(Item groupExtItem) {
		return groupExtItem.getOutgoingItem(CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF, false);
	}

	/**
		get resolved link destination 'memberOf' from 'GroupExtItem' to 'Item'.
		@generated
	*/
	static public Item getMemberOf(Item groupExtItem) {
		return groupExtItem.getOutgoingItem(CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF, true);
	}

	/**
		set a link 'memberOf' from 'GroupExtItem' to 'Item'.
		@generated
	*/
	static public void setMemberOf(Item groupExtItem, Item value) throws CadseException {
		groupExtItem.setOutgoingItem(CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF,value);
	}

}


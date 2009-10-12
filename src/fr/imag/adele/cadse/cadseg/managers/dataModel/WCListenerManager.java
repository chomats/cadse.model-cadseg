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
package fr.imag.adele.cadse.cadseg.managers.dataModel;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import java.util.Collection;
import java.util.List;

/**
 * @generated
 */
public class WCListenerManager extends DefaultItemManager {

	/**
	 * @generated
	 */
	public WCListenerManager() {
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
	 * get links 'listenItemTypes' from 'WCListener' to 'ItemType'.
	 * 
	 * @generated
	 */
	static public List<Link> getListenItemTypesLink(Item wCListener) {
        return wCListener.getOutgoingLinks(CadseGCST.WCLISTENER_lt_LISTEN_ITEM_TYPES);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getListenItemTypesAll(Item wCListener) {
        return wCListener.getOutgoingItems(CadseGCST.WCLISTENER_lt_LISTEN_ITEM_TYPES, false);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getListenItemTypes(Item wCListener) {
        return wCListener.getOutgoingItems(CadseGCST.WCLISTENER_lt_LISTEN_ITEM_TYPES,true);
    }

	/**
	 * @generated
	 */
	static public void addListenItemTypes(Item wCListener, Item value) throws CadseException {
        wCListener.addOutgoingItem(CadseGCST.WCLISTENER_lt_LISTEN_ITEM_TYPES,value);
    }

	/**
	 * @generated
	 */
	static public void removeListenItemTypes(Item wCListener, Item value) throws CadseException {
        wCListener.removeOutgoingItem(CadseGCST.WCLISTENER_lt_LISTEN_ITEM_TYPES,value);
    }

	/**
	 * get links 'listenAttributeDefinitions' from 'WCListener' to 'Attribute'.
	 * 
	 * @generated
	 */
	static public List<Link> getListenAttributeDefinitionsLink(Item wCListener) {
        return wCListener.getOutgoingLinks(CadseGCST.WCLISTENER_lt_LISTEN_ATTRIBUTE_DEFINITIONS);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getListenAttributeDefinitionsAll(Item wCListener) {
        return wCListener.getOutgoingItems(CadseGCST.WCLISTENER_lt_LISTEN_ATTRIBUTE_DEFINITIONS, false);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getListenAttributeDefinitions(Item wCListener) {
        return wCListener.getOutgoingItems(CadseGCST.WCLISTENER_lt_LISTEN_ATTRIBUTE_DEFINITIONS,true);
    }

	/**
	 * @generated
	 */
	static public void addListenAttributeDefinitions(Item wCListener, Item value) throws CadseException {
        wCListener.addOutgoingItem(CadseGCST.WCLISTENER_lt_LISTEN_ATTRIBUTE_DEFINITIONS,value);
    }

	/**
	 * @generated
	 */
	static public void removeListenAttributeDefinitions(Item wCListener, Item value) throws CadseException {
        wCListener.removeOutgoingItem(CadseGCST.WCLISTENER_lt_LISTEN_ATTRIBUTE_DEFINITIONS,value);
    }

}

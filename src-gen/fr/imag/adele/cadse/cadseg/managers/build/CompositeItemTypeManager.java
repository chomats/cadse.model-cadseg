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

package fr.imag.adele.cadse.cadseg.managers.build;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class CompositeItemTypeManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class CompositeItemTypeManager extends DefaultItemManager {

	/**
	 * Instantiates a new composite item type manager.
	 */
	public CompositeItemTypeManager() {
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
	 * get links 'composers' from 'CompositeItemType' to 'Composer'.
	 * 
	 * @param compositeItemType
	 *            the composite item type
	 * 
	 * @return the composers link
	 * 
	 * @generated
	 */
	static public List<Link> getComposersLink(Item compositeItemType) {
        return compositeItemType.getOutgoingLinks(CadseGCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS);
    }

	/**
	 * Gets the composers all.
	 * 
	 * @param compositeItemType
	 *            the composite item type
	 * 
	 * @return the composers all
	 * 
	 * @generated
	 */
	static public Collection<Item> getComposersAll(Item compositeItemType) {
        return compositeItemType.getOutgoingItems(CadseGCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS, false);
    }

	/**
	 * Gets the composers.
	 * 
	 * @param compositeItemType
	 *            the composite item type
	 * 
	 * @return the composers
	 * 
	 * @generated
	 */
	static public Collection<Item> getComposers(Item compositeItemType) {
        return compositeItemType.getOutgoingItems(CadseGCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS,true);
    }

	/**
	 * Adds the composers.
	 * 
	 * @param compositeItemType
	 *            the composite item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addComposers(Item compositeItemType, Item value) throws CadseException {
        compositeItemType.addOutgoingItem(CadseGCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS,value);
    }

	/**
	 * Removes the composers.
	 * 
	 * @param compositeItemType
	 *            the composite item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeComposers(Item compositeItemType, Item value) throws CadseException {
        compositeItemType.removeOutgoingItem(CadseGCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS,value);
    }

	/**
	 * get links 'builders' from 'CompositeItemType' to 'Builder'.
	 * 
	 * @param compositeItemType
	 *            the composite item type
	 * 
	 * @return the builders link
	 * 
	 * @generated
	 */
	static public List<Link> getBuildersLink(Item compositeItemType) {
        return compositeItemType.getOutgoingLinks(CadseGCST.COMPOSITE_ITEM_TYPE_lt_BUILDERS);
    }

	/**
	 * Gets the builders all.
	 * 
	 * @param compositeItemType
	 *            the composite item type
	 * 
	 * @return the builders all
	 * 
	 * @generated
	 */
	static public Collection<Item> getBuildersAll(Item compositeItemType) {
        return compositeItemType.getOutgoingItems(CadseGCST.COMPOSITE_ITEM_TYPE_lt_BUILDERS, false);
    }

	/**
	 * Gets the builders.
	 * 
	 * @param compositeItemType
	 *            the composite item type
	 * 
	 * @return the builders
	 * 
	 * @generated
	 */
	static public Collection<Item> getBuilders(Item compositeItemType) {
        return compositeItemType.getOutgoingItems(CadseGCST.COMPOSITE_ITEM_TYPE_lt_BUILDERS,true);
    }

	/**
	 * Adds the builders.
	 * 
	 * @param compositeItemType
	 *            the composite item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addBuilders(Item compositeItemType, Item value) throws CadseException {
        compositeItemType.addOutgoingItem(CadseGCST.COMPOSITE_ITEM_TYPE_lt_BUILDERS,value);
    }

	/**
	 * Removes the builders.
	 * 
	 * @param compositeItemType
	 *            the composite item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeBuilders(Item compositeItemType, Item value) throws CadseException {
        compositeItemType.removeOutgoingItem(CadseGCST.COMPOSITE_ITEM_TYPE_lt_BUILDERS,value);
    }

	/**
	 * get a link 'item-type' from 'CompositeItemType' to 'ItemType'.
	 * 
	 * @param compositeItemType
	 *            the composite item type
	 * 
	 * @return the item type link
	 * 
	 * @generated
	 */
	static public Link getItemTypeLink(Item compositeItemType) {
		return compositeItemType.getOutgoingLink(CadseGCST.COMPOSITE_ITEM_TYPE_lt_ITEM_TYPE);
	}

	/**
	 * get all link destination 'item-type' from 'CompositeItemType' to
	 * 'ItemType'.
	 * 
	 * @param compositeItemType
	 *            the composite item type
	 * 
	 * @return the item type all
	 * 
	 * @generated
	 */
	static public Item getItemTypeAll(Item compositeItemType) {
		return compositeItemType.getOutgoingItem(CadseGCST.COMPOSITE_ITEM_TYPE_lt_ITEM_TYPE, false);
	}

	/**
	 * Gets the item type.
	 * 
	 * @param compositeItemType
	 *            the composite item type
	 * 
	 * @return the item type
	 * 
	 * @generated
	 */
	static public Item getItemType(Item compositeItemType) {
		return compositeItemType.getOutgoingItem(CadseGCST.COMPOSITE_ITEM_TYPE_lt_ITEM_TYPE, true);
	}

	/**
	 * set a link 'item-type' from 'CompositeItemType' to 'ItemType'.
	 * 
	 * @generated
	 */
	static public void setItemType(Item compositeItemType, Item value) throws CadseException {
		compositeItemType.setOutgoingItem(CadseGCST.COMPOSITE_ITEM_TYPE_lt_ITEM_TYPE,value);
	}

	/**
	 * Gets the composite item from item type.
	 * 
	 * @param itemType
	 *            the item type
	 * 
	 * @return the composite item from item type
	 */
	public static Item getCompositeItemFromItemType(Item itemType) {
		for (Link l : itemType.getIncomingLinks()) {
			if (l.getSource().getType() == CadseGCST.COMPOSITE_ITEM_TYPE) {
				return l.getSource();
			}
		}
		return null;
	}

	@Override
	public String canCreateMeItem(Item itemParent, LinkType lt, ItemType destType) {
		return "It's automatic item, cannot create it";
	}

	@Override
	public String canDeleteItem(Item item) {
		return "It's automatic item, cannot delete it";
	}
}

package fr.imag.adele.cadse.cadseg.managers.content;


import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.IItemFactory;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.delta.ItemDelta;
import fr.imag.adele.cadse.core.impl.ContentItemImpl;



/**
    @generated
*/
public class ContentItemManager extends DefaultItemManager implements IItemFactory {

	/**
	    @generated
	*/
	public ContentItemManager() {
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
		get a link 'owner-item' from 'ContentItem' to 'Item'.
		@generated
	*/
	static public Link getOwnerItemLink(Item contentItem) {
		return contentItem.getOutgoingLink(CadseGCST.CONTENT_ITEM_lt_OWNER_ITEM);
	}

	/**
		get all link destination 'owner-item' from 'ContentItem' to 'Item'.
		@generated
	*/
	static public Item getOwnerItemAll(Item contentItem) {
		return contentItem.getOutgoingItem(CadseGCST.CONTENT_ITEM_lt_OWNER_ITEM, false);
	}

	/**
		get resolved link destination 'owner-item' from 'ContentItem' to 'Item'.
		@generated
	*/
	static public Item getOwnerItem(Item contentItem) {
		return contentItem.getOutgoingItem(CadseGCST.CONTENT_ITEM_lt_OWNER_ITEM, true);
	}

	/**
		set a link 'owner-item' from 'ContentItem' to 'Item'.
		@generated
	*/
	static public void setOwnerItem(Item contentItem, Item value) throws CadseException {
		contentItem.setOutgoingItem(CadseGCST.CONTENT_ITEM_lt_OWNER_ITEM,value);
	}

	/**
		get  links 'children' from 'ContentItem' to 'ContentItem'.
        @generated
    */
    static public List<Link> getChildrenLink(Item contentItem) {
        return contentItem.getOutgoingLinks(CadseGCST.CONTENT_ITEM_lt_CHILDREN);
    }

	/**
        @generated
    */
    static public Collection<Item> getChildrenAll(Item contentItem) {
        return contentItem.getOutgoingItems(CadseGCST.CONTENT_ITEM_lt_CHILDREN, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getChildren(Item contentItem) {
        return contentItem.getOutgoingItems(CadseGCST.CONTENT_ITEM_lt_CHILDREN,true);
    }

	/**
        @generated
    */
    static public void addChildren(Item contentItem, Item value) throws CadseException {
        contentItem.addOutgoingItem(CadseGCST.CONTENT_ITEM_lt_CHILDREN,value);
    }

	/**
        @generated
    */
    static public void removeChildren(Item contentItem, Item value) throws CadseException {
        contentItem.removeOutgoingItem(CadseGCST.CONTENT_ITEM_lt_CHILDREN,value);
    }

	@Override
	public Item newForCommitItem(LogicalWorkspace logicalWorkspace,
			ItemType itemType, ItemDelta itemOp) {
		
		ItemDelta ownerItem = itemOp.getOutgoingItem(CadseGCST.CONTENT_ITEM_lt_OWNER_ITEM, false);
		
		ItemType it = ownerItem.getType();
		
		final IItemManager itemManager = it.getItemManager();
		final IContentItemFactory contentItemFactory = itemManager.getContentItemFactory();
		if (contentItemFactory == null) {
			return ContentItem.NO_CONTENT;
		}
		ContentItem ret = null;
		try {
			ret = contentItemFactory.createContentItem(itemOp.getId());
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ret == null) {
			ret = ContentItem.INVALID_CONTENT;
		} else if (ret != ContentItem.NO_CONTENT && ret != ContentItem.INVALID_CONTENT) {
			if (ownerItem.getBaseItem() == null)
				((ContentItemImpl) ret).setOwnerItem(ownerItem.getBaseItem());
		}
		return ret;		
	}
}
/** Licensed to the Apache Software Foundation (ASF) under one* or more contributor license agreements.  See the NOTICE file* distributed with this work for additional information* regarding copyright ownership.  The ASF licenses this file* to you under the Apache License, Version 2.0 (the* "License"); you may not use this file except in compliance* with the License.  You may obtain a copy of the License at**   http://www.apache.org/licenses/LICENSE-2.0** Unless required by applicable law or agreed to in writing,* software distributed under the License is distributed on an* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY* KIND, either express or implied.  See the License for the* specific language governing permissions and limitations* under the License.*/


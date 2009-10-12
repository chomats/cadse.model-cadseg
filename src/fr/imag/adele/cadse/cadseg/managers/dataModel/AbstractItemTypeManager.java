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

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.util.Convert;
import java.lang.String;

/**
 * The Class AbstractItemTypeManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class AbstractItemTypeManager extends ItemManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public AbstractItemTypeManager() {
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
	 * get links 'attributes' from 'AbstractItemType' to 'Attribute'.
	 * 
	 * @param abstractItemType
	 *            the abstract item type
	 * 
	 * @return the attributes link
	 * 
	 * @generated
	 */
	static public List<Link> getAttributesLink(Item abstractItemType) {
        return abstractItemType.getOutgoingLinks(CadseGCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES);
    }

	/**
	 * Gets the attributes all.
	 * 
	 * @param abstractItemType
	 *            the abstract item type
	 * 
	 * @return the attributes all
	 * 
	 * @generated
	 */
	static public Collection<Item> getAttributesAll(Item abstractItemType) {
        return abstractItemType.getOutgoingItems(CadseGCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES, false);
    }

	/**
	 * Gets the attributes.
	 * 
	 * @param abstractItemType
	 *            the abstract item type
	 * 
	 * @return the attributes
	 * 
	 * @generated
	 */
	static public Collection<Item> getAttributes(Item abstractItemType) {
        return abstractItemType.getOutgoingItems(CadseGCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES,true);
    }

	/**
	 * Adds the attributes.
	 * 
	 * @param abstractItemType
	 *            the abstract item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addAttributes(Item abstractItemType, Item value) throws CadseException {
        abstractItemType.addOutgoingItem(CadseGCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES,value);
    }

	/**
	 * Removes the attributes.
	 * 
	 * @param abstractItemType
	 *            the abstract item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeAttributes(Item abstractItemType, Item value) throws CadseException {
        abstractItemType.removeOutgoingItem(CadseGCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES,value);
    }

	/**
	 * get a link 'creation-dialog' from 'AbstractItemType' to 'CreationDialog'.
	 * 
	 * @param abstractItemType
	 *            the abstract item type
	 * 
	 * @return the creation dialog link
	 * 
	 * @generated
	 */
	static public Link getCreationDialogLink(Item abstractItemType) {
		return abstractItemType.getOutgoingLink(CadseGCST.ABSTRACT_ITEM_TYPE_lt_CREATION_DIALOG);
	}

	/**
	 * get all link destination 'creation-dialog' from 'AbstractItemType' to
	 * 'CreationDialog'.
	 * 
	 * @param abstractItemType
	 *            the abstract item type
	 * 
	 * @return the creation dialog all
	 * 
	 * @generated
	 */
	static public Item getCreationDialogAll(Item abstractItemType) {
		return abstractItemType.getOutgoingItem(CadseGCST.ABSTRACT_ITEM_TYPE_lt_CREATION_DIALOG, false);
	}

	/**
	 * get resolved link destination 'creation-dialog' from 'AbstractItemType'
	 * to 'CreationDialog'.
	 * 
	 * @param abstractItemType
	 *            the abstract item type
	 * 
	 * @return the creation dialog
	 * 
	 * @generated
	 */
	static public Item getCreationDialog(Item abstractItemType) {
		return abstractItemType.getOutgoingItem(CadseGCST.ABSTRACT_ITEM_TYPE_lt_CREATION_DIALOG, true);
	}

	/**
	 * set a link 'creation-dialog' from 'AbstractItemType' to 'CreationDialog'.
	 * 
	 * @param abstractItemType
	 *            the abstract item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setCreationDialog(Item abstractItemType, Item value) throws CadseException {
		abstractItemType.setOutgoingItem(CadseGCST.ABSTRACT_ITEM_TYPE_lt_CREATION_DIALOG,value);
	}

	/**
	 * get a link 'modification-dialog' from 'AbstractItemType' to
	 * 'ModificationDialog'.
	 * 
	 * @param abstractItemType
	 *            the abstract item type
	 * 
	 * @return the modification dialog link
	 * 
	 * @generated
	 */
	static public Link getModificationDialogLink(Item abstractItemType) {
		return abstractItemType.getOutgoingLink(CadseGCST.ABSTRACT_ITEM_TYPE_lt_MODIFICATION_DIALOG);
	}

	/**
	 * get all link destination 'modification-dialog' from 'AbstractItemType' to
	 * 'ModificationDialog'.
	 * 
	 * @param abstractItemType
	 *            the abstract item type
	 * 
	 * @return the modification dialog all
	 * 
	 * @generated
	 */
	static public Item getModificationDialogAll(Item abstractItemType) {
		return abstractItemType.getOutgoingItem(CadseGCST.ABSTRACT_ITEM_TYPE_lt_MODIFICATION_DIALOG, false);
	}

	/**
	 * get resolved link destination 'modification-dialog' from
	 * 'AbstractItemType' to 'ModificationDialog'.
	 * 
	 * @param abstractItemType
	 *            the abstract item type
	 * 
	 * @return the modification dialog
	 * 
	 * @generated
	 */
	static public Item getModificationDialog(Item abstractItemType) {
		return abstractItemType.getOutgoingItem(CadseGCST.ABSTRACT_ITEM_TYPE_lt_MODIFICATION_DIALOG, true);
	}

	/**
	 * set a link 'modification-dialog' from 'AbstractItemType' to
	 * 'ModificationDialog'.
	 * 
	 * @param abstractItemType
	 *            the abstract item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setModificationDialog(Item abstractItemType, Item value) throws CadseException {
		abstractItemType.setOutgoingItem(CadseGCST.ABSTRACT_ITEM_TYPE_lt_MODIFICATION_DIALOG,value);
	}

	/**
		@generated
	*/
	public static final String getIdRuntimeAttribute(Item abstractItemType) {
		return abstractItemType.getAttributeWithDefaultValue(CadseGCST.ABSTRACT_ITEM_TYPE_at_ID_RUNTIME_, null);
	}

	/**
		@generated
	*/
	public static final void setIdRuntimeAttribute(Item abstractItemType, String value) {
		try {
			abstractItemType.setAttribute(CadseGCST.ABSTRACT_ITEM_TYPE_at_ID_RUNTIME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * 
	 * @param absItemType
	 *            An item of type ItemType or ExtendItemType
	 * @return an item of type ItemType which is the superType or null if not
	 */
	public static Item getSuperAbstractItemType(Item absItemType) {
		if (absItemType.getType() == CadseGCST.EXT_ITEM_TYPE) {
			return ExtItemTypeManager.getRefType(absItemType);
		}
		return ItemTypeManager.getSuperType(absItemType);
	}

	/**
	 * Gets the uUID.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the uUID
	 */
	public static CompactUUID getIdRuntime(Item itemtype) {
		
		String uuid_str = itemtype.getAttributeOwner(CadseGCST.ABSTRACT_ITEM_TYPE_at_ID_RUNTIME_);
		if (uuid_str == null || uuid_str.length() == 0) {
			CompactUUID uuid = CompactUUID.randomUUID();
			uuid_str = uuid.toString();
			try {
				itemtype.setAttribute(CadseGCST.ABSTRACT_ITEM_TYPE_at_ID_RUNTIME_, uuid_str);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return uuid;
		}
		return new CompactUUID(uuid_str);

	}
}

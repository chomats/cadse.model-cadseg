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
import java.util.UUID;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
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
public class TypeDefinitionManager extends ItemManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public TypeDefinitionManager() {
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
	static public List<Link> getAttributesLink(Item typeDefinition) {
        return typeDefinition.getOutgoingLinks(CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES);
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
	static public Collection<Item> getAttributesAll(Item typeDefinition) {
        return typeDefinition.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES, false);
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
	static public Collection<Item> getAttributes(Item typeDefinition) {
        return typeDefinition.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES,true);
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
	static public void addAttributes(Item typeDefinition, Item value) throws CadseException {
        typeDefinition.addOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES,value);
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
	static public void removeAttributes(Item typeDefinition, Item value) throws CadseException {
        typeDefinition.removeOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES,value);
    }

	/**
		@generated
	*/
	public static final String getIdRuntimeAttribute(Item typeDefinition) {
		return typeDefinition.getAttributeWithDefaultValue(CadseGCST.TYPE_DEFINITION_at_ID_RUNTIME_, null);
	}

	/**
		@generated
	*/
	public static final void setIdRuntimeAttribute(Item typeDefinition, String value) {
		try {
			typeDefinition.setAttribute(CadseGCST.TYPE_DEFINITION_at_ID_RUNTIME_, value);
		} catch (Throwable t) {

		}
	}

	/**
		get  links 'fields' from 'TypeDefinition' to 'Field'.
        @generated
    */
    static public List<Link> getFieldsLink(Item typeDefinition) {
        return typeDefinition.getOutgoingLinks(CadseGCST.TYPE_DEFINITION_lt_FIELDS);
    }

	/**
        @generated
    */
    static public Collection<Item> getFieldsAll(Item typeDefinition) {
        return typeDefinition.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_FIELDS, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getFields(Item typeDefinition) {
        return typeDefinition.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_FIELDS,true);
    }

	/**
        @generated
    */
    static public void addFields(Item typeDefinition, Item value) throws CadseException {
        typeDefinition.addOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_FIELDS,value);
    }

	/**
        @generated
    */
    static public void removeFields(Item typeDefinition, Item value) throws CadseException {
        typeDefinition.removeOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_FIELDS,value);
    }

	/**
		get  links 'creation-pages' from 'TypeDefinition' to 'Page'.
        @generated
    */
    static public List<Link> getCreationPagesLink(Item typeDefinition) {
        return typeDefinition.getOutgoingLinks(CadseGCST.TYPE_DEFINITION_lt_CREATION_PAGES);
    }

	/**
        @generated
    */
    static public Collection<Item> getCreationPagesAll(Item typeDefinition) {
        return typeDefinition.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_CREATION_PAGES, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getCreationPages(Item typeDefinition) {
        return typeDefinition.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_CREATION_PAGES,true);
    }

	/**
        @generated
    */
    static public void addCreationPages(Item typeDefinition, Item value) throws CadseException {
        typeDefinition.addOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_CREATION_PAGES,value);
    }

	/**
        @generated
    */
    static public void removeCreationPages(Item typeDefinition, Item value) throws CadseException {
        typeDefinition.removeOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_CREATION_PAGES,value);
    }

	/**
		get  links 'validators' from 'TypeDefinition' to 'UIValidator'.
        @generated
    */
    static public List<Link> getValidatorsLink(Item typeDefinition) {
        return typeDefinition.getOutgoingLinks(CadseGCST.TYPE_DEFINITION_lt_VALIDATORS);
    }

	/**
        @generated
    */
    static public Collection<Item> getValidatorsAll(Item typeDefinition) {
        return typeDefinition.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_VALIDATORS, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getValidators(Item typeDefinition) {
        return typeDefinition.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_VALIDATORS,true);
    }

	/**
        @generated
    */
    static public void addValidators(Item typeDefinition, Item value) throws CadseException {
        typeDefinition.addOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_VALIDATORS,value);
    }

	/**
        @generated
    */
    static public void removeValidators(Item typeDefinition, Item value) throws CadseException {
        typeDefinition.removeOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_VALIDATORS,value);
    }

	/**
		get a link 'cadse' from 'TypeDefinition' to 'CadseRuntime'.
		@generated
	*/
	static public Link getCadseLink(Item typeDefinition) {
		return typeDefinition.getOutgoingLink(CadseGCST.TYPE_DEFINITION_lt_CADSE);
	}

	/**
		get all link destination 'cadse' from 'TypeDefinition' to 'CadseRuntime'.
		@generated
	*/
	static public Item getCadseAll(Item typeDefinition) {
		return typeDefinition.getOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_CADSE, false);
	}

	/**
		get resolved link destination 'cadse' from 'TypeDefinition' to 'CadseRuntime'.
		@generated
	*/
	static public Item getCadse(Item typeDefinition) {
		return typeDefinition.getOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_CADSE, true);
	}

	/**
		set a link 'cadse' from 'TypeDefinition' to 'CadseRuntime'.
		@generated
	*/
	static public void setCadse(Item typeDefinition, Item value) throws CadseException {
		typeDefinition.setOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_CADSE,value);
	}

	/**
		get  links 'modification-pages' from 'TypeDefinition' to 'Page'.
        @generated
    */
    static public List<Link> getModificationPagesLink(Item typeDefinition) {
        return typeDefinition.getOutgoingLinks(CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES);
    }

	/**
        @generated
    */
    static public Collection<Item> getModificationPagesAll(Item typeDefinition) {
        return typeDefinition.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getModificationPages(Item typeDefinition) {
        return typeDefinition.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES,true);
    }

	/**
        @generated
    */
    static public void addModificationPages(Item typeDefinition, Item value) throws CadseException {
        typeDefinition.addOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES,value);
    }

	/**
        @generated
    */
    static public void removeModificationPages(Item typeDefinition, Item value) throws CadseException {
        typeDefinition.removeOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES,value);
    }

	/**
		get  links 'goupsOfAttributes' from 'TypeDefinition' to 'GroupOfAttributes'.
        @generated
    */
    static public List<Link> getGoupsOfAttributesLink(Item typeDefinition) {
        return typeDefinition.getOutgoingLinks(CadseGCST.TYPE_DEFINITION_lt_GOUPS_OF_ATTRIBUTES);
    }

	/**
        @generated
    */
    static public Collection<Item> getGoupsOfAttributesAll(Item typeDefinition) {
        return typeDefinition.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_GOUPS_OF_ATTRIBUTES, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getGoupsOfAttributes(Item typeDefinition) {
        return typeDefinition.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_GOUPS_OF_ATTRIBUTES,true);
    }

	/**
        @generated
    */
    static public void addGoupsOfAttributes(Item typeDefinition, Item value) throws CadseException {
        typeDefinition.addOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_GOUPS_OF_ATTRIBUTES,value);
    }

	/**
        @generated
    */
    static public void removeGoupsOfAttributes(Item typeDefinition, Item value) throws CadseException {
        typeDefinition.removeOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_GOUPS_OF_ATTRIBUTES,value);
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
	public static UUID getIdRuntime(Item itemtype) {
		
		String uuid_str = itemtype.getAttributeOwner(CadseGCST.TYPE_DEFINITION_at_ID_RUNTIME_);
		if (uuid_str == null || uuid_str.length() == 0) {
			UUID uuid = UUID.randomUUID();
			uuid_str = uuid.toString();
			try {
				itemtype.setAttribute(CadseGCST.TYPE_DEFINITION_at_ID_RUNTIME_, uuid_str);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return uuid;
		}
		return UUID.fromString(uuid_str);

	}
}

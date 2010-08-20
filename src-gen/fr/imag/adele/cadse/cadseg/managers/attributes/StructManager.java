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

package fr.imag.adele.cadse.cadseg.managers.attributes;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class StructManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class StructManager extends AttributeManager implements IItemManager, IModelWorkspaceManager {

	/**
	 * Instantiates a new struct manager.
	 */
	public StructManager() {
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
	 * get links 'attributes' from 'Struct' to 'Attribute'.
	 * 
	 * @param struct
	 *            the struct
	 * 
	 * @return the attributes link
	 * 
	 * @generated
	 */
	static public List<Link> getAttributesLink(Item struct) {
        return struct.getOutgoingLinks(CadseGCST.STRUCT_lt_ATTRIBUTES);
    }

	/**
	 * Gets the attributes all.
	 * 
	 * @param struct
	 *            the struct
	 * 
	 * @return the attributes all
	 * 
	 * @generated
	 */
	static public Collection<Item> getAttributesAll(Item struct) {
        return struct.getOutgoingItems(CadseGCST.STRUCT_lt_ATTRIBUTES, false);
    }

	/**
	 * Gets the attributes.
	 * 
	 * @param struct
	 *            the struct
	 * 
	 * @return the attributes
	 * 
	 * @generated
	 */
	static public Collection<Item> getAttributes(Item struct) {
        return struct.getOutgoingItems(CadseGCST.STRUCT_lt_ATTRIBUTES,true);
    }

	/**
	 * Adds the attributes.
	 * 
	 * @param struct
	 *            the struct
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addAttributes(Item struct, Item value) throws CadseException {
        struct.addOutgoingItem(CadseGCST.STRUCT_lt_ATTRIBUTES,value);
    }

	/**
	 * Removes the attributes.
	 * 
	 * @param struct
	 *            the struct
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeAttributes(Item struct, Item value) throws CadseException {
        struct.removeOutgoingItem(CadseGCST.STRUCT_lt_ATTRIBUTES,value);
    }

	

}

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

package fr.imag.adele.cadse.cadseg.managers;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class ContentTypeManager.
 * 
 * @generated
 */
public class ContentTypeManager extends DefaultItemManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ContentTypeManager() {
		super();
	}

	/**
	 * Compute unique name.
	 * 
	 * @param item
	 *            the item
	 * @param shortName
	 *            the short name
	 * @param parent
	 *            the parent
	 * @param lt
	 *            the lt
	 * 
	 * @return the string
	 * 
	 * @generated
	 */
	@Override
	public String computeUniqueName(Item item, String name, Item parent, LinkType lt) {
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
	 * get links 'attributes' from 'ContentType' to 'Attribute'.
	 * 
	 * @param contentType
	 *            the content type
	 * 
	 * @return the attributes link
	 * 
	 * @generated
	 */
	static public List<Link> getAttributesLink(Item contentType) {
		return contentType.getOutgoingLinks(WorkspaceCST.CONTENT_TYPE_lt_ATTRIBUTES);
	}

	/**
	 * Gets the attributes all.
	 * 
	 * @param contentType
	 *            the content type
	 * 
	 * @return the attributes all
	 * 
	 * @generated
	 */
	static public Collection<Item> getAttributesAll(Item contentType) {
		return contentType.getOutgoingItems(WorkspaceCST.CONTENT_TYPE_lt_ATTRIBUTES, false);
	}

	/**
	 * Gets the attributes.
	 * 
	 * @param contentType
	 *            the content type
	 * 
	 * @return the attributes
	 * 
	 * @generated
	 */
	static public Collection<Item> getAttributes(Item contentType) {
		return contentType.getOutgoingItems(WorkspaceCST.CONTENT_TYPE_lt_ATTRIBUTES, true);
	}

	/**
	 * Adds the attributes.
	 * 
	 * @param contentType
	 *            the content type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addAttributes(Item contentType, Item value) throws CadseException {
		contentType.addOutgoingItem(WorkspaceCST.CONTENT_TYPE_lt_ATTRIBUTES, value);
	}

	/**
	 * Removes the attributes.
	 * 
	 * @param contentType
	 *            the content type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeAttributes(Item contentType, Item value) throws CadseException {
		contentType.removeOutgoingItem(WorkspaceCST.CONTENT_TYPE_lt_ATTRIBUTES, value);
	}

	/**
	 * get a link 'super-type' from 'ContentType' to 'ContentType'.
	 * 
	 * @param contentType
	 *            the content type
	 * 
	 * @return the super type link
	 * 
	 * @generated
	 */
	static public Link getSuperTypeLink(Item contentType) {
		return contentType.getOutgoingLink(WorkspaceCST.CONTENT_TYPE_lt_SUPER_TYPE);
	}

	/**
	 * get all link destination 'super-type' from 'ContentType' to
	 * 'ContentType'.
	 * 
	 * @param contentType
	 *            the content type
	 * 
	 * @return the super type all
	 * 
	 * @generated
	 */
	static public Item getSuperTypeAll(Item contentType) {
		return contentType.getOutgoingItem(WorkspaceCST.CONTENT_TYPE_lt_SUPER_TYPE, false);
	}

	/**
	 * get resolved link destination 'super-type' from 'ContentType' to
	 * 'ContentType'.
	 * 
	 * @param contentType
	 *            the content type
	 * 
	 * @return the super type
	 * 
	 * @generated
	 */
	static public Item getSuperType(Item contentType) {
		return contentType.getOutgoingItem(WorkspaceCST.CONTENT_TYPE_lt_SUPER_TYPE, true);
	}

	/**
	 * set a link 'super-type' from 'ContentType' to 'ContentType'.
	 * 
	 * @generated
	 */
	static public void setSuperType(Item contentType, Item value) throws CadseException {
		contentType.setOutgoingItem(WorkspaceCST.CONTENT_TYPE_lt_SUPER_TYPE, value);
	}

}

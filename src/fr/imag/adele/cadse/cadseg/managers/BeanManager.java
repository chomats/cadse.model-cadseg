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
 * The Class BeanManager.
 * 
 * @generated
 */
public class BeanManager extends DefaultItemManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public BeanManager() {
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
	 * get links 'attributes' from 'Bean' to 'Attribute'.
	 * 
	 * @param bean
	 *            the bean
	 * 
	 * @return the attributes link
	 * 
	 * @generated
	 */
	static public List<Link> getAttributesLink(Item bean) {
		return bean.getOutgoingLinks(WorkspaceCST.BEAN_lt_ATTRIBUTES);
	}

	/**
	 * Gets the attributes all.
	 * 
	 * @param bean
	 *            the bean
	 * 
	 * @return the attributes all
	 * 
	 * @generated
	 */
	static public Collection<Item> getAttributesAll(Item bean) {
		return bean.getOutgoingItems(WorkspaceCST.BEAN_lt_ATTRIBUTES, false);
	}

	/**
	 * Gets the attributes.
	 * 
	 * @param bean
	 *            the bean
	 * 
	 * @return the attributes
	 * 
	 * @generated
	 */
	static public Collection<Item> getAttributes(Item bean) {
		return bean.getOutgoingItems(WorkspaceCST.BEAN_lt_ATTRIBUTES, true);
	}

	/**
	 * Adds the attributes.
	 * 
	 * @param bean
	 *            the bean
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addAttributes(Item bean, Item value) throws CadseException {
		bean.addOutgoingItem(WorkspaceCST.BEAN_lt_ATTRIBUTES, value);
	}

	/**
	 * Removes the attributes.
	 * 
	 * @param bean
	 *            the bean
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeAttributes(Item bean, Item value) throws CadseException {
		bean.removeOutgoingItem(WorkspaceCST.BEAN_lt_ATTRIBUTES, value);
	}

	/**
	 * get a link 'super-type' from 'Bean' to 'ItemType'.
	 * 
	 * @param bean
	 *            the bean
	 * 
	 * @return the super type link
	 * 
	 * @generated
	 */
	static public Link getSuperTypeLink(Item bean) {
		return bean.getOutgoingLink(WorkspaceCST.BEAN_lt_SUPER_TYPE);
	}

	/**
	 * get all link destination 'super-type' from 'Bean' to 'ItemType'.
	 * 
	 * @param bean
	 *            the bean
	 * 
	 * @return the super type all
	 * 
	 * @generated
	 */
	static public Item getSuperTypeAll(Item bean) {
		return bean.getOutgoingItem(WorkspaceCST.BEAN_lt_SUPER_TYPE, false);
	}

	/**
	 * get resolved link destination 'super-type' from 'Bean' to 'ItemType'.
	 * 
	 * @param bean
	 *            the bean
	 * 
	 * @return the super type
	 * 
	 * @generated
	 */
	static public Item getSuperType(Item bean) {
		return bean.getOutgoingItem(WorkspaceCST.BEAN_lt_SUPER_TYPE, true);
	}

	/**
	 * set a link 'super-type' from 'Bean' to 'ItemType'.
	 * 
	 * @param bean
	 *            the bean
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setSuperType(Item bean, Item value) throws CadseException {
		bean.setOutgoingItem(WorkspaceCST.BEAN_lt_SUPER_TYPE, value);
	}

}

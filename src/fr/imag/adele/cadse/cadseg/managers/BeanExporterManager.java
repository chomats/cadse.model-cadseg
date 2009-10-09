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
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class BeanExporterManager.
 * 
 * @generated
 */
public class BeanExporterManager extends BeanManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public BeanExporterManager() {
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
	 * get links 'attributes' from 'BeanExporter' to 'Attribute'.
	 * 
	 * @param beanExporter
	 *            the bean exporter
	 * 
	 * @return the attributes link
	 * 
	 * @generated
	 */
	static public List<Link> getAttributesLink(Item beanExporter) {
		return beanExporter.getOutgoingLinks(WorkspaceCST.BEAN_EXPORTER_lt_ATTRIBUTES);
	}

	/**
	 * Gets the attributes all.
	 * 
	 * @param beanExporter
	 *            the bean exporter
	 * 
	 * @return the attributes all
	 * 
	 * @generated
	 */
	static public Collection<Item> getAttributesAll(Item beanExporter) {
		return beanExporter.getOutgoingItems(WorkspaceCST.BEAN_EXPORTER_lt_ATTRIBUTES, false);
	}

	/**
	 * Gets the attributes.
	 * 
	 * @param beanExporter
	 *            the bean exporter
	 * 
	 * @return the attributes
	 * 
	 * @generated
	 */
	static public Collection<Item> getAttributes(Item beanExporter) {
		return beanExporter.getOutgoingItems(WorkspaceCST.BEAN_EXPORTER_lt_ATTRIBUTES, true);
	}

	/**
	 * Adds the attributes.
	 * 
	 * @param beanExporter
	 *            the bean exporter
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addAttributes(Item beanExporter, Item value) throws CadseException {
		beanExporter.addOutgoingItem(WorkspaceCST.BEAN_EXPORTER_lt_ATTRIBUTES, value);
	}

	/**
	 * Removes the attributes.
	 * 
	 * @param beanExporter
	 *            the bean exporter
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeAttributes(Item beanExporter, Item value) throws CadseException {
		beanExporter.removeOutgoingItem(WorkspaceCST.BEAN_EXPORTER_lt_ATTRIBUTES, value);
	}

	/**
	 * get a link 'super-type' from 'BeanExporter' to 'ItemType'.
	 * 
	 * @param beanExporter
	 *            the bean exporter
	 * 
	 * @return the super type link
	 * 
	 * @generated
	 */
	static public Link getSuperTypeLink(Item beanExporter) {
		return beanExporter.getOutgoingLink(WorkspaceCST.BEAN_EXPORTER_lt_SUPER_TYPE);
	}

	/**
	 * get all link destination 'super-type' from 'BeanExporter' to 'ItemType'.
	 * 
	 * @param beanExporter
	 *            the bean exporter
	 * 
	 * @return the super type all
	 * 
	 * @generated
	 */
	static public Item getSuperTypeAll(Item beanExporter) {
		return beanExporter.getOutgoingItem(WorkspaceCST.BEAN_EXPORTER_lt_SUPER_TYPE, false);
	}

	/**
	 * get resolved link destination 'super-type' from 'BeanExporter' to
	 * 'ItemType'.
	 * 
	 * @param beanExporter
	 *            the bean exporter
	 * 
	 * @return the super type
	 * 
	 * @generated
	 */
	static public Item getSuperType(Item beanExporter) {
		return beanExporter.getOutgoingItem(WorkspaceCST.BEAN_EXPORTER_lt_SUPER_TYPE, true);
	}

	/**
	 * set a link 'super-type' from 'BeanExporter' to 'ItemType'.
	 * 
	 * @generated
	 */
	static public void setSuperType(Item beanExporter, Item value) throws CadseException {
		beanExporter.setOutgoingItem(WorkspaceCST.BEAN_EXPORTER_lt_SUPER_TYPE, value);
	}

}

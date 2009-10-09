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
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class ProductModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ProductModelManager extends DefaultItemManager implements IItemManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ProductModelManager() {
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
	 * get links 'types' from 'ProductModel' to 'ItemType'.
	 * 
	 * @param productModel
	 *            the product model
	 * 
	 * @return the types link
	 * 
	 * @generated
	 */
	static public List<Link> getTypesLink(Item productModel) {
		return productModel.getOutgoingLinks(WorkspaceCST.PRODUCT_MODEL_lt_TYPES);
	}

	/**
	 * Gets the types all.
	 * 
	 * @param productModel
	 *            the product model
	 * 
	 * @return the types all
	 * 
	 * @generated
	 */
	static public Collection<Item> getTypesAll(Item productModel) {
		return productModel.getOutgoingItems(WorkspaceCST.PRODUCT_MODEL_lt_TYPES, false);
	}

	/**
	 * Gets the types.
	 * 
	 * @param productModel
	 *            the product model
	 * 
	 * @return the types
	 * 
	 * @generated
	 */
	static public Collection<Item> getTypes(Item productModel) {
		return productModel.getOutgoingItems(WorkspaceCST.PRODUCT_MODEL_lt_TYPES, true);
	}

	/**
	 * Adds the types.
	 * 
	 * @param productModel
	 *            the product model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addTypes(Item productModel, Item value) throws CadseException {
		productModel.addOutgoingItem(WorkspaceCST.PRODUCT_MODEL_lt_TYPES, value);
	}

	/**
	 * Removes the types.
	 * 
	 * @param productModel
	 *            the product model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeTypes(Item productModel, Item value) throws CadseException {
		productModel.removeOutgoingItem(WorkspaceCST.PRODUCT_MODEL_lt_TYPES, value);
	}

}

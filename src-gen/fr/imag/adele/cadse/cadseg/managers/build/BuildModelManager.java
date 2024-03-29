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
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.cadseg.managers.build;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class BuildModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class BuildModelManager extends DefaultItemManager implements IItemManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public BuildModelManager() {
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
	 * get links 'composite-types' from 'BuildModel' to 'CompositeItemType'.
	 * 
	 * @param buildModel
	 *            the build model
	 * 
	 * @return the composite types link
	 * 
	 * @generated
	 */
	static public List<Link> getCompositeTypesLink(Item buildModel) {
        return buildModel.getOutgoingLinks(CadseGCST.BUILD_MODEL_lt_COMPOSITE_TYPES);
    }

	/**
	 * Gets the composite types all.
	 * 
	 * @param buildModel
	 *            the build model
	 * 
	 * @return the composite types all
	 * 
	 * @generated
	 */
	static public Collection<Item> getCompositeTypesAll(Item buildModel) {
        return buildModel.getOutgoingItems(CadseGCST.BUILD_MODEL_lt_COMPOSITE_TYPES, false);
    }

	/**
	 * Gets the composite types.
	 * 
	 * @param buildModel
	 *            the build model
	 * 
	 * @return the composite types
	 * 
	 * @generated
	 */
	static public Collection<Item> getCompositeTypes(Item buildModel) {
        return buildModel.getOutgoingItems(CadseGCST.BUILD_MODEL_lt_COMPOSITE_TYPES,true);
    }

	/**
	 * Adds the composite types.
	 * 
	 * @param buildModel
	 *            the build model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addCompositeTypes(Item buildModel, Item value) throws CadseException {
        buildModel.addOutgoingItem(CadseGCST.BUILD_MODEL_lt_COMPOSITE_TYPES,value);
    }

	/**
	 * Removes the composite types.
	 * 
	 * @param buildModel
	 *            the build model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeCompositeTypes(Item buildModel, Item value) throws CadseException {
        buildModel.removeOutgoingItem(CadseGCST.BUILD_MODEL_lt_COMPOSITE_TYPES,value);
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canDeleteItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canDeleteItem(Item item) {
		return "Cannot delete a build model";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return "Cannot rename a build model";
	}
}

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
package fr.imag.adele.cadse.cadseg.managers.content;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class MappingModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class MappingModelManager extends DefaultItemManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public MappingModelManager() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canDeleteItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canDeleteItem(Item item) {
		return "Cannot delete a mapping model";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return "Cannot rename a mapping model";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#isAbstract(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType)
	 */
	@Override
	public boolean isAbstract(Item parent, LinkType type) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#getDisplayName(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String getDisplayName(Item item) {
		return item.getName();
	}

	/**
	 * get links 'managers' from 'MappingModel' to 'Manager'.
	 * 
	 * @param mappingModel
	 *            the mapping model
	 * 
	 * @return the managers link
	 * 
	 * @generated
	 */
	static public List<Link> getManagersLink(Item mappingModel) {
        return mappingModel.getOutgoingLinks(CadseGCST.MAPPING_MODEL_lt_MANAGERS);
    }

	/**
	 * Gets the managers all.
	 * 
	 * @param mappingModel
	 *            the mapping model
	 * 
	 * @return the managers all
	 * 
	 * @generated
	 */
	static public Collection<Item> getManagersAll(Item mappingModel) {
        return mappingModel.getOutgoingItems(CadseGCST.MAPPING_MODEL_lt_MANAGERS, false);
    }

	/**
	 * Gets the managers.
	 * 
	 * @param mappingModel
	 *            the mapping model
	 * 
	 * @return the managers
	 * 
	 * @generated
	 */
	static public Collection<Item> getManagers(Item mappingModel) {
        return mappingModel.getOutgoingItems(CadseGCST.MAPPING_MODEL_lt_MANAGERS,true);
    }

	/**
	 * Adds the managers.
	 * 
	 * @param mappingModel
	 *            the mapping model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addManagers(Item mappingModel, Item value) throws CadseException {
        mappingModel.addOutgoingItem(CadseGCST.MAPPING_MODEL_lt_MANAGERS,value);
    }

	/**
	 * Removes the managers.
	 * 
	 * @param mappingModel
	 *            the mapping model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeManagers(Item mappingModel, Item value) throws CadseException {
        mappingModel.removeOutgoingItem(CadseGCST.MAPPING_MODEL_lt_MANAGERS,value);
    }

}

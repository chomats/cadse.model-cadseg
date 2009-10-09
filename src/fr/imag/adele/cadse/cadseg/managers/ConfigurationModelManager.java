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
/*
 * Adele/LIG/ Grenoble University, France
 * 2006-2008
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
 * The Class ConfigurationModelManager.
 */
public class ConfigurationModelManager extends DefaultItemManager {

	/**
	 * Instantiates a new configuration model manager.
	 */
	public ConfigurationModelManager() {
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
	 * @see fede.workspace.model.manager.DefaultItemManager#getDisplayName(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String getDisplayName(Item item) {
		return item.getName();
	}

	/**
	 * get links 'build' from 'ConfigurationModel' to 'BuildModel'.
	 * 
	 * @param configurationModel
	 *            the configuration model
	 * 
	 * @return the builds the link
	 * 
	 * @generated
	 */
	static public List<Link> getBuildLink(Item configurationModel) {
		return configurationModel.getOutgoingLinks(WorkspaceCST.CONFIGURATION_MODEL_lt_BUILD);
	}

	/**
	 * Gets the build all.
	 * 
	 * @param configurationModel
	 *            the configuration model
	 * 
	 * @return the builds the all
	 * 
	 * @generated
	 */
	static public Collection<Item> getBuildAll(Item configurationModel) {
		return configurationModel.getOutgoingItems(WorkspaceCST.CONFIGURATION_MODEL_lt_BUILD, false);
	}

	/**
	 * Gets the build.
	 * 
	 * @param configurationModel
	 *            the configuration model
	 * 
	 * @return the builds the
	 * 
	 * @generated
	 */
	static public Collection<Item> getBuild(Item configurationModel) {
		return configurationModel.getOutgoingItems(WorkspaceCST.CONFIGURATION_MODEL_lt_BUILD, true);
	}

	/**
	 * Adds the build.
	 * 
	 * @param configurationModel
	 *            the configuration model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addBuild(Item configurationModel, Item value) throws CadseException {
		configurationModel.addOutgoingItem(WorkspaceCST.CONFIGURATION_MODEL_lt_BUILD, value);
	}

	/**
	 * Removes the build.
	 * 
	 * @param configurationModel
	 *            the configuration model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeBuild(Item configurationModel, Item value) throws CadseException {
		configurationModel.removeOutgoingItem(WorkspaceCST.CONFIGURATION_MODEL_lt_BUILD, value);
	}

	/**
	 * get a link '#invert_part_configuration_to_CadseDefinition' from
	 * 'ConfigurationModel' to 'CadseDefinition'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_configuration_to_CadseDefinitionLink(Item configurationModel) {
		return configurationModel
				.getOutgoingLink(WorkspaceCST.CONFIGURATION_MODEL_lt__$_INVERT_PART_CONFIGURATION_TO_CADSE_DEFINITION);
	}

	/**
	 * get all link destination '#invert_part_configuration_to_CadseDefinition'
	 * from 'ConfigurationModel' to 'CadseDefinition'.
	 * 
	 * @generated
	 */
	static public Item get_$_Invert_part_configuration_to_CadseDefinitionAll(Item configurationModel) {
		return configurationModel.getOutgoingItem(
				WorkspaceCST.CONFIGURATION_MODEL_lt__$_INVERT_PART_CONFIGURATION_TO_CADSE_DEFINITION, false);
	}

	/**
	 * get resolved link destination
	 * '#invert_part_configuration_to_CadseDefinition' from 'ConfigurationModel'
	 * to 'CadseDefinition'.
	 * 
	 * @generated
	 */
	static public Item get_$_Invert_part_configuration_to_CadseDefinition(Item configurationModel) {
		return configurationModel.getOutgoingItem(
				WorkspaceCST.CONFIGURATION_MODEL_lt__$_INVERT_PART_CONFIGURATION_TO_CADSE_DEFINITION, true);
	}

	/**
	 * set a link '#invert_part_configuration_to_CadseDefinition' from
	 * 'ConfigurationModel' to 'CadseDefinition'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_configuration_to_CadseDefinition(Item configurationModel, Item value)
			throws CadseException {
		configurationModel.setOutgoingItem(
				WorkspaceCST.CONFIGURATION_MODEL_lt__$_INVERT_PART_CONFIGURATION_TO_CADSE_DEFINITION, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return CANNOT_RENAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canDeleteItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canDeleteItem(Item item) {
		return CANNOT_DELETE;
	}
}
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
package fr.imag.adele.cadse.cadseg.managers.dataModel;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import java.util.UUID;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.key.DefaultKeyDefinitionImpl;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;

/**
 * The Class DataModelManager.
 * 
 * @extends DefaultWorkspaceManager
 */
public class DataModelManager extends DefaultWorkspaceManager {

	/**
	 * Instantiates a new data model manager.
	 */
	public DataModelManager() {
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
	 * get links 'categories' from 'DataModel' to 'DataModel'.
	 * 
	 * @param dataModel
	 *            the data model
	 * 
	 * @return the categories link
	 * 
	 * @generated
	 */
	static public List<Link> getCategoriesLink(Item dataModel) {
        return dataModel.getOutgoingLinks(CadseGCST.DATA_MODEL_lt_CATEGORIES);
    }

	/**
	 * Gets the categories all.
	 * 
	 * @param dataModel
	 *            the data model
	 * 
	 * @return the categories all
	 * 
	 * @generated
	 */
	static public Collection<Item> getCategoriesAll(Item dataModel) {
        return dataModel.getOutgoingItems(CadseGCST.DATA_MODEL_lt_CATEGORIES, false);
    }

	/**
	 * Gets the categories.
	 * 
	 * @param dataModel
	 *            the data model
	 * 
	 * @return the categories
	 * 
	 * @generated
	 */
	static public Collection<Item> getCategories(Item dataModel) {
        return dataModel.getOutgoingItems(CadseGCST.DATA_MODEL_lt_CATEGORIES,true);
    }

	/**
	 * Adds the categories.
	 * 
	 * @param dataModel
	 *            the data model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addCategories(Item dataModel, Item value) throws CadseException {
        dataModel.addOutgoingItem(CadseGCST.DATA_MODEL_lt_CATEGORIES,value);
    }

	/**
	 * Removes the categories.
	 * 
	 * @param dataModel
	 *            the data model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeCategories(Item dataModel, Item value) throws CadseException {
        dataModel.removeOutgoingItem(CadseGCST.DATA_MODEL_lt_CATEGORIES,value);
    }

	/**
	 * get links 'types' from 'DataModel' to 'ItemType'.
	 * 
	 * @param dataModel
	 *            the data model
	 * 
	 * @return the types link
	 * 
	 * @generated
	 */
	static public List<Link> getTypesLink(Item dataModel) {
        return dataModel.getOutgoingLinks(CadseGCST.DATA_MODEL_lt_TYPES);
    }

	/**
	 * Gets the types all.
	 * 
	 * @param dataModel
	 *            the data model
	 * 
	 * @return the types all
	 * 
	 * @generated
	 */
	static public Collection<Item> getTypesAll(Item dataModel) {
        return dataModel.getOutgoingItems(CadseGCST.DATA_MODEL_lt_TYPES, false);
    }

	/**
	 * Gets the types.
	 * 
	 * @param dataModel
	 *            the data model
	 * 
	 * @return the types
	 * 
	 * @generated
	 */
	static public Collection<Item> getTypes(Item dataModel) {
        return dataModel.getOutgoingItems(CadseGCST.DATA_MODEL_lt_TYPES,true);
    }

	/**
	 * Adds the types.
	 * 
	 * @param dataModel
	 *            the data model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addTypes(Item dataModel, Item value) throws CadseException {
        dataModel.addOutgoingItem(CadseGCST.DATA_MODEL_lt_TYPES,value);
    }

	/**
	 * Removes the types.
	 * 
	 * @param dataModel
	 *            the data model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeTypes(Item dataModel, Item value) throws CadseException {
        dataModel.removeOutgoingItem(CadseGCST.DATA_MODEL_lt_TYPES,value);
    }

	/**
	 * get links 'enums' from 'DataModel' to 'EnumType'.
	 * 
	 * @param dataModel
	 *            the data model
	 * 
	 * @return the enums link
	 * 
	 * @generated
	 */
	static public List<Link> getEnumsLink(Item dataModel) {
        return dataModel.getOutgoingLinks(CadseGCST.DATA_MODEL_lt_ENUMS);
    }

	/**
	 * Gets the enums all.
	 * 
	 * @param dataModel
	 *            the data model
	 * 
	 * @return the enums all
	 * 
	 * @generated
	 */
	static public Collection<Item> getEnumsAll(Item dataModel) {
        return dataModel.getOutgoingItems(CadseGCST.DATA_MODEL_lt_ENUMS, false);
    }

	/**
	 * Gets the enums.
	 * 
	 * @param dataModel
	 *            the data model
	 * 
	 * @return the enums
	 * 
	 * @generated
	 */
	static public Collection<Item> getEnums(Item dataModel) {
        return dataModel.getOutgoingItems(CadseGCST.DATA_MODEL_lt_ENUMS,true);
    }

	/**
	 * Adds the enums.
	 * 
	 * @param dataModel
	 *            the data model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addEnums(Item dataModel, Item value) throws CadseException {
        dataModel.addOutgoingItem(CadseGCST.DATA_MODEL_lt_ENUMS,value);
    }

	/**
	 * Removes the enums.
	 * 
	 * @param dataModel
	 *            the data model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeEnums(Item dataModel, Item value) throws CadseException {
        dataModel.removeOutgoingItem(CadseGCST.DATA_MODEL_lt_ENUMS,value);
    }


	/**
	 * Gets the item types.
	 * 
	 * @param dataModel
	 *            the data model
	 * 
	 * @return the item types
	 */
	public static Item[] getItemTypes(Item dataModel) {
		if (dataModel == null) {
			return new Item[0];
		}
		Collection<Item> ret = dataModel.getPartChildren(CadseGCST.DATA_MODEL_lt_TYPES);
		return ret.toArray(new Item[ret.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.DefaultWorkspaceManager#getWorkspaceModel(fr
	 * .imag.adele.cadse.core.Item)
	 */
	@Override
	public Item getWorkspaceModel(Item source) {
		return source.getPartParent();
	}

	/**
	 * Static get workspace model.
	 * 
	 * @param source
	 *            the source
	 * 
	 * @return the item
	 */
	public static Item staticGetWorkspaceModel(Item source) {
		return source.getPartParent();
	}

	/**
	 * Checks if is category.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return true, if is category
	 */
	public boolean isCategory(Item item) {
		Item parent = item.getPartParent();
		return (parent != null && parent.getType() == CadseGCST.DATA_MODEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#canDeleteItem(fr.imag
	 * .adele.cadse.core.Item)
	 */
	@Override
	public String canDeleteItem(Item item) {
		if (isCategory(item)) {
			return null;
		}
		return "Cannot delete a data-model";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag
	 * .adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		if (isCategory(item)) {
			return null;
		}
		return "Cannot rename a data-model";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#isAbstract(fr.imag.adele
	 * .cadse.core.Item, fr.imag.adele.cadse.core.LinkType)
	 */
	@Override
	public boolean isAbstract(Item parent, LinkType type) {
		if (parent != null && parent.getType() == CadseGCST.DATA_MODEL) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the item type.
	 * 
	 * @param datamodel
	 *            the datamodel
	 * @param s
	 *            the s
	 * 
	 * @return the item type
	 */
	public static Item getItemType(Item datamodel, String s) {
		for (Link l : datamodel.getOutgoingLinks()) {
			if (l.getLinkType() != CadseGCST.DATA_MODEL_lt_TYPES) {
				continue;
			}
			if (!l.isLinkResolved()) {
				continue;
			}
			Item it = l.getDestination();
			if (it.getName().equals(s)) {
				return it;
			}
		}
		return null;
	}

	/**
	 * Gets the or create category.
	 * 
	 * @param datamodel
	 *            the datamodel
	 * @param subpackage
	 *            the subpackage
	 * 
	 * @return the or create category
	 */
	public static Item getOrCreateCategory(Item datamodel, String subpackage) {
		String id = datamodel.getQualifiedName() + "." + subpackage;
		Item ret = null;
		try {
			ret = CadseCore.createItemIfNeed(id, subpackage, CadseGCST.DATA_MODEL, datamodel, null);
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * Gets the all categories.
	 * 
	 * @param datamodel
	 *            the datamodel
	 * 
	 * @return the all categories
	 */
	public static Item[] getAllCategories(Item datamodel) {
		if (datamodel == null) {
			return new Item[0];
		}
		HashSet<Item> ret = new HashSet<Item>();
		ret.add(datamodel);
		Set<Item> pass = new HashSet<Item>();
		pass.add(datamodel);
		while (pass.size() != 0) {
			Set<Item> nextpass = new HashSet<Item>();
			for (Item source : pass) {
				for (Link l : source.getOutgoingLinks()) {
					if (l.getLinkType() == CadseGCST.DATA_MODEL_lt_CATEGORIES && l.isLinkResolved()) {
						Item dest = l.getResolvedDestination();
						if (!ret.contains(dest)) {
							ret.add(dest);
							nextpass.add(dest);
						}
					}
				}
			}
			pass = nextpass;
		}

		return ret.toArray(new Item[ret.size()]);
	}

	/**
	 * Creer un item type dans cadseg � partir d'un nom, du data model et de son
	 * supertype.
	 * 
	 * @param shortName
	 *            The name of the new item type.
	 * @param datamodel
	 *            The data model where the new item type is inserted.
	 * @param superItem
	 *            The super type of the new type. the type of superType must be
	 *            cadseg's ItemType.
	 * 
	 * @return the item
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	public static Item createItemType(String shortName, Item datamodel, Item superItem) throws CadseException {

		// get the model
		LogicalWorkspace model = datamodel.getLogicalWorkspace();
		LogicalWorkspaceTransaction copy = model.createTransaction();
		// create the itemtype
		Item theitemtype = copy.createItem(CadseGCST.ITEM_TYPE, datamodel, CadseGCST.DATA_MODEL_lt_TYPES);

		// set the short name and compute the unique name from parent and parent
		// link type setting before
		CadseCore.setName(theitemtype, shortName);

		if (superItem != null) {
			theitemtype.createLink(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, superItem);
		}

		ItemTypeManager.setIsAbstractAttribute(theitemtype, false);

		createManager(copy, theitemtype, superItem);

		UUID id = theitemtype.getId();
		copy.commit();

		return model.getItem(id);
	}

	public static void createManager(LogicalWorkspaceTransaction copy, Item theitemtype, Item superItem)
			throws CadseException {
		// get the mapping model
		Item mappingModel = CadseDefinitionManager.getMappingModel(ItemTypeManager.getCadseDefinition(theitemtype));

		// create the manager
		Item managerItem = copy.createItem(CadseGCST.MANAGER, mappingModel, CadseGCST.MAPPING_MODEL_lt_MANAGERS);

		// set the short name and compute the unique name from parent and parent
		// link type setting before
		CadseCore.setName(managerItem, theitemtype.getName() + "-manager");

		// ManagerManager.setManagerType(managerItem, "default");
		ManagerManager.setHumanNameAttribute(managerItem, theitemtype.getName());
		ManagerManager.setUniqueNameTemplate(managerItem, "${#parent.qualified-name}{.}${#name}");
		ManagerManager.setDisplayNameTemplateAttribute(managerItem, "${#name}");
		// create a link form manager to theitemtype
		ManagerManager.setItemType(managerItem, theitemtype);

		if (superItem != null) {
			Item supermanager = ManagerManager.getManagerFromItemType(superItem);
			ManagerManager.setUniqueNameTemplate(managerItem, ManagerManager.getUniqueNameTemplate(supermanager));
			ManagerManager.setDisplayNameTemplateAttribute(managerItem, ManagerManager
					.getDisplayNameTemplateAttribute(supermanager));
		}
	}

	/**
	 * Creates the link type.
	 * 
	 * @param ltname
	 *            the ltname
	 * @param datamodel
	 *            the datamodel
	 * @param sourceName
	 *            the source name
	 * @param destinationName
	 *            the destination name
	 * 
	 * @return the item
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	public static Item createLinkType(String ltname, Item datamodel, String sourceName, String destinationName)
			throws CadseException {
		// get the model
		LogicalWorkspace model = datamodel.getLogicalWorkspace();
		LogicalWorkspaceTransaction copy = model.createTransaction();

		Item sourceItemType = getItemType(datamodel, sourceName);
		if (sourceItemType == null) {
			throw new CadseException("Cannot find the item type {0} from the data model {1}.", sourceName, datamodel
					.getQualifiedName());
		}
		Item destItemType = getItemType(datamodel, destinationName);
		if (destItemType == null) {
			throw new CadseException("Cannot find the item type {0} from the data model {1}.", destinationName,
					datamodel.getQualifiedName());
		}

		Item thelinktype = copy.createItem(CadseGCST.LINK_TYPE, sourceItemType,
				CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES);
		CadseCore.setName(thelinktype, ltname);
		LinkTypeManager.setMaxAttribute(thelinktype, -1);
		LinkTypeManager.setMinAttribute(thelinktype, 0);
		LinkTypeManager.setDestination(thelinktype, destItemType);
		copy.commit();

		return model.getItem(thelinktype.getId());

	}

	/**
	 * Delete link type.
	 * 
	 * @param ltname
	 *            the ltname
	 * @param datamodel
	 *            the datamodel
	 * @param sourceName
	 *            the source name
	 * @param throwE
	 *            the throw e
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	public static void deleteLinkType(String ltname, Item datamodel, String sourceName, boolean throwE)
			throws CadseException {
		Item sourceItemType = getItemType(datamodel, sourceName);
		if (sourceItemType == null) {
			if (throwE) {
				throw new CadseException("Cannot find the item type {0} from the data model {1}.", sourceName,
						datamodel.getQualifiedName());
			}
			return;
		}
		Item thelinktype = ItemTypeManager.getOutgoingLinkType(sourceItemType, ltname);
		if (thelinktype == null) {
			if (throwE) {
				throw new CadseException("Cannot find the link type {0} from the item type {1}.", ltname,
						sourceItemType.getQualifiedName());
			}
			return;
		}
		thelinktype.delete(true);

	}

	/**
	 * Gets the from workspace model.
	 * 
	 * @param modelName
	 *            the model name
	 * 
	 * @return the from workspace model
	 */
	public static Item getFromWorkspaceModel(String modelName) {
		Item wsModel = CadseDefinitionManager.getWorkspaceModel_static(modelName);
		if (wsModel != null) {
			return CadseDefinitionManager.getMainDataModel(wsModel);
		}
		return null;
	}

	public static String getQualifiedDM(Item dm) {
		if (dm.getPartParent().isInstanceOf(CadseGCST.CADSE_DEFINITION)) {
			return "default";
		}
		StringBuilder sb = new StringBuilder();
		while (dm.getPartParent().getType() != CadseGCST.CADSE_DEFINITION) {
			if (sb.length() != 0) {
				sb.insert(0, '.');
			}
			sb.insert(0, dm.getName());
			dm = dm.getPartParent();
		}
		return sb.toString();
	}

}
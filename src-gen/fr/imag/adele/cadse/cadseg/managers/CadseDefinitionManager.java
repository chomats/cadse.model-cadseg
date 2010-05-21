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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.cadseg.init.CadsegInit;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.InitAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.util.Convert;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

/**
 * The Class CadseDefinitionManager.
 */
public class CadseDefinitionManager extends CadseManager implements IModelWorkspaceManager, InitAction {
	public static final String	MAPPING				= "mapping";

	public static final String	BUILD_MODEL			= "build-model";

	public static final String	CONFIGURATION_MODEL	= "configuration-model";

	public static final String	DATA_MODEL			= "data-model";

	/** The Constant DEFAULT_SHORT_NAME. */
	public static final String	VIEW_MODEL			= "view-model";

	

	/**
	 * Gets the uUID.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the uUID
	 */
	public static UUID getIdRuntime(Item cadseDefinition) {
		if (cadseDefinition.getType() == CadseGCST.CADSE) {
			return cadseDefinition.getId();
		}
		
		String uuid_str = cadseDefinition.getAttribute(CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_);
		if (uuid_str == null || uuid_str.length() == 0) {
			UUID uuid = UUID.randomUUID();
			uuid_str = uuid.toString();
			try {
				cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_, uuid_str);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return uuid;
		}
		return UUID.fromString(uuid_str);
	}
	
	@Override
	public void init() {
		new CadsegInit().init();
	}
	
	/**
	 * Gets the uUID.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the uUID
	 */
	public static UUID getIdDef(Item cadseDefinition) {
		if (cadseDefinition.getType() == CadseGCST.CADSE) {
			return UUID.fromString(cadseDefinition.getAttribute(CadseGCST.CADSE_at_ID_DEFINITION_));
		}
		return cadseDefinition.getId();
	}
		
	/** The Constant DEFAULT_PACKAGE. */
	public final static String	DEFAULT_PACKAGE	= "model.workspace.";

	/**
	 * Instantiates a new cadse definition manager.
	 */
	public CadseDefinitionManager() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#computeUniqueName(fr.
	 * imag.adele.cadse.core.Item, java.lang.String,
	 * fr.imag.adele.cadse.core.Item, fr.imag.adele.cadse.core.LinkType)
	 */
	@Override
	public String computeQualifiedName(Item item, String shortid, Item parent, LinkType type) {
		if (shortid.contains("."))
			return shortid;				
		return CadseRuntime.CADSE_NAME_SUFFIX + shortid;
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
	 * Gets the package name.
	 * 
	 * @param wsModelItem
	 *            the ws model item
	 * 
	 * @return the package name
	 */
	public static String getPackageName(Item wsModelItem) {
		return wsModelItem.getAttribute(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_);
	}

	/**
	 * Gets the imports.
	 * 
	 * @param wsModelItem
	 *            the ws model item
	 * 
	 * @return the imports
	 */
	public static List<String> getImports(Item wsModelItem) {
		return wsModelItem.getAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_);
	}

	/**
	 * Checks for package.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return true, if successful
	 */
	static public boolean hasPackage(Item item) {
		String p = getPackageName(item);
		return (p != null && p.length() != 0);
	}

	/**
	 * Gets the default package.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param item
	 *            the item
	 * 
	 * @return the default package
	 */
	static public String getDefaultPackage(ContextVariable cxt, Item item) {
		String p = getPackageName(item);
		if (p != null && p.length() != 0) {
			return p;
		}
		return DEFAULT_PACKAGE + cxt.getAttribute(item, CadseGCST.ITEM_at_NAME_).toLowerCase();
	}

	/**
	 * TODO rename to getAllItemType.
	 * 
	 * @param cadseDefinition
	 *            the workspace model
	 * 
	 * @return the item types
	 */
	public static Item[] getItemTypes2(Item cadseDefinition) {
		Item dataModel = getMainDataModel(cadseDefinition);
		return ItemTypeManager.getAllItemType(dataModel);
	}

	/**
	 * Return the datamodel of the model cadseDefinition.
	 * 
	 * @param cadseDefinition
	 *            A cadseDefinition
	 * 
	 * @return A data model
	 */
	public static Item getMainDataModel(Item cadseDefinition) {
		Item dataModel = cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL, true);
		return dataModel;
	}

	/**
	 * Gets the view model.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the view model
	 */
	public static Item getViewModel(Item item) {
		Item viewModel = item.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL, true);
		if (viewModel == null) {
			List<Item> viewModels = CadseGCST.VIEW_MODEL.getItems();
			for (Item aViewModel : viewModels) {
				Item parentViewModel = aViewModel.getPartParent();
				if (parentViewModel == item) {
					// find good
					try {
						aViewModel.getPartParent(true);
					} catch (Throwable e) {
					}
					return aViewModel;
				}
			}
			try {
				viewModel = CadseCore.createItemIfNeed(null, VIEW_MODEL, CadseGCST.VIEW_MODEL, item,
						CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return viewModel;
	}

	/**
	 * set a link 'view-model' from 'CadseDefinition' to 'ViewModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setViewModel(Item cadseDefinition, Item value) throws CadseException {
		cadseDefinition.setOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL,value);
	}

	/**
	 * Gets the imports attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the imports attribute
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final List<String> getImportsAttribute(Item cadseDefinition) {
		try {
			List<String> list = cadseDefinition.getAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_);

			if (list == null)
				return null;

			return new ArrayList<String>(list);
		} catch (Throwable t) {
			return new ArrayList<String>();
		}
	}

	/**
	 * Sets the imports attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param valueList
	 *            the value list
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void setImportsAttribute(Item cadseDefinition, List<String> valueList) {
		try {
			List<String> list = new ArrayList<String>(valueList);
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * Adds the imports attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @not generated
	 */
	@SuppressWarnings("unchecked")
	public static final void addImportsAttribute(Item cadseDefinition, String value) {
		try {
			List<String> list = (List<String>) cadseDefinition.getAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_);
			if (list == null) {
				list = new ArrayList<String>();
			}
			String setvalue = value;
			list.add(setvalue);
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_, list);
			((IGenerateContent) cadseDefinition.getContentItem()).generate(ContextVariableImpl.DEFAULT);
		} catch (Throwable t) {

		}
	}

	/**
	 * Removes the imports attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void removeImportsAttribute(Item cadseDefinition, String value) {
		try {

			List<String> list = cadseDefinition.getAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_);
			if (list == null) {
				return;
			}
			list.remove(value);
			if (list.size() == 0)
				cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_, null);
			else
				cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the packagename attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the packagename attribute
	 * 
	 * @generated
	 */
	public static final String getPackagenameAttribute(Item cadseDefinition) {
		return cadseDefinition.getAttributeWithDefaultValue(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_, null);
	}

	/**
	 * Sets the packagename attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setPackagenameAttribute(Item cadseDefinition, String value) {
		try {
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the vendor name attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the vendor name attribute
	 * 
	 * @generated
	 */
	public static final String getVendorNameAttribute(Item cadseDefinition) {
		return cadseDefinition.getAttributeWithDefaultValue(CadseGCST.CADSE_DEFINITION_at_VENDOR_NAME_, null);
	}

	/**
	 * Sets the vendor name attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setVendorNameAttribute(Item cadseDefinition, String value) {
		try {
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_VENDOR_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final String getVersionCadseAttribute(Item cadseDefinition) {
		return cadseDefinition.getAttributeWithDefaultValue(CadseGCST.CADSE_DEFINITION_at_VERSION_CADSE_, null);
	}

	/**
	 * @generated
	 */
	public static final void setVersionCadseAttribute(Item cadseDefinition, String value) {
		try {
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_VERSION_CADSE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final String getCommentaryAttribute(Item cadseDefinition) {
		return cadseDefinition.getAttributeWithDefaultValue(CadseGCST.CADSE_DEFINITION_at_COMMENTARY_, null);
	}

	/**
	 * @generated
	 */
	public static final void setCommentaryAttribute(Item cadseDefinition, String value) {
		try {
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_COMMENTARY_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getCadseNameAttribute(Item cadseDefinition) {
		return cadseDefinition.getAttributeWithDefaultValue(CadseGCST.CADSE_DEFINITION_at_CADSE_NAME_, null);
	}

	/**
		@generated
	*/
	public static final void setCadseNameAttribute(Item cadseDefinition, String value) {
		try {
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_CADSE_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getIdRuntimeAttribute(Item cadseDefinition) {
		return cadseDefinition.getAttributeWithDefaultValue(CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_, null);
	}

	/**
		@generated
	*/
	public static final void setIdRuntimeAttribute(Item cadseDefinition, String value) {
		try {
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the mapping model.
	 * 
	 * @param wsModel
	 *            the ws model
	 * 
	 * @return the mapping model
	 */
	public static Item getMappingModel(Item wsModel) {
		Item mappingModel = wsModel.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_MAPPING, true);
		if (mappingModel == null) {
			try {
				mappingModel = CadseCore.createItemIfNeed(null, MAPPING, CadseGCST.MAPPING_MODEL, wsModel,
						CadseGCST.CADSE_DEFINITION_lt_MAPPING);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mappingModel;
	}

	

	

	/**
	 * Gets the builds the model.
	 * 
	 * @param wsmodel
	 *            the wsmodel
	 * 
	 * @return the builds the model
	 */
	public static Item getBuildModel(Item wsmodel) {
		Item buildModel = wsmodel.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_BUILD, true);
		return buildModel;
	}

	/**
	 * Gets the workspace model_static.
	 * 
	 * @param modelName
	 *            the model name
	 * 
	 * @return the workspace model_static
	 */
	public static Item getWorkspaceModel_static(String modelName) {
		LogicalWorkspace wsModel = CadseCore.getLogicalWorkspace();
		if (wsModel != null) {
			return wsModel.getItem(modelName);
		}
		return null;
	}
	
	/**
	 * Gets the workspace model_static.
	 * 
	 * @param modelName
	 *            the model name
	 * 
	 * @return the workspace model_static
	 */
	public Item getWorkspaceModel(Item source) {
		return source;
	}

	


	/**
	 * Gets the version.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the version
	 */
	public static int getVersion(Item cadseDefinition) {
		return Convert.toInt(cadseDefinition.getAttribute(CadseGCST.ITEM_at_TW_VERSION_),
				CadseGCST.ITEM_at_TW_VERSION_, 0);
	}

	/**
	 * get a link 'configuration' from 'WorkspaceModel' to 'ConfigurationModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the configuration link
	 * 
	 * @generated
	 */
	static public Link getConfigurationLink(Item cadseDefinition) {
		return cadseDefinition.getOutgoingLink(CadseGCST.CADSE_DEFINITION_lt_CONFIGURATION);
	}

	/**
	 * get all link destination 'configuration' from 'WorkspaceModel' to
	 * 'ConfigurationModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the configuration all
	 * 
	 * @generated
	 */
	static public Item getConfigurationAll(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_CONFIGURATION, false);
	}

	/**
	 * get resolved link destination 'configuration' from 'WorkspaceModel' to
	 * 'ConfigurationModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the configuration
	 * 
	 * @generated
	 */
	static public Item getConfiguration(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_CONFIGURATION, true);
	}

	/**
	 * set a link 'configuration' from 'CadseDefinition' to
	 * 'ConfigurationModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setConfiguration(Item cadseDefinition, Item value) throws CadseException {
		cadseDefinition.setOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_CONFIGURATION,value);
	}

	/**
	 * get a link 'build' from 'WorkspaceModel' to 'BuildModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the builds the link
	 * 
	 * @generated
	 */
	static public Link getBuildLink(Item cadseDefinition) {
		return cadseDefinition.getOutgoingLink(CadseGCST.CADSE_DEFINITION_lt_BUILD);
	}

	/**
	 * get all link destination 'build' from 'WorkspaceModel' to 'BuildModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the builds the all
	 * 
	 * @generated
	 */
	static public Item getBuildAll(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_BUILD, false);
	}

	/**
	 * get resolved link destination 'build' from 'WorkspaceModel' to
	 * 'BuildModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the builds the
	 * 
	 * @generated
	 */
	static public Item getBuild(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_BUILD, true);
	}

	/**
	 * set a link 'build' from 'CadseDefinition' to 'BuildModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setBuild(Item cadseDefinition, Item value) throws CadseException {
		cadseDefinition.setOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_BUILD,value);
	}

	/**
	 * get a link 'mapping' from 'WorkspaceModel' to 'MappingModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the mapping link
	 * 
	 * @generated
	 */
	static public Link getMappingLink(Item cadseDefinition) {
		return cadseDefinition.getOutgoingLink(CadseGCST.CADSE_DEFINITION_lt_MAPPING);
	}

	/**
	 * get all link destination 'mapping' from 'WorkspaceModel' to
	 * 'MappingModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the mapping all
	 * 
	 * @generated
	 */
	static public Item getMappingAll(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_MAPPING, false);
	}

	/**
	 * get resolved link destination 'mapping' from 'WorkspaceModel' to
	 * 'MappingModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the mapping
	 * 
	 * @generated
	 */
	static public Item getMapping(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_MAPPING, true);
	}

	/**
	 * set a link 'mapping' from 'CadseDefinition' to 'MappingModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setMapping(Item cadseDefinition, Item value) throws CadseException {
		cadseDefinition.setOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_MAPPING,value);
	}

	/**
	 * get a link 'view-model' from 'WorkspaceModel' to 'ViewModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the view model link
	 * 
	 * @generated
	 */
	static public Link getViewModelLink(Item cadseDefinition) {
		return cadseDefinition.getOutgoingLink(CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL);
	}

	/**
	 * get all link destination 'view-model' from 'WorkspaceModel' to
	 * 'ViewModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the view model all
	 * 
	 * @generated
	 */
	static public Item getViewModelAll(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL, false);
	}

	/**
	 * get a link 'data-model' from 'WorkspaceModel' to 'DataModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the data model link
	 * 
	 * @generated
	 */
	static public Link getDataModelLink(Item cadseDefinition) {
		return cadseDefinition.getOutgoingLink(CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL);
	}

	/**
	 * get all link destination 'data-model' from 'WorkspaceModel' to
	 * 'DataModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the data model all
	 * 
	 * @generated
	 */
	static public Item getDataModelAll(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL, false);
	}

	/**
	 * get resolved link destination 'data-model' from 'WorkspaceModel' to
	 * 'DataModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the data model
	 * 
	 * @generated
	 */
	static public Item getDataModel(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL, true);
	}

	/**
	 * set a link 'data-model' from 'CadseDefinition' to 'DataModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setDataModel(Item cadseDefinition, Item value) throws CadseException {
		cadseDefinition.setOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL,value);
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @seefede.workspace.dependencies.eclipse.java.fix.IFixManager#
	 * findItemWithExportPackageWithType(java.lang.String, java.lang.String)
	 */
	public Item[] findItemWithExportPackageWithType(String qualifiedPackage, String typeDef) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.dependencies.eclipse.java.fix.IFixManager#findLT(fr.imag
	 * .adele.cadse.core.Item, fr.imag.adele.cadse.core.Item)
	 */
	public LinkType findLT(Item itemSource, Item destination) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public boolean hasImageByItem() {
		return false;
	}

}

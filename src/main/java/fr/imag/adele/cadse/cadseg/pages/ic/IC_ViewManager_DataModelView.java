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
package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewLinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class IC_ViewManager_DataModelView.
 */
public class IC_ViewManager_DataModelView extends IC_DataModelView {

	
	public IC_ViewManager_DataModelView() {
	}
	
	@Override
	public void init() throws CadseException {
		Item view = getItem();
		Item cadsedef = ViewManager.getCadsegModel(view);
		datamodel = CadseDefinitionManager.getDependenciesCadsesAndMe(cadsedef);
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#createViewLinkType(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	protected void createViewLinkType(Item item) {
		ViewManager.createViewLinkType(getViewmodel(), item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#createViewItemType(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	protected void createViewItemType(Item item) {
		ViewManager.createViewItemType(getViewmodel(), item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#setIsFirstElementChecked(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.Item, boolean)
	 */
	@Override
	public void setIsFirstElementChecked(Item itemtype, Item viewitemtype, boolean b) {
		viewitemtype = ViewManager.getViewItemType(getViewmodel(), itemtype);
		if (viewitemtype != null) {
			ViewItemTypeManager.setIsRootElementAttribute(viewitemtype, b);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#setAggregationChecked(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.Item, boolean)
	 */
	@Override
	public void setAggregationChecked(Item linktype, Item viewlinktype, boolean b) {
		if (viewlinktype != null) {
			ViewLinkTypeManager.setAggregationAttribute(viewlinktype, b);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#setCanCreateItemChecked(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.Item, boolean)
	 */
	@Override
	public void setCanCreateItemChecked(Item linktype, Item viewlinktype, boolean b) {
		if (viewlinktype != null) {
			ViewLinkTypeManager.setCanCreateItemAttribute(viewlinktype, b);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#setCanCreateLinkChecked(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.Item, boolean)
	 */
	@Override
	public void setCanCreateLinkChecked(Item linktype, Item viewlinktype, boolean b) {
		if (viewlinktype != null) {
			ViewLinkTypeManager.setCanCreateLinkAttribute(viewlinktype, b);
		}
	}

}
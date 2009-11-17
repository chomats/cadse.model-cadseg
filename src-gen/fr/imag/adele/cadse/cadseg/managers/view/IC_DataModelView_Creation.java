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

package fr.imag.adele.cadse.cadseg.managers.view;

import java.util.HashMap;
import java.util.Map;

import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.AbstractModelController;

/**
 * The Class IC_DataModelView_Creation.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_DataModelView_Creation extends IC_DataModelView {
	
	class DataModelMC extends AbstractModelController {
		
	}

	/** The value. */
	Map<Item, Integer>	value		= new HashMap<Item, Integer>();

	/** The Constant AGGREGATION. */
	static final int	AGGREGATION	= 0x0001;

	/** The Constant CAN_LINK. */
	static final int	CAN_LINK	= 0x0002;

	/** The Constant CAN_ITEM. */
	static final int	CAN_ITEM	= 0x0004;

	/** The Constant ROOT_ITEM. */
	static final int	ROOT_ITEM	= 0x0008;

	/**
	 * Instantiates a new i c_ data model view_ creation.
	 * 
	 * @param datamodel
	 *            the datamodel
	 * @param viewmodel
	 *            the viewmodel
	 */
	public IC_DataModelView_Creation(Item[] datamodel, Item viewmodel) {
		super(datamodel, viewmodel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#isAggregationChecked(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public boolean isAggregationChecked(Item linktype, Item viewlinktype) {
		int ret = isSet(linktype, AGGREGATION);
		if (ret != -1) {
			return ret != 0;
		}
		if (viewlinktype == null) {
			return LinkManager.isAggregation(linktype);
		}
		return super.isAggregationChecked(linktype, viewlinktype);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#isCanCreateItemChecked(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public boolean isCanCreateItemChecked(Item linktype, Item viewlinktype) {
		int ret = isSet(linktype, CAN_ITEM);
		if (ret != -1) {
			return ret != 0;
		}
		if (viewlinktype == null) {
			return LinkManager.isAggregation(linktype);
		}
		return super.isCanCreateItemChecked(linktype, viewlinktype);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#isCanCreateLinkChecked(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public boolean isCanCreateLinkChecked(Item linktype, Item viewlinktype) {
		int ret = isSet(linktype, CAN_LINK);
		if (ret != -1) {
			return ret != 0;
		}
		if (viewlinktype == null) {
			return true;
		}
		return super.isCanCreateLinkChecked(linktype, viewlinktype);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#isFirstElementChecked(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public boolean isFirstElementChecked(Item itemtype, Item viewitemtype) {
		int ret = isSet(itemtype, ROOT_ITEM);
		if (ret != -1) {
			return ret != 0;
		}
		if (viewitemtype == null) {
			return ItemTypeManager.isIsRootElementAttribute(itemtype);
		}
		return super.isFirstElementChecked(itemtype, viewitemtype);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#setAggregationChecked(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.Item, boolean)
	 */
	@Override
	public void setAggregationChecked(Item linktype, Item viewlinktype, boolean b) {
		setValue(linktype, AGGREGATION, b);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#setCanCreateItemChecked(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.Item, boolean)
	 */
	@Override
	public void setCanCreateItemChecked(Item linktype, Item viewlinktype, boolean b) {
		setValue(linktype, CAN_ITEM, b);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#setCanCreateLinkChecked(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.Item, boolean)
	 */
	@Override
	public void setCanCreateLinkChecked(Item linktype, Item viewlinktype, boolean b) {
		setValue(linktype, CAN_LINK, b);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.view.IC_DataModelView#setIsFirstElementChecked(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.Item, boolean)
	 */
	@Override
	public void setIsFirstElementChecked(Item itemtype, Item viewitemtype, boolean b) {
		setValue(itemtype, ROOT_ITEM, b);
	}

	/**
	 * Checks if is set.
	 * 
	 * @param key
	 *            the key
	 * @param kind
	 *            the kind
	 * 
	 * @return the int
	 */
	int isSet(Item key, int kind) {
		Integer i = value.get(key);
		if (i == null) {
			return -1;
		}
		return ((i.intValue() & kind) != 0) ? 1 : 0;
	}

	/**
	 * Sets the value.
	 * 
	 * @param key
	 *            the key
	 * @param kind
	 *            the kind
	 * @param v
	 *            the v
	 */
	void setValue(Item key, int kind, boolean v) {
		Integer i = value.get(key);
		int newi = 0;
		if (i != null) {
			newi = i.intValue();
		}
		if (v) {
			newi |= kind;
		} else {
			newi &= ~kind;
		}
		value.put(key, newi);
	}

	public AbstractModelController mc() {
		return new MC();
	}
}
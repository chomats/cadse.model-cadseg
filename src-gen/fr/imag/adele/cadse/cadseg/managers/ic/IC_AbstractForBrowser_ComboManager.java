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
package fr.imag.adele.cadse.cadseg.managers.ic;

import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_AbstractForBrowser_Combo;

// TODO message
/**
 * The Class IC_AbstractForBrowser_ComboManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_AbstractForBrowser_ComboManager extends InteractionControllerManager implements IItemManager {

	

	/** The Constant SELECT_TITLE_ATTRIBUTE. */
	public static final String	SELECT_TITLE_ATTRIBUTE		= "select-title";

	/** The Constant SELECT_MESSAGE_ATTRIBUTE. */
	public static final String	SELECT_MESSAGE_ATTRIBUTE	= "select-messsage";

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
		@generated
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager#getDefaultClassName()
	 */
	@Override
	public Class<?> getDefaultClassName() {
		return IC_AbstractForBrowser_Combo.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateMeItem(Item itemParent, LinkType lt, ItemType destType) {
		Item field = itemParent;
		if (field == null)
			return "cannot find the parent of "+itemParent;
		Item attribute = FieldManager.getAttribute(field);
		if (attribute == null) {
			return "select an attribute before";
		}
		if (itemParent.getType() != CadseGCST.DBROWSER && itemParent.getType() != CadseGCST.DCOMBO) {
			return "It's not a browser field or a combo field";
		}
		return null;

	}

	

}

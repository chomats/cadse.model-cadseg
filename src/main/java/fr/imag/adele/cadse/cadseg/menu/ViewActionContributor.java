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
package fr.imag.adele.cadse.cadseg.menu;

import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.view.LinkTypeViewAction;
import fr.imag.adele.cadse.cadseg.managers.view.ViewItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Menu;
import fr.imag.adele.cadse.core.ui.AbstractActionContributor;
import fr.imag.adele.cadse.core.ui.view.ViewDescription;

public class ViewActionContributor extends AbstractActionContributor {

	@Override
	public void contributeMenuAction(ViewDescription viewDescription,
			Menu menu, IItemNode[] selection) {
		if (selection.length == 1) {
			Item item = selection[0].getItem();
			if (item != null) {
				if (item.isInstanceOf(CadseGCST.VIEW_ITEM_TYPE)) {
					vit_contributeMenuNewAction(menu, item);
				}
			}
		}
	}
	
	
	public void vit_contributeMenuNewAction(Menu menu, Item view) {
		if (view.isResolved()) {
			Item itemtype = ViewItemTypeManager.getItemType(view);
			if (itemtype != null) {
				Item[] linkTypes = ItemTypeManager.getOugoingLinkTypes(itemtype);
				for (Item acat : linkTypes) {
					if (acat.getIncomingItem(CadseGCST.VIEW_LINK_TYPE_lt_LINK_TYPE) == null)
						menu.insert(IMenuAction.NEW_MENU_ID, new LinkTypeViewAction(view, acat), true);
				}
			}
		}
	}

}

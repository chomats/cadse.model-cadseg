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
package fr.imag.adele.cadse.cadseg.managers.actions;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;

/**
 * The Class MenuManager.
 * 
 * @generated
 */
public class MenuManager extends MenuAbstractManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public MenuManager() {
		super();
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
	 * get links 'children' from 'Menu' to 'MenuAbstract'.
	 * 
	 * @param menu
	 *            the menu
	 * 
	 * @return the children link
	 * 
	 * @generated
	 */
	static public List<Link> getChildrenLink(Item menu) {
        return menu.getOutgoingLinks(CadseGCST.MENU_lt_CHILDREN);
    }

	/**
	 * Gets the children all.
	 * 
	 * @param menu
	 *            the menu
	 * 
	 * @return the children all
	 * 
	 * @generated
	 */
	static public Collection<Item> getChildrenAll(Item menu) {
        return menu.getOutgoingItems(CadseGCST.MENU_lt_CHILDREN, false);
    }

	/**
	 * Gets the children.
	 * 
	 * @param menu
	 *            the menu
	 * 
	 * @return the children
	 * 
	 * @generated
	 */
	static public Collection<Item> getChildren(Item menu) {
        return menu.getOutgoingItems(CadseGCST.MENU_lt_CHILDREN,true);
    }

	/**
	 * Adds the children.
	 * 
	 * @param menu
	 *            the menu
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addChildren(Item menu, Item value) throws CadseException {
        menu.addOutgoingItem(CadseGCST.MENU_lt_CHILDREN,value);
    }

	/**
	 * Removes the children.
	 * 
	 * @param menu
	 *            the menu
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeChildren(Item menu, Item value) throws CadseException {
        menu.removeOutgoingItem(CadseGCST.MENU_lt_CHILDREN,value);
    }

}

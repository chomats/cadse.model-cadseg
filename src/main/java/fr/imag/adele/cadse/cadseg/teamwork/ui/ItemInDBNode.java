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
package fr.imag.adele.cadse.cadseg.teamwork.ui;

import java.util.UUID;

import fede.workspace.tool.view.ItemInViewer;
import fede.workspace.tool.view.node.AbstractCadseViewNode;
import fede.workspace.tool.view.node.CadseViewModelController;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * A tree node which represents an item in a Teamwork repository.
 * 
 * @author Thomas
 *
 */
public class ItemInDBNode extends AbstractCadseViewNode {

	final UUID	_itemId;
	final ItemType _itemType;
	private String _itemName;
	
	public ItemInDBNode(CadseViewModelController viewer, AbstractCadseViewNode parent, 
			UUID itemId, String itemName, ItemType itemType) {
		super(ItemInViewer.ITEM, parent);
		_itemId = itemId;
		_itemName = itemName;
		_itemType = itemType;
		ctl = viewer;
	}
	
	public UUID getItemId() {
		return _itemId;
	}
	
	@Override
	public Object getElementModel() {
		return _itemId;
	}
	
	@Override
	public Item getItem() {
		return null;
	}
	@Override
	public Link getLink() {
		return null;
	}
	@Override
	public LinkType getLinkType() {
		return null;
	}
	
	@Override
	public ItemType getItemType() {
		return _itemType;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public String getName() {
		return _itemName;
	}
	
	public String getItemName() {
		return _itemName;
	}
}

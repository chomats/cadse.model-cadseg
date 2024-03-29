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

import fr.imag.adele.cadse.core.Item;
import fede.workspace.tool.view.node.AbstractCadseViewNode;
import fede.workspace.tool.view.node.CadseViewModelController;
import fede.workspace.tool.view.node.ItemNode;

/**
 * A tree node representing an item.
 * A flag is used to decide if children are shown or not.
 * 
 * @author Thomas
 *
 */
public class ItemNodeWithoutChildren extends ItemNode {

	private boolean _showChildren;

	public ItemNodeWithoutChildren(CadseViewModelController viewer,
			AbstractCadseViewNode parent, Item item, boolean showChildren) {
		super(viewer, parent, item);
		_showChildren = showChildren;
	}

	@Override
	public AbstractCadseViewNode[] getChildren() {
		if (!_showChildren)
			return new AbstractCadseViewNode[0];
			
		return super.getChildren();
	}
}

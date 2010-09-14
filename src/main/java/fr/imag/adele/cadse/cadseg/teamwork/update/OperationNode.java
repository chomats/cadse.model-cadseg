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
package fr.imag.adele.cadse.cadseg.teamwork.update;

import fede.workspace.tool.view.ItemInViewer;
import fede.workspace.tool.view.node.AbstractCadseViewNode;
import fede.workspace.tool.view.node.CadseViewModelController;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

public class OperationNode extends AbstractCadseViewNode {
	
	private OpToPerform _op;

	protected OperationNode(CadseViewModelController viewer, AbstractCadseViewNode parent,
			OpToPerform op) {
		super(ItemInViewer.OTHER, parent);
		ctl = viewer;
		_op = op;
	}
	
	public OpToPerform getOperation() {
		return _op;
	}

	@Override
	public Object getElementModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Link getLink() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkType getLinkType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}

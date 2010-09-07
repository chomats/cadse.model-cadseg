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

import java.util.UUID;

import org.eclipse.swt.graphics.Image;

import fede.workspace.tool.view.node.AbstractCadseViewNode;
import fede.workspace.tool.view.node.ItemNode;
import fr.imag.adele.cadse.cadseg.teamwork.ui.ErrorDecorator;
import fr.imag.adele.cadse.core.Item;

public class UpdateErrorDecorator extends ErrorDecorator {
	
	private UpdateState _updateState;

	public UpdateErrorDecorator(UpdateState updateState) {
		_updateState = updateState;
	}

	@Override
	public Image decorateImage(Image image, Object element) {
		OpToPerform op = null;
		OperationNode opNode = null;
		if (element instanceof OperationNode) {
			opNode = (OperationNode) element;
			op = opNode.getOperation();
		}
		if (op == null)
			return null;
		
		UUID itemId = op.getItemId();
		Item item = _updateState.getTransaction().getItem(itemId);
		if (item == null)
			return null;
		
		if (_updateState.getOperationsToPerformErrors().isInError(itemId)) {
			return computeImage(image, "icons/delete_ovr.gif");
		}
		if (_updateState.isUpdated(itemId)) {
			return computeImage(image, "icons/add_ovr.gif");
		}

		return null;
	}

}

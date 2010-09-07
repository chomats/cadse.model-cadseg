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
package fr.imag.adele.cadse.cadseg.teamwork.commit;

import java.util.UUID;

import org.eclipse.swt.graphics.Image;

import fr.imag.adele.cadse.cadseg.teamwork.ui.ErrorDecorator;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;

public class CommitErrorDecorator extends ErrorDecorator {
	
	private CommitState _commitState;
	
	public CommitErrorDecorator(CommitState commitState) {
		_commitState = commitState;
	}

	public Image decorateImage(Image image, Object element) {
		if (image == null) {
			return null;
		}

		if (element == null) {
			return null;
		}

		if (!(element instanceof IItemNode)) {
			return null;
		}

		IItemNode itemNode = (IItemNode) element;
		Item item = itemNode.getItem();
		if (item == null) {
			return null;
		}
		UUID itemId = item.getId();
		if (_commitState.getErrors().isInError(itemId)) {
			return computeImage(image, "icons/delete_ovr.gif");
		}
		if (_commitState.isCommitted(itemId)) {
			return computeImage(image, "icons/add_ovr.gif");
		}

		return null;
	}
}

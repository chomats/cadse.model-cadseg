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

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.PlatformUI;

import fr.imag.adele.cadse.core.ChangeID;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.WorkspaceListener;
import fr.imag.adele.cadse.core.transaction.delta.ImmutableWorkspaceDelta;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fede.workspace.tool.view.node.AbstractCadseViewNode;

public class TWSelfViewContentProvider implements ITreeContentProvider {

	private AbstractCadseViewNode	_rootWS;
	TWSelfWorkspaceListener			_listener;

	public TWSelfViewContentProvider() {
	}

	class TWSelfWorkspaceListener extends WorkspaceListener {
		@Override
		public void workspaceChanged(ImmutableWorkspaceDelta delta) {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

				public void run() {
					if (_rootWS != null) {
						_rootWS.recomputeChildren();
					}
				}
			});
		}
	}

	public void dispose() {
		if (_listener != null) {
			CadseCore.getLogicalWorkspace().removeListener(_listener);
		}
	}

	public Object[] getElements(Object parent) {
		if (parent instanceof AbstractCadseViewNode) {
			AbstractCadseViewNode isrt = (AbstractCadseViewNode) parent;
			return isrt.getChildren();
		}
		return getChildren(parent);
	}

	public Object[] getChildren(Object parent) {

		if (parent instanceof AbstractCadseViewNode) {
			return ((AbstractCadseViewNode) parent).getChildren();
		}

		return new Object[0];
	}

	public Object getParent(Object child) {
		if (child instanceof AbstractCadseViewNode) {
			return ((AbstractCadseViewNode) child).getParent();
		}
		return null;
	}

	public boolean hasChildren(Object parent) {
		return getChildren(parent).length != 0;
	}

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		_rootWS = (AbstractCadseViewNode) newInput;
		if (_listener == null) {
			_listener = new TWSelfWorkspaceListener();
			CadseCore.getLogicalWorkspace().addListener(_listener, 0xFFFFF);
		}

	}

	public void notifieChangeEvent(ChangeID id, final Object... values) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			public void run() {
				if (_rootWS != null) {
					_rootWS.recomputeChildren();
				}
			}
		});
	}

	// private void recomputeChildren(Item item) {
	// recomputeChildren(item, _rootWS);
	// }

	private void recomputeChildren(Item item, AbstractCadseViewNode current) {
		Item currentItem = current.getItem();
		if (currentItem != null && item.equals(currentItem)) {
			current.recomputeChildren();
		}
		if (current.hasChildren()) {
			for (AbstractCadseViewNode childIIV : current.getChildren()) {
				recomputeChildren(item, childIIV);
			}
		}
	}

	public boolean isItemType(ItemType it) {
		return true;
	}

}

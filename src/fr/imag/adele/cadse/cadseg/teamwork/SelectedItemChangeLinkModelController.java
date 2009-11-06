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
package fr.imag.adele.cadse.cadseg.teamwork;

import java.text.MessageFormat;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ChangeID;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.WorkspaceListener;
import fr.imag.adele.cadse.core.delta.ImmutableItemDelta;
import fr.imag.adele.cadse.core.delta.ImmutableWorkspaceDelta;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;

public class SelectedItemChangeLinkModelController extends LinkModelController implements ItemSelectionListener {

	protected MyListener	_Listener;

	class MyListener extends WorkspaceListener {

		@Override
		public void workspaceChanged(ImmutableWorkspaceDelta delta) {
			ImmutableItemDelta itemDelta = delta.getItem(getItem());
			if (itemDelta == null) {
				return;
			}
			if (itemDelta.hasAddedOutgoingLink()) {
				for (Link l : itemDelta.getLinksAdded()) {
					if (l.getLinkType().equals(lt)) {
						getUIField().thisFieldHasChanged();
						return;
					}
				}
			}
			if (itemDelta.hasRemovedOutgoingLink()) {
				for (Link l : itemDelta.getLinksRemoved()) {
					if (l.getLinkType().equals(lt)) {
						getUIField().thisFieldHasChanged();
						return;
					}
				}
			}
			if (itemDelta.hasOrderOutgoingLinkChanged()) {
				getUIField().thisFieldHasChanged();
			}
		}
	}

	public SelectedItemChangeLinkModelController(boolean mandatory, String msg) {
		super(mandatory, msg);
	}

	@Override
	public void init() throws CadseException {
		item = getItem();
		if (item == null) {
			return;
		}

		selectItem(item);
	}

	@Override
	public Object getValue() {
		if (item != null) {
			return super.getValue();
		}

		return null;
	}

	public void selectItem(Item newItem) {
		if (item != null) {
			deselectItem(item);
		}
		item = newItem;

		if (!item.isInstanceOf(lt.getSource())) {
			throw new AssertionError(MessageFormat.format("The link type {0} in the item type {1} is bad.",
					getUIField().getAttributeName(), item.getType().getName()));
		}

		_Listener = new MyListener();
		item.addListener(_Listener, ChangeID.CREATE_OUTGOING_LINK.ordinal() + ChangeID.ORDER_OUTGOING_LINK.ordinal()
				+ ChangeID.DELETE_OUTGOING_LINK.ordinal());
	}

	@Override
	public void dispose() {
		noMoreSelectedItem();
	}

	public void noMoreSelectedItem() {
		if (item == null) {
			return;
		}

		item.removeListener(_Listener);
		item = null;
	}

	public void deselectItem(Item oldItem) {
		noMoreSelectedItem();
	}
}

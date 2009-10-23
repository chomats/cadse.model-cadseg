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

package fr.imag.adele.cadse.cadseg;

import fede.workspace.model.manager.properties.IC_ForCheckedViewer;
import fede.workspace.model.manager.properties.impl.ic.IC_AbstractForChecked;
import fede.workspace.model.manager.properties.impl.ui.DCheckedListUI;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemFilter;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.WorkspaceListener;
import fr.imag.adele.cadse.core.delta.ImmutableItemDelta;
import fr.imag.adele.cadse.core.delta.ImmutableWorkspaceDelta;
import fr.imag.adele.cadse.core.impl.CadseCore;

/**
 * The Class FieldsCheckedUserController.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
final public class FieldsCheckedUserController extends IC_AbstractForChecked implements IC_ForCheckedViewer {

	/** The page. */
	Item				_page		= null;

	/** The itemtype. */
	private Item		_itemtype;

	WorkspaceListener	_listener	= new WorkspaceListener() {
										@Override
										public void workspaceChanged(ImmutableWorkspaceDelta wd) {
											for (ImmutableItemDelta _id : wd.getItems()) {
												if (_id.isCreated() || _id.isDeleted()) {
													Item itemAttributeEvent = _id.getItem();
													Item itemItemTypeEvent = itemAttributeEvent.getPartParent();
													if (itemItemTypeEvent != null
															&& itemItemTypeEvent.getType() == CadseGCST.ITEM_TYPE
															&& (itemItemTypeEvent == _itemtype || ItemTypeManager
																	.isSuperTypeOf(itemItemTypeEvent, _itemtype))
															&& itemAttributeEvent.isInstanceOf(CadseGCST.ATTRIBUTE)) {
														((DCheckedListUI) getUIField()).setSource(getSources());
														break;
													}
												}
											}
										}
									};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.properties.IC_ForCheckedViewer#getSources()
	 */
	public Object[] getSources() {
		_itemtype = _page.getPartParent();
		Item[] attributes = ItemTypeManager.getAllAttributes(null, _itemtype, new ItemFilter() {

			public boolean accept(Item item) {
				return true; // !AttributeManager.isClassAttributeAttribute(item);
			}

			public boolean stop() {
				return false;
			}

		}, true);
		return attributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.properties.impl.ic.IC_Abstract#dispose()
	 */
	@Override
	public void dispose() {
		CadseCore.getLogicalWorkspace().removeListener(_listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.impl.ic.IC_Abstract#init()
	 */
	@Override
	public void init() {
		_page = getItem();
		CadseCore.getLogicalWorkspace().addListener(_listener, 0xFFFFF);
	}

	public ItemType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
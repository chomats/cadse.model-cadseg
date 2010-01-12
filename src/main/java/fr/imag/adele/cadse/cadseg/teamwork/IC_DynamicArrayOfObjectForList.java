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

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_AbstractForList;

/**
 * Interaction controller used to represent a dynamic array of objects.
 * 
 * @author Thomas
 * 
 */
public class IC_DynamicArrayOfObjectForList extends IC_AbstractForList implements ILabelProvider {

	private CompactUUID[]		_itemIds;
	private LogicalWorkspace	_wl;

	public IC_DynamicArrayOfObjectForList(String title, String message, CompactUUID[] itemIds, LogicalWorkspace wl) {
		this._message = message;
		this._title = title;
		this._itemIds = itemIds;
		_wl = wl;
	}

	
	@Override
	public Object[] getValues() {
		return _itemIds;
	}

	public void setValues(Object[] values) {
		this._itemIds = (CompactUUID[]) values;
	}

	public ItemType getType() {
		return null;
	}

	public ILabelProvider getLabelProvider() {
		return this;
	}

	public Image getImage(Object element) {
		return null;
	}

	public String getText(Object element) {
		if ((element == null) || !(element instanceof CompactUUID)) {
			return "";
		}

		CompactUUID itemId = (CompactUUID) element;
		Item item = _wl.getItem(itemId);
		return item.getQualifiedName();
	}

	public void addListener(ILabelProviderListener listener) {
		// do nothing
	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// do nothing
	}
}

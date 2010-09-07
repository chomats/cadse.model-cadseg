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

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;

/**
 * Represents definition of an item import operation.
 * 
 * @author Thomas
 *
 */
public class ImportOperation extends Operation {
	
	private int _rev;
	private String _itemName;
	private ItemType _itemType;

	public ImportOperation(UUID itemId, String itemName, int rev, ItemType itemType, boolean isRequirement) {
		super(itemId, OperationType.IMPORT, isRequirement);
		_rev = rev;
		_itemName = itemName;
		_itemType = itemType;
	}

	/**
	 * Returns revision number of item version to import.
	 * 
	 * @return revision number of item version to import.
	 */
	public int getDestinationRevNb() {
		return _rev;
	}
	
	/**
	 * Returns type of item to import.
	 * 
	 * @return type of item to import.
	 */
	public ItemType getItemType() {
		return _itemType;
	}

	@Override
	public String getRequirementDisplay(LogicalWorkspace lw) {
		return "Import of " + _itemName + "(" + _rev + ")";
	}
	
	@Override
	public Operation clone() {
		return new ImportOperation(getItemId(), getItemName(), _rev, _itemType, isRequirement());
	}

	/**
	 * Returns name of item to import.
	 * 
	 * @return name of item to import.
	 */
	public String getItemName() {
		return _itemName;
	}
}

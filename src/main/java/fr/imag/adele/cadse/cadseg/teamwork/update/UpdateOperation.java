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
 * Represents definition of an update operation.
 * 
 * @author Thomas
 *
 */
public class UpdateOperation extends Operation {
	
	private int _toRev;

	public UpdateOperation(UUID itemId, int toRev, boolean isRequirement) {
		super(itemId, OperationType.UPDATE, isRequirement);
		_toRev = toRev;
	}
	
	/**
	 * Returns destination item revision number.
	 * 
	 * @return destination item revision number.
	 */
	public int getDestinationRevNb() {
		return _toRev;
	}
	
	@Override
	public String getRequirementDisplay(LogicalWorkspace lw) {
		return "Update of " + getQualifiedItemName(lw) + "(" + 
		getDisplayOfRev(lw.getItem(getItemId()).getVersion()) + " to " + getDisplayOfRev(getDestinationRevNb()) + ")";
	}

	@Override
	public Operation clone() {
		return new UpdateOperation(getItemId(), _toRev, isRequirement());
	}
}

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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.imag.adele.cadse.core.LogicalWorkspace;

/**
 * Represents an operation (import, update or revert) of a global update operation.
 * 
 * @author Thomas
 *
 */
public abstract class Operation {
	
	protected enum OperationType { REVERT, UPDATE, IMPORT }

	private UUID _itemId;
	private OperationType _opType;
	private List<Operation> _causes = new ArrayList<Operation>();
	private List<Operation> _consequences = new ArrayList<Operation>();
	private boolean _isRequirement;
	
	protected Operation(UUID itemId, OperationType opType, boolean isRequirement) {
		_itemId = itemId;
		_opType = opType;
		_isRequirement = isRequirement;
	}
	
	/**
	 * Returns type of this operation.
	 * 
	 * @return type of this operation.
	 */
	public OperationType getType() {
		return _opType;
	}
	
	/**
	 * Returns true if it is a user requirement.
	 * 
	 * @return true if it is a user requirement.
	 */
	public boolean isRequirement() {
		return _isRequirement;
	}
	
	/**
	 * Returns a human readable .
	 * 
	 * @param lw
	 * @return
	 */
	public String getImpactDisplay(LogicalWorkspace lw) {
		return getRequirementDisplay(lw) + " from Requirements.";
	}
	
	/**
	 * Set specified operation as a cause of this operation.
	 * 
	 * @param op an operation
	 */
	public void addCause(Operation op) {
		_causes.add(op);
		op._consequences.add(this);
	}
	
	/**
	 * Returns list of operation which causes this operation.
	 * 
	 * @return list of operation which causes this operation.
	 */
	public List<Operation> getCauses() {
		return _causes;
	}
	
	/**
	 * Returns list of operation which are consequences of this operation.
	 * 
	 * @return list of operation which are consequences of cause this operation.
	 */
	public List<Operation> getConsequences() {
		return _consequences;
	}
	
	/**
	 * Return id of item related to this operation.
	 * 
	 * @return id of item related to this operation.
	 */
	public UUID getItemId() {
		return _itemId;
	}
	
	/**
	 * Return true if this operation is a revert one.
	 * 
	 * @return true if this operation is a revert one.
	 */
	public boolean isRevert() {
		return OperationType.REVERT.equals(_opType);
	}
	
	/**
	 * Return true if this operation is an update one.
	 * 
	 * @return true if this operation is an update one.
	 */
	public boolean isUpdate(){
		return OperationType.UPDATE.equals(_opType);
	}
	
	/**
	 * Return true if this operation is an import one.
	 * 
	 * @return true if this operation is an import one.
	 */
	public boolean isImport() {
		return OperationType.IMPORT.equals(_opType);
	}

	/**
	 * Returns a human readable label for this operation. 
	 * 
	 * @param lw the logical workspace
	 * @return a human readable label for this operation. 
	 */
	public abstract String getRequirementDisplay(LogicalWorkspace lw);
	
	/**
	 * Returns qualified name of item related to this operation.
	 * 
	 * @param lw a logical workspace
	 * @return qualified name of item related to this operation.
	 */
	protected String getQualifiedItemName(LogicalWorkspace lw) {
		return lw.getItem(getItemId()).getQualifiedDisplayName();
	}
	
	/**
	 * Returns human readable revision number of specified revision.
	 * 
	 * @param rev revision number
	 * @return human readable revision number of specified revision.
	 */
	protected String getDisplayOfRev(int rev) {
		if (rev == UpdateState.LAST_REV)
			return "HEAD";
		if (rev == 0)
			return "No";
		
		return Integer.toString(rev);
	}
	
	/**
	 * Returns a clone of this operation.
	 * 
	 * @return a clone of this operation.
	 */
	public abstract Operation clone();
}

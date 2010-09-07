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


import java.util.List;
import java.util.UUID;

import fr.imag.adele.cadse.core.LogicalWorkspace;

/**
 * Represents an operation in progress (import, update or revert) of a global update operation.
 * 
 * @author Thomas
 *
 */
public class OpToPerform extends Operation {
	
	private Operation _impactOp;
	
	private boolean _stateDone;
	private boolean _linksDone;
	private boolean _contentDone;

	public OpToPerform(Operation impactOp) {
		super(impactOp.getItemId(), impactOp.getType(), false);
		_impactOp = impactOp;
	}
	
	public void setContentUpdated() {
		_contentDone = true;
	}
	
	public void setLinksUpdated() {
		_linksDone = true;
	}
	
	public void setStateUpdated() {
		_stateDone = true;
	}
	
	/**
	 * Return true if related item content has been updated without errors.
	 * 
	 * @return true if related item content has been updated without errors.
	 */
	public boolean isContentUpdated() {
		return _contentDone;
	}
	
	/**
	 * Return true if related item state has been updated without errors.
	 * 
	 * @return true if related item state has been updated without errors.
	 */
	public boolean isStateUpdated() {
		return _stateDone;
	}
	
	/**
	 * Return true if related item outgoing links have been updated without errors.
	 * 
	 * @return true if related item outgoing links have been updated without errors.
	 */
	public boolean isLinksUpdated() {
		return _linksDone;
	}

	@Override
	public Operation clone() {
		return new OpToPerform(_impactOp);
	}

	@Override
	public String getRequirementDisplay(LogicalWorkspace lw) {
		return _impactOp.getRequirementDisplay(lw);
	}
	
	@Override
	public String getImpactDisplay(LogicalWorkspace lw) {
		return _impactOp.getImpactDisplay(lw);
	}

	@Override
	public List<Operation> getCauses() {
		return _impactOp.getCauses();
	}
	
	@Override
	public List<Operation> getConsequences() {
		return _impactOp.getConsequences();
	}
	
	public Operation getOriginalOperation() {
		return _impactOp;
	}
}

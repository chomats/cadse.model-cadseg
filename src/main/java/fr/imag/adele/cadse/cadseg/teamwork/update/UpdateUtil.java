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

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;

/**
 * Methods used to compute list of operation to perform 
 * from requirements and impacts of an update operation.
 * 
 * @author Thomas
 *
 */
public class UpdateUtil {

	/**
	 * Computes impacts of requirements.
	 * 
	 * @param updateState update state containing update operation definition.
	 */
	public static void computeImpacts(UpdateState updateState) {
		UpdateDefinition definition = updateState.getDefinition();
		definition.clearImpacts();
		
		for (Operation op : definition.getRequirements()) {
			computeDirectImpacts(op, updateState);
		}
		List<Operation> directImpacts = new ArrayList<Operation>();
		directImpacts.addAll(updateState.getDefinition().getImpacts());
		for (Operation op : directImpacts) {
			computePropagatedImpacts(op, updateState);
		}
	}

	private static void computeDirectImpacts(Operation requirementOp, UpdateState updateState) {
		
		// add this operation
		Operation impactOp = requirementOp.clone();
		impactOp.addCause(requirementOp);
		updateState.getDefinition().getImpacts().add(impactOp);
	}	
		
	private static void computePropagatedImpacts(Operation impactOp, UpdateState updateState) {
		LogicalWorkspace transaction = updateState.getTransaction();
		Item item = null;
		if (!impactOp.isImport()) {
			item = transaction.getItem(impactOp.getItemId());
		}
		
		// manage coupled propagation
		if (impactOp.isRevert()) {
			RevertOperation revertOp = (RevertOperation) impactOp;
			
			for (Link link : item.getIncomingLinks()) {
				if (!TWUtil.isCoupled(link.getLinkType()))
					continue;
				
				Item srcItem = link.getSource();
				RevertOperation srcImpactOp = new RevertOperation(srcItem.getId(), false);
				srcImpactOp.addCause(impactOp);
				updateState.getDefinition().getImpacts().add(impactOp);
			}
			for (Link link : item.getOutgoingLinks()) {
				if (!TWUtil.isCoupled(link.getLinkType()))
					continue;
				
				Item destItem = link.getDestination();
				RevertOperation destImpactOp = new RevertOperation(destItem.getId(), false);
				destImpactOp.addCause(impactOp);
				updateState.getDefinition().getImpacts().add(destImpactOp);
			}
		}
		
		//TODO finish compute impacts
	}

	public static void computeOperationsToPerform(UpdateState updateState) {
		UpdateDefinition definition = updateState.getDefinition();
		
		for (Operation impactOp : definition.getImpacts()) {
			OpToPerform opToPerform = new OpToPerform(impactOp);
			
			//TODO order operations
			updateState.addOperationToPerform(opToPerform);
		}
	}

}

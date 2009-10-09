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
package fr.imag.adele.cadse.cadseg.managers.attributes;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.delta.ItemDelta;
import fr.imag.adele.cadse.core.delta.SetAttributeOperation;
import fr.imag.adele.cadse.core.transaction.AbstractLogicalWorkspaceTransactionListener;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;

public class AttributeWLWC extends AbstractLogicalWorkspaceTransactionListener {

	public AttributeWLWC() {
		WorkspaceCST.ATTRIBUTE.addLogicalWorkspaceTransactionListener(this);
	}

	@Override
	public void notifyChangeAttribute(LogicalWorkspaceTransaction wc, ItemDelta item, SetAttributeOperation attOperation)
			throws CadseException {
		if (attOperation.getOldValue() == null) {
			return;
		}
		super.notifyChangeAttribute(wc, item, attOperation);
	}

	@Override
	public void validateChangeAttribute(LogicalWorkspaceTransaction wc, ItemDelta item,
			SetAttributeOperation attOperation) throws CadseException {
		// TODO Auto-generated method stub
		super.validateChangeAttribute(wc, item, attOperation);
	}
}

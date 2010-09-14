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

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.widgets.Shell;

import fr.imag.adele.cadse.cadseg.teamwork.db.DBUtil;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.teamwork.db.DBConnectionException;
import fr.imag.adele.teamwork.db.ModelVersionDBException;
import fr.imag.adele.teamwork.db.TransactionException;

public class AddItemToUpdateDialog extends AddItemForOperationDialog {
	
	protected Item _selectedItem;
	
	public AddItemToUpdateDialog(Shell parent, ILabelProvider labelProvider,
			ITreeContentProvider contentProvider) {
		super(parent, labelProvider, contentProvider);
	}
	
	protected void computeSelectedItem(Object selectedNode) {
		IItemNode selectedItemNode = (IItemNode) selectedNode;
		if (selectedNode != null)
			_selectedItem = selectedItemNode.getItem();
		else
			_selectedItem = null;
	}

	@Override
	protected Item getSelectedItemObj() {
		return _selectedItem;
	}

	protected String getRevisionStateStr() throws TransactionException,
			ModelVersionDBException, DBConnectionException {
		return DBUtil.getRevisionStateStr(_selectedItem, _selectedRev);
	}
	
	protected int[] getAllRevisions() throws TransactionException,
			ModelVersionDBException, DBConnectionException {
		return DBUtil.getAllRevisions(_selectedItem);
	}
}

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
package fr.imag.adele.cadse.cadseg.pages.dataModel;

import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;

/**
 * @generated
 */
public class AbstractItemTypeEvolDefinition_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item				item;

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldTWCommitKind;

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldTWImportKind;

	/**
	 * @generated
	 */
	protected AbstractItemTypeEvolDefinition_ModificationPage(String id, String label, String title,
			String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public AbstractItemTypeEvolDefinition_ModificationPage(Item item) {
		super("evol-definition", "Evolution definition", "Evolution definition", "", false, 3);
		this.item = item;
		setActionPage(null);
		addLast();

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

}

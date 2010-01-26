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
package fr.imag.adele.cadse.cadseg.pages.attributes;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;

/**
 * @generated
 */
public class UUIDModificationPage1_ModificationPage extends
		AttributeModificationPage1_ModificationPage {

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldCannotBeUndefined;

	/**
	 * @generated
	 */
	protected UUIDModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public UUIDModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "UUID", "UUID", "", false, 3);
		this.item = item;
		this.fieldCannotBeUndefined = createFieldCannotBeUndefined();
		this.fieldFinalValue = createFieldFinalValue();
		this.fieldIsList = createFieldIsList();
		this.fieldMustBeInitialized = createFieldMustBeInitialized();
		this.fieldNatif = createFieldNatif();
		this.fieldRequire = createFieldRequire();
		this.fieldTransient = createFieldTransient();
		setActionPage(null);
		addLast(this.fieldCannotBeUndefined, this.fieldFinalValue,
				this.fieldIsList, this.fieldMustBeInitialized, this.fieldNatif,
				this.fieldRequire, this.fieldTransient);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldCannotBeUndefined() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED,
				"cannot-be-undefined", EPosLabel.none, mc, null);
	}

}

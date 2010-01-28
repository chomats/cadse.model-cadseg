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
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;

/**
 * @generated
 */
public class StructModificationPage1_ModificationPage extends
		AttributeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DListUI fieldAttributes;

	/**
	 * @generated
	 */
	protected StructModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public StructModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Struct", "Struct", "", false, 3);
		this.item = item;
		this.fieldAttributes = createFieldAttributes();
		setActionPage(null);
		addLast(this.fieldAttributes);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DListUI createFieldAttributes() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.STRUCT_lt_ATTRIBUTES);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.STRUCT_lt_ATTRIBUTES);
		return new DListUI(CadseGCST.STRUCT_lt_ATTRIBUTES.getName(),
				"attributes", EPosLabel.top, mc, ic, true, false, false, false);
	}

}

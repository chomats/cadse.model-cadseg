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
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class DoubleModificationPage1_ModificationPage extends
		AttributeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DoubleModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public DoubleModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Double", "Double", "", false, 3);
		this.item = item;
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldIsList = createFieldIsList();
		setActionPage(null);
		addLast(this.fieldDefaultValue, this.fieldIsList);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @not generated
	 */
	public DTextUI createFieldDefaultValue() {
		return new DTextUI(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE,
				"default-value", EPosLabel.left, new MC_DoubleTextField(),
				null, 1, "", false, false, false);
	}

	@Override
	protected MC_AttributesItem createMCDefaultValue() {
		return new MC_DoubleTextField();
	}
}

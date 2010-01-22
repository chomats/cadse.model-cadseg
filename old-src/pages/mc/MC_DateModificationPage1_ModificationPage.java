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
package fr.imag.adele.cadse.cadseg.pages.mc;

import java.text.SimpleDateFormat;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class MC_DateModificationPage1_ModificationPage extends
		ModelControllerModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DTextUI fieldPattern;

	/**
	 * @generated
	 */
	protected MC_DateModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public MC_DateModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "MC_Date", "MC_Date", "", false, 3);
		this.item = item;
		this.fieldPattern = createFieldPattern();
		setActionPage(null);
		addLast(this.fieldPattern);

		registerListener();
	}

	@Override
	protected void registerListener() {
		super.registerListener();
		fieldPattern.addValidateContributor(this);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldPattern() {
		return new DTextUI(CadseGCST.MC_DATE_at_PATTERN, "pattern",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	@Override
	public boolean validValueChanged(UIField field, Object value) {
		if (field == fieldPattern) {
			try {
				new SimpleDateFormat((String) value);
			} catch (RuntimeException e) {
				setMessageError(fieldPattern.getLabel() + ": invalid format");
				return true;
			}
		}
		return super.validValueChanged(field, value);
	}
}

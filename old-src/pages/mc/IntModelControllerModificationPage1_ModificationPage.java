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
package fr.imag.adele.cadse.cadseg.pages.mc;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class IntModelControllerModificationPage1_ModificationPage extends
		ModelControllerModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DTextUI fieldErrorMsgMax;

	/**
	 * @generated
	 */
	protected DTextUI fieldErrorMsgMin;

	/**
	 * @generated
	 */
	protected DTextUI fieldMax;

	/**
	 * @generated
	 */
	protected DTextUI fieldMin;

	/**
	 * @generated
	 */
	protected DTextUI fieldDefaultValue;

	/**
	 * @generated
	 */
	protected IntModelControllerModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IntModelControllerModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "IntModelController", "IntModelController",
				"", false, 3);
		this.item = item;
		this.fieldErrorMsgMax = createFieldErrorMsgMax();
		this.fieldErrorMsgMin = createFieldErrorMsgMin();
		this.fieldMax = createFieldMax();
		this.fieldMin = createFieldMin();
		this.fieldDefaultValue = createFieldDefaultValue();
		setActionPage(null);
		addLast(this.fieldErrorMsgMax, this.fieldErrorMsgMin, this.fieldMax,
				this.fieldMin, this.fieldDefaultValue);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldErrorMsgMax() {
		return new DTextUI(CadseGCST.INT_MODEL_CONTROLLER_at_ERROR_MSG_MAX,
				"error-msg-max", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldErrorMsgMin() {
		return new DTextUI(CadseGCST.INT_MODEL_CONTROLLER_at_ERROR_MSG_MIN,
				"error-msg-min", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldMax() {
		return new DTextUI(CadseGCST.INT_MODEL_CONTROLLER_at_MAX, "max",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldMin() {
		return new DTextUI(CadseGCST.INT_MODEL_CONTROLLER_at_MIN, "min",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldDefaultValue() {
		return new DTextUI(CadseGCST.INT_MODEL_CONTROLLER_at_DEFAULT_VALUE,
				"default-value", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

}

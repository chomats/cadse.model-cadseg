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
package fr.imag.adele.cadse.cadseg.pages.attributes;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;

/**
 * @generated
 */
public class TimeModificationPage1_ModificationPage extends
		LongModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldInitWithTheCurrentTime;
	private DCheckBoxUI fieldCannotBeUndefined;

	/**
	 * @generated
	 */
	protected TimeModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public TimeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Time", "Time", "", false, 3);
		this.item = item;
		this.fieldNatif = createFieldNatif();
		this.fieldTransient = createFieldTransient();
		this.fieldCannotBeUndefined = createFieldCannotBeUndefined();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldFinalValue = createFieldFinalValue();
		this.fieldRequire = createFieldRequire();
		this.fieldMustBeInitialized = createFieldMustBeInitialized();
		this.fieldIsList = createFieldIsList();
		this.fieldInitWithTheCurrentTime = createFieldInitWithTheCurrentTime();
		setActionPage(null);
		addLast(this.fieldNatif, this.fieldTransient,
				this.fieldCannotBeUndefined, this.fieldDefaultValue,
				this.fieldFinalValue, this.fieldRequire,
				this.fieldMustBeInitialized, this.fieldIsList,
				this.fieldInitWithTheCurrentTime);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldCannotBeUndefined() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED,
				"cannot-be-undefined", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldInitWithTheCurrentTime() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.TIME_at_INIT_WITH_THE_CURRENT_TIME,
				"init-with-the-current-time", EPosLabel.none, mc, null);
	}

}

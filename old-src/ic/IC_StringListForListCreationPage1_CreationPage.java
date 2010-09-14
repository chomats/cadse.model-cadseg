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
package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;

/**
 * @generated
 */
public class IC_StringListForListCreationPage1_CreationPage extends
		IC_AbstractForListCreationPage1_CreationPage {

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldAllowDuplicate;

	/**
	 * @generated
	 */
	protected IC_StringListForListCreationPage1_CreationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public IC_StringListForListCreationPage1_CreationPage(Item parent,
			ItemType it, LinkType lt) {
		super("creation-page1", "Create IC_StringListForList",
				"Create IC_StringListForList", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldAllowDuplicate = createFieldAllowDuplicate();
		setActionPage(null);
		addLast(this.fieldAllowDuplicate);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldAllowDuplicate() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(
				CadseGCST.IC_STRING_LIST_FOR_LIST_at_ALLOW_DUPLICATE,
				"allowDuplicate", EPosLabel.none, mc, null);
	}

}

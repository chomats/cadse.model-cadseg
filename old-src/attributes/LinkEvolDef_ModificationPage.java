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

import fede.workspace.model.manager.properties.impl.mc.StringToEnumModelController;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.enumdef.TWDestEvol;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_EnumForBrowser_Combo;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;

/**
 * @generated
 */
public class LinkEvolDef_ModificationPage extends
		AttributeEvolDef_ModificationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldTWCoupled;
	/**
	 * @generated
	 */
	protected DBrowserUI fieldTWDestEvol;

	/**
	 * @generated
	 */
	protected LinkEvolDef_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public LinkEvolDef_ModificationPage(Item item) {
		super("evol-def", "Evolution definition", "Evolution definition",
				"The definition of the evolution control", false, 3);
		this.item = item;
		this.fieldTWEvol = createFieldTWEvol();
		this.fieldTWCoupled = createFieldTWCoupled();
		this.fieldTWDestEvol = createFieldTWDestEvol();
		this.fieldTWRevSpecific = createFieldTWRevSpecific();
		this.fieldTWCommitKind = createFieldTWCommitKind();
		this.fieldTWUpdateKind = createFieldTWUpdateKind();
		setActionPage(null);
		addLast(this.fieldTWEvol, this.fieldTWCoupled, this.fieldTWDestEvol,
				this.fieldTWRevSpecific, this.fieldTWCommitKind,
				this.fieldTWUpdateKind);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldTWCoupled() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.LINK_at_TWCOUPLED, "TWCoupled",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWDestEvol() {
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("??", "??",
				TWDestEvol.class);
		StringToEnumModelController mc = new StringToEnumModelController(
				TWDestEvol.class, TWDestEvol.immutable);
		return new DBrowserUI(CadseGCST.LINK_at_TWDEST_EVOL, "TWDestEvol",
				EPosLabel.left, mc, ic);
	}

}

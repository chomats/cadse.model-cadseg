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
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.enumdef.TWDestEvol;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_EnumForBrowser_Combo;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;

/**
 * @generated
 */
public class LinkEvolDef_CreationPage extends AttributeEvolDef_CreationPage {

	/**
	 * @generated
	 */
	protected DBrowserUI fieldTWDestEvol;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldTWCoupled;

	/**
	 * @generated
	 */
	protected LinkEvolDef_CreationPage(String id, String label, String title,
			String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public LinkEvolDef_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("evol-def", "Evolution definition", "Evolution definition",
				"Set the evolutions definition", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldTWEvol = createFieldTWEvol();
		this.fieldTWRevSpecific = createFieldTWRevSpecific();
		this.fieldTWDestEvol = createFieldTWDestEvol();
		this.fieldTWCoupled = createFieldTWCoupled();
		setActionPage(null);
		addLast(this.fieldTWEvol, this.fieldTWRevSpecific,
				this.fieldTWDestEvol, this.fieldTWCoupled);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
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

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldTWCoupled() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.LINK_at_TWCOUPLED, "TWCoupled",
				EPosLabel.none, mc, null);
	}

}

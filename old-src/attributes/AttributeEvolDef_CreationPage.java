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
import fr.imag.adele.cadse.core.enumdef.TWCommitKind;
import fr.imag.adele.cadse.core.enumdef.TWEvol;
import fr.imag.adele.cadse.core.enumdef.TWUpdateKind;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_EnumForBrowser_Combo;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;

/**
 * @generated
 */
public class AttributeEvolDef_CreationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item parent;

	/**
	 * @generated
	 */
	public ItemType it;

	/**
	 * @generated
	 */
	public LinkType lt;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldTWEvol;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldTWRevSpecific;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldTWCommitKind;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldTWUpdateKind;

	/**
	 * @generated
	 */
	protected AttributeEvolDef_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public AttributeEvolDef_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("evol-def", "Evolutions definition", "Evolutions definition",
				"Set the evolutions definition", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldTWEvol = createFieldTWEvol();
		this.fieldTWRevSpecific = createFieldTWRevSpecific();
		this.fieldTWCommitKind = createFieldTWCommitKind();
		this.fieldTWUpdateKind = createFieldTWUpdateKind();
		setActionPage(null);
		addLast(this.fieldTWEvol, this.fieldTWRevSpecific,
				this.fieldTWCommitKind, this.fieldTWUpdateKind);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWEvol() {
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("??", "??",
				TWEvol.class);
		StringToEnumModelController mc = new StringToEnumModelController(
				TWEvol.class, TWEvol.twImmutable);
		return new DBrowserUI(CadseGCST.ATTRIBUTE_at_TWEVOL, "TWEvol",
				EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldTWRevSpecific() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC,
				"TWRevSpecific", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWCommitKind() {
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("??", "??",
				TWCommitKind.class);
		StringToEnumModelController mc = new StringToEnumModelController(
				TWCommitKind.class, TWCommitKind.conflict);
		return new DBrowserUI(CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND,
				"TWCommitKind", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWUpdateKind() {
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("??", "??",
				TWUpdateKind.class);
		StringToEnumModelController mc = new StringToEnumModelController(
				TWUpdateKind.class, TWUpdateKind.merge);
		return new DBrowserUI(CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND,
				"TWUpdateKind", EPosLabel.left, mc, ic);
	}

}

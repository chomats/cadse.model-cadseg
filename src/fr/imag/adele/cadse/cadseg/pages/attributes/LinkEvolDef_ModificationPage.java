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

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ic.IC_EnumForBrowser_Combo;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.mc.StringToEnumModelController;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.enumdef.TWCommitKind;
import fr.imag.adele.cadse.core.enumdef.TWDestEvol;
import fr.imag.adele.cadse.core.enumdef.TWEvol;
import fr.imag.adele.cadse.core.enumdef.TWUpdateKind;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * @generated
 */
public class LinkEvolDef_ModificationPage extends AttributeEvolDef_ModificationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldTWCoupled;
	/**
	 * @generated
	 */
	protected DBrowserUI	fieldTWDestEvol;

	/**
	 * @generated
	 */
	protected LinkEvolDef_ModificationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public LinkEvolDef_ModificationPage(Item item) {
		super("evol-def", "Evolution definition", "Evolution definition", "The definition of the evolution control",
				false, 3);
		this.item = item;
		this.fieldTWEvol = createFieldTWEvol();
		this.fieldTWCoupled = createFieldTWCoupled();
		this.fieldTWDestEvol = createFieldTWDestEvol();
		this.fieldTWRevSpecific = createFieldTWRevSpecific();
		this.fieldTWCommitKind = createFieldTWCommitKind();
		this.fieldTWUpdateKind = createFieldTWUpdateKind();
		setActionPage(null);
		addLast(this.fieldTWEvol, this.fieldTWCoupled, this.fieldTWDestEvol, this.fieldTWRevSpecific,
				this.fieldTWCommitKind, this.fieldTWUpdateKind);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWEvol() {
		StringToEnumModelController mc = new StringToEnumModelController(TWEvol.class, TWEvol.twImmutable);
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("Select a value.", "Select a value.", TWEvol.class);
		return new DBrowserUI(WorkspaceCST.ATTRIBUTE_at_TWEVOL, "TWEvol", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldTWCoupled() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.LINK_at_TWCOUPLED, "TWCoupled", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldTWRevSpecific() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_TWREV_SPECIFIC, "TWRevSpecific", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWCommitKind() {
		StringToEnumModelController mc = new StringToEnumModelController(TWCommitKind.class, TWCommitKind.conflict);
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("Select a value.", "Select a value.",
				TWCommitKind.class);
		return new DBrowserUI(WorkspaceCST.ATTRIBUTE_at_TWCOMMIT_KIND, "TWCommitKind", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWUpdateKind() {
		StringToEnumModelController mc = new StringToEnumModelController(TWUpdateKind.class, TWUpdateKind.merge);
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("Select a value.", "Select a value.",
				TWUpdateKind.class);
		return new DBrowserUI(WorkspaceCST.ATTRIBUTE_at_TWUPDATE_KIND, "TWUpdateKind", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldTWDestEvol() {
		StringToEnumModelController mc = new StringToEnumModelController(TWDestEvol.class, TWDestEvol.immutable);
		IC_EnumForBrowser_Combo ic = new IC_EnumForBrowser_Combo("Select a value.", "Select a value.", TWDestEvol.class);
		return new DBrowserUI(WorkspaceCST.LINK_at_TWDEST_EVOL, "TWDestEvol", EPosLabel.left, mc, ic);
	}

}

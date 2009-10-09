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

import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.MaxModelController;
import fede.workspace.model.manager.properties.impl.mc.MinModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.managers.IC_LINK_Selection;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager.SelectionMC;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ui.EPosLabel;

/**
 * @generated
 */
public class LinkModificationPage1_ModificationPage extends AttributeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldComposition;

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldDestination;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldPart;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldAnnotation;

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldInverseLink;

	/**
	 * @generated
	 */
	protected DTextUI		fieldMin;

	/**
	 * @generated
	 */
	protected DTextUI		fieldMax;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldAggregation;

	private DCheckBoxUI		fieldNatif;

	/**
	 * @generated
	 */
	protected LinkModificationPage1_ModificationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated TODO inverse min and max
	 */
	public LinkModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Link", "Link", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldDestination = createFieldDestination();
		this.fieldInverseLink = createFieldInverseLink();
		this.fieldAggregation = createFieldAggregation();
		this.fieldPart = createFieldPart();
		this.fieldRequire = createFieldRequire();
		this.fieldAnnotation = createFieldAnnotation();
		this.fieldComposition = createFieldComposition();
		this.fieldMin = createFieldMin();
		this.fieldMax = createFieldMax();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldDestination, this.fieldInverseLink, this.fieldAggregation,
				this.fieldPart, this.fieldRequire, this.fieldAnnotation, this.fieldComposition, this.fieldMin,
				this.fieldMax);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldComposition() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.LINK_at_COMPOSITION, "composition", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldRequire() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.LINK_at_REQUIRE, "require", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldPart() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.LINK_at_PART, "part", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldAnnotation() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.LINK_at_ANNOTATION, "annotation", EPosLabel.none, mc, null);
	}

	/**
	 * @not generated
	 */
	public DBrowserUI createFieldInverseLink() {
		LinkModelController mc = new LinkModelController(false, null, WorkspaceCST.LINK_lt_INVERSE_LINK);
		// IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
		// "Select a value.", "Select a value.",
		// WorkspaceCST.LINK_lt_INVERSE_LINK);

		IC_LinkForBrowser_Combo_List ic = new IC_InverseLink("Select an inverse link", "Select an inverse link",
				WorkspaceCST.LINK_lt_INVERSE_LINK);
		return new DBrowserUI(WorkspaceCST.LINK_lt_INVERSE_LINK.getName(), "inverse-link", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldAggregation() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.LINK_at_AGGREGATION, "aggregation", EPosLabel.none, mc, null);
	}

	/**
	 * @not generated
	 */
	public DTextUI createFieldSelection() {
		return new DTextUI(WorkspaceCST.LINK_at_SELECTION, "selection", EPosLabel.left, new SelectionMC(),
				new IC_LINK_Selection(), 0, 1, "");
	}

	/**
	 * @not generated
	 */
	public DTextUI createFieldMin() {
		MinModelController minVC = new MinModelController();

		return new DTextUI(WorkspaceCST.ATTRIBUTE_at_MIN, "min", EPosLabel.left, minVC, minVC, 0, 1, "");
	}

	/**
	 * @not generated
	 */
	public DTextUI createFieldMax() {
		MaxModelController maxVC = new MaxModelController();

		return new DTextUI(WorkspaceCST.ATTRIBUTE_at_MAX, "max", EPosLabel.left, maxVC, maxVC, 0, 1, "");
	}

	public DCheckBoxUI createFieldNatif() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_NATIF, "natif", EPosLabel.none, mc, null);
	}

	public DBrowserUI createFieldDestination() {
		LinkModelController mc = new LinkModelController(true, "You must set the destination",
				WorkspaceCST.LINK_lt_DESTINATION);
		IC_LinkForBrowser_Combo_List ic = new LinkCreationPage1_CreationPage.IC_DestinationLinkForBrowser_Combo(
				"Select a destination", "Select a destination");
		return new DBrowserUI(WorkspaceCST.LINK_lt_DESTINATION.getName(), "destination", EPosLabel.left, mc, ic);
	}
}

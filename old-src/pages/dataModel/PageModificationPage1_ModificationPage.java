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

package fr.imag.adele.cadse.cadseg.pages.dataModel;

import fr.imag.adele.cadse.cadseg.ArrayOfAttributeFromFieldModelController;
import fr.imag.adele.cadse.cadseg.FieldsCheckedUserController;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_ForCheckedViewer;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckedListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class PageModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DTextUI fieldTitle;

	/**
	 * @generated
	 */
	protected DTextUI fieldDescription;

	/**
	    @generated
	 */
	protected DCheckedListUI fieldAttributes;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldIsRemoved;

	/**
	    @generated
	 */
	protected DTextUI fieldLabel;

	/**
	    @generated
	 */
	protected DTextUI fieldIdRuntime;

	/**
	    @generated
	 */
	protected DListUI fieldOverwrite;

	/**
	 * @generated
	 */
	protected PageModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * 
	 */
	public PageModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Page", "Page", "", false, 3);

		setActionPage(null);
		IC_ForCheckedViewer fieldcheckeduc = new FieldsCheckedUserController();

		// IntModelController hspan_vc = new IntModelController(1, 0,
		// "The number of columns must be > 0", null, 2);

		fieldIsRemoved = createFieldIsRemoved();
		fieldHspan = createFieldHspan();
		// FieldsCore.createIntField(
		// CadseGCST.PAGE_at_HSPAN, "number of columns", hspan_vc)
		addLast(FieldsCore.createShortNameField(), FieldsCore.createTextField(
				CadseGCST.PAGE_at_TITLE, "title"), FieldsCore.createTextField(
				CadseGCST.PAGE_at_DESCRIPTION, "description"), fieldHspan,
				fieldIsRemoved, FieldsCore.createCheckBox(
						CadseGCST.PAGE_at_CREATE_PAGE_ACTION,
						"create page action"), FieldsCore.createCheckBoxList(
						CadseGCST.PAGE_lt_FIELDS.getName(),
						"Fields in this dialog", fieldcheckeduc,
						new ArrayOfAttributeFromFieldModelController(item)));

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldTitle() {
		return new DTextUI(CadseGCST.PAGE_at_TITLE, "title", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldDescription() {
		return new DTextUI(CadseGCST.PAGE_at_DESCRIPTION, "description",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	/**
	    @generated
	 */
	public DCheckedListUI createFieldAttributes() {
		return new DCheckedListUI(CadseGCST.PAGE_lt_ATTRIBUTES.getName(),
				"attributes", EPosLabel.top, new MC_AttributesItem(), null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldIsRemoved() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.PAGE_at_IS_REMOVED, "is-removed",
				EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldLabel() {
		return new DTextUI(CadseGCST.PAGE_at_LABEL, "label", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldIdRuntime() {
		return new DTextUI(CadseGCST.PAGE_at_ID_RUNTIME, "id-runtime",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	/**
	    @generated
	 */
	public DListUI createFieldOverwrite() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.PAGE_lt_OVERWRITE);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.PAGE_lt_OVERWRITE);
		return new DListUI(CadseGCST.PAGE_lt_OVERWRITE.getName(), "overwrite",
				EPosLabel.top, mc, ic, true, false, false, false);
	}

}

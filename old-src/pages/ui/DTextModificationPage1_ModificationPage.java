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
package fr.imag.adele.cadse.cadseg.pages.ui;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class DTextModificationPage1_ModificationPage extends
		DisplayModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DTextUI fieldToolTip;

	/**
	 * @generated
	 */
	protected DTextUI fieldVerticalSpan;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldExtendsIC;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldExtendsMC;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldExtendsUI;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldMultiLigne;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldWrapLine;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldNoBorder;

	/**
	 * @generated
	 */
	protected DTextModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public DTextModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "DText", "DText", "", false, 3);
		this.item = item;
		this.fieldToolTip = createFieldToolTip();
		this.fieldVerticalSpan = createFieldVerticalSpan();
		this.fieldExtendsIC = createFieldExtendsIC();
		this.fieldExtendsMC = createFieldExtendsMC();
		this.fieldExtendsUI = createFieldExtendsUI();
		this.fieldMultiLigne = createFieldMultiLigne();
		this.fieldWrapLine = createFieldWrapLine();
		this.fieldNoBorder = createFieldNoBorder();
		setActionPage(null);
		addLast(this.fieldToolTip, this.fieldVerticalSpan, this.fieldExtendsIC,
				this.fieldExtendsMC, this.fieldExtendsUI, this.fieldMultiLigne,
				this.fieldWrapLine, this.fieldNoBorder);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldToolTip() {
		return new DTextUI(CadseGCST.DTEXT_at_TOOL_TIP, "tool tip",
				EPosLabel.left, new MC_AttributesItem(), null, 5, "", true,
				false, true);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldVerticalSpan() {
		IntModelController mc = new IntModelController(0, 0, null, null, null);
		return new DTextUI(CadseGCST.DTEXT_at_VERTICAL_SPAN, "vertical span",
				EPosLabel.left, mc, null, 1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsIC() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_IC, "extendsIC",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsMC() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_MC, "extendsMC",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsUI() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_UI, "extendsUI",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldMultiLigne() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DTEXT_at_MULTI_LINE, "multi line",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldWrapLine() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DTEXT_at_WRAP_LINE, "wrap line",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldNoBorder() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DTEXT_at_NO_BORDER, "no border",
				EPosLabel.none, mc, null);
	}

}

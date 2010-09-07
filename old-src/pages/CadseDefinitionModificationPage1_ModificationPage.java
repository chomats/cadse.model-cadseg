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
package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_DefaultForList;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_DefaultForList;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class CadseDefinitionModificationPage1_ModificationPage extends
		CadseRuntimeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected DTextUI fieldPackagename;

	/**
	 * @generated
	 */
	protected DListUI fieldImports;

	/**
	 * @generated
	 */
	protected DTextUI fieldVendorName;

	/**
	 * @generated
	 */
	protected DTextUI fieldVersionCadse;

	/**
	 * @generated
	 */
	protected DTextUI fieldCommentary;

	/**
	    @generated
	 */
	protected DTextUI fieldCadseName;

	/**
	    @generated
	 */
	protected DTextUI fieldDescription;

	/**
	 * @generated
	 */
	protected CadseDefinitionModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public CadseDefinitionModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Cadse definition", "Cadse definition", "",
				false, 3);
		this.item = item;
		this.fieldPackagename = createFieldPackagename();
		this.fieldImports = createFieldImports();
		this.fieldExtends = createFieldExtends();
		this.fieldVendorName = createFieldVendorName();
		this.fieldVersionCadse = createFieldVersionCadse();
		this.fieldDescription = createFieldDescription();
		this.fieldCommentary = createFieldCommentary();
		this.fieldCadseName = createFieldCadseName();
		setActionPage(null);
		addLast(this.fieldPackagename, this.fieldImports, this.fieldExtends,
				this.fieldVendorName, this.fieldVersionCadse,
				this.fieldDescription, this.fieldCommentary,
				this.fieldCadseName);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldPackagename() {
		return new DTextUI(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME,
				"main package", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DListUI createFieldImports() {
		IC_DefaultForList ic = new IC_DefaultForList("", "", false);
		MC_DefaultForList mc = new MC_DefaultForList(0, -1);
		return new DListUI(CadseGCST.CADSE_DEFINITION_at_IMPORTS, "imports",
				EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldVendorName() {
		return new DTextUI(CadseGCST.CADSE_DEFINITION_at_VENDOR_NAME,
				"vendor name", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldVersionCadse() {
		return new DTextUI(CadseGCST.CADSE_DEFINITION_at_VERSION_CADSE,
				"version cadse", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldCommentary() {
		return new DTextUI(CadseGCST.CADSE_DEFINITION_at_COMMENTARY,
				"commentary", EPosLabel.left, new MC_AttributesItem(), null,
				25, "", true, false, true);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldCadseName() {
		return new DTextUI(CadseGCST.CADSE_DEFINITION_at_CADSE_NAME,
				"cadse-name", EPosLabel.left, new MC_AttributesItem(), null, 1,
				"", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldDescription() {
		return new DTextUI(CadseGCST.CADSE_RUNTIME_at_DESCRIPTION,
				"description", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

}

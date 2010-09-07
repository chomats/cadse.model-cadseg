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
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 @generated
 */
public class CadseRuntimeRepo_ModificationPage extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DTextUI fieldItemRepoLogin;

	/**
	    @generated
	 */
	protected DTextUI fieldDefaultContentRepoURL;

	/**
	    @generated
	 */
	protected DTextUI fieldItemRepoPasswd;

	/**
	    @generated
	 */
	protected DTextUI fieldItemRepoURL;

	/**
	    @generated
	 */
	protected CadseRuntimeRepo_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public CadseRuntimeRepo_ModificationPage(Item item) {
		super("repo", "repo", "repo", "repo Page", false, 3);
		this.item = item;
		this.fieldItemRepoLogin = createFieldItemRepoLogin();
		this.fieldDefaultContentRepoURL = createFieldDefaultContentRepoURL();
		this.fieldItemRepoPasswd = createFieldItemRepoPasswd();
		this.fieldItemRepoURL = createFieldItemRepoURL();
		setActionPage(null);
		addLast(this.fieldItemRepoLogin, this.fieldDefaultContentRepoURL,
				this.fieldItemRepoPasswd, this.fieldItemRepoURL);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DTextUI createFieldItemRepoLogin() {
		return new DTextUI(CadseGCST.CADSE_RUNTIME_at_ITEM_REPO_LOGIN,
				"itemRepoLogin", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldDefaultContentRepoURL() {
		return new DTextUI(CadseGCST.CADSE_RUNTIME_at_DEFAULT_CONTENT_REPO_URL,
				"defaultContentRepoURL", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldItemRepoPasswd() {
		return new DTextUI(CadseGCST.CADSE_RUNTIME_at_ITEM_REPO_PASSWD,
				"itemRepoPasswd", EPosLabel.left, new MC_AttributesItem(),
				null, 1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldItemRepoURL() {
		return new DTextUI(CadseGCST.CADSE_RUNTIME_at_ITEM_REPO_URL,
				"itemRepoURL", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

}

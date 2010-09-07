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
package fr.imag.adele.cadse.cadseg.pages.group;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;

/**
 @generated
 */
public class GroupExtItemModificationPageGroupExtItem_ModificationPage extends
		PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DListUI fieldMembers;

	/**
	    @generated
	 */
	protected DBrowserUI fieldMemberOf;

	/**
	    @generated
	 */
	protected GroupExtItemModificationPageGroupExtItem_ModificationPage(
			String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public GroupExtItemModificationPageGroupExtItem_ModificationPage(Item item) {
		super("modification-page-GroupExtItem", "GroupExtItem", "GroupExtItem",
				"", false, 3);
		this.item = item;
		this.fieldMembers = createFieldMembers();
		this.fieldMemberOf = createFieldMemberOf();
		setActionPage(null);
		addLast(this.fieldMembers, this.fieldMemberOf);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DListUI createFieldMembers() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS);
		return new DListUI(CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS.getName(),
				"members", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	    @generated
	 */
	public DBrowserUI createFieldMemberOf() {
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF);
		return new DBrowserUI(CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF.getName(),
				"memberOf", EPosLabel.left, mc, ic);
	}

}

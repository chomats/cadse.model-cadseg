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
package fr.imag.adele.cadse.cadseg.pages;

import java.awt.image.RescaleOp;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;

/**
 * @generated
 */
public class CadseDefinitionCreationPage1_CreationPage extends
		CadseRuntimeCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected DTextUI fieldPackagename;

	/**
	    @generated
	 */
	protected DListUI fieldExtends;

	/**
	 * @generated
	 */
	protected CadseDefinitionCreationPage1_CreationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public CadseDefinitionCreationPage1_CreationPage(Item parent, ItemType it,
			LinkType lt) {
		super("creation-page1", "Create a cadse definition",
				"Create a cadse definition", "Create a cadse definition",
				false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.__short_name__ = createInternalNameField();
		this.fieldPackagename = createFieldPackagename();
		this.fieldExtends = createFieldExtends();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldPackagename, this.fieldExtends);

		registerListener();
	}

	protected void registerListener() {
		this.fieldPackagename.addValidateContributor(this);
	}

	@Override
	public boolean validValue(UIField field, Object value) {
		if (checkPackageName((String) fieldPackagename.getVisualValue()) == true)
			return true;

		return super.validValue(field, value);
	}

	@Override
	public boolean validValueChanged(UIField field, Object value) {

		if (field == fieldPackagename) {
			String pack_name = (String) value;
			return checkPackageName(pack_name);
		} else
			return false;
	}

	private boolean checkPackageName(String pack_name) {
		IStatus res = JavaConventions.validatePackageName(pack_name, "1.5",
				"1.5");

		if (res.isOK())
			return false;
		else if (res.matches(IStatus.ERROR)) {
			setMessageError(res.getMessage());
			return true;
		} else if (res.matches(IStatus.WARNING)) {
			setMessageWarning(res.getMessage());
			return false;
		} else {
			setMessageInfo(res.getMessage());
			return false;
		}
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
	    @generated
	 */
	public DListUI createFieldExtends() {
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.CADSE_RUNTIME_lt_EXTENDS);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.CADSE_RUNTIME_lt_EXTENDS);
		return new DListUI(CadseGCST.CADSE_RUNTIME_lt_EXTENDS.getName(),
				"extends", EPosLabel.top, mc, ic, true, false, false, false);
	}

}

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

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
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
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;

/**
 * @generated
 */
public class StringModificationPage1_ModificationPage extends AttributeModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected StringModificationPage1_ModificationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public StringModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "String", "String", "", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldIsList = createFieldIsList();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldDefaultValue, this.fieldIsList);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldIsList() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_IS_LIST, "is-list", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldDefaultValue() {
		return new DTextUI(WorkspaceCST.ATTRIBUTE_at_DEFAULT_VALUE, "default-value", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

}

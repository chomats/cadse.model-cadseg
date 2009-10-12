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

package fr.imag.adele.cadse.cadseg.managers.ui;

import org.eclipse.swt.SWT;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ic.IC_EnumForBrowser_Combo;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.mc.StringToEnumModelController;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
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
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * The Class FieldModificationMainPage.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class FieldModificationMainPage extends PageFactory {

	/**
	 * Instantiates a new field modification main page.
	 */
	public FieldModificationMainPage() {
		super("main");
	}

	/**
	 * Creates the wizard field isreadonly.
	 * 
	 * @return the UI field
	 * 
	 * @generated
	 */
	public static UIField createWizardFieldIsreadonly() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.FIELD_at_READONLY, "is readonly", EPosLabel.left, mc, null);
	}

	/**
	 * Creates the wizard field label.
	 * 
	 * @return the UI field
	 * 
	 * @generated
	 */
	public static UIField createWizardFieldLabel() {
		return new DTextUI(CadseGCST.FIELD_at_LABEL, "label", EPosLabel.left, new MC_AttributesItem(), null, 0, 1,
				"The label of the field");
	}

	/**
	 * Creates the wizard field fposition.
	 * 
	 * @return the UI field
	 * 
	 * @generated
	 */
	public static UIField createWizardFieldFposition() {
		StringToEnumModelController<EPosLabel> mc = new StringToEnumModelController<EPosLabel>(EPosLabel.class,
				EPosLabel.left);
		return new DBrowserUI(CadseGCST.FIELD_at_POSITION, "position", EPosLabel.left, mc,
				new IC_EnumForBrowser_Combo("Select a position", "Select a position", EPosLabel.class), 0);
	}

	/**
	 * Creates the wizard field fattribute.
	 * 
	 * @return the UI field
	 * 
	 * @generated
	 */
	public static UIFieldImpl createWizardFieldFattribute() {
		LinkModelController mc = new LinkModelController(true, "error", CadseGCST.FIELD_lt_ATTRIBUTE);
		return new DBrowserUI(CadseGCST.FIELD_lt_ATTRIBUTE.getName(), "attribute", EPosLabel.left, mc,
				new IC_LinkForBrowser_Combo_List("Select an attribute", "Select an attribute",
						CadseGCST.FIELD_lt_ATTRIBUTE), SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.PageFactory#createPage(int,
	 *      fr.imag.adele.cadse.core.Link, fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.IItemNode,
	 *      fr.imag.adele.cadse.core.ItemType,
	 *      fr.imag.adele.cadse.core.LinkType)
	 */
	@Override
	public PageImpl createPage(int cas, Link l, Item item, IItemNode node, ItemType type, LinkType lt) {
		if (cas == PAGE_CREATION_ITEM) {
			return new PageImpl(null, "main", "main", "Create a field", "Create a field", false, 3, null,
					createWizardFieldFattribute(), createWizardFieldFattribute(), FieldsCore.createShortNameField(),
					createWizardFieldIsreadonly(), createWizardFieldLabel(), createWizardFieldFposition());

		}
		if (cas == PAGE_PROPERTY_ITEM || cas == PAGE_PROPERTY_VIEW_ITEM) {
			return new PageImpl(null, "main", "Main field page", "Main field page", "Main field page", false, 3, null,
					createWizardFieldFattribute(), FieldsCore.createShortNameField(), createWizardFieldIsreadonly(),
					createWizardFieldLabel(), createWizardFieldFposition());

		}
		return null;
	}
}

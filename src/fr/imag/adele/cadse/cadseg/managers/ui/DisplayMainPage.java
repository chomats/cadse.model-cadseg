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

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;

/**
 * The Class DisplayMainPage.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class DisplayMainPage extends PageFactory {

	/** The title. */
	private String	title;

	/**
	 * Instantiates a new display main page.
	 * 
	 * @param title
	 *            the title
	 */
	public DisplayMainPage(CompactUUID id, String title) {
		super(id, "main");
		this.title = title;
	}

	/**
	 * Creates the field extends ic.
	 * 
	 * @return the d check box ui
	 * 
	 * @generated
	 */
	static public DCheckBoxUI createFieldExtendsIC() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.DISPLAY_at_EXTENDS_IC, "extends Interaction Controller", EPosLabel.none,
				mc, null);
	}

	/**
	 * Creates the field extends mc.
	 * 
	 * @return the d check box ui
	 * 
	 * @generated
	 */
	static public DCheckBoxUI createFieldExtendsMC() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.DISPLAY_at_EXTENDS_MC, "extends Model Controller", EPosLabel.none, mc, null);
	}

	/**
	 * Creates the field extends ui.
	 * 
	 * @return the d check box ui
	 * 
	 * @generated
	 */
	static public DCheckBoxUI createFieldExtendsUI() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.DISPLAY_at_EXTENDS_UI, "extends UI", EPosLabel.none, mc, null);
	}

	/**
	 * Creates the field editable.
	 * 
	 * @return the d check box ui
	 * 
	 * @generated
	 */
	static public DCheckBoxUI createFieldEditable() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.DISPLAY_at_EDITABLE, "editable", EPosLabel.none, mc, null);
	}

	/**
	 * Creates the field enable.
	 * 
	 * @return the d check box ui
	 * 
	 * @generated
	 */
	static public DCheckBoxUI createFieldEnable() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.DISPLAY_at_ENABLE, "enable", EPosLabel.none, mc, null);
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
		// if (cas == PAGE_CREATION_ITEM) {
		// return new IPage("main", "main", "Create a field", "Create a field",
		// false, 3, null,
		// createWizardFieldFattribute(),
		// createFieldExtendsIC(),
		// createFieldExtendsMC(),
		// createFieldExtendsUI());
		//				
		// }
		// if (cas == PAGE_PROPERTY_ITEM || cas == PAGE_PROPERTY_VIEW_ITEM) {
		// return new IPage("main", "Main field page", "Main field page", "Main
		// field page", false, 3, null,
		// createWizardFieldFattribute(),
		// createFieldExtendsIC(),
		// createFieldExtendsMC(),
		// createFieldExtendsUI());
		//				
		// }
		return null;
	}

}

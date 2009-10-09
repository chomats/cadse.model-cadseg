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

import fede.workspace.model.manager.properties.FieldsCore;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.Pages;

/**
 * The Class DCheckBoxManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class DCheckBoxManager extends DisplayManager implements IItemManager {

	/** The Constant DEFAUL_CLASS_NAME. */
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI";

	/**
	 * Instantiates a new d check box manager.
	 */
	public DCheckBoxManager() {
	}

	/**
	 * @generated
	 */
	@Override
	public String computeUniqueName(Item item, String shortName, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(shortName);
			return sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * @generated
	 */
	@Override
	public String getDisplayName(Item item) {
		try {
			Object value;
			Item currentItem;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * get a link 'ic' from 'DCheckBox' to 'InteractionController'.
	 * 
	 * @generated
	 */
	static public Link getIcLink(Item dCheckBox) {
		return dCheckBox.getOutgoingLink(WorkspaceCST.DCHECK_BOX_lt_IC);
	}

	/**
	 * get all link destination 'ic' from 'DCheckBox' to
	 * 'InteractionController'.
	 * 
	 * @generated
	 */
	static public Item getIcAll(Item dCheckBox) {
		return dCheckBox.getOutgoingItem(WorkspaceCST.DCHECK_BOX_lt_IC, false);
	}

	/**
	 * get resolved link destination 'ic' from 'DCheckBox' to
	 * 'InteractionController'.
	 * 
	 * @generated
	 */
	static public Item getIc(Item dCheckBox) {
		return dCheckBox.getOutgoingItem(WorkspaceCST.DCHECK_BOX_lt_IC, true);
	}

	/**
	 * set a link 'ic' from 'DCheckBox' to 'InteractionController'.
	 * 
	 * @generated
	 */
	static public void setIc(Item dCheckBox, Item value) throws CadseException {
		dCheckBox.setOutgoingItem(WorkspaceCST.DCHECK_BOX_lt_IC, value);
	}

	/**
	 * get a link 'mc' from 'DCheckBox' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public Link getMcLink(Item dCheckBox) {
		return dCheckBox.getOutgoingLink(WorkspaceCST.DCHECK_BOX_lt_MC);
	}

	/**
	 * get all link destination 'mc' from 'DCheckBox' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public Item getMcAll(Item dCheckBox) {
		return dCheckBox.getOutgoingItem(WorkspaceCST.DCHECK_BOX_lt_MC, false);
	}

	/**
	 * get resolved link destination 'mc' from 'DCheckBox' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public Item getMc(Item dCheckBox) {
		return dCheckBox.getOutgoingItem(WorkspaceCST.DCHECK_BOX_lt_MC, true);
	}

	/**
	 * set a link 'mc' from 'DCheckBox' to 'ModelController'.
	 * 
	 * @generated
	 */
	static public void setMc(Item dCheckBox, Item value) throws CadseException {
		dCheckBox.setOutgoingItem(WorkspaceCST.DCHECK_BOX_lt_MC, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createCreationPages(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {

		CreationAction action = new CreationAction(theItemParent, desType, theLinkType, DEFAULT_SHORT_NAME);

		return FieldsCore.createWizard(action, FieldsCore.createPage("p1", "Create a check box", "Create a check box",
				2, createFieldExtendsIC(), createFieldExtendsMC(), createFieldExtendsUI()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Pages createModificationPage(Item item) {
		AbstractActionPage action = new ModificationAction(item);

		return FieldsCore.createWizard(action, FieldsCore.createPage("p1", "a check box", "a check box", 2,

		createFieldExtendsIC(), createFieldExtendsMC(), createFieldExtendsUI()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#getDefaultPosLabel()
	 */
	@Override
	protected EPosLabel getDefaultPosLabel() {
		return EPosLabel.none;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateMeItem(Item field, LinkType lt, ItemType destType) {
		Item attribute = FieldManager.getAttribute(field);
		if (attribute == null) {
			return "You must add a link to an attribute link";
		}
		if (AttributeManager.isIsListAttribute(attribute)) {
			return "CheckBox is not for a list";
		}
		ItemType it = attribute.getType();

		if (WorkspaceCST.BOOLEAN == it) {
			return null;
		}

		if (WorkspaceCST.LINK == it && LinkManager.getMin(attribute) == 0) {
			return null;
		}

		return "CheckBox is for Boolean";
	}
}

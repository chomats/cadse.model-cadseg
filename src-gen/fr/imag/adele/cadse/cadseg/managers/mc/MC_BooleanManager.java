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
package fr.imag.adele.cadse.cadseg.managers.mc;

import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_StringToBoolean;

/**
 * The Class StringToBooleanModelControllerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class MC_BooleanManager extends ModelControllerManager implements IItemManager {

	/** The Constant DEFAUL_CLASS_NAME. */
	@SuppressWarnings("hiding")
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler";

	/**
	 * Instantiates a new string to boolean model controller manager.
	 */
	public MC_BooleanManager() {
	}

	/**
		@generated
	*/
	@Override
	public String computeQualifiedName(Item item, String name, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			Item currentItem;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(name);
			return sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
		@generated
	*/
	@Override
	public String getDisplayName(Item item) {
		try {
			Object value;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#getClassName(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String getClassName(Item uc) {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#getDefaultClassName()
	 */
	@Override
	public Class<?> getDefaultClassName() {
		return MC_StringToBoolean.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#createCreationPages(fr.imag.adele.cadse.core.Item,
//	 *      fr.imag.adele.cadse.core.LinkType,
//	 *      fr.imag.adele.cadse.core.ItemType)
//	 */
//	@Override
//	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {
//
//		CreationAction action = new CreationAction(theItemParent, desType, theLinkType,
//				DisplayManager.MC_DEFAULT_NAME);
//
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1",
//				"Create a string to boolean model controler", "Create a string to boolean model controler", 3
//
//		));
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#createModificationPage(fr.imag.adele.cadse.core.Item)
//	 */
//	@Override
//	public Pages createModificationPage(Item item) {
//		AbstractActionPage action = new ModificationAction(item);
//
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "a a string to boolean model controler",
//				"a string to boolean model controler", 3));
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateMeItem(Item itemParent, LinkType lt, ItemType destType) {
		Item field = itemParent;
		Item attribut = FieldManager.getAttribute(field);
		if (attribut == null)
			return "Must set the attribut link for the item " + itemParent.getId();
		if (AttributeManager.isIsListAttribute(attribut))
			return "Must be a singleton value";
		if (attribut.getType() == CadseGCST.BOOLEAN)
			return null;

		return "The type of the attribut linked at the field must be boolean attribute";
	}
}

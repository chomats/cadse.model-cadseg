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

package fr.imag.adele.cadse.cadseg.managers.mc;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_LinkToBoolean;

/**
 * The Class MC_LinkToBooleanManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class MC_LinkToBooleanManager extends MC_LinkManager {
	// boolean mandatory, String msg, LinkType lt

	/**
	    @generated
	*/
	public MC_LinkToBooleanManager() {
		super();
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
	 * @see model.workspace.workspace.managers.mc.LinkModelControllerManager#getDefaultClassName()
	 */
	@Override
	public Class<?> getDefaultClassName() {
		return MC_LinkToBoolean.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.LinkModelControllerManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return true;
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see model.workspace.workspace.managers.mc.LinkModelControllerManager#createCreationPages(fr.imag.adele.cadse.core.Item,
//	 *      fr.imag.adele.cadse.core.LinkType,
//	 *      fr.imag.adele.cadse.core.ItemType)
//	 */
//	@Override
//	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {
//
//		CreationAction action = new CreationAction(theItemParent, desType, theLinkType,
//				DisplayManager.MC_DEFAULT_NAME);
//
//		RunningModelController mc = new MC_AttributesItem();
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "Add a Link to boolean model controller",
//				"Add a Link to boolean model controller for check box", 2));
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see model.workspace.workspace.managers.mc.LinkModelControllerManager#createModificationPage(fr.imag.adele.cadse.core.Item)
//	 */
//	@Override
//	public Pages createModificationPage(Item item) {
//		AbstractActionPage action = new ModificationAction(item);
//
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "Link to boolean model controller",
//				"Link to boolean model controller", 3
//
//		));
//	}
}

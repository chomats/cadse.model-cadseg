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

package fr.imag.adele.cadse.cadseg.managers.ic;

import java.util.List;

import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.Validator;

/**
 * The Class IC_PartLinkForBrowser_Combo_ListManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_PartLinkForBrowser_Combo_ListManager extends IC_LinkForBrowser_Combo_ListManager implements Validator {


	/** The Constant DEFAUL_CLASS_NAME. */

	@SuppressWarnings("hiding")
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.ic.IC_PartLinkForBrowser_Combo_List";

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public IC_PartLinkForBrowser_Combo_ListManager() {
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

	///** The Constant ERROR_MESSAGE_ATTRIBUTE. */
//	public static final String	ERROR_MESSAGE_ATTRIBUTE	= "select-messsage";



	// /**
	// * @generated
	// */
	// static public Item getSelectLink(Item linkDialogUserControllerForBrowser)
	// {
	// return linkDialogUserControllerForBrowser.getOutgoingItem(
	// SELECT_LINK_LINK, true);
	// }
	//
	// /**
	// * @generated
	// */
	// static public void setSelectLink(Item linkDialogUserControllerForBrowser,
	// Item value) throws CadseException {
	// linkDialogUserControllerForBrowser.setOutgoingItem(SELECT_LINK_LINK,
	// value);
	// }
	//
	// /**
	// * @generated
	// */
	// static public Item getSelectType(Item linkDialogUserControllerForBrowser)
	// {
	// return linkDialogUserControllerForBrowser.getOutgoingItem(
	// SELECT_TYPE_LINK, true);
	// }
	//
	// /**
	// * @generated
	// */
	// static public void setSelectType(Item linkDialogUserControllerForBrowser,
	// Item value) throws CadseException {
	// linkDialogUserControllerForBrowser.setOutgoingItem(SELECT_TYPE_LINK,
	// value);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateMeItem(Item itemParent, LinkType lt, ItemType destType) {
		String error = super.canCreateMeItem(itemParent, lt, destType);
		if (error != null) {
			return error;
		}

		Item field = itemParent;
		Item attribute = FieldManager.getAttribute(field);
		if (attribute.getType() != CadseGCST.LINK_TYPE) {
			return "It's not a link attribute";
		}
		Item itemtypedest = LinkTypeManager.getDestination(attribute);

		Item[] incomingLinkType = ItemTypeManager.getIncomingLinkTypesOfPart(itemtypedest);
		if (incomingLinkType.length == 1 && incomingLinkType[0] != attribute) {
			return null;
		}
		return "no incoming part";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#validate(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.IItemManager.ProblemReporter)
	 */
	@Override
	public List<Item> validate(Item item, ProblemReporter reporter) {
		Item ic = item;

		Item a = FieldManager.getAttribute(ic.getPartParent().getPartParent());
		Item itemtypedest = LinkTypeManager.getDestination(a);

		Item[] incomingLinkType = ItemTypeManager.getIncomingLinkTypesOfPart(itemtypedest);

		if (!(incomingLinkType.length == 1 && incomingLinkType[0] != a)) {
			reporter.error(item, 0, "Cannot find incoming part from {0}.", a.getName());
		}
		return null;
	}
	
	@Override
	public Class<Validator> getClassAdapt() {
		return Validator.class;
	}
}

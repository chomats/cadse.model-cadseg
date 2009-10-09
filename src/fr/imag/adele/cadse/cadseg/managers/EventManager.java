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
/*
 * Adele/LIG/ Grenoble University, France
 * 2006-2008
 */
package fr.imag.adele.cadse.cadseg.managers;

import org.eclipse.swt.graphics.Image;

import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import java.util.Collection;
import java.util.List;
import fr.imag.adele.cadse.core.ui.Pages;
import fede.workspace.tool.view.WSPlugin;

/**
 * The Class EventManager.
 */
public class EventManager extends DefaultWorkspaceManager implements IModelWorkspaceManager {

	/** The Constant IMPORT_JAVA_ATTRIBUTE. */
	public static final String	IMPORT_JAVA_ATTRIBUTE	= "imports-java";

	/** The Constant REQUIRE. */
	public static final String	REQUIRE					= "body";

	/** The Constant TYPE_ATTRIBUTE. */
	public static final String	TYPE_ATTRIBUTE			= "type";

	/** The default_. */
	static EventManager			default_;

	/**
	 * Instantiates a new event manager.
	 */
	public EventManager() {
		default_ = this;
	}

	/**
	 * Compute unique name.
	 * 
	 * @param item
	 *            the item
	 * @param shortName
	 *            the short name
	 * @param parent
	 *            the parent
	 * @param lt
	 *            the lt
	 * 
	 * @return the string
	 * 
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
	 * Gets the display name.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the display name
	 * 
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
	 * get links '#invert_part_events_to_Manager' from 'Event' to 'Manager'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_events_to_ManagerLink(Item event) {
		return event.getOutgoingLink(WorkspaceCST.EVENT_lt__$_INVERT_PART_EVENTS_TO_MANAGER);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_events_to_ManagerAll(Item event) {
		return event.getOutgoingItem(WorkspaceCST.EVENT_lt__$_INVERT_PART_EVENTS_TO_MANAGER, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_events_to_Manager(Item event) {
		return event.getOutgoingItem(WorkspaceCST.EVENT_lt__$_INVERT_PART_EVENTS_TO_MANAGER, true);
	}

	/**
	 * set a link '#invert_part_events_to_Manager' from 'Event' to 'Manager'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_events_to_Manager(Item event, Item value) throws CadseException {
		event.setOutgoingItem(WorkspaceCST.EVENT_lt__$_INVERT_PART_EVENTS_TO_MANAGER, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Pages createModificationPage(Item item) {
		return CadseDefinitionManager.createWizardEvent(item);
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
		return CadseDefinitionManager.createWizardEvent(theItemParent, desType, theLinkType);
	}

	/**
	 * _get image.
	 * 
	 * @return the image
	 */
	public static Image _getImage() {
		return WSPlugin.getDefault().getImageFrom(WorkspaceCST.EVENT, null);
	}

}
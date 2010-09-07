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
package fr.imag.adele.cadse.cadseg.managers.build;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class ComposerLinkTypeManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ComposerLinkManager extends DefaultItemManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ComposerLinkManager() {
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
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * get links 'exporters' from 'ComposerLink' to 'Exporter'.
	 * 
	 * @param composerLink
	 *            the composer link
	 * 
	 * @return the exporters link
	 * 
	 * @generated
	 */
	static public List<Link> getExportersLink(Item composerLink) {
        return composerLink.getOutgoingLinks(CadseGCST.COMPOSER_LINK_lt_EXPORTERS);
    }

	/**
	 * Gets the exporters all.
	 * 
	 * @param composerLink
	 *            the composer link
	 * 
	 * @return the exporters all
	 * 
	 * @generated
	 */
	static public Collection<Item> getExportersAll(Item composerLink) {
        return composerLink.getOutgoingItems(CadseGCST.COMPOSER_LINK_lt_EXPORTERS, false);
    }

//	/**
//	 * Creates the wizard page page1.
//	 * 
//	 * @return the i page
//	 */
//	IPage createWizardPagePage1() {
//		return FieldsCore.createPage("page1", "Create ComposerLink", "", 2, FieldsCore.createShortNameField());
//	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see fede.workspace.model.manager.DefaultItemManager#createCreationPages(fr.imag.adele.cadse.core.Item,
//	 *      fr.imag.adele.cadse.core.LinkType,
//	 *      fr.imag.adele.cadse.core.ItemType)
//	 */
//	@Override
//	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {
//		return FieldsCore
//				.createWizard(new CreationAction(theItemParent, desType, theLinkType), createWizardPagePage1());
//	}
//
//	/**
//	 * Creates the property folder folder1.
//	 * 
//	 * @return the i page
//	 */
//	IPage createPropertyFolderFolder1() {
//		return FieldsCore.createPage("folder1", "ComposerLink", "", 2, FieldsCore.createShortNameField());
//	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see fede.workspace.model.manager.DefaultItemManager#createModificationPage(fr.imag.adele.cadse.core.Item)
//	 */
//	@Override
//	public Pages createModificationPage(Item item) {
//		return FieldsCore.createWizard(new ModificationAction(item), createPropertyFolderFolder1());
//	}

	/**
	 * Gets the link.
	 * 
	 * @param composerLink
	 *            the composer link
	 * 
	 * @return the link
	 * 
	 * @generated
	 */
	static public Item getLink(Item composerLink) {
		return composerLink.getOutgoingItem(CadseGCST.COMPOSER_LINK_lt_LINK, true);
	}

	/**
	 * set a link 'link' from 'ComposerLink' to 'Link'.
	 * 
	 * @generated
	 */
	static public void setLink(Item composerLink, Item value) throws CadseException {
		composerLink.setOutgoingItem(CadseGCST.COMPOSER_LINK_lt_LINK,value);
	}
	
	/**
	 * Gets the composer link from link.
	 * 
	 * @param link
	 *            the link
	 * 
	 * @return the composer link from link
	 */
	public static Item getComposerLinkFromLink(Item link) {
		for (Link l : link.getIncomingLinks()) {
			if (l.getSource().getType() == CadseGCST.COMPOSER_LINK) {
				return l.getSource();
			}
		}
		return null;
	}

	/**
	 * Gets the exporters.
	 * 
	 * @param composerLink
	 *            the composer link
	 * 
	 * @return the exporters
	 * 
	 * @generated
	 */
	static public Collection<Item> getExporters(Item composerLink) {
        return composerLink.getOutgoingItems(CadseGCST.COMPOSER_LINK_lt_EXPORTERS,true);
    }

	/**
	 * Adds the exporters.
	 * 
	 * @param composerLink
	 *            the composer link
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addExporters(Item composerLink, Item value) throws CadseException {
        composerLink.addOutgoingItem(CadseGCST.COMPOSER_LINK_lt_EXPORTERS,value);
    }

	/**
	 * Removes the exporters.
	 * 
	 * @param composerLink
	 *            the composer link
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeExporters(Item composerLink, Item value) throws CadseException {
        composerLink.removeOutgoingItem(CadseGCST.COMPOSER_LINK_lt_EXPORTERS,value);
    }

	/**
	 * get a link 'link' from 'ComposerLink' to 'Link'.
	 * 
	 * @param composerLink
	 *            the composer link
	 * 
	 * @return the link link
	 * 
	 * @generated
	 */
	static public Link getLinkLink(Item composerLink) {
		return composerLink.getOutgoingLink(CadseGCST.COMPOSER_LINK_lt_LINK);
	}

	/**
	 * get all link destination 'link' from 'ComposerLink' to 'Link'.
	 * 
	 * @param composerLink
	 *            the composer link
	 * 
	 * @return the link all
	 * 
	 * @generated
	 */
	static public Item getLinkAll(Item composerLink) {
		return composerLink.getOutgoingItem(CadseGCST.COMPOSER_LINK_lt_LINK, false);
	}

}

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

package fr.imag.adele.cadse.cadseg.managers.build;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.Pages;
import fede.workspace.model.manager.properties.FieldsCore;

/**
 * The Class ComposerLinkManager.
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
	public String computeUniqueName(Item item, String name, Item parent, LinkType lt) {
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
		return composerLink.getOutgoingLinks(WorkspaceCST.COMPOSER_LINK_lt_EXPORTERS);
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
		return composerLink.getOutgoingItems(WorkspaceCST.COMPOSER_LINK_lt_EXPORTERS, false);
	}

	/**
	 * Creates the wizard page page1.
	 * 
	 * @return the i page
	 */
	IPage createWizardPagePage1() {
		return FieldsCore.createPage("page1", "Create ComposerLink", "", 2, FieldsCore.createShortNameField());
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
		return FieldsCore
				.createWizard(new CreationAction(theItemParent, desType, theLinkType), createWizardPagePage1());
	}

	/**
	 * Creates the property folder folder1.
	 * 
	 * @return the i page
	 */
	IPage createPropertyFolderFolder1() {
		return FieldsCore.createPage("folder1", "ComposerLink", "", 2, FieldsCore.createShortNameField());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Pages createModificationPage(Item item) {
		return FieldsCore.createWizard(new ModificationAction(item), createPropertyFolderFolder1());
	}

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
		return composerLink.getOutgoingItem(WorkspaceCST.COMPOSER_LINK_lt_LINK, true);
	}

	/**
	 * set a link 'link' from 'ComposerLink' to 'Link'.
	 * 
	 * @generated
	 */
	static public void setLink(Item composerLink, Item value) throws CadseException {
		composerLink.setOutgoingItem(WorkspaceCST.COMPOSER_LINK_lt_LINK, value);
	}

	/**
	 * get links '#invert_part_composer-links_to_AJProjectComposer' from
	 * 'ComposerLink' to 'AJProjectComposer'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_composerLinks_to_AJProjectComposerLink(Item composerLink) {
		return composerLink
				.getOutgoingLink(WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_AJPROJECT_COMPOSER);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_composerLinks_to_AJProjectComposerAll(Item composerLink) {
		return composerLink.getOutgoingItem(
				WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_AJPROJECT_COMPOSER, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_composerLinks_to_AJProjectComposer(Item composerLink) {
		return composerLink.getOutgoingItem(
				WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_AJPROJECT_COMPOSER, true);
	}

	/**
	 * set a link '#invert_part_composer-links_to_AJProjectComposer' from
	 * 'ComposerLink' to 'AJProjectComposer'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_composerLinks_to_AJProjectComposer(Item composerLink, Item value)
			throws CadseException {
		composerLink.setOutgoingItem(WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_AJPROJECT_COMPOSER,
				value);
	}

	/**
	 * get links '#invert_part_composer-links_to_Composer' from 'ComposerLink'
	 * to 'Composer'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_composerLinks_to_ComposerLink(Item composerLink) {
		return composerLink.getOutgoingLink(WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_COMPOSER);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_composerLinks_to_ComposerAll(Item composerLink) {
		return composerLink.getOutgoingItem(WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_COMPOSER,
				false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_composerLinks_to_Composer(Item composerLink) {
		return composerLink.getOutgoingItem(WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_COMPOSER,
				true);
	}

	/**
	 * set a link '#invert_part_composer-links_to_Composer' from 'ComposerLink'
	 * to 'Composer'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_composerLinks_to_Composer(Item composerLink, Item value) throws CadseException {
		composerLink.setOutgoingItem(WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_COMPOSER, value);
	}

	/**
	 * get links '#invert_part_composer-links_to_JavaProjectComposer' from
	 * 'ComposerLink' to 'JavaProjectComposer'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_composerLinks_to_JavaProjectComposerLink(Item composerLink) {
		return composerLink
				.getOutgoingLink(WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_JAVA_PROJECT_COMPOSER);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_composerLinks_to_JavaProjectComposerAll(Item composerLink) {
		return composerLink.getOutgoingItem(
				WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_JAVA_PROJECT_COMPOSER, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_composerLinks_to_JavaProjectComposer(Item composerLink) {
		return composerLink.getOutgoingItem(
				WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_JAVA_PROJECT_COMPOSER, true);
	}

	/**
	 * set a link '#invert_part_composer-links_to_JavaProjectComposer' from
	 * 'ComposerLink' to 'JavaProjectComposer'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_composerLinks_to_JavaProjectComposer(Item composerLink, Item value)
			throws CadseException {
		composerLink.setOutgoingItem(
				WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_JAVA_PROJECT_COMPOSER, value);
	}

	/**
	 * get links '#invert_part_composer-links_to_EclipseComposer' from
	 * 'ComposerLink' to 'EclipseComposer'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_composerLinks_to_EclipseComposerLink(Item composerLink) {
		return composerLink
				.getOutgoingLink(WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_ECLIPSE_COMPOSER);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_composerLinks_to_EclipseComposerAll(Item composerLink) {
		return composerLink.getOutgoingItem(
				WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_ECLIPSE_COMPOSER, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_composerLinks_to_EclipseComposer(Item composerLink) {
		return composerLink.getOutgoingItem(
				WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_ECLIPSE_COMPOSER, true);
	}

	/**
	 * set a link '#invert_part_composer-links_to_EclipseComposer' from
	 * 'ComposerLink' to 'EclipseComposer'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_composerLinks_to_EclipseComposer(Item composerLink, Item value)
			throws CadseException {
		composerLink.setOutgoingItem(WorkspaceCST.COMPOSER_LINK_lt__$_INVERT_PART_COMPOSER_LINKS_TO_ECLIPSE_COMPOSER,
				value);
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
			if (l.getSource().getType() == WorkspaceCST.COMPOSER_LINK) {
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
		return composerLink.getOutgoingItems(WorkspaceCST.COMPOSER_LINK_lt_EXPORTERS, true);
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
		composerLink.addOutgoingItem(WorkspaceCST.COMPOSER_LINK_lt_EXPORTERS, value);
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
		composerLink.removeOutgoingItem(WorkspaceCST.COMPOSER_LINK_lt_EXPORTERS, value);
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
		return composerLink.getOutgoingLink(WorkspaceCST.COMPOSER_LINK_lt_LINK);
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
		return composerLink.getOutgoingItem(WorkspaceCST.COMPOSER_LINK_lt_LINK, false);
	}

}

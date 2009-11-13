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

import java.util.HashSet;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ExtItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemFilter;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;

/**
 * The Class IC_InverseLink.
 */
public final class IC_InverseLink extends IC_LinkForBrowser_Combo_List {

	// Filtre les attributs de type link et qui pointe vers source.
	/**
	 * The Class InverseLinkFilter.
	 */
	private final class InverseLinkFilter implements ItemFilter {

		/** The source. */
		Item	source;
		Item	linkCreated;

		/**
		 * Instantiates a new inverse link filter.
		 * 
		 * @param source
		 *            the source
		 */
		public InverseLinkFilter(Item source, Item linkCreated) {
			this.source = source;
			this.linkCreated = linkCreated;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ItemFilter#accept(fr.imag.adele.cadse.core.Item)
		 */
		public boolean accept(Item linkAttribute) {
			if (linkAttribute.getType() == CadseGCST.LINK) {
				Item findDestination = LinkManager.getDestination(linkAttribute);
				Item invLink = LinkManager.getInverseLink(linkAttribute);
				if (invLink != null && invLink != this.linkCreated) {
					return false;
				}
				return findDestination == source;
			}
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ItemFilter#stop()
		 */
		public boolean stop() {
			return false;
		}
	}

	/**
	 * Instantiates a new i c_ inverse link.
	 * 
	 * @param title
	 *            the title
	 * @param message
	 *            the message
	 * @param type
	 *            the type
	 */
	public IC_InverseLink(String title, String message, LinkType type) {
		super(title, message, type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#getValues()
	 */
	@Override
	public Object[] getValues() {
		Item theItemLinkType = getUIField().getItem();

		Item containerAttribute = theItemLinkType.getPartParent();

		// la source de la relation est le meme item que le container (l'item
		// qui contient l'attribut link)
		// sauf pour les extentions de type.
		Item source = containerAttribute;
		if (source.getType() == CadseGCST.EXT_ITEM_TYPE) {
			// le type est un extension. La source de la relation
			// est le type r�f�renc�.
			source = ExtItemTypeManager.getRefType(source);
		}
		// la destination de la relation
		Item destination = LinkManager.getDestination(theItemLinkType);
		if (destination == null) {
			return new Object[0];
		}
		// calcule tout les cadses � partir du container et du cadse ou il est
		// d�finie.
		Item cadse = ItemTypeManager.getCadseDefinition(containerAttribute);
		HashSet<Item> allCadse = new HashSet<Item>();
		allCadse.add(cadse);
		allCadse.addAll(CadseDefinitionManager.getAllDependenciesCadse(cadse));

		// / calcul tous les liens de destination vers source
		// / ces liens peuvent etre d�finit dans un sous type
		// / ou dans une extension

		Item[] allLinks = ItemTypeManager.getAllAttributes(allCadse, destination, new InverseLinkFilter(source,
				theItemLinkType), true);

		return allLinks;
	}
}
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
package fr.imag.adele.cadse.cadseg.managers.build.composer;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.cadseg.managers.build.ComposerManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class EclipseComposerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class EclipseComposerManager extends ComposerManager {

	/**
	 * Instantiates a new eclipse composer manager.
	 */
	public EclipseComposerManager() {
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
	 * get links 'composer-links' from 'EclipseComposer' to 'ComposerLink'.
	 * 
	 * @param eclipseComposer
	 *            the eclipse composer
	 * 
	 * @return the composer links link
	 * 
	 * @generated
	 */
	static public List<Link> getComposerLinksLink(Item eclipseComposer) {
        return eclipseComposer.getOutgoingLinks(CadseGCST.ECLIPSE_COMPOSER_lt_COMPOSER_LINKS);
    }

	/**
	 * Gets the composer links all.
	 * 
	 * @param eclipseComposer
	 *            the eclipse composer
	 * 
	 * @return the composer links all
	 * 
	 * @generated
	 */
	static public Collection<Item> getComposerLinksAll(Item eclipseComposer) {
        return eclipseComposer.getOutgoingItems(CadseGCST.ECLIPSE_COMPOSER_lt_COMPOSER_LINKS, false);
    }

	/**
	 * Gets the composer links.
	 * 
	 * @param eclipseComposer
	 *            the eclipse composer
	 * 
	 * @return the composer links
	 * 
	 * @generated
	 */
	static public Collection<Item> getComposerLinks(Item eclipseComposer) {
        return eclipseComposer.getOutgoingItems(CadseGCST.ECLIPSE_COMPOSER_lt_COMPOSER_LINKS,true);
    }

	/**
	 * Adds the composer links.
	 * 
	 * @param eclipseComposer
	 *            the eclipse composer
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addComposerLinks(Item eclipseComposer, Item value) throws CadseException {
        eclipseComposer.addOutgoingItem(CadseGCST.ECLIPSE_COMPOSER_lt_COMPOSER_LINKS,value);
    }

	/**
	 * Removes the composer links.
	 * 
	 * @param eclipseComposer
	 *            the eclipse composer
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeComposerLinks(Item eclipseComposer, Item value) throws CadseException {
        eclipseComposer.removeOutgoingItem(CadseGCST.ECLIPSE_COMPOSER_lt_COMPOSER_LINKS,value);
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.ComposerManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seemodel.workspace.workspace.managers.build.ComposerManager#
	 * generateGetTargetMethod()
	 */
	@Override
	public boolean generateGetTargetMethod() {
		return false;
	}

}

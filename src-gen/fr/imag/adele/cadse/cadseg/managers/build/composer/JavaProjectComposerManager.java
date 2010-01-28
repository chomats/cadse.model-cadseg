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

package fr.imag.adele.cadse.cadseg.managers.build.composer;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class JavaProjectComposerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class JavaProjectComposerManager extends EclipseComposerManager {

	/**
	 * Instantiates a new java project composer manager.
	 */
	public JavaProjectComposerManager() {
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
	 * get links 'composer-links' from 'JavaProjectComposer' to 'ComposerLink'.
	 * 
	 * @param javaProjectComposer
	 *            the java project composer
	 * 
	 * @return the composer links link
	 * 
	 * @generated
	 */
	static public List<Link> getComposerLinksLink(Item javaProjectComposer) {
        return javaProjectComposer.getOutgoingLinks(CadseGCST.JAVA_PROJECT_COMPOSER_lt_COMPOSER_LINKS);
    }

	/**
	 * Gets the composer links all.
	 * 
	 * @param javaProjectComposer
	 *            the java project composer
	 * 
	 * @return the composer links all
	 * 
	 * @generated
	 */
	static public Collection<Item> getComposerLinksAll(Item javaProjectComposer) {
        return javaProjectComposer.getOutgoingItems(CadseGCST.JAVA_PROJECT_COMPOSER_lt_COMPOSER_LINKS, false);
    }

	/**
	 * Gets the composer links.
	 * 
	 * @param javaProjectComposer
	 *            the java project composer
	 * 
	 * @return the composer links
	 * 
	 * @generated
	 */
	static public Collection<Item> getComposerLinks(Item javaProjectComposer) {
        return javaProjectComposer.getOutgoingItems(CadseGCST.JAVA_PROJECT_COMPOSER_lt_COMPOSER_LINKS,true);
    }

	/**
	 * Adds the composer links.
	 * 
	 * @param javaProjectComposer
	 *            the java project composer
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addComposerLinks(Item javaProjectComposer, Item value) throws CadseException {
        javaProjectComposer.addOutgoingItem(CadseGCST.JAVA_PROJECT_COMPOSER_lt_COMPOSER_LINKS,value);
    }

	/**
	 * Removes the composer links.
	 * 
	 * @param javaProjectComposer
	 *            the java project composer
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeComposerLinks(Item javaProjectComposer, Item value) throws CadseException {
        javaProjectComposer.removeOutgoingItem(CadseGCST.JAVA_PROJECT_COMPOSER_lt_COMPOSER_LINKS,value);
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.composer.EclipseComposerManager
	 * #mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	/** The Constant DEFAUL_CLASS_NAME. */
	@SuppressWarnings("hiding")
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.eclipse.composition.java.JavaProjectComposer";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.composer.EclipseComposerManager
	 * #getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.composer.EclipseComposerManager
	 * #generateGetTargetMethod()
	 */
	@Override
	public boolean generateGetTargetMethod() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.ComposerManager#callsuperPostCompose
	 * ()
	 */
	@Override
	public boolean callsuperPostCompose() {
		return true;
	}

}

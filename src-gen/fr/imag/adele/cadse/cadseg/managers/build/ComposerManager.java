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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.cadseg.managers.IExtendClassManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.Menu;
import fr.imag.adele.cadse.core.build.Composer;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.util.Convert;

/**
 * The Class ComposerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ComposerManager extends DefaultItemManager implements IExtendClassManager {

	
	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ComposerManager() {
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
	 * The Class ComposerLinkAction.
	 */
	class ComposerLinkAction extends IMenuAction {

		/** The composer. */
		Item	composer;

		/** The link. */
		Item	link;

		/**
		 * Instantiates a new composer link action.
		 * 
		 * @param composer
		 *            the composer
		 * @param link
		 *            the link
		 */
		public ComposerLinkAction(Item composer, Item link) {
			this.composer = composer;
			this.link = link;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IMenuAction#run(fr.imag.adele.cadse.core.IItemNode[])
		 */
		@Override
		public void run(IItemNode[] selection) throws CadseException {
			try {
				CadseCore.createItemIfNeed(null, "link-" + link.getName(), CadseGCST.COMPOSER_LINK, composer,
						CadseGCST.COMPOSER_lt_COMPOSER_LINKS, CadseGCST.COMPOSER_LINK_lt_LINK, link);

			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IMenuAction#getImage()
		 */
		@Override
		public String getImage() {
			return CadseGCST.COMPOSER_LINK.getImage();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IMenuAction#getLabel()
		 */
		@Override
		public String getLabel() {
			return "Add composite link " + link.getName();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IMenuAction#isSeparator()
		 */
		@Override
		public boolean isSeparator() {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IMenuAction#getMenuPath()
		 */
		@Override
		public String getMenuPath() {
			return NEW_MENU;
		}

	}


	

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#getDisplayName(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String getDisplayName(Item item) {
		return item.getName();
	}
	
	/**
	 * get links 'composer-links' from 'Composer' to 'ComposerLink'.
	 * 
	 * @param composer
	 *            the composer
	 * 
	 * @return the composer links link
	 * 
	 * @generated
	 */
	static public List<Link> getComposerLinksLink(Item composer) {
        return composer.getOutgoingLinks(CadseGCST.COMPOSER_lt_COMPOSER_LINKS);
    }

	/**
	 * Gets the composer links all.
	 * 
	 * @param composer
	 *            the composer
	 * 
	 * @return the composer links all
	 * 
	 * @generated
	 */
	static public Collection<Item> getComposerLinksAll(Item composer) {
        return composer.getOutgoingItems(CadseGCST.COMPOSER_lt_COMPOSER_LINKS, false);
    }

	/**
	 * Callsuper post compose.
	 * 
	 * @return true, if successful
	 */
	public boolean callsuperPostCompose() {
		return false;
	}

	/**
	 * Generate get target method.
	 * 
	 * @return true, if successful
	 */
	public boolean generateGetTargetMethod() {
		return true;
	}

	/**
	 * Checks if is extends class.
	 * 
	 * @param contentmodel
	 *            the contentmodel
	 * 
	 * @return true, if is extends class
	 */
	public static final boolean isExtendsClass(Item contentmodel) {
		Object value = contentmodel.getAttribute(CadseGCST.RUNTIME_ITEM_at_EXTENDS_CLASS_);
		if (value == null) {
			return false;
		}

		try {
			return Convert.toBooleanFalseIfNull(value);
		} catch (Throwable t) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#getClassName(fr.imag.adele.cadse.core.Item)
	 */
	public String getClassName(Item uc) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#getDefaultClassName()
	 */
	public Class<?> getDefaultClassName() {
		return Composer.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#mustBeExtended()
	 */
	public boolean mustBeExtended() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.IModelWorkspaceManager#getWorkspaceModel(fr.imag.adele.cadse.core.Item)
	 */
	public Item getWorkspaceModel(Item source) {
		return source.getPartParent(CadseGCST.CADSE_DEFINITION);
	}

	/**
	 * Gets the composer links.
	 * 
	 * @param composer
	 *            the composer
	 * 
	 * @return the composer links
	 * 
	 * @generated
	 */
	static public Collection<Item> getComposerLinks(Item composer) {
        return composer.getOutgoingItems(CadseGCST.COMPOSER_lt_COMPOSER_LINKS,true);
    }

	/**
	 * Adds the composer links.
	 * 
	 * @param composer
	 *            the composer
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addComposerLinks(Item composer, Item value) throws CadseException {
        composer.addOutgoingItem(CadseGCST.COMPOSER_lt_COMPOSER_LINKS,value);
    }

	/**
	 * Removes the composer links.
	 * 
	 * @param composer
	 *            the composer
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeComposerLinks(Item composer, Item value) throws CadseException {
        composer.removeOutgoingItem(CadseGCST.COMPOSER_lt_COMPOSER_LINKS,value);
    }

	/**
	 * Gets the types attribute.
	 * 
	 * @param composer
	 *            the composer
	 * 
	 * @return the types attribute
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final List<String> getTypesAttribute(Item composer) {
		try {
			List<String> list = composer.getAttribute(CadseGCST.COMPOSER_at_TYPES_);

			if (list == null)
				return null;

			return new ArrayList<String>(list);
		} catch (Throwable t) {
			return new ArrayList<String>();
		}
	}

	/**
	 * Sets the types attribute.
	 * 
	 * @param composer
	 *            the composer
	 * @param valueList
	 *            the value list
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void setTypesAttribute(Item composer, List<String> valueList) {
		try {
			List<String> list = new ArrayList<String>(valueList);
			composer.setAttribute(CadseGCST.COMPOSER_at_TYPES_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * Adds the types attribute.
	 * 
	 * @param composer
	 *            the composer
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void addTypesAttribute(Item composer, String value) {
		try {
			List<String> list = composer.getAttribute(CadseGCST.COMPOSER_at_TYPES_);
			if (list == null) {
				list = new ArrayList<String>();
			}
			list.add(value);
			composer.setAttribute(CadseGCST.COMPOSER_at_TYPES_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * Removes the types attribute.
	 * 
	 * @param composer
	 *            the composer
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void removeTypesAttribute(Item composer, String value) {
		try {

			List<String> list = composer.getAttribute(CadseGCST.COMPOSER_at_TYPES_);
			if (list == null) {
				return;
			}
			list.remove(value);
			if (list.size() == 0)
				composer.setAttribute(CadseGCST.COMPOSER_at_TYPES_, null);
			else
				composer.setAttribute(CadseGCST.COMPOSER_at_TYPES_, list);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#contributeMenuNewAction(fr.imag.adele.cadse.core.IMenuAction.Menu,
	 *      fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public void contributeMenuNewAction(Menu menu, Item composer) {

		Collection<Item> composerlinks = getComposerLinks(composer);
		Item itemtype = CompositeItemTypeManager.getItemType(composer.getPartParent());
		ONE: for (Link l : itemtype.getOutgoingLinks()) {
			if (l.getDestinationType() != CadseGCST.LINK_TYPE) {
				continue;
			}
			if (!l.isLinkResolved()) {
				continue;
			}

			Item link = l.getResolvedDestination();
			for (Item item : composerlinks) {
				if (ComposerLinkManager.getLink(item) == link) {
					continue ONE;
				}
			}
			// not found
			menu.insert(null, new ComposerLinkAction(composer, link), true);

		}
	}

}

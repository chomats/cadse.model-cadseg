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
package fr.imag.adele.cadse.cadseg.managers.content;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.eclipse.ParseTemplate;
import fr.imag.adele.cadse.cadseg.exp.ParseException;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.Validator;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
 * The Class ManagerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ManagerManager extends DefaultWorkspaceManager implements
		fr.imag.adele.cadse.cadseg.IModelWorkspaceManager {

	/** The Constant DEFAULT_HSPAN_FIRST_PAGE. */
	public static final int	DEFAULT_HSPAN_FIRST_PAGE	= 3;

	

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ManagerManager() {
		super();
	}

	/**
	 * @generated
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
	 * get a link 'item-type' from 'Manager' to 'ItemType'.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the item type link
	 * 
	 * @generated
	 */
	static public Link getItemTypeLink(Item manager) {
		return manager.getOutgoingLink(CadseGCST.MANAGER_lt_ITEM_TYPE);
	}

	/**
	 * get all link destination 'item-type' from 'Manager' to 'ItemType'.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the item type all
	 * 
	 * @generated
	 */
	static public Item getItemTypeAll(Item manager) {
		return manager.getOutgoingItem(CadseGCST.MANAGER_lt_ITEM_TYPE, false);
	}

	// TODO :rename to getCadseDefinition
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.DefaultWorkspaceManager#getWorkspaceModel(fr
	 * .imag.adele.cadse.core.Item)
	 */
	@Override
	public Item getWorkspaceModel(Item manager) {
		return _getCadseDefinition(manager);
	}

	// TODO :rename to _getCadseDefinition
	/**
	 * _get workspace model.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the item
	 */
	public static Item _getCadseDefinition(Item manager) {
		return manager.getPartParent(CadseGCST.CADSE_DEFINITION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#canDeleteItem(fr.imag
	 * .adele.cadse.core.Item)
	 */
	@Override
	public String canDeleteItem(Item item) {
		return "Cannot delete manager...";
	}

	@Override
	public String canCreateMeItem(Item itemParent, LinkType lt, ItemType destType) {
		return "Cannot create manager...";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#canCreateChildItem(fr
	 * .imag.adele.cadse.core.Item, fr.imag.adele.cadse.core.LinkType,
	 * fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateChildItem(Item itemParent, LinkType lt, ItemType destType) {
		Item itemtype = getItemType(itemParent);
		if (itemtype == null) {
			return "Not found item type";
		}

		boolean hascontent = ItemTypeManager.isHasContentAttribute(itemtype);
		if (!hascontent && lt == CadseGCST.MANAGER_lt_CONTENT_MODEL) {
			return "Cannot create a content for manager which associted item type has no content";
		}
		return null;
	}

	/**
	 * Sets the unique name template.
	 * 
	 * @param manager
	 *            the manager
	 * @param value
	 *            the value
	 */
	public static final void setUniqueNameTemplate(Item manager, String value) {
		setQualifiedNameTemplateAttribute(manager, value);
	}

	/**
	 * Gets the display name template attribute.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the display name template attribute
	 * 
	 * @generated
	 */
	public static final String getDisplayNameTemplateAttribute(Item manager) {
		return manager.getAttributeWithDefaultValue(CadseGCST.MANAGER_at_DISPLAY_NAME_TEMPLATE_, null);
	}

	/**
	 * Sets the display name template attribute.
	 * 
	 * @param manager
	 *            the manager
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setDisplayNameTemplateAttribute(Item manager, String value) {
		try {
			manager.setAttribute(CadseGCST.MANAGER_at_DISPLAY_NAME_TEMPLATE_, value);
		} catch (Throwable t) {

		}
	}

/**
		@generated
	*/
	public static final String getIconAttribute(Item manager) {
		return manager.getAttributeWithDefaultValue(CadseGCST.MANAGER_at_ICON_, null);
	}

	/**
		@generated
	*/
	public static final void setIconAttribute(Item manager, String value) {
		try {
			manager.setAttribute(CadseGCST.MANAGER_at_ICON_, value);
		} catch (Throwable t) {

		}
	}

	//	/**
//	 * Gets the icon attribute.
//	 * 
//	 * @param manager
//	 *            the manager
//	 * 
//	 * @return the icon attribute
//	 * 
//	 * @generated
//	 */
//	public static final String getIconAttribute(Item manager) {
//		return manager.getAttributeWithDefaultValue(CadseGCST.MANAGER_at_ICON_, null);
//	}
//
//	/**
//	 * Sets the icon attribute.
//	 * 
//	 * @param manager
//	 *            the manager
//	 * @param value
//	 *            the value
//	 * 
//	 * @generated
//	 */
//	public static final void setIconAttribute(Item manager, String value) {
//		try {
//			manager.setAttribute(CadseGCST.MANAGER_at_ICON_, value);
//		} catch (Throwable t) {
//
//		}
//	}

//	/**
//	 * Sets the icon.
//	 * 
//	 * @param manager
//	 *            the manager
//	 * @param value
//	 *            the value
//	 */
//	@Deprecated
//	public static final void setIcon(Item manager, String value) {
//		setIconAttribute(manager, value);
//	}

	/**
	 * Gets the valid pattern id attribute.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the valid pattern id attribute
	 * 
	 * @generated
	 */
	public static final String getValidPatternIdAttribute(Item manager) {
		return manager.getAttributeWithDefaultValue(CadseGCST.MANAGER_at_VALID_PATTERN_ID_, null);
	}

	/**
	 * Sets the valid pattern id attribute.
	 * 
	 * @param manager
	 *            the manager
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setValidPatternIdAttribute(Item manager, String value) {
		try {
			manager.setAttribute(CadseGCST.MANAGER_at_VALID_PATTERN_ID_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getQualifiedNameTemplateAttribute(Item manager) {
		return manager.getAttributeWithDefaultValue(CadseGCST.MANAGER_at_QUALIFIED_NAME_TEMPLATE_, null);
	}

	/**
		@generated
	*/
	public static final void setQualifiedNameTemplateAttribute(Item manager, String value) {
		try {
			manager.setAttribute(CadseGCST.MANAGER_at_QUALIFIED_NAME_TEMPLATE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the message error id attribute.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the message error id attribute
	 * 
	 * @generated
	 */
	public static final String getMessageErrorIdAttribute(Item manager) {
		return manager.getAttributeWithDefaultValue(CadseGCST.MANAGER_at_MESSAGE_ERROR_ID_, null);
	}

	/**
	 * Sets the message error id attribute.
	 * 
	 * @param manager
	 *            the manager
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setMessageErrorIdAttribute(Item manager, String value) {
		try {
			manager.setAttribute(CadseGCST.MANAGER_at_MESSAGE_ERROR_ID_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the human name attribute.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the human name attribute
	 * 
	 * @generated
	 */
	public static final String getHumanNameAttribute(Item manager) {
		return manager.getAttributeWithDefaultValue(CadseGCST.MANAGER_at_HUMAN_NAME_, null);
	}

	/**
	 * Sets the human name attribute.
	 * 
	 * @param manager
	 *            the manager
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setHumanNameAttribute(Item manager, String value) {
		try {
			manager.setAttribute(CadseGCST.MANAGER_at_HUMAN_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the human name.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the human name
	 */
	@Deprecated
	public static final String getHumanName(Item manager) {
		return getHumanNameAttribute(manager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag
	 * .adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return "Cannot rename a build model";
	}

	/**
	 * Gets the human type name.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the human type name
	 */
	@Deprecated
	public static String getHumanTypeName(Item manager) {
		return getHumanNameAttribute(manager);
	}

	/**
	 * get links 'exporters' from 'Manager' to 'Exporter'.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the exporters link
	 * 
	 * @generated
	 */
	static public List<Link> getExportersLink(Item manager) {
        return manager.getOutgoingLinks(CadseGCST.MANAGER_lt_EXPORTERS);
    }

	/**
	 * Gets the exporters all.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the exporters all
	 * 
	 * @generated
	 */
	static public Collection<Item> getExportersAll(Item manager) {
        return manager.getOutgoingItems(CadseGCST.MANAGER_lt_EXPORTERS, false);
    }

	/**
	 * Gets the exporters.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the exporters
	 * 
	 * @generated
	 */
	static public Collection<Item> getExporters(Item manager) {
        return manager.getOutgoingItems(CadseGCST.MANAGER_lt_EXPORTERS,true);
    }

	/**
	 * Adds the exporters.
	 * 
	 * @param manager
	 *            the manager
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addExporters(Item manager, Item value) throws CadseException {
        manager.addOutgoingItem(CadseGCST.MANAGER_lt_EXPORTERS,value);
    }

	/**
	 * Removes the exporters.
	 * 
	 * @param manager
	 *            the manager
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeExporters(Item manager, Item value) throws CadseException {
        manager.removeOutgoingItem(CadseGCST.MANAGER_lt_EXPORTERS,value);
    }

	/**
	 * Gets the unique name template.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the unique name template
	 */
	public static String getUniqueNameTemplate(Item manager) {
		return getQualifiedNameTemplateAttribute(manager);
	}

	/**
	 * Gets the display template.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the display template
	 */
	@Deprecated
	public static String getDisplayTemplate(Item manager) {
		return getDisplayNameTemplateAttribute(manager);
	}

	/**
	 * Gets the message error id.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the message error id
	 */
	@Deprecated
	public static String getMessageErrorId(Item manager) {
		return getMessageErrorIdAttribute(manager);
	}

	/**
	 * Gets the valid pattern id.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the valid pattern id
	 */
	@Deprecated
	public static String getValidPatternId(Item manager) {
		return getValidPatternIdAttribute(manager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#getImage(fr.imag.adele
	 * .cadse.core.Item)
	 */
	@Override
	public String getImage(Item item) {
		Item itemType = getItemType(item);
		String iconPath = ItemTypeManager.getIconAttribute(itemType);
		if (iconPath == null || iconPath.length() == 0) {
			return null;
		}
		IFile f = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(iconPath));
		if (f != null && f.exists()) {
			return "platform:/resource/"+f.getFullPath().toPortableString();
		}

		return iconPath;
	}

	/**
	 * Gets the item type.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the item type
	 * 
	 * @generated
	 */
	static public Item getItemType(Item manager) {
		return manager.getOutgoingItem(CadseGCST.MANAGER_lt_ITEM_TYPE, true);
	}

	/**
	 * set a link 'item-type' from 'Manager' to 'ItemType'.
	 * 
	 * @param manager
	 *            the manager
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setItemType(Item manager, Item value) throws CadseException {
		manager.setOutgoingItem(CadseGCST.MANAGER_lt_ITEM_TYPE,value);
	}

	/**
	 * get a link 'content-model' from 'Manager' to 'ContentModel'.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the content model link
	 * 
	 * @generated
	 */
	static public Link getContentModelLink(Item manager) {
		return manager.getOutgoingLink(CadseGCST.MANAGER_lt_CONTENT_MODEL);
	}

	/**
	 * get all link destination 'content-model' from 'Manager' to
	 * 'ContentModel'.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the content model all
	 * 
	 * @generated
	 */
	static public Item getContentModelAll(Item manager) {
		return manager.getOutgoingItem(CadseGCST.MANAGER_lt_CONTENT_MODEL, false);
	}

	/**
	 * get resolved link destination 'content-model' from 'Manager' to
	 * 'ContentModel'.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the content model
	 * 
	 * @generated
	 */
	static public Item getContentModel(Item manager) {
		return manager.getOutgoingItem(CadseGCST.MANAGER_lt_CONTENT_MODEL, true);
	}

	/**
	 * set a link 'content-model' from 'Manager' to 'ContentModel'.
	 * 
	 * @param manager
	 *            the manager
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setContentModel(Item manager, Item value) throws CadseException {
		manager.setOutgoingItem(CadseGCST.MANAGER_lt_CONTENT_MODEL,value);
	}

	/**
	 * Gets the manager from item type.
	 * 
	 * @param itemType
	 *            the item type
	 * 
	 * @return the manager from item type
	 */
	public static Item getManagerFromItemType(Item itemType) {
		for (Link l : itemType.getIncomingLinks()) {
			if (l.getSource().getType() == CadseGCST.MANAGER) {
				return l.getSource();
			}
		}
		return null;
	}

	/**
	 * Gets the super manager.
	 * 
	 * @param manager
	 *            the manager
	 * 
	 * @return the super manager
	 */
	public static Item getSuperManager(Item manager) {
		Item itemtype = getItemType(manager);
		if (itemtype == null) {
			return null;
		}
		Item superItem = ItemTypeManager.getSuperType(itemtype);
		if (superItem == null) {
			return null;
		}
		Item superItemManager = ItemTypeManager.getManager(superItem);
		return superItemManager;
	}

	public final static class ManagerValidator extends Validator {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fede.workspace.model.manager.DefaultItemManager#validate(fr.imag.adele
		 * .cadse.core.Item, fr.imag.adele.cadse.core.IItemManager.ProblemReporter)
		 */
		@Override
		public List<Item> validate(Item item, ProblemReporter reporter) {
			Item itemtype = getItemType(item);
			O: {
				if (itemtype == null) {
					reporter.error(item, 1, "Item type is null");
					break O;
				}
				String uniqueNameTemplate = getUniqueNameTemplate(item);
				if (uniqueNameTemplate != null && uniqueNameTemplate.length() != 0) {
					ParseTemplate pt = new ParseTemplate(itemtype, uniqueNameTemplate);
					try {
						pt.main();
						pt.validate(reporter, item);
					} catch (ParseException e) {
						reporter.error(item, 0, "Error when parse unique name value : {0} ", e.getMessage());
					}
				}
				String displayTemplate = getDisplayTemplate(item);
				if (displayTemplate != null && displayTemplate.length() != 0) {
					ParseTemplate pt = new ParseTemplate(itemtype, displayTemplate, null);
					try {
						pt.main();
						pt.validate(reporter, item);
					} catch (ParseException e) {
						reporter.error(item, 0, "Error when parse display name value : {0} ", e.getMessage());
					}
				}
			}
			return null;
		}
	}
	
	public static void getPackageName(Item ow) {
		// TODO Auto-generated method stub
		
	}

}

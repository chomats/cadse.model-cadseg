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
package fr.imag.adele.cadse.cadseg.managers.mc;

import java.util.Set;

import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.managers.IExtendClassManager;
import fr.imag.adele.cadse.cadseg.managers.ui.DisplayManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemFactory;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.impl.ItemFactory;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.transaction.delta.ItemDelta;

/**
 * The Class ModelControllerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ModelControllerManager extends DefaultWorkspaceManager implements IItemManager, IExtendClassManager , IItemFactory{
	
	/**
	 * Instantiates a new model controller manager.
	 */
	public ModelControllerManager() {
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
	 * Checks if is user controller.
	 * 
	 * @return true, if is user controller
	 */
	public boolean isUserController() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#getClassName(fr.imag.adele.cadse.core.Item)
	 */
	public String getClassName(Item mc) {
		String className = mc.getAttribute(CadseGCST.RUNTIME_ITEM_at_CLASS_NAME_);
		if (className == null) {
			className = getDefaultClassName() == null ? null : getDefaultClassName().getSimpleName();
		}
		return className;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#getDefaultClassName()
	 */
	public Class<?> getDefaultClassName() {
		return MC_AttributesItem.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.IExtendClassManager#mustBeExtended()
	 */
	public boolean mustBeExtended() {
		return true;
	}

	/**
	 * Checks if is extends class.
	 * 
	 * @param mc
	 *            the mc
	 * 
	 * @return true, if is extends class
	 */
	public static final boolean isExtendsClass(Item mc) {
		Item display = mc.getPartParent();
		return DisplayManager.isExtendsMCAttribute(display);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return "Cannot rename a mc";
	}

	/**
	 * Gets the field.
	 * 
	 * @param mc
	 *            the mc
	 * 
	 * @return the field
	 */
	public Item getField(Item mc) {
		Item display = mc.getPartParent();
		if (display == null) {
			return null;
		}
		return display.getPartParent();
	}

	/**
	 * Gets the attribute.
	 * 
	 * @param mc
	 *            the mc
	 * 
	 * @return the attribute
	 */
	static public Item getAttribute(Item mc) {
		Item display = mc.getPartParent();
		if (display == null) {
			return null;
		}
		Item field = display.getPartParent();
		if (field == null) {
			return null;
		}
		return FieldManager.getAttribute(field);
	}

	/**
	 * Generate call arguments.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param object
	 *            the object
	 */
	protected void generateCallArguments(Item item, GenStringBuilder sb, Set<String> imports, Object object) {
	}

	/**
	 * Generate construstor arguments.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstrustorArguments(Item item, GenStringBuilder sb) {
	}

	/**
	 * Generate constructor parameter.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstructorParameter(Item item, GenStringBuilder sb) {
	}
	
	public Item newForCommitItem(LogicalWorkspace wl, ItemType it, ItemDelta item) {
		return ItemFactory.SINGLETON.newForCommitItem(wl, it, item);
	}
}

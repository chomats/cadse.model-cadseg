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

package fr.imag.adele.cadse.cadseg.managers.mc;

import java.util.Set;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.mc.MaxModelController;
import fede.workspace.model.manager.properties.impl.mc.MinModelController;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.ui.DisplayManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.Pages;

/**
 * The Class ListOfStringModelControllerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ListOfStringModelControllerManager extends ModelControllerManager implements IItemManager {

	/**
	 * The Class MyContentItem.
	 */
	class MyContentItem extends ModelControllerManager.ModelControllerContent {

		/**
		 * Instantiates a new my content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 * @throws CadseException
		 */
		public MyContentItem(CompactUUID id) throws CadseException {
			super(id);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set, java.lang.Object)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
			Item mc = getItem();
			sb.append_exp_vir(mc, MIN, "0");
			sb.append_exp_vir(mc, MAX, "-1");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			sb.append("min, max");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			sb.append("int min, int max");
		}
	}

	/** The Constant DEFAUL_CLASS_NAME. */
	@SuppressWarnings("hiding")
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.model.manager.properties.impl.mc.MC_DefaultForList";

	/** The Constant MIN. */
	private static final String	MIN					= "MIN";

	/** The Constant MAX. */
	private static final String	MAX					= "MAX";

	/**
	 * Instantiates a new list of string model controller manager.
	 */
	public ListOfStringModelControllerManager() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#getClassName(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String getClassName(Item uc) {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#createCreationPages(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {

		CreationAction action = new CreationAction(theItemParent, desType, theLinkType,
				DisplayManager.MC_DEFAULT_NAME);

		IModelController mc = new MC_AttributesItem();
		MinModelController minVC = new MinModelController();

		MaxModelController maxVC = new MaxModelController();
		return FieldsCore
				.createWizard(action, FieldsCore.createPage("page1", "Create a list of string model controller",
						"Create a list of string model controller", 3, FieldsCore.createIntField(MIN, "min", minVC,
								minVC), FieldsCore.createIntField(MAX, "max", maxVC, maxVC)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Pages createModificationPage(Item item) {
		AbstractActionPage action = new ModificationAction(item);

		IModelController mc = new MC_AttributesItem();
		MinModelController minVC = new MinModelController();

		MaxModelController maxVC = new MaxModelController();
		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "a list of string model controller",
				"a list of string model controller", 3, FieldsCore.createIntField(MIN, "min", minVC, minVC), FieldsCore
						.createIntField(MAX, "max", maxVC, maxVC)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canCreateMeItem(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public String canCreateMeItem(Item itemParent, LinkType lt, ItemType destType) {
		Item field = itemParent.getPartParent();
		Item attribut = FieldManager.getAttribute(field);
		if (attribut == null) {
			return "Must set the attribut link for the item " + itemParent.getId();
		}
		boolean islist = AttributeManager.isIsListAttribute(attribut);
		if (!islist) {
			return "The attribut must be a list of string";
		}

		if (attribut.getType() == CadseGCST.STRING) {
			return null;
		}

		return "The attribut must be a list of string";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentItem(CompactUUID id) throws CadseException {
		return new MyContentItem(id);
	}
}
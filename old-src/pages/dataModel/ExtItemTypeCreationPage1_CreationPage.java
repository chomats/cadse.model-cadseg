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
package fr.imag.adele.cadse.cadseg.pages.dataModel;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ITreeContentProvider;

import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.cadseg.ItemShortNameComparator;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.ItemTreeContentProvider;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;

/**
 * @generated
 */
public class ExtItemTypeCreationPage1_CreationPage extends
		ItemTypeCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	static public class RefTypeIC extends IC_LinkForBrowser_Combo_List {

		/**
		 * @generated
		 */
		public RefTypeIC(String title, String message, LinkType linkType) {
			super(title, message, linkType);
		}

		// {context <-[parent-part]- -[item-types] ->} - {context} -
		// {context<-[super-type]-*}
		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#getValues()
		 */
		@Override
		public Object[] getValues() {
			Item theAttribute = getItem();
			Item theItemType = theAttribute.getPartParent();
			Item cadsedef = theItemType
					.getPartParent(CadseGCST.CADSE_DEFINITION);

			return ItemTypeManager.getAllAllItemType(cadsedef, null);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#getTreeContentProvider()
		 */
		@Override
		protected ITreeContentProvider getTreeContentProvider() {
			return new ItemTreeContentProvider(new ItemShortNameComparator(),
					CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL,
					CadseGCST.DATA_MODEL_lt_TYPES,
					CadseGCST.DATA_MODEL_lt_CATEGORIES);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#validate(java.lang.Object[])
		 */
		@Override
		public IStatus validate(Object[] selection) {
			if (selection != null && selection.length == 1) {
				Object sel = selection[0];
				if (sel instanceof Item) {
					if (((Item) sel).getType() == CadseGCST.ITEM_TYPE) {
						return Status.OK_STATUS;
					}
				}
			}
			return new Status(Status.ERROR, WSPlugin.PLUGIN_ID,
					"select an item type");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#getInputValues()
		 */
		@Override
		protected Object getInputValues() {
			Item theAttribute = getItem();
			Item theItemType = theAttribute.getPartParent();
			Item cadsedef = theItemType
					.getPartParent(CadseGCST.CADSE_DEFINITION);
			List<Item> allcadse = CadseDefinitionManager
					.getAllDependenciesCadse(cadsedef);
			allcadse.add(cadsedef);
			Item[] ret = (Item[]) allcadse.toArray(new Item[allcadse.size()]);
			Arrays.sort(ret, new ItemShortNameComparator());
			return ret;
		}

	}

	/**
	 * @generated
	 */
	protected DBrowserUI fieldRefType;

	/**
	 * @generated
	 */
	protected ExtItemTypeCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public ExtItemTypeCreationPage1_CreationPage(Item parent, ItemType it,
			LinkType lt) {
		super("creation-page1", "Create ExtItemType", "Create ExtItemType", "",
				false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldName = createFieldName();
		this.fieldRefType = createFieldRefType();
		setActionPage(null);
		addLast(this.fieldName, this.fieldRefType);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	 * @generated
	 */
	public DBrowserUI createFieldRefType() {
		RefTypeIC ic = new RefTypeIC("Select a value.", "Select a value.",
				CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE);
		return new DBrowserUI(CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE.getName(),
				"ref-type", EPosLabel.left, mc, ic);
	}

}

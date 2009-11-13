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
package fr.imag.adele.cadse.cadseg.pages.actions.dataModel;

import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.ui.IPageObject;
import fr.imag.adele.cadse.core.ui.Pages;

/**
 * @generated
 */
public class ItemType_CreationPagesAction extends CreationAction {

	/**
	 * @generated
	 */
	public ItemType_CreationPagesAction(Item parent, ItemType type, LinkType lt, final String defaultName) {
		super(parent, type, lt, defaultName);
	}

	/**
	 * @generated
	 */
	public ItemType_CreationPagesAction(Item parent, ItemType type, LinkType lt) {
		super(parent, type, lt);
	}

	/** The manager item. */
	private Item	managerItem;

	/** The theitemtype. */
	private Item	theitemtype;

	/** The init manager_. */
	private boolean	initManager_	= false;	;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.impl.ui.CreationAction#init(fr.imag.adele.cadse.core.ui.IPageObject)
	 */
	@Override
	public void init(IPageObject pageObject) throws CadseException {
		super.init(pageObject);
		theitemtype = getItem();
		managerItem = theitemtype.getIncomingItem(CadseGCST.MANAGER_lt_ITEM_TYPE);
		//((Pages) pageObject).getPage("manager").setItem(managerItem);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.impl.ui.AbstractActionPage#doNextPageAction(java.lang.Object,
	 *      fr.imag.adele.cadse.core.ui.Pages, int)
	 */
	@Override
	public void doNextPageAction(Object monitor, Pages pages, int currentPage) throws Exception {
		if (currentPage == 0) {
			if (!initManager_) {
				initManager(pages);
			}
		}
		super.doNextPageAction(monitor, pages, currentPage);
	}

	/**
	 * Inits the manager.
	 * 
	 * @param pages
	 *            the pages
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	private void initManager(Pages pages) throws CadseException {
		if (pages != null) {
			pages.updateField("page2", CadseGCST.MANAGER_at_HUMAN_NAME);
		}

		Item superItem = ItemTypeManager.getSuperType(theitemtype);
		if (superItem != null) {
			Item supermanager = ManagerManager.getManagerFromItemType(superItem);
			if (supermanager != null) {
				ManagerManager.setUniqueNameTemplate(managerItem,
						ManagerManager.getUniqueNameTemplate(supermanager));
				ManagerManager.setDisplayNameTemplateAttribute(managerItem,
						ManagerManager
								.getDisplayNameTemplateAttribute(supermanager));
				if (pages != null) {
					pages.updateField("page2",
							CadseGCST.MANAGER_at_LONG_NAME_TEMPLATE);
				}
			}
		}
		initManager_ = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.impl.ui.CreationAction#doFinish(java.lang.Object)
	 */
	@Override
	public void doFinish(Object monitor) throws Exception {
		if (!initManager_) {
			initManager(null);
		}
		super.doFinish(monitor);
	}

}

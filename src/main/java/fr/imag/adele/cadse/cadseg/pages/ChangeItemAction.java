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
package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.Pages;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;

public class ChangeItemAction implements IActionPage {

	IPage _page;
    private UIPlatform _uiPlatform;
	public ChangeItemAction(IPage page) {
		_page = page;
	}

	@Override
	public void dispose(UIPlatform uiPlatform) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doCancel(UIPlatform uiPlatform, Object monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFinish(UIPlatform uiPlatform, Object monitor)
			throws Exception {
//		if (!initManager_) {
//			initManager(null);
//		}
	}

	@Override
	public String getTypeId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(UIPlatform uiPlatform) throws CadseException {
                _uiPlatform = uiPlatform;
        theitemtype = uiPlatform.getItem();
        managerItem = theitemtype.getIncomingItem(CadseGCST.MANAGER_lt_ITEM_TYPE);

//		Item manager = null;
//		if (!uiPlatform.isModification()) {
//			Item baseItem = uiPlatform.getItem();
//			Item cadseDef = baseItem.getPartParent(CadseGCST.CADSE_DEFINITION);
//			Item mapping = CadseDefinitionManager.getMapping(cadseDef);
//			manager  = uiPlatform.getCopy().createItem(CadseGCST.MANAGER, mapping, CadseGCST.MAPPING_MODEL_lt_MANAGERS);
//		} else {
//			Item baseItem = uiPlatform.getItem();
//			manager  = baseItem.getIncomingItem(CadseGCST.MANAGER_lt_ITEM_TYPE);
//		}
		associateItem(uiPlatform, managerItem);
	}

	private void associateItem(UIPlatform uiPlatform, Item manager) {
		IAttributeType<?>[] pageAttributes = _page.getAttributes();
		for (IAttributeType<?> att : pageAttributes) {
			UIField f = uiPlatform.getField(att);
			uiPlatform.setVariable(f.getId().toString(), manager);
		}
	}

	@Override
	public void initAfterUI(UIPlatform uiPlatform) {
		// TODO Auto-generated method stub

	}

	/** The manager item. */
	private Item	managerItem;

	/** The theitemtype. */
	private Item	theitemtype;

	/** The init manager_. */
	private boolean	initManager_	= false;	;

//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see fr.imag.adele.cadse.core.impl.ui.AbstractActionPage#doNextPageAction(java.lang.Object,
//	 *      fr.imag.adele.cadse.core.ui.Pages, int)
//	 */
//	@Override
//	public void doNextPageAction(Object monitor, Pages pages, int currentPage) throws Exception {
//		if (currentPage == 0) {
//			if (!initManager_) {
//				initManager(pages);
//			}
//		}
//		super.doNextPageAction(monitor, pages, currentPage);
//	}
//
//	/**
//	 * Inits the manager.
//	 *
//	 * @param pages
//	 *            the pages
//	 *
//	 * @throws CadseException
//	 *             the melusine exception
//	 */
//	private void initManager(Pages pages) throws CadseException {
//		if (pages != null) {
//			_uiPlatform.updateField(CadseGCST.MANAGER_at_HUMAN_NAME_);
//		}
//
//		Item superItem = ItemTypeManager.getSuperType(theitemtype);
//		if (superItem != null) {
//			Item supermanager = ManagerManager.getManagerFromItemType(superItem);
//			if (supermanager != null) {
//				ManagerManager.setUniqueNameTemplate(managerItem,
//						ManagerManager.getUniqueNameTemplate(supermanager));
//				ManagerManager.setDisplayNameTemplateAttribute(managerItem,
//						ManagerManager
//								.getDisplayNameTemplateAttribute(supermanager));
//				if (pages != null) {
//					_uiPlatform.updateField(
//							CadseGCST.MANAGER_at_QUALIFIED_NAME_TEMPLATE_);
//				}
//			}
//		}
//		initManager_ = true;
//	}

}

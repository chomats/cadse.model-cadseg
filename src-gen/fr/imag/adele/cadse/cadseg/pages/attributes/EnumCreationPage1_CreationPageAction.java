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

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.ui.IPageObject;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * @generated
 */
public class EnumCreationPage1_CreationPageAction extends AbstractActionPage {
	UIField	enumTypeField;

	public EnumCreationPage1_CreationPageAction(Link l, Item item, IItemNode node, ItemType type, LinkType lt) {
		super(l, item, node, type, lt);
	}

	public EnumCreationPage1_CreationPageAction() {
	}

	@Override
	public void init(IPageObject pageObject) throws CadseException {
		super.init(pageObject);
		enumTypeField = pageObject.getField(CadseGCST.ENUM_lt_ENUM_TYPE.getName());
	}

	@Override
	protected void setDefaultValues() {
		super.setDefaultValues();

		Item enumItem = (Item) getItem();
		Item superEnumType = EnumManager.getEnumType(this.superAttribute);
		try {
			EnumManager.setEnumType(enumItem, superEnumType);
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void disableField() {
		super.disableField();
		if (enumTypeField != null)
			enumTypeField.setEnabled(false);
	}

	@Override
	protected void enableField() {
		super.enableField();
		if (enumTypeField != null)
			enumTypeField.setEnabled(true);
	}

}

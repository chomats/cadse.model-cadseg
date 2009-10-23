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

package fr.imag.adele.cadse.cadseg.managers.attributes;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;

/**
 * A factory for creating AttributePage objects.
 */
public class AttributePageFactory extends PageFactory {

	/** The title. */
	String	title;

	/** The desc. */
	String	desc;

	/**
	 * Instantiates a new attribute page factory.
	 * 
	 * @param title
	 *            the title
	 * @param desc
	 *            the desc
	 */
	public AttributePageFactory(CompactUUID id, String title, String desc) {
		super(id, "main");
		this.title = title;
		this.desc = desc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.PageFactory#createPage(int,
	 *      fr.imag.adele.cadse.core.Link, fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.IItemNode,
	 *      fr.imag.adele.cadse.core.ItemType,
	 *      fr.imag.adele.cadse.core.LinkType)
	 */
	@Override
	public PageImpl createPage(int cas, Link l, Item item, IItemNode node, ItemType type, LinkType lt) {
		if (cas == PAGE_CREATION_ITEM) {

		}
		if (cas == PAGE_PROPERTY_ITEM || cas == PAGE_PROPERTY_VIEW_ITEM) {
			return new PageImpl(getName(), title, title, desc, false, 3, null, FieldsCore.createShortNameField(),
					FieldsCore.createCheckBox(CadseGCST.ATTRIBUTE_at_IS_LIST, "is list"), FieldsCore
							.createTextField(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE, "default value"), FieldsCore
							.createCheckBox(CadseGCST.ATTRIBUTE_at_REQUIRE, "mandatory"));
		}
		return null;
	}

}

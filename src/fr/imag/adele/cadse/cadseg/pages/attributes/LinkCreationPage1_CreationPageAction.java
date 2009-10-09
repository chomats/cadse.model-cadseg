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

import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * @generated
 */
public class LinkCreationPage1_CreationPageAction extends AttributeCreationPage1_CreationPageAction {

	public LinkCreationPage1_CreationPageAction() {
		super();
	}

	@Override
	protected void enableField() {
		super.enableField();
		LinkCreationPage1_CreationPage page = (LinkCreationPage1_CreationPage) pageObject;
		page.fieldAggregation.setEnabled(true);
		page.fieldAnnotation.setEnabled(true);
		page.fieldComposition.setEnabled(true);
	}

	@Override
	protected void disableField() {
		super.disableField();
		LinkCreationPage1_CreationPage page = (LinkCreationPage1_CreationPage) pageObject;
		page.fieldAggregation.setEnabled(false);
		page.fieldAnnotation.setEnabled(false);
		page.fieldComposition.setEnabled(false);
	}

}

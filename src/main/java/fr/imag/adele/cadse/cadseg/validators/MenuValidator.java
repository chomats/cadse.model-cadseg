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
package fr.imag.adele.cadse.cadseg.validators;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.ui.JavaClassValidator;
import fr.imag.adele.cadse.core.impl.ui.mc.UIValidator_Descriptor;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;

public class MenuValidator extends AbstractUIRunningValidator {

	@Override
	public void init(UIPlatform uiPlatform) {
		super.init(uiPlatform);
		if (_uiPlatform.isModification()) return;
		
		Item item = _uiPlatform.getItem();
		Item parent = item.getPartParent();
		if (parent.isInstanceOf(CadseGCST.TYPE_DEFINITION)) {
			try {
				item.setAttribute(CadseGCST.ITEM_at_NAME_, "main");
				item.setAttribute(CadseGCST.MENU_ABSTRACT_at_LABEL_, "main");
				_uiPlatform.setHidden(CadseGCST.MENU_ABSTRACT_at_ICON_, true);
				_uiPlatform.setHidden(CadseGCST.MENU_ABSTRACT_at_LABEL_, true);
				_uiPlatform.setHidden(CadseGCST.ITEM_at_NAME_, true);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}

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

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;

public class UIFieldValidator extends AbstractUIRunningValidator {

	
	@Override
	public void init(UIPlatform uiPlatform) {
		super.init(uiPlatform);
	}
	
	@Override
	public void notifieValueChanged(UIField field, Object value) {
		if (field.getAttributeDefinition() == CadseGCST.FIELD_lt_ATTRIBUTE) {
			if (!_uiPlatform.isModification()) {
				Item item = _uiPlatform.getItem();
				try {
					item.setAttribute(CadseGCST.ITEM_at_DISPLAY_NAME_, value);
					item.setAttribute(CadseGCST.FIELD_at_LABEL_, value);
				} catch (CadseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}

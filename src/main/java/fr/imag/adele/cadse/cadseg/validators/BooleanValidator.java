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

import fr.imag.adele.cadse.cadseg.managers.attributes.BooleanManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;

/**
 * @generated
 */
public class BooleanValidator extends AbstractUIRunningValidator {
	
	@Override
	public void init(UIPlatform uiPlatform) {
		super.init(uiPlatform);
		if (_uiPlatform.isModification()) return;
		
		Item item = _uiPlatform.getItem();
		BooleanManager.setDefaultValueAttribute(item, "false");
		BooleanManager.setCannotBeUndefinedAttribute(item, true);
	}
	
	@Override
	public boolean validValue(UIField field, Object value) {

		if (!isValidConfig()) {
			_uiPlatform.setMessageError("Default value can't be null when null value is not allowed");
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void notifieValueChanged(UIField field, Object value) {
		if (_uiPlatform.isModification()) {
			if (!isValidConfig()) {
				_uiPlatform.setVisualValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, true, false);
				Item item = _uiPlatform.getItem();
				try {
					item.setAttribute(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, true);
				} catch (CadseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	protected boolean isValidConfig() {

		Item item = _uiPlatform.getItem();
		Boolean cbu = item.getAttribute(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_);
		String defVal = item.getAttribute(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);

		if (cbu == true && (defVal == null || defVal.isEmpty()))
			return false;
		else
			return true;
	}
}

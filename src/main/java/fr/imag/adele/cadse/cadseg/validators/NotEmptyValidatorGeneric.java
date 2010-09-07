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

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.attribute.CheckStatus;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;

public class NotEmptyValidatorGeneric extends AbstractUIRunningValidator {

	
	
	
	@Override
	public boolean validValue(UIField field, Object value) {
		if (_uiPlatform.isModification()) return false;
		
		Item item = _uiPlatform.getItem();
		IAttributeType<?>[] attribute = item.getLocalAllAttributeTypes();
		for (IAttributeType<?> att : attribute) {
			if (att.getType() != CadseGCST.STRING) {
				continue;
			}
			StringAttributeType sAttr = (StringAttributeType) att;
			if (!sAttr.isNotEMpty()) continue;
			if (_uiPlatform.contains(att)) continue;
			if (item.isDelegatedValue(att)) continue;
			
			Object v = item.getAttribute(att);
			CheckStatus error = att.check(item, v);
			if (error != null) {
				if (error.getType() == UIPlatform.ERROR) {
					_uiPlatform.setMessageError("Hidden attribute "+att.getName()+": "+error.getFormatedMessage());
					return true;
				}
				_uiPlatform.setMessage("Hidden attribute "+att.getName()+": "+error.getFormatedMessage(), error.getType());
			}
			
		}
		return false;
	}
}

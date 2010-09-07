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

import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.enumdef.TWCommitKind;
import fr.imag.adele.cadse.core.impl.attribute.AttributeType;
import fr.imag.adele.cadse.core.transaction.delta.ItemDelta;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;

public class UITWCommitValidator extends AbstractUIRunningValidator {
	
	@Override
	public boolean validValue(UIField field, Object value) {
		
		return validValue();
	}
	
	@Override
	public boolean validValueChanged(UIField field, Object value) {
		if (field.getAttributeDefinition() == CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_) {
			return validValue();
		}
		
		return false;
	}

	private boolean validValue() {
		Item attrItem = _uiPlatform.getItem();
		if (attrItem instanceof ItemDelta) {
			attrItem = ((ItemDelta)attrItem).getBaseItem();
		}
		if (attrItem instanceof AttributeType) {
			AttributeType attr = (AttributeType) attrItem;
			
			TWCommitKind commitKind = AttributeManager.getTWCommitKindAttribute(attr);
			boolean hasReconcile = TWCommitKind.reconcile.equals(commitKind);
			boolean isList = AttributeManager.isIsListAttribute(attr);
			if (!isList && hasReconcile) {
				_uiPlatform.setMessageError("Attribute must be a list one to apply " + 
					TWCommitKind.reconcile.toString() + " evolution politic.");
				return true;
			}
		}
		return false;
	}
}

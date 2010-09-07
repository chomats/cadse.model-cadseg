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
package fr.imag.adele.cadse.cadseg.pages.mc;

import java.util.List;

import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * The Class DefaultEnumMC.
 */
public final class MC_DefaultEnum extends MC_AttributesItem {

	/**
	 * Gets the values.
	 * 
	 * @return the values
	 */
	private List<String> getValues() {
		Item enumItem = getItem();
		Item enumType = EnumManager.getEnumType(enumItem);
		return EnumTypeManager.getEnumTypeValues(enumType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.AbstractModelController#validValueChanged(fr.imag.adele.cadse.core.ui.UIField,
	 *      java.lang.Object)
	 */
	@Override
	public boolean validValueChanged(UIField field, Object value) {
		List<String> values = getValues();
		if (value == null) {
			_uiPlatform.setMessageError("Enter a default value");
			return true;
		}
		if (!values.contains(value)) {
			_uiPlatform.setMessageError("Enter a valid default value");
			return true;
		}
		return false;
	}
}
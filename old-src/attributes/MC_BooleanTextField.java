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
/**
 *
 */
package fr.imag.adele.cadse.cadseg.pages.attributes;

import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.UIField;

public final class MC_BooleanTextField extends MC_AttributesItem {

	@Override
	public boolean validValueChanged(UIField field, Object value) {
		if (value == null) {
			if (field.getAttributeDefinition() != null) {
				Object d = field.getAttributeDefinition().getDefaultValue();
				if (d != null) {
					return ((Boolean) d).booleanValue();
				}
			}
			return false;
		}
		if (value.toString().length() == 0) {
			return false;
		}
		if ("true".equals(value)) {
			return false;
		}
		if ("false".equals(value)) {
			return false;
		}
		setMessageError("The default value must be 'true' or 'false'");
		return true;
	}
}
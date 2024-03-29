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

import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.UIField;

public final class MC_BooleanTextField extends MC_AttributesItem {

	
	@Override
	public Object visualToModel(Object ret) {
		return ret == null ? null : ret.toString();
	}
	
	@Override
	protected Object modelToVisual(Object v) {
		return v == null ? null : "".equals(v) ? null :  Boolean.parseBoolean(v.toString());
	}
	
	@Override
	public boolean validValueChanged(UIField field, Object value) {
		if (value == null) {
			return false;
		}
		if (value.toString().length() == 0) {
			return false;
		}
		if (Boolean.TRUE.equals(value) || "true".equals(value)) {
			return false;
		}
		if (Boolean.FALSE.equals(value) || "false".equals(value)) {
			return false;
		}
		_uiPlatform.setMessageError("The default value must be 'true' or 'false'");
		return true;
	}
}

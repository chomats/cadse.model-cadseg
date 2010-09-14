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
package fr.imag.adele.cadse.cadseg.managers.attributes;

import java.util.ArrayList;
import java.util.List;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;

/**
 * @generated
 */
public class SymbolicBitMapManager extends AttributeManager {

	/**
	 * @generated
	 */
	public SymbolicBitMapManager() {
		super();
	}

	/**
		@generated
	*/
	@Override
	public String computeQualifiedName(Item item, String name, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			Item currentItem;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(name);
			return sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * @generated
	 */
	@Override
	public String getDisplayName(Item item) {
		try {
			Object value;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final List<String> getValuesAttribute(Item symbolicBitMap) {
		try {
			List<String> list = symbolicBitMap.getAttribute(CadseGCST.SYMBOLIC_BIT_MAP_at_VALUES_);

			if (list == null)
				return null;

			return new ArrayList<String>(list);
		} catch (Throwable t) {
			return new ArrayList<String>();
		}
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void setValuesAttribute(Item symbolicBitMap, List<String> valueList) {
		try {
			List<String> list = new ArrayList<String>(valueList);
			symbolicBitMap.setAttribute(CadseGCST.SYMBOLIC_BIT_MAP_at_VALUES_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void addValuesAttribute(Item symbolicBitMap, String value) {
		try {
			List<String> list = symbolicBitMap.getAttribute(CadseGCST.SYMBOLIC_BIT_MAP_at_VALUES_);
			if (list == null) {
				list = new ArrayList<String>();
			}
			list.add(value);
			symbolicBitMap.setAttribute(CadseGCST.SYMBOLIC_BIT_MAP_at_VALUES_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void removeValuesAttribute(Item symbolicBitMap, String value) {
		try {

			List<String> list = symbolicBitMap.getAttribute(CadseGCST.SYMBOLIC_BIT_MAP_at_VALUES_);
			if (list == null) {
				return;
			}
			list.remove(value);
			if (list.size() == 0)
				symbolicBitMap.setAttribute(CadseGCST.SYMBOLIC_BIT_MAP_at_VALUES_, null);
			else
				symbolicBitMap.setAttribute(CadseGCST.SYMBOLIC_BIT_MAP_at_VALUES_, list);
		} catch (Throwable t) {

		}
	}


}

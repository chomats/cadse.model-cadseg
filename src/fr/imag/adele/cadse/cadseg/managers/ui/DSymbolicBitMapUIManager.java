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
package fr.imag.adele.cadse.cadseg.managers.ui;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.util.Convert;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public class DSymbolicBitMapUIManager extends DisplayManager {

	/**
	 * @generated
	 */
	public DSymbolicBitMapUIManager() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	public String computeUniqueName(Item item, String shortName, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(shortName);
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
			Item currentItem;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * @generated
	 */
	public static final int getNumberColumnAttribute(Item dSymbolicBitMapUI) {
		Object value = dSymbolicBitMapUI.getAttribute(WorkspaceCST.DSYMBOLIC_BIT_MAP_UI_at_NUMBER_COLUMN);
		if (value == null)
			return -1;

		try {
			return Convert.toInt(value, null, -1);
		} catch (Throwable t) {
			return -1;
		}

	}

	/**
	 * @generated
	 */
	public static final void setNumberColumnAttribute(Item dSymbolicBitMapUI, int value) {
		try {
			Object setvalue = value;
			dSymbolicBitMapUI.setAttribute(WorkspaceCST.DSYMBOLIC_BIT_MAP_UI_at_NUMBER_COLUMN, setvalue);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final List<String> getLabelsAttribute(Item dSymbolicBitMapUI) {
		try {
			List<String> listValue = (List<String>) dSymbolicBitMapUI
					.getAttribute(WorkspaceCST.DSYMBOLIC_BIT_MAP_UI_at_LABELS);

			if (listValue == null)
				return null;

			List<String> list = new ArrayList<String>();

			for (String value : listValue) {
				list.add((String) value);
			}
			return list;
		} catch (Throwable t) {
			return new ArrayList<String>();
		}
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void setLabelsAttribute(Item dSymbolicBitMapUI, List<String> valueList) {
		try {
			List<String> list = new ArrayList<String>();
			for (String value : valueList) {
				list.add(value);
			}
			dSymbolicBitMapUI.setAttribute(WorkspaceCST.DSYMBOLIC_BIT_MAP_UI_at_LABELS, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void addLabelsAttribute(Item dSymbolicBitMapUI, String value) {
		try {
			List<String> list = (List<String>) dSymbolicBitMapUI
					.getAttribute(WorkspaceCST.DSYMBOLIC_BIT_MAP_UI_at_LABELS);
			if (list == null) {
				list = new ArrayList<String>();
			}
			String setvalue = value;
			list.add(setvalue);
			dSymbolicBitMapUI.setAttribute(WorkspaceCST.DSYMBOLIC_BIT_MAP_UI_at_LABELS, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void removeLabelsAttribute(Item dSymbolicBitMapUI, String value) {
		try {

			List<String> list = (List<String>) dSymbolicBitMapUI
					.getAttribute(WorkspaceCST.DSYMBOLIC_BIT_MAP_UI_at_LABELS);
			if (list == null) {
				return;
			}
			String setvalue = value;
			list.remove(setvalue);
			if (list.size() == 0)
				dSymbolicBitMapUI.setAttribute(WorkspaceCST.DSYMBOLIC_BIT_MAP_UI_at_LABELS, null);
			else
				dSymbolicBitMapUI.setAttribute(WorkspaceCST.DSYMBOLIC_BIT_MAP_UI_at_LABELS, list);
		} catch (Throwable t) {

		}
	}

}

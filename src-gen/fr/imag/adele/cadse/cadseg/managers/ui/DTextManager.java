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

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class DTextManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class DTextManager extends DisplayManager implements IItemManager {

	/** The Constant DEFAUL_CLASS_NAME. */
	public static final String	DEFAUL_CLASS_NAME	= "fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI";

	/**
	 * Instantiates a new d text manager.
	 */
	public DTextManager() {
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
	public static final String getToolTipAttribute(Item dText) {
		return dText.getAttributeWithDefaultValue(CadseGCST.DTEXT_at_TOOL_TIP_, null);
	}

	/**
	 * @generated
	 */
	public static final void setToolTipAttribute(Item dText, String value) {
		try {
			dText.setAttribute(CadseGCST.DTEXT_at_TOOL_TIP_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final int getVerticalSpanAttribute(Item dText) {
		return dText.getAttributeWithDefaultValue(CadseGCST.DTEXT_at_VERTICAL_SPAN_, 1);
	}

	/**
	 * @generated
	 */
	public static final void setVerticalSpanAttribute(Item dText, int value) {
		try {
			dText.setAttribute(CadseGCST.DTEXT_at_VERTICAL_SPAN_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isMultiLineAttribute(Item dText) {
		return dText.getAttributeWithDefaultValue(CadseGCST.DTEXT_at_MULTI_LINE_, false);
	}

	/**
	 * @generated
	 */
	public static final void setMultiLineAttribute(Item dText, boolean value) {
		try {
			dText.setAttribute(CadseGCST.DTEXT_at_MULTI_LINE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isWrapLineAttribute(Item dText) {
		return dText.getAttributeWithDefaultValue(CadseGCST.DTEXT_at_WRAP_LINE_, false);
	}

	/**
	 * @generated
	 */
	public static final void setWrapLineAttribute(Item dText, boolean value) {
		try {
			dText.setAttribute(CadseGCST.DTEXT_at_WRAP_LINE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isNoBorderAttribute(Item dText) {
		return dText.getAttributeWithDefaultValue(CadseGCST.DTEXT_at_NO_BORDER_, false);
	}

	/**
	 * @generated
	 */
	public static final void setNoBorderAttribute(Item dText, boolean value) {
		try {
			dText.setAttribute(CadseGCST.DTEXT_at_NO_BORDER_, value);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

}

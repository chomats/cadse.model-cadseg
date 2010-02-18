/** Licensed to the Apache Software Foundation (ASF) under one* or more contributor license agreements.  See the NOTICE file* distributed with this work for additional information* regarding copyright ownership.  The ASF licenses this file* to you under the Apache License, Version 2.0 (the* "License"); you may not use this file except in compliance* with the License.  You may obtain a copy of the License at**   http://www.apache.org/licenses/LICENSE-2.0** Unless required by applicable law or agreed to in writing,* software distributed under the License is distributed on an* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY* KIND, either express or implied.  See the License for the* specific language governing permissions and limitations* under the License.*/
package fr.imag.adele.cadse.cadseg.managers.meta;


import java.util.ArrayList;
import java.util.List;

import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;



/**
    @generated
*/
public class LinkTypeTypeManager extends ItemManager {

	/**
	    @generated
	*/
	public LinkTypeTypeManager() {
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
		@generated
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
		@generated
	*/
	public static final int getVersionAttribute(Item linkTypeType) {
		return linkTypeType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_TYPE_at_VERSION_, -1);
	}

	/**
		@generated
	*/
	public static final void setVersionAttribute(Item linkTypeType, int value) {
		try {
			linkTypeType.setAttribute(CadseGCST.LINK_TYPE_TYPE_at_VERSION_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final int getIndexOfAttribute(Item linkTypeType) {
		return linkTypeType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_TYPE_at_INDEX_OF_, -1);
	}

	/**
		@generated
	*/
	public static final void setIndexOfAttribute(Item linkTypeType, int value) {
		try {
			linkTypeType.setAttribute(CadseGCST.LINK_TYPE_TYPE_at_INDEX_OF_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	@SuppressWarnings("unchecked")
	public static final List<Integer> getCompatibleVersionsAttribute(Item linkTypeType) {
		try {
			List<Integer> list = linkTypeType.getAttribute(CadseGCST.LINK_TYPE_TYPE_at_COMPATIBLE_VERSIONS_);

			if (list == null)
				return null;

			return new ArrayList<Integer>(list);
		} catch (Throwable t) {
			return new ArrayList<Integer>();
		}
	}

	/**
		@generated
	*/
	@SuppressWarnings("unchecked")
	public static final void setCompatibleVersionsAttribute(Item linkTypeType, List<Integer> valueList) {
		try {
			List<Integer> list = new ArrayList<Integer>(valueList);
			linkTypeType.setAttribute(CadseGCST.LINK_TYPE_TYPE_at_COMPATIBLE_VERSIONS_, list);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	@SuppressWarnings("unchecked")
	public static final void addCompatibleVersionsAttribute(Item linkTypeType, Integer value) {
		try {
			List<Integer> list = linkTypeType.getAttribute(CadseGCST.LINK_TYPE_TYPE_at_COMPATIBLE_VERSIONS_);
			if (list == null) {
				list = new ArrayList<Integer>();
			}
			list.add(value);
			linkTypeType.setAttribute(CadseGCST.LINK_TYPE_TYPE_at_COMPATIBLE_VERSIONS_, list);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	@SuppressWarnings("unchecked")
	public static final void removeCompatibleVersionsAttribute(Item linkTypeType, Integer value) {
		try {

			List<Integer> list = linkTypeType.getAttribute(CadseGCST.LINK_TYPE_TYPE_at_COMPATIBLE_VERSIONS_);
			if (list == null) {
				return;
			}
			list.remove(value);
			if (list.size() == 0)
				linkTypeType.setAttribute(CadseGCST.LINK_TYPE_TYPE_at_COMPATIBLE_VERSIONS_, null);
			else
				linkTypeType.setAttribute(CadseGCST.LINK_TYPE_TYPE_at_COMPATIBLE_VERSIONS_, list);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final boolean isHiddenAttribute(Item linkTypeType) {
		return linkTypeType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_TYPE_at_HIDDEN_, false);
	}

	/**
		@generated
	*/
	public static final void setHiddenAttribute(Item linkTypeType, boolean value) {
		try {
			linkTypeType.setAttribute(CadseGCST.LINK_TYPE_TYPE_at_HIDDEN_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final boolean isReadOnlyAttribute(Item linkTypeType) {
		return linkTypeType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_TYPE_at_READ_ONLY_, false);
	}

	/**
		@generated
	*/
	public static final void setReadOnlyAttribute(Item linkTypeType, boolean value) {
		try {
			linkTypeType.setAttribute(CadseGCST.LINK_TYPE_TYPE_at_READ_ONLY_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final boolean isValidAttribute(Item linkTypeType) {
		return linkTypeType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_TYPE_at_VALID_, false);
	}

	/**
		@generated
	*/
	public static final void setValidAttribute(Item linkTypeType, boolean value) {
		try {
			linkTypeType.setAttribute(CadseGCST.LINK_TYPE_TYPE_at_VALID_, value);
		} catch (Throwable t) {

		}
	}

}


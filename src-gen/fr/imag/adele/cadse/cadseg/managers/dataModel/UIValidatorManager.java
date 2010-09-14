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
package fr.imag.adele.cadse.cadseg.managers.dataModel;


import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;



/**
    @generated
*/
public class UIValidatorManager extends ItemManager {

	/**
	    @generated
	*/
	public UIValidatorManager() {
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
			if (parent != null) {
				value = ItemManager.getQualifiedNameAttribute(parent);
				sb.append(
				String.valueOf(value));
			}
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
		get  links 'overwrite' from 'UIValidator' to 'UIValidator'.
        @generated
    */
    static public List<Link> getOverwriteLink(Item uIValidator) {
        return uIValidator.getOutgoingLinks(CadseGCST.UIVALIDATOR_lt_OVERWRITE);
    }

	/**
        @generated
    */
    static public Collection<Item> getOverwriteAll(Item uIValidator) {
        return uIValidator.getOutgoingItems(CadseGCST.UIVALIDATOR_lt_OVERWRITE, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getOverwrite(Item uIValidator) {
        return uIValidator.getOutgoingItems(CadseGCST.UIVALIDATOR_lt_OVERWRITE,true);
    }

	/**
        @generated
    */
    static public void addOverwrite(Item uIValidator, Item value) throws CadseException {
        uIValidator.addOutgoingItem(CadseGCST.UIVALIDATOR_lt_OVERWRITE,value);
    }

	/**
        @generated
    */
    static public void removeOverwrite(Item uIValidator, Item value) throws CadseException {
        uIValidator.removeOutgoingItem(CadseGCST.UIVALIDATOR_lt_OVERWRITE,value);
    }

	/**
		get  links 'listenAttributes' from 'UIValidator' to 'Attribute'.
        @generated
    */
    static public List<Link> getListenAttributesLink(Item uIValidator) {
        return uIValidator.getOutgoingLinks(CadseGCST.UIVALIDATOR_lt_LISTEN_ATTRIBUTES);
    }

	/**
        @generated
    */
    static public Collection<Item> getListenAttributesAll(Item uIValidator) {
        return uIValidator.getOutgoingItems(CadseGCST.UIVALIDATOR_lt_LISTEN_ATTRIBUTES, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getListenAttributes(Item uIValidator) {
        return uIValidator.getOutgoingItems(CadseGCST.UIVALIDATOR_lt_LISTEN_ATTRIBUTES,true);
    }

	/**
        @generated
    */
    static public void addListenAttributes(Item uIValidator, Item value) throws CadseException {
        uIValidator.addOutgoingItem(CadseGCST.UIVALIDATOR_lt_LISTEN_ATTRIBUTES,value);
    }

	/**
        @generated
    */
    static public void removeListenAttributes(Item uIValidator, Item value) throws CadseException {
        uIValidator.removeOutgoingItem(CadseGCST.UIVALIDATOR_lt_LISTEN_ATTRIBUTES,value);
    }

}
/** Licensed to the Apache Software Foundation (ASF) under one* or more contributor license agreements.  See the NOTICE file* distributed with this work for additional information* regarding copyright ownership.  The ASF licenses this file* to you under the Apache License, Version 2.0 (the* "License"); you may not use this file except in compliance* with the License.  You may obtain a copy of the License at**   http://www.apache.org/licenses/LICENSE-2.0** Unless required by applicable law or agreed to in writing,* software distributed under the License is distributed on an* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY* KIND, either express or implied.  See the License for the* specific language governing permissions and limitations* under the License.*/


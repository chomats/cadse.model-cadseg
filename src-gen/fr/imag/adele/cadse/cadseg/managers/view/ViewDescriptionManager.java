package fr.imag.adele.cadse.cadseg.managers.view;


import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import java.util.Collection;
import java.util.List;



/**
    @generated
*/
public class ViewDescriptionManager extends ItemManager {

	/**
	    @generated
	*/
	public ViewDescriptionManager() {
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
	}	/**
		get  links 'root-types' from 'ViewDescription' to 'TypeDefinition'.
        @generated
    */
    static public List<Link> getRootTypesLink(Item viewDescription) {
        return viewDescription.getOutgoingLinks(CadseGCST.VIEW_DESCRIPTION_lt_ROOT_TYPES);
    }

    /**
        @generated
    */
    static public Collection<Item> getRootTypesAll(Item viewDescription) {
        return viewDescription.getOutgoingItems(CadseGCST.VIEW_DESCRIPTION_lt_ROOT_TYPES, false);
    }

    /**
        @generated
    */
    static public Collection<Item> getRootTypes(Item viewDescription) {
        return viewDescription.getOutgoingItems(CadseGCST.VIEW_DESCRIPTION_lt_ROOT_TYPES,true);
    }

    /**
        @generated
    */
    static public void addRootTypes(Item viewDescription, Item value) throws CadseException {
        viewDescription.addOutgoingItem(CadseGCST.VIEW_DESCRIPTION_lt_ROOT_TYPES,value);
    }

    /**
        @generated
    */
    static public void removeRootTypes(Item viewDescription, Item value) throws CadseException {
        viewDescription.removeOutgoingItem(CadseGCST.VIEW_DESCRIPTION_lt_ROOT_TYPES,value);
    }


}
/** Licensed to the Apache Software Foundation (ASF) under one* or more contributor license agreements.  See the NOTICE file* distributed with this work for additional information* regarding copyright ownership.  The ASF licenses this file* to you under the Apache License, Version 2.0 (the* "License"); you may not use this file except in compliance* with the License.  You may obtain a copy of the License at**   http://www.apache.org/licenses/LICENSE-2.0** Unless required by applicable law or agreed to in writing,* software distributed under the License is distributed on an* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY* KIND, either express or implied.  See the License for the* specific language governing permissions and limitations* under the License.*/


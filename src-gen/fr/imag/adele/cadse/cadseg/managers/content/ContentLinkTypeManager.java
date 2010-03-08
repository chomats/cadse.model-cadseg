package fr.imag.adele.cadse.cadseg.managers.content;


import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;



/**
    @generated
*/
public class ContentLinkTypeManager extends LinkTypeManager {

	/**
	    @generated
	*/
	public ContentLinkTypeManager() {
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
		get a link 'content-definition' from 'ContentLinkType' to 'ContentItemType'.
		@generated
	*/
	static public Link getContentDefinitionLink(Item contentLinkType) {
		return contentLinkType.getOutgoingLink(CadseGCST.CONTENT_LINK_TYPE_lt_CONTENT_DEFINITION);
	}

	/**
		get all link destination 'content-definition' from 'ContentLinkType' to 'ContentItemType'.
		@generated
	*/
	static public Item getContentDefinitionAll(Item contentLinkType) {
		return contentLinkType.getOutgoingItem(CadseGCST.CONTENT_LINK_TYPE_lt_CONTENT_DEFINITION, false);
	}

	/**
		get resolved link destination 'content-definition' from 'ContentLinkType' to 'ContentItemType'.
		@generated
	*/
	static public Item getContentDefinition(Item contentLinkType) {
		return contentLinkType.getOutgoingItem(CadseGCST.CONTENT_LINK_TYPE_lt_CONTENT_DEFINITION, true);
	}

	/**
		set a link 'content-definition' from 'ContentLinkType' to 'ContentItemType'.
		@generated
	*/
	static public void setContentDefinition(Item contentLinkType, Item value) throws CadseException {
		contentLinkType.setOutgoingItem(CadseGCST.CONTENT_LINK_TYPE_lt_CONTENT_DEFINITION,value);
	}

}
/** Licensed to the Apache Software Foundation (ASF) under one* or more contributor license agreements.  See the NOTICE file* distributed with this work for additional information* regarding copyright ownership.  The ASF licenses this file* to you under the Apache License, Version 2.0 (the* "License"); you may not use this file except in compliance* with the License.  You may obtain a copy of the License at**   http://www.apache.org/licenses/LICENSE-2.0** Unless required by applicable law or agreed to in writing,* software distributed under the License is distributed on an* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY* KIND, either express or implied.  See the License for the* specific language governing permissions and limitations* under the License.*/


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
package fr.imag.adele.cadse.cadseg.managers.content;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * @generated
 */
public class AspectJProjectContentModelManager extends JavaProjectContentModelManager {

	/**
	 * @generated
	 */
	public AspectJProjectContentModelManager() {
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
	 * get links 'content-model' from 'AspectJProjectContentModel' to
	 * 'ResourceContentModel'.
	 * 
	 * @generated
	 */
	static public List<Link> getContentModelLink(Item aspectJProjectContentModel) {
        return aspectJProjectContentModel.getOutgoingLinks(CadseGCST.ASPECT_JPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getContentModelAll(Item aspectJProjectContentModel) {
        return aspectJProjectContentModel.getOutgoingItems(CadseGCST.ASPECT_JPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL, false);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getContentModel(Item aspectJProjectContentModel) {
        return aspectJProjectContentModel.getOutgoingItems(CadseGCST.ASPECT_JPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL,true);
    }

	/**
	 * @generated
	 */
	static public void addContentModel(Item aspectJProjectContentModel, Item value) throws CadseException {
        aspectJProjectContentModel.addOutgoingItem(CadseGCST.ASPECT_JPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL,value);
    }

	/**
	 * @generated
	 */
	static public void removeContentModel(Item aspectJProjectContentModel, Item value) throws CadseException {
        aspectJProjectContentModel.removeOutgoingItem(CadseGCST.ASPECT_JPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL,value);
    }

}

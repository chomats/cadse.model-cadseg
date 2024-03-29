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
 * The Class PDEProjectContentModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class PDEProjectContentModelManager extends JavaProjectContentModelManager {

	/**
	 * Instantiates a new pDE project content model manager.
	 */
	public PDEProjectContentModelManager() {
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
	 * Gets the display name.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the display name
	 * 
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
	 * get links 'content-model' from 'PDEProjectContentModel' to
	 * 'ResourceContentModel'.
	 * 
	 * @param pDEProjectContentModel
	 *            the dE project content model
	 * 
	 * @return the content model link
	 * 
	 * @generated
	 */
	static public List<Link> getContentModelLink(Item pDEProjectContentModel) {
        return pDEProjectContentModel.getOutgoingLinks(CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL);
    }

	/**
	 * Gets the content model all.
	 * 
	 * @param pDEProjectContentModel
	 *            the dE project content model
	 * 
	 * @return the content model all
	 * 
	 * @generated
	 */
	static public Collection<Item> getContentModelAll(Item pDEProjectContentModel) {
        return pDEProjectContentModel.getOutgoingItems(CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL, false);
    }

	/**
	 * Gets the content model.
	 * 
	 * @param pDEProjectContentModel
	 *            the dE project content model
	 * 
	 * @return the content model
	 * 
	 * @generated
	 */
	static public Collection<Item> getContentModel(Item pDEProjectContentModel) {
        return pDEProjectContentModel.getOutgoingItems(CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL,true);
    }

	/**
	 * Adds the content model.
	 * 
	 * @param pDEProjectContentModel
	 *            the dE project content model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addContentModel(Item pDEProjectContentModel, Item value) throws CadseException {
        pDEProjectContentModel.addOutgoingItem(CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL,value);
    }

	/**
	 * Removes the content model.
	 * 
	 * @param pDEProjectContentModel
	 *            the dE project content model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeContentModel(Item pDEProjectContentModel, Item value) throws CadseException {
        pDEProjectContentModel.removeOutgoingItem(CadseGCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL,value);
    }

	

}

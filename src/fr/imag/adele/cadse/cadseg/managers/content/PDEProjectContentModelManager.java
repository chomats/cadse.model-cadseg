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

package fr.imag.adele.cadse.cadseg.managers.content;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
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
	 * Compute unique name.
	 * 
	 * @param item
	 *            the item
	 * @param shortName
	 *            the short name
	 * @param parent
	 *            the parent
	 * @param lt
	 *            the lt
	 * 
	 * @return the string
	 * 
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
			Item currentItem;
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
		return pDEProjectContentModel.getOutgoingLinks(WorkspaceCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL);
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
		return pDEProjectContentModel.getOutgoingItems(WorkspaceCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL, false);
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
		return pDEProjectContentModel.getOutgoingItems(WorkspaceCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL, true);
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
		pDEProjectContentModel.addOutgoingItem(WorkspaceCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL, value);
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
		pDEProjectContentModel.removeOutgoingItem(WorkspaceCST.PDEPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL, value);
	}

	@Override
	public boolean hasParentContent() {
		return false;
	}

	@Override
	public Class<? extends ContentItem> getRuntimeClassName() {
		return fede.workspace.eclipse.composition.java.EclipsePluginContentManger.class;
	}

}

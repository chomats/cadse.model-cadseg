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

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import java.util.Collection;
import java.util.List;
import fr.imag.adele.cadse.core.impl.ContentItemImpl;

/**
 * The Class ResourceContentModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ResourceContentModelManager extends ContentModelManager {

	/**
	 * Instantiates a new resource content model manager.
	 */
	public ResourceContentModelManager() {
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
	 * get links '#invert_part_content-model_to_FolderContentModel' from
	 * 'ResourceContentModel' to 'FolderContentModel'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_contentModel_to_FolderContentModelLink(Item resourceContentModel) {
		return resourceContentModel
				.getOutgoingLink(WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_FOLDER_CONTENT_MODEL);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_contentModel_to_FolderContentModelAll(Item resourceContentModel) {
		return resourceContentModel.getOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_FOLDER_CONTENT_MODEL, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_contentModel_to_FolderContentModel(Item resourceContentModel) {
		return resourceContentModel.getOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_FOLDER_CONTENT_MODEL, true);
	}

	/**
	 * set a link '#invert_part_content-model_to_FolderContentModel' from
	 * 'ResourceContentModel' to 'FolderContentModel'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_contentModel_to_FolderContentModel(Item resourceContentModel, Item value)
			throws CadseException {
		resourceContentModel.setOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_FOLDER_CONTENT_MODEL, value);
	}

	/**
	 * get links '#invert_part_content-model_to_JavaProjectContentModel' from
	 * 'ResourceContentModel' to 'JavaProjectContentModel'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_contentModel_to_JavaProjectContentModelLink(Item resourceContentModel) {
		return resourceContentModel
				.getOutgoingLink(WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_JAVA_PROJECT_CONTENT_MODEL);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_contentModel_to_JavaProjectContentModelAll(Item resourceContentModel) {
		return resourceContentModel.getOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_JAVA_PROJECT_CONTENT_MODEL,
				false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_contentModel_to_JavaProjectContentModel(Item resourceContentModel) {
		return resourceContentModel
				.getOutgoingItem(
						WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_JAVA_PROJECT_CONTENT_MODEL,
						true);
	}

	/**
	 * set a link '#invert_part_content-model_to_JavaProjectContentModel' from
	 * 'ResourceContentModel' to 'JavaProjectContentModel'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_contentModel_to_JavaProjectContentModel(Item resourceContentModel, Item value)
			throws CadseException {
		resourceContentModel.setOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_JAVA_PROJECT_CONTENT_MODEL,
				value);
	}

	/**
	 * get links '#invert_part_content-model_to_AspectJProjectContentModel' from
	 * 'ResourceContentModel' to 'AspectJProjectContentModel'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_contentModel_to_AspectJProjectContentModelLink(Item resourceContentModel) {
		return resourceContentModel
				.getOutgoingLink(WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_ASPECT_JPROJECT_CONTENT_MODEL);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_contentModel_to_AspectJProjectContentModelAll(Item resourceContentModel) {
		return resourceContentModel.getOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_ASPECT_JPROJECT_CONTENT_MODEL,
				false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_contentModel_to_AspectJProjectContentModel(Item resourceContentModel) {
		return resourceContentModel.getOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_ASPECT_JPROJECT_CONTENT_MODEL,
				true);
	}

	/**
	 * set a link '#invert_part_content-model_to_AspectJProjectContentModel'
	 * from 'ResourceContentModel' to 'AspectJProjectContentModel'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_contentModel_to_AspectJProjectContentModel(Item resourceContentModel,
			Item value) throws CadseException {
		resourceContentModel.setOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_ASPECT_JPROJECT_CONTENT_MODEL,
				value);
	}

	/**
	 * get links '#invert_part_content-model_to_ProjectContentModel' from
	 * 'ResourceContentModel' to 'ProjectContentModel'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_contentModel_to_ProjectContentModelLink(Item resourceContentModel) {
		return resourceContentModel
				.getOutgoingLink(WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_PROJECT_CONTENT_MODEL);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_contentModel_to_ProjectContentModelAll(Item resourceContentModel) {
		return resourceContentModel.getOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_PROJECT_CONTENT_MODEL, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_contentModel_to_ProjectContentModel(Item resourceContentModel) {
		return resourceContentModel.getOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_PROJECT_CONTENT_MODEL, true);
	}

	/**
	 * set a link '#invert_part_content-model_to_ProjectContentModel' from
	 * 'ResourceContentModel' to 'ProjectContentModel'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_contentModel_to_ProjectContentModel(Item resourceContentModel, Item value)
			throws CadseException {
		resourceContentModel.setOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_PROJECT_CONTENT_MODEL, value);
	}

	/**
	 * get links '#invert_part_content-model_to_PDEProjectContentModel' from
	 * 'ResourceContentModel' to 'PDEProjectContentModel'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_contentModel_to_PDEProjectContentModelLink(Item resourceContentModel) {
		return resourceContentModel
				.getOutgoingLink(WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_PDEPROJECT_CONTENT_MODEL);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_contentModel_to_PDEProjectContentModelAll(Item resourceContentModel) {
		return resourceContentModel.getOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_PDEPROJECT_CONTENT_MODEL, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_contentModel_to_PDEProjectContentModel(Item resourceContentModel) {
		return resourceContentModel.getOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_PDEPROJECT_CONTENT_MODEL, true);
	}

	/**
	 * set a link '#invert_part_content-model_to_PDEProjectContentModel' from
	 * 'ResourceContentModel' to 'PDEProjectContentModel'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_contentModel_to_PDEProjectContentModel(Item resourceContentModel, Item value)
			throws CadseException {
		resourceContentModel.setOutgoingItem(
				WorkspaceCST.RESOURCE_CONTENT_MODEL_lt__$_INVERT_PART_CONTENT_MODEL_TO_PDEPROJECT_CONTENT_MODEL, value);
	}

	@Override
	public boolean hasParentContent() {
		return true;
	}

	@Override
	public Class<? extends ContentItem> getRuntimeClassName() {
		return ContentItemImpl.class;
	}

}

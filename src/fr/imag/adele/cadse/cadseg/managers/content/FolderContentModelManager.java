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

import fede.workspace.eclipse.content.FolderContentManager;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class FolderContentModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class FolderContentModelManager extends ResourceContentModelManager {

	/**
	 * The Class ContentManager.
	 */
	public class MyContentItem extends ContentModelManager.MyContentItem {

		/**
		 * Instantiates a new content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		public MyContentItem(ContentItem parent, Item item) {
			super(parent, item);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#getResourceKindsName()
		 */
		@Override
		protected String[] getResourceKindsName() {
			return new String[] { WorkspaceCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH };
		}
	}

	/**
	 * Instantiates a new folder content model manager.
	 */
	public FolderContentModelManager() {
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
	 * get links 'content-model' from 'FolderContentModel' to
	 * 'ResourceContentModel'.
	 * 
	 * @param folderContentModel
	 *            the folder content model
	 * 
	 * @return the content model link
	 * 
	 * @generated
	 */
	static public List<Link> getContentModelLink(Item folderContentModel) {
		return folderContentModel.getOutgoingLinks(WorkspaceCST.FOLDER_CONTENT_MODEL_lt_CONTENT_MODEL);
	}

	/**
	 * Gets the content model all.
	 * 
	 * @param folderContentModel
	 *            the folder content model
	 * 
	 * @return the content model all
	 * 
	 * @generated
	 */
	static public Collection<Item> getContentModelAll(Item folderContentModel) {
		return folderContentModel.getOutgoingItems(WorkspaceCST.FOLDER_CONTENT_MODEL_lt_CONTENT_MODEL, false);
	}

	/**
	 * Gets the content model.
	 * 
	 * @param folderContentModel
	 *            the folder content model
	 * 
	 * @return the content model
	 * 
	 * @generated
	 */
	static public Collection<Item> getContentModel(Item folderContentModel) {
		return folderContentModel.getOutgoingItems(WorkspaceCST.FOLDER_CONTENT_MODEL_lt_CONTENT_MODEL, true);
	}

	/**
	 * Adds the content model.
	 * 
	 * @param folderContentModel
	 *            the folder content model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addContentModel(Item folderContentModel, Item value) throws CadseException {
		folderContentModel.addOutgoingItem(WorkspaceCST.FOLDER_CONTENT_MODEL_lt_CONTENT_MODEL, value);
	}

	/**
	 * Removes the content model.
	 * 
	 * @param folderContentModel
	 *            the folder content model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeContentModel(Item folderContentModel, Item value) throws CadseException {
		folderContentModel.removeOutgoingItem(WorkspaceCST.FOLDER_CONTENT_MODEL_lt_CONTENT_MODEL, value);
	}

	/**
	 * @generated
	 */
	public static final String getFolderPathAttribute(Item folderContentModel) {
		return folderContentModel.getAttributeWithDefaultValue(WorkspaceCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH_, null);
	}

	/**
	 * @generated
	 */
	public static final void setFolderPathAttribute(Item folderContentModel, String value) {
		try {
			folderContentModel.setAttribute(WorkspaceCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH_, value);
		} catch (Throwable t) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentManager(Item item) throws CadseException {
		return new MyContentItem(null, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	@Override
	public Class<? extends ContentItem> getRuntimeClassName() {
		return FolderContentManager.class;
	}
}

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

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class SourceFolderContentModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class SourceFolderContentModelManager extends FolderContentModelManager {

	/** The Constant FOLDER_PATH_ATTRIBUTE. */
	public static final String	OUTPUT_PATH_ATTRIBUTE	= "output-path";

	/**
	 * The Class ContentManager.
	 */
	public class MyContentItem extends FolderContentModelManager.MyContentItem {

		/**
		 * Instantiates a new content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		public MyContentItem(CompactUUID id) {
			super(id);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#getResourceKindsName()
		 */
		@Override
		protected String[] getResourceKindsName() {
			return new String[] { CadseGCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH, OUTPUT_PATH_ATTRIBUTE };
		}

		@Override
		protected String getDefaultValue(String strKinds, String value) {
			if (strKinds.equals(OUTPUT_PATH_ATTRIBUTE)) {
				if (value != null && value.length() == 0) {
					return null;
				}
				return value;
			}
			return super.getDefaultValue(strKinds, value);
		}
	}

	/**
	 * Instantiates a new source folder content model manager.
	 */
	public SourceFolderContentModelManager() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentItem(CompactUUID id) throws CadseException {
		return new MyContentItem(id);
	}

	@Override
	public boolean hasParentContent() {
		return true;
	}

	@Override
	public Class<? extends ContentItem> getRuntimeClassName() {
		return fede.workspace.eclipse.java.manager.JavaSourceFolderContentManager.class;
	}
}
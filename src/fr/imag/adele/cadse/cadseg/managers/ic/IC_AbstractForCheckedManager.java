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
package fr.imag.adele.cadse.cadseg.managers.ic;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.var.Variable;
import fede.workspace.eclipse.content.SubFileContentManager;
import fede.workspace.model.manager.properties.impl.ic.IC_AbstractForChecked;

/**
 * @generated
 */
public class IC_AbstractForCheckedManager extends InteractionControllerManager {

	/**
	 * @generated
	 */
	public class MyContentItem extends InteractionControllerManager.MyContentItem {

		/**
		 * @generated
		 */
		public MyContentItem(ContentItem parent, Item item) throws CadseException {
			super(parent, item);
		}

	}

	/**
	 * @generated
	 */
	public IC_AbstractForCheckedManager() {
		super();
	}

	/**
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
	 * @generated
	 */
	@Override
	public ContentItem createContentManager(Item iC_AbstractForChecked) throws CadseException {
		MyContentItem cm = new MyContentItem(null, iC_AbstractForChecked);
		cm.setComposers();
		cm.setExporters();
		return cm;
	}

	@Override
	public String getDefaultClassName() {
		return "fede.workspace.model.manager.properties.impl.ic.IC_AbstractForChecked";
	}

}

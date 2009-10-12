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

package fr.imag.adele.cadse.cadseg.managers.dataModel;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.MelusineProjectManager;
import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.generate.GenerateExtItemType;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.var.NullVariable;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.key.SpaceKeyType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.Variable;
import fr.imag.adele.fede.workspace.si.view.View;

/**
 * The Class ExtItemTypeManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ExtItemTypeManager extends ItemTypeManager {

	/**
		@generated
	*/
	public class ExtItemTypeContent extends JavaFileContentManager {

		/**
			@generated
		*/
		public ExtItemTypeContent(CompactUUID id, Variable packageNameVariable, Variable classNameVariable) throws CadseException {
			super(id, packageNameVariable, classNameVariable);
		}

	}

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ExtItemTypeManager() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#init(fr.imag.adele.cadse
	 * .core.ItemType)
	 */
	@Override
	public void init() {
		CadseGCST.EXT_ITEM_TYPE.setSpaceKeyType(new SpaceKeyType(CadseGCST.EXT_ITEM_TYPE, null));
		CadseGCST.EXT_ITEM_TYPE.setHasShortNameAttribute(true);
		CadseGCST.EXT_ITEM_TYPE.setHasUniqueNameAttribute(false);
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
		@generated
	*/
	@Override
	public ContentItem createContentItem(CompactUUID id ) throws CadseException {
		ExtItemTypeContent cm = new ExtItemTypeContent(
			id, NullVariable.INSTANCE, NullVariable.INSTANCE
			);
		cm.setComposers(
		);
		cm.setExporters(
		);
		return cm;
	}

	/**
	 * get a link 'ref-type' from 'ExtItemType' to 'ItemType'.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the ref type link
	 * 
	 * @generated
	 */
	static public Link getRefTypeLink(Item extItemType) {
		return extItemType.getOutgoingLink(CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE);
	}

	/**
	 * get all link destination 'ref-type' from 'ExtItemType' to 'ItemType'.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the ref type all
	 * 
	 * @generated
	 */
	static public Item getRefTypeAll(Item extItemType) {
		return extItemType.getOutgoingItem(CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE, false);
	}

	/**
	 * get resolved link destination 'ref-type' from 'ExtItemType' to
	 * 'ItemType'.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the ref type
	 * 
	 * @generated
	 */
	static public Item getRefType(Item extItemType) {
		return extItemType.getOutgoingItem(CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE, true);
	}

	/**
	 * set a link 'ref-type' from 'ExtItemType' to 'ItemType'.
	 * 
	 * @generated
	 */
	static public void setRefType(Item extItemType, Item value) throws CadseException {
		extItemType.setOutgoingItem(CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE,value);
	}

	

}

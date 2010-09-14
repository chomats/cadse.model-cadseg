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
package fr.imag.adele.cadse.cadseg.eclipse.linkmanagers.dataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.TreeViewer;

import fede.workspace.model.manager.DefaultLinkTypeManager;
import fede.workspace.tool.view.WSPlugin;
import fede.workspace.tool.view.node.FilterItem;
import fede.workspace.tool.view.node.FilteredItemNode;
import fede.workspace.tool.view.node.FilteredItemNodeModel;
import fede.workspace.tool.view.node.ItemsFromLinkOfLinkTypeRule;
import fede.workspace.tool.view.node.ItemsRule;
import fr.imag.adele.cadse.cadseg.ItemShortNameComparator;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseUtil;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class ItemType_lt_SuperType.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ItemType_lt_SuperType extends DefaultLinkTypeManager {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.AbstractLinkTypeManager#getSelectingDestination(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Collection<Item> getSelectingDestination(Item theItemType) {

		Item cadsedef = ItemTypeManager.getCadseDefinition(theItemType);

		ArrayList<Item> ret = new ArrayList<Item>(Arrays.asList(ItemTypeManager.getAllAllItemType(cadsedef, null, true)));

		ret.removeAll(CadseUtil.incomingClosure(theItemType, CadseGCST.ITEM_TYPE_lt_SUPER_TYPE));

		return ret;
	}

	// public ITreeContentProvider getContentProvider() {
	// return new ItemTreeContentProvider(new ItemShortNameComparator(),
	// CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL,
	// CadseGCST.DATA_MODEL_lt_TYPES,
	// CadseGCST.DATA_MODEL_lt_CATEGORIES);
	// }
	//	
	// @Override
	// public ILabelProvider getLabelProvider() {
	// return LinkLabelProvider.INSTANCE;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultLinkTypeManager#validate(fr.imag.adele.cadse.core.Item,
	 *      java.lang.Object[])
	 */
	public IStatus validate(Item source, Object[] selection) {
		Item theItemType = source;
		if (selection != null && selection.length == 1) {
			Object sel = selection[0];
			if (sel instanceof Item) {
				if (((Item) sel).getType() == CadseGCST.ITEM_TYPE) {
					Collection<Item> incomingClosures = CadseUtil.incomingClosure(theItemType,
							CadseGCST.ITEM_TYPE_lt_SUPER_TYPE);
					if (incomingClosures.contains(sel))
						return new Status(Status.ERROR, WSPlugin.PLUGIN_ID, "select an item type");
					return Status.OK_STATUS;
				}
			}
		}
		return new Status(Status.ERROR, WSPlugin.PLUGIN_ID, "select an item type");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultLinkTypeManager#getInputValues(fr.imag.adele.cadse.core.Item,
	 *      org.eclipse.jface.viewers.TreeViewer, fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	public Object getInputValues(Item source, TreeViewer viewer, Item itemParent, LinkType linkType, ItemType itemDest) {
		Item theItemType = source;
		Item cadsedef = theItemType.getPartParent(CadseGCST.CADSE_DEFINITION);
		List<Item> allcadse = CadseDefinitionManager.getAllDependenciesCadse(cadsedef);
		allcadse.add(cadsedef);
		Item[] ret = (Item[]) allcadse.toArray(new Item[allcadse.size()]);
		ItemShortNameComparator itemShortNameComparator = new ItemShortNameComparator();
		Arrays.sort(ret, itemShortNameComparator);

		FilteredItemNode node = new FilteredItemNode(viewer);
		FilteredItemNodeModel model = node.getModel();
		model.addRule(FilteredItemNodeModel.ROOT_ENTRY, new ItemsRule(ret));
		model.addItemFromLinkTypeEntry(CadseGCST.CADSE_DEFINITION, CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL,
				null, true, false);
		model.addRule(CadseGCST.DATA_MODEL, new ItemsFromLinkOfLinkTypeRule(CadseGCST.DATA_MODEL_lt_TYPES, null,
				true, false, getSelectingDestination(source)));
		model.addRule(CadseGCST.DATA_MODEL, new ItemsFromLinkOfLinkTypeRule(CadseGCST.DATA_MODEL_lt_CATEGORIES,
				null, true, false, (FilterItem) null));
		return node;
	}

}

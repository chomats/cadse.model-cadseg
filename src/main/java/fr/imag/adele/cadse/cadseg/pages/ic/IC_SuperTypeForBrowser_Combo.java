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
package fr.imag.adele.cadse.cadseg.pages.ic;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ITreeContentProvider;

import fr.imag.adele.cadse.cadseg.ItemShortNameComparator;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseUtil;
import fr.imag.adele.cadse.core.Item;
import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.ItemTreeContentProvider;
/**
 * The Class IC_SuperTypeForBrowser_Combo.
 */
@SuppressWarnings("deprecation")
public final class IC_SuperTypeForBrowser_Combo extends IC_LinkForBrowser_Combo_List {

	public IC_SuperTypeForBrowser_Combo() {
	}
	
	/**
	 * Instantiates a new i c_ super type for browser_ combo.
	 * 
	 * @param title
	 *            the title
	 * @param message
	 *            the message
	 */
	public IC_SuperTypeForBrowser_Combo(String title, String message) {
		this._title = title;
		this._message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#getTreeContentProvider()
	 */
	@Override
	protected ITreeContentProvider getTreeContentProvider() {
		return new ItemTreeContentProvider(new ItemShortNameComparator(), CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL,
				CadseGCST.DATA_MODEL_lt_TYPES, CadseGCST.DATA_MODEL_lt_CATEGORIES, 
				CadseGCST.CADSE_lt_ITEM_TYPES);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#validate(java.lang.Object[])
	 */
	@Override
	public IStatus validate(Object[] selection) {
		Item theItemType = getItem();
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
	 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#getInputValues()
	 */
	@Override
	protected Object getInputValues() {
		Item theItemType = getItem();
		Item cadsedef = theItemType.getPartParent(CadseGCST.CADSE_DEFINITION);
		List<Item> allcadse = CadseDefinitionManager.getAllDependenciesCadse(cadsedef);
		allcadse.add(cadsedef);
		Item[] ret = (Item[]) allcadse.toArray(new Item[allcadse.size()]);
		Arrays.sort(ret, new ItemShortNameComparator());
		return ret;
	}
}
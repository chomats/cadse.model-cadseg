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

package fr.imag.adele.cadse.cadseg.operation;

import java.util.Arrays;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.WorkspacePlugin;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Menu;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.ui.IActionContributor;
import fr.imag.adele.cadse.core.ui.view.ViewDescription;

/**
 * The Class WorkspaceActionContributor.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class WorkspaceActionContributor implements IActionContributor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.IActionContributor#contributeMenuAction(fr.imag.adele.cadse.core.IMenuAction.Menu,
	 *      fr.imag.adele.cadse.core.IItemNode[])
	 */
	public void contributeMenuAction(ViewDescription viewDescription, Menu menu, IItemNode[] selection) {
		LogicalWorkspace wl = CadseCore.getLogicalWorkspace();
		if (Arrays.asList(wl.getCadseName()).contains(WorkspacePlugin.PLUGIN_ID)) {
			// if (menu.find(ImportAction.IMPORT_BINARY_CADSE) == null)
			// menu.insert(IMenuAction.CONTEXT_3_MENU, new ImportAction(),
			// true);
			// if (menu.find(MigrateCodeAction.IMPORT_BINARY_CADSE) == null)
			// menu.insert(IMenuAction.CONTEXT_3_MENU, new MigrateCodeAction(),
			// true);
			if (menu.find(ImportCadseAction.IMPORT_BINARY_CADSE) == null) {
				menu.insert(IMenuAction.CONTEXT_3_MENU, new ImportCadseAction(), true);
			}
		}
		Item item;
		if (selection != null && selection.length == 1 && (item = selection[0].getItem()) != null
				&& item.getType() == WorkspaceCST.CADSE_DEFINITION) {
			menu.insert(IMenuAction.CONTEXT_3_MENU, new ExportCadseAction(item), true);
		}
	}

}

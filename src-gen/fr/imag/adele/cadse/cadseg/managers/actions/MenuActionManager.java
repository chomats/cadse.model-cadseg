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

package fr.imag.adele.cadse.cadseg.managers.actions;

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import fr.imag.adele.cadse.core.CadseGCST;
import java.util.UUID;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.cadseg.contents.actions.MenuActionCIF;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.impl.var.NullVariable;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.Variable;

/**
 * The Class MenuActionManager.
 * 
 * @generated
 */
public class MenuActionManager extends MenuAbstractManager {

	/**
		@generated
	*/
	public class MenuActionContent extends JavaFileContentManager {

		/**
			@generated
		*/
		public MenuActionContent(UUID id, Variable packageNameVariable, Variable classNameVariable) throws CadseException {
			super(id, packageNameVariable, classNameVariable);
		}

	}



	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public MenuActionManager() {
		super();
	}

	@Override
	public IContentItemFactory getContentItemFactory() {
		return new MenuActionCIF();
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
	public ContentItem createContentItem(UUID id, Item owerItem ) throws CadseException {
		MenuActionContent cm = new MenuActionContent(
			id, NullVariable.INSTANCE, NullVariable.INSTANCE
			);
		cm.setComposers(
		);
		cm.setExporters(
		);
		return cm;
	}

	@Override
	public void doubleClick(Item item) {
		if (item != null) {
			IFile jf = item.getMainMappingContent(IFile.class);
			if (jf != null) {
				try {
					IWorkbench workbench = PlatformUI.getWorkbench();
					IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
					IDE.openEditor(activePage, jf, true);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

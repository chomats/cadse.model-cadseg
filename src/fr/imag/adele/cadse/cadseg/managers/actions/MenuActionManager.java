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

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.contents.actions.MenuActionCIF;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.Variable;

/**
 * The Class MenuActionManager.
 * 
 * @generated
 */
public class MenuActionManager extends DefaultItemManager {

	/**
	 * @generated
	 */
	static final class PackageNameVariable extends VariableImpl {

		/**
		 * @generated
		 */
		public final static Variable	INSTANCE	= new PackageNameVariable();

		/**
		 * @generated
		 */
		public String compute(ContextVariable context, Item itemCurrent) {
			try {
				Object value;
				Item currentItem;
				return context.getName(itemCurrent);
			} catch (Throwable e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	/**
	 * @generated
	 */
	static final class ClassNameVariable extends VariableImpl {

		/**
		 * @generated
		 */
		public final static Variable	INSTANCE	= new ClassNameVariable();

		/**
		 * @generated
		 */
		public String compute(ContextVariable context, Item itemCurrent) {
			try {
				Object value;
				Item currentItem;
				return context.getName(itemCurrent);
			} catch (Throwable e) {
				e.printStackTrace();
				return "error";
			}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#init(fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public void init() {
		WorkspaceCST.MENU_ACTION.setHasUniqueNameAttribute(false);
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
			Item currentItem;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
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

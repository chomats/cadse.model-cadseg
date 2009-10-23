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
package fr.imag.adele.cadse.cadseg.menu;

import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.ui.IWorkbenchWindow;

import fede.workspace.model.manager.properties.impl.ui.UIWizardDialog;
import fede.workspace.model.manager.properties.impl.ui.WizardController;
import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.ui.view.NewContext;

/**
 * A <code>BaseNewWizardMenu</code> is used to populate a menu manager with
 * New Wizard actions for the current perspective's new wizard shortcuts,
 * including an Other... action to open the new wizard dialog.
 * 
 * @since 3.1
 */
public class MenuNewAction extends IMenuAction {

	
	private IShellProvider	_workbenchWindow;
	private String				_label;
	private NewContext _c;

	/**
	 * Creates a new wizard shortcut menu for the IDE.
	 * 
	 * @param window
	 *            the window containing the menu
	 * @param parent
	 *            the item parent for the new item or the item from the new item
	 *            is created..
	 * @param string
	 *            label
	 */
	public MenuNewAction(IShellProvider window, NewContext c, String label) {
		Assert.isNotNull(window);

		this._workbenchWindow = window;
		this._c = c;
		
		if (label == null) {
			label = c.getDestinationType().getDisplayName();
		}
		this._label = label;
	}

	public int compareTo(MenuNewAction arg0) {
		return this._label.compareTo(arg0._label);
	}

	@Override
	public URL getImage() {
		return WSPlugin.getDefault().getImageURLFrom(this._c.getDestinationType(), null);
	}

	@Override
	public String getLabel() {
		return _label;
	}

	@Override
	public String getMenuPath() {
		return NEW_MENU;
	}

	@Override
	public boolean isSeparator() {
		return false;
	}

	public WizardController createWizard()
			throws CadseException {
		return new WizardController(_c.getDestinationType().getGoodCreationPage(_c), _c);
	}

	@Override
	public void run(IItemNode[] selection) throws CadseException {
		try {
			WizardController wizard = createWizard();
			UIWizardDialog wd = new UIWizardDialog(_workbenchWindow.getShell(), wizard);
			wd.open();
		} catch (Throwable e1) {
			e1.printStackTrace();
			String message = MessageFormat.format("Cannot create an item from {0}", _c);
			MessageDialog.openError(_workbenchWindow.getShell(), "Error in create dialog", message);
			WSPlugin.log(new Status(Status.ERROR, WSPlugin.PLUGIN_ID, 0, message, e1));
		}
	}

	

}
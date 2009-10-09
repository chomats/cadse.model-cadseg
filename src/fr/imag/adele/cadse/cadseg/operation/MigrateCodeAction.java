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

import java.net.URL;

import org.eclipse.jface.wizard.WizardDialog;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.ui.Pages;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ui.WizardController;

/**
 * The Class MigrateCodeAction.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class MigrateCodeAction extends IMenuAction {

	/** The Constant IMPORT_BINARY_CADSE. */
	public static final String	IMPORT_BINARY_CADSE	= "Migrate code cadse";

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#getId()
	 */
	@Override
	public String getId() {
		return IMPORT_BINARY_CADSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#getImage()
	 */
	@Override
	public URL getImage() {
		return WorkspaceCST.CADSE_DEFINITION.getImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#getLabel()
	 */
	@Override
	public String getLabel() {
		return IMPORT_BINARY_CADSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#run(fr.imag.adele.cadse.core.IItemNode[])
	 */
	@Override
	public void run(IItemNode[] selection) throws CadseException {
		try {

			MigrateCodePagesAction myaction = new MigrateCodePagesAction();
			Pages f = FieldsCore.createWizard(myaction, FieldsCore.createPage("page1", IMPORT_BINARY_CADSE,
					IMPORT_BINARY_CADSE, 4, myaction.createOldCadseField(), myaction.createNewCadseField()));

			WizardController wc = new WizardController(f);
			WizardDialog wd = new WizardDialog(null, wc);
			wd.setPageSize(300, 200);
			wd.open();

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

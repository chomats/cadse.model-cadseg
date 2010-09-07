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
package fr.imag.adele.cadse.cadseg.menu;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.ui.MenuAction;

/**
 * The Class ExportBundleMenuAction.
 * 
 * @generated
 */
public class ExportBundleMenuAction extends MenuAction {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ExportBundleMenuAction() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.MenuAction#getId()
	 */
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#run(fr.imag.adele.cadse.core.IItemNode[])
	 */
	@Override
	public void run(IItemNode[] selection) throws CadseException {
//		try {
//
//			ExportBundlePagesAction myaction = new ExportBundlePagesAction();
//			Item cadsedef = selection[0].getItem();
//			myaction.setCadsedef(cadsedef);
//			myaction.setSelectJar(null);
//			Pages f = FieldsCore.createWizard(myaction, FieldsCore.createPage("page1", "Build plugin cadse",
//					"Build plugin cadse", 4, myaction.createChooseFolderField(), myaction.createTimeStampField(),
//					myaction.createDeleteOldField(), myaction.createExportSourceField()));
//
//			WizardController wc = new WizardController(f);
//			WizardDialog wd = new WizardDialog(null, wc);
//			wd.setPageSize(300, 200);
//			wd.open();
//
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.MenuAction#isEnable(fr.imag.adele.cadse.core.IItemNode[])
	 */
	@Override
	public boolean isEnable(IItemNode[] selection) {
		IItemNode item;
		if (selection != null && selection.length == 1 && (item = selection[0]) != null && item.getItem() != null) {
			return true;
		}
		return false;
	}

}

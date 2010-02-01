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
package fr.imag.adele.cadse.cadseg.teamwork.update;

import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.dialog.SWTDialog;



/**
 * Dialog used for providing informations about current update operation to user. Status
 * informations provided to the user are : 
 * - order of update operations 
 * - status of each operation (not already performed, 
 * performed for content part, performed for item part or operation failed) 
 * - list of errors
 * 
 * @author Thomas
 * 
 */
public class UpdateStatusDialog extends SWTDialog {

	public UpdateStatusDialog(SWTUIPlatform swtuiPlatforms, String title,
			String label) {
		super(swtuiPlatforms, title, label);
	}

	public static void openDialog(UpdateState updateState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected IActionPage getFinishAction() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

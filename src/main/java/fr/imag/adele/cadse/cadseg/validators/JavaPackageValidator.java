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
package fr.imag.adele.cadse.cadseg.validators;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;

import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;

/**
 * @generated
 */
public class JavaPackageValidator extends AbstractUIRunningValidator {

	@Override
	public boolean validValue(UIField field, Object value) {
		if (checkPackageName((String) value) == true)
			return true;

		return false;
	}

	@Override
	public boolean validValueChanged(UIField field, Object value) {
		String pack_name = (String) value;
		return checkPackageName(pack_name);
	}

	private boolean checkPackageName(String pack_name) {
		IStatus res = JavaConventions.validatePackageName(pack_name, "1.5",
				"1.5");

		if (res.isOK())
			return false;
		else if (res.matches(IStatus.ERROR)) {
			_uiPlatform.setMessageError(res.getMessage());
			return true;
		} else if (res.matches(IStatus.WARNING)) {
			_uiPlatform.setMessage(res.getMessage(), UIPlatform.WARNING);
			return false;
		} else {
			_uiPlatform.setMessage(res.getMessage(), UIPlatform.INFORMATION);
			return false;
		}
	}


}

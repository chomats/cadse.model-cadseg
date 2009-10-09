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

package fr.imag.adele.cadse.cadseg.type;

import org.eclipse.core.runtime.IConfigurationElement;

import fr.imag.adele.cadse.core.ui.EPosLabel;

/**
 * The Class Attribute.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class Attribute extends AEHasAttributeValue {

	/**
	 * Instantiates a new attribute.
	 * 
	 * @param ce
	 *            the ce
	 */
	public Attribute(IConfigurationElement ce) {
		super(ce);
	}

	/**
	 * Gets the uI controller str.
	 * 
	 * @return the uI controller str
	 */
	public String getUIControllerStr() {
		return ce.getAttribute("ui-controller");
	}

	/**
	 * Gets the model controller str.
	 * 
	 * @return the model controller str
	 */
	public String getModelControllerStr() {
		return ce.getAttribute("model-controller");
	}

	/**
	 * Gets the user controller str.
	 * 
	 * @return the user controller str
	 */
	public String getUserControllerStr() {
		return ce.getAttribute("user-controller");
	}

	/**
	 * Gets the attribute type str.
	 * 
	 * @return the attribute type str
	 */
	public String getAttributeTypeStr() {
		return ce.getAttribute("type");
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return ce.getAttribute("id");
	}

	/**
	 * Gets the label.
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return ce.getAttribute("label");
	}

	/**
	 * Gets the pos label.
	 * 
	 * @return the pos label
	 */
	public EPosLabel getPosLabel() {
		String str = ce.getAttribute("pos-label");
		if (str == null)
			return EPosLabel.none;
		return EPosLabel.valueOf(str);

	}

	// <element ref="attribute-value" minOccurs="0" maxOccurs="unbounded"/>

	/**
	 * Gets the uI controller.
	 * 
	 * @return the uI controller
	 */
	public UIControllerType getUIController() {
		String str = ce.getAttribute("ui-controller");
		if (str == null)
			return null;
		return UIControllerType.getTypeFromString_(str);
	}

	/**
	 * Gets the model controller.
	 * 
	 * @return the model controller
	 */
	public ModelControllerType getModelController() {
		String str = getModelControllerStr();
		if (str == null)
			return null;
		return ModelControllerType.getTypeFromString_(str);

	}

	/**
	 * Gets the user controller.
	 * 
	 * @return the user controller
	 */
	public UserControllerType getUserController() {
		String str = getUserControllerStr();
		if (str == null)
			return null;
		return UserControllerType.getTypeFromString_(str);
	}

	// @Override
	// public void completeField(UIField fd) throws CoreException {
	// super.completeField(fd);
	// EPosLabel pl = getPosLabel();
	// if (pl != EPosLabel.none) {
	// fd.put(IFieldDescription.POS_LABEL, pl);
	// fd.put(IFieldDescription.LABEL, getLabel());
	// }
	// UIControllerType uc = getUIController();
	// if (uc != null && uc != this) {
	// uc.completeField(fd);
	// }
	// ModelControllerType mc = getModelController();
	// if (mc != null) {
	// mc.completeField(fd);
	// }
	// UserControllerType userC = getUserController();
	// if (userC != null) {
	// userC.completeField(fd);
	// }
	// }

	// public IFieldDescription createField() throws CoreException {
	// IFieldDescription fd = FieldsCore.createFD(getName());
	// completeField(fd);
	// return fd;
	// }

}

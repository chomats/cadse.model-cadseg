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
package fr.imag.adele.cadse.cadseg.validators;

import java.text.MessageFormat;

import fr.imag.adele.cadse.cadseg.Messages;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;

/**
 * Listen : CadseGCST.ITEM_at_NAME_ and CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_
 * 
 */

public class AttributeValidator  extends AbstractUIRunningValidator {

	/** The field. */
	Item	_superAttribute;

	public Item getSuperAttribute() {
		return _superAttribute;
	}


	@Override
	public void initAfterUI() {
		String shortName = _uiPlatform.getItem().getName();
		if (shortName != null && shortName.length() != 0) {
			computeSuperAttribute(shortName);

			if (this._superAttribute != null) {
				disableField();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.IValidateContributor#validValueChanged(fr.imag.adele.cadse.core.ui.UIField,
	 *      java.lang.Object)
	 */
	public boolean validValueChanged(UIField field, Object value) {
		if (field.getAttributeDefinition() == CadseGCST.ITEM_at_NAME_) {
			String shortName = (String) value;
			computeSuperAttribute(shortName);
			if (this._superAttribute != null) {
				if (this._superAttribute.getType() == _uiPlatform.getItem().getType()) {
					/*
					 * if
					 * (AttributeManager.isOverwritableAttribute(this.superAttribute)) {
					 * setDefaultValues(); disableField();
					 * pageObject.getPageController().setMessage(
					 * MessageFormat.format(Messages.information_overwrite_attribute,
					 * this.superAttribute .getPartParent().getShortName()),
					 * IPageController.INFORMATION); } else
					 */{
						_uiPlatform.setMessage(
								MessageFormat.format(Messages.error_overwrite_attribute_cannot_permitted,
										this._superAttribute.getPartParent().getName()), UIPlatform.ERROR);
					}

				} else {
					_uiPlatform.setMessage(
							MessageFormat.format(Messages.error_overwrite_attribute_bad_type, this._superAttribute
									.getPartParent().getName(), this._superAttribute.getType().getName(), _uiPlatform.getItem()
									.getType().getName()), UIPlatform.ERROR);
					return true;
				}
			} else {
				enableField();
			}
		}
		return false;
	}

	private  void computeSuperAttribute(String shortName) {
		this._superAttribute = ItemTypeManager.findSuperAttribute(_uiPlatform.getItem(), shortName);
	}
	
	private void setEnable(IAttributeType<?> att, boolean v) {
		UIField f = _uiPlatform.getField(att);
		if (f != null)
			_uiPlatform.setEnabled(f, v);
	}

	private  void enableField() {
		setEnable(CadseGCST.ATTRIBUTE_at_IS_LIST_, true);
		setEnable(CadseGCST.ATTRIBUTE_at_REQUIRE_, true);
	}

	private  void disableField() {
		setEnable(CadseGCST.ATTRIBUTE_at_IS_LIST_, false);
		setEnable(CadseGCST.ATTRIBUTE_at_REQUIRE_, false);		
	}

}

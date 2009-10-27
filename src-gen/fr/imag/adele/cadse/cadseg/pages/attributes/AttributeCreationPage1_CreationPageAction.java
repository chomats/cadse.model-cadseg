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
package fr.imag.adele.cadse.cadseg.pages.attributes;

import java.text.MessageFormat;

import fr.imag.adele.cadse.cadseg.Messages;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.ui.IPageController;
import fr.imag.adele.cadse.core.ui.IPageObject;
import fr.imag.adele.cadse.core.ui.IValidateContributor;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * @generated
 */
public class AttributeCreationPage1_CreationPageAction extends AbstractActionPage implements IValidateContributor {

	/** The field. */
	Item	superAttribute;

	/** The short name field. */
	UIField	shortNameField;
	UIField	defaultValueField;
	UIField	isListField;
	UIField	requireAttributeField;

	public Item getSuperAttribute() {
		return superAttribute;
	}

	public AttributeCreationPage1_CreationPageAction(Link l, Item item, IItemNode node, ItemType type, LinkType lt) {
		// TODO
	}

	/**
	 * Instantiates a new atribute action page.
	 * 
	 * 
	 */
	public AttributeCreationPage1_CreationPageAction() {

	}

	@Override
	public void init(IPageObject pageObject) throws CadseException {
		super.init(pageObject);
		this.shortNameField = pageObject.getField(CadseGCST.ITEM_at_NAME);
		shortNameField.addValidateContributor(this);
		this.defaultValueField = pageObject.getField(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE);
		this.isListField = pageObject.getField(CadseGCST.ATTRIBUTE_at_IS_LIST);
		requireAttributeField = pageObject.getField(CadseGCST.ATTRIBUTE_at_REQUIRE);
		if (defaultValueField != null) {
			defaultValueField.addValidateContributor(this);
		}
	}

	@Override
	public void initAfterUI() {
		String shortName = getItem().getName();
		if (shortName != null && shortName.length() != 0) {
			computeSuperAttribute(shortName);

			if (this.superAttribute != null) {
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
		if (field == shortNameField) {
			String shortName = (String) shortNameField.getVisualValue();
			computeSuperAttribute(shortName);
			if (this.superAttribute != null) {
				if (this.superAttribute.getType() == getItem().getType()) {
					/*
					 * if
					 * (AttributeManager.isOverwritableAttribute(this.superAttribute)) {
					 * setDefaultValues(); disableField();
					 * pageObject.getPageController().setMessage(
					 * MessageFormat.format(Messages.information_overwrite_attribute,
					 * this.superAttribute .getPartParent().getShortName()),
					 * IPageController.INFORMATION); } else
					 */{
						pageObject.getPageController().setMessage(
								MessageFormat.format(Messages.error_overwrite_attribute_cannot_permitted,
										this.superAttribute.getPartParent().getName()), IPageController.ERROR);
					}

				} else {
					pageObject.getPageController().setMessage(
							MessageFormat.format(Messages.error_overwrite_attribute_bad_type, this.superAttribute
									.getPartParent().getName(), this.superAttribute.getType().getName(), getItem()
									.getType().getName()), IPageController.ERROR);
					return true;
				}
			} else {
				enableField();
			}
		}
//		if (field == defaultValueField) {
//			if (AttributeManager.isClassAttributeAttribute(getItem())) {
//				String df = (String) value;
//				if (df == null || df.length() == 0) {
//					pageObject.getPageController().setMessage("The default value must be set", IPageController.ERROR);
//					return true;
//				}
//			}
//
//		}
		return false;
	}

	protected void computeSuperAttribute(String shortName) {
		Item absItemType = getItem().getPartParent();
		Item superItemtype = ItemTypeManager.getSuperAbstractItemType(absItemType);
		if (superItemtype == null) {
			superAttribute = null;
			return;
		}
		this.superAttribute = ItemTypeManager.findAttribute(superItemtype, shortName);

	}

	protected void setDefaultValues() {
		AttributeManager.setIsListAttribute(getItem(), AttributeManager.isIsListAttribute(superAttribute));
		AttributeManager.setRequireAttribute(getItem(), AttributeManager.isRequireAttribute(superAttribute));

	}

	protected void enableField() {
		if (isListField != null) {
			isListField.setEnabled(true);
		}
		if (requireAttributeField != null) {
			requireAttributeField.setEnabled(true);
		}
	}

	protected void disableField() {
		if (isListField != null) {
			isListField.setEnabled(false);
		}
		if (requireAttributeField != null) {
			requireAttributeField.setEnabled(false);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.IValidateContributor#validSubValueAdded(fr.imag.adele.cadse.core.ui.UIField,
	 *      java.lang.Object)
	 */
	public boolean validSubValueAdded(UIField field, Object added) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.IValidateContributor#validSubValueRemoved(fr.imag.adele.cadse.core.ui.UIField,
	 *      java.lang.Object)
	 */
	public boolean validSubValueRemoved(UIField field, Object removed) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.IValidateContributor#validValue(fr.imag.adele.cadse.core.ui.UIField,
	 *      java.lang.Object)
	 */
	public boolean validValue(UIField field, Object value) {
		return validValueChanged(field, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.IValidateContributor#validValueDeleted(fr.imag.adele.cadse.core.ui.UIField,
	 *      java.lang.Object)
	 */
	public boolean validValueDeleted(UIField field, Object removed) {
		return false;
	}

}

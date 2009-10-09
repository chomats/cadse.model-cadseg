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

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.cadseg.Messages;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.IInteractionController;
import fr.imag.adele.cadse.core.ui.IPageController;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * @generated
 */
public class AttributeCreationPage1_CreationPage extends PageImpl {

	/**
	 * @generated
	 */
	static public class ClassAttributeMC extends StringToBooleanModelControler {

		/**
		 * @generated
		 */
		public ClassAttributeMC() {
			super();
		}

	}

	/**
	 * @generated
	 */
	public Item				parent;

	/**
	 * @generated
	 */
	public ItemType			it;

	/**
	 * @generated
	 */
	public LinkType			lt;

	/**
	 * @generated
	 */
	protected DTextUI		__short_name__;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldClassAttribute;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldIsList;

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldRequire;

	/**
	 * @generated
	 */
	protected DTextUI		fieldDefaultValue;

	/** The field. */
	Item					superAttribute;

	/** The short name field. */
	UIField					defaultValueField;
	UIField					isListField;
	UIField					metaAttributeField;
	UIField					requireAttributeField;

	public Item getSuperAttribute() {
		return superAttribute;
	}

	/**
	 * @generated
	 */
	protected AttributeCreationPage1_CreationPage(String id, String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public AttributeCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create an attribute", "Create an attribute", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.__short_name__ = createInternalNameField();
		this.fieldIsList = createFieldIsList();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldRequire = createFieldRequire();
		this.fieldClassAttribute = createFieldClassAttribute();
		setActionPage(new AttributeCreationPage1_CreationPageAction());
		addLast(this.__short_name__, this.fieldIsList, this.fieldDefaultValue, this.fieldRequire,
				this.fieldClassAttribute);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * @generated
	 */
	public DTextUI createInternalNameField() {
		return FieldsCore.createShortNameField();
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldClassAttribute() {
		ClassAttributeMC mc = new ClassAttributeMC();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_CLASS_ATTRIBUTE, "class attribute", EPosLabel.none, mc, null);
	}

	/**
	 * Creates the field is list.
	 * 
	 * @return the UI field
	 * 
	 * @change label is-list ==> is list
	 */
	public DCheckBoxUI createFieldIsList() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_IS_LIST, "is list", EPosLabel.none, mc, null);
	}

	/**
	 * Creates the field require.
	 * 
	 * @return the UI field
	 * @generated
	 */
	public DCheckBoxUI createFieldRequire() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(WorkspaceCST.ATTRIBUTE_at_REQUIRE, "mandatory", EPosLabel.none, mc, null);
	}

	/**
	 * Creates the field default value.
	 * 
	 * @return the UI field
	 * 
	 * @change add method to create mc and ic
	 */
	public DTextUI createFieldDefaultValue() {
		return new DTextUI(WorkspaceCST.ATTRIBUTE_at_DEFAULT_VALUE, "default value", EPosLabel.left,
				createMCDefaultValue(), createICDefaultValue(), 0, 1, "");
	}

	protected MC_AttributesItem createMCDefaultValue() {
		return new MC_AttributesItem();
	}

	protected IInteractionController createICDefaultValue() {
		return null;
	}

	@Override
	public void initAfterUI() {
		__short_name__.addValidateContributor(this);
		this.defaultValueField = getField(WorkspaceCST.ATTRIBUTE_at_DEFAULT_VALUE);
		this.isListField = getField(WorkspaceCST.ATTRIBUTE_at_IS_LIST);
		requireAttributeField = getField(WorkspaceCST.ATTRIBUTE_at_REQUIRE);
		metaAttributeField = getField(WorkspaceCST.ATTRIBUTE_at_CLASS_ATTRIBUTE);
		if (metaAttributeField != null) {
			metaAttributeField.addValidateContributor(this);
		}
		if (defaultValueField != null) {
			defaultValueField.addValidateContributor(this);
		}

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
		if (field == __short_name__) {
			String shortName = (String) __short_name__.getVisualValue();
			computeSuperAttribute(shortName);
			if (this.superAttribute != null) {
				if (this.superAttribute.getType() == getItem().getType()) {
					/*
					 * if
					 * (AttributeManager.isOverwritableAttribute(this.superAttribute)) {
					 * setDefaultValues(); disableField();
					 * getPageController().setMessage(
					 * MessageFormat.format(Messages.information_overwrite_attribute,
					 * this.superAttribute.getPartParent().getShortName()),
					 * IPageController.INFORMATION); } else
					 */{
						getPageController().setMessage(
								MessageFormat.format(Messages.error_overwrite_attribute_cannot_permitted,
										this.superAttribute.getPartParent().getName()), IPageController.ERROR);
					}

				} else {
					getPageController().setMessage(
							MessageFormat.format(Messages.error_overwrite_attribute_bad_type, this.superAttribute
									.getPartParent().getName(), this.superAttribute.getType().getName(), getItem()
									.getType().getName()), IPageController.ERROR);
					return true;
				}
			} else {
				enableField();
			}
		}
		if (field == defaultValueField) {
			if (AttributeManager.isClassAttributeAttribute(getItem())) {
				String df = (String) value;
				if (df == null || df.length() == 0) {
					getPageController().setMessage("The default value must be set", IPageController.ERROR);
					return true;
				}
			}

		}
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
		AttributeManager.setClassAttributeAttribute(getItem(), AttributeManager
				.isClassAttributeAttribute(superAttribute));
		AttributeManager.setRequireAttribute(getItem(), AttributeManager.isRequireAttribute(superAttribute));

	}

	protected void enableField() {
		if (isListField != null) {
			isListField.setEnabled(true);
		}
		if (metaAttributeField != null) {
			metaAttributeField.setEnabled(true);
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
		if (metaAttributeField != null) {
			metaAttributeField.setEnabled(false);
		}
	}

}

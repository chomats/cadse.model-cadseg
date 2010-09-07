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
package fr.imag.adele.cadse.cadseg.pages.attributes;

import java.text.MessageFormat;

import fede.workspace.model.manager.properties.impl.mc.MC_ShortNameItemProperty;
import fr.imag.adele.cadse.cadseg.Messages;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_StringToBoolean;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.RuningInteractionController;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class AttributeCreationPage1_CreationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item parent;

	/**
	 * @generated
	 */
	public ItemType it;

	/**
	 * @generated
	 */
	public LinkType lt;

	/**
	    @generated
	 */
	protected DTextUI fieldName;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldIsList;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldRequire;

	/**
	 * @generated
	 */
	protected DTextUI fieldDefaultValue;

	/** The field. */
	Item superAttribute;

	/** The short name field. */
	UIField defaultValueField;
	UIField isListField;
	UIField requireAttributeField;

	public Item getSuperAttribute() {
		return superAttribute;
	}

	/**
	 * @generated
	 */
	protected AttributeCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public AttributeCreationPage1_CreationPage(Item parent, ItemType it,
			LinkType lt) {
		super("creation-page1", "Create an attribute", "Create an attribute",
				"", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldName = createFieldName();
		this.fieldIsList = createFieldIsList();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldRequire = createFieldRequire();
		setActionPage(new AttributeCreationPage1_CreationPageAction());
		addLast(this.fieldName, this.fieldIsList, this.fieldDefaultValue,
				this.fieldRequire);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DTextUI createFieldName() {
		MC_ShortNameItemProperty mc = new MC_ShortNameItemProperty();
		return new DTextUI(CadseGCST.ITEM_at_NAME, "name", EPosLabel.left, mc,
				null, 1, "", false, false, false);
	}

	/**
	 * Creates the field is list.
	 * 
	 * @return the UI field
	 * 
	 * @change label is-list ==> is list
	 */
	public DCheckBoxUI createFieldIsList() {
		MC_StringToBoolean mc = new MC_StringToBoolean();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_IS_LIST, "is list",
				EPosLabel.none, mc, null);
	}

	/**
	 * Creates the field require.
	 * 
	 * @return the UI field
	 * @generated
	 */
	public DCheckBoxUI createFieldRequire() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_REQUIRE, "mandatory",
				EPosLabel.none, mc, null);
	}

	/**
	 * Creates the field default value.
	 * 
	 * @return the UI field
	 * 
	 * @change add method to create mc and ic
	 */
	public DTextUI createFieldDefaultValue() {
		return new DTextUI(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE,
				"default value", EPosLabel.left, createMCDefaultValue(),
				createICDefaultValue(), 0, 1, "");
	}

	protected MC_AttributesItem createMCDefaultValue() {
		return new MC_AttributesItem();
	}

	protected RuningInteractionController createICDefaultValue() {
		return null;
	}

	@Override
	public void initAfterUI() {
		__short_name__.addValidateContributor(this);
		this.defaultValueField = getField(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE);
		this.isListField = getField(CadseGCST.ATTRIBUTE_at_IS_LIST);
		requireAttributeField = getField(CadseGCST.ATTRIBUTE_at_REQUIRE);

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
						getPageController()
								.setMessage(
										MessageFormat
												.format(
														Messages.error_overwrite_attribute_cannot_permitted,
														this.superAttribute
																.getPartParent()
																.getName()),
										UIPlatform.ERROR);
					}

				} else {
					getPageController()
							.setMessage(
									MessageFormat
											.format(
													Messages.error_overwrite_attribute_bad_type,
													this.superAttribute
															.getPartParent()
															.getName(),
													this.superAttribute
															.getType()
															.getName(),
													getItem().getType()
															.getName()),
									UIPlatform.ERROR);
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
		//					getPageController().setMessage(
		//							"The default value must be set",
		//							IPageController.ERROR);
		//					return true;
		//				}
		//			}
		//
		//		}
		return false;
	}

	protected void computeSuperAttribute(String shortName) {
		Item absItemType = getItem().getPartParent();
		Item superItemtype = ItemTypeManager
				.getSuperAbstractItemType(absItemType);
		if (superItemtype == null) {
			superAttribute = null;
			return;
		}
		this.superAttribute = ItemTypeManager.findAttribute(superItemtype,
				shortName);

	}

	protected void setDefaultValues() {
		AttributeManager.setIsListAttribute(getItem(), AttributeManager
				.isIsListAttribute(superAttribute));
		AttributeManager.setRequireAttribute(getItem(), AttributeManager
				.isRequireAttribute(superAttribute));

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

}

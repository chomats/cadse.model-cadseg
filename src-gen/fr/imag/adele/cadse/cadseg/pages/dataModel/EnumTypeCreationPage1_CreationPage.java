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
package fr.imag.adele.cadse.cadseg.pages.dataModel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;

import fede.workspace.eclipse.java.fields.IC_JavaClassForBrowser_Combo;
import fede.workspace.eclipse.java.fields.MC_StringToJavaElement;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_DefaultForList;
import fede.workspace.model.manager.properties.impl.mc.MC_DefaultForList;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.util.Convert;

/**
 * @generated
 */
public class EnumTypeCreationPage1_CreationPage extends PageImpl {

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
	 * @generated
	 */
	protected DTextUI __short_name__;

	/**
	 * @generated
	 */
	protected DListUI fieldValues;

	/**
	 * @generated
	 */
	protected DTextUI fieldJavaClass;

	/**
	 * @generated
	 */
	protected EnumTypeCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public EnumTypeCreationPage1_CreationPage(Item parent, ItemType it,
			LinkType lt) {
		super("creation-page1", "Create EnumType", "Create EnumType", "",
				false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.__short_name__ = createInternalNameField();
		this.fieldValues = createFieldValues();
		this.fieldJavaClass = createFieldJavaClass();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldValues, this.fieldJavaClass);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
		this.fieldJavaClass.addListener(this);
		// this.fieldMustBeGenerated.addListener(this);
		this.fieldValues.addListener(this);

		this.fieldJavaClass.addValidateContributor(this);
		// this.fieldMustBeGenerated.addValidateContributor(this);
		this.fieldValues.addValidateContributor(this);
		// CreationAction action = new CreationAction(parent, type, lt) {
		// @Override
		// public void doFinish(Object monitor) throws Exception {
		// Item item = getItem();
		// if (item.getAttribute(EnumTypeManager.JAVA_CLASS) != null) {
		// item.setAttribute("generate", "false");
		// }
		// super.doFinish(monitor);
		// }
		// };
		//
		// return FieldsCore.createWizard(action,
		// FieldsCore.createPage("page1", "Create an enum type", "Create an enum
		// type",
		// 3,
		// FieldsCore.createShortNameField(),
		// FieldsCore.createCheckBox("generate", "generate java class"),
		// JavaFieldsCore.createJavaField(EnumTypeManager.JAVA_CLASS, "java
		// class", "",
		// "Select an enum.",IJavaElementSearchConstants.CONSIDER_ENUMS),
		// FieldsCore.createList_ListOfString(EnumTypeManager.VALUES_ATTRIBUTE,
		// "values", new ListOfStringModelController( 1,-1) {
		// @Override
		// protected boolean isEnable() {
		// Item item = (Item) getItem();
		// return item.getAttribute(EnumTypeManager.JAVA_CLASS) == null;
		// }
		// },
		// new IC_StringListForList("Select an enum.","Select an enum.",false) {
		// @Override
		// protected boolean isEnable() {
		// Item item = (Item) getItem();
		// return item.getAttribute(EnumTypeManager.JAVA_CLASS) == null;
		// }
		// })
		// )
		// );
	}

	@Override
	public void initAfterUI() {
		super.initAfterUI();
		this.fieldValues.setEnabled(true);
		EnumTypeManager.setMustBeGeneratedAttribute(getItem(), true);
		// this.fieldJavaClass.setEnabled(false);

	}

	@Override
	public void notifieValueChanged(UIField field, Object value) {
		//		if (field == this.fieldMustBeGenerated) {
		//			boolean booleanValue = Convert.toBoolean(value, false);
		//			this.fieldValues.setEnabled(booleanValue);
		//			this.fieldJavaClass.setEnabled(!booleanValue);
		//			if (!booleanValue) { // not generated
		//				IType javaClass = EnumTypeManager
		//						.getSelectedEnumQualifiedClass(getItem(), true);
		//				if (javaClass != null) {
		//					this.fieldValues
		//							.setVisualValue(getEnumTypeValues(javaClass));
		//				}
		//			}
		//		}
		if (field == this.fieldJavaClass) {
			IType javaClass = value instanceof IType ? (IType) value : null;
			if (javaClass != null) {
				this.fieldValues.setVisualValue(getEnumTypeValues(javaClass));
				EnumTypeManager.setMustBeGeneratedAttribute(getItem(), false);
				this.fieldValues.setEnabled(false);
			} else {
				this.fieldValues.setEnabled(true);
			}
		}
	}

	@Override
	public boolean validValue(UIField field, Object value) {
		//		if (field == this.fieldMustBeGenerated) {
		//			boolean booleanValue = Convert.toBoolean(value, false);
		//			if (!booleanValue) { // not generated
		//				IType javaClass = EnumTypeManager
		//						.getSelectedEnumQualifiedClass(getItem(), true);
		//				if (javaClass == null) {
		//					setMessageError("You must select a java enum type");
		//					return true;
		//				}
		//			}
		//		}
		if (field == this.fieldJavaClass) {
			boolean booleanValue = EnumTypeManager
					.isMustBeGeneratedAttribute(getItem());
			if (!booleanValue) { // not generated
				IType javaClass = value instanceof IType ? (IType) value : null;
				if (javaClass == null) {
					setMessageError("You must select a java enum type");
					return true;
				}
			}
		}
		return super.validValue(field, value);
	}

	/**
	 * Gets the enum type values.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param enumType
	 *            the enum type
	 * 
	 * @return the enum type values
	 */
	public static List<String> getEnumTypeValues(IType type) {
		List<String> values = new ArrayList<String>();
		try {
			IField[] fields = type.getFields();
			for (IField field : fields) {
				if (field.getElementName().equals("ENUM$VALUES")) {
					continue;
				}
				values.add(field.getElementName());
			}
			return values;
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values;
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
	public DListUI createFieldValues() {
		MC_DefaultForList mc = new MC_DefaultForList(0, -1);
		IC_DefaultForList ic = new IC_DefaultForList("Select a value.",
				"Select a value.", false);
		return new DListUI(CadseGCST.ENUM_TYPE_at_VALUES, "values",
				EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldJavaClass() {
		return new DTextUI(CadseGCST.ENUM_TYPE_at_JAVA_CLASS, "java-class",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

}

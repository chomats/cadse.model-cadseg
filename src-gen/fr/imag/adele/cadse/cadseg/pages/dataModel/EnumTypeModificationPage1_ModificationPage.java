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

import java.util.List;

import org.eclipse.jdt.core.IType;
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
public class EnumTypeModificationPage1_ModificationPage extends PageImpl {

	/**
	 * @generated
	 */
	public Item item;

	/**
	 * @generated
	 */
	protected DTextUI __short_name__;

	/**
	 * @generated
	 */
	protected DListUI	fieldValues;

	/**
	    @generated
	 */
	protected DBrowserUI	fieldJavaClass;

	/**
	 * @generated
	 */
	protected EnumTypeModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public EnumTypeModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "EnumType", "EnumType", "", false, 3);
		this.item = item;
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
		this.fieldValues.addListener(this);

		this.fieldJavaClass.addValidateContributor(this);
		this.fieldValues.addValidateContributor(this);

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
				this.fieldValues.setVisualValue(EnumTypeCreationPage1_CreationPage.getEnumTypeValues(javaClass));
				EnumTypeManager.setMustBeGeneratedAttribute(getItem(), false);
				this.fieldValues.setEnabled(false);
			} else {
				this.fieldValues.setEnabled(true);
				EnumTypeManager.setMustBeGeneratedAttribute(getItem(), true);
			}
		}
	}

	// /**
	// * Creates the property enum type.
	// *
	// * @return the pages
	// */
	// public static Pages createPropertyEnumType(Item item) {
	// AbstractActionPage action = new ModificationAction(item);
	//
	// DBrowserUI javaField;
	// return FieldsCore.createWizard(action,
	// FieldsCore.createPage("page1", "An enum type", "An enum type", 3,
	// FieldsCore.createShortNameField(),
	// javaField = JavaFieldsCore.createJavaField(EnumTypeManager.JAVA_CLASS,
	// "java
	// class", "", "Select an
	// enum.",IJavaElementSearchConstants.CONSIDER_ENUMS),
	// FieldsCore.createCheckBox("generate", "generate java class", new
	// IC_GenerateCasePage(javaField)),
	// FieldsCore.createList_ListOfString(EnumTypeManager.VALUES_ATTRIBUTE,
	// "values",null, null, false,1,-1)
	// )
	// );
	// }

	@Override
	public void initAfterUI() {
		super.initAfterUI();

		List<String> values = EnumTypeManager.getEnumTypeValues(getItem());
		this.fieldValues.setVisualValue(values);
		boolean g = EnumTypeManager.isMustBeGeneratedAttribute(getItem());
		this.fieldValues.setEnabled(g);
		// this.fieldJavaClass.setEnabled(!g);
	}

	@Override
	public boolean validValue(UIField field, Object value) {
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
	 * @generated
	 */
	public DTextUI createInternalNameField() {
		return FieldsCore.createUniqueNameField();
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
	    @generated
	 */
	public DBrowserUI createFieldJavaClass() {
		IC_JavaClassForBrowser_Combo ic = new IC_JavaClassForBrowser_Combo(
				"select an enum class", "select an enum class",
				IJavaElementSearchConstants.CONSIDER_ENUMS, "");
		MC_StringToJavaElement mc = new MC_StringToJavaElement();
		return new DBrowserUI(CadseGCST.ENUM_TYPE_at_JAVA_CLASS,
				"enum class", EPosLabel.left, mc, ic);
	}

}

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

import java.util.List;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

import fede.workspace.model.manager.properties.IFieldContenProposalProvider;
import fede.workspace.model.manager.properties.Proposal;
import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.impl.ui.ic.IC_Abstract;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.EventAdapter;
import fr.imag.adele.cadse.core.ui.IEventListener;
import fr.imag.adele.cadse.core.ui.RuningInteractionController;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * @generated
 */
public class EnumCreationPage1_CreationPage extends
		AttributeCreationPage1_CreationPage {
	/**
	 * @generated
	 */
	protected DBrowserUI fieldEnumType;
	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldMustBeInitialized;

	
	/**
	 * The Class IsListMinAndMaxCheckFieldController.
	 */
	public static final class IsListMinAndMaxCheckFieldController extends
			EventAdapter implements IEventListener {

		/** The min field. */
		private UIField minField;

		/** The max field. */
		private UIField maxField;

		/** The islist field. */
		private UIFieldImpl islistField;

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.EventAdapter#init(fr.imag.adele.cadse.core.ui.UIField)
		 */
		@Override
		public void init(UIField field) {
			if (field == islistField) {
				setEnableMinMax("true".equals(islistField.getItem()
						.getAttribute(CadseGCST.ATTRIBUTE_at_IS_LIST)));
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.EventAdapter#notifieValueChanged(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		@Override
		public void notifieValueChanged(UIField field, Object value) {

			if (field == islistField) {
				setEnableMinMax(((Boolean) value).booleanValue());
			}
		}

		/**
		 * Sets the enable min max.
		 * 
		 * @param islist
		 *            the new enable min max
		 */
		private void setEnableMinMax(boolean islist) {
			minField.setEnabled(islist);
			maxField.setEnabled(islist);
		}

		/**
		 * Instantiates a new checks if is list min and max check field
		 * controller.
		 * 
		 * @param islistField
		 *            the islist field
		 * @param minField
		 *            the min field
		 * @param maxField
		 *            the max field
		 */
		public IsListMinAndMaxCheckFieldController(UIFieldImpl islistField,
				UIField minField, UIField maxField) {
			super();
			this.islistField = islistField;
			this.minField = minField;
			this.maxField = maxField;
			islistField.addListener(this);
		}
	}

	/**
	 * @generated
	 */
	protected EnumCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public EnumCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create Enum", "Create Enum", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.fieldName = createFieldName();
		this.fieldEnumType = createFieldEnumType();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldMustBeInitialized = createFieldMustBeInitialized();
		this.fieldIsList = createFieldIsList();
		setActionPage(new EnumCreationPage1_CreationPageAction());
		addLast(this.fieldName, this.fieldEnumType, this.fieldDefaultValue,
				this.fieldMustBeInitialized, this.fieldIsList);

		registerListener();
	}

	//	/**
	//	 * @not generated
	//	 */
	//	public EnumCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt, int oldversion) {
	//		super("creation-page1", "Create an enum attribute", "Create an enum attribute", "", false, 3);
	//		this.parent = parent;
	//		this.it = it;
	//		this.lt = lt;
	//		this.__short_name__ = createInternalNameField();
	//		setActionPage(new EnumCreationPage1_CreationPageAction());
	//
	//		UIFieldImpl islist;
	//		UIField min;
	//		UIField max;
	//		DBrowserUI et;
	//
	//		MaxModelController maxVC = new MaxModelController();
	//		MinModelController minVC = new MinModelController();
	//		DefaultValueIC ic = new DefaultValueIC();
	//		addLast(this.__short_name__, et = createEnumTypeField2(), createFieldRequire(),
	//		// createFieldClassAttribute(),
	//				FieldsCore.createTextField(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE, "default value", 1, null, ic,
	//						new DefaultEnumMC()), islist = createFieldIsList(), min = FieldsCore.createIntField(
	//						CadseGCST.ATTRIBUTE_at_MIN, "min", minVC, minVC), max = FieldsCore.createIntField(
	//						CadseGCST.ATTRIBUTE_at_MAX, "max", maxVC, maxVC));
	//
	//		registerListener();
	//
	//		new IsListMinAndMaxCheckFieldController(islist, min, max);
	//		et.addListener(ic);
	//	}

	protected void registerListener() {
		// add init and register
	}

	

	/**
	 * @not generated
	 */
	public DTextUI createFieldDefaultValue() {
		return new DTextUI(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE,
				"default-value", EPosLabel.left, new DefaultEnumMC(),
				new DefaultValueIC(), 1, "", false, false, false);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldMustBeInitialized() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED,
				"show attribute in creation wizard", EPosLabel.none, mc, null);
	}

	/**
	 * Creates the enum type field.
	 * 
	 * @return the d browser ui
	 */
	public DBrowserUI createEnumTypeField2() {
		return new DBrowserUI(CadseGCST.ENUM_lt_ENUM_TYPE.getName(),
				"enum type", EPosLabel.left, new LinkModelController(true,
						null, CadseGCST.ENUM_lt_ENUM_TYPE),
				new IC_LinkForBrowser_Combo_List("Select a type enum",
						"Select a type enum", CadseGCST.ENUM_lt_ENUM_TYPE), 0);
	}
}

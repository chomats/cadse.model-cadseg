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

import java.util.List;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ic.IC_PartLinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.IFieldContenProposalProvider;
import fede.workspace.model.manager.properties.Proposal;
import fede.workspace.model.manager.properties.impl.ic.IC_Abstract;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.mc.MaxModelController;
import fede.workspace.model.manager.properties.impl.mc.MinModelController;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.EventAdapter;
import fr.imag.adele.cadse.core.ui.IEventListener;
import fr.imag.adele.cadse.core.ui.IInteractionController;
import fr.imag.adele.cadse.core.ui.UIField;

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
	 * The Class DefaultValueIC.
	 */
	public static final class DefaultValueIC extends IC_Abstract implements
			IInteractionController, IEventListener,
			IFieldContenProposalProvider, IContentProposalProvider {

		/** The values. */
		List<String> values = null;

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieSubValueAdded(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public void notifieSubValueAdded(UIField field, Object added) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieSubValueRemoved(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public void notifieSubValueRemoved(UIField field, Object removed) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieValueChanged(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public void notifieValueChanged(UIField field, Object value) {
			if (field != null
					&& value instanceof Link
					&& CadseGCST.ENUM_lt_ENUM_TYPE.getName().equals(
							field.getName())) {
				Item enumType = ((Link) value).getResolvedDestination();
				if (enumType != null) {
					// force to recomptue values
					values = null;
					getValues();
					if (values.size() != 0) {
						String actuelValues = (String) getUIField()
								.getVisualValue();
						if (!values.contains(actuelValues)) {
							getUIField().setVisualValue(values.get(0));
							getUIField().setEnabled(true);
							getModelController().notifieValueChanged(
									getUIField(), values.get(0));
						}
					}
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_Abstract#initAfterUI()
		 */
		@Override
		public void initAfterUI() {
			getUIField().setEnabled(isEnable());
		}

		/**
		 * Checks if is enable.
		 * 
		 * @return true, if is enable
		 */
		private boolean isEnable() {
			return getValues().size() != 0;
		}

		/**
		 * Gets the values.
		 * 
		 * @return the values
		 */
		private List<String> getValues() {
			if (values == null) {
				Item enumItem = getItem();
				Item enumType = EnumManager.getEnumType(enumItem);
				values = EnumTypeManager.getEnumTypeValues(enumType);
			}
			return values;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieValueDeleted(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public void notifieValueDeleted(UIField field, Object oldvalue) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.IFieldContenProposalProvider#getAutoActivationCharacters()
		 */
		public char[] getAutoActivationCharacters() {
			return new char[0];
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.IFieldContenProposalProvider#getCommandId()
		 */
		public String getCommandId() {
			return ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.IFieldContenProposalProvider#getContentProposalProvider()
		 */
		public IContentProposalProvider getContentProposalProvider() {
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.IFieldContenProposalProvider#getProposalAcceptanceStyle()
		 */
		public int getProposalAcceptanceStyle() {
			return ContentProposalAdapter.PROPOSAL_REPLACE;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.IFieldContenProposalProvider#setControlContents(java.lang.String)
		 */
		public Object setControlContents(String newValue) {
			return null;
		}

		public Object getValueFromProposal(Proposal proposal) {
			return proposal.getContent();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.fieldassist.IContentProposalProvider#getProposals(java.lang.String,
		 *      int)
		 */
		public IContentProposal[] getProposals(String contents, int position) {
			List<String> values = getValues();
			IContentProposal[] ret = new IContentProposal[values.size()];
			for (int i = 0; i < ret.length; i++) {
				String v = values.get(i);
				ret[i] = new Proposal(v, v, "", v.length());
			}
			return ret;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#init(fr.imag.adele.cadse.core.ui.UIField)
		 */
		public void init(UIField field) {
		}

		public ItemType getType() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	/**
	 * The Class DefaultEnumMC.
	 */
	public static final class DefaultEnumMC extends MC_AttributesItem {

		/**
		 * Gets the values.
		 * 
		 * @return the values
		 */
		private List<String> getValues() {
			Item enumItem = getItem();
			Item enumType = EnumManager.getEnumType(enumItem);
			return EnumTypeManager.getEnumTypeValues(enumType);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.AbstractModelController#validValueChanged(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		@Override
		public boolean validValueChanged(UIField field, Object value) {
			List<String> values = getValues();
			if (value == null) {
				setMessageError("Enter a default value");
				return true;
			}
			if (!values.contains(value)) {
				setMessageError("Enter a valid default value");
				return true;
			}
			return false;
		}
	}

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
		this.__short_name__ = createInternalNameField();
		this.fieldEnumType = createFieldEnumType();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldMustBeInitialized = createFieldMustBeInitialized();
		this.fieldIsList = createFieldIsList();
		setActionPage(new EnumCreationPage1_CreationPageAction());
		addLast(this.__short_name__, this.fieldEnumType,
				this.fieldDefaultValue, this.fieldMustBeInitialized,
				this.fieldIsList);

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
	public DBrowserUI createFieldEnumType() {
		return new DBrowserUI(CadseGCST.ENUM_lt_ENUM_TYPE.getName(),
				"enum type", EPosLabel.left, new LinkModelController(true,
						null, CadseGCST.ENUM_lt_ENUM_TYPE),
				new IC_LinkForBrowser_Combo_List("Select a type enum",
						"Select a type enum", CadseGCST.ENUM_lt_ENUM_TYPE), 0);
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

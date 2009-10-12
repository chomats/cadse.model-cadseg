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

import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.EventAdapter;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.Pages;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.util.Convert;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;
import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;

/**
 * @generated
 */
public class CreationDialogModificationPage1_ModificationPage extends PageImpl {

	/**
	 * The Class CreationDialogMC.
	 */
	public final class CreationDialogMC extends EventAdapter {

		/** The automatic short name f. */
		private UIFieldImpl automaticShortNameF;

		/** The extends dialog f. */
		private UIFieldImpl extendsDialogF;

		/** The generate automatic short name f. */
		private UIFieldImpl generateAutomaticShortNameF;

		/**
		 * Instantiates a new creation dialog mc.
		 * 
		 * @param defaultShortNameF
		 *            the default short name f
		 * @param automaticShortNameF
		 *            the automatic short name f
		 * @param extendsDialogF
		 *            the extends dialog f
		 * @param generateAutomaticShortNameF
		 *            the generate automatic short name f
		 */
		public CreationDialogMC(UIFieldImpl automaticShortNameF,
				UIFieldImpl extendsDialogF,
				UIFieldImpl generateAutomaticShortNameF) {
			this.automaticShortNameF = automaticShortNameF;
			this.extendsDialogF = extendsDialogF;
			this.generateAutomaticShortNameF = generateAutomaticShortNameF;

			this.automaticShortNameF.addListener(this);
			this.extendsDialogF.addListener(this);
			this.generateAutomaticShortNameF.addListener(this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fr.imag.adele.cadse.core.ui.EventAdapter#notifieValueChanged(fr.imag
		 * .adele.cadse.core.ui.UIField, java.lang.Object)
		 */
		@Override
		public void notifieValueChanged(UIField field, Object value) {
			Item item = field.getItem();
			if (field == automaticShortNameF) {
				if (Convert.toBoolean(value, false)) {
					enableEXTENDS_DIALOG_CONTROLLER_ATTRIBUTE();
					enableGenerateField();
				} else {
					disableGenerateField();
				}
			} else if (field == extendsDialogF) {
				if (Convert
						.toBoolean(
								item
										.getAttribute(CadseGCST.CREATION_DIALOG_at_AUTOMATIC_SHORT_NAME_),
								false)
						&& !Convert.toBoolean(value, false)) {
					disableAUTOMATIC_SHORT_NAME_ATTRIBUTE();
					disableGenerateField();
				}
			} else if (field == generateAutomaticShortNameF) {
				if (!(value == null || ((String) value).length() == 0)) {
					if (!((String) value).contains("${"))
						return;
					enableAUTOMATIC_SHORT_NAME_ATTRIBUTE();
					enableEXTENDS_DIALOG_CONTROLLER_ATTRIBUTE();
				}
			}
		}

		/**
		 * Disable automati c_ shor t_ nam e_ attribute.
		 */
		private void disableAUTOMATIC_SHORT_NAME_ATTRIBUTE() {
			automaticShortNameF.setVisualValue(false);
			try {
				automaticShortNameF.getItem().setAttribute(
						CadseGCST.CREATION_DIALOG_at_AUTOMATIC_SHORT_NAME_,
						false);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * Enable extend s_ dialo g_ controlle r_ attribute.
		 */
		private void enableEXTENDS_DIALOG_CONTROLLER_ATTRIBUTE() {
			extendsDialogF.setVisualValue(true);
			try {
				extendsDialogF
						.getItem()
						.setAttribute(
								CadseGCST.CREATION_DIALOG_at_EXTENDS_DIALOG_CONTROLLER_,
								true);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * Enable automati c_ shor t_ nam e_ attribute.
		 */
		private void enableAUTOMATIC_SHORT_NAME_ATTRIBUTE() {
			automaticShortNameF.setVisualValue(true);
			try {
				automaticShortNameF.getItem().setAttribute(
						CadseGCST.CREATION_DIALOG_at_AUTOMATIC_SHORT_NAME_,
						true);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * Enable generate field.
		 */
		private void enableGenerateField() {
			generateAutomaticShortNameF.setEnabled(true);
		}

		/**
		 * Disable generate field.
		 */
		private void disableGenerateField() {
			generateAutomaticShortNameF.setEnabled(false);
			generateAutomaticShortNameF.setVisualValue("");
			try {
				generateAutomaticShortNameF
						.getItem()
						.setAttribute(
								CadseGCST.CREATION_DIALOG_at_GENERATE_AUTOMATIC_SHORT_NAME_,
								null);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

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
	protected DListUI fieldPages;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldAutomaticShortName;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldExtendsDialogController;

	/**
	 * @generated
	 */
	protected DTextUI fieldGenerateAutomaticShortName;

	/**
	 * @generated
	 */
	protected CreationDialogModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @not generated
	 */
	public CreationDialogModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "Creation dialog", "Creation dialog", "",
				false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldPages = createFieldPages();
		this.fieldAutomaticShortName = createFieldAutomaticShortName();
		this.fieldExtendsDialogController = createFieldExtendsDialogController();
		this.fieldGenerateAutomaticShortName = createFieldGenerateAutomaticShortName();

		AbstractActionPage action = new ModificationAction(item);
		setActionPage(action);

		DTextUI g;
		DCheckBoxUI e;

		//d = FieldsCore.createTextField(CadseGCST.CREATION_DIALOG_at_DEFAULT_SHORT_NAME, "default short name");
		this.fieldAutomaticShortName = FieldsCore.createCheckBox(
				CadseGCST.CREATION_DIALOG_at_AUTOMATIC_SHORT_NAME,
				"automatic short name");
		this.fieldExtendsDialogController = FieldsCore.createCheckBox(
				CadseGCST.CREATION_DIALOG_at_EXTENDS_DIALOG_CONTROLLER,
				"extends dialog controller");

		this.fieldGenerateAutomaticShortName = FieldsCore.createTextField(
				CadseGCST.CREATION_DIALOG_at_GENERATE_AUTOMATIC_SHORT_NAME,
				"generate automatic short name", 1, "",
				new IC_ItemTypeTemplateForText() {
					@Override
					protected Item getItemFromContext() {
						Item itemtype = getContext().getPartParent();
						return itemtype;
					}
				}, null);

		addLast(this.fieldGenerateAutomaticShortName,
				this.fieldAutomaticShortName, this.fieldExtendsDialogController);

		//		Pages ret = FieldsCore.createWizard(action, FieldsCore.createPage("page1", "Creation dialog",
		//				"Creation dialog", 3, , ,
		//				, ));
		new CreationDialogMC(this.fieldAutomaticShortName,
				fieldExtendsDialogController, fieldGenerateAutomaticShortName);
		registerListener();
	}

	protected void registerListener() {
		// add init and register
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
	public DListUI createFieldPages() {
		LinkModelController mc = new LinkModelController(true, null,
				CadseGCST.CREATION_DIALOG_lt_PAGES);
		IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
				"Select a value.", "Select a value.",
				CadseGCST.CREATION_DIALOG_lt_PAGES);
		return new DListUI(CadseGCST.CREATION_DIALOG_lt_PAGES.getName(),
				"pages", EPosLabel.top, mc, ic, true, false, false, false);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldAutomaticShortName() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(
				CadseGCST.CREATION_DIALOG_at_AUTOMATIC_SHORT_NAME,
				"automatic short name", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldExtendsDialogController() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(
				CadseGCST.CREATION_DIALOG_at_EXTENDS_DIALOG_CONTROLLER,
				"extends dialog controller", EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldGenerateAutomaticShortName() {
		return new DTextUI(
				CadseGCST.CREATION_DIALOG_at_GENERATE_AUTOMATIC_SHORT_NAME,
				"generate automatic short name", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

}

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

import java.util.Arrays;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ITreeContentProvider;

import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.Proposal;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.ic.ItemTreeContentProvider;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.mc.MaxModelController;
import fede.workspace.model.manager.properties.impl.mc.MinModelController;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.cadseg.ItemShortNameComparator;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.DataModelManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemFilter;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * @generated
 */
public class LinkCreationPage1_CreationPage extends
		AttributeCreationPage1_CreationPage {

	/**
	 * The Class IC_DestinationLinkForBrowser_Combo.
	 */
	public static final class IC_DestinationLinkForBrowser_Combo extends
			IC_LinkForBrowser_Combo_List {

		/**
		 * Instantiates a new i c_ destination link for browser_ combo.
		 * 
		 * @param title
		 *            the title
		 * @param message
		 *            the message
		 */
		public IC_DestinationLinkForBrowser_Combo(String title, String message) {
			super(title, message, CadseGCST.LINK_lt_DESTINATION);
		}

		// {context <-[parent-part]- -[item-types] ->} - {context} -
		// {context<-[super-type]-*}
		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#getValues()
		 */
		@Override
		public Object[] getValues() {
			// TODO Heritage de type
			AttributeCreationPage1_CreationPageAction action = (AttributeCreationPage1_CreationPageAction) getUIField()
					.getPage().getActionPage();

			Item superAttribute = action == null ? null : action
					.getSuperAttribute();
			final Item superDestinationType = superAttribute == null ? null
					: LinkManager.getDestination(superAttribute);

			Item theAttribute = getItem();
			final Item theItemType = theAttribute.getPartParent();
			Item cadsedef = theItemType
					.getPartParent(CadseGCST.CADSE_DEFINITION);

			return ItemTypeManager.getAllAllItemType(cadsedef,
					new ItemFilter() {
						public boolean accept(Item item) {
							if (item == theItemType) {
								return false;
							}
							return (superDestinationType == null || ItemTypeManager
									.isSuperTypeOf(superDestinationType, item));
						}

						public boolean stop() {
							return false;
						}
					});
		}

		@Override
		protected Proposal createProposal(Item item, String contents,
				int position, Object[] items) {
			if (contents != null && !item.getName().startsWith(contents)) {
				return null;
			}
			String content = item.getName();

			Item dm = item.getPartParent();

			Item cadse = ItemTypeManager.getCadseDefinition(item);

			String packageString = DataModelManager.getQualifiedDM(dm);
			String cadseString = cadse.getName();

			String label = content;
			String description = "Item Type " + item.getName() + " in package "
					+ packageString + " in cadse " + cadseString;
			return new Proposal(content, label, description, 0, item);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#getTreeContentProvider()
		 */
		@Override
		protected ITreeContentProvider getTreeContentProvider() {
			return new ItemTreeContentProvider(new ItemShortNameComparator(),
					CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL,
					CadseGCST.DATA_MODEL_lt_TYPES,
					CadseGCST.DATA_MODEL_lt_CATEGORIES);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#validate(java.lang.Object[])
		 */
		@Override
		public IStatus validate(Object[] selection) {
			if (selection != null && selection.length == 1) {
				Object sel = selection[0];
				if (sel instanceof Item) {
					if (((Item) sel).getType() == CadseGCST.ITEM_TYPE) {
						AttributeCreationPage1_CreationPageAction action = (AttributeCreationPage1_CreationPageAction) getUIField()
								.getPage().getActionPage();

						Item superAttribute = action == null ? null : action
								.getSuperAttribute();
						final Item superDestinationType = superAttribute == null ? null
								: LinkManager.getDestination(superAttribute);

						if (superDestinationType != null) {
							if (ItemTypeManager.isSuperTypeOf(
									superDestinationType, (Item) sel)
									|| sel == superDestinationType) {
								return Status.OK_STATUS;
							} else {
								return new Status(Status.ERROR,
										WSPlugin.PLUGIN_ID,
										"you must select a sub type of "
												+ superDestinationType
														.getName());

							}
						}

						return Status.OK_STATUS;
					}
				}
			}
			return new Status(Status.ERROR, WSPlugin.PLUGIN_ID,
					"select an item type");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List#getInputValues()
		 */
		@Override
		protected Object getInputValues() {
			Item theAttribute = getItem();
			Item theItemType = theAttribute.getPartParent();
			Item cadsedef = theItemType
					.getPartParent(CadseGCST.CADSE_DEFINITION);
			Item[] ret = CadseDefinitionManager
					.getDependenciesCadsesAndMe(cadsedef);
			Arrays.sort(ret, new ItemShortNameComparator());
			return ret;
		}

	}

	/**
	 * @generated
	 */
	protected DCheckBoxUI	fieldMustBeInitialized;

	/**
	 * @generated
	 */
	protected DBrowserUI	fieldDestination;

	/**
	 * @generated
	 */
	protected DBrowserUI fieldInverseLink;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldAnnotation;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldAggregation;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldComposition;

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldPart;

	/**
	 * @generated
	 */
	protected DTextUI fieldSelection;

	private DTextUI fieldMax;

	private DTextUI fieldMin;

	/**
	 * @generated
	 */
	public LinkCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * TODO inverse min and max
	 */
	public LinkCreationPage1_CreationPage(Item parent, ItemType it, LinkType lt) {
		super("creation-page1", "Create link definition",
				"Create link definition", "", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.__short_name__ = createInternalNameField();
		this.fieldDestination = createFieldDestination();
		this.fieldInverseLink = createFieldInverseLink();
		this.fieldAnnotation = createFieldAnnotation();
		this.fieldAggregation = createFieldAggregation();
		this.fieldComposition = createFieldComposition();
		this.fieldRequire = createFieldRequire();
		this.fieldPart = createFieldPart();
		this.fieldMax = createFieldMax();
		this.fieldMin = createFieldMin();
		this.fieldMustBeInitialized = createFieldMustBeInitialized();

		setActionPage(new AttributeCreationPage1_CreationPageAction());
		addLast(this.__short_name__, this.fieldDestination, this.fieldInverseLink, this.fieldMustBeInitialized,
				this.fieldAggregation, this.fieldPart, this.fieldRequire, this.fieldAnnotation, this.fieldComposition,
				this.fieldMin, this.fieldMax);

		registerListener();
	}

	@Override
	protected void registerListener() {
		// add init and register
	}

	/**
	 * FieldsCore.createLinkDependencyField( //
	 * CadseGCST.LINK_lt_DESTINATION, "destination",EPosLabel.top, // new
	 * IC_DestinationLinkForBrowser_Combo( // "Select a destination", // "Select
	 * a destination"),true, "You must set the destination" // ),
	 */
	public DBrowserUI createFieldDestination() {
		IC_LinkForBrowser_Combo_List ic = new IC_DestinationLinkForBrowser_Combo(
				"Select a destination.", "Select a destination.");
		LinkModelController mc = new LinkModelController(true,
				"You must set the destination", CadseGCST.LINK_lt_DESTINATION);
		return new DBrowserUI(CadseGCST.LINK_lt_DESTINATION.getName(),
				"destination", EPosLabel.left, mc, ic);
	}

	/**
	 * IC_LinkForBrowser_Combo_List inverseLinkAction = new
	 * IC_InverseLink("Select an inverse link", "Select an inverse link",
	 * CadseGCST.LINK_lt_INVERSE_LINK); //
	 * FieldsCore.createLinkDependencyField( //
	 * CadseGCST.LINK_lt_INVERSE_LINK, "inverse link",EPosLabel.top,
	 * inverseLinkAction, false, null // ),
	 */
	public DBrowserUI createFieldInverseLink() {
		IC_LinkForBrowser_Combo_List ic = new IC_InverseLink(
				"Select an inverse link", "Select an inverse link",
				CadseGCST.LINK_lt_INVERSE_LINK);
		// IC_LinkForBrowser_Combo_List ic = new IC_LinkForBrowser_Combo_List(
		// "Select a
		// value.", "Select a value.",CadseGCST.LINK_lt_INVERSE_LINK);
		LinkModelController mc = new LinkModelController(false, null,
				CadseGCST.LINK_lt_INVERSE_LINK);
		return new DBrowserUI(CadseGCST.LINK_lt_INVERSE_LINK.getName(),
				"inverse link", EPosLabel.left, mc, ic);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldAnnotation() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.LINK_at_ANNOTATION, "annotation",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldAggregation() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.LINK_at_AGGREGATION, "aggregation",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldComposition() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.LINK_at_COMPOSITION, "composition",
				EPosLabel.none, mc, null);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldPart() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.LINK_at_PART, "part", EPosLabel.none,
				mc, null);
	}

	/**
	 * 
	 */
	public DTextUI createFieldMax() {
		MaxModelController maxVC = new MaxModelController(true);
		return FieldsCore.createIntField(CadseGCST.LINK_at_MAX, "max", maxVC,
				maxVC);
	}

	/**
	 * @generated
	 */
	public DTextUI createFieldSelection() {
		return new DTextUI(CadseGCST.LINK_at_SELECTION, "selection",
				EPosLabel.left, new MC_AttributesItem(), null, 1, "", false,
				false, false);
	}

	private DTextUI createFieldMin() {
		MinModelController minVC = new MinModelController(true);
		return FieldsCore.createIntField(CadseGCST.LINK_at_MIN, "min", minVC,
				minVC);
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldMustBeInitialized() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED, "show attribute in creation wizard",
				EPosLabel.none, mc, null);
	}
}

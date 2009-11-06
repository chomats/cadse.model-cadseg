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
package fr.imag.adele.cadse.cadseg.preferences;

import java.util.ArrayList;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemShortNameComparator;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.internal.ui.PagesImpl;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.AbstractModelController;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIValidator;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_TreeModel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DGridUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DSashFormUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTreeModelUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.FieldsPreferencePage;
import fede.workspace.tool.view.node.AbstractCadseViewNode;
import fede.workspace.tool.view.node.FilteredItemNode;
import fede.workspace.tool.view.node.FilteredItemNodeModel;
import fede.workspace.tool.view.node.FilteredItemNode.Category;
import fr.imag.adele.cadse.core.CadseGCST;

public class TeamWorkPreferencePage extends FieldsPreferencePage implements IWorkbenchPreferencePage {

	private IPage		_page;

	protected DGridUI		fieldsCadse;

	protected DSashFormUI	fieldsShash;

	protected DTreeModelUI	fieldExtends;

	protected DTextUI		fieldDescription;

	protected DTextUI		fieldItemRepoURL;

	protected DTextUI		fieldItemRepoLogin;

	protected DTextUI		fieldItemRepoPassword;

	protected DTextUI		fieldDefaultContentRepoURL;

	public TeamWorkPreferencePage() {
		this._page = _swtPlatform.createPageDescription("Cadse Repository Definition", "Cadse Repository Definition");
		this.fieldExtends = createFieldExtends();
		this.fieldDescription = createFieldDescription();
		this.fieldItemRepoURL = createFieldItemRepoURL();
		this.fieldItemRepoLogin = createFieldItemRepoLogin();
		this.fieldItemRepoPassword = createFieldItemRepoPassword();
		this.fieldDefaultContentRepoURL = createFieldDefaultContentRepoURL();

		MyMC_AttributesItem defaultMc = new MyMC_AttributesItem();

		
		DGridUI comp1 = _swtPlatform.createDGridUI(_page, "#tree", "", EPosLabel.none, defaultMc, null, fieldExtends);
		fieldsShash.setWeight(60);

		DGridUI comp = _swtPlatform.createDGridUI(_page, "#edit", "", EPosLabel.none, defaultMc, null,
				this.fieldDescription, this.fieldItemRepoURL, this.fieldItemRepoLogin,
				this.fieldItemRepoPassword, this.fieldDefaultContentRepoURL);

		this.fieldsShash = _swtPlatform.createDSashFormUI(_page, "#sash", "", EPosLabel.none, defaultMc, null,
				comp1, comp);
		fieldsShash.setHorizontal(false);

		_page.addLast(fieldsShash.getAttributeDefinition());
		registerListener();

		setController(createPages());
	}

	public DTextUI createFieldDefaultContentRepoURL() {
		return _swtPlatform.createTextUI(_page, CadseGCST.CADSE_at_DEFAULT_CONTENT_REPO_URL_, "Default Content Repository URL",
				EPosLabel.left, new MyMC_AttributesItem(), null, 1, false, false, false, false, false);
	}

	public DTextUI createFieldItemRepoPassword() {
		return _swtPlatform.createTextUI(_page, CadseGCST.CADSE_at_ITEM_REPO_PASSWD_, "Item Repository Password", EPosLabel.left,
				new MyMC_AttributesItem(), null, 1, false, false, false, false, false);
	}

	public DTextUI createFieldItemRepoLogin() {
		return _swtPlatform.createTextUI(_page, CadseGCST.CADSE_at_ITEM_REPO_LOGIN_, "Item Repository Login", EPosLabel.left,
				new MyMC_AttributesItem(), null, 1, false, false, false, false, false);
	}

	public DTextUI createFieldItemRepoURL() {
		return _swtPlatform.createTextUI(_page, CadseGCST.CADSE_at_ITEM_REPO_URL_, "Item Repository URL", EPosLabel.left,
				new MyMC_AttributesItem(), null, 1, false, false, false, false, false);
	}

	public PagesImpl createPages() {
		
		return _swtPlatform.createPages(newAction(), _page, new AbstractUIRunningValidator());
	}

	private IActionPage newAction() {
		return new MyActionPage();
	}

	Item		selectedItem	= null;
	Category	categoryExtendsTo;
	Category	categoryExtendedBy;

	public class MyMC_AttributesItem extends MC_AttributesItem {

		@Override
		public Item getItem() {
			return selectedItem;
		}

		@Override
		public Object getValue() {
			if (getItem() == null) {
				return "";
			}
			Object _ret = super.getValue();
			if (_ret == null) {
				return "";
			}
			return _ret;
		}
	}

	public class ReadOnlyMC_AttributesItem extends MC_AttributesItem {

		@Override
		public Item getItem() {
			return selectedItem;
		}

		@Override
		public Object getValue() {
			if (getItem() == null) {
				return "";
			}
			Object _ret = super.getValue();
			if (_ret == null) {
				return "";
			}
			return _ret;
		}

		@Override
		public void notifieValueChanged(UIField field, Object value) {
			// read only value
		}
	}

	public class MyActionPage extends AbstractActionPage {

		@Override
		public void doFinish(Object monitor) throws Exception {
		}
	}

	public class IC_ItemTypeForTreeUI extends IC_TreeModel {

		@Override
		public ItemType getType() {
			return null;
		}

		@Override
		protected FilteredItemNodeModel getTreeModel() {
			if (model == null) {
				model = new FilteredItemNodeModel();
				// en premier on rajoute les insances de cadse runtime trier par
				// le nom
				model.addItemFromItemTypeEntry(null, CadseGCST.CADSE_RUNTIME, ItemShortNameComparator.INSTANCE);

				// on creer deux categories
				categoryExtendsTo = new FilteredItemNode.Category();
				categoryExtendsTo.name = "extends";

				categoryExtendedBy = new FilteredItemNode.Category();
				categoryExtendedBy.name = "extended by";

				// on lie les deux category à un instance de ce Cadseruntime
				model.addCategories(CadseGCST.CADSE_RUNTIME, categoryExtendsTo, categoryExtendedBy);
				model.addItemFromLinkTypeEntry(categoryExtendsTo, CadseGCST.CADSE_RUNTIME_lt_EXTENDS,
						ItemShortNameComparator.INSTANCE, false, false);
				model.addItemFromLinkTypeEntry(categoryExtendedBy, CadseGCST.CADSE_RUNTIME_lt_EXTENDS,
						ItemShortNameComparator.INSTANCE, false, true);

			}
			return super.getTreeModel();
		}

		public void edit(Object o) {
			// TODO Auto-generated method stub

		}

		public Object[] getSources() {
			return CadseCore.getLogicalWorkspace().getCadseRuntime();
		}

		@Override
		public void select(Object data) {
			IItemNode node = (IItemNode) data;
			if (node.getItem() == null || node.getItem().getType() != CadseGCST.CADSE_RUNTIME) {
				return;
			}
			setSelectedItem(node.getItem());
		}

		@Override
		public void initAfterUI() {
			super.initAfterUI();
			AbstractCadseViewNode[] rootNodes = getOrCreateFilteredNode().getChildren();
			if ((rootNodes != null) && (rootNodes.length != 0)) {
				((DTreeModelUI) getUIField()).selectNode(rootNodes[0]);
			}
		}

	}

	public class MC_CDtree extends AbstractModelController {

		public Object getValue() {
			ArrayList<CadseRuntime> executedCadse = new ArrayList<CadseRuntime>();
			for (CadseRuntime cadseRuntime : CadseCore.getLogicalWorkspace().getCadseRuntime()) {
				if (cadseRuntime.isExecuted()) {
					executedCadse.add(cadseRuntime);
				}
			}

			return executedCadse.toArray(new CadseRuntime[executedCadse.size()]);
		}

		public void notifieValueChanged(UIField field, Object value) {

		}

		public ItemType getType() {
			return null;
		}

	}

	protected void registerListener() {
		// fieldExtends.addValidateContributor(this);
	}

	public void setSelectedItem(Item selectedItem) {
		this.selectedItem = selectedItem;
		this.fieldDescription.resetVisualValue();
		this.fieldItemRepoURL.resetVisualValue();
		this.fieldItemRepoLogin.resetVisualValue();
		this.fieldItemRepoPassword.resetVisualValue();
		this.fieldDefaultContentRepoURL.resetVisualValue();
	}

	/**
	 * 
	 */
	public DTreeModelUI createFieldExtends() {
		return new DTreeModelUI("#list", "Cadse", EPosLabel.top, new MC_CDtree(), new IC_ItemTypeForTreeUI(), false);
	}

	/**
	 * 
	 */
	public DTextUI createFieldDescription() {
		return new DTextUI(CadseGCST.CADSE_RUNTIME_at_DESCRIPTION, "description", EPosLabel.left,
				new ReadOnlyMC_AttributesItem(), null, 20, "", true, false, false);
	}

	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}
}

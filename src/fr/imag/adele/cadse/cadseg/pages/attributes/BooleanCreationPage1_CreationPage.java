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

import fede.workspace.model.manager.properties.FieldsCore;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

import fede.workspace.model.manager.properties.IFieldContenProposalProvider;
import fede.workspace.model.manager.properties.Proposal;
import fede.workspace.model.manager.properties.impl.ic.IC_Abstract;
import fede.workspace.model.manager.properties.impl.mc.StringToBooleanModelControler;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
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
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.IInteractionController;

/**
 * @generated
 */
public class BooleanCreationPage1_CreationPage extends
		AttributeCreationPage1_CreationPage {

	/**
	 * @generated
	 */
	protected DCheckBoxUI fieldMustBeInitialized;

	public final static class IC_DefaultValue extends IC_Abstract implements
			IInteractionController, IFieldContenProposalProvider,
			IContentProposalProvider {

		public char[] getAutoActivationCharacters() {
			return new char[0];
		}

		public String getCommandId() {
			return ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS;
		}

		public IContentProposalProvider getContentProposalProvider() {
			return this;
		}

		public int getProposalAcceptanceStyle() {
			return ContentProposalAdapter.PROPOSAL_REPLACE;
		}

		public Object setControlContents(String newValue) {
			return null;
		}

		public Object getValueFromProposal(Proposal proposal) {
			return proposal.getContent();
		}

		public IContentProposal[] getProposals(String contents, int position) {
			IContentProposal[] ret = new IContentProposal[2];
			ret[0] = new Proposal("true", "true", "", 4);
			ret[1] = new Proposal("false", "false", "", 5);
			return ret;
		}

		public ItemType getType() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	/**
	 * @generated
	 */
	protected BooleanCreationPage1_CreationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public BooleanCreationPage1_CreationPage(Item parent, ItemType it,
			LinkType lt) {
		super("creation-page1", "Create Boolean attribute",
				"Create Boolean attribute",
				"Create an attribute of type boolean", false, 3);
		this.parent = parent;
		this.it = it;
		this.lt = lt;
		this.__short_name__ = createInternalNameField();
		this.fieldDefaultValue = createFieldDefaultValue();
		this.fieldMustBeInitialized = createFieldMustBeInitialized();
		this.fieldIsList = createFieldIsList();
		setActionPage(new BooleanCreationPage1_CreationPageAction());
		addLast(this.__short_name__, this.fieldDefaultValue,
				this.fieldMustBeInitialized, this.fieldIsList);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	 * 
	 */
	public DTextUI createFieldDefaultValue() {
		return super.createFieldDefaultValue();
	}

	/**
	 * @generated
	 */
	public DCheckBoxUI createFieldMustBeInitialized() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED,
				"show attribute in creation wizard", EPosLabel.none, mc, null);
	}

	@Override
	protected IInteractionController createICDefaultValue() {
		return new IC_DefaultValue();
	}

	@Override
	protected MC_AttributesItem createMCDefaultValue() {
		return new MC_BooleanTextField();
	}
}

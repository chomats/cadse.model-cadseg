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
package fr.imag.adele.cadse.cadseg.managers;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

import fede.workspace.model.manager.properties.IFieldContenProposalProvider;
import fede.workspace.model.manager.properties.Proposal;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.cadseg.path.ParsePath;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.ui.RuningInteractionController;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.ICRunningField;

/**
 * The Class SelectionUC.
 */
public class IC_LINK_Selection extends ICRunningField implements RuningInteractionController, IFieldContenProposalProvider,
		IContentProposalProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.IFieldContenProposalProvider#getAutoActivationCharacters()
	 */
	public char[] getAutoActivationCharacters() {
		return new char[] { 's', '.' };
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
		return ContentProposalAdapter.PROPOSAL_INSERT;
	}

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
		Item currentItem = getItem();
		Item source = LinkManager.getSource(currentItem);
		Item dest = LinkManager.getDestination(currentItem);
		ParsePath pp = new ParsePath(source, dest, contents);
		return pp.getProposals(contents, position);
	}

	public ItemType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
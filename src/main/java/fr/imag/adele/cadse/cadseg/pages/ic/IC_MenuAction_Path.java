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
package fr.imag.adele.cadse.cadseg.pages.ic;

import java.util.ArrayList;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.ICRunningField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.IFieldContenProposalProvider;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.Proposal;

/**
 * The Class GlobalPathIC.
 * 
 * @generated
 */
public class IC_MenuAction_Path extends ICRunningField implements IFieldContenProposalProvider, IContentProposalProvider {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public IC_MenuAction_Path() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.IFieldContenProposalProvider#getAutoActivationCharacters()
	 */
	public char[] getAutoActivationCharacters() {
		return new char[] { 'C' };
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

	/** The whole regular expression. */
	String				fExpression;

	/** The document offset. */
	int					fDocumentOffset;

	/** The proposals. */
	ArrayList<Proposal>	fProposals;

	/** The high-priority proposals. */
	ArrayList<Proposal>	fPriorityProposals;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.fieldassist.IContentProposalProvider#getProposals(java.lang.String,
	 *      int)
	 */
	public IContentProposal[] getProposals(String contents, int position) {
		fProposals = new ArrayList<Proposal>();
		fPriorityProposals = new ArrayList<Proposal>();
		fExpression = contents;
		fDocumentOffset = position;

		addProposal("CONTEXT1", "CONTEXT 1", "insert in group context1"); //$NON-NLS-1$
		addProposal("CONTEXT2", "CONTEXT 2", "insert in group context2"); //$NON-NLS-1$
		addProposal("CONTEXT3", "CONTEXT 3", "insert in group context3"); //$NON-NLS-1$
		addProposal("CONTEXT4", "CONTEXT 4", "insert in group context4"); //$NON-NLS-1$
		addProposal("CONTEXT5", "CONTEXT 5", "insert in group context5"); //$NON-NLS-1$
		addProposal("CONTEXT6", "CONTEXT 6", "insert in group context6"); //$NON-NLS-1$
		addProposal("CONTEXT1/NEW", "In new menu", "insert in new menu"); //$NON-NLS-1$

		fPriorityProposals.addAll(fProposals);
		return fPriorityProposals.toArray(new IContentProposal[fPriorityProposals.size()]);
	}

	/**
	 * Adds a proposal.
	 * 
	 * @param proposal
	 *            the string to be inserted
	 * @param displayString
	 *            the proposal's label
	 * @param additionalInfo
	 *            the additional information
	 */
	private void addProposal(String proposal, String displayString, String additionalInfo) {
		String prolog = fExpression.substring(0, fDocumentOffset);
		if (!prolog.endsWith(proposal)) {
			for (int i = 1; i <= proposal.length() - 1; i++) {
				String prefix = proposal.substring(0, i);
				if (prolog.endsWith(prefix)) {
					fPriorityProposals.add(new Proposal(proposal.substring(i), displayString, additionalInfo, proposal
							.length()
							- i));
					return;
				}
			}
		}

		fProposals.add(new Proposal(proposal, displayString, additionalInfo, proposal.length()));
	}

	public ItemType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
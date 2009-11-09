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

package fr.imag.adele.cadse.cadseg;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

import fede.workspace.model.manager.properties.IFieldContenProposalProvider;
import fede.workspace.model.manager.properties.Proposal;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemFilter;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.ui.RuningInteractionController;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.ICRunningField;

/**
 * The Class IC_ItemTypeTemplateForText.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IC_ItemTypeTemplateForText extends ICRunningField implements RuningInteractionController,
		IFieldContenProposalProvider, IContentProposalProvider {

	/** The itemtype. */
	Item			itemtype;

	/** The context. */
	private Item	context;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.impl.ic.IC_Abstract#init()
	 */
	@Override
	public void init() {
		context = getItem();
	}

	/**
	 * Gets the item from context.
	 * 
	 * @return the item from context
	 */
	protected Item getItemFromContext() {
		return ManagerManager.getItemType(context);
	}

	/**
	 * Gets the context.
	 * 
	 * @return the context
	 */
	public Item getContext() {
		return context;
	}

	/**
	 * Gets the item type.
	 * 
	 * @return the item type
	 */
	public Item getItemType() {
		return itemtype;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.properties.IFieldContenProposalProvider#getAutoActivationCharacters()
	 */
	public char[] getAutoActivationCharacters() {
		return new char[] { '$', '{', '\\' };
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
		return newValue;
	}

	public Object getValueFromProposal(Proposal proposal) {
		return proposal.getContent();
	}

	/** The whole regular expression. */
	String				fExpression;

	/** The low-priority proposals. */
	ArrayList<Proposal>	fProposals;

	/** The document offset. */
	int					fDocumentOffset;

	/** The high-priority proposals. */
	ArrayList<Proposal>	fPriorityProposals;

	/**
	 * <code>true</code> iff <code>fExpression</code> ends with an open
	 * escape.
	 */
	boolean				fIsEscape;

	/**
	 * Computes applicable proposals for the find field.
	 * 
	 * @param contents
	 *            the contents of the subject control
	 * @param position
	 *            the cursor position
	 * 
	 * @return the proposals
	 */
	public IContentProposal[] getProposals(String contents, int position) {

		itemtype = getItemFromContext();
		fExpression = contents;
		fDocumentOffset = position;
		fPriorityProposals = new ArrayList<Proposal>();
		fProposals = new ArrayList<Proposal>();
		// String prolog= fExpression.substring(0, fDocumentOffset);

		boolean isEscape = false;
		esc: for (int i = position - 1; i >= 0; i--) {
			if (fExpression.charAt(i) == '\\') {
				isEscape = !isEscape;
			} else {
				break esc;
			}
		}
		fIsEscape = isEscape;

		// Item[] partParents = ItemTypeManager.getPartParents(itemtype);
		Item theCurrentItemType = itemtype;
		// if (partParents.length >1) {
		// for (Item item : partParents) {
		// if (prolog.endsWith("${#parent:"+item.getShortName()+"}") ) {
		// theCurrentItemType = item;
		// break;
		// }
		// }
		// } else {
		// if ((partParents.length == 1) && (prolog.endsWith("${#parent}")) ) {
		// theCurrentItemType = partParents[0];
		// }
		// }
		// if (theCurrentItemType != itemtype)
		// partParents = ItemTypeManager.getPartParents(itemtype);

		addProposal("${#parent-id}", "${#parent-id} : the parent internal id", ""); //$NON-NLS-1$
		addProposal("${#parent-unique-name}", "${#parent-unique-name} : the parent internal unique name", ""); //$NON-NLS-1$
		addProposal("${#parent-short-name}", "${#parent-short-name} : the parent internal short name", ""); //$NON-NLS-1$
		addProposal("${#parent-display-name}", "${#parent-display-name} : the parent internal display name}", ""); //$NON-NLS-1$
		addProposal("${#parent-type-name}", "${#parent-type-name} : the parent type", ""); //$NON-NLS-1$
		addProposal("${#id}", "${#id} : the internal id", ""); //$NON-NLS-1$
		addProposal("${#unique-name}", "${#unique-name} : the internal unique name", ""); //$NON-NLS-1$
		addProposal("${#short-name}", "${#short-name} : the internal short name", ""); //$NON-NLS-1$
		addProposal("${#display-name}", "${#display-name} : the internal display name}", ""); //$NON-NLS-1$
		addProposal("${#type-name}", "${#type-name} : it type", ""); //$NON-NLS-1$
		addProposal("${#link-type-name}", "${#link-type-name} - link-type name", ""); //$NON-NLS-1$

		HashSet<Item> visited = new HashSet<Item>();
		addAttributes(theCurrentItemType, visited, null);

		// if (partParents.length >1) {
		// for (Item item : partParents) {
		// addProposal( "${#parent:"+item.getShortName()+"}",
		// "${#parent:"+item.getShortName()+"}", "Parent :
		// "+item.getShortName());
		// }
		// } else {
		// if (partParents.length == 1)
		// addProposal( "${#parent}", "${#parent} - parent reference", "");
		// //$NON-NLS-1$
		// }

		// characters
		addBsProposal("\\\\", "\\\\ - Backslash", "Backslash"); //$NON-NLS-1$
		addBsProposal("\\{", "\\{ - {", "{"); //$NON-NLS-1$
		addBsProposal("\\}", "\\} - }", "}"); //$NON-NLS-1$

		fPriorityProposals.addAll(fProposals);
		return fPriorityProposals.toArray(new IContentProposal[fProposals.size()]);
	}

	/**
	 * Adds the attributes.
	 * 
	 * @param theCurrentItemType
	 *            the the current item type
	 * @param visited
	 *            the visited
	 * @param parentstring
	 *            the parentstring
	 */
	private void addAttributes(Item theCurrentItemType, HashSet<Item> visited, String parentstring) {
		if (visited.add(theCurrentItemType)) {
			Item[] attributes = ItemTypeManager.getAllAttributes(null, theCurrentItemType, new ItemFilter() {
				public boolean accept(Item item) {
					return item.getType() != CadseGCST.LINK;
				}

				public boolean stop() {
					return false;
				}
			}, true);

			for (Item item : attributes) {
				addProposal("${" + (parentstring == null ? "" : (parentstring + ".")) + item.getName() + "}", "${"
						+ (parentstring == null ? "" : (parentstring + ".")) + item.getName() + "}", "Attribute : "
						+ (parentstring == null ? "" : (parentstring + ".")) + item.getName());
			}

			Item[] links = ItemTypeManager.getAllAttributes(null, theCurrentItemType, new ItemFilter() {
				public boolean accept(Item item) {
					return item.getType() == CadseGCST.LINK && LinkManager.getMax(item) == 1;
				}

				public boolean stop() {
					return false;
				}
			}, true);

			for (Item l : links) {
				Item dest = LinkManager.getDestination(l);
				if (dest == null) {
					continue;
				}
				addAttributes(dest, visited, (parentstring == null ? "" : (parentstring + ".")) + l.getName());
			}
		}
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

	/**
	 * Adds a proposal.
	 * 
	 * @param proposal
	 *            the string to be inserted
	 * @param cursorPosition
	 *            the cursor position after insertion, relative to the start of
	 *            the proposal
	 * @param displayString
	 *            the proposal's label
	 * @param additionalInfo
	 *            the additional information
	 * @param fProposals
	 *            the f proposals
	 */
	private void addProposal(ArrayList<Proposal> fProposals, String proposal, int cursorPosition, String displayString,
			String additionalInfo) {
		fProposals.add(new Proposal(proposal, displayString, additionalInfo, cursorPosition));
	}

	//
	// /**
	// * Adds a proposal to the priority proposals list.
	// *
	// * @param proposal the string to be inserted
	// * @param displayString the proposal's label
	// * @param additionalInfo the additional information
	// */
	// private void addPriorityProposal(ArrayList<Proposal> fPriorityProposals,
	// String proposal, String displayString, String additionalInfo) {
	// fPriorityProposals.add(new Proposal(proposal, displayString,
	// additionalInfo,
	// proposal.length()));
	// }

	// /**
	// * Adds a proposal. Ensures that existing pre- and postfixes are not
	// duplicated.
	// *
	// * @param proposal the string to be inserted
	// * @param cursorPosition the cursor position after insertion,
	// * relative to the start of the proposal
	// * @param displayString the proposal's label
	// * @param additionalInfo the additional information
	// * @param fDocumentOffset
	// * @param fExpression
	// * @param fProposals
	// * @param fIsEscape
	// * @param fPriorityProposals
	// */
	// private void addBracketProposal( int fDocumentOffset, String fExpression,
	// ArrayList<Proposal> fProposals, boolean fIsEscape, String proposal, int
	// cursorPosition, String displayString, String additionalInfo,
	// ArrayList<Proposal> fPriorityProposals) {
	// String prolog= fExpression.substring(0, fDocumentOffset);
	// if (! fIsEscape && prolog.endsWith("\\") && proposal.startsWith("\\")) {
	// //$NON-NLS-1$//$NON-NLS-2$
	// fProposals.add(new Proposal(proposal, displayString, additionalInfo,
	// cursorPosition));
	// return;
	// }
	// for (int i= 1; i <= cursorPosition; i++) {
	// String prefix= proposal.substring(0, i);
	// if (prolog.endsWith(prefix)) {
	// String postfix= proposal.substring(cursorPosition);
	// String epilog= fExpression.substring(fDocumentOffset);
	// if (epilog.startsWith(postfix)) {
	// fPriorityProposals.add(new Proposal(proposal.substring(i,
	// cursorPosition),
	// displayString, additionalInfo, cursorPosition-i));
	// } else {
	// fPriorityProposals.add(new Proposal(proposal.substring(i), displayString,
	// additionalInfo, cursorPosition-i));
	// }
	// return;
	// }
	// }
	// fProposals.add(new Proposal(proposal, displayString, additionalInfo,
	// cursorPosition));
	// }

	/**
	 * Adds a proposal that starts with a backslash. Ensures that the backslash
	 * is not repeated if already typed.
	 * 
	 * @param proposal
	 *            the string to be inserted
	 * @param displayString
	 *            the proposal's label
	 * @param additionalInfo
	 *            the additional information
	 */
	private void addBsProposal(String proposal, String displayString, String additionalInfo) {
		String prolog = fExpression.substring(0, fDocumentOffset);
		int position = proposal.length();
		// If the string already contains the backslash, do not include in
		// the proposal
		if (prolog.endsWith("\\")) { //$NON-NLS-1$
			position--;
			proposal = proposal.substring(1);
		}

		if (fIsEscape) {
			fPriorityProposals.add(new Proposal(proposal, displayString, additionalInfo, position));
		} else {
			addProposal(fProposals, proposal, position, displayString, additionalInfo);
		}
	}

	public ItemType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
/**
 * 
 */
package fr.imag.adele.cadse.cadseg.pages.ic;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.ui.RuningInteractionController;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.ICRunningField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.IFieldContenProposalProvider;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.Proposal;

public final class IC_BooleanDefaultValue extends ICRunningField implements
	RuningInteractionController, IFieldContenProposalProvider,
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
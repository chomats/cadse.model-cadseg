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

import java.util.List;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.ui.IEventListener;
import fr.imag.adele.cadse.core.ui.RuningInteractionController;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;
import fr.imag.adele.cadse.core.ui.UIRunningValidator;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.ICRunningField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.IFieldContenProposalProvider;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.Proposal;

/**
 * The Class DefaultValueIC.
 */
public final class IC_EnumDefaultValue extends ICRunningField implements
		RuningInteractionController, IEventListener,
		IFieldContenProposalProvider, IContentProposalProvider, UIRunningValidator {

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
				&& CadseGCST.ENUM_lt_ENUM_TYPE == field.getAttributeDefinition()) {
			Item enumType = ((Link) value).getResolvedDestination();
			if (enumType != null) {
				// force to recomptue values
				values = null;
				getValues();
				if (values.size() != 0) {
					String actuelValues = (String) _uiPlatform.getVisualValue(getUIField());
					if (!values.contains(actuelValues)) {
						_uiPlatform.setVisualValue(getUIField().getAttributeDefinition(), values.get(0), true);
					}
					_uiPlatform.setEnabled(getUIField(), true);
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
		_uiPlatform.setEnabled(getUIField(), isEnable());
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

	@Override
	public void init(UIPlatform uiPlatform) throws CadseException {
		_uiPlatform = uiPlatform;
	}

	@Override
	public boolean validSubValueAdded(UIField field, Object addedElement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validSubValueRemoved(UIField field, Object removedElement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validValue(UIField field, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validValueChanged(UIField field, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validValueDeleted(UIField field, Object deletedValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Item getDescriptor() {
		return _ic;
	}

	@Override
	public int incrementError() {
		return 0;
	}
}
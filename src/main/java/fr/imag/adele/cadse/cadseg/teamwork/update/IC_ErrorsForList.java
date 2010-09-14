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
package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_AbstractForList;

/**
 * Interaction controller used to represent a list of errors.
 * 
 * @author Thomas
 * 
 */
public class IC_ErrorsForList extends IC_AbstractForList implements ILabelProvider {

	private UpdateState	_updateState;
	private OperationCategory _opCateg;
	
	public IC_ErrorsForList(UpdateState updateState, OperationCategory opCateg) {
		super("", "");
		_updateState = updateState;
		_opCateg = opCateg;
	}

	@Override
	public Object[] getValues() {
		List<fr.imag.adele.cadse.cadseg.teamwork.Error> errors = null;
		if (OperationCategory.IMPACTS.equals(_opCateg))
			errors = _updateState.getDefinition().getErrors().getErrors();
		else if (OperationCategory.TO_PERFORM.equals(_opCateg))
			errors = _updateState.getOperationsToPerformErrors().getErrors();
		
		return errors.toArray(new fr.imag.adele.cadse.cadseg.teamwork.Error[errors.size()]);
	}

	public ItemType getType() {
		return null;
	}

	public ILabelProvider getLabelProvider() {
		return this;
	}

	public Image getImage(Object element) {
		return null;
	}

	public String getText(Object element) {
		if ((element == null) || !(element instanceof fr.imag.adele.cadse.cadseg.teamwork.Error)) {
			return "";
		}

		fr.imag.adele.cadse.cadseg.teamwork.Error error = (fr.imag.adele.cadse.cadseg.teamwork.Error) element;
		return error.getMessage();
	}

	public void addListener(ILabelProviderListener listener) {
		// do nothing
	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// do nothing
	}
}

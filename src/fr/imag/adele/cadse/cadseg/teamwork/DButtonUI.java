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

package fr.imag.adele.cadse.cadseg.teamwork;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import fede.workspace.model.manager.properties.impl.ui.DAbstractField;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IFedeFormToolkit;
import fr.imag.adele.cadse.core.ui.IInteractionController;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPageController;

/**
 * Represents a field with a button.
 */
public class DButtonUI extends DAbstractField {

	protected Button	_button;

	public DButtonUI(CompactUUID uuid, String key, String label, IModelController mc, ActionController ic) {
		super(uuid, key, label, EPosLabel.none, mc, ic);
	}

	public DButtonUI(String key, String label, IModelController mc, ActionController ic) {
		super(key, label, EPosLabel.none, mc, ic);
	}

	public DButtonUI(CompactUUID uuid, String key) {
		super(uuid, key);
	}

	@Override
	public Object createControl(final IPageController globalUIController, IFedeFormToolkit toolkit, Object container,
			int hspan) {
		Composite composite = (Composite) container;

		_button = new Button(composite, SWT.PUSH);
		_button.setText(getLabel());
		_button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(@SuppressWarnings("unused")
			SelectionEvent e) {
				handleSelect(globalUIController);
			}
		});

		internalSetEditable(isEditable());

		return composite;
	}

	@Override
	public void initAfterUI() {
		super.initAfterUI();
	}

	protected void handleSelect(IPageController fieldController) {
		IInteractionController ic = getInteractionController();
		if (ic == null) {
			return;
		}

		if (ic instanceof ActionController) {
			ActionController ac = (ActionController) ic;
			ac.callAction();
		}
	}

	@Override
	public Object getUIObject(int index) {
		return _button;
	}

	@Override
	public Object getVisualValue() {
		return null;
	}

	public void setVisualValue(Object visualValue, boolean sendNotification) {
		// do nothing
	}

	public ItemType getType() {
		return null;
	}

	@Override
	public Control getMainControl() {
		return _button;
	}

	@Override
	public Object[] getSelectedObjects() {
		return null;
	}
}

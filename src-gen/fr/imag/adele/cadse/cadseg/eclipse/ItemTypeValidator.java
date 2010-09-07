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
package fr.imag.adele.cadse.cadseg.eclipse;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.cadseg.Messages;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;

/**
 * The Class ItemTypeValidator.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ItemTypeValidator implements ISelectionStatusValidator {

	/** The it. */
	ItemType	it;

	/**
	 * Instantiates a new item type validator.
	 * 
	 * @param it
	 *            the it
	 */
	public ItemTypeValidator(ItemType it) {
		this.it = it;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.dialogs.ISelectionStatusValidator#validate(java.lang.Object[])
	 */
	public IStatus validate(Object[] selection) {
		if (selection == null)
			return new Status(Status.ERROR, WSPlugin.PLUGIN_ID, Messages.status_select_an_item);
		if (selection.length != 1)
			return new Status(Status.ERROR, WSPlugin.PLUGIN_ID, Messages.status_select_one_item);
		if (!(selection[0] instanceof Item))
			return new Status(Status.ERROR, WSPlugin.PLUGIN_ID, Messages.status_select_one_item);

		return equals(it, (Item) selection[0]);
	}

	/**
	 * Equals.
	 * 
	 * @param it2
	 *            the it2
	 * @param item
	 *            the item
	 * 
	 * @return the i status
	 */
	protected IStatus equals(ItemType it2, Item item) {
		if (item.getType() != it2)
			return new Status(Status.ERROR, WSPlugin.PLUGIN_ID, Messages.status_select_an_item_of_type
					+ it2.getDisplayName());
		return Status.OK_STATUS;
	}

}

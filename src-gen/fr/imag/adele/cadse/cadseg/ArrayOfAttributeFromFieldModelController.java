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
import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.cadseg.managers.CadseG_WLWCListener;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.ui.AbstractModelController;
import fr.imag.adele.cadse.core.key.Key;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;
import fr.imag.adele.cadse.core.ui.RunningModelController;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * The Class ArrayOfAttributeFromFieldModelController.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ArrayOfAttributeFromFieldModelController extends AbstractModelController implements RunningModelController {

	/** The page. */
	private Item	page;

	/**
	 * Instantiates a new array of attribute from field model controller.
	 * 
	 * @param page
	 *            the page
	 */
	public ArrayOfAttributeFromFieldModelController(Item page) {
		this.page = page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.IModelController#getValue()
	 */
	public Object getValue() {
		Collection<Item> ret = page.getOutgoingItems(CadseGCST.PAGE_lt_ATTRIBUTES, true);
		List<Item> attributesList = new ArrayList<Item>();
		for (Item item : ret) {
			Item att = FieldManager.getAttribute(item);
			if (att != null) {
				attributesList.add(att);
			}
		}

		return attributesList.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.imag.adele.cadse.core.ui.IEventListener#notifieValueChanged(fr.imag
	 * .adele.cadse.core.ui.UIField, java.lang.Object)
	 */
	public void notifieValueChanged(UIField field, Object value) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.imag.adele.cadse.core.ui.AbstractModelController#notifieSubValueAdded
	 * (fr.imag.adele.cadse.core.ui.UIField, java.lang.Object)
	 */
	@Override
	public void notifieSubValueAdded(UIField field, Object added) {
		try {
			if (added instanceof Object[]) {
				Object[] arrayadded = (Object[]) added;
				CadseCore.getCadseDomain().beginOperation("createLinks");
				try {
					for (int i = 0; i < arrayadded.length; i++) {
						doCreateField((Item) arrayadded[i]);
					}
				} finally {
					CadseCore.getCadseDomain().endOperation();
				}
			} else {
				doCreateField((Item) added);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Do create field.
	 * 
	 * @param att
	 *            the att
	 */
	private void doCreateField(Item att) {
		try {
			LogicalWorkspaceTransaction copy = CadseCore.getLogicalWorkspace().createTransaction();
			//CadseG_WLWCListener.doCreateField(copy, itemType, att, page);
			copy.getItem(page).createLink(CadseGCST.PAGE_lt_ATTRIBUTES, att);
			copy.commit();
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Do remove field.
	 * 
	 * @param page
	 *            the page
	 * @param attribute
	 *            the attribute
	 */
	public static void doRemoveField(Item page, Item attribute) {
		String shortName = CadseG_WLWCListener.doShortName(attribute);

		try {
			Key key = CadseGCST.FIELD.getKeyDefinition().computeKey(page.getKey(), shortName, attribute);
			Item field = CadseCore.getLogicalWorkspace().getItem(key);
			if (field != null) {
				field.delete(false);
			}
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.imag.adele.cadse.core.ui.AbstractModelController#notifieSubValueRemoved
	 * (fr.imag.adele.cadse.core.ui.UIField, java.lang.Object)
	 */
	@Override
	public void notifieSubValueRemoved(UIField field, Object removed) {
		if (removed instanceof Object[]) {
			Object[] arrayremoved = (Object[]) removed;
			for (int i = 0; i < arrayremoved.length; i++) {
				doRemoveField(page, (Item) arrayremoved[i]);
			}
		} else {
			doRemoveField(page, (Item) removed);
		}
	}

	public ItemType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}

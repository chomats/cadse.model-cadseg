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

package fr.imag.adele.cadse.cadseg.ext.actions;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;

/**
 * The Class ExtItemTypeExt.
 * 
 * @generated
 */
public class ExtItemTypeExt {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ExtItemTypeExt() {
	}

	/**
	 * get a link 'actions-model' from 'extItemType' to 'actions-model'.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the actions model link
	 * 
	 * @generated
	 */
	static public Link getActionsModelLink(Item extItemType) {
		return extItemType.getOutgoingLink(CadseGCST.ACTION_EXT_ITEM_TYPE_lt_ACTIONS_MODEL);
	}

	/**
	 * get all link destination 'actions-model' from 'extItemType' to
	 * 'actions-model'.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the actions model all
	 * 
	 * @generated
	 */
	static public Item getActionsModelAll(Item extItemType) {
		return extItemType.getOutgoingItem(CadseGCST.ACTION_EXT_ITEM_TYPE_lt_ACTIONS_MODEL, false);
	}

	/**
	 * get resolved link destination 'actions-model' from 'extItemType' to
	 * 'actions-model'.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the actions model
	 * 
	 * @generated
	 */
	static public Item getActionsModel(Item extItemType) {
		return extItemType.getOutgoingItem(CadseGCST.ACTION_EXT_ITEM_TYPE_lt_ACTIONS_MODEL, true);
	}

	/**
	 * set a link 'actions-model' from 'extItemType' to 'Menu'.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setActionsModel(Item extItemType, Item value) throws CadseException {
		extItemType.setOutgoingItem(CadseGCST.ACTION_EXT_ITEM_TYPE_lt_ACTIONS_MODEL, value);
	}

}

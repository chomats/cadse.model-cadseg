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
package fr.imag.adele.cadse.cadseg.teamwork.ui;

import fr.imag.adele.cadse.core.Item;

/**
 * Listener which is interested in item selection informations.
 * @author Thomas
 *
 */
public interface ItemSelectionListener {

	/**
	 * Called each time a new item is selected.
	 * 
	 * @param newItem new selected item
	 */
	public void selectItem(Item newItem);
	
	/**
	 * Called each time a selected item becomes deselected.
	 * 
	 * @param oldItem item which has been deselected
	 */
	public void deselectItem(Item oldItem);
	
	/**
	 * Called when there is no more selected item. 
	 */
	public void noMoreSelectedItem();
}

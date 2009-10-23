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

import fr.imag.adele.cadse.core.CompactUUID;

/**
 * Simple error containing only a message.
 * 
 * @author Thomas
 *
 */
public class MsgError implements Error {
	
	private CompactUUID _itemId;
	
	private String _errorMsg;
	
	/**
	 * Create a new error with a message.
	 * 
	 * @param itemId   item id
	 * @param errorMsg error message
	 */
	public MsgError(CompactUUID itemId, String errorMsg) {
		_itemId = itemId;
		_errorMsg = errorMsg;
	}

	public CompactUUID getItem() {
		return _itemId;
	}

	public String getMessage() {
		return _errorMsg;
	}

}

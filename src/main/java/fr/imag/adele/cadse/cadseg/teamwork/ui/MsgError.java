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

import java.util.UUID;

import fr.imag.adele.cadse.cadseg.teamwork.Error;

/**
 * Simple error containing only a message.
 * 
 * @author Thomas
 *
 */
public class MsgError implements Error {
	
	private UUID _itemId;
	
	private String _errorMsg;
	
	private Throwable _error;
	
	/**
	 * Create a new error with a message.
	 * 
	 * @param itemId   item id
	 * @param errorMsg error message
	 */
	public MsgError(UUID itemId, String errorMsg, Throwable error) {
		_itemId = itemId;
		_errorMsg = errorMsg;
		_error = error;
	}

	public UUID getItem() {
		return _itemId;
	}

	public String getMessage() {
		return _errorMsg;
	}

	@Override
	public boolean isResolved() {
		return false;
	}
	
	public Throwable getException() {
		return _error;
	}

}

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.imag.adele.cadse.cadseg.teamwork.ui.MsgError;
import java.util.UUID;

/**
 * Represents a validation state of a set of items.
 * Each item is associated with a list of errors.
 *
 * Note: this class is not thread safe.
 * 
 * @author Thomas
 *
 */
public class Errors {

	private Map<UUID, List<Error>> _errors = new HashMap<UUID, List<Error>>();
	
	private List<UnexpectedError> _unexpectedErrors = new ArrayList<UnexpectedError>();
	
	/**
	 * Attach an unexpected error.
	 * 
	 * @param errorMessage   error message
	 * @param errorException a throwable
	 */
	public void addError(String errorMessage, Throwable errorException) {
		_unexpectedErrors.add(new UnexpectedError(errorMessage, errorException));
	}
	
	/**
	 * Returns list of unexpected errors.
	 * 
	 * @return list of unexpected errors.
	 */
	public List<UnexpectedError> getUnexpectedErrors() {
		return _unexpectedErrors;
	}
	
	/**
	 * Attach a simple error to specified item.
	 * 
	 * @param itemId       id of item in error
	 * @param errorMessage error message
	 */
	public void addError(UUID itemId, String errorMessage) {
		addError(itemId, new MsgError(itemId, errorMessage));
	}
	
	/**
	 * Attach an error to specified item.
	 * 
	 * @param itemId id of item in error
	 * @param error  the error
	 */
	public void addError(UUID itemId, Error error) {
		List<Error> errors = _errors.get(itemId);
		if (errors == null) {
			errors = new ArrayList<Error>();
			_errors.put(itemId, errors);
		}
		errors.add(error);
	}
	
	/**
	 * Remove specified error from error list of this item. 
	 * 
	 * @param itemId item id
	 * @param error  error to remove
	 */
	public void removeError(UUID itemId, Error error) {
		List<Error> errors = _errors.get(itemId);
		if (errors == null) 
			return;
		
		errors.remove(error);
		
		if (errors.isEmpty())
			_errors.remove(itemId);
	}
	
	/**
	 * Remove all errors instance of specified class attached to specified item.
	 * 
	 * @param itemId     item id
	 * @param errorClass class of error (should implements or extends Error)
	 */
	public void removeError(UUID itemId, Class errorClass) {
		List<Error> errors = _errors.get(itemId);
		if (errors == null) 
			return;
		
		List<Error> errorsToRem = new ArrayList<Error>();
		for (Error error : errors) {
			if (errorClass.isInstance(error))
				errorsToRem.add(error);
		}
		
		for (Error error : errorsToRem) {
			errors.remove(error);
		}
		
		if (errors.isEmpty())
			_errors.remove(itemId);
	}
	
	/**
	 * Remove all errors attached to the specified item.
	 * 
	 * @param itemId item id
	 */
	public void removeErrors(UUID itemId) {
		_errors.remove(itemId);
	}
	
	/**
	 * Return first error attached to this item.
	 * Return null if no error is attached to specified item.
	 * 
	 * @param itemId item id
	 * @return first error attached to this item.
	 */
	public Error getError(UUID itemId) {
		List<Error> errors = _errors.get(itemId);
		if ((errors == null) || (errors.isEmpty()))
			return null;
		
		return errors.get(0);
	}
	
	/**
	 * Return all errors attached to this item.
	 * Return an empty list if there is no error attached to this item.
	 * 
	 * @param itemId item id
	 * @return all errors attached to this item.
	 */
	public List<Error> getErrors(UUID itemId) {
		List<Error> errors = _errors.get(itemId);
		if ((errors == null) || (errors.isEmpty()))
			return new ArrayList<Error>();
		
		return errors;
	}
	
	/**
	 * Return all errors.
	 * Return an empty list if there is no error.
	 * 
	 * @return all errors.
	 */
	public List<Error> getErrors() {
		List<Error> errors = new ArrayList<Error>();
		for (List<Error> curErrors : _errors.values()) {
			if ((errors == null) || (errors.isEmpty()))
				continue;
		
			errors.addAll(curErrors);
		}
		
		return errors;
	}
	
	/**
	 * Return true if at least one error is attached to this item.
	 * 
	 * @param itemId item id
	 * @return true if at least one error is attached to this item.
	 */
	public boolean isInError(UUID itemId) {
		return _errors.containsKey(itemId);
	}
	
	/**
	 * Return true if there is at least one error.
	 * 
	 * @return true if there is at least one error.
	 */
	public boolean hasError() {
		return !_errors.isEmpty();
	}

	/**
	 * Remove all errors.
	 */
	public void clear() {
		_errors.clear();
	}

	/**
	 * Return true if there is at least one error which is considered as not resolved.
	 * 
	 * @return true if there is at least one error which is considered as not resolved.
	 */
	public boolean hasNotResolvedError() {
		for (List<Error> errors : _errors.values()) {
			if (errors == null)
				continue;
			
			for (Error error : errors) {
				if (!error.isResolved())
					return true;
			}
		}
		
		return false;
	}
}
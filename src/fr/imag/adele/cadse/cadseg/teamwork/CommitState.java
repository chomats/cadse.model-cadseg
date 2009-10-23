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
import java.util.List;

import fr.imag.adele.cadse.core.CompactUUID;

/**
 * Represents state of a commit operation :
 * - list of visited items
 * - list of items to commit
 * - list of already commited items
 * - list of encountered errors
 *  
 * @author Thomas
 *
 */
public class CommitState {

	private List<CompactUUID> _commitedItems = new ArrayList<CompactUUID>();
	
	private List<CompactUUID> _toCommitItems = new ArrayList<CompactUUID>();

	private Errors _errors = new Errors();
	
	private String _comment;

	private boolean _performCommit = false;
	
	private boolean _commitPerformed = false;
	
	private boolean _failed = false;
	
	private List<CommitListener> _listeners = new ArrayList<CommitListener>();
	
	/**
	 * Return validation state containing all errors.
	 * 
	 * @return validation state containing all errors.
	 */
	public Errors getErrors() {
		return _errors;
	}
	
	/**
	 * Return all items which will be commit.
	 * 
	 * @return all items which will be commit.
	 */
	public List<CompactUUID> getItemsToCommit() {
		return _toCommitItems;
	}

	/**
	 * Add an item to commit.
	 * 
	 * @param itemId item id
	 */
	public void addItemToCommit(CompactUUID itemId) {
		if (!_toCommitItems.contains(itemId))
			_toCommitItems.add(itemId);
	}
	
	/**
	 * Mark an item as in commit process.
	 * 
	 * @param itemId item id
	 */
	public void beginCommittingItem(CompactUUID itemId) {
		// notify listeners
		synchronized (_listeners) {
			for (CommitListener listener : _listeners) {
				try {
					listener.beginCommitItem(itemId);
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Mark item as committed.
	 * 
	 * @param itemId item id
	 */
	public void markAsCommitted(CompactUUID itemId) {
		_toCommitItems.remove(itemId);
		addCommitedItem(itemId);
	}
	
	/**
	 * Return all items which has been committed.
	 * 
	 * @return all items which has been committed.
	 */
	public List<CompactUUID> getCommittedItems() {
		return _commitedItems;
	}

	/**
	 * Internal method to mark an item as committed.
	 * 
	 * @param itemId item id
	 */
	private void addCommitedItem(CompactUUID itemId) {
		if (!_commitedItems.contains(itemId)) {
			_commitedItems.add(itemId);
			
			// notify listeners
			synchronized (_listeners) {
				for (CommitListener listener : _listeners) {
					try {
						listener.endCommitItem(itemId);
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Return comment of this commit operation.
	 * 
	 * @return comment of this commit operation.
	 */
	public String getComment() {
		if (_comment == null)
			return "";
		
		return _comment;
	}

	/**
	 * Set comment of this commit operation.
	 * 
	 * @param comment comment of this commit operation
	 */ 
	public void setComment(String comment) {
		this._comment = comment;
	}

	/**
	 * Return true if commit operation is performing.
	 * 
	 * @return true if commit operation is performing.
	 */
	public boolean isPerformingCommit() {
		return _performCommit;
	}

	/**
	 * Mark this commit operation as started.
	 */
	public void beginCommit() {
		_performCommit = true;
		
		// notify listeners
		synchronized (_listeners) {
			for (CommitListener listener : _listeners) {
				try {
					listener.beginCommit();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Mark this commit operation as aborted.
	 * This can happen if an error has been thrown
	 * or user canceled the commit operation.
	 */
	public void abortCommit() {
		_performCommit = false;
		_commitPerformed = true;
		_failed = true;
		
		// notify listeners
		synchronized (_listeners) {
			for (CommitListener listener : _listeners) {
				try {
					listener.commitFail();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Mark this commit operation as completed.
	 * In this case, no error has been noted.
	 */
	public void endCommit() {
		_performCommit = false;
		_commitPerformed = true;
		_failed = false;
		
		// notify listeners
		synchronized (_listeners) {
			for (CommitListener listener : _listeners) {
				try {
					listener.endCommit();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Return true if commit operation has been performed successfully.
	 * 
	 * @return true if commit operation has been performed successfully.
	 */
	public boolean isCommitPerformed() {
		return _commitPerformed && !_failed;
	}

	/**
	 * Return true if commit operation has failed.
	 * 
	 * @return true if commit operation has failed.
	 */
	public boolean isFailed() {
		return _failed;
	}

	/**
	 * Return all listeners interested in monitoring this commit operation. 
	 * 
	 * @return all listeners interested in monitoring this commit operation. 
	 */
	public List<CommitListener> getListeners() {
		return _listeners;
	}

	/**
	 * Add a listener interested in monitoring this commit operation. 
	 * 
	 * @param listener a listener
	 */
	public void addListener(CommitListener listener) {
		synchronized (_listeners) {
			_listeners.add(listener);
		}
	}
	
	/**
	 * Remove a listener interested in monitoring this commit operation. 
	 * 
	 * @param listener a listener
	 */
	public void removeListener(CommitListener listener) {
		synchronized (_listeners) {
			_listeners.remove(listener);
		}
	}

	/**
	 * Return true if specified item will be committed.
	 * 
	 * @param itemId item id
	 * @return true if specified item will be committed.
	 */
	public boolean isToCommit(CompactUUID itemId) {
		if (itemId == null)
			return false;
		
		return _toCommitItems.contains(itemId);
	}
	
	/**
	 * Return true if specified item has been committed without errors.
	 * 
	 * @param itemId item id
	 * @return true if specified item has been committed without errors.
	 */
	public boolean isCommitted(CompactUUID itemId) {
		if (itemId == null)
			return false;
		
		return _commitedItems.contains(itemId);
	}

	/**
	 * Remove an item to list of items to commit.
	 * 
	 * @param itemId item id
	 */
	public void removeItemToCommit(CompactUUID itemId) {
		_toCommitItems.remove(itemId);
	}
	
	/**
	 * Remove all items to list of items to commit.
	 * Remove also all errors associated to these items.
	 */
	public void clearItemsToCommit() {
		_toCommitItems.clear();
		clearErrors();
	}
	
	/**
	 * Remove all errors.
	 */
	public void clearErrors() {
		_errors.clear();
	}

	/**
	 * Returns true if there is no items to commit.
	 * 
	 * @return true if there is no items to commit.
	 */
	public boolean hasNoItemToCommit() {
		return _toCommitItems.isEmpty();
	}
}

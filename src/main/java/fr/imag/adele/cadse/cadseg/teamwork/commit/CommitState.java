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
package fr.imag.adele.cadse.cadseg.teamwork.commit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.imag.adele.cadse.cadseg.teamwork.Errors;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;

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

	private List<UUID> _commitedItemStates = new ArrayList<UUID>();
	
	private List<UUID> _commitedItemLinks = new ArrayList<UUID>();
	
	private List<UUID> _commitedItemContents = new ArrayList<UUID>();
	
	private List<UUID> _commitedItems = new ArrayList<UUID>();
	
	private List<UUID> _toCommitItems = new ArrayList<UUID>();
	
	private List<UUID> _toCommitItemsRequirements = new ArrayList<UUID>();

	private Errors _errors = new Errors();
	
	private String _comment;

	private boolean _performCommit = false;
	
	private boolean _commitPerformed = false;
	
	private boolean _failed = false;
	
	private List<CommitListener> _listeners = new ArrayList<CommitListener>();
	
	private LogicalWorkspaceTransaction _transaction; 
	
	/**
	 * Return validation state containing all errors.
	 * 
	 * @return validation state containing all errors.
	 */
	public Errors getErrors() {
		return _errors;
	}
	
	/**
	 * Return all items which requires to be committed.
	 * 
	 * @return all items which requires to be committed.
	 */
	public List<UUID> getItemsToCommitRequirements() {
		return _toCommitItemsRequirements;
	}
	
	/**
	 * Return all items which requires to be committed and that it is not already committed.
	 * 
	 * @return all items which requires to be committed and that it is not already committed.
	 */
	public List<UUID> getItemsToCommit() {
		return _toCommitItems;
	}

	/**
	 * Add an item to commit.
	 * 
	 * @param itemId item id
	 */
	public void addItemToCommit(UUID itemId) {
		if (!_toCommitItems.contains(itemId))
			_toCommitItems.add(itemId);
	}
	
	/**
	 * Mark an item as in commit process.
	 * 
	 * @param itemId item id
	 */
	public void beginCommittingItem(UUID itemId) {
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
	 * Mark item state as committed.
	 * 
	 * @param itemId item id
	 */
	public void markStateAsCommitted(UUID itemId) {
		if (!_commitedItemStates.contains(itemId)) {
			_commitedItemStates.add(itemId);
			
			// notify listeners
			synchronized (_listeners) {
				for (CommitListener listener : _listeners) {
					try {
						listener.endCommitItemState(itemId);
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * Mark item outgoing links as committed.
	 * 
	 * @param itemId item id
	 */
	public void markLinksAsCommitted(UUID itemId) {
		if (!_commitedItemLinks.contains(itemId)) {
			_commitedItemLinks.add(itemId);
			
			// notify listeners
			synchronized (_listeners) {
				for (CommitListener listener : _listeners) {
					try {
						listener.endCommitItemLinks(itemId);
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * Mark item content as committed.
	 * 
	 * @param itemId item id
	 */
	public void markContentsAsCommitted(UUID itemId) {
		if (!_commitedItemContents.contains(itemId)) {
			_commitedItemContents.add(itemId);
			
			// notify listeners
			synchronized (_listeners) {
				for (CommitListener listener : _listeners) {
					try {
						listener.endCommitItemContent(itemId);
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * Mark item as committed.
	 * 
	 * @param itemId item id
	 */
	public void markAsCommitted(UUID itemId) {
		_toCommitItems.remove(itemId);
		addCommitedItem(itemId);
	}
	
	/**
	 * Return all items which has been committed.
	 * 
	 * @return all items which has been committed.
	 */
	public List<UUID> getCommittedItems() {
		return _commitedItems;
	}

	/**
	 * Internal method to mark an item as committed.
	 * 
	 * @param itemId item id
	 */
	private void addCommitedItem(UUID itemId) {
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
		
		_transaction = TWUtil.createWorkspaceTransactionForTWoperation();
		
		_toCommitItemsRequirements = new ArrayList<UUID>(_toCommitItems);
		computeCommitOperationsOrder();
		
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
	 * Returns transaction in which all commit operations will be performed.
	 * 
	 * @return transaction in which all commit operations will be performed.
	 */
	public LogicalWorkspaceTransaction getTransaction() {
		return _transaction;
	}
	
	/**
	 * Compute from requirements order of commit operations.
	 * 
	 */
	private void computeCommitOperationsOrder() {
		_toCommitItems.clear();
		
		for (UUID itemId : _toCommitItemsRequirements) {
			Item item = _transaction.getItem(itemId);
			int itemIdx = _toCommitItems.indexOf(itemId);
			if (itemIdx != -1)
				continue;
			
			Item parent = item.getPartParent();
			if (parent == null) {
				_toCommitItems.add(itemId);
			} else {
				UUID parentId = parent.getId();
				int parentIdx = _toCommitItems.indexOf(parentId);
				if (parentIdx == -1) 
					_toCommitItems.add(parentId);
				_toCommitItems.add(itemId);
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
	public boolean isToCommit(UUID itemId) {
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
	public boolean isCommitted(UUID itemId) {
		if (itemId == null)
			return false;
		
		return _commitedItems.contains(itemId);
	}
	
	/**
	 * Return true if specified item content has been committed without errors.
	 * 
	 * @param itemId item id
	 * @return true if specified item content has been committed without errors.
	 */
	public boolean isContentCommitted(UUID itemId) {
		if (itemId == null)
			return false;
		
		return _commitedItemContents.contains(itemId);
	}
	
	/**
	 * Return true if specified item state has been committed without errors.
	 * 
	 * @param itemId item id
	 * @return true if specified item state has been committed without errors.
	 */
	public boolean isStateCommitted(UUID itemId) {
		if (itemId == null)
			return false;
		
		return _commitedItemStates.contains(itemId);
	}
	
	/**
	 * Return true if specified item outgoing links have been committed without errors.
	 * 
	 * @param itemId item id
	 * @return true if specified item outgoing links have been committed without errors.
	 */
	public boolean isLinksCommitted(UUID itemId) {
		if (itemId == null)
			return false;
		
		return _commitedItemLinks.contains(itemId);
	}

	/**
	 * Remove an item to list of items to commit.
	 * 
	 * @param itemId item id
	 */
	public void removeItemToCommit(UUID itemId) {
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

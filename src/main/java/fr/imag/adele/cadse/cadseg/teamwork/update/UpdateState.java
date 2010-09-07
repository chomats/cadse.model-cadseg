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
package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import fr.imag.adele.cadse.cadseg.teamwork.Errors;
import fr.imag.adele.cadse.cadseg.teamwork.commit.CommitListener;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;

/**
 * Represents state of a global update operation which can be constituted of
 * - revert operations
 * - update operations
 * - import operations
 * This state includes
 * - original requirements
 * - current status of the global update operation.
 * 
 * @author Thomas
 *
 */
public class UpdateState {
	
	private UpdateDefinition _updateDefinition = new UpdateDefinition();
	
	private LogicalWorkspaceTransaction _transaction; 
	
	private Errors _errors = new Errors();
	
	private boolean _performUpdate = false;
	
	private boolean _updatePerformed = false;
	
	private boolean _failed = false;
	
	private List<UpdateListener> _listeners = new ArrayList<UpdateListener>();
	
	/**
	 * Used to keep order or operations.
	 */
	private List<OpToPerform> _operationsList = new ArrayList<OpToPerform>();

	private Map<UUID, OpToPerform> _operations = new HashMap<UUID, OpToPerform>();
	
	public UpdateState() {
		_transaction = TWUtil.createWorkspaceTransactionForTWoperation();
	}

	/**
	 * Return definition of this global update operation.
	 * 
	 * @return definition of this global update operation.
	 */
	public UpdateDefinition getDefinition() {
		return _updateDefinition;
	}

	public static final int LAST_REV = -1;
	
	

	public void beginUpdate() {
		_performUpdate = true;
		
		// compute operation to perform
		UpdateUtil.computeOperationsToPerform(this);
		
		// notify listeners
		synchronized (_listeners) {
			for (UpdateListener listener : _listeners) {
				try {
					listener.beginUpdate();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Returns true if there is no operation to perform for this update operation.
	 * The possible causes are 
	 * - no requirements
	 * - impacts of requirements has not been computed.
	 * 
	 * @return true if there is no operation to perform for this update operation.
	 */
	public boolean hasNoOperationToPerform() {
		List<Operation> impacts = _updateDefinition.getImpacts();
		
		return ((impacts == null) || impacts.isEmpty());
	}

	/**
	 * Aborts the update operation.
	 */
	public void abortUpdate() {
		_failed = true;
		_performUpdate = false;
		_updatePerformed = true;
		
		// notify listeners
		synchronized (_listeners) {
			for (UpdateListener listener : _listeners) {
				try {
					listener.updateFail();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Marks this update operation as completed.
	 */
	public void endUpdate() {
		_failed = false;
		_performUpdate = false;
		_updatePerformed = true;
		
		// notify listeners
		synchronized (_listeners) {
			for (UpdateListener listener : _listeners) {
				try {
					listener.endUpdate();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Mark an item as in update process.
	 * 
	 * @param itemId item id
	 */
	public void beginUpdatingItem(UUID itemId) {
		// notify listeners
		synchronized (_listeners) {
			for (UpdateListener listener : _listeners) {
				try {
					listener.beginUpdatingItem(itemId);
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
	public void markStateAsUpdated(UUID itemId) {
		if (!_operations.containsKey(itemId))
			throw new IllegalArgumentException("No operation to perform for item " + itemId);
		
		OpToPerform op = _operations.get(itemId);
		op.setStateUpdated();
			
		// notify listeners
		synchronized (_listeners) {
			for (UpdateListener listener : _listeners) {
				try {
					listener.endUpdateItemState(itemId);
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Mark item outgoing links as committed.
	 * 
	 * @param itemId item id
	 */
	public void markLinksAsUpdated(UUID itemId) {
		if (!_operations.containsKey(itemId))
			throw new IllegalArgumentException("No operation to perform for item " + itemId);
		
		OpToPerform op = _operations.get(itemId);
		op.setLinksUpdated();
			
		// notify listeners
		synchronized (_listeners) {
			for (UpdateListener listener : _listeners) {
				try {
					listener.endUpdateItemLinks(itemId);
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Mark item content as updated.
	 * 
	 * @param itemId item id
	 */
	public void markContentsAsUpdated(UUID itemId) {
		if (!_operations.containsKey(itemId))
			throw new IllegalArgumentException("No operation to perform for item " + itemId);
		
		OpToPerform op = _operations.get(itemId);
		op.setContentUpdated();
			
		// notify listeners
		synchronized (_listeners) {
			for (UpdateListener listener : _listeners) {
				try {
					listener.endUpdateItemContent(itemId);
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
	public void markAsUpdated(UUID itemId) {
		checkOpExists(itemId);
		
		OpToPerform op = _operations.get(itemId);
		op.setStateUpdated();
		op.setLinksUpdated();
		op.setContentUpdated();
	}
	
	private void checkOpExists(UUID itemId) {
		if (!_operations.containsKey(itemId))
			throw new IllegalArgumentException("No operation to perform for item " + itemId);
	}

	/**
	 * Returns true if update operation is performing.
	 * 
	 * @return true if update operation is performing.
	 */
	public boolean isPerformingUpdate() {
		return _performUpdate;
	}
	
	/**
	 * Return true if update operation has failed.
	 * 
	 * @return true if update operation has failed.
	 */
	public boolean isFailed() {
		return _failed;
	}
	
	/**
	 * Return true if update operation has been performed successfully.
	 * 
	 * @return true if update operation has been performed successfully.
	 */
	public boolean isUpdatePerformed() {
		return _updatePerformed && !_failed;
	}

	/**
	 * Returns transaction in which all update operations will be performed.
	 * 
	 * @return transaction in which all update operations will be performed.
	 */
	public LogicalWorkspaceTransaction getTransaction() {
		return _transaction;
	}

	/**
	 * Returns all operations to perform for this global update operation.
	 * 
	 * @return all operations to perform for this global update operation.
	 */
	public List<OpToPerform> getOperationsToPerform() {
		return _operationsList;
	}

	/**
	 * Returns all errors which have appeared during the update operation.
	 * 
	 * @return all errors which have appeared during the update operation.
	 */
	public Errors getOperationsToPerformErrors() {
		return _errors;
	}
	
	/**
	 * Remove all errors.
	 */
	public void clearErrors() {
		_errors.clear();
	}

	/**
	 * Return all listeners interested in monitoring this update operation. 
	 * 
	 * @return all listeners interested in monitoring this update operation. 
	 */
	public List<UpdateListener> getListeners() {
		return _listeners;
	}

	/**
	 * Add a listener interested in monitoring this update operation. 
	 * 
	 * @param listener a listener
	 */
	public void addListener(UpdateListener listener) {
		synchronized (_listeners) {
			_listeners.add(listener);
		}
	}
	
	/**
	 * Remove a listener interested in monitoring this update operation. 
	 * 
	 * @param listener a listener
	 */
	public void removeListener(UpdateListener listener) {
		synchronized (_listeners) {
			_listeners.remove(listener);
		}
	}
	
	/**
	 * Add specified operation to operation to perform list.
	 * 
	 * @param op an operation
	 * @throws IllegalArgumentException if it already exists an operation for specified item.
	 */
	public void addOperationToPerform(OpToPerform op) {
		UUID itemId = op.getItemId();
		checkNoOtherOp(itemId);
		
		_operations.put(itemId, op);
		_operationsList.add(op);
	}

	private void checkNoOtherOp(UUID itemId) {
		if (_operations.containsKey(itemId))
			throw new IllegalArgumentException("Another operation to perform for item " + itemId + " already exists.");
	}

	/**
	 * Returns true if specified item has been updated without errors.
	 * 
	 * @param itemId
	 * @return true if specified item has been updated without errors.
	 */
	public boolean isUpdated(UUID itemId) {
		checkOpExists(itemId);
		
		OpToPerform op = _operations.get(itemId);
		return !getOperationsToPerformErrors().isInError(itemId) && 
			op.isStateUpdated() && op.isLinksUpdated() && op.isContentUpdated();
	}
}

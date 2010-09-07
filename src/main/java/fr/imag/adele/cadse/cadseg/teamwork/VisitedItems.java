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
package fr.imag.adele.cadse.cadseg.teamwork;

import java.util.HashSet;
import java.util.Set;

import java.util.UUID;
import fr.imag.adele.cadse.core.Item;

/**
 * Utility class to mark items with a visited flag.
 * 
 * @author Thomas
 *
 */
public class VisitedItems {

	private Set<UUID> _visited = new HashSet<UUID>();

	/**
	 * Return set of id of item which has been visited.
	 * 
	 * @return set of id of item which has been visited.
	 */
	public Set<UUID> getVisitedItemIds() {
		return _visited;
	}
	
	/**
	 * Mark specified item as visited.
	 * 
	 * @param itemId item id
	 */
	public void markAsVisited(UUID itemId) {
		_visited.add(itemId);
	}
	
	/**
	 * Mark specified item as visited.
	 * 
	 * @param item an item
	 */
	public void markAsVisited(Item item) {
		markAsVisited(item.getId());
	}
	
	/**
	 * Return true if specified item has been visited.
	 * 
	 * @param itemId item id
	 * @return true if specified item has been visited.
	 */
	public boolean hasBeenVisited(UUID itemId) {
		return _visited.contains(itemId);
	}
	
	/**
	 * Return true if specified item has been visited.
	 * 
	 * @param item an item
	 * @return true if specified item has been visited.
	 */
	public boolean hasBeenVisited(Item item) {
		return hasBeenVisited(item.getId());
	}
}

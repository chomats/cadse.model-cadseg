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
package fr.imag.adele.cadse.cadseg.teamwork.db;

import java.util.UUID;

/**
 * Represents description of an item in a teamwork repository.
 * @author Thomas
 *
 */
public class ItemInDBDesc {

	private UUID _id;
	private String _name;
	
	public ItemInDBDesc(UUID id, String name) {
		_id = id;
		_name = name;
	}

	public UUID getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ItemInDBDesc))
			return false;
		
		ItemInDBDesc item2 = (ItemInDBDesc) obj;
		return _id.equals(item2._id);
	}
	
	@Override
	public int hashCode() {
		return _id.hashCode();
	}
}

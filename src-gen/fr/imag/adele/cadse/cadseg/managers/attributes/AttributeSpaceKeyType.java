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
package fr.imag.adele.cadse.cadseg.managers.attributes;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.key.DefaultKeyDefinitionImpl;
import fr.imag.adele.cadse.core.key.DefaultKeyImpl;
import fr.imag.adele.cadse.core.key.Key;

public final class AttributeSpaceKeyType extends DefaultKeyDefinitionImpl {
	public AttributeSpaceKeyType(ItemType itemType, ItemType spaceKeyType) {
		super(itemType, spaceKeyType);
	}

	@Override
	protected Key getParentSpaceKeyFromItem(Item item) {
		Item it = null;
		it = item.getPartParent(false);
		if (it == null) {
			it = ((Item) item).getPartParent(true);
		}
		if (it == null)
			return DefaultKeyImpl.INVALID;
		
		Key key = it.getKey();
		assert key != null;
		return key;
	}
	
	@Override
	public String convertName(String name) {
		if (name == null) return null;
		return name.toUpperCase();
	}
	
	@Override
	protected String getName(Item item) {
		return convertName(super.getName(item));
	}
}

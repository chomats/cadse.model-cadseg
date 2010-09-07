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
package fr.imag.adele.cadse.cadseg.managers.dataModel;

import java.util.logging.Level;
import java.util.logging.Logger;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.key.DefaultKeyDefinitionImpl;
import fr.imag.adele.cadse.core.key.DefaultKeyImpl;
import fr.imag.adele.cadse.core.key.Key;

public final class PageSpaceKeyType extends DefaultKeyDefinitionImpl {
	public PageSpaceKeyType(ItemType type, ItemType type2) {
		super(type, type2);
	}

	@Override
	public Key computeKey(Item item) {
		Key parentKey = null;
		if (_parentKeyDefinition != null) {
			parentKey = getParentSpaceKeyFromItem(item);
		}
		if (parentKey == DefaultKeyImpl.INVALID) {
			Logger.getLogger("fr.imag.adele.cadse.key").log(Level.SEVERE, 
					"Parent key is invalide for item "+item.getType().getName() + "::"+item.getDisplayName());				
			return DefaultKeyImpl.INVALID;
		}
		return new PageSpaceKey(item, this, item.getName(), parentKey);
	}

	@Override
	public Key computeKey(Key parentKey, Object... values)
			throws CadseException {
		if (parentKey == DefaultKeyImpl.INVALID)
			return DefaultKeyImpl.INVALID;
		
		return new PageSpaceKey(null, this, (String) values[0], parentKey, ((Boolean) values[1]).booleanValue()
				);
		//parentItem.getPartParentLinkType() == CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES
	}
	
	@Override
	public void getQualifiedString(Key key, StringBuilder sb) {
		if (((PageSpaceKey) key).modificationPage) {
			sb.append("Modification ");
		} else {
			sb.append("Creation ");
		}
		sb.append(getItemType().getName()).append(" ");
		super.getQualifiedString(key, sb);
	}


}
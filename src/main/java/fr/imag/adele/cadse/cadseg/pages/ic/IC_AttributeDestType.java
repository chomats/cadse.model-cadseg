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
package fr.imag.adele.cadse.cadseg.pages.ic;

import java.util.Arrays;
import java.util.HashSet;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;

public class IC_AttributeDestType extends IC_DestTypeCombo {

	@Override
	protected Object[] filter(ItemType[] types) {
		HashSet<ItemType> s = new HashSet<ItemType>(Arrays.asList(types));
		s.remove(CadseGCST.LIST);
		s.remove(CadseGCST.GROUP_OF_ATTRIBUTES);
		s.remove(CadseGCST.LINK_TYPE);
		s.remove(CadseGCST.CONTENT_LINK_TYPE);
		return (ItemType[]) s.toArray(new ItemType[s.size()]);
	}
}

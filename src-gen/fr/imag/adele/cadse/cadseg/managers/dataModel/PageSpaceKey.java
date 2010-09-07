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

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.key.DefaultKeyImpl;
import fr.imag.adele.cadse.core.key.Key;

public final class PageSpaceKey extends DefaultKeyImpl {
	final boolean	modificationPage;

	PageSpaceKey(Item source, PageSpaceKeyType type, String name, Key parentKey) {
		super(type, parentKey, name);
		modificationPage = PageManager.isModificationPage(source);
	}

	PageSpaceKey(Item source, PageSpaceKeyType type, String name, Key parentKey, boolean b) {
		super(type, parentKey, name);
		modificationPage = b;
	}

	@Override
	protected int computeHashCode() {
		return super.computeHashCode() ^ Boolean.valueOf(modificationPage).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PageSpaceKey) {
			return super.equals((PageSpaceKey) obj) && ((PageSpaceKey) obj).modificationPage == modificationPage;
		}
		return false;
	}

}
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
package fr.imag.adele.cadse.cadseg.managers.build;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class BuilderManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class BuilderManager extends DefaultWorkspaceManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public BuilderManager() {
		super();
	}

	/**
		@generated
	*/
	@Override
	public String computeQualifiedName(Item item, String name, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			Item currentItem;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(name);
			return sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * Gets the display name.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the display name
	 * 
	 * @generated
	 */
	@Override
	public String getDisplayName(Item item) {
		try {
			Object value;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * get links 'requires' from 'Builder' to 'Builder'.
	 * 
	 * @param builder
	 *            the builder
	 * 
	 * @return the requires link
	 * 
	 * @generated
	 */
	static public List<Link> getRequiresLink(Item builder) {
        return builder.getOutgoingLinks(CadseGCST.BUILDER_lt_REQUIRES);
    }

	/**
	 * Gets the requires all.
	 * 
	 * @param builder
	 *            the builder
	 * 
	 * @return the requires all
	 * 
	 * @generated
	 */
	static public Collection<Item> getRequiresAll(Item builder) {
        return builder.getOutgoingItems(CadseGCST.BUILDER_lt_REQUIRES, false);
    }

	/**
	 * Gets the requires.
	 * 
	 * @param builder
	 *            the builder
	 * 
	 * @return the requires
	 * 
	 * @generated
	 */
	static public Collection<Item> getRequires(Item builder) {
        return builder.getOutgoingItems(CadseGCST.BUILDER_lt_REQUIRES,true);
    }

	/**
	 * Adds the requires.
	 * 
	 * @param builder
	 *            the builder
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addRequires(Item builder, Item value) throws CadseException {
        builder.addOutgoingItem(CadseGCST.BUILDER_lt_REQUIRES,value);
    }

	/**
	 * Removes the requires.
	 * 
	 * @param builder
	 *            the builder
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeRequires(Item builder, Item value) throws CadseException {
        builder.removeOutgoingItem(CadseGCST.BUILDER_lt_REQUIRES,value);
    }

	@Override
	public boolean isAbstract(Item parent, LinkType type) {
		return true;
	}

}

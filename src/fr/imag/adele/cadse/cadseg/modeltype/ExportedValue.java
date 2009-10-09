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

package fr.imag.adele.cadse.cadseg.modeltype;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.build.IBuildingContext;
import fr.imag.adele.cadse.core.build.IExportedContent;

/**
 * The Class ExportedValue.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ExportedValue implements IExportedContent {

	/** The type. */
	String			type;

	/** The value. */
	Object			value;

	/** The item. */
	Item			item;

	/** The children. */
	ExportedValue[]	children;

	/**
	 * Instantiates a new exported value.
	 * 
	 * @param type
	 *            the type
	 * @param item
	 *            the item
	 * @param value
	 *            the value
	 * @param children
	 *            the children
	 */
	public ExportedValue(String type, Item item, Object value, ExportedValue[] children) {
		super();
		this.type = type;
		this.value = value;
		this.item = item;
		this.children = children;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.build.IExportedContent#delete(fr.imag.adele.cadse.core.build.IBuildingContext)
	 */
	public void delete(IBuildingContext context) throws CadseException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.build.IExportedContent#getChildren()
	 */
	public IExportedContent[] getChildren() {
		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.build.IExportedContent#getExporterType()
	 */
	public String getExporterType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.build.IExportedContent#getItemIdentification()
	 */
	public CompactUUID getItemIdentification() {
		return item.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.build.IExportedContent#getItem()
	 */
	public Item getItem() {
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.build.IExportedContent#getItemDisplayName()
	 */
	public String getItemDisplayName() {
		return item.getType().getItemManager().getDisplayName(getItem());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.build.IExportedContent#hasChildren()
	 */
	public boolean hasChildren() {
		return children != null;
	}

	public void setLink(Link l) {
		// TODO Auto-generated method stub

	}
}

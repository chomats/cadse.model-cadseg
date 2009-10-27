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
package fr.imag.adele.cadse.cadseg.managers.ui;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.Variable;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;

/**
 * @generated
 */
public class UIListenerManager extends DefaultItemManager {

	/**
	 * @generated
	 */
	static final class PackageNameVariable extends VariableImpl {

		/**
		 * @generated
		 */
		public final static Variable	INSTANCE	= new PackageNameVariable();

		/**
		 * @generated
		 */
		public String compute(ContextVariable context, Item itemCurrent) {
			try {
				Object value;
				return context.getName(itemCurrent);
			} catch (Throwable e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	/**
	 * @generated
	 */
	static final class ClassNameVariable extends VariableImpl {

		/**
		 * @generated
		 */
		public final static Variable	INSTANCE	= new ClassNameVariable();

		/**
		 * @generated
		 */
		public String compute(ContextVariable context, Item itemCurrent) {
			try {
				Object value;
				return context.getName(itemCurrent);
			} catch (Throwable e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	/**
		@generated
	*/
	public class UIListenerContent extends JavaFileContentManager {

		/**
			@generated
		*/
		public UIListenerContent(CompactUUID id, Variable packageNameVariable, Variable classNameVariable) throws CadseException {
			super(id, packageNameVariable, classNameVariable);
		}

	}

	/**
	 * @generated
	 */
	public UIListenerManager() {
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
		@generated
	*/
	@Override
	public ContentItem createContentItem(CompactUUID id ) throws CadseException {
		UIListenerContent cm = new UIListenerContent(
			id, PackageNameVariable.INSTANCE, ClassNameVariable.INSTANCE
			);
		cm.setComposers(
		);
		cm.setExporters(
		);
		return cm;
	}

	/**
	 * get links 'fields' from 'UIListener' to 'Field'.
	 * 
	 * @generated
	 */
	static public List<Link> getFieldsLink(Item uIListener) {
        return uIListener.getOutgoingLinks(CadseGCST.UILISTENER_lt_FIELDS);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getFieldsAll(Item uIListener) {
        return uIListener.getOutgoingItems(CadseGCST.UILISTENER_lt_FIELDS, false);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getFields(Item uIListener) {
        return uIListener.getOutgoingItems(CadseGCST.UILISTENER_lt_FIELDS,true);
    }

	/**
	 * @generated
	 */
	static public void addFields(Item uIListener, Item value) throws CadseException {
        uIListener.addOutgoingItem(CadseGCST.UILISTENER_lt_FIELDS,value);
    }

	/**
	 * @generated
	 */
	static public void removeFields(Item uIListener, Item value) throws CadseException {
        uIListener.removeOutgoingItem(CadseGCST.UILISTENER_lt_FIELDS,value);
    }

}

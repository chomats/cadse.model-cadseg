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
package fr.imag.adele.cadse.cadseg.managers.dataModel;

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
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.util.Convert;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.Variable;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;

/**
 * @generated
 */
public class PageListenerManager extends DefaultItemManager {

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
	public class PageListenerContent extends JavaFileContentManager {

		/**
			@generated
		*/
		public PageListenerContent(CompactUUID id, Variable packageNameVariable, Variable classNameVariable) throws CadseException {
			super(id, packageNameVariable, classNameVariable);
		}

	}

	/**
	 * @generated
	 */
	public PageListenerManager() {
		super();
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
		PageListenerContent cm = new PageListenerContent(
			id, PackageNameVariable.INSTANCE, ClassNameVariable.INSTANCE
			);
		cm.setComposers(
		);
		cm.setExporters(
		);
		return cm;
	}

	/**
	 * get links 'fields' from 'PageListener' to 'Field'.
	 * 
	 * @generated
	 */
	static public List<Link> getFieldsLink(Item pageListener) {
        return pageListener.getOutgoingLinks(CadseGCST.PAGE_LISTENER_lt_FIELDS);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getFieldsAll(Item pageListener) {
        return pageListener.getOutgoingItems(CadseGCST.PAGE_LISTENER_lt_FIELDS, false);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getFields(Item pageListener) {
        return pageListener.getOutgoingItems(CadseGCST.PAGE_LISTENER_lt_FIELDS,true);
    }

	/**
	 * @generated
	 */
	static public void addFields(Item pageListener, Item value) throws CadseException {
        pageListener.addOutgoingItem(CadseGCST.PAGE_LISTENER_lt_FIELDS,value);
    }

	/**
	 * @generated
	 */
	static public void removeFields(Item pageListener, Item value) throws CadseException {
        pageListener.removeOutgoingItem(CadseGCST.PAGE_LISTENER_lt_FIELDS,value);
    }

	/**
	 * @generated
	 */
	public static final boolean isListenShortNameAttribute(Item pageListener) {
		return pageListener.getAttributeWithDefaultValue(CadseGCST.PAGE_LISTENER_at_LISTEN_SHORT_NAME_, false);
	}

	/**
	 * @generated
	 */
	public static final void setListenShortNameAttribute(Item pageListener, boolean value) {
		try {
			pageListener.setAttribute(CadseGCST.PAGE_LISTENER_at_LISTEN_SHORT_NAME_, value);
		} catch (Throwable t) {

		}
	}

}

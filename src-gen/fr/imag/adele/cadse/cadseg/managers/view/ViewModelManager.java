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

package fr.imag.adele.cadse.cadseg.managers.view;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class ViewModelManager.
 * 
 * @generated
 */
public class ViewModelManager extends DefaultItemManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ViewModelManager() {
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
		get  links 'views' from 'ViewModel' to 'View'.
        @generated
    */
    static public List<Link> getViewsLink(Item viewModel) {
        return viewModel.getOutgoingLinks(CadseGCST.VIEW_MODEL_lt_VIEWS);
    }

	/**
        @generated
    */
    static public Collection<Item> getViewsAll(Item viewModel) {
        return viewModel.getOutgoingItems(CadseGCST.VIEW_MODEL_lt_VIEWS, false);
    }
    
	/**
	 * Gets the views.
	 * 
	 * @param viewModel
	 *            the view model
	 * 
	 * @return the views
	 * 
	 * @generated
	 */
	static public Collection<Item> getViews(Item viewModel) {
        return viewModel.getOutgoingItems(CadseGCST.VIEW_MODEL_lt_VIEWS,true);
    }

	/**
	 * Adds the views.
	 * 
	 * @param viewModel
	 *            the view model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addViews(Item viewModel, Item value) throws CadseException {
        viewModel.addOutgoingItem(CadseGCST.VIEW_MODEL_lt_VIEWS,value);
    }

	/**
	 * Removes the views.
	 * 
	 * @param viewModel
	 *            the view model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeViews(Item viewModel, Item value) throws CadseException {
        viewModel.removeOutgoingItem(CadseGCST.VIEW_MODEL_lt_VIEWS,value);
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return DefaultItemManager.CANNOT_RENAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canDeleteItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canDeleteItem(Item item) {
		Item parent = item.getPartParent();
		if (parent == null) {
			return null;
		}

		return DefaultItemManager.CANNOT_DELETE;
	}

}

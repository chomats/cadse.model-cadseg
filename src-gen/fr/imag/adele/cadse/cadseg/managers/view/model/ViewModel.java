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

package fr.imag.adele.cadse.cadseg.managers.view.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewLinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class ViewModel.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ViewModel {
	/*
	 * name="%name.view.workspace-view" icon="icons/sample.gif"
	 * category="fede.workspace.tool.view"
	 * class="fede.workspace.tool.view.WSView"
	 * id="fede.workspace.tool.view.WSView">
	 */
	/** The id. */
	public String					id;

	/** The name. */
	public String					name;

	/** The icon. */
	public String					icon;

	/** The category. */
	public String					category;

	/** The package name. */
	public String					packageName;

	/** The class name. */
	public String					className;

	/** The qualified class name. */
	public String					qualifiedClassName;

	/** The first items. */
	private List<ViewItemTypeModel>	firstItems;

	/** The all items. */
	private List<ViewItemTypeModel>	allItems;

	/** The imports array. */
	public Set<String>				importsArray;

	/** The super class name. */
	public String					superClassName;

	/** The root_cst. */
	public String[]					root_cst;

	/**
	 * Instantiates a new view model.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param view
	 *            the view
	 */
	public ViewModel(ContextVariable cxt, Item view) {
		this.className = ViewManager.getClassName(cxt, view);
		this.packageName = ViewManager.getPackage(cxt, view);
		this.qualifiedClassName = packageName + "." + className;
		this.id = view.getQualifiedName();
		this.name = view.getName();
		this.icon = ViewManager.getIconPath(view);

		// Item manager = getItem();
		importsArray = new TreeSet<String>();

		allItems = new ArrayList<ViewItemTypeModel>();
		firstItems = new ArrayList<ViewItemTypeModel>();

		List<String> root_cst = new ArrayList<String>();
		for (Item vit : ViewManager.getViewItemTypes(view)) {
			ViewItemTypeModel vitm = new ViewItemTypeModel();
			vitm.vit = vit;
			vitm.itemType = ViewItemTypeManager.getItemType(vit);
			if (vitm.itemType == null) {
				continue;
			}
			vitm.m = ManagerManager.getManagerFromItemType(vitm.itemType);
			if (vitm.m == null) {
				continue;
			}

			vitm.isFirst = ViewItemTypeManager.isIsFirstElementAttribute(vit);
			vitm.isRef = ViewItemTypeManager.isRefAttribute(vit);
			// vitm.classname = ManagerManager.getManagerClassName(vitm.m);
			// vitm.packagename = ManagerManager.getPackage(vitm.m);
			// vitm.ID_cst=
			// JavaIdentifier.javaIdentifierFromStringUPPER(vitm.itemType.getShortName(),null);
			vitm.REF_cst = GenerateJavaIdentifier.cstQualifiedAttributeItemType(cxt, vitm.itemType, null,
					this.importsArray);
			if (vitm.isFirst) {
				root_cst.add(vitm.REF_cst);
			}
			addViewItemModel(vitm);

			vitm.links = new ArrayList<ViewLinkTypeModel>();
			for (Item vlt : ViewItemTypeManager.getViewLinkTypes(vit)) {
				ViewLinkTypeModel vltm = new ViewLinkTypeModel();
				vltm.vlt = vlt;
				vltm.lt = ViewLinkTypeManager.getLinkType(vlt);
				if (vltm.lt == null) {
					continue;
				}

				vltm.aggregation = ViewLinkTypeManager.isAggregationAttribute(vlt);
				vltm.canCreateItem = ViewLinkTypeManager.isCanCreateItemAttribute(vlt);
				vltm.canCreateLink = ViewLinkTypeManager.isCanCreateLinkAttribute(vlt);
				vltm.displayCreate = ViewLinkTypeManager.getDisplayCreateAttribute(vlt);
				if (vltm.displayCreate != null && vltm.displayCreate.length() == 0) {
					vltm.displayCreate = null;
				}

				// vltm.ID_cst =
				// JavaIdentifier.javaIdentifierFromStringUPPER(vltm.lt.getShortName(),null);
				vltm.REF_cst = GenerateJavaIdentifier.cstQualifiedAttribute(cxt, vltm.lt, vitm.itemType, null,
						this.importsArray);
				vitm.links.add(vltm);
			}
			Item superit = ItemTypeManager.getSuperType(vitm.itemType);
			while (superit != null) {
				Item supervit = ViewManager.getViewItemType(view, superit);
				if (supervit != null) {
					for (Item vlt : ViewItemTypeManager.getViewLinkTypes(supervit)) {
						ViewLinkTypeModel vltm = new ViewLinkTypeModel();
						vltm.vlt = vlt;
						vltm.lt = ViewLinkTypeManager.getLinkType(vlt);
						if (vltm.lt == null) {
							continue;
						}

						vltm.aggregation = ViewLinkTypeManager.isAggregationAttribute(vlt);
						vltm.canCreateItem = ViewLinkTypeManager.isCanCreateItemAttribute(vlt);
						vltm.canCreateLink = ViewLinkTypeManager.isCanCreateLinkAttribute(vlt);
						vltm.displayCreate = ViewLinkTypeManager.getDisplayCreateAttribute(vlt);
						if (vltm.displayCreate != null && vltm.displayCreate.length() == 0) {
							vltm.displayCreate = null;
						}

						// vltm.ID_cst =
						// JavaIdentifier.javaIdentifierFromStringUPPER(vltm.lt.getShortName(),null);
						vltm.REF_cst = GenerateJavaIdentifier.cstQualifiedAttribute(cxt, vltm.lt, superit, null,
								this.importsArray);
						vitm.links.add(vltm);
					}
				}
				superit = ItemTypeManager.getSuperType(superit);
			}

		}

		this.root_cst = root_cst.toArray(new String[root_cst.size()]);
		this.superClassName = "AbstractCadseView";
		this.importsArray.add("fr.imag.adele.cadse.eclipse.view.AbstractCadseView");
		this.importsArray.add("org.eclipse.ui.IViewSite");
		this.importsArray.add("fr.imag.adele.cadse.core.*");
		this.importsArray.add("fr.imag.adele.cadse.eclipse.view.AbstractCadseTreeViewUI");
		this.importsArray.add("java.util.HashSet");
		this.importsArray.add("java.util.Arrays");
		this.importsArray.add("java.util.HashMap");

	}

	/**
	 * Adds the view item model.
	 * 
	 * @param itemModel
	 *            the item model
	 */
	void addViewItemModel(ViewItemTypeModel itemModel) {
		if (itemModel.isFirst) {
			firstItems.add(itemModel);
		}
		allItems.add(itemModel);
	}

	/**
	 * Gets the all items.
	 * 
	 * @return the all items
	 */
	public List<ViewItemTypeModel> getAllItems() {
		return allItems;
	}

	/**
	 * Gets the first items.
	 * 
	 * @return the first items
	 */
	public List<ViewItemTypeModel> getFirstItems() {
		return firstItems;
	}

}

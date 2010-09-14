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
package fr.imag.adele.cadse.cadseg.managers.view;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.Menu;
import fr.imag.adele.cadse.core.Separator;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class ViewManager.
 * 
 * @generated
 */
public class ViewManager extends DefaultItemManager {

	/**
	 * Gets the package.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param view
	 *            the view
	 * 
	 * @return the package
	 */
	public static String getPackage(ContextVariable cxt, Item view) {
		Item cadsegModel = getCadsegModel(view);
		String packageName = CadseDefinitionManager.getDefaultPackage(cxt, cadsegModel) + ".views";

		packageName += "." + JavaIdentifier.javaIdentifierFromString(cxt.getName(view), false, true, null);

		return packageName;
	}

	/**
	 * Gets the cadseg model.
	 * 
	 * @param view
	 *            the view
	 * 
	 * @return the cadseg model
	 */
	public static Item getCadsegModel(Item view) {
		if (view == null) {
			return null;
		}
		Item parent1 = view.getPartParent();
		if (parent1 == null) {
			return null;
		}
		return parent1.getPartParent();
	}

	/**
	 * Gets the class name.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param view
	 *            the view
	 * 
	 * @return the class name
	 */
	public static String getClassName(ContextVariable cxt, Item view) {
		return JavaIdentifier.javaIdentifierFromString(cxt.getName(view), true, false, "View");
	}

	/**
	 * The Class ViewJavaFileContentManager.
	 */
	private final static class ViewJavaFileContentManager extends JavaFileContentManager {

		/**
		 * Instantiates a new view java file content manager.
		 * 
		 * @param workspaceModel
		 *            the workspace model
		 * @param view
		 *            the view
		 * 
		 * @throws CadseException
		 *             the melusine exception
		 */
		private ViewJavaFileContentManager(UUID id) throws CadseException {
			super(id, new VariableImpl() {
				public String compute(ContextVariable context, Item itemCurrent) {
					return ViewManager.getPackage(context, itemCurrent);
				}
			}, new VariableImpl() {
				public String compute(ContextVariable context, Item itemCurrent) {
					// TODO Auto-generated method stub
					return ViewManager.getClassName(context, itemCurrent);
				}
			});
		}


	}


	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ViewManager() {
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

	// /**
	// * Creates the creation page creation page1.
	// *
	// * @return the i page
	// *
	// * @generated
	// */
	// protected IPage createCreationPageCreationPage1() {
	// return FieldsCore.createPage("creation-page1","Create View","",3,
	// FieldsCore.createShortNameField()
	// );
	// }
	//
	// /**
	// * Creates the creation pages.
	// *
	// * @param theItemParent
	// * the the item parent
	// * @param theLinkType
	// * the the link type
	// * @param desType
	// * the des type
	// *
	// * @return the pages
	// *
	// * @generated
	// */
	// @Override
	// public Pages createCreationPages(Item theItemParent, LinkType
	// theLinkType, ItemType desType) {
	//
	// CreationAction action = new CreationAction(theItemParent, desType,
	// theLinkType);
	//
	// return FieldsCore.createWizard(action,
	// createCreationPageCreationPage1()
	// );
	// }

	/**
		get  links 'view-item-types' from 'View' to 'ViewItemType'.
        @generated
    */
    static public List<Link> getViewItemTypesLink(Item view) {
        return view.getOutgoingLinks(CadseGCST.VIEW_lt_VIEW_ITEM_TYPES);
    }

	/**
        @generated
    */
    static public Collection<Item> getViewItemTypesAll(Item view) {
        return view.getOutgoingItems(CadseGCST.VIEW_lt_VIEW_ITEM_TYPES, false);
    }


	/**
	 * Gets the icon path.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the icon path
	 */
	public static String getIconPath(Item item) {
		String pStr = (String) item.getAttribute(CadseGCST.VIEW_at_ICON_);
		if (pStr == null || pStr.length() == 0) {
			return null;
		}

		IPath p = new Path(pStr);
		return p.removeFirstSegments(1).makeRelative().toPortableString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#contributeMenuNewAction(fr.imag.adele.cadse.core.IMenuAction.Menu,
	 *      fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public void contributeMenuNewAction(Menu menu, Item view) {
		Item cadsegModel = getCadsegModel(view);
		Item dataModel = CadseDefinitionManager.getMainDataModel(cadsegModel);
		Item[] alldataModel = ItemTypeManager.getAllDataModel(dataModel, true);
		for (Item acat : alldataModel) {
			menu.insert(null, new DataModelViewAction(this, view, acat), true);
		}
		menu.insert(null, new Separator(), true);
		Item[] itemtypes = ItemTypeManager.getAllItemType(dataModel);

		for (Item itype : itemtypes) {
			Item viewitemtype = getViewItemType(view, itype);
			if (viewitemtype == null) {
				menu.insert(null, new ItemTypeViewAction(this, view, itype), true);
			}
		}

	}

	/**
	 * Gets the view item type.
	 * 
	 * @param view
	 *            the view
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the view item type
	 */
	public static Item getViewItemType(Item view, Item itemtype) {
		try {
			Item viewitemtype = CadseCore.getItem(null, itemtype.getName(), CadseGCST.VIEW_ITEM_TYPE, view,
					CadseGCST.VIEW_lt_VIEW_ITEM_TYPES);
			return viewitemtype;
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Creates the view item type.
	 * 
	 * @param view
	 *            the view
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the item
	 */
	public static Item createViewItemType(Item view, Item itemtype) {
		try {
			Item viewitemtype = CadseCore
					.createItemIfNeed(null, itemtype.getName(), CadseGCST.VIEW_ITEM_TYPE, view,
							CadseGCST.VIEW_lt_VIEW_ITEM_TYPES,

							CadseGCST.VIEW_ITEM_TYPE_lt_ITEM_TYPE, itemtype,
							CadseGCST.VIEW_ITEM_TYPE_at_IS_ROOT_ELEMENT_, ItemTypeManager
									.isIsRootElementAttribute(itemtype));
			return viewitemtype;
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Creates the view link type.
	 * 
	 * @param view
	 *            the view
	 * @param link
	 *            the link
	 * 
	 * @return the item
	 */
	public static Item createViewLinkType(Item view, Item link) {
		Item viewitemtype = createViewItemType(view, link.getPartParent());
		if (viewitemtype == null) {
			return null;
		}
		return createViewLinkType2(viewitemtype, link);

	}

	/**
	 * Creates the view link type2.
	 * 
	 * @param viewitemtype
	 *            the viewitemtype
	 * @param link
	 *            the link
	 * 
	 * @return the item
	 */
	static Item createViewLinkType2(Item viewitemtype, Item link) {
		try {
			Item viewlinktype = CadseCore.createItemIfNeed(null, link.getName(), CadseGCST.VIEW_LINK_TYPE,
					viewitemtype, CadseGCST.VIEW_ITEM_TYPE_lt_VIEW_LINK_TYPES,
					CadseGCST.VIEW_LINK_TYPE_lt_LINK_TYPE, link,

					CadseGCST.VIEW_LINK_TYPE_at_AGGREGATION_, LinkTypeManager.isAggregation(link),
					CadseGCST.VIEW_LINK_TYPE_at_CAN_CREATE_ITEM_, LinkTypeManager.isAggregation(link),
					CadseGCST.VIEW_LINK_TYPE_at_CAN_CREATE_LINK_, true);
			return viewlinktype;
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets the view link type.
	 * 
	 * @param viewitemtype
	 *            the viewitemtype
	 * @param link
	 *            the link
	 * 
	 * @return the view link type
	 */
	public static Item getViewLinkType(Item viewitemtype, Item link) {
		try {
			Item viewlinktype = CadseCore.getItem(null, link.getName(), CadseGCST.VIEW_LINK_TYPE, viewitemtype,
					CadseGCST.VIEW_ITEM_TYPE_lt_VIEW_LINK_TYPES);
			return viewlinktype;
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets the view item types.
	 * 
	 * @param view
	 *            the view
	 * 
	 * @return the view item types
	 * 
	 * @generated
	 */
	static public Collection<Item> getViewItemTypes(Item view) {
        return view.getOutgoingItems(CadseGCST.VIEW_lt_VIEW_ITEM_TYPES,true);
    }

	/**
        @generated
    */
    static public void addViewItemTypes(Item view, Item value) throws CadseException {
        view.addOutgoingItem(CadseGCST.VIEW_lt_VIEW_ITEM_TYPES,value);
    }

	/**
        @generated
    */
    static public void removeViewItemTypes(Item view, Item value) throws CadseException {
        view.removeOutgoingItem(CadseGCST.VIEW_lt_VIEW_ITEM_TYPES,value);
    }

	/**
		@generated
	*/
	public static final String getIconAttribute(Item view) {
		return view.getAttributeWithDefaultValue(CadseGCST.VIEW_at_ICON_, null);
	}

	/**
		@generated
	*/
	public static final void setIconAttribute(Item view, String value) {
		try {
			view.setAttribute(CadseGCST.VIEW_at_ICON_, value);
		} catch (Throwable t) {

		}
	}
}

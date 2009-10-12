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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generate.GeneratePageActionClass;
import fr.imag.adele.cadse.cadseg.generate.GeneratePageClass2;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldGenerateInfo;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.key.AbstractSpaceKey;
import fr.imag.adele.cadse.core.key.ISpaceKey;
import fr.imag.adele.cadse.core.key.SpaceKey;
import fr.imag.adele.cadse.core.key.SpaceKeyType;
import fr.imag.adele.cadse.core.util.Convert;
import java.lang.String;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class PageManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class PageManager extends DefaultItemManager implements IItemManager {
	

	/**
	 * Gets the uUID.
	 * 
	 * @param page
	 *            the itemtype
	 * 
	 * @return the uUID
	 */
	public static CompactUUID getIdRuntime(Item page) {
		String uuid_str = page.getAttribute(CadseGCST.PAGE_at_ID_RUNTIME_);
		if (uuid_str == null || uuid_str.length() == 0) {
			CompactUUID uuid = CompactUUID.randomUUID();
			uuid_str = uuid.toString();
			try {
				page.setAttribute(CadseGCST.PAGE_at_ID_RUNTIME_, uuid_str);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return uuid;
		}
		return new CompactUUID(uuid_str);

	}

	static private final class PageSpaceKeyType extends SpaceKeyType {
		private PageSpaceKeyType(ItemType type, ItemType type2) {
			super(type, type2);
		}

		@Override
		public ISpaceKey computeKey(Item item) {
			ISpaceKey parentKey = null;
			if (parentSpaceKeyType != null) {
				parentKey = getParentSpaceKeyFromItem(item);
			}
			if (parentKey == AbstractSpaceKey.INVALID) {
				Logger.getLogger("fr.imag.adele.cadse.key").log(Level.SEVERE, 
						"Parent key is invalide for item "+item.getType().getName() + "::"+item.getDisplayName());				
				return AbstractSpaceKey.INVALID;
			}
			return new PageSpaceKey(item, this, item.getName(), parentKey);
		}

		@Override
		public SpaceKey computeKey(String name, Item parentItem, Object... key_attributes) {
			ISpaceKey parentKey = null;
			if (parentSpaceKeyType != null) {
				parentKey = parentItem != null ? parentItem.getKey() : AbstractSpaceKey.INVALID;
			}
			return new PageSpaceKey(null, this, name, parentKey,
					parentItem.getType() == CadseGCST.MODIFICATION_DIALOG);
		}

		@Override
		public String getQualifiedString(AbstractSpaceKey key) {
			StringBuilder sb = new StringBuilder();
			if (((PageSpaceKey) key).modificationPage) {
				sb.append("Modification ");
			} else {
				sb.append("Creation ");
			}
			sb.append(getItemType().getName()).append(" ");
			getQualifiedString(key, sb);
			return sb.toString();
		}

	}

	static private final class PageSpaceKey extends SpaceKey {
		final boolean	modificationPage;

		PageSpaceKey(Item source, SpaceKeyType type, String name, ISpaceKey parentKey) {
			super(source, type, name, parentKey);
			modificationPage = PageManager.isModificationPage(source);
		}

		PageSpaceKey(Item source, PageSpaceKeyType type, String name, ISpaceKey parentKey, boolean b) {
			super(source, type, name, parentKey);
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

	/**
	 * The Class PageContentManager.
	 */
	public final class PageContentManager extends JavaFileContentManager implements IPDEContributor, IGenerateContent {

		/**
		 * Instantiates a new page content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		private PageContentManager(CompactUUID id) {
			super(id, new VariableImpl() {
				public String compute(ContextVariable context, Item itemCurrent) {
					return GenerateJavaIdentifier.javaPackagePageFactoryFromPage(context, itemCurrent);
				}
			}, new VariableImpl() {
				public String compute(ContextVariable context, Item itemCurrent) {
					return GenerateJavaIdentifier.javaClassNamePageFactoryFromPage(context, itemCurrent);
				}
			});
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeExportsPackage(java.util.Set)
		 */
		public void computeExportsPackage(Set<String> exports) {
			exports.add(getPackageName(ContextVariable.DEFAULT));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeImportsPackage(java.util.Set)
		 */
		public void computeImportsPackage(Set<String> imports) {
			imports.add("fede.workspace.model.manager.properties");
			imports.add("fr.imag.adele.cadse.core.ui");
			imports.add("fr.imag.adele.cadse.core.impl.ui");
			imports.add("fede.workspace.model.manager.properties.impl.ui");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fr.imag.adele.cadse.core.ContentManager#generate(fr.imag.adele.cadse
		 * .core.GenStringBuilder, java.lang.String, java.lang.String,
		 * java.util.Set, fr.imag.adele.cadse.core.GenContext)
		 */
		@Override
		public void generate(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {

			generate();
		}

		/**
		 * Generate.
		 */
		public void generate() {
			GeneratePageClass2.generate(ContextVariable.DEFAULT, this, getOwnerItem());
			if (hasPageAction(getOwnerItem())) {
				GeneratePageActionClass.generate(ContextVariable.DEFAULT, getOwnerItem());
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion
		 * (org.eclipse.pde.core.plugin.IPluginBase,
		 * org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
		 */
		public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
		}

		public void generate(ContextVariable cxt) {
			generate();
		}

		public GenerateModel getGenerateModel() {
			return null;
		}
	}

	/**
	 * Instantiates a new page manager.
	 */
	public PageManager() {
	}

	public static boolean _addInternalShortName(Item page) {
		Item dialog = page.getPartParent();
		if (dialog == null) {
			return false;
		}

		if (isModificationPage(page)) {
			return false;
		}

		if (CreationDialogManager.isAutomaticShortNameAttribute(dialog)) {
			return false;
		}
		if (CreationDialogManager.getDefaultShortNameAttribute(dialog) != null) {
			return false;
		}

		Item absItemType = dialog.getPartParent();
		if (absItemType == null) {
			return false;
		}
		if (absItemType.getType() == CadseGCST.EXT_ITEM_TYPE) {
			return false;
		}
		return true;
	}

	/**
	 * est-ce qu'il faut ajouter un le field short name dans la page.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return true, if adds the internal short name
	 */
	public static boolean addInternalShortName(Item page) {
		Item dialog = page.getPartParent();
		if (dialog == null) {
			return false;
		}

		if (isModificationPage(page)) {
			return false;
		}

		if (CreationDialogManager.isAutomaticShortNameAttribute(dialog)) {
			return false;
		}
		if (CreationDialogManager.getDefaultShortNameAttribute(dialog) != null) {
			return false;
		}

		Item absItemType = dialog.getPartParent();
		if (absItemType == null) {
			return false;
		}
		if (absItemType.getType() == CadseGCST.EXT_ITEM_TYPE) {
			return false;
		}

		//
		Item[] allSuperTypes = ItemTypeManager.getAllSuperTypes(absItemType);
		if (allSuperTypes.length != 0) {
			for (int i = 0; i < allSuperTypes.length; i++) {
				Item superTypeBase = allSuperTypes[i];
				Item creationDialog = ItemTypeManager.getCreationDialog(superTypeBase);
				if (creationDialog == null) {
					continue;
				}
				Collection<Item> pages = CreationDialogManager.getPages(creationDialog);
				// recherche le premier super type qui a une page de creation
				if (pages.size() != 0) {
					// la première page
					// est-ce qu'elle a un name
					Item p = pages.iterator().next();
					return _addInternalShortName(p) && p.getName().equals(page.getName());
				}
			}
		}
		List<? extends Link> links = dialog.getOutgoingLinks();
		for (Link l : links) {
			if (l.getResolvedDestination() == page) {
				return l.getIndex() == 0;
			}
		}

		return false;
	}

	/**
	 * Adds the internal attribute.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return true, if successful
	 */
	public static boolean addInternalAttribute(Item item) {
		Item dialog = item.getPartParent();
		if (dialog == null) {
			return false;
		}

		Item absItemType = dialog.getPartParent();
		if (absItemType == null) {
			return false;
		}
		if (absItemType.getType() == CadseGCST.EXT_ITEM_TYPE) {
			return false;
		}

		if (dialog.getType() != CadseGCST.MODIFICATION_DIALOG) {
			return false;
		}

		if (!ModificationDialogManager.isShowInternalAttribute(dialog)) {
			return false;
		}

		List<? extends Link> links = dialog.getOutgoingLinks();
		for (Link l : links) {
			if (l.getResolvedDestination() == item) {
				return l.getIndex() == 0;
			}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#init(fr.imag.adele.cadse
	 * .core.ItemType)
	 */
	@Override
	public void init() {
		CadseGCST.PAGE.setHasUniqueNameAttribute(false);
		CadseGCST.PAGE.setSpaceKeyType(new PageSpaceKeyType(CadseGCST.PAGE, CadseGCST.ABSTRACT_ITEM_TYPE));
	}

	/**
	 * Checks for page action.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return true, if successful
	 */
	public static boolean hasPageAction(Item item) {
		return Convert.toBooleanFalseIfNull(item.getAttribute(CadseGCST.PAGE_at_CREATE_PAGE_ACTION_));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#createContentManager(
	 * fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentItem(CompactUUID id) {
		return new PageContentManager(id);
	}
	
	@Override
	public boolean hasContent(Item item) {
		if (item.getPartParent(CadseGCST.CADSE_DEFINITION) == null) 
			return false;
		return super.hasContent(item);
	}
	
	@Override
	public ContentItem getParentContentItemWherePutMyContent(ContentItem cm) {
		Item cadsedef = cm.getOwnerItem().getPartParent(CadseGCST.CADSE_DEFINITION);
		if (cadsedef == null) {
			return null; // Cannot found the cadse definition
		}
		return cadsedef.getContentItem();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#validateShortName(fr.
	 * imag.adele.cadse.core.Item, java.lang.String)
	 */
	@Override
	public String validateShortName(Item futurItem, String shortName) {
		try {
			JavaIdentifier.javaIdentifierFromString(shortName, true, false, null);
		} catch (Throwable e) {
			return "must be to transform to java identifier : space and - acceppter";
		}
		return null;
	}

	/**
	 * Return the itemtype or ExtendsItemType from a page.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return the item type from page or null if error
	 */
	public static Item getItemTypeFromPage(Item page) {
		Item dialog = page.getPartParent();
		if (dialog == null) {
			return null;
		}
		return dialog.getPartParent();
	}

	/**
	 * Gets the fields.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return the fields
	 */
	public Collection<Item> getFields(Item page) {
		return page.getOutgoingItems(CadseGCST.PAGE_lt_FIELDS, true);
	}

	/**
	 * Gets the field generate infos.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param page
	 *            the page
	 * @param imports
	 *            the imports
	 * 
	 * @return the field generate infos
	 */
	public static Collection<FieldGenerateInfo> getFieldGenerateInfos(ContextVariable cxt, Item page,
			Set<String> imports, Item superpage) {
		ArrayList<FieldGenerateInfo> ret = new ArrayList<FieldGenerateInfo>();
		HashMap<String, Item> supersField = null;
		if (superpage != null) {
			ArrayList<Item> superpages = new ArrayList<Item>();
			superpages.add(superpage);
			PageManager.getSuperPages(superpage, superpages);

			supersField = new HashMap<String, Item>();
			for (Item p : superpages) {
				Collection<Item> fields = PageManager.getFieldsAll(p);
				for (Item f : fields) {
					if (!supersField.containsKey(f.getName())) {
						supersField.put(f.getName(), f);
					}
				}
			}
		}

		for (Link l : page.getOutgoingLinks()) {
			// Select link has kind Part and destination.
			if (l.getLinkType() == CadseGCST.PAGE_lt_FIELDS) {
				if (l.isLinkResolved()) {
					Item field = l.getDestination();
					Item superField = supersField == null ? null : supersField.get(field.getName());
					FieldGenerateInfo fieldGenerateInfo = FieldManager.getFieldGenerateInfo(cxt, field, superField,
							imports);
					if (fieldGenerateInfo != null) {
						ret.add(fieldGenerateInfo);
					}
				}
			}
		}

		return ret;

	}

	/**
	 * Adds the fields.
	 * 
	 * @param page
	 *            the page
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addFields(Item page, Item value) throws CadseException {
        page.addOutgoingItem(CadseGCST.PAGE_lt_FIELDS,value);
    }

	/**
	 * Removes the fields.
	 * 
	 * @param page
	 *            the page
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeFields(Item page, Item value) throws CadseException {
        page.removeOutgoingItem(CadseGCST.PAGE_lt_FIELDS,value);
    }

	/**
	 * Gets the title attribute.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return the title attribute
	 * 
	 * @generated
	 */
	public static final String getTitleAttribute(Item page) {
		return page.getAttributeWithDefaultValue(CadseGCST.PAGE_at_TITLE_, null);
	}

	/**
	 * Sets the title attribute.
	 * 
	 * @param page
	 *            the page
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setTitleAttribute(Item page, String value) {
		try {
			page.setAttribute(CadseGCST.PAGE_at_TITLE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the description attribute.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return the description attribute
	 * 
	 * @generated
	 */
	public static final String getDescriptionAttribute(Item page) {
		return page.getAttributeWithDefaultValue(CadseGCST.PAGE_at_DESCRIPTION_, null);
	}

	/**
	 * Sets the description attribute.
	 * 
	 * @param page
	 *            the page
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setDescriptionAttribute(Item page, String value) {
		try {
			page.setAttribute(CadseGCST.PAGE_at_DESCRIPTION_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the hspan attribute.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return the hspan attribute
	 * 
	 * @generated
	 */
	public static final int getHspanAttribute(Item page) {
		return page.getAttributeWithDefaultValue(CadseGCST.PAGE_at_HSPAN_, 3);
	}

	/**
	 * Sets the hspan attribute.
	 * 
	 * @param page
	 *            the page
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setHspanAttribute(Item page, int value) {
		try {
			page.setAttribute(CadseGCST.PAGE_at_HSPAN_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is create page action attribute.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return true, if checks if is create page action attribute
	 * 
	 * @generated
	 */
	public static final boolean isCreatePageActionAttribute(Item page) {
		return page.getAttributeWithDefaultValue(CadseGCST.PAGE_at_CREATE_PAGE_ACTION_, false);
	}

	/**
	 * Sets the create page action attribute.
	 * 
	 * @param page
	 *            the page
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setCreatePageActionAttribute(Item page, boolean value) {
		try {
			page.setAttribute(CadseGCST.PAGE_at_CREATE_PAGE_ACTION_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * get links 'listeners' from 'Page' to 'UIListener'.
	 * 
	 * @generated
	 */
	static public List<Link> getListenersLink(Item page) {
        return page.getOutgoingLinks(CadseGCST.PAGE_lt_LISTENERS);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getListenersAll(Item page) {
        return page.getOutgoingItems(CadseGCST.PAGE_lt_LISTENERS, false);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getListeners(Item page) {
        return page.getOutgoingItems(CadseGCST.PAGE_lt_LISTENERS,true);
    }

	/**
	 * @generated
	 */
	static public void addListeners(Item page, Item value) throws CadseException {
        page.addOutgoingItem(CadseGCST.PAGE_lt_LISTENERS,value);
    }

	/**
	 * @generated
	 */
	static public void removeListeners(Item page, Item value) throws CadseException {
        page.removeOutgoingItem(CadseGCST.PAGE_lt_LISTENERS,value);
    }

	/**
	 * get a link 'super' from 'Page' to 'Page'.
	 * 
	 * @generated
	 */
	static public Link getSuperLink(Item page) {
		return page.getOutgoingLink(CadseGCST.PAGE_lt_SUPER);
	}

	/**
	 * get all link destination 'super' from 'Page' to 'Page'.
	 * 
	 * @generated
	 */
	static public Item getSuperAll(Item page) {
		return page.getOutgoingItem(CadseGCST.PAGE_lt_SUPER, false);
	}

	/**
	 * get resolved link destination 'super' from 'Page' to 'Page'.
	 * 
	 * @generated
	 */
	static public Item getSuper(Item page) {
		return page.getOutgoingItem(CadseGCST.PAGE_lt_SUPER, true);
	}

	/**
	 * set a link 'super' from 'Page' to 'Page'.
	 * 
	 * @generated
	 */
	static public void setSuper(Item page, Item value) throws CadseException {
		page.setOutgoingItem(CadseGCST.PAGE_lt_SUPER,value);
	}

	/**
	 * get links 'deleted-fields' from 'Page' to 'Field'.
	 * 
	 * @generated
	 */
	static public List<Link> getDeletedFieldsLink(Item page) {
        return page.getOutgoingLinks(CadseGCST.PAGE_lt_DELETED_FIELDS);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getDeletedFieldsAll(Item page) {
        return page.getOutgoingItems(CadseGCST.PAGE_lt_DELETED_FIELDS, false);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getDeletedFields(Item page) {
        return page.getOutgoingItems(CadseGCST.PAGE_lt_DELETED_FIELDS,true);
    }

	/**
	 * @generated
	 */
	static public void addDeletedFields(Item page, Item value) throws CadseException {
        page.addOutgoingItem(CadseGCST.PAGE_lt_DELETED_FIELDS,value);
    }

	/**
	 * @generated
	 */
	static public void removeDeletedFields(Item page, Item value) throws CadseException {
        page.removeOutgoingItem(CadseGCST.PAGE_lt_DELETED_FIELDS,value);
    }


	/**
	 * change default value to false
	 * 
	 * @generated
	 */
	public static final boolean isIsRemovedAttribute(Item page) {
		return page.getAttributeWithDefaultValue(CadseGCST.PAGE_at_IS_REMOVED_, false);
	}

	/**
	 * @generated
	 */
	public static final void setIsRemovedAttribute(Item page, boolean value) {
		try {
			page.setAttribute(CadseGCST.PAGE_at_IS_REMOVED_, value);
		} catch (Throwable t) {

		}
	}


	/**
		@generated
	*/
	public static final String getLabelAttribute(Item page) {
		return page.getAttributeWithDefaultValue(CadseGCST.PAGE_at_LABEL_, null);
	}

	/**
		@generated
	*/
	public static final void setLabelAttribute(Item page, String value) {
		try {
			page.setAttribute(CadseGCST.PAGE_at_LABEL_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getIdRuntimeAttribute(Item page) {
		return page.getAttributeWithDefaultValue(CadseGCST.PAGE_at_ID_RUNTIME_, null);
	}

	/**
		@generated
	*/
	public static final void setIdRuntimeAttribute(Item page, String value) {
		try {
			page.setAttribute(CadseGCST.PAGE_at_ID_RUNTIME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the desciption.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return the desciption
	 */
	public static String getDesciption(Item page) {
		String ret = page.getAttribute(CadseGCST.PAGE_at_DESCRIPTION_);
		if (ret == null) {
			ret = "";
		}
		return ret;
	}

	/**
	 * Gets the title.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return the title
	 */
	public static String getTitle(Item page) {
		String ret = page.getAttribute(CadseGCST.PAGE_at_TITLE_);
		if (ret == null) {
			ret = "";
		}
		return ret;
	}

	/**
	 * Gets the key.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return the key
	 */
	public static String getKey(Item page) {
		String ret = page.getName();
		if (ret == null) {
			ret = "";
		}
		return ret;
	}

	/**
	 * Checks if is modification page.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return true, if is modification page
	 */
	public static boolean isModificationPage(Item page) {
		Item dialog = page.getPartParent();
		return dialog.getType() == CadseGCST.MODIFICATION_DIALOG;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#getDisplayName(fr.imag
	 * .adele.cadse.core.Item)
	 */
	@Override
	public String getDisplayName(Item item) {
		return item.getName();
	}

	/**
	 * get links 'fields' from 'Page' to 'Field'.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return the fields link
	 * 
	 * @generated
	 */
	static public List<Link> getFieldsLink(Item page) {
        return page.getOutgoingLinks(CadseGCST.PAGE_lt_FIELDS);
    }

	/**
	 * Gets the fields all.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return the fields all
	 * 
	 * @generated
	 */
	static public Collection<Item> getFieldsAll(Item page) {
        return page.getOutgoingItems(CadseGCST.PAGE_lt_FIELDS, false);
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#isOutgoingLinkSorted()
	 */
	@Override
	public boolean isOutgoingLinkSorted() {
		return false;
	}

	/**
	 * Gets the cadse definition.
	 * 
	 * @param page
	 *            the page
	 * 
	 * @return the cadse definition
	 */
	public static Item getCadseDefinition(Item page) {
		Item dialog = page.getPartParent();
		Item itemtype = dialog.getPartParent();
		return ItemTypeManager.getCadseDefinition(itemtype);
	}

	public static Item getSuperPage(Item page) {
		// Item actualSuperPage =
		Item absItemType = getItemTypeFromPage(page);
		if (absItemType == null) {
			return null;
		}
		Item superItemType = AbstractItemTypeManager.getSuperAbstractItemType(absItemType);
		if (superItemType == null) {
			return null;
		}

		if (isModificationPage(page)) {
			while (true) {
				Item dialog = ItemTypeManager.getModificationDialog(superItemType);
				if (dialog != null) {
					Item superpage = ModificationDialogManager.getPage(dialog, page.getName());
					if (superpage != null) {
						return superpage;
					}
				}
				superItemType = ItemTypeManager.getSuperType(superItemType);
				if (superItemType == null) {
					return null;
				}
			}
		} else {
			while (true) {
				Item dialog = ItemTypeManager.getCreationDialog(superItemType);
				if (dialog != null) {
					Item superpage = CreationDialogManager.getPage(dialog, page.getName());
					if (superpage != null) {
						return superpage;
					}
				}
				superItemType = ItemTypeManager.getSuperType(superItemType);
				if (superItemType == null) {
					return null;
				}
			}
		}
	}

	public static void getSuperPages(Item page, ArrayList<Item> pages) {
		Item absItemType = getItemTypeFromPage(page);
		if (absItemType == null) {
			return;
		}
		Item superItemType = AbstractItemTypeManager.getSuperAbstractItemType(absItemType);
		if (superItemType == null) {
			return;
		}

		if (isModificationPage(page)) {
			while (true) {
				Item dialog = ItemTypeManager.getModificationDialog(superItemType);
				if (dialog != null) {
					Item superpage = ModificationDialogManager.getPage(dialog, page.getName());
					if (superpage != null) {
						pages.add(superpage);
					}
				}
				superItemType = ItemTypeManager.getSuperType(superItemType);
				if (superItemType == null) {
					return;
				}
			}
		} else {
			while (true) {
				Item dialog = ItemTypeManager.getCreationDialog(superItemType);
				if (dialog != null) {
					Item superpage = CreationDialogManager.getPage(dialog, page.getName());
					if (superpage != null) {
						pages.add(superpage);
					}
				}
				superItemType = ItemTypeManager.getSuperType(superItemType);
				if (superItemType == null) {
					return;
				}
			}
		}
	}

}

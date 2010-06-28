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
import java.util.UUID;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultItemManager;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;

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
	public static UUID getIdRuntime(Item page) {
		String uuid_str = page.getAttribute(CadseGCST.PAGE_at_ID_RUNTIME_);
		if (uuid_str == null || uuid_str.length() == 0) {
			UUID uuid = UUID.randomUUID();
			uuid_str = uuid.toString();
			try {
				page.setAttribute(CadseGCST.PAGE_at_ID_RUNTIME_, uuid_str);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return uuid;
		}
		return UUID.fromString(uuid_str);

	}

	

	/**
	 * Instantiates a new page manager.
	 */
	public PageManager() {
	}
	
	@Override
	public boolean hasContent(Item item) {
		if (item.getPartParent(CadseGCST.CADSE_DEFINITION) == null) 
			return false;
		return super.hasContent(item);
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


//	/**
//	 * Gets the field generate infos.
//	 * 
//	 * @param cxt
//	 *            the cxt
//	 * @param page
//	 *            the page
//	 * @param imports
//	 *            the imports
//	 * 
//	 * @return the field generate infos
//	 */
//	public static Collection<FieldGenerateInfo> getFieldGenerateInfos(ContextVariable cxt, Item page,
//			Set<String> imports, Item superpage) {
//		ArrayList<FieldGenerateInfo> ret = new ArrayList<FieldGenerateInfo>();
//		HashMap<String, Item> supersField = null;
//		if (superpage != null) {
//			ArrayList<Item> superpages = new ArrayList<Item>();
//			superpages.add(superpage);
//			PageManager.getSuperPages(superpage, superpages);
//
//			supersField = new HashMap<String, Item>();
//			for (Item p : superpages) {
//				Collection<Item> fields = PageManager.getFieldsAll(p);
//				for (Item f : fields) {
//					if (!supersField.containsKey(f.getName())) {
//						supersField.put(f.getName(), f);
//					}
//				}
//			}
//		}
//
//		for (Link l : page.getOutgoingLinks()) {
//			// Select link has kind Part and destination.
//			if (l.getLinkType() == CadseGCST.PAGE_lt_FIELDS) {
//				if (l.isLinkResolved()) {
//					Item field = l.getDestination();
//					Item superField = supersField == null ? null : supersField.get(field.getName());
//					FieldGenerateInfo fieldGenerateInfo = FieldManager.getFieldGenerateInfo(cxt, field, superField,
//							imports);
//					if (fieldGenerateInfo != null) {
//						ret.add(fieldGenerateInfo);
//					}
//				}
//			}
//		}
//
//		return ret;
//
//	}

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
		get  links 'overwrite' from 'Page' to 'Page'.
        @generated
    */
    static public List<Link> getOverwriteLink(Item page) {
        return page.getOutgoingLinks(CadseGCST.PAGE_lt_OVERWRITE);
    }

	/**
        @generated
    */
    static public Collection<Item> getOverwriteAll(Item page) {
        return page.getOutgoingItems(CadseGCST.PAGE_lt_OVERWRITE, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getOverwrite(Item page) {
        return page.getOutgoingItems(CadseGCST.PAGE_lt_OVERWRITE,true);
    }

	/**
        @generated
    */
    static public void addOverwrite(Item page, Item value) throws CadseException {
        page.addOutgoingItem(CadseGCST.PAGE_lt_OVERWRITE,value);
    }

	/**
        @generated
    */
    static public void removeOverwrite(Item page, Item value) throws CadseException {
        page.removeOutgoingItem(CadseGCST.PAGE_lt_OVERWRITE,value);
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
		List<Link> incomingLinks = page.getIncomingLinks(CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES);
		return incomingLinks.size() != 0;
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
		get  links 'attributes' from 'Page' to 'Attribute'.
        @generated
    */
    static public List<Link> getAttributesLink(Item page) {
        return page.getOutgoingLinks(CadseGCST.PAGE_lt_ATTRIBUTES);
    }

	/**
        @generated
    */
    static public Collection<Item> getAttributesAll(Item page) {
        return page.getOutgoingItems(CadseGCST.PAGE_lt_ATTRIBUTES, false);
    }

	/**
        @generated
    */
    static public Collection<Item> getAttributes(Item page) {
        return page.getOutgoingItems(CadseGCST.PAGE_lt_ATTRIBUTES,true);
    }

	/**
        @generated
    */
    static public void addAttributes(Item page, Item value) throws CadseException {
        page.addOutgoingItem(CadseGCST.PAGE_lt_ATTRIBUTES,value);
    }

	/**
        @generated
    */
    static public void removeAttributes(Item page, Item value) throws CadseException {
        page.removeOutgoingItem(CadseGCST.PAGE_lt_ATTRIBUTES,value);
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

//	public static Item getSuperPage(Item page) {
//		// Item actualSuperPage =
//		Item absItemType = getItemTypeFromPage(page);
//		if (absItemType == null) {
//			return null;
//		}
//		Item superItemType = TypeDefinitionManager.getSuperAbstractItemType(absItemType);
//		if (superItemType == null) {
//			return null;
//		}
//
//		if (isModificationPage(page)) {
//			while (true) {
//				Item dialog = ItemTypeManager.getModificationDialog(superItemType);
//				if (dialog != null) {
//					Item superpage = ModificationDialogManager.getPage(dialog, page.getName());
//					if (superpage != null) {
//						return superpage;
//					}
//				}
//				superItemType = ItemTypeManager.getSuperType(superItemType);
//				if (superItemType == null) {
//					return null;
//				}
//			}
//		} else {
//			while (true) {
//				Item dialog = ItemTypeManager.getCreationDialog(superItemType);
//				if (dialog != null) {
//					Item superpage = CreationDialogManager.getPage(dialog, page.getName());
//					if (superpage != null) {
//						return superpage;
//					}
//				}
//				superItemType = ItemTypeManager.getSuperType(superItemType);
//				if (superItemType == null) {
//					return null;
//				}
//			}
//		}
//	}

//	public static void getSuperPages(Item page, ArrayList<Item> pages) {
//		Item absItemType = getItemTypeFromPage(page);
//		if (absItemType == null) {
//			return;
//		}
//		Item superItemType = TypeDefinitionManager.getSuperAbstractItemType(absItemType);
//		if (superItemType == null) {
//			return;
//		}
//
//		if (isModificationPage(page)) {
//			while (true) {
//				Item dialog = ItemTypeManager.getModificationDialog(superItemType);
//				if (dialog != null) {
//					Item superpage = ModificationDialogManager.getPage(dialog, page.getName());
//					if (superpage != null) {
//						pages.add(superpage);
//					}
//				}
//				superItemType = ItemTypeManager.getSuperType(superItemType);
//				if (superItemType == null) {
//					return;
//				}
//			}
//		} else {
//			while (true) {
//				Item dialog = ItemTypeManager.getCreationDialog(superItemType);
//				if (dialog != null) {
//					Item superpage = CreationDialogManager.getPage(dialog, page.getName());
//					if (superpage != null) {
//						pages.add(superpage);
//					}
//				}
//				superItemType = ItemTypeManager.getSuperType(superItemType);
//				if (superItemType == null) {
//					return;
//				}
//			}
//		}
//	}

}

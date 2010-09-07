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
package fr.imag.adele.cadse.cadseg.managers.attributes;

import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.enumdef.TWDestEvol;
import fr.imag.adele.cadse.core.util.Convert;

/**
 * The Class LinkTypeManager.
 */
public class LinkTypeManager extends AttributeManager implements IModelWorkspaceManager {

	/** The Constant INT_ID_ATTRIBUTE. */
	public static final String	INT_ID_ATTRIBUTE	= "int-id";

	/**
	 * Instantiates a new link manager.
	 */
	public LinkTypeManager() {
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
	 * Gets the max.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the max
	 */
	public static int getMax(Item item) {
		try {
			return Convert.toInt(item.getAttribute(CadseGCST.LINK_TYPE_at_MAX_), null);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				item.setAttribute(CadseGCST.LINK_TYPE_at_MAX_, 1);
			} catch (CadseException e2) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}
	}

	/**
	 * Gets the min.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the min
	 */
	public static int getMin(Item item) {
		try {
			return Convert.toInt(item.getAttribute(CadseGCST.LINK_TYPE_at_MIN_), null);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	

	/**
	 * Sets the min attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setMinAttribute(Item linkType, int value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_MIN_, value);
		} catch (Throwable t) {

		}
	}
	
	
	/**
		@generated
	*/
	public static final int getMaxAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_MAX_, -1);
	}

	/**
	 * Sets the max attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setMaxAttribute(Item linkType, int value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_MAX_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final int getKindAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_KIND_, -1);
	}

	/**
		@generated
	*/
	public static final void setKindAttribute(Item linkType, int value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_KIND_, value);
		} catch (Throwable t) {

		}
	}

	/**
		get a link 'source' from 'Link' to 'ItemType'.
		@generated
	*/
	static public Link getSourceLink(Item linkType) {
		return linkType.getOutgoingLink(CadseGCST.LINK_TYPE_lt_SOURCE);
	}

	/**
		get all link destination 'source' from 'Link' to 'ItemType'.
		@generated
	*/
	static public Item getSourceAll(Item linkType) {
		return linkType.getOutgoingItem(CadseGCST.LINK_TYPE_lt_SOURCE, false);
	}

	/**
	 * get a link 'destination' from 'LinkType' to 'ItemType'.
	 * 
	 * @param link
	 *            the link
	 * 
	 * @return the destination link
	 * 
	 * @generated
	 */
	static public Link getDestinationLink(Item linkType) {
		return linkType.getOutgoingLink(CadseGCST.LINK_TYPE_lt_DESTINATION);
	}

	/**
	 * get all link destination 'destination' from 'LinkType' to 'ItemType'.
	 * 
	 * @param link
	 *            the link
	 * 
	 * @return the destination all
	 * 
	 * @generated
	 */
	static public Item getDestinationAll(Item linkType) {
		return linkType.getOutgoingItem(CadseGCST.LINK_TYPE_lt_DESTINATION, false);
	}

	/**
	 * get a link 'inverse-link' from 'Link' to 'Link'.
	 * 
	 * @param link
	 *            the link
	 * 
	 * @return the inverse link link
	 * 
	 * @generated
	 */
	static public Link getInverseLinkLink(Item linkType) {
		return linkType.getOutgoingLink(CadseGCST.LINK_TYPE_lt_INVERSE_LINK);
	}

	/**
	 * get all link destination 'inverse-link' from 'Link' to 'Link'.
	 * 
	 * @param link
	 *            the link
	 * 
	 * @return the inverse link all
	 * 
	 * @generated
	 */
	static public Item getInverseLinkAll(Item linkType) {
		return linkType.getOutgoingItem(CadseGCST.LINK_TYPE_lt_INVERSE_LINK, false);
	}

	/**
	 * get resolved link destination 'inverse-link' from 'Link' to 'Link'.
	 * 
	 * @param link
	 *            the link
	 * 
	 * @return the inverse link
	 * 
	 * @generated
	 */
	static public Item getInverseLink(Item linkType) {
		return linkType.getOutgoingItem(CadseGCST.LINK_TYPE_lt_INVERSE_LINK, true);
	}

	/**
	 * set a link 'inverse-link' from 'Link' to 'Link'.
	 * 
	 * @generated
	 */
	static public void setInverseLink(Item linkType, Item value) throws CadseException {
		linkType.setOutgoingItem(CadseGCST.LINK_TYPE_lt_INVERSE_LINK,value);
	}

	/**
	 * @generated
	 */
	public static final boolean isAnnotationAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_ANNOTATION_, false);
	}

	/**
	 * @generated
	 */
	public static final void setAnnotationAttribute(Item linkType, boolean value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_ANNOTATION_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isPartAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_PART_, false);
	}

	/**
	 * @generated
	 */
	public static final void setPartAttribute(Item linkType, boolean value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_PART_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isAggregationAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_AGGREGATION_, true);
	}

	/**
	 * @generated
	 */
	public static final void setAggregationAttribute(Item linkType, boolean value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_AGGREGATION_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isCompositionAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_COMPOSITION_, false);
	}

	/**
	 * @generated
	 */
	public static final void setCompositionAttribute(Item linkType, boolean value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_COMPOSITION_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final String getSelectionAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_SELECTION_, null);
	}

	/**
	 * @generated
	 */
	public static final void setSelectionAttribute(Item linkType, String value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_SELECTION_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isRequireAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_REQUIRE_, false);
	}

	/**
	 * @generated
	 */
	public static final void setRequireAttribute(Item linkType, boolean value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_REQUIRE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isMappingAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_MAPPING_, false);
	}

	/**
	 * @generated
	 */
	public static final void setMappingAttribute(Item linkType, boolean value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_MAPPING_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getLinkManagerAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_LINK_MANAGER_, "");
	}

	/**
		@generated
	*/
	public static final void setLinkManagerAttribute(Item linkType, String value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_LINK_MANAGER_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final TWDestEvol getTWDestEvolAttribute(Item linkType) {
		Object value = linkType.getAttribute(CadseGCST.LINK_TYPE_at_TWDEST_EVOL_);
		return Convert.toEnum(value,CadseGCST.LINK_TYPE_at_TWDEST_EVOL_,TWDestEvol.immutable);
	}

	/**
	 * @generated
	 */
	public static final void setTWDestEvolAttribute(Item linkType, TWDestEvol value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_TWDEST_EVOL_, value);
		} catch (Throwable t) {
		}
	}

	/**
	 * @generated
	 */
	public static final boolean isTWCoupledAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_TWCOUPLED_, false);
	}

	/**
	 * @generated
	 */
	public static final void setTWCoupledAttribute(Item linkType, boolean value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_TWCOUPLED_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isHiddenAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_HIDDEN_, false);
	}

	/**
	 * @generated
	 */
	public static final void setHiddenAttribute(Item linkType, boolean value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_HIDDEN_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final int getMinAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_MIN_, 0);
	}

	/**
	 * Gets the destination.
	 * 
	 * @param theItemLinkType
	 *            the the item link type
	 * 
	 * @return the destination
	 */
	public static Item getDestination(Item theItemLinkType) {
		Link ret = theItemLinkType.getOutgoingLink(CadseGCST.LINK_TYPE_lt_DESTINATION);
		if (ret == null || !ret.isLinkResolved()) {
			return null;
		}
		return ret.getResolvedDestination();
	}

	/**
	 * set a link 'destination' from 'Link' to 'ItemType'.
	 * 
	 * @generated
	 */
	static public void setDestination(Item linkType, Item value) throws CadseException {
		linkType.setOutgoingItem(CadseGCST.LINK_TYPE_lt_DESTINATION,value);
	}

	/**
	 * set a link 'destination' from 'LinkType' to 'ItemType'.
	 * 
	 * @param link
	 *            the link
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @not generated
	 * 
	 * @deprecated
	 */
	@Deprecated
	static public void setDestinationAttribute(Item link, Item value) throws CadseException {
		setDestination(link, value);
	}

	/**
	 * Checks if is composition.
	 * 
	 * @param linkType
	 *            the link type
	 * 
	 * @return true, if is composition
	 */
	public static boolean isComposition(Item linkType) {
		return Convert.toBoolean(linkType.getAttribute(CadseGCST.LINK_TYPE_at_COMPOSITION_), false);
	}

	/**
	 * Checks if is aggregation.
	 * 
	 * @param linkType
	 *            the link type
	 * 
	 * @return true, if is aggregation
	 */
	public static boolean isAggregation(Item linkType) {
		return Convert.toBoolean(linkType.getAttribute(CadseGCST.LINK_TYPE_at_AGGREGATION_), false);
	}

	/**
	 * Checks if is part.
	 * 
	 * @param linkType
	 *            the link type
	 * 
	 * @return true, if is part
	 */
	public static boolean isPart(Item linkType) {
		return Convert.toBoolean(linkType.getAttribute(CadseGCST.LINK_TYPE_at_PART_), false);
	}

	/**
	 * Gets the source.
	 * 
	 * @param lt
	 *            the lt
	 * 
	 * @return the source
	 */
	public static Item getSource(Item lt) {
		return lt.getPartParent();
	}

	/**
		set a link 'source' from 'Link' to 'ItemType'.
		@generated
	*/
	static public void setSource(Item linkType, Item value) throws CadseException {
		linkType.setOutgoingItem(CadseGCST.LINK_TYPE_lt_SOURCE,value);
	}

	/**
		@generated
	*/
	public static final boolean isGroupAttribute(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_GROUP_, false);
	}

	/**
		@generated
	*/
	public static final void setGroupAttribute(Item linkType, boolean value) {
		try {
			linkType.setAttribute(CadseGCST.LINK_TYPE_at_GROUP_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is annotation.
	 * 
	 * @param linkType
	 *            the link type
	 * 
	 * @return true, if is annotation
	 */
	public static boolean isAnnotation(Item linkType) {
		return Convert.toBooleanFalseIfNull(linkType.getAttribute(CadseGCST.LINK_TYPE_at_ANNOTATION_));
	}

	/**
	 * Checks if is annotation.
	 * 
	 * @param linkType
	 *            the link type
	 * 
	 * @return true, if is annotation
	 */
	public static boolean isNatif(Item linkType) {
		return Convert.toBooleanFalseIfNull(linkType.getAttribute(CadseGCST.ATTRIBUTE_at_NATIF_));
	}

	/**
	 * Gets the selection expression.
	 * 
	 * @param linkType
	 *            the link type
	 * 
	 * @return the selection expression
	 */
	public static String getSelectionExpression(Item linkType) {
		return linkType.getAttributeWithDefaultValue(CadseGCST.LINK_TYPE_at_SELECTION_, "");
	}

	/**
	 * Gets the inverse link name.
	 * 
	 * @param linkType
	 *            the link type
	 * 
	 * @return the inverse link name
	 */
	public static String getInverseLinkName(Item linkType) {
		Link l = getInverseLinkLink(linkType);
		if (l == null) {
			return "";
		}
		if (!l.isLinkResolved()) {
			return "";
		}
		Item inverseLinkItem = l.getResolvedDestination();
		return inverseLinkItem.getName();
	}

	/**
	 * Compute int id.
	 * 
	 * @param linkType
	 *            the link type
	 * @param intID
	 *            the int id
	 */
	public static void computeIntID(Item linkType, int intID) {
		
	}

	/**
	 * Gets the int id.
	 * 
	 * @param linkType
	 *            the link type
	 * 
	 * @return the int id
	 */
	public static int getIntID(Item linkType) {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#canRenameItem(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String canRenameItem(Item item) {
		return null;
	}



}

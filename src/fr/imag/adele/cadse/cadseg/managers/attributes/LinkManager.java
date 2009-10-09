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
/*
 * Adele/LIG/ Grenoble University, France
 * 2006-2008
 */
package fr.imag.adele.cadse.cadseg.managers.attributes;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;

import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.contents.attributes.LinkCIF;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseRootCST;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.enumdef.TWDestEvol;
import fr.imag.adele.cadse.core.util.Convert;

/**
 * The Class LinkManager.
 */
public class LinkManager extends AttributeManager implements IModelWorkspaceManager {

	/** The Constant INT_ID_ATTRIBUTE. */
	public static final String	INT_ID_ATTRIBUTE	= "int-id";

	/**
	 * Instantiates a new link manager.
	 */
	public LinkManager() {
	}

	/**
	 * Compute unique name.
	 * 
	 * @param item
	 *            the item
	 * @param shortName
	 *            the short name
	 * @param parent
	 *            the parent
	 * @param lt
	 *            the lt
	 * 
	 * @return the string
	 * 
	 * @generated
	 */
	@Override
	public String computeUniqueName(Item item, String shortName, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(shortName);
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
			Item currentItem;
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
			return Convert.toInt(item.getAttribute(WorkspaceCST.ATTRIBUTE_at_MAX_), null);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				item.setAttribute(WorkspaceCST.ATTRIBUTE_at_MAX_, 1);
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
			return Convert.toInt(item.getAttribute(WorkspaceCST.ATTRIBUTE_at_MIN_), null);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

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
	static public Link getDestinationLink(Item link) {
		return link.getOutgoingLink(WorkspaceCST.LINK_lt_DESTINATION);
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
	static public Item getDestinationAll(Item link) {
		return link.getOutgoingItem(WorkspaceCST.LINK_lt_DESTINATION, false);
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
	static public Link getInverseLinkLink(Item link) {
		return link.getOutgoingLink(WorkspaceCST.LINK_lt_INVERSE_LINK);
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
	static public Item getInverseLinkAll(Item link) {
		return link.getOutgoingItem(WorkspaceCST.LINK_lt_INVERSE_LINK, false);
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
	static public Item getInverseLink(Item link) {
		return link.getOutgoingItem(WorkspaceCST.LINK_lt_INVERSE_LINK, true);
	}

	/**
	 * set a link 'inverse-link' from 'Link' to 'Link'.
	 * 
	 * @generated
	 */
	static public void setInverseLink(Item link, Item value) throws CadseException {
		link.setOutgoingItem(WorkspaceCST.LINK_lt_INVERSE_LINK, value);
	}

	/**
	 * @generated
	 */
	public static final boolean isAnnotationAttribute(Item link) {
		return link.getAttributeWithDefaultValue(WorkspaceCST.LINK_at_ANNOTATION_, false);
	}

	/**
	 * @generated
	 */
	public static final void setAnnotationAttribute(Item link, boolean value) {
		try {
			link.setAttribute(WorkspaceCST.LINK_at_ANNOTATION_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isPartAttribute(Item link) {
		return link.getAttributeWithDefaultValue(WorkspaceCST.LINK_at_PART_, false);
	}

	/**
	 * @generated
	 */
	public static final void setPartAttribute(Item link, boolean value) {
		try {
			link.setAttribute(WorkspaceCST.LINK_at_PART_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isAggregationAttribute(Item link) {
		return link.getAttributeWithDefaultValue(WorkspaceCST.LINK_at_AGGREGATION_, false);
	}

	/**
	 * @generated
	 */
	public static final void setAggregationAttribute(Item link, boolean value) {
		try {
			link.setAttribute(WorkspaceCST.LINK_at_AGGREGATION_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isCompositionAttribute(Item link) {
		return link.getAttributeWithDefaultValue(WorkspaceCST.LINK_at_COMPOSITION_, false);
	}

	/**
	 * @generated
	 */
	public static final void setCompositionAttribute(Item link, boolean value) {
		try {
			link.setAttribute(WorkspaceCST.LINK_at_COMPOSITION_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final String getSelectionAttribute(Item link) {
		return link.getAttributeWithDefaultValue(WorkspaceCST.LINK_at_SELECTION_, "");
	}

	/**
	 * @generated
	 */
	public static final void setSelectionAttribute(Item link, String value) {
		try {
			link.setAttribute(WorkspaceCST.LINK_at_SELECTION_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isRequireAttribute(Item link) {
		return link.getAttributeWithDefaultValue(WorkspaceCST.LINK_at_REQUIRE_, false);
	}

	/**
	 * @generated
	 */
	public static final void setRequireAttribute(Item link, boolean value) {
		try {
			link.setAttribute(WorkspaceCST.LINK_at_REQUIRE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isMappingAttribute(Item link) {
		return link.getAttributeWithDefaultValue(WorkspaceCST.LINK_at_MAPPING_, false);
	}

	/**
	 * @generated
	 */
	public static final void setMappingAttribute(Item link, boolean value) {
		try {
			link.setAttribute(WorkspaceCST.LINK_at_MAPPING_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final String getLinkManagerAttribute(Item link) {
		return link.getAttributeWithDefaultValue(WorkspaceCST.LINK_at_LINK_MANAGER_, "");
	}

	public static final IType getLinkManagerType(Item link) {
		String linkManager = getLinkManagerAttribute(link);
		if (linkManager == null || linkManager.length() == 0) {
			return null;
		}
		IJavaElement ret = JavaCore.create(linkManager);
		if (ret instanceof IType) {
			return (IType) ret;
		}
		return null;
	}

	/**
	 * @generated
	 */
	public static final void setLinkManagerAttribute(Item link, String value) {
		try {
			link.setAttribute(WorkspaceCST.LINK_at_LINK_MANAGER_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final TWDestEvol getTWDestEvolAttribute(Item link) {
		Object value = link.getAttribute(WorkspaceCST.LINK_at_TWDEST_EVOL);
		return Convert.toEnum(value, WorkspaceCST.LINK_at_TWDEST_EVOL_, TWDestEvol.immutable);
	}

	/**
	 * @generated
	 */
	public static final void setTWDestEvolAttribute(Item link, TWDestEvol value) {
		try {
			link.setAttribute(WorkspaceCST.LINK_at_TWDEST_EVOL, value);
		} catch (Throwable t) {
		}
	}

	/**
	 * @generated
	 */
	public static final boolean isTWCoupledAttribute(Item link) {
		return link.getAttributeWithDefaultValue(WorkspaceCST.LINK_at_TWCOUPLED_, false);
	}

	/**
	 * @generated
	 */
	public static final void setTWCoupledAttribute(Item link, boolean value) {
		try {
			link.setAttribute(WorkspaceCST.LINK_at_TWCOUPLED_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isHiddenAttribute(Item link) {
		return link.getAttributeWithDefaultValue(WorkspaceCST.LINK_at_HIDDEN_, false);
	}

	/**
	 * @generated
	 */
	public static final void setHiddenAttribute(Item link, boolean value) {
		try {
			link.setAttribute(WorkspaceCST.LINK_at_HIDDEN_, value);
		} catch (Throwable t) {

		}
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
		Link ret = theItemLinkType.getOutgoingLink(WorkspaceCST.LINK_lt_DESTINATION);
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
	static public void setDestination(Item link, Item value) throws CadseException {
		link.setOutgoingItem(WorkspaceCST.LINK_lt_DESTINATION, value);
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
		return Convert.toBoolean(linkType.getAttribute(WorkspaceCST.LINK_at_COMPOSITION), false);
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
		return Convert.toBoolean(linkType.getAttribute(WorkspaceCST.LINK_at_AGGREGATION), false);
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
		return Convert.toBoolean(linkType.getAttribute(WorkspaceCST.LINK_at_PART), false);
	}

	@Override
	public IContentItemFactory getContentItemFactory() {
		return new LinkCIF(this);
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
	 * Checks if is annotation.
	 * 
	 * @param linkType
	 *            the link type
	 * 
	 * @return true, if is annotation
	 */
	public static boolean isAnnotation(Item linkType) {
		return Convert.toBooleanFalseIfNull(linkType.getAttribute(WorkspaceCST.LINK_at_ANNOTATION));
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
		return Convert.toBooleanFalseIfNull(linkType.getAttribute(WorkspaceCST.ATTRIBUTE_at_NATIF));
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
		return linkType.getAttributeWithDefaultValue(WorkspaceCST.LINK_at_SELECTION, "");
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
		try {
			linkType.setAttribute(INT_ID_ATTRIBUTE, intID);
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		Object intID = linkType.getAttribute(INT_ID_ATTRIBUTE);
		if (intID == null) {
			Item itemType = getSource(linkType);
			// ItemTypeManager.computeIntID(ItemTypeManager.getMainDataModel(itemType
			// ));
		}
		intID = linkType.getAttribute(INT_ID_ATTRIBUTE);
		return (Integer) intID;
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

	@Override
	public ItemType getCadseRootType() {
		return CadseRootCST.LINK_DEFINITION_ATTIBUTE_TYPE;
	}

	@Override
	public Class<? extends IAttributeType<?>> getAttributeDefinitionTypeJava() {
		return null;
	}

}
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

package fr.imag.adele.cadse.cadseg.managers.attributes;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.IType;

import fede.workspace.tool.loadmodel.model.jaxb.CAttType;
import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.contents.attributes.AttributeCIF;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ExtItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseRootCST;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.ListAttributeType;
import fr.imag.adele.cadse.core.enumdef.TWCommitKind;
import fr.imag.adele.cadse.core.enumdef.TWEvol;
import fr.imag.adele.cadse.core.enumdef.TWUpdateKind;
import fr.imag.adele.cadse.core.impl.internal.ItemImpl;
import fr.imag.adele.cadse.core.key.ISpaceKey;
import fr.imag.adele.cadse.core.key.SpaceKeyType;
import fr.imag.adele.cadse.core.util.Assert;
import fr.imag.adele.cadse.core.util.Convert;
import java.lang.String;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.as.initmodel.IAttributeCadsegForGenerate;

/**
 * The Class AttributeManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class AttributeManager extends DefaultWorkspaceManager implements IItemManager, IModelWorkspaceManager,
		IAttributeCadsegForGenerate {

	/**
	 * Checks if is link attribute.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return true, if is link attribute
	 */
	public static boolean isLinkAttribute(Item item) {
		return item.getType() == WorkspaceCST.LINK;
	}

	private final class AttributeSpaceKeyType extends SpaceKeyType {
		private AttributeSpaceKeyType(ItemType itemType, ItemType spaceKeyType) {
			super(itemType, spaceKeyType);
		}

		@Override
		protected ISpaceKey getParentSpaceKeyFromItem(Item item) {
			Item it = null;
			it = item.getPartParent(false);
			if (it == null) {
				it = ((ItemImpl) item).getParentInStorage();
			}
			Assert.isNotNull(it, "Cannot found parent form " + item.getType().getName() + "::" + item.getName());

			if (it.getType() == WorkspaceCST.EXT_ITEM_TYPE) {
				Item it2 = ExtItemTypeManager.getRefType(it);
				Assert
						.isNotNull(it2, "Cannot found ref itemtype form " + it.getQualifiedName() + "::"
								+ item.getName());

				it = it2;
			}
			ISpaceKey key = it.getKey();
			assert key != null;
			return key;
		}
	}

	// /** The Constant TYPE_ATTRIBUTE. */
	// / @Deprecated
	// public static final String TYPE_ATTRIBUTE = "type";

	/**
	 * Instantiates a new attribute manager.
	 */
	public AttributeManager() {
	}

	public void computeImportsManifest(Set<String> imports) {
		imports.add("fr.imag.adele.cadse.core");
		imports.add("org.eclipse.ltk.core.refactoring");
		imports.add("fr.imag.adele.cadse.core.util");
	}

	public void addJavaImport(Set<String> imports) {
		imports.add("fr.imag.adele.cadse.core.Item");
		imports.add("fr.imag.adele.cadse.core.CadseException");
		imports.add("fr.imag.adele.cadse.core.util.Convert");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#computeUniqueName(fr.imag.adele.cadse.core.Item,
	 *      java.lang.String, fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType)
	 */
	@Override
	public String computeUniqueName(Item item, String shortid, Item parent, LinkType lt) {
		return Item.NO_VALUE_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#hasContent(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public boolean hasContent(Item item) {
		return true;
	}

	@Override
	public IContentItemFactory getContentItemFactory() {
		return new AttributeCIF(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#init(fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public void init() {
		getItemType().setHasShortNameAttribute(true);
		getItemType().setHasUniqueNameAttribute(false);
		if (getItemType() == WorkspaceCST.ATTRIBUTE) {
			WorkspaceCST.ATTRIBUTE.setSpaceKeyType(new AttributeSpaceKeyType(WorkspaceCST.ATTRIBUTE,
					WorkspaceCST.ITEM_TYPE));
			// new AttributeWLWC();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.DefaultWorkspaceManager#getWorkspaceModel(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Item getWorkspaceModel(Item source) {
		return source.getPartParent().getPartParent().getPartParent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.DefaultWorkspaceManager#getDisplayName(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String getDisplayName(Item item) {
		return item.getName();
	}

	/**
	 * Gets the min attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * 
	 * @return the min attribute
	 * 
	 * @generated
	 */
	public static final int getMinAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(WorkspaceCST.ATTRIBUTE_at_MIN_, 0);
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
	public static final void setMinAttribute(Item attribute, int value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_MIN_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the max attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * 
	 * @return the max attribute
	 * 
	 * @generated
	 */
	public static final int getMaxAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(WorkspaceCST.ATTRIBUTE_at_MAX_, -1);
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
	public static final void setMaxAttribute(Item attribute, int value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_MAX_, value);
		} catch (Throwable t) {

		}
	}

	/* @not generated */
	/**
	 * Checks if is checks if is list attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * 
	 * @return true, if is checks if is list attribute
	 */
	public static final boolean isIsListAttribute(Item attribute) {
		if (attribute.getType() == WorkspaceCST.LINK) { // correction bug
			// Une definition d'un type de link n'a pas l'attribut 'is-list'
			// pour dire si c'est une liste ou non
			// C'est l'attribut max contient cette information sous une autre
			// forme : un entier. '-1' siginifie qu'il n'y a pas de limite.
			// 'unbounded' en anglais.
			int max = LinkManager.getMax(attribute);
			return max == -1 || max > 1;
		}
		Object value = attribute.getAttribute(WorkspaceCST.ATTRIBUTE_at_IS_LIST_);

		try {
			return Convert.toBooleanFalseIfNull(value);
		} catch (Throwable t) {
			return false;
		}

	}

	/**
	 * Sets the is list attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setIsListAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_IS_LIST_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the default value attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * 
	 * @return the default value attribute
	 * 
	 * @not generated
	 */
	public static final String getDefaultValueAttribute(Item attribute) {
		Object value = attribute.getAttribute(WorkspaceCST.ATTRIBUTE_at_DEFAULT_VALUE_);
		if (value == null) {
			return "";
		}

		try {
			return value.toString();
		} catch (Throwable t) {
			return "";
		}

	}

	/**
	 * Sets the default value attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setDefaultValueAttribute(Item attribute, String value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_DEFAULT_VALUE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is require attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * 
	 * @return true, if checks if is require attribute
	 * 
	 * @generated
	 */
	public static boolean isRequireAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(WorkspaceCST.ATTRIBUTE_at_REQUIRE_, false);
	}

	/**
	 * Sets the require attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static void setRequireAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_REQUIRE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isClassAttributeAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(WorkspaceCST.ATTRIBUTE_at_CLASS_ATTRIBUTE_, false);
	}

	/**
	 * @generated
	 */
	public static final void setClassAttributeAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_CLASS_ATTRIBUTE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isCannotBeUndefinedAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(WorkspaceCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_, false);
	}

	/**
	 * @generated
	 */
	public static final void setCannotBeUndefinedAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isNatifAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(WorkspaceCST.ATTRIBUTE_at_NATIF_, false);
	}

	/**
	 * @generated
	 */
	public static final void setNatifAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_NATIF_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isTransientAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(WorkspaceCST.ATTRIBUTE_at_TRANSIENT_, false);
	}

	/**
	 * @generated
	 */
	public static final void setTransientAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_TRANSIENT_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isFinalValueAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(WorkspaceCST.ATTRIBUTE_at_FINAL_VALUE_, false);
	}

	/**
	 * @generated
	 */
	public static final void setFinalValueAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_FINAL_VALUE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isMustBeInitializedAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(WorkspaceCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_, true);
	}

	/**
	 * @generated
	 */
	public static final void setMustBeInitializedAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isTWRevSpecificAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(WorkspaceCST.ATTRIBUTE_at_TWREV_SPECIFIC_, true);
	}

	/**
	 * @generated
	 */
	public static final void setTWRevSpecificAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_TWREV_SPECIFIC_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final TWEvol getTWEvolAttribute(Item attribute) {
		Object value = attribute.getAttribute(WorkspaceCST.ATTRIBUTE_at_TWEVOL);
		return Convert.toEnum(value, WorkspaceCST.ATTRIBUTE_at_TWEVOL_, TWEvol.twImmutable);
	}

	/**
	 * @generated
	 */
	public static final void setTWEvolAttribute(Item attribute, TWEvol value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_TWEVOL, value);
		} catch (Throwable t) {
		}
	}

	/**
	 * @generated
	 */
	public static final TWUpdateKind getTWUpdateKindAttribute(Item attribute) {
		Object value = attribute.getAttribute(WorkspaceCST.ATTRIBUTE_at_TWUPDATE_KIND);
		return Convert.toEnum(value, WorkspaceCST.ATTRIBUTE_at_TWUPDATE_KIND_, TWUpdateKind.merge);
	}

	/**
	 * @generated
	 */
	public static final void setTWUpdateKindAttribute(Item attribute, TWUpdateKind value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_TWUPDATE_KIND, value);
		} catch (Throwable t) {
		}
	}

	/**
	 * @generated
	 */
	public static final TWCommitKind getTWCommitKindAttribute(Item attribute) {
		Object value = attribute.getAttribute(WorkspaceCST.ATTRIBUTE_at_TWCOMMIT_KIND);
		return Convert.toEnum(value, WorkspaceCST.ATTRIBUTE_at_TWCOMMIT_KIND_, TWCommitKind.conflict);
	}

	/**
	 * @generated
	 */
	public static final void setTWCommitKindAttribute(Item attribute, TWCommitKind value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_TWCOMMIT_KIND, value);
		} catch (Throwable t) {
		}
	}

	/**
	 * get links 'wcListens' from 'Attribute' to 'WCListener'.
	 * 
	 * @generated
	 */
	static public List<Link> getWcListensLink(Item attribute) {
		return attribute.getOutgoingLinks(WorkspaceCST.ATTRIBUTE_lt_WC_LISTENS);
	}

	/**
	 * @generated
	 */
	static public Collection<Item> getWcListensAll(Item attribute) {
		return attribute.getOutgoingItems(WorkspaceCST.ATTRIBUTE_lt_WC_LISTENS, false);
	}

	/**
	 * @generated
	 */
	static public Collection<Item> getWcListens(Item attribute) {
		return attribute.getOutgoingItems(WorkspaceCST.ATTRIBUTE_lt_WC_LISTENS, true);
	}

	/**
	 * @generated
	 */
	static public void addWcListens(Item attribute, Item value) throws CadseException {
		attribute.addOutgoingItem(WorkspaceCST.ATTRIBUTE_lt_WC_LISTENS, value);
	}

	/**
	 * @generated
	 */
	static public void removeWcListens(Item attribute, Item value) throws CadseException {
		attribute.removeOutgoingItem(WorkspaceCST.ATTRIBUTE_lt_WC_LISTENS, value);
	}

	/**
	 * @generated
	 */
	public static final boolean isCachedAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(WorkspaceCST.ATTRIBUTE_at_CACHED_, false);
	}

	/**
	 * @generated
	 */
	public static final void setCachedAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_CACHED_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * get links '#invert_part_attributes_to_AbstractItemType' from 'Attribute'
	 * to 'AbstractItemType'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_attributes_to_AbstractItemTypeLink(Item attribute) {
		return attribute.getOutgoingLink(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_ABSTRACT_ITEM_TYPE);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_AbstractItemTypeAll(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_ABSTRACT_ITEM_TYPE,
				false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_AbstractItemType(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_ABSTRACT_ITEM_TYPE,
				true);
	}

	/**
	 * set a link '#invert_part_attributes_to_AbstractItemType' from 'Attribute'
	 * to 'AbstractItemType'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_attributes_to_AbstractItemType(Item attribute, Item value)
			throws CadseException {
		attribute.setOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_ABSTRACT_ITEM_TYPE, value);
	}

	/**
	 * get links '#invert_part_attributes_to_BeanComposer' from 'Attribute' to
	 * 'BeanComposer'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_attributes_to_BeanComposerLink(Item attribute) {
		return attribute.getOutgoingLink(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN_COMPOSER);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_BeanComposerAll(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN_COMPOSER, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_BeanComposer(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN_COMPOSER, true);
	}

	/**
	 * set a link '#invert_part_attributes_to_BeanComposer' from 'Attribute' to
	 * 'BeanComposer'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_attributes_to_BeanComposer(Item attribute, Item value) throws CadseException {
		attribute.setOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN_COMPOSER, value);
	}

	/**
	 * get links '#invert_part_attributes_to_Bean' from 'Attribute' to 'Bean'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_attributes_to_BeanLink(Item attribute) {
		return attribute.getOutgoingLink(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_BeanAll(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_Bean(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN, true);
	}

	/**
	 * set a link '#invert_part_attributes_to_Bean' from 'Attribute' to 'Bean'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_attributes_to_Bean(Item attribute, Item value) throws CadseException {
		attribute.setOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN, value);
	}

	/**
	 * get links '#invert_part_attributes_to_ContentType' from 'Attribute' to
	 * 'ContentType'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_attributes_to_ContentTypeLink(Item attribute) {
		return attribute.getOutgoingLink(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_CONTENT_TYPE);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_ContentTypeAll(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_CONTENT_TYPE, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_ContentType(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_CONTENT_TYPE, true);
	}

	/**
	 * set a link '#invert_part_attributes_to_ContentType' from 'Attribute' to
	 * 'ContentType'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_attributes_to_ContentType(Item attribute, Item value) throws CadseException {
		attribute.setOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_CONTENT_TYPE, value);
	}

	/**
	 * get links '#invert_part_attributes_to_BeanExporter' from 'Attribute' to
	 * 'BeanExporter'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_attributes_to_BeanExporterLink(Item attribute) {
		return attribute.getOutgoingLink(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN_EXPORTER);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_BeanExporterAll(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN_EXPORTER, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_BeanExporter(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN_EXPORTER, true);
	}

	/**
	 * set a link '#invert_part_attributes_to_BeanExporter' from 'Attribute' to
	 * 'BeanExporter'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_attributes_to_BeanExporter(Item attribute, Item value) throws CadseException {
		attribute.setOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN_EXPORTER, value);
	}

	/**
	 * get links '#invert_part_attributes_to_BeanFieldController' from
	 * 'Attribute' to 'BeanFieldController'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_attributes_to_BeanFieldControllerLink(Item attribute) {
		return attribute.getOutgoingLink(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN_FIELD_CONTROLLER);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_BeanFieldControllerAll(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN_FIELD_CONTROLLER,
				false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_BeanFieldController(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN_FIELD_CONTROLLER,
				true);
	}

	/**
	 * set a link '#invert_part_attributes_to_BeanFieldController' from
	 * 'Attribute' to 'BeanFieldController'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_attributes_to_BeanFieldController(Item attribute, Item value)
			throws CadseException {
		attribute.setOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_BEAN_FIELD_CONTROLLER, value);
	}

	/**
	 * get links '#invert_part_attributes_to_Struct' from 'Attribute' to
	 * 'Struct'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_attributes_to_StructLink(Item attribute) {
		return attribute.getOutgoingLink(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_STRUCT);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_StructAll(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_STRUCT, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_attributes_to_Struct(Item attribute) {
		return attribute.getOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_STRUCT, true);
	}

	/**
	 * set a link '#invert_part_attributes_to_Struct' from 'Attribute' to
	 * 'Struct'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_attributes_to_Struct(Item attribute, Item value) throws CadseException {
		attribute.setOutgoingItem(WorkspaceCST.ATTRIBUTE_lt__$_INVERT_PART_ATTRIBUTES_TO_STRUCT, value);
	}

	/**
	 * @generated
	 */
	public static final boolean isDevGeneratedAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(WorkspaceCST.ATTRIBUTE_at_DEV_GENERATED_, false);
	}

	/**
	 * @generated
	 */
	public static final void setDevGeneratedAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(WorkspaceCST.ATTRIBUTE_at_DEV_GENERATED_, value);
		} catch (Throwable t) {

		}
	}

	/** The Constant UUID_ATTRIBUTE. */
	public static final String	UUID_ATTRIBUTE	= "UUID_ATTRIBUTE";

	/**
	 * Gets the uUID.
	 * 
	 * @param attribute
	 *            object (item) the attribute
	 * 
	 * @return the uUID
	 */
	public static CompactUUID getUUID(Item itemtype) {
		String uuid_str = itemtype.getAttribute(UUID_ATTRIBUTE);
		if (uuid_str == null || uuid_str.length() == 0) {
			CompactUUID uuid = CompactUUID.randomUUID();
			uuid_str = uuid.toString();
			try {
				itemtype.setAttribute(UUID_ATTRIBUTE, uuid_str);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return uuid;
		}
		return new CompactUUID(uuid_str);

	}

	public ItemType getCadseRootType() {
		return CadseRootCST.ATTRIBUTE_TYPE;
	}

	public void addCompleteAttributeDefinition(CAttType attType) {
	}

	public void generateAttributeRefCst(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
			Set<String> imports) {
		Class<?> cl = getAttributeDefinitionTypeJava();
		if (cl != null) {
			if (AttributeManager.isIsListAttribute(attribute)) {
				appendCST2(cxt, sb, absItemType, attribute, imports, ListAttributeType.class, getTypeJava(false));
			} else {
				appendCST(cxt, sb, absItemType, attribute, imports, cl);
			}
		}
	}

	protected void appendCST(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
			Set<String> imports, Class<?> cl, IType... params) {
		sb.appendGeneratedTag();
		sb.newline().append("public static ");
		sb.append(cl.getSimpleName());
		if (cl.getTypeParameters().length != 0) {
			sb.append("<");
			if (params.length == 0) {
				sb.append("?");
			} else {
				for (int i = 0; i < params.length; i++) {
					IType p = params[i];
					if (i != 0) {
						sb.append(",");
					}
					sb.append(p.getElementName());
					imports.add(p.getFullyQualifiedName());
				}
			}
			sb.append(">");
		}

		sb.append(" ").append(GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append("_;");
		imports.add(cl.getName());
	}

	protected void appendCST(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
			Set<String> imports, Class<?> cl, Class<?> cl2, IType... params) {
		sb.appendGeneratedTag();
		sb.newline().append("public static ");
		sb.append(cl.getSimpleName());
		if (cl.getTypeParameters().length != 0) {
			sb.append("<");
			sb.append(cl2.getSimpleName());

			if (cl2.getTypeParameters().length != 0) {
				sb.append("<");
				if (params.length == 0) {
					sb.append("?");
				} else {
					for (int i = 0; i < params.length; i++) {
						IType p = params[i];
						if (i != 0) {
							sb.append(",");
						}
						sb.append(p.getElementName());
						imports.add(p.getFullyQualifiedName());
					}
				}
				sb.append(">");
			}

			sb.append(">");
		}

		sb.append(" ").append(GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append("_;");
		imports.add(cl.getName());
	}

	protected void appendCST2(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
			Set<String> imports, Class<?> cl, Class<?>... params) {
		sb.appendGeneratedTag();
		sb.newline().append("public static ");
		sb.append(cl.getSimpleName());
		if (cl.getTypeParameters().length != 0) {
			sb.append("<");
			if (params.length == 0) {
				sb.append("?");
			} else {
				for (int i = 0; i < params.length; i++) {
					Class<?> p = params[i];
					if (i != 0) {
						sb.append(",");
					}
					sb.append(p.getSimpleName());
					imports.add(p.getName());
				}
			}
			sb.append(">");
		}

		sb.append(" ").append(GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append("_;");
		imports.add(cl.getName());
	}

	public Class<?> getTypeJava(boolean primitive) {
		return Object.class;
	}

	public Class<?> getAttributeDefinitionTypeJava() {
		return fr.imag.adele.cadse.core.impl.attribute.AttributeType.class;
	}

	public int getCadseRootFlag(Item attribute) {
		return (AttributeManager.isRequireAttribute(attribute) ? Item.MUST_BE_INITIALIZED_AT_CREATION_TIME : 0)
				+ (AttributeManager.isTransientAttribute(attribute) ? Item.TRANSIENT : 0)
				+ (!AttributeManager.isCannotBeUndefinedAttribute(attribute) ? Item.CAN_BE_UNDEFINED : 0)
				+ (AttributeManager.isClassAttributeAttribute(attribute) ? Item.IS_META_ATTRIBUTE : 0)
				+ (AttributeManager.isCachedAttribute(attribute) ? Item.PERSISTENCE_CACHE : 0);
	}

	public Object getCadseRootAttributeValue(ContextVariable cxt, IAttributeType<?> attType, Item attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCadseRootRequireAttribute(Item attribute) {
		return isRequireAttribute(attribute);
	}

	public String getJavaTypeDefaultValue(Item attribute) {
		String defaultValue = attribute.getAttribute(WorkspaceCST.ATTRIBUTE_at_DEFAULT_VALUE_);
		if (defaultValue == null || defaultValue.length() == 0) {
			defaultValue = generatedDefaultValue();
		}

		if (defaultValue == null || defaultValue.length() == 0) {
			defaultValue = "null";
		}
		return defaultValue;
	}

	protected String generatedDefaultValue() {
		return null;
	}

	public String getJavaTypeConvertFromMethod() {
		return null;
	}

	public String getJavaTypeConvertToMethod() {
		return null;
	}

	public String exp_to_string() {
		return null;
	}

	@Override
	public String canDeleteItem(Item item) {
		if (isDevGeneratedAttribute(item)) {
			return "is dev generated attribute definition";
		}
		return null;
	}

	@Override
	public String canDeleteLink(Link link) {
		if (isDevGeneratedAttribute(link.getSource())) {
			return "is dev generated attribute definition";
		}
		return null;
	}
}

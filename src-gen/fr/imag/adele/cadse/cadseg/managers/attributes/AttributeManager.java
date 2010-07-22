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
import java.util.UUID;

import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.enumdef.TWCommitKind;
import fr.imag.adele.cadse.core.enumdef.TWEvol;
import fr.imag.adele.cadse.core.enumdef.TWUpdateKind;
import fr.imag.adele.cadse.core.impl.CadseIllegalArgumentException;
import fr.imag.adele.cadse.core.util.Convert;

/**
 * The Class AttributeManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class AttributeManager extends DefaultWorkspaceManager implements IItemManager, IModelWorkspaceManager {

	/**
	 * Checks if is link attribute.
	 * 
	 * @param item
	 *            the item
	 * @return true, if is link attribute
	 */
	public static boolean isLinkAttribute(Item item) {
		return item.getType() == CadseGCST.LINK_TYPE || item.getType() == CadseGCST.CONTENT_LINK_TYPE;
	}

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
	 * @see fede.workspace.model.manager.DefaultItemManager#computeUniqueName(fr.imag.adele.cadse.core.Item,
	 * java.lang.String, fr.imag.adele.cadse.core.Item, fr.imag.adele.cadse.core.LinkType)
	 */
	@Override
	public String computeQualifiedName(Item item, String shortid, Item parent, LinkType lt) {
		return Item.NO_VALUE_STRING;
	}


	/*
	 * (non-Javadoc)
	 * @see model.workspace.workspace.DefaultWorkspaceManager#getWorkspaceModel(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Item getWorkspaceModel(Item source) {
		return source.getPartParent().getPartParent().getPartParent();
	}

	/*
	 * (non-Javadoc)
	 * @see model.workspace.workspace.DefaultWorkspaceManager#getDisplayName(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public String getDisplayName(Item item) {
		return item.getName();
	}

	/* @not generated */
	/**
	 * Checks if is checks if is list attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * @return true, if is checks if is list attribute
	 */
	public static final boolean isIsListAttribute(Item attribute) {
		if (attribute.getType() == CadseGCST.LINK_TYPE) { // correction bug
			// Une definition d'un type de link n'a pas l'attribut 'is-list'
			// pour dire si c'est une liste ou non
			// C'est l'attribut max contient cette information sous une autre
			// forme : un entier. '-1' siginifie qu'il n'y a pas de limite.
			// 'unbounded' en anglais.
			int max = LinkTypeManager.getMax(attribute);
			return max == -1 || max > 1;
		}
		Object value = attribute.getAttribute(CadseGCST.ATTRIBUTE_at_IS_LIST_);

		try {
			return Convert.toBooleanFalseIfNull(value);
		}
		catch (Throwable t) {
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
	 * @generated
	 */
	public static final void setIsListAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_IS_LIST_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the default value attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * @return the default value attribute
	 * @not generated
	 */
	public static final String getDefaultValueAttribute(Item attribute) {
		Object value = attribute.getAttribute(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);
		if (value == null) {
			return null;
		}

		try {
			return value.toString();
		}
		catch (Throwable t) {
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
	 * @generated
	 */
	public static final void setDefaultValueAttribute(Item attribute, String value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Checks if is require attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * @return true, if checks if is require attribute
	 * @generated
	 */
	public static boolean isRequireAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(CadseGCST.ATTRIBUTE_at_REQUIRE_, false);
	}

	/**
	 * Sets the require attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * @param value
	 *            the value
	 * @generated
	 */
	public static void setRequireAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_REQUIRE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isCannotBeUndefinedAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_, false);
	}

	/**
	 * @generated
	 */
	public static final void setCannotBeUndefinedAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isNatifAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(CadseGCST.ATTRIBUTE_at_NATIF_, false);
	}

	/**
	 * @generated
	 */
	public static final void setNatifAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_NATIF_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isTransientAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(CadseGCST.ATTRIBUTE_at_TRANSIENT_, false);
	}

	/**
	 * @generated
	 */
	public static final void setTransientAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_TRANSIENT_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isFinalAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(CadseGCST.ATTRIBUTE_at_FINAL_, false);
	}

	/**
	 * @generated
	 */
	public static final void setFinalAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_FINAL_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final boolean isShowInDefaultCpAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_, false);
	}

	/**
		@generated
	*/
	public static final void setShowInDefaultCpAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final boolean isTWRevSpecificAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_, true);
	}

	/**
	 * @generated
	 */
	public static final void setTWRevSpecificAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final TWEvol getTWEvolAttribute(Item attribute) {
		Object value = attribute.getAttribute(CadseGCST.ATTRIBUTE_at_TWEVOL_);
		return Convert.toEnum(value,CadseGCST.ATTRIBUTE_at_TWEVOL_,TWEvol.twImmutable);
	}

	/**
	 * @generated
	 */
	public static final void setTWEvolAttribute(Item attribute, TWEvol value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_TWEVOL_, value);
		} catch (Throwable t) {
		}
	}

	/**
	 * @generated
	 */
	public static final TWUpdateKind getTWUpdateKindAttribute(Item attribute) {
		Object value = attribute.getAttribute(CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_);
		return Convert.toEnum(value,CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_,TWUpdateKind.merge);
	}

	/**
	 * @generated
	 */
	public static final void setTWUpdateKindAttribute(Item attribute, TWUpdateKind value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_, value);
		} catch (Throwable t) {
		}
	}

	/**
	 * @generated
	 */
	public static final TWCommitKind getTWCommitKindAttribute(Item attribute) {
		Object value = attribute.getAttribute(CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_);
		return Convert.toEnum(value,CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_,TWCommitKind.conflict);
	}

	/**
	 * @generated
	 */
	public static final void setTWCommitKindAttribute(Item attribute, TWCommitKind value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_, value);
		} catch (Throwable t) {
		}
	}

	/**
	 * get links 'wcListens' from 'Attribute' to 'WCListener'.
	 * 
	 * @generated
	 */
	static public List<Link> getWcListensLink(Item attribute) {
        return attribute.getOutgoingLinks(CadseGCST.ATTRIBUTE_lt_WC_LISTENS);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getWcListensAll(Item attribute) {
        return attribute.getOutgoingItems(CadseGCST.ATTRIBUTE_lt_WC_LISTENS, false);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getWcListens(Item attribute) {
        return attribute.getOutgoingItems(CadseGCST.ATTRIBUTE_lt_WC_LISTENS,true);
    }

	/**
	 * @generated
	 */
	static public void addWcListens(Item attribute, Item value) throws CadseException {
        attribute.addOutgoingItem(CadseGCST.ATTRIBUTE_lt_WC_LISTENS,value);
    }

	/**
	 * @generated
	 */
	static public void removeWcListens(Item attribute, Item value) throws CadseException {
        attribute.removeOutgoingItem(CadseGCST.ATTRIBUTE_lt_WC_LISTENS,value);
    }

	/**
	 * @generated
	 */
	public static final boolean isDevGeneratedAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(CadseGCST.ATTRIBUTE_at_DEV_GENERATED_, false);
	}

	/**
	 * @generated
	 */
	public static final void setDevGeneratedAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_DEV_GENERATED_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final String getIdRuntimeAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(CadseGCST.ATTRIBUTE_at_ID_RUNTIME_, null);
	}

	/**
	 * @generated
	 */
	public static final void setIdRuntimeAttribute(Item attribute, String value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_ID_RUNTIME_, value);
		} catch (Throwable t) {

		}
	}
	
	/**
		@generated
	*/
	public static final boolean isShowInDefaultMpAttribute(Item attribute) {
		return attribute.getAttributeWithDefaultValue(CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_, true);
	}

	/**
		@generated
	*/
	public static final void setShowInDefaultMpAttribute(Item attribute, boolean value) {
		try {
			attribute.setAttribute(CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the uUID.
	 * 
	 * @param attribute
	 *            object (item) the attribute
	 * @return the uUID
	 */
	public static UUID getIdRuntime(Item attribute) {
		if (attribute.isRuntime()) {
			return attribute.getId();
		}

		String uuid_str = getIdRuntimeAttribute(attribute);
		if (uuid_str == null || uuid_str.length() == 0) {
			if (attribute.isReadOnly()) {
				throw new CadseIllegalArgumentException("Cannot set id rutime on {0} of type {1}", attribute.getName(),
						attribute.getPartParent().getName());
			}
			UUID uuid = UUID.randomUUID();
			uuid_str = uuid.toString();
			setIdRuntimeAttribute(attribute, uuid_str);
			return uuid;
		}
		return UUID.fromString(uuid_str);
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
	
//	public ItemType getCadseRootType() {
//		return CadseGCST.ATTRIBUTE;
//	}
//
//	public void addCompleteAttributeDefinition(CAttType attType) {
//	}
//
//	public void generateAttributeRefCst(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
//			Set<String> imports) {
//		Class<?> cl = getAttributeDefinitionTypeJava();
//		if (cl != null) {
//			if (AttributeManager.isIsListAttribute(attribute)) {
//				appendCST2(cxt, sb, absItemType, attribute, imports, ListAttributeType.class, getTypeJava(false));
//			}
//			else {
//				appendCST(cxt, sb, absItemType, attribute, imports, cl);
//			}
//		}
//	}
//
//	protected void appendCST(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
//			Set<String> imports, Class<?> cl, IType... params) {
//		sb.appendGeneratedTag();
//		sb.newline().append("public static ");
//		sb.append(cl.getSimpleName());
//		if (cl.getTypeParameters().length != 0) {
//			sb.append("<");
//			if (params.length == 0) {
//				sb.append("?");
//			}
//			else {
//				for (int i = 0; i < params.length; i++) {
//					IType p = params[i];
//					if (i != 0) {
//						sb.append(",");
//					}
//					sb.append(p.getElementName());
//					imports.add(p.getFullyQualifiedName());
//				}
//			}
//			sb.append(">");
//		}
//
//		sb.append(" ").append(GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append("_;");
//		imports.add(cl.getName());
//	}
//
//	protected void appendCST(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
//			Set<String> imports, Class<?> cl, Class<?> cl2, IType... params) {
//		sb.appendGeneratedTag();
//		sb.newline().append("public static ");
//		sb.append(cl.getSimpleName());
//		if (cl.getTypeParameters().length != 0) {
//			sb.append("<");
//			sb.append(cl2.getSimpleName());
//
//			if (cl2.getTypeParameters().length != 0) {
//				sb.append("<");
//				if (params.length == 0) {
//					sb.append("?");
//				}
//				else {
//					for (int i = 0; i < params.length; i++) {
//						IType p = params[i];
//						if (i != 0) {
//							sb.append(",");
//						}
//						sb.append(p.getElementName());
//						imports.add(p.getFullyQualifiedName());
//					}
//				}
//				sb.append(">");
//			}
//
//			sb.append(">");
//		}
//
//		sb.append(" ").append(GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append("_;");
//		imports.add(cl.getName());
//	}
//
//	protected void appendCST2(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
//			Set<String> imports, Class<?> cl, Class<?>... params) {
//		sb.appendGeneratedTag();
//		sb.newline().append("public static ");
//		sb.append(cl.getSimpleName());
//		if (cl.getTypeParameters().length != 0) {
//			sb.append("<");
//			if (params.length == 0) {
//				sb.append("?");
//			}
//			else {
//				for (int i = 0; i < params.length; i++) {
//					Class<?> p = params[i];
//					if (i != 0) {
//						sb.append(",");
//					}
//					sb.append(p.getSimpleName());
//					imports.add(p.getName());
//				}
//			}
//			sb.append(">");
//		}
//
//		sb.append(" ").append(GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append("_;");
//		imports.add(cl.getName());
//	}
//
//	public Class<?> getTypeJava(boolean primitive) {
//		return Object.class;
//	}
//
//	public Class<?> getAttributeDefinitionTypeJava() {
//		return fr.imag.adele.cadse.core.impl.attribute.AttributeType.class;
//	}
//
//	public int getCadseRootFlag(Item attribute) {
//		return (AttributeManager.isShowInDefaultCpAttribute(attribute) ? Item.SHOW_IN_DEFAULT_CP : 0)
//				| (AttributeManager.isTransientAttribute(attribute) ? Item.TRANSIENT : 0)
//				| (!AttributeManager.isCannotBeUndefinedAttribute(attribute) ? Item.CAN_BE_UNDEFINED : 0)
//				| (AttributeManager.isShowInDefaultMpAttribute(attribute) ? Item.SHOW_IN_DEFAULT_MP : 0);
//	}
//
//	public Object getCadseRootAttributeValue(ContextVariable cxt, IAttributeType<?> attType, Item attribute) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public boolean isCadseRootRequireAttribute(Item attribute) {
//		return isRequireAttribute(attribute);
//	}
//
//	public String getJavaTypeDefaultValue(Item attribute) {
//		String defaultValue = attribute.getAttribute(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);
//		if (defaultValue == null || defaultValue.length() == 0) {
//			defaultValue = generatedDefaultValue();
//		}
//
//		if (defaultValue == null || defaultValue.length() == 0) {
//			defaultValue = "null";
//		}
//		return defaultValue;
//	}
//
//	protected String generatedDefaultValue() {
//		return null;
//	}
//
//	public String getJavaTypeConvertFromMethod() {
//		return null;
//	}
//
//	public String getJavaTypeConvertToMethod() {
//		return null;
//	}
//
//	public String exp_to_string() {
//		return null;
//	}
//
//	
//
//	/*
//	 * (non-Javadoc)
//	 * @see
//	 * fr.imag.adele.cadse.core.root.managers.attribute.InitModelLoadAndWrite#loadAttributeDefinition(fr.imag.adele.
//	 * fede.workspace.as.initmodel.IInitModel, fr.imag.adele.cadse.core.IWorkspaceLogique,
//	 * fr.imag.adele.cadse.core.ItemType, fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType, java.lang.String)
//	 */
//	@Override
//	public IAttributeType<?> loadAttributeDefinition(IInitModel initModel, LogicalWorkspace theWorkspaceLogique,
//			TypeDefinition parent, CValuesType type, String cadseName) throws CadseException {
//		return null;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * @see
//	 * fr.imag.adele.cadse.core.root.managers.attribute.InitModelLoadAndWrite#writeAttributeDefinition(fr.imag.adele
//	 * .fede.workspace.as.initmodel.jaxb.ObjectFactory, fr.imag.adele.cadse.core.var.ContextVariable,
//	 * fr.imag.adele.cadse.core.root.managers.attribute.IAttributeCadsegForGenerate,
//	 * fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType, fr.imag.adele.cadse.core.Item)
//	 */
//	@Override
//	public void writeAttributeDefinition(ObjectFactory factory, ContextVariable cxt,
//			IAttributeCadsegForGenerate cadsegManager, CValuesType cvt, Item attribute) {
//		
//		
//		cvt.setTypeName(attribute.getType().getId().toString());
//		cvt.setMin(cadsegManager.isCadseRootRequireAttribute(attribute) ? 1 : 0);
//		if (isIsListAttribute(attribute)) {
//			if (attribute.getType() == CadseGCST.LIST)
//				cvt.setFlag(cadsegManager.getCadseRootFlag(attribute) & (Item.SHOW_IN_DEFAULT_CP | Item.SHOW_IN_DEFAULT_MP));
//			else
//				cvt.setFlag(cadsegManager.getCadseRootFlag(attribute) & ~Item.CAN_BE_UNDEFINED);
//		}
//		else
//			cvt.setFlag(cadsegManager.getCadseRootFlag(attribute));
//	}
}

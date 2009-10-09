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

package fr.imag.adele.cadse.cadseg.generate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fede.workspace.tool.loadmodel.model.jaxb.CAbsItemType;
import fede.workspace.tool.loadmodel.model.jaxb.CAction;
import fede.workspace.tool.loadmodel.model.jaxb.CActionContributor;
import fede.workspace.tool.loadmodel.model.jaxb.CAttType;
import fede.workspace.tool.loadmodel.model.jaxb.CCadse;
import fede.workspace.tool.loadmodel.model.jaxb.CCadseRef;
import fede.workspace.tool.loadmodel.model.jaxb.CExtensionItemType;
import fede.workspace.tool.loadmodel.model.jaxb.CItemType;
import fede.workspace.tool.loadmodel.model.jaxb.CLinkType;
import fede.workspace.tool.loadmodel.model.jaxb.CMenuAction;
import fede.workspace.tool.loadmodel.model.jaxb.CMetaAttribute;
import fede.workspace.tool.loadmodel.model.jaxb.CPage;
import fede.workspace.tool.loadmodel.model.jaxb.CPages;
import fede.workspace.tool.loadmodel.model.jaxb.CValuesType;
import fede.workspace.tool.loadmodel.model.jaxb.CommitKindType;
import fede.workspace.tool.loadmodel.model.jaxb.EvolutionDestinationKindType;
import fede.workspace.tool.loadmodel.model.jaxb.EvolutionKindType;
import fede.workspace.tool.loadmodel.model.jaxb.ObjectFactory;
import fede.workspace.tool.loadmodel.model.jaxb.UpdateKindType;
import fede.workspace.tool.loadmodel.model.jaxb.ValueTypeType;
import fr.imag.adele.cadse.cadseg.IAttributeGenerator;
import fr.imag.adele.cadse.cadseg.ItemShortNameComparator;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.ext.actions.ExtItemTypeExt;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.actions.MenuAbstractManager;
import fr.imag.adele.cadse.cadseg.managers.actions.MenuManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.CreationDialogManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ExtItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ModificationDialogManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.PageManager;
import fr.imag.adele.cadse.core.CadseRootCST;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.enumdef.TWCommitKind;
import fr.imag.adele.cadse.core.enumdef.TWDestEvol;
import fr.imag.adele.cadse.core.enumdef.TWEvol;
import fr.imag.adele.cadse.core.enumdef.TWUpdateKind;
import fr.imag.adele.cadse.core.impl.ui.ConfigurablePageFactory;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.as.initmodel.InitModelLoadAndWrite;

/**
 * The Class WSModelGenerateModel.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class GenerateCadseDefinitionModel {

	static Map<ItemType, IAttributeGenerator>		generators		= new HashMap<ItemType, IAttributeGenerator>();

	static List<GenerateCadseDefinitionModelExt>	generatorsExt	= new ArrayList<GenerateCadseDefinitionModelExt>();

	/**
	 * Generate cadse.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the c cadse
	 */
	public static CCadse generateCADSE(Item cadseDefinition) {

		ObjectFactory factory = new ObjectFactory();
		ContextVariable cxt = new ContextVariable();
		CCadse cadse = factory.createCCadse();
		cadse.setName(CadseDefinitionManager.RESOURCE_SUFFIX + cadseDefinition.getName());
		// ItemTypeManager
		int version = CadseDefinitionManager.getVersion(cadseDefinition);
		cadse.setVersion(version);
		cadse.setId(CadseDefinitionManager.getUUID(cadseDefinition).toString());
		cadse.setIdCadseDefinition(cadseDefinition.getId().toString());
		cadse.setDescription(CadseDefinitionManager.getDescriptionAttribute(cadseDefinition));
		String displayNameAttribute = CadseDefinitionManager.getDisplayNameAttribute(cadseDefinition);
		if (displayNameAttribute != null && displayNameAttribute.length() > 0) {
			cadse.setDisplayName(displayNameAttribute);
		}
		String classNameCST = GenerateJavaIdentifier.javaClassNameFileCST_FromCadseDefinition(cxt, cadseDefinition);
		String packageNameCST = GenerateJavaIdentifier.javaPackageNameFileCST_FromCadseDefinition(cxt, cadseDefinition);

		Collection<Item> extendsItems = CadseDefinitionManager.getExtends(cadseDefinition);
		for (Item item : extendsItems) {
			CCadseRef cadseref = factory.createCCadseRef();
			cadseref.setName(CadseDefinitionManager.getUniqueName(item));
			cadseref.setId(CadseDefinitionManager.getUUID(item).toString());
			cadseref.setIdCadseDefinition(item.getId().toString());
			cadse.getCadseRef().add(cadseref);
		}

		cadse.setCstClass(packageNameCST + "." + classNameCST);
		// name="" bundle-id="" cst-class="" id=""
		Item theDataModel = CadseDefinitionManager.getMainDataModel(cadseDefinition);
		Item[] itemTypes = ItemTypeManager.getAllItemType(theDataModel);
		Arrays.sort(itemTypes, new ItemShortNameComparator());
		for (Item itemType : itemTypes) {
			Item manager = ItemTypeManager.getManager(itemType);
			if (manager == null) {
				System.err.println("Generate cadse model : No manager found !! for " + itemType);
				continue;
			}

			CItemType cit = factory.createCItemType();

			cadse.getItemType().add(cit);
			setItemType(cxt, itemType, manager, cit);
			generateCommonInformation(cxt, factory, itemType, cit);
		}

		Item[] extItemTypes = ItemTypeManager.getAllExtItemType(theDataModel);
		for (Item extIt : extItemTypes) {
			CExtensionItemType ceit = factory.createCExtensionItemType();
			cadse.getExtItemType().add(ceit);
			// TODO set the name and other information
			// ceit.setName(value);
			// ceit.setQualifiedName(value);
			// ceit.setId(value);
			// ceit.setMetaType(value)
			// ceit.setSuperTypeName(value);

			Item refItemType = ExtItemTypeManager.getRefType(extIt);
			if (refItemType != null && refItemType.isResolved()) {
				CompactUUID uuid = ItemTypeManager.getUUID(refItemType);
				ceit.setItemTypeSource(uuid.toString());
			}

			generateCommonInformation(cxt, factory, extIt, ceit);
		}

		for (GenerateCadseDefinitionModelExt g : generatorsExt) {
			g.generate(cadse);
		}

		return cadse;
	}

	/**
	 * Generate page link action.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param factory
	 *            the factory
	 * @param abstractItemType
	 *            the abstract item type
	 * @param cit
	 *            the cit
	 */
	private static void generateCommonInformation(ContextVariable cxt, ObjectFactory factory, Item abstractItemType,
			CAbsItemType cit) {
		// creation page
		generateCreationDialog(cxt, factory, abstractItemType, cit);
		generateModificationDialog(cxt, factory, abstractItemType, cit);
		generateAttributes(cxt, factory, abstractItemType, cit);

		Item menu = ExtItemTypeExt.getActionsModel(abstractItemType);
		if (menu != null) {
			generateMenu(cxt, factory, menu, cit, null);
		}
	}

	/**
	 * Generate attributes.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param factory
	 *            the factory pour creer des factory
	 * @param abstractItemType
	 *            un item type ou un extended item type
	 * @param cit
	 *            the cit
	 */
	private static void generateAttributes(ContextVariable cxt, ObjectFactory factory, Item abstractItemType,
			CAbsItemType cit) {
		Collection<Item> outgoingItem = abstractItemType.getOutgoingItems(
				WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES, true);
		Item[] attributeItems = outgoingItem.toArray(new Item[outgoingItem.size()]);
		Arrays.sort(attributeItems, new ItemShortNameComparator());
		for (Item attribute : attributeItems) {
			if (!attribute.isResolved()) {
				continue;
			}
			if (AttributeManager.isClassAttributeAttribute(attribute)) {
				if (AttributeManager.isLinkAttribute(attribute)) {
					continue;
				}
				CMetaAttribute ma = factory.createCMetaAttribute();
				CValuesType cvt = attributeToCValueType(cxt, factory, abstractItemType, attribute);
				ma.setValue(cvt);
				ma.setKey(cvt.getKey());

				cvt.setKey(null);
				cvt.setValue(AttributeManager.getDefaultValueAttribute(attribute));
				// cvt.setMin(1);
				cvt.setFlag(null);
				cvt.setId(null);
				cvt.setCstName(null);

				cit.getMetaAttribute().add(ma);
				continue;
			}
			if (AttributeManager.isLinkAttribute(attribute)) {

				Item itemTypeDest = LinkManager.getDestination(attribute);
				if (itemTypeDest == null) {
					continue;
				}

				CLinkType clt = generateOutgoingLink(cxt, factory, abstractItemType, cit, attribute, itemTypeDest);
				setLinkEvolutionInfo(clt, attribute);
			} else {
				CValuesType cvt;
				AttributeManager manager = (AttributeManager) attribute.getType().getItemManager();
				ItemType cadseRootItemType = manager.getCadseRootType();
				if (cadseRootItemType != null) {
					if (cadseRootItemType.isAbstract()) {
						continue;
					}

					InitModelLoadAndWrite cadseRootManager = (InitModelLoadAndWrite) cadseRootItemType.getItemManager();
					cvt = factory.createCValuesType();
					// attribute name
					cvt.setKey(attribute.getName());

					// attribute id
					cvt.setId(AttributeManager.getUUID(attribute).toString());

					// add constance if the meta attributes, cst_class_name and
					// cst_package_name, are defined.
					cvt.setCstName(GenerateJavaIdentifier.cstAttribute(cxt, attribute, abstractItemType) + "_");

					// evolution information
					cvt.setTwEvolution(convert(AttributeManager.getTWEvolAttribute(attribute)));
					cvt.setTwCommit(convert(AttributeManager.getTWCommitKindAttribute(attribute)));
					cvt.setTwUpdate(convert(AttributeManager.getTWUpdateKindAttribute(attribute)));
					cvt.setTwRevSpecific(AttributeManager.isTWRevSpecificAttribute(attribute));

					// default value
					cvt.setValue(AttributeManager.getDefaultValueAttribute(attribute));
					// add in type
					cit.getAttributeType().add(cvt);

					// cas de la list
					if (AttributeManager.isIsListAttribute(attribute)) {
						cvt.setType(ValueTypeType.LIST);
						cvt.setMax(AttributeManager.getMinAttribute(attribute));
						cvt.setMin(AttributeManager.getMaxAttribute(attribute));
						ItemType cadseRootList = CadseRootCST.LIST_ATTRIBUTE_TYPE;
						cvt.setTypeName(cadseRootList.getId().toString());
						InitModelLoadAndWrite cadseListRootManager = (InitModelLoadAndWrite) cadseRootList
								.getItemManager();
						cadseListRootManager.writeAttributeDefinition(factory, cxt, manager, cvt, attribute);

						// creer une deuxième description pour dècrire le type
						// du contenu de la liste
						CValuesType ncvt = factory.createCValuesType();
						cvt.getElement().add(ncvt);

						cvt = ncvt;
					}

					// the id of the attribute type of attribute definition
					cvt.setTypeName(cadseRootItemType.getId().toString());

					cadseRootManager.writeAttributeDefinition(factory, cxt, manager, cvt, attribute);
					continue;

				}

				IAttributeGenerator gen = generators.get(attribute.getType());
				cvt = (gen != null) ? gen.attributeTypeToCValueType(cxt, factory, abstractItemType, attribute)
						: attributeToCValueType(cxt, factory, abstractItemType, attribute);

				cvt.setTwEvolution(convert(AttributeManager.getTWEvolAttribute(attribute)));
				cvt.setTwCommit(convert(AttributeManager.getTWCommitKindAttribute(attribute)));
				cvt.setTwUpdate(convert(AttributeManager.getTWUpdateKindAttribute(attribute)));
				cvt.setTwRevSpecific(AttributeManager.isTWRevSpecificAttribute(attribute));
				cvt.setValue(AttributeManager.getDefaultValueAttribute(attribute));
				cit.getAttributeType().add(cvt);

				if (cvt == null) {
					CAttType attType = factory.createCAttType();
					// attType.set
					if (cadseRootItemType == null || cadseRootItemType.isAbstract()) {
						return;
					}

					attType.setType(cadseRootItemType.getId().toString());
					attType.setTwEvolution(convert(AttributeManager.getTWEvolAttribute(attribute)));
					attType.setTwCommit(convert(AttributeManager.getTWCommitKindAttribute(attribute)));
					attType.setTwUpdate(convert(AttributeManager.getTWUpdateKindAttribute(attribute)));
					attType.setTwRevSpecific(AttributeManager.isTWRevSpecificAttribute(attribute));
					attType.setValue(AttributeManager.getDefaultValueAttribute(attribute));

					attType.setFlag(manager.getCadseRootFlag(attribute));
					attType.setShortName(attribute.getName());
					attType.setId(AttributeManager.getUUID(attribute).toString());

					attType.setCstName(GenerateJavaIdentifier.cstAttribute(cxt, attribute, abstractItemType) + "_");

					manager.addCompleteAttributeDefinition(attType);

					cit.getAttributeDefinition().add(attType);
				}
			}
		}
	}

	private static void setLinkEvolutionInfo(CLinkType clt, Item attribute) {
		clt.setTwEvolution(convert(AttributeManager.getTWEvolAttribute(attribute)));
		clt.setTwCommit(convert(AttributeManager.getTWCommitKindAttribute(attribute)));
		clt.setTwUpdate(convert(AttributeManager.getTWUpdateKindAttribute(attribute)));
		clt.setTwRevSpecific(AttributeManager.isTWRevSpecificAttribute(attribute));
		clt.setTwEvolDestination(convert(LinkManager.getTWDestEvolAttribute(attribute)));
		clt.setTwCoupled(LinkManager.isTWCoupledAttribute(attribute));
	}

	private static UpdateKindType convert(TWUpdateKind updateKindAttribute) {
		if (updateKindAttribute == null) {
			return UpdateKindType.MERGE;
		}
		switch (updateKindAttribute) {
			case compute:
				return UpdateKindType.COMPUTE;
			case merge:
				return UpdateKindType.MERGE;
			case none:
				return UpdateKindType.NONE;

			default:
				break;
		}
		return UpdateKindType.MERGE;
	}

	private static EvolutionDestinationKindType convert(TWDestEvol destEvolAttribute) {
		if (destEvolAttribute == null) {
			return EvolutionDestinationKindType.IMMUTABLE;
		}
		switch (destEvolAttribute) {
			case branch:
				return EvolutionDestinationKindType.BRANCH;
			case effective:
				return EvolutionDestinationKindType.EFFECTIVE;
			case finalDest:
				return EvolutionDestinationKindType.FINAL;
			case immutable:
				return EvolutionDestinationKindType.IMMUTABLE;
			case mutable:
				return EvolutionDestinationKindType.MUTABLE;

			default:
				break;
		}
		return EvolutionDestinationKindType.MUTABLE;
	}

	private static CommitKindType convert(TWCommitKind commitKindAttribute) {
		if (commitKindAttribute == null) {
			return CommitKindType.CONFLICT;
		}
		switch (commitKindAttribute) {
			case conflict:
				return CommitKindType.CONFLICT;
			case none:
				return CommitKindType.NONE;
			case reconcile:
				return CommitKindType.RECONCILE;

			default:
				break;
		}
		return CommitKindType.CONFLICT;
	}

	private static EvolutionKindType convert(TWEvol evolAttribute) {
		if (evolAttribute == null) {
			return EvolutionKindType.IMMUTABLE;
		}
		switch (evolAttribute) {
			case twFinal:
				return EvolutionKindType.FINAL;
			case twImmutable:
				return EvolutionKindType.IMMUTABLE;
			case twMutable:
				return EvolutionKindType.MUTABLE;
			case twTransient:
				return EvolutionKindType.TRANSIENT;

			default:
				break;
		}
		return EvolutionKindType.IMMUTABLE;
	}

	/**
	 * Attribute to c value type.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param factory
	 *            the factory
	 * @param abstractItemType
	 *            the abstract item type
	 * @param attribute
	 *            the attribute
	 * 
	 * @return the c values type
	 */
	private static CValuesType attributeToCValueType(ContextVariable cxt, ObjectFactory factory, Item abstractItemType,
			Item attribute) {
		CValuesType cvt = factory.createCValuesType();
		// On retrourne toujours cette objet. la cvt pointe sur la description �
		// remplir
		final CValuesType ret = cvt;

		cvt.setKey(attribute.getName());
		cvt.setId(AttributeManager.getUUID(attribute).toString());

		// add constance if the meta attributes, cst_class_name and
		// cst_package_name, are defined.
		cvt.setCstName(GenerateJavaIdentifier.cstAttribute(cxt, attribute, abstractItemType) + "_");

		if (AttributeManager.isIsListAttribute(attribute)) {
			cvt.setType(ValueTypeType.LIST);
			cvt.setMax(AttributeManager.getMinAttribute(attribute));
			cvt.setMin(AttributeManager.getMaxAttribute(attribute));

			// creer une deuxième description pour dècrire le type du contenu de
			// la liste
			CValuesType ncvt = factory.createCValuesType();
			cvt.getElement().add(ncvt);

			cvt = ncvt;
		} else if (attribute.getType() == WorkspaceCST.SYMBOLIC_BIT_MAP) {
			cvt.setType(ValueTypeType.SYMBOLIC_BIT_MAP);
			cvt.setMin(AttributeManager.isRequireAttribute(attribute) ? 1 : 0);
			// cvt.setFlag(getFlag(attribute));
			CValuesType symbols = factory.createCValuesType();
			// symbols
			// cvt.getElement().add(symbols );
		}
		return ret;
	}

	/**
	 * Generate menu.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param factory
	 *            the factory
	 * @param menu
	 *            the menu
	 * @param cit
	 *            the cit
	 * @param pathAttribute
	 *            the path attribute
	 */
	private static void generateMenu(ContextVariable cxt, ObjectFactory factory, Item menu, CAbsItemType cit,
			String pathAttribute) {
		Collection<Item> children = MenuManager.getChildren(menu);
		for (Item child : children) {
			if (child.getType() == WorkspaceCST.MENU) {
				CMenuAction cma = factory.createCMenuAction();
				cit.getMenu().add(cma);
				cma.setName(child.getName());
				cma.setLabel(MenuAbstractManager.getLabelAttribute(child));
				cma.setIcon(MenuAbstractManager.getIconPath(child));
				cma.setKind(0);
				String newpathAttribute = null;
				if (pathAttribute != null) {
					cma.setPath(pathAttribute);
					newpathAttribute = pathAttribute + "/" + menu.getName();
				} else {
					cma.setPath(MenuAbstractManager.getPathAttribute(child));
					newpathAttribute = child.getName();
				}
				generateMenu(cxt, factory, child, cit, newpathAttribute);
			} else if (child.getType() == WorkspaceCST.MENU_ACTION) {
				CAction ca = factory.createCAction();
				cit.getAction().add(ca);
				ca.setName(child.getName());
				ca.setLabel(MenuAbstractManager.getLabelAttribute(child));
				ca.setIcon(MenuAbstractManager.getIconPath(child));
				ca.setForNb(-1);
				if (pathAttribute != null) {
					ca.setPath(pathAttribute);
				} else {
					ca.setPath(MenuAbstractManager.getPathAttribute(child));
				}
				ca.setClassAction(GenerateJavaIdentifier.qualifiedMenuAction(cxt, child));
			} else if (child.getType() == WorkspaceCST.DYNAMIC_ACTIONS) {
				CActionContributor cac = factory.createCActionContributor();
				cit.getActionContributor().add(cac);
				JavaFileContentManager cm = (JavaFileContentManager) child.getContentItem();
				cac.setClazz(cm.getPackageName(cxt) + "." + cm.getClassName(cxt));
			}
		}
	}

	/**
	 * Sets the item type.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param itemType
	 *            the item type
	 * @param manager
	 *            the manager
	 * @param cit
	 *            the cit
	 */
	private static void setItemType(ContextVariable cxt, Item itemType, Item manager, CItemType cit) {
		cit.setId(ItemTypeManager.getUUID(itemType).toString());
		cit.setIntID(ItemTypeManager.getIntID(itemType));
		cit.setName(itemType.getName());
		cit.setIsRootElement(ItemTypeManager.isIsRootElementAttribute(itemType));
		cit.setHasContent(ItemTypeManager.isHasContentAttribute(itemType));
		cit.setQualifiedName(ItemTypeManager.getQualifiedName(itemType));

		cit.setHidden(ItemTypeManager.isIsHiddenAttribute(itemType));
		cit.setMetaType(ItemTypeManager.getMetaType(itemType));
		IJavaElement je = JavaCore.create(ItemTypeManager.getItemFactoryClass(itemType));
		cit.setFactoryClass((je != null && je instanceof IType) ? ((IType) je).getFullyQualifiedName() : null);
		cit.setPackageName(ItemTypeManager.getSubPackageFromCategory(itemType));

		Item superItemType = ItemTypeManager.getSuperType(itemType);
		if (superItemType != null) {
			cit.setSuperTypeName(ItemTypeManager.getUUID(superItemType).toString());
		} else {
			if (ItemTypeManager.isIsMetaItemTypeAttribute(itemType)) {
				cit.setSuperTypeName(CadseRootCST.META_ITEM_TYPE.getId().toString());
			}
		}

		String humanTypeName = ManagerManager.getHumanNameAttribute(manager);
		if (humanTypeName != null && humanTypeName.length() != 0) {
			cit.setDisplayName(humanTypeName);
		} else {
			cit.setDisplayName(itemType.getName());
		}

		cit.setIcon(ManagerManager.getIconPath(manager));
		cit.setIsAbstract(ItemTypeManager.isIsAbstractAttribute(itemType));
		cit.setPatternId(ManagerManager.getUniqueNameTemplate(manager));
		String error_valid_id = notempty(ManagerManager.getMessageErrorIdAttribute(manager));
		cit.setErrorValidId(error_valid_id);

		String pattern_valid_id = ManagerManager.getValidPatternIdAttribute(manager);
		if (pattern_valid_id != null && pattern_valid_id.length() == 0) {
			pattern_valid_id = null;
		}
		cit.setPatternValidId(pattern_valid_id);
		cit.setManagerClass(((JavaFileContentManager) manager.getContentItem()).getQualifiedClassName(cxt));
		cit.setCstName(GenerateJavaIdentifier.cstItemType(cxt, itemType));

	}

	/**
	 * Generate outgoing link.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param factory
	 *            the factory
	 * @param itemType
	 *            the item type
	 * @param cit
	 *            the cit
	 * @param linkType
	 *            the link type
	 * @param itemTypeDest
	 *            the item type dest
	 * 
	 * @return the c link type
	 */
	private static CLinkType generateOutgoingLink(ContextVariable cxt, ObjectFactory factory, Item itemType,
			CAbsItemType cit, Item linkType, Item itemTypeDest) {
		CLinkType clt = factory.createCLinkType();
		cit.getOutgoingLink().add(clt);

		clt.setId(LinkManager.getUUID(linkType).toString());
		clt.setIntID(0); // LinkManager.getIntID(linkType)
		IType manager = LinkManager.getLinkManagerType(linkType);
		if (manager != null) {
			clt.setManagerClass(manager.getFullyQualifiedName());
		}

		clt.setName(linkType.getName());
		clt.setMin(LinkManager.getMin(linkType));
		clt.setMax(LinkManager.getMax(linkType));
		clt.setDestination(ItemTypeManager.getUUID(itemTypeDest).toString());
		clt.setIsComposition(LinkManager.isComposition(linkType));
		clt.setIsAggregation(LinkManager.isAggregation(linkType));
		clt.setIsPart(LinkManager.isPart(linkType));
		clt.setIsRequire(LinkManager.isRequireAttribute(linkType));
		clt.setIsAnnotation(LinkManager.isAnnotation(linkType));
		clt.setIsNatif(LinkManager.isNatif(linkType));
		clt.setSelectionExpression(notempty(LinkManager.getSelectionExpression(linkType)));
		clt.setAssociationItemType(null);
		clt.setCstName(GenerateJavaIdentifier.cstAttribute(cxt, linkType, itemType));
		clt.setInverseLink(notempty(LinkManager.getInverseLinkName(linkType)));
		clt.setHidden(LinkManager.isHiddenAttribute(linkType));
		return clt;
	}

	/**
	 * Generate creation dialog.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param factory
	 *            the factory
	 * @param itemType
	 *            the item type
	 * @param cit
	 *            the cit
	 * 
	 * @return the item
	 */
	private static Item generateCreationDialog(ContextVariable cxt, ObjectFactory factory, Item itemType,
			CAbsItemType cit) {
		Item creationDialog = ItemTypeManager.getCreationDialog(itemType);
		if (creationDialog != null) {
			CPages pages = factory.createCPages();
			cit.setCreationPages(pages);
			if (CreationDialogManager.hasGlobalActionPage(creationDialog)) {
				pages.setMainActionClass(GenerateJavaIdentifier.qualifiedGlobalCreationActionFromCreationDialog(cxt,
						creationDialog));
			}
			String defaultShortName = notempty(CreationDialogManager.getDefaultShortNameAttribute(creationDialog));

			pages.setDefaultShortName(defaultShortName);

			Collection<Item> pagesItems = CreationDialogManager.getPages(creationDialog);
			generatePages(cxt, factory, pages, pagesItems);
		}
		return creationDialog;
	}

	/**
	 * Notempty.
	 * 
	 * @param aString
	 *            the a string
	 * 
	 * @return the string
	 */
	private static String notempty(String aString) {
		if (aString == null) {
			return null;
		}
		if (aString.length() == 0) {
			return null;
		}
		return aString;
	}

	/**
	 * Generate modification dialog.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param factory
	 *            the factory
	 * @param itemType
	 *            the item type
	 * @param cit
	 *            the cit
	 * 
	 * @return the item
	 */
	private static Item generateModificationDialog(ContextVariable cxt, ObjectFactory factory, Item itemType,
			CAbsItemType cit) {
		Item modificationDialog = ItemTypeManager.getModificationDialog(itemType);
		if (modificationDialog != null) {
			CPages pages = factory.createCPages();
			cit.setModificationPages(pages);

			Collection<Item> pagesItems = ModificationDialogManager.getPages(modificationDialog);
			generatePages(cxt, factory, pages, pagesItems);
		}
		return modificationDialog;
	}

	/**
	 * Generate pages.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param factory
	 *            the factory
	 * @param pages
	 *            the pages
	 * @param pagesItems
	 *            the pages items
	 */
	private static void generatePages(ContextVariable cxt, ObjectFactory factory, CPages pages,
			Collection<Item> pagesItems) {
		for (Item aPageItem : pagesItems) {
			CPage aPage = factory.createCPage();
			pages.getPage().add(aPage);
			aPage.setCas(PageManager.isModificationPage(aPageItem) ? ConfigurablePageFactory.PAGE_PROPERTY_ITEM
					: ConfigurablePageFactory.PAGE_CREATION_ITEM);

			aPage.setClassName(GenerateJavaIdentifier.qualifiedPageFactoryFromPage(cxt, aPageItem));
			aPage.setId(aPageItem.getName());
			aPage.setUuid(PageManager.getUUID(aPageItem).toString());
			aPage.setTitre(PageManager.getTitle(aPageItem));
		}
	}

	public static void addGenerateCadseDefinitionModelExt(GenerateCadseDefinitionModelExt g) {
		if (!generatorsExt.contains(g)) {
			generatorsExt.add(g);
		}
	}

}
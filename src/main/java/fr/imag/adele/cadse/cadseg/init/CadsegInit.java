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
package fr.imag.adele.cadse.cadseg.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import fr.imag.adele.cadse.cadseg.contents.CadseDefinitionContent;
import fr.imag.adele.cadse.cadseg.contents.ExtItemTypeContent;
import fr.imag.adele.cadse.cadseg.contents.ManagerJavaFileContentManager;
import fr.imag.adele.cadse.cadseg.contents.ViewJavaFileContentManager;
import fr.imag.adele.cadse.cadseg.contents.actions.DynamicActionsContent;
import fr.imag.adele.cadse.cadseg.contents.actions.MenuActionContent;
import fr.imag.adele.cadse.cadseg.eclipse.CadseG_WLWCListener;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeSpaceKeyType;
import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.content.ProjectContentModelManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ExtItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.PageSpaceKeyType;
import fr.imag.adele.cadse.cadseg.managers.ic.IC_PartLinkForBrowser_Combo_ListManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewLinkTypeManager;
import fr.imag.adele.cadse.cadseg.menu.DefaultMenuContributor;
import fr.imag.adele.cadse.cadseg.menu.TeamWorkMenuActionContributor;
import fr.imag.adele.cadse.cadseg.menu.ViewActionContributor;
import fr.imag.adele.cadse.cadseg.migration.MigrationInit;
import fr.imag.adele.cadse.cadseg.operation.WorkspaceActionContributor;
import fr.imag.adele.cadse.cadseg.pages.PageInit;
import fr.imag.adele.cadse.cadseg.readwriteattribute.LW_Attribute;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.DefaultValidator;
import fr.imag.adele.cadse.core.InitAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.AbstractLinkTypeManager;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.key.DefaultKeyDefinitionImpl;
import fr.imag.adele.cadse.core.key.Key;

public class CadsegInit implements InitAction {

	@Override
	public void init() {
		CadseGCST.CADSE.setHasQualifiedNameAttribute(false);
		CadseGCST.CADSE.setKeyDefinition(new DefaultKeyDefinitionImpl(CadseGCST.CADSE, null));

		CadseGCST.ITEM_lt_INSTANCE_OF.setIsNatif(true);
		
		CadseGCST.ITEM_at_COMMITTED_DATE_.setFlag(Item.CAN_BE_UNDEFINED, true);
		CadseGCST.ITEM_TYPE_at_ITEM_FACTORY_.setFlag(Item.CAN_BE_UNDEFINED, true);
		CadseGCST.ITEM_TYPE_at_ICON_.setFlag(Item.CAN_BE_UNDEFINED, true);
		CadseGCST.MENU_ABSTRACT_at_ICON_.setFlag(Item.CAN_BE_UNDEFINED, true);
		CadseGCST.MENU_ABSTRACT_at_ICON_.setFlag(Item.NOT_EMPTY, false);
		CadseGCST.MENU_ABSTRACT_at_PATH_.setFlag(Item.CAN_BE_UNDEFINED, true);
		
		CadseGCST.ITEM_at_REQUIRE_NEW_REV_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.ATTRIBUTE_at_IS_LIST_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.ATTRIBUTE_at_TRANSIENT_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.ATTRIBUTE_at_REQUIRE_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.LINK_TYPE_at_AGGREGATION_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.LINK_TYPE_at_ANNOTATION_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.LINK_TYPE_at_COMPOSITION_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.LINK_TYPE_at_GROUP_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.LINK_TYPE_at_HIDDEN_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.LINK_TYPE_at_PART_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.LINK_TYPE_at_MAPPING_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.LINK_TYPE_at_REQUIRE_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.LINK_TYPE_at_TWCOUPLED_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.ITEM_TYPE_at_IS_INSTANCE_HIDDEN_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.ITEM_TYPE_at_HAS_CONTENT_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.ITEM_TYPE_at_HAS_SHORT_NAME_.setFlag(Item.CAN_BE_UNDEFINED, false);
		CadseGCST.ITEM_TYPE_at_HAS_UNIQUE_NAME_.setFlag(Item.CAN_BE_UNDEFINED, false);
		
		CadseGCST.MENU_ABSTRACT_at_PATH_.setFlag(Item.NOT_EMPTY, false);
		
		
		CadseGCST.CONTENT_ITEM_lt_OWNER_ITEM.setFlag(Item.TRANSIENT, true);
		CadseGCST.ITEM_TYPE_lt_LINK_TYPE.setFlag(Item.TRANSIENT, true);
		
		// CadseGCST.CADSE_DEFINITION.setKeyDefinition(new DefaultKeyDefinitionImpl(CadseGCST.CADSE_DEFINITION, null));
		new CadseG_WLWCListener();
		//new CadseG_WorkspaceListener();
		CadseCore.theItem.addActionContributeur(new WorkspaceActionContributor());
		CadseCore.theItem.addActionContributeur(new TeamWorkMenuActionContributor());

		CadseCore.theItem.setIsAbstract(true);
		CadseGCST.CADSE.setIsAbstract(true);
		CadseGCST.CONTENT_ITEM.setIsAbstract(true);
		CadseGCST.LINK_TYPE_TYPE.setIsAbstract(true);
		CadseGCST.VIEW_DESCRIPTION.setIsAbstract(true);
		CadseGCST.UILISTENER.setIsAbstract(true);
		CadseGCST.RUNTIME_ITEM.setIsAbstract(true);

		CadseGCST.TYPE_DEFINITION.setHasNameAttribute(true);
		CadseGCST.TYPE_DEFINITION.setHasQualifiedNameAttribute(true);
		CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES.setIsNatif(true);
		CadseGCST.TYPE_DEFINITION_lt_CREATION_PAGES.setIsNatif(true);
		CadseGCST.TYPE_DEFINITION_lt_CADSE.setIsNatif(true);
		CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_.setIsNatif(true);
		CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF.setIsNatif(true);

		CadseGCST.PAGE.setHasQualifiedNameAttribute(false);
		CadseGCST.ATTRIBUTE.setHasNameAttribute(true);
		CadseGCST.ATTRIBUTE.setHasQualifiedNameAttribute(false);
		CadseGCST.DATA_MODEL.setHasNameAttribute(true);
		CadseGCST.DATA_MODEL.setHasQualifiedNameAttribute(false);
		CadseGCST.INTERACTION_CONTROLLER.setHasQualifiedNameAttribute(false);
		CadseGCST.MODEL_CONTROLLER.setHasQualifiedNameAttribute(false);
		CadseGCST.MENU_ACTION.setHasQualifiedNameAttribute(false);
		CadseGCST.MENU.setHasQualifiedNameAttribute(false);
		CadseGCST.FIELD.setHasQualifiedNameAttribute(false);

		CadseGCST.ITEM_at_COMMITTED_BY_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_at_COMMITTED_DATE_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_at_DISPLAY_NAME_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_at_ID_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_at_ISVALID_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_at_ITEM_HIDDEN_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_at_ITEM_READONLY_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_at_NAME_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_at_QUALIFIED_NAME_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_at_REQUIRE_NEW_REV_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_at_REV_MODIFIED_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_at_TW_VERSION_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_at_TWLAST_COMMENT_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_lt_CONTENTS.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_lt_INSTANCE_OF.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_lt_PARENT.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.GROUP_EXT_ITEM_lt_MEMBER_OF.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.GROUP_EXT_ITEM_lt_MEMBERS.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.TYPE_DEFINITION_at_ID_RUNTIME_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.TYPE_DEFINITION_lt_CADSE.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.TYPE_DEFINITION_lt_CREATION_PAGES.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.TYPE_DEFINITION_lt_FIELDS.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.TYPE_DEFINITION_lt_GOUPS_OF_ATTRIBUTES.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.TYPE_DEFINITION_lt_VALIDATORS.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_CUSTOM_MANAGER_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_DISPLAY_NAME_TEMPLATE_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_HAS_CONTENT_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_HAS_SHORT_NAME_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_HAS_UNIQUE_NAME_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_ICON_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_ICON_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_IS_INSTANCE_HIDDEN_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_IS_META_ITEM_TYPE_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_IS_META_ITEM_TYPE_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_ITEM_FACTORY_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_ITEM_MANAGER_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_MANAGER_CLASS_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_MESSAGE_ERROR_ID_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_OVERWRITE_DEFAULT_PAGES_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_PACKAGE_NAME_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_QUALIFIED_NAME_TEMPLATE_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_at_VALIDATE_NAME_RE_.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_lt_LINK_TYPE.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_lt_SUB_TYPES.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_lt_SUB_TYPES.setFlag(Item.ATTRIBUTE_HEAD, true);
		CadseGCST.ITEM_TYPE_lt_WC_LISTENERS.setFlag(Item.ATTRIBUTE_HEAD, true);

		CadseGCST.DATA_MODEL.setKeyDefinition(new DefaultKeyDefinitionImpl(CadseGCST.DATA_MODEL,
				CadseGCST.CADSE_DEFINITION));

		CadseGCST.TYPE_DEFINITION.setKeyDefinition(new DefaultKeyDefinitionImpl(CadseGCST.TYPE_DEFINITION,
				CadseGCST.CADSE) {
			@Override
			public String convertName(String name) {
				if (name == null) {
					return null;
				}
				return name.toUpperCase();
			}

			@Override
			protected String getName(Item item) {
				return convertName(super.getName(item));
			}
			
			@Override
			protected Key getParentSpaceKeyFromItem(Item item) {
				Item partparent = item.getPartParent(_parentKeyDefinition.getItemType());
				if (partparent == null) {
					return null;
				}
				Key key = partparent.getKey();
				return key;
			}
		});

		// CadseGCST.EXT_ITEM_TYPE.setKeyDefinition(new DefaultKeyDefinitionImpl(
		// CadseGCST.EXT_ITEM_TYPE, null));
		CadseGCST.PAGE.setKeyDefinition(new PageSpaceKeyType(CadseGCST.PAGE, CadseGCST.TYPE_DEFINITION));

		CadseGCST.ATTRIBUTE.setKeyDefinition(new AttributeSpaceKeyType(CadseGCST.ATTRIBUTE, CadseGCST.TYPE_DEFINITION));

		CadseGCST.FIELD.setKeyDefinition(new DefaultKeyDefinitionImpl(CadseGCST.FIELD, CadseGCST.TYPE_DEFINITION,
				CadseGCST.ITEM_at_NAME_, CadseGCST.FIELD_lt_ATTRIBUTE));

		CadseGCST.FIELD_lt_ATTRIBUTE.setManager(new AbstractLinkTypeManager() {
			@Override
			public Collection<Item> getSelectingDestination(Item field) {
				Item container = field.getPartParent();
				Item itemType = null;
				Collection<Item> allAttributes = new ArrayList<Item>();

				if (container.getType() == CadseGCST.EXT_ITEM_TYPE) {
					itemType = ExtItemTypeManager.getRefType(container);
					allAttributes.addAll(ExtItemTypeManager.getAttributes(container));
				}
				else if (container.getType() == CadseGCST.ITEM_TYPE) {
					itemType = container;
				}
				if (itemType != null) {
					Item[] ret = ItemTypeManager.getAllAttributes(null, itemType, null, true);
					allAttributes.addAll(Arrays.asList(ret));
				}
				return allAttributes;
			}
		});

		CadseGCST.VIEW.setKeyDefinition(new DefaultKeyDefinitionImpl(CadseGCST.VIEW, CadseGCST.CADSE_DEFINITION));

		try {
			PageInit.init();
			CadseGCST.ITEM.addActionContributeur(new DefaultMenuContributor());
			CadseGCST.VIEW_ITEM_TYPE.addActionContributeur(new ViewActionContributor());
		}
		catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MigrationInit.init();
		LW_Attribute.init();
		
		CadseGCST.CADSE_DEFINITION.setContentItemClass(CadseDefinitionContent.class);
		CadseGCST.DYNAMIC_ACTIONS.setContentItemClass(DynamicActionsContent.class);
		CadseGCST.MENU_ACTION.setContentItemClass(MenuActionContent.class);
		CadseGCST.MANAGER.setContentItemClass(ManagerJavaFileContentManager.class);
		CadseGCST.EXT_ITEM_TYPE.setContentItemClass(ExtItemTypeContent.class);
		CadseGCST.VIEW.setContentItemClass(ViewJavaFileContentManager.class);
		
		CadseGCST.ITEM.addAdapter(new DefaultValidator());
		CadseGCST.ENUM.addAdapter(new EnumManager.EnumValidator());
		CadseGCST.MANAGER.addAdapter(new ManagerManager.ManagerValidator());
		CadseGCST.PROJECT_CONTENT_MODEL.addAdapter(new ProjectContentModelManager.ProjectContentValidator());
		CadseGCST.IC_PART_LINK_FOR_BROWSER_COMBO_LIST.addAdapter(new IC_PartLinkForBrowser_Combo_ListManager.IC_PartLinkForBrowser_Combo_ListValidator());
		CadseGCST.VIEW_LINK_TYPE.addAdapter(new ViewLinkTypeManager.ViewLinkTypeValidator());
	}

}

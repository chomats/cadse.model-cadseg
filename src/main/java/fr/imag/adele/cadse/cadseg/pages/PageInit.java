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
package fr.imag.adele.cadse.cadseg.pages;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import fede.workspace.eclipse.java.fields.IC_JavaClassForBrowser_Combo;
import fede.workspace.eclipse.java.fields.MC_StringToJavaElement;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_AttributeDestType;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_DestinationLinkForBrowser_Combo;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_EnumDefaultValue;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_ExtType_RefTypes;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_ItemTypeTemplateForTextFromManager;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_ItemtypeIcon;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_MenuAction_Path;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_SuperTypeForBrowser_Combo;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_ViewManager_DataModelView;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_BooleanTextField;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_DefaultEnum;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_DestTypeCombo;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_DoubleTextField;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_FILE_PATH;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_IntegerTextField;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_LongTextField;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_PROJECT_NAME;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_ResourceToURL;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_ViewItemType;
import fr.imag.adele.cadse.cadseg.teamwork.ui.IC_ModifiedAttr_List;
import fr.imag.adele.cadse.cadseg.teamwork.ui.ModifiedAttr_ModelController;
import fr.imag.adele.cadse.cadseg.validators.BooleanValidator;
import fr.imag.adele.cadse.cadseg.validators.ExtendsClassValidator;
import fr.imag.adele.cadse.cadseg.validators.IsListValidator;
import fr.imag.adele.cadse.cadseg.validators.JavaPackageValidator;
import fr.imag.adele.cadse.cadseg.validators.LinkTypeIsGoupListener;
import fr.imag.adele.cadse.cadseg.validators.MenuValidator;
import fr.imag.adele.cadse.cadseg.validators.NotEmptyValidator;
import fr.imag.adele.cadse.cadseg.validators.NotEmptyValidatorGeneric;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.ui.GroupOfAttributesDescriptor;
import fr.imag.adele.cadse.core.impl.ui.JavaClassValidator;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.impl.ui.ic.IC_Descriptor;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_DefaultForList;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_Descriptor;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageParticipator;
import fr.imag.adele.cadse.core.ui.view.FilterContext;
import fr.imag.adele.cadse.core.util.CreatedObjectManager;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_Max;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_Min;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.mc.MaxModelController;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.mc.MinMaxValidator;

public class PageInit {

	public static GroupOfAttributesDescriptor ITEM_GROUP_NAME;

	/**
	 * Helper method used to create a group of attributes.
	 * 
	 * @param name
	 *            The name and label of this group
	 * @param column
	 *            The number of columns
	 * @param border
	 *            Visible border
	 * @param attr
	 *            The list of attributes displayed by this group
	 * @return the group
	 */
	public static GroupOfAttributesDescriptor createGroup(String name, int column, boolean border,
			IAttributeType<?>... attr) {

		GroupOfAttributesDescriptor goad = new GroupOfAttributesDescriptor(name, column);
		goad.setHasBoxGroup(border);

		for (IAttributeType<?> a : attr) {
			goad.add(a);
		}

		return goad;
	}

	/**
	 * Helper method which creates the group Name.
	 * 
	 * @return the group name.
	 */
	public static GroupOfAttributesDescriptor createGroupNameItem() {
		return createGroup("Name", 1, false, CadseGCST.ITEM_at_NAME_, CadseGCST.ITEM_at_DISPLAY_NAME_,
				CadseGCST.ITEM_at_QUALIFIED_NAME_, CadseGCST.ITEM_TYPE_at_DEFAULT_INSTANCE_NAME_,
				CadseGCST.MANAGER_at_HUMAN_NAME_, CadseGCST.LIST_lt_SUB_TYPE);
	}

	/**
	 * Helper method which creates the group basic properties for a CADSE
	 * definition
	 * 
	 * @return the group.
	 */
	private static GroupOfAttributesDescriptor createGroupRepositoryConfigurationCadse() {
		return createGroup("Repository configuration", 2, true, CadseGCST.CADSE_at_ITEM_REPO_LOGIN_,
				CadseGCST.CADSE_at_ITEM_REPO_PASSWD_, CadseGCST.CADSE_at_ITEM_REPO_URL_,
				CadseGCST.CADSE_at_DEFAULT_CONTENT_REPO_URL_);
	}

	/**
	 * Helper method which creates the group basic properties for a CADSE
	 * definition
	 * 
	 * @return the group.
	 */
	private static GroupOfAttributesDescriptor createGroupBasicPropertiesCadseDefinition() {
		return createGroup("Basic properties", 1, true, CadseGCST.CADSE_DEFINITION_at_CADSE_NAME_,
				CadseGCST.CADSE_at_DESCRIPTION_, CadseGCST.CADSE_DEFINITION_at_COMMENTARY_,
				CadseGCST.CADSE_DEFINITION_at_VENDOR_NAME_, CadseGCST.CADSE_DEFINITION_at_VERSION_CADSE_);
	}

	/**
	 * Helper method which creates the group Flags
	 * 
	 * @return the group flags
	 */
	private static GroupOfAttributesDescriptor createGroupFlagsItemType() {
		return createGroup("Flags", 4, true, CadseGCST.ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_,
				CadseGCST.ITEM_TYPE_at_IS_INSTANCE_HIDDEN_, CadseGCST.ITEM_TYPE_at_IS_META_ITEM_TYPE_,
				CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_, CadseGCST.ITEM_TYPE_at_HAS_CONTENT_);
	}

	/**
	 * Helper method which creates the group properties.
	 * 
	 * @return the group properties.
	 */
	private static GroupOfAttributesDescriptor createGroupBasicPropertiesAttributes() {

		GroupOfAttributesDescriptor gBasicProperties = createGroup("Basic properties", 1, true);

		// Specific part 1 : Default value
		GroupOfAttributesDescriptor gSpecificPart1 = createGroup("Basic properties", 1, false,
				CadseGCST.ENUM_lt_ENUM_TYPE, CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);
		gBasicProperties.add(gSpecificPart1);

		// Specific part 2 : check boxes
		GroupOfAttributesDescriptor gSpecificPart2 = createGroup("Basic properties", 3, false,
				CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_, CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_,
				CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_, CadseGCST.STRING_at_NOT_EMPTY_,
				CadseGCST.ATTRIBUTE_at_IS_LIST_);
		gBasicProperties.add(gSpecificPart2);

		return gBasicProperties;
	}

	/**
	 * Helper method which creates the group properties.
	 * 
	 * @return the group properties.
	 */
	private static GroupOfAttributesDescriptor createGroupBasicPropertiesBoolean() {

		return createGroup("Basic properties", 3, true, CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_,
				CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_, CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_,
				CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_, CadseGCST.STRING_at_NOT_EMPTY_,
				CadseGCST.ATTRIBUTE_at_IS_LIST_);
	}

	/**
	 * Helper method which creates the group for editing link properties.
	 * 
	 * @return this specific group.
	 */
	private static GroupOfAttributesDescriptor createGroupPropertiesLink() {

		// Link properties container
		GroupOfAttributesDescriptor gLinkProperties = createGroup("Link properties", 1, true);

		// Part1
		GroupOfAttributesDescriptor gPart1 = createGroup("Link properties", 1, false, CadseGCST.LINK_TYPE_lt_SOURCE,
				CadseGCST.LINK_TYPE_lt_DESTINATION, CadseGCST.LINK_TYPE_lt_INVERSE_LINK);
		gLinkProperties.add(gPart1);

		// Part2 : flags
		GroupOfAttributesDescriptor gPart2 = createGroup("Link properties", 4, false,
				CadseGCST.LINK_TYPE_at_ANNOTATION_, CadseGCST.LINK_TYPE_at_AGGREGATION_,
				CadseGCST.LINK_TYPE_at_COMPOSITION_, CadseGCST.LINK_TYPE_at_PART_, CadseGCST.LINK_TYPE_at_REQUIRE_,
				CadseGCST.LINK_TYPE_at_MAPPING_, CadseGCST.LINK_TYPE_at_GROUP_, CadseGCST.LINK_TYPE_at_HIDDEN_);
		gLinkProperties.add(gPart2);

		// Part3 : selection and link manager
		GroupOfAttributesDescriptor gPart3 = createGroup("Link properties", 1, false,
				CadseGCST.LINK_TYPE_at_SELECTION_, CadseGCST.LINK_TYPE_at_LINK_MANAGER_);
		gLinkProperties.add(gPart3);

		// Part4 : cardinalities
		GroupOfAttributesDescriptor gPart4 = createGroup("Cardinalities", 2, false, CadseGCST.LINK_TYPE_at_MIN_,
				CadseGCST.LINK_TYPE_at_MAX_);
		gLinkProperties.add(gPart4);

		return gLinkProperties;
	}

	private static GroupOfAttributesDescriptor createGroupEvolutionAttributes() {
		return createGroup("Evolution", 2, true, CadseGCST.ATTRIBUTE_at_TWEVOL_, CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_,
				CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_, CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_);
	}

	private static GroupOfAttributesDescriptor createGroupEvolutionLink() {
		return createGroup("Evolution", 2, true, CadseGCST.ATTRIBUTE_at_TWEVOL_, CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_,
				CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_, CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_,
				CadseGCST.LINK_TYPE_at_TWCOUPLED_, CadseGCST.LINK_TYPE_at_TWDEST_EVOL_);
	}

	private static GroupOfAttributesDescriptor createGroupAdvancedPropertiesAttributes() {
		return createGroup("Advanced properties", 3, true, CadseGCST.ATTRIBUTE_at_NATIF_,
				CadseGCST.ATTRIBUTE_at_TRANSIENT_);
	}

	private static GroupOfAttributesDescriptor createGroupAdvancedPropertiesLink() {
		return createGroup("Advanced properties", 3, true, CadseGCST.ATTRIBUTE_at_NATIF_,
				CadseGCST.ATTRIBUTE_at_TRANSIENT_);
	}

	private static GroupOfAttributesDescriptor createGroupContentModel() {
		return createGroup("Java Project Content Model", 1, false, CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_,
				CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_,
				CadseGCST.JAVA_FILE_CONTENT_MODEL_at_CLASS_NAME_, CadseGCST.JAVA_FILE_CONTENT_MODEL_at_PACKAGE_NAME_,
				CadseGCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH_, CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_,
				CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_);
	}

	public static void init() throws CadseException {

		// **************************************************************** //
		// Displayed attributes in creation pages and in modification pages //
		// **************************************************************** //

		CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.ATTRIBUTE_at_IS_LIST_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LIST_lt_SUB_TYPE.setFlag(Item.SHOW_IN_DEFAULT_CP, true);

		CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.CADSE_lt_EXTENDS.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.ENUM_lt_ENUM_TYPE.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.ENUM_TYPE_at_VALUES_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.ITEM_at_NAME_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.ITEM_TYPE_at_HAS_CONTENT_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.ITEM_TYPE_lt_SUPER_TYPE.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.JAVA_FILE_CONTENT_MODEL_at_CLASS_NAME_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.JAVA_FILE_CONTENT_MODEL_at_PACKAGE_NAME_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LINK_TYPE_at_AGGREGATION_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LINK_TYPE_at_ANNOTATION_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LINK_TYPE_at_COMPOSITION_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LINK_TYPE_at_GROUP_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LINK_TYPE_at_HIDDEN_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LINK_TYPE_at_MAPPING_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LINK_TYPE_at_MAX_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LINK_TYPE_at_MIN_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LINK_TYPE_at_PART_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LINK_TYPE_at_REQUIRE_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LINK_TYPE_at_TWCOUPLED_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LINK_TYPE_at_TWDEST_EVOL_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.LINK_TYPE_lt_DESTINATION.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.RUNTIME_ITEM_at_EXTENDS_CLASS_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);
		CadseGCST.EXT_ITEM_TYPE_lt_CONTENTS.setFlag(Item.SHOW_IN_DEFAULT_CP, false);
		CadseGCST.MENU_ACTION_lt_CONTENTS.setFlag(Item.SHOW_IN_DEFAULT_CP, false);
		CadseGCST.MENU_ABSTRACT_at_LABEL_.setFlag(Item.SHOW_IN_DEFAULT_CP, true);

		CadseGCST.ATTRIBUTE_at_DEV_GENERATED_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ATTRIBUTE_at_FINAL_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ATTRIBUTE_at_ID_RUNTIME_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ATTRIBUTE_at_REQUIRE_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ATTRIBUTE_lt_WC_LISTENS.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.CADSE_at_EXECUTED_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.CADSE_at_ID_DEFINITION_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.CADSE_DEFINITION_lt_BUILD.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.CADSE_DEFINITION_lt_CONFIGURATION.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.CADSE_DEFINITION_lt_MAPPING.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.CADSE_lt_ITEM_TYPES.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ENUM_at_ENUM_CLAZZ_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ENUM_at_VALUES_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.FOLDER_CONTENT_MODEL_lt_CONTENT_MODEL.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_at_ID_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_at_ISVALID_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_at_ITEM_HIDDEN_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_at_ITEM_READONLY_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_lt_CONTENTS.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_lt_INSTANCE_OF.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_lt_PARENT.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_at_CUSTOM_MANAGER_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_at_DISPLAY_NAME_TEMPLATE_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_at_HAS_SHORT_NAME_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_at_HAS_UNIQUE_NAME_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_at_IS_META_ITEM_TYPE_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_at_ITEM_MANAGER_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_at_MANAGER_CLASS_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_at_MESSAGE_ERROR_ID_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_at_OVERWRITE_DEFAULT_PAGES_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_at_QUALIFIED_NAME_TEMPLATE_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_at_VALIDATE_NAME_RE_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_lt_LINK_TYPE.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_lt_SUB_TYPES.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.ITEM_TYPE_lt_WC_LISTENERS.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.JAVA_PROJECT_CONTENT_MODEL_lt_CONTENT_MODEL.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.LINK_TYPE_at_KIND_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.MANAGER_at_DISPLAY_NAME_TEMPLATE_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.MANAGER_at_ICON_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.MANAGER_at_MESSAGE_ERROR_ID_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.MANAGER_at_QUALIFIED_NAME_TEMPLATE_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.MANAGER_at_VALID_PATTERN_ID_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.MANAGER_lt_CONTENT_MODEL.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.MANAGER_lt_EXPORTERS.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.MANAGER_lt_ITEM_TYPE.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.TYPE_DEFINITION_at_ID_RUNTIME_.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		// CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES.setFlag(Item.SHOW_IN_DEFAULT_MP,
		// false);
		CadseGCST.TYPE_DEFINITION_lt_CADSE.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.TYPE_DEFINITION_lt_CREATION_PAGES.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.TYPE_DEFINITION_lt_FIELDS.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.TYPE_DEFINITION_lt_GOUPS_OF_ATTRIBUTES.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.TYPE_DEFINITION_lt_VALIDATORS.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.EXT_ITEM_TYPE_lt_CONTENTS.setFlag(Item.SHOW_IN_DEFAULT_MP, false);
		CadseGCST.MENU_ACTION_lt_CONTENTS.setFlag(Item.SHOW_IN_DEFAULT_MP, false);

		// ***************** //
		// Not editable //
		// ***************** //

		CadseGCST.ITEM_at_NAME_.setFlag(Item.NOT_EDITABLE_IN_DEFAULT_MP, true);
		CadseGCST.ITEM_at_QUALIFIED_NAME_.setFlag(Item.NOT_EDITABLE_IN_DEFAULT_MP, true);
		CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES.setFlag(Item.NOT_EDITABLE_IN_DEFAULT_MP, true);
		CadseGCST.ITEM_at_REV_MODIFIED_.setFlag(Item.NOT_EDITABLE_IN_DEFAULT_MP, true);
		CadseGCST.ITEM_at_REQUIRE_NEW_REV_.setFlag(Item.NOT_EDITABLE_IN_DEFAULT_MP, true);
		CadseGCST.ITEM_at_COMMITTED_BY_.setFlag(Item.NOT_EDITABLE_IN_DEFAULT_MP, true);
		CadseGCST.ITEM_at_COMMITTED_DATE_.setFlag(Item.NOT_EDITABLE_IN_DEFAULT_MP, true);
		CadseGCST.ITEM_at_TW_VERSION_.setFlag(Item.NOT_EDITABLE_IN_DEFAULT_MP, true);
		CadseGCST.ITEM_at_TWLAST_COMMENT_.setFlag(Item.NOT_EDITABLE_IN_DEFAULT_MP, true);
		CadseGCST.LINK_TYPE_lt_SOURCE.setFlag(Item.NOT_EDITABLE_IN_DEFAULT_MP, true);

		// ***************** //
		// Fields definition //
		// ***************** //

		// ITEM_at_NAME_ ( overwrite mc)
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_NAME_ATTRIBUTE);
			CadseGCST.ITEM.addField(new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(), CadseGCST.ITEM_at_NAME_,
					"name", EPosLabel.left, mc, null));
		}

		// ITEM_TYPE_lt_SUPER_TYPE (overwrite ic)
		{
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST,
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, "Select a super type",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, "Select a super type");
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_SuperTypeForBrowser_Combo.class);
			CadseGCST.ITEM_TYPE.addField(new UIFieldImpl(CadseGCST.DBROWSER, UUID.randomUUID(),
					CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, "super type:", EPosLabel.left, new MC_Descriptor(
							CadseGCST.MC_LINK), ic));
		}

		// ITEM_TYPE_at_ICON_
		{
			IC_Descriptor icIcon = new IC_Descriptor(CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST,
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, "Select an icon",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, "Select a icon");
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icIcon, IC_ItemtypeIcon.class);

			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(null, mc, MC_ResourceToURL.class);
			CadseGCST.ITEM_TYPE.addField(new UIFieldImpl(CadseGCST.DBROWSER, UUID.randomUUID(),
					CadseGCST.ITEM_TYPE_at_ICON_, "icon", EPosLabel.left, mc, icIcon));
		}

		// LINK_TYPE_lt_DESTINATION (overwrite ic)
		{
			IC_Descriptor icDesttype = new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST,
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, "Select a destination",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, "Select a destination");
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icDesttype,
					IC_DestinationLinkForBrowser_Combo.class);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_LINK, CadseGCST.MC_LINK_at_ERROR_MESSAGE_,
					"You must set the destination");
			CadseGCST.LINK_TYPE.addField(new UIFieldImpl(CadseGCST.DBROWSER, UUID.randomUUID(),
					CadseGCST.LINK_TYPE_lt_DESTINATION, "destination:", EPosLabel.left, mc, icDesttype));
		}

		// LINK_TYPE_lt_INVERSE_LINK (overwrite ic)
		{
			IC_Descriptor icInverseLink = new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST,
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, "Select an inverse link",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, "Select an inverse link");
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icInverseLink,
					fr.imag.adele.cadse.cadseg.pages.ic.IC_InverseLink.class);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_LINK);
			CadseGCST.LINK_TYPE.addField(new UIFieldImpl(CadseGCST.DBROWSER, UUID.randomUUID(),
					CadseGCST.LINK_TYPE_lt_INVERSE_LINK, "inverse link:", EPosLabel.left, mc, icInverseLink));
		}

		// LINK_TYPE_at_MIN_
		{
			IC_Descriptor icMin = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icMin, IC_Min.class);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_INTEGER, CadseGCST.MC_INTEGER_at_DEFAULT_VALUE_, 0,
					CadseGCST.MC_INTEGER_at_ERROR_MSG_MIN_, "min must be >= 0", CadseGCST.MC_INTEGER_at_MIN_, 0);
			CadseGCST.LINK_TYPE.addField(new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.LINK_TYPE_at_MIN_, "min:", EPosLabel.left, mc, icMin));
		}

		// LINK_TYPE_at_MAX_
		{
			IC_Descriptor icMax = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icMax, IC_Max.class);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_INTEGER, CadseGCST.MC_INTEGER_at_DEFAULT_VALUE_, 0,
					CadseGCST.MC_INTEGER_at_ERROR_MSG_MIN_, "max must greter than 0", CadseGCST.MC_INTEGER_at_MIN_, 0);
			CreatedObjectManager.register(null, mc, MaxModelController.class);
			CadseGCST.LINK_TYPE.addField(new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.LINK_TYPE_at_MAX_, "max:", EPosLabel.left, mc, icMax));
		}

		// EXT_ITEM_TYPE_lt_REF_TYPE
		{
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_ExtType_RefTypes.class);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_LINK);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DLIST, UUID.randomUUID(),
					CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE, "Ref types", EPosLabel.top, mc, ic,
					CadseGCST.DLIST_at_EDITABLE_BUTTON_, true);
			CadseGCST.EXT_ITEM_TYPE.addField(field);
		}

		// ITEM_lt_MODIFIED_ATTRIBUTES
		{
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_ModifiedAttr_List.class);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_LINK);
			CreatedObjectManager.register(null, mc, ModifiedAttr_ModelController.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DLIST, UUID.randomUUID(),
					CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES, "Modified attributes and content", EPosLabel.top, mc, ic);
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);
		}

		// MENU_ABSTRACT_at_PATH_
		{
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_MenuAction_Path.class);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(), CadseGCST.MENU_ABSTRACT_at_PATH_,
					"path in menu", EPosLabel.left, mc, ic, CadseGCST.DTEXT_at_TOOL_TIP_,
					"La valeur peut être context1, context2 ... context6, context1/new");
			CadseGCST.MENU_ABSTRACT.addField(field);
		}

		// MENU_ABSTRACT_at_ICON_
		{
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST,
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, "Select an icon",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, "Select a icon");
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_ItemtypeIcon.class);

			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(null, mc, MC_ResourceToURL.class);

			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(), CadseGCST.MENU_ABSTRACT_at_ICON_,
					"icon", EPosLabel.left, mc, ic);
			CadseGCST.MENU_ABSTRACT.addField(field);
		}

		// MANAGER_at_QUALIFIED_NAME_TEMPLATE_
		{
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_ItemTypeTemplateForText.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.MANAGER_at_QUALIFIED_NAME_TEMPLATE_, "Qualified name template", EPosLabel.left, null, ic);
			CadseGCST.ITEM_TYPE.addField(field);
		}

		// MANAGER_at_DISPLAY_NAME_TEMPLATE_
		{
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_ItemTypeTemplateForText.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.MANAGER_at_DISPLAY_NAME_TEMPLATE_, "Display name template", EPosLabel.left, null, ic);
			CadseGCST.ITEM_TYPE.addField(field);
		}

		// MANAGER_at_MESSAGE_ERROR_ID_
		{
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.MANAGER_at_MESSAGE_ERROR_ID_, "Error message while invalid name", EPosLabel.left, null,
					null);
			CadseGCST.ITEM_TYPE.addField(field);
		}

		// MANAGER_at_VALID_PATTERN_ID_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc, ValidPattern.class);
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, CadseDefinitionManager.ValidFieldIC.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.MANAGER_at_VALID_PATTERN_ID_, "Valid name pattern", EPosLabel.left, mc, ic);
			CadseGCST.ITEM_TYPE.addField(field);
		}

		// CADSE_DEFINITION_at_IMPORTS_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_LIST_OF_STRING);
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_STRING_LIST_FOR_LIST,
					CadseGCST.IC_STRING_LIST_FOR_LIST_at_ALLOW_DUPLICATE_, false,
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, "",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, "Enter an import package");
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DLIST, UUID.randomUUID(),
					CadseGCST.CADSE_DEFINITION_at_IMPORTS_, "Import", EPosLabel.left, mc, ic,
					CadseGCST.DLIST_at_EDITABLE_BUTTON_, Boolean.TRUE);
			CadseGCST.CADSE_DEFINITION.addField(field);
		}

		// ATTRIBUTE_at_TWEVOL_
		{
			// TW Evol
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_ENUM_FOR_BROWSER_COMBO);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_ENUM);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(), CadseGCST.ATTRIBUTE_at_TWEVOL_,
					"Change impact", EPosLabel.left, mc, ic);
			CadseGCST.ATTRIBUTE.addField(field);
		}

		// ATTRIBUTE_at_TWCOMMIT_KIND_
		{
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_ENUM_FOR_BROWSER_COMBO);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_ENUM);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_, "Conflict impact on commit", EPosLabel.left, mc, ic);
			CadseGCST.ATTRIBUTE.addField(field);
		}

		// ATTRIBUTE_at_TWREV_SPECIFIC_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_BOOLEAN);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_, "Each revision can have a different value", EPosLabel.none,
					mc, null);
			CadseGCST.ATTRIBUTE.addField(field);
		}

		// ATTRIBUTE_at_TWUPDATE_KIND_
		{
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_ENUM_FOR_BROWSER_COMBO);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_ENUM);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_, "Conflict impact on update", EPosLabel.left, mc, ic);
			CadseGCST.ATTRIBUTE.addField(field);
		}

		// LIST_lt_SUB_TYPE
		{
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_ABSTRACT_FOR_BROWSER_COMBO);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_AttributeDestType.class);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc, MC_DestTypeCombo.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(), CadseGCST.LIST_lt_SUB_TYPE,
					"Type of list", EPosLabel.left, mc, ic);
			CadseGCST.LIST.addField(field);
		}

		// LINK_TYPE_at_TWCOUPLED_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_BOOLEAN);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.LINK_TYPE_at_TWCOUPLED_, "Propagates evolution actions in both sides", EPosLabel.none,
					mc, null);
			CadseGCST.LINK_TYPE.addField(field);
		}

		// LINK_TYPE_at_TWDEST_EVOL_
		{
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_ENUM_FOR_BROWSER_COMBO);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_ENUM);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(),
					CadseGCST.LINK_TYPE_at_TWDEST_EVOL_, "Change on dest impact", EPosLabel.left, mc, ic);
			CadseGCST.LINK_TYPE.addField(field);
		}

		// ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_BOOLEAN);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_, "Is abstract", EPosLabel.none, mc, null);
			CadseGCST.ITEM_TYPE.addField(field);
		}

		// ITEM_TYPE_at_IS_INSTANCE_HIDDEN_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_BOOLEAN);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.ITEM_TYPE_at_IS_INSTANCE_HIDDEN_, "Instances are hidden", EPosLabel.none, mc, null);
			CadseGCST.ITEM_TYPE.addField(field);
		}

		// ITEM_TYPE_at_ITEM_FACTORY_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_STRING_TO_JAVA_ELEMENT);
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO,
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, "Select an Factory class",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, "Select an Factory class",
					CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_STYLE_, "CLASSES");
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DBROWSER, UUID.randomUUID(),
					CadseGCST.ITEM_TYPE_at_ITEM_FACTORY_, "Factory class", EPosLabel.left, mc, ic);
			CadseGCST.ITEM_TYPE.addField(field);
		}

		// TYPE_DEFINITION
		{
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST,
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, "Select a destination.");
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_LINK);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DLIST, UUID.randomUUID(),
					CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES, "Attributes ordered", EPosLabel.top, mc, ic,
					CadseGCST.DLIST_at_EDITABLE_BUTTON_, Boolean.FALSE, CadseGCST.DLIST_at_ORDER_BUTTON_, Boolean.TRUE);
			CadseGCST.TYPE_DEFINITION.addField(field);
		}

		// PROJECT_CONTENT_MODEL_at_PROJECT_NAME_
		{
			/*
			 * Par défaut, mettre le focus dans le champ name. Pages de
			 * properties d'un item type is-instance-abstract ==>
			 * is-abstract-instance is-instance-hidden ==> is-hidden-instance
			 * validate-name-re ==> ???????????? goups-of-attributes ==>
			 * gRoups-of-attributes cadse ==> CADSE Page de properties d'un lien
			 * Item-hidden ==> hidden-item item-readonly ==> readonly item
			 * kind????
			 */
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc, MC_PROJECT_NAME.class);
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_ItemTypeTemplateForTextFromManager.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_, "Project name", EPosLabel.left, mc, ic);
			CadseGCST.PROJECT_CONTENT_MODEL.addField(field);
		}

		// FILE_CONTENT_MODEL_at_FILE_PATH_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc, MC_FILE_PATH.class);
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_ItemTypeTemplateForTextFromManager.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_, "File path", EPosLabel.left, mc, ic);
			CadseGCST.FILE_CONTENT_MODEL.addField(field);
		}

		// FOLDER_CONTENT_MODEL_at_FOLDER_PATH_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_ItemTypeTemplateForTextFromManager.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH_, "Folder path", EPosLabel.left, mc, ic);
			CadseGCST.FOLDER_CONTENT_MODEL.addField(field);
		}

		// JAVA_FILE_CONTENT_MODEL_at_CLASS_NAME_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_ItemTypeTemplateForTextFromManager.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.JAVA_FILE_CONTENT_MODEL_at_CLASS_NAME_, "Class name", EPosLabel.left, mc, ic);
			CadseGCST.JAVA_FILE_CONTENT_MODEL.addField(field);
		}

		// JAVA_FILE_CONTENT_MODEL_at_PACKAGE_NAME_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_ItemTypeTemplateForTextFromManager.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.JAVA_FILE_CONTENT_MODEL_at_PACKAGE_NAME_, "Package name", EPosLabel.left, mc, ic);
			CadseGCST.JAVA_FILE_CONTENT_MODEL.addField(field);
		}

		// BOOLEAN - ATTRIBUTE_at_DEFAULT_VALUE_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc, MC_BooleanTextField.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "Default value", EPosLabel.none, mc, null);
			CadseGCST.BOOLEAN.addField(field);
		}

		// DOUBLE - ATTRIBUTE_at_DEFAULT_VALUE_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc, MC_DoubleTextField.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "Default value", EPosLabel.left, mc, null);
			CadseGCST.DOUBLE.addField(field);
		}

		// INTEGER - ATTRIBUTE_at_DEFAULT_VALUE_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc, MC_IntegerTextField.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "Default value", EPosLabel.left, mc, null);
			CadseGCST.INTEGER.addField(field);
		}

		// LONG - ATTRIBUTE_at_DEFAULT_VALUE_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc, MC_LongTextField.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "Default value", EPosLabel.left, mc, null);
			CadseGCST.LONG.addField(field);
		}

		// ENUM - ATTRIBUTE_at_DEFAULT_VALUE_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc, MC_DefaultEnum.class);
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_EnumDefaultValue.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "Default value", EPosLabel.left, mc, ic);
			CadseGCST.ENUM.addField(field);
			ic.setListenAttributes(CadseGCST.ENUM_lt_ENUM_TYPE);
		}

		// ENUM TYPE
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_LINK);
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST,
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, "Select a type enum",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, "Select a type enum");
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DBROWSER, UUID.randomUUID(), CadseGCST.ENUM_lt_ENUM_TYPE,
					"Enum type", EPosLabel.left, mc, ic);
			CadseGCST.ENUM.addField(field);
		}

		// ENUM_TYPE_at_VALUES_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc, MC_DefaultForList.class);
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_STRING_LIST_FOR_LIST,
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, "Enter a new value",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, "Enter a new value",
					CadseGCST.IC_STRING_LIST_FOR_LIST_at_ALLOW_DUPLICATE_, false);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DLIST, UUID.randomUUID(), CadseGCST.ENUM_TYPE_at_VALUES_,
					"Values", EPosLabel.top, mc, ic, CadseGCST.DLIST_at_EDITABLE_BUTTON_, Boolean.TRUE);
			CadseGCST.ENUM_TYPE.addField(field);
		}

		// ENUM_TYPE_at_JAVA_CLASS_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_STRING_TO_JAVA_ELEMENT);
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO,
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, "Select an Enum class",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, "Select an Enum class",
					CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_STYLE_, "ENUMS");
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DBROWSER, UUID.randomUUID(),
					CadseGCST.ENUM_TYPE_at_JAVA_CLASS_, "Enum class", EPosLabel.left, mc, ic);
			CadseGCST.ENUM_TYPE.addField(field);
		}

		// IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_STYLE_
		{
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_STATIC_ARRAY_OF_OBJECT_FOR_BROWSER_COMBO,
					CadseGCST.IC_STATIC_ARRAY_OF_OBJECT_FOR_BROWSER_COMBO_at_VALUES_,
					IC_JavaClassForBrowser_Combo.style_values);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(),
					CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_STYLE_, "Style", EPosLabel.left, null, ic);
			CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO.addField(field);
		}

		// IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_FILTER_
		{
			String message = "The initial pattern to filter the set of types.\n"
					+ "For example \"Abstract\" shows  all types starting with \"Abstract\".\n"
					+ "The meta character '?' representing any character and\n"
					+ "'*' representing any string are supported.\n"
					+ "You can pass an empty string if no filtering is required";
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_FILTER_, "Filter", EPosLabel.left, null, null,
					CadseGCST.DTEXT_at_TOOL_TIP_, message);
			CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO.addField(field);
		}

		// register java implementation
		// TODO move this code in an eclipse extension (implement it)
		{
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), CadseGCST.MC_STRING_TO_JAVA_ELEMENT,
					MC_StringToJavaElement.class);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO,
					IC_JavaClassForBrowser_Combo.class);
		}

		// CadseGCST.CONTENT_ITEM_at_SCM_MODIFIED_
		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_BOOLEAN);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.CONTENT_ITEM_at_SCM_MODIFIED_, "Content is considered modified by the SCM",
					EPosLabel.none, mc, null);
			field.setEditable(true);
			CadseGCST.CONTENT_ITEM.addField(field);
		}

		// CadseGCST.CONTENT_ITEM_at_SCM_REPO_URL_
		{
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.CONTENT_ITEM_at_SCM_REPO_URL_, "Maven SCM repository URL", EPosLabel.left, null, null);
			field.setEditable(true);
			CadseGCST.CONTENT_ITEM.addField(field);
		}

		// CadseGCST.CONTENT_ITEM_at_SCM_REVISION_
		{
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.CONTENT_ITEM_at_SCM_REVISION_, "Revision", EPosLabel.left, null, null);
			field.setEditable(true);
			CadseGCST.CONTENT_ITEM.addField(field);
		}

		// ********** //
		// Validators //
		// ********** //
		{
			JavaClassValidator v = new JavaClassValidator(null);
			v.setClazz(CannotBeUndefinedValidator.class);
			CadseGCST.ITEM.addValidators(v);

			v = new JavaClassValidator(null);
			v.setClazz(NotEmptyValidatorGeneric.class);
			CadseGCST.ITEM.addValidators(v);
		}

		// CADSE_DEFINITION_at_PACKAGENAME_
		{
			JavaClassValidator v = new JavaClassValidator(null);
			v.setClazz(JavaPackageValidator.class);
			v.setListenAttributes(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_);
			CadseGCST.CADSE_DEFINITION.addValidators(v);
		}
		// BOOLEAN
		{
			JavaClassValidator v = new JavaClassValidator(CadseGCST.UIVALIDATOR);
			v.setClazz(BooleanValidator.class);
			v.setListenAttributes(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_, CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);
			CadseGCST.BOOLEAN.addValidators(v);
		}
		// ATTRIBUTE_at_IS_LIST_
		{
			JavaClassValidator v = new JavaClassValidator(null);
			v.setClazz(IsListValidator.class);
			v.setListenAttributes(CadseGCST.ATTRIBUTE_at_IS_LIST_);
			CadseGCST.ATTRIBUTE.addValidators(v);
		}

		// LINK_TYPE_at_MIN_ and LINK_TYPE_at_MAX_
		{
			JavaClassValidator v2 = new JavaClassValidator(null);
			v2.setClazz(MinMaxValidator.class);
			v2.setListenAttributes(CadseGCST.LINK_TYPE_at_MIN_, CadseGCST.LINK_TYPE_at_MAX_);
			CadseGCST.LINK_TYPE.addValidators(v2);
		}

		// LINK_TYPE_at_MIN_ and LINK_TYPE_at_MAX_
		{
			JavaClassValidator v2 = new JavaClassValidator(null);
			v2.setClazz(LinkTypeIsGoupListener.class);
			v2.setListenAttributes(CadseGCST.LINK_TYPE_at_GROUP_);
			CadseGCST.LINK_TYPE.addValidators(v2);
		}

		// ENUM_TYPE_at_JAVA_CLASS_
		{
			JavaClassValidator v = new JavaClassValidator(CadseGCST.UIVALIDATOR);
			v.setClazz(UIEnumValidator.class);
			v.setListenAttributes(CadseGCST.ENUM_TYPE_at_JAVA_CLASS_);
			CadseGCST.ENUM_TYPE.addValidators(v);
		}

		// ATTRIBUTE_at_TWCOMMIT_KIND_
		{
			JavaClassValidator v = new JavaClassValidator(CadseGCST.UIVALIDATOR);
			v.setClazz(UITWCommitValidator.class);
			v.setListenAttributes(CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_);
			CadseGCST.ATTRIBUTE.addValidators(v);
		}

		// CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_
		{
			JavaClassValidator v = new JavaClassValidator(CadseGCST.UIVALIDATOR);
			v.setClazz(ExtendsClassValidator.class);
			v.setListenAttributes(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_);
			CadseGCST.FILE_CONTENT_MODEL.addValidators(v);
		}

		// FILE_CONTENT_MODEL_at_FILE_PATH_
		{
			JavaClassValidator v = new JavaClassValidator(CadseGCST.UIVALIDATOR);
			v.setClazz(NotEmptyValidator.class);
			v.setListenAttributes(CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_);
			CadseGCST.FILE_CONTENT_MODEL.addValidators(v);
		}

		// JAVA_FILE_CONTENT_MODEL_at_CLASS_NAME_
		{
			JavaClassValidator v = new JavaClassValidator(CadseGCST.UIVALIDATOR);
			v.setClazz(NotEmptyValidator.class);
			v.setListenAttributes(CadseGCST.JAVA_FILE_CONTENT_MODEL_at_CLASS_NAME_);
			CadseGCST.JAVA_FILE_CONTENT_MODEL.addValidators(v);
		}

		// JAVA_FILE_CONTENT_MODEL_at_PACKAGE_NAME_
		{
			JavaClassValidator v = new JavaClassValidator(CadseGCST.UIVALIDATOR);
			v.setClazz(NotEmptyValidator.class);
			v.setListenAttributes(CadseGCST.JAVA_FILE_CONTENT_MODEL_at_PACKAGE_NAME_);
			CadseGCST.JAVA_FILE_CONTENT_MODEL.addValidators(v);
		}

		// FOLDER_CONTENT_MODEL_at_FOLDER_PATH_
		{
			JavaClassValidator v = new JavaClassValidator(CadseGCST.UIVALIDATOR);
			v.setClazz(NotEmptyValidator.class);
			v.setListenAttributes(CadseGCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH_);
			CadseGCST.FOLDER_CONTENT_MODEL.addValidators(v);
		}

		// PROJECT_CONTENT_MODEL_at_PROJECT_NAME_
		{
			JavaClassValidator v = new JavaClassValidator(CadseGCST.UIVALIDATOR);
			v.setClazz(NotEmptyValidator.class);
			v.setListenAttributes(CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_);
			CadseGCST.PROJECT_CONTENT_MODEL.addValidators(v);
		}

		// MENU_ABSTRACT_at_LABEL_
		{
			JavaClassValidator v = new JavaClassValidator(CadseGCST.UIVALIDATOR);
			v.setClazz(MenuValidator.class);
			v.setListenAttributes(CadseGCST.MENU_ABSTRACT_at_LABEL_, CadseGCST.ITEM_at_NAME_);
			CadseGCST.MENU.addValidators(v);
		}

		// ************************************** //
		// Pages layout with Groups of attributes //
		// ************************************** //

		// Item pages
		ITEM_GROUP_NAME = createGroupNameItem();
		CadseGCST.ITEM.addGroupOfAttributes(ITEM_GROUP_NAME);

		// CADSE definition pages
		GroupOfAttributesDescriptor cadseDefinitionGroup = createGroup("CadseDefinition", 1, false,
				createGroupNameItem(), CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_,
				createGroupBasicPropertiesCadseDefinition(), createGroupRepositoryConfigurationCadse(),
				CadseGCST.CADSE_DEFINITION_at_IMPORTS_, CadseGCST.CADSE_lt_EXTENDS);
		CadseGCST.CADSE_DEFINITION.addGroupOfAttributes(cadseDefinitionGroup);
		cadseDefinitionGroup.setOverWriteGroup(ITEM_GROUP_NAME);

		// Item Type pages
		GroupOfAttributesDescriptor itemTypeGroup = createGroup("ItemType", 1, false, createGroupNameItem(),
				CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, CadseGCST.ITEM_TYPE_at_ICON_, CadseGCST.ITEM_TYPE_at_PACKAGE_NAME_,
				CadseGCST.ITEM_TYPE_at_ITEM_FACTORY_, createGroupFlagsItemType());
		CadseGCST.ITEM_TYPE.addGroupOfAttributes(itemTypeGroup);
		itemTypeGroup.setOverWriteGroup(ITEM_GROUP_NAME);

		// Attributes pages
		GroupOfAttributesDescriptor attributeGroup = createGroup("Attribute", 1, false, createGroupNameItem(),
				createGroupBasicPropertiesAttributes(), createGroupEvolutionAttributes(),
				createGroupAdvancedPropertiesAttributes());
		CadseGCST.ATTRIBUTE.addGroupOfAttributes(attributeGroup);
		attributeGroup.setOverWriteGroup(ITEM_GROUP_NAME);

		// Boolean pages
		GroupOfAttributesDescriptor booleanGroup = createGroup("Boolean", 1, false, createGroupNameItem(),
				createGroupBasicPropertiesBoolean(), createGroupEvolutionAttributes(),
				createGroupAdvancedPropertiesAttributes());
		CadseGCST.BOOLEAN.addGroupOfAttributes(booleanGroup);
		booleanGroup.setOverWriteGroup(attributeGroup);

		// Link pages
		GroupOfAttributesDescriptor linkGroup = createGroup("Link", 1, false, createGroupNameItem(),
				createGroupPropertiesLink(), createGroupBasicPropertiesAttributes(), createGroupEvolutionLink(),
				createGroupAdvancedPropertiesLink());
		CadseGCST.LINK_TYPE.addGroupOfAttributes(linkGroup);
		linkGroup.setOverWriteGroup(attributeGroup);

		// Field group select before attribute
		GroupOfAttributesDescriptor fieldGroupName = createGroup("Field", 1, false, CadseGCST.FIELD_lt_ATTRIBUTE,
				CadseGCST.ITEM_at_NAME_, CadseGCST.FIELD_at_LABEL_, CadseGCST.ITEM_at_DISPLAY_NAME_,
				CadseGCST.ITEM_at_QUALIFIED_NAME_);
		fieldGroupName.setOverWriteGroup(ITEM_GROUP_NAME);
		CadseGCST.FIELD.addGroupOfAttributes(fieldGroupName);

		// Content model
		GroupOfAttributesDescriptor fieldCM = createGroupContentModel();
		fieldCM.setOverWriteGroup(ITEM_GROUP_NAME);
		CadseGCST.CONTENT_ITEM_TYPE.addGroupOfAttributes(fieldCM);

		// ***************** //
		// Hidden attributes //
		// ***************** //

		// Hidden page for LinkType
		IPage ltHiddenAttributes = new PageImpl(UUID.randomUUID(), "Hidden attributes", "Hidden attributes",
				"Hidden attributes", "Hidden attributes", false, null);
		ltHiddenAttributes.addHiddenAttributes(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_,
				CadseGCST.ATTRIBUTE_at_NATIF_, CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, CadseGCST.ATTRIBUTE_at_IS_LIST_);
		CadseGCST.LINK_TYPE.addCreationPages(ltHiddenAttributes);
		CadseGCST.LINK_TYPE.addModificationPages(ltHiddenAttributes);

		// Hidden page for Enum
		IPage enumHiddenAttributes = new PageImpl(UUID.randomUUID(), "Hidden attributes", "Hidden attributes",
				"Hidden attributes", "Hidden attributes", false, null);
		enumHiddenAttributes.addHiddenAttributes(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_);
		CadseGCST.ENUM.addCreationPages(enumHiddenAttributes);
		CadseGCST.ENUM.addModificationPages(enumHiddenAttributes);

		// Hidden attributes for content model
		IPage cmHiddenAttributes = new PageImpl(UUID.randomUUID(), "Hidden attributes", "Hidden attributes",
				"Hidden attributes", "Hidden attributes", false, null);
		cmHiddenAttributes.addHiddenAttributes(CadseGCST.ITEM_at_NAME_, CadseGCST.ITEM_at_DISPLAY_NAME_,
				CadseGCST.ITEM_at_QUALIFIED_NAME_);
		CadseGCST.CONTENT_ITEM_TYPE.addCreationPages(cmHiddenAttributes);
		CadseGCST.CONTENT_ITEM_TYPE.addModificationPages(cmHiddenAttributes);

		// Hidden attribute for JavaFile
		IPage jfHiddenAttributes = new PageImpl(UUID.randomUUID(), "Hidden attributes", "Hidden attributes",
				"Hidden attributes", "Hidden attributes", false, null);
		jfHiddenAttributes.addHiddenAttributes(CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_);
		CadseGCST.JAVA_FILE_CONTENT_MODEL.addCreationPages(jfHiddenAttributes);
		CadseGCST.JAVA_FILE_CONTENT_MODEL.addModificationPages(jfHiddenAttributes);

		// Hidden attributes for ContentLT
		IPage cltHiddenAttributes = new PageImpl(UUID.randomUUID(), "Hidden attributes", "Hidden attributes",
				"Hidden attributes", "Hidden attributes", false, null);
		cltHiddenAttributes.addHiddenAttributes(CadseGCST.ITEM_at_NAME_, CadseGCST.ITEM_at_DISPLAY_NAME_,
				CadseGCST.ITEM_at_QUALIFIED_NAME_, CadseGCST.LINK_TYPE_lt_SOURCE, CadseGCST.LINK_TYPE_lt_DESTINATION,
				CadseGCST.LINK_TYPE_lt_INVERSE_LINK, CadseGCST.LINK_TYPE_at_ANNOTATION_,
				CadseGCST.LINK_TYPE_at_AGGREGATION_, CadseGCST.LINK_TYPE_at_COMPOSITION_, CadseGCST.LINK_TYPE_at_PART_,
				CadseGCST.LINK_TYPE_at_REQUIRE_, CadseGCST.LINK_TYPE_at_MAPPING_, CadseGCST.LINK_TYPE_at_GROUP_,
				CadseGCST.LINK_TYPE_at_HIDDEN_, CadseGCST.LINK_TYPE_at_SELECTION_,
				CadseGCST.LINK_TYPE_at_LINK_MANAGER_, CadseGCST.LINK_TYPE_at_MIN_, CadseGCST.LINK_TYPE_at_MAX_,
				CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_, CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_,
				CadseGCST.ATTRIBUTE_at_TRANSIENT_, CadseGCST.CONTENT_LINK_TYPE_lt_CONTENT_DEFINITION,
				CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_, CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_,
				CadseGCST.LINK_TYPE_at_TWCOUPLED_, CadseGCST.LINK_TYPE_at_TWDEST_EVOL_);
		CadseGCST.CONTENT_LINK_TYPE.addModificationPages(cltHiddenAttributes);

		// Hidden attributes for ContentLT
		IPage listHiddenAttributes = new PageImpl(UUID.randomUUID(), "Hidden attributes", "Hidden attributes",
				"Hidden attributes", "Hidden attributes", false, null);
		listHiddenAttributes.addHiddenAttributes(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_,
				CadseGCST.ATTRIBUTE_at_IS_LIST_, CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_);
		CadseGCST.LIST.addModificationPages(listHiddenAttributes);
		CadseGCST.LIST.addCreationPages(listHiddenAttributes);

		// Add a dynamic filter for attribute definiion inner in a list
		// definition
		// ex : a list of long (long attr def : somme attribute must be hidden)
		CadseGCST.ATTRIBUTE.addAdapter(new PageParticipator() {
			@Override
			public void filterPage(Item item, FilterContext context, List<IPage> list, Set<IAttributeType<?>> ro,
					HashSet<IAttributeType<?>> hiddenAttributeInComputedPages) {

				
				Item parentItem = item.getPartParent();
				if (parentItem != null && parentItem.getType() == CadseGCST.LIST) {
					if (!context.isModificationPages()) {
						AttributeManager.setCannotBeUndefinedAttribute(item, true);
					}
					hiddenAttributeInComputedPages.addAll(Arrays.asList(CadseGCST.ITEM_at_NAME_,
							CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_, CadseGCST.ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_,
							CadseGCST.ATTRIBUTE_at_IS_LIST_, CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_,
							CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_, CadseGCST.ATTRIBUTE_at_TWEVOL_,
							CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_, CadseGCST.ATTRIBUTE_at_NATIF_,
							CadseGCST.ATTRIBUTE_at_TRANSIENT_,
							CadseGCST.ITEM_at_DISPLAY_NAME_,
							CadseGCST.ITEM_at_QUALIFIED_NAME_));
				}
			};
		});
		// ***** //
		// Pages //
		// ***** //

		// Evolution control page
		{
			IPage itemEvolPage = new PageImpl(UUID.randomUUID(), "Evolution control", "Evolution control",
					"Evolution control", "Evolution control", false, null, CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES,
					CadseGCST.ITEM_at_REV_MODIFIED_, CadseGCST.ITEM_at_REQUIRE_NEW_REV_, CadseGCST.ITEM_at_TW_VERSION_,
					CadseGCST.ITEM_at_COMMITTED_DATE_, CadseGCST.ITEM_at_COMMITTED_BY_,
					CadseGCST.ITEM_at_TWLAST_COMMENT_

			);
			CadseGCST.ITEM.addModificationPages(itemEvolPage);

			// SCM page
			IPage evolPage = new PageImpl(UUID.randomUUID(), "SCM parameters", "SCM parameters", "SCM parameters",
					"SCM parameters", false, null, CadseGCST.CONTENT_ITEM_at_SCM_MODIFIED_,
					CadseGCST.CONTENT_ITEM_at_SCM_REPO_URL_, CadseGCST.CONTENT_ITEM_at_SCM_REVISION_);
			CadseGCST.CONTENT_ITEM.addModificationPages(evolPage);
			evolPage.addOverridePage(itemEvolPage);
		}

		// Instance name control page
		{
			PageImpl nameControl = new PageImpl(UUID.randomUUID(), CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE,
					CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE, CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE,
					CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE, true, null,
					CadseGCST.MANAGER_at_QUALIFIED_NAME_TEMPLATE_, CadseGCST.MANAGER_at_DISPLAY_NAME_TEMPLATE_,
					CadseGCST.MANAGER_at_VALID_PATTERN_ID_, CadseGCST.MANAGER_at_MESSAGE_ERROR_ID_);

			ChangeItemAction action = new ChangeItemAction(nameControl);
			nameControl.setActionPage(action);
			CadseGCST.ITEM_TYPE.addModificationPages(nameControl);
			CadseGCST.ITEM_TYPE.addCreationPages(nameControl);
		}

		{
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc, MC_ViewItemType.class);
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_ABSTRACT_FOR_CHECKED);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic, IC_ViewManager_DataModelView.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCHECKED_TREE, UUID.randomUUID(),
					CadseGCST.VIEW_lt_VIEW_ITEM_TYPES, "Item types", EPosLabel.none, mc, ic);
			CadseGCST.VIEW.addField(field);
		}
	}
}

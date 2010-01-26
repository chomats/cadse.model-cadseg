package fr.imag.adele.cadse.cadseg.pages;

import java.util.UUID;

import org.eclipse.jdt.ui.IJavaElementSearchConstants;

import fede.workspace.eclipse.java.fields.IC_JavaClassForBrowser_Combo;
import fede.workspace.eclipse.java.fields.MC_StringToJavaElement;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_BooleanDefaultValue;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_DestinationLinkForBrowser_Combo;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_EnumDefaultValue;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_ItemTypeTemplateForTextFromManager;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_ItemtypeIcon;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_SuperTypeForBrowser_Combo;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_BooleanTextField;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_DefaultEnum;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_DoubleTextField;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_FILE_NAME;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_FILE_PATH;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_IntegerTextField;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_LongTextField;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_PROJECT_NAME;
import fr.imag.adele.cadse.cadseg.pages.mc.MC_ResourceToURL;
import fr.imag.adele.cadse.cadseg.validators.JavaPackageValidator;
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
	 * 
	 * @return the group
	 */
	private static GroupOfAttributesDescriptor createGroup(String name,
			int column, boolean border, IAttributeType<?>... attr) {

		GroupOfAttributesDescriptor goad = new GroupOfAttributesDescriptor(
				name, column);
		goad.setHasBoxGroup(border);

		for (IAttributeType<?> a : attr)
			goad.add(a);

		return goad;
	}

	/**
	 * Helper method which creates the group Name.
	 * 
	 * @return the group name.
	 */
	private static GroupOfAttributesDescriptor createGroupName() {
		return createGroup("Name", 1, false,
				CadseGCST.ITEM_at_NAME_, CadseGCST.ITEM_at_DISPLAY_NAME_,
				CadseGCST.ITEM_at_QUALIFIED_NAME_);
	}

	/**
	 * Helper method which creates the group properties.
	 *  
	 * @return the group properties.
	 */
	private static GroupOfAttributesDescriptor createGroupBasicProperties() {

		GroupOfAttributesDescriptor gBasicProperties = createGroup(
				"Basic properties", 1, true);
		
		// Specific part 1 : Default value
		GroupOfAttributesDescriptor gSpecificPart1 = createGroup(
				"Basic properties", 1, false, 
				CadseGCST.ENUM_lt_ENUM_TYPE,
				CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);
		gBasicProperties.add(gSpecificPart1);

		// Specific part 2 : check boxes
		GroupOfAttributesDescriptor gSpecificPart2 = createGroup(
				"Basic properties", 3, false,
				CadseGCST.ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_,
				CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_,
				CadseGCST.ATTRIBUTE_at_IS_LIST_);
		gBasicProperties.add(gSpecificPart2);
		
		return gBasicProperties;
	}

	/**
	 * Helper method which creates the group for editing link properties. 
	 * 
	 * @return this specific group.
	 */
	private static GroupOfAttributesDescriptor createGroupLinkProperties() {

		// Link properties container
		GroupOfAttributesDescriptor gLinkProperties = createGroup(
				"Link properties", 1, true);
		
		// Part1
		GroupOfAttributesDescriptor gPart1 = createGroup("Link properties", 1,
				false, CadseGCST.LINK_TYPE_lt_SOURCE,
				CadseGCST.LINK_TYPE_lt_DESTINATION,
				CadseGCST.LINK_TYPE_lt_INVERSE_LINK);
		gLinkProperties.add(gPart1);

		// Part2 : flags
		GroupOfAttributesDescriptor gPart2 = createGroup("Link properties", 4,
				false, CadseGCST.LINK_TYPE_at_ANNOTATION_,
				CadseGCST.LINK_TYPE_at_AGGREGATION_,
				CadseGCST.LINK_TYPE_at_COMPOSITION_,
				CadseGCST.LINK_TYPE_at_PART_, CadseGCST.LINK_TYPE_at_REQUIRE_,
				CadseGCST.LINK_TYPE_at_MAPPING_, CadseGCST.LINK_TYPE_at_GROUP_,
				CadseGCST.LINK_TYPE_at_HIDDEN_);
		gLinkProperties.add(gPart2);

		// Part3 : selection and link manager
		GroupOfAttributesDescriptor gPart3 = createGroup("Link properties", 1,
				false, CadseGCST.LINK_TYPE_at_SELECTION_,
				CadseGCST.LINK_TYPE_at_LINK_MANAGER_);
		gLinkProperties.add(gPart3);

		// Part4 : cardinalities
		GroupOfAttributesDescriptor gPart4 = createGroup("Cardinalities", 2,
				false, CadseGCST.LINK_TYPE_at_MIN_, CadseGCST.LINK_TYPE_at_MAX_);
		gLinkProperties.add(gPart4);

		return gLinkProperties;
	}

	private static GroupOfAttributesDescriptor createGroupEvolutionGeneral() {
		return createGroup("Evolution", 2, true,
				CadseGCST.ATTRIBUTE_at_TWEVOL_,
				CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_,
				CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_,
				CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_);
	}

	
	private static GroupOfAttributesDescriptor createGroupEvolutionLink() {
		return createGroup("Evolution", 2,
				true, CadseGCST.ATTRIBUTE_at_TWEVOL_,
				CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_,
				CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_,
				CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_,
				CadseGCST.LINK_TYPE_at_TWCOUPLED_,
				CadseGCST.LINK_TYPE_at_TWDEST_EVOL_);
	}
	
	private static GroupOfAttributesDescriptor createGroupAdvancedPropertiesGeneral() {
		return createGroup(
				"Advanced properties", 3, true,
				CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_,
				CadseGCST.ATTRIBUTE_at_FINAL_, CadseGCST.ATTRIBUTE_at_NATIF_,
				CadseGCST.ATTRIBUTE_at_REQUIRE_,
				CadseGCST.ATTRIBUTE_at_TRANSIENT_,
				CadseGCST.STRING_at_NOT_EMPTY_);
	}
	
	private static GroupOfAttributesDescriptor createGroupAdvancedPropertiesLink() {
		return createGroup(
				"Advanced properties", 3, true,
				CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_,
				CadseGCST.ATTRIBUTE_at_NATIF_,
				CadseGCST.ATTRIBUTE_at_TRANSIENT_);
	}

	public static void init() throws CadseException {

		// **************************************************************** //
		// Displayed attributes in creation pages and in modification pages //
		// **************************************************************** //

		// if HIDDEN_IN_COMPUTED_PAGES is true : hidden in creation page and
		// modification.
		// if MUST_BE_INITIALIZED_AT_CREATION_TIME is true : show in creation
		// page, false not
		// if MUST_BE_INITIALIZED_AT_CREATION_TIME is false show only in
		// modification page (if HIDDEN_IN_COMPUTED_PAGES is false)

		
		CadseGCST.CADSE_DEFINITION_lt_BUILD.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.CADSE_DEFINITION_lt_CONFIGURATION.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.CADSE_DEFINITION_lt_MAPPING.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.ITEM_lt_INSTANCE_OF.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.ITEM_at_NAME_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.CADSE_lt_EXTENDS.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.TYPE_DEFINITION_lt_CADSE.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.ITEM_TYPE_lt_SUPER_TYPE.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ITEM_TYPE_at_HAS_CONTENT_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ATTRIBUTE_at_IS_LIST_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_at_AGGREGATION_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_at_ANNOTATION_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_at_COMPOSITION_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_at_GROUP_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_at_HIDDEN_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_at_MAPPING_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_at_MAX_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_at_MIN_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_at_SELECTION_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.LINK_TYPE_at_PART_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_at_REQUIRE_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_at_TWDEST_EVOL_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_at_TWCOUPLED_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_lt_DESTINATION.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_TYPE_lt_INVERSE_LINK.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.LINK_TYPE_lt_INVERSE_LINK
				.setFlag(Item.CAN_BE_UNDEFINED, true);
		CadseGCST.LINK_TYPE_lt_SOURCE.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.LINK_TYPE_at_KIND_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.LINK_TYPE_at_KIND_.setFlag(
				Item.HIDDEN_IN_COMPUTED_PAGES, true);
		CadseGCST.ENUM_lt_ENUM_TYPE.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ENUM_TYPE_at_VALUES_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.RUNTIME_ITEM_at_EXTENDS_CLASS_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.FILE_CONTENT_MODEL_at_FILE_NAME_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_.setFlag(
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ENUM_at_ENUM_CLAZZ_.setFlag(
				Item.HIDDEN_IN_COMPUTED_PAGES, true);
		CadseGCST.ENUM_at_VALUES_.setFlag(
				Item.HIDDEN_IN_COMPUTED_PAGES, true);
		CadseGCST.ATTRIBUTE_at_DEV_GENERATED_.setFlag(
				Item.HIDDEN_IN_COMPUTED_PAGES, true);
		CadseGCST.ATTRIBUTE_at_ID_RUNTIME_.setFlag(
				Item.HIDDEN_IN_COMPUTED_PAGES, true);
		CadseGCST.ATTRIBUTE_lt_WC_LISTENS.setFlag(
				Item.HIDDEN_IN_COMPUTED_PAGES, true);
		CadseGCST.ITEM_at_ID_.setFlag(
				Item.HIDDEN_IN_COMPUTED_PAGES, true);
		CadseGCST.ITEM_at_ISVALID_.setFlag(
				Item.HIDDEN_IN_COMPUTED_PAGES, true);
		CadseGCST.ITEM_at_ITEM_HIDDEN_.setFlag(
				Item.HIDDEN_IN_COMPUTED_PAGES, true);
		CadseGCST.ITEM_at_ITEM_READONLY_.setFlag(
				Item.HIDDEN_IN_COMPUTED_PAGES, true);
		CadseGCST.ITEM_lt_CONTENTS.setFlag(
				Item.HIDDEN_IN_COMPUTED_PAGES, true);
		CadseGCST.ITEM_lt_INSTANCE_OF.setFlag(
				Item.HIDDEN_IN_COMPUTED_PAGES, true);
		CadseGCST.ITEM_lt_PARENT.setFlag(
				Item.HIDDEN_IN_COMPUTED_PAGES, true);
		
		
		// ************************** //
		// Specific fields definition //
		// ************************** //

		// create name field ( overwrite mc)
		CadseGCST.ITEM.addField(new UIFieldImpl(CadseGCST.DTEXT, UUID
				.randomUUID(), CadseGCST.ITEM_at_NAME_, "name", EPosLabel.left,
				new MC_Descriptor(CadseGCST.MC_NAME_ATTRIBUTE), null));

		// create super type field (overwrite ic)
		{
			IC_Descriptor icSupertype = new IC_Descriptor(
					CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST,

					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
					"Select a super type",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
					"Select a super type");
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(),
					icSupertype, IC_SuperTypeForBrowser_Combo.class);

			CadseGCST.ITEM_TYPE.addField(new UIFieldImpl(CadseGCST.DBROWSER,
					UUID.randomUUID(), CadseGCST.ITEM_TYPE_lt_SUPER_TYPE,
					"super type:", EPosLabel.left, new MC_Descriptor(
							CadseGCST.MC_LINK), icSupertype));
		}

		// ITEM_TYPE_at_ICON_
		{
			IC_Descriptor icIcon = new IC_Descriptor(
					CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST,

					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
					"Select an icon",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
					"Select a icon");
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icIcon,
					IC_ItemtypeIcon.class);
			MC_Descriptor mc = new MC_Descriptor(
					CadseGCST.INTERACTION_CONTROLLER);

			CreatedObjectManager.register(null, mc, MC_ResourceToURL.class);
			CadseGCST.ITEM_TYPE.addField(new UIFieldImpl(CadseGCST.DBROWSER,
					UUID.randomUUID(), CadseGCST.ITEM_TYPE_at_ICON_, "icon",
					EPosLabel.left, mc, icIcon));
		}

		// create destination field (overwrite ic)
		{
			IC_Descriptor icDesttype = new IC_Descriptor(
					CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST,
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
					"Select a destination",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
					"Select a destination");
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(),
					icDesttype, IC_DestinationLinkForBrowser_Combo.class);

			CadseGCST.LINK_TYPE.addField(new UIFieldImpl(CadseGCST.DBROWSER,
					UUID.randomUUID(), CadseGCST.LINK_TYPE_lt_DESTINATION,
					"destination:", EPosLabel.left, new MC_Descriptor(
							CadseGCST.MC_LINK,
							CadseGCST.MC_LINK_at_ERROR_MESSAGE_,
							"You must set the destination"), icDesttype));
		}

		// create inverse link field (overwrite ic)
		{
			IC_Descriptor icInverseLink = new IC_Descriptor(
					CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST,
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
					"Select an inverse link",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
					"Select an inverse link");
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(),
					icInverseLink,
					fr.imag.adele.cadse.cadseg.pages.ic.IC_InverseLink.class);

			CadseGCST.LINK_TYPE.addField(new UIFieldImpl(CadseGCST.DBROWSER,
					UUID.randomUUID(), CadseGCST.LINK_TYPE_lt_INVERSE_LINK,
					"inverse link:", EPosLabel.left, new MC_Descriptor(
							CadseGCST.MC_LINK), icInverseLink));

		}

		// min
		{
			IC_Descriptor icMin = new IC_Descriptor(
					CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icMin,
					IC_Min.class);

			CadseGCST.LINK_TYPE
					.addField(new UIFieldImpl(CadseGCST.DTEXT, UUID
							.randomUUID(), CadseGCST.LINK_TYPE_at_MIN_, "min:",
							EPosLabel.left, new MC_Descriptor(
									CadseGCST.MC_INTEGER,
									CadseGCST.MC_INTEGER_at_DEFAULT_VALUE_, 0,
									CadseGCST.MC_INTEGER_at_ERROR_MSG_MIN_,
									"min must be >= 0",
									CadseGCST.MC_INTEGER_at_MIN_, 0), icMin));
		}

		// max
		{
			IC_Descriptor icMax = new IC_Descriptor(
					CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icMax,
					IC_Max.class);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_INTEGER,
					CadseGCST.MC_INTEGER_at_DEFAULT_VALUE_, 0,
					CadseGCST.MC_INTEGER_at_ERROR_MSG_MIN_,
					"max must greter than 0", CadseGCST.MC_INTEGER_at_MIN_, 0);
			CreatedObjectManager.register(null, mc, MaxModelController.class);
			CadseGCST.LINK_TYPE.addField(new UIFieldImpl(CadseGCST.DTEXT, UUID
					.randomUUID(), CadseGCST.LINK_TYPE_at_MAX_, "max:",
					EPosLabel.left, mc, icMax));
		}

		// ********** //
		// Validators //
		// ********** //

		// create a validator for attribute package name
		{
			JavaClassValidator v = new JavaClassValidator(null);
			v.setClazz(JavaPackageValidator.class); // class d'implementation du
													// validateur
			v.setListenAttributes(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_); // les
																				// champs
																				// qu'il
																				// ecoute
			CadseGCST.CADSE_DEFINITION.addValidators(v);
		}

		// create a validator for attribute min and max value
		{
			JavaClassValidator v2 = new JavaClassValidator(null);
			v2.setClazz(MinMaxValidator.class);
			v2.setListenAttributes(CadseGCST.LINK_TYPE_at_MIN_,
					CadseGCST.LINK_TYPE_at_MAX_);
			CadseGCST.LINK_TYPE.addValidators(v2);
		}

		// ************************************** //
		// Pages layout with Groups of attributes //
		// ************************************** //

		ITEM_GROUP_NAME = createGroupName();
		CadseGCST.ITEM.addGroupOfAttributes(ITEM_GROUP_NAME);
		
		// For general attributes pages 
		GroupOfAttributesDescriptor general = createGroup("Attribute", 1, false,
				createGroupName(),
				createGroupBasicProperties(),
				createGroupEvolutionGeneral(),
				createGroupAdvancedPropertiesGeneral());
		general.setOverWriteGroup(ITEM_GROUP_NAME);
		// Link special page
		GroupOfAttributesDescriptor link = createGroup("Link", 1, false,
				createGroupName(),
				createGroupLinkProperties(),
				createGroupBasicProperties(),
				createGroupEvolutionLink(),
				createGroupAdvancedPropertiesLink());
		
		// Overwrite rules
		CadseGCST.ATTRIBUTE.addGroupOfAttributes(general);
		CadseGCST.LINK_TYPE.addGroupOfAttributes(link);
		link.setOverWriteGroup(general);		

		
		// ***************** //
		// Hidden attributes //
		// ***************** //
		
		// Hidden page for LinkType
		IPage ltHiddenAttributes = new PageImpl(UUID.randomUUID(),
				"Hidden attributes", "Hidden attributes", "Hidden attributes",
				"Hidden attributes", false, null);
		ltHiddenAttributes.addHiddenAttributes(
				CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_,
				CadseGCST.ATTRIBUTE_at_FINAL_, CadseGCST.ATTRIBUTE_at_NATIF_,
				CadseGCST.ATTRIBUTE_at_REQUIRE_,
				CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_,
				CadseGCST.ATTRIBUTE_at_IS_LIST_);
		CadseGCST.LINK_TYPE.addCreationPages(ltHiddenAttributes);
		CadseGCST.LINK_TYPE.addModificationPages(ltHiddenAttributes);

		
		// ***** //
		// Pages //
		// ***** //
		
		// Evolution control page
		{
			IPage evolPage = new PageImpl(UUID.randomUUID(),
					"Evolution control", "Evolution control",
					"Evolution control", "Evolution control", false,

					null, CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES,
					CadseGCST.ITEM_at_REV_MODIFIED_,
					CadseGCST.ITEM_at_REQUIRE_NEW_REV_,
					CadseGCST.ITEM_at_TW_VERSION_,
					CadseGCST.ITEM_at_COMMITTED_DATE_,
					CadseGCST.ITEM_at_COMMITTED_BY_,
					CadseGCST.ITEM_at_TWLAST_COMMENT_

			);

			CadseGCST.ITEM.addModificationPages(evolPage);

			// create name field ( overwrite mc)
			// Modified attributes
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DLIST, UUID
					.randomUUID(), CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES,
					"Modified attributes", EPosLabel.top, new MC_Descriptor(
							CadseGCST.MC_LINK), new IC_Descriptor(
							CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST));
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);

			// Rev modified
			field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.ITEM_at_REV_MODIFIED_, "Is modified",
					EPosLabel.none, new MC_Descriptor(CadseGCST.MC_BOOLEAN),
					null);
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);

			// Require new rev
			field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.ITEM_at_REQUIRE_NEW_REV_,
					"Next commit will create a new revision", EPosLabel.none,
					new MC_Descriptor(CadseGCST.MC_BOOLEAN), null);
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);

			// TW version
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.ITEM_at_TW_VERSION_, "Revision number",
					EPosLabel.left, new MC_Descriptor(CadseGCST.MC_INTEGER),
					null);
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);

			// Commited date
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.ITEM_at_COMMITTED_DATE_, "Last commit date",
					EPosLabel.left, new MC_Descriptor(CadseGCST.MC_DATE), null);
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);

			// commited by
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.ITEM_at_COMMITTED_BY_, "Last committer",
					EPosLabel.left, null, null);
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);

			// last commit comment
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.ITEM_at_TWLAST_COMMENT_, "Last commit comment",
					EPosLabel.left, null, null);
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);
		}

		// Instance name control page
		{
			PageImpl nameControl = new PageImpl(UUID.randomUUID(),
					CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE,
					CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE,
					CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE,
					CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE, true, null,
					CadseGCST.MANAGER_at_QUALIFIED_NAME_TEMPLATE_,
					CadseGCST.MANAGER_at_DISPLAY_NAME_TEMPLATE_,
					CadseGCST.MANAGER_at_VALID_PATTERN_ID_,
					CadseGCST.MANAGER_at_MESSAGE_ERROR_ID_

			);
			ChangeItemAction action = new ChangeItemAction(nameControl);
			nameControl.setActionPage(action);
			CadseGCST.ITEM_TYPE.addModificationPages(nameControl);
			CadseGCST.ITEM_TYPE.addCreationPages(nameControl);

			// qualified name template
			IC_Descriptor ic = new IC_Descriptor(
					CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic,
					IC_ItemTypeTemplateForText.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID
					.randomUUID(),
					CadseGCST.MANAGER_at_QUALIFIED_NAME_TEMPLATE_,
					"Qualified name template", EPosLabel.left, null, ic);
			CadseGCST.ITEM_TYPE.addField(field);

			// display name template
			ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic,
					IC_ItemTypeTemplateForText.class);
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.MANAGER_at_DISPLAY_NAME_TEMPLATE_,
					"Display name template", EPosLabel.left, null, ic);
			CadseGCST.ITEM_TYPE.addField(field);

			// message error ID
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.MANAGER_at_MESSAGE_ERROR_ID_,
					"Error message while invalid name", EPosLabel.left, null,
					null);
			CadseGCST.ITEM_TYPE.addField(field);

			// Valid pattern ID
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
					ValidPattern.class);
			ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic,
					CadseDefinitionManager.ValidFieldIC.class);
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.MANAGER_at_VALID_PATTERN_ID_,
					"Valid name pattern", EPosLabel.left, mc, ic);
			CadseGCST.ITEM_TYPE.addField(field);
		}

		// Editor evolution fields
		{
			// TW Evol
			IC_Descriptor ic = new IC_Descriptor(
					CadseGCST.IC_ENUM_FOR_BROWSER_COMBO);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCOMBO, UUID
					.randomUUID(), CadseGCST.ATTRIBUTE_at_TWEVOL_,
					"Change impact", EPosLabel.left, new MC_Descriptor(
							CadseGCST.MC_ENUM), ic);
			CadseGCST.ATTRIBUTE.addField(field);

			// TW commit kind
			ic = new IC_Descriptor(CadseGCST.IC_ENUM_FOR_BROWSER_COMBO);
			field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_,
					"Conflict impact on commit", EPosLabel.left,
					new MC_Descriptor(CadseGCST.MC_ENUM), ic);
			CadseGCST.ATTRIBUTE.addField(field);

			// TW rev specific
			field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_,
					"Each revision can have a different value", EPosLabel.none,
					new MC_Descriptor(CadseGCST.MC_BOOLEAN), null);
			CadseGCST.ATTRIBUTE.addField(field);

			// TW update kind
			ic = new IC_Descriptor(CadseGCST.IC_ENUM_FOR_BROWSER_COMBO);
			field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_,
					"Conflict impact on update", EPosLabel.left,
					new MC_Descriptor(CadseGCST.MC_ENUM), ic);
			CadseGCST.ATTRIBUTE.addField(field);

			// TW coupled
			field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.LINK_TYPE_at_TWCOUPLED_,
					"Propagates evolution actions in both sides",
					EPosLabel.none, new MC_Descriptor(CadseGCST.MC_BOOLEAN),
					null);
			CadseGCST.LINK_TYPE.addField(field);

			// TW dest evol
			ic = new IC_Descriptor(CadseGCST.IC_ENUM_FOR_BROWSER_COMBO);
			field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(),
					CadseGCST.LINK_TYPE_at_TWDEST_EVOL_,
					"Change on dest impact", EPosLabel.left, new MC_Descriptor(
							CadseGCST.MC_ENUM), ic);
			CadseGCST.LINK_TYPE.addField(field);
		}

		// LABELS ...
		/**
		 * Page de création d'un item type
		 */
		// **************************/
		// ** IS_INSTANCE_ABSTRACT **/
		// **************************/
		UIFieldImpl field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID
				.randomUUID(), CadseGCST.ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_,
				"Is abstract", EPosLabel.none, new MC_Descriptor(
						CadseGCST.MC_BOOLEAN), null);
		CadseGCST.ITEM_TYPE.addField(field);

		// **************************/
		// ** IS_INSTANCE_HIDDEN **/
		// **************************/
		field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
				CadseGCST.ITEM_TYPE_at_IS_INSTANCE_HIDDEN_,
				"Instances are hidden", EPosLabel.none, new MC_Descriptor(
						CadseGCST.MC_BOOLEAN), null);
		CadseGCST.ITEM_TYPE.addField(field);

		/**
		 * Par défaut, mettre le focus dans le champ name. Pages de properties
		 * d'un item type is-instance-abstract ==> is-abstract-instance
		 * is-instance-hidden ==> is-hidden-instance validate-name-re ==>
		 * ???????????? goups-of-attributes ==> gRoups-of-attributes cadse ==>
		 * CADSE Page de properties d'un lien Item-hidden ==> hidden-item
		 * item-readonly ==> readonly item kind????
		 */

		// **************************/
		// ** PROJECT_NAME **/
		// **************************/
		MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_PROJECT_NAME.class);
		IC_Descriptor ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic,
				IC_ItemTypeTemplateForTextFromManager.class);
		field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
				CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_,
				"Project name", EPosLabel.left, mc, ic);
		CadseGCST.PROJECT_CONTENT_MODEL.addField(field);

		// **************************/
		// ** FILE_NAME **/
		// **************************/
		mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_FILE_NAME.class);
		ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic,
				IC_ItemTypeTemplateForTextFromManager.class);
		field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
				CadseGCST.FILE_CONTENT_MODEL_at_FILE_NAME_, "File name",
				EPosLabel.left, mc, ic);
		CadseGCST.FILE_CONTENT_MODEL.addField(field);

		// **************************/
		// ** FILE_PATH **/
		// **************************/
		mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_FILE_PATH.class);
		ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic,
				IC_ItemTypeTemplateForTextFromManager.class);
		field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
				CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_, "File path",
				EPosLabel.left, mc, ic);
		CadseGCST.FILE_CONTENT_MODEL.addField(field);

		// ***************************/
		// ** BOOLEAN DEFAULT VALUE **/
		// ***************************/
		mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_BooleanTextField.class);
		ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic,
				IC_BooleanDefaultValue.class);
		field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
				CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "Default value",
				EPosLabel.left, mc, ic);
		CadseGCST.BOOLEAN.addField(field);

		// **************************/
		// ** DOUBLE DEFAULT VALUE **/
		// **************************/
		mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_DoubleTextField.class);
		field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
				CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "Default value",
				EPosLabel.left, mc, null);
		CadseGCST.DOUBLE.addField(field);

		// ***************************/
		// ** INTEGER DEFAULT VALUE **/
		// ***************************/
		mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_IntegerTextField.class);
		field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
				CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "Default value",
				EPosLabel.left, mc, null);
		CadseGCST.INTEGER.addField(field);

		// **************************/
		// ** LONG DEFAULT VALUE **/
		// **************************/
		mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_LongTextField.class);
		field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
				CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "Default value",
				EPosLabel.left, mc, null);
		CadseGCST.LONG.addField(field);

		// **************************/
		// ** ENUM DEFAULT VALUE **/
		// **************************/
		mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_DefaultEnum.class);
		ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic,
				IC_EnumDefaultValue.class);
		field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
				CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "Default value",
				EPosLabel.left, mc, ic);
		CadseGCST.ENUM.addField(field);
		ic.setListenAttributes(CadseGCST.ENUM_lt_ENUM_TYPE);

		// **************************/
		// ** ENUM TYPE **/
		// **************************/
		mc = new MC_Descriptor(CadseGCST.MC_LINK);
		ic = new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST,
				CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
				"Select a type enum",
				CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
				"Select a type enum");
		field = new UIFieldImpl(CadseGCST.DBROWSER, UUID.randomUUID(),
				CadseGCST.ENUM_lt_ENUM_TYPE, "Enum type", EPosLabel.left, mc,
				ic);
		CadseGCST.ENUM.addField(field);

		// *************************/
		// ** ENUM_TYPE::VALUES **/
		// *************************/
		mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_DefaultForList.class);
		ic = new IC_Descriptor(CadseGCST.IC_STRING_LIST_FOR_LIST,
				CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
				"Enter a new value",
				CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
				"Enter a new value",
				CadseGCST.IC_STRING_LIST_FOR_LIST_at_ALLOW_DUPLICATE_, false);
		field = new UIFieldImpl(CadseGCST.DLIST, UUID.randomUUID(),
				CadseGCST.ENUM_TYPE_at_VALUES_, "Values", EPosLabel.top, mc, ic);
		CadseGCST.ENUM_TYPE.addField(field);

		// *****************************/
		// ** ENUM_TYPE::JAVA_CLASS **/
		// *****************************/
		mc = new MC_Descriptor(CadseGCST.MC_STRING_TO_JAVA_ELEMENT);
		ic = new IC_Descriptor(CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO,
				CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
				"Select an Enum class",
				CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
				"Select an Enum class",
				CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_STYLE_,
				"ENUMS");
		field = new UIFieldImpl(CadseGCST.DBROWSER, UUID.randomUUID(),
				CadseGCST.ENUM_TYPE_at_JAVA_CLASS_, "Enum class",
				EPosLabel.left, mc, ic);
		CadseGCST.ENUM_TYPE.addField(field);
		JavaClassValidator v = new JavaClassValidator(CadseGCST.UIVALIDATOR);
		v.setClazz(UIEnumValidator.class);
		CadseGCST.ENUM_TYPE.addValidators(v);
		
		// register java implementation 
		// TODO move this code in an eclipse extension (implement it)
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), CadseGCST.MC_STRING_TO_JAVA_ELEMENT,
				MC_StringToJavaElement.class);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO,
				IC_JavaClassForBrowser_Combo.class);
		
		
		//***********************************/
		//* IC_JAVA_CLASS_FOR_BROWSER_COMBO */
		//***********************************/
		
		
		//*********/
		//* STYLE */
		//*********/
		
		ic = new IC_Descriptor(CadseGCST.IC_STATIC_ARRAY_OF_OBJECT_FOR_BROWSER_COMBO,
				CadseGCST.IC_STATIC_ARRAY_OF_OBJECT_FOR_BROWSER_COMBO_at_VALUES_,
				IC_JavaClassForBrowser_Combo.style_values);
		field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(),
				CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_STYLE_, "Style",
				EPosLabel.left, null, ic);
		CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO.addField(field);
		
		
		//*********/
		//* FILTER */
		//*********/
		
		field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
				CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_FILTER_, "Filter",
				EPosLabel.left, null, null,
				CadseGCST.DTEXT_at_TOOL_TIP_,
				"The initial pattern to filter the set of types.\n"
				+ "For example \"Abstract\" shows  all types starting with \"Abstract\".\n"
				+ "The meta character '?' representing any character and\n"
				+ "'*' representing any string are supported.\n"
				+ "You can pass an empty string if no filtering is required");
		CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO.addField(field);
		
		
	}
}

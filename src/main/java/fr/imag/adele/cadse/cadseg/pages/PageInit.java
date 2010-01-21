package fr.imag.adele.cadse.cadseg.pages;


import java.util.UUID;


import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.mc.MC_BooleanManager;
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
import fr.imag.adele.cadse.core.impl.ui.GroupOfAttributesDescriptor;
import fr.imag.adele.cadse.core.impl.ui.JavaClassValidator;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.impl.ui.ic.IC_Descriptor;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_DefaultForList;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_Descriptor;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_StringToBoolean;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.UIValidator;
import fr.imag.adele.cadse.core.util.CreatedObjectManager;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_DefaultForList;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_LinkForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_Max;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_Min;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.mc.MaxModelController;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.mc.MinMaxValidator;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DBrowserUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DListUI;

public class PageInit {
	public static void init() throws CadseException {
		// set flag must be initialized...
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
				Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
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

		{
			IC_Descriptor icIcon = new IC_Descriptor(
					CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST,

					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
					"Select an icon",
					CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
					"Select a icon");
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icIcon,
					IC_ItemtypeIcon.class);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			
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

		{// create destination field (overwrite ic)
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

		{
			IC_Descriptor icMax = new IC_Descriptor(
					CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icMax,
					IC_Max.class);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_INTEGER,
					CadseGCST.MC_INTEGER_at_DEFAULT_VALUE_, 0,
					CadseGCST.MC_INTEGER_at_ERROR_MSG_MIN_, "max must be > 0",
					CadseGCST.MC_INTEGER_at_MIN_, 0);
			CreatedObjectManager.register(null, mc, MaxModelController.class);
			CadseGCST.LINK_TYPE.addField(new UIFieldImpl(CadseGCST.DTEXT, UUID
					.randomUUID(), CadseGCST.LINK_TYPE_at_MAX_, "max:",
					EPosLabel.left, mc, icMax));
		}
		{
			// create a validator for attribute package name
			JavaClassValidator v = new JavaClassValidator(null);
			v.setClazz(JavaPackageValidator.class);
			v.setListenAttributes(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_);
			CadseGCST.CADSE_DEFINITION.addValidators(v);
		}

		{
			// create a validator for attribute min and max value
			JavaClassValidator v2 = new JavaClassValidator(null);
			v2.setClazz(MinMaxValidator.class);
			v2.setListenAttributes(CadseGCST.LINK_TYPE_at_MIN_,
					CadseGCST.LINK_TYPE_at_MAX_);
			CadseGCST.LINK_TYPE.addValidators(v2);
		}
		
		{
			GroupOfAttributesDescriptor gevol = new GroupOfAttributesDescriptor(
					"evolution", 2);
			CadseGCST.ATTRIBUTE.addGroupOfAttributes(gevol);
			gevol.add(CadseGCST.ATTRIBUTE_at_TWEVOL_);
			gevol.add(CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_);
			gevol.add(CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_);
			gevol.add(CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_);

			GroupOfAttributesDescriptor gevolLink = new GroupOfAttributesDescriptor(
					"evolution", 2);
			CadseGCST.LINK_TYPE.addGroupOfAttributes(gevolLink);
			gevolLink.setOverWriteGroup(gevol);
			gevolLink.add(CadseGCST.ATTRIBUTE_at_TWEVOL_);
			gevolLink.add(CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_);
			gevolLink.add(CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_);
			gevolLink.add(CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_);
			gevolLink.add(CadseGCST.LINK_TYPE_at_TWCOUPLED_);
			gevolLink.add(CadseGCST.LINK_TYPE_at_TWDEST_EVOL_);
		}
		{
			//*********************************//
			//*** BASIC GROUP FOR ATTRIBUTE ***//
			//*********************************//
			
			
			GroupOfAttributesDescriptor gBasicProps3 = new GroupOfAttributesDescriptor(
					"basic properties", 3);
			gBasicProps3.add(CadseGCST.ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_);
			gBasicProps3.add(CadseGCST.ATTRIBUTE_at_IS_LIST_);
			gBasicProps3.add(CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_);
			gBasicProps3.setHasBoxGroup(true);
			
			GroupOfAttributesDescriptor gBasicProps = new GroupOfAttributesDescriptor(
					"basic properties", 1);
			CadseGCST.ATTRIBUTE.addGroupOfAttributes(gBasicProps);
			gBasicProps.add(CadseGCST.ITEM_at_NAME_);
			gBasicProps.add(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);
			gBasicProps.add(gBasicProps3);
			gBasicProps.setHasBoxGroup(false);
			
			
			GroupOfAttributesDescriptor gAdvancedProps = new GroupOfAttributesDescriptor(
					"advanced properties", 3);
			CadseGCST.ATTRIBUTE.addGroupOfAttributes(gAdvancedProps);
			gAdvancedProps.add(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_);
			gAdvancedProps.add(CadseGCST.ATTRIBUTE_at_FINAL_);
			gAdvancedProps.add(CadseGCST.ATTRIBUTE_at_NATIF_);
			gAdvancedProps.add(CadseGCST.ATTRIBUTE_at_REQUIRE_);
			gAdvancedProps.add(CadseGCST.ATTRIBUTE_at_TRANSIENT_);

			//*********************************//
			//***   BASIC GROUP FOR LINK    ***//
			//*********************************//
			
			GroupOfAttributesDescriptor gattkindsLink3 = new GroupOfAttributesDescriptor(
					"basic properties", 3);
			gattkindsLink3.add(CadseGCST.ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_);
			gattkindsLink3.add(CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_);
			
			GroupOfAttributesDescriptor gkinds = new GroupOfAttributesDescriptor(
					"link properties", 4);
			gkinds.add(CadseGCST.LINK_TYPE_at_AGGREGATION_);
			gkinds.add(CadseGCST.LINK_TYPE_at_ANNOTATION_);
			gkinds.add(CadseGCST.LINK_TYPE_at_COMPOSITION_);
			gkinds.add(CadseGCST.LINK_TYPE_at_GROUP_);
			gkinds.add(CadseGCST.LINK_TYPE_at_MAPPING_);
			gkinds.add(CadseGCST.LINK_TYPE_at_PART_);
			gkinds.add(CadseGCST.LINK_TYPE_at_REQUIRE_);
			
			GroupOfAttributesDescriptor gcard = new GroupOfAttributesDescriptor(
					"cardinality", 2);
			gcard.add(CadseGCST.LINK_TYPE_at_MIN_);
			gcard.add(CadseGCST.LINK_TYPE_at_MAX_);
			
			GroupOfAttributesDescriptor gattkindsLink = new GroupOfAttributesDescriptor(
					"basic properties", 1);
			CadseGCST.LINK_TYPE.addGroupOfAttributes(gattkindsLink);
			
			gattkindsLink.setOverWriteGroup(gBasicProps);
			gattkindsLink.add(CadseGCST.ITEM_at_NAME_);
			gattkindsLink.add(CadseGCST.LINK_TYPE_lt_DESTINATION);
			gattkindsLink.add(gattkindsLink3);
			gattkindsLink.add(gkinds);
			gattkindsLink.add(gcard);
			gattkindsLink.setHasBoxGroup(false);
			
			GroupOfAttributesDescriptor gattkindsLinkA = new GroupOfAttributesDescriptor(
					"advanced properties", 3);
			CadseGCST.LINK_TYPE.addGroupOfAttributes(gattkindsLinkA);
			gattkindsLinkA.setOverWriteGroup(gAdvancedProps);
			gattkindsLinkA.add(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_);
			gattkindsLinkA.add(CadseGCST.ATTRIBUTE_at_NATIF_);
			gattkindsLinkA.add(CadseGCST.ATTRIBUTE_at_TRANSIENT_);
			gattkindsLinkA.add(CadseGCST.LINK_TYPE_at_HIDDEN_);
			
			
			//*********************************//
			//***   BASIC GROUP FOR ENUM    ***//
			//*********************************//
			
			GroupOfAttributesDescriptor gEnumProps3 = new GroupOfAttributesDescriptor(
					"basic properties", 3);
			gEnumProps3.add(CadseGCST.ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_);
			gEnumProps3.add(CadseGCST.ATTRIBUTE_at_IS_LIST_);
			gEnumProps3.add(CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_);
			gEnumProps3.setHasBoxGroup(true);
			
			GroupOfAttributesDescriptor gEnumProps = new GroupOfAttributesDescriptor(
					"basic properties", 1);
			CadseGCST.ENUM.addGroupOfAttributes(gEnumProps);
			gEnumProps.add(CadseGCST.ITEM_at_NAME_);
			gEnumProps.add(CadseGCST.ENUM_lt_ENUM_TYPE);
			gEnumProps.add(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);
			gEnumProps.add(gBasicProps3);
			gEnumProps.setHasBoxGroup(false);
			gEnumProps.setOverWriteGroup(gBasicProps);			
			
			
			//*********************************//
			//*** ADVANCED GROUP FOR ENUM   ***//
			//*********************************//
			
			GroupOfAttributesDescriptor gStringAdvancedProps = new GroupOfAttributesDescriptor(
					"advanced properties", 3);
			CadseGCST.STRING.addGroupOfAttributes(gStringAdvancedProps);
			gStringAdvancedProps.setOverWriteGroup(gAdvancedProps);
			gAdvancedProps.add(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_);
			gAdvancedProps.add(CadseGCST.ATTRIBUTE_at_FINAL_);
			gAdvancedProps.add(CadseGCST.ATTRIBUTE_at_NATIF_);
			gAdvancedProps.add(CadseGCST.ATTRIBUTE_at_REQUIRE_);
			gAdvancedProps.add(CadseGCST.ATTRIBUTE_at_TRANSIENT_);
			gAdvancedProps.add(CadseGCST.STRING_at_NOT_EMPTY_);
			
		}

		//****************/
		//** LINK_TYPE ***/
		//****************/
		
		IPage ltHiddenAttributes = new PageImpl(UUID.randomUUID(), "Hidden attributes",
				"Hidden attributes", "Hidden attributes", "Hidden attributes",
				false,

				null);
		ltHiddenAttributes.addHiddenAttributes(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_,
				CadseGCST.ATTRIBUTE_at_FINAL_,
				CadseGCST.ATTRIBUTE_at_IS_LIST_);
		CadseGCST.LINK_TYPE.addCreationPages(ltHiddenAttributes);
		CadseGCST.LINK_TYPE.addModificationPages(ltHiddenAttributes);

		//********************************/
		//**      EVOLUTION PAGE        **/
		//********************************/
		{
			IPage evolPage = new PageImpl(UUID.randomUUID(), "Evolution control",
					"Evolution control", "Evolution control", "Evolution control",
					false,
	
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
			//*****************************/
			//** MODIFIED_ATTRIBUTES     **/
			//*****************************/
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DLIST, UUID.randomUUID(),
					CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES, "Modified attributes",
					EPosLabel.top, new MC_Descriptor(CadseGCST.MC_LINK),
					new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST));
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);
	
			//*****************************/
			//** REV_MODIFIED **/
			//*****************************/
			field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.ITEM_at_REV_MODIFIED_, "Is modified", EPosLabel.none,
					new MC_Descriptor(CadseGCST.MC_BOOLEAN), null);
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);
	
			//*****************************/
			//** REQUIRE_NEW_REV **/
			//*****************************/
			field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.ITEM_at_REQUIRE_NEW_REV_,
					"Next commit will create a new revision", EPosLabel.none,
					new MC_Descriptor(CadseGCST.MC_BOOLEAN), null);
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);
	
			//*****************************/
			//** TW_VERSION **/
			//*****************************/
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.ITEM_at_TW_VERSION_, "Revision number",
					EPosLabel.left, new MC_Descriptor(CadseGCST.MC_INTEGER), null);
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);
	
			//*****************************/
			//** COMMITTED_DATE **/
			//*****************************/
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.ITEM_at_COMMITTED_DATE_, "Last commit date",
					EPosLabel.left, new MC_Descriptor(CadseGCST.MC_DATE), null);
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);
	
			//*****************************/
			//** COMMITTED_BY **/
			//*****************************/
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.ITEM_at_COMMITTED_BY_, "Last committer",
					EPosLabel.left, null, null);
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);
			
			//*****************************/
			//** ITEM_at_TWLAST_COMMENT_ **/
			//*****************************/
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.ITEM_at_TWLAST_COMMENT_, "Last commit comment",
					EPosLabel.left, null, null);
			field.setEditable(false);
			CadseGCST.ITEM.addField(field);
		}
		
		//********************************/
		//** INSTANCE NAME CONTROL PAGE **/
		//********************************/
		{
			PageImpl nameControl = new PageImpl(UUID.randomUUID(), CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE,
					CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE, CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE, CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE, true, null,
					CadseGCST.MANAGER_at_QUALIFIED_NAME_TEMPLATE_,
					CadseGCST.MANAGER_at_DISPLAY_NAME_TEMPLATE_,
					CadseGCST.MANAGER_at_VALID_PATTERN_ID_,
					CadseGCST.MANAGER_at_MESSAGE_ERROR_ID_
	
			);
			ChangeItemAction action = new ChangeItemAction(nameControl);
			nameControl.setActionPage(action);
			CadseGCST.ITEM_TYPE.addModificationPages(nameControl);
			CadseGCST.ITEM_TYPE.addCreationPages(nameControl);
	
			//*****************************/
			//** QUALIFIED_NAME_TEMPLATE **/
			//*****************************/
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic,
					IC_ItemTypeTemplateForText.class);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.MANAGER_at_QUALIFIED_NAME_TEMPLATE_,
					"Qualified name template", EPosLabel.left, null, ic);
			CadseGCST.ITEM_TYPE.addField(field);
	
			//***************************/
			//** DISPLAY_NAME_TEMPLATE **/
			//***************************/
			ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic,
					IC_ItemTypeTemplateForText.class);
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.MANAGER_at_DISPLAY_NAME_TEMPLATE_,
					"Display name template", EPosLabel.left, null, ic);
			CadseGCST.ITEM_TYPE.addField(field);
	
			//**********************/
			//** MESSAGE_ERROR_ID **/
			//**********************/
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.MANAGER_at_MESSAGE_ERROR_ID_,
					"Error message while invalid name", EPosLabel.left, null, null);
			CadseGCST.ITEM_TYPE.addField(field);
	
			//**********************/
			//** VALID_PATTERN_ID **/
			//**********************/
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
					ValidPattern.class);
			ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic,
					CadseDefinitionManager.ValidFieldIC.class);
			field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
					CadseGCST.MANAGER_at_VALID_PATTERN_ID_, "Valid name pattern",
					EPosLabel.left, mc, ic);
			CadseGCST.ITEM_TYPE.addField(field);
		}
		
		//********************************/
		//**  EDITOR EVOLUTION FIELDS   **/
		//********************************/
		{
			
			//*****************************/
			//** TWEVOL **/
			//*****************************/
			IC_Descriptor ic = new IC_Descriptor(CadseGCST.IC_ENUM_FOR_BROWSER_COMBO);
			UIFieldImpl field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_TWEVOL_,
					"Change impact", EPosLabel.left, new MC_Descriptor(CadseGCST.MC_ENUM), ic);
			CadseGCST.ATTRIBUTE.addField(field);
	
			//***************************/
			//** TWCOMMIT_KIND **/
			//***************************/
			ic = new IC_Descriptor(CadseGCST.IC_ENUM_FOR_BROWSER_COMBO);
			field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_,
					"Conflict impact on commit", EPosLabel.left, new MC_Descriptor(CadseGCST.MC_ENUM), ic);
			CadseGCST.ATTRIBUTE.addField(field);
	
			//**********************/
			//** TWREV_SPECIFIC **/
			//**********************/
			field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_,
					"Value is shared by all revisions", EPosLabel.none, new MC_Descriptor(CadseGCST.MC_BOOLEAN), null);
			CadseGCST.ATTRIBUTE.addField(field);
	
			//**********************/
			//** TWUPDATE_KIND **/
			//**********************/
			ic = new IC_Descriptor(CadseGCST.IC_ENUM_FOR_BROWSER_COMBO);
			field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(),
					CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_, "Conflict impact on update",
					EPosLabel.left, new MC_Descriptor(CadseGCST.MC_ENUM), ic);
			CadseGCST.ATTRIBUTE.addField(field);

		
			//**********************/
			//** TWCOUPLED **/
			//**********************/
			field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
					CadseGCST.LINK_TYPE_at_TWCOUPLED_, "Propagates evolution actions in both sides",
					EPosLabel.none, new MC_Descriptor(CadseGCST.MC_BOOLEAN), null);
			CadseGCST.LINK_TYPE.addField(field);

			
			//**********************/
			//** TWDEST_EVOL **/
			//**********************/
			ic = new IC_Descriptor(CadseGCST.IC_ENUM_FOR_BROWSER_COMBO);
			field = new UIFieldImpl(CadseGCST.DCOMBO, UUID.randomUUID(),
					CadseGCST.LINK_TYPE_at_TWDEST_EVOL_, "Change on dest impact",
					EPosLabel.left, new MC_Descriptor(CadseGCST.MC_ENUM), ic);
			CadseGCST.LINK_TYPE.addField(field);

		}
		
		//LABELS ...
		/**
		Page de création d'un item type */
		//**************************/
		//** IS_INSTANCE_ABSTRACT **/
		//**************************/
		UIFieldImpl field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
				CadseGCST.ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_, "Is abstract",
				EPosLabel.none, new MC_Descriptor(CadseGCST.MC_BOOLEAN), null);
		CadseGCST.ITEM_TYPE.addField(field);
		
		//**************************/
		//**  IS_INSTANCE_HIDDEN  **/
		//**************************/
		field = new UIFieldImpl(CadseGCST.DCHECK_BOX, UUID.randomUUID(),
				CadseGCST.ITEM_TYPE_at_IS_INSTANCE_HIDDEN_, "Instances are hidden",
				EPosLabel.none, new MC_Descriptor(CadseGCST.MC_BOOLEAN), null);
		CadseGCST.ITEM_TYPE.addField(field);
		
		/**
		Par défaut, mettre le focus dans le champ name.
		Pages de properties d'un item type
		is-instance-abstract ==> is-abstract-instance
		is-instance-hidden ==> is-hidden-instance
		validate-name-re ==> ????????????
		goups-of-attributes ==> gRoups-of-attributes
		cadse ==> CADSE
		Page de properties d'un lien
		Item-hidden ==> hidden-item
		item-readonly ==> readonly item
		kind????
		*/
		
		
		
		//**************************/
		//**     PROJECT_NAME     **/
		//**************************/
		MC_Descriptor mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_PROJECT_NAME.class);
		IC_Descriptor ic = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), ic,
				IC_ItemTypeTemplateForTextFromManager.class);
		field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
				CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_, "Project name",
				EPosLabel.left, mc, ic);
		CadseGCST.PROJECT_CONTENT_MODEL.addField(field);
		
		
		//**************************/
		//**      FILE_NAME       **/
		//**************************/
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
		
		
		//**************************/
		//**      FILE_PATH       **/
		//**************************/
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

		
		//***************************/
		//** BOOLEAN DEFAULT VALUE **/
		//***************************/
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
		
		
		
		//**************************/
		//** DOUBLE DEFAULT VALUE **/
		//**************************/
		mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_DoubleTextField.class);
		field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
				CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "Default value",
				EPosLabel.left, mc, null);
		CadseGCST.DOUBLE.addField(field);
		
		//***************************/
		//** INTEGER DEFAULT VALUE **/
		//***************************/
		mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_IntegerTextField.class);
		field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
				CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "Default value",
				EPosLabel.left, mc, null);
		CadseGCST.INTEGER.addField(field);
		
		//**************************/
		//**  LONG DEFAULT VALUE  **/
		//**************************/
		mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_LongTextField.class);
		field = new UIFieldImpl(CadseGCST.DTEXT, UUID.randomUUID(),
				CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "Default value",
				EPosLabel.left, mc, null);
		CadseGCST.LONG.addField(field);
		
		//**************************/
		//**      ENUM DEFAULT VALUE       **/
		//**************************/
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
		
		
		//**************************/
		//**      ENUM TYPE       **/
		//**************************/
		mc = new MC_Descriptor(CadseGCST.MC_LINK);
		ic = new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST, CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
				"Select a type enum", CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, "Select a type enum");
		field = new UIFieldImpl(CadseGCST.DBROWSER, UUID.randomUUID(),
				CadseGCST.ENUM_lt_ENUM_TYPE, "Enum type",
				EPosLabel.left, mc, ic);
		CadseGCST.ENUM.addField(field);
		
		
		mc = new MC_Descriptor(CadseGCST.MODEL_CONTROLLER);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), mc,
				MC_DefaultForList.class);
		ic = new IC_Descriptor(CadseGCST.IC_STRING_LIST_FOR_LIST, CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
				"Enter a new value", CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, "Enter a new value",
				CadseGCST.IC_STRING_LIST_FOR_LIST_at_ALLOW_DUPLICATE_, false);
		field = new UIFieldImpl(CadseGCST.DLIST, UUID.randomUUID(),
				CadseGCST.ENUM_TYPE_at_VALUES_, "Values",
				EPosLabel.top, mc, ic);
		CadseGCST.ENUM_TYPE.addField(field);
		
		/*
		 * /**
	 * @not generated
	 *
	public DBrowserUI createFieldEnumType() {
		return new DBrowserUI(CadseGCST.ENUM_lt_ENUM_TYPE.getName(),
				"enum type", EPosLabel.left, new LinkModelController(true,
						null, CadseGCST.ENUM_lt_ENUM_TYPE),
				new IC_LinkForBrowser_Combo_List("Select a type enum",
						"Select a type enum", CadseGCST.ENUM_lt_ENUM_TYPE), 0);
	}
		 */
	}

}


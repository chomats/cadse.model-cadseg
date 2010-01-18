package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_DestinationLinkForBrowser_Combo;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_SuperTypeForBrowser_Combo;
import fr.imag.adele.cadse.cadseg.validators.JavaPackageValidator;
import fr.imag.adele.cadse.cadseg.views.cadseg.CadsegView;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.GroupOfAttributesDescriptor;
import fr.imag.adele.cadse.core.impl.ui.JavaClassValidator;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.impl.ui.ic.IC_Descriptor;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_Descriptor;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.util.CreatedObjectManager;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_IconResourceForBrowser_Combo_List;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_Max;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_Min;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.mc.MaxModelController;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.mc.MinMaxValidator;

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
					IC_IconResourceForBrowser_Combo_List.class);
			CadseGCST.ITEM_TYPE.addField(new UIFieldImpl(CadseGCST.DBROWSER,
					UUID.randomUUID(), CadseGCST.ITEM_TYPE_at_ICON_, "icon",
					EPosLabel.left, null, icIcon));
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
			GroupOfAttributesDescriptor gkinds = new GroupOfAttributesDescriptor(
					"kinds", 4);
			CadseGCST.LINK_TYPE.addGroupOfAttributes(gkinds);
			gkinds.add(CadseGCST.LINK_TYPE_at_AGGREGATION_);
			gkinds.add(CadseGCST.LINK_TYPE_at_ANNOTATION_);
			gkinds.add(CadseGCST.LINK_TYPE_at_COMPOSITION_);
			gkinds.add(CadseGCST.LINK_TYPE_at_GROUP_);
			gkinds.add(CadseGCST.LINK_TYPE_at_MAPPING_);
			gkinds.add(CadseGCST.LINK_TYPE_at_PART_);
			gkinds.add(CadseGCST.LINK_TYPE_at_REQUIRE_);
		}
		{
			GroupOfAttributesDescriptor gcard = new GroupOfAttributesDescriptor(
					"cardinality", 2);
			CadseGCST.LINK_TYPE.addGroupOfAttributes(gcard);
			gcard.add(CadseGCST.LINK_TYPE_at_MIN_);
			gcard.add(CadseGCST.LINK_TYPE_at_MAX_);
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
			GroupOfAttributesDescriptor gattkinds = new GroupOfAttributesDescriptor(
					"attribute kinds", 3);
			CadseGCST.ATTRIBUTE.addGroupOfAttributes(gattkinds);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_FINAL_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_IS_LIST_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_NATIF_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_REQUIRE_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_TRANSIENT_);

			GroupOfAttributesDescriptor gattkindsLink = new GroupOfAttributesDescriptor(
					"attribute kinds", 3);
			CadseGCST.LINK_TYPE.addGroupOfAttributes(gattkindsLink);
			gattkindsLink.setOverWriteGroup(gattkinds);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_FINAL_);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_IS_LIST_);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_NATIF_);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_TRANSIENT_);
			gattkindsLink.add(CadseGCST.LINK_TYPE_at_HIDDEN_);
		}

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
					CadseGCST.ITEM_at_COMMITTED_BY_
	
			);
	
			List<IPage> modificationPages = Collections.singletonList(evolPage);
			CadseGCST.ITEM.addModificationPages(modificationPages);
	
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
		}
		
		//********************************/
		//** INSTANCE NAME CONTROL PAGE **/
		//********************************/
		{
			PageImpl nameControl = new PageImpl(UUID.randomUUID(), "Instance name control",
					"Instance name control", "Instance name control", "Instance name control", false, null,
					CadseGCST.MANAGER_at_QUALIFIED_NAME_TEMPLATE_,
					CadseGCST.MANAGER_at_DISPLAY_NAME_TEMPLATE_,
					CadseGCST.MANAGER_at_VALID_PATTERN_ID_,
					CadseGCST.MANAGER_at_MESSAGE_ERROR_ID_
	
			);
			ChangeItemAction action = new ChangeItemAction(nameControl);
			nameControl.setActionPage(action);
			List<IPage> modificationPages = Collections.singletonList((IPage) nameControl);
			CadseGCST.ITEM_TYPE.addModificationPages(modificationPages);
			CadseGCST.ITEM_TYPE.addCreationPages(modificationPages);
	
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
	}

}

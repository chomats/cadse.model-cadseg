package fr.imag.adele.cadse.cadseg.pages;



import fr.imag.adele.cadse.cadseg.pages.ic.IC_DestinationLinkForBrowser_Combo;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_SuperTypeForBrowser_Combo;
import fr.imag.adele.cadse.cadseg.validators.JavaPackageValidator;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.GroupOfAttributesDescriptor;
import fr.imag.adele.cadse.core.impl.ui.JavaClassValidator;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.impl.ui.ic.IC_Descriptor;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_Descriptor;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.util.CreatedObjectManager;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_Max;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_Min;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.mc.MaxModelController;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.mc.MinMaxValidator;

public class PageInit {
	public static void init() throws CadseException {
		// set flag must be initialized...
		CadseGCST.CADSE_DEFINITION_lt_BUILD.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.CADSE_DEFINITION_lt_CONFIGURATION.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.CADSE_DEFINITION_lt_MAPPING.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		
		CadseGCST.ITEM_lt_INSTANCE_OF.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		
		CadseGCST.ITEM_at_NAME_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.CADSE_lt_EXTENDS.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		
		CadseGCST.TYPE_DEFINITION_lt_CADSE.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		
		
		CadseGCST.ITEM_TYPE_lt_SUPER_TYPE.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ITEM_TYPE_at_HAS_CONTENT_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ITEM_TYPE_at_IS_ABSTRACT_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		
		CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		
		CadseGCST.LINK_at_AGGREGATION_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_ANNOTATION_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_COMPOSITION_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_GROUP_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_HIDDEN_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_MAPPING_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_MAX_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_MIN_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_SELECTION_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.LINK_at_PART_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_REQUIRE_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_TWDEST_EVOL_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_TWCOUPLED_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_lt_DESTINATION.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_lt_INVERSE_LINK.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.LINK_lt_INVERSE_LINK.setFlag(Item.CAN_BE_UNDEFINED, true);
		CadseGCST.LINK_lt_SOURCE.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		
		
		
		// create name field ( overwrite mc)
		CadseGCST.ITEM.addField(new UIFieldImpl(CadseGCST.DTEXT, CompactUUID.randomUUID(),CadseGCST.ITEM_at_NAME_,
				"name",EPosLabel.left,
				new MC_Descriptor(CadseGCST.MC_NAME_ATTRIBUTE), null));
		
		// create super type field (overwrite ic)
		{
			IC_Descriptor icSupertype = new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST,
		
					CadseGCST.IC_ABSTRACT_TREE_DIALOG_FOR_LIST_BROWSER_COMBO_at_TITLE_, "Select a super type",
					CadseGCST.IC_ABSTRACT_TREE_DIALOG_FOR_LIST_BROWSER_COMBO_at_MESSAGE_, "Select a super type");
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icSupertype, IC_SuperTypeForBrowser_Combo.class);
			
			CadseGCST.ITEM_TYPE.addField(new UIFieldImpl(
					CadseGCST.DBROWSER, CompactUUID.randomUUID(),CadseGCST.ITEM_TYPE_lt_SUPER_TYPE,
					"super type:", EPosLabel.left,
					new MC_Descriptor(CadseGCST.MC_LINK), icSupertype));
		}
		
		// create destination field (overwrite ic)
		{
			IC_Descriptor icDesttype = new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST,
				CadseGCST.IC_ABSTRACT_TREE_DIALOG_FOR_LIST_BROWSER_COMBO_at_TITLE_, "Select a destination",
				CadseGCST.IC_ABSTRACT_TREE_DIALOG_FOR_LIST_BROWSER_COMBO_at_MESSAGE_, "Select a destination");
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icDesttype, IC_DestinationLinkForBrowser_Combo.class);
		
			CadseGCST.LINK.addField(new UIFieldImpl(
				CadseGCST.DBROWSER, CompactUUID.randomUUID(),CadseGCST.LINK_lt_DESTINATION,
				"destination:", EPosLabel.left,
				new MC_Descriptor(CadseGCST.MC_LINK,
						CadseGCST.MC_LINK_at_ERROR_MESSAGE_,
						"You must set the destination"), icDesttype));
		}
		
		{// create destination field (overwrite ic)
			IC_Descriptor icInverseLink = new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST,
					CadseGCST.IC_ABSTRACT_TREE_DIALOG_FOR_LIST_BROWSER_COMBO_at_TITLE_, "Select an inverse link",
					CadseGCST.IC_ABSTRACT_TREE_DIALOG_FOR_LIST_BROWSER_COMBO_at_MESSAGE_, "Select an inverse link");
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icInverseLink, fr.imag.adele.cadse.cadseg.pages.ic.IC_InverseLink.class);
			
			CadseGCST.LINK.addField(new UIFieldImpl(
					CadseGCST.DBROWSER, CompactUUID.randomUUID(),CadseGCST.LINK_lt_INVERSE_LINK,
					"inverse link:", EPosLabel.left,
					new MC_Descriptor(CadseGCST.MC_LINK), icInverseLink));
			
		}
		{
			IC_Descriptor icMin = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icMin, IC_Min.class);
			
			CadseGCST.LINK.addField(new UIFieldImpl(
					CadseGCST.DTEXT, CompactUUID.randomUUID(),CadseGCST.LINK_at_MIN_,
					"min:", EPosLabel.left,
					new MC_Descriptor(CadseGCST.MC_INTEGER,
							CadseGCST.MC_INTEGER_at_DEFAULT_VALUE_, 0,
							CadseGCST.MC_INTEGER_at_ERROR_MSG_MIN_, "min must be >= 0",
							CadseGCST.MC_INTEGER_at_MIN_, 0), icMin));
		}
		
		{
			IC_Descriptor icMax = new IC_Descriptor(CadseGCST.INTERACTION_CONTROLLER);
			CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icMax, IC_Max.class);
			MC_Descriptor mc = new MC_Descriptor(CadseGCST.MC_INTEGER,
					CadseGCST.MC_INTEGER_at_DEFAULT_VALUE_, 0,
					CadseGCST.MC_INTEGER_at_ERROR_MSG_MIN_, "max must be > 0",
					CadseGCST.MC_INTEGER_at_MIN_, 0);
			CreatedObjectManager.register(null, mc, MaxModelController.class);
			CadseGCST.LINK.addField(new UIFieldImpl(
					CadseGCST.DTEXT, CompactUUID.randomUUID(),CadseGCST.LINK_at_MAX_,
					"max:", EPosLabel.left,
					mc, icMax));
		}
		{
			// create a validator for attribute package name
			JavaClassValidator v  = new JavaClassValidator(null);
			v.setClazz(JavaPackageValidator.class);
			v.setListenAttributes(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_);
			CadseGCST.CADSE_DEFINITION.addValidators(v);
		}
		
		{
			// create a validator for attribute min and max value
			JavaClassValidator v2  = new JavaClassValidator(null);
			v2.setClazz(MinMaxValidator.class);
			v2.setListenAttributes(CadseGCST.LINK_at_MIN_, CadseGCST.LINK_at_MAX_);
			CadseGCST.LINK.addValidators(v2);
		}
		
		{
			GroupOfAttributesDescriptor gkinds = new GroupOfAttributesDescriptor("kinds", 4);
			CadseGCST.LINK.addGroupOfAttributes(gkinds);
			gkinds.add(CadseGCST.LINK_at_AGGREGATION_);
			gkinds.add(CadseGCST.LINK_at_ANNOTATION_);
			gkinds.add(CadseGCST.LINK_at_COMPOSITION_);
			gkinds.add(CadseGCST.LINK_at_GROUP_);
			gkinds.add(CadseGCST.LINK_at_MAPPING_);
			gkinds.add(CadseGCST.LINK_at_PART_);
			gkinds.add(CadseGCST.LINK_at_REQUIRE_);
		}
		{
			GroupOfAttributesDescriptor gcard = new GroupOfAttributesDescriptor("cardinality", 2);
			CadseGCST.LINK.addGroupOfAttributes(gcard);
			gcard.add(CadseGCST.LINK_at_MIN_);
			gcard.add(CadseGCST.LINK_at_MAX_);
		}
		{
			GroupOfAttributesDescriptor gevol = new GroupOfAttributesDescriptor("evolution", 2);
			CadseGCST.ATTRIBUTE.addGroupOfAttributes(gevol);
			gevol.add(CadseGCST.ATTRIBUTE_at_TWEVOL_);
			gevol.add(CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_);
			gevol.add(CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_);
			gevol.add(CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_);
		
			GroupOfAttributesDescriptor gevolLink = new GroupOfAttributesDescriptor("evolution", 2);
			CadseGCST.LINK.addGroupOfAttributes(gevolLink);
			gevolLink.setOverWriteGroup(gevol);
			gevolLink.add(CadseGCST.ATTRIBUTE_at_TWEVOL_);
			gevolLink.add(CadseGCST.ATTRIBUTE_at_TWCOMMIT_KIND_);
			gevolLink.add(CadseGCST.ATTRIBUTE_at_TWREV_SPECIFIC_);
			gevolLink.add(CadseGCST.ATTRIBUTE_at_TWUPDATE_KIND_);
			gevolLink.add(CadseGCST.LINK_at_TWCOUPLED_);		
			gevolLink.add(CadseGCST.LINK_at_TWDEST_EVOL_);
		}
		{
			GroupOfAttributesDescriptor gattkinds = new GroupOfAttributesDescriptor("attribute kinds", 3);
			CadseGCST.ATTRIBUTE.addGroupOfAttributes(gattkinds);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_FINAL_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_IS_LIST_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_NATIF_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_REQUIRE_);
			gattkinds.add(CadseGCST.ATTRIBUTE_at_TRANSIENT_);
			
			GroupOfAttributesDescriptor gattkindsLink = new GroupOfAttributesDescriptor("attribute kinds", 3);
			CadseGCST.LINK.addGroupOfAttributes(gattkindsLink);
			gattkindsLink.setOverWriteGroup(gattkinds);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_FINAL_);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_IS_LIST_);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_NATIF_);
			gattkindsLink.add(CadseGCST.ATTRIBUTE_at_TRANSIENT_);
			gattkindsLink.add(CadseGCST.LINK_at_HIDDEN_);
		}
		
	}

	
}

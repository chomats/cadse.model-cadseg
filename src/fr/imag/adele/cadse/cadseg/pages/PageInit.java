package fr.imag.adele.cadse.cadseg.pages;




import fr.imag.adele.cadse.cadseg.pages.ic.IC_SuperTypeForBrowser_Combo;
import fr.imag.adele.cadse.cadseg.validators.JavaPackageValidator;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.JavaClassValidator;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.impl.ui.ic.IC_Descriptor;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_Descriptor;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.util.CreatedObjectManager;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform;

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
		CadseGCST.LINK_at_SELECTION_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_REQUIRE_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_TWDEST_EVOL_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_at_TWCOUPLED_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_lt_DESTINATION.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.LINK_lt_INVERSE_LINK.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		
		
		
		// create name field ( overwrite mc)
		CadseGCST.ITEM.addField(new UIFieldImpl(CadseGCST.DTEXT, CompactUUID.randomUUID(),CadseGCST.ITEM_at_NAME_,
				"name",EPosLabel.left,
				new MC_Descriptor(CadseGCST.MC_NAME_ATTRIBUTE), null));
		
		// create super type field (overwrite ic)
		IC_Descriptor icSupertype = new IC_Descriptor(CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST);
		CreatedObjectManager.register(SWTUIPlatform.getPlatform(), icSupertype, IC_SuperTypeForBrowser_Combo.class);
		icSupertype.setAttribute(CadseGCST.IC_ABSTRACT_TREE_DIALOG_FOR_LIST_BROWSER_COMBO_at_TITLE_, "Select a super type");
		icSupertype.setAttribute(CadseGCST.IC_ABSTRACT_TREE_DIALOG_FOR_LIST_BROWSER_COMBO_at_MESSAGE_, "Select a super type");
		
		CadseGCST.ITEM_TYPE.addField(new UIFieldImpl(
				CadseGCST.DBROWSER, CompactUUID.randomUUID(),CadseGCST.ITEM_TYPE_lt_SUPER_TYPE,
				"super type", EPosLabel.left,
				new MC_Descriptor(CadseGCST.LINK_MODEL_CONTROLLER), icSupertype));
		
		
		// create a validator for attribute package name
		JavaClassValidator v  = new JavaClassValidator();
		v.setClazz(JavaPackageValidator.class);
		v.setListenAttributes(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_);
		CadseGCST.CADSE_DEFINITION.addValidators(v);
		
		
		
	}

	
}

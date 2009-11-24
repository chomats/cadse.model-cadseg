package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.cadseg.validators.JavaPackageValidator;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;

public class PageInit {

	public static void init() {
		CadseGCST.CADSE_DEFINITION_lt_BUILD.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.CADSE_DEFINITION_lt_CONFIGURATION.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.CADSE_DEFINITION_lt_MAPPING.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		
		CadseGCST.ITEM_lt_INSTANCE_OF.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, false);
		
		CadseGCST.ITEM_at_NAME_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		CadseGCST.CADSE_lt_EXTENDS.setFlag(Item.MUST_BE_INITIALIZED_AT_CREATION_TIME, true);
		JavaClassValidator v  = new JavaClassValidator();
		v.setClazz(JavaPackageValidator.class);
		v.setListenAttributes(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_);
		CadseGCST.CADSE_DEFINITION.addValidators(v);
	}
}

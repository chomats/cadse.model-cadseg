package fr.imag.adele.cadse.cadseg.migration;

import java.util.Map;
import java.util.Set;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.CadseCore;

public class MigrationInit {
 public static  void init() {
	 
	 Map<String, IAttributeType<?>> oldNameMap = CadseCore.getOldNameMap();
	 oldNameMap.put("final-value", CadseGCST.ATTRIBUTE_at_FINAL_);
	 oldNameMap.put("readonly", CadseGCST.ITEM_at_ITEM_READONLY_);
	 oldNameMap.put("enable", CadseGCST.FIELD_at_EDITABLE_);
	 oldNameMap.put("long-name-template", CadseGCST.MANAGER_at_QUALIFIED_NAME_TEMPLATE_);
	 oldNameMap.put("is-abstract", CadseGCST.ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_);
	 oldNameMap.put("is-hidden", CadseGCST.ITEM_TYPE_at_IS_INSTANCE_HIDDEN_);
	 oldNameMap.put("cadse-runtime", CadseGCST.TYPE_DEFINITION_lt_CADSE);
	 oldNameMap.put("message", CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_);
	 oldNameMap.put("title", CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_);
	 oldNameMap.put("package-name", CadseGCST.ITEM_TYPE_at_PACKAGE_NAME_);
		 
	 Set<String> removedElts = CadseCore.getRemovedElements();
	removedElts.add("b1b807a6-835f-457c-9bf5-d8dd5b0f34c2hspan");
	removedElts.add("a1922398-35c7-4711-90e9-8ec54331c003display");
	removedElts.add("b1b807a6-835f-457c-9bf5-d8dd5b0f34c2create-page-action");
	removedElts.add("c21fc39d-2dce-4821-bfc0-853890763c55");
	removedElts.add("b1b807a6-835f-457c-9bf5-d8dd5b0f34c2fields");
	removedElts.add("c21fc39d-2dce-4821-bfc0-853890763c55");
	removedElts.add("599cd2f8-d9c8-4cb7-885e-a88a04b809ba");
	removedElts.add("#mLT");
	removedElts.add("meta-link-type");
	removedElts.add("36c8f1c2-3972-40e7-b687-b23f3e46f37bhas-content");
	removedElts.add("6c8f1c2-3972-40e7-b687-b23f3e46f37bis-root-element");
	removedElts.add("6c8f1c2-3972-40e7-b687-b23f3e46f37bis-root-element");
	
	/*
	 * ATTENTION: Can't find attribute 36c8f1c2-3972-40e7-b687-b23f3e46f37blink-type
ATTENTION: Can't find attribute 36c8f1c2-3972-40e7-b687-b23f3e46f37bsuper-type
ATTENTION: Can't find attribute 43808065-4f70-4290-adba-cca999431ba1#mLT
ATTENTION: Can't find attribute 43808065-4f70-4290-adba-cca999431ba1cadse-runtime
ATTENTION: Can't find attribute 43808065-4f70-4290-adba-cca999431ba1meta-link-type
ATTENTION: Can't find dest type 599cd2f8-d9c8-4cb7-885e-a88a04b809ba
ATTENTION: Can't find dest type c21fc39d-2dce-4821-bfc0-853890763c55
ATTENTION: Cannot found attribute 08fcca26-7bb7-454e-bbd3-da34b8e12680message in IC_LinkForBrowser_Combo_List
ATTENTION: Cannot found attribute 08fcca26-7bb7-454e-bbd3-da34b8e12680title in IC_LinkForBrowser_Combo_List
ATTENTION: Cannot found attribute 36c8f1c2-3972-40e7-b687-b23f3e46f37bcustom-manager in ExtItemType
ATTENTION: Cannot found attribute 36c8f1c2-3972-40e7-b687-b23f3e46f37bhas-content in ExtItemType
ATTENTION: Cannot found attribute 36c8f1c2-3972-40e7-b687-b23f3e46f37bhas-short-name in ExtItemType
ATTENTION: Cannot found attribute 36c8f1c2-3972-40e7-b687-b23f3e46f37bhas-unique-name in ExtItemType
ATTENTION: Cannot found attribute 36c8f1c2-3972-40e7-b687-b23f3e46f37bis-meta-item-type in ExtItemType
ATTENTION: Cannot found attribute 36c8f1c2-3972-40e7-b687-b23f3e46f37bis-root-element in ExtItemType
ATTENTION: Cannot found attribute 36c8f1c2-3972-40e7-b687-b23f3e46f37boverwrite-default-pages in ExtItemType
ATTENTION: Cannot found attribute 36c8f1c2-3972-40e7-b687-b23f3e46f37bpackage-name in ExtItemType
ATTENTION: Cannot found attribute 43808065-4f70-4290-adba-cca999431ba1is-abstract in ItemType
ATTENTION: Cannot found attribute 43808065-4f70-4290-adba-cca999431ba1is-hidden in ItemType

	 */
	
	//599cd2f8-d9c8-4cb7-885e-a88a04b809ba
	//c21fc39d-2dce-4821-bfc0-853890763c55
	 //b1b807a6-835f-457c-9bf5-d8dd5b0f34c2-hspan
	 //a1922398-35c7-4711-90e9-8ec54331c003-display
	 //b1b807a6-835f-457c-9bf5-d8dd5b0f34c2-create-page-action
	 //c21fc39d-2dce-4821-bfc0-853890763c55
	 //b1b807a6-835f-457c-9bf5-d8dd5b0f34c2-fields
			
	// TODO Auto-generated method stub

}
}

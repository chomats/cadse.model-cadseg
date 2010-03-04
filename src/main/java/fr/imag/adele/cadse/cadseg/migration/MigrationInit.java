package fr.imag.adele.cadse.cadseg.migration;

import java.util.Map;
import java.util.Set;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.transaction.delta.SetAttributeOperation;

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
	 
	 oldNameMap.put(CadseGCST.CADSE_DEFINITION.getId()+"::UUID_ATTRIBUTE",CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_);
	 oldNameMap.put(CadseGCST.PAGE.getId()+"::UUID_ATTRIBUTE",CadseGCST.PAGE_at_ID_RUNTIME_);
	 oldNameMap.put(CadseGCST.ATTRIBUTE.getId()+"::UUID_ATTRIBUTE",CadseGCST.ATTRIBUTE_at_ID_RUNTIME_);
	 oldNameMap.put(CadseGCST.TYPE_DEFINITION.getId()+"::UUID_ATTRIBUTE",CadseGCST.TYPE_DEFINITION_at_ID_RUNTIME_);
	
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
 }
}

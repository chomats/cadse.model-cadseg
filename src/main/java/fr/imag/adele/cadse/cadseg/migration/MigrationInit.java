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
package fr.imag.adele.cadse.cadseg.migration;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

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
	 oldNameMap.put("ext-types", CadseGCST.DATA_MODEL_lt_TYPES);
	 
	 
	 oldNameMap.put(CadseGCST.CADSE_DEFINITION.getId()+"::UUID_ATTRIBUTE",CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_);
	 oldNameMap.put(CadseGCST.PAGE.getId()+"::UUID_ATTRIBUTE",CadseGCST.PAGE_at_ID_RUNTIME_);
	 oldNameMap.put(CadseGCST.ATTRIBUTE.getId()+"::UUID_ATTRIBUTE",CadseGCST.ATTRIBUTE_at_ID_RUNTIME_);
	 oldNameMap.put(CadseGCST.TYPE_DEFINITION.getId()+"::UUID_ATTRIBUTE",CadseGCST.TYPE_DEFINITION_at_ID_RUNTIME_);
	 oldNameMap.put("695d9c75-d5bf-4c60-b541-67d54d1dbf50::select-messsage", CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_);
	 CadseCore.addOldNameRE(null, "#invert_part.*", CadseGCST.ITEM_lt_PARENT);
	 
	CadseCore.addIgnore("b1b807a6-835f-457c-9bf5-d8dd5b0f34c2::hspan");
	CadseCore.addIgnore("a1922398-35c7-4711-90e9-8ec54331c003::display");
	CadseCore.addIgnore("b1b807a6-835f-457c-9bf5-d8dd5b0f34c2::create-page-action");
	CadseCore.addIgnore("c21fc39d-2dce-4821-bfc0-853890763c55");
	CadseCore.addIgnore("b1b807a6-835f-457c-9bf5-d8dd5b0f34c2::fields");
	CadseCore.addIgnore("c21fc39d-2dce-4821-bfc0-853890763c55");
	CadseCore.addIgnore("599cd2f8-d9c8-4cb7-885e-a88a04b809ba");
	CadseCore.addIgnore("#mLT");
	CadseCore.addIgnore("meta-link-type");
	CadseCore.addIgnore("qualified-display-name");
	CadseCore.addIgnore("36c8f1c2-3972-40e7-b687-b23f3e46f37b::has-content");
	CadseCore.addIgnore("6c8f1c2-3972-40e7-b687-b23f3e46f37b::is-root-element");
	CadseCore.addIgnore("6c8f1c2-3972-40e7-b687-b23f3e46f37b::is-root-element");
	//CadseCore.addIgnorePattern("#invert_part.*");

	CadseCore.addIgnore("a807eb31-575e-4fbb-822c-c0e4c6095339::max");
	CadseCore.addIgnore("a807eb31-575e-4fbb-822c-c0e4c6095339::min");
	CadseCore.addIgnore("b1b807a6-835f-457c-9bf5-d8dd5b0f34c2::use-factory");
	CadseCore.addIgnore("9b9e9c64-284a-4895-a1f6-98614b1b2a19::file-name");
	/*
336d08df-329d-4600-a106-3fbed65c4c18
36b55e68-739a-4603-82eb-ad8f43a4d0ca
58d48b65-cd24-498d-bb0f-78d8c502a7bd
8cf2e8d9-a24c-44be-a7bf-09012b43bf34
bc626deb-655e-4a78-8ef4-36faa28cf89c
c8d7b841-b8ec-4118-9a75-bb1437251486
cfd331c7-821b-4f56-93a3-032b295eb31f
ec37762f-8b87-47a4-bbdb-6da811fdc904

	 * 
	 */
 }
}

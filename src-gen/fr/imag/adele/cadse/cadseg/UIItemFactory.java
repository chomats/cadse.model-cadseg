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
 */
package fr.imag.adele.cadse.cadseg;

import org.eclipse.swt.widgets.Control;

import fr.imag.adele.cadse.cadseg.template.ListOfValueAttribute;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.IItemFactory;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.delta.ItemDelta;
import fr.imag.adele.cadse.core.impl.ItemFactory;
import fr.imag.adele.cadse.core.impl.internal.AbstractGeneratedItem;
import fr.imag.adele.cadse.core.impl.internal.ItemImpl;
import fr.imag.adele.cadse.core.impl.internal.ItemTypeImpl;
import fr.imag.adele.cadse.core.impl.ui.ic.IC_Abstract;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_Integer;
import fr.imag.adele.cadse.core.impl.ui.mc.LinkModelController;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_Date;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_StringToBoolean;
import fr.imag.adele.cadse.core.ui.IFedeFormToolkit;
import fr.imag.adele.cadse.core.ui.IPageController;
import fede.workspace.eclipse.java.fields.MC_StringToJavaElement;
import fede.workspace.model.manager.properties.impl.ic.IC_EnumForBrowser_Combo;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.ic.IC_DefaultForList;
import fede.workspace.model.manager.properties.impl.ic.IC_PartLinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.MC_ShortNameItemProperty;
import fede.workspace.model.manager.properties.impl.mc.StringToEnumModelController;
import fede.workspace.model.manager.properties.impl.ui.DAbstractField;
import fede.workspace.model.manager.properties.impl.ui.DBrowserUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DCheckedListUI;
import fede.workspace.model.manager.properties.impl.ui.DComboUI;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fede.workspace.model.manager.properties.impl.ui.DTreeUI;
import fr.imag.adele.cadse.core.CadseGCST;

public class UIItemFactory extends ItemFactory implements IItemFactory {
	
	class MC_ extends MC_AttributesItem {
		ItemType _type;
		
		public MC_(CompactUUID id, ItemType type) {
			super(id);
			_type = type;
		}

		public ItemType getType() { 
			return _type; 
		}
		
	}
	
	class IC_ extends IC_Abstract {
		ItemType _type;
		
		public IC_(CompactUUID id, ItemType type) {
			super(id);
			_type = type;
		}

		public ItemType getType() { 
			return _type; 
		}
		
	}
	
	class UI_ extends DAbstractField {
		ItemType _type;
		
		public UI_(CompactUUID id, ItemType type) {
			super(id);
			_type = type;
		}

		public ItemType getType() { 
			return _type; 
		}

		@Override
		public Control getMainControl() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] getSelectedObjects() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object createControl(IPageController globalUIController,
				IFedeFormToolkit toolkit, Object container, int hspan) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getUIObject(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getVisualValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setVisualValue(Object visualValue, boolean sendNotification) {
			// TODO Auto-generated method stub
			
		}
		
	}
	static final public UIItemFactory	SINGLETON	= new UIItemFactory();

	private boolean isThisOrSubType(ItemType it, ItemType superType) {
		return superType.isSuperTypeOf(it) || it == superType;
	}

	@Override
	public Item newForCommitItem(LogicalWorkspace wl, ItemType it, ItemDelta item) {
		if (isThisOrSubType(it, CadseGCST.ITEM_TYPE)) {
			return new ItemTypeImpl(wl, it, item);
		}
		CompactUUID id = item.getId();
		String shortName = item.getName();
		
		if (it == CadseGCST.IC_ENUM_FOR_BROWSER_COMBO) {
			return new IC_EnumForBrowser_Combo(id, shortName);
		}
		if (it == CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST) {
			return new IC_LinkForBrowser_Combo_List(id);
		}
		if (it == CadseGCST.STRING_TO_BOOLEAN_MODEL_CONTROLLER) {
			return new MC_StringToBoolean(id);
		}
		if (it == CadseGCST.LINK_MODEL_CONTROLLER) {
			return new LinkModelController(id);
		}
		if (it == CadseGCST.MC_NAME_ATTRIBUTE) {
			return new MC_ShortNameItemProperty(id);
		}
		if (it == CadseGCST.MC_STRING_TO_JAVA_ELEMENT) {
			return new MC_StringToJavaElement(id);
		}
		if (it == CadseGCST.IC_STRING_LIST_FOR_LIST) {
			return new IC_DefaultForList(id);
		}
		if (it == CadseGCST.MC_DATE) {
			return new MC_Date(id);
		}
		if (it == CadseGCST.INT_MODEL_CONTROLLER) {
			return new MC_Integer(id);
		}
		if (it == CadseGCST.STRING_TO_ENUM_MODEL_CONTROLLER) {
			return new StringToEnumModelController(id);
		}
		if (it == CadseGCST.INTERACTION_CONTROLLER) {
			return new IC_Abstract(id);
		}
		//ListOfStringModelController
		if (it == CadseGCST.LIST_OF_STRING_MODEL_CONTROLLER) {
			return new MC_(id, CadseGCST.LIST_OF_STRING_MODEL_CONTROLLER);
		}
		if (it == CadseGCST.IC_PART_LINK_FOR_BROWSER_COMBO_LIST) {
			return new IC_PartLinkForBrowser_Combo_List(id);
		}
		
		if (it == CadseGCST.MODEL_CONTROLLER || CadseGCST.MODEL_CONTROLLER.isSuperTypeOf(it)) {
			return new MC_(id, it);
		}
		if (it == CadseGCST.INTERACTION_CONTROLLER || CadseGCST.INTERACTION_CONTROLLER.isSuperTypeOf(it)) {
			return new IC_(id, it);
		}
		if (it == CadseGCST.DISPLAY || CadseGCST.DISPLAY.isSuperTypeOf(it)) {
			return new UI_(id, it);
		}
		
		
		return super.newForCommitItem(wl, it, item);
	}

}

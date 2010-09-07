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
package fr.imag.adele.cadse.cadseg.teamwork.ui;

import java.util.ArrayList;
import java.util.List;

import fr.imag.adele.cadse.as.platformide.IPlatformIDE;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.internal.CadseDomainImpl;

public class SelectedItemChange_ModifiedAttr_ModelController extends
		SelectedItemChangeLinkModelController {

	public SelectedItemChange_ModifiedAttr_ModelController(boolean mandatory,
			String msg) {
		super(mandatory, msg);
	}

	@Override
	public Object getValue() {
		List<Object> resultList = new ArrayList<Object>();
		List<Link> linkList = (List<Link>) super.getValue();
		if (linkList != null)
			resultList.addAll(linkList);
		
		Item item = getItem();
		if (item == null)
			return resultList;
		ContentItem contentItem = item.getContentItem();
		if ((contentItem != null) && contentItem.isSCMModified()) {
			StringBuffer contentSB = new StringBuffer();
			contentSB.append("content");
			
			IPlatformIDE ideService = ((CadseDomainImpl) CadseCore.getCadseDomain()).getIdeService();
			String resName = ideService.getRessourceName(contentItem);
			if ((resName != null) && !resName.trim().equals("")) {
				contentSB.append(" (");
				contentSB.append(resName);
				contentSB.append(")");
			}
			resultList.add(contentSB.toString());
		}
		
		return resultList;
	}
}

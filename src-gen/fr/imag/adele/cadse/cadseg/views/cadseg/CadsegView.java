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

package fr.imag.adele.cadse.cadseg.views.cadseg;

import org.eclipse.ui.IViewSite;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.eclipse.view.AbstractCadseTreeViewUI;
import fr.imag.adele.cadse.eclipse.view.AbstractCadseView;

/**
 * @generated
 */
class CadsegViewUI extends AbstractCadseTreeViewUI {

	public CadsegViewUI(IViewSite site) {
		super(site);
		setRecomputeChildren(false);
	}

	@Override
	public ItemType[] getFirstItemType(LogicalWorkspace model) {
		return new ItemType[] { CadseGCST.CADSE_DEFINITION };

	}

	@Override
	public boolean isAggregationLink(Link arg0) {
		return arg0.isAggregation();
	}

	@Override
	public boolean isFirstItemType(ItemType it, LogicalWorkspace cadseModel) {
		return CadseGCST.CADSE_DEFINITION == it;
	}

	@Override
	public boolean isItemType(TypeDefinition it, LogicalWorkspace cadseModel) {
		return true;
	}

	@Override
	protected boolean isLink(Link link) {
		return true;
	}

	@Override
	public boolean isRefItemType(TypeDefinition it, LogicalWorkspace cadseModel) {
		return false;
	}

	@Override
	public String getDislplayCreate(LinkType link) {
		return null;
	}

	@Override
	public boolean isCreateLink(LinkType link) {
		return link.isAggregation();
	}

}

/**
 * The Class CadsegView.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class CadsegView extends AbstractCadseView {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.eclipse.view.AbstractCadseView#createUIController(org.eclipse.ui.IViewSite)
	 */
	@Override
	protected AbstractCadseTreeViewUI createUIController(IViewSite site) {
		return new CadsegViewUI(site);
	}

}

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

package fr.imag.adele.cadse.cadseg.managers.view.model;

import java.util.ArrayList;
import java.util.List;

import fr.imag.adele.cadse.cadseg.managers.view.ViewModelManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

/**
 * The Class ViewModels.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ViewModels {

	/**
	 * Instantiates a new view models.
	 * 
	 * @param viewModel
	 *            the view model
	 */
	public ViewModels(Item viewModel) {
		views = new ArrayList<ViewModel>();
		categories = new ArrayList<ViewCategoryModel>();
		ViewCategoryModel vc = new ViewCategoryModel();
		vc.name = viewModel.getPartParent().getName();
		vc.id = viewModel.getQualifiedName() + ".category";
		this.categories.add(vc);

		for (Item view : ViewModelManager.getViews(viewModel)) {
			ViewModel vm = new ViewModel(ContextVariableImpl.DEFAULT, view);
			vm.category = vc.id;

			this.views.add(vm);
		}
	}

	/** The views. */
	public List<ViewModel>			views;

	/** The categories. */
	public List<ViewCategoryModel>	categories;
}

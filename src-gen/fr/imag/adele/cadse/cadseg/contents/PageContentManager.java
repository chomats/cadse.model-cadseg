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
package fr.imag.adele.cadse.cadseg.contents;

import java.util.Set;
import java.util.UUID;

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
	 * The Class PageContentManager.
	 */
	public final class PageContentManager extends JavaFileContentManager  {

		/**
		 * Instantiates a new page content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		PageContentManager(UUID id) {
			super(id, new VariableImpl() {
				public String compute(ContextVariable context, Item itemCurrent) {
					return GenerateJavaIdentifier.javaPackagePageFactoryFromPage(context, itemCurrent);
				}
			}, new VariableImpl() {
				public String compute(ContextVariable context, Item itemCurrent) {
					return GenerateJavaIdentifier.javaClassNamePageFactoryFromPage(context, itemCurrent);
				}
			});
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeExportsPackage(java.util.Set)
		 */
		public void computeExportsPackage(Set<String> exports) {
			//exports.add(getPackageName(ContextVariableImpl.DEFAULT));
		}

		

//		/*
//		 * (non-Javadoc)
//		 * 
//		 * @see
//		 * fr.imag.adele.cadse.core.ContentManager#generate(fr.imag.adele.cadse
//		 * .core.GenStringBuilder, java.lang.String, java.lang.String,
//		 * java.util.Set, fr.imag.adele.cadse.core.GenContext)
//		 */
//		@Override
//		public void generate(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {
//
//			generate();
//		}

		/**
		 * Generate.
		 */
		public void generate() {
			//GeneratePageClass2.generate(ContextVariableImpl.DEFAULT, this, getOwnerItem());
			//if (hasPageAction(getOwnerItem())) {
			//	GeneratePageActionClass.generate(ContextVariableImpl.DEFAULT, getOwnerItem());
			//}
		}

		

		public void generate(ContextVariable cxt) {
			generate();
		}

		@Override
		public ContentItem getParentContentItemWherePutMyContent() {
			Item cadsedef = getOwnerItem().getPartParent(CadseGCST.CADSE_DEFINITION);
			if (cadsedef == null) {
				return null; // Cannot found the cadse definition
			}
			return cadsedef.getContentItem();
		}
	}
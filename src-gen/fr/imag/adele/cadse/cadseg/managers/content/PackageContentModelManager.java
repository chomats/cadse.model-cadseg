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

package fr.imag.adele.cadse.cadseg.managers.content;

import java.util.Set;

import fede.workspace.model.manager.properties.FieldsCore;
import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.cadseg.ParseTemplate;
import fr.imag.adele.cadse.cadseg.exp.ParseException;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.Pages;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * The Class PackageContentModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class PackageContentModelManager extends FolderContentModelManager {

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public PackageContentModelManager() {
		super();
	}

	/**
		@generated
	*/
	@Override
	public String computeQualifiedName(Item item, String name, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			Item currentItem;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(name);
			return sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * Gets the display name.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the display name
	 * 
	 * @generated
	 */
	@Override
	public String getDisplayName(Item item) {
		try {
			Object value;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * The Class ContentManager.
	 */
	public class ContentManager extends ContentItemTypeManager.MyContentItem {

		/**
		 * Instantiates a new content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		public ContentManager(CompactUUID id) {
			super(id);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			super.generateConstructorParameter(sb);
			sb.append(" String packageName,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			super.generateConstrustorArguments(sb);
			sb.append(" packageName,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#generateCallInit(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
		 */
		@Override
		protected void generateCallInit(GenStringBuilder sb, Set<String> imports, GenContext newcontext) {
			String itemVar = newcontext.getAttribute("itemVar");
			String value = getItem().getAttribute(PACKAGE_NAME_ATTRIBUTE);
			if (value == null || value.length() == 0) {
				value = "${#short-name}";
			}
			Item itemtype = ManagerManager.getItemType(getItem().getPartParent());

			ParseTemplate pt = new ParseTemplate(itemtype, value, null);
			try {
				pt.main();
				pt.build(itemVar, "sbPackage", sb, imports, null);
			} catch (ParseException e) {
			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, GenContext context) {
			sb.append("sbPackage.toString(),");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#computeImportsPackage(java.util.Set)
		 */
		@Override
		public void computeImportsPackage(Set<String> imports) {
			super.computeImportsPackage(imports);
			imports.add("fede.workspace.eclipse.composition");
		}

	}

	/** The Constant PACKAGE_NAME_ATTRIBUTE. */
	public static final String	PACKAGE_NAME_ATTRIBUTE	= "package-name";

	/**
	 * Creates the package name field.
	 * 
	 * @return the uI field
	 */
	protected UIField createPackageNameField() {
		return FieldsCore.createTextField(PACKAGE_NAME_ATTRIBUTE, "package name:", 1, "",
				new IC_ItemTypeTemplateForText() {
					@Override
					protected Item getItemFromContext() {
						Item manager = getContext().getPartParent();
						return ManagerManager.getItemType(manager);
					}
				}, new MC_AttributesItem() {
					@Override
					public Object defaultValue() {
						return "${#short-name}";
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager#createCreationPages(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.LinkType,
	 *      fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {

		ItemType it = CadseGCST.PACKAGE_CONTENT_MODEL;

		String title = "Create " + desType.getDisplayName();
		CreationAction action = new CreationAction(theItemParent, desType, theLinkType, it.getName());

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "Create " + title, "Create " + title, 3,
				FieldsCore.createCheckBox(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS, "extends class"),
				createPackageNameField()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager#createModificationPage(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Pages createModificationPage(Item item) {
		AbstractActionPage action = new ModificationAction(item);

		IModelController getandsetcontroller = new MC_AttributesItem();
		ItemType it = CadseGCST.PACKAGE_CONTENT_MODEL;

		String title = it.getDisplayName();

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "Create " + title, "Create " + title, 3,
				FieldsCore.createCheckBox(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS, "extends class"),
				createPackageNameField()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentManager createContentItem(CompactUUID id) throws CadseException {
		return new ContentManager(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	@Override
	public boolean hasParentContent() {
		return true;
	}

	@Override
	public Class<? extends ContentItem> getRuntimeClassName() {
		return fede.workspace.eclipse.java.manager.JavaPackageFolderContentManager.class;
	}

}
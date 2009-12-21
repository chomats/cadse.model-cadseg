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

import fr.imag.adele.cadse.core.CadseException;
import java.util.UUID;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class JavaFileContentModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class JavaFileContentModelManager extends FileContentModelManager {

	/**
	 * The Class ContentManager.
	 */
	public class MyContentItem extends ContentItemTypeManager.MyContentItem {

		/**
		 * Instantiates a new content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		public MyContentItem(UUID id) {
			super(id);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#getResourceKindsName()
		 */
		@Override
		protected String[] getResourceKindsName() {
			return new String[] { PACKAGE_NAME_ATTRIBUTE, CLASS_NAME_ATTRIBUTE };
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
			imports.add("fede.workspace.eclipse.content");
		}

	}

	/** The Constant PACKAGE_NAME_ATTRIBUTE. */
	public static final String	PACKAGE_NAME_ATTRIBUTE	= "package-name";

	/** The Constant CLASS_NAME_ATTRIBUTE. */
	public static final String	CLASS_NAME_ATTRIBUTE	= "class-name";

	/**
	 * Instantiates a new java file content model manager.
	 */
	public JavaFileContentModelManager() {
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

//	/**
//	 * Creates the class name field.
//	 * 
//	 * @return the uI field
//	 */
//	protected UIField createClassNameField() {
//		return FieldsCore.createTextField(CLASS_NAME_ATTRIBUTE, "class name:", 1, "", new IC_ItemTypeTemplateForText() {
//			@Override
//			protected Item getItemFromContext() {
//				Item manager = getContext().getPartParent();
//				return ManagerManager.getItemType(manager);
//			}
//		}, new MC_AttributesItem() {
//			@Override
//			public Object defaultValue() {
//				return "${#short-name}";
//			}
//		});
//	}
//
//	/**
//	 * Creates the package name field.
//	 * 
//	 * @return the uI field
//	 */
//	protected UIField createPackageNameField() {
//		return FieldsCore.createTextField(PACKAGE_NAME_ATTRIBUTE, "package name:", 1, "",
//				new IC_ItemTypeTemplateForText() {
//					@Override
//					protected Item getItemFromContext() {
//						Item manager = getContext().getPartParent();
//						return ManagerManager.getItemType(manager);
//					}
//				}, new MC_AttributesItem() {
//					@Override
//					public Object defaultValue() {
//						return "${#short-name}";
//					}
//				});
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see model.workspace.workspace.managers.content.ContentModelManager#createCreationPages(fr.imag.adele.cadse.core.Item,
//	 *      fr.imag.adele.cadse.core.LinkType,
//	 *      fr.imag.adele.cadse.core.ItemType)
//	 */
//	@Override
//	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {
//
//		ItemType it = desType;
//
//		String title = "Create " + desType.getDisplayName();
//		CreationAction action = new CreationAction(theItemParent, desType, theLinkType, it.getName());
//
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "Create " + title, "Create " + title, 3,
//				FieldsCore.createCheckBox(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS, "extends class"),
//				createPackageNameField(), createClassNameField()));
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see model.workspace.workspace.managers.content.ContentModelManager#createModificationPage(fr.imag.adele.cadse.core.Item)
//	 */
//	@Override
//	public Pages createModificationPage(Item item) {
//		AbstractActionPage action = new ModificationAction(item);
//
//		RunningModelController getandsetcontroller = new MC_AttributesItem();
//		ItemType it = item.getType();
//
//		String title = it.getDisplayName();
//
//		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "Create " + title, "Create " + title, 3,
//				FieldsCore.createCheckBox(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS, "extends class"),
//				createPackageNameField(), createClassNameField()));
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentItem createContentItem(UUID id) throws CadseException {
		return new MyContentItem(id);
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
		return fede.workspace.eclipse.java.manager.JavaFileContentManager.class;
	}
}

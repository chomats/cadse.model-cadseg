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

import java.util.Collection;
import java.util.List;
import java.util.Set;

import fede.workspace.model.manager.properties.FieldsCore;
import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.cadseg.ParseTemplate;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.exp.ParseException;
import fr.imag.adele.cadse.cadseg.exp.TokenMgrError;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * The Class ProjectContentModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ProjectContentModelManager extends ContentModelManager {

	/**
	 * The Class ContentManager.
	 */
	public class MyContentItem extends ContentModelManager.MyContentItem {

		/**
		 * Instantiates a new content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 */
		public MyContentItem(MyContentItem parent, Item item) {
			super(parent, item);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#getResourceKindsName()
		 */
		@Override
		protected String[] getResourceKindsName() {
			return new String[] { WorkspaceCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME };
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#generate(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.lang.String, java.lang.String, java.util.Set,
		 *      fr.imag.adele.cadse.core.GenContext)
		 */
		@Override
		public void generate(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {
			super.generate(sb, type, kind, imports, context);
			imports.add("fede.workspace.eclipse.content.ProjectContentManager");
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
			imports.add("org.eclipse.ui.model");
			imports.add("fede.workspace.eclipse.content");

		}

	}

	/**
	 * Instantiates a new project content model manager.
	 */
	public ProjectContentModelManager() {
	}

	/**
	 * Compute unique name.
	 * 
	 * @param item
	 *            the item
	 * @param shortName
	 *            the short name
	 * @param parent
	 *            the parent
	 * @param lt
	 *            the lt
	 * 
	 * @return the string
	 * 
	 * @generated
	 */
	@Override
	public String computeUniqueName(Item item, String shortName, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(shortName);
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
			Item currentItem;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * get links 'content-model' from 'ProjectContentModel' to
	 * 'ResourceContentModel'.
	 * 
	 * @param projectContentModel
	 *            the project content model
	 * 
	 * @return the content model link
	 * 
	 * @generated
	 */
	static public List<Link> getContentModelLink(Item projectContentModel) {
		return projectContentModel.getOutgoingLinks(WorkspaceCST.PROJECT_CONTENT_MODEL_lt_CONTENT_MODEL);
	}

	/**
	 * Gets the content model all.
	 * 
	 * @param projectContentModel
	 *            the project content model
	 * 
	 * @return the content model all
	 * 
	 * @generated
	 */
	static public Collection<Item> getContentModelAll(Item projectContentModel) {
		return projectContentModel.getOutgoingItems(WorkspaceCST.PROJECT_CONTENT_MODEL_lt_CONTENT_MODEL, false);
	}

	/**
	 * Gets the content model.
	 * 
	 * @param projectContentModel
	 *            the project content model
	 * 
	 * @return the content model
	 * 
	 * @generated
	 */
	static public Collection<Item> getContentModel(Item projectContentModel) {
		return projectContentModel.getOutgoingItems(WorkspaceCST.PROJECT_CONTENT_MODEL_lt_CONTENT_MODEL, true);
	}

	/**
	 * Adds the content model.
	 * 
	 * @param projectContentModel
	 *            the project content model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void addContentModel(Item projectContentModel, Item value) throws CadseException {
		projectContentModel.addOutgoingItem(WorkspaceCST.PROJECT_CONTENT_MODEL_lt_CONTENT_MODEL, value);
	}

	/**
	 * Removes the content model.
	 * 
	 * @param projectContentModel
	 *            the project content model
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void removeContentModel(Item projectContentModel, Item value) throws CadseException {
		projectContentModel.removeOutgoingItem(WorkspaceCST.PROJECT_CONTENT_MODEL_lt_CONTENT_MODEL, value);
	}

	/**
	 * @generated
	 */
	public static final String getProjectNameAttribute(Item projectContentModel) {
		return projectContentModel
				.getAttributeWithDefaultValue(WorkspaceCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_, "");
	}

	/**
	 * @generated
	 */
	public static final void setProjectNameAttribute(Item projectContentModel, String value) {
		try {
			projectContentModel.setAttribute(WorkspaceCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Creates the project field.
	 * 
	 * @return the uI field
	 */
	protected UIField createProjectField() {
		return FieldsCore.createTextField(WorkspaceCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME, "project name:", 1, "",
				new IC_ItemTypeTemplateForText() {
					@Override
					protected Item getItemFromContext() {
						Item manager = getContext().getPartParent();
						return ManagerManager.getItemType(manager);
					}
				}, new MC_AttributesItem() {
					@Override
					public Object defaultValue() {
						return "${#unique-name}";
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager#createContentManager(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public MyContentItem createContentManager(Item item) throws CadseException {
		return new MyContentItem(null, item);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#validate(fr.imag.adele.cadse.core.Item,
	 *      fr.imag.adele.cadse.core.IItemManager.ProblemReporter)
	 */
	@Override
	public List<Item> validate(Item item, ProblemReporter reporter) {
		String value = item.getAttribute(WorkspaceCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_);
		if (value == null || value.length() == 0) {
			value = "${#unique-name}";
		}
		Item itemtype = ManagerManager.getItemType(item.getPartParent());
		O: {
			if (itemtype == null) {
				reporter.error(item, 1, "Item type is null");
				break O;
			}
			ParseTemplate pt = new ParseTemplate(itemtype, value, null);
			try {
				pt.main();
				pt.validate(reporter, item);
			} catch (ParseException e) {
				reporter.error(item, 0, "Error when parse project name value : {0} ", e.getMessage());
			} catch (TokenMgrError e) {
				reporter.error(item, 0, "Error when parse project name value : {0} ", e.getMessage());
			}

		}
		return super.validate(item, reporter);
	}

	@Override
	public boolean hasParentContent() {
		return false;
	}

	@Override
	public Class<? extends ContentItem> getRuntimeClassName() {
		return fede.workspace.eclipse.content.ProjectContentManager.class;
	}
}

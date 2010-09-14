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
package fr.imag.adele.cadse.cadseg.managers.content;

import java.util.Collection;
import java.util.List;

import fr.imag.adele.cadse.cadseg.eclipse.ParseTemplate;
import fr.imag.adele.cadse.cadseg.exp.ParseException;
import fr.imag.adele.cadse.cadseg.exp.TokenMgrError;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.Validator;

/**
 * The Class ProjectContentModelManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ProjectContentModelManager extends ContentItemTypeManager {

	

	/**
	 * Instantiates a new project content model manager.
	 */
	public ProjectContentModelManager() {
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
        return projectContentModel.getOutgoingLinks(CadseGCST.PROJECT_CONTENT_MODEL_lt_CONTENT_MODEL);
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
        return projectContentModel.getOutgoingItems(CadseGCST.PROJECT_CONTENT_MODEL_lt_CONTENT_MODEL, false);
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
        return projectContentModel.getOutgoingItems(CadseGCST.PROJECT_CONTENT_MODEL_lt_CONTENT_MODEL,true);
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
        projectContentModel.addOutgoingItem(CadseGCST.PROJECT_CONTENT_MODEL_lt_CONTENT_MODEL,value);
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
        projectContentModel.removeOutgoingItem(CadseGCST.PROJECT_CONTENT_MODEL_lt_CONTENT_MODEL,value);
    }

	/**
	 * @generated
	 */
	public static final String getProjectNameAttribute(Item projectContentModel) {
		return projectContentModel.getAttributeWithDefaultValue(CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_, "${#qualified-name}");
	}

	/**
	 * @generated
	 */
	public static final void setProjectNameAttribute(Item projectContentModel, String value) {
		try {
			projectContentModel.setAttribute(CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_, value);
		} catch (Throwable t) {

		}
	}


	public static final class ProjectContentValidator extends Validator {

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.DefaultItemManager#validate(fr.imag.adele.cadse.core.Item,
		 *      fr.imag.adele.cadse.core.IItemManager.ProblemReporter)
		 */
		@Override
		public List<Item> validate(Item item, ProblemReporter reporter) {
			String value = item.getAttribute(CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_);
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
			return null;
		}
	}
	
}

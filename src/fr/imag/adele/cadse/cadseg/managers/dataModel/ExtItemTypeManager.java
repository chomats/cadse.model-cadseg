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

package fr.imag.adele.cadse.cadseg.managers.dataModel;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.MelusineProjectManager;
import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.generate.GenerateExtItemType;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.key.SpaceKeyType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.Variable;
import fr.imag.adele.fede.workspace.si.view.View;

/**
 * The Class ExtItemTypeManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ExtItemTypeManager extends AbstractItemTypeManager {

	/**
	 * @generated
	 */
	static final class PackageNameVariable extends VariableImpl {

		/**
		 * @generated
		 */
		public final static Variable	INSTANCE	= new PackageNameVariable();

		/**
		 * @generated
		 */
		public String compute(ContextVariable context, Item itemCurrent) {
			try {
				Object value;
				return context.getName(itemCurrent);
			} catch (Throwable e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	/**
	 * @generated
	 */
	static final class ClassNameVariable extends VariableImpl {

		/**
		 * @generated
		 */
		public final static Variable	INSTANCE	= new ClassNameVariable();

		/**
		 * @generated
		 */
		public String compute(ContextVariable context, Item itemCurrent) {
			try {
				Object value;
				return context.getName(itemCurrent);
			} catch (Throwable e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	/**
	 * The Class MyContentItem. implements IGenerateContent
	 * 
	 * @generated
	 */
	public class MyContentItem extends JavaFileContentManager implements IGenerateContent {

		/**
		 * @generated
		 */
		public MyContentItem(ContentItem parent, Item item, Variable packageNameVariable, Variable classNameVariable)
				throws CadseException {
			super(parent, item, packageNameVariable, classNameVariable);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fr.imag.adele.cadse.core.IGenerateContent#generate(fr.imag.adele.
		 * cadse.core.var.ContextVariable)
		 */
		public void generate(ContextVariable cxt) {
			Item extit = getItem();

			GenerateExtItemType ge = new GenerateExtItemType(cxt, this);
			Item cadseDefinition = extit.getPartParent(WorkspaceCST.CADSE_DEFINITION);

			((IGenerateContent) cadseDefinition.getContentItem()).generate(cxt);

			String path = getPath(cxt);
			try {
				EclipsePluginContentManger.generateJava(MelusineProjectManager.getProject(cadseDefinition).getFile(
						new Path(path)), ge.getContent(), View.getDefaultMonitor());

			} catch (CoreException e) {
				e.printStackTrace();
			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.IGenerateContent#getGenerateModel()
		 */
		public ManagerManager.GenerateModel getGenerateModel() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeExportsPackage(java.util.Set)
		 */
		public void computeExportsPackage(Set<String> exports) {
			exports.add(getPackageName(ContextVariable.DEFAULT));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeImportsPackage(java.util.Set)
		 */
		public void computeImportsPackage(Set<String> imports) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion
		 * (org.eclipse.pde.core.plugin.IPluginBase,
		 * org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
		 */
		public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
		}

	}

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public ExtItemTypeManager() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#init(fr.imag.adele.cadse
	 * .core.ItemType)
	 */
	@Override
	public void init() {
		WorkspaceCST.EXT_ITEM_TYPE.setSpaceKeyType(new SpaceKeyType(WorkspaceCST.EXT_ITEM_TYPE, null));
		WorkspaceCST.EXT_ITEM_TYPE.setHasShortNameAttribute(true);
		WorkspaceCST.EXT_ITEM_TYPE.setHasUniqueNameAttribute(false);
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
	public String computeUniqueName(Item item, String name, Item parent, LinkType lt) {
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
	 * Creates the content manager.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the content manager
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	@Override
	public ContentItem createContentManager(Item extItemType) throws CadseException {
		MyContentItem cm = new MyContentItem(null, extItemType, PackageNameVariable.INSTANCE,
				ClassNameVariable.INSTANCE);
		cm.setComposers();
		cm.setExporters();
		return cm;
	}

	/**
	 * get a link 'ref-type' from 'ExtItemType' to 'ItemType'.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the ref type link
	 * 
	 * @generated
	 */
	static public Link getRefTypeLink(Item extItemType) {
		return extItemType.getOutgoingLink(WorkspaceCST.EXT_ITEM_TYPE_lt_REF_TYPE);
	}

	/**
	 * get all link destination 'ref-type' from 'ExtItemType' to 'ItemType'.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the ref type all
	 * 
	 * @generated
	 */
	static public Item getRefTypeAll(Item extItemType) {
		return extItemType.getOutgoingItem(WorkspaceCST.EXT_ITEM_TYPE_lt_REF_TYPE, false);
	}

	/**
	 * get resolved link destination 'ref-type' from 'ExtItemType' to
	 * 'ItemType'.
	 * 
	 * @param extItemType
	 *            the ext item type
	 * 
	 * @return the ref type
	 * 
	 * @generated
	 */
	static public Item getRefType(Item extItemType) {
		return extItemType.getOutgoingItem(WorkspaceCST.EXT_ITEM_TYPE_lt_REF_TYPE, true);
	}

	/**
	 * set a link 'ref-type' from 'ExtItemType' to 'ItemType'.
	 * 
	 * @generated
	 */
	static public void setRefType(Item extItemType, Item value) throws CadseException {
		extItemType.setOutgoingItem(WorkspaceCST.EXT_ITEM_TYPE_lt_REF_TYPE, value);
	}

	/**
	 * get links '#invert_part_ext-types_to_DataModel' from 'ExtItemType' to
	 * 'DataModel'.
	 * 
	 * @generated
	 */
	static public Link get_$_Invert_part_extTypes_to_DataModelLink(Item extItemType) {
		return extItemType.getOutgoingLink(WorkspaceCST.EXT_ITEM_TYPE_lt__$_INVERT_PART_EXT_TYPES_TO_DATA_MODEL);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_extTypes_to_DataModelAll(Item extItemType) {
		return extItemType.getOutgoingItem(WorkspaceCST.EXT_ITEM_TYPE_lt__$_INVERT_PART_EXT_TYPES_TO_DATA_MODEL, false);
	}

	/**
	 * @generated
	 */
	static public Item get_$_Invert_part_extTypes_to_DataModel(Item extItemType) {
		return extItemType.getOutgoingItem(WorkspaceCST.EXT_ITEM_TYPE_lt__$_INVERT_PART_EXT_TYPES_TO_DATA_MODEL, true);
	}

	/**
	 * set a link '#invert_part_ext-types_to_DataModel' from 'ExtItemType' to
	 * 'DataModel'.
	 * 
	 * @generated
	 */
	static public void set_$_Invert_part_extTypes_to_DataModel(Item extItemType, Item value) throws CadseException {
		extItemType.setOutgoingItem(WorkspaceCST.EXT_ITEM_TYPE_lt__$_INVERT_PART_EXT_TYPES_TO_DATA_MODEL, value);
	}

}

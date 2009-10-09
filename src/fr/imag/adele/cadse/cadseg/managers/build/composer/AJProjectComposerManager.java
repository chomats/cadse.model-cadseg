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

package fr.imag.adele.cadse.cadseg.managers.build.composer;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import fede.workspace.model.manager.properties.FieldsCore;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.ui.Pages;

/**
 * The Class AJProjectComposerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class AJProjectComposerManager extends EclipseComposerManager {

	/**
	 * The Class ContentManager.
	 */
	public class ContentManager extends EclipseComposerManager.MyContentItem {

		/**
		 * Instantiates a new content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 * @throws CadseException
		 */
		public ContentManager(ContentManager parent, Item item) throws CadseException {
			super(parent, item);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * model.workspace.workspace.managers.build.ComposerManager.ContentManager
		 * #
		 * generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder
		 * )
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			super.generateConstructorParameter(sb);
			sb.append("Path skipWeaving,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * model.workspace.workspace.managers.build.ComposerManager.ContentManager
		 * #
		 * generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder
		 * )
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			super.generateConstrustorArguments(sb);
			sb.append("skipWeaving,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * model.workspace.workspace.managers.build.ComposerManager.ContentManager
		 * #generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 * java.util.Set, fr.imag.adele.cadse.core.GenContext)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, GenContext context) {
			sb.append(getItem().getAttributeWithDefaultValue(SKIP_WEAVING_ATTRIBUTE, "false"));
		}

	}

	/** The Constant SKIP_WEAVING_ATTRIBUTE. */
	public static final String	SKIP_WEAVING_ATTRIBUTE	= "SKIP_WEAVING";

	/**
	 * Instantiates a new aJ project composer manager.
	 */
	public AJProjectComposerManager() {
	}

	/**
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
	 * get links 'composer-links' from 'AJProjectComposer' to 'ComposerLink'.
	 * 
	 * @generated
	 */
	static public List<Link> getComposerLinksLink(Item aJProjectComposer) {
		return aJProjectComposer.getOutgoingLinks(WorkspaceCST.AJPROJECT_COMPOSER_lt_COMPOSER_LINKS);
	}

	/**
	 * @generated
	 */
	static public Collection<Item> getComposerLinksAll(Item aJProjectComposer) {
		return aJProjectComposer.getOutgoingItems(WorkspaceCST.AJPROJECT_COMPOSER_lt_COMPOSER_LINKS, false);
	}

	/**
	 * @generated
	 */
	static public Collection<Item> getComposerLinks(Item aJProjectComposer) {
		return aJProjectComposer.getOutgoingItems(WorkspaceCST.AJPROJECT_COMPOSER_lt_COMPOSER_LINKS, true);
	}

	/**
	 * @generated
	 */
	static public void addComposerLinks(Item aJProjectComposer, Item value) throws CadseException {
		aJProjectComposer.addOutgoingItem(WorkspaceCST.AJPROJECT_COMPOSER_lt_COMPOSER_LINKS, value);
	}

	/**
	 * @generated
	 */
	static public void removeComposerLinks(Item aJProjectComposer, Item value) throws CadseException {
		aJProjectComposer.removeOutgoingItem(WorkspaceCST.AJPROJECT_COMPOSER_lt_COMPOSER_LINKS, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.ComposerManager#createCreationPages
	 * (fr.imag.adele.cadse.core.Item, fr.imag.adele.cadse.core.LinkType,
	 * fr.imag.adele.cadse.core.ItemType)
	 */
	@Override
	public Pages createCreationPages(Item theItemParent, LinkType theLinkType, ItemType desType) {
		CreationAction action = new CreationAction(theItemParent, desType, theLinkType, "aspectj-composer");

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "Create an aspectj composer",
				"Create an aspectj composer", 3, FieldsCore.createCheckBox(EXTENDS_CLASS_ATTRIBUTE, "extends class"),
				FieldsCore.createCheckBox(SKIP_WEAVING_ATTRIBUTE, "skip weaving")

		));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seemodel.workspace.workspace.managers.build.ComposerManager#
	 * createModificationPage(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public Pages createModificationPage(Item item) {
		AbstractActionPage action = new ModificationAction(item);

		return FieldsCore.createWizard(action, FieldsCore.createPage("page1", "an aspectj composer",
				"an aspectj composer", 3, FieldsCore.createShortNameField(), FieldsCore.createCheckBox(
						EXTENDS_CLASS_ATTRIBUTE, "extends class"), FieldsCore.createCheckBox(SKIP_WEAVING_ATTRIBUTE,
						"skip weaving")

		));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.ComposerManager#createContentManager
	 * (fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public ContentManager createContentManager(Item item) throws CadseException {
		return new ContentManager(null, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.composer.EclipseComposerManager
	 * #mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	/** The Constant DEFAUL_CLASS_NAME. */
	@SuppressWarnings("hiding")
	public static final String	DEFAUL_CLASS_NAME	= "fede.workspace.eclipse.composition.java.AspectJProjectComposer";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.composer.EclipseComposerManager
	 * #getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		return DEFAUL_CLASS_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.composer.EclipseComposerManager
	 * #generateGetTargetMethod()
	 */
	@Override
	public boolean generateGetTargetMethod() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.ComposerManager#callsuperPostCompose
	 * ()
	 */
	@Override
	public boolean callsuperPostCompose() {
		return true;
	}
}

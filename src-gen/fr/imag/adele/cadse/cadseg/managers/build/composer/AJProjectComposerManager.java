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

import fr.imag.adele.cadse.cadseg.contents.ic.InteractionControllerContent;
import fr.imag.adele.cadse.cadseg.managers.ic.InteractionControllerManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;

/**
 * The Class AJProjectComposerManager.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class AJProjectComposerManager extends EclipseComposerManager {

	/**
	 * The Class ContentManager.
	 */
	public class AJProjectComposerContent extends InteractionControllerContent {

		/**
		 * Instantiates a new content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 * @throws CadseException
		 */
		public AJProjectComposerContent(CompactUUID id, InteractionControllerManager manager) throws CadseException {
			super(id, manager);
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
        return aJProjectComposer.getOutgoingLinks(CadseGCST.AJPROJECT_COMPOSER_lt_COMPOSER_LINKS);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getComposerLinksAll(Item aJProjectComposer) {
        return aJProjectComposer.getOutgoingItems(CadseGCST.AJPROJECT_COMPOSER_lt_COMPOSER_LINKS, false);
    }

	/**
	 * @generated
	 */
	static public Collection<Item> getComposerLinks(Item aJProjectComposer) {
        return aJProjectComposer.getOutgoingItems(CadseGCST.AJPROJECT_COMPOSER_lt_COMPOSER_LINKS,true);
    }

	/**
	 * @generated
	 */
	static public void addComposerLinks(Item aJProjectComposer, Item value) throws CadseException {
        aJProjectComposer.addOutgoingItem(CadseGCST.AJPROJECT_COMPOSER_lt_COMPOSER_LINKS,value);
    }

	/**
	 * @generated
	 */
	static public void removeComposerLinks(Item aJProjectComposer, Item value) throws CadseException {
        aJProjectComposer.removeOutgoingItem(CadseGCST.AJPROJECT_COMPOSER_lt_COMPOSER_LINKS,value);
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

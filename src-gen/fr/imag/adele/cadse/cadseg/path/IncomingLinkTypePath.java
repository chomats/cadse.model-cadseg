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

package fr.imag.adele.cadse.cadseg.path;

import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.path.PathConstants;

/**
 * The Class IncomingLinkTypePath.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class IncomingLinkTypePath extends ElementPath {

	/** The source. */
	public final Item	fSource;

	/** The link type. */
	public final Item	fLinkType;

	/**
	 * Instantiates a new incoming link type path.
	 * 
	 * @param lt
	 *            the lt
	 */
	public IncomingLinkTypePath(Item lt) {
		fSource = LinkTypeManager.getSource(lt);
		;
		fLinkType = lt;
	}

	/**
	 * Instantiates a new incoming link type path.
	 * 
	 * @param lt
	 *            the lt
	 * @param pos
	 *            the pos
	 */
	public IncomingLinkTypePath(Item lt, int pos) {
		this(lt);
		this.position = pos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.path.ElementPath#getItemType()
	 */
	@Override
	public Item getItemType() {
		return fSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.path.ElementPath#getText()
	 */
	@Override
	public String getText() {
		return "." + PathConstants.INCOMING_LINK_PATH + fLinkType.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.path.ElementPath#getCursorPosition()
	 */
	@Override
	public int getCursorPosition() {
		return position + getText().length();
	}

}
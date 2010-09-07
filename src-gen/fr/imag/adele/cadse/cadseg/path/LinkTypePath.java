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
package fr.imag.adele.cadse.cadseg.path;

import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class LinkTypePath.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class LinkTypePath extends ElementPath {

	/** The destination. */
	public final Item		fDestination;

	/** The link type. */
	public final Item		fLinkType;

	/** The closure. */
	public final boolean	fClosure;

	/** The pos. */
	private int				pos;

	/**
	 * Instantiates a new link type path.
	 * 
	 * @param lt
	 *            the lt
	 * @param closure
	 *            the closure
	 */
	public LinkTypePath(Item lt, boolean closure) {
		fDestination = LinkTypeManager.getDestination(lt);
		if (fDestination == null)
			throw new IllegalArgumentException("Invalide Link type");
		fLinkType = lt;
		fClosure = closure;
	}

	/**
	 * Instantiates a new link type path.
	 * 
	 * @param lt
	 *            the lt
	 * @param closure
	 *            the closure
	 * @param pos
	 *            the pos
	 */
	public LinkTypePath(Item lt, boolean closure, int pos) {
		this(lt, closure);
		this.pos = pos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.path.ElementPath#getItemType()
	 */
	@Override
	public Item getItemType() {
		return fDestination;
	}

	/**
	 * Checks if is closure.
	 * 
	 * @return true, if is closure
	 */
	public boolean isClosure() {
		return fClosure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.path.ElementPath#getText()
	 */
	@Override
	public String getText() {
		return "." + fLinkType.getName() + (fClosure ? "*" : "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.path.ElementPath#getCursorPosition()
	 */
	@Override
	public int getCursorPosition() {
		return pos + getText().length();
	}

}
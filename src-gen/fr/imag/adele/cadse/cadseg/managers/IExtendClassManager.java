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

package fr.imag.adele.cadse.cadseg.managers;

import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.core.Item;

/**
 * The Interface IExtendClassManager.
 */
public interface IExtendClassManager extends IModelWorkspaceManager {

	/** The Constant CLASS_NAME. */
	public static final String	CLASS_NAME	= "class-name";

	/**
	 * Gets the class name.
	 * 
	 * @param uc
	 *            the uc
	 * 
	 * @return the class name
	 */
	public abstract String getClassName(Item uc);

	/**
	 * Gets the default class name.
	 * 
	 * @return the default class name
	 */
	public abstract String getDefaultClassName();

	/**
	 * Must be extended.
	 * 
	 * @return true, if successful
	 */
	public abstract boolean mustBeExtended();

}
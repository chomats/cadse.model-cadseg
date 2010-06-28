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

package fr.imag.adele.cadse.cadseg;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * The Class Activator.
 * 
 * @generated
 */
public class Activator implements BundleActivator {

	/** The PLUGI n_ id. */
	public static String		PLUGIN_ID	= "Model.Workspace.CadseG";

	/** The _default. */
	private static Activator	_default;

	/**
	 * The Constructor.
	 * 
	 * @generated
	 */
	public Activator() {
		Activator._default = this;
	}

	/**
	 * Start.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @throws Exception
	 *             the exception
	 * 
	 * @generated
	 */
	@Override
	public void start(BundleContext context) throws Exception {
	}

	/**
	 * Stop.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @throws Exception
	 *             the exception
	 * 
	 * @generated
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
	}

	/**
	 * Gets the default.
	 * 
	 * @return the default
	 * 
	 * @generated
	 */
	public static Activator getDefault() {
		return _default;
	}

}

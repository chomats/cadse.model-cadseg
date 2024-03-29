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
package fr.imag.adele.cadse.cadseg.eclipse;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The Class Activator.
 * 
 * @generated
 */
public class Activator extends AbstractUIPlugin {

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
		super.start(context);
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
		super.stop(context);
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

	/**
	 * Log.
	 * 
	 * @param status
	 *            the status
	 * 
	 * @generated
	 */
	public void log(IStatus status) {
		this.getLog().log(status);
	}
	
	
	public static void logMessage(String message) {
		Activator.getDefault().log(new Status(Status.ERROR, Activator.PLUGIN_ID, message));
	}
	
	public static void logMessage(String message, Throwable e) {
		Activator.getDefault().log(new Status(Status.ERROR, Activator.PLUGIN_ID, message, 
				e ));	}
}

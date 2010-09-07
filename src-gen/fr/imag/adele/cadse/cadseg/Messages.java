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
package fr.imag.adele.cadse.cadseg;

import fr.imag.adele.cadse.util.NLS;


/**
 * The Class Messages.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class Messages extends NLS {

	/** The Constant BUNDLE_NAME. */
	private static final String	BUNDLE_NAME	= "fr.imag.adele.cadse.cadseg.messages";	//$NON-NLS-1$

	public static String		error_overwrite_attribute_bad_type;

	public static String		error_overwrite_attribute_cannot_permitted;

	public static String		information_overwrite_attribute;

	/** The java_version. */
	public static String		java_version;

	/** The status_select_an_item. */
	public static String		status_select_an_item;

	/** The status_select_an_item_of_type. */
	public static String		status_select_an_item_of_type;

	/** The status_select_one_item. */
	public static String		status_select_one_item;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	/**
	 * Instantiates a new messages.
	 */
	private Messages() {
	}
}

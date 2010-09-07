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
package fr.imag.adele.cadse.cadseg.teamwork;

import org.eclipse.jface.preference.IPreferenceStore;

import fr.imag.adele.cadse.cadseg.eclipse.Activator;

/**
 * Used to get and set values of TeamWork preferences.
 * 
 * @author Thomas
 *
 */
public class TeamWorkPreferences {

	private static final String DEFAULT_USER_NAME = "";
	private static final String TEAMWORK_USER_PROP_NAME = "teamwork.user";

	/**
	 * Set user name for versioning.
	 * 
	 * @param userName new user name
	 */
	public static void setUserName(String userName) {
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		prefStore.setValue(TEAMWORK_USER_PROP_NAME, userName);
	}

	/**
	 * Returns user name for versioning.
	 * 
	 * @return user name for versioning.
	 */
	public static String getUserName() {
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		if (!prefStore.contains(TEAMWORK_USER_PROP_NAME))
			return DEFAULT_USER_NAME;
			
		String val = prefStore.getString(TEAMWORK_USER_PROP_NAME);
		if (val == null)
			return DEFAULT_USER_NAME;
		else
			return val;
	}
}

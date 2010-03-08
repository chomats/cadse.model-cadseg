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
package fr.imag.adele.cadse.cadseg.teamwork.db;

import org.eclipse.jface.preference.IPreferenceStore;

import fr.imag.adele.cadse.cadseg.Activator;


/**
 * Represents all connexion parameters needed to access to a teamwork database.
 * These informations are saved in workspace properties.
 * 
 * @author Thomas
 * 
 */
public class DBConnexionParams {

	private static final String	DB_PROP_PREFIX	= "teamwork.db.";

	private static final String	DB_URL_PROP_POSTFIX	= ".url";
	
	private static final String	DB_LOGIN_PROP_POSTFIX	= ".login";
	
	private static final String	DB_PWD_PROP_POSTFIX	= ".pwd";
	
	private static final String	DB_CONTENT_URL_PROP_POSTFIX	= ".default.content.url";
	
	/*
	 * Fields
	 */
	private String _url;

	private String _login;
	
	private String _password;
	
	private String _defaultContentRepoURL;
	
	public String getUrl() {
		return _url;
	}

	public String getLogin() {
		return _login;
	}

	public String getPassword() {
		return _password;
	}

	public String getDefaultContentRepoURL() {
		return _defaultContentRepoURL;
	}
	
	private DBConnexionParams(String url, String login, String password, String defaultContentUrl) {
		_url = url;
		_login = login;
		_password = password;
		_defaultContentRepoURL = defaultContentUrl;
	}
	
	/**
	 * Returns parameters necessary to be able to connect to specified teamwork database.
	 * 
	 * @param cadseName name of cadse we want to access related database.
	 * @return parameters necessary to be able to connect to specified teamwork database.
	 */
	public static synchronized DBConnexionParams getConnectionParams(String cadseName) {
		
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		String urlProp = getURLProp(cadseName);
		String loginProp = getLoginProp(cadseName);
		String pwdProp = getPasswordProp(cadseName);
		String contentUrlProp = getDefaultContentURLProp(cadseName);
		
		String url = getWorkspacePropValue(prefStore, urlProp);
		String login = getWorkspacePropValue(prefStore, loginProp);
		String password = getWorkspacePropValue(prefStore, pwdProp);
		String defaultContentUrl = getWorkspacePropValue(prefStore, contentUrlProp);

		return new DBConnexionParams(url, login, password, defaultContentUrl);
	}

	private static String getDefaultContentURLProp(String cadseName) {
		return DB_PROP_PREFIX + cadseName + DB_CONTENT_URL_PROP_POSTFIX;
	}

	private static String getPasswordProp(String cadseName) {
		return DB_PROP_PREFIX + cadseName + DB_PWD_PROP_POSTFIX;
	}

	private static String getLoginProp(String cadseName) {
		return DB_PROP_PREFIX + cadseName + DB_LOGIN_PROP_POSTFIX;
	}

	private static String getURLProp(String cadseName) {
		return DB_PROP_PREFIX + cadseName + DB_URL_PROP_POSTFIX;
	}
	
	private static String getWorkspacePropValue(IPreferenceStore prefStore, String property) {
		if (!prefStore.contains(property))
			return "";
			
		String val = prefStore.getString(property);
		if (val == null)
			return "";
		else
			return val;
	}
	
	/**
	 * Saves parameters necessary to be able to connect to specified teamwork database.
	 * 
	 * @param cadseName name of cadse we want to access related database.
	 * @param url               jdbc url of the teamwork database
	 * @param login             login to connect to the teamwork database
	 * @param password          password to connect to the teamwork database
	 * @param defaultContentUrl url of default repository used for content
	 */
	public static synchronized void setConnectionParams(String cadseName, 
			String url, String login, String password, String defaultContentUrl) {
		
		String urlProp = getURLProp(cadseName);
		String loginProp = getLoginProp(cadseName);
		String pwdProp = getPasswordProp(cadseName);
		String contentUrlProp = getDefaultContentURLProp(cadseName);
		
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		prefStore.setValue(urlProp, url);
		prefStore.setValue(loginProp, login);
		prefStore.setValue(pwdProp, password);
		prefStore.setValue(contentUrlProp, defaultContentUrl);
	}
	
	/**
	 * Saves url of specified teamwork database.
	 * 
	 * @param cadseName name of cadse we want to access related database.
	 * @param url       jdbc url of the teamwork database
	 */
	public static synchronized void setURL(String cadseName, String url) {
		
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		prefStore.setValue(getURLProp(cadseName), url);
	}
	
	/**
	 * Saves login to connect to specified teamwork database.
	 * 
	 * @param cadseName name of cadse we want to access related database.
	 * @param login     login to connect to the teamwork database
	 */
	public static synchronized void setLogin(String cadseName, String login) {
		
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		prefStore.setValue(getLoginProp(cadseName), login);
	}
	
	/**
	 * Saves password to connect to specified teamwork database.
	 * 
	 * @param cadseName name of cadse we want to access related database.
	 * @param password  password to connect to the teamwork database
	 */
	public static synchronized void setPassword(String cadseName, String password) {
		
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		prefStore.setValue(getPasswordProp(cadseName), password);
	}
	
	/**
	 * Saves url of default content repository.
	 * 
	 * @param cadseName         name of cadse we want to access related database.
	 * @param defaultContentUrl url of content repository (for example, svn url)
	 */
	public static synchronized void setDefaultContentURL(String cadseName, String defaultContentUrl) {
		
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		prefStore.setValue(getDefaultContentURLProp(cadseName), defaultContentUrl);
	}
}

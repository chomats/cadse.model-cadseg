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
package fr.imag.adele.cadse.cadseg.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;

/**
 * The Class AttributeValue.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class AttributeValue {

	/** The key. */
	String	key;

	/** The value. */
	Object	value;

	/**
	 * The Enum AttributeKind.
	 */
	enum AttributeKind {
		/** The string. */
		string, /** The integer. */
		integer, /** The bool. */
		bool, /** The array of string. */
		arrayOfString, /** The list of string. */
		listOfString, /** The map of string of string. */
		mapOfStringOfString
	};

	/**
	 * Instantiates a new attribute value.
	 * 
	 * @param ce
	 *            the ce
	 */
	public AttributeValue(IConfigurationElement ce) {
		key = ce.getAttribute("name");
		assert key != null;

		AttributeKind type = AttributeKind.valueOf(ce.getAttribute("type"));

		switch (type) {
			case string: {
				String v = ce.getAttribute("value");
				assert v != null;
				this.value = v;
				break;
			}
			case integer: {
				String v = ce.getAttribute("value");
				assert v != null;
				this.value = Integer.valueOf(v);
				break;
			}
			case bool: {
				String v = ce.getAttribute("value");
				assert v != null;
				this.value = Boolean.valueOf(v);
				break;
			}
			case arrayOfString: {
				List<String> v_ar = getListOfString(ce);
				value = (String[]) v_ar.toArray(new String[v_ar.size()]);
				break;
			}
			case listOfString: {
				value = getListOfString(ce);
				break;
			}
			case mapOfStringOfString: {
				value = getMapOfStringOfString(ce);
				break;
			}
		}
	}

	/**
	 * Gets the list of string.
	 * 
	 * @param ce
	 *            the ce
	 * 
	 * @return the list of string
	 */
	static private List<String> getListOfString(IConfigurationElement ce) {
		IConfigurationElement[] v_ce = ce.getChildren("attribute-value");
		assert v_ce.length != 0;
		List<String> v_ar = new ArrayList<String>();
		for (int i = 0; i < v_ce.length; i++) {
			v_ar.add(v_ce[i].getAttribute("value"));
		}
		return v_ar;
	}

	/**
	 * Gets the map of string of string.
	 * 
	 * @param ce
	 *            the ce
	 * 
	 * @return the map of string of string
	 */
	static private Map<String, String> getMapOfStringOfString(IConfigurationElement ce) {
		IConfigurationElement[] v_ce = ce.getChildren("attribute-value");
		assert v_ce.length != 0;
		Map<String, String> v_ar = new HashMap<String, String>();
		for (int i = 0; i < v_ce.length; i++) {
			v_ar.put(v_ce[i].getAttribute("name"), v_ce[i].getAttribute("value"));
		}
		return v_ar;
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

}

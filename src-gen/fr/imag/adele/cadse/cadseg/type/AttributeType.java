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

package fr.imag.adele.cadse.cadseg.type;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.Platform;

/**
 * The Class AttributeType.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class AttributeType extends AbstractTypeImport {

	/** The Constant PLUGIN_ID. */
	private static final String	PLUGIN_ID	= "Model.Workspace.Workspace";

	/** The Constant PROVIDER. */
	private static final String	PROVIDER	= "type_attribute";

	/** The type java. */
	private String				typeJava;

	/** The default value. */
	String						defaultValue;

	/** The exp_to_string. */
	private String				exp_to_string;

	/** The exp_from_string. */
	private String				exp_from_string;

	/** The is primitive. */
	public boolean				isPrimitive;

	/**
	 * Instantiates a new attribute type.
	 * 
	 * @param ce
	 *            the ce
	 */
	public AttributeType(IConfigurationElement ce) {
		super(ce);
		this.typeJava = ce.getAttribute("typeJava");
		this.defaultValue = ce.getAttribute("defaultValue");
		;
		this.exp_from_string = ce.getAttribute("exp_from_string");
		;
		this.exp_to_string = ce.getAttribute("exp_to_string");
		;
		this.isPrimitive = Boolean.parseBoolean(ce.getAttribute("isPrimitive"));
	}

	/**
	 * Gets the type java.
	 * 
	 * @return the type java
	 */
	public String getTypeJava() {
		return typeJava;
	}

	/**
	 * Default value.
	 * 
	 * @return the string
	 */
	public String defaultValue() {
		return this.defaultValue;
	}

	/**
	 * Gets the exp_from_string.
	 * 
	 * @return the exp_from_string
	 */
	public String getExp_from_string() {
		return exp_from_string;
	}

	/**
	 * Gets the exp_to_string.
	 * 
	 * @return the exp_to_string
	 */
	public String getExp_to_string() {
		return exp_to_string;
	}

	/**
	 * Gets the default value.
	 * 
	 * @return the default value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Register provider.
	 */
	public static void registerProvider() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint point = registry.getExtensionPoint(PLUGIN_ID, PROVIDER);

		Platform.getExtensionRegistry().addRegistryChangeListener(new IRegistryChangeListener() {

			public void registryChanged(IRegistryChangeEvent event) {
				IExtensionDelta[] ed = event.getExtensionDeltas();
				for (int i = 0; i < ed.length; i++) {
					if (PROVIDER.equals(ed[i].getExtension().getExtensionPointUniqueIdentifier())) {
						System.out.println("AttributeType.registerProvider()" + ed[i]);
					}
				}
			}
		}, PLUGIN_ID);

		if (point == null)
			return;
		IExtension[] extensions = point.getExtensions();
		types = new HashMap<String, AttributeType>();
		for (int i = 0; i < extensions.length; i++) {
			IConfigurationElement[] ce = extensions[i].getConfigurationElements();
			for (int j = 0; j < ce.length; j++) {
				AttributeType at = new AttributeType(ce[j]);
				register(at);
			}
		}
	}

	/** The types. */
	static Map<String, AttributeType>	types	= null;

	/**
	 * Register.
	 * 
	 * @param at
	 *            the at
	 */
	private static void register(AttributeType at) {
		types.put(at.getName(), at);
	}

	/**
	 * Gets the types.
	 * 
	 * @return the types
	 */
	public static AttributeType[] getTypes() {
		if (types == null)
			registerProvider();
		return (AttributeType[]) types.values().toArray(new AttributeType[types.values().size()]);
	}

	/**
	 * Gets the type from string.
	 * 
	 * @param name
	 *            the name
	 * 
	 * @return the type from string
	 */
	public static AttributeType getTypeFromString(String name) {
		if (name == null)
			return null;
		if (types == null)
			registerProvider();

		return types.get(name);
	}
}
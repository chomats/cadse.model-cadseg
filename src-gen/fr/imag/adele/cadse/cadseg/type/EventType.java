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
 * The Class EventType.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class EventType extends AbstractTypeImport {

	/** The Constant PLUGIN_ID. */
	private static final String	PLUGIN_ID	= "Model.Workspace.Workspace";

	/** The Constant PROVIDER. */
	private static final String	PROVIDER	= "type_event";

	/** The method signature. */
	private String				methodSignature;

	/** The return type. */
	char[]						returnType;

	/** The method name. */
	char[]						methodName;

	/** The params_name. */
	char[][]					params_name;

	/** The params_type. */
	char[][]					params_type;

	/** The throws_class. */
	char[]						throws_class;

	/**
	 * Instantiates a new event type.
	 * 
	 * @param ce
	 *            the ce
	 */
	public EventType(IConfigurationElement ce) {
		super(ce);
		this.methodSignature = ce.getAttribute("method-signature");
	}

	/**
	 * Gets the method signature.
	 * 
	 * @return the method signature
	 */
	public String getMethodSignature() {
		return methodSignature;
	}

	/**
	 * Register provider.
	 */
	public static void registerProvider() {
		types = new HashMap<String, EventType>();
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

		for (int i = 0; i < extensions.length; i++) {
			IConfigurationElement[] ce = extensions[i].getConfigurationElements();
			for (int j = 0; j < ce.length; j++) {
				EventType at = new EventType(ce[j]);
				register(at);
			}
		}
	}

	/** The types. */
	static Map<String, EventType>	types	= new HashMap<String, EventType>();

	/**
	 * Register.
	 * 
	 * @param at
	 *            the at
	 */
	private static void register(EventType at) {
		types.put(at.getName(), at);
	}

	/**
	 * Gets the types.
	 * 
	 * @return the types
	 */
	public static EventType[] getTypes() {
		if (types == null)
			registerProvider();
		return (EventType[]) types.values().toArray(new EventType[types.values().size()]);
	}

	/**
	 * Gets the type from string.
	 * 
	 * @param name
	 *            the name
	 * 
	 * @return the type from string
	 */
	public static EventType getTypeFromString(String name) {
		if (name == null)
			return null;
		if (types == null)
			registerProvider();

		return types.get(name);
	}
}
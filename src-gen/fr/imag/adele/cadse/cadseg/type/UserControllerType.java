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
 * The Class UserControllerType.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class UserControllerType extends AEHasAttributeValue {

	/** The Constant PLUGIN_ID. */
	private static final String	PLUGIN_ID	= "Model.Workspace.Workspace";

	/** The Constant PROVIDER. */
	private static final String	PROVIDER	= "user_controller";

	/**
	 * Instantiates a new user controller type.
	 * 
	 * @param ce
	 *            the ce
	 */
	public UserControllerType(IConfigurationElement ce) {
		super(ce);
	}

	/**
	 * Gets the interface.
	 * 
	 * @return the interface
	 */
	public String getInterface() {
		String value = ce.getAttribute("interface");
		if (value == null) {
			UserControllerType superUI = (UserControllerType) getSuper();
			if (superUI != null)
				return superUI.getInterface();
		}
		return value;
	}

	/**
	 * Checks if is single.
	 * 
	 * @return true, if is single
	 */
	public boolean isSingle() {
		return "true".equals(ce.getAttribute("single"));
	}

	/**
	 * Checks if is list set.
	 * 
	 * @return true, if is list set
	 */
	public boolean isListSet() {
		UserControllerType superUI = (UserControllerType) getSuper();
		if (ce.getAttribute("list-set") == null && superUI != null)
			return superUI.isListSet();
		return "true".equals(ce.getAttribute("list-set"));
	}

	/**
	 * Checks if is list add and remove.
	 * 
	 * @return true, if is list add and remove
	 */
	public boolean isListAddAndRemove() {
		return "true".equals(ce.getAttribute("list-add-remove"));
	}

	/**
	 * Gets the model controller str.
	 * 
	 * @return the model controller str
	 */
	public String getModelControllerStr() {
		String model_controller = ce.getAttribute("model-controller");
		if (model_controller == null) {
			UserControllerType superUI = (UserControllerType) getSuper();
			if (superUI != null)
				return superUI.getModelControllerStr();
		}
		return model_controller;
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
		types = new HashMap<String, UserControllerType>();
		for (int i = 0; i < extensions.length; i++) {
			IConfigurationElement[] ce = extensions[i].getConfigurationElements();
			for (int j = 0; j < ce.length; j++) {
				UserControllerType at = new UserControllerType(ce[j]);
				register(at);
			}
		}
	}

	/** The types. */
	static Map<String, UserControllerType>	types	= null;

	/**
	 * Register.
	 * 
	 * @param at
	 *            the at
	 */
	private static void register(UserControllerType at) {
		types.put(at.getName(), at);
	}

	/**
	 * Gets the types.
	 * 
	 * @return the types
	 */
	public static UserControllerType[] getTypes() {
		return (UserControllerType[]) types.values().toArray(new UserControllerType[types.values().size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.type.AEHasAttributeValue#getTypeFromString(java.lang.String)
	 */
	@Override
	protected AEHasAttributeValue getTypeFromString(String superUI) {
		return getTypeFromString_(superUI);
	}

	/**
	 * Gets the type from string_.
	 * 
	 * @param name
	 *            the name
	 * 
	 * @return the type from string_
	 */
	public static UserControllerType getTypeFromString_(String name) {
		if (name == null)
			return null;
		if (types == null)
			registerProvider();

		return types.get(name);
	}
	//
	// @Override
	// public void completeField(IFieldDescription fd) throws CoreException {
	// super.completeField(fd);
	// Object o = createExecutable();
	// if (o!= null) fd.put(IFieldDescription.INTERACTION_CONTROLLER, o);
	//		
	// }
}

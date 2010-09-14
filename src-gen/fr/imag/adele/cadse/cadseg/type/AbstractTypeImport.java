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
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

/**
 * The Class AbstractTypeImport.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class AbstractTypeImport extends AEObject {

	/** The manifest_imports. */
	private String[]	manifest_imports;

	/** The java_imports. */
	private String[]	java_imports;

	/**
	 * Instantiates a new abstract type import.
	 * 
	 * @param ce
	 *            the ce
	 */
	public AbstractTypeImport(IConfigurationElement ce) {
		super(ce);

		List<String> ij = new ArrayList<String>();
		List<String> im = new ArrayList<String>();
		IConfigurationElement[] children = ce.getChildren();
		for (int i = 0; i < children.length; i++) {
			if ("import-java".equals(children[i].getName())) {
				ij.add(children[i].getAttribute("classname"));
			}
			if ("import-manifest".equals(children[i].getName())) {
				im.add(children[i].getAttribute("package"));
			}
		}
		manifest_imports = (String[]) im.toArray(new String[im.size()]);
		java_imports = (String[]) ij.toArray(new String[ij.size()]);
	}

	/**
	 * Gets the java imports.
	 * 
	 * @return the java imports
	 */
	public String[] getJavaImports() {
		return java_imports;
	}

	/**
	 * Gets the manifest imports.
	 * 
	 * @return the manifest imports
	 */
	public String[] getManifestImports() {
		return manifest_imports;
	}

}

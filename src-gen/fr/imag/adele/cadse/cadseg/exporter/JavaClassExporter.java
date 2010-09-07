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
package fr.imag.adele.cadse.cadseg.exporter;

import java.util.Set;

import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.build.Exporter;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
 * The Class JavaClassExporter.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class JavaClassExporter extends Exporter {

	/**
	 * Instantiates a new java class exporter.
	 * 
	 * @param contentItem
	 *            the content item
	 * @param exporterTypes
	 *            the exporter types
	 */
	protected JavaClassExporter(ContentItem contentItem, String... exporterTypes) {
		super(contentItem, exporterTypes);
	}

	

	/**
	 * Generate construstor arguments.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("contentItem, exporterTypes,");
	}

	/**
	 * Generate constructor parameter.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("ContentItem contentItem, String... exporterTypes,");
	}

	/**
	 * Generate call arguments.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 */
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, GenContext context) {
	}

}

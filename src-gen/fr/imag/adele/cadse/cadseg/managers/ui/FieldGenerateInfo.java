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
package fr.imag.adele.cadse.cadseg.managers.ui;

import fr.imag.adele.cadse.core.Item;

/**
 * The Class FieldGenerateInfo.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class FieldGenerateInfo {

	/** The field. */
	public Item		field;

	/** The ic. */
	public Item		ic;

	/** The mc. */
	public Item		mc;

	/** The field name. */
	public String	fieldName;

	/** The method name. */
	public String	methodName;

	/** The ui type name. */
	public String	uiTypeName;

	/** The default qualified class name. */
	public String	defaultQualifiedClassName;

	/** The extends ui. */
	public boolean	extendsUI;

	/**
	 * The super field.
	 */
	public Item		superField;

}

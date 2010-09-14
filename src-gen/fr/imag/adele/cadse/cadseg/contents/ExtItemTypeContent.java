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
package fr.imag.adele.cadse.cadseg.contents;

import java.util.UUID;

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.var.VariableImpl;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.Variable;

/**
 * The Class ExtItemTypeContent. implements IGenerateContent
 * 
 * @generated
 */
public class ExtItemTypeContent extends JavaFileContentManager  {
	static final class PackageNameVariable extends VariableImpl {

		public final static Variable	INSTANCE	= new PackageNameVariable();

		public String compute(ContextVariable context, Item itemCurrent) {
			try {
				return GenerateJavaIdentifier.getExtPackage(context, itemCurrent);
			} catch (Throwable e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	static final class ClassNameVariable extends VariableImpl {

		public final static Variable	INSTANCE	= new ClassNameVariable();

		public String compute(ContextVariable context, Item itemCurrent) {
			try {
				return GenerateJavaIdentifier.getExtClassName(context, itemCurrent);
			} catch (Throwable e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	/**
		@generated
	*/
	public ExtItemTypeContent(UUID id) throws CadseException {
		super(id, PackageNameVariable.INSTANCE, ClassNameVariable.INSTANCE);
	}
	
}

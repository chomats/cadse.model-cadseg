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
package fr.imag.adele.cadse.cadseg.managers.view.model;

import java.util.List;

import fr.imag.adele.cadse.core.Item;

/**
 * The Class ViewItemTypeModel.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ViewItemTypeModel {

	/** The is first. */
	public boolean					isFirst;

	/** The is ref. */
	public boolean					isRef;

	/** The item type. */
	public Item						itemType;

	/** The m. */
	public Item						m;

	/** The vit. */
	public Item						vit;

	/** The RE f_cst. */
	public String					REF_cst;

	/** The links. */
	public List<ViewLinkTypeModel>	links;

}

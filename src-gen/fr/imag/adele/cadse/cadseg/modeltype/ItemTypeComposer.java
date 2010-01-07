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

package fr.imag.adele.cadse.cadseg.modeltype;

import java.util.Collections;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.build.Composer;
import fr.imag.adele.cadse.core.build.IExportedContent;
import fr.imag.adele.cadse.core.build.IExporterTarget;

/**
 * The Class ItemTypeComposer.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ItemTypeComposer extends Composer implements IExporterTarget {

	/** The Constant BUILD_TYPE_MV. */
	private static final String	BUILD_TYPE_MV	= "MV";

	/** The Constant BUILD_TYPE_LT. */
	public static final String	BUILD_TYPE_LT	= "LT";

	/** The Constant BUILD_TYPE_AT. */
	public static final String	BUILD_TYPE_AT	= "AT";

	/** The Constant BUILD_TYPE_T. */
	public static final String	BUILD_TYPE_T	= "T";

	/** The Constant BUILD_TYPE_W. */
	public static final String	BUILD_TYPE_W	= "W";

	/** The Constant BUILD_TYPE_I. */
	public static final String	BUILD_TYPE_I	= "I";

	/**
	 * Instantiates a new item type composer.
	 * 
	 * @param contentManager
	 *            the content manager
	 */
	protected ItemTypeComposer(ContentItem contentManager) {
		super(contentManager, new String[] { BUILD_TYPE_AT, BUILD_TYPE_LT, BUILD_TYPE_MV });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.build.Composer#getTarget()
	 */
	@Override
	protected IExporterTarget getTarget() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.build.IExporterTarget#getRepositoryComponents(java.lang.String)
	 */
	public List<IExportedContent> getRepositoryComponents(String exporterType) throws CadseException {
		return Collections.emptyList();
	}

}

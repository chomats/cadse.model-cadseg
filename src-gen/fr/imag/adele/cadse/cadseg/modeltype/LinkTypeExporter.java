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

import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CLinkType;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.build.Exporter;
import fr.imag.adele.cadse.core.build.IBuildingContext;
import fr.imag.adele.cadse.core.build.IExportedContent;
import fr.imag.adele.cadse.core.build.IExporterTarget;

/**
 * The Class LinkTypeExporter.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class LinkTypeExporter extends Exporter {

	/**
	 * The Class LinkTypeExportedValue.
	 */
	static class LinkTypeExportedValue extends ExportedValue {

		/**
		 * Instantiates a new link type exported value.
		 * 
		 * @param linktype
		 *            the linktype
		 * @param cl
		 *            the cl
		 */
		public LinkTypeExportedValue(Item linktype, CLinkType cl) {
			super(ItemTypeComposer.BUILD_TYPE_LT, linktype, cl, null);
		}

	}

	/**
	 * Instantiates a new link type exporter.
	 * 
	 * @param contentManager
	 *            the content manager
	 */
	public LinkTypeExporter(ContentItem contentManager) {
		super(contentManager, new String[] { ItemTypeComposer.BUILD_TYPE_LT });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.build.Exporter#exportItem(fr.imag.adele.cadse.core.build.IBuildingContext,
	 *      fr.imag.adele.cadse.core.build.IExporterTarget, java.lang.String)
	 */
	@Override
	public IExportedContent exportItem(IBuildingContext context, IExporterTarget target, String exporterType)
			throws CadseException {

		CLinkType cl = exportLinkType(context);

		return new LinkTypeExportedValue(getItem(), cl);
	}

	/**
	 * Export link type.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return the c link type
	 */
	private CLinkType exportLinkType(IBuildingContext context) {
		CLinkType cl = new CLinkType();
		Item linkType = getItem();
		/*
		 * name="<%=linkType.getName()%>" min="<%=LinkManager.getMin(linkType)%>"
		 * max="<%=LinkManager.getMax(linkType)%>" destination="<%=itemTypeDest.getName()%>"
		 * isComposition="<%=LinkManager.isComposition(linkType)%>"
		 * isAggregation="<%=LinkManager.isAggregation(linkType)%>" isPart="<%=LinkManager.isPart(linkType)%>"
		 * isRequire="<%=LinkManager.isRequire(linkType)%>" isAssociation="<%=LinkManager.isAnnotation(linkType)%>"
		 * inverse-link="<%=LinkManager.getInverseLinkName(linkType)%>"
		 * selection-expression="<%=LinkManager.getSelectionExpression(linkType)%>"
		 */
		Item itemTypeDest = LinkManager.getDestination(linkType);
		if (itemTypeDest == null) {
			context.report("Cannot find the destination of the linktype {0}", linkType.getName());
			return null;
		}
		cl.setName(linkType.getName());
		cl.setMin(LinkManager.getMin(linkType));
		cl.setMax(LinkManager.getMax(linkType));
		cl.setDestination(itemTypeDest.getName());
		cl.setIsComposition(LinkManager.isComposition(linkType));
		cl.setIsAggregation(LinkManager.isAggregation(linkType));
		cl.setIsPart(LinkManager.isPart(linkType));
		cl.setIsRequire(LinkManager.isRequireAttribute(linkType));
		cl.setIsAnnotation(LinkManager.isAnnotation(linkType));
		cl.setInverseLink(LinkManager.getInverseLinkName(linkType));
		cl.setSelectionExpression(LinkManager.getSelectionExpression(linkType));
		return cl;
	}

}

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
package fr.imag.adele.cadse.cadseg.eclipse;

import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class CadseDefinitionJavaCompletionProposal.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class CadseDefinitionJavaCompletionProposal implements IJavaCompletionProposal {

	/** The qualified package. */
	String	qualifiedPackage;

	/** The addimport. */
	boolean	addimport;

	/** The type name. */
	String	typeName;

	/** The item source. */
	Item	itemSource;

	/**
	 * Instantiates a new cadse definition java completion proposal.
	 * 
	 * @param itemSource
	 *            the item source
	 * @param qualifiedPackage
	 *            the qualified package
	 * @param addimport
	 *            the addimport
	 */
	public CadseDefinitionJavaCompletionProposal(Item itemSource, String qualifiedPackage, boolean addimport) {
		super();
		this.qualifiedPackage = qualifiedPackage;
		this.itemSource = itemSource;
		this.addimport = addimport;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.ui.text.java.IJavaCompletionProposal#getRelevance()
	 */
	public int getRelevance() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getAdditionalProposalInfo()
	 */
	public String getAdditionalProposalInfo() {

		StringBuilder sb = new StringBuilder();
		sb.append("Add import package ");
		sb.append(qualifiedPackage).append(" in ");
		sb.append(itemSource.getQualifiedName());
		if (addimport) {
			sb.append("\n add import in source");
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getContextInformation()
	 */
	public IContextInformation getContextInformation() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getDisplayString()
	 */
	public String getDisplayString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Add import package ");
		sb.append(qualifiedPackage).append(" in ");
		sb.append(itemSource.getQualifiedName());
		if (addimport) {
			sb.append("\n add import in source");
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getImage()
	 */
	public Image getImage() {
		return WSPlugin.getDefault().getImageFrom(itemSource.getType(), itemSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getSelection(org.eclipse.jface.text.IDocument)
	 */
	public Point getSelection(IDocument document) {
		return new Point(0, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposal#apply(org.eclipse.jface.text.IDocument)
	 */
	public void apply(IDocument document) {
		CadseDefinitionManager.addImportsAttribute(itemSource, qualifiedPackage);
	}
}

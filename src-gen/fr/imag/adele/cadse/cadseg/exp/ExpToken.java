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

package fr.imag.adele.cadse.cadseg.exp;

import java.util.ArrayList;

/**
 * The Class AttributeToken.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ExpToken extends Token {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** The attributes name. */
	ArrayList<Token>	_tokens	= new ArrayList<Token>();

	/**
	 * Instantiates a new attribute token.
	 */
	public ExpToken() {
		this.kind = ExpressionParseConstants.ATTR_EXP;
		this.image = computeImage();
	}

	/**
	 * Adds the attribute.
	 * 
	 * @param t
	 *            the t
	 */
	public void add(Token t) {
		if (_tokens.size() == 0) {
			this.beginColumn = t.beginColumn;
			this.beginLine = t.beginLine;
		}
		this.endColumn = t.endColumn;
		this.endLine = t.endLine;
		this.next = t.next;
		_tokens.add(t);
		this.image = computeImage();
	}

	/**
	 * Gets the attribute names.
	 * 
	 * @return the attribute names
	 */
	public Token[] getAttributeNames() {
		return _tokens.toArray(new Token[_tokens.size()]);
	}

	/**
	 * Compute image.
	 * 
	 * @return the string
	 */
	public String computeImage() {
		StringBuilder sb = new StringBuilder();
		sb.append("< ");
		for (Token t : _tokens) {
			sb.append(ExpressionParseConstants.tokenImage[t.kind]).append(":");
			sb.append(t.image).append(" ");
		}

		sb.append(">");
		return sb.toString();
	}

	ArrayList<Token> tokens() {
		return _tokens;
	}

}

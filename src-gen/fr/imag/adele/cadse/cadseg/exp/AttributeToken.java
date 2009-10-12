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
import java.util.HashMap;
import java.util.Map;

/**
 * The Class AttributeToken.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class AttributeToken extends Token {

	/** The attributes name. */
	ArrayList<Token>	attributesName	= new ArrayList<Token>();
	Map<String, String>	options;

	/** The last. */
	Token				last			= null;

	/**
	 * Instantiates a new attribute token.
	 */
	public AttributeToken() {
		this.kind = ExpressionParseConstants.ATTR_QUALIFIED;
		this.image = computeImage();
	}

	/**
	 * Adds the attribute.
	 * 
	 * @param t
	 *            the t
	 */
	public void addAttribute(Token t) {

		switch (t.kind) {
			case ExpressionParseConstants.ATTR_PARENT_UNIQUE_NAME:
				convert(t, ExpressionParseConstants.PARENT, ExpressionParseConstants.ATTR_QUALIFIED_NAME);
				return;
			case ExpressionParseConstants.ATTR_PARENT_DISPLAY_NAME:
				convert(t, ExpressionParseConstants.PARENT, ExpressionParseConstants.ATTR_DISPLAY_NAME);
				return;
			case ExpressionParseConstants.ATTR_PARENT_SHORT_NAME:
				convert(t, ExpressionParseConstants.PARENT, ExpressionParseConstants.ATTR_NAME);
				return;
			case ExpressionParseConstants.ATTR_SHORT_NAME:
				convert(t, -1, ExpressionParseConstants.ATTR_NAME);
				return;
			case ExpressionParseConstants.ATTR_UNIQUE_NAME:
				convert(t, -1, ExpressionParseConstants.ATTR_QUALIFIED_NAME);
				return;
			case ExpressionParseConstants.ATTR_PARENT_TYPE_NAME:
				convert(t, ExpressionParseConstants.PARENT, ExpressionParseConstants.ATTR_TYPE_NAME);
				return;
			case ExpressionParseConstants.ATTR_PARENT_ID:
				convert(t, ExpressionParseConstants.PARENT, ExpressionParseConstants.ATTR_ID);
				return;
		}

		if (attributesName.size() == 0) {
			this.beginColumn = t.beginColumn;
			this.beginLine = t.beginLine;
		}
		this.endColumn = t.endColumn;
		this.endLine = t.endLine;
		this.next = t.next;
		attributesName.add(t);
		if (last != null && last.kind == ExpressionParseConstants.ATTR) {
			last.kind = ExpressionParseConstants.ATTR_LINK;
		}
		last = t;
		this.image = computeImage();
	}

	private void convert(Token t, int kindParent, int kindAtt) {
		Token pt;
		if (kindParent != -1) {
			pt = new Token();
			pt.beginColumn = t.beginColumn;
			pt.beginLine = t.beginLine;
			pt.endColumn = t.endColumn;
			pt.endLine = t.endLine;
			pt.image = ExpressionParseConstants.tokenImage[kindParent];
			pt.kind = kindParent;
			addAttribute(pt);
		}
		pt = new Token();
		pt.beginColumn = t.beginColumn;
		pt.beginLine = t.beginLine;
		pt.endColumn = t.endColumn;
		pt.endLine = t.endLine;
		pt.image = ExpressionParseConstants.tokenImage[kindAtt];
		pt.kind = kindAtt;
		addAttribute(pt);
	}

	/**
	 * Checks for attr link.
	 * 
	 * @return true, if successful
	 */
	public boolean hasAttrLink() {
		return attributesName.size() >= 2;
	}

	/**
	 * Gets the attribute names.
	 * 
	 * @return the attribute names
	 */
	public Token[] getAttributeNames() {
		return attributesName.toArray(new Token[attributesName.size()]);
	}

	/**
	 * Compute image.
	 * 
	 * @return the string
	 */
	public String computeImage() {
		StringBuilder sb = new StringBuilder();
		sb.append("< ");
		for (Token t : attributesName) {
			sb.append(ExpressionParseConstants.tokenImage[t.kind]).append(":");
			sb.append(t.image).append(" ");
		}

		sb.append(">");
		return sb.toString();
	}

	public void putOption(String key, String value) {
		if (options == null) {
			options = new HashMap<String, String>();
		}
		options.put(key, value);
	}

	public String getOption(String key, String defaultValue) {
		if (options == null) {
			return defaultValue;
		}
		String ret = options.get(key);
		return ret == null ? defaultValue : ret;
	}

}

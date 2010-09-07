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
package fr.imag.adele.cadse.cadseg.util;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.attribute.IAttributeType;

/**
 * The Class Util.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class Util {

	/**
	 * Sets the default value if need.
	 * 
	 * @param item
	 *            the item
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 */
	public static <T> void setDefaultValueIfNeed(Item item, IAttributeType<T> key, T defaultValue) {
		if (item.getAttribute(key) == null)
			try {
				item.setAttribute(key, defaultValue);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * Replaces unprintable characters by their espaced (or unicode escaped)
	 * equivalents in the given string.
	 * 
	 * @param str
	 *            the str
	 * 
	 * @return the string
	 */
	public static final String addEscapes(String str) {
		StringBuilder sb = new StringBuilder();
		char ch;
		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
				case 0:
					continue;
				case '\b':
					sb.append("\\b");
					continue;
				case '\t':
					sb.append("\\t");
					continue;
				case '\n':
					sb.append("\\n");
					continue;
				case '\f':
					sb.append("\\f");
					continue;
				case '\r':
					sb.append("\\r");
					continue;
				case '\"':
					sb.append("\\\"");
					continue;
				case '\'':
					sb.append("\\\'");
					continue;
				case '\\':
					sb.append("\\\\");
					continue;
				default:
					if ((ch = str.charAt(i)) < 0x20 || ch > 0x7e) {
						String s = "0000" + Integer.toString(ch, 16);
						sb.append("\\u" + s.substring(s.length() - 4, s.length()));
					} else {
						sb.append(ch);
					}
					continue;
			}
		}
		return sb.toString();
	}

	/**
	 * Escape xml.
	 * 
	 * @param inbuf
	 *            the inbuf
	 * 
	 * @return the string
	 */
	public static String escapeXML(String inbuf) {
		return escapeXML(inbuf.toCharArray(), 0, inbuf.length(), true);
	}

	/**
	 * Escape xml.
	 * 
	 * @param inbuf
	 *            the inbuf
	 * @param isAttValue
	 *            the is att value
	 * 
	 * @return the string
	 */
	public static String escapeXML(String inbuf, boolean isAttValue) {
		return escapeXML(inbuf.toCharArray(), 0, inbuf.length(), isAttValue);
	}

	/**
	 * Escape xml.
	 * 
	 * @param inbuf
	 *            the inbuf
	 * @param start
	 *            the start
	 * @param len
	 *            the len
	 * @param isAttValue
	 *            the is att value
	 * 
	 * @return the string
	 */
	public static String escapeXML(char[] inbuf, int start, int len, boolean isAttValue) {

		StringBuffer buf = new StringBuffer();
		for (int i = start; i < start + len; i++) {
			char ch = inbuf[i];

			// you are supposed to do the standard XML character escapes
			// like & ... &amp; < ... &lt; etc

			if (ch == '&') {
				buf.append("&amp;");
				continue;
			}
			if (ch == '<') {
				buf.append("&lt;");
				continue;
			}
			if (ch == '>') {
				buf.append("&gt;");
				continue;
			}

			if (ch == '\r') {
				buf.append("&#13;");
				continue;
			}

			if (ch == '"' && isAttValue) {
				// isAttValue is set to true when the marshaller is processing
				// attribute values. Inside attribute values, there are more
				// things you need to escape, usually.
				buf.append("&quot;");
				continue;
			}
			if (ch == '\'' && isAttValue) {
				buf.append("&apos;");
				continue;
			}

			// you should handle other characters like < or >

			if (ch > 0x7F) {
				// escape everything above ASCII to &#xXXXX;
				buf.append("&#x");
				buf.append(Integer.toHexString(ch));
				buf.append(";");
				continue;
			}

			// otherwise print normally
			buf.append(ch);
		}
		return buf.toString();
	}
}

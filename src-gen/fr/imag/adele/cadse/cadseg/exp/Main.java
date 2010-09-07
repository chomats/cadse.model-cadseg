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
package fr.imag.adele.cadse.cadseg.exp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * The Class Main.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class Main {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			try {
				System.out.print(":>");
				String line = br.readLine();
				if (line == null || line.length() == 0)
					return;
				ExpressionParseTokenManager source = new ExpressionParseTokenManager(new SimpleCharStream(
						new StringReader(line)));
				while (true) {
					Token t = source.getNextToken();
					print(t);
					if (t.kind == ExpressionParseConstants.EOF)
						break;
				}
				ExpressionParse ep = new ExpressionParse(new StringReader(line)) {

					@Override
					protected void parseBEGIN() {
						print("BEGIN");
					}

					@Override
					protected void parseEND(ExpToken e) {
						print(exp);

						print("END");
					}

				};
				ep.main();
			} catch (Throwable e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

	/**
	 * Prints the.
	 * 
	 * @param string
	 *            the string
	 * @param attr
	 *            the attr
	 */
	protected static void print(String string, String attr) {
		System.out.println(string + ": " + attr);

	}

	/**
	 * Prints the.
	 * 
	 * @param string
	 *            the string
	 */
	protected static void print(String string) {
		System.out.println(string);
	}

	/**
	 * Prints the.
	 * 
	 * @param t
	 *            the t
	 */
	private static void print(Token t) {
		System.out.println(ExpressionParseConstants.tokenImage[t.kind] + ": " + t.image);
	}
}

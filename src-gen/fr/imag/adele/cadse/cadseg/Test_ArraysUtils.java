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

package fr.imag.adele.cadse.cadseg;

import fr.imag.adele.cadse.core.util.ArraysUtil;

/**
 * The Class Test_ArraysUtils.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class Test_ArraysUtils {

	/** The test. */
	static String[][]	test	= new String[][] { {}, { "a" }, { "a", "b" }, { "a", "b", "c" },
			{ "a", "b", "c", "d" }, { "a", "b", "c", "d", "e" } };

	/** The test_r. */
	static String[][]	test_r	= new String[][] { {}, { "a" }, { "b", "a" }, { "c", "b", "a" },
			{ "d", "c", "b", "a" }, { "e", "d", "c", "b", "a" } };

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		for (int i = 0; i < test.length; i++) {
			test(i, test[i], test_r[i]);
		}

	}

	/**
	 * Test.
	 * 
	 * @param i
	 *            the i
	 * @param t
	 *            the t
	 * @param r
	 *            the r
	 */
	private static void test(int i, String[] t, String[] r) {
		System.out.print("Test " + i + " : ");
		for (String pt : t) {
			System.out.print(pt);
			System.out.print(", ");
		}
		ArraysUtil.inverser(t);
		if (equals(t, r))
			System.out.println(" OK");
		else
			System.out.println(" ERROR");
	}

	/**
	 * Equals.
	 * 
	 * @param t
	 *            the t
	 * @param r
	 *            the r
	 * 
	 * @return true, if successful
	 */
	private static boolean equals(String[] t, String[] r) {
		for (int i = 0; i < t.length; i++) {
			if (t[i].equals(r[i]))
				continue;
			return false;
		}
		return true;
	}

}

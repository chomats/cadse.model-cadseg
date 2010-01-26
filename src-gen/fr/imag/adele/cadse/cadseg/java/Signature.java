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

package fr.imag.adele.cadse.cadseg.java;

/**
 * The Class Signature.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class Signature {

	/**
	 * The Class SignatureError.
	 */
	static class SignatureError extends IllegalArgumentException {

		/**
		 * Instantiates a new signature error.
		 * 
		 * @param message
		 *            the message
		 * @param s
		 *            the s
		 */
		public SignatureError(String message, Signature s) {
			super(append(message, s));
		}

		/**
		 * Append.
		 * 
		 * @param message
		 *            the message
		 * @param s
		 *            the s
		 * 
		 * @return the string
		 */
		private static String append(String message, Signature s) {
			StringBuilder sb = new StringBuilder();
			sb.append(message);
			sb.append("\n\n").append(s.methodSignature);
			sb.append("\n");
			for (int i = 0; i < s.index; i++) {
				sb.append(" ");
			}
			sb.append("^");
			// TODO Auto-generated method stub
			return sb.toString();
		}

		/** The Constant serialVersionUID. */
		private static final long	serialVersionUID	= 1L;

	}

	/** The Constant NO_CHAR_CHAR. */
	private static final char[][]	NO_CHAR_CHAR	= new char[0][];

	// static char PARAN_OPEN = '(';
	//
	// static char PARAN_CLOSE = ')';
	//
	// static char PARAM_VIRG = ',';
	//
	// static char[] THROWS = { 't', 'h', 'r', 'o', 'w', 's' };
	//
	// static char[] ARG_NAME = { ' ' };

	/** The index. */
	int								index;

	/** The method signature. */
	char[]							methodSignature;

	/**
	 * parse the next java id from start index.
	 * 
	 * @param accept_point
	 *            the accept_point
	 * @param test
	 *            the test
	 * 
	 * @return non null and non empty char array wich the id.
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	private char[] parse_id(boolean accept_point, boolean test) throws IllegalArgumentException {
		assert methodSignature != null;

		skipspace();

		assert index >= 0 && index < methodSignature.length;

		int start = index;
		char c = methodSignature[index];
		if (!Character.isJavaIdentifierStart(c)) {
			throw new SignatureError("not javaidentifier start", this);
		}
		int i = index + 1;
		for (; i < methodSignature.length; i++) {
			c = methodSignature[i];
			if (accept_point && c == '.') {
				if (i + 1 == methodSignature.length) {
					index = i + 1;
					throw new SignatureError("not javaidentifier start", this);
				}
				if (!Character.isJavaIdentifierStart(methodSignature[i + 1])) {
					index = i + 1;
					throw new SignatureError("not javaidentifier start", this);
				}
				continue;
			}
			if (!Character.isJavaIdentifierPart(c)) {
				break;
			}
		}
		index = i;

		if (test)
			return null;

		char[] v = new char[i - start];
		System.arraycopy(methodSignature, start, v, 0, v.length);

		return v;
	}

	/**
	 * Skipspace.
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	private void skipspace() throws IllegalArgumentException {
		assert methodSignature != null;
		if (index == methodSignature.length)
			return;

		int i = index;
		char c = ' ';
		for (; i < methodSignature.length; i++) {
			c = methodSignature[i];
			if (c != ' ')
				break;
		}
		index = i;
	}

	/**
	 * Test.
	 * 
	 * @param c
	 *            the c
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	private void test(char c) throws IllegalArgumentException {
		skipspace();
		assert index >= 0 && index < methodSignature.length;
		if (c != methodSignature[index]) {
			throw new SignatureError("not javaidentifier start", this);
		}
		index++;
	}

	/**
	 * Testif.
	 * 
	 * @param c
	 *            the c
	 * 
	 * @return true, if successful
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	private boolean testif(char c) throws IllegalArgumentException {
		skipspace();
		if (index == methodSignature.length)
			return false;
		assert index >= 0 && index < methodSignature.length;
		if (c == methodSignature[index]) {
			index++;
			return true;
		}
		return false;
	}

	/**
	 * Parses the signature.
	 * 
	 * @param methodSignature
	 *            the method signature
	 * 
	 * @return the object[]
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public Object[] parseSignature(String methodSignature) throws IllegalArgumentException {
		return parseSignature(methodSignature.toCharArray());
	}

	/**
	 * <char[],char[], char[][], char[][], char[][]>.
	 * 
	 * @param methodSignature
	 *            the method signature
	 * 
	 * @return the object[]
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public Object[] parseSignature(char[] methodSignature) throws IllegalArgumentException {
		// skip type parameters

		index = 0;

		this.methodSignature = methodSignature;
		char[] return_type = parse_id(true, false);
		char[] method_name = parse_id(false, false);
		char[][] param_name = NO_CHAR_CHAR;
		char[][] param_type = NO_CHAR_CHAR;
		char[][] throw_class = NO_CHAR_CHAR;

		test('(');
		if (!testif(')')) {
			int save_index = index;
			int count_param = 0;
			for (; index < methodSignature.length;) {

				parse_id(true, true);
				parse_id(false, true);
				count_param++;
				if (testif(')'))
					break;
				test(',');
				if (index + 1 == methodSignature.length)
					throw new SignatureError("not javaidentifier start", this);
			}
			index = save_index;
			param_name = new char[count_param][];
			param_type = new char[count_param][];
			count_param = 0;
			for (; index < methodSignature.length;) {

				param_type[count_param] = parse_id(true, false);
				param_name[count_param] = parse_id(false, false);
				count_param++;
				if (testif(')'))
					break;
				test(',');
				if (index + 1 == methodSignature.length)
					throw new SignatureError("not javaidentifier start", this);
			}
		}

		if (testif('t')) {
			if (methodSignature.length - index < 6)
				throw new SignatureError("not javaidentifier start", this);
			char c = methodSignature[index];
			if (c != 'h')
				throw new SignatureError("not javaidentifier start", this);
			c = methodSignature[++index];
			if (c != 'r')
				throw new SignatureError("not javaidentifier start", this);
			c = methodSignature[++index];
			if (c != 'o')
				throw new SignatureError("not javaidentifier start", this);
			c = methodSignature[++index];
			if (c != 'w')
				throw new SignatureError("not javaidentifier start", this);
			c = methodSignature[++index];
			if (c != 's')
				throw new SignatureError("not javaidentifier start", this);
			c = methodSignature[++index];
			if (c != ' ')
				throw new SignatureError("not javaidentifier start", this);

			int save_index = index;
			parse_id(true, true);
			int count_throw = 1;
			while (testif(',')) {
				parse_id(true, true);
				count_throw++;
			}

			index = save_index;
			throw_class = new char[count_throw][];
			throw_class[0] = parse_id(true, false);
			count_throw = 1;
			while (testif(',')) {
				throw_class[count_throw] = parse_id(true, false);
				count_throw++;
			}
		}
		skipspace();
		if (this.methodSignature.length != index)
			throw new SignatureError("bad end", this);

		return new Object[] { return_type, method_name, param_type, param_name, throw_class };
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Signature s = new Signature();

		System.out.println(toString(s.parseSignature("void beforeDeleteItem(Item item) throws MelusineError")));
		System.out.println(toString(s.parseSignature("void beforeDeleteItem() throws MelusineError")));
		System.out.println(toString(s.parseSignature("void beforeDeleteItem(Item item)")));
		System.out
				.println(toString(s
						.parseSignature("java.lang.String beforeDeleteItem(Item item, int a) throws MelusineError, IllegalArgumentException")));

		try {
			System.out
					.println(toString(s
							.parseSignature(".lang.String beforeDeleteItem(Item item, int a) throws MelusineError, IllegalArgumentException")));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out
					.println(toString(s
							.parseSignature("a.lang..String beforeDeleteItem(Item item, int a) throws MelusineError, IllegalArgumentException")));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out
					.println(toString(s
							.parseSignature("a.lang.String. beforeDeleteItem(Item item, int a) throws MelusineError, IllegalArgumentException")));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out
					.println(toString(s
							.parseSignature("a.lang.String.z beforeDele teItem(Item item, int a) throws MelusineError, IllegalArgumentException")));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out
					.println(toString(s
							.parseSignature("a.lang.String.z beforeDeleteItem(Item item , a , int a) throws MelusineError, IllegalArgumentException")));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out
					.println(toString(s
							.parseSignature("a.lang.String.z beforeDeleteItem(Item item ,  , int a) throws MelusineError, IllegalArgumentException")));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out
					.println(toString(s
							.parseSignature("a.lang.String.z beforeDeleteItem(Item item  ) , int a) throws MelusineError, IllegalArgumentException")));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * To string.
	 * 
	 * @param objects
	 *            the objects
	 * 
	 * @return the string
	 */
	private static String toString(Object[] objects) {
		StringBuilder sb = new StringBuilder();
		sb.append((char[]) objects[0]);
		sb.append(' ');
		sb.append((char[]) objects[1]);
		sb.append('(');
		char[][] param_type = (char[][]) objects[2];
		char[][] param_name = (char[][]) objects[3];
		for (int i = 0; i < param_name.length; i++) {
			if (i != 0)
				sb.append(", ");
			sb.append(param_type[i]);
			sb.append(' ');
			sb.append(param_name[i]);
		}
		sb.append(')');
		char[][] throws_def = (char[][]) objects[4];
		if (throws_def.length != 0) {
			sb.append(" throws ");
			for (int i = 0; i < throws_def.length;) {
				if (i != 0)
					sb.append(", ");
				sb.append(throws_def[i++]);
			}
		}
		return sb.toString();
	}

	// 
}

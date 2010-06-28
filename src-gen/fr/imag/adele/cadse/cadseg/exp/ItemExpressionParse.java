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

import java.io.StringReader;

import fr.imag.adele.cadse.core.Item;

/**
 * The Class ItemExpressionParse.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ItemExpressionParse extends ExpressionParse {

	/** The current item. */
	protected Item		_currentItem;

	/** The orignal item. */
	protected Item		_orignalItem;

	/** The current attr. */
	protected Item		_currentAttr;

	/** The exp. */
	protected String	_expString;

	/**
	 * Instantiates a new item expression parse.
	 * 
	 * @param currentItem
	 *            the current item
	 * @param exp
	 *            the exp
	 */
	public ItemExpressionParse(Item currentItem, String exp) {
		super(new StringReader(exp));
		this._expString = exp;
		assert currentItem != null;
		this._orignalItem = currentItem;
		this._currentItem = currentItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadseg.exp.ExpressionParse#parseBEGIN()
	 */
	@Override
	protected void parseBEGIN() {
	}

	@Override
	protected void parseEND(ExpToken e) {
	}

}

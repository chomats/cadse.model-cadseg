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

import java.util.Set;

import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.exp.AttributeToken;
import fr.imag.adele.cadse.cadseg.exp.ExpressionParseConstants;
import fr.imag.adele.cadse.cadseg.exp.ItemExpressionParse;
import fr.imag.adele.cadse.cadseg.exp.Token;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Validator.ProblemReporter;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

/**
 * The Class ParseTemplate.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ParseTemplate extends ItemExpressionParse implements ExpressionParseConstants {

	/** The short name var. */
	final String		shortNameVar;

	/** The has lt. */
	private boolean		hasLT	= true;

	/** The reset. */
	protected boolean	reset	= false;

	/** The current link. */
	private Item		fCurrentLink;

	/**
	 * The Class BuildJava.
	 */
	protected class BuildJava {

		/** The sb. */
		final GenStringBuilder	sb;

		/** The imports. */
		final Set<String>		imports;
		// private boolean display;
		/** The return inst. */
		final private boolean	returnInst;

		/** The stringbuilder var name. */
		final String			stringbuilderVarName;

		/** The item var. */
		final String			itemVar;

		/** The parent var. */
		final String			parentVar;
		
		/** The parent var. */
		String			currentParentVar;

		/** The current item var. */
		private String			fCurrentItemVar;

		/** The useif. */
		private int				useif;

		/** The cxt. */
		private ContextVariable	cxt;

		/** The context variable. */
		boolean					contextVariable	= false;

		private String			_orinalPackageName;

		/**
		 * Instantiates a new builds the java.
		 * 
		 * @param itemVar
		 *            the item var
		 * @param varName
		 *            the var name
		 * @param sb
		 *            the sb
		 * @param imports
		 *            the imports
		 * @param parentVar
		 *            the parent var
		 * @param returnInst
		 *            the return inst
		 * @param usecontext
		 *            the usecontext
		 * @param packageName
		 */
		public BuildJava(String itemVar, String varName, GenStringBuilder sb, Set<String> imports, String parentVar,
				boolean returnInst, boolean usecontext, String packageName) {
			this.sb = sb;
			this.imports = imports;
			this.stringbuilderVarName = varName;
			this.returnInst = returnInst;
			this.itemVar = itemVar;
			this.currentParentVar = this.parentVar = parentVar;
			this.cxt = new ContextVariableImpl();
			this.contextVariable = usecontext;

			if (packageName == null) {
				this._orinalPackageName = GenerateJavaIdentifier.getManagerPackage(cxt, (ItemType) _orignalItem, ItemTypeManager
						.getManager(_orignalItem));
			} else {
				this._orinalPackageName = packageName;
			}

		}

		/**
		 * Preexp.
		 * 
		 * @param t
		 *            the t
		 */
		protected void preexp(Token t, int i) {
			switch (t.kind) {
				case ExpressionParseConstants.CST_SEP:
					useif++;
					sb.newline().append("if (").append(stringbuilderVarName).append(".length() != 0) {");
					sb.begin();
					break;
					
				case ExpressionParseConstants.ATTR_NAME:
					if (i == 0 && shortNameVar != null)
						break;
				case ExpressionParseConstants.ATTR_TYPE_NAME:
				case ExpressionParseConstants.ATTR_QUALIFIED_NAME:
					if (!contextVariable) {
						useif++;
						sb.newline().append("if (").append(fCurrentItemVar).append(" != null) {");
						sb.begin();
					}
					break;
				
				case ExpressionParseConstants.ATTR_LINK: {

					ItemType linkItemType = (ItemType) fCurrentLink.getPartParent();
					
					String qClassName = ItemTypeManager.getManagerClass(linkItemType, null, null);
					String className =  JavaIdentifier.getlastclassName(qClassName);
					String packageName = JavaIdentifier.getPackageName(qClassName);
					
					if (!packageName.equals(_orinalPackageName)) {
						imports.add(packageName + "." + className);
					}
					useif++;
					sb.newline().append("if (").append(fCurrentItemVar).append(" != null) {");
					sb.begin();
					sb.newline().append("currentItem = ").append(className).append(".").append(
							GenerateJavaIdentifier.cstGetAttribute(cxt, fCurrentLink)).append("(").append(
							fCurrentItemVar).append(");");
					fCurrentItemVar = "currentItem";
					break;
				}
				case ExpressionParseConstants.ATTR: {
					if (!contextVariable) {
						ItemType attrItemType = (ItemType) _currentAttr.getPartParent();
						
						String qClassName = ItemTypeManager.getManagerClass(attrItemType, null, null);
						String className =  JavaIdentifier.getlastclassName(qClassName);
						String packageName = JavaIdentifier.getPackageName(qClassName);

						if (!packageName.equals(_orinalPackageName)) {
							imports.add(packageName + "." + className);
						}
						String cst = GenerateJavaIdentifier.cstGetAttribute(cxt, _currentAttr);

						useif++;
						sb.newline().append("if (").append(fCurrentItemVar).append(" != null) {");
						sb.begin();

						sb.newline().append("value = ").append(className).append(".").append(cst).append("(").append(
								fCurrentItemVar);
						sb.append(");");
					}

					break;
				}
				case ExpressionParseConstants.PARENT:
					if (currentParentVar != null) {
						fCurrentItemVar = currentParentVar;
						currentParentVar = null;
					} else {
						useif++;
						sb.newline().append("if (").append(fCurrentItemVar).append(" != null) {");
						sb.begin();
						sb.newline().append("currentItem = ").append(fCurrentItemVar).append(".getPartParent();");
						fCurrentItemVar = "currentItem";
					}
					break;
				default:
					break;
			}
		}

		/**
		 * Exp.
		 * 
		 * @param t
		 *            the t
		 * 
		 * @return true, if successful
		 */
		protected boolean exp(Token t, int i) {
			switch (t.kind) {

				case ExpressionParseConstants.ATTR_ID:
					sb.append(fCurrentItemVar).append(".getId()");
					return true;

				case ExpressionParseConstants.ATTR_DISPLAY_NAME:
					sb.append(fCurrentItemVar).append(".getDisplayName()");
					return true;

				case ExpressionParseConstants.ATTR_LINK_TYPE_NAME:
					sb.append("lt.getName()");
					return true;

				case ExpressionParseConstants.ATTR_QUALIFIED_NAME:
					if (contextVariable) {
						sb.append("context.getQualifiedName(").append(fCurrentItemVar).append(")");
					} else {
						sb.append(fCurrentItemVar).append(".getQualifiedName()");
					}
					return true;

				case ExpressionParseConstants.CST_SEP:
				case ExpressionParseConstants.CST:
					sb.append("\"").append(addEscapes(t.image)).append("\"");
					return true;

				case ExpressionParseConstants.ATTR_NAME:
					if (i == 0) {
						if (shortNameVar == null) {
							if (contextVariable) {
								sb.append("context.getName(").append(fCurrentItemVar).append(")");
							} else {
								sb.append(fCurrentItemVar).append(".getName()");
							}
						} else {
							sb.append(shortNameVar);
						}
					} else {
						if (contextVariable) {
							sb.append("context.getName(").append(fCurrentItemVar).append(")");
						} else {
							sb.append(fCurrentItemVar).append(".getName()");
						}
					}

					return true;

				case ExpressionParseConstants.ATTR_TYPE_NAME:
					sb.append(fCurrentItemVar).append(".getType().getName()");
					return true;

				case ExpressionParseConstants.ATTR:
					if (contextVariable) {
						sb.append("context.getValue(").append(fCurrentItemVar).append(",").append(
								GenerateJavaIdentifier.cstQualifiedAttribute(cxt, _currentAttr, null, null, imports))
								.append("_)");
					} else {
						sb.newline().append("String.valueOf(value)");
					}
					return true;
			}
			return false;
		}

		/* return true if use variable value of type Object */
		/**
		 * Use object.
		 * 
		 * @return true, if successful
		 */
		protected boolean useObject() {
			for (Token t : tokens()) {
				switch (t.kind) {
					case ExpressionParseConstants.ATTR_QUALIFIED:
						return true;

					default:
						break;
				}
			}
			return false;
		}

		/* return true if use variable currentItem of type Item */
		/**
		 * Use current item.
		 * 
		 * @return true, if successful
		 */
		protected boolean useCurrentItem() {
			for (Token t : tokens()) {
				switch (t.kind) {
					case ExpressionParseConstants.ATTR_QUALIFIED:
						if (((AttributeToken) t).hasAttrLink()) {
							return true;
						}
					default:
						break;
				}
			}
			return false;
		}

		/**
		 * Builds the.
		 */
		protected void build() {
			useif = 0;
			if (!(tokens().size() == 1 && returnInst)) {
				sb.newline().append("StringBuilder ").append(stringbuilderVarName).append(" = new StringBuilder();");
			}

			sb.newline().append("try {");
			sb.begin();
			if (useObject()) {
				sb.newline().append("Object value;");
			}
			if (useCurrentItem()) {
				imports.add("fr.imag.adele.cadse.core.Item");
				sb.newline().append("Item currentItem;");
			}
			ONE: {
				if (returnInst) {
					// Not tokens : return empty string
					if (tokens().size() == 0) {
						generateReturnNoTokens();
						break ONE;
					}
					if (tokens().size() == 1) {
						fCurrentItemVar = itemVar;
						Token t = tokens().get(0);
						if (t.kind == ExpressionParseConstants.ATTR_QUALIFIED) {
							AttributeToken at = (AttributeToken) t;
							Token[] attributesName = at.getAttributeNames();
							for (int i = 0; i < attributesName.length; i++) {
								if (isValid(attributesName[i])) {
									preexp(attributesName[i], attributesName.length - 1);
									if (i == attributesName.length - 1) {
										sb.newline().append("return ");
										exp(attributesName[i], i);
										sb.append(";");
									}
								} else {
									sb.newline().append("return \"error\";");
									break;
								}
							}

							for (int i = useif; i > 0; i--) {
								sb.end();
								sb.newline().append("}");
							}
							if (useif != 0) {
								generateReturnNull();
							}
						} else {
							if (isValid(t)) {
								preexp(t, 0);
								sb.newline().append("return ");
								exp(t, 0);
								sb.append(";");
								for (int i = useif; i > 0; i--) {
									sb.end();
									sb.newline().append("}");
								}
							} else {
								sb.newline().append("return \"error\";");
							}
						}
						break ONE;
					}
				}

				fCurrentItemVar = itemVar;

				TWO: for (Token t : tokens()) {
					if (t.kind == ExpressionParseConstants.ATTR_QUALIFIED) {
						AttributeToken at = (AttributeToken) t;
						Token[] attributesName = at.getAttributeNames();
						for (int i = 0; i < attributesName.length; i++) {
							if (!isValid(attributesName[i])) {
								continue TWO;
							}
							preexp(attributesName[i], attributesName.length - 1);
							if (i == attributesName.length - 1) {
								sb.newline().append(stringbuilderVarName).append(".append(");
								exp(attributesName[i], i);
								sb.append(");");
							}
						}
						for (int i = useif; i > 0; i--) {
							sb.end();
							sb.newline().append("}");
						}
					} else {
						if (!isValid(t)) {
							continue TWO;
						}

						preexp(t, 0);
						sb.newline().append(stringbuilderVarName).append(".append(");
						exp(t, 0);
						sb.append(");");
						for (int i = useif; i > 0; i--) {
							sb.end();
							sb.newline().append("}");
						}
					}
					// reset
					_currentItem = _orignalItem;
					fCurrentItemVar = itemVar;
					currentParentVar = parentVar;
					useif = 0;
				}
				if (returnInst) {
					sb.newline().append("return ").append(stringbuilderVarName).append(".toString();");
				}
			}
			sb.end();
			sb.newline().append("} catch (Throwable e) {");
			sb.begin();
			sb.newline().append("e.printStackTrace();");

			if (returnInst) {
				sb.newline().append("return \"error\";");
			}
			sb.end();
			sb.newline().append("}");
		}

		/**
		 * Generate return no tokens.
		 */
		protected void generateReturnNoTokens() {
			sb.newline().append("return \"\";");
		}

		/**
		 * Generate return null.
		 */
		protected void generateReturnNull() {
			sb.newline().append("return \"\";");
		}
	}

	/**
	 * Validate.
	 * 
	 * @param reporter
	 *            the reporter
	 * @param itemforreporter
	 *            the itemforreporter
	 */
	public void validate(ProblemReporter reporter, Item itemforreporter) {
		for (Token t : tokens()) {
			if (reset) {
				_currentItem = _orignalItem;
				reset = false;
			}
			validate(reporter, itemforreporter, t);

		}
	}

	/**
	 * Instantiates a new parses the template.
	 * 
	 * @param item
	 *            the item
	 * @param exp
	 *            the exp
	 */
	public ParseTemplate(Item item, String exp) {
		this(item, exp, "name");
	}

	/**
	 * Instantiates a new parses the template.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * @param exp
	 *            the exp
	 * @param shortNameVar
	 *            the short name var
	 */
	public ParseTemplate(Item itemtype, String exp, String shortNameVar) {
		super(itemtype, exp);
		this.shortNameVar = shortNameVar;
	}

	/**
	 * Builds the.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param returnInst
	 *            the return inst
	 */
	public void build(GenStringBuilder sb, Set<String> imports, boolean returnInst, String packageName) {
		build("item", "sb", sb, imports, "parent", returnInst, packageName);
	}

	/**
	 * Builds the.
	 * 
	 * @param varName
	 *            the var name
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param returnInst
	 *            the return inst
	 */
	public void build(String varName, GenStringBuilder sb, Set<String> imports, boolean returnInst, String packageName) {
		build("item", varName, sb, imports, "parent", returnInst, packageName);
	}

	/**
	 * Builds the.
	 * 
	 * @param itemVar
	 *            the item var
	 * @param varName
	 *            the var name
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 */
	public void build(String itemVar, String varName, GenStringBuilder sb, Set<String> imports, String packageName) {
		build(itemVar, varName, sb, imports, null, false, packageName);
	}

	/**
	 * Builds the.
	 * 
	 * @param itemVar
	 *            the item var
	 * @param varName
	 *            the var name
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param parentVar
	 *            the parent var
	 * @param returnInst
	 *            the return inst
	 */
	public void build(String itemVar, String varName, GenStringBuilder sb, Set<String> imports, String parentVar,
			boolean returnInst, String packageName) {
		build(itemVar, varName, sb, imports, parentVar, returnInst, false, packageName);
	}

	/**
	 * Builds the.
	 * 
	 * @param itemVar
	 *            the item var
	 * @param varName
	 *            the var name
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param parentVar
	 *            the parent var
	 * @param returnInst
	 *            the return inst
	 * @param useContext
	 *            the use context
	 */
	public void build(String itemVar, String varName, GenStringBuilder sb, Set<String> imports, String parentVar,
			boolean returnInst, boolean useContext, String packageName) {
		new BuildJava(itemVar, varName, sb, imports, parentVar, returnInst, useContext, packageName).build();

	}

	/**
	 * Checks if is valid.
	 * 
	 * @param t
	 *            the t
	 * 
	 * @return true, if is valid
	 */
	protected boolean isValid(Token t) {
		switch (t.kind) {
			case ExpressionParseConstants.CST_SEP:
			case ExpressionParseConstants.CST:
				return true;

			case ExpressionParseConstants.ATTR_QUALIFIED_NAME:
			case ExpressionParseConstants.ATTR_DISPLAY_NAME:
			case ExpressionParseConstants.ATTR_TYPE_NAME:
			case ExpressionParseConstants.ATTR_ID:
				return (_currentItem != null);
			case ExpressionParseConstants.ATTR_NAME:
				return (shortNameVar != null || _currentItem != null);

			case ExpressionParseConstants.ATTR_LINK_TYPE_NAME:
				return hasLT;

			case ExpressionParseConstants.ATTR_LINK:
				fCurrentLink = null;
				if (_currentItem != null) {
					fCurrentLink = ItemTypeManager.getOutgoingLinkType(_currentItem, t.image);
					if (fCurrentLink != null) {
						_currentItem = LinkTypeManager.getDestination(fCurrentLink);
					}
				}
				return fCurrentLink != null && _currentItem != null;
			case ExpressionParseConstants.ATTR:
				_currentAttr = null;
				if (_currentItem != null) {
					_currentAttr = ItemTypeManager.getAttribute(_currentItem, t.image);
				}
				return _currentAttr != null;
			case ExpressionParseConstants.PARENT:
				if (_currentItem != null) {
					Item newParent = ItemTypeManager.getPartParent(_currentItem);
					_currentItem = newParent;
				}
				reset = true;
				return _currentItem != null;
			default:
				break;
		}
		return true;
	}

	/**
	 * Validate.
	 * 
	 * @param reporter
	 *            the reporter
	 * @param itemforreporter
	 *            the itemforreporter
	 * @param t
	 *            the t
	 * 
	 * @return true, if successful
	 */
	protected boolean validate(ProblemReporter reporter, Item itemforreporter, Token t) {
		switch (t.kind) {
			case ExpressionParseConstants.CST_SEP:
			case ExpressionParseConstants.CST:
				return true;

			case ExpressionParseConstants.ATTR_UNIQUE_NAME:
			case ExpressionParseConstants.ATTR_DISPLAY_NAME:
			case ExpressionParseConstants.ATTR_TYPE_NAME:
			case ExpressionParseConstants.ATTR_ID:
				if (_currentItem == null) {
					report(reporter, itemforreporter, t, "The current item is null");
				}
				break;

			case ExpressionParseConstants.ATTR_SHORT_NAME:
				if ((shortNameVar != null || _currentItem != null) == false) {
					report(reporter, itemforreporter, t, "The current item is null");
				}
				break;

			case ExpressionParseConstants.ATTR_LINK_TYPE_NAME:
				return true;

			case ExpressionParseConstants.ATTR:
				_currentAttr = null;
				if (_currentItem != null) {
					_currentAttr = ItemTypeManager.getAttribute(_currentItem, t.image);
				}
				if (_currentAttr == null) {
					report(reporter, itemforreporter, t, "Cannot find the attribute " + t.image);
				}
				return true;

			case ExpressionParseConstants.ATTR_PARENT_ID:
			case ExpressionParseConstants.ATTR_PARENT_UNIQUE_NAME:
			case ExpressionParseConstants.ATTR_PARENT_SHORT_NAME:
			case ExpressionParseConstants.ATTR_PARENT_DISPLAY_NAME:
			case ExpressionParseConstants.ATTR_PARENT_TYPE_NAME:
				if (_currentItem != null) {
					Item newParent = ItemTypeManager.getPartParent(_currentItem);
					_currentItem = newParent;
					if (_currentItem == null) {
						report(reporter, itemforreporter, t, "Cannot find the parent ");
					}
				}
				reset = true;
				break;

		}
		return true;
	}

	/**
	 * Report.
	 * 
	 * @param reporter
	 *            the reporter
	 * @param itemforreporter
	 *            the itemforreporter
	 * @param t
	 *            the t
	 * @param msg
	 *            the msg
	 */
	protected void report(ProblemReporter reporter, Item itemforreporter, Token t, String msg) {
		reporter.warning(itemforreporter, 1, "Error in \\'{0}\\' position {1}  : {2}", _expString, t.beginColumn, msg);
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
	protected final String addEscapes(String str) {
		StringBuilder sb = new StringBuilder();
		char ch;
		boolean slash = false;
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (slash) {
				slash = false;
				if (ch == '$' || ch == '{' || ch == '}') {
					sb.append(ch);
					continue;
				}
				sb.append("\\\\");
			}
			switch (ch) {
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
				case '\\': {
					slash = true;
					continue;
				}
				default:
					if (ch < 0x20 || ch > 0x7e) {
						String s = "0000" + Integer.toString(ch, 16);
						sb.append("\\u" + s.substring(s.length() - 4, s.length()));
					} else {
						sb.append(ch);
					}
					continue;
			}
		}
		if (slash) {
			sb.append("\\\\");
		}
		return sb.toString();
	}

}

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

package fr.imag.adele.cadse.cadseg.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.DataModelManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.path.AbstractParsePath;
import fr.imag.adele.cadse.core.path.PathConstants;

/**
 * The Class ParsePath.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ParsePath extends AbstractParsePath implements IContentProposalProvider {

	/** The source. */
	private final Item				source;

	/** The dest. */
	Item							dest;

	/** The elements. */
	ElementPath[]					elements;

	/** The issingleton. */
	boolean							issingleton;

	/** The datamodel. */
	private Item					datamodel;

	/** The find elements. */
	private ArrayList<ElementPath>	findElements;

	/** The find type. */
	Item							findType;

	/** The lt. */
	Item							lt;

	private Item					cadsedefinition;

	/**
	 * Instantiates a new parses the path.
	 * 
	 * @param source
	 *            the source
	 * @param dest
	 *            the dest
	 * @param path
	 *            the path
	 */
	public ParsePath(Item source, Item dest, String path) {
		super(path);
		this.dest = dest;
		this.source = source;
		this.cadsedefinition = ItemTypeManager.getCadseDefinition(source);
		this.datamodel = CadseDefinitionManager.getMainDataModel(cadsedefinition);
		parse(path, false);
	}

	// private String parse2(String value, boolean partiel) {
	// int lastindex = 0;
	// String s;
	// boolean closure = false;
	// Item findType;
	// Item lt;
	//		
	// int len = value.length();
	// int nextindex = len;
	// error = null;
	//		
	// List<ElementPath> findElements = new ArrayList<ElementPath>();
	// ONE: {
	// if (len == 0) {
	// break ONE;
	// }
	//			
	// int index = value.indexOf('.');
	// if (index == lastindex) {
	// error = "Bad expresion not begin by '.'";
	// break ONE;
	// }
	// nextindex = (index == -1) ? len : index;
	// s = value.substring(lastindex, nextindex);
	// if (s.equals(PathConstants.SELF)) {
	// findElements.add(new SelfPath(source));
	// findType = source;
	// issingleton = true;
	// } else {
	// if (s.endsWith("*")) {
	// closure = true;
	// s = s.substring(0, s.length() - 1);
	// }
	// findType = DataModelManager.getItemType(datamodel, s);
	// valideBeginItemType(findType, s);
	// if (error != null)
	// break ONE;
	// findElements.add(new ItemTypePath(findType, closure));
	// issingleton = false;
	// }
	// while (index != -1) {
	// lastindex = nextindex +1;
	// if (lastindex == len ) {
	// error = "Bad expression not end by '.'";
	// break ONE;
	// }
	// index = value.indexOf('.',lastindex);
	// if (index == lastindex) {
	// error = "Bad expresion find '..'";
	// break ONE;
	// }
	// nextindex = (index == -1) ? len : index;
	// s = value.substring(lastindex, nextindex);
	//			
	// closure = false;
	// if (s.endsWith("*")) {
	// closure = true;
	// s = s.substring(0, s.length() - 1);
	// }
	// if (s.startsWith(PathConstants.PARENT_PATH)) {
	// s = s.substring(PathConstants.PARENT_PATH.length());
	// Item findType2 = DataModelManager.getItemType(datamodel, s);
	// valideItemType(findType2, s);
	// if (error != null)
	// break ONE;
	// if (closure) {
	// findElements.add( new ParentItemTypePath(findType2));
	// } else {
	// lt = ItemTypeManager.getIncomingPart(findType,
	// findType2);
	// valideLinkType(lt, s);
	// if (error != null)
	// break ONE;
	// findElements.add( new ParentPath(lt) );
	// }
	// findType = findType2;
	// continue;
	// }
	// if (s.equals(PathConstants.INCOMING_LINK_PATH)) {
	// s = s.substring(PathConstants.INCOMING_LINK_PATH.length());
	//
	// lt = ItemTypeManager.getIncomingLinkType(findType, s);
	// valideLinkType(lt, s);
	//
	// if (error != null)
	// break ONE;
	// findElements.add( new IncomingLinkTypePath(lt));
	// findType = LinkTypeManager.getSource(lt);
	// continue;
	// }
	// lt = ItemTypeManager.getOutgoingLinkType(findType, s);
	// valideLinkType(lt, s);
	//
	// if (error != null)
	// break ONE;
	// findElements.add( new LinkTypePath(lt, closure));
	// findType = LinkTypeManager.getDestination(lt);
	// }
	// if (!partiel && dest != null
	// && !(ItemTypeManager.isSuperTypeOf(dest, findType) || dest
	// .equals(findType))) {
	// error = "Bad expresion : not a sub type of " + dest;
	// }
	// }
	// elements = (ElementPath[]) findElements.toArray(new
	// ElementPath[findElements.size()]);
	// return value.substring(lastindex);
	// }

	/**
	 * Valide link type.
	 * 
	 * @param lt
	 *            the lt
	 * @param linkTypeName
	 *            the link type name
	 */
	private void valideLinkType(Item lt, String linkTypeName) {
		if (lt == null) {
			error = "Cannot find link type " + linkTypeName;
		}
	}

	/**
	 * Valide item type.
	 * 
	 * @param findType
	 *            the find type
	 * @param typeName
	 *            the type name
	 */
	public void valideItemType(Item findType, String typeName) {
		if (findType == null) {
			error = "Cannot find type " + typeName;
		}

	}

	/**
	 * Valide begin item type.
	 * 
	 * @param findType
	 *            the find type
	 * @param typeName
	 *            the type name
	 */
	public void valideBeginItemType(Item findType, String typeName) {
		if (findType == null) {
			error = "Cannot find type " + typeName;
		}
		// correct par rapport � la destination du lien.
	}

	/**
	 * Gets the elements.
	 * 
	 * @return the elements
	 */
	public ElementPath[] getElements() {
		return elements;
	}

	/**
	 * Gets the source.
	 * 
	 * @return the source
	 */
	public Item getSource() {
		return source;
	}

	/**
	 * Gets the destination.
	 * 
	 * @return the destination
	 */
	public Item getDestination() {
		return dest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (ElementPath ep : elements) {
			if (ep != null)
				sb.append(ep.getText());
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.fieldassist.IContentProposalProvider#getProposals(java.lang.String,
	 *      int)
	 */
	public IContentProposal[] getProposals(String contents, int position) {
		List<IContentProposal> ret = new ArrayList<IContentProposal>();
		contents = contents.substring(0, position);
		/* String nextcontents = */parse(contents, true);
		int pos;
		if (error == null || position == 0) {
			if (position == 0 || elements.length == 0) {
				ret.add(new SelfPath(source));
				if (dest != null) {
					ret.add(new ItemTypePath(dest, false));
					ret.add(new ItemTypePath(dest, true));
					subType(ret, dest);
				}
			} else {
				pos = toString().length();
				ElementPath ep = elements[elements.length - 1];
				Item type = ep.getItemType();

				Map<String, Item> lts = ItemTypeManager.getOugoingLinkTypesH(type);
				for (Item item : lts.values()) {
					Item dest = LinkTypeManager.getDestination(item);
					if (dest == null)
						continue;
					ret.add(new LinkTypePath(item, false, pos));
					if (dest == type) {
						ret.add(new LinkTypePath(item, true, pos));
					}
				}
				lts = ItemTypeManager.getIncomingLinkTypes(type);
				for (Item item : lts.values()) {
					ret.add(new IncomingLinkTypePath(item, pos));
					if (LinkTypeManager.isPart(item)) {
						ret.add(new ParentPath(item, pos));
						Item parent = LinkTypeManager.getSource(item);
						while (parent != null) {
							ParentItemTypePath v = new ParentItemTypePath(parent, pos);
							if (ret.contains(v))
								break;
							ret.add(v);
							parent = ItemTypeManager.getPartParent(parent);
						}
					}
				}
			}
		} else {
			if ((elements == null || elements.length == 0) && PathConstants.SELF.startsWith(contents)) {
				ret.add(new SelfPath(source));
			}
			if (elements != null && elements.length != 0) {
				pos = toString().length();
				ElementPath ep = elements[elements.length - 1];
				Item type = ep.getItemType();

				Map<String, Item> lts = ItemTypeManager.getOugoingLinkTypesH(type);
				for (Item item : lts.values()) {
					Item dest = LinkTypeManager.getDestination(item);
					if (dest == null)
						continue;
					ret.add(new LinkTypePath(item, false, pos));
					if (dest == type) {
						ret.add(new LinkTypePath(item, true, pos));
					}
				}
				lts = ItemTypeManager.getIncomingLinkTypes(type);
				for (Item item : lts.values()) {
					ret.add(new IncomingLinkTypePath(item, pos));
				}
				for (Item item : lts.values()) {
					if (LinkTypeManager.isPart(item)) {
						ret.add(new ParentPath(item, pos));
						Item parent = LinkTypeManager.getSource(item);
						while (parent != null) {
							ret.add(new ParentItemTypePath(parent, pos));
							parent = ItemTypeManager.getPartParent(parent);
						}
					}
				}
			}
		}
		return (IContentProposal[]) ret.toArray(new IContentProposal[ret.size()]);
	}

	/**
	 * Sub type.
	 * 
	 * @param ret
	 *            the ret
	 * @param dest
	 *            the dest
	 */
	private void subType(List<IContentProposal> ret, Item dest) {
		Item[] subTypes = ItemTypeManager.getSubTypes2(dest);
		for (int i = 0; i < subTypes.length; i++) {
			ret.add(new ItemTypePath(subTypes[i], false));
			ret.add(new ItemTypePath(subTypes[i], true));
			subType(ret, subTypes[i]);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.path.AbstractParsePath#beginParse()
	 */
	@Override
	protected void beginParse() {
		findElements = new ArrayList<ElementPath>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.path.AbstractParsePath#endParse(boolean)
	 */
	@Override
	protected void endParse(boolean partiel) {
		if (!partiel && dest != null && !(ItemTypeManager.isSuperTypeOf(dest, findType) || dest.equals(findType))) {
			error = "Bad expresion : not a sub type of " + dest;
		}
		elements = (ElementPath[]) findElements.toArray(new ElementPath[findElements.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.path.AbstractParsePath#parseIncomingLink(java.lang.String,
	 *      boolean)
	 */
	@Override
	protected void parseIncomingLink(String s, boolean closure) {
		lt = ItemTypeManager.getIncomingLinkType(findType, s);
		valideLinkType(lt, s);

		if (error != null)
			return;
		findElements.add(new IncomingLinkTypePath(lt));
		findType = LinkTypeManager.getSource(lt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.path.AbstractParsePath#parseOutgoingLink(java.lang.String,
	 *      boolean)
	 */
	@Override
	protected void parseOutgoingLink(String s, boolean closure) {
		lt = ItemTypeManager.getOutgoingLinkType(findType, s);
		valideLinkType(lt, s);

		if (error != null)
			return;
		findElements.add(new LinkTypePath(lt, closure));
		findType = LinkTypeManager.getDestination(lt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.path.AbstractParsePath#parseParent(java.lang.String,
	 *      boolean)
	 */
	@Override
	protected void parseParent(String s, boolean closure) {
		Item findType2 = DataModelManager.getItemType(datamodel, s);
		valideItemType(findType2, s);
		if (error != null)
			return;
		if (closure) {
			findElements.add(new ParentItemTypePath(findType2));
		} else {
			lt = ItemTypeManager.getIncomingPart(findType, findType2);
			valideLinkType(lt, s);
			if (error != null)
				return;
			findElements.add(new ParentPath(lt));
		}
		findType = findType2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.path.AbstractParsePath#parseSelf()
	 */
	@Override
	protected void parseSelf() {
		findElements.add(new SelfPath(source));
		findType = source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.path.AbstractParsePath#parseType(java.lang.String,
	 *      boolean)
	 */
	@Override
	protected void parseType(String s, boolean closure) {
		findType = DataModelManager.getItemType(datamodel, s);
		valideBeginItemType(findType, s);
		if (error != null)
			return;
		findElements.add(new ItemTypePath(findType, closure));
		issingleton = false;
	}
}
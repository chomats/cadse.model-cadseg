package fr.imag.adele.cadse.cadseg.menu.group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ExtendedType;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.ui.view.DefineNewContext;
import fr.imag.adele.cadse.core.ui.view.FilterContext;
import fr.imag.adele.cadse.core.ui.view.NewContext;
import fr.imag.adele.cadse.core.ui.view.ViewDescription;

public class RuntimeDefinieNewContext implements DefineNewContext {

	@Override
	public void computeNew(FilterContext cxt, List<NewContext> result) {
		ViewDescription view = cxt.getView();
		Item source = cxt.getItemNode() == null ? null : cxt.getItemNode().getItem();
		formIt(cxt, result, view, view.getFirstItemType());
		if (source != null) {
			for (LinkType lt : source.getType().getOutgoingLinkTypes()) {
				if (!view.canCreateDestination(lt))
					continue;
				
				if (lt.isPart() && lt.isGroup() && source instanceof ItemType) {
					for (ItemType it : getAllDestType(lt.getDestination())) {
						if (it.isAbstract()) {
							continue;
						}
						ItemType groupType = ((ItemType) source).getGroupType();
						
						NewContext newContext = new NewContext(cxt);
						newContext.setDestinationType(it);
						newContext.setPartParent(source, lt);
						newContext.setGroupHead((ItemType) source, lt);
						newContext.setGroupType(groupType);
						newContext.setLabel("Member "+source.getDisplayName()+" "+it.getDisplayName());
						if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
						result.add(newContext);
					}
				} 
//				else  {
//					if (lt.getDestination().hasIncomingParts() && !lt.isGroup())
//						continue;
//					
//					for (ItemType it : getAllDestType(lt.getDestination())) {
//						if (it.isAbstract()) {
//							continue;
//						}
//						if (it.hasIncomingParts() && !lt.isGroup())
//							continue;
//						NewContext newContext = new NewContext(cxt);
//						newContext.setDestinationType(it);
//						newContext.setPartParent(source, lt);
//						if (it.isGroupType()) {
//							for (LinkType groupLT : it.getIncomingLinkTypes()) {
//								if (groupLT.isGroup()) {
//									for (ItemType groupHead : getAllSourceGroupHead(groupLT)) {
//										NewContext newContext2 = new NewContext(newContext);
//										newContext2.setGroupHead(groupHead, groupLT);
//										newContext2.setGroupType(groupHead.getGroupType());
//										newContext.setLabel(groupHead.getDisplayName()+" "+it.getDisplayName());
//										
//										if (view.filterNew(newContext2) || !it.canCreateItem(newContext2)) continue;
//										result.add(newContext2);
//									}
//								}
//							}
//						} else {
//							if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
//							newContext.setLabel(it.getDisplayName());
//							result.add(newContext);
//						}
//					}
//				}
			}
		}
	}

	private void formIt(FilterContext cxt, List<NewContext> result,
			ViewDescription view, ItemType[] types) {
		for (ItemType sit : types) {
			for (ItemType it : getAllDestType(sit)) {
				if (it == null || it.isAbstract()) {
					continue;
				}
				if (it.hasIncomingParts()) {
					continue;
				}
				if (it.isGroupType() && it.getGroupType() == null) {
					boolean addGroupHead = true;
					for (LinkType groupLT : it.getIncomingLinkTypes()) {
						if (groupLT.isGroup()) {
							addGroupHead = false;
							for (ItemType groupHead : getAllSourceGroupHead(groupLT)) {
								NewContext newContext = new NewContext(cxt);
								newContext.setDestinationType(it);
								newContext.addOutgoingLink(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, it);
								newContext.setGroupHead(groupHead, groupLT);
								newContext.setGroupType(groupHead
										.getGroupType());
								newContext.setLabel("Head/Member "+ groupHead.getDisplayName()+" "+it.getDisplayName());
								if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
								result.add(newContext);
							}
						}
					}
					if (addGroupHead) {
						NewContext newContext = new NewContext(cxt);
						newContext.setDestinationType(it);
						newContext.addOutgoingLink(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, it);
						newContext.setLabel("Head "+it.getDisplayName());
						result.add(newContext);
					}

				}  else if (it.isMemberType()) {
					for (LinkType groupLT : it.getIncomingLinkTypes()) {
						if (groupLT.isGroup()) {
							for (ItemType groupHead : getAllSourceGroupHead(groupLT)) {
								NewContext newContext = new NewContext(cxt);
								newContext.setDestinationType(it);
								newContext.addOutgoingLink(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, it);
								newContext.setGroupHead(groupHead, groupLT);
								newContext.setGroupType(groupHead
										.getGroupType());
								newContext.setLabel(groupHead.getDisplayName()+" "+it.getDisplayName());
								if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
								result.add(newContext);
							}
						}
					}
				}
			}
		}
	}

	private List<ItemType> getAllDestType(TypeDefinition it) {
		ArrayList<ItemType> subTypes = new ArrayList<ItemType>();
		ArrayList<ItemType> types = new ArrayList<ItemType>();
		add(it, types);
		while (!types.isEmpty()) {
			ItemType gt = types.remove(0);
			subTypes.add(gt);
			types.addAll(Arrays.asList(gt.getSubTypes()));
		}
		return subTypes;
	}

	private void add(TypeDefinition it, ArrayList<ItemType> types) {
		if (it.isMainType())
			types.add((ItemType) it);
		else {
			ExtendedType eit = (ExtendedType) it;
			for (ItemType it2 : eit.getExendsItemType()) {
				types.add(it2);
			}
			
		}
	}

	private List<ItemType> getAllSourceGroupHead(LinkType groupLT) {
		ArrayList<ItemType> groupHeads = new ArrayList<ItemType>();
		ArrayList<ItemType> groupTypes = new ArrayList<ItemType>();
		add(groupLT.getDestination(), groupTypes);
		while (!groupTypes.isEmpty()) {
			ItemType gt = groupTypes.remove(0);
			if (gt.isGroupHead()) {
				groupHeads.add(gt);
			}
			groupTypes.addAll(Arrays.asList(gt.getSubTypes()));
			for (Item i : gt.getItems()) {
				if (i instanceof ItemType) {
					groupHeads.add((ItemType) i);
				}
			}
		}
		return groupHeads;
	}

	private List<Item> getAllSourcePart(LinkType groupLT) {
		return groupLT.getSource().getItems();
	}

	private void computePartNew(ViewDescription view, ItemType it, FilterContext cxt, List<NewContext> result) {
		for (LinkType partLT : it.getIncomingLinkTypes()) {
			if (partLT.isPart() && partLT.isGroup()) {
					if (it.isAbstract()) {
						continue;
					}
					for (ItemType groupHead : getAllSourceGroupHead(partLT)) {
						ItemType groupType = ((ItemType) groupHead).getGroupType();
						NewContext newContext = new NewContext(cxt);
						newContext.setDestinationType(it);
						newContext.setPartParent(groupHead, partLT);
						newContext.setGroupHead((ItemType) groupHead, partLT);
						newContext.setGroupType(groupType);
						newContext.setLabel(groupHead.getDisplayName()+" "+it.getDisplayName());
						if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
						result.add(newContext);
					}
			} else if (partLT.isPart()) {
				for (Item s : getAllSourcePart(partLT)) {
					NewContext newContext = new NewContext(cxt);
					newContext.setDestinationType(it);
					newContext.setPartParent(s, partLT);
					if (it.isGroupType()) {
						for (LinkType groupLT : it.getIncomingLinkTypes()) {
							if (groupLT.isGroup()) {
								for (ItemType groupHead : getAllSourceGroupHead(groupLT)) {
									NewContext newContext2 = new NewContext(newContext);
									newContext2.setGroupHead(groupHead, groupLT);
									newContext2.setGroupType(groupHead.getGroupType());
									newContext2.setLabel(groupHead.getDisplayName()+" "+it.getDisplayName());
									if (view.filterNew(newContext2) || !it.canCreateItem(newContext2)) continue;
									result.add(newContext2);
								}
							}
						}
					} else {
						newContext.setLabel(it.getDisplayName());
						if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
						result.add(newContext);
					}
				}
			}
		}
	}

}

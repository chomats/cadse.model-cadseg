package fr.imag.adele.cadse.cadseg.menu.group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.imag.adele.cadse.core.GroupType;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.ui.view.DefineNewContext;
import fr.imag.adele.cadse.core.ui.view.FilterContext;
import fr.imag.adele.cadse.core.ui.view.NewContext;
import fr.imag.adele.cadse.core.ui.view.ViewDescription;

public class RuntimeDefinieNewContext implements DefineNewContext {

	@Override
	public void computeNew(FilterContext cxt, List<NewContext> result) {
		ViewDescription view = cxt.getView();
		Item source = cxt.getItemNode() == null ? null : cxt.getItemNode().getItem();

		if (source == null) {
			ItemType[] types = view.getCreatableItemType();
			for (ItemType sit : types) {
				for (ItemType it : getAllDestType(sit)) {
					if (it == null || it.isAbstract()) {
						continue;
					}
					if (it.hasIncomingParts()) {
						computePartNew(view, it, cxt, result);
						continue;
					}
					if (it.isMemberType()) {
						for (LinkType groupLT : it.getIncomingLinkTypes()) {
							if (groupLT.isGroup()) {
								for (ItemType groupHead : getAllSourceGroupHead(groupLT)) {
									NewContext newContext = new NewContext(cxt);
									newContext.setDestinationType(it);
									newContext.setGroupHead(groupHead, groupLT);
									newContext.setGroupType(groupHead
											.getGroupType());
									if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
									result.add(newContext);
								}
							}
						}

					} else if (it.isGroupType()) {
						NewContext newContext = new NewContext(cxt);
						newContext.setDestinationType(it);
						if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
						result.add(newContext);
					} else {
						NewContext newContext = new NewContext(cxt);
						newContext.setDestinationType(it);
						if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
						result.add(newContext);
					}
				}
			}
		} else {
			for (LinkType lt : source.getInstanceOutgoingLinkTypes()) {
				if (!view.canCreateDestination(lt))
					continue;
				
				if (lt.isPart() && lt.isMember() && source instanceof ItemType) {
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
						if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
						result.add(newContext);
					}
				} else if (lt.isPart()) {
					for (ItemType it : getAllDestType(lt.getDestination())) {
						if (it.isAbstract()) {
							continue;
						}
						NewContext newContext = new NewContext(cxt);
						newContext.setDestinationType(it);
						newContext.setPartParent(source, lt);
						if (it.isMember()) {
							for (LinkType groupLT : it.getIncomingLinkTypes()) {
								if (groupLT.isGroup()) {
									for (ItemType groupHead : getAllSourceGroupHead(groupLT)) {
										NewContext newContext2 = new NewContext(newContext);
										newContext2.setGroupHead(groupHead, groupLT);
										newContext2.setGroupType(groupHead.getGroupType());
										if (view.filterNew(newContext2) || !it.canCreateItem(newContext2)) continue;
										result.add(newContext2);
									}
								}
							}
						} else {
							if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
							result.add(newContext);
						}
					}
				} else if (lt.isMember()) {
					for (ItemType it : getAllDestType(lt.getDestination())) {
						if (it.isAbstract()) {
							continue;
						}
						ItemType groupType = ((ItemType) source).getGroupType();
						NewContext newContext = new NewContext(cxt);
						newContext.setDestinationType(it);
						newContext.setGroupHead((ItemType) source, lt);
						newContext.setGroupType(groupType);
						if (it.hasIncomingParts()) {
							for (LinkType partLt : it.getIncomingLinkTypes()) {
								if (partLt.isPart()) {
									for (Item parent : getAllSourcePart(partLt)) {
										NewContext newContext2 = new NewContext(newContext);
										newContext2.setPartParent(parent, partLt);
										if (view.filterNew(newContext2) || !it.canCreateItem(newContext2)) continue;
										result.add(newContext2);
									}
								}
							}
							
						} else {
							if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
							result.add(newContext);
						}
							
					}
				} else  {
					for (ItemType it : getAllDestType(lt.getDestination())) {
						if (it.isAbstract()) {
							continue;
						}
						NewContext newContext = new NewContext(cxt);
						newContext.setDestinationType(it);
						newContext.setPartParent(source, lt);
						if (it.isMember()) {
							for (LinkType groupLT : it.getIncomingLinkTypes()) {
								if (groupLT.isGroup()) {
									for (ItemType groupHead : getAllSourceGroupHead(groupLT)) {
										NewContext newContext2 = new NewContext(newContext);
										newContext2.setGroupHead(groupHead, groupLT);
										newContext2.setGroupType(groupHead.getGroupType());
										if (view.filterNew(newContext2) || !it.canCreateItem(newContext2)) continue;
										result.add(newContext2);
									}
								}
							}
						} else {
							if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
							result.add(newContext);
						}
					}
				}
			}
		}

	}

	private List<ItemType> getAllDestType(ItemType it) {
		ArrayList<ItemType> subTypes = new ArrayList<ItemType>();
		ArrayList<ItemType> types = new ArrayList<ItemType>();
		types.add(it);
		while (!types.isEmpty()) {
			ItemType gt = types.remove(0);
			subTypes.add(gt);
			types.addAll(Arrays.asList(gt.getSubTypes()));
		}
		return subTypes;
	}

	private List<ItemType> getAllSourceGroupHead(LinkType groupLT) {
		ArrayList<ItemType> groupHeads = new ArrayList<ItemType>();
		ArrayList<ItemType> groupTypes = new ArrayList<ItemType>();
		groupTypes.add(groupLT.getSource());
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
			if (partLT.isPart() && partLT.isMember()) {
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
						if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
						result.add(newContext);
					}
			} else if (partLT.isPart()) {
				for (Item s : getAllSourcePart(partLT)) {
					NewContext newContext = new NewContext(cxt);
					newContext.setDestinationType(it);
					newContext.setPartParent(s, partLT);
					if (it.isMember()) {
						for (LinkType groupLT : it.getIncomingLinkTypes()) {
							if (groupLT.isGroup()) {
								for (ItemType groupHead : getAllSourceGroupHead(groupLT)) {
									NewContext newContext2 = new NewContext(newContext);
									newContext2.setGroupHead(groupHead, groupLT);
									newContext2.setGroupType(groupHead.getGroupType());
									if (view.filterNew(newContext2) || !it.canCreateItem(newContext2)) continue;
									result.add(newContext2);
								}
							}
						}
					} else {
						if (view.filterNew(newContext) || !it.canCreateItem(newContext)) continue;
						result.add(newContext);
					}
				}
			}
		}
	}

}

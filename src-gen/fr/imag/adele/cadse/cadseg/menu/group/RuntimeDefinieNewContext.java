package fr.imag.adele.cadse.cadseg.menu.group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			ItemType[] types = view.getFirstItemType();
			for (ItemType it : types) {
				if (it == null) {
					continue;
				}

				if (it.hasIncomingParts()) {
					computePartNew(it, cxt, result);
					continue;
				}

				if (it.isMemberType()) {
					for (LinkType groupLT : it.getIncomingLinkTypes()) {
						if (groupLT.isGroup()) {
							for (ItemType groupHead : getAllSourceGroupHead(groupLT)) {
								NewContext newContext = new NewContext(cxt);
								newContext.setDestinationType(it);
								newContext.setGroupHead(groupHead);
								newContext.setGroupLinkType(groupLT);
								newContext.setGroupType(groupHead.getGroupType());
								result.add(newContext);
							}
						}
					}

				} else if (it.isGroupType()) {
					NewContext newContext = new NewContext(cxt);
					newContext.setDestinationType(it);
					result.add(newContext);
				} else {
					NewContext newContext = new NewContext(cxt);
					newContext.setDestinationType(it);
					result.add(newContext);
				}
			}
		} else {
			for (LinkType lt : source.getInstanceOutgoingLinkTypes()) {

			}
		}

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

	private void computePartNew(ItemType it, FilterContext cxt, List<NewContext> result) {
		for (LinkType partLT : it.getIncomingLinkTypes()) {
			if (partLT.isPart()) {
				for (Item s : getAllSourcePart(partLT)) {
					NewContext newContext = new NewContext(cxt);
					newContext.setDestinationType(it);
					newContext.setPartParent(s);
					newContext.setPartLinkType(partLT);
					if (it.isMember()) {
						for (LinkType groupLT : it.getIncomingLinkTypes()) {
							if (groupLT.isGroup()) {
								for (ItemType groupHead : getAllSourceGroupHead(groupLT)) {
									NewContext newContext2 = new NewContext(newContext);
									newContext2.setGroupHead(groupHead);
									newContext2.setGroupLinkType(groupLT);
									newContext2.setGroupType(groupHead.getGroupType());
									result.add(newContext2);
								}
							}
						}
					} else
						result.add(newContext);
				}
			}
		}
	}

}

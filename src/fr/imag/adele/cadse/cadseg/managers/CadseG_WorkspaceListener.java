package fr.imag.adele.cadse.cadseg.managers;

import java.util.HashSet;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.managers.build.CompositeItemTypeManager;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemState;
import fr.imag.adele.cadse.core.WorkspaceListener;
import fr.imag.adele.cadse.core.delta.ImmutableItemDelta;
import fr.imag.adele.cadse.core.delta.ImmutableWorkspaceDelta;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.var.ContextVariable;

public class CadseG_WorkspaceListener extends WorkspaceListener {

	public CadseG_WorkspaceListener() {
		setKind(ListenerKind.BUILD);
		CadseCore.getLogicalWorkspace().addListener(this, 0xFFFFF);
	}

	@Override
	public void workspaceChanged(ImmutableWorkspaceDelta wd) {
		HashSet<Item> toRegenerate = new HashSet<Item>();
		for (ImmutableItemDelta itemDelta : wd.getItems()) {
			Item item = itemDelta.getItem();
			computeToGenerate(toRegenerate, item);
		}

		for (Item item : toRegenerate) {
			final ContentItem contentItem = item.getContentItem();
			if (contentItem != null && (contentItem instanceof IGenerateContent)) {
				IGenerateContent cm = ((IGenerateContent) contentItem);
				cm.generate(ContextVariable.DEFAULT);
			} else {
				if (item.getType().getItemManager() instanceof IBuildManager) {
					((IBuildManager) item.getType().getItemManager()).generate(ContextVariable.DEFAULT, item);
				}
			}
		}
	}

	private void addToGenerate(HashSet<Item> toRegenerate, Item manager) {
		if (manager != null && manager.getState() != ItemState.DELETED) {
			toRegenerate.add(manager);
		}
	}

	boolean computeToGenerate(HashSet<Item> toRegenerate, Item item) {
		if (item.getType() == WorkspaceCST.MANAGER) {
			addToGenerate(toRegenerate, item);
			addToGenerate(toRegenerate, item.getPartParent(WorkspaceCST.CADSE_DEFINITION));
			return true;
		}

		if (item.getType() == WorkspaceCST.CADSE_DEFINITION) {
			addToGenerate(toRegenerate, item);
			return true;
		}

		if (item.getType() == WorkspaceCST.DYNAMIC_ACTIONS) {
			addToGenerate(toRegenerate, item);
			addToGenerate(toRegenerate, item.getPartParent(WorkspaceCST.CADSE_DEFINITION));
			return true;
		}

		if (item.getType() == WorkspaceCST.MENU_ACTION) {
			addToGenerate(toRegenerate, item);
			addToGenerate(toRegenerate, item.getPartParent(WorkspaceCST.CADSE_DEFINITION));
			return true;
		}

		if (item.getType() == WorkspaceCST.COMPOSITE_ITEM_TYPE) {
			Item itemtype = CompositeItemTypeManager.getItemType(item);
			if (itemtype != null) {
				return computeToGenerate(toRegenerate, itemtype);
			}
			return true;
		}

		if (item.getType() == WorkspaceCST.PAGE) {
			addToGenerate(toRegenerate, item);
			addToGenerate(toRegenerate, item.getPartParent(WorkspaceCST.CADSE_DEFINITION));
			return true;
		}

		if (item.getType() == WorkspaceCST.ENUM_TYPE) {
			addToGenerate(toRegenerate, item);
			return true;
		}

		if (item.getType() == WorkspaceCST.EXT_ITEM_TYPE) {
			addToGenerate(toRegenerate, item);
			addToGenerate(toRegenerate, item.getPartParent(WorkspaceCST.CADSE_DEFINITION));
			return true;
		}

		if (item.isInstanceOf(WorkspaceCST.ATTRIBUTE)) {
			computeToGenerate(toRegenerate, item.getPartParent());
			addToGenerate(toRegenerate, item.getPartParent(WorkspaceCST.CADSE_DEFINITION));
			for (Item field : item.getIncomingItems(WorkspaceCST.FIELD_lt_ATTRIBUTE)) {
				computeToGenerate(toRegenerate, field);
			}
			return true;
		}

		if (item.getType() == WorkspaceCST.ITEM_TYPE) {
			Item managerItem = fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager.getManager(item);
			return computeToGenerate(toRegenerate, managerItem);
		}

		if (item.getType() == WorkspaceCST.VIEW) {
			addToGenerate(toRegenerate, item);
			return true;
		}

		while (item.getPartParent() != null) {
			item = item.getPartParent();
			if (computeToGenerate(toRegenerate, item)) {
				return true;
			}
		}
		return false;
	}

}

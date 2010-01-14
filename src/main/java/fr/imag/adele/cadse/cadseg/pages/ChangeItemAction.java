package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;

public class ChangeItemAction implements IActionPage {

	IPage _page;
	public ChangeItemAction(IPage page) {
		_page = page;
	}

	@Override
	public void dispose(UIPlatform uiPlatform) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doCancel(UIPlatform uiPlatform, Object monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFinish(UIPlatform uiPlatform, Object monitor)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTypeId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(UIPlatform uiPlatform) throws CadseException {
		Item manager = null;
		if (!uiPlatform.isModification()) {
			Item baseItem = uiPlatform.getItem();
			Item cadseDef = baseItem.getPartParent(CadseGCST.CADSE_DEFINITION);
			Item mapping = CadseDefinitionManager.getMapping(cadseDef);
			manager  = uiPlatform.getCopy().createItem(CadseGCST.MANAGER, mapping, CadseGCST.MAPPING_MODEL_lt_MANAGERS);
		} else {
			Item baseItem = uiPlatform.getItem();
			manager  = baseItem.getIncomingItem(CadseGCST.MANAGER_lt_ITEM_TYPE);
		}
		associateItem(uiPlatform, manager);
	}

	private void associateItem(UIPlatform uiPlatform, Item manager) {
		IAttributeType<?>[] pageAttributes = _page.getAttributes();
		for (IAttributeType<?> att : pageAttributes) {
			UIField f = uiPlatform.getField(att);
			uiPlatform.setVariable(f.getId().toString(), manager);
		}
	}

	@Override
	public void initAfterUI(UIPlatform uiPlatform) {
		// TODO Auto-generated method stub

	}

}

package fr.imag.adele.cadse.cadseg.pages.mc;

import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.CadseIllegalArgumentException;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.oper.WSODeleteLink;
import fr.imag.adele.cadse.core.transaction.delta.ItemDelta;
import fr.imag.adele.cadse.core.ui.UIField;

public class MC_DestTypeCombo extends MC_AttributesItem {

	private final boolean mandatory = false;
	private final String msg = null;
	boolean init;
	private ItemDelta secondItem;
	// Object defaultValue = null;

	private boolean dialogRunning = false;

	@Override
	protected Object modelToVisual(Object ret) {
		if (ret == null)
			return null;
		IAttributeType<?> attRef = getUIField().getAttributeDefinition();
		if (attRef.getType() == CadseGCST.LINK_TYPE) {
			LinkType lt = (LinkType) attRef;
			List<Link> ret2 = (List<Link>) ret;
			if (lt.getMax() == 1) {
				return ret2.size() >= 1 ? ret2.get(0) : null;
			}
			return ret;
		}
		return ret;
	}

	@Override
	public Object getValue() {
		Item item = _uiPlatform.getItem(getUIField());
		if (item == null) {
			throw new CadseIllegalArgumentException("No item in the context.");
		}
		IAttributeType<?> attRef = getUIField().getAttributeDefinition();
		if (attRef.getType() == CadseGCST.LINK_TYPE) {
			LinkType lt = (LinkType) attRef;
			Link ret = item.getOutgoingLink(lt);
			if (ret == null)
				return null;
			return ret.getDestinationType();
		}

		return null;
	}

	@Override
	public void notifieValueChanged(UIField field, Object value) {

		if (dialogRunning == true)
			return;
		dialogRunning = true;

		try {
			if (secondItem != null)
				try {
					secondItem.delete(false);
				} catch (CadseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			secondItem = _uiPlatform.openInTransactionDialog("sub-element", _uiPlatform.getItem(),
					(LinkType) getUIField().getAttributeDefinition(), (ItemType) value);
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dialogRunning = false;
		}
	}

	@Override
	public boolean validValue(UIField field, Object value) {
		if (field == getUIField()) {
			if (value instanceof ItemType) {
				return false;
			}
			_uiPlatform.setMessageError("Select sub type");
			return true;
		}
		return false;
	}

	@Override
	public boolean validValueChanged(UIField field, Object value) {
		if (value instanceof ItemType) {
			return false;
		}
		_uiPlatform.setMessageError("Select sub type");
		return true;
	}

	@Override
	public void notifieValueDeleted(UIField field, Object oldvalue) {
		if (oldvalue instanceof Link) {
			Link l = (Link) oldvalue;
			WSODeleteLink oper = new WSODeleteLink(l);
			oper.execute();
			CadseCore.registerInTestIfNeed(oper);
		}
	}

	public ItemType getType() {
		return CadseGCST.MC_LINK;
	}

	@Override
	public Object defaultValue() {
		return null;
	}
}

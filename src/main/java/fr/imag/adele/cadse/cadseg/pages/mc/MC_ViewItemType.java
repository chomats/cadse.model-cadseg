/**
 * 
 */
package fr.imag.adele.cadse.cadseg.pages.mc;

import java.util.ArrayList;
import java.util.Collection;

import fr.imag.adele.cadse.cadseg.managers.view.ViewItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewLinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.view.ViewManager;
import fr.imag.adele.cadse.cadseg.pages.ic.IC_DataModelView;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.AbstractModelController;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.UIRunningField;

public class MC_ViewItemType extends AbstractModelController {
	private IC_DataModelView icDataModelView;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.IModelController#defaultValue()
	 */
	public Object defaultValue() {
		return null;
	}

	@Override
	public void initAfterUI(UIField field) {
		super.initAfterUI(field);
		icDataModelView = ((UIRunningField<IC_DataModelView>)_ruiField)._ic;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.IModelController#getValue()
	 */
	public Object getValue() {
		ArrayList<Item> viewmodelItems = new ArrayList<Item>();
		Item view = getItem();
		Collection<Item> viewitemtypes = ViewManager.getViewItemTypes(view);

		for (Item item : viewitemtypes) {
			Item itemtype = ViewItemTypeManager.getItemType(item);
			if (itemtype == null) {
				continue;
			}
			viewmodelItems.add(itemtype);
			Collection<Item> viewlinktypes = ViewItemTypeManager.getViewLinkTypes(item);
			for (Item vlt : viewlinktypes) {
				Item lt = ViewLinkTypeManager.getLinkType(vlt);
				if (lt == null) {
					continue;
				}
				viewmodelItems.add(lt);
			}
		}
		return viewmodelItems.toArray(new Item[viewmodelItems.size()]);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieSubValueAdded(fr.imag.adele.cadse.core.ui.UIField,
	 *      java.lang.Object)
	 */
	public void notifieSubValueAdded(UIField field, Object added) {
		try {
			if (added instanceof Object[]) {
				Object[] arrayadded = (Object[]) added;
				for (Object obj : arrayadded) {
					icDataModelView.addItem((Item) obj);
				}
			} else {
				icDataModelView.addItem((Item) added);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieSubValueRemoved(fr.imag.adele.cadse.core.ui.UIField,
	 *      java.lang.Object)
	 */
	public void notifieSubValueRemoved(UIField field, Object removed) {
		try {
			if (removed instanceof Object[]) {
				Object[] arrayremoved = (Object[]) removed;
				for (Object obj : arrayremoved) {
					icDataModelView.removeItem((Item) obj);
				}
			} else {
				icDataModelView.removeItem((Item) removed);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
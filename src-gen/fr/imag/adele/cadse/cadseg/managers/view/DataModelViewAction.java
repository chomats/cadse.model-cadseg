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

package fr.imag.adele.cadse.cadseg.managers.view;

import java.net.URL;

import org.eclipse.jface.wizard.WizardDialog;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IItemManager;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.impl.ui.DCheckedTreeUI;
import fede.workspace.model.manager.properties.impl.ui.WizardController;

/**
 * The Class DataModelViewAction.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class DataModelViewAction extends IMenuAction {

	/** The manager. */
	private final ViewManager	manager;

	/**
	 * The Class DataModelViewWizardController.
	 */
	class DataModelViewWizardController extends AbstractActionPage {

		/** The ic_mc. */
		IC_DataModelView_Creation	ic_mc;

		/**
		 * Instantiates a new data model view wizard controller.
		 * 
		 * @param ic_mc
		 *            the ic_mc
		 */
		public DataModelViewWizardController(IC_DataModelView_Creation ic_mc) {
			this.ic_mc = ic_mc;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.impl.ui.AbstractActionPage#doFinish(java.lang.Object)
		 */
		@Override
		public void doFinish(Object monitor) throws Exception {
			Object[] selected = (Object[]) checkedTreeUI.getVisualValue();
			for (Object so : selected) {
				System.out.println(so.toString());
				Item dest = (Item) so;
				ItemType type = dest.getType();
				if (type == CadseGCST.ITEM_TYPE) {
					Item viewitemtype = ViewManager.createViewItemType(view, dest);
					int v = ic_mc.isSet(dest, IC_DataModelView_Creation.ROOT_ITEM);
					if (v != -1) {
						ViewItemTypeManager.setIsRootElementAttribute(viewitemtype, v != 0);
					}
				}
			}
			for (Object so : selected) {
				Item dest = (Item) so;
				ItemType type = dest.getType();
				if (type == CadseGCST.LINK) {
					Item viewlinktype = ViewManager.createViewLinkType(view, dest);
					int v = ic_mc.isSet(dest, IC_DataModelView_Creation.AGGREGATION);
					if (v != -1) {
						ViewLinkTypeManager.setAggregationAttribute(viewlinktype, v != 0);
					}
					v = ic_mc.isSet(dest, IC_DataModelView_Creation.CAN_ITEM);
					if (v != -1) {
						ViewLinkTypeManager.setCanCreateItemAttribute(viewlinktype, v != 0);
					}
					v = ic_mc.isSet(dest, IC_DataModelView_Creation.CAN_LINK);
					if (v != -1) {
						ViewLinkTypeManager.setCanCreateLinkAttribute(viewlinktype, v != 0);
					}
				}
			}
		}
	}

	/** The datamodel. */
	Item			datamodel;

	/** The view. */
	Item			view;

	/** The checked tree ui. */
	DCheckedTreeUI	checkedTreeUI;

	/**
	 * Instantiates a new data model view action.
	 * 
	 * @param manager
	 *            the manager
	 * @param view
	 *            the view
	 * @param datamodel
	 *            the datamodel
	 */
	public DataModelViewAction(ViewManager manager, Item view, Item datamodel) {
		this.manager = manager;
		this.datamodel = datamodel;
		this.view = view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#run(fr.imag.adele.cadse.core.IItemNode[])
	 */
	public void run(IItemNode[] selection) throws CadseException {
		try {
			IC_DataModelView_Creation ic_mc = new IC_DataModelView_Creation(new Item[] { datamodel }, view);
			DataModelViewWizardController action = new DataModelViewWizardController(ic_mc);
			checkedTreeUI = new DCheckedTreeUI("sel", "", EPosLabel.none, ic_mc, ic_mc, true, false);
			ic_mc.setCheckedTreeUI(checkedTreeUI);
			WizardController wc = new WizardController(FieldsCore.createWizard(action, FieldsCore.createPage("page1",
					"Select Item Type and Link", "Select Item Type and Link for this view", 3, checkedTreeUI

			)));
			WizardDialog wd = new WizardDialog(null, wc);
			wd.setPageSize(300, 500);
			wd.open();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#getImage()
	 */
	public URL getImage() {
		return datamodel.getType().getImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#getLabel()
	 */
	public String getLabel() {
		String shortName = datamodel.getName();
		if (datamodel.getPartParent().getType() == CadseGCST.CADSE_DEFINITION) {
			shortName = datamodel.getPartParent().getName();
		}
		return "Add data model " + shortName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#getManager()
	 */
	public IItemManager getManager() {
		return this.manager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#isSeparator()
	 */
	public boolean isSeparator() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IMenuAction#getMenuPath()
	 */
	public String getMenuPath() {
		return NEW_MENU;
	}

}
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

package fr.imag.adele.cadse.cadseg.migrate;

import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.DataModelManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.MigrateAction;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;

/**
 * The Class DataCategoryMigrateAction.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class DataCategoryMigrateAction extends MigrateAction {

	/**
	 * Instantiates a new data category migrate action.
	 */
	public DataCategoryMigrateAction() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.MigrateAction#migrate(fr.imag.adele.cadse.core.Item)
	 */
	@Override
	public void migrate(Item item) {
		Item m = ItemTypeManager.getManager(item);
		if (m == null) {
			return;
		}

		String subpackage = m.getAttribute(ManagerManager.SUB_PACKAGE_ATTRIBUTE);
		if (subpackage == null || subpackage.length() == 0) {
			return;
		}

		Item datamodel = ItemTypeManager.getMainDataModel(item);

		Item datacategory = DataModelManager.getOrCreateCategory(datamodel, subpackage);
		if (datacategory == null) {
			return;
		}

		LogicalWorkspaceTransaction t = item.getLogicalWorkspace().createTransaction();
		try {
			t.getItem(item).migratePartLink(datacategory, null);
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t.rollback();
		}
		try {
			t.commit();
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

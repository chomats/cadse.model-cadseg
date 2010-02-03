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
package fr.imag.adele.cadse.cadseg.menu;

import java.util.ArrayList;
import java.util.List;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Menu;
import fr.imag.adele.cadse.core.ui.AbstractActionContributor;
import fr.imag.adele.cadse.core.ui.IActionContributor;
import fr.imag.adele.cadse.core.ui.view.ViewDescription;
import fede.workspace.tool.view.actions.AbstractEclipseMenuAction;
import fr.imag.adele.cadse.cadseg.teamwork.TWCst;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.cadse.cadseg.teamwork.commit.CommitDialog;
import fr.imag.adele.cadse.cadseg.teamwork.commit.CommitState;
import fr.imag.adele.cadse.cadseg.teamwork.update.UpdateDialogPage;
import fr.imag.adele.cadse.cadseg.teamwork.update.UpdateState;

/**
 * @generated
 */
public class TeamWorkMenuActionContributor  extends AbstractActionContributor {

	/**
	 * @generated
	 */
	public TeamWorkMenuActionContributor() {
	}

	public class ImportAction extends AbstractEclipseMenuAction {

		public ImportAction() {
		}

		@Override
		public String getLabel() {
			return "Import...";
		}

		@Override
		public void run(IItemNode[] selection) throws CadseException {
			UpdateState updateState = new UpdateState();

			// show dialog for update
			UpdateDialogPage.openDialog(updateState);
		}

	}

	public class UpdateAction extends AbstractEclipseMenuAction {

		public UpdateAction() {
		}

		@Override
		public String getLabel() {
			return "Update...";
		}

		@Override
		public void run(IItemNode[] selection) throws CadseException {
			UpdateState updateState = new UpdateState();

			// compute initial items to update
			if ((selection != null) && (selection.length != 0)) {
				for (IItemNode itemNode : selection) {
					Item item = itemNode.getItem();
					if (item == null) {
						continue;
					}

					updateState.getDefinition().addItemToUpdate(item.getId(), UpdateState.LAST_REV);
				}
			}

			// show dialog for update
			UpdateDialogPage.openDialog(updateState);
		}
		
		@Override
		public boolean isEnable(IItemNode[] selection) {
			if ((selection == null) || (selection.length == 0)) {
				return true;
			}
			for (IItemNode itemNode : selection) {
				Item item = itemNode.getItem();
				if (item == null) {
					continue;
				}
				
				if (TWUtil.cannotUpdate(item))
					return false;
			}

			return super.isEnable(selection);
		}
	}

	public class UpdateToLastAction extends AbstractEclipseMenuAction {

		public UpdateToLastAction() {
		}

		@Override
		public String getLabel() {
			return "Update to HEAD";
		}

		@Override
		public void run(IItemNode[] selection) throws CadseException {
			UpdateState updateState = new UpdateState();

			// compute initial items to update
			if ((selection != null) && (selection.length != 0)) {
				for (IItemNode itemNode : selection) {
					Item item = itemNode.getItem();
					if (item == null) {
						continue;
					}

					updateState.getDefinition().addItemToUpdate(item.getId(), UpdateState.LAST_REV);
				}
			}

			// show dialog for update
			UpdateDialogPage.openDialog(updateState);
		}

		@Override
		public boolean isEnable(IItemNode[] selection) {
			if ((selection == null) || (selection.length == 0)) {
				return false;
			}
			for (IItemNode itemNode : selection) {
				Item item = itemNode.getItem();
				if (item == null) {
					continue;
				}
				
				if (TWUtil.cannotUpdate(item))
					return false;
			}

			return super.isEnable(selection);
		}
	}

	public class RevertAction extends AbstractEclipseMenuAction {

		public RevertAction() {
		}

		@Override
		public String getLabel() {
			return "Revert";
		}

		@Override
		public void run(IItemNode[] selection) throws CadseException {
			UpdateState updateState = new UpdateState();

			// compute initial items to update
			if ((selection != null) && (selection.length != 0)) {
				for (IItemNode itemNode : selection) {
					Item item = itemNode.getItem();
					if (item == null) {
						continue;
					}

					updateState.getDefinition().addItemToRevert(item.getId());
				}
			}

			// show dialog for update
			UpdateDialogPage.openDialog(updateState);
		}

		@Override
		public boolean isEnable(IItemNode[] selection) {
			if ((selection == null) || (selection.length == 0)) {
				return false;
			}
			for (IItemNode itemNode : selection) {
				Item item = itemNode.getItem();
				if (item == null) {
					continue;
				}
				
				if (TWUtil.cannotRevert(item))
					return false;
			}

			return super.isEnable(selection);
		}
	}
	
	public class ResetStateAction extends AbstractEclipseMenuAction {

		public ResetStateAction() {
		}

		@Override
		public String getLabel() {
			return "ResetState";
		}

		@Override
		public void run(IItemNode[] selection) throws CadseException {
			if ((selection != null) && (selection.length != 0)) {
				for (IItemNode itemNode : selection) {
					Item item = itemNode.getItem();
					if (item == null) {
						continue;
					}

					TWUtil.resetTWState(item);
				}
			}
		}

		@Override
		public boolean isEnable(IItemNode[] selection) {
			if ((selection == null) || (selection.length == 0)) {
				return false;
			}

			return super.isEnable(selection);
		}
	}

	public class CommitAction extends AbstractEclipseMenuAction {

		public CommitAction() {
		}

		@Override
		public String getLabel() {
			return "Commit...";
		}

		@Override
		public void run(IItemNode[] selection) throws CadseException {
			CommitState commitState = new CommitState();

			// compute initial items to commit
			if ((selection != null) && (selection.length != 0)) {
				for (IItemNode itemNode : selection) {
					Item item = itemNode.getItem();
					if (item == null) {
						continue;
					}
					
					if (!TWUtil.cannotCommit(item))
						commitState.addItemToCommit(item.getId());
				}
			}

			// show dialog for commiting
			CommitDialog.openDialog(commitState);
		}

		@Override
		public boolean isEnable(IItemNode[] selection) {
			if ((selection == null) || (selection.length == 0)) {
				return false;
			}
			for (IItemNode itemNode : selection) {
				Item item = itemNode.getItem();
				if (item == null) {
					continue;
				}
				
				if (TWUtil.cannotCommit(item))
					return false;
			}

			return super.isEnable(selection);
		}
	}

	public void contributeMenuAction(ViewDescription viewDescription, Menu menu, IItemNode[] selection) {
		List<IMenuAction> actions = new ArrayList<IMenuAction>();
		actions.add(new CommitAction());
		actions.add(new UpdateToLastAction());
		actions.add(new UpdateAction());
		actions.add(new ImportAction());
		actions.add(new RevertAction());
		if (TWCst.DEBUG_MODE) {
			actions.add(new ResetStateAction());
		}

		try {
			Menu teamMenu = new Menu("Team", "Team", null, // new java.net.URL("icons/twMenu.gif"),
					actions);
			menu.insert(IMenuAction.CONTEXT_1_MENU, teamMenu, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
